from dataclasses import dataclass

from antlr4 import ParserRuleContext

from ...simulator.pda.transition import Transition


@dataclass
class Match:
    n_step: int
    bindings: dict
    matches: list[tuple[Transition, ParserRuleContext]]  # List of (Transition, code_node) pairs

class MatchSet:
    def __init__(self):
        self.matches = []

    def record(self, match: Match):
        self.matches.append(match)

    def count(self):
        return len(self.matches)

    def __str__(self):
        return f"MatchSet with {self.count()} matches: {self.matches}"

    def __repr__(self):
        return self.__str__()
