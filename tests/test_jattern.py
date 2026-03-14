import importlib.resources as pkg_resources
import os
from unittest import skipIf

import pytest
from loguru import logger

from pyttern import match_files, match_wildcards
from pyttern.simulator.pda.PDA import PDAEncoder, PDA
from pyttern.simulator.pda.transition import Transition, NavigationAlphabet, NodeTransition
from . import tests_files_jattern

PATTERN_EXTENSION = ".jat"
PATTERN_NAME = "pattern" + PATTERN_EXTENSION

def get_test_file(path):
    return str(pkg_resources.files(tests_files_jattern) / path)

def discover_dir(directory):
    for root, _, files in os.walk(directory):
        if not PATTERN_NAME in files:
            continue
        
        pattern_path = get_test_file(os.path.join(root, PATTERN_NAME))

        for file in files:
            if PATTERN_EXTENSION not in file:
                if file.startswith("ok"):
                    yield (pattern_path, os.path.join(root, file), True)
                elif file.startswith("ko"):
                    yield (pattern_path, os.path.join(root, file), False)
                else:
                    raise f"File does not respect format (should be a pattern.jat or start with ko or ok) : {file}"

class TestJattern():
    @pytest.mark.parametrize("test_data", discover_dir(get_test_file("")))
    def test_statement_ok(self, test_data):
        pattern_path = get_test_file(test_data[0])
        code_path = get_test_file(test_data[1])

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")

        if test_data[2]:
            assert res, det
        else:
            assert not res, det