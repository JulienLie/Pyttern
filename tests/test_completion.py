import pytest
from pyttern.language_processors.python_processor import PythonProcessor

def test_completion_wildcards():
    code = "x = ?"
    # Cursor right after the '?'
    completions = PythonProcessor.get_completions(code, 0, 5)
    labels = [c["label"] for c in completions]
    
    assert "?" in labels
    assert "?name" in labels
    assert "?*" in labels
    assert "?:" in labels
    assert "?:*" in labels
    assert "?<...>" in labels
    assert "?$(...)" in labels
    assert "?{n, m}" in labels
    
    # Check that standard keywords or subpattern stuff are NOT in the list
    assert "def" not in labels
    assert "$#" not in labels

def test_completion_wildcards_partial():
    code = "x = ?n"
    # Cursor after '?n'
    completions = PythonProcessor.get_completions(code, 0, 6)
    labels = [c["label"] for c in completions]
    
    assert "?name" in labels
    assert "?" not in labels
    assert "?*" not in labels

def test_completion_subpatterns():
    code = "$"
    # Cursor after '$'
    completions = PythonProcessor.get_completions(code, 0, 1)
    labels = [c["label"] for c in completions]
    
    assert "$&Name(args)" in labels
    assert "$|Name(args)" in labels
    assert "$#" in labels
    assert "def" not in labels
    assert "?" not in labels

def test_completion_pyttern_api():
    code = "mat"
    # Cursor after 'mat'
    completions = PythonProcessor.get_completions(code, 0, 3)
    labels = [c["label"] for c in completions]
    
    assert "match_files" in labels
    assert "PytternMatcher" not in labels
    assert "def" not in labels

def test_completion_python_keywords():
    code = "de"
    # Cursor after 'de'
    completions = PythonProcessor.get_completions(code, 0, 2)
    labels = [c["label"] for c in completions]
    
    assert "def" in labels
    assert "del" in labels
    assert "class" not in labels

def test_completion_local_identifiers():
    code = (
        "def custom_my_function(x):\n"
        "    custom_var = 10\n"
        "    cust"
    )
    # Cursor after 'cust' on line 2 (0-indexed)
    completions = PythonProcessor.get_completions(code, 2, 8)
    labels = [c["label"] for c in completions]
    
    assert "custom_my_function" in labels
    assert "custom_var" in labels
    assert "def" not in labels

def test_completion_empty_prefix():
    code = "   "
    completions = PythonProcessor.get_completions(code, 0, 3)
    labels = [c["label"] for c in completions]
    
    # When no prefix, all candidates should be returned
    assert "def" in labels
    assert "match_files" in labels
    assert "?" in labels


def test_subpattern_completion_skeletons():
    from pyttern.subpattern import subpattern_parser

    # 1. Test when prefix is $&
    code = "$&"
    completions = subpattern_parser.get_completions(code, 0, 2)
    labels = [c["label"] for c in completions]
    assert len(labels) == 1
    assert labels[0] == "$&SubPatternName(?arg)\n\n$# TransformationName\n?arg"

    # 2. Test when prefix is $|
    code = "$|"
    completions = subpattern_parser.get_completions(code, 0, 2)
    labels = [c["label"] for c in completions]
    assert len(labels) == 1
    assert labels[0] == "$|SubPatternName(?arg)\n\n$# TransformationName\n?arg"

    # 3. Test when prefix is $!
    code = "$!"
    completions = subpattern_parser.get_completions(code, 0, 2)
    labels = [c["label"] for c in completions]
    assert len(labels) == 1
    assert labels[0] == "$!SubPatternName(?arg)\n\n$# TransformationName\n?arg"

    # 4. Test when prefix is $
    code = "$"
    completions = subpattern_parser.get_completions(code, 0, 1)
    labels = [c["label"] for c in completions]
    assert "$&SubPatternName(?arg)\n\n$# TransformationName\n?arg" in labels
    assert "$|SubPatternName(?arg)\n\n$# TransformationName\n?arg" in labels
    assert "$!SubPatternName(?arg)\n\n$# TransformationName\n?arg" in labels
    assert "$#" in labels

