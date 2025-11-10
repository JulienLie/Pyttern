from pathlib import Path

import pytest

from pyttern import match_files


@pytest.mark.timeout(1)
@pytest.mark.parametrize("code_path", sorted(Path(__file__).parent.glob("*.py")))
def test_multiple_params_simple(code_path):
    pattern_path = Path(__file__).parent / "simple_params.pyt"
    code_path = Path(__file__).parent / code_path

    if code_path.resolve() == Path(__file__).resolve():
        pytest.skip("Ignorer le fichier de test lui-même")

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

def test_multiple_params_complex():
    pattern_path = Path(__file__).parent / "multiple_params.pyt"
    code_path = Path(__file__).parent / "multiple_params_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = Path(__file__).parent / "multiple_params_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert not res, det