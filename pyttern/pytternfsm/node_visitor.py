class NodeVisitor:
    def visit(self, node):
        if node is None:
            return None
        rule_name = getattr(node, "rule_name", "")
        method_name = f"visit{rule_name}"
        visitor = getattr(self, method_name, self.visitChildren)
        return visitor(node)

    def visitChildren(self, node):
        results = []
        for child in node.children:
            res = self.visit(child)
            if res is not None:
                results.append(res)
        return results