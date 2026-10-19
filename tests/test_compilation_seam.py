from unittest.mock import MagicMock
import pytest
from antlr4 import RuleContext

from pyttern.antlr.python import Python3Parser
from pyttern.language_processors.base_processor_interface import BaseProcessor
from pyttern.language_processors.java_processor import JavaProcessor
from pyttern.language_processors.languages import Languages
from pyttern.language_processors.python_processor import PythonProcessor
from pyttern.simulator.pda import PDA
from pyttern.subpattern import SubPatternCatalog, SubPatternCallContext, parse_subpattern_from_string


class TestLanguageProcessorCompilationSeam:
    """Requirement 1 & 2: Interface method on BaseProcessor and implementation on PythonProcessor."""

    def test_base_processor_defines_compile_subpattern_interface(self):
        base = BaseProcessor()
        subp = MagicMock()
        ctx = MagicMock()
        with pytest.raises(NotImplementedError):
            base.compile_subpattern(subp, ctx)

    def test_java_processor_raises_not_implemented_error(self):
        java_proc = JavaProcessor()
        subp = MagicMock()
        ctx = MagicMock()
        with pytest.raises(NotImplementedError):
            java_proc.compile_subpattern(subp, ctx)

    def test_python_processor_compiles_subpattern_for_context(self):
        processor = PythonProcessor()
        code = """
$&Assign(?x, ?y)
$# t1
?x = ?y
"""
        subpatterns = parse_subpattern_from_string(code, Languages.PYTHON)
        subpattern = subpatterns[0]

        # Generate a stmt AST context from code
        sample_code = "a = 1\n"
        tree = processor.generate_tree_from_code(sample_code)
        # Find a StmtContext inside tree
        stmt_ctx = None
        for child in tree.getChildren():
            if isinstance(child, Python3Parser.StmtContext):
                stmt_ctx = child
                break
        assert stmt_ctx is not None

        call_ctx = SubPatternCallContext(ast_ctx=stmt_ctx)
        compiled = processor.compile_subpattern(subpattern, call_ctx)

        assert isinstance(compiled, dict)
        assert "t1" in compiled
        assert isinstance(compiled["t1"], PDA)

    def test_python_processor_compile_invalid_pruning_raises_error(self):
        processor = PythonProcessor()
        code = """
$&Assign(?x, ?y)
$# t1
?x = ?y
"""
        subpatterns = parse_subpattern_from_string(code, Languages.PYTHON)
        subpattern = subpatterns[0]

        # Incompatible context where stmt cannot be pruned
        class FakeIncompatibleContext(RuleContext):
            pass

        bad_ctx = SubPatternCallContext(ast_ctx=FakeIncompatibleContext())
        with pytest.raises(Exception, match="Cannot prune"):
            processor.compile_subpattern(subpattern, bad_ctx)


class TestCatalogContextKeyedCaching:
    """Requirement 3, 4 & 5: Subpattern catalog caching keyed by (name, context_type)."""

    def test_catalog_maintains_cache_indexed_by_name_and_context_type(self):
        catalog = SubPatternCatalog()
        processor = PythonProcessor()

        code = """
$|Incr(?x)
$# t1
?x += 1
"""
        subpattern = catalog.load_string(code, Languages.PYTHON)[0]

        sample_code = "x += 1\n"
        tree = processor.generate_tree_from_code(sample_code)
        stmt_ctx = next(c for c in tree.getChildren() if isinstance(c, Python3Parser.StmtContext))

        call_ctx = SubPatternCallContext(ast_ctx=stmt_ctx)
        compiled = catalog.get_or_compile("Incr", call_ctx, processor)

        assert isinstance(compiled, dict)
        assert "t1" in compiled
        assert isinstance(compiled["t1"], PDA)

        # Cache key should be (name, context_type)
        expected_key = ("Incr", Python3Parser.StmtContext)
        assert expected_key in catalog._compiled_cache
        assert catalog._compiled_cache[expected_key] is compiled

    def test_repeated_requests_in_same_context_type_reuse_cache_without_recompilation(self):
        catalog = SubPatternCatalog()
        processor = PythonProcessor()

        code = """
$|Incr(?x)
$# t1
?x += 1
"""
        catalog.load_string(code, Languages.PYTHON)

        sample_tree = processor.generate_tree_from_code("x += 1\n")
        stmt_ctx1 = next(c for c in sample_tree.getChildren() if isinstance(c, Python3Parser.StmtContext))

        sample_tree2 = processor.generate_tree_from_code("y += 2\n")
        stmt_ctx2 = next(c for c in sample_tree2.getChildren() if isinstance(c, Python3Parser.StmtContext))

        ctx1 = SubPatternCallContext(ast_ctx=stmt_ctx1)
        ctx2 = SubPatternCallContext(ast_ctx=stmt_ctx2)

        # Mock compile_subpattern on processor to verify single call
        mock_compile = MagicMock(wraps=processor.compile_subpattern)
        processor.compile_subpattern = mock_compile

        res1 = catalog.get_or_compile("Incr", ctx1, processor)
        assert mock_compile.call_count == 1

        # Second call with same context type (StmtContext)
        res2 = catalog.get_or_compile("Incr", ctx2, processor)
        # Should NOT have compiled again!
        assert mock_compile.call_count == 1
        assert res1 is res2

    def test_invocations_in_different_context_types_generate_independent_cached_entries(self):
        catalog = SubPatternCatalog()
        processor = PythonProcessor()

        code = """
$|AddOne(?x)
$# t1
?x + 1
"""
        catalog.load_string(code, Languages.PYTHON)

        sample_tree = processor.generate_tree_from_code("x + 1\n")
        stmt_ctx = next(c for c in sample_tree.getChildren() if isinstance(c, Python3Parser.StmtContext))

        def find_node(node, cls):
            if isinstance(node, cls):
                return node
            if hasattr(node, 'getChildren'):
                for child in node.getChildren():
                    res = find_node(child, cls)
                    if res is not None:
                        return res
            return None

        expr_ctx = find_node(sample_tree, Python3Parser.ExprContext)
        assert expr_ctx is not None

        ctx_stmt = SubPatternCallContext(ast_ctx=stmt_ctx)
        ctx_expr = SubPatternCallContext(ast_ctx=expr_ctx)

        mock_compile = MagicMock(wraps=processor.compile_subpattern)
        processor.compile_subpattern = mock_compile

        res_stmt = catalog.get_or_compile("AddOne", ctx_stmt, processor)
        assert mock_compile.call_count == 1

        res_expr = catalog.get_or_compile("AddOne", ctx_expr, processor)
        assert mock_compile.call_count == 2

        # Both context types cached independently
        key_stmt = ("AddOne", Python3Parser.StmtContext)
        key_expr = ("AddOne", Python3Parser.ExprContext)

        assert key_stmt in catalog._compiled_cache
        assert key_expr in catalog._compiled_cache
        assert catalog._compiled_cache[key_stmt] is res_stmt
        assert catalog._compiled_cache[key_expr] is res_expr
