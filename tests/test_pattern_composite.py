from dataclasses import FrozenInstanceError
from pathlib import Path
from unittest.mock import MagicMock
import pytest

from pyttern.language_processors.languages import Languages
from pyttern.pattern import (
    PatternCompiler,
    CompiledPattern,
    LeafPattern,
    MatchResult,
    AndPattern,
    OrPattern,
    NotPattern,
)
from pyttern.pattern.composite import CompositePattern


class MockPattern(CompiledPattern):
    def __init__(self, name: str, should_match: bool):
        super().__init__(name=name)
        object.__setattr__(self, "call_count", 0)
        object.__setattr__(self, "should_match", should_match)

    def matches(self, target, stop_at_first=None):
        object.__setattr__(self, "call_count", self.call_count + 1)
        return MatchResult(matched=self.should_match, name=self.name, children=())


class TestCompositePatternHierarchyAndImmutability:
    def test_composite_patterns_subclass_compiled_pattern(self):
        and_pat = AndPattern()
        or_pat = OrPattern()
        not_pat = NotPattern()

        assert isinstance(and_pat, CompositePattern)
        assert isinstance(and_pat, CompiledPattern)
        assert isinstance(or_pat, CompositePattern)
        assert isinstance(or_pat, CompiledPattern)
        assert isinstance(not_pat, CompositePattern)
        assert isinstance(not_pat, CompiledPattern)

    def test_default_names(self):
        assert AndPattern().name == "and"
        assert OrPattern().name == "or"
        assert NotPattern().name == "not"

    def test_custom_names(self):
        assert AndPattern(name="custom_and").name == "custom_and"
        assert OrPattern(name="custom_or").name == "custom_or"
        assert NotPattern(name="custom_not").name == "custom_not"

    def test_children_coerced_to_tuple(self):
        child1 = MockPattern("c1", True)
        child2 = MockPattern("c2", False)

        pat = AndPattern(children=[child1, child2])
        assert isinstance(pat.children, tuple)
        assert pat.children == (child1, child2)

    def test_positional_children_initialization(self):
        c1 = MockPattern("c1", True)
        c2 = MockPattern("c2", False)

        and_pat = AndPattern([c1, c2])
        assert and_pat.children == (c1, c2)
        assert and_pat.name == "and"

        or_pat = OrPattern([c1, c2])
        assert or_pat.children == (c1, c2)
        assert or_pat.name == "or"

    def test_not_pattern_single_child_init(self):
        c1 = MockPattern("c1", True)

        not_pat1 = NotPattern(c1)
        assert not_pat1.child is c1
        assert not_pat1.children == (c1,)

        not_pat2 = NotPattern(child=c1)
        assert not_pat2.child is c1
        assert not_pat2.children == (c1,)

        not_pat3 = NotPattern([c1])
        assert not_pat3.child is c1
        assert not_pat3.children == (c1,)

    def test_immutability(self):
        pat = AndPattern(children=[MockPattern("c1", True)])

        with pytest.raises((FrozenInstanceError, AttributeError)):
            pat.name = "mutated"

        with pytest.raises((FrozenInstanceError, AttributeError)):
            pat.children = ()


