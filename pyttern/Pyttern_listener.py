from abc import ABC
from .simulator import Matcher
from .pytternfsm.python.match_set import Match

from loguru import logger


class Pyttern_listener(ABC):
    def on_match(self, matcher: Matcher, match: Match):
        pass

    def step(self, simulator, fsm, ast, stack, variables, matches):
        pass

    def on_transition(self, simulator, current_fsm, current_ast, next_node, next_ast, classes, movements, matches):
        pass

    def on_end(self, simulator, fsm, node):
        pass

    def on_start(self, simulator, fsm, node):
        pass

    def on_new_variable(self, simulator, fsm, node, var):
        pass


class ConsolePytternListener(Pyttern_listener):
    def on_transition(self, simulator, current_fsm, current_ast, next_node, next_ast, classes, movements, _):
        current_ast_str = f"{current_ast.__class__.__name__[:-7]}({current_ast.getText()})".replace("\n", "\\n")
        next_ast_str = f"{next_ast.__class__.__name__[:-7]}({next_ast.getText()})".replace("\n", "\\n")
        logger.info(
            f"Step {simulator.n_step}: Transition from node {current_fsm} to {next_node}, matching {current_ast_str} "
            f"with {classes}"
            f"and {movements} to {next_ast_str}")

    def on_start(self, simulator, fsm, node):
        print(f"Start matching {fsm} with {node}")

    def on_match(self, simulator, _):
        print(f"Match found at step {simulator.n_step}!")
