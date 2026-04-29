from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file

def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

#@pytest.mark.timeout(1)
def test_composite_subpattern():
    #logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "composite.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 2, f"Expected 1 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "Composite", f"Expected subpattern name 'Composite', got {subpattern.name}"
    assert len(subpattern.transformations) == 3, f"Expected 3 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    subpattern = ret[1]
    assert subpattern.name == "CompositeBody", f"Expected subpattern name 'Composite', got {subpattern.name}"
    assert len(subpattern.transformations) == 4, f"Expected 4 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    code_path = Path(__file__).parent / "composite.py"
    pattern_path = Path(__file__).parent / "composite.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    #assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "Component", "Component")
    compare_binding(bindings, "execute", "operation")

def test_composite_body():
    subpattern_file = Path(__file__).parent / "composite.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON, True)

    code_path = Path(__file__).parent / "composite.py"
    pattern_path = Path(__file__).parent / "composite_body.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    #assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "Component", "Component")
    compare_binding(bindings, "Composite", "Composite")
    compare_binding(bindings, "children", "_children")
    compare_binding(bindings, "execute", "operation")