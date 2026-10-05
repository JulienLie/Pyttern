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
    subpattern_file = Path(__file__).parent / "my_async.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)


def test_async_subpattern_match():
    pattern_path = Path(__file__).parent / "pattern_async.pyt"
    code_path = Path(__file__).parent / "async_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() > 0
    bindings = det.matches[0].bindings
    assert bindings["fn"].getText() == "handler"


def test_async_subpattern_mismatch():
    pattern_path = Path(__file__).parent / "pattern_async.pyt"
    code_path = Path(__file__).parent / "async_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res


def test_async_subpattern_metadata():
    subpattern_file = Path(__file__).parent / "my_async.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1
    assert ret[0].name == "MyAsyncFunc"
    assert ret[0].body_var == "body"
    assert ret[0].args_order == ["name"]
