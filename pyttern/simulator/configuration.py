"""
Environment and variable binding representation for Pyttern.
"""

from __future__ import annotations

from dataclasses import dataclass
from typing import Optional, Iterator, Any

from antlr4.tree.Tree import Tree
from loguru import logger

_PRIVATE_TOKEN = object()


_Matcher = None
_Python3Parser = None


def _get_matcher_and_parser():
    global _Matcher, _Python3Parser
    if _Matcher is None:
        from .Matcher import Matcher
        from ..antlr.python import Python3Parser
        _Matcher = Matcher
        _Python3Parser = Python3Parser
    return _Matcher, _Python3Parser


@dataclass(frozen=True)
class Environment:
    """
    Immutable mapping of wildcard variable names to bound AST nodes or values.
    """

    mapping: dict[str, object]
    _cached_hash: int | None = None

    def __init__(self, mapping: dict[str, object], *, _secret: object = None):
        if _secret is not _PRIVATE_TOKEN:
            raise RuntimeError(
                "Environment constructor is private. Use Environment.empty() or merge()."
            )
        object.__setattr__(self, 'mapping', mapping)
        object.__setattr__(self, '_cached_hash', None)

    @classmethod
    def _create(cls, mapping: dict[str, object]) -> 'Environment':
        """
        Internal factory to create an Environment instance.

        Args:
            mapping (dict[str, object]): The dictionary of variable bindings.

        Returns:
            Environment: New immutable environment instance.
        """
        return cls(mapping, _secret=_PRIVATE_TOKEN)

    @classmethod
    def empty(cls) -> 'Environment':
        """
        Creates an empty Environment with no variable bindings.

        Returns:
            Environment: An empty environment.
        """
        return cls._create({})

    @classmethod
    def from_dict(cls, mapping: dict[str, object] | 'Environment' | None = None) -> 'Environment':
        """
        Creates an Environment from a dictionary or an existing Environment.

        Args:
            mapping (dict or Environment, optional): Initial variable mapping.

        Returns:
            Environment: An initialized environment.
        """
        if mapping is None:
            return cls.empty()
        if isinstance(mapping, Environment):
            return mapping
        return cls._create(dict(mapping))

    def merge(self, other: 'Environment') -> Optional['Environment']:
        """
        Merges this environment with another, checking for conflicting bindings.

        Equivalent AST trees or identical mock values are combined, preferring
        NameContext unwrapped nodes when available.

        Args:
            other (Environment): The other environment to merge with.

        Returns:
            Optional[Environment]: Merged environment if compatible, None on conflict.
        """
        if not self.mapping:
            return other if isinstance(other, Environment) else Environment.from_dict(other)
        if not isinstance(other, Environment):
            other = Environment.from_dict(other)
        if not other.mapping:
            return self
        Matcher, Python3Parser = _get_matcher_and_parser()
        for key, val in self.mapping.items():
            if val is not None and key in other.mapping and other.mapping[key] is not None:
                other_val = other.mapping[key]
                if val is other_val or val == other_val:
                    continue
                if isinstance(val, Tree) and isinstance(other_val, Tree):
                    if Matcher._match_tree(val, other_val) or Matcher._match_tree(other_val, val):
                        continue
                logger.trace(f"Environment merge conflict on key '{key}': {val} vs {other_val}")
                return None

        merged = self.mapping.copy()
        for key, val in other.mapping.items():
            if val is not None:
                if key in merged and merged[key] is not None:
                    curr_val = merged[key]
                    if isinstance(val, Python3Parser.NameContext) and not isinstance(curr_val, Python3Parser.NameContext):
                        merged[key] = val
                else:
                    merged[key] = val
            elif key not in merged:
                merged[key] = val
        return self._create(merged)

    def bind(self, key: str, value: object) -> 'Environment':
        """
        Binds a variable key to a new value, returning a new Environment.

        Args:
            key (str): Variable name.
            value (object): Bound AST node or value.

        Returns:
            Environment: A new environment with the updated binding.
        """
        new_mapping = self.mapping.copy()
        new_mapping[key] = value
        return self._create(new_mapping)

    def update(self, other: 'Environment' | dict[str, object]) -> 'Environment':
        """
        Updates this environment with all keys from another environment or dictionary.

        Args:
            other (Environment or dict): Key-value pairs to overwrite with.

        Returns:
            Environment: A new updated environment.
        """
        new_mapping = self.mapping.copy()
        if isinstance(other, Environment):
            new_mapping.update(other.mapping)
        else:
            new_mapping.update(other)
        return self._create(new_mapping)

    def join(self, other: 'Environment' | dict[str, object]) -> 'Environment':
        """
        Joins non-None values from other into this environment.

        Args:
            other (Environment or dict): Source of overriding non-None values.

        Returns:
            Environment: A new joined environment.
        """
        new_mapping = self.mapping.copy()
        other_mapping = other.mapping if isinstance(other, Environment) else other
        for key, value in other_mapping.items():
            if value is not None:
                new_mapping[key] = value
        return self._create(new_mapping)

    def copy(self) -> 'Environment':
        """
        Creates a shallow copy of this environment.

        Returns:
            Environment: Cloned environment.
        """
        return self._create(self.mapping.copy())

    def get(self, key: str, default: Any = None) -> Any:
        """
        Retrieves the value bound to key, or default if missing.

        Args:
            key (str): Variable name.
            default (Any, optional): Fallback value.

        Returns:
            Any: Bound value or default.
        """
        return self.mapping.get(key, default)

    def items(self):
        """Returns iterator of (key, value) pairs."""
        return self.mapping.items()

    def keys(self):
        """Returns iterator of variable names."""
        return self.mapping.keys()

    def values(self):
        """Returns iterator of bound values."""
        return self.mapping.values()

    def __getitem__(self, key: str) -> object:
        return self.mapping[key]

    def __contains__(self, key: str) -> bool:
        return key in self.mapping

    def __iter__(self) -> Iterator[str]:
        return iter(self.mapping)

    def __len__(self) -> int:
        return len(self.mapping)

    def __eq__(self, other: object) -> bool:
        if isinstance(other, Environment):
            return self.mapping == other.mapping
        if isinstance(other, dict):
            return self.mapping == other
        return False

    def __hash__(self) -> int:
        h = self._cached_hash
        if h is None:
            items = []
            for k in sorted(self.mapping.keys()):
                v = self.mapping[k]
                try:
                    vh = hash(v)
                except TypeError:
                    vh = id(v)
                items.append((k, vh))
            h = hash(tuple(items))
            object.__setattr__(self, '_cached_hash', h)
        return h

    @staticmethod
    def _format_val(n: object) -> str | None:
        """Helper to safely format AST node or mock object for string representation."""
        if n is None:
            return None
        if hasattr(n, "getText"):
            return n.getText()
        return str(n)

    def __str__(self) -> str:
        pretty_mapping = {v: self._format_val(n) for v, n in self.mapping.items()}
        return f"Environment({pretty_mapping})"

    def __repr__(self) -> str:
        pretty_mapping = {v: self._format_val(n) for v, n in self.mapping.items()}
        return f"Environment({pretty_mapping!r})"