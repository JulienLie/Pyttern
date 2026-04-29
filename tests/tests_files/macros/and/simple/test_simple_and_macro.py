from pathlib import Path

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


#@pytest.mark.timeout(1)
def test_incr_subpattern():
    #logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "assign.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "Assign", f"Expected subpattern name 'Assign', got {subpattern.name}"
    assert len(subpattern.transformations) == 2, f"Expected 2 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

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