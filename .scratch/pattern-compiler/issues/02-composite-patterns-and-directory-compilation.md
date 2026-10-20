# 02: Composite Patterns (And, Or, Not) and Directory Compilation

**What to build:** A caller can compile composite patterns from directory trees or programmatic combinations into structured logical pattern trees (AND, OR, NOT). Composite patterns evaluate matching over their child patterns with short-circuiting semantics based on stopping rules.

**Blocked by:** 01: Core Pattern Compiler, Leaf Pattern, and MatchResult

**Status:** ready-for-agent

- [ ] `AndPattern` evaluates all child patterns and short-circuits on the first failure when stopping at first match.
- [ ] `OrPattern` evaluates child patterns and short-circuits on the first successful match.
- [ ] `NotPattern` inverts the match outcome of its single child pattern.
- [ ] `compile_dir` recursively traverses directories, treating subdirectories named `and`, `or`, and `not` as logical combinators.
- [ ] Subdirectories with names other than `and`, `or`, or `not` default to `and` composition.
- [ ] Directory compilation returns an immutable composite `CompiledPattern` tree containing leaf patterns for pattern files.
- [ ] Polymorphic `compile(source)` automatically recognizes directory paths and dispatches to `compile_dir`.
- [ ] Composite matching produces a hierarchical `MatchResult` containing child match results.
