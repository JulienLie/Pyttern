from pathlib import Path
import pytest

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import (
    parse_subpattern_from_file,
    parse_subpattern_from_string,
)


@pytest.fixture(autouse=True)
def setup_subpattern():
    subpattern_file = Path(__file__).parent / "my_if.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)


def test_if_subpattern_is_branch_match():
    pattern_path = Path(__file__).parent / "pattern_if.pyt"
    code_path = Path(__file__).parent / "is_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() > 0
    bindings = det.matches[0].bindings
    assert bindings["x"].getText() == "val"
    assert bindings["y"].getText() == "None"


def test_if_subpattern_eq_branch_match():
    pattern_path = Path(__file__).parent / "pattern_if.pyt"
    code_path = Path(__file__).parent / "eq_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() > 0
    bindings = det.matches[0].bindings
    assert bindings["x"].getText() == "val"
    assert bindings["y"].getText() == "0"


def test_if_subpattern_mismatch():
    pattern_path = Path(__file__).parent / "pattern_if.pyt"
    code_path = Path(__file__).parent / "compare_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res


def test_if_subpattern_metadata():
    subpattern_file = Path(__file__).parent / "my_if.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1
    assert ret[0].name == "MyIf"
    assert ret[0].body_var == "body"
    assert ret[0].args_order == ["a", "b"]
