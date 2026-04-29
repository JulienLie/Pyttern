from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file

def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

#@pytest.mark.timeout(1)
def test_decorator_subpattern():
    logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "decorator.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "Decorator", f"Expected subpattern name 'Decorator', got {subpattern.name}"
    assert len(subpattern.transformations) == 4, f"Expected 3 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    code_path = Path(__file__).parent / "decorator.py"
    pattern_path = Path(__file__).parent / "decorator.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    #assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "Component", "Component")
    compare_binding(bindings, "execute", "operation")
    compare_binding(bindings, "BaseDecorator", "Decorator")
    compare_binding(bindings, "ConcreteDecorator", "ConcreteDecoratorA")

def test_base_decorator():
    subpattern_file = Path(__file__).parent / "decorator.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)

    code_path = Path(__file__).parent / "decorator.py"
    pattern_path = Path(__file__).parent / "base_decorator.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    #assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "Component", "Component")
    compare_binding(bindings, "execute", "operation")
    compare_binding(bindings, "BaseDecorator", "Decorator")