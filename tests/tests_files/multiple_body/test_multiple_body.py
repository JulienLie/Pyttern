from pathlib import Path

import pytest

from pyttern import match_files

BASE = Path(__file__).parent


class TestMultipleBodyWildcard:
    @pytest.mark.timeout(2)
    @pytest.mark.parametrize("pattern_path", sorted((BASE / "simple").glob("*.pyt")))
    def test_simple(self, pattern_path):
        code_path = BASE / "simple" / "multiple_body_simple_ok.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = BASE / "simple" / "multiple_body_simple_ko.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    # @pytest.mark.timeout(1)  # TODO: This test is too long, Pyttern should be optimized
    @pytest.mark.parametrize("pattern_path", sorted((BASE / "multiple").glob("*.pyt")))
    def test_multiple(self, pattern_path):
        code_path = BASE / "multiple" / "multiple_body_multiple_ok.py"
        res, det = match_files(pattern_path, code_path, match_details=True, stop_at_first=True)
        assert res, det

        code_path = BASE / "multiple" / "multiple_body_multiple_ko.py"
        res, det = match_files(pattern_path, code_path, match_details=True, stop_at_first=True)
        assert not res, det
