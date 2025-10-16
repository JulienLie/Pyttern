from enum import Enum

class NavigationAlphabet(Enum):
    PARENT = 0
    RIGHT_SIBLING = 1
    LEFT_CHILD = 2

class StackAlphabet(Enum):
    EPSILON = ""  # Represents an empty stack operation
    BODY = "B"
    INDENT = "I"