"""Python AST tree pruning visitor for simplifying ANTLR trees."""

from typing import Any

from antlr4 import ParseTreeListener, ParseTreeVisitor, RuleContext, TerminalNode
from loguru import logger

from ..generic_to_pda import Generic_to_PDA
from ...antlr.python import Python3Parser, Python3ParserVisitor
from ..generic_tree_pruner import GenericTreePruner


class BlockEndContext(RuleContext):
    """Synthetic marker node appended to indicate the end of a block scope."""

    def __init__(self, parent: RuleContext | None = None, invokingStateNumber: int | None = None):
        """Initialize the synthetic block end context.

        Args:
            parent: Parent AST rule context.
            invokingStateNumber: Optional parser state number.
        """
        super().__init__(parent, invokingStateNumber)
        self.children = []

    def enterRule(self, listener: ParseTreeListener) -> None:
        """Notify listener on entering the block end rule.

        Args:
            listener: Active parse tree listener.
        """
        if hasattr(listener, "enterBlockEnd"):
            listener.enterBlock(self)

    def exitRule(self, listener: ParseTreeListener) -> None:
        """Notify listener on exiting the block end rule.

        Args:
            listener: Active parse tree listener.
        """
        if hasattr(listener, "exitBlockEnd"):
            listener.exitBlock(self)

    def accept(self, visitor: ParseTreeVisitor) -> Any:
        """Dispatch visit to visitor if supported, or default to child visits.

        Args:
            visitor: Active parse tree visitor.

        Returns:
            Any: Result of the visit invocation.
        """
        logger.debug(f"Accepting {self} in {visitor}")
        if hasattr(visitor, "visitBlockEnd"):
            logger.debug(f"{visitor} has visitBlockEnd")
            return visitor.visitBlockEnd(self)
        else:
            logger.debug(f"{visitor} does not have visitBlockEnd")
            return visitor.visitChildren(self)


class TreePruner(GenericTreePruner, Python3ParserVisitor):
    """Python-specific AST pruner that retains essential grammar constructs."""

    def __init__(self) -> None:
        """Initialize Python tree pruning rules with preserved node types and pruned tokens."""
        TO_KEEP = (
            TerminalNode,
            Python3Parser.NameContext,
            Python3Parser.Expr_wildcardContext,
            Python3Parser.ExprContext,
        )
        TO_REMOVE = "():,."
        super().__init__(TO_KEEP, TO_REMOVE)

    def visitBlock(self, ctx: Python3Parser.BlockContext) -> Python3Parser.BlockContext:
        """Visit block node and append BlockEndContext marker when appropriate.

        Args:
            ctx: Python block rule context.

        Returns:
            Python3Parser.BlockContext: Block node with synthetic end marker attached if needed.
        """
        ctx = self.visitChildren(ctx)

        put = True
        if len(ctx.children) == 1:
            child = ctx.getChild(0)
            if Generic_to_PDA.lookahead(child, (Python3Parser.Double_wildcardContext)):
                put = False

        if put:
            end_node = BlockEndContext(parent=ctx)
            ctx.children.append(end_node)

        return ctx

    def visitAtom_expr(self, ctx: Python3Parser.Atom_exprContext) -> Any:
        """Prune single-child atom expression wrappers.

        Args:
            ctx: Atom expression context.

        Returns:
            Any: Compressed child node.
        """
        return self.prune_single_child(ctx)

    def visitExpr_stmt(self, ctx: Python3Parser.Expr_stmtContext) -> Any:
        """Prune single-child expression statement wrappers.

        Args:
            ctx: Expression statement context.

        Returns:
            Any: Compressed child node.
        """
        return self.prune_single_child(ctx)

    def visitTfpdef(self, ctx: Python3Parser.TfpdefContext) -> Any:
        """Prune single-child typed parameter definition wrappers.

        Args:
            ctx: Typed parameter definition context.

        Returns:
            Any: Compressed child node.
        """
        return self.prune_single_child(ctx)

    def visitTerminal(self, node: Any) -> Any:
        """Filter out indentation, dedentation, and newline tokens.

        Args:
            node: Terminal token leaf node.

        Returns:
            Any: Terminal node, or None if token represents whitespace formatting.
        """
        sym = node.getSymbol()
        if sym.type in [Python3Parser.NEWLINE, Python3Parser.INDENT, Python3Parser.DEDENT]:
            return None
        return super().visitTerminal(node)