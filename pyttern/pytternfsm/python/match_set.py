from dataclasses import dataclass

@dataclass
class Match:
    n_step: int
    bindings: dict

class MatchSet:
    def __init__(self):
        self.matches = []

    def record(self, match: Match):
        self.matches.append(match)

    def count(self):
        return len(self.matches)
