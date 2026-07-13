from pathlib import Path

import pytest

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


@pytest.mark.timeout(5)
def test_incr_subpattern():
    #logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "incr.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    assert ret[0].name == "Incr", f"Expected subpattern name 'Incr', got {ret[0].name}"
    assert len(ret[0].transformations) == 3, f"Expected 3 transformations, got {len(ret[0].transformations)}"

    code_path = Path(__file__).parent / "increment_1_ok.py"
    pattern_path = Path(__file__).parent / "increment.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "increment_2_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "increment_3_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det