from antlr4 import ParseTreeListener, ParseTreeVisitor, ParserRuleContext, RuleContext, TerminalNode
from loguru import logger

from ...antlr.python import Python3ParserVisitor, Python3Parser
from ..generic_tree_pruner import GenericTreePruner

class BlockEndContext(RuleContext):
    def __init__(self, parent = None, invokingStateNumber = None):
        super().__init__(parent, invokingStateNumber)
        self.children = []

    def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterBlockEnd" ):
                listener.enterBlock(self)

    def exitRule(self, listener:ParseTreeListener):
        if hasattr( listener, "exitBlockEnd" ):
            listener.exitBlock(self)

    def accept(self, visitor:ParseTreeVisitor):
        logger.debug(f"Accepting {self} in {visitor}")
        if hasattr( visitor, "visitBlockEnd" ):
            logger.debug(f"{visitor} has visitBlockEnd")
            return visitor.visitBlockEnd(self)
        else:
            logger.debug(f"{visitor} does not have visitBlockEnd")
            return visitor.visitChildren(self)

class TreePruner(GenericTreePruner, Python3ParserVisitor):
    def __init__(self):
        TO_KEEP = (
            TerminalNode,
            Python3Parser.NameContext,
            Python3Parser.Expr_wildcardContext
        )
        TO_REMOVE = "():,."
        super().__init__(TO_KEEP, TO_REMOVE)

    def visitBlock(self, ctx: Python3Parser.BlockContext):
        res = self.visitChildren(ctx)
        end_node = BlockEndContext(parent=ctx)
        res.children.append(end_node)
        return ctx

    def visitTest(self, ctx: Python3Parser.TestContext):
        return self.prune_single_child(ctx)

    def visitExpr_stmt(self, ctx:Python3Parser.Expr_stmtContext):
        return self.prune_single_child(ctx)

    def visitTfpdef(self, ctx:Python3Parser.TfpdefContext):
        return self.prune_single_child(ctx)

    def visitExpr(self, ctx:Python3Parser.ExprContext):
        return self.prune_single_child(ctx)

    def visitTerminal(self, node):
        sym = node.getSymbol()
        if sym.type in [Python3Parser.NEWLINE, Python3Parser.INDENT, Python3Parser.DEDENT]:
            return None
        return super().visitTerminal(node)
