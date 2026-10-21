import sys
from pathlib import Path
from unittest.mock import patch, MagicMock
import pytest

from pyttern import PytternMatcher, match_files, SubPatternCatalog
from pyttern.pattern import PatternCompiler, CompiledPattern, MatchResult
from pyttern.main import main


class TestLegacyDictionaryPurge:
    """Verifies all obsolete dictionary traversal methods are completely removed."""

    def test_legacy_dictionary_methods_removed(self):
        legacy_methods = [
            "parse_json_pattern",
            "_dir_to_pattern_tree",
            "_match_pyttern_bool",
            "_match_pyttern_details",
            "match_tree",
        ]
        matcher = PytternMatcher()
        for method_name in legacy_methods:
            assert not hasattr(PytternMatcher, method_name), f"PytternMatcher still has {method_name}"
            assert not hasattr(matcher, method_name), f"PytternMatcher instance still has {method_name}"


class TestMatchingInvariantsStopAtFirst:
    """Verifies stop_at_first invariants based on match_details flag."""

    def test_stop_at_first_enforced_when_details_disabled(self):
        # Default initialization (match_details=False)
        m1 = PytternMatcher()
        assert m1.match_details is False
        assert m1.stop_at_first is True

        # Explicit match_details=False
        m2 = PytternMatcher(match_details=False)
        assert m2.stop_at_first is True

        # User attempts stop_at_first=False with match_details=False -> enforced True
        m3 = PytternMatcher(match_details=False, stop_at_first=False)
        assert m3.stop_at_first is True

        # User provides stop_at_first=True with match_details=False -> enforced True
        m4 = PytternMatcher(match_details=False, stop_at_first=True)
        assert m4.stop_at_first is True

    def test_stop_at_first_honors_override_when_details_enabled(self):
        # Default stop_at_first with match_details=True -> defaults to False
        m1 = PytternMatcher(match_details=True)
        assert m1.match_details is True
        assert m1.stop_at_first is False

        # Explicit stop_at_first=None with match_details=True -> defaults to False
        m2 = PytternMatcher(match_details=True, stop_at_first=None)
        assert m2.stop_at_first is False

        # Explicit user override stop_at_first=True with match_details=True -> True
        m3 = PytternMatcher(match_details=True, stop_at_first=True)
        assert m3.stop_at_first is True

        # Explicit user override stop_at_first=False with match_details=True -> False
        m4 = PytternMatcher(match_details=True, stop_at_first=False)
        assert m4.stop_at_first is False

    def test_stop_at_first_behavioral_difference(self, tmp_path):
        pattern_file = tmp_path / "assign.pyt"
        pattern_file.write_text("x = ?v\n")

        code_file = tmp_path / "multi_assign.py"
        code_file.write_text("x = 1\nx = 2\nx = 3\n")

        # Details disabled: short-circuits fast path, returns boolean True
        m_bool = PytternMatcher(match_details=False)
        res_bool = m_bool.match(str(pattern_file), str(code_file), lang="python")
        assert res_bool is True

        # Details enabled, default stop_at_first (False): collects all 3 matches
        m_all = PytternMatcher(match_details=True)
        res_all, det_all = m_all.match(str(pattern_file), str(code_file), lang="python")
        assert res_all is True
        assert isinstance(det_all, MatchResult)
        assert det_all.count() == 3

        # Details enabled, user override stop_at_first=True: stops after 1 match
        m_first = PytternMatcher(match_details=True, stop_at_first=True)
        res_first, det_first = m_first.match(str(pattern_file), str(code_file), lang="python")
        assert res_first is True
        assert isinstance(det_first, MatchResult)
        assert det_first.count() == 1


