from pathlib import Path

from pyttern import match_files

BASE = Path(__file__).parent / "pi"


def test_ast_simple_addition():
    pattern_path = BASE / "piPattern.pyt"
    code_path = BASE / "piCode_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = BASE / "piCode_ko.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det
