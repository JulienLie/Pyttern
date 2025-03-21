"""
The PDA can be defied as a 6-tuple (Q, Σ, Γ, δ, q0, F) where:
    - Q is a finite set of states
    - Σ is a finite set of input symbols
    - Γ is a finite set of stack symbols
    - δ is the transition function
    - q0 is the initial state
    - F is the set of final states
"""
import json

from .navigation_direction import NavigationDirection
from .transition import Transition


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
        if isinstance(o, NavigationDirection):
            return str(o.name)
        return super().default(o)


class PDA:
    def __init__(self):
        self.states: set[int] = {0}
        self.input_symbols: set[str] = set()
        self.stack_symbols: set[str] = set()
        self.transitions: dict[int, list[Transition]] = {0:[]}
        self.initial_state: int = 0
        self.final_states: set[int] = set()

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