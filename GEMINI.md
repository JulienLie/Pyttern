# Pyttern Development & Testing Invariants

## ANTLR Parse Tree & Token Handling
- **Equality & Comparisons**: ANTLR nodes (`ParserRuleContext`, `TerminalNode`) do not implement value `__eq__`. Never rely on `node1 == node2` for AST equivalence across different trees or passes; use `Matcher._match_tree(tree1, tree2)`.
- **Terminal Nodes**: `TerminalNode` instances do not have a `children` attribute (`hasattr(node, 'children') is False`). Always check `isinstance(node, Tree)` (from `antlr4.tree.Tree`) when distinguishing AST nodes from primitives (`int`, `str`), and compare terminal nodes with `str(node1) == str(node2)`.
- **Tree Unwrapping**: Unwrapping single-child `ExprContext` to `NameContext` must be handled carefully: unwrap only to extract the target child without blanket collapsing both sides, to avoid over-matching wildcards.

## Subpattern Operators (`AND`, `OR`, `NOT`)
- **AND Operator**: Multi-pattern conjunctions must combine candidates across variable scopes and sibling index ranges without requiring identical sibling starting offsets, while enforcing disjoint bindings for non-identical variables.
- **OR Operator with NOT**: Branch evaluation must only anchor at `siblings[0]` when the branch contains a `NOT` subpattern call (negative lookahead); positive branches must evaluate across sibling offsets via `eval_base`.
- **Environment Merging**: `Environment.merge` must support both primitive mock values (used in configuration unit tests) and AST `Tree` nodes, preferring `NameContext` bindings when resolving equivalent wrapped expression nodes.

## Testing & Pattern Symmetry
- **Symmetric Wildcards**: When testing patterns with symmetric roles (such as multiple concrete creators or products in Factory Method), assert on set membership rather than hardcoding index-0 binding order, as matching order can vary across runs.
- **Regression Verification**: Always execute the full test suite (`poetry run pytest`) before concluding changes to `Matcher`, `Environment`, or subpattern simulation logic.
