# Pyttern Domain Glossary

Core domain terminology for Pyttern's structural pattern matching engine.

## Language

**Pattern**:
A structural code query expressed in extended syntax, compiled into a Pushdown Automaton to match Abstract Syntax Tree nodes.
_Avoid_: Query, template, rule

**Subpattern**:
A reusable, named pattern definition expressing logical composition (AND, OR, NOT) over sibling AST sequences.
_Avoid_: Macro, sub-rule, fragment

**Subpattern Catalog**:
An explicit registry and resolution table that stores subpattern definitions and their context-compiled Pushdown Automata, serving as the import namespace for patterns.
_Avoid_: Global registry, macro table, loaded_subpatterns

**Call Context**:
The Concrete Syntax Tree node type and block scope at the call site where a subpattern is invoked, determining AST pruning.
_Avoid_: Call site, parent context

**Matcher**:
The simulation engine that steps Pushdown Automata across target Concrete Syntax Trees with environment variable bindings.
_Avoid_: Engine, runner, evaluator
