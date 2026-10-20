import pytest
from antlr4 import ParserRuleContext
from pyttern.language_processors.languages import Languages
from pyttern.language_processors.python_processor import PythonProcessor
from pyttern.main import PytternMatcher
from pyttern.pytternfsm.python.python_to_pda import Python_to_PDA
from pyttern.simulator.Matcher import Matcher
from pyttern.simulator.subpatterns import _tau_calls_not
from pyttern.subpattern import (
    SubPatternCatalog,
    loaded_subpatterns,
)


@pytest.fixture(autouse=True)
def clean_globals():
    loaded_subpatterns.clear()
    yield
    loaded_subpatterns.clear()


class TestCompilerCatalogIntegration:
    """Requirement 1: Pattern compiler receives catalog and delegates resolution/compilation to it."""

    def test_compiler_resolves_and_compiles_subpattern_from_catalog(self):
        catalog = SubPatternCatalog()
        code = """
$|Incr(?x)
$# t1
?x += 1
"""
        catalog.load_string(code, Languages.PYTHON)

        processor = PythonProcessor()
        pattern_code = """
def foo(?val):
    ?$Incr(?val)
    return ?val
"""
        tree = processor.generate_tree_from_code(pattern_code)

        # Compiler receives catalog explicitly
        compiler = Python_to_PDA(catalog=catalog)
        fsm_dict = compiler.visit(tree)

        assert "__main__" in fsm_dict
        # Subpattern transformation should be compiled and included in fsm_dict
        assert "t1" in fsm_dict
        # Global dictionary must remain untouched
        assert len(loaded_subpatterns) == 0

    def test_compiler_raises_error_when_subpattern_not_in_catalog(self):
        catalog = SubPatternCatalog()
        processor = PythonProcessor()
        pattern_code = """
def foo(?val):
    ?$MissingSubpattern(?val)
"""
        tree = processor.generate_tree_from_code(pattern_code)

        compiler = Python_to_PDA(catalog=catalog)
        with pytest.raises(ValueError, match="MissingSubpattern"):
            compiler.visit(tree)


class TestRuntimeMatcherCatalogIntegration:
    """Requirement 2 & 3: Runtime matcher accepts catalog and passes it to subpattern evaluators."""

    def test_runtime_matcher_and_operator_end_to_end(self):
        catalog = SubPatternCatalog()
        subp_code = """
$&AssignBoth(?a, ?b)
$# t1
?a = 1
$# t2
?b = 2
"""
        catalog.load_string(subp_code, Languages.PYTHON)

        processor = PythonProcessor()
        pattern_code = """
def test():
    ?$AssignBoth(?x, ?y)
"""
        pattern_tree = processor.generate_tree_from_code(pattern_code)
        compiler = Python_to_PDA(catalog=catalog)
        fsm_dict = compiler.visit(pattern_tree)

        code_text = """
def test():
    x = 1
    y = 2
"""
        code_tree = processor.generate_tree_from_code(code_text)

        # Matcher accepts catalog in constructor
        matcher = Matcher(fsm_dict, code_tree, catalog=catalog)
        matcher.start()
        while len(matcher.configurations) > 0:
            matcher.step()

        assert matcher.match_set.count() > 0
        match = matcher.match_set.matches[0]
        assert "x" in match.bindings
        assert "y" in match.bindings
        assert match.bindings["x"].getText() == "x"
        assert match.bindings["y"].getText() == "y"
        # Confirm no global dictionary reads/writes occurred
        assert len(loaded_subpatterns) == 0

    def test_runtime_matcher_or_operator_end_to_end(self):
        catalog = SubPatternCatalog()
        subp_code = """
$|Incr(?x)
$# aug
?x += 1
$# add
?x = ?x + 1
"""
        catalog.load_string(subp_code, Languages.PYTHON)

        processor = PythonProcessor()
        pattern_code = """
def test(?x):
    ?$Incr(?x)
"""
        pattern_tree = processor.generate_tree_from_code(pattern_code)
        compiler = Python_to_PDA(catalog=catalog)
        fsm_dict = compiler.visit(pattern_tree)

        code_text = """
def test(val):
    val = val + 1
"""
        code_tree = processor.generate_tree_from_code(code_text)

        match_set = Matcher.match(fsm_dict, code_tree, catalog=catalog)
        assert match_set.count() > 0
        match = match_set.matches[0]
        assert match.bindings["x"].getText() == "val"
        assert len(loaded_subpatterns) == 0


class TestNestedSubpatternChecksThroughCatalog:
    """Requirement 4: Nested subpattern checks (_tau_calls_not) resolve cleanly through catalog."""

    def test_tau_calls_not_resolves_via_catalog(self):
        catalog = SubPatternCatalog()
        subp_code = """
$!Forbidden(?x)
$# t1
assert ?x == 0

$|Outer(?y)
$# call_not
?$Forbidden(?y)
"""
        catalog.load_string(subp_code, Languages.PYTHON)

        processor = PythonProcessor()
        pattern_code = """
?$Outer(?val)
"""
        pattern_tree = processor.generate_tree_from_code(pattern_code)
        compiler = Python_to_PDA(catalog=catalog)
        fsm_dict = compiler.visit(pattern_tree)

        tau_call_not = fsm_dict.get("call_not")
        assert tau_call_not is not None

        # Verify _tau_calls_not resolves through catalog without loaded_subpatterns
        assert len(loaded_subpatterns) == 0
        assert _tau_calls_not(tau_call_not, catalog=catalog) is True

    def test_tau_calls_not_returns_false_when_catalog_lacks_subpattern(self):
        catalog = SubPatternCatalog()
        subp_code = """
$&Regular(?x)
$# t1
?x = 1

$|Outer(?y)
$# call_reg
?$Regular(?y)
"""
        catalog.load_string(subp_code, Languages.PYTHON)

        processor = PythonProcessor()
        pattern_tree = processor.generate_tree_from_code("?$Outer(?val)\n")
        compiler = Python_to_PDA(catalog=catalog)
        fsm_dict = compiler.visit(pattern_tree)

        tau_call_reg = fsm_dict.get("call_reg")
        assert _tau_calls_not(tau_call_reg, catalog=catalog) is False


class TestPytternMatcherWithExplicitCatalog:
    """Requirement 5: Patterns compile and match end-to-end without reading global dictionary."""

    def test_pyttern_matcher_with_catalog(self):
        catalog = SubPatternCatalog()
        catalog.load_string("""
$|Step(?v)
$# t1
?v += 1
""", Languages.PYTHON)

        pattern = """
def compute(?a):
    ?$Step(?a)
    return ?a
"""
        code = """
def compute(count):
    count += 1
    return count
"""
        matcher = PytternMatcher(catalog=catalog)
        res = matcher.match(pattern, code, lang="python")
        assert res is True
        assert len(loaded_subpatterns) == 0
