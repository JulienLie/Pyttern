import tempfile
from pathlib import Path
import pytest
from dataclasses import FrozenInstanceError

from pyttern.language_processors.languages import Languages
from pyttern.language_processors.python_processor import PythonProcessor
from pyttern.simulator.pda import PDA
from pyttern.subpattern import SubPatternCatalog

# Public exports under test
from pyttern.pattern import PatternCompiler, CompiledPattern, LeafPattern, MatchResult


class TestPatternCompilerInitialization:
    def test_init_with_language_enum(self):
        compiler = PatternCompiler(Languages.PYTHON)
        assert compiler.language == Languages.PYTHON
        assert isinstance(compiler.processor, PythonProcessor)
        assert isinstance(compiler.catalog, SubPatternCatalog)

    def test_init_with_language_string_case_insensitive(self):
        compiler_lower = PatternCompiler("python")
        assert compiler_lower.language == Languages.PYTHON

        compiler_upper = PatternCompiler("PYTHON")
        assert compiler_upper.language == Languages.PYTHON

    def test_init_with_custom_catalog(self):
        catalog = SubPatternCatalog()
        compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
        assert compiler.catalog is catalog

    def test_init_with_invalid_language_raises_value_error(self):
        with pytest.raises(ValueError):
            PatternCompiler("unsupported_lang_xyz")


class TestPatternCompilerCompilation:
    def test_compile_code_returns_leaf_pattern_with_pda(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile_code("x = ?a")

        assert isinstance(pattern, LeafPattern)
        assert isinstance(pattern, CompiledPattern)
        assert pattern.name == "unnamed"
        assert pattern.pda is not None

    def test_compile_code_custom_name(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile_code("x = ?a", name="assign_pattern")

        assert pattern.name == "assign_pattern"

    def test_compile_code_invalid_type_raises_type_error(self):
        compiler = PatternCompiler(Languages.PYTHON)
        with pytest.raises(TypeError):
            compiler.compile_code(123)

    def test_compile_file_loads_and_compiles(self, tmp_path):
        pattern_file = tmp_path / "custom_pattern.pyt"
        pattern_file.write_text("x = ?a\n", encoding="utf-8")

        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile_file(pattern_file)

        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "custom_pattern.pyt"

    def test_compile_file_nonexistent_raises_file_not_found(self):
        compiler = PatternCompiler(Languages.PYTHON)
        with pytest.raises(FileNotFoundError):
            compiler.compile_file("non_existent_pattern_path_12345.pyt")

    def test_polymorphic_compile_dispatches_to_code(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?a")

        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "unnamed"

    def test_polymorphic_compile_dispatches_to_file_str(self, tmp_path):
        pattern_file = tmp_path / "my_rule.pyt"
        pattern_file.write_text("x = ?a\n", encoding="utf-8")

        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile(str(pattern_file))

        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "my_rule.pyt"

    def test_polymorphic_compile_dispatches_to_file_path_obj(self, tmp_path):
        pattern_file = tmp_path / "my_path_rule.pyt"
        pattern_file.write_text("x = ?a\n", encoding="utf-8")

        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile(pattern_file)

        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "my_path_rule.pyt"

    def test_polymorphic_compile_path_obj_nonexistent_raises_error(self):
        compiler = PatternCompiler(Languages.PYTHON)
        with pytest.raises(FileNotFoundError):
            compiler.compile(Path("does_not_exist_98765.pyt"))


class TestLeafPatternExecution:
    def test_matches_with_cst_node(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        cst = compiler.processor.generate_tree_from_code("x = 42\n")
        result = pattern.matches(cst)

        assert isinstance(result, MatchResult)
        assert bool(result) is True
        assert result.matched is True
        assert result.name == "unnamed"
        assert result.matches is not None
        assert result.matches.count() > 0

    def test_matches_with_raw_code_string(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        result = pattern.matches("x = 42\n")

        assert bool(result) is True
        assert result.matched is True

    def test_matches_with_code_string_no_match(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        result = pattern.matches("y = 42\n")

        assert bool(result) is False
        assert result.matched is False
        assert result.matches.count() == 0

    def test_matches_with_file_path(self, tmp_path):
        code_file = tmp_path / "target.py"
        code_file.write_text("x = 42\n", encoding="utf-8")

        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        result_str_path = pattern.matches(str(code_file))
        assert bool(result_str_path) is True

        result_obj_path = pattern.matches(code_file)
        assert bool(result_obj_path) is True

    def test_matches_with_unsupported_target_raises_type_error(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        with pytest.raises(TypeError):
            pattern.matches(12345)


class TestMatchResultContract:
    def test_boolean_evaluation(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        res_match = pattern.matches("x = 10\n")
        res_fail = pattern.matches("y = 10\n")

        assert res_match
        assert not res_fail

    def test_result_name_and_status(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val", name="test_assign")

        result = pattern.matches("x = 10\n")
        assert result.name == "test_assign"
        assert result.matched is True

    def test_result_bindings_access(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        result = pattern.matches("x = 99\n")
        assert result.matched is True

        # Underlying match set bindings
        assert result.matches is not None
        assert len(result.matches.matches) == 1

        # Convenience bindings property
        assert len(result.bindings) == 1
        assert "val" in result.bindings[0]
        assert result.bindings[0]["val"].getText() == "99"

        # Convenience singular binding property
        assert result.binding is not None
        assert result.binding["val"].getText() == "99"

    def test_result_bindings_empty_on_failed_match(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        result = pattern.matches("z = 99\n")
        assert result.matched is False
        assert result.bindings == []
        assert result.binding is None

    def test_result_is_immutable(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        result = pattern.matches("x = 99\n")
        with pytest.raises((FrozenInstanceError, AttributeError)):
            result.matched = False

        with pytest.raises((FrozenInstanceError, AttributeError)):
            result.name = "new_name"


class TestPatternImmutability:
    def test_leaf_pattern_is_immutable(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile("x = ?val")

        with pytest.raises((FrozenInstanceError, AttributeError)):
            pattern.name = "mutated"

        with pytest.raises((FrozenInstanceError, AttributeError)):
            pattern.pda = None


class TestSubPatternCatalogIntegration:
    def test_compiler_with_catalog_and_subpattern(self):
        catalog = SubPatternCatalog()
        subp_code = """
$|Incr(?x)
$# t1
?x += 1
"""
        catalog.load_string(subp_code, Languages.PYTHON)

        compiler = PatternCompiler(Languages.PYTHON, catalog=catalog)
        pattern_code = """
def foo(?val):
    ?$Incr(?val)
    return ?val
"""
        pattern = compiler.compile(pattern_code)

        target_code = """
def foo(count):
    count += 1
    return count
"""
        result = pattern.matches(target_code)
        assert bool(result) is True
        assert result.matched is True
        assert result.binding is not None
        assert "val" in result.binding
