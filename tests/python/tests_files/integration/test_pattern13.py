from pathlib import Path

import pytest

from pyttern import match_files

BASE = Path(__file__).parent / "pattern13"


@pytest.mark.timeout(10)
def test_soft_pattern_match():
    pattern_path = BASE / "pattern13.pyt"
    code_path = BASE / "pattern13_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
