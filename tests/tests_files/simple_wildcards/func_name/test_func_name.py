from pathlib import Path

import pytest

from pyttern import PytternMatcher


@pytest.mark.timeout(1)
def test_func_name():
    pattern_path = Path(__file__).parent / "simple_wildcard.pyt"
    code_path = Path(__file__).parent / "simple_wildcard_ok.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert res, det