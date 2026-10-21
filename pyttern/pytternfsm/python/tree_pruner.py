from __future__ import annotations

from typing import Any

from antlr4 import ParseTreeListener, ParseTreeVisitor, ParserRuleContext, RuleContext, TerminalNode
from loguru import logger

from ..generic_to_pda import Generic_to_PDA
from ...antlr.python import Python3ParserVisitor, Python3Parser
from ..generic_tree_pruner import GenericTreePruner


class BlockEndContext(RuleContext):
    def __init__(self, parent: RuleContext | None = None, invokingStateNumber: int | None = None) -> None:
        super().__init__(parent, invokingStateNumber)
        self.children: list[Any] = []

    def enterRule(self, listener: ParseTreeListener) -> None:
        if hasattr(listener, "enterBlockEnd"):
            getattr(listener, "enterBlockEnd")(self)
        elif hasattr(listener, "enterBlock"):
            getattr(listener, "enterBlock")(self)

    def exitRule(self, listener: ParseTreeListener) -> None:
        if hasattr(listener, "exitBlockEnd"):
            getattr(listener, "exitBlockEnd")(self)
        elif hasattr(listener, "exitBlock"):
            getattr(listener, "exitBlock")(self)

    def accept(self, visitor: ParseTreeVisitor) -> Any:
        logger.debug(f"Accepting {self} in {visitor}")
        if hasattr(visitor, "visitBlockEnd"):
            logger.debug(f"{visitor} has visitBlockEnd")
            return getattr(visitor, "visitBlockEnd")(self)
        else:
            logger.debug(f"{visitor} does not have visitBlockEnd")
            return visitor.visitChildren(self)


class TreePruner(GenericTreePruner, Python3ParserVisitor):
    def __init__(self) -> None:
        TO_KEEP = (
            TerminalNode,
            Python3Parser.NameContext,
            Python3Parser.Expr_wildcardContext,
            Python3Parser.ExprContext,
        )
        TO_REMOVE = "():,."
        super().__init__(TO_KEEP, TO_REMOVE)

    def visitBlock(self, ctx: Python3Parser.BlockContext) -> Python3Parser.BlockContext:
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
        return self.prune_single_child(ctx)

    def visitExpr_stmt(self, ctx: Python3Parser.Expr_stmtContext) -> Any:
        return self.prune_single_child(ctx)

    def visitTfpdef(self, ctx: Python3Parser.TfpdefContext) -> Any:
        return self.prune_single_child(ctx)

    def visitTerminal(self, node: TerminalNode) -> TerminalNode | None:
        sym = node.getSymbol()
        if sym.type in [Python3Parser.NEWLINE, Python3Parser.INDENT, Python3Parser.DEDENT]:
            return None
        return super().visitTerminal(node)


__all__ = ["BlockEndContext", "TreePruner"]
