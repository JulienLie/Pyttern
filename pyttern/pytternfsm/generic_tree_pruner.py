from __future__ import annotations

from typing import Any, TypeVar

from antlr4 import RuleContext, Token
from antlr4.tree.Tree import ParseTreeVisitor, TerminalNode

T = TypeVar("T", bound=RuleContext)


class GenericTreePruner(ParseTreeVisitor):
    def __init__(
        self,
        TO_KEEP: tuple[type, ...] | type,
        TO_REMOVE: str | set[str] | tuple[str, ...],
    ) -> None:
        self.TO_KEEP = TO_KEEP
        self.TO_REMOVE = TO_REMOVE

    def visitChildren(self, node: T) -> T:
        if getattr(node, "_is_pruned", False):
            return node
        result: list[Any] = super().visitChildren(node)

        node.children = result
        for child in result:
            if child is not None:
                child.parentCtx = node

        node._is_pruned = True
        return node

    def prune_single_child(self, node: Any) -> Any:
        # As long as there is only one child, prune everything except the terminal node (last node) or important nodes (identifier & expression wildcard)
        new_child = self.visitChildren(node)
        while len(new_child.children) == 1:
            new_child = new_child.getChild(0)
            if isinstance(new_child, self.TO_KEEP):
                return new_child
        return new_child

    def visitWildcard_number(self, ctx: Any) -> Any:
        return ctx

    def visitTerminal(self, node: TerminalNode) -> TerminalNode | None:
        # Remove syntactic nodes which carry no information (only useful in the parsing, not in the PDA)
        sym = node.getSymbol()
        if sym.type == Token.EOF:
            sym.text = "<EOF>"
            return node
        txt = node.getText().strip()
        if txt in self.TO_REMOVE:
            return None
        return node

    def visitErrorNode(self, node: Any) -> Any:
        return node

    def defaultResult(self) -> list[Any]:
        return []

    def aggregateResult(self, aggregate: list[Any], nextResult: Any) -> list[Any]:
        if nextResult is None:
            return aggregate
        return aggregate + [nextResult]


__all__ = ["GenericTreePruner"]
