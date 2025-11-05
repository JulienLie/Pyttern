from pathlib import Path

import pytest

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file


@pytest.mark.timeout(1)
def test_unbound_assign():
    macro_path = Path(__file__).parent / "unbound.myt"
    parse_macro_from_file(str(macro_path), language=Languages.PYTHON, override=True)

    pattern_path = Path(__file__).parent / "unbound.pyt"
    code_path = Path(__file__).parent / "unbound_assign_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    assert len(det.matches) == 1, f"Expected 1 match, got {len(det.matches)}"
    match = det.matches[0]
    bindings = match.bindings
    assert "x" in bindings, "Expected binding for 'x'"
    binding_x = bindings["x"]
    assert binding_x.__class__.__name__ == "NameContext", f"Expected binding type 'NameContext', got {binding_x.__class__.__name__}"
    assert binding_x.getText() == "a", f"Expected binding text 'a', got {binding_x.getText()}"

    code_path = Path(__file__).parent / "unbound_use_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    assert len(det.matches) == 1, f"Expected 1 match, got {len(det.matches)}"
    match = det.matches[0]
    bindings = match.bindings
    assert "x" in bindings, "Expected binding for 'x'"
    binding_x = bindings["x"]
    assert binding_x.__class__.__name__ == "TerminalNodeImpl", (f"Expected binding type 'TerminalNodeImpl', "
                                                        f"got {binding_x.__class__.__name__}")
    assert binding_x.getText() == "10", f"Expected binding text '10', got {binding_x.getText()}"

    code_path = Path(__file__).parent / "unbound_call_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    assert len(det.matches) == 1, f"Expected 1 match, got {len(det.matches)}"
    match = det.matches[0]
    bindings = match.bindings
    assert "x" in bindings, "Expected binding for 'x'"
    binding_x = bindings["x"]
    assert binding_x.__class__.__name__ == "NameContext", f"Expected binding type 'NameContext', got {binding_x.__class__.__name__}"
    assert binding_x.getText() == "print", f"Expected binding text 'print', got {binding_x.getText()}"