import pytest
from pyttern.language_processors import Languages
from pyttern.pattern.compiled import LeafPattern
from pyttern.pattern.compiler import PatternCompiler
from pyttern.pattern.composite import AndPattern, OrPattern, NotPattern
from pyttern.pattern.result import MatchResult


class TestPatternJsonLeaf:
    def test_compile_leaf_with_filename_and_code(self):
        compiler = PatternCompiler(Languages.PYTHON)
        leaf_json = {
            "filename": "assign.pyt",
            "code": "x = ?",
            "lang": "python",
        }
        pattern = compiler.compile_json(leaf_json)

        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "assign.pyt"

        result = pattern.matches("x = 10")
        assert bool(result) is True
        assert result.matched is True
        assert result.matches is not None
        assert result.matches.count() > 0

        neg_result = pattern.matches("y = 10")
        assert bool(neg_result) is False

    def test_compile_leaf_with_name_and_code(self):
        compiler = PatternCompiler(Languages.PYTHON)
        leaf_json = {
            "name": "call_expr",
            "code": "foo(?)",
        }
        pattern = compiler.compile_json(leaf_json)

        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "call_expr"

        res = pattern.matches("foo(42)")
        assert bool(res) is True

    def test_compile_leaf_unnamed_fallback(self):
        compiler = PatternCompiler(Languages.PYTHON)
        leaf_json = {
            "code": "x = 1",
        }
        pattern = compiler.compile_json(leaf_json)
        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "unnamed"

    def test_compile_leaf_invalid_missing_code_and_file(self):
        compiler = PatternCompiler(Languages.PYTHON)
        leaf_json = {"name": "invalid"}
        with pytest.raises((ValueError, KeyError)):
            compiler.compile_json(leaf_json)


class TestPatternJsonComposite:
    def test_compile_composite_and(self):
        compiler = PatternCompiler(Languages.PYTHON)
        tree_json = {
            "name": "and",
            "children": [
                {"name": "p1", "code": "x = ?"},
                {"name": "p2", "code": "y = ?"},
            ],
        }
        pattern = compiler.compile_json(tree_json)

        assert isinstance(pattern, AndPattern)
        assert pattern.name == "and"
        assert len(pattern.children) == 2
        assert isinstance(pattern.children[0], LeafPattern)
        assert isinstance(pattern.children[1], LeafPattern)

        # Matches both
        res = pattern.matches("x = 1\ny = 2\n")
        assert bool(res) is True

        # Matches only one
        res_fail = pattern.matches("x = 1\nz = 2\n")
        assert bool(res_fail) is False

    def test_compile_composite_or(self):
        compiler = PatternCompiler(Languages.PYTHON)
        tree_json = {
            "name": "or",
            "children": [
                {"name": "p1", "code": "x = ?"},
                {"name": "p2", "code": "y = ?"},
            ],
        }
        pattern = compiler.compile_json(tree_json)

        assert isinstance(pattern, OrPattern)
        assert pattern.name == "or"
        assert len(pattern.children) == 2

        # Matches one
        res = pattern.matches("x = 1\n")
        assert bool(res) is True

        # Matches neither
        res_fail = pattern.matches("z = 3\n")
        assert bool(res_fail) is False

    def test_compile_composite_not(self):
        compiler = PatternCompiler(Languages.PYTHON)
        tree_json = {
            "name": "not",
            "children": [
                {"name": "p1", "code": "x = ?"},
            ],
        }
        pattern = compiler.compile_json(tree_json)

        assert isinstance(pattern, NotPattern)
        assert pattern.name == "not"

        # Pattern matches code -> NotPattern returns False
        res_match = pattern.matches("x = 1\n")
        assert bool(res_match) is False

        # Pattern doesn't match code -> NotPattern returns True
        res_nomatch = pattern.matches("y = 2\n")
        assert bool(res_nomatch) is True

    def test_compile_composite_custom_root_name_defaults_to_and(self):
        compiler = PatternCompiler(Languages.PYTHON)
        tree_json = {
            "name": "EarlyReturn",
            "children": [
                {"name": "p1", "code": "x = ?"},
                {"name": "p2", "code": "y = ?"},
            ],
        }
        pattern = compiler.compile_json(tree_json)

        assert isinstance(pattern, AndPattern)
        assert pattern.name == "EarlyReturn"

        res = pattern.matches("x = 1\ny = 2\n")
        assert bool(res) is True

    def test_compile_composite_case_insensitivity(self):
        compiler = PatternCompiler(Languages.PYTHON)
        or_json = {
            "name": "OR",
            "children": [{"name": "p1", "code": "x = ?"}],
        }
        not_json = {
            "name": "NoT",
            "children": [{"name": "p1", "code": "x = ?"}],
        }
        and_json = {
            "name": "AnD",
            "children": [{"name": "p1", "code": "x = ?"}],
        }

        p_or = compiler.compile_json(or_json)
        assert isinstance(p_or, OrPattern)
        assert p_or.name == "OR"

        p_not = compiler.compile_json(not_json)
        assert isinstance(p_not, NotPattern)
        assert p_not.name == "NoT"

        p_and = compiler.compile_json(and_json)
        assert isinstance(p_and, AndPattern)
        assert p_and.name == "AnD"

    def test_compile_composite_default_name_when_missing(self):
        compiler = PatternCompiler(Languages.PYTHON)
        tree_json = {
            "children": [
                {"name": "p1", "code": "x = ?"},
            ],
        }
        pattern = compiler.compile_json(tree_json)
        assert isinstance(pattern, AndPattern)
        assert pattern.name == "and"


