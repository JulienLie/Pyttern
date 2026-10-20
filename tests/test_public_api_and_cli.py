import sys
from unittest.mock import patch
import pytest

from pyttern import PytternMatcher, match_files, SubPatternCatalog
from pyttern.main import main


def test_pyttern_matcher_default_catalog():
    matcher = PytternMatcher()
    assert isinstance(matcher.catalog, SubPatternCatalog)
    assert len(matcher.catalog) == 0


def test_pyttern_matcher_explicit_catalog():
    catalog = SubPatternCatalog()
    catalog.load_string("""
$|Assign(?a, ?b)
$# t1
?a = ?b
""")
    assert "Assign" in catalog
    matcher = PytternMatcher(catalog=catalog)
    assert matcher.catalog is catalog
    assert "Assign" in matcher.catalog


def test_match_files_threads_catalog(tmp_path):
    sub_file = tmp_path / "test_sub.myt"
    sub_file.write_text("""
$|Assign(?a, ?b)
$# t1
?a = ?b
""")
    pattern_file = tmp_path / "pattern.pyt"
    pattern_file.write_text("?$Assign(?x, ?y)\n")
    code_file = tmp_path / "code.py"
    code_file.write_text("foo = 42\n")

    catalog = SubPatternCatalog()
    catalog.load_file(sub_file)

    # Calling match_files with the explicit catalog must succeed
    res, det = match_files(str(pattern_file), str(code_file), lang="python", match_details=True, catalog=catalog)
    assert res is True
    assert len(det.matches) == 1
    assert det.matches[0].bindings["x"].getText() == "foo"
    assert det.matches[0].bindings["y"].getText() == "42"


def test_match_files_without_catalog_fails_for_subpattern(tmp_path):
    pattern_file = tmp_path / "pattern.pyt"
    pattern_file.write_text("?$Assign(?x, ?y)\n")
    code_file = tmp_path / "code.py"
    code_file.write_text("foo = 42\n")

    # Without passing catalog, should raise ValueError indicating subpattern was not loaded
    with pytest.raises(ValueError, match="Calling Assign subpattern, but was not loaded"):
        match_files(str(pattern_file), str(code_file), lang="python")


def test_cli_sub_flags_load_into_catalog(tmp_path):
    sub1 = tmp_path / "sub1.myt"
    sub1.write_text("""
$|SubOne(?x)
$# t1
?x = 1
""")
    sub2 = tmp_path / "sub2.myt"
    sub2.write_text("""
$|SubTwo(?y)
$# t1
?y = 2
""")
    pattern_file = tmp_path / "pattern.pyt"
    pattern_file.write_text("?$SubOne(?a)\n")
    code_file = tmp_path / "code.py"
    code_file.write_text("a = 1\n")

    test_args = [
        "pyttern",
        "-s", str(sub1),
        "--sub", str(sub2),
        "--lang", "python",
        str(pattern_file),
        str(code_file),
    ]

    with patch.object(sys, "argv", test_args):
        # Should execute without crashing or raising AttributeError on args.sub_patterns
        main()