class TestBatchWildcardMatchingOrchestration:
    """Validates single-pass compilation and O(M + N) complexity in match_wildcards."""

    def test_single_pass_compilation_across_multiple_code_files(self, tmp_path):
        # Create 2 pattern files (M = 2)
        p1 = tmp_path / "p1.pyt"
        p1.write_text("x = ?a\n")
        p2 = tmp_path / "p2.pyt"
        p2.write_text("y = ?b\n")

        # Create 3 code files (N = 3)
        c1 = tmp_path / "c1.py"
        c1.write_text("x = 10\n")
        c2 = tmp_path / "c2.py"
        c2.write_text("y = 20\n")
        c3 = tmp_path / "c3.py"
        c3.write_text("z = 30\n")

        matcher = PytternMatcher(match_details=False)

        real_compile = PatternCompiler.compile
        with patch.object(PatternCompiler, "compile", autospec=True, side_effect=real_compile) as spy_compile:
            results = matcher.match_wildcards(
                str(tmp_path / "p*.pyt"),
                str(tmp_path / "c*.py"),
            )

            # PatternCompiler.compile must be called exactly M = 2 times, NOT M * N = 6 times!
            assert spy_compile.call_count == 2
            called_paths = {str(call.args[1]) for call in spy_compile.call_args_list}
            assert str(p1) in called_paths
            assert str(p2) in called_paths

        # Verify results aggregation: ret[code_file][pattern_file]
        assert results[str(c1)][str(p1)] is True
        assert results[str(c1)][str(p2)] is False
        assert results[str(c2)][str(p1)] is False
        assert results[str(c2)][str(p2)] is True
        assert results[str(c3)][str(p1)] is False
        assert results[str(c3)][str(p2)] is False

    def test_single_pass_code_parsing(self, tmp_path):
        # Create 3 patterns (M = 3) and 2 code files (N = 2)
        p1 = tmp_path / "p1.pyt"
        p1.write_text("x = 1\n")
        p2 = tmp_path / "p2.pyt"
        p2.write_text("x = 2\n")
        p3 = tmp_path / "p3.pyt"
        p3.write_text("x = 3\n")

        c1 = tmp_path / "c1.py"
        c1.write_text("x = 1\n")
        c2 = tmp_path / "c2.py"
        c2.write_text("x = 2\n")

        matcher = PytternMatcher(match_details=False)
        py_processor = matcher._get_processor_for_file(str(c1))

        with patch.object(py_processor, "generate_tree_from_file", wraps=py_processor.generate_tree_from_file) as spy_parse:
            results = matcher.match_wildcards(
                str(tmp_path / "p*.pyt"),
                str(tmp_path / "c*.py"),
            )
            # Total parsing calls across both patterns and code is M + N = 3 + 2 = 5, NOT M * N = 6!
            assert spy_parse.call_count == 5
            # Specifically, each code file is parsed exactly once (N = 2)
            code_calls = [str(call.args[0]) for call in spy_parse.call_args_list if str(call.args[0]) in (str(c1), str(c2))]
            assert len(code_calls) == 2
            assert str(c1) in code_calls
            assert str(c2) in code_calls

    def test_batch_wildcards_with_match_details_true(self, tmp_path):
        p1 = tmp_path / "p1.pyt"
        p1.write_text("val = ?v\n")

        c1 = tmp_path / "c1.py"
        c1.write_text("val = 100\n")
        c2 = tmp_path / "c2.py"
        c2.write_text("other = 200\n")

        matcher = PytternMatcher(match_details=True)
        results = matcher.match_wildcards(
            str(tmp_path / "p*.pyt"),
            str(tmp_path / "c*.py"),
        )

        res_c1, det_c1 = results[str(c1)][str(p1)]
        assert res_c1 is True
        assert isinstance(det_c1, MatchResult)
        assert det_c1.count() == 1
        assert det_c1.binding["v"].getText() == "100"

        res_c2, det_c2 = results[str(c2)][str(p1)]
        assert res_c2 is False
        assert isinstance(det_c2, MatchResult)
        assert det_c2.count() == 0

    def test_batch_wildcards_empty_matches(self, tmp_path):
        matcher = PytternMatcher()
        results = matcher.match_wildcards(
            str(tmp_path / "nonexistent_*.pyt"),
            str(tmp_path / "*.py"),
        )
        assert results == {}


class TestTopLevelDelegationAndCLI:
    """Verifies matcher delegation to PatternCompiler and CLI behavior."""

    def test_matcher_delegates_to_pattern_compiler(self, tmp_path):
        pattern_file = tmp_path / "pat.pyt"
        pattern_file.write_text("x = ?v\n")
        code_file = tmp_path / "code.py"
        code_file.write_text("x = 42\n")

        matcher = PytternMatcher(match_details=False)
        real_compile = PatternCompiler.compile
        with patch.object(PatternCompiler, "compile", autospec=True, side_effect=real_compile) as spy_compile:
            res = matcher.match(str(pattern_file), str(code_file), lang="python")
            assert res is True
            assert spy_compile.call_count == 1

    def test_cli_wildcard_matching(self, tmp_path, capsys):
        p = tmp_path / "pat.pyt"
        p.write_text("a = ?x\n")
        c = tmp_path / "code.py"
        c.write_text("a = 99\n")

        test_args = [
            "pyttern",
            str(tmp_path / "*.pyt"),
            str(tmp_path / "*.py"),
        ]
        with patch.object(sys, "argv", test_args):
            main()

    def test_cli_details_and_stop_first_flags(self, tmp_path):
        p = tmp_path / "pat.pyt"
        p.write_text("a = ?x\n")
        c = tmp_path / "code.py"
        c.write_text("a = 1\na = 2\n")

        test_args = [
            "pyttern",
            "--lang", "python",
            "--details",
            "--stop-first",
            str(p),
            str(c),
        ]
        with patch.object(sys, "argv", test_args):
            main()
