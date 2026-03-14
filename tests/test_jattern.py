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

def discover_files(directory, extension=None, file_prefix=""):
    for root, _, files in os.walk(directory):
        for file in files:
            if ".pyc" not in file and file.startswith(file_prefix):
                if extension is None or file.endswith(extension):
                    yield os.path.join(root, file)

class TestTrivial():
    def test_simple(self):
        pattern_path = get_test_file("trivial_case/simple/trivial_case.jat")
        code_path = get_test_file("trivial_case/simple/trivial_case.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det
    
    def test_hello_world(self):
        pattern_path = get_test_file("trivial_case/func_call/hello_world.jat")
        code_path = get_test_file("trivial_case/func_call/hello_world.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

        code_path = get_test_file("trivial_case/func_call/hello_world_sandwich.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

    def test_array(self):
        pattern_path = get_test_file("trivial_case/arrays/array.jat")
        code_path = get_test_file("trivial_case/arrays/array_ok.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

        code_path = get_test_file("trivial_case/arrays/array_ko.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert not res, det

    @pytest.mark.parametrize("file_path", discover_files(get_test_file("trivial_case/sandwich/"), ".java"))
    def test_not_strict(self, file_path):
        pattern_path = get_test_file("trivial_case/sandwich/pattern.jat")
        code_path = get_test_file(file_path)

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

class TestRelaxed():
    @pytest.mark.parametrize("file_path", discover_files(get_test_file("trivial_case/non_strict_mode/"), ".java", "relaxed_ok"))
    def test_ok(self, file_path):
        pattern_path = get_test_file("trivial_case/non_strict_mode/relaxed.jat")
        code_path = get_test_file(file_path)

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

    @pytest.mark.parametrize("file_path", discover_files(get_test_file("trivial_case/non_strict_mode/"), ".java", "relaxed_ko"))
    def test_ko(self, file_path):
        pattern_path = get_test_file("trivial_case/non_strict_mode/relaxed.jat")
        code_path = get_test_file(file_path)

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert not res, det

class TestSimpleWildcard():
    @pytest.mark.parametrize("file_path", discover_files(get_test_file("simple_wildcard/statement/"), ".java", "statement_ok"))
    def test_statement_ok(self, file_path):
        pattern_path = get_test_file("simple_wildcard/statement/pattern.jat")
        code_path = get_test_file(file_path)

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

    @pytest.mark.parametrize("file_path", discover_files(get_test_file("simple_wildcard/classname/"), ".java", "statement_ko"))
    def test_statement_ko(self, file_path):
        pattern_path = get_test_file("simple_wildcard/classname/pattern.jat")
        code_path = get_test_file(file_path)

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert not res, det
    
    @pytest.mark.parametrize("file_path", discover_files(get_test_file("simple_wildcard/classname/"), ".java", "class_ok"))
    def test_class_ok(self, file_path):
        pattern_path = get_test_file("simple_wildcard/classname/pattern.jat")
        code_path = get_test_file(file_path)

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

    @pytest.mark.parametrize("file_path", discover_files(get_test_file("simple_wildcard/classname/"), ".java", "class_ko"))
    def test_class_ko(self, file_path):
        pattern_path = get_test_file("simple_wildcard/classname/pattern.jat")
        code_path = get_test_file(file_path)

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert not res, det
    
    def test_interface(self):
        pattern_path = get_test_file("simple_wildcard/interfacename/pattern.jat")
        code_path = get_test_file("simple_wildcard/interfacename/interface_ok.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

        code_path = get_test_file("simple_wildcard/interfacename/interface_ok_2.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

        code_path = get_test_file("simple_wildcard/interfacename/interface_ko.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert not res, det

    @pytest.mark.parametrize("file_path", discover_files(get_test_file("simple_wildcard/classname/"), ".java", "expression_ok"))
    def test_expression_ok(self, file_path):
        pattern_path = get_test_file("simple_wildcard/expression/pattern.jat")
        code_path = get_test_file(file_path)

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

    @pytest.mark.parametrize("file_path", discover_files(get_test_file("simple_wildcard/expression/"), ".java", "expression_ko"))
    def test_expression_ko(self, file_path):
        pattern_path = get_test_file("simple_wildcard/expression/pattern.jat")
        code_path = get_test_file(file_path)

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert not res, det
    
    def test_method_name(self):
        pattern_path = get_test_file("simple_wildcard/names/method_name.jat")
        code_path = get_test_file("simple_wildcard/names/method_name.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

    def test_variable_name(self):
        pattern_path = get_test_file("simple_wildcard/names/variable_name.jat")
        code_path = get_test_file("simple_wildcard/names/variable_name.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det
    
    def test_field_name(self):
        pattern_path = get_test_file("simple_wildcard/names/field_name.jat")
        code_path = get_test_file("simple_wildcard/names/field_name.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det
    
    def test_type(self):
        pattern_path = get_test_file("simple_wildcard/type/pattern.jat")
        code_path = get_test_file("simple_wildcard/type/primitive.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det

        code_path = get_test_file("simple_wildcard/type/class.java")

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")
        assert res, det