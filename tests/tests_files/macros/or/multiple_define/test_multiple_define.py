from pathlib import Path

from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file


def test_subcall_macro_parse():
    macro_file = Path(__file__).parent / "loop.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 3, f"Expected 1 macro, got {len(ret)}"

    assert ret[0].name == "Incr", f"Expected macro name 'Incr', got {ret[0].name}"
    assert len(ret[0].transformations) == 3, f"Expected 3 transformations, got {len(ret[0].transformations)}"

    assert ret[1].name == "Comp", f"Expected macro name 'Comp', got {ret[1].name}"
    assert len(ret[1].transformations) == 6, f"Expected 6 transformations, got {len(ret[1].transformations)}"

    assert ret[2].name == "Loop", f"Expected macro name 'loop', got {ret[2].name}"
    assert len(ret[2].transformations) == 2, f"Expected 2 transformations, got {len(ret[2].transformations)}"