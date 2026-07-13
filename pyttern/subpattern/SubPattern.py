from abc import ABC, abstractmethod
from dataclasses import dataclass, field
from typing import Literal, Any

@dataclass(frozen=True)
class SubPatternCallContext:
    ast_ctx: Any
    body: Any = None
    alone: bool = False

from antlr4 import RuleContext
from antlr4.tree.Tree import Tree, ParseTree
from loguru import logger

from pyttern.simulator.pda.PDA_alphabets import NavigationAlphabet
from pyttern.simulator.pda.transition import CallTransition, NodeTransition, NotCallTransition, Transition

from ..antlr.python import Python3Parser

from ..simulator.pda import PDA

def prune(tree: RuleContext, ctx: RuleContext | None):
    if isinstance(ctx, Python3Parser.Expr_wildcardContext):
        tree = tree.getChild(0) #stmt -> simple_stmts
        tree = tree.getChild(0) #simple_stmts -> simple_stmt
        return tree
    return tree


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
    args: dict[str, Tree]
    args_order: list[str]
    code: str
    transformations: dict[str, ParseTree] = field(default_factory=dict)

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
        """
        :context: The call-site context containing ast_ctx, body, alone.
        :return: A dictionary of transformation names to PDAs.
        """
        if context.alone:
            return self._compile_alone(context)
        else:
            return self._compile(context)
    
    def _compile(self, context: SubPatternCallContext) -> dict[str, PDA]:
        from ..pytternfsm.python.python_to_pda import Python_to_PDA
        ret = {}
        for (name, trans) in self.transformations.items():
            trans = prune(trans, context.ast_ctx)

            pda = Python_to_PDA().visit(trans)
            ret[f"{self.name}::{name}"] = pda

        return ret

    def _compile_alone(self, context: SubPatternCallContext) -> dict[str, PDA]:
        from ..pytternfsm.python.python_to_pda import Python_to_PDA
        ret = {}
        for (name, trans) in self.transformations.items():
            trans = prune(trans, context.ast_ctx)

            pda = PDA()
            starting_state = pda.initial_state
            stmt_state = pda.new_state()
            body_to_stmt = Transition(starting_state, '', NodeTransition(""), [NavigationAlphabet.LEFT_CHILD], stmt_state, 'I')
            pda.add_transition(body_to_stmt)

            self_transition = Transition(stmt_state, '', NodeTransition(""), [NavigationAlphabet.RIGHT_SIBLING], stmt_state, '')
            pda.add_transition(self_transition)

            simple_stmt_state = pda.new_state()
            stmt_to_simple_stmt = Transition(stmt_state, '', NodeTransition(""), [NavigationAlphabet.LEFT_CHILD], simple_stmt_state, 'I')
            pda.add_transition(stmt_to_simple_stmt)

            pda_generator = Python_to_PDA()
            pda_generator.pda = pda
            pda_generator.current_state = simple_stmt_state
            ret[f"{self.name}::{name}"] = pda_generator.visit(trans)

        return ret
    
    @property
    @abstractmethod
    def type(self) -> Literal["AND", "OR", "NOT"]:
        pass
    

    def generate_pda(self, pda: PDA, args: list[str], starting_state: int, context: SubPatternCallContext) -> int:
        """
        Generates the transition needed for this subpattern in the pda @pda using the argument names from @args
        """
        self.check_args_nbr(args)
        if context.alone:
            return self._generate_pda_alone(pda, args, starting_state, context)
        else:
            return self._generate_pda(pda, args, starting_state, context)

    @abstractmethod
    def _generate_pda(self, pda: PDA, args: list[str], starting_state: int, context: SubPatternCallContext) -> int:
        pass

    def _generate_pda_alone(self, pda: PDA, args: list[str], starting_state: int, context: SubPatternCallContext) -> int:
        return self._generate_pda(pda, args, starting_state, context)


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
    
    def _generate_pda(self, pda, args, starting_state, context: SubPatternCallContext) -> int:
        next_state = pda.new_state()
        subpattern_name = self.name

        for transformation in self.transformations:
            logger.trace(f"Adding transformation {transformation} for OR subpattern {subpattern_name}")
            transition = Transition(starting_state, '', CallTransition(subpattern_name, transformation, args), [],
                                    next_state, '')
            pda.add_transition(transition)

        return next_state
    
@dataclass
class AndSubPattern(BaseSubPattern):
    @property
    def type(self) -> Literal["AND"]:
        return "AND"
    
    def _generate_pda(self, pda, args, starting_state, context: SubPatternCallContext) -> int:
        transformations = list(self.transformations.keys())
        n = len(transformations)
        subpattern_name = self.name

        if n == 0:
            return starting_state

        if n > 10:
            logger.warning(
                f"Subpattern {subpattern_name} has {n} AND-clauses, which will create a PDA with {1 << n} states."
            )

        # Create 2^n states, one for each subset of matched transformations (represented by a bitmask)
        pda_states = {mask: pda.new_state() for mask in range(1, 1 << n)}
        pda_states[0] = starting_state

        for mask in range(1 << n):
            current_pda_state = pda_states[mask]

            # Add a self-loop to navigate/skip statements that do not match any required transformations.
            # This allows matching transformations in any order, interspersed with other statements.
            # The navigation path is based on the original implementation's logic.
            if mask != (1 << n) - 1:
                nav_loop_transition = Transition(
                    current_pda_state,
                    "",
                    NodeTransition(""),
                    [
                        NavigationAlphabet.PARENT,
                        NavigationAlphabet.RIGHT_SIBLING,
                        NavigationAlphabet.LEFT_CHILD,
                    ],
                    current_pda_state,
                    "",
                )
                pda.add_transition(nav_loop_transition)

            # For each transformation not yet matched in the current subset (mask)
            for i, trans_name in enumerate(transformations):
                if not ((mask >> i) & 1):  # Check if i-th bit is not set
                    next_mask = mask | (1 << i)
                    next_pda_state = pda_states[next_mask]

                    # Add a transition to match the transformation and move to the next state (subset)
                    match_transition = Transition(
                        current_pda_state,
                        "",
                        CallTransition(subpattern_name, trans_name, args),
                        [],
                        next_pda_state,
                        "",
                    )
                    pda.add_transition(match_transition)

        # The final state is the one where all transformations have been matched
        return pda_states[(1 << n) - 1]
    
    def _generate_pda_alone(self, pda, args, starting_state, context) -> int:
        prev_state = starting_state
        for transformation in list(self.transformations.keys()):
            next_state = pda.new_state()

            transformation_transition = Transition(prev_state, "", CallTransition(self.name, transformation, args), [], next_state, "")
            pda.add_transition(transformation_transition)

            prev_state = next_state

        return prev_state

    
@dataclass
class NotSubPattern(BaseSubPattern):
    @property
    def type(self) -> Literal["NOT"]:
        return "NOT"
    
    def _generate_pda_alone(self, pda, args, starting_state, context: SubPatternCallContext) -> int:
        logger.debug(f"Not transformation disponible: {list(self.transformations.keys())}")
        name = list(self.transformations.keys())[0]

        next_state = pda.new_state()
        transition = Transition(
            starting_state,
            "",
            NotCallTransition(self.name, name, args),
            [],
            next_state,
            ""
        )
        pda.add_transition(transition)
        logger.debug(f"Adding Not transition: {transition}")

        return next_state

    def _generate_pda(self, pda, args, starting_state, context: SubPatternCallContext):
        return self._generate_pda_alone(pda, args, starting_state, context)

loaded_subpatterns: dict[str, BaseSubPattern] = {}
