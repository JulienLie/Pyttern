from enum import Enum

class NavigationAlphabet(Enum):
    PARENT = 0
    RIGHT_SIBLING = 1
    LEFT_CHILD = 2

    def __str__(self):
        return "".join(m[0] for m in self.name.split("_"))

class StackAlphabet(Enum):
    EPSILON = ""  # Represents an empty stack operation
    BODY = "B"
    INDENT = "I"