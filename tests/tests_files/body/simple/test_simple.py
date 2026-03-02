from pathlib import Path

import pytest

from pyttern import PytternMatcher


@pytest.mark.timeout(1)
@pytest.mark.parametrize("pattern_path", sorted(Path(__file__).parent.glob("*.pyt")))
def test_simple(pattern_path):
    pattern_path = pattern_path
    code_path = Path(__file__).parent / "body_simple_ok.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert res, det

    code_path = Path(__file__).parent / "body_simple_ko.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert not res, det