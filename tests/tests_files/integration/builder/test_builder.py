from pathlib import Path

from loguru import logger
import pytest

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file


def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

def test_builder_macro():
    logger.enable("pyttern")

    macro_file = Path(__file__).parent / "builder.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 2, f"Expected 2 macro, got {len(ret)}"
    macro = ret[0]
    assert macro.name == "Builder", f"Expected macro name 'Builder', got {macro.name}"
    assert len(macro.transformations) == 4, f"Expected 4 transformations, got {len(macro.transformations)}"
    assert macro.type == "AND", f"Expected macro type 'AND', got {macro.type}"

    macro = ret[1]
    assert macro.name == "BuildSteps", f"Expected macro name 'BuildSteps', got {macro.name}"
    assert len(macro.transformations) == 3, f"Expected 3 transformations, got {len(macro.transformations)}"
    assert macro.type == "AND", f"Expected macro type 'AND', got {macro.type}"

    code_path = Path(__file__).parent / "builder.py"
    pattern_path = Path(__file__).parent / "builder.pyt"

    pytest.skip("Test only for future work, this takes too much time to run due to combinatorial explosion")

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "Builder", "AbstractFactory")
    compare_binding(bindings, "buildStepA", "create_product_a")
    compare_binding(bindings, "buildStepB", "create_product_b")
    compare_binding(bindings, "getResult", "AbstractProductA")
    
    code_path = Path(__file__).parent / "getter_setter_ko.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det