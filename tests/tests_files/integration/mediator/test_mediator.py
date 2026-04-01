from pathlib import Path
from loguru import logger
import pytest
from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file

def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

def test_mediator_pattern():
    logger.enable("pyttern")

    code_path = Path(__file__).parent / "mediator_ok.py"
    pattern_path = Path(__file__).parent / "mediator.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() >= 1, f"Expected at least 1 match but got {det.count()}"
    
    # We can check bindings if needed
    matched = det.matches[0]
    bindings = matched.bindings
    compare_binding(bindings, "Mediator", "Mediator")
    compare_binding(bindings, "notify", "notify")
    compare_binding(bindings, "BaseComponent", "BaseComponent")
    compare_binding(bindings, "ConcreteMediator", "ConcreteMediator")
