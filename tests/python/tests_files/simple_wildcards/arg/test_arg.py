from pathlib import Path

import pytest

from pyttern import PytternMatcher


@pytest.mark.timeout(2)
def test_arg():
    pattern_path = Path(__file__).parent / "simple_wildcard.pyt"
    code_path = Path(__file__).parent / "simple_wildcard_ok.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert res, det

    code_path = Path(__file__).parent / "simple_wildcard_ko_def_arg.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert not res, det

    code_path = Path(__file__).parent / "simple_wildcard_ko_1arg.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert not res, det

    code_path = Path(__file__).parent / "simple_wildcard_ko_3arg.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert not res, det