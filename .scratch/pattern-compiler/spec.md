# Spec: Encapsulated Pattern Compilation and Immutable Compiled Patterns

Status: resolved

## Problem Statement

When working with structural patterns in Pyttern, patterns are treated as untyped, mutable dictionaries containing Pushdown Automaton instances and operator strings (`{'name': 'and', 'children': [...]}`). Callers must understand this internal dictionary structure, and adapters (such as the web visualizer) directly mutate dictionary keys in place during execution.

Furthermore, pattern compilation (reading files, directory trees, parsing ASTs, creating Pushdown Automata) is entangled with execution and logging inside `PytternMatcher`. When matching batches of patterns against code files using wildcards, each pattern is repeatedly re-read from disk, re-parsed, and re-compiled into a Pushdown Automaton for every target code file. For $M$ patterns and $N$ code files, this results in $M \times N$ redundant compilations instead of $M$, causing significant performance degradation.

Additionally, pattern matching results vary between raw booleans and nested dictionaries depending on runtime flags, forcing callers and internal logic to maintain duplicate execution paths.

## Solution

Introduce a deep pattern module featuring `PatternCompiler` and `CompiledPattern` abstractions:
- `PatternCompiler` absorbs all parsing (from files, directory trees, raw code strings, and JSON definitions) and transforms pattern sources into an immutable `CompiledPattern` hierarchy (`LeafPattern`, `AndPattern`, `OrPattern`, `NotPattern`).
- `CompiledPattern.matches(target)` owns the execution interface, handling short-circuit boolean evaluation across composite children and delegating single Pushdown Automaton simulation to the core matcher. Target code can be passed as either pre-parsed CST nodes or raw files/strings.
- Matching produces an immutable `MatchResult` object implementing `__bool__` for boolean ergonomics, while providing structured properties for match bindings, child results, and web visualizer dictionary serialization.
- `PytternMatcher` is streamlined into a thin orchestration adapter for batch wildcard matching and CLI invocation, compiling each pattern once and enforcing matching invariants: if details are disabled, `stop_at_first` is forced to `True`; if details are enabled, user preference is respected.
- Obsolete dictionary traversal methods on `PytternMatcher` are purged.

## User Stories

1. As a CLI user, I want batch wildcard matching to compile each pattern file only once, so that large multi-file matching runs complete quickly.
2. As a CLI user, I want pattern matching with details disabled to short-circuit on the first match, so that boolean queries execute at maximum performance.
3. As a CLI user, I want to request detailed match results while optionally stopping at the first match or collecting all occurrences, so that I have control over matching depth.
4. As a pattern developer, I want to compile a pattern directly from a file path using `compile_file`, so that I get a typed, validated pattern object.
5. As a pattern developer, I want to compile a pattern from raw code strings using `compile_code`, so that I can construct ad-hoc patterns dynamically without writing temporary files.
6. As a pattern developer, I want to compile a directory tree with `and/`, `or/`, and `not/` subfolders using `compile_dir`, so that hierarchical composite patterns are represented as typed composite pattern objects.
7. As a pattern author, I want non-operator subdirectories to default to `and` composition, so that existing directory hierarchies continue to work seamlessly.
8. As an API consumer, I want `compile(source)` to automatically infer whether the input is a file path, directory, or raw code string, so that I have a convenient single entry point.
9. As a web visualizer developer, I want to compile compound patterns from JSON payloads using `compile_json`, so that web requests don't rely on raw dictionary inspection or in-place key mutations.
10. As a developer, I want `CompiledPattern.matches()` to accept either a target file path, raw code string, or pre-parsed CST node, so that one-off scripts don't require boilerplate language processor setup.
11. As a performance-critical developer, I want to pass a pre-parsed CST node to `CompiledPattern.matches()`, so that I can match multiple patterns against a single AST without re-parsing the target code.
12. As an API consumer, I want `CompiledPattern.matches()` to return a `MatchResult` that evaluates to `True` or `False` in boolean contexts, so that simple conditional checks (`if pattern.matches(code):`) remain intuitive.
13. As an API consumer, I want `MatchResult` to provide access to detailed match bindings and child results, so that I can inspect match locations without calling a separate method or setting runtime flags.
14. As a web developer, I want `MatchResult.to_dict()` to produce a serialized dictionary matching the visualizer's expected schema, so that the web controller remains simple and declarative.
15. As a pattern author, I want subpatterns defined in a catalog to be resolved seamlessly during pattern compilation, so that composite and subpattern calls interact reliably.
16. As a library developer, I want `CompiledPattern` instances to be immutable, so that a compiled pattern can be safely shared across concurrent execution threads.
17. As a test author, I want to test pattern compilation and matching without dealing with untyped dictionaries or internal matcher configurations, so that tests only interact with public interfaces.
18. As a codebase maintainer, I want obsolete dictionary traversal methods removed from `PytternMatcher`, so that the codebase remains clean without lingering deprecated paths.

