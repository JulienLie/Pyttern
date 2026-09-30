from pathlib import Path
from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_or_calls_not_subpattern():
    subpattern_file = Path(__file__).parent / "or_calls_not.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 3

    pattern_path = Path(__file__).parent / "or_calls_not.pyt"

    res, det = match_files(pattern_path, Path(__file__).parent / "pure_ok.py", match_details=True)
    assert res, det

    res, det = match_files(pattern_path, Path(__file__).parent / "print_ko.py", match_details=True)
    assert not res, det
