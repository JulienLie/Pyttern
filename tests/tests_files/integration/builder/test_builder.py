from pathlib import Path

from loguru import logger
import pytest

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

def test_builder_subpattern():
    logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "builder.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 2, f"Expected 2 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "Builder", f"Expected subpattern name 'Builder', got {subpattern.name}"
    assert len(subpattern.transformations) == 4, f"Expected 4 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    subpattern = ret[1]
    assert subpattern.name == "BuildSteps", f"Expected subpattern name 'BuildSteps', got {subpattern.name}"
    assert len(subpattern.transformations) == 3, f"Expected 3 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

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