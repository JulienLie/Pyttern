from abc import ABC, abstractmethod
from dataclasses import dataclass
from typing import Optional

from .PDA_alphabets import NavigationAlphabet, StackAlphabet


class TransitionCondition(ABC):
    @abstractmethod
    def to_json(self):
        ...

@dataclass
class NodeTransition(TransitionCondition):
    name: str
    down: int = 1
    up: int = 1

    def to_json(self):
        return {
            "type": "NodeTransition",
            "name": self.name,
            "down": f"{self.down}",
            "up": f"{self.up}"
        }


@dataclass
class NamedTransition(TransitionCondition):
    name: str

    def to_json(self):
        return {
            "type": "NamedTransition",
            "name": self.name
        }


@dataclass
class CallTransition(TransitionCondition):
    subpattern_name: str
    args: list[str]
    call_id: Optional[int] = None

    def to_json(self):
        res = {
            "type": "CallTransition",
            "subpattern_name": self.subpattern_name,
            "args": self.args
        }
        if self.call_id is not None:
            res["call_id"] = self.call_id
        return res
    
    def __str__(self):
        if self.call_id is not None:
            return f"{self.subpattern_name}({self.args})#{self.call_id}"
        return f"{self.subpattern_name}({self.args})"


@dataclass(frozen=True)
class Transition:
    """
    A transition is a 6-tuple (q, a, A, t, q', α) where:
        - q is the current state
        - a is the stack symbol
        - A is the input symbol
        - t is the navigation direction
        - q' is the next state
        - β is the stack replacement
    """
    q: int
    alpha: str | list[StackAlphabet]
    A: TransitionCondition
    t: list[NavigationAlphabet]
    q_prime: int
    beta: str | list[StackAlphabet]
