from __future__ import annotations

from typing import Any

from antlr4 import TerminalNode

from ...antlr.java.JavaParserVisitor import JavaParserVisitor
from ...antlr.java.JavaParser import JavaParser
from ..generic_tree_pruner import GenericTreePruner


class TreePruner(GenericTreePruner, JavaParserVisitor):
    def __init__(self) -> None:
        TO_KEEP = (
            TerminalNode,
            JavaParser.IdentifierContext,
            JavaParser.Expr_wildcardContext,
        )
        TO_REMOVE = "():,.}{;"
        super().__init__(TO_KEEP, TO_REMOVE)

    def visitIdentifier(self, ctx: JavaParser.IdentifierContext) -> Any:
        # Remove the intermediate "identifier" node iff its child is a wildcard
        # NB: the getChildCount will always be 1 for an identifier but keeping it for consistency
        if ctx.getChildCount() == 1 and (
            isinstance(ctx.getChild(0), JavaParser.Simple_wildcardContext)
            or isinstance(ctx.getChild(0), JavaParser.Var_wildcardContext)
        ):
            return ctx.getChild(0)
        return ctx

    def visitTypeType(self, ctx: JavaParser.TypeTypeContext) -> Any:
        # Remove the intermediate "typeType" node iff its child is a wildcard
        if ctx.getChildCount() == 1 and (
            isinstance(ctx.getChild(0), JavaParser.Simple_wildcardContext)
            or isinstance(ctx.getChild(0), JavaParser.Var_wildcardContext)
        ):
            return ctx.getChild(0)
        return ctx

    def visitExpression(self, ctx: JavaParser.ExpressionContext) -> Any:
        return self.prune_single_child(ctx)

    def visitPrimary(self, ctx: JavaParser.PrimaryContext) -> Any:
        return self.prune_single_child(ctx)


__all__ = ["TreePruner"]
