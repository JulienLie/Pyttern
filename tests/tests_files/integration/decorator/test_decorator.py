from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file

def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

#@pytest.mark.timeout(1)
def test_decorator_macro():
    logger.enable("pyttern")

    macro_file = Path(__file__).parent / "decorator.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    macro = ret[0]
    assert macro.name == "Decorator", f"Expected macro name 'Decorator', got {macro.name}"
    assert len(macro.transformations) == 4, f"Expected 3 transformations, got {len(macro.transformations)}"
    assert macro.type == "AND", f"Expected macro type 'AND', got {macro.type}"

    code_path = Path(__file__).parent / "decorator.py"
    pattern_path = Path(__file__).parent / "decorator.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "Component", "Component")
    compare_binding(bindings, "execute", "operation")
    compare_binding(bindings, "BaseDecorator", "Decorator")
    compare_binding(bindings, "ConcreteDecorator", "ConcreteDecoratorA")