from __future__ import annotations

from abc import ABC
from typing import Any, TYPE_CHECKING
from loguru import logger

if TYPE_CHECKING:
    from .simulator.Matcher import Matcher
    from .pytternfsm.python.match_set import Match


class Pyttern_listener(ABC):
    def on_match(self, matcher: Matcher, match: Match) -> None:
        pass

    def step(
        self,
        simulator: Any,
        fsm: Any,
        ast: Any,
        stack: Any,
        variables: Any,
        matches: Any,
    ) -> None:
        pass

    def on_transition(
        self,
        simulator: Any,
        current_fsm: Any,
        current_ast: Any,
        next_node: Any,
        next_ast: Any,
        classes: Any,
        movements: Any,
        matches: Any,
    ) -> None:
        pass

    def on_end(self, simulator: Any, fsm: Any, node: Any) -> None:
        pass

    def on_start(self, simulator: Any, fsm: Any, node: Any) -> None:
        pass

    def on_new_variable(self, simulator: Any, fsm: Any, node: Any, var: Any) -> None:
        pass


class ConsolePytternListener(Pyttern_listener):
    def on_transition(
        self,
        simulator: Any,
        current_fsm: Any,
        current_ast: Any,
        next_node: Any,
        next_ast: Any,
        classes: Any,
        movements: Any,
        _: Any,
    ) -> None:
        current_ast_str = f"{current_ast.__class__.__name__[:-7]}({current_ast.getText()})".replace("\n", "\\n")
        next_ast_str = f"{next_ast.__class__.__name__[:-7]}({next_ast.getText()})".replace("\n", "\\n")
        logger.info(
            f"Step {simulator.n_step}: Transition from node {current_fsm} to {next_node}, matching {current_ast_str} "
            f"with {classes}"
            f"and {movements} to {next_ast_str}"
        )

    def on_start(self, simulator: Any, fsm: Any, node: Any) -> None:
        print(f"Start matching {fsm} with {node}")

    def on_match(self, matcher: Matcher, match: Match) -> None:
        print(f"Match found at step {matcher.n_step}!")
