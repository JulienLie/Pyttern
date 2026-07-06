from abc import ABC, abstractmethod
from dataclasses import dataclass, field
from typing import Literal

from antlr4 import RuleContext
from antlr4.tree.Tree import Tree, ParseTree
from loguru import logger

from pyttern.simulator.pda.PDA_alphabets import NavigationAlphabet
from pyttern.simulator.pda.transition import CallTransition, NodeTransition, NotCallTransition, Transition

from ..antlr.python import Python3Parser

from ..simulator.pda import PDA

def check_for_alone(tree: ParseTree):
    return False
    if isinstance(tree, Python3Parser.Macro_stmtsContext):
        macro = loaded_subpatterns[tree]
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
    alone: bool = False

    def __post_init__(self):
        loaded_subpatterns[self.name] = self

    def add_transformation(self, name: str, transformation: ParseTree):
        """
        Adds a transformation to the subpattern.

        :param name: The name of the transformation.
        :param transformation: The transformation object of type PDA.
        """
        self.transformations[name] = transformation
        if not self.alone:
            check_for_alone(transformation)


    def compile(self, ctx, body=None) -> dict[str, PDA]:
        """
        :ctx: the current context in which the subpattern will be called, this help prune the tree to more precise match
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
    
    @property
    @abstractmethod
    def type(self) -> Literal["AND", "OR", "NOT"]:
        pass
    

    @abstractmethod
    def generate_pda(self, pda: PDA, args: list[str], starting_state: int) -> int:
        """
        Generates the transition needed for this subpattern in the pda @pda using the argument names from @args
        """
        pass

    def check_args_nbr(self, args_names):
        n_args_req = sum(1 for key in self.args if self.args[key] is None)
        if len(args_names) < n_args_req:
            logger.error(f"Macro {self.name} requires at least {n_args_req} arguments, but got {len(args_names)}")
            raise ValueError(f"Macro {self.name} requires at least {n_args_req} arguments, but got {len(args_names)}")

@dataclass
class OrSubPattern(BaseSubPattern):
    @property
    def type(self) -> Literal["OR"]:
        return "OR"
    
    def generate_pda(self, pda, args, starting_state) -> int:
        super().check_args_nbr(args)
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
    
    def generate_pda(self, pda, args, starting_state) -> int:
        super().check_args_nbr(args)
        transformations = list(self.transformations.keys())
        n = len(transformations)
        subpattern_name = self.name

        if n == 0:
            return starting_state

        if n > 10:
            logger.warning(
                f"Macro {subpattern_name} has {n} AND-clauses, which will create a PDA with {1 << n} states."
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
    
@dataclass
class NotSubPattern(BaseSubPattern):
    @property
    def type(self) -> Literal["NOT"]:
        return "NOT"
    
    def compile(self, ctx, body=None):
        from ..pytternfsm.python.python_to_pda import Python_to_PDA
        ret = {}
        for (name, trans) in self.transformations.items():
            trans = prune(trans, ctx)

            pda = PDA()
            current_state = self.__add_default_transitions(pda)

            pda = Python_to_PDA(pda, current_state).visit(trans)
            ret[f"{self.name}::{name}"] = pda

        return ret
    
    def __add_default_transitions(self, pda: PDA):
        # Descend in the body
        after_body = pda.new_state()
        body_transition = Transition(
            pda.initial_state,
            "",
            NodeTransition(""), # TODO: Should probably replace "" with the correct value
            [NavigationAlphabet.LEFT_CHILD],
            after_body,
            "I"
        )
        pda.add_transition(body_transition)

        self_transition = Transition(
            after_body,
            "",
            NodeTransition(""),
            [NavigationAlphabet.RIGHT_SIBLING],
            after_body,
            ""
        )
        pda.add_transition(self_transition)

        stmt_body_state = pda.new_state()
        to_stmt_body = Transition(
            after_body,
            "",
            NodeTransition(""),
            [NavigationAlphabet.LEFT_CHILD],
            stmt_body_state,
            "I"
        )
        pda.add_transition(to_stmt_body)

        return stmt_body_state
    
    def generate_pda(self, pda, args, starting_state) -> int:
        super().check_args_nbr(args)

        logger.trace(f"Not transformation disponible: {list(self.transformations.keys())}")
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

        return next_state

loaded_subpatterns: dict[str, BaseSubPattern] = {}
