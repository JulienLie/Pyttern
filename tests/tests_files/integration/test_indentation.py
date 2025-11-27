from pathlib import Path

from pyttern import match_files

BASE = Path(__file__).parent / "indentation"


def test_too_much_indentation():
    pattern_path = BASE / "indentation.pyt"
    code_path = BASE / "indentation_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