class TestAndPatternExecution:
    def test_and_pattern_all_match(self):
        compiler = PatternCompiler(Languages.PYTHON)
        p1 = compiler.compile("x = ?a")
        p2 = compiler.compile("y = ?b")

        and_pat = AndPattern([p1, p2])
        code = "x = 1\ny = 2\n"

        result = and_pat.matches(code)
        assert bool(result) is True
        assert result.matched is True
        assert result.name == "and"
        assert len(result.children) == 2
        assert result.children[0].matched is True
        assert result.children[1].matched is True

    def test_and_pattern_one_fails(self):
        compiler = PatternCompiler(Languages.PYTHON)
        p1 = compiler.compile("x = ?a")
        p2 = compiler.compile("z = ?b")

        and_pat = AndPattern([p1, p2])
        code = "x = 1\ny = 2\n"

        result = and_pat.matches(code)
        assert bool(result) is False
        assert result.matched is False

    def test_and_pattern_matches_cst_node(self):
        compiler = PatternCompiler(Languages.PYTHON)
        p1 = compiler.compile("x = ?a")
        p2 = compiler.compile("y = ?b")

        and_pat = AndPattern([p1, p2])
        cst = compiler.processor.generate_tree_from_code("x = 1\ny = 2\n")

        result = and_pat.matches(cst)
        assert bool(result) is True
        assert result.matched is True

    def test_and_pattern_matches_file_path(self, tmp_path):
        target_file = tmp_path / "target.py"
        target_file.write_text("x = 1\ny = 2\n")

        compiler = PatternCompiler(Languages.PYTHON)
        p1 = compiler.compile("x = ?a")
        p2 = compiler.compile("y = ?b")

        and_pat = AndPattern([p1, p2])
        result = and_pat.matches(target_file)
        assert bool(result) is True

    def test_and_pattern_invalid_target_type_raises_type_error(self):
        compiler = PatternCompiler(Languages.PYTHON)
        p1 = compiler.compile("x = ?a")
        and_pat = AndPattern([p1])

        with pytest.raises(TypeError):
            and_pat.matches(12345)

    def test_and_pattern_short_circuits_on_failure_when_stop_at_first(self):
        c1 = MockPattern("c1", True)
        c2 = MockPattern("c2", False)
        c3 = MockPattern("c3", True)

        and_pat = AndPattern([c1, c2, c3])
        result = and_pat.matches("code", stop_at_first=True)

        assert result.matched is False
        assert c1.call_count == 1
        assert c2.call_count == 1
        assert c3.call_count == 0  # Short-circuited!
        assert len(result.children) == 2

    def test_and_pattern_exhaustive_when_not_stop_at_first(self):
        c1 = MockPattern("c1", True)
        c2 = MockPattern("c2", False)
        c3 = MockPattern("c3", True)

        and_pat = AndPattern([c1, c2, c3])
        result = and_pat.matches("code", stop_at_first=False)

        assert result.matched is False
        assert c1.call_count == 1
        assert c2.call_count == 1
        assert c3.call_count == 1  # Evaluated all
        assert len(result.children) == 3


class TestOrPatternExecution:
    def test_or_pattern_first_matches(self):
        compiler = PatternCompiler(Languages.PYTHON)
        p1 = compiler.compile("x = ?a")
        p2 = compiler.compile("z = ?b")

        or_pat = OrPattern([p1, p2])
        code = "x = 1\ny = 2\n"

        result = or_pat.matches(code)
        assert bool(result) is True
        assert result.matched is True
        assert result.name == "or"

    def test_or_pattern_all_fail(self):
        compiler = PatternCompiler(Languages.PYTHON)
        p1 = compiler.compile("w = ?a")
        p2 = compiler.compile("z = ?b")

        or_pat = OrPattern([p1, p2])
        code = "x = 1\ny = 2\n"

        result = or_pat.matches(code)
        assert bool(result) is False
        assert result.matched is False

    def test_or_pattern_short_circuits_on_success_when_stop_at_first(self):
        c1 = MockPattern("c1", False)
        c2 = MockPattern("c2", True)
        c3 = MockPattern("c3", True)

        or_pat = OrPattern([c1, c2, c3])
        result = or_pat.matches("code", stop_at_first=True)

        assert result.matched is True
        assert c1.call_count == 1
        assert c2.call_count == 1
        assert c3.call_count == 0  # Short-circuited!
        assert len(result.children) == 2

    def test_or_pattern_exhaustive_when_not_stop_at_first(self):
        c1 = MockPattern("c1", False)
        c2 = MockPattern("c2", True)
        c3 = MockPattern("c3", False)

        or_pat = OrPattern([c1, c2, c3])
        result = or_pat.matches("code", stop_at_first=False)

        assert result.matched is True
        assert c1.call_count == 1
        assert c2.call_count == 1
        assert c3.call_count == 1  # Evaluated all
        assert len(result.children) == 3


