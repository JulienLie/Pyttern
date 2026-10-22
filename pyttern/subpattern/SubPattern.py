from __future__ import annotations

from abc import ABC, abstractmethod
from dataclasses import dataclass, field
from typing import Any, Literal

from antlr4 import RuleContext
from antlr4.tree.Tree import ParseTree, Tree
from loguru import logger

from pyttern.antlr.python import Python3Parser
from pyttern.simulator.pda.transition import CallTransition, Transition
from ..simulator.pda import PDA


@dataclass(frozen=True)
class SubPatternCallContext:
    ast_ctx: Any
    body: Any = None
    args: list[str] | None = None
    call_id: int | None = None


def clone_tree(node: Any, parent: Any = None) -> Any:
    from antlr4.tree.Tree import TerminalNodeImpl

    if isinstance(node, TerminalNodeImpl):
        new_node = TerminalNodeImpl(node.getSymbol())
        new_node.parentCtx = parent
        return new_node
    cls = node.__class__
    new_node = cls.__new__(cls)
    new_node.parentCtx = parent
    new_node.invokingState = getattr(node, "invokingState", -1)
    for k, v in node.__dict__.items():
        if k not in ("parentCtx", "children", "invokingState"):
            setattr(new_node, k, v)
    if hasattr(node, "children") and node.children is not None:
        new_node.children = [clone_tree(c, parent=new_node) for c in node.children]
    else:
        new_node.children = []
    return new_node


def rename_vars_in_tree(node: Any, var_map: dict[str, str]) -> None:
    if not var_map:
        return
    if isinstance(node, Python3Parser.Var_wildcardContext):
        name_node = node.NAME()
        if name_node is not None:
            old_name = name_node.getText()
            if old_name in var_map:
                name_node.getSymbol().text = var_map[old_name]
    if hasattr(node, "children") and node.children:
        for c in node.children:
            rename_vars_in_tree(c, var_map)


def extract_body_stmts(body_block: Any) -> list[Any]:
    if body_block is None:
        return []
    stmts: list[Any] = []
    for child in getattr(body_block, "children", []):
        if isinstance(child, Python3Parser.StmtContext):
            stmts.append(child)
        elif isinstance(child, Python3Parser.Simple_stmtsContext):
            stmt_ctx = Python3Parser.StmtContext(parser=None, parent=None, invokingState=-1)
            stmt_ctx.children = [child]
            child.parentCtx = stmt_ctx
            stmts.append(stmt_ctx)
    return stmts


def is_body_var_stmt(stmt: Any, body_var: str) -> bool:
    if not isinstance(stmt, Python3Parser.StmtContext):
        return False
    text = stmt.getText().strip().replace("?", "").replace(";", "").replace("\n", "")
    return text == body_var


def substitute_body(tree: Any, body_stmts: list[Any], body_var: str, var_map: dict[str, str] | None = None) -> bool:
    substituted = False
    if isinstance(tree, Python3Parser.BlockContext):
        new_children = []
        for child in getattr(tree, "children", []):
            if is_body_var_stmt(child, body_var):
                substituted = True
                for b_stmt in body_stmts:
                    b_cloned = clone_tree(b_stmt, parent=tree)
                    if var_map:
                        rename_vars_in_tree(b_cloned, var_map)
                    new_children.append(b_cloned)
            else:
                new_children.append(child)
        tree.children = new_children

    if hasattr(tree, "children") and tree.children:
        for child in tree.children:
            if substitute_body(child, body_stmts, body_var, var_map):
                substituted = True
    return substituted


def _adapt_stmt_to_tfpdef(tree: RuleContext) -> RuleContext | None:
    desc: Any = tree
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


def prune(tree: RuleContext, ctx: RuleContext | None) -> Any:
    from ..language_processors.python_processor import PythonProcessor
    return PythonProcessor().prune_subpattern_tree(tree, ctx)


@dataclass
class BaseSubPattern(ABC):
    """
    Represents a subpattern with a name, arguments, and transformations.

    Attributes:
        name (str): The name of the subpattern.
        args (dict[str, Tree | None]): A dictionary of argument names and their default values.
        transformations (dict): A dictionary of transformations associated with the subpattern.
    """

    name: str
    args: dict[str, Tree | None]
    args_order: list[str]
    code: str
    body_var: str | None = None
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

    def generate_pda(
        self,
        pda: PDA,
        args: list[str],
        starting_state: int,
        context_type: Any = None,
        call_id: int | None = None,
    ) -> int:
        """
        Generates the transition needed for this subpattern in the pda @pda using the argument names from @args
        """
        self.check_args_nbr(args)
        next_state = pda.new_state()
        subpattern_name = self.name

        logger.trace(f"Adding transition for {self.type} subpattern {subpattern_name} (call_id={call_id})")
        transition = Transition(
            starting_state,
            "",
            CallTransition(subpattern_name, args, context_type=context_type, call_id=call_id),
            [],
            next_state,
            "",
        )
        pda.add_transition(transition)

        return next_state

    def check_args_nbr(self, args_names: list[str]) -> None:
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
