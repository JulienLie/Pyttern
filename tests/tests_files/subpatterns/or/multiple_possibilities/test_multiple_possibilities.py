from pathlib import Path

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_incr_subpattern():
    #logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "subpattern.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    assert ret[0].name == "Assign", f"Expected subpattern name 'Assign', got {ret[0].name}"
    assert len(ret[0].transformations) == 1, f"Expected 1 transformations, got {len(ret[0].transformations)}"

    code_path = Path(__file__).parent / "assign.py"
    pattern_path = Path(__file__).parent / "pattern.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True, stop_at_first=False)
    assert res, det

    assert len(det.matches) == 3, f"Expected 3 matches, got {len(det.matches)}"

    expected_vars = {"a", "b", "c"}
    bound_vars = set()
    for match in det.matches:
        bindings = match.bindings
        assert "x" in bindings, "Expected binding for 'x'"
        binding_x = bindings["x"]
        assert binding_x.__class__.__name__ == "NameContext", f"Expected binding type 'NameContext', got {binding_x.__class__.__name__}"
        bound_vars.add(binding_x.getText())

    assert len(bound_vars) == 3, f"Expected 3 distinct variable bindings, got {bound_vars}"
    for var in expected_vars:
        assert var in bound_vars, f"Expected variable '{var}' to appear in results, got {bound_vars}"
    assert bound_vars == expected_vars