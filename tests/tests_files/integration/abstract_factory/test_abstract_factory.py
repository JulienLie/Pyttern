from pathlib import Path

from loguru import logger
import pytest

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file


def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

#@pytest.mark.timeout(1)
def test_abstract_factory_macro():
    logger.enable("pyttern")

    macro_file = Path(__file__).parent / "abstract_factory.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    macro = ret[0]
    assert macro.name == "AbstractFactory", f"Expected macro name 'AbstractFactory', got {macro.name}"
    assert len(macro.transformations) == 9, f"Expected 9 transformations, got {len(macro.transformations)}"
    assert macro.type == "AND", f"Expected macro type 'AND', got {macro.type}"

    code_path = Path(__file__).parent / "abstract_factory.py"
    pattern_path = Path(__file__).parent / "abstract_factory.pyt"

    pytest.skip("Test only for future work, this takes too much time to run due to combinatorial explosion")

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "AbstractFactory", "AbstractFactory")
    compare_binding(bindings, "CreateProductA", "create_product_a")
    compare_binding(bindings, "CreateProductB", "create_product_b")
    compare_binding(bindings, "ProductA", "AbstractProductA")
    compare_binding(bindings, "ConcreteProductA1", "ConcreteProductA1")
    compare_binding(bindings, "ConcreteProductA2", "ConcreteProductA2")
    compare_binding(bindings, "ProductB", "AbstractProductB")
    compare_binding(bindings, "ConcreteProductB1", "ConcreteProductB1")
    compare_binding(bindings, "CreateProductB2", "ConcreteProductB2")
    
    code_path = Path(__file__).parent / "getter_setter_ko.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det