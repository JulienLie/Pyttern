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

def prune(tree: RuleContext, ctx: RuleContext | None):
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
    __compiled_transformations: dict[str, PDA] = field(default_factory=dict)

    def __post_init__(self):
        loaded_subpatterns[self.name] = self

    def add_transformation(self, name: str, transformation: ParseTree):
        """
        Adds a transformation to the subpattern.

        :param name: The name of the transformation.
        :param transformation: The transformation object of type PDA.
        """
        self.transformations[name] = transformation

    def compile(self, context: SubPatternCallContext) -> dict[str, PDA]:
        from ..pytternfsm.python.python_to_pda import Python_to_PDA
        if len(self.__compiled_transformations) > 0:
            logger.warning(f"Recompiling subpattern {self.name}, this might be a mistake.")
        self.__compiled_transformations  = {}
        for (name, trans) in self.transformations.items():
            pruned_trans = prune(trans, context.ast_ctx)
            if pruned_trans is None:
                raise Exception(f"Cannot prune tree {trans.getText()} to {context.ast_ctx.__class__.__name__}")
            pda = Python_to_PDA().visit(pruned_trans)
            self.__compiled_transformations[name] = pda

        return self.__compiled_transformations

    def get_compiled_transf(self):
        if len(self.__compiled_transformations) == 0:
            logger.error(f"Subpattern {self.name} has not be compiled yet!")
            raise Exception(f"Subpattern {self.name} has not be compiled yet!")
        return self.__compiled_transformations

    
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
