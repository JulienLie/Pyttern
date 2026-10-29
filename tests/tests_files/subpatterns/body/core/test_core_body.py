from pathlib import Path
import pytest

from pyttern import match_files, SubPatternCatalog
from pyttern.language_processors.languages import Languages
from pyttern.language_processors.python_processor import PythonProcessor
from pyttern.simulator.Matcher import Matcher


@pytest.fixture
def catalog():
    cat = SubPatternCatalog()
    subpattern_file = Path(__file__).parent / "loop.myt"
    cat.load_file(subpattern_file)
    return cat


def test_inline_subpattern_call_for_match(catalog):
    pattern_path = Path(__file__).parent / "loop_call_inline.pyt"
    code_path = Path(__file__).parent / "for_code_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det
    assert det.count() > 0


def test_inline_subpattern_call_mismatch(catalog):
    pattern_path = Path(__file__).parent / "loop_call_inline.pyt"
    code_path = Path(__file__).parent / "for_code_nok.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res


def test_multiline_subpattern_call_for_match(catalog):
    pattern_path = Path(__file__).parent / "loop_call_multiline.pyt"
    code_path = Path(__file__).parent / "for_code_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det
    assert det.count() > 0


def test_multiline_subpattern_call_while_match(catalog):
    pattern_path = Path(__file__).parent / "loop_call_multiline.pyt"
    code_path = Path(__file__).parent / "while_code_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det
    assert det.count() > 0


def test_multiline_subpattern_call_mismatch(catalog):
    pattern_path = Path(__file__).parent / "loop_call_multiline.pyt"
    code_path = Path(__file__).parent / "for_code_nok.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res


def test_argument_renaming_in_body():
    sub_code = """
$|LoopRename(?i, ?v): ?body

$# For
for ?i in ?v:
    ?body
"""
    catalog = SubPatternCatalog()
    catalog.load_string(sub_code, Languages.PYTHON)

    pattern_code = """
def ?():
    ?$LoopRename(?elem, ?container):
        ?.append(?elem)
"""
    code_target = """
def run():
    for x in my_list:
        my_res.append(x)
"""
    proc = PythonProcessor()
    pda = proc.create_pda(proc.generate_tree_from_code(pattern_code), catalog=catalog)
    res = Matcher.match(pda, proc.generate_tree_from_code(code_target), catalog=catalog)
    assert res.count() > 0
    bindings = res.matches[0].bindings
    assert bindings["elem"].getText() == "x"
    assert bindings["container"].getText() == "my_list"


def test_extra_named_wildcard_in_body():
    sub_code = """
$|LoopCapture(?i, ?v): ?body

$# For
for ?i in ?v:
    ?body
"""
    catalog = SubPatternCatalog()
    catalog.load_string(sub_code, Languages.PYTHON)

    pattern_code = """
def ?():
    ?$LoopCapture(?i, ?v):
        ?target.append(?v)
"""
    code_target = """
def run():
    for x in items:
        my_collector.append(items)
"""
    proc = PythonProcessor()
    pda = proc.create_pda(proc.generate_tree_from_code(pattern_code), catalog=catalog)
    res = Matcher.match(pda, proc.generate_tree_from_code(code_target), catalog=catalog)
    assert res.count() > 0
    bindings = res.matches[0].bindings
    assert bindings["target"].getText() == "my_collector"
    assert bindings["v"].getText() == "items"
    assert bindings["i"].getText() == "x"


def test_custom_body_var_name():
    sub_code = """
$|CustomVar(?val): ?my_custom_body

$# WrapIf
if ?val:
    ?my_custom_body
"""
    catalog = SubPatternCatalog()
    ret = catalog.load_string(sub_code, Languages.PYTHON)
    assert len(ret) == 1
    assert ret[0].body_var == "my_custom_body"

    pattern_code = """
def ?():
    ?$CustomVar(?flag):
        do_work()
"""
    code_ok = """
def run():
    if is_ready:
        do_work()
"""
    code_nok = """
def run():
    if is_ready:
        do_other()
"""
    proc = PythonProcessor()
    pda = proc.create_pda(proc.generate_tree_from_code(pattern_code), catalog=catalog)

    res_ok = Matcher.match(pda, proc.generate_tree_from_code(code_ok), catalog=catalog)
    assert res_ok.count() > 0
    assert res_ok.matches[0].bindings["flag"].getText() == "is_ready"

    res_nok = Matcher.match(pda, proc.generate_tree_from_code(code_nok), catalog=catalog)
    assert res_nok.count() == 0


def test_error_when_body_required_but_missing():
    sub_code = """
$|NeedsBody(?x): ?body

$# For
for ?i in ?x:
    ?body
"""
    catalog = SubPatternCatalog()
    catalog.load_string(sub_code, Languages.PYTHON)

    pattern_code = """
def ?():
    ?$NeedsBody(?v)
"""
    proc = PythonProcessor()
    with pytest.raises(ValueError, match="requires a body wildcard"):
        proc.create_pda(proc.generate_tree_from_code(pattern_code), catalog=catalog)


def test_error_when_body_provided_but_not_accepted():
    sub_code = """
$|NoBodyAccepted(?x)

$# Simple
?x += 1
"""
    catalog = SubPatternCatalog()
    catalog.load_string(sub_code, Languages.PYTHON)

    pattern_code = """
def ?():
    ?$NoBodyAccepted(?v):
        extra()
"""
    proc = PythonProcessor()
    with pytest.raises(ValueError, match="does not accept a body"):
        proc.create_pda(proc.generate_tree_from_code(pattern_code), catalog=catalog)


def test_multiple_calls_with_different_bodies():
    sub_code = """
$|LoopTwice(?i, ?v): ?body

$# For
for ?i in ?v:
    ?body
"""
    catalog = SubPatternCatalog()
    catalog.load_string(sub_code, Languages.PYTHON)

    pattern_code = """
def ?():
    ?$LoopTwice(?i, ?v):
        res.append(?i)
    ?$LoopTwice(?j, ?w):
        res.remove(?j)
"""
    code_target = """
def run():
    for x in items_in:
        res.append(x)
    for y in items_out:
        res.remove(y)
"""
    proc = PythonProcessor()
    pda = proc.create_pda(proc.generate_tree_from_code(pattern_code), catalog=catalog)
    res = Matcher.match(pda, proc.generate_tree_from_code(code_target), catalog=catalog)
    assert res.count() > 0
    bindings = res.matches[0].bindings
    assert bindings["i"].getText() == "x"
    assert bindings["v"].getText() == "items_in"
    assert bindings["j"].getText() == "y"
    assert bindings["w"].getText() == "items_out"
