# 03: JSON Pattern Deserialization and Web Adapter Migration

**What to build:** A caller can compile compound pattern structures from JSON definitions into typed composite pattern trees, and serialize match results into a structured dictionary for consumption by external adapters. The web visualizer endpoint is migrated to use the compiler and result serializer, eliminating in-place dictionary mutations.

**Blocked by:** 02: Composite Patterns (And, Or, Not) and Directory Compilation

**Status:** resolved

- [x] `compile_json` recursively parses nested pattern JSON definitions into a composite `CompiledPattern` tree.
- [x] `compile_json` handles leaf pattern objects containing pattern code and metadata.
- [x] `MatchResult.to_dict()` serializes a match result hierarchy into the dictionary format expected by the web visualizer.
- [x] The web visualizer matching endpoint compiles incoming compound patterns using `compile_json`.
- [x] In-place dictionary mutations (e.g. forcing `name = 'and'` on untyped dictionaries) are completely removed from web handlers.
- [x] The web visualizer matching endpoint formats responses using `MatchResult.to_dict()`.
