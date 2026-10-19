# 1. Explicit Subpattern Catalog and Elimination of Global State

Subpatterns were previously registered via `__post_init__` side-effects into an ambient global dictionary (`loaded_subpatterns`), causing cross-test contamination, circular imports between `SubPattern.py` and `Python_to_PDA`, and preventing concurrent matching pipelines.

We decided to replace the global mutable dictionary with an explicit `SubPatternCatalog` module. The catalog acts as an import table holding subpattern definitions, delegating AST-to-PDA compilation to the active `LanguageProcessor`, and caching compiled transformations keyed by `(subpattern_name, context_type)`. 

Key architectural agreements:
- **No global fallback catalog**: All execution paths (CLI, library API, and tests) construct or receive an explicit catalog instance. `PytternMatcher` owns a `SubPatternCatalog` populated from CLI `-s` flags or passed by callers.
- **Import table abstraction**: The catalog API is shaped around `load_file()`, `register()`, and `get()`, serving as the direct foundation for future file-based `?import` pattern statements.
- **Seam decoupling**: AST-to-PDA compilation is moved to `LanguageProcessor`, eliminating circular imports from `SubPattern.py`.
- **Runtime thread-through**: `Matcher` and `subpatterns.py` receive the catalog directly to resolve transitions during execution.
