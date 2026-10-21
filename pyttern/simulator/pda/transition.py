from __future__ import annotations

from abc import ABC, abstractmethod
from dataclasses import dataclass
from typing import Any

from .PDA_alphabets import NavigationAlphabet, StackAlphabet


class TransitionCondition(ABC):
    @abstractmethod
    def to_json(self) -> dict[str, Any]:
        ...


@dataclass
class NodeTransition(TransitionCondition):
    name: str
    down: int | float = 1
    up: int | float = 1

    def to_json(self) -> dict[str, Any]:
        return {
            "type": "NodeTransition",
            "name": self.name,
            "down": f"{self.down}",
            "up": f"{self.up}",
        }


@dataclass
class NamedTransition(TransitionCondition):
    name: str

    def to_json(self) -> dict[str, Any]:
        return {
            "type": "NamedTransition",
            "name": self.name,
        }


@dataclass
class CallTransition(TransitionCondition):
    subpattern_name: str
    args: list[str]
    context_type: Any = None

    def to_json(self) -> dict[str, Any]:
        ctx_name = (
            self.context_type.__name__
            if isinstance(self.context_type, type)
            else (self.context_type.__class__.__name__ if self.context_type is not None else None)
        )
        return {
            "type": "CallTransition",
            "subpattern_name": self.subpattern_name,
            "args": self.args,
            "context_type": ctx_name,
        }

    def __str__(self) -> str:
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
