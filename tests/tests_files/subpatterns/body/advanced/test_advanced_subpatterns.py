from pathlib import Path
import pytest

from pyttern import match_files, SubPatternCatalog
from pyttern.language_processors.languages import Languages


def test_and_method_subpattern_match():
    catalog = SubPatternCatalog()
    subpattern_file = Path(__file__).parent / "and_logged.myt"
    catalog.load_file(subpattern_file)

    pattern_path = Path(__file__).parent / "and_pattern.pyt"
    code_path = Path(__file__).parent / "and_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det
    assert det.count() > 0
    bindings = det.matches[0].bindings
    assert bindings["C"].getText() == "Worker"
    assert bindings["m"].getText() == "execute"


def test_and_method_subpattern_mismatch():
    catalog = SubPatternCatalog()
    subpattern_file = Path(__file__).parent / "and_logged.myt"
    catalog.load_file(subpattern_file)

    pattern_path = Path(__file__).parent / "and_pattern.pyt"
    code_path = Path(__file__).parent / "and_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res


def test_not_if_subpattern_match_absence():
    catalog = SubPatternCatalog()
    subpattern_file = Path(__file__).parent / "not_if.myt"
    catalog.load_file(subpattern_file)

    pattern_path = Path(__file__).parent / "not_pattern.pyt"
    code_path = Path(__file__).parent / "not_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det
    assert det.count() > 0


def test_not_if_subpattern_mismatch_presence():
    catalog = SubPatternCatalog()
    subpattern_file = Path(__file__).parent / "not_if.myt"
    catalog.load_file(subpattern_file)

    pattern_path = Path(__file__).parent / "not_pattern.pyt"
    code_path = Path(__file__).parent / "not_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res


def test_nested_subpatterns():
    sub_code_outer = """
$|WithWrapper(?ctx): ?body

$# WithBlock
with ?ctx:
    ?body
"""
    sub_code_inner = """
$|InnerFor(?i, ?items): ?body

$# ForLoop
for ?i in ?items:
    ?body
"""
    catalog = SubPatternCatalog()
    catalog.load_string(sub_code_outer, Languages.PYTHON)
    catalog.load_string(sub_code_inner, Languages.PYTHON)

    from pyttern.language_processors.python_processor import PythonProcessor
    from pyttern.simulator.Matcher import Matcher

    pattern_code = """
def ?():
    ?$WithWrapper(?lock):
        ?$InnerFor(?item, ?pool):
            consume(?item)
"""
    code_match = """
def run():
    with my_mutex:
        for x in queue:
            consume(x)
"""
    code_mismatch = """
def run():
    with my_mutex:
        for x in queue:
            wrong_call(x)
"""
    proc = PythonProcessor()
    pda = proc.create_pda(proc.generate_tree_from_code(pattern_code), catalog=catalog)

    res_ok = Matcher.match(pda, proc.generate_tree_from_code(code_match), catalog=catalog)
    assert res_ok.count() > 0
    bindings = res_ok.matches[0].bindings
    assert bindings["lock"].getText() == "my_mutex"
    assert bindings["item"].getText() == "x"
    assert bindings["pool"].getText() == "queue"

    res_nok = Matcher.match(pda, proc.generate_tree_from_code(code_mismatch), catalog=catalog)
    assert res_nok.count() == 0
