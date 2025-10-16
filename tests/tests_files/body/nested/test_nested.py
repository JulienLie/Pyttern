from pathlib import Path

import pytest

from pyttern import match_files


@pytest.mark.timeout(1)
def test_nested():
    pattern_path = Path(__file__).parent / "body_nested_1.pyt"
    code_path = Path(__file__).parent / "body_nested_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "body_nested_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det
