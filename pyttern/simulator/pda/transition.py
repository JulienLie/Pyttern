"""Data structures representing PDA transitions and their matching conditions."""

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
    down: int = 1
    up: int = 1

    def to_json(self) -> dict[str, Any]:
        """Serialize the node transition condition to a dictionary.

        Returns:
            dict[str, Any]: Serialized node condition attributes.
        """
        return {
            "type": "NodeTransition",
            "name": self.name,
            "down": f"{self.down}",
            "up": f"{self.up}",
        }


@dataclass
class NamedTransition(TransitionCondition):
    """Condition that matches a named wildcard variable binding.

    Attributes:
        name: Name of the wildcard variable to bind or match.
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
    """

    subpattern_name: str
    args: list[str]

    def to_json(self) -> dict[str, Any]:
        """Serialize the call transition condition to a dictionary.

        Returns:
            dict[str, Any]: Serialized subpattern call condition attributes.
        """
        return {
            "type": "CallTransition",
            "subpattern_name": self.subpattern_name,
            "args": self.args,
        }

    def __str__(self) -> str:
        """Return the string representation of the subpattern call.

        Returns:
            str: Function-call formatted string (e.g. 'subpattern(args)').
        """
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

