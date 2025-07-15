import importlib.resources as pkg_resources
import os
from unittest import skipIf

import pytest
from loguru import logger

from pyttern import match_files, match_wildcards
from pyttern.simulator.pda.PDA import PDAEncoder, PDA
from pyttern.simulator.pda.transition import Transition, NavigationDirection
from . import tests_files


def get_test_file(path):
    return str(pkg_resources.files(tests_files) / path)


def discover_files(directory, extension=None):
    for root, _, files in os.walk(directory):
        for file in files:
            if ".pyc" not in file:
                if extension is None or file.endswith(extension):
                    yield os.path.join(root, file)


class TestPDAEncoder():

    PDA = None
    Transition = None
    NavigationDirection = None

    @pytest.fixture(autouse=True)
    def setUp(self):
        class DummyPDA:
            def __init__(self):
                self.states = {0, 1}
                self.input_symbols = {"a", "b"}
                self.transitions = {0: []}
                self.initial_state = 0
                self.final_states = {1}

        self.Transition = Transition(0, "a", "", [NavigationDirection.LEFT_CHILD], 1, "A")
        self.PDA = PDA({0, 1}, {"a", "b"}, {0: [self.Transition]}, 0, {1})
        self.NavigationDirection = NavigationDirection.LEFT_CHILD

    def test_encode_pda(self):
        encoder = PDAEncoder()
        result = encoder.default(self.PDA)
        assert isinstance(result, dict)
        assert "states" in result
        assert isinstance(result["states"], list)

    def test_encode_transition(self):
        encoder = PDAEncoder()
        result = encoder.default(self.Transition)
        assert isinstance(result, dict)
        assert "q" in result

    def test_encode_navigation_direction(self):
        encoder = PDAEncoder()
        result = encoder.default(self.NavigationDirection)
        assert isinstance(result, str)
        assert result == "LEFT_CHILD"

    def test_encode_unknown_type(self):
        encoder = PDAEncoder()
        import pytest
        with pytest.raises(TypeError):
            encoder.default(object())


class TestPytternWildcards:
    @pytest.fixture(autouse=True)
    def setUp(self):
        logger.enable("pyttern")
        logger.remove()
        logger.add(lambda x: print(x), level="INFO")

