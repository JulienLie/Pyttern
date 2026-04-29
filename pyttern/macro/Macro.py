from dataclasses import dataclass, field
from typing import Literal

from antlr4 import RuleContext
from antlr4.tree.Tree import Tree, ParseTree

from ..antlr.python import Python3Parser

from ..simulator.pda import PDA

def check_for_alone(tree: ParseTree):
    return False
    if isinstance(tree, Python3Parser.Macro_stmtsContext):
        macro = loaded_macros[tree]
        if macro is not None and macro.alone:
            return True
    if hasattr("children", tree):
        if any([check_for_alone(child) for child in tree.children]):
            return True
    return False

def prune(tree: RuleContext, ctx: RuleContext | None):
    if isinstance(ctx, Python3Parser.Expr_wildcardContext):
        tree = tree.getChild(0) #stmt -> simple_stmts
        tree = tree.getChild(0) #simple_stmts -> simple_stmt
        return tree
    return tree


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
    type: Literal["AND", "OR", "NOT"] = "OR"
    transformations: dict[str, ParseTree] = field(default_factory=dict)
    alone: bool = False

    def __post_init__(self):
        loaded_macros[self.name] = self

    def add_transformation(self, name: str, transformation: ParseTree):
        """
        Adds a transformation to the macro.

        :param name: The name of the transformation.
        :param transformation: The transformation object of type PDA.
        """
        self.transformations[name] = transformation
        if not self.alone:
            check_for_alone(transformation)


    def compile(self, ctx, body=None) -> dict[str, PDA]:
        """
        :ctx: the current context in which the macro will be called, this help prune the tree to more precise match
        :body: WIP
        :return:
        """
        from ..pytternfsm.python.python_to_pda import Python_to_PDA
        ret = {}
        for (name, trans) in self.transformations.items():
            trans = prune(trans, ctx)

            pda = Python_to_PDA().visit(trans)
            ret[f"{self.name}::{name}"] = pda

        return ret

loaded_macros: dict[str, Macro] = {}
