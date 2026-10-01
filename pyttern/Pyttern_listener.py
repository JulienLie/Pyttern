"""Listener interfaces and event handlers for monitoring Pyttern simulation."""

from __future__ import annotations

from abc import ABC
from typing import TYPE_CHECKING, Any

from loguru import logger

from .pytternfsm.python.match_set import Match

if TYPE_CHECKING:
    from .simulator.Matcher import Matcher


class Pyttern_listener(ABC):
    """Abstract base class for Pyttern matching listeners."""

    def on_match(self, matcher: Matcher, match: Match) -> None:
        """Called when a complete pattern match is detected.

        Args:
            matcher: Matcher engine instance.
            match: Match record containing bindings and step count.
        """
        pass

    def step(
        self,
        simulator: Any,
        fsm: Any,
        ast: Any,
        stack: list[Any],
        variables: dict[str, Any],
        matches: list[Any],
    ) -> None:
        """Called on each simulation evaluation step.

        Args:
            simulator: Active Simulator or Matcher instance.
            fsm: Current PDA state index or object.
            ast: Current AST node under evaluation.
            stack: Current PDA symbol stack.
            variables: Currently bound variable environments.
            matches: Matches accumulated so far.
        """
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
        """Called when a valid transition is taken.

        Args:
            simulator: Active simulator instance.
            current_fsm: Source PDA state.
            current_ast: Source AST node.
            next_node: Target PDA state.
            next_ast: Target AST node.
            classes: Matching condition or node class.
            movements: Navigation movements traversed.
            matches: Accumulated match list.
        """
        pass

    def on_end(self, simulator: Any, fsm: Any, node: Any) -> None:
        """Called when a matching branch concludes.

        Args:
            simulator: Active simulator instance.
            fsm: Ending PDA state.
            node: Ending AST node.
        """
        pass

    def on_start(self, simulator: Any, fsm: Any, node: Any) -> None:
        """Called when simulation begins matching an AST root.

        Args:
            simulator: Active simulator instance.
            fsm: Start state.
            node: Start AST root node.
        """
        pass

    def on_new_variable(self, simulator: Any, fsm: Any, node: Any, var: str) -> None:
        """Called when a new wildcard variable binding is created.

        Args:
            simulator: Active simulator instance.
            fsm: Current PDA state.
            node: Bound AST node value.
            var: Bound variable name.
        """
        pass


class ConsolePytternListener(Pyttern_listener):
    """Standard console listener for monitoring matching progress."""

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
        """Log a formatted trace message for the transition taken.

        Args:
            simulator: Active simulator instance.
            current_fsm: Source state.
            current_ast: Source AST node.
            next_node: Destination state.
            next_ast: Destination AST node.
            classes: Condition or class matched.
            movements: Navigation movements.
            _: Unused match accumulator.
        """
        current_ast_str = f"{current_ast.__class__.__name__[:-7]}({current_ast.getText()})".replace("\n", "\\n")
        next_ast_str = f"{next_ast.__class__.__name__[:-7]}({next_ast.getText()})".replace("\n", "\\n")
        logger.info(
            f"Step {simulator.n_step}: Transition from node {current_fsm} to {next_node}, matching {current_ast_str} "
            f"with {classes} and {movements} to {next_ast_str}"
        )

    def on_start(self, simulator: Any, fsm: Any, node: Any) -> None:
        """Print match start announcement.

        Args:
            simulator: Active simulator.
            fsm: Start state.
            node: Start AST node.
        """
        print(f"Start matching {fsm} with {node}")

    def on_match(self, simulator: Any, _: Any) -> None:
        """Print match found announcement.

        Args:
            simulator: Active simulator.
            _: Unused match details.
        """
        print(f"Match found at step {simulator.n_step}!")

