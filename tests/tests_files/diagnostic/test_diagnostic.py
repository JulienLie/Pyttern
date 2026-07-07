from pathlib import Path

import pytest

from pyttern.language_processors.python_processor import PythonProcessor

BASE = Path(__file__).parent


@pytest.mark.timeout(1)
def test_diagnostic_ok():
    with open(BASE / "code_ok.pyt") as file:
        res = PythonProcessor.parse_diagnostics(file.read())
        assert res == []

def read_file(file):
    with open(BASE / file) as file:
        return file.read()

def test_contains_ko():
    content = read_file("contains_ko.pyt")

    res = PythonProcessor.parse_diagnostics(content)
    assert len(res) > 0