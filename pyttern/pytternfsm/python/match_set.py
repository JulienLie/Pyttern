from __future__ import annotations

from dataclasses import dataclass
from typing import Any, TYPE_CHECKING

from antlr4 import ParserRuleContext

from ...simulator.pda.transition import Transition

if TYPE_CHECKING:
    from ...simulator.configuration import Environment


@dataclass
class Match:
    n_step: int
    bindings: dict[str, Any] | Environment
    matches: list[tuple[Transition, ParserRuleContext]]  # List of (Transition, code_node) pairs

class MatchSet:
    def __init__(self) -> None:
        self.matches: list[Match] = []

    def record(self, match: Match) -> None:
        self.matches.append(match)

    def count(self) -> int:
        return len(self.matches)

    def __str__(self) -> str:
        return f"MatchSet with {self.count()} matches: {self.matches}"

    def __repr__(self) -> str:
        return self.__str__()
