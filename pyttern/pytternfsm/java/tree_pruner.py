"""Java AST tree pruning visitor for simplifying ANTLR trees."""

from typing import Any

from antlr4 import TerminalNode

from ...antlr.java.JavaParser import JavaParser
from ...antlr.java.JavaParserVisitor import JavaParserVisitor
from ..generic_tree_pruner import GenericTreePruner


class TreePruner(GenericTreePruner, JavaParserVisitor):
    """Java-specific AST pruner that retains essential grammar constructs."""

    def __init__(self) -> None:
        """Initialize Java tree pruning rules with preserved node types and pruned delimiters."""
        TO_KEEP = (
            TerminalNode,
            JavaParser.IdentifierContext,
            JavaParser.Expr_wildcardContext,
        )
        TO_REMOVE = "():,.}{;"
        super().__init__(TO_KEEP, TO_REMOVE)

    def visitIdentifier(self, ctx: JavaParser.IdentifierContext) -> Any:
        """Unwrap intermediate identifier nodes if child is a wildcard.

        Args:
            ctx: Identifier context node.

        Returns:
            Any: Unwrapped child wildcard node or original identifier.
        """
        if (
            ctx.getChildCount() == 1
            and (isinstance(ctx.getChild(0), JavaParser.Simple_wildcardContext)
                 or isinstance(ctx.getChild(0), JavaParser.Var_wildcardContext))
        ):
            return ctx.getChild(0)
        return ctx

    def visitTypeType(self, ctx: JavaParser.TypeTypeContext) -> Any:
        """Unwrap intermediate typeType nodes if child is a wildcard.

        Args:
            ctx: TypeType context node.

        Returns:
            Any: Unwrapped child wildcard node or original typeType context.
        """
        if (
            ctx.getChildCount() == 1
            and (isinstance(ctx.getChild(0), JavaParser.Simple_wildcardContext)
                 or isinstance(ctx.getChild(0), JavaParser.Var_wildcardContext))
        ):
            return ctx.getChild(0)
        return ctx

    def visitExpression(self, ctx: JavaParser.ExpressionContext) -> Any:
        """Prune single-child expression wrappers.

        Args:
            ctx: Expression context node.

        Returns:
            Any: Innermost preserved or non-single child.
        """
        return self.prune_single_child(ctx)

    def visitPrimary(self, ctx: JavaParser.PrimaryContext) -> Any:
        """Prune single-child primary expression wrappers.

        Args:
            ctx: Primary expression context node.

        Returns:
            Any: Innermost preserved or non-single child.
        """
        return self.prune_single_child(ctx)

