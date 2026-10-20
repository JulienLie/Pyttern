# 03: Compiler and Runtime Simulator Integration

**What to build:** End-to-end integration of the explicit catalog through pattern compilation and simulation. The pattern compiler resolves and compiles subpattern transitions via the passed catalog, and the runtime simulator executes subpattern calls and queries (including nested NOT checks) through the catalog rather than process-wide globals.

**Blocked by:** 02: Language Processor Compilation Seam and Context-Keyed Caching

**Status:** resolved

- [x] The pattern compiler receives the active subpattern catalog and delegates all subpattern call site resolutions and compilations to it.
- [x] The runtime matcher accepts the subpattern catalog in its constructor and passes it down into subpattern execution routines.
- [x] Subpattern evaluators (for AND, OR, and NOT compositions) query definitions and transformations exclusively from the provided catalog.
- [x] Nested subpattern checks (such as detecting whether a called transformation executes a NOT subpattern) resolve cleanly through the catalog.
- [x] Patterns referencing subpatterns compile and match against code with correct variable bindings end-to-end without reading from any global dictionary.
