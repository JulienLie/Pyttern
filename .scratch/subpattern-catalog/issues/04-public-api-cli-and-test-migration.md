# 04: Public API, CLI Integration, and Test Suite Migration

**What to build:** Public interface and command-line wiring for explicit catalogs, and full migration of existing subpattern test suites to isolated catalogs. Top-level matching entrypoints accept an optional catalog, CLI `-s` flags correctly populate the matcher's catalog, and tests execute without manual global cleanup hacks.

**Blocked by:** 03: Compiler and Runtime Simulator Integration

**Status:** resolved

- [x] `PytternMatcher` accepts an optional `catalog` parameter in its constructor, instantiating an empty catalog if none is provided.
- [x] Top-level matching helper functions (`match_files`) accept an optional `catalog` parameter and thread it to the matcher.
- [x] The CLI command parser accepts multiple `-s / --sub` flags and loads each subpattern file into the matcher's catalog without crashes.
- [x] Existing subpattern and NOT test suites are migrated to use explicit catalogs.
- [x] All tests pass without requiring calls to clear or reset global state between test functions.
