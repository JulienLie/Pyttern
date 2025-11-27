from pathlib import Path

from pyttern import match_files

BASE = Path(__file__).parent


def test_missplaced_return_indent_ok():
    pattern_path = BASE / "indentreturn.pyt"
    code_path = BASE / "code55.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
