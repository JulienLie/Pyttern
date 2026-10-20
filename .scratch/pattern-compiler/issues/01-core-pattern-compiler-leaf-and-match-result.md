# 01: Core Pattern Compiler, Leaf Pattern, and MatchResult

**What to build:** A caller can compile a single pattern from a file or code string into a leaf pattern using a pattern compiler configured with an active language and subpattern catalog. Executing the pattern against target code (passed as either pre-parsed syntax tree nodes or raw code/file paths) performs structural matching and returns an immutable result object that evaluates cleanly in boolean contexts and provides match bindings and status.

**Blocked by:** None (can start immediately)

**Status:** ready-for-agent

- [ ] `PatternCompiler` initializes with an active language and an optional `SubPatternCatalog`.
- [ ] `compile_code` compiles a raw pattern string into an executable `LeafPattern` wrapping a Pushdown Automaton.
- [ ] `compile_file` loads and compiles a pattern file into an executable `LeafPattern`.
- [ ] Polymorphic `compile(source)` automatically dispatches between raw code strings and file paths.
- [ ] `LeafPattern.matches(target)` accepts pre-parsed CST nodes and executes matching against the Pushdown Automaton.
- [ ] `LeafPattern.matches(target)` accepts raw code strings or file paths, automatically generating CST nodes via the language processor.
- [ ] Matching returns an immutable `MatchResult` implementing `__bool__` representing match success.
- [ ] `MatchResult` provides access to match name, boolean status, and underlying match set bindings.
