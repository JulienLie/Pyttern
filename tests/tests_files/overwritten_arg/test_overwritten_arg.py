from pathlib import Path

from pyttern import match_files

BASE = Path(__file__).parent


def test_var_wildcard_in_arg():
    pattern_path = BASE / "overwritten_arg.pyt"
    code_path = BASE / "arg_ok.py"

    res = match_files(pattern_path, code_path)
    assert res, f"{pattern_path} on {code_path} should match"

    code_path = BASE / "arg_nok.py"

    res = match_files(pattern_path, code_path)
    assert not res, f"{pattern_path} on {code_path} should not match"
