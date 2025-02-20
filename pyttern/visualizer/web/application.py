import json
from functools import wraps

from antlr4 import ParseTreeVisitor
from cachelib import FileSystemCache
from flask import Flask, render_template, request, redirect, session, flash, get_flashed_messages
from flask_session import Session
from loguru import logger

from ...PytternListener import PytternListener
from ...pytternfsm.python.python_visitor import Python_Visitor
from ...simulator.simulator import Simulator
from ...language_processors import get_processor
from ...language_processors.language_utils import determine_language

app = Flask(__name__)
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


def get_simulator(pyttern_code, code):
    if "pattern_language" in session and session["pattern_language"] is not None and "code_language" in session and session["code_language"] is not None:
        current_language_processor = get_processor(session["pattern_language"])
        pyttern_tree = current_language_processor.generate_tree_from_code(pyttern_code)
        # strict = session.get('strict', False)
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


""" Web endpoints """
from flask_assets import Environment, Bundle

assets = Environment(app)

js = Bundle('code/visualizer.js', 'code/gifmaker.js', 'code/gifs/gif.js', 'code/gifs/gif.worker.js',
            filters='jsmin', output='gen/packed.js')
assets.register('js_all', js)

@app.route("/")
def index():
    session["n_match"] = 0

    pyttern_code = None
    code_file = None
    pyttern_fsm_graph = "null"
    pyttern_tree_graph = "null"
    code_tree_graph = "null"
    
    # session["pyttern_code"] = None
    # session["code_file"] = None
    if "pyttern_code" in session and session["pyttern_code"] is not None:
        try:
            pyttern_code = session["pyttern_code"]
            if "pattern_language" in session and session["pattern_language"] is not None:
                current_language_processor = get_processor(session["pattern_language"])
                pyttern_tree = current_language_processor.generate_tree_from_code(pyttern_code)
                pyttern_tree_graph = PtToJson().visit(pyttern_tree)
                # strict = session.get('strict', False)
                pyttern_fsm = current_language_processor.create_fsm(pyttern_tree)
                print("PYTTERN FSM ::: ", pyttern_fsm)
                pyttern_fsm_graph = fsm_to_json(pyttern_fsm)
            else:
                flash("Pattern language not set.", "error")
        except IOError as e:
            pyttern_code = None
            session.pop('pyttern_code', None)
            print(e)
            
    if "code_file" in session and session["code_file"] is not None:
        code_file = session["code_file"]
        if "code_language" in session and session["code_language"] is not None:
            current_processor = get_processor(session["code_language"])
            code_tree = current_processor.generate_tree_from_code(code_file)
            code_tree_graph = PtToJson().visit(code_tree)
        else:
            flash("Code language not set.", "error")

    return render_template(
        'index.html', pyttern_code=pyttern_code, code_file=code_file, code_tree=code_tree_graph,
        pyttern_graph=pyttern_fsm_graph, pyttern_tree=pyttern_tree_graph)


@app.route("/submit-pyttern", methods=['POST'])
def submit_pyttern():
    global current_processor
    code = request.files['pyttern-file']
    
    pattern_language = determine_language(code.filename)
    if pattern_language is None:
        return json.dumps({"status": "error", "message": "Unsupported pattern file extension"})
    
    # Ensure the pattern and code languages match
    if 'code_language' in session and session['code_language'] is not None and session['code_language'] != pattern_language:
        return json.dumps({"status": "error", "message": "Pattern file language does not match the code file language"})
    
    session['pattern_language'] = pattern_language
    session['pyttern_code'] = code.stream.read().decode()
    current_processor = get_processor(pattern_language)
    
    strict = request.form.get('strict', 'off') == 'on'
    session['strict'] = strict
    return redirect("/")


@app.route("/remove-pyttern", methods=['POST'])
def remove_pyttern():
    session.pop('pyttern_code', None)
    session.pop('pattern_language', None)
    global current_processor
    current_processor = None
    return redirect("/")


@app.route("/submit-code", methods=['POST'])
def submit_code():
    global current_processor
    code = request.files['code-file']
    
    code_language = determine_language(code.filename)
    if code_language is None:
        return json.dumps({"status": "error", "message": "Unsupported code file extension"})

    # Ensure the pattern and code languages match
    if 'pattern_language' in session and session['pattern_language'] is not None and session['pattern_language'] != code_language:
        return json.dumps({"status": "error", "message": "Code file language does not match the pattern file language"})
    
    session['code_language'] = code_language
    session['code_file'] = code.stream.read().decode()
    current_processor = get_processor(code_language)
    return redirect("/")


@app.route("/remove-code", methods=['POST'])
def remove_python():
    session.pop('code_file', None)
    session.pop('code_language', None)
    global current_processor
    current_processor = None
    return redirect("/")


"""API endpoints"""


@app.route("/api/start", methods=['POST'])
@file_check()
def start():
    session["n_match"] = 0

    pyttern_code = session["pyttern_code"]
    code_file = session["code_file"]
    simulator = get_simulator(pyttern_code, code_file)
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
    return json.dumps({
        "status": "ok",
        "n_steps": simulator.n_step - 1,
        "state": first_state_info,
        "match_states": match_states
    })


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
