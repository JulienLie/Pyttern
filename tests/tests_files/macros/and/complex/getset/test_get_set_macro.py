from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


#@pytest.mark.timeout(1)
def test_getter_setter_subpattern():
    logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "getset.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "GetSet", f"Expected subpattern name 'GetSet', got {subpattern.name}"
    assert len(subpattern.transformations) == 3, f"Expected 3 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    code_path = Path(__file__).parent / "getter_setter_ok.py"
    pattern_path = Path(__file__).parent / "getset.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings
    assert bindings['var'] is not None
    assert bindings['var'].getText() == "foo", f"Expected var to be 'foo' but was '{bindings['var'].getText()}'"
    assert bindings['getter'] is not None
    assert bindings['getter'].getText() == "get_foo", f"Expected getter to be 'get_foo' but was '{bindings['getter'].getText()}'"
    assert bindings['setter'] is not None
    assert bindings['setter'].getText() == "set_foo", f"Expected setter to be 'set_foo' but was '{bindings['setter'].getText()}'"

    code_path = Path(__file__).parent / "getter_setter_ko.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det

def test_getter_setter_subpattern_2_match():
    logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "getset.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "GetSet", f"Expected subpattern name 'GetSet', got {subpattern.name}"
    assert len(subpattern.transformations) == 3, f"Expected 3 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    code_path = Path(__file__).parent / "getter_setter_2_ok.py"
    pattern_path = Path(__file__).parent / "getset.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    #assert det.count() == 2, f"Expected 2 match but got {det.count()}"

    for matched in det.matches:

        bindings = matched.bindings
        assert bindings['var'] is not None
        assert bindings['getter'] is not None
        assert bindings['setter'] is not None

        var = bindings['var'].getText()
        assert var in ["foo", "bar"], f"Expected var to be one of [foo, bar] but was {var}"

        assert bindings['getter'].getText() == f"get_{var}", f"Expected getter to be 'get_{var}' but was '{bindings['getter'].getText()}'"
        assert bindings['setter'].getText() == f"set_{var}", f"Expected setter to be 'set_{var}' but was '{bindings['setter'].getText()}'"