from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file

def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

def test_compile_subpattern():
    subpattern_file = Path(__file__).parent / "factory_method.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 2, f"Expected 2 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "FactoryMethod", f"Expected subpattern name 'FactoryMethod', got {subpattern.name}"
    assert len(subpattern.transformations) == 6, f"Expected 6 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"


#@pytest.mark.timeout(1)
def test_factory_method_subpattern():
    #logger.enable("pyttern")
    subpattern_file = Path(__file__).parent / "factory_method.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON, True)
    
    code_path = Path(__file__).parent / "factory_method.py"
    pattern_path = Path(__file__).parent / "factory_method.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() > 0, f"Expected at least one match but got None"
    #assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "Creator", "Dialog")
    compare_binding(bindings, "createProduct", "createButton")
    compare_binding(bindings, "Product", "Button")
    compare_binding(bindings, "ProductA", "WindowsButton")
    compare_binding(bindings, "ProductB", "WindowsButton")
    compare_binding(bindings, "productMethod", "render")


def test_creator():
    subpattern_file = Path(__file__).parent / "factory_method.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)

    code_path = Path(__file__).parent / "factory_method.py"
    pattern_path = Path(__file__).parent / "creator_body.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() > 0, f"Expected at least one match but got None"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "createProduct", "createButton")

    assert det.count() == 1, f"Expected 1 match but got {det.count()}"

def test_concrete_creator():
    code_path = Path(__file__).parent / "factory_method.py"
    pattern_path = Path(__file__).parent / "concrete_creator.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() > 0, f"Expected at least one match but got None"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "Creator", "Dialog")
    compare_binding(bindings, "ConcreteCreatorA", "WindowsDialog")
    compare_binding(bindings, "ConcreteCreatorB", "WebDialog")
    compare_binding(bindings, "createProduct", "createButton")
    compare_binding(bindings, "ProductA", "WindowsButton")
    compare_binding(bindings, "ProductB", "WebButton")

    assert det.count() == 1, f"Expected 1 match but got {det.count()}"