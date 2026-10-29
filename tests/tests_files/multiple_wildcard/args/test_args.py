from pathlib import Path

import pytest

from pyttern import PytternMatcher


@pytest.mark.timeout(1)
def test_multiple_args():
    pattern_path = Path(__file__).parent / "multiple_args.pyt"
    code_path = Path(__file__).parent / "multiple_args_one_ok.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert res, det

    code_path = Path(__file__).parent / "multiple_args_two_ok.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert res, det

    code_path = Path(__file__).parent / "multiple_args_ko.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert not res, det
    
    
@pytest.mark.timeout(1)
def test_multiple_args_complex():
    pattern_path = Path(__file__).parent / "multiple_args_complex.pyt"
    code_path = Path(__file__).parent / "multiple_args_one_ok.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert res, det

    code_path = Path(__file__).parent / "multiple_args_two_ok.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert res, det

    code_path = Path(__file__).parent / "multiple_args_ko.py"

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert not res, det

@pytest.mark.timeout(1)
@pytest.mark.parametrize(
    "code_path",
    [p for p in sorted(Path(__file__).parent.glob("*.py")) if p.name != Path(__file__).name],
)
def test_simple_args(code_path):
    pattern_path = Path(__file__).parent / "simple_args.pyt"
    code_path = Path(__file__).parent / code_path

    matcher = PytternMatcher(match_details=True)
    res, det = matcher.match(pattern_path, code_path, lang="python")
    assert res, f"{code_path}: {det}"