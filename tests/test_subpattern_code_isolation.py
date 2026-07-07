
import pytest
from pathlib import Path
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file, parse_subpattern_from_string
from pyttern.subpattern.SubPattern import BaseSubPattern

def test_subpattern_code_isolation():
    """Test that multiple subpatterns in the same file have isolated code attributes."""
    # Using loop.myt which has 3 subpatterns: Incr, Comp, Loop
    subpattern_file = Path("tests/tests_files/subpatterns/or/multiple_define/loop.myt")
    subpatterns = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    
    assert len(subpatterns) == 3
    for sp in subpatterns:
        assert isinstance(sp, BaseSubPattern)
    
    names = [s.name for s in subpatterns]
    assert "Incr" in names
    assert "Comp" in names
    assert "Loop" in names
    
    incr = next(s for s in subpatterns if s.name == "Incr")
    comp = next(s for s in subpatterns if s.name == "Comp")
    loop = next(s for s in subpatterns if s.name == "Loop")
    
    # Check Incr isolation
    assert "$|Incr" in incr.code
    assert "$|Comp" not in incr.code
    assert "$|Loop" not in incr.code
    
    # Check Comp isolation
    assert "$|Comp" in comp.code
    assert "$|Incr" not in comp.code
    assert "$|Loop" not in comp.code
    
    # Check Loop isolation
    assert "$|Loop" in loop.code
    assert "$|Incr" not in loop.code
    assert "$|Comp" not in loop.code

def test_subpattern_code_integrity():
    """Test that the retrieved code is exactly what was in the source for that subpattern."""
    code = """$|Subpattern1(?x)
$# t1
?x = 1

$|Subpattern2(?y)
$# t1
?y = 2
"""
    subpatterns = parse_subpattern_from_string(code, Languages.PYTHON)
    assert len(subpatterns) == 2
    
    m1 = next(s for s in subpatterns if s.name == "Subpattern1")
    m2 = next(s for s in subpatterns if s.name == "Subpattern2")
    
    # The code should include its own header and transformations
    assert m1.code.strip() == "$|Subpattern1(?x)\n$# t1\n?x = 1"
    assert m2.code.strip() == "$|Subpattern2(?y)\n$# t1\n?y = 2"
    
    # Ensure they don't leak into each other
    assert "Subpattern2" not in m1.code
    assert "Subpattern1" not in m2.code

def test_subpattern_with_comments_isolation():
    """Test that comments are preserved and isolated within their respective subpatterns."""
    code = """$|M1()
$# t1
?x = 1 # Comment 1

# Inter-subpattern comment

$|M2()
$# t1
?x = 2 # Comment 2
"""
    subpatterns = parse_subpattern_from_string(code, Languages.PYTHON)
    m1 = next(s for s in subpatterns if s.name == "M1")
    m2 = next(s for s in subpatterns if s.name == "M2")
    
    assert "# Comment 1" in m1.code
    assert "# Comment 2" in m2.code
    
    # Isolation check: M1 should not contain M2 header
    assert "$|M2" not in m1.code
    # M2 should not contain M1 header
    assert "$|M1" not in m2.code
    
    # We don't strictly care if the inter-subpattern comment is captured by M1 as long as it doesn't leak M2
    # But it's better if it's NOT in M2.
    assert "# Inter-subpattern comment" not in m2.code

if __name__ == "__main__":
    pytest.main([__file__])
