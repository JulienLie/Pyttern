from pathlib import Path

import pytest

from pyttern import match_files


@pytest.mark.timeout(1)
def test_stmt():
    pattern_path = Path(__file__).parent / "shadowing.pyt"
    code_path = Path(__file__).parent / "shadowing.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det