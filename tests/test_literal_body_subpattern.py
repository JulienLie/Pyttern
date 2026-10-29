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

$|NoBodySub(?val)

$# Simple
?val += 1
""")
    return cat


def test_literal_body_match_success(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$MyWith(?lock):
        do_work()
""")
    res = pattern.matches("""
def run():
    with my_lock:
        do_work()
""")
    assert res
    assert res.matched
    assert res.count() > 0
    assert res.binding is not None
    assert res.binding["lock"].getText() == "my_lock"


def test_literal_body_mismatch(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    pattern = compiler.compile("""
def ?():
    ?$MyWith(?lock):
        do_work()
""")
    res = pattern.matches("""
def run():
    with my_lock:
        other_work()
""")
    assert not res
    assert not res.matched
    assert res.matches.count() == 0


def test_error_when_body_required_but_missing(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    with pytest.raises(ValueError, match="requires a body wildcard"):
        compiler.compile("""
def ?():
    ?$MyWith(?lock)
""")


def test_error_when_body_provided_but_not_accepted(catalog):
    compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
    with pytest.raises(ValueError, match="does not accept a body"):
        compiler.compile("""
def ?():
    ?$NoBodySub(?x):
        do_work()
""")


def test_literal_body_preserves_syntactic_statement_structure(catalog):
    cat = SubPatternCatalog()
    cat.load_string("""
$|LoopBlock(?items): ?body

$# ForLoop
for ?x in ?items:
    ?body
""")
    compiler = PatternCompiler(Languages.PYTHON, catalog=cat)
    pattern = compiler.compile("""
def ?():
    ?$LoopBlock(?collection):
        if is_valid():
            process()
""")
    res_ok = pattern.matches("""
def run():
    for item in data:
        if is_valid():
            process()
""")
    assert res_ok
    assert res_ok.binding is not None
    assert res_ok.binding["collection"].getText() == "data"

    res_ko = pattern.matches("""
def run():
    for item in data:
        if is_invalid():
            process()
""")
    assert not res_ko


def test_match_files_literal_body(tmp_path, catalog):
    from pyttern import match_files
    pat_file = tmp_path / "pattern.pyt"
    pat_file.write_text("def ?():\n    ?$MyWith(?lock):\n        do_work()\n")
    code_file = tmp_path / "code.py"
    code_file.write_text("def run():\n    with my_lock:\n        do_work()\n")
    res, det = match_files(pat_file, code_file, catalog=catalog, match_details=True)
    assert res
    assert det.count() > 0
    assert det.matches[0].bindings["lock"].getText() == "my_lock"
