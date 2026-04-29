from pathlib import Path

from loguru import logger

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file

def compare_binding(bindings, name, value):
    assert bindings[name] is not None
    assert bindings[name].getText() == value, f"Expected {name} to be '{value}' but was '{bindings[name].getText()}'"

def test_subpattern_compilation():
    subpattern_file = Path(__file__).parent / "proxy.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 3, f"Expected 2 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "Proxy", f"Expected subpattern name 'Proxy', got {subpattern.name}"
    assert len(subpattern.transformations) == 3, f"Expected 3 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    subpattern = ret[1]
    assert subpattern.name == "ProxyBody", f"Expected subpattern name 'ProxyBody', got {subpattern.name}"
    assert len(subpattern.transformations) == 2, f"Expected 2 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    subpattern = ret[2]
    assert subpattern.name == "Operation", f"Expected subpattern name 'Operation', got {subpattern.name}"
    assert len(subpattern.transformations) == 2, f"Expected 2 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "OR", f"Expected subpattern type 'OR', got {subpattern.type}"

#@pytest.mark.timeout(1)
def test_proxy_subpattern():
    logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "proxy.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON, True)

    code_path = Path(__file__).parent / "proxy.py"
    pattern_path = Path(__file__).parent / "proxy.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
    #assert det.count() == 1, f"Expected 1 match but got {det.count()}"
    matched = det.matches[0]
    bindings = matched.bindings

    compare_binding(bindings, "ServiceInterface", "Subject")
    compare_binding(bindings, "operation", "request")