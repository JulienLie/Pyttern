# Spec: Explicit Subpattern Catalog and Encapsulated Compilation Seam

Status: ready-for-agent

## Problem Statement

When using subpatterns in Pyttern, subpattern definitions are implicitly registered into a shared global dictionary across the entire Python process. This causes cross-test pollution where subpatterns declared in one test leak into subsequent tests, prevents concurrent matching sessions, and makes it impossible to know which subpatterns are actively available to a pattern. 

Furthermore, when a subpattern is invoked across different syntactic positions in a pattern (such as at the file level and inside a nested block), the compiler overwrites previous transformations, emitting warnings and causing unstable matching behavior. Finally, subpattern compilation is tightly coupled to Python AST internals, preventing language independence and clean modular imports.

## Solution

Introduce an explicit `SubPatternCatalog` module that acts as an isolated import table for subpatterns. Subpattern definitions are parsed into clean AST data models without global side effects. Callers and the CLI explicitly construct or pass a `SubPatternCatalog` to `PytternMatcher`, which delegates context-specific Pushdown Automaton compilation to the active `LanguageProcessor` and caches compiled transformations by call context. The runtime `Matcher` receives the catalog explicitly, eliminating all ambient mutable state across the compiler and simulator seams while paving the way for future `?import` syntax.

## User Stories

1. As a pattern author, I want to define and use subpatterns in my project without their names colliding with subpatterns loaded by other patterns in the same process.
2. As a pattern author, I want to specify subpattern files on the command line using `-s` flags, so that they are loaded into an explicit catalog used for my match query.
3. As a pattern author, I want to call the same subpattern from different syntactic contexts (e.g., in a statement and in a block), so that both call sites match correctly without overwriting each other.
4. As a test author, I want to run tests with isolated subpattern catalogs, so that test cases never leak state into other tests or require manual global state cleanup.
5. As an API user, I want to pass an explicit `SubPatternCatalog` to `PytternMatcher`, so that I have complete control over the subpatterns available to my queries.
6. As an API user, I want to load subpatterns from files or strings into a catalog, so that I can programmatically configure pattern libraries.
7. As an API user, I want clear errors when a pattern references an undeclared subpattern, so that I can quickly diagnose missing subpattern definitions.
8. As a language tooling developer, I want subpattern AST compilation to be handled by the language processor, so that subpattern compilation does not hardcode Python-specific visitor classes in general data structures.
9. As a future contributor, I want the subpattern catalog structured as an import table, so that introducing syntactic `?import` statements will simply delegate to catalog operations.
10. As a performance-conscious user, I want compiled subpattern Pushdown Automata to be cached per call context type, so that repeated invocations avoid redundant tree pruning and compilation overhead.
11. As a CLI user, I want multiple `-s` flags to reliably register all provided subpattern files without argument parsing attribute crashes.
12. As a library consumer, I want clean exception messages when a subpattern is called with insufficient arguments, so that pattern syntax errors are immediately evident.
13. As a developer, I want `SubPattern` definitions to be pure, immutable data models, so that their state is predictable throughout pattern execution.
14. As an automator running concurrent searches, I want two `PytternMatcher` instances with different catalogs to execute simultaneously without race conditions or shared state.

## Implementation Decisions

- **Subpattern Catalog Module**:
  - A dedicated `SubPatternCatalog` module manages subpattern definitions and cached transformations.
  - The catalog exposes methods for registering subpatterns, loading subpatterns from files and strings, retrieving definitions by name, and retrieving or compiling transformations for a specific call context.
  - The catalog stores compiled transformations in a cache keyed by the pair of `(subpattern_name, context_type)`.
  - No global fallback catalog will exist; all execution paths must instantiate or receive an explicit catalog.

- **Decoupled Subpattern Data Model**:
  - `BaseSubPattern` and its subclasses (`AndSubPattern`, `OrSubPattern`, `NotSubPattern`) become pure data models.
  - The `__post_init__` side-effect that wrote to `loaded_subpatterns` is removed.
  - The private `__compiled_transformations` cache and the dynamic import of Python visitor classes are removed from `BaseSubPattern`.

- **Compilation Seam in Language Processor**:
  - The `LanguageProcessor` interface receives a method for compiling a subpattern transformation tree given a call context.
  - The Python language processor implements this method by pruning the transformation tree to match the call context type and compiling it into a Pushdown Automaton via the visitor.
  - `Python_to_PDA` receives the active catalog during visitor traversal and delegates subpattern lookup and compilation to `catalog.get_or_compile(name, context, processor)`.

- **Simulator and Matcher Integration**:
  - `Matcher` accepts the active `SubPatternCatalog` in its constructor.
  - When encountering a `CallTransition`, `Matcher` passes the catalog to `call_subpattern`.
  - The subpattern evaluation functions (`search`, `eval_base`, `OR_operator`, `AND_operator`, `NOT_operator`, `_tau_calls_not`) query subpattern definitions and transformations through the passed catalog instead of module globals.

- **CLI and Public API**:
  - `PytternMatcher` receives an optional `catalog` argument in its constructor, defaulting to a new empty catalog.
  - `match_files` accepts an optional `catalog` argument and passes it to `PytternMatcher`.
  - The CLI entrypoint initializes `PytternMatcher` and loads all files supplied via `--sub` into `matcher.catalog`.
  - The CLI argument parsing bug referencing non-existent `args.sub_patterns` is corrected to use `args.sub`.

## Testing Decisions

- **External Behavior Focus**: Tests will focus on verifying that subpatterns match and bind variables correctly across isolated catalogs, without checking internal visitor state.
- **Modules Tested**:
  - `PytternMatcher` and `match_files` with isolated catalogs.
  - `SubPatternCatalog` registration, loading, and context-caching behavior.
  - Multi-context subpattern invocations (same subpattern called in multiple syntactic positions).
  - CLI execution with multiple `-s` flags.
- **Prior Art**:
  - Existing tests in `tests/test_subpattern.py`, `tests/test_not.py`, and `tests/test_api.py`.
  - Existing tests will be migrated to instantiate and pass `SubPatternCatalog`, eliminating `loaded_subpatterns.clear()`.

## Out of Scope

- Implementing parser grammar changes for Python-like `?import` syntax (the catalog is designed to support it later, but parser modifications are deferred).
- Implementing Java subpattern AST parsing and compilation (seams are prepared, but Java subpattern grammar is not implemented in this scope).
- Modifying directory-based logical composition (AND/OR/NOT folder matching).

## Further Notes

- ADR-0001 in [`docs/adr/0001-subpattern-catalog.md`](file:///home/julien/Documents/phd/Pyttern/docs/adr/0001-subpattern-catalog.md) documents the architectural decision to eliminate global state and reject an ambient fallback catalog.
- Domain terms conform to [`GLOSSARY.md`](file:///home/julien/Documents/phd/Pyttern/GLOSSARY.md).
