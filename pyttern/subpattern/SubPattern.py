from abc import ABC, abstractmethod
from dataclasses import dataclass, field
from typing import Literal, Any, Optional

from pyttern.antlr.python import Python3Parser

@dataclass(frozen=True)
class SubPatternCallContext:
    ast_ctx: Any
    body: Any = None

from antlr4 import RuleContext
from antlr4.tree.Tree import Tree, ParseTree
from loguru import logger

from pyttern.simulator.pda.transition import CallTransition, Transition


from ..simulator.pda import PDA

def _adapt_stmt_to_tfpdef(tree: RuleContext) -> RuleContext | None:
    desc = tree
    while desc and not isinstance(desc, Python3Parser.Expr_stmtContext):
        children = list(desc.getChildren())
        if len(children) != 1:
            break
        desc = children[0]

    if desc and isinstance(desc, Python3Parser.Expr_stmtContext):
        target_name = desc.getChild(0)
        while target_name.getChildCount() == 1:
            target_name = target_name.getChild(0)

        if desc.getChildCount() > 1:
            annassign = desc.getChild(1)
            type_expr = annassign.getChild(0)
            tfpdef = Python3Parser.TfpdefContext(None, parent=None, invokingState=-1)
            tfpdef.children = [target_name, type_expr]
            target_name.parentCtx = tfpdef
            type_expr.parentCtx = tfpdef
            return tfpdef
        else:
            tfpdef = Python3Parser.TfpdefContext(None, parent=None, invokingState=-1)
            tfpdef.children = [target_name]
            target_name.parentCtx = tfpdef
            return tfpdef
    return None


def prune(tree: RuleContext, ctx: RuleContext | None):
    if ctx is not None and isinstance(ctx, (Python3Parser.ParametersContext, Python3Parser.TfpdefContext)):
        adapted = _adapt_stmt_to_tfpdef(tree)
        if adapted is not None:
            return adapted

    desc = tree
    while desc and not isinstance(desc, ctx.__class__):
        children = list(desc.getChildren())
        if len(children) != 1:
            return None
        desc = children[0]
    return desc



@dataclass
class BaseSubPattern(ABC):
    """
    Represents a subpattern with a name, arguments, and transformations.

    Attributes:
        name (str): The name of the subpattern.
        args (dict[str, str]): A dictionary of argument names and their default values.
        transformations (dict): A dictionary of transformations associated with the subpattern.
    """

    name: str
    args: dict[str, Optional[Tree]]
    args_order: list[str]
    code: str
    transformations: dict[str, ParseTree] = field(default_factory=dict)

    def add_transformation(self, name: str, transformation: ParseTree) -> None:
        """
        Adds a transformation to the subpattern.

        :param name: The name of the transformation.
        :param transformation: The transformation parse tree.
        """
        self.transformations[name] = transformation

    @property
    @abstractmethod
    def type(self) -> Literal["AND", "OR", "NOT"]:
        pass
    

    def generate_pda(self, pda: PDA, args: list[str], starting_state: int) -> int:
        """
        Generates the transition needed for this subpattern in the pda @pda using the argument names from @args
        """
        self.check_args_nbr(args)
        next_state = pda.new_state()
        subpattern_name = self.name

        logger.trace(f"Adding transition for {self.type} subpattern {subpattern_name}")
        transition = Transition(starting_state, '', CallTransition(subpattern_name, args), [],
                                next_state, '')
        pda.add_transition(transition)

        return next_state


    def check_args_nbr(self, args_names) -> None:
        n_args_req = sum(1 for key in self.args if self.args[key] is None)
        if len(args_names) < n_args_req:
            logger.error(f"Subpattern {self.name} requires at least {n_args_req} arguments, but got {len(args_names)}")
            raise ValueError(f"Subpattern {self.name} requires at least {n_args_req} arguments, but got {len(args_names)}")

@dataclass
class OrSubPattern(BaseSubPattern):
    @property
    def type(self) -> Literal["OR"]:
        return "OR"
    
    
@dataclass
class AndSubPattern(BaseSubPattern):
    @property
    def type(self) -> Literal["AND"]:
        return "AND"

    
@dataclass
class NotSubPattern(BaseSubPattern):
    @property
    def type(self) -> Literal["NOT"]:
        return "NOT"

loaded_subpatterns: dict[str, BaseSubPattern] = {}
