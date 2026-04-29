from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file

def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

#@pytest.mark.timeout(1)
def test_singleton_subpattern():
    logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "singleton.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "Singleton", f"Expected subpattern name 'Singleton', got {subpattern.name}"
    assert len(subpattern.transformations) == 2, f"Expected 2 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    code_path = Path(__file__).parent / "singleton.py"
    pattern_path = Path(__file__).parent / "singleton.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "SingletonMeta", "SingletonMeta")
    compare_binding(bindings, "Singleton", "Singleton")