from pathlib import Path

import pytest

from pyttern import match_files


@pytest.mark.timeout(1)
@pytest.mark.parametrize("pattern_path", sorted(Path(__file__).parent.glob("*.pyt")))
def test_stmt(pattern_path):
    pattern_path = pattern_path
    code_path = Path(__file__).parent / "multiple_stmt_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det