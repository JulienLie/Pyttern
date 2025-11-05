import json
from functools import wraps

from antlr4 import ParseTreeVisitor
from cachelib import FileSystemCache
from flasgger import Swagger
from flask import Flask, request, session, flash, get_flashed_messages, send_from_directory, Response
from flask_session import Session
from loguru import logger

from ...PytternListener import PytternListener
from ...language_processors import Languages, get_processor, determine_language, determine_language_from_code
from ...macro.Macro import loaded_macros
from ...macro.macro_parser import parse_macro_from_string
from ...pyttern_error_listener import PytternSyntaxException
from ...simulator.Matcher import Matcher
from ...simulator.pda.PDA import PDAEncoder

app = Flask(__name__, static_folder='dist', static_url_path='')
app.secret_key = b'a78b11744f599a29207910d3b55eded2dd22cbf9c1dc6c007586b68ff649ac6f'

SESSION_TYPE = 'cachelib'
SESSION_SERIALIZATION_FORMAT = 'json'
SESSION_CACHELIB = FileSystemCache(threshold=500, cache_dir="./sessions")
app.config.from_object(__name__)
Session(app)

# Global Swagger template with shared definitions
app.config['SWAGGER'] = {
    'title': 'Pyttern Visualizer API',
    'openapi': '3.0.2'
}

swagger = Swagger(app, template_file='swagger_template.yml')

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


def get_matcher(pattern_code, code, lang=None) -> Matcher:
    if lang is None:
        lang = determine_language(code)
    if lang is None:
        raise Exception("Cannot determine the language")

    current_language_processor = get_processor(lang)
    pyttern_tree = current_language_processor.generate_tree_from_code(pattern_code)
    pyttern_fsm = current_language_processor.create_pda(pyttern_tree)
    code_tree = current_language_processor.generate_tree_from_code(code)

    return Matcher(pyttern_fsm, code_tree)

