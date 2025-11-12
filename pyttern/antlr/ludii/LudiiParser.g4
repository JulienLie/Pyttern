parser grammar LudiiParser;

options {
    tokenVocab = LudiiLexer;
}

file_input: function* EOF;

function : LPAREN NAME args RPAREN ;
args : (named_argument | argument)* ;
named_argument : NAME COLON argument;
argument: function | lst | NAME ;
lst: LBRACE argument* RBRACE;
