lexer grammar LudiiLexer;

// Separators
LPAREN       : '(' ;
RPAREN       : ')' ;
COLON        : ':' ;
COMMA        : ',' ;
SEMI_COLON   : ';' ;
LBRACE       : '{' ;
RBRACE       : '}' ;

WS           : [ \t\r\n\u000C]+ -> skip;
COMMENT      : '/*' .*? '*/'    -> skip;
LINE_COMMENT : '//' ~[\r\n]*    -> skip;

NAME         : LITERAL | STRING | NUMBER | REFERENCE;
STRING       : '"' ( '\\' . | ~["\\\r\n] )* '"';
LITERAL      : (~["\\\r\n(){}])+ ;
NUMBER       : [0-9]+ ('.' [0-9]+)? ;
REFERENCE    : '<' (~["\\\r\n])* '>' ;
