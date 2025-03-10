import json
from functools import wraps

from antlr4 import ParseTreeVisitor
from cachelib import FileSystemCache
from flask import Flask, render_template, request, redirect, session, flash, get_flashed_messages, send_from_directory
from flask_session import Session
from loguru import logger

from ...PytternListener import PytternListener
from ...language_processors import get_processor, determine_language
from ...pyttern_error_listener import PytternSyntaxException

app = Flask(__name__, static_folder='dist', static_url_path='')
app.secret_key = b'a78b11744f599a29207910d3b55eded2dd22cbf9c1dc6c007586b68ff649ac6f'

SESSION_TYPE = 'cachelib'
SESSION_SERIALIZATION_FORMAT = 'json'
SESSION_CACHELIB = FileSystemCache(threshold=500, cache_dir="./sessions")
app.config.from_object(__name__)
Session(app)

current_language_processor = None

""" Helper classes and methods """


class PtToJson(ParseTreeVisitor):
    def visitChildren(self, node):
        elem = {"name": node.__class__.__name__, "children": [], "id": hash(node)}
        if node.start is not None:
            elem["start"] = (node.start.line, node.start.column)
        if node.stop is not None:
            elem["end"] = (node.stop.line, node.stop.column)
        result = self.defaultResult()
        n = node.getChildCount()
        for i in range(n):
            if not self.shouldVisitNextChild(node, result):
                return result

            c = node.getChild(i)
            childResult = c.accept(self)
            result = self.aggregateResult(result, childResult)
        elem["children"] = result
        return elem

    def visitTerminal(self, node):
        return {
            "name": node.__class__.__name__,
            "symbol": node.symbol.text,
            "children": [],
            'id': hash(node)
        }

    def visitErrorNode(self, node):
        return {"name": node.__class__.__name__, "children": [], id: hash(node)}

    def defaultResult(self):
        return []

    def aggregateResult(self, aggregate, nextResult):
        return aggregate + [nextResult]


def file_check():
    def _file_check(f):
        @wraps(f)
        def __file_check(*args, **kwargs):
            if ("pyttern_code" in session and "code_file" in session
                    and session["pyttern_code"] is not None
                    and session["code_file"] is not None):
                return f(*args, **kwargs)

            return json.dumps({
                "status": "error",
                "message": "Missing pyttern or code file"
            })

        return __file_check

    return _file_check


def get_simulator(pattern_code, code):
    logger.debug("Getting simulator")
    if "pattern_language" in session and session["pattern_language"] is not None:
        current_language_processor = get_processor(session["pattern_language"])
        pyttern_tree = current_language_processor.generate_tree_from_code(pattern_code)
        pyttern_fsm = current_language_processor.create_fsm(pyttern_tree)
        code_tree = current_language_processor.generate_tree_from_code(code)

        return current_language_processor.create_simulator(pyttern_fsm, code_tree)
    else:
        raise Exception("No language is set")


def fsm_to_json(fsm):
    print("FSM :::", fsm)
    nodes = []
    to_check = [fsm]
    visited = set()
    while len(to_check) > 0:
        node = to_check.pop()
        if node in visited:
            continue
        visited.add(node)
        transitions = []
        for next_node, func, mov in node.get_transitions():
            name = str(func)
            transitions.append({"next": str(next_node), "func": name, "mov": str(mov)})
            to_check.append(next_node)
        infos = node.__dict__.copy()
        infos['transitions'] = transitions
        infos['id'] = str(node)
        logger.debug(infos)
        json_obj = json.dumps(infos)
        nodes.append(json_obj)
    return nodes


class JsonListener(PytternListener):
    def __init__(self):
        self.data = []

    def step(self, _, fsm, ast, variables, matches):
        state_info = (str(fsm), hash(ast))
        current_matchings = [(str(fsm), hash(ast)) for fsm, ast in matches]
        logger.debug(variables)
        var_strs = [f"{var}: {PtToJson().visit(variables[var])}" for var in variables]
        logger.debug(var_strs)
        self.data.append({
            "state": state_info,
            "matches": current_matchings,
            "match": False,
            "variables": var_strs
        })

    def on_match(self, _):
        self.data[-1]["match"] = True


