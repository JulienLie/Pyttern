from pathlib import Path

from pyttern import match_files

BASE = Path(__file__).parent / "recursion"


def test_match_recursion():
    pattern_path = BASE / "recursion.pyt"
    code_path = BASE / "recursion_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
