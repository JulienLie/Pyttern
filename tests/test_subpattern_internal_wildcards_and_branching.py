import pytest
from pyttern.language_processors.languages import Languages
from pyttern.pattern.compiler import PatternCompiler
from pyttern.subpattern.catalog import SubPatternCatalog


@pytest.fixture
def catalog():
    cat = SubPatternCatalog()
    cat.load_string("""
$|LoopInternal(?collection): ?body

$# ForLoop
for ?temp in ?collection:
    ?body

$|RepeatTwice(?lock): ?body

$# Twice
with ?lock:
    ?body
    ?body

$|BranchSub(?ctx): ?body

$# WithBranch
with ?ctx:
    ?body

$# TryBranch
try:
    ?body
except:
    pass
""")
    return cat


def test_internal_wildcard_does_not_collide_with_caller_variable(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    # Caller defines ?temp in outer scope and uses ?temp in the body
    pattern = compiler.compile("""
def ?():
    ?temp = 42
    ?$LoopInternal(?items):
        process(?temp)
""")
    # Target code: loop variable is 'item' (bound to internal ?temp in subpattern),
    # and process(x) matches the caller's ?temp bound to 'x'.
    code_ok = """
def run():
    x = 42
    for item in items_list:
        process(x)
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["temp"].getText() == "x"
    assert res.binding["items"].getText() == "items_list"
    # Ensure internal wildcard did not leak into final bindings
    assert not any(k.startswith("__") for k in res.binding.keys())


def test_internal_wildcard_rejection_on_caller_mismatch(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?temp = 42
    ?$LoopInternal(?items):
        process(?temp)
""")
    code_nok = """
def run():
    x = 42
    for item in items_list:
        process(999)
"""
    res = pattern.matches(code_nok)
    assert not res
    assert not res.matched


def test_multiple_body_placeholders_in_same_transformation(catalog):
    # Transformation has ?body twice in sequence:
    # with ?lock: ?body \n ?body
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$RepeatTwice(?lock):
        work()
""")
    code_ok = """
def run():
    with my_mutex:
        work()
        work()
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["lock"].getText() == "my_mutex"

    code_nok = """
def run():
    with my_mutex:
        work()
        sleep()
"""
    res_nok = pattern.matches(code_nok)
    assert not res_nok


def test_branching_subpattern_first_branch(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$BranchSub(?ctx):
        work()
""")
    code_with = """
def run():
    with active_lock:
        work()
"""
    res = pattern.matches(code_with)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["ctx"].getText() == "active_lock"


def test_branching_subpattern_second_branch(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$BranchSub(?ctx):
        work()
""")
    code_try = """
def run():
    try:
        work()
    except:
        pass
"""
    res = pattern.matches(code_try)
    assert res
    assert res.matched
    assert res.binding is not None


def test_branching_subpattern_mismatch_neither(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$BranchSub(?ctx):
        work()
""")
    code_neither = """
def run():
    work()
"""
    res = pattern.matches(code_neither)
    assert not res
    assert not res.matched
