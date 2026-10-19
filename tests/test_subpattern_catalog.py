from pathlib import Path
import pytest

from pyttern.language_processors.languages import Languages
from pyttern.subpattern import (
    AndSubPattern,
    BaseSubPattern,
    NotSubPattern,
    OrSubPattern,
    loaded_subpatterns,
    parse_subpattern_from_file,
    parse_subpattern_from_string,
)
from pyttern.subpattern.catalog import SubPatternCatalog


class TestSubPatternCatalogRegistrationAndRetrieval:
    """Requirement 1 & 5: Dedicated catalog class providing explicit registration, retrieval, and error handling."""

    def test_catalog_instantiation_empty(self):
        catalog = SubPatternCatalog()
        assert len(catalog) == 0
        assert list(catalog.keys()) == []
        assert list(catalog.values()) == []
        assert list(catalog.items()) == []

    def test_catalog_register_and_get(self):
        catalog = SubPatternCatalog()
        subp = OrSubPattern(name="TestOr", args={}, args_order=[], code="$|TestOr()")
        catalog.register(subp)

        assert len(catalog) == 1
        assert "TestOr" in catalog
        assert catalog.get("TestOr") is subp
        assert catalog["TestOr"] is subp

    def test_catalog_register_all_subpattern_types(self):
        catalog = SubPatternCatalog()
        or_sp = OrSubPattern(name="OrSP", args={}, args_order=[], code="$|OrSP()")
        and_sp = AndSubPattern(name="AndSP", args={}, args_order=[], code="$&AndSP()")
        not_sp = NotSubPattern(name="NotSP", args={}, args_order=[], code="$!NotSP()")

        catalog.register(or_sp)
        catalog.register(and_sp)
        catalog.register(not_sp)

        assert len(catalog) == 3
        assert catalog.get("OrSP").type == "OR"
        assert catalog.get("AndSP").type == "AND"
        assert catalog.get("NotSP").type == "NOT"

    def test_catalog_register_override_behavior(self):
        catalog = SubPatternCatalog()
        subp1 = OrSubPattern(name="Foo", args={}, args_order=[], code="$|Foo() # 1")
        subp2 = OrSubPattern(name="Foo", args={}, args_order=[], code="$|Foo() # 2")

        catalog.register(subp1)
        assert catalog.get("Foo").code == "$|Foo() # 1"

        # By default override=True replaces existing
        catalog.register(subp2, override=True)
        assert catalog.get("Foo").code == "$|Foo() # 2"

        # With override=False raises ValueError
        with pytest.raises(ValueError, match="already registered"):
            catalog.register(subp1, override=False)

    def test_catalog_query_unknown_returns_none_or_default(self):
        catalog = SubPatternCatalog()
        assert catalog.get("NonExistent") is None
        default_val = object()
        assert catalog.get("NonExistent", default=default_val) is default_val

    def test_catalog_getitem_unknown_raises_descriptive_error(self):
        catalog = SubPatternCatalog()
        subp = AndSubPattern(name="Existing", args={}, args_order=[], code="$&Existing()")
        catalog.register(subp)

        with pytest.raises(KeyError) as exc_info:
            _ = catalog["Missing"]

        err_msg = str(exc_info.value)
        assert "Missing" in err_msg
        assert "Existing" in err_msg


