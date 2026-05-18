from antlr4 import TerminalNode, Token

from ...antlr.python import Python3ParserVisitor, Python3Parser
from ..generic_tree_pruner import GenericTreePruner

class TreePruner(GenericTreePruner, Python3ParserVisitor):
    def __init__(self):
        TO_KEEP = (
            TerminalNode,
            Python3Parser.NameContext,
            Python3Parser.Expr_wildcardContext
        )
        TO_REMOVE = "():,."
        super().__init__(TO_KEEP, TO_REMOVE)

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
