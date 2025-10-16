import io
from sys import argv

from antlr4.CommonTokenStream import CommonTokenStream
from antlr4.FileStream import FileStream

from pyttern.antlr.python import Python3Parser
from pyttern.antlr.python.Python3Lexer import Python3Lexer
from pyttern.pyttern_error_listener import Python3ErrorListener

if __name__ == '__main__':
    if len(argv) != 2:
        print(f"Usage: python {argv[0]} <file>")
        exit(1)

    file = argv[1]

    with open(file, 'r') as f:
        stream = FileStream(file, encoding="utf-8")
        lexer = Python3Lexer(stream)
        stream = CommonTokenStream(lexer)
        py_parser = Python3Parser(stream)

        error = io.StringIO()

        py_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(error)
        py_parser.addErrorListener(error_listener)

        tree = py_parser.file_input()