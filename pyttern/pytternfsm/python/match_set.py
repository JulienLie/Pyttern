"""Data models representing match results and collections of pattern matches."""
from __future__ import annotations

from dataclasses import dataclass
from typing import TYPE_CHECKING, Any

from antlr4 import ParserRuleContext

if TYPE_CHECKING:
    from ...simulator.configuration import Environment
    from ...simulator.pda.transition import Transition


@dataclass
class Match:
    """Represents an individual pattern match trace in the target AST.

    Attributes:
        n_step: Number of simulation steps taken to achieve the match.
        bindings: Dictionary mapping wildcard variable names to AST node values or an Environment.
        matches: List of tuples associating transitions with their matched AST rule contexts.
    """

    n_step: int
    bindings: dict[str, Any] | Environment
    matches: list[tuple[Transition, ParserRuleContext]]  # List of (Transition, code_node) pairs


class MatchSet:
    """Collection container for accumulating multiple match candidates."""

    def __init__(self) -> None:
        """Initialize an empty match container."""
        self.matches: list[Match] = []

    def record(self, match: Match) -> None:
        """Record a successful match result.

        Args:
            match: Match instance to append.
        """
        self.matches.append(match)

    def count(self) -> int:
        """Return the number of matches recorded.

        Returns:
            int: Match count.
        """
        return len(self.matches)

    def __str__(self) -> str:
        return f"MatchSet with {self.count()} matches: {self.matches}"

    def __repr__(self) -> str:
        """Return official representation of the match set.

        Returns:
            str: String representation.
        """
        return self.__str__()


__all__ = ["Match", "MatchSet"]
