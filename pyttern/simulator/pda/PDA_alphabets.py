"""Alphabet definitions for Pushdown Automata (PDA) navigation and stack operations."""

from enum import Enum


class NavigationAlphabet(Enum):
    """Enumeration representing navigation directions in the AST.

    Attributes:
        PARENT: Move to the parent node.
        RIGHT_SIBLING: Move to the next right sibling node.
        LEFT_CHILD: Move to the first child node.
    """

    PARENT = 0
    RIGHT_SIBLING = 1
    LEFT_CHILD = 2

    def __str__(self) -> str:
        """Return the abbreviated symbol representation of the navigation direction.

        Returns:
            str: Abbreviated direction symbol (e.g. 'P', 'RS', 'LC').
        """
        return "".join(m[0] for m in self.name.split("_"))


class StackAlphabet(Enum):
    """Enumeration representing stack symbols used in the PDA.

    Attributes:
        EPSILON: Empty stack operation symbol.
        BODY: Stack symbol marking a block/body context.
        INDENT: Stack symbol marking an indentation level context.
    """

    EPSILON = ""  # Represents an empty stack operation
    BODY = "B"
    INDENT = "I"