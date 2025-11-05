from dataclasses import dataclass, field
from typing import Literal

from antlr4.tree.Tree import Tree

from ..simulator.pda import PDA


@dataclass
class Macro:
    """
    Represents a macro with a name, arguments, and transformations.

    Attributes:
        name (str): The name of the macro.
        args (dict[str, str]): A dictionary of argument names and their default values.
        transformations (dict): A dictionary of transformations associated with the macro.
    """

    name: str
    args: dict[str, Tree]
    args_order: list[str]
    code: str
    type: Literal["AND", "OR"] = "OR"
    transformations: dict[str, PDA] = field(default_factory=dict)

    def __post_init__(self):
        loaded_macros[self.name] = self

    def add_transformation(self, name: str, transformation: PDA):
        """
        Adds a transformation to the macro.

        :param name: The name of the transformation.
        :param transformation: The transformation object of type PDA.
        """
        self.transformations[name] = transformation


loaded_macros: dict[str, Macro] = {}
