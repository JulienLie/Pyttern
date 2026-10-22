import pytest
from pyttern.language_processors.languages import Languages
from pyttern.pattern.compiler import PatternCompiler
from pyttern.subpattern.catalog import SubPatternCatalog


@pytest.fixture
def catalog():
    cat = SubPatternCatalog()
    cat.load_string("""
$|WithWrap(?lock): ?body

$# With
with ?lock:
    ?body

$|LoopWrap(?it): ?body

$# Loop
for ?x in ?it:
    ?body
""")
    return cat


def test_multi_statement_body_exact_order_matches(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$WithWrap(?lock):
        step1()
        step2()
        step3()
""")
    code_ok = """
def run():
    with m:
        step1()
        step2()
        step3()
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["lock"].getText() == "m"


def test_multi_statement_body_wrong_order_fails(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$WithWrap(?lock):
        step1()
        step2()
        step3()
""")
    code_nok = """
def run():
    with m:
        step2()
        step1()
        step3()
"""
    res = pattern.matches(code_nok)
    assert not res
    assert not res.matched


def test_multi_statement_body_missing_statement_fails(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$WithWrap(?lock):
        step1()
        step2()
        step3()
""")
    code_nok = """
def run():
    with m:
        step1()
        step2()
"""
    res = pattern.matches(code_nok)
    assert not res
    assert not res.matched


def test_multi_statement_body_with_variables_interleaved(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$WithWrap(?lock):
        ?x = init()
        ?y = compute(?x)
        save(?y)
    verify(?x, ?y)
""")
    code_ok = """
def run():
    with m:
        val1 = init()
        val2 = compute(val1)
        save(val2)
    verify(val1, val2)
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["lock"].getText() == "m"
    assert res.binding["x"].getText() == "val1"
    assert res.binding["y"].getText() == "val2"


def test_multiple_call_sites_same_subpattern_different_bodies(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$WithWrap(?lock1):
        first_action()
    middle_step()
    ?$WithWrap(?lock2):
        second_action()
""")
    code_ok = """
def run():
    with l1:
        first_action()
    middle_step()
    with l2:
        second_action()
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["lock1"].getText() == "l1"
    assert res.binding["lock2"].getText() == "l2"

    code_nok = """
def run():
    with l1:
        first_action()
    middle_step()
    with l2:
        first_action()
"""
    res_nok = pattern.matches(code_nok)
    assert not res_nok
    assert not res_nok.matched


def test_catalog_cache_isolation_by_call_id(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    # Clear cache before compiling to observe freshly cached call-site entries
    catalog.clear_cache()
    pattern = compiler.compile("""
def ?():
    ?$WithWrap(?lock1):
        action_one()
    ?$WithWrap(?lock2):
        action_two()
""")
    # Check that catalog._compiled_cache contains distinct call-site entries
    with_wrap_entries = [
        key for key in catalog._compiled_cache.keys()
        if key[0] == "WithWrap"
    ]
    # Expect 2 separate entries for the two distinct call sites
    assert len(with_wrap_entries) >= 2
    # Verify call_ids are distinct
    call_ids = [k[1] for k in with_wrap_entries]
    assert len(set(call_ids)) == len(call_ids)
