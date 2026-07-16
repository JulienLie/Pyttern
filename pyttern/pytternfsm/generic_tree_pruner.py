from typing import TypeVar

from antlr4 import RuleContext, Token

T = TypeVar('T', bound=RuleContext)

class GenericTreePruner():
    def __init__(self, TO_KEEP, TO_REMOVE):
        self.TO_KEEP = TO_KEEP
        self.TO_REMOVE = TO_REMOVE

    def visitChildren(self, node: T) -> T:
        result = super().visitChildren(node)

        node.children = result
        for child in result:
            if child is not None:
                child.parentCtx = node

        return node

    def prune_single_child(self, node):
        # As long as there is only one child, prune everything except the terminal node (last node) or important nodes (identifier & expression wildcard)
        new_child = self.visitChildren(node)
        while len(new_child.children) == 1:
            new_child = new_child.getChild(0)
            if isinstance(new_child, self.TO_KEEP):
                return new_child
        return new_child

    def visitWildcard_number(self, ctx):
        return ctx

    def visitTerminal(self, node):
        # Remove syntactic nodes which carry no information (only useful in the parsing, not in the PDA)
        sym = node.getSymbol()
        if sym.type == Token.EOF:
            sym.text = "<EOF>"
            return node
        txt = node.getText().strip()
        if txt in self.TO_REMOVE:
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
