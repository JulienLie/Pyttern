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
    subpattern_file = Path(__file__).parent / "my_try.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)


def test_try_subpattern_match():
    pattern_path = Path(__file__).parent / "pattern_try.pyt"
    code_path = Path(__file__).parent / "try_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() > 0
    bindings = det.matches[0].bindings
    assert bindings["e"].getText() == "KeyError"


def test_try_subpattern_else_match():
    pattern_path = Path(__file__).parent / "pattern_try.pyt"
    code_path = Path(__file__).parent / "try_else_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() > 0
    bindings = det.matches[0].bindings
    assert bindings["e"].getText() == "KeyError"


def test_try_subpattern_mismatch():
    pattern_path = Path(__file__).parent / "pattern_try.pyt"
    code_path = Path(__file__).parent / "try_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res


def test_try_subpattern_metadata():
    subpattern_file = Path(__file__).parent / "my_try.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1
    assert ret[0].name == "MyTryExcept"
    assert ret[0].body_var == "body"
    assert ret[0].args_order == ["err"]
    assert len(ret[0].transformations) == 2
