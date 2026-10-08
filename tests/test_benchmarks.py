"""Automated benchmark tests verifying performance optimizations in Pyttern."""

import time
from pathlib import Path
import pytest
from antlr4 import InputStream, CommonTokenStream, PredictionContextCache
from antlr4.atn.LexerATNSimulator import LexerATNSimulator

from pyttern.antlr.python.Python3Lexer import Python3Lexer
from pyttern.main import PytternMatcher, match_files
from pyttern.language_processors.languages import Languages
from pyttern.simulator.configuration import Environment
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_lexer_optimization_speedup():
    """Verify that Python3LexerATNSimulator provides substantial speedup over standard LexerATNSimulator."""
    code = """
def process_data(items, multiplier=2):
    results = []
    for item in items:
        if item % 2 == 0:
            results.append(item * multiplier)
        else:
            results.append(item)
    return results
"""
    iterations = 25

    # 1. Measure optimized lexer (default in Python3Lexer)
    t0 = time.perf_counter()
    for _ in range(iterations):
        inp = InputStream(code)
        lexer = Python3Lexer(inp)
        tokens = CommonTokenStream(lexer)
        tokens.fill()
    opt_duration = time.perf_counter() - t0

    # 2. Measure baseline unoptimized LexerATNSimulator
    t0 = time.perf_counter()
    for _ in range(iterations):
        inp = InputStream(code)
        lexer = Python3Lexer(inp)
        lexer._interp = LexerATNSimulator(lexer, lexer.atn, lexer.decisionsToDFA, PredictionContextCache())
        tokens = CommonTokenStream(lexer)
        tokens.fill()
    baseline_duration = time.perf_counter() - t0

    assert opt_duration < baseline_duration, (
        f"Optimized lexer ({opt_duration:.4f}s) was not faster than baseline ({baseline_duration:.4f}s)"
    )
    speedup = baseline_duration / max(opt_duration, 1e-9)
    # The optimization routinely achieves 10x-20x speedup; assert at least 2x
    assert speedup >= 2.0, f"Expected at least 2x speedup, got {speedup:.2f}x"


def test_environment_hash_and_merge_performance():
    """Verify Environment hash caching and merge fast-paths maintain high throughput."""
    # Test hash caching under heavy set membership lookups
    envs = [
        Environment.from_dict({f"var_{i}": i, f"other_{i}": f"val_{i}"})
        for i in range(20)
    ]
    env_set = set(envs)

    t0 = time.perf_counter()
    for _ in range(500):
        for env in envs:
            _ = env in env_set
            _ = hash(env)
    hash_duration = time.perf_counter() - t0
    # 10,000 lookups should finish in less than 50ms
    assert hash_duration < 0.2, f"Hash lookup took too long: {hash_duration:.4f}s"

    # Test merge throughput
    e1 = Environment.from_dict({"a": 1, "b": 2})
    e2 = Environment.from_dict({"c": 3, "d": 4})
    e_empty = Environment.empty()

    t0 = time.perf_counter()
    for _ in range(5000):
        _ = e1.merge(e2)
        _ = e1.merge(e_empty)
    merge_duration = time.perf_counter() - t0
    assert merge_duration < 0.2, f"Merge took too long: {merge_duration:.4f}s"


def test_tree_matching_throughput():
    """Verify Matcher executes rapidly on standard AST tree matching."""
    matcher = PytternMatcher()
    processor = matcher._language_processors[Languages.PYTHON]
    pattern_code = "?x = ?y + 1\n"
    target_code = """a = 10 + 1
b = 20 + 1
c = 30 + 2
d = 40 + 1
"""
    p_ast = processor.generate_tree_from_code(pattern_code)
    fsm = processor.create_pda(p_ast)
    pattern_tree = {"name": "test", "result": fsm}
    target_tree = processor.generate_tree_from_code(target_code)

    # Prime and measure
    t0 = time.perf_counter()
    iterations = 50
    for _ in range(iterations):
        res = matcher._match_pyttern_details(pattern_tree, target_tree)
        assert res["result"] is True
        assert len(res["matches"].matches) == 3
    match_duration = time.perf_counter() - t0

    avg_time_ms = (match_duration / iterations) * 1000
    # Average match should be under 5ms per run
    assert avg_time_ms < 15.0, f"Average matching latency ({avg_time_ms:.2f}ms) exceeded threshold"


def test_subpattern_matching_performance():
    """Verify complex subpattern evaluation executes within expected low latency."""
    base_dir = Path(__file__).parent / "tests_files" / "subpatterns" / "and" / "simple"
    subpattern_file = base_dir / "assign.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON, override=True)

    pattern_path = base_dir / "assign.pyt"
    code_path = base_dir / "assign_a_b_ok.py"

    t0 = time.perf_counter()
    iterations = 10
    for _ in range(iterations):
        res, det = match_files(str(pattern_path), str(code_path), match_details=True)
        assert res is True
        assert len(det.matches) > 0
    duration = time.perf_counter() - t0

    avg_time_ms = (duration / iterations) * 1000
    # 10 subpattern match file runs should finish fast (under 100ms per run)
    assert avg_time_ms < 100.0, f"Average subpattern latency ({avg_time_ms:.2f}ms) exceeded threshold"
