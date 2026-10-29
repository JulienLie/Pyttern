"""Data structures representing PDA transitions and their matching conditions."""
from __future__ import annotations

from abc import ABC, abstractmethod
from dataclasses import dataclass
from typing import Any

from .PDA_alphabets import NavigationAlphabet, StackAlphabet


class TransitionCondition(ABC):
    """Abstract base class for conditions evaluated during a PDA transition."""

    @abstractmethod
    def to_json(self) -> dict[str, Any]:
        """Serialize the transition condition to a JSON-compatible dictionary.

        Returns:
            dict[str, Any]: Serialized condition data.
        """
        ...


@dataclass
class NodeTransition(TransitionCondition):
    """Condition that matches an AST rule node name.

    Attributes:
        name: Name of the AST node rule to match.
        down: Number of levels to descend in the tree.
        up: Number of levels to ascend in the tree.
    """

    name: str
    down: int | float = 1
    up: int | float = 1

    def to_json(self) -> dict[str, Any]:
        """Serialize the node transition condition to a dictionary.

        Returns:
            dict[str, Any]: Serialized node condition attributes.
        """
        return {
            "type": "NodeTransition",
            "name": self.name,
            "down": self.down,
            "up": self.up,
        }


@dataclass
class NamedTransition(TransitionCondition):
    """Condition representing a named wildcard variable match.

    Attributes:
        name: Name of the wildcard variable binding (e.g. 'var' for $var).
    """

    name: str

    def to_json(self) -> dict[str, Any]:
        """Serialize the named transition condition to a dictionary.

        Returns:
            dict[str, Any]: Serialized named wildcard condition attributes.
        """
        return {
            "type": "NamedTransition",
            "name": self.name,
        }


@dataclass
class CallTransition(TransitionCondition):
    """Condition representing a call to an external subpattern.

    Attributes:
        subpattern_name: Identifier of the invoked subpattern.
        args: List of argument variable names passed to the subpattern.
        context_type: Target AST context type.
        call_id: Unique invocation identifier.
    """

    subpattern_name: str
    args: list[str]
    context_type: Any = None
    call_id: int | None = None

    def to_json(self) -> dict[str, Any]:
        """Serialize the call transition condition to a dictionary.

        Returns:
            dict[str, Any]: Serialized subpattern call condition attributes.
        """
        ctx_name = (
            self.context_type.__name__
            if isinstance(self.context_type, type)
            else (self.context_type.__class__.__name__ if self.context_type is not None else None)
        )
        res: dict[str, Any] = {
            "type": "CallTransition",
            "subpattern_name": self.subpattern_name,
            "args": self.args,
            "context_type": ctx_name,
        }
        if self.call_id is not None:
            res["call_id"] = self.call_id
        return res

    def __str__(self) -> str:
        """Return the string representation of the subpattern call.

        Returns:
            str: Function-call formatted string (e.g. 'subpattern(args)').
        """
        if self.call_id is not None:
            return f"{self.subpattern_name}({self.args})#{self.call_id}"
        return f"{self.subpattern_name}({self.args})"


@dataclass(frozen=True)
class Transition:
    """Represents a transition in a Pushdown Automaton (PDA).

    A transition is a 6-tuple (q, alpha, A, t, q_prime, beta) where:
        q: Source state index.
        alpha: Expected stack symbol(s) to pop/match.
        A: Input symbol / condition (Node, Named wildcard, or Call).
        t: Sequence of tree navigation directions to take.
        q_prime: Target state index.
        beta: Stack symbol(s) to push.

    Attributes:
        q: Current state identifier.
        alpha: Popped stack symbol or empty string.
        A: Condition matching the AST node or subpattern.
        t: List of navigation directions to traverse.
        q_prime: Target destination state identifier.
        beta: Pushed stack symbol or empty string.
    """

    q: int
    alpha: str | list[StackAlphabet]
    A: TransitionCondition
    t: list[NavigationAlphabet]
    q_prime: int
    beta: str | list[StackAlphabet]


__all__ = [
    "TransitionCondition",
    "NodeTransition",
    "NamedTransition",
    "CallTransition",
    "Transition",
]
