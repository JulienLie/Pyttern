from pathlib import Path

import pytest
from loguru import logger

from pyttern.language_processors import LudiiProcessor

logger.enable("pyttern")

@pytest.mark.parametrize("ludii_file", sorted((Path(__file__).parent / "tests_files" / "parse").glob("*.lud")))
def test_parse(ludii_file):
    with open(ludii_file) as file:
        code = file.read()
    processor = LudiiProcessor()
    tree = processor.generate_tree_from_code(code)

    assert tree is not None, f"Failed to parse {ludii_file}"

def test_simple():
    code = """
(define "NextCanNotMove"
    (not (can Move (do (forEach Piece Next) ifAfterwards:(not ("IsInCheck" "King" Next)))) )
)
"""
    processor = LudiiProcessor()
    tree = processor.generate_tree_from_code(code)

    assert tree is not None, "Failed to parse simple code"