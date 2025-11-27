from pathlib import Path

import pytest

from pyttern import match_files

BASE = Path(__file__).parent


@pytest.mark.timeout(1)
@pytest.mark.parametrize("file_1_path", sorted(BASE.glob("*.py")))
@pytest.mark.parametrize("file_2_path", sorted(BASE.glob("*.py")))
def test_ast_equal_match(file_1_path, file_2_path):
    res, det = match_files(file_1_path, file_2_path, match_details=True)
    if file_1_path == file_2_path:
        assert res, f"Expected match for {file_1_path} and {file_2_path}: {det}"
    else:
        assert not res, f"Expected no match for {file_1_path} and {file_2_path}: {det}"
