from pathlib import Path

import pytest

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


@pytest.mark.timeout(1)
def test_loop_subpattern():
    subpattern_file = Path(__file__).parent / "loop.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    assert ret[0].name == "Loop", f"Expected subpattern name 'Incr', got {ret[0].name}"
    assert len(ret[0].transformations) == 2, f"Expected 2 transformations, got {len(ret[0].transformations)}"

@pytest.mark.timeout(1)
def test_loop_subpattern_for():
    #logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "loop.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)

    code_path = Path(__file__).parent / "for_loop.py"
    pattern_path = Path(__file__).parent / "loop.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    assert len(det.matches) == 1, f"Expected 1 match, got {len(det.matches)}"
    match = det.matches[0]
    bindings = match.bindings
    assert "i" in bindings, f"Expected binding for 'i', found bindings for {bindings.keys()}"
    binding_i = bindings["i"]
    assert binding_i.__class__.__name__ == "NameContext", f"Expected binding type 'NameContext', got {binding_i.__class__.__name__}"
    assert binding_i.getText() == "i", f"Expected binding text 'i', got {binding_i.getText()}"

    assert "v" in bindings, f"Expected binding for 'v', found bindings for {bindings.keys()}"
    binding_v = bindings["v"]
    assert binding_v.__class__.__name__ == "TerminalNodeImpl", (f"Expected binding type 'TerminalNodeImpl', "
                                                           f"got {binding_v.__class__.__name__}")
    assert binding_v.getText() == "10", f"Expected binding text '10', got {binding_v.getText()}"


@pytest.mark.timeout(1)
def test_loop_subpattern_while():
    #logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "loop.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)

    code_path = Path(__file__).parent / "while_loop.py"
    pattern_path = Path(__file__).parent / "loop.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    assert len(det.matches) == 1, f"Expected 1 match, got {len(det.matches)}"
    match = det.matches[0]
    bindings = match.bindings
    assert "i" in bindings, f"Expected binding for 'i', found bindings for {bindings.keys()}"
    binding_i = bindings["i"]
    assert binding_i.__class__.__name__ == "NameContext", f"Expected binding type 'NameContext', got {binding_i.__class__.__name__}"
    assert binding_i.getText() == "i", f"Expected binding text 'i', got {binding_i.getText()}"

    assert "v" in bindings, f"Expected binding for 'v', found bindings for {bindings.keys()}"
    binding_v = bindings["v"]
    assert binding_v.__class__.__name__ == "TerminalNodeImpl", (f"Expected binding type 'TerminalNodeImpl', "
                                                                f"got {binding_v.__class__.__name__}")
    assert binding_v.getText() == "10", f"Expected binding text '10', got {binding_v.getText()}"