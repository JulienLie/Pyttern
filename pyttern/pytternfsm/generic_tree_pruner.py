"""Generic AST tree pruner base class for eliminating uninformative intermediate nodes."""
from __future__ import annotations

from typing import Any, TypeVar

from antlr4 import RuleContext, Token
from antlr4.tree.Tree import ParseTreeVisitor, TerminalNode

T = TypeVar("T", bound=RuleContext)


class GenericTreePruner(ParseTreeVisitor):
    """Base parse tree pruner that strips redundant syntax nodes and compresses chains.

    Attributes:
        TO_KEEP: Tuple of node types that should be preserved during single-child pruning.
        TO_REMOVE: Collection of terminal node string values (e.g. delimiters) to prune.
    """

    def __init__(
        self,
        TO_KEEP: tuple[type, ...] | type,
        TO_REMOVE: str | set[str] | tuple[str, ...] | list[str],
    ) -> None:
        """Initialize tree pruner with retention rules.

        Args:
            TO_KEEP: Tuple of AST node types to preserve.
            TO_REMOVE: Collection of punctuation or whitespace symbols to discard.
        """
        self.TO_KEEP = TO_KEEP
        self.TO_REMOVE = TO_REMOVE

    def visitChildren(self, node: T) -> T:
        """Visit and update children of an AST node, pruning discarded nodes.

        Args:
            node: Target parse tree rule context.

        Returns:
            T: Updated node with pruned children.
        """
        if getattr(node, "_is_pruned", False):
            return node
        result: list[Any] = super().visitChildren(node)

        node.children = result
        for child in result:
            child.parentCtx = node

        node._is_pruned = True
        return node

    def prune_single_child(self, node: Any) -> Any:
        """Compress single-child wrapper chains down to a preserved or leaf node.

        Args:
            node: Parse tree node to prune downward.

        Returns:
            Any: The innermost non-single or preserved descendant node.
        """
        new_child = self.visitChildren(node)
        while len(new_child.children) == 1:
            new_child = new_child.getChild(0)
            if isinstance(new_child, self.TO_KEEP) or isinstance(new_child, TerminalNode):
                break
        return new_child

    def visitWildcard_number(self, ctx: Any) -> Any:
        """Preserve numeric wildcard bound nodes.

        Args:
            ctx: Wildcard number context node.

        Returns:
            Any: Unmodified context node.
        """
        return ctx

    def visitTerminal(self, node: TerminalNode) -> TerminalNode | None:
        """Visit terminal leaf tokens and prune delimiters defined in TO_REMOVE.

        Args:
            node: Terminal token AST node.

        Returns:
            TerminalNode | None: The terminal node, or None if pruned.
        """
        sym = node.getSymbol()
        if sym.type == Token.EOF:
            sym.text = "<EOF>"
        if sym.text in self.TO_REMOVE:
            return None
        return node

    def visitErrorNode(self, node: Any) -> Any:
        """Preserve error parse nodes.

        Args:
            node: Error node.

        Returns:
            Any: Unmodified error node.
        """
        return node

    def defaultResult(self) -> list[Any]:
        """Return the default empty list result accumulator.

        Returns:
            list[Any]: Empty list.
        """
        return []

    def aggregateResult(self, aggregate: list[Any], nextResult: Any) -> list[Any]:
        """Aggregate child visitor results into a list, skipping None values.

        Args:
            aggregate: Accumulator list.
            nextResult: Child visitor result.

        Returns:
            list[Any]: Aggregated results list.
        """
        if nextResult is None:
            return aggregate
        aggregate.append(nextResult)
        return aggregate


__all__ = ["GenericTreePruner"]
