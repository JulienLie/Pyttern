# 02: Language Processor Compilation Seam and Context-Keyed Caching

**What to build:** Context-aware compilation of subpattern ASTs into Pushdown Automata through the language processor interface, caching compiled transformations inside the subpattern catalog keyed by `(subpattern_name, context_type)`. This ensures that calling a subpattern across different syntactic positions (e.g. block vs statement) compiles each variant cleanly without clobbering other call sites or emitting recompilation warnings.

**Blocked by:** 01: Core Subpattern Catalog and Decoupled Subpattern Data Model

**Status:** resolved

- [x] The language processor interface defines a method for compiling a subpattern definition into a Pushdown Automaton given a call context.
- [x] The Python language processor implements this method, pruning the subpattern transformation AST down to the calling context type and compiling it via the visitor.
- [x] The subpattern catalog maintains a cache of compiled transformations indexed by the subpattern name and the context AST type.
- [x] Repeated requests for the same subpattern in the same context type reuse the cached Pushdown Automata without re-compilation.
- [x] Invocations of the same subpattern in two different context types generate and cache independent Pushdown Automata without overwriting each other.

## Comments

Implemented `compile_subpattern` on `BaseProcessor` and `PythonProcessor`, delegating syntax tree pruning to `PythonProcessor.prune_subpattern_tree`. Implemented context-keyed compilation and caching on `SubPatternCatalog.get_or_compile(name, context, processor)`, indexing cache entries by `(subpattern_name, context_type)` so different call contexts coexist without clobbering. Comprehensive unit tests added in `tests/test_compilation_seam.py`.