def try_processors(code):
    languages = ["python", "java"]
    for lang in languages:
        try:
            processor = get_processor(lang)
            processor.generate_tree_from_code(code)
            session["pattern_language"] = lang
            return json.dumps({"status": "ok"})
        except Exception as e:
            logger.error(f"Error with {lang}: {e}")
    return json.dumps({"status": "error", "message": "Cannot recognize the language"})


""" Web endpoints """

@app.route("/")
def index():
    return send_from_directory("dist/", "index.html")


"""API endpoints"""

@app.route("/api/validate", methods=['POST'])
def validate():
    pattern_code = request.json["code"]
    pattern_lang = request.json['lang']
    if pattern_lang == "":
        return try_processors(pattern_code)
    logger.info(f"Current lang: {pattern_lang}")
    try:
        lang = determine_language(pattern_lang)
        session['pattern_language'] = lang
        current_language_processor = get_processor(lang)
        current_language_processor.generate_tree_from_code(pattern_code)
    except PytternSyntaxException as e:
        return json.dumps({"status": "error",
            "message": {"line": e.line, "column": e.column, "symbol": e.symbol, "msg": e.msg}})
    except Exception as e:
        return json.dumps({"status": "error", "message": f"{e}"})
    return json.dumps({"status": "ok"})


@app.route("/api/pattern", methods=['POST'])
def pattern():
    pattern_code = request.json["code"]
    try:
        lang = session["pattern_language"]
        current_language_processor = get_processor(lang)
        pattern_tree = current_language_processor.generate_tree_from_code(pattern_code)
    except Exception as e:
        return json.dumps({
            "status": "error",
            "message": str(e)
        })
    pattern_tree_graph = PtToJson().visit(pattern_tree)
    pyttern_fsm = current_language_processor.create_fsm(pattern_tree)
    pattern_fsm_graph = fsm_to_json(pyttern_fsm)
    pattern_fsm_graph = [json.loads(node) for node in pattern_fsm_graph]
    return json.dumps({
        "status": "ok",
        "graph": {
            "GRAPH": pattern_fsm_graph,
            "TREE": pattern_tree_graph,
        }
    })


@app.route("/api/code", methods=['POST'])
def code():
    code = request.json["code"]
    lang = session["pattern_language"]
    current_language_processor = get_processor(lang)
    tree = current_language_processor.generate_tree_from_code(code)
    tree_graph = PtToJson().visit(tree)
    return json.dumps({
        "status": "ok",
        "graph": {
            "TREE": tree_graph
        }
    })

@app.route("/api/match", methods=['POST'])
def match():
    data = request.json
    code = data["code"]
    pattern = data["pattern"]

    simulator = get_simulator(pattern, code)
    json_listener = JsonListener()
    simulator.add_listener(json_listener)
    simulator.start()
    first_state = simulator.states[-1]
    fsm, ast, _, _ = first_state
    first_state_info = (str(fsm), hash(ast))
    while len(simulator.states) > 0:
        simulator.step()
    logger.debug(f"Number of steps: {simulator.n_step}")
    session["data"] = json_listener.data
    match_states = [i for i, data in enumerate(json_listener.data) if data["match"]]
    logger.debug(f"Matching states: {match_states}")
    return json.dumps(
        {"status": "ok", "n_steps": simulator.n_step - 1, "state": first_state_info, "match_states": match_states})


@app.route("/api/step", methods=['POST'])
def step():
    current_step = int(request.json["step"])
    logger.info(f"Getting step {current_step}")
    current_data = session["data"][current_step]
    last_data = session["data"][current_step - 1] if current_step > 0 else None
    logger.debug(f"Current step: {current_data}")
    logger.debug(f"Last step: {last_data}")

    state_info = current_data["state"]

    current_matchings = current_data["matches"]
    previous_matchings = last_data["matches"] if last_data is not None else []

    if current_data["match"]:
        flash("New match found", "message")

    return json.dumps({
        "status": "ok",
        "state": state_info,
        "current_matchings": current_matchings,
        "previous_matchings": previous_matchings,
        "messages": get_flashed_messages(),
        "match": current_data["match"],
        "variables": current_data["variables"]
    })
