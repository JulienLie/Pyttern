from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file


#@pytest.mark.timeout(1)
def test_getter_setter_macro():
    logger.enable("pyttern")

    macro_file = Path(__file__).parent / "getset.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    macro = ret[0]
    assert macro.name == "GetSet", f"Expected macro name 'GetSet', got {macro.name}"
    assert len(macro.transformations) == 3, f"Expected 3 transformations, got {len(macro.transformations)}"
    assert macro.type == "AND", f"Expected macro type 'AND', got {macro.type}"

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

def test_getter_setter_macro_2_match():
    logger.enable("pyttern")

    macro_file = Path(__file__).parent / "getset.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    macro = ret[0]
    assert macro.name == "GetSet", f"Expected macro name 'GetSet', got {macro.name}"
    assert len(macro.transformations) == 3, f"Expected 3 transformations, got {len(macro.transformations)}"
    assert macro.type == "AND", f"Expected macro type 'AND', got {macro.type}"

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