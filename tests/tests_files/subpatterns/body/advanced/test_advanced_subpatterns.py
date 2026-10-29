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
    cat = SubPatternCatalog()
    cat.load_string(sub_code_outer, Languages.PYTHON)
    cat.load_string(sub_code_inner, Languages.PYTHON)

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
    from pyttern.pattern.compiler import PatternCompiler
    compiler = PatternCompiler(Languages.PYTHON, catalog=cat)
    pat = compiler.compile(pattern_code)

    res_ok = pat.matches(code_match)
    assert res_ok
    assert res_ok.matched
    assert res_ok.count() > 0
    bindings = res_ok.binding
    assert bindings["lock"].getText() == "my_mutex"
    assert bindings["item"].getText() == "x"
    assert bindings["pool"].getText() == "queue"

    res_nok = pat.matches(code_mismatch)
    assert not res_nok
    assert not res_nok.matched
