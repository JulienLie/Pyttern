from __future__ import annotations

from collections.abc import ItemsView, Iterator, KeysView, Mapping, ValuesView
from dataclasses import dataclass
from typing import Any, NamedTuple

from antlr4.ParserRuleContext import ParserRuleContext
from antlr4.tree.Tree import Tree

_PRIVATE_TOKEN = object()


class Configuration(NamedTuple):
    """
    The simulation state tuple capturing current automaton state, parse tree node,
    action, environment, and call stack history.
    """
    state: Any
    parse_tree: Tree | ParserRuleContext
    action: str
    environment: Environment
    history: list[Any]

    @property
    def stack(self) -> str:
        return self.action

    @property
    def node(self) -> Tree | ParserRuleContext:
        return self.parse_tree


@dataclass(frozen=True)
class Environment:
    mapping: dict[str, Any]

    def __init__(self, mapping: Mapping[str, Any] | None = None, *, _secret: object = None) -> None:
        if _secret is not _PRIVATE_TOKEN:
            raise RuntimeError(
                "Environment constructor is private. Use Environment.empty() or merge()."
            )
        object.__setattr__(self, 'mapping', dict(mapping) if mapping is not None else {})

    @classmethod
    def _create(cls, mapping: Mapping[str, Any]) -> Environment:
        return cls(mapping, _secret=_PRIVATE_TOKEN)

    @classmethod
    def empty(cls) -> Environment:
        return cls._create({})

    @classmethod
    def from_dict(cls, mapping: Mapping[str, Any] | Environment | None = None) -> Environment:
        if mapping is None:
            return cls.empty()
        if isinstance(mapping, Environment):
            return mapping
        return cls._create(dict(mapping))

    def merge(self, other: Environment | Mapping[str, Any]) -> Environment | None:
        if not isinstance(other, Environment):
            other = Environment.from_dict(other)
        from .Matcher import Matcher
        from ..antlr.python import Python3Parser
        for key, val in self.mapping.items():
            if val is not None and key in other.mapping and other.mapping[key] is not None:
                other_val = other.mapping[key]
                if val == other_val:
                    continue
                from antlr4.tree.Tree import Tree
                if isinstance(val, Tree) and isinstance(other_val, Tree):
                    if Matcher._match_tree(val, other_val) or Matcher._match_tree(other_val, val):
                        continue
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

    def bind(self, key: str, value: Any) -> Environment:
        new_mapping = self.mapping.copy()
        new_mapping[key] = value
        return self._create(new_mapping)

    def update(self, other: Environment | Mapping[str, Any]) -> Environment:
        new_mapping = self.mapping.copy()
        if isinstance(other, Environment):
            new_mapping.update(other.mapping)
        else:
            new_mapping.update(other)
        return self._create(new_mapping)

    def join(self, other: Environment | Mapping[str, Any]) -> Environment:
        new_mapping = self.mapping.copy()
        other_mapping = other.mapping if isinstance(other, Environment) else other
        for key, value in other_mapping.items():
            if value is not None:
                new_mapping[key] = value
        return self._create(new_mapping)

    def copy(self) -> Environment:
        return self._create(self.mapping.copy())

    def get(self, key: str, default: Any = None) -> Any:
        return self.mapping.get(key, default)

    def items(self) -> ItemsView[str, Any]:
        return self.mapping.items()

    def keys(self) -> KeysView[str]:
        return self.mapping.keys()

    def values(self) -> ValuesView[Any]:
        return self.mapping.values()

    def __getitem__(self, key: str) -> Any:
        return self.mapping[key]

    def __contains__(self, key: object) -> bool:
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
        items: list[tuple[str, int]] = []
        for k in sorted(self.mapping.keys()):
            v = self.mapping[k]
            try:
                h = hash(v)
            except TypeError:
                h = id(v)
            items.append((k, h))
        return hash(tuple(items))

    def __str__(self) -> str:
        pretty_mapping = {
            v: (n.getText() if hasattr(n, 'getText') else str(n)) if n is not None else None
            for v, n in self.mapping.items()
        }
        return f"Environment({pretty_mapping})"

    def __repr__(self) -> str:
        pretty_mapping = {
            v: (n.getText() if hasattr(n, 'getText') else str(n)) if n is not None else None
            for v, n in self.mapping.items()
        }
        return f"Environment({pretty_mapping!r})"
