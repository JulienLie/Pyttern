parser grammar LudiiParser;

options {
    tokenVocab = LudiiLexer;
}

file_input: function* EOF;

function : LPAREN NAME args RPAREN ;
args : (argument | named_argument)* ;
named_argument : NAME COLON argument;
argument: function | lst | NAME ;
lst: LBRACE args RBRACE;
