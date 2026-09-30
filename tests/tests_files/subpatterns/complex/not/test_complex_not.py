from pathlib import Path
from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_complex_not_subpattern():
    subpattern_file = Path(__file__).parent / "unsafe.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1
    assert ret[0].name == "NoUnsafeCall"
    assert ret[0].type == "NOT"

    pattern_path = Path(__file__).parent / "unsafe.pyt"

    res, det = match_files(pattern_path, Path(__file__).parent / "safe_ok.py", match_details=True)
    assert res, det

    res, det = match_files(pattern_path, Path(__file__).parent / "unsafe_eval_ko.py", match_details=True)
    assert not res, det
