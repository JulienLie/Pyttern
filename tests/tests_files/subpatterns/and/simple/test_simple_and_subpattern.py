from pathlib import Path

import pytest

from pyttern import match_files, SubPatternCatalog
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


@pytest.fixture
def catalog():
    cat = SubPatternCatalog()
    subpattern_file = Path(__file__).parent / "assign.myt"
    cat.load_file(subpattern_file)
    return cat


def test_assign_a_b_ok(catalog):
    subpattern_file = Path(__file__).parent / "assign.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "Assign", f"Expected subpattern name 'Assign', got {subpattern.name}"
    assert len(subpattern.transformations) == 2, f"Expected 2 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    code_path = Path(__file__).parent / "assign_a_b_ok.py"
    pattern_path = Path(__file__).parent / "assign.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det


def test_assign_b_a_ok(catalog):
    subpattern_file = Path(__file__).parent / "assign.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "Assign", f"Expected subpattern name 'Assign', got {subpattern.name}"
    assert len(subpattern.transformations) == 2, f"Expected 2 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    code_path = Path(__file__).parent / "assign_b_a_ok.py"
    pattern_path = Path(__file__).parent / "assign.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det


def test_assign_nok(catalog):
    subpattern_file = Path(__file__).parent / "assign.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 subpattern, got {len(ret)}"
    subpattern = ret[0]
    assert subpattern.name == "Assign", f"Expected subpattern name 'Assign', got {subpattern.name}"
    assert len(subpattern.transformations) == 2, f"Expected 2 transformations, got {len(subpattern.transformations)}"
    assert subpattern.type == "AND", f"Expected subpattern type 'AND', got {subpattern.type}"

    code_path = Path(__file__).parent / "assign_nok.py"
    pattern_path = Path(__file__).parent / "assign.pyt"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res, det
