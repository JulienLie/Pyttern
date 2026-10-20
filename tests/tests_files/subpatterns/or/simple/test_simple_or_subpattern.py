from pathlib import Path

from pyttern import match_files, SubPatternCatalog
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_incr_subpattern():
    subpattern_file = Path(__file__).parent / "incr.myt"
    catalog = SubPatternCatalog()
    catalog.load_file(subpattern_file)

    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    assert ret[0].name == "Incr", f"Expected subpattern name 'Incr', got {ret[0].name}"
    assert len(ret[0].transformations) == 3, f"Expected 3 transformations, got {len(ret[0].transformations)}"

    pattern_path = Path(__file__).parent / "increment.pyt"

    code_path = Path(__file__).parent / "increment_1_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det

    code_path = Path(__file__).parent / "increment_2_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det

    code_path = Path(__file__).parent / "increment_3_ok.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det
