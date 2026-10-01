"""Subpattern definitions, compilation contexts, and registry."""

from abc import ABC, abstractmethod
from dataclasses import dataclass, field
from typing import Any, Literal, Optional

from antlr4 import RuleContext
from antlr4.tree.Tree import ParseTree, Tree
from loguru import logger

from pyttern.antlr.python import Python3Parser
from pyttern.simulator.pda import PDA
from pyttern.simulator.pda.transition import CallTransition, Transition


@dataclass(frozen=True)
class SubPatternCallContext:
    """Context information provided when invoking a subpattern during compilation.

    Attributes:
        ast_ctx: Expected target AST rule context type or instance.
        body: Optional body node or statements associated with the call context.
    """

    ast_ctx: Any
    body: Any = None


def _adapt_stmt_to_tfpdef(tree: RuleContext) -> RuleContext | None:
    """Adapt an expression statement AST node into a typed parameter (tfpdef) node.

    Used when a subpattern defined as a statement needs to match within a function
    parameter or type annotation context.

    Args:
        tree: The ANTLR rule context to adapt.

    Returns:
        RuleContext | None: A synthesized TfpdefContext node if convertible, else None.
    """
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


def prune(tree: RuleContext, ctx: RuleContext | None) -> RuleContext | None:
    """Prune intermediate single-child wrapper nodes to align tree with target context.

    Args:
        tree: Parse tree root to prune downwards.
        ctx: Target rule context to align with.

    Returns:
        RuleContext | None: The matching descendant subtree node, or None if unmatched.
    """
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
    """Abstract base class representing a modular subpattern with transformations.

    Attributes:
        name: Unique identifier name of the subpattern.
        args: Dictionary mapping argument names to default values or None.
        args_order: Ordered list of parameter names.
        code: Original source code text of the subpattern definition.
        transformations: Mapping of branch names to uncompiled parse trees.
    """

    name: str
    args: dict[str, Optional[Tree]]
    args_order: list[str]
    code: str
    transformations: dict[str, ParseTree] = field(default_factory=dict)
    __compiled_transformations: dict[str, PDA] = field(default_factory=dict)

    def __post_init__(self) -> None:
        """Register the subpattern in the global loaded subpatterns registry."""
        loaded_subpatterns[self.name] = self

    def add_transformation(self, name: str, transformation: ParseTree) -> None:
        """Register a transformation parse tree under a branch name.

        Args:
            name: Branch or transformation label.
            transformation: Uncompiled parse tree for this branch.
        """
        self.transformations[name] = transformation

    def compile(self, context: SubPatternCallContext) -> dict[str, PDA]:
        """Compile all transformation branches into pushdown automata (PDAs).

        Args:
            context: Call context containing the target AST context type.

        Returns:
            dict[str, PDA]: Mapping of transformation branch names to compiled PDAs.

        Raises:
            Exception: If a transformation tree cannot be pruned to the target context.
        """
        from ..pytternfsm.python.python_to_pda import Python_to_PDA

        if len(self.__compiled_transformations) > 0:
            logger.warning(f"Recompiling subpattern {self.name}, this might be a mistake.")
        self.__compiled_transformations = {}
        ctx_name = context.ast_ctx.__class__.__name__ if context.ast_ctx is not None else "None"
        logger.debug(
            f"Compiling subpattern '{self.name}' ({self.type}) with {len(self.transformations)} "
            f"transformation(s) against context {ctx_name}"
        )
        for (name, trans) in self.transformations.items():
            pruned_trans = prune(trans, context.ast_ctx)
            if pruned_trans is None:
                raise Exception(f"Cannot prune tree {trans.getText()} to {ctx_name}")
            pda = Python_to_PDA().visit(pruned_trans)
            self.__compiled_transformations[name] = pda

        return self.__compiled_transformations

    def get_compiled_transf(self) -> dict[str, PDA]:
        """Retrieve compiled transformation PDAs.

        Returns:
            dict[str, PDA]: Compiled transformation branches.

        Raises:
            Exception: If compile() has not been called prior to this invocation.
        """
        if len(self.__compiled_transformations) == 0:
            logger.error(f"Subpattern {self.name} has not be compiled yet!")
            raise Exception(f"Subpattern {self.name} has not be compiled yet!")
        return self.__compiled_transformations

    @property
    @abstractmethod
    def type(self) -> Literal["AND", "OR", "NOT"]:
        """Return the subpattern logical composition operator ('AND', 'OR', or 'NOT')."""
        pass

    def generate_pda(self, pda: PDA, args: list[str], starting_state: int) -> int:
        """Generate a CallTransition in the target PDA invoking this subpattern.

        Args:
            pda: Host Pushdown Automaton to augment.
            args: Concrete argument variable names passed to the subpattern call.
            starting_state: Source state ID in the host PDA.

        Returns:
            int: The index of the newly created target state.
        """
        self.check_args_nbr(args)
        next_state = pda.new_state()
        subpattern_name = self.name

        logger.trace(f"Adding transition for {self.type} subpattern {subpattern_name}")
        transition = Transition(
            starting_state,
            '',
            CallTransition(subpattern_name, args),
            [],
            next_state,
            '',
        )
        pda.add_transition(transition)
        return next_state

    def check_args_nbr(self, args_names: list[str]) -> None:
        """Validate that the required number of non-optional arguments are supplied.

        Args:
            args_names: List of provided argument names.

        Raises:
            ValueError: If fewer arguments are supplied than required.
        """
        n_args_req = sum(1 for key in self.args if self.args[key] is None)
        if len(args_names) < n_args_req:
            logger.error(
                f"Subpattern {self.name} requires at least {n_args_req} arguments, but got {len(args_names)}"
            )
            raise ValueError(
                f"Subpattern {self.name} requires at least {n_args_req} arguments, but got {len(args_names)}"
            )


@dataclass
class OrSubPattern(BaseSubPattern):
    """Subpattern evaluated using disjunctive logic (matches if any branch matches)."""

    @property
    def type(self) -> Literal["OR"]:
        """Return operator type 'OR'."""
        return "OR"


@dataclass
class AndSubPattern(BaseSubPattern):
    """Subpattern evaluated using conjunctive logic (all branches must match)."""

    @property
    def type(self) -> Literal["AND"]:
        """Return operator type 'AND'."""
        return "AND"


@dataclass
class NotSubPattern(BaseSubPattern):
    """Subpattern evaluated using negative lookahead (matches if transformation fails)."""

    @property
    def type(self) -> Literal["NOT"]:
        """Return operator type 'NOT'."""
        return "NOT"


loaded_subpatterns: dict[str, BaseSubPattern] = {}


def clear_loaded_subpatterns() -> None:
    """Clear all currently registered subpatterns from the global registry."""
    loaded_subpatterns.clear()


def get_loaded_subpattern(name: str) -> Optional[BaseSubPattern]:
    """Retrieve a loaded subpattern by its registered name.

    Args:
        name: Name identifier of the subpattern.

    Returns:
        Optional[BaseSubPattern]: The subpattern instance if registered, else None.
    """
    return loaded_subpatterns.get(name)