class TestNoGlobalStateMutation:
    """Requirement 2: Subpatterns parsed from files or strings do not mutate module-level dictionaries."""

    @pytest.fixture(autouse=True)
    def clean_globals(self):
        loaded_subpatterns.clear()
        yield
        loaded_subpatterns.clear()

    def test_parse_from_string_does_not_mutate_module_level_dictionary(self):
        code = """
$&Assign(?x, ?y)
$# t1
?x = ?y
"""
        assert len(loaded_subpatterns) == 0
        parsed = parse_subpattern_from_string(code, Languages.PYTHON)
        assert len(parsed) == 1
        assert parsed[0].name == "Assign"
        # Must NOT write to module-level dictionary!
        assert len(loaded_subpatterns) == 0

    def test_parse_from_file_does_not_mutate_module_level_dictionary(self):
        subpattern_file = Path("tests/tests_files/subpatterns/and/simple/assign.myt")
        assert len(loaded_subpatterns) == 0
        parsed = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
        assert len(parsed) == 1
        assert parsed[0].name == "Assign"
        # Must NOT write to module-level dictionary!
        assert len(loaded_subpatterns) == 0

    def test_catalog_load_string_registers_into_catalog_only(self):
        code = """
$|Incr(?x)
$# t1
?x += 1
"""
        catalog = SubPatternCatalog()
        assert len(loaded_subpatterns) == 0
        result = catalog.load_string(code, Languages.PYTHON)

        assert len(result) == 1
        assert "Incr" in catalog
        assert catalog.get("Incr") is result[0]
        # Must NOT leak to module-level loaded_subpatterns!
        assert len(loaded_subpatterns) == 0

    def test_catalog_load_file_registers_into_catalog_only(self):
        subpattern_file = Path("tests/tests_files/subpatterns/and/simple/assign.myt")
        catalog = SubPatternCatalog()
        assert len(loaded_subpatterns) == 0
        result = catalog.load_file(subpattern_file, Languages.PYTHON)

        assert len(result) == 1
        assert "Assign" in catalog
        assert catalog.get("Assign") is result[0]
        # Must NOT leak to module-level loaded_subpatterns!
        assert len(loaded_subpatterns) == 0

    def test_parse_with_explicit_catalog_argument(self):
        code = """
$!Guard(?x)
$# t1
assert ?x
"""
        catalog = SubPatternCatalog()
        parsed = parse_subpattern_from_string(code, Languages.PYTHON, catalog=catalog)
        assert len(parsed) == 1
        assert "Guard" in catalog
        assert len(loaded_subpatterns) == 0


class TestMultipleCatalogsIsolation:
    """Requirement 3: Multiple independent catalog instances can coexist without sharing state."""

    def test_catalogs_isolated_state(self):
        cat1 = SubPatternCatalog()
        cat2 = SubPatternCatalog()

        sp1 = AndSubPattern(name="SharedName", args={}, args_order=[], code="$&SharedName() # 1")
        sp2 = OrSubPattern(name="SharedName", args={}, args_order=[], code="$|SharedName() # 2")

        cat1.register(sp1)
        cat2.register(sp2)

        assert cat1.get("SharedName") is sp1
        assert cat2.get("SharedName") is sp2
        assert cat1.get("SharedName").type == "AND"
        assert cat2.get("SharedName").type == "OR"
        assert cat1.get("SharedName").code != cat2.get("SharedName").code

    def test_registering_in_one_does_not_affect_other(self):
        cat1 = SubPatternCatalog()
        cat2 = SubPatternCatalog()

        sp = AndSubPattern(name="OnlyIn1", args={}, args_order=[], code="$&OnlyIn1()")
        cat1.register(sp)

        assert "OnlyIn1" in cat1
        assert "OnlyIn1" not in cat2
        assert cat2.get("OnlyIn1") is None


class TestDecoupledSubPatternDataModel:
    """Requirement 4: Subpattern data classes are pure syntax definitions without lazy imports or compilation caches."""

    def test_subpatterns_pure_data_classes(self):
        classes = [AndSubPattern, OrSubPattern, NotSubPattern]
        for cls in classes:
            sp = cls(name="Sample", args={}, args_order=[], code="code")
            # Should have standard syntax definition fields
            assert sp.name == "Sample"
            assert sp.args == {}
            assert sp.args_order == []
            assert sp.code == "code"
            assert isinstance(sp.transformations, dict)
            # Should NOT have private compilation cache
            assert not hasattr(sp, "_BaseSubPattern__compiled_transformations")
            assert not hasattr(sp, "__compiled_transformations")
            assert not hasattr(sp, "compiled_transformations")

    def test_instantiation_has_no_global_side_effects(self):
        loaded_subpatterns.clear()
        _ = AndSubPattern(name="NoSideEffect", args={}, args_order=[], code="code")
        _ = OrSubPattern(name="NoSideEffect2", args={}, args_order=[], code="code")
        _ = NotSubPattern(name="NoSideEffect3", args={}, args_order=[], code="code")
        assert len(loaded_subpatterns) == 0

    def test_no_lazy_compiler_imports_in_subpattern_module(self):
        import pyttern.subpattern.SubPattern as sp_module
        # The module should not import Python_to_PDA or compiler classes
        assert not hasattr(sp_module, "Python_to_PDA")
