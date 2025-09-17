from antlr4 import TerminalNode, ParseTreeVisitor

from ..simulator.pda import PDA

loaded_macros = {}

class Macro:
    """
    Represents a macro with a name, arguments, and transformations.

    Attributes:
        name (str): The name of the macro.
        args (dict[str, str]): A dictionary of argument names and their default values.
        transformations (dict): A dictionary of transformations associated with the macro.
    """

    def __init__(self, name: str, code: str, args: dict[str, str], args_order: list[str]):
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


class TerminalMacroNode(TerminalNode):
    def __init__(self):
        super().__init__()
        self.parentCtx = None

    def __setattr__(self, key, value):
        super().__setattr__(key, value)

    def getChild(self, _: int):
        return None

    def getSymbol(self):
        return ""

    def getParent(self):
        return self.parentCtx

    def getPayload(self):
        return ""

    def getSourceInterval(self):
        return -1, -1

    def getChildCount(self):
        return 0

    def accept(self, visitor: ParseTreeVisitor):
        return visitor.visitTerminal(self)

    def getText(self):
        return ""

    def __str__(self):
        return ""