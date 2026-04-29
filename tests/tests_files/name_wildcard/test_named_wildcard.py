from pathlib import Path

import pytest

from pyttern import match_files

BASE = Path(__file__).parent


class TestNamedWildcard:
    @pytest.mark.timeout(1)
    def test_named_var(self):
        pattern_path = BASE / "named_var" / "named_var.pyt"
        code_path = BASE / "named_var" / "named_var_ok.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

    @pytest.mark.timeout(1)
    def test_named_stmt(self):
        pattern_path = BASE / "named_stmt" / "named_stmt.pyt"
        code_path = BASE / "named_stmt" / "named_stmt_ok.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

    def test_named_args(self):
        pattern_path = BASE / "named_args" / "named_args.pyt"
        code_path = BASE / "named_args" / "named_args_ok.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = BASE / "named_args" / "named_args_ko.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    def test_named_func(self):
        pattern_path = BASE / "named_func" / "named_func.pyt"
        code_path = BASE / "named_func" / "named_func_ok.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = BASE / "named_func" / "named_func_ko.py"
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det