class JsonListener(PytternListener):
    def __init__(self):
        self.data = []

    def step(self, _, fsm, ast, stack, variables, matches):
        state_info = (str(fsm), hash(ast))
        pos = ()
        if hasattr(ast, "start") and hasattr(ast, "stop"):
            pos = (ast.start.start, ast.stop.stop)
        if hasattr(ast, "symbol"):
            pos = (ast.symbol.start, ast.symbol.stop)

            #logger.debug(f"{ast.start.start} -> {ast.stop.stop}")
        current_matchings = [(str(fsm), hash(ast)) for fsm, ast in matches]
        logger.debug(variables)
        var_strs = [f"{var}: {PtToJson().visit(variables[var])}" for var in variables if variables[var] is not None]
        logger.debug(var_strs)
        logger.debug(stack)
        self.data.append({
            "state": state_info,
            "matches": current_matchings,
            "match": False,
            "variables": var_strs,
            "stack": stack,
            "code_pos": pos
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
@app.route("/macros")
def index():
    return send_from_directory("dist/", "index.html")


"""API endpoints"""

@app.route("/api/validate", methods=['POST'])
def validate():
    """
    Try to compile the code/pattern in the given language or try to determine the language.
    ---
    requestBody:
      required: true
      content:
        application/json:
          schema:
            type: object
            properties:
              code:
                type: string
                description: The code/pattern to validate
              lang:
                type: string
                description: The language of the code/pattern (python/java). If empty, try to determine the language.
            required:
              - code
    responses:
      '200':
        description: Validation result
        content:
          application/json:
            schema:
              type: object
              properties:
                status:
                  type: string
                  enum: [ok, error]
                message:
                  type: string
                  description: Error message if status is error

    """
    logger.debug(request.json)
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
    """
    Parse the pattern and return its tree and PDA representation.
    ---
    requestBody:
      required: true
      content:
        application/json:
          schema:
            type: object
            properties:
              code:
                type: string
                description: The code/pattern to validate
              lang:
                type: string
                description: The language of the code/pattern (python/java). If empty, try to determine the language.
            required:
              - code
    responses:
        '200':
          description: Pattern parsing result
          content:
            application/json:
              schema:
                type: object
                properties:
                  status:
                    type: string
                    enum: [ok, error]
                  message:
                    type: string
                    description: Error message if status is error
                  graph:
                    type: object
                    properties:
                      GRAPH:
                        type: object
                        $ref: '#/components/schemas/PDA'
                      TREE:
                        type: object
                        $ref: '#/components/schemas/ParseTree'

    """
    data = request.json
    pattern_code = data["code"]
    if "lang" in data:
        lang = data["lang"]
    else:
        lang = determine_language_from_code(pattern_code)
    try:
        current_language_processor = get_processor(lang)
        pattern_tree = current_language_processor.generate_tree_from_code(pattern_code)
    except Exception as e:
        return json.dumps({
            "status": "error",
            "message": str(e)
        })
    pattern_tree_graph = PtToJson().visit(pattern_tree)
    pyttern_fsm = current_language_processor.create_pda(pattern_tree)

    pattern_fsm_graph = json.loads(json.dumps(pyttern_fsm, cls=PDAEncoder))
    return json.dumps({
        "status": "ok",
        "graph": {
            "GRAPH": pattern_fsm_graph,
            "TREE": pattern_tree_graph,
        }
    })


@app.route("/api/code", methods=['POST'])
def code():
    """
    Parse the code and return its tree representation.
    ---
    requestBody:
      required: true
      content:
        application/json:
            schema:
              type: object
              properties:
                code:
                  type: string
                  description: The code to parse
                lang:
                  type: string
                  description: The language of the code (python/java). If empty, try to determine the language.
              required:
                - code
    responses:
        '200':
          description: Code parsing result
          content:
            application/json:
                schema:
                  type: object
                  properties:
                    status:
                      type: string
                      enum: [ok, error]
                    message:
                      type: string
                      description: Error message if status is error
                    graph:
                      type: object
                      properties:
                          TREE:
                            $ref: '#/components/schemas/ParseTree'
    """
    data = request.json
    code = data["code"]
    if "lang" in data:
        lang = data["lang"]
    else:
        lang = determine_language_from_code(code)
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
    """
    Match the pattern on the code and return the matching steps.
    ---
    requestBody:
      required: true
      content:
        application/json:
          schema:
            type: object
            properties:
              code:
                type: string
                description: The code to match the pattern against
              pattern:
                type: string
                description: The pattern to match
              lang:
                type: string
                description: The language of the code/pattern (python/java). If empty, try to determine the language.
            required:
              - code
              - pattern
    responses:
        '200':
            description: Match result
            content:
                application/json:
                schema:
                    type: object
                    properties:
                        status:
                            type: string
                            enum: [ok, error]
                        message:
                            type: string
                            description: Error message if status is error
                        n_steps:
                            type: integer
                            description: Number of steps taken during the match
                        state:
                            type: array
                            items:
                            type: string
                            description: Information about the initial state (FSM state, AST hash)
                        match_states:
                            type: array
                            items:
                            type: integer
                            description: List of step indices where a match was found
    """
    logger.info("Asking to start match")
    data = request.json
    code = data["code"]
    pattern = data["pattern"]
    if "lang" in data:
        lang = data["lang"]
    else:
        lang = determine_language_from_code(code)

    matcher = get_matcher(pattern, code, lang)
    json_listener = JsonListener()
    matcher.add_listener(json_listener)
    matcher.start()
    first_state = matcher.configurations[-1]
    fsm, ast, _, _, _ = first_state
    first_state_info = (str(fsm), hash(ast))
    while len(matcher.configurations) > 0:
        matcher.step()
    logger.debug(f"Number of steps: {matcher.n_step}")
    session["data"] = json_listener.data
    match_states = [i for i, data in enumerate(json_listener.data) if data["match"]]
    logger.debug(f"Matching states: {match_states}")
    return json.dumps(
        {"status": "ok", "n_steps": matcher.n_step, "state": first_state_info, "match_states": match_states})


@app.route("/api/batch_match", methods=['POST'])
def batch_match():
    """
    match a list of files with a compound pattern
    ---
    requestBody:
      required: true
      content:
        application/json:
          schema:
            type: object
            properties:
              codes:
                type: object
                description: The list of code files to match the pattern against
                additionalProperties:
                  type: string
                  description: The code content
                example:
                    file1.py: "def foo(): pass"
                    file2.py: "def bar(): pass"
              patterns:
                type: object
                description: The list of pattern files to match
                $ref: '#/components/schemas/PatternTree'
              lang:
                type: string
                description: The language of the code/pattern (python/java). If empty, try to determine the language.
            required:
                - codes
                - pattern
    responses:
      '200':
        description: Match result
        content:
          application/json:
            schema:
              type: object
              additionalProperties:
                type: boolean
                description: Whether the pattern matches the corresponding code file
              example:
                file1.py: true
                file2.py: false
    """
    logger.info("Asking to start match")
    data = request.json
    codes = data["codes"]
    patterns = data["patterns"]

    # TODO: Change main file to handle compound patterns with json rather than files
    return Response("Not implemented yet", status=501)

@app.route("/api/batch_validate", methods=['POST'])
def batch_validate():
    """
    Validate a list of pattern or code files in the given language or try to determine the language.
    ---
    requestBody:
      required: true
      content:
        application/json:
          schema:
            type: object
            properties:
              codes:
                type: object
                additionalProperties:
                  type: string
                example:
                  file1.py: "def foo(): pass"
                  file2.py: "def ?(): ?"
                description: The list of code/pattern files to validate
                lang:
                  type: string
                  description: The language of the code/pattern (python/java). If empty, try to determine the language.
                required:
                  - codes
                  - lang
    responses:
      '200':
        description: Validation result
        content:
          application/json:
            schema:
              type: object
              additionalProperties:
                type: object
                properties:
                  status:
                    type: string
                    enum: [ok, error]
                  message:
                    type: string
                    description: Error message if status is error
    """
    logger.debug(request.json)
    pattern_codes = request.json["codes"]
    pattern_lang = request.json['lang']
    if pattern_lang == "":
        return try_processors(pattern_codes[0].code)
    logger.info(f"Current lang: {pattern_lang}")
    res = {}
    try:
        lang = determine_language(pattern_lang)
        lang = "python"
        current_language_processor = get_processor(lang)
        for pattern_code in pattern_codes:
            filename = pattern_code["filename"]
            logger.debug(f"Validating pattern code: {filename}")
            code = pattern_code["code"]
            try:
                current_language_processor.generate_tree_from_code(code)
                res[filename] = {
                    "status": "ok",
                    "message": None
                }
            except PytternSyntaxException as e:
                res[filename] = {
                    "status": "error",
                    "message": {"line": e.line, "column": e.column, "symbol": e.symbol, "msg": e.msg}
                }
    except Exception as e:
        return Response(f"Error: {e}", status=500)
    return json.dumps(res)

@app.route("/api/step", methods=['POST'])
def step():
    """
    Get the information about the current step of the matching process.
    ---
    requestBody:
      required: true
      content:
        application/json:
          schema:
            type: object
            properties:
              step:
                type: integer
                description: The current step index
            required:
              - step
    responses:
      '400':
        description: Missing match data
      '200':
        description: Step information
        content:
          application/json:
            schema:
              type: object
              $ref: '#/components/schemas/Step'
    """
    if "data" not in session or session["data"] is None:
        return Response("Missing match data", status=400)
    current_step = int(request.json["step"])
    logger.info(f"Getting step {current_step}")
    current_data = session["data"][current_step]
    last_data = session["data"][current_step - 1] if current_step > 0 else None
    logger.debug(f"Current step: {current_data}")
    logger.debug(f"Last step: {last_data}")

    state_info = current_data["state"]

    current_matchings = current_data["matches"]
    previous_matchings = last_data["matches"] if last_data is not None else []

    current_stack = current_data["stack"]
    previous_stack = last_data["stack"] if last_data is not None else ""

    code_pos = current_data["code_pos"]

    if current_data["match"]:
        flash("New match found", "message")

    return json.dumps({
        "status": "ok",
        "state": state_info,
        "current_matchings": current_matchings,
        "previous_matchings": previous_matchings,
        "messages": get_flashed_messages(),
        "match": current_data["match"],
        "variables": current_data["variables"],
        "current_stack": current_stack,
        "previous_stack": previous_stack,
        "code_pos": code_pos
    })

@app.route("/api/macro", methods=['POST'])
def parse_macro():
    """
    [WIP] Parse a macro and return its PDA representation.
    ---
    requestBody:
        required: true
        content:
          application/json:
            schema:
              type: object
              additionalProperties: true
    responses:
      '200':
        description: Macro parsing result
        content:
          application/json:
            schema:
              type: object
              properties:
                status:
                  type: string
                  enum: [ok, error]
                message:
                  type: string
                  description: Error message if status is error
                macro:
                  type: object
                  properties:
                    name:
                      type: string
                      description: The name of the macro
                    code:
                      type: string
                      description: The code of the macro
                    transformations:
                      type: object
                      additionalProperties:
                        $ref: '#/components/schemas/PDA'
    """
    macro_code = request.json["code"]
    lang = Languages.PYTHON
    try:
        macros = parse_macro_from_string(macro_code, lang)
        logger.debug(f"Parsed macros: {macros}")
    except Exception as e:
        logger.error(f"Error parsing macro: {e}")
        return json.dumps({"status": "error", "message": str(e)})
    macro = macros[0] if macros else None
    if macro is None:
        return json.dumps({"status": "error", "message": "No macro found"})
    macro_json = {
        "name": macro.name,
        "code": macro_code,
        "transformations": {name: json.loads(json.dumps(pda, cls=PDAEncoder)) for name,
            pda in macro.transformations.items()}
    }
    logger.debug(f"Macro JSON: {macro_json}")
    return json.dumps({"status": "ok", "macro": macro_json})


@app.route("/api/macro", methods=['GET'])
def loaded_macro():
    """
    [WIP] Returns the loaded macros in the session.
    ---
    responses:
        '200':
            description: Loaded macros
    """
    macros = []
    for macro in loaded_macros.values():
        macro_json = {
            "name": macro.name,
            "code": macro.code,
            "transformations": {name: json.loads(json.dumps(pda, cls=PDAEncoder)) for name,
                pda in macro.transformations.items()}
        }
        macros.append(macro_json)

    return json.dumps({
        "status": "ok",
        "macros": macros
    })