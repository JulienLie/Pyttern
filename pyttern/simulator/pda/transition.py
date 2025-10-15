from abc import ABC, abstractmethod
from dataclasses import dataclass

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
    macro_name: str
    transformation_name: str
    args: list[str]

    def to_json(self):
        return {
            "type": "CallTransition",
            "macro_name": self.macro_name,
            "transformation_name": self.transformation_name,
            "args": self.args
        }


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