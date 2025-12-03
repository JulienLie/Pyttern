from pathlib import Path

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file


def test_subcall_macro_parse():
    incr_macro_file = Path(__file__).parent / "incr.myt"
    ret = parse_macro_from_file(str(incr_macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    assert ret[0].name == "Incr", f"Expected macro name 'Incr', got {ret[0].name}"
    assert len(ret[0].transformations) == 3, f"Expected 3 transformations, got {len(ret[0].transformations)}"

    comp_macro_file = Path(__file__).parent / "comp.myt"
    ret = parse_macro_from_file(str(comp_macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    assert ret[0].name == "Comp", f"Expected macro name 'Comp', got {ret[0].name}"
    assert len(ret[0].transformations) == 6, f"Expected 6 transformations, got {len(ret[0].transformations)}"

    macro_file = Path(__file__).parent / "loop.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    assert ret[0].name == "Loop", f"Expected macro name 'Incr', got {ret[0].name}"
    assert len(ret[0].transformations) == 2, f"Expected 2 transformations, got {len(ret[0].transformations)}"

def test_subcall_macro_for():
    #logger.enable("pyttern")

    incr_macro_file = Path(__file__).parent / "incr.myt"
    parse_macro_from_file(str(incr_macro_file), Languages.PYTHON)

    comp_macro_file = Path(__file__).parent / "comp.myt"
    parse_macro_from_file(str(comp_macro_file), Languages.PYTHON)

    macro_file = Path(__file__).parent / "loop.myt"
    parse_macro_from_file(str(macro_file), Languages.PYTHON)

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


def test_subcall_macro_while():
    #logger.enable("pyttern")

    incr_macro_file = Path(__file__).parent / "incr.myt"
    parse_macro_from_file(str(incr_macro_file), Languages.PYTHON)

    comp_macro_file = Path(__file__).parent / "comp.myt"
    parse_macro_from_file(str(comp_macro_file), Languages.PYTHON)

    macro_file = Path(__file__).parent / "loop.myt"
    parse_macro_from_file(str(macro_file), Languages.PYTHON)

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