class TestPatternJsonNestedCompound:
    def test_nested_early_return_hierarchy(self):
        # Reproducing data.json structure
        pattern_json = {
            "name": "EarlyReturn",
            "children": [
                {
                    "name": "and",
                    "children": [
                        {
                            "name": "not",
                            "children": [
                                {
                                    "filename": "okreturn.pyt",
                                    "code": "def ?(?*):\n    return ?",
                                }
                            ],
                        },
                        {
                            "filename": "earlyreturn.pyt",
                            "code": "def ?(?*):\n    ?:\n        return ?",
                        },
                    ],
                }
            ],
        }

        compiler = PatternCompiler(Languages.PYTHON)
        pattern = compiler.compile_json(pattern_json)

        assert isinstance(pattern, AndPattern)
        assert pattern.name == "EarlyReturn"

        code_match = (
            "def get(x):\n"
            "    if x is None:\n"
            "        return 0\n"
            "    return x\n"
        )

        res = pattern.matches(code_match, stop_at_first=False)
        assert bool(res) is False
        assert res.name == "EarlyReturn"

        # Verify leaf results
        leaf_results = res.leaf_results
        assert len(leaf_results) == 2
        leaf_dict = {lr.name: lr.matched for lr in leaf_results}
        assert leaf_dict["okreturn.pyt"] is True
        assert leaf_dict["earlyreturn.pyt"] is True

        # Verify to_dict structure
        d = res.to_dict()
        assert d["name"] == "EarlyReturn"
        assert d["result"] is False
        assert "children" in d

        and_child = d["children"][0]
        assert and_child["name"] == "and"
        assert and_child["result"] is False
        assert len(and_child["children"]) == 2

        not_child = next(c for c in and_child["children"] if c["name"] == "not")
        assert not_child["result"] is False
        assert len(not_child["children"]) == 1
        assert not_child["children"][0]["name"] == "okreturn.pyt"
        assert not_child["children"][0]["result"] is True
        assert "matches" in not_child["children"][0]

        early_leaf = next(c for c in and_child["children"] if c["name"] == "earlyreturn.pyt")
        assert early_leaf["result"] is True
        assert "matches" in early_leaf


class TestPolymorphicCompileJson:
    def test_compile_dict_routes_to_compile_json(self):
        compiler = PatternCompiler(Languages.PYTHON)
        pattern_dict = {
            "name": "simple",
            "code": "x = 1",
        }
        pattern = compiler.compile(pattern_dict)
        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "simple"

    def test_compile_json_string_routes_to_compile_json(self):
        compiler = PatternCompiler(Languages.PYTHON)
        json_str = '{"name": "json_leaf", "code": "y = 2"}'
        pattern = compiler.compile_json(json_str)
        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "json_leaf"

    def test_compile_polymorphic_json_string(self):
        compiler = PatternCompiler(Languages.PYTHON)
        json_str = '{"name": "json_leaf", "code": "y = 2"}'
        pattern = compiler.compile(json_str)
        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "json_leaf"

    def test_compile_json_explicit_lang(self):
        compiler = PatternCompiler(Languages.PYTHON)
        leaf_json = {
            "name": "java_leaf",
            "code": "int x = 1;",
        }
        pattern = compiler.compile_json(leaf_json, lang="java")
        assert isinstance(pattern, LeafPattern)
        assert pattern.name == "java_leaf"


