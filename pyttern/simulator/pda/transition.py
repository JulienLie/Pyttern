
from dataclasses import dataclass

from .navigation_direction import NavigationDirection


@dataclass(frozen=True)
class Transition:
    """
    A transition is a 6-tuple (q, a, A, t, q', α) where:
        - q is the current state
        - a is the input symbol
        - A is the stack symbol
        - t is the navigation direction
        - q' is the next state
        - α is the stack replacement
    """
    q: int
    a: str
    A: str
    t: list[NavigationDirection]
    q_prime: int
    alpha: str