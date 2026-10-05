from abc import ABC, abstractmethod
from dataclasses import dataclass, field
from typing import Literal, Any, Optional

from pyttern.antlr.python import Python3Parser

@dataclass(frozen=True)
class SubPatternCallContext:
    ast_ctx: Any
    body: Any = None
    args: Optional[list[str]] = None
    call_id: Optional[int] = None

from antlr4 import RuleContext
from antlr4.tree.Tree import Tree, ParseTree
from loguru import logger

from pyttern.simulator.pda.transition import CallTransition, Transition


from ..simulator.pda import PDA


def clone_tree(node, parent=None):
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


def rename_vars_in_tree(node, var_map: dict[str, str]):
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
    stmts = []
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
    text = stmt.getText().strip().replace('?', '').replace(';', '').replace('\n', '')
    return text == body_var


def substitute_body(tree: Any, body_stmts: list[Any], body_var: str, var_map: dict[str, str]) -> bool:
    substituted = False
    if isinstance(tree, Python3Parser.BlockContext):
        new_children = []
        for child in getattr(tree, "children", []):
            if is_body_var_stmt(child, body_var):
                substituted = True
                for b_stmt in body_stmts:
                    b_cloned = clone_tree(b_stmt, parent=tree)
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

    if ctx is not None and isinstance(ctx, (Python3Parser.StmtContext, Python3Parser.Compound_stmtContext, Python3Parser.Compound_subpattern_callContext)):
        desc = tree
        while desc and not isinstance(desc, Python3Parser.StmtContext):
            children = list(desc.getChildren())
            if len(children) != 1:
                break
            desc = children[0]
        if desc and isinstance(desc, Python3Parser.StmtContext):
            return desc
        return tree

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
    body_var: Optional[str] = None
    transformations: dict[str, ParseTree] = field(default_factory=dict)
    __compiled_transformations: dict[str, PDA] = field(default_factory=dict)
    _call_compiled_transformations: dict[int, dict[str, PDA]] = field(default_factory=dict)

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

        if self.body_var is not None and context.body is None:
            raise ValueError(f"Subpattern '{self.name}' requires a body wildcard '(?{self.body_var})', but no body was provided at call site.")
        if self.body_var is None and context.body is not None:
            raise ValueError(f"Subpattern '{self.name}' does not accept a body, but a body was provided.")

        call_id = context.call_id

        body_stmts = []
        var_map = {}
        if self.body_var is not None and context.body is not None:
            body_stmts = extract_body_stmts(context.body)
            if context.args is not None:
                for caller_arg, param in zip(context.args, self.args_order):
                    var_map[caller_arg] = param

        compiled_dict = {}
        for (name, trans) in self.transformations.items():
            cloned_trans = clone_tree(trans)
            if self.body_var is not None and body_stmts:
                if is_body_var_stmt(cloned_trans, self.body_var) and len(body_stmts) == 1:
                    cloned_trans = clone_tree(body_stmts[0])
                    rename_vars_in_tree(cloned_trans, var_map)
                else:
                    substitute_body(cloned_trans, body_stmts, self.body_var, var_map)

            pruned_trans = prune(cloned_trans, context.ast_ctx)
            if pruned_trans is None:
                raise Exception(f"Cannot prune tree {cloned_trans.getText()} to {context.ast_ctx.__class__.__name__}")
            pda = Python_to_PDA().visit(pruned_trans)
            compiled_dict[name] = pda

        if call_id is not None:
            self._call_compiled_transformations[call_id] = compiled_dict
        self.__compiled_transformations = compiled_dict

        return compiled_dict

    def get_compiled_transf(self, call_id: Optional[int] = None):
        if call_id is not None and call_id in self._call_compiled_transformations:
            return self._call_compiled_transformations[call_id]
        if len(self.__compiled_transformations) == 0:
            logger.error(f"Subpattern {self.name} has not be compiled yet!")
            raise Exception(f"Subpattern {self.name} has not be compiled yet!")
        return self.__compiled_transformations

    
    @property
    @abstractmethod
    def type(self) -> Literal["AND", "OR", "NOT"]:
        pass
    

    def generate_pda(self, pda: PDA, args: list[str], starting_state: int, call_id: Optional[int] = None) -> int:
        """
        Generates the transition needed for this subpattern in the pda @pda using the argument names from @args
        """
        self.check_args_nbr(args)
        next_state = pda.new_state()
        subpattern_name = self.name

        logger.trace(f"Adding transition for {self.type} subpattern {subpattern_name} (call_id={call_id})")
        transition = Transition(starting_state, '', CallTransition(subpattern_name, args, call_id=call_id), [],
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