class TestMatchResultSerialization:
    def test_leaf_to_dict_contains_matches(self):
        compiler = PatternCompiler(Languages.PYTHON)
        leaf = compiler.compile_code("x = ?", name="leaf_assign")
        res = leaf.matches("x = 10")
        d = res.to_dict()
        assert d["name"] == "leaf_assign"
        assert d["result"] is True
        assert "matches" in d
        assert d["matches"] is not None
        assert d["matches"].count() == 1

    def test_composite_to_dict_contains_children(self):
        compiler = PatternCompiler(Languages.PYTHON)
        tree_json = {
            "name": "and",
            "children": [
                {"name": "p1", "code": "x = ?"},
                {"name": "p2", "code": "y = ?"},
            ],
        }
        pattern = compiler.compile_json(tree_json)
        res = pattern.matches("x = 1\ny = 2\n")
        d = res.to_dict()
        assert d["name"] == "and"
        assert d["result"] is True
        assert "children" in d
        assert len(d["children"]) == 2
        assert d["children"][0]["name"] == "p1"
        assert "matches" in d["children"][0]
        assert d["children"][1]["name"] == "p2"
        assert "matches" in d["children"][1]

    def test_leaf_results_property(self):
        compiler = PatternCompiler(Languages.PYTHON)
        tree_json = {
            "name": "EarlyReturn",
            "children": [
                {
                    "name": "or",
                    "children": [
                        {"name": "a", "code": "x = 1"},
                        {"name": "b", "code": "x = 2"},
                    ],
                },
                {"name": "c", "code": "y = 3"},
            ],
        }
        pattern = compiler.compile_json(tree_json)
        res = pattern.matches("x = 1\ny = 3\n", stop_at_first=False)
        leaves = res.leaf_results
        assert len(leaves) == 3
        assert [l.name for l in leaves] == ["a", "b", "c"]
        assert [l.matched for l in leaves] == [True, False, True]


class TestVisualizerWebBatchMatch:
    @pytest.fixture
    def client(self):
        from pyttern.visualizer.web.application import app
        app.config["TESTING"] = True
        with app.test_client() as client:
            yield client

    def test_batch_match_endpoint_with_compound_pattern(self, client):
        payload = {
            "compoundPattern": {
                "name": "TestCompound",
                "children": [
                    {
                        "name": "and",
                        "children": [
                            {"filename": "assign_x.pyt", "code": "x = ?"},
                            {"filename": "assign_y.pyt", "code": "y = ?"},
                        ],
                    }
                ],
            },
            "codes": [
                {"filename": "match_both.py", "code": "x = 1\ny = 2\n"},
                {"filename": "match_one.py", "code": "x = 1\nz = 3\n"},
            ],
            "lang": "python",
        }

        # Keep a copy of compoundPattern to verify it was NOT mutated
        import copy
        pattern_copy = copy.deepcopy(payload["compoundPattern"])

        response = client.post("/api/batch_match", json=payload)
        assert response.status_code == 200

        # Verify no in-place mutation of input pattern
        assert payload["compoundPattern"]["name"] == pattern_copy["name"]

        import json
        data = json.loads(response.data)
        assert len(data) == 2

        # Check match_both.py
        r1 = next(r for r in data if r["name"] == "match_both.py")
        assert r1["match"] is True
        assert r1["patternsMatchResults"]["assign_x.pyt"] is True
        assert r1["patternsMatchResults"]["assign_y.pyt"] is True

        # Check match_one.py
        r2 = next(r for r in data if r["name"] == "match_one.py")
        assert r2["match"] is False
        assert r2["patternsMatchResults"]["assign_x.pyt"] is True
        assert r2["patternsMatchResults"]["assign_y.pyt"] is False

