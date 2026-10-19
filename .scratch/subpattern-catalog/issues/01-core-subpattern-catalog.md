# 01: Core Subpattern Catalog and Decoupled Subpattern Data Model

**What to build:** An isolated catalog module that allows callers to register, parse, and load subpattern definitions from files and strings without writing to any global dictionary or creating cross-test state leakage. `SubPattern` definitions become pure data models stripped of implicit global registration side effects.

**Blocked by:** None (can start immediately)

**Status:** resolved

- [x] A dedicated subpattern catalog class provides explicit registration and retrieval of subpattern definitions by name.
- [x] Subpatterns parsed from files or strings are returned directly to the caller or registered into a provided catalog, without mutating any module-level dictionary.
- [x] Multiple independent catalog instances can coexist without sharing state or colliding on subpattern names.
- [x] Subpattern data classes (`AndSubPattern`, `OrSubPattern`, `NotSubPattern`) represent pure syntax definitions without lazy imports of compiler classes or private compilation caches.
- [x] Querying an unknown subpattern name from the catalog returns a clean None or raises a descriptive error.

## Comments

Implemented `SubPatternCatalog` in `pyttern/subpattern/catalog.py` providing isolated registration, retrieval, and loading from strings and files. Decoupled `BaseSubPattern` from global side-effects by removing `__post_init__`, private `__compiled_transformations`, and lazy compiler imports. Added comprehensive unit tests in `tests/test_subpattern_catalog.py`.
