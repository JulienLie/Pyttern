from __future__ import annotations

from dataclasses import dataclass, field
from typing import Any, Optional, Sequence
from pyttern.pytternfsm.python.match_set import MatchSet


@dataclass(frozen=True)
class MatchResult:
    """
    An immutable result object produced by matching a pattern against code,
    containing boolean status, node bindings, and hierarchical child results.
    """
    matched: bool
    name: str
    matches: Optional[MatchSet] = None
    children: tuple[MatchResult, ...] = field(default_factory=tuple)

    def __post_init__(self) -> None:
        if not isinstance(self.children, tuple):
            object.__setattr__(self, "children", tuple(self.children))

    def __bool__(self) -> bool:
        return bool(self.matched)

    @property
    def bindings(self) -> list[Any]:
        """List of binding environments across all recorded matches."""
        if self.matches is not None:
            return [m.bindings for m in self.matches.matches]
        return []

    @property
    def binding(self) -> Optional[Any]:
        """Convenience property for accessing the bindings of the first match."""
        if self.matches is not None and self.matches.matches:
            return self.matches.matches[0].bindings
        return None

    @property
    def leaf_results(self) -> list[MatchResult]:
        """Returns all leaf MatchResult instances in depth-first order."""
        if not self.children:
            return [self]
        leaves: list[MatchResult] = []
        for child in self.children:
            leaves.extend(child.leaf_results)
        return leaves

    def __getitem__(self, key: str) -> Any:
        if key in ("matched", "result"):
            return self.matched
        if key == "name":
            return self.name
        if key == "matches":
            return self.matches
        if key == "children":
            return self.children
        if key == "bindings":
            return self.bindings
        raise KeyError(key)

    def to_dict(self) -> dict[str, Any]:
        """
        Serializes the match result hierarchy into the dictionary format expected by adapters.
        """
        d: dict[str, Any] = {
            "name": self.name,
            "result": self.matched,
        }
        if self.children:
            d["children"] = [child.to_dict() for child in self.children]
        if self.matches is not None or not self.children:
            d["matches"] = self.matches
        return d
