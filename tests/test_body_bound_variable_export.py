import pytest
from pyttern.language_processors.languages import Languages
from pyttern.pattern.compiler import PatternCompiler
from pyttern.subpattern.catalog import SubPatternCatalog


@pytest.fixture
def catalog():
    cat = SubPatternCatalog()
    cat.load_string("""
$|MyWith(?ctx): ?body

$# WithPlain
with ?ctx:
    ?body

$|MyLoop(?item, ?items): ?body

$# ForLoop
for ?item in ?items:
    ?body
""")
    return cat


def test_body_bound_variable_exported_to_subsequent_statement(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?func():
    ?$MyWith(?lock):
        ?result = fetch()
    return ?result
""")
    code_ok = """
def run():
    with my_mutex:
        ans = fetch()
    return ans
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.count() > 0
    bindings = res.binding
    assert bindings is not None
    assert bindings["func"].getText() == "run"
    assert bindings["lock"].getText() == "my_mutex"
    assert bindings["result"].getText() == "ans"


def test_body_bound_variable_mismatch_in_subsequent_statement(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?func():
    ?$MyWith(?lock):
        ?result = fetch()
    return ?result
""")
    code_nok = """
def run():
    with my_mutex:
        ans = fetch()
    return different_val
"""
    res = pattern.matches(code_nok)
    assert not res
    assert not res.matched
    assert res.count() == 0


def test_multiple_variables_bound_in_body_exported(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$MyWith(?lock):
        ?first = 1
        ?second = 2
    verify(?first, ?second)
""")
    code_ok = """
def run():
    with my_lock:
        a = 1
        b = 2
    verify(a, b)
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.binding is not None
    assert res.binding["first"].getText() == "a"
    assert res.binding["second"].getText() == "b"
    assert res.binding["lock"].getText() == "my_lock"


def test_body_bound_variable_used_in_outer_and_inner_mix(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?(?outer):
    ?$MyLoop(?x, ?xs):
        ?derived = transform(?outer, ?x)
    return ?derived
""")
    code_ok = """
def run(source):
    for elem in elements:
        res = transform(source, elem)
    return res
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.binding is not None
    assert res.binding["outer"].getText() == "source"
    assert res.binding["x"].getText() == "elem"
    assert res.binding["xs"].getText() == "elements"
    assert res.binding["derived"].getText() == "res"
