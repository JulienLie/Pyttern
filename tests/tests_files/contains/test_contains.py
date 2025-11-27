from pathlib import Path

import pytest

from pyttern import match_files

BASE = Path(__file__).parent


class TestContainsWildcard:
    @pytest.mark.timeout(1)
    @pytest.mark.parametrize("pattern_path", sorted((BASE / "simple_var").glob("*.pyt")))
    def test_simple_contains(self, pattern_path):
        code_path = BASE / "simple_var" / "contains_ok.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = BASE / "simple_var" / "contains_ko.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    @pytest.mark.timeout(1)
    @pytest.mark.parametrize("pattern_path", sorted((BASE / "complex_expr").glob("*.pyt")))
    def test_complex_contains(self, pattern_path):
        code_path = BASE / "complex_expr" / "contains_ok.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = BASE / "complex_expr" / "contains_ko.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det
