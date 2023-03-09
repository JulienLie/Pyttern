import io

from antlr4 import FileStream, CommonTokenStream

from Matcher import Matcher
from PyHoleErrorListener import Python3ErrorListener
from PyHoleVisitor import PyHoleVisitor
from antlr.Python3Lexer import Python3Lexer
from antlr.Python3Parser import Python3Parser

def parse_pyhole(path):
    input = FileStream(path, encoding="utf-8")
    lexer = Python3Lexer(input)
    stream = CommonTokenStream(lexer)
    parser = Python3Parser(stream)

    error = io.StringIO()

    parser.removeErrorListeners()
    error_listener = Python3ErrorListener(error)
    parser.addErrorListener(error_listener)
    tree = parser.file_input()
    if len(error_listener.symbol) > 0:
        raise IOError()

    generated_tree = PyHoleVisitor().visit(tree)
    return generated_tree


def match_files(path_pattern, path_python):
    pattern = parse_pyhole(path_pattern)
    python = parse_pyhole(path_python)

    res = Matcher().match(pattern, python)

    return res
