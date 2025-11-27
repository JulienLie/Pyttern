from pathlib import Path

from pyttern import match_files

BASE = Path(__file__).parent / "hardcoded_list"


def test_hardcoded_list():
    pattern_path = BASE / "hardcodedlist.pyt"
    code_path = BASE / "hardcodedlist_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
