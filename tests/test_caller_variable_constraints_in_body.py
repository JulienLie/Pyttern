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

$|MyLoop(?v): ?body

$# ForLoop
for ?v in ?:
    ?body
""")
    return cat


def test_outer_variable_equality_constraint_match(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?func(?x):
    ?$MyWith(?lock):
        do_work(?x)
""")
    code_ok = """
def run(target):
    with my_mutex:
        do_work(target)
"""
    res = pattern.matches(code_ok)
    assert res
    assert res.matched
    assert res.count() > 0
    bindings = res.binding
    assert bindings is not None
    assert bindings["func"].getText() == "run"
    assert bindings["x"].getText() == "target"
    assert bindings["lock"].getText() == "my_mutex"


def test_outer_variable_equality_constraint_mismatch(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?func(?x):
    ?$MyWith(?lock):
        do_work(?x)
""")
    code_nok = """
def run(target):
    with my_mutex:
        do_work(other_var)
"""
    res = pattern.matches(code_nok)
    assert not res
    assert not res.matched
    assert res.count() == 0


def test_same_name_parameter_and_argument_preserved(catalog):
    # Parameter is 'v', caller argument is also 'v'
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$MyLoop(?v):
        process(?v)
""")
    code = """
def run():
    for item in items:
        process(item)
"""
    res = pattern.matches(code)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["v"].getText() == "item"


def test_different_name_parameter_mapped(catalog):
    # Parameter is 'ctx', caller argument is 'my_resource'
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$MyWith(?my_resource):
        release()
""")
    code = """
def run():
    with file_handle:
        release()
"""
    res = pattern.matches(code)
    assert res
    assert res.matched
    assert res.binding is not None
    assert res.binding["my_resource"].getText() == "file_handle"


def test_multiple_outer_variables_in_body(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?(?first, ?second):
    ?$MyWith(?lock):
        combine(?first, ?second)
""")
    code_ok = """
def run(a, b):
    with m:
        combine(a, b)
"""
    res_ok = pattern.matches(code_ok)
    assert res_ok
    assert res_ok.binding is not None
    assert res_ok.binding["first"].getText() == "a"
    assert res_ok.binding["second"].getText() == "b"

    code_nok = """
def run(a, b):
    with m:
        combine(a, wrong)
"""
    res_nok = pattern.matches(code_nok)
    assert not res_nok


def test_outer_variable_same_name_as_subpattern_formal_parameter(catalog):
    # Outer caller has variable 'ctx', subpattern also has formal parameter 'ctx',
    # but subpattern is invoked with argument 'my_res'.
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?func(?ctx):
    ?$MyWith(?my_res):
        do_work(?ctx)
""")
    code_ok = """
def run(data):
    with lock:
        do_work(data)
"""
    res_ok = pattern.matches(code_ok)
    assert res_ok
    assert res_ok.binding is not None
    assert res_ok.binding["func"].getText() == "run"
    assert res_ok.binding["ctx"].getText() == "data"
    assert res_ok.binding["my_res"].getText() == "lock"

    code_nok = """
def run(data):
    with lock:
        do_work(other)
"""
    res_nok = pattern.matches(code_nok)
    assert not res_nok

