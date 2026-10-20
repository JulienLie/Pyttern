# 03: JSON Pattern Deserialization and Web Adapter Migration

**What to build:** A caller can compile compound pattern structures from JSON definitions into typed composite pattern trees, and serialize match results into a structured dictionary for consumption by external adapters. The web visualizer endpoint is migrated to use the compiler and result serializer, eliminating in-place dictionary mutations.

**Blocked by:** 02: Composite Patterns (And, Or, Not) and Directory Compilation

**Status:** ready-for-agent

- [ ] `compile_json` recursively parses nested pattern JSON definitions into a composite `CompiledPattern` tree.
- [ ] `compile_json` handles leaf pattern objects containing pattern code and metadata.
- [ ] `MatchResult.to_dict()` serializes a match result hierarchy into the dictionary format expected by the web visualizer.
- [ ] The web visualizer matching endpoint compiles incoming compound patterns using `compile_json`.
- [ ] In-place dictionary mutations (e.g. forcing `name = 'and'` on untyped dictionaries) are completely removed from web handlers.
- [ ] The web visualizer matching endpoint formats responses using `MatchResult.to_dict()`.
