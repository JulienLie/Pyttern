from pathlib import Path
from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_complex_and_subpattern():
    subpattern_file = Path(__file__).parent / "resource.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1
    assert ret[0].name == "ResourceFlow"
    assert ret[0].type == "AND"
    assert len(ret[0].transformations) == 3

    pattern_path = Path(__file__).parent / "resource.pyt"

    code_ok = Path(__file__).parent / "resource_ok.py"
    res, det = match_files(pattern_path, code_ok, match_details=True)
    assert res, det

    code_ko = Path(__file__).parent / "resource_missing_close_ko.py"
    res, det = match_files(pattern_path, code_ko, match_details=True)
    assert not res, det
