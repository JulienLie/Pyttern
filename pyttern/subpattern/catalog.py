from __future__ import annotations

from pathlib import Path
from typing import TYPE_CHECKING, Any, ItemsView, Iterator, KeysView, Optional, ValuesView

from loguru import logger

if TYPE_CHECKING:
    from .SubPattern import BaseSubPattern
    from ..language_processors.languages import Languages


class SubPatternCatalog:
    """
    An explicit catalog and import table for subpattern definitions.

    Maintains isolated collections of parsed SubPattern instances and caches
    compiled context transformations without leaking state to process-wide globals.
    """

    def __init__(self) -> None:
        self._subpatterns: dict[str, BaseSubPattern] = {}
        self._compiled_cache: dict[tuple[str, Any], Any] = {}

    def register(self, subpattern: BaseSubPattern, override: bool = True) -> None:
        """
        Registers a subpattern definition in this catalog.

        :param subpattern: The subpattern instance to register.
        :param override: If False, raises a ValueError if a subpattern with the same name already exists.
        :raises ValueError: When override is False and the subpattern name already exists.
        """
        if not override and subpattern.name in self._subpatterns:
            logger.error(
                f"Subpattern '{subpattern.name}' is already registered in this catalog."
            )
            raise ValueError(
                f"Subpattern '{subpattern.name}' is already registered in this catalog."
            )
        self._subpatterns[subpattern.name] = subpattern
        logger.trace(f"Registered subpattern '{subpattern.name}' in catalog.")

    def get(
        self, name: str, default: Optional[BaseSubPattern] = None
    ) -> Optional[BaseSubPattern]:
        """
        Retrieves a subpattern definition by name.

        :param name: The name of the subpattern.
        :param default: Default value to return if not found. Defaults to None.
        :return: The SubPattern instance or default.
        """
        return self._subpatterns.get(name, default)

    def __getitem__(self, name: str) -> BaseSubPattern:
        """
        Retrieves a subpattern definition by name or raises a descriptive KeyError.

        :param name: The name of the subpattern.
        :return: The SubPattern instance.
        :raises KeyError: If the subpattern is not present in this catalog.
        """
        if name not in self._subpatterns:
            available = list(self._subpatterns.keys())
            raise KeyError(
                f"Subpattern '{name}' not found in catalog. Available subpatterns: {available}"
            )
        return self._subpatterns[name]

    def __contains__(self, name: str) -> bool:
        return name in self._subpatterns

    def __len__(self) -> int:
        return len(self._subpatterns)

    def __iter__(self) -> Iterator[str]:
        return iter(self._subpatterns)

    def keys(self) -> KeysView[str]:
        return self._subpatterns.keys()

    def names(self) -> KeysView[str]:
        return self.keys()

    def values(self) -> ValuesView[BaseSubPattern]:
        return self._subpatterns.values()

    def items(self) -> ItemsView[str, BaseSubPattern]:
        return self._subpatterns.items()

    @staticmethod
    def _extract_context_type(context: Any) -> Any:
        """
        Extracts the AST context class type from a call context or AST node.
        """
        from .SubPattern import SubPatternCallContext

        if isinstance(context, SubPatternCallContext):
            ctx_node = context.ast_ctx
        else:
            ctx_node = context

        if isinstance(ctx_node, type):
            return ctx_node
        if ctx_node is not None:
            return ctx_node.__class__
        return None

    def get_compiled(self, name: str, context: Any) -> Optional[dict[str, Any]]:
        """
        Retrieves cached compiled transformations for a subpattern under a call context.

        :param name: Name of the subpattern.
        :param context: The call context or AST node.
        :return: Dict of compiled PDA transformations, or None if not cached.
        """
        ctx_type = self._extract_context_type(context)
        return self._compiled_cache.get((name, ctx_type))

    def get_or_compile(
        self, name: str, context: Any, processor: Any
    ) -> dict[str, Any]:
        """
        Retrieves compiled transformations for a subpattern under a call context,
        compiling via the language processor and caching if not already cached.

        :param name: Name of the subpattern.
        :param context: The call context (SubPatternCallContext or AST node).
        :param processor: The language processor responsible for compiling the AST.
        :return: Dict mapping transformation names to compiled PDA instances.
        """
        ctx_type = self._extract_context_type(context)
        cache_key = (name, ctx_type)
        if cache_key in self._compiled_cache:
            logger.trace(f"Reusing cached compiled subpattern '{name}' for context {ctx_type}")
            return self._compiled_cache[cache_key]

        subpattern = self[name]
        try:
            compiled = processor.compile_subpattern(subpattern, context, catalog=self)
        except TypeError:
            compiled = processor.compile_subpattern(subpattern, context)
        self._compiled_cache[cache_key] = compiled
        logger.trace(f"Compiled and cached subpattern '{name}' for context {ctx_type}")
        return compiled

    def clear_cache(self) -> None:
        """
        Clears the cache of compiled subpattern transformations.
        """
        self._compiled_cache.clear()

    def load_string(
        self,
        code: str,
        language: Languages | None = None,
        override: bool = True,
    ) -> list[BaseSubPattern]:
        """
        Parses subpatterns from a string and registers them into this catalog.

        :param code: Subpattern definition source code.
        :param language: Target programming language (defaults to Languages.PYTHON).
        :param override: Whether to override existing subpatterns with the same name.
        :return: List of parsed SubPattern instances registered into this catalog.
        """
        from .subpattern_parser import parse_subpattern_from_string
        from ..language_processors.languages import Languages

        lang = language if language is not None else Languages.PYTHON
        return parse_subpattern_from_string(
            code, language=lang, override=override, catalog=self
        )

    def load_file(
        self,
        file: str | Path,
        language: Languages | None = None,
        override: bool = True,
    ) -> list[BaseSubPattern]:
        """
        Parses subpatterns from a file and registers them into this catalog.

        :param file: Path to the subpattern definition file.
        :param language: Target programming language (defaults to Languages.PYTHON).
        :param override: Whether to override existing subpatterns with the same name.
        :return: List of parsed SubPattern instances registered into this catalog.
        """
        from .subpattern_parser import parse_subpattern_from_file
        from ..language_processors.languages import Languages

        lang = language if language is not None else Languages.PYTHON
        return parse_subpattern_from_file(
            file, language=lang, override=override, catalog=self
        )
