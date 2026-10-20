# 04: Batch Wildcard Matching Orchestration and Legacy Dictionary Purge

**What to build:** Batch wildcard matching compiles pattern files once and reuses compiled representations across all target code files. Matching invariants are enforced: simple boolean matches automatically stop on the first match, while detailed matches respect user stopping preferences. All obsolete dictionary traversal methods are purged, and the matcher operates strictly as a high-level orchestration adapter.

**Blocked by:** 03: JSON Pattern Deserialization and Web Adapter Migration

**Status:** ready-for-agent

- [ ] Batch wildcard matching compiles all discovered pattern files once before iterating across target code files.
- [ ] When match details are disabled, `stop_at_first` is automatically enforced as `True` for fast-path short-circuiting.
- [ ] When match details are enabled, `stop_at_first` defaults to `False` while honoring user overrides.
- [ ] The top-level matcher delegates all pattern compilation to `PatternCompiler`.
- [ ] Obsolete dictionary traversal methods (`parse_json_pattern`, `_dir_to_pattern_tree`, `_match_pyttern_bool`, `_match_pyttern_details`) are completely removed.
- [ ] CLI and API entry points are updated to consume `PatternCompiler` and `CompiledPattern`.
- [ ] Existing API and CLI test suites are migrated and pass cleanly.
