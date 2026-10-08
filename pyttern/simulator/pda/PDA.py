"""Pushdown Automaton (PDA) graph model and JSON serialization."""

import json
from dataclasses import dataclass, field
from typing import Any

from loguru import logger

from .PDA_alphabets import NavigationAlphabet
from .transition import Transition, TransitionCondition


class PDAEncoder(json.JSONEncoder):
    """Custom JSON encoder supporting PDA models, transitions, and alphabets."""

    def default(self, o: Any) -> Any:
        """Encode PDA-related instances into JSON-serializable types.

        Args:
            o: Object to serialize.

        Returns:
            Any: Serialized JSON object or fallback to standard encoder.
        """
        if isinstance(o, PDA):
            json_object = {k: v for k, v in o.__dict__.items() if not k.startswith("_")}
            for elem in json_object:
                if isinstance(json_object[elem], set):
                    json_object[elem] = list(json_object[elem])
            return json_object
        if isinstance(o, Transition):
            return o.__dict__
        if isinstance(o, NavigationAlphabet):
            return str(o.name)
        if isinstance(o, TransitionCondition):
            return o.to_json()
        return super().default(o)


@dataclass
class PDA:
    """Pushdown Automaton (PDA) defined as a 5-tuple (Q, T, Γ, δ, q0, qf).

    Attributes:
        states: Finite set of state integers.
        named_wildcards: Set of variable names bound by wildcards.
        transitions: Mapping of state ID to outgoing transitions.
        initial_state: Index of the start state (typically 0).
        final_states: Index of the accepting/final state.
    """

    states: set[int] = field(default_factory=lambda: {0})
    named_wildcards: set[str] = field(default_factory=set)
    transitions: dict[int, list[Transition]] = field(default_factory=lambda: {0: []})
    initial_state: int = 0
    final_states: int = 0
    _calls_not: bool | None = field(default=None, repr=False)

    def new_state(self) -> int:
        """Create and register a new state in the automaton.

        Returns:
            int: The index of the newly added state.
        """
        new_state = len(self.states)
        self.states.add(new_state)
        self.transitions[new_state] = []
        return new_state

    def last_state(self) -> int:
        """Return the highest index state in the PDA.

        Returns:
            int: Index of the most recently added state.
        """
        return len(self.states) - 1

    def add_transition(self, transition: Transition) -> None:
        """Add a transition originating from its source state.

        Args:
            transition: Transition instance to add to the automaton.

        Raises:
            ValueError: If the source state does not exist in the PDA.
        """
        if transition.q == transition.q_prime:
            logger.trace(f"Adding self transition: {transition}")

        current_state = transition.q
        if current_state not in self.states:
            raise ValueError("State not in the PDA")
        if transition not in self.transitions[current_state]:
            self.transitions[current_state].append(transition)
        else:
            logger.warning(f"Transition {transition} already exists in state {current_state}")

    def get_transitions(self, state: int | None = None) -> list[Transition]:
        """Retrieve transitions originating from a specific state, or all transitions.

        Args:
            state: Optional state index. If None, returns all transitions.

        Returns:
            list[Transition]: List of matching transitions.
        """
        if state is None:
            return [
                transition
                for transitions in self.transitions.values()
                for transition in transitions
            ]  # Flatten

        return self.transitions[state]

    def __str__(self) -> str:
        """Return a summary string of the PDA structure.

        Returns:
            str: String containing state and transition counts.
        """
        num_transitions = sum(len(t) for t in self.transitions.values())
        return (
            f"PDA("
            f"states={len(self.states)}, "
            f"transitions={num_transitions}, "
            f"initial_state={self.initial_state}, "
            f"final_states={self.final_states}, "
            f"named_wildcards={self.named_wildcards}"
            f")"
        )

    def __repr__(self) -> str:
        """Return the official representation of the PDA.

        Returns:
            str: Summary string representation.
        """
        return self.__str__()