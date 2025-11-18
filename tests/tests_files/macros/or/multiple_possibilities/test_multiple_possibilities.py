from pathlib import Path

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file


def test_incr_macro():
    #logger.enable("pyttern")

    macro_file = Path(__file__).parent / "macro.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    assert ret[0].name == "Assign", f"Expected macro name 'Assign', got {ret[0].name}"
    assert len(ret[0].transformations) == 1, f"Expected 1 transformations, got {len(ret[0].transformations)}"

    code_path = Path(__file__).parent / "assign.py"
    pattern_path = Path(__file__).parent / "pattern.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    assert len(det.matches) == 3, f"Expected 3 match, got {len(det.matches)}"
    match = det.matches[0]
    bindings = match.bindings
    assert "x" in bindings, "Expected binding for 'x'"
    binding_x = bindings["x"]
    assert binding_x.__class__.__name__ == "NameContext", f"Expected binding type 'NameContext', got {binding_x.__class__.__name__}"
    assert binding_x.getText() == "c", f"Expected binding text 'c', got {binding_x.getText()}"

    match = det.matches[1]
    bindings = match.bindings
    assert "x" in bindings, "Expected binding for 'x'"
    binding_x = bindings["x"]
    assert binding_x.__class__.__name__ == "NameContext", f"Expected binding type 'NameContext', got {binding_x.__class__.__name__}"
    assert binding_x.getText() == "b", f"Expected binding text 'b', got {binding_x.getText()}"

    match = det.matches[2]
    bindings = match.bindings
    assert "x" in bindings, "Expected binding for 'x'"
    binding_x = bindings["x"]
    assert binding_x.__class__.__name__ == "NameContext", f"Expected binding type 'NameContext', got {binding_x.__class__.__name__}"
    assert binding_x.getText() == "a", f"Expected binding text 'a', got {binding_x.getText()}"