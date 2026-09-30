from pathlib import Path


from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.language_processors.python_processor import PythonProcessor
from pyttern.main import configure_logger
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


def test_not_alone_not():
    configure_logger(2)

    subpattern_file = Path(__file__).parent / "no_return.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    assert ret[0].name == "NoReturn", f"Expected subpattern name 'NoReturn', got {ret[0].name}"
    assert len(ret[0].transformations) == 1, f"Expected 1 transformations, got {len(ret[0].transformations)}"
    
    code_path = Path(__file__).parent / "no_return_1_ok.py"
    pattern_path = Path(__file__).parent / "no_return.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "no_return_2_ok.py"
    pattern_path = Path(__file__).parent / "no_return.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "no_return_1_ko.py"
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det

    code_path = Path(__file__).parent / "no_return_2_ko.py"
    if code_path.exists():
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det