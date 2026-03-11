from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file

def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

#@pytest.mark.timeout(1)
def test_factory_method_macro():
    logger.enable("pyttern")

    macro_file = Path(__file__).parent / "factory_method.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    macro = ret[0]
    assert macro.name == "FactoryMethod", f"Expected macro name 'FactoryMethod', got {macro.name}"
    assert len(macro.transformations) == 6, f"Expected 6 transformations, got {len(macro.transformations)}"
    assert macro.type == "AND", f"Expected macro type 'AND', got {macro.type}"

    code_path = Path(__file__).parent / "factory_method.py"
    pattern_path = Path(__file__).parent / "factory_method.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "Creator", "Dialog")
    compare_binding(bindings, "createProduct", "createButton")
    compare_binding(bindings, "Product", "Button")
    compare_binding(bindings, "ProductA", "WindowsButton")
    compare_binding(bindings, "ProductB", "WebButton")
    compare_binding(bindings, "productMethod", "render")
    
    code_path = Path(__file__).parent / "getter_setter_ko.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det