class TestSimpleWildcard(TestPytternWildcards):
    @pytest.mark.timeout(1)
    def test_stmt(self):
        pattern_path = get_test_file("simple_wildcards/stmt/simple_wildcard.pyt")
        code_path = get_test_file("simple_wildcards/stmt/simple_wildcard_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("simple_wildcards/stmt/simple_wildcard_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    @pytest.mark.timeout(1)
    def test_arg(self):
        pattern_path = get_test_file("simple_wildcards/arg/simple_wildcard.pyt")
        code_path = get_test_file("simple_wildcards/arg/simple_wildcard_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("simple_wildcards/arg/simple_wildcard_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    @pytest.mark.timeout(1)
    def test_expr(self):
        pattern_path = get_test_file("simple_wildcards/expr/simple_wildcard.pyt")
        code_path = get_test_file("simple_wildcards/expr/simple_wildcard_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("simple_wildcards/expr/simple_wildcard_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    @pytest.mark.timeout(1)
    def test_func_name(self):
        pattern_path = get_test_file("simple_wildcards/func_name/simple_wildcard.pyt")
        code_path = get_test_file("simple_wildcards/func_name/simple_wildcard_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det


class TestBodyWildcard(TestPytternWildcards):
    @pytest.mark.timeout(1)
    @pytest.mark.parametrize("pattern_path", discover_files(get_test_file("body/simple"), ".pyt"))
    def test_simple(self, pattern_path):
        pattern_path = get_test_file(pattern_path)
        code_path = get_test_file("body/simple/body_simple_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("body/simple/body_simple_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    @pytest.mark.timeout(1)
    def test_nested(self):
        pattern_path = get_test_file("body/nested/body_nested_1.pyt")
        code_path = get_test_file("body/nested/body_nested_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("body/nested/body_nested_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det


class TestMultipleWildcard(TestPytternWildcards):
    @pytest.mark.timeout(1)
    @pytest.mark.parametrize("pattern_path", discover_files(get_test_file("multiple_stmt"), ".pyt"))
    def test_stmt(self, pattern_path):
        pattern_path = get_test_file(pattern_path)
        code_path = get_test_file("multiple_stmt/multiple_stmt_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

    @pytest.mark.timeout(1)
    def test_multiple_args(self):
        pattern_path = get_test_file("multiple_wildcard/args/multiple_args.pyt")
        code_path = get_test_file("multiple_wildcard/args/multiple_args_one_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("multiple_wildcard/args/multiple_args_two_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("multiple_wildcard/args/multiple_args_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    @pytest.mark.timeout(1)
    def test_multiple_args_complex(self):
        pattern_path = get_test_file("multiple_wildcard/args/multiple_args_complex.pyt")
        code_path = get_test_file("multiple_wildcard/args/multiple_args_one_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("multiple_wildcard/args/multiple_args_two_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("multiple_wildcard/args/multiple_args_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    @pytest.mark.timeout(1)
    @pytest.mark.parametrize("code_path", discover_files(get_test_file("multiple_wildcard"), ".py"))
    def test_simple_args(self, code_path):
        pattern_path = get_test_file("multiple_wildcard/args/simple_args.pyt")
        code_path = get_test_file(code_path)

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det


class TestNamedWildcard(TestPytternWildcards):
    @pytest.mark.timeout(1)
    def test_named_var(self):
        pattern_path = get_test_file("name_wildcard/named_var/named_var.pyt")
        code_path = get_test_file("name_wildcard/named_var/named_var_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

    @pytest.mark.timeout(1)
    def test_named_stmt(self):
        pattern_path = get_test_file("name_wildcard/named_stmt/named_stmt.pyt")
        code_path = get_test_file("name_wildcard/named_stmt/named_stmt_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

    def test_named_args(self):
        pattern_path = get_test_file("name_wildcard/named_args/named_args.pyt")
        code_path = get_test_file("name_wildcard/named_args/named_args_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("name_wildcard/named_args/named_args_ko.py")
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    def test_named_func(self):
        pattern_path = get_test_file("name_wildcard/named_func/named_func.pyt")
        code_path = get_test_file("name_wildcard/named_func/named_func_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("name_wildcard/named_func/named_func_ko.py")
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det


class TestMultipleBodyWildcard(TestPytternWildcards):
    @pytest.mark.timeout(1)
    @pytest.mark.parametrize("pattern_path", discover_files(get_test_file("multiple_body/simple"), ".pyt"))
    def test_simple(self, pattern_path):
        pattern_path = get_test_file(pattern_path)
        code_path = get_test_file("multiple_body/simple/multiple_body_simple_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("multiple_body/simple/multiple_body_simple_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    #@pytest.mark.timeout(1) TODO: This test is too long, Pyttern should be optimized
    @pytest.mark.parametrize("pattern_path", discover_files(get_test_file("multiple_body/multiple"), ".pyt"))
    def test_multiple(self, pattern_path):
        pattern_path = get_test_file(pattern_path)
        code_path = get_test_file("multiple_body/multiple/multiple_body_multiple_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True, stop_at_first=True)
        assert res, det

        code_path = get_test_file("multiple_body/multiple/multiple_body_multiple_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True, stop_at_first=True)
        assert not res, det


class TestContainsWildcard(TestPytternWildcards):
    @pytest.mark.timeout(1)
    @pytest.mark.parametrize("pattern_path", discover_files(get_test_file("contains/simple_var"), ".pyt"))
    def test_simple_contains(self, pattern_path):
        pattern_path = get_test_file(pattern_path)
        code_path = get_test_file("contains/simple_var/contains_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("contains/simple_var/contains_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    @pytest.mark.timeout(1)
    @pytest.mark.parametrize("pattern_path", discover_files(get_test_file("contains/complex_expr"), ".pyt"))
    def test_complex_contains(self, pattern_path):
        pattern_path = pattern_path
        code_path = get_test_file("contains/complex_expr/contains_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("contains/complex_expr/contains_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det


class TestSimpleNumberWildcard(TestPytternWildcards):
    #@pytest.mark.timeout(1)
    def test_simple_number(self, subtests):
        for place in ["arg", "list", "stmt"]:
            for folder in ["low", "between", "strict"]:
                with subtests.test(msg=f"Testing simple number wildcard in {place} -> {folder}", place=place,
                                   folder=folder):
                    pattern_path = get_test_file(f"simple_number_wildcard/{place}/{folder}/{folder}.pyt")
                    code_path = get_test_file(f"simple_number_wildcard/{place}/{folder}/{folder}_ok.py")

                    res, det = match_files(pattern_path, code_path, match_details=True)
                    assert res, f"Was {res} for {place}/{folder} but should be True"

                    code_path = get_test_file(f"simple_number_wildcard/{place}/{folder}/{folder}_ko.py")

                    res, det = match_files(pattern_path, code_path, match_details=True)
                    assert not res, f"Was {res} for {place}/{folder} but should be False"


class TestIntegration(TestPytternWildcards):

    @pytest.mark.timeout(1)
    @pytest.mark.parametrize("file_1_path", discover_files(get_test_file("equal_ast"), ".py"))
    @pytest.mark.parametrize("file_2_path", discover_files(get_test_file("equal_ast"), ".py"))
    def test_ast_equal_match(self, file_1_path, file_2_path):
        file_1_path = get_test_file(file_1_path)
        file_2_path = get_test_file(file_2_path)

        res, det = match_files(file_1_path, file_2_path, match_details=True)
        if file_1_path == file_2_path:
            assert res, f"Expected match for {file_1_path} and {file_2_path}: {det}"
        else:
            assert not res, f"Expected no match for {file_1_path} and {file_2_path}: {det}"

    def test_ast_simple_addition(self):
        pattern_path = get_test_file("integration/pi/piPattern.pyt")
        code_path = get_test_file("integration/pi/piCode_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("integration/pi/piCode_ko.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, det

    @pytest.mark.timeout(10)
    def test_soft_pattern_match(self):
        pattern_path = get_test_file("integration/pattern13/pattern13.pyt")
        code_path = get_test_file("integration/pattern13/pattern13_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

    @skipIf(True, "Not sure if we should keep strict mode")
    def test_strict_mode(self):
        val, match = match_files(get_test_file("strictModeTest.pyh"), get_test_file("q1_254.py"),
                                 match_details=True)
        assert val, match

        val, match = match_files(get_test_file("strictModeTest.pyh"), get_test_file("strictModeNok.py"),
                                 match_details=True)
        assert not val, match

    def test_match_wildcards_multiple_code(self):
        pattern_path = get_test_file("Pattern_13soft.pyh")
        code_path = get_test_file("q1_*.py")
        matches = match_wildcards(pattern_path, code_path)
        for code, match in matches.items():
            for pattern, result in match.items():
                if "q1_560.py" in match:
                    assert result, f"{pattern} on {code} should match"
                else:
                    assert not result, f"{pattern} on {code} should not match"

    def test_match_mult_and_div(self):
        pattern_path = get_test_file("multAndDivPatterns/*.pyh")
        code_path = get_test_file("multAndDiv.py")
        matches = match_wildcards(pattern_path, code_path, match_details=True)
        for _, match in matches.items():
            for pattern, result in match.items():
                do_match, details = result
                if "patternMultPlusDIv" in pattern:
                    assert do_match, f"Cannot match {pattern}: {details}"
                else:
                    assert not do_match, details

    def test_match_recursion(self):
        pattern_path = get_test_file("integration/recursion/recursion.pyt")
        code_path = get_test_file("integration/recursion/recursion_ok.py")
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

    def test_observer_pattern(self):
        pattern_path = get_test_file("integration/observer/observer.pyt")
        code_path = get_test_file("integration/observer/observer_ok.py")
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

    def test_augassign(self):
        pytest.skip("Not implemented")

        pattern_path = get_test_file("augassign.pyh")
        code_path = get_test_file("piCode.py")
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

        code_path = get_test_file("piCodeBis.py")
        ret, det = match_files(pattern_path, code_path, match_details=True)
        assert ret, det

        pattern_path = get_test_file("augassignNok.pyh")
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, str(det)

        code_path = get_test_file("piCode.py")
        res, det = match_files(pattern_path, code_path, match_details=True)
        assert not res, str(det)

    @pytest.mark.parametrize("file_path", discover_files(get_test_file("test_zero")))
    def test_zero_wildcard(self, file_path):
        pytest.skip("Not implemented")
        pattern_path = get_test_file(file_path)
        code_path = get_test_file("type.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        if "Ok" in file_path:
            assert res, det
        elif "Ko" in file_path:
            assert not res, det
        else:
            assert False, f"Not ok nor ko in file name: {file_path}"

    def test_too_much_indentation(self):
        pattern_path = get_test_file("integration/indentation/indentation.pyt")
        code_path = get_test_file("integration/indentation/indentation_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det

    def test_var_wildcard_in_arg(self):
        pattern_path = get_test_file("overwritten_arg/overwritten_arg.pyt")
        code_path = get_test_file("overwritten_arg/arg_*.py")

        matches = match_wildcards(pattern_path, code_path)

        for code, match in matches.items():
            for pattern, result in match.items():
                if "nok" in code:
                    assert not result, f"{pattern} on {code} should not match"
                else:
                    assert result, f"{pattern} on {code} should match"

    def test_missplaced_return(self):
        pattern_path = get_test_file("missplacedreturn/toplevelreturn.pyt")
        code_path = get_test_file("missplacedreturn/code55.py")

        # res, det = match_files(pattern_path, code_path, match_details=True, strict_match=False)
        # assert not res, det

        pattern_path = get_test_file("missplacedreturn/indentreturn.pyt")
        res, det = match_files(pattern_path, code_path, match_details=True)

        assert res, det

    def test_hardcoded_list(self):
        pattern_path = get_test_file("integration/hardcoded_list/hardcodedlist.pyt")
        code_path = get_test_file("integration/hardcoded_list/hardcodedlist_ok.py")

        res, det = match_files(pattern_path, code_path, match_details=True)
        assert res, det
