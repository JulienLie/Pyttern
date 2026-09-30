from pathlib import Path
from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_complex_or_subpattern():
    subpattern_file = Path(__file__).parent / "error_handling.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1
    assert ret[0].name == "ErrorHandling"
    assert ret[0].type == "OR"
    assert len(ret[0].transformations) == 4

    pattern_path = Path(__file__).parent / "error_handling.pyt"

    res, det = match_files(pattern_path, Path(__file__).parent / "try_except_ok.py", match_details=True)
    assert res, det

    res, det = match_files(pattern_path, Path(__file__).parent / "guard_if_ok.py", match_details=True)
    assert res, det

    res, det = match_files(pattern_path, Path(__file__).parent / "no_error_handling_ko.py", match_details=True)
    assert not res, det
