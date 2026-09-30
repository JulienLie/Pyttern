from pathlib import Path

import pytest

from pyttern import match_files
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file
from pyttern.subpattern.SubPattern import loaded_subpatterns


@pytest.fixture(autouse=True)
def clean_subpatterns():
    loaded_subpatterns.clear()
    yield
    loaded_subpatterns.clear()


def test_double_def_subpattern():
    subpattern_file = Path(__file__).parent / "double_def.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 2, f"Expected 2 subpatterns, got {len(ret)}"
    assert ret[0].name == "DoubleDef", f"Expected subpattern name 'DoubleDef', got {ret[0].name}"
    assert ret[0].type == "AND", f"Expected subpattern type 'AND', got {ret[0].type}"
    assert len(ret[0].transformations) == 2, f"Expected 2 transformations, got {len(ret[0].transformations)}"

    assert ret[1].name == "IntAndStr", f"Expected subpattern name 'IntAndStr', got {ret[1].name}"
    assert ret[1].type == "AND", f"Expected subpattern type 'AND', got {ret[1].type}"
    assert len(ret[1].transformations) == 2, f"Expected 2 transformations, got {len(ret[1].transformations)}"


def test_double_def_ok():
    subpattern_file = Path(__file__).parent / "double_def.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)

    pattern_path = Path(__file__).parent / "double_def.pyt"
    code_path = Path(__file__).parent / "double_def_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det


def test_double_def_nok():
    subpattern_file = Path(__file__).parent / "double_def.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)

    pattern_path = Path(__file__).parent / "double_def.pyt"
    code_path = Path(__file__).parent / "double_def_nok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det


def test_simple_def_nok():
    subpattern_file = Path(__file__).parent / "double_def.myt"
    parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)

    pattern_path = Path(__file__).parent / "double_def.pyt"
    code_path = Path(__file__).parent / "simple_def_nok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det
