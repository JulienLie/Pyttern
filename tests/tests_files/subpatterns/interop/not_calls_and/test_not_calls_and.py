from pathlib import Path
from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_not_calls_and_subpattern():
    subpattern_file = Path(__file__).parent / "not_calls_and.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 2

    pattern_path = Path(__file__).parent / "not_calls_and.pyt"

    res, det = match_files(pattern_path, Path(__file__).parent / "single_lock_ok.py", match_details=True)
    assert res, det

    res, det = match_files(pattern_path, Path(__file__).parent / "double_lock_ko.py", match_details=True)
    assert not res, det
