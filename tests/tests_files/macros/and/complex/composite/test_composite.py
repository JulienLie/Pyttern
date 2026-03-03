from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file


#@pytest.mark.timeout(1)
def test_composite_sub_pattern():
    logger.enable("pyttern")

    macro_file = Path(__file__).parent / "composite.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 2, f"Expected 2 macros, got {len(ret)}"
    macro_global = ret[0]
    assert macro_global.name == "Composite", f"Expected macro name 'Composite', got {macro_global.name}"
    assert len(macro_global.transformations) == 3, f"Expected 3 transformations, got {len(macro_global.transformations)}"
    assert macro_global.type == "AND", f"Expected macro type 'AND', got {macro_global.type}"


    sub_macro = ret[1]
    assert sub_macro.name == "CompositeClass", f"Expected macro name 'CompositeClass', got {sub_macro.name}"
    assert len(sub_macro.transformations) == 4, f"Expected 4 transformations, got {len(sub_macro.transformations)}"
    assert sub_macro.type == "AND", f"Expected macro type 'AND', got {sub_macro.type}"

    code_path = Path(__file__).parent / "composite_ok.py"
    pattern_path = Path(__file__).parent / "composite.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings
    assert bindings['Component'] is not None
    assert bindings['Component'].getText() == "Composant", f"Expected var to be 'Composant' but was '{bindings['Component'].getText()}'"
    assert bindings['execute'] is not None
    assert bindings['execute'].getText() == "get_foo", f"Expected execute to be 'get_foo' but was '{bindings['execute'].getText()}'"
