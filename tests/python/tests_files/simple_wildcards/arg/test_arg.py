from pathlib import Path

import pytest

from pyttern import match_files


@pytest.mark.timeout(2)
def test_arg():
    pattern_path = Path(__file__).parent / "simple_wildcard.pyt"
    code_path = Path(__file__).parent / "simple_wildcard_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "simple_wildcard_ko_def_arg.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det

    code_path = Path(__file__).parent / "simple_wildcard_ko_1arg.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det

    code_path = Path(__file__).parent / "simple_wildcard_ko_3arg.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det