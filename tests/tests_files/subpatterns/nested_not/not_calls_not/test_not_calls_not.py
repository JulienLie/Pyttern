from pathlib import Path
from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_not_calls_not_subpattern():
    subpattern_file = Path(__file__).parent / "not_calls_not.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 2
    assert ret[0].name == "NotHasUnsafe"
    assert ret[0].type == "NOT"
    assert ret[1].name == "HasUnsafe"
    assert ret[1].type == "NOT"

    pattern_path = Path(__file__).parent / "not_calls_not.pyt"

    res, det = match_files(pattern_path, Path(__file__).parent / "unsafe_exec_ok.py", match_details=True)
    assert res, det

    res, det = match_files(pattern_path, Path(__file__).parent / "safe_no_exec_ko.py", match_details=True)
    assert not res, det
