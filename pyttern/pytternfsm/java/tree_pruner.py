from antlr4 import TerminalNode, Token

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
        # NB: the getChildCount will always be 1 for an identifier but keeping it for consistency
        if ctx.getChildCount() == 1 and isinstance(ctx.getChild(0), JavaParser.JavaParser.Simple_wildcardContext) or isinstance(ctx.getChild(0), JavaParser.JavaParser.Var_wildcardContext):
            return ctx.getChild(0)
        return ctx
    
    def visitTypeType(self, ctx:JavaParser.JavaParser.TypeTypeContext):
        if ctx.getChildCount() == 1 and isinstance(ctx.getChild(0), JavaParser.JavaParser.Simple_wildcardContext) or isinstance(ctx.getChild(0), JavaParser.JavaParser.Var_wildcardContext):
            return ctx.getChild(0)
        return ctx

    def visitExpression(self, ctx:JavaParser.JavaParser.ExpressionContext):
        return self.prune_single_child(ctx)

    def visitPrimary(self, ctx:JavaParser.JavaParser.PrimaryContext):
        return self.prune_single_child(ctx)

    def visitWildcard_number(self, ctx):
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

    def defaultResult(self):
        return []

    def aggregateResult(self, aggregate, nextResult):
        if nextResult is None:
            return aggregate
        return aggregate + [nextResult]
