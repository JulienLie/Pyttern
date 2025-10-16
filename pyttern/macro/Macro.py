from dataclasses import dataclass, field

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
    transformations: dict[str, PDA] = field(default_factory=dict)

    def __init__(self, name: str, code: str, args: dict[str, Tree], args_order: list[str]):
        """
        Initializes a Macro object.

        :param name: The name of the macro.
        :param args: A dictionary of argument names and their default values.
        """
        self.name = name
        self.args = args
        self.args_order = args_order
        self.code = code
        self.transformations = {}
        loaded_macros[name] = self

    def add_transformation(self, name: str, transformation: PDA):
        """
        Adds a transformation to the macro.

        :param name: The name of the transformation.
        :param transformation: The transformation object of type PDA.
        """
        self.transformations[name] = transformation


loaded_macros: dict[str, Macro] = {}