## Implementation Decisions

- **Pattern Module Abstraction**:
  - A dedicated pattern module encapsulates pattern compilation and immutable pattern trees.
  - `CompiledPattern` serves as the abstract base class defining `matches(target, stop_at_first=None) -> MatchResult`.
  - Concrete subclasses include `LeafPattern` (encapsulating an elementary Pushdown Automaton) and composite patterns (`AndPattern`, `OrPattern`, `NotPattern`) managing child pattern evaluations.
  - Composite patterns implement short-circuiting: `AndPattern` aborts on the first child failure when `stop_at_first` is active; `OrPattern` aborts on the first child success; `NotPattern` inverts the child outcome.

- **Pattern Compiler Seam**:
  - `PatternCompiler` accepts an active language and an optional `SubPatternCatalog` at construction.
  - Explicit factory methods (`compile_file`, `compile_code`, `compile_dir`, `compile_json`) absorb respective input parsing and return a `CompiledPattern`.
  - A polymorphic `compile(source)` method inspects the source type and dispatches to the corresponding factory method.
  - In `compile_dir`, directory names `'and'`, `'or'`, and `'not'` determine the operator combinator, with `'and'` as the default for directories.

- **Target Code Handling**:
  - `CompiledPattern.matches(target)` checks whether `target` is a string or path.
  - If a string or path is supplied, it generates the CST node using the pattern's associated language processor.
  - If a CST node is supplied, it executes directly without re-parsing.

- **Unified Match Result**:
  - `MatchResult` is an immutable class providing attributes: `matched: bool`, `name: str`, `matches: MatchSet`, and `children: list[MatchResult]`.
  - `MatchResult` implements `__bool__` returning `self.matched`.
  - `MatchResult.to_dict()` returns the dictionary hierarchy expected by the web visualizer.

- **Matching Invariants & Orchestration Adapter**:
  - When `match_details` is disabled (`False`), `stop_at_first` is forced to `True`.
  - When `match_details` is enabled (`True`), `stop_at_first` defaults to `False` but respects user overrides.
  - `PytternMatcher` is retained as a high-level adapter for CLI wildcard batch matching and logging.
  - In batch matching, `PytternMatcher` compiles pattern files once into `CompiledPattern` instances before running them against target code files.
  - Legacy dictionary traversal methods (`parse_json_pattern`, `_dir_to_pattern_tree`, `_match_pyttern_bool`, `_match_pyttern_details`) are removed from `PytternMatcher`.

## Testing Decisions

- **Good Test Criteria**: Tests must test only external behavior via `PatternCompiler.compile(...)` and `CompiledPattern.matches(...)`, asserting on `MatchResult` state without asserting on internal AST node structures or visitor state.
- **Seams Tested**:
  - **Primary Seam**: `PatternCompiler` compilation factory methods and `CompiledPattern.matches()` execution.
  - **Batch Orchestration Seam**: `PytternMatcher.match_wildcards()` verifying that single-pass compilation occurs and results aggregate correctly.
  - **Web Payload Seam**: `PatternCompiler.compile_json()` and `MatchResult.to_dict()` verifying correct visualizer serialization.
- **Prior Art**:
  - Existing compound pattern tests in `tests/test_compound_pattern.py`.
  - Existing API tests in `tests/test_api.py`.
  - Existing CLI wildcard matching tests.

## Out of Scope

- Changes to ANTLR grammar files or syntax parsing rules.
- Implementation of Java subpattern grammar.
- Refactoring internal Pushdown Automaton stepping inside `Matcher.step()` (covered in Candidate 2).
- Web frontend UI modifications (`pyttern/visualizer/web/dist/`).

## Further Notes

- ADR-0002 in [`docs/adr/0002-pattern-compiler-and-compiled-pattern.md`](file:///home/julien/Documents/phd/Pyttern/docs/adr/0002-pattern-compiler-and-compiled-pattern.md) documents the architectural decision.
- Terminology conforms strictly to [`GLOSSARY.md`](file:///home/julien/Documents/phd/Pyttern/GLOSSARY.md).