class TestNotPatternExecution:
    def test_not_pattern_inverts_match(self):
        compiler = PatternCompiler(Languages.PYTHON)
        p1 = compiler.compile("x = ?a")
        not_pat = NotPattern(p1)

        res_match = not_pat.matches("x = 1\n")
        assert bool(res_match) is False
        assert res_match.matched is False

        res_no_match = not_pat.matches("y = 1\n")
        assert bool(res_no_match) is True
        assert res_no_match.matched is True

    def test_not_pattern_multiple_children_not_any(self):
        c1 = MockPattern("c1", False)
        c2 = MockPattern("c2", True)

        not_pat = NotPattern(children=[c1, c2])
        result = not_pat.matches("code")

        assert result.matched is False  # not any([False, True]) -> False

    def test_not_pattern_short_circuits_on_match_when_stop_at_first(self):
        c1 = MockPattern("c1", True)
        c2 = MockPattern("c2", False)

        not_pat = NotPattern(children=[c1, c2])
        result = not_pat.matches("code", stop_at_first=True)

        assert result.matched is False
        assert c1.call_count == 1
        assert c2.call_count == 0  # Short-circuited!
        assert len(result.children) == 1

    def test_not_pattern_exhaustive_when_not_stop_at_first(self):
        c1 = MockPattern("c1", True)
        c2 = MockPattern("c2", False)

        not_pat = NotPattern(children=[c1, c2])
        result = not_pat.matches("code", stop_at_first=False)

        assert result.matched is False
        assert c1.call_count == 1
        assert c2.call_count == 1
        assert len(result.children) == 2


class TestDirectoryCompilation:
    def test_compile_dir_simple_and_folder(self, tmp_path):
        pattern_dir = tmp_path / "simple_dir"
        pattern_dir.mkdir()
        (pattern_dir / "p1.pyt").write_text("x = ?a\n")
        (pattern_dir / "p2.pyt").write_text("y = ?b\n")
        (pattern_dir / "ignored.txt").write_text("ignored file\n")

        compiler = PatternCompiler(Languages.PYTHON)
        compiled = compiler.compile_dir(pattern_dir)

        assert isinstance(compiled, AndPattern)
        assert compiled.name == "and"
        assert len(compiled.children) == 2
        assert isinstance(compiled.children[0], LeafPattern)
        assert isinstance(compiled.children[1], LeafPattern)
        assert [c.name for c in compiled.children] == ["p1.pyt", "p2.pyt"]

    def test_compile_dir_with_logical_subdirectories(self, tmp_path):
        root = tmp_path / "composite_root"
        root.mkdir()
        (root / "root.pyt").write_text("a = 1\n")

        or_dir = root / "or"
        or_dir.mkdir()
        (or_dir / "opt1.pyt").write_text("b = 2\n")
        (or_dir / "opt2.pyt").write_text("c = 3\n")

        not_dir = root / "not"
        not_dir.mkdir()
        (not_dir / "forbidden.pyt").write_text("d = 4\n")

        compiler = PatternCompiler(Languages.PYTHON)
        compiled = compiler.compile_dir(root)

        assert isinstance(compiled, AndPattern)
        assert len(compiled.children) == 3

        names = [c.name for c in compiled.children]
        assert "not" in names
        assert "or" in names
        assert "root.pyt" in names

        # Find the or and not subtrees
        or_child = next(c for c in compiled.children if c.name == "or")
        not_child = next(c for c in compiled.children if c.name == "not")

        assert isinstance(or_child, OrPattern)
        assert len(or_child.children) == 2
        assert [c.name for c in or_child.children] == ["opt1.pyt", "opt2.pyt"]

        assert isinstance(not_child, NotPattern)
        assert len(not_child.children) == 1
        assert not_child.children[0].name == "forbidden.pyt"

    def test_compile_dir_non_logical_subdirectory_defaults_to_and(self, tmp_path):
        root = tmp_path / "custom_root"
        root.mkdir()

        nested_dir = root / "custom_subfolder"
        nested_dir.mkdir()
        (nested_dir / "rule.pyt").write_text("x = 10\n")

        compiler = PatternCompiler(Languages.PYTHON)
        compiled = compiler.compile_dir(root)

        assert isinstance(compiled, AndPattern)
        assert len(compiled.children) == 1

        nested_child = compiled.children[0]
        assert isinstance(nested_child, AndPattern)
        assert nested_child.name == "and"
        assert len(nested_child.children) == 1
        assert nested_child.children[0].name == "rule.pyt"

    def test_compile_dir_nonexistent_raises_file_not_found(self):
        compiler = PatternCompiler(Languages.PYTHON)
        with pytest.raises(FileNotFoundError):
            compiler.compile_dir("nonexistent_pattern_dir_12345")

    def test_compile_dir_on_file_raises_not_a_directory(self, tmp_path):
        a_file = tmp_path / "single_file.pyt"
        a_file.write_text("x = 1\n")

        compiler = PatternCompiler(Languages.PYTHON)
        with pytest.raises(NotADirectoryError):
            compiler.compile_dir(a_file)

    def test_compile_dir_invalid_op_raises_value_error(self, tmp_path):
        empty_dir = tmp_path / "empty"
        empty_dir.mkdir()

        compiler = PatternCompiler(Languages.PYTHON)
        with pytest.raises(ValueError):
            compiler.compile_dir(empty_dir, op="invalid_op")

    def test_compile_dir_java_language(self, tmp_path):
        java_dir = tmp_path / "java_dir"
        java_dir.mkdir()
        (java_dir / "rule.jat").write_text("class #name {}\n")
        (java_dir / "ignored.py").write_text("x = 1\n")

        compiler = PatternCompiler(Languages.JAVA)
        compiled = compiler.compile_dir(java_dir)

        assert isinstance(compiled, AndPattern)
        assert len(compiled.children) == 1
        assert compiled.children[0].name == "rule.jat"


