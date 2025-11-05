from pathlib import Path

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.macro.macro_parser import parse_macro_from_file


#@pytest.mark.timeout(1)
def test_incr_macro():
    #logger.enable("pyttern")

    macro_file = Path(__file__).parent / "assign.myt"
    ret = parse_macro_from_file(str(macro_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    macro = ret[0]
    assert macro.name == "Assign", f"Expected macro name 'Assign', got {macro.name}"
    assert len(macro.transformations) == 2, f"Expected 2 transformations, got {len(macro.transformations)}"
    assert macro.type == "AND", f"Expected macro type 'AND', got {macro.type}"

    code_path = Path(__file__).parent / "assign_a_b_ok.py"
    pattern_path = Path(__file__).parent / "assign.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "assign_b_a_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "assign_nok.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det