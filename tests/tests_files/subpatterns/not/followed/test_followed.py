from pathlib import Path

from pyttern import match_files, SubPatternCatalog
from pyttern.language_processors.languages import Languages
from pyttern.main import configure_logger
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_not_subpattern():
    configure_logger(1)

    subpattern_file = Path(__file__).parent / "followed.myt"
    catalog = SubPatternCatalog()
    catalog.load_file(subpattern_file)

    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 2, f"Expected 2 subpattern, got {len(ret)}"
    
    code_path = Path(__file__).parent / "followed_ok.py"
    pattern_path = Path(__file__).parent / "followed.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det

    code_path = Path(__file__).parent / "followed_ko_1.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res, det

    code_path = Path(__file__).parent / "followed_ko_2.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res, det

    code_path = Path(__file__).parent / "followed_ko_3.py"
    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res, det
