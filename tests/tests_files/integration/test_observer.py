from pathlib import Path

from pyttern import match_files

BASE = Path(__file__).parent / "observer"


def test_observer_pattern():
    pattern_path = BASE / "observer.pyt"
    code_path = BASE / "observer_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
