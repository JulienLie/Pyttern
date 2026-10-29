from pathlib import Path
import pytest

from pyttern import match_files, SubPatternCatalog
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file


@pytest.fixture
def catalog():
    cat = SubPatternCatalog()
    subpattern_file = Path(__file__).parent / "deepnested.myt"
    cat.load_file(subpattern_file)
    return cat


def test_deepnested_simple_match(catalog):
    pattern_path = Path(__file__).parent / "deepnested.pyt"
    code_path = Path(__file__).parent / "deepnested_simple_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det
    assert det.count() > 0
    bindings = det.matches[0].bindings
    assert bindings["Service"].getText() == "OrderFulfillmentService"
    assert bindings["method"].getText() == "process"
    assert bindings["tx"].getText() == "db"
    assert bindings["res"].getText() == "res"


def test_deepnested_subclass_match(catalog):
    pattern_path = Path(__file__).parent / "deepnested.pyt"
    code_path = Path(__file__).parent / "deepnested_subclass_ok.py"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert res, det
    assert det.count() > 0
    bindings = det.matches[0].bindings
    assert bindings["Service"].getText() == "PaymentGatewayService"
    assert bindings["method"].getText() == "process"
    assert bindings["tx"].getText() == "tx"
    assert bindings["res"].getText() == "res"


def test_deepnested_forbidden_commit_mismatch(catalog):
    pattern_path = Path(__file__).parent / "deepnested.pyt"
    code_path = Path(__file__).parent / "deepnested_forbidden_commit_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res


def test_deepnested_missing_audit_mismatch(catalog):
    pattern_path = Path(__file__).parent / "deepnested.pyt"
    code_path = Path(__file__).parent / "deepnested_missing_audit_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res


def test_deepnested_wrong_class_constraint_mismatch(catalog):
    pattern_path = Path(__file__).parent / "deepnested.pyt"
    code_path = Path(__file__).parent / "deepnested_wrong_class_constraint_ko.py"

    res, det = match_files(pattern_path, code_path, match_details=True, catalog=catalog)
    assert not res


def test_deepnested_subpattern_metadata():
    subpattern_file = Path(__file__).parent / "deepnested.myt"
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    assert len(ret) == 4
    by_name = {sp.name: sp for sp in ret}

    assert by_name["ServiceClass"].type == "OR"
    assert by_name["ServiceClass"].body_var == "body"
    assert by_name["ServiceClass"].args_order == ["Service"]

    assert by_name["AuditedMethod"].type == "AND"
    assert by_name["AuditedMethod"].body_var == "body"
    assert by_name["AuditedMethod"].args_order == ["method", "service"]

    assert by_name["SafeTransaction"].type == "OR"
    assert by_name["SafeTransaction"].body_var == "body"
    assert by_name["SafeTransaction"].args_order == ["tx"]

    assert by_name["NoDirectCommit"].type == "NOT"
    assert by_name["NoDirectCommit"].body_var is None
    assert by_name["NoDirectCommit"].args_order == ["tx"]
