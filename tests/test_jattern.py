import importlib.resources as pkg_resources
import os
from unittest import skipIf

import pytest
from loguru import logger

from pyttern import match_files, match_wildcards
from pyttern.simulator.pda.PDA import PDAEncoder, PDA
from pyttern.simulator.pda.transition import Transition, NavigationAlphabet, NodeTransition
from . import tests_files_jattern


def get_test_file(path):
    return str(pkg_resources.files(tests_files_jattern) / path)

class TestTrivial():
    @pytest.mark.timeout(1)
    def test_simple(self):
        pattern_path = get_test_file("trivial_case/simple/trivial_case.jat")
        code_path = get_test_file("trivial_case/simple/trivial_case.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det
    
    @pytest.mark.timeout(1)
    def test_hello_world(self):
        pattern_path = get_test_file("trivial_case/func_call/hello_world.jat")
        code_path = get_test_file("trivial_case/func_call/hello_world.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

        code_path = get_test_file("trivial_case/func_call/hello_world_sandwich.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

    @pytest.mark.timeout(1)

    def test_array(self):
        pattern_path = get_test_file("trivial_case/arrays/array.jat")
        code_path = get_test_file("trivial_case/arrays/array_ok.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

        code_path = get_test_file("trivial_case/arrays/array_ko.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert not res, det

class TestRelaxed():
    @pytest.mark.timeout(1)
    def test_assert(self):
        pattern_path = get_test_file("trivial_case/non_strict_mode/relaxed.jat")
        code_path = get_test_file("trivial_case/non_strict_mode/relaxed_ok.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

        pattern_path = get_test_file("trivial_case/non_strict_mode/relaxed.jat")
        code_path = get_test_file("trivial_case/non_strict_mode/relaxed_ko.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert not res, det