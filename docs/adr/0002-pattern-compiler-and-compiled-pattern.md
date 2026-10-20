# 2. Pattern Compiler and Immutable Compiled Pattern Abstractions

Patterns were previously represented across the codebase as mutable, untyped nested dictionaries containing PDA instances and logical operator strings (`{'name': 'and', 'children': [...]}`). This caused pattern compilation (parsing files, AST creation, directory traversal, and JSON deserialization) to leak across the simulation engine, the web visualizer, and the CLI adapter, forcing redundant $M \times N$ re-compilations during batch wildcard matching.

We decided to introduce a dedicated pattern module (`pyttern.pattern`) featuring `PatternCompiler` and `CompiledPattern` abstractions:
- **Separation of compilation from execution**: `PatternCompiler` absorbs all parsing (files, directories, code strings, JSON) and returns an immutable `CompiledPattern` hierarchy (`LeafPattern`, `AndPattern`, `OrPattern`, `NotPattern`).
- **Encapsulated execution interface**: `CompiledPattern.matches(target)` owns the execution interface, handling short-circuit evaluation for composites and delegating elementary simulation to `Matcher.match`. Target CST/AST generation from raw strings or files is accepted directly.
- **Unified result object**: Matching produces an immutable `MatchResult` implementing `__bool__`, ending the divergence between boolean fast paths and detailed match dictionaries. When details are requested, `stop_at_first` respects user preference (defaulting to exhaustive matching); when details are disabled, `stop_at_first` is forced to `True`.
- **Elimination of legacy shims**: Untyped dictionary methods on `PytternMatcher` (`parse_json_pattern`, `_dir_to_pattern_tree`, `_match_pyttern_bool`, `_match_pyttern_details`) are removed in favor of direct compiler usage.
