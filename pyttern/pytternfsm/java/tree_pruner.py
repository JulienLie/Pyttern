from antlr4 import TerminalNode, Token

from ...antlr.python import Python3ParserVisitor, Python3Parser
from ...antlr.java import JavaParserVisitor, JavaParser


class TreePruner(JavaParserVisitor.JavaParserVisitor):

    def visitChildren(self, node):
        result = super().visitChildren(node)

        node.children = result
        for child in result:
            if child is not None:
                child.parentCtx = node

        return node

    def prune_single_child(self, node):
        new_child = self.visitChildren(node)
        while len(new_child.children) == 1:
            new_child = new_child.getChild(0)
            if isinstance(new_child, TerminalNode):
                return new_child
            if isinstance(new_child, JavaParser.JavaParser.IdentifierContext):
                return new_child
            if isinstance(new_child, JavaParser.JavaParser.Expr_wildcardContext):
                return new_child
        return new_child
    
    def visitIdentifier(self, ctx:JavaParser.JavaParser.IdentifierContext):
        if isinstance(ctx.getChild(0), JavaParser.JavaParser.Simple_wildcardContext) or isinstance(ctx.getChild(0), JavaParser.JavaParser.Var_wildcardContext):
            return ctx.getChild(0)
        return ctx

    def visitExpression(self, ctx:JavaParser.JavaParser.ExpressionContext):
        return self.prune_single_child(ctx)

    def visitPrimary(self, ctx:JavaParser.JavaParser.PrimaryContext):
        return self.prune_single_child(ctx)

    def visitWildcard_number(self, ctx:Python3Parser.Wildcard_numberContext):
        return ctx

    def visitTerminal(self, node):
        sym = node.getSymbol()
        if sym.type == Token.EOF:
            sym.text = "<EOF>"
            return node
        txt = node.getText().strip()
        if txt in "():,.}{;":
            return None
        return node

    def visitErrorNode(self, node):
        return node

    def defaultResult(self): # pas utilisé?
        return []

    def aggregateResult(self, aggregate, nextResult): # pas utilisé?
        if nextResult is None:
            return aggregate
        return aggregate + [nextResult]
