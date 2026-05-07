from pathlib import Path

import pytest

from pyttern import PytternMatcher


@pytest.mark.timeout(1)
def test_nested():
    pattern_path = Path(__file__).parent / "body_nested_1.pyt"
    code_path = Path(__file__).parent / "body_nested_ok.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert res, det

    code_path = Path(__file__).parent / "body_nested_ko.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert not res, det
