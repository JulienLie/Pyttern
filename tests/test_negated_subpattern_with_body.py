import pytest
from pyttern.language_processors.languages import Languages
from pyttern.pattern.compiler import PatternCompiler
from pyttern.subpattern.catalog import SubPatternCatalog


@pytest.fixture
def catalog():
    cat = SubPatternCatalog()
    cat.load_string("""
$!NoExceptWith(?res): ?body

$# TryExceptWith
try:
    with ?res:
        ?body
except:
    pass

$!NoBareIf(?x): ?body

$# BareIf
if ?x:
    ?body
""")
    return cat


def test_negated_body_conditioned_absence_matches(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?res = get_conn()
    ?$NoExceptWith(?res):
        send_data()
    close(?res)
""")
    code_ok = """
def run():
    conn = get_conn()
    send_data()
    close(conn)
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["res"].getText() == "conn"


def test_negated_body_conditioned_presence_rejects(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?res = get_conn()
    ?$NoExceptWith(?res):
        send_data()
    close(?res)
""")
    code_nok = """
def run():
    conn = get_conn()
    try:
        with conn:
            send_data()
    except:
        pass
    close(conn)
"""
    res = pattern.matches(code_nok)
    assert not res
    assert not res.matched


def test_negated_body_different_variable_does_not_reject(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?res = get_conn()
    ?$NoExceptWith(?res):
        send_data()
    close(?res)
""")
    # Target code contains the prohibited structure with 'other_conn', NOT with 'conn'
    code_ok = """
def run():
    conn = get_conn()
    try:
        with other_conn:
            send_data()
    except:
        pass
    close(conn)
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["res"].getText() == "conn"


def test_negated_body_wildcard_discard(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?start = 1
    ?$NoBareIf(?start):
        ?leaked_var = 99
    finish(?start)
""")
    code_ok = """
def run():
    a = 1
    finish(a)
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["start"].getText() == "a"
    # Ensure variables inside the negated body are discarded and not present
    assert "leaked_var" not in res.binding


def test_negated_subpattern_unbound_arg_discard(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$NoBareIf(?existential):
        action()
    finish()
""")
    code_ok = """
def run():
    action()
    finish()
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.binding is not None
    # Existential parameter in negated subpattern should not leak as a valid binding
    assert res.binding.get("existential") is None
