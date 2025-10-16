import importlib.resources as pkg_resources

from loguru import logger

from pyttern import match_files
from pyttern.language_processors import Languages
from pyttern.macro.macro_parser import parse_macro_from_file
from . import tests_files


def get_test_file(path):
    return str(pkg_resources.files(tests_files) / path)

def test_macro_parser():
    #logger.enable("pyttern")

    macro_file = get_test_file("macros/loop.myt")

    ret = parse_macro_from_file(macro_file, Languages.PYTHON)

    #assert False, ret

def test_incr_macro():
    logger.enable("pyttern")

    macro_file = get_test_file("macros/incr.myt")

    ret = parse_macro_from_file(macro_file, Languages.PYTHON)
    assert len(ret) == 1, f"Expected 1 macro, got {len(ret)}"
    assert ret[0].name == "Incr", f"Expected macro name 'Incr', got {ret[0].name}"
    assert len(ret[0].transformations) == 3, f"Expected 2 transformations, got {len(ret[0].transformations)}"

    code_path = get_test_file("macros/increment_1_ok.py")
    pattern_path = get_test_file("macros/increment.pyt")

    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det

    code_path = get_test_file("macros/increment_2_ok.py")
    res, det = match_files(pattern_path, code_path, match_details=True)
    assert res, det