class TestPolymorphicCompileWithDirectories:
    def test_compile_with_directory_path_str(self, tmp_path):
        pattern_dir = tmp_path / "dir_str"
        pattern_dir.mkdir()
        (pattern_dir / "p1.pyt").write_text("x = ?a\n")

        compiler = PatternCompiler(Languages.PYTHON)
        compiled = compiler.compile(str(pattern_dir))

        assert isinstance(compiled, AndPattern)
        assert len(compiled.children) == 1

    def test_compile_with_directory_path_obj(self, tmp_path):
        pattern_dir = tmp_path / "dir_obj"
        pattern_dir.mkdir()
        (pattern_dir / "p1.pyt").write_text("x = ?a\n")

        compiler = PatternCompiler(Languages.PYTHON)
        compiled = compiler.compile(pattern_dir)

        assert isinstance(compiled, AndPattern)
        assert len(compiled.children) == 1

    def test_compile_with_directory_custom_name(self, tmp_path):
        pattern_dir = tmp_path / "named_dir"
        pattern_dir.mkdir()
        (pattern_dir / "p1.pyt").write_text("x = ?a\n")

        compiler = PatternCompiler(Languages.PYTHON)
        compiled = compiler.compile(pattern_dir, name="my_custom_root")

        assert isinstance(compiled, AndPattern)
        assert compiled.name == "my_custom_root"


class TestHierarchicalMatchResult:
    def test_match_result_to_dict_hierarchy(self, tmp_path):
        pattern_dir = tmp_path / "hierarchy_dir"
        pattern_dir.mkdir()
        (pattern_dir / "p1.pyt").write_text("x = ?a\n")

        or_dir = pattern_dir / "or"
        or_dir.mkdir()
        (or_dir / "p2.pyt").write_text("y = ?b\n")

        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile(pattern_dir)

        code = "x = 1\ny = 2\n"
        result = pattern.matches(code)

        assert bool(result) is True
        res_dict = result.to_dict()

        assert res_dict["name"] == "and"
        assert res_dict["result"] is True
        assert "children" in res_dict
        assert len(res_dict["children"]) == 2

        # One child is p1.pyt leaf, other is or composite
        leaf_res = next(c for c in res_dict["children"] if c["name"] == "p1.pyt")
        or_res = next(c for c in res_dict["children"] if c["name"] == "or")

        assert leaf_res["result"] is True
        assert "matches" in leaf_res

        assert or_res["result"] is True
        assert "children" in or_res
        assert or_res["children"][0]["name"] == "p2.pyt"
