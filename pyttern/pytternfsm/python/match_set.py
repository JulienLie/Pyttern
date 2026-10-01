"""Data models representing match results and collections of pattern matches."""

from dataclasses import dataclass
from typing import Any

from antlr4 import ParserRuleContext

from ...simulator.pda.transition import Transition


@dataclass
class Match:
    """Represents an individual pattern match trace in the target AST.

    Attributes:
        n_step: Number of simulation steps taken to achieve the match.
        bindings: Dictionary mapping wildcard variable names to AST node values.
        matches: List of tuples associating transitions with their matched AST rule contexts.
    """

    n_step: int
    bindings: dict[str, Any]
    matches: list[tuple[Transition, ParserRuleContext]]  # List of (Transition, code_node) pairs


class MatchSet:
    """Collection container for accumulating multiple match candidates."""

    def __init__(self) -> None:
        """Initialize an empty match container."""
        self.matches: list[Match] = []

    def record(self, match: Match) -> None:
        """Add a match instance to the collection.

        Args:
            match: Match instance to record.
        """
        self.matches.append(match)

    def count(self) -> int:
        """Return the total number of recorded matches.

        Returns:
            int: Number of matches.
        """
        return len(self.matches)

    def __str__(self) -> str:
        """Return summary string representation of the match set.

        Returns:
            str: Human-readable match count and summary.
        """
        return f"MatchSet with {self.count()} matches: {self.matches}"

    def __repr__(self) -> str:
        """Return official representation of the match set.

        Returns:
            str: String representation.
        """
        return self.__str__()

