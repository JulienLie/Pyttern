import json
from dataclasses import dataclass, field

from .PDA_alphabets import NavigationAlphabet
from .transition import Transition, TransitionCondition


class PDAEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, PDA):
            json_object = o.__dict__.copy()
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
    """
    The PDA can be defied as a 5-tuple (Q, T, Γ, δ, q0, qf) where:
        - Q is a finite set of states
        - T is the set of named wildcards
        - δ is the transition set
        - q0 is the initial state
        - qf is the final states
    """

    states: set[int] = field(default_factory=lambda: {0})
    named_wildcards: set[str] = field(default_factory=set)
    transitions: dict[int, list[Transition]] = field(default_factory=lambda: {0: []})
    initial_state: int = 0
    final_states: int = 0

    def new_state(self) -> int:
        new_state = len(self.states)
        self.states.add(new_state)
        self.transitions[new_state] = []
        return new_state

    def last_state(self) -> int:
        return len(self.states) - 1

    def add_transition(self, transition: Transition) -> None:
        current_state = transition.q
        if current_state not in self.states:
            raise ValueError("State not in the PDA")
        self.transitions[current_state].append(transition)


    def get_transitions(self, state: int | None = None) -> list[Transition]:
        if state is None:
            return [
                transition
                for transitions in self.transitions.values()
                for transition in transitions
            ] # Flatten

        return self.transitions[state]