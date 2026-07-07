from pathlib import Path

import pytest

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


@pytest.mark.timeout(1)
def test_incr_subpattern():
    #logger.enable("pyttern")

    subpattern_file = Path(__file__).parent / "body_subpattern.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    assert ret[0].name == "Body", f"Expected subpattern name 'Body', got {ret[0].name}"
    assert len(ret[0].transformations) == 2, f"Expected 2 transformations, got {len(ret[0].transformations)}"

    code_path = Path(__file__).parent / "body_subpattern_ok1.py"
    pattern_path = Path(__file__).parent / "body_subpattern.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "body_subpattern_ok2.py"
    pattern_path = Path(__file__).parent / "body_subpattern.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det
