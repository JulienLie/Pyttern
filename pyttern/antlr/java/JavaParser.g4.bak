
/*
 [The "BSD licence"]
 Copyright (c) 2013 Terence Parr, Sam Harwell
 Copyright (c) 2017 Ivan Kochurkin (upgrade to Java 8)
 Copyright (c) 2021 Michał Lorek (upgrade to Java 11)
 Copyright (c) 2022 Michał Lorek (upgrade to Java 17)
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

parser grammar JavaParser;

options {
    tokenVocab = JavaLexer;
}

compilationUnit
    : packageDeclaration? (importDeclaration | ';')* (typeDeclaration | ';')*
    | moduleDeclaration EOF
    ;

packageDeclaration
    : annotation* PACKAGE qualifiedName ';'
    ;

importDeclaration
    : IMPORT STATIC? qualifiedName ('.' '*')? ';'
    ;

typeDeclaration
    : classOrInterfaceModifier* (
        classDeclaration
        | enumDeclaration
        | interfaceDeclaration
        | annotationTypeDeclaration
        | recordDeclaration
    )
    ;

modifier
    : classOrInterfaceModifier
    | NATIVE
    | SYNCHRONIZED
    | TRANSIENT
    | VOLATILE
    ;

classOrInterfaceModifier
    : annotation
    | PUBLIC
    | PROTECTED
    | PRIVATE
    | STATIC
    | ABSTRACT
    | FINAL // FINAL for class only -- does not apply to interfaces
    | STRICTFP
    | SEALED     // Java17
    | NON_SEALED // Java17
    ;

variableModifier
    : FINAL
    | annotation
    ;

classDeclaration
    : CLASS (identifier | simple_wildcard | var_wildcard) typeParameters? (EXTENDS typeType)? (IMPLEMENTS typeList)? (
        PERMITS typeList
    )? // Java17
    classBody
    ;

typeParameters
    : '<' typeParameter (',' typeParameter)* '>'
    ;

typeParameter
    : annotation* (identifier | simple_wildcard | var_wildcard) (EXTENDS annotation* typeBound)?
    ;

typeBound
    : typeType ('&' typeType)*
    ;

enumDeclaration
    : ENUM (identifier | simple_wildcard | var_wildcard) (IMPLEMENTS typeList)? '{' enumConstants? ','? enumBodyDeclarations? '}'
    ;

enumConstants
    : enumConstant (',' enumConstant)*
    ;

enumConstant
    : annotation* (identifier | simple_wildcard | var_wildcard) arguments? classBody?
    ;

enumBodyDeclarations
    : ';' classBodyDeclaration*
    ;

interfaceDeclaration
    : INTERFACE (identifier | simple_wildcard | var_wildcard) typeParameters? (EXTENDS typeList)? (PERMITS typeList)? interfaceBody
    ;

classBody
    : '{' classBodyDeclaration* '}'
    ;

interfaceBody
    : '{' interfaceBodyDeclaration* '}'
    ;

classBodyDeclaration
    : ';'
    | STATIC? block
    | modifier* memberDeclaration
    ;

memberDeclaration
    : recordDeclaration //Java17
    | methodDeclaration
    | genericMethodDeclaration
    | fieldDeclaration
    | constructorDeclaration
    | genericConstructorDeclaration
    | interfaceDeclaration
    | annotationTypeDeclaration
    | classDeclaration
    | enumDeclaration
    ;

/* We use rule this even for void methods which cannot have [] after parameters.
   This simplifies grammar and we can consider void to be a type, which
   renders the [] matching as a context-sensitive issue or a semantic check
   for invalid return type after parsing.
 */
methodDeclaration
    : typeTypeOrVoid (identifier | simple_wildcard | var_wildcard) formalParameters ('[' ']')* (THROWS qualifiedNameList)? methodBody
    ;

methodBody
    : block
    | ';'
    ;

typeTypeOrVoid
    : typeType
    | VOID
    ;

genericMethodDeclaration
    : typeParameters methodDeclaration
    ;

genericConstructorDeclaration
    : typeParameters constructorDeclaration
    ;

constructorDeclaration
    : (identifier | simple_wildcard | var_wildcard) formalParameters (THROWS qualifiedNameList)? constructorBody = block
    ;

compactConstructorDeclaration
    : modifier* identifier constructorBody = block
    ;

fieldDeclaration
    : typeType variableDeclarators ';'
    ;

interfaceBodyDeclaration
    : modifier* interfaceMemberDeclaration
    | ';'
    ;

interfaceMemberDeclaration
    : recordDeclaration // Java17
    | constDeclaration
    | interfaceMethodDeclaration
    | genericInterfaceMethodDeclaration
    | interfaceDeclaration
    | annotationTypeDeclaration
    | classDeclaration
    | enumDeclaration
    ;

constDeclaration
    : typeType constantDeclarator (',' constantDeclarator)* ';'
    ;

constantDeclarator
    : (identifier | simple_wildcard | var_wildcard) ('[' ']')* '=' variableInitializer
    ;

// Early versions of Java allows brackets after the method name, eg.
// public int[] return2DArray() [] { ... }
// is the same as
// public int[][] return2DArray() { ... }
interfaceMethodDeclaration
    : interfaceMethodModifier* interfaceCommonBodyDeclaration
    ;

// Java8
interfaceMethodModifier
    : annotation
    | PUBLIC
    | ABSTRACT
    | DEFAULT
    | STATIC
    | STRICTFP
    ;

genericInterfaceMethodDeclaration
    : interfaceMethodModifier* typeParameters interfaceCommonBodyDeclaration
    ;

interfaceCommonBodyDeclaration
    : annotation* typeTypeOrVoid (identifier | simple_wildcard | var_wildcard) formalParameters ('[' ']')* (THROWS qualifiedNameList)? methodBody
    ;

variableDeclarators
    : variableDeclarator (',' variableDeclarator)*
    ;

variableDeclarator
    : variableDeclaratorId ('=' variableInitializer)?
    ;

variableDeclaratorId
    : identifier ('[' ']')*
    | simple_wildcard
    | var_wildcard
    ;

variableInitializer
    : arrayInitializer
    | expression
    ;

arrayInitializer
    : '{' (variableInitializer (',' variableInitializer)* ','?)? '}'
    ;

classOrInterfaceType
    : (identifier typeArguments? '.')* typeIdentifier typeArguments? // TODO ?
    ;

typeArgument
    : typeType
    | annotation* '?' ((EXTENDS | SUPER) typeType)?
    ;

qualifiedNameList
    : qualifiedName (',' qualifiedName)*
    ;

formalParameters
    : '(' (
        receiverParameter?
        | receiverParameter (',' formalParameterList)?
        | formalParameterList?
    ) ')'
    ;

receiverParameter
    : typeType (identifier '.')* THIS // TODO ?
    ;

formalParameterList
    : (list_wildcard | formalParameter) (',' (list_wildcard | formalParameter))* (',' lastFormalParameter)?
    | lastFormalParameter
    ;

formalParameter
    : variableModifier* typeType variableDeclaratorId
    ;

lastFormalParameter
    : variableModifier* typeType annotation* '...' variableDeclaratorId
    ;

// local variable type inference
lambdaLVTIList
    : lambdaLVTIParameter (',' lambdaLVTIParameter)*
    ;

lambdaLVTIParameter
    : variableModifier* VAR identifier
    ;

qualifiedName
    : identifier ('.' identifier)*
    ;

literal
    : integerLiteral
    | floatLiteral
    | CHAR_LITERAL
    | STRING_LITERAL
    | BOOL_LITERAL
    | NULL_LITERAL
    | TEXT_BLOCK // Java17
    ;

integerLiteral
    : DECIMAL_LITERAL
    | HEX_LITERAL
    | OCT_LITERAL
    | BINARY_LITERAL
    ;

floatLiteral
    : FLOAT_LITERAL
    | HEX_FLOAT_LITERAL
    ;

// ANNOTATIONS
altAnnotationQualifiedName
    : (identifier DOT)* '@' identifier
    ;

annotation
    : ('@' qualifiedName | altAnnotationQualifiedName) (
        '(' ( elementValuePairs | elementValue)? ')'
    )?
    ;

elementValuePairs
    : elementValuePair (',' elementValuePair)*
    ;

elementValuePair
    : identifier '=' elementValue
    ;

elementValue
    : expression
    | annotation
    | elementValueArrayInitializer
    ;

elementValueArrayInitializer
    : '{' (elementValue (',' elementValue)*)? ','? '}'
    ;

annotationTypeDeclaration
    : '@' INTERFACE identifier annotationTypeBody
    ;

annotationTypeBody
    : '{' annotationTypeElementDeclaration* '}'
    ;

annotationTypeElementDeclaration
    : modifier* annotationTypeElementRest
    | ';' // this is not allowed by the grammar, but apparently allowed by the actual compiler
    ;

annotationTypeElementRest
    : typeType annotationMethodOrConstantRest ';'
    | classDeclaration ';'?
    | interfaceDeclaration ';'?
    | enumDeclaration ';'?
    | annotationTypeDeclaration ';'?
    | recordDeclaration ';'? // Java17
    ;

annotationMethodOrConstantRest
    : annotationMethodRest
    | annotationConstantRest
    ;

annotationMethodRest
    : identifier '(' ')' defaultValue?
    ;

annotationConstantRest
    : variableDeclarators
    ;

defaultValue
    : DEFAULT elementValue
    ;

// MODULES - Java9

moduleDeclaration
    : OPEN? MODULE qualifiedName moduleBody
    ;

moduleBody
    : '{' moduleDirective* '}'
    ;

moduleDirective
    : REQUIRES requiresModifier* qualifiedName ';'
    | EXPORTS qualifiedName (TO qualifiedName)? ';'
    | OPENS qualifiedName (TO qualifiedName)? ';'
    | USES qualifiedName ';'
    | PROVIDES qualifiedName WITH qualifiedName ';'
    ;

requiresModifier
    : TRANSITIVE
    | STATIC
    ;

// RECORDS - Java 17

recordDeclaration
    : RECORD (identifier | simple_wildcard | var_wildcard) typeParameters? recordHeader (IMPLEMENTS typeList)? recordBody
    ;

recordHeader
    : '(' recordComponentList? ')'
    ;

recordComponentList
    : recordComponent (',' recordComponent)*
    ;

recordComponent
    : typeType (identifier | simple_wildcard | var_wildcard)
    ;

recordBody
    : '{' (classBodyDeclaration | compactConstructorDeclaration)* '}'
    ;

// STATEMENTS / BLOCKS

block
    : '{' blockStatement* '}'
    ;

blockStatement
    : localVariableDeclaration ';'
    | localTypeDeclaration
    | statement
    ;

localVariableDeclaration
    : variableModifier* (VAR identifier '=' expression | typeType variableDeclarators)
    ;

identifier
    : IDENTIFIER
    | MODULE
    | OPEN
    | REQUIRES
    | EXPORTS
    | OPENS
    | TO
    | USES
    | PROVIDES
    | WITH
    | TRANSITIVE
    | YIELD
    | SEALED
    | PERMITS
    | RECORD
    | VAR // TODO: put wildcard here to simplify things?
    ;

typeIdentifier // Identifiers that are not restricted for type declarations
    : IDENTIFIER
    | MODULE
    | OPEN
    | REQUIRES
    | EXPORTS
    | OPENS
    | TO
    | USES
    | PROVIDES
    | WITH
    | TRANSITIVE
    | SEALED
    | PERMITS
    | RECORD
    ;

localTypeDeclaration
    : classOrInterfaceModifier* (classDeclaration | interfaceDeclaration | recordDeclaration)
    ;

statement
    : compound_stmt
    | ASSERT expression (':' expression)? ';'
    | RETURN expression? ';'
    | THROW expression ';'
    | BREAK identifier? ';' // TODO: add wildcard here?
    | CONTINUE identifier? ';'
    | YIELD expression ';' // Java17
    | SEMI
    | statementExpression = expression ';'
    | switchExpression ';'? // Java17
    | stmt_wildcard
    ;

compound_stmt
    : compound_wildcard
    | blockLabel = block
    | IF parExpression statement (ELSE statement)?
    | FOR '(' forControl ')' statement
    | WHILE parExpression statement
    | DO statement WHILE parExpression ';'
    | TRY block (catchClause+ finallyBlock? | finallyBlock)
    | TRY resourceSpecification block catchClause* finallyBlock?
    | SWITCH parExpression '{' switchBlockStatementGroup* switchLabel* '}'
    | SYNCHRONIZED parExpression block
    | identifierLabel = identifier ':' statement
    ;

catchClause
    : CATCH '(' variableModifier* catchType identifier ')' block // TODO: add wildcard here?
    ;

catchType
    : qualifiedName ('|' qualifiedName)*
    ;

finallyBlock
    : FINALLY block
    ;

resourceSpecification
    : '(' resources ';'? ')'
    ;

resources
    : resource (';' resource)*
    ;

resource
    : variableModifier* (classOrInterfaceType variableDeclaratorId | VAR identifier) '=' expression // TODO: add wildcard here?
    | qualifiedName
    ;

/** Matches cases then statements, both of which are mandatory.
 *  To handle empty cases at the end, we add switchLabel* to statement.
 */
switchBlockStatementGroup
    : switchLabel+ blockStatement+
    ;

switchLabel// TODO: add wildcard here?
    : CASE (
        constantExpression = expression
        | enumConstantName = IDENTIFIER
        | typeType varName = identifier
    ) ':'
    | DEFAULT ':'
    ;

forControl
    : enhancedForControl
    | forInit? ';' expression? ';' forUpdate = expressionList?
    ;

forInit
    : localVariableDeclaration
    | expressionList
    ;

enhancedForControl
    : variableModifier* (typeType | VAR) variableDeclaratorId ':' expression
    ;

// EXPRESSIONS

parExpression
    : '(' expression ')'
    ;

expressionList
    : (list_wildcard | expression) (',' (list_wildcard | expression))*
    ;

methodCall
    : (identifier | THIS | SUPER) arguments // TODO: add wildcard here?
    ;

expression
    // Expression order in accordance with https://introcs.cs.princeton.edu/java/11precedence/
    // Level 16, Primary, array and member access
    : primary
    | expression '[' expression ']'
    | expression bop = '.' (
        identifier
        | methodCall
        | THIS
        | NEW nonWildcardTypeArguments? innerCreator
        | SUPER superSuffix
        | explicitGenericInvocation
        | var_wildcard
        | simple_wildcard 
    )
    // Method calls and method references are part of primary, and hence level 16 precedence
    | methodCall
    | expression '::' typeArguments? identifier
    | typeType '::' (typeArguments? identifier | NEW)
    | classType '::' typeArguments? NEW
    | switchExpression // Java17

    // Level 15 Post-increment/decrement operators
    | expression postfix = ('++' | '--')

    // Level 14, Unary operators
    | prefix = ('+' | '-' | '++' | '--' | '~' | '!') expression

    // Level 13 Cast and object creation
    | '(' annotation* typeType ('&' typeType)* ')' expression
    | NEW creator

    // Level 12 to 1, Remaining operators
    | expression bop = ('*' | '/' | '%') expression           // Level 12, Multiplicative operators
    | expression bop = ('+' | '-') expression                 // Level 11, Additive operators
    | expression ('<' '<' | '>' '>' '>' | '>' '>') expression // Level 10, Shift operators
    | expression bop = ('<=' | '>=' | '>' | '<') expression   // Level 9, Relational operators
    | expression bop = INSTANCEOF (simple_wildcard | var_wildcard | typeType | pattern)
    | expression bop = ('==' | '!=') expression                      // Level 8, Equality Operators
    | expression bop = '&' expression                                // Level 7, Bitwise AND
    | expression bop = '^' expression                                // Level 6, Bitwise XOR
    | expression bop = '|' expression                                // Level 5, Bitwise OR
    | expression bop = '&&' expression                               // Level 4, Logic AND
    | expression bop = '||' expression                               // Level 3, Logic OR
    | <assoc = right> expression bop = '?' expression ':' expression // Level 2, Ternary
    // Level 1, Assignment
    | <assoc = right> expression bop = (
        '='
        | '+='
        | '-='
        | '*='
        | '/='
        | '&='
        | '|='
        | '^='
        | '>>='
        | '>>>='
        | '<<='
        | '%='
    ) expression

    // Level 0, Lambda Expression
    | lambdaExpression // Java8

    | expr_wildcard
    ;

// Java17
pattern
    : variableModifier* typeType annotation* identifier
    ;

// Java8
lambdaExpression
    : lambdaParameters '->' lambdaBody
    ;

// Java8
lambdaParameters
    : identifier
    | '(' formalParameterList? ')'
    | '(' identifier (',' identifier)* ')'
    | '(' lambdaLVTIList? ')'
    | simple_wildcard
    | var_wildcard
    ;

// Java8
lambdaBody
    : expression
    | block
    ;

primary
    : '(' expression ')'
    | THIS
    | SUPER
    | literal
    | identifier
    | typeTypeOrVoid '.' CLASS
    | nonWildcardTypeArguments (explicitGenericInvocationSuffix | THIS arguments)
    ;

// Java17
switchExpression
    : SWITCH parExpression '{' switchLabeledRule* '}'
    ;

// Java17
switchLabeledRule
    : CASE (expressionList | NULL_LITERAL | guardedPattern) (ARROW | COLON) switchRuleOutcome
    | DEFAULT (ARROW | COLON) switchRuleOutcome
    ;

// Java17
guardedPattern
    : '(' guardedPattern ')'
    | variableModifier* typeType annotation* identifier ('&&' expression)*
    | guardedPattern '&&' expression
    ;

// Java17
switchRuleOutcome
    : block
    | blockStatement*
    ;

classType
    : (classOrInterfaceType '.')? annotation* identifier typeArguments? // TODO: add wildcard here?
    ;

creator
    : nonWildcardTypeArguments? createdName classCreatorRest
    | createdName arrayCreatorRest
    ;

createdName
    : identifier typeArgumentsOrDiamond? ('.' identifier typeArgumentsOrDiamond?)* // TODO: add wildcard here?
    | primitiveType
    ;

innerCreator
    : identifier nonWildcardTypeArgumentsOrDiamond? classCreatorRest
    ;

arrayCreatorRest
    : ('[' ']')+ arrayInitializer
    | ('[' expression ']')+ ('[' ']')*
    ;

classCreatorRest
    : arguments classBody?
    ;

explicitGenericInvocation
    : nonWildcardTypeArguments explicitGenericInvocationSuffix
    ;

typeArgumentsOrDiamond
    : '<' '>'
    | typeArguments
    ;

nonWildcardTypeArgumentsOrDiamond
    : '<' '>'
    | nonWildcardTypeArguments
    ;

nonWildcardTypeArguments
    : '<' typeList '>'
    ;

typeList
    : typeType (',' typeType)*
    ;

typeType
    : annotation* (classOrInterfaceType | primitiveType) (annotation* '[' ']')*
    | simple_wildcard
    | var_wildcard
    ;

primitiveType
    : BOOLEAN
    | CHAR
    | BYTE
    | SHORT
    | INT
    | LONG
    | FLOAT
    | DOUBLE
    ;

typeArguments
    : '<' typeArgument (',' typeArgument)* '>'
    ;

superSuffix
    : arguments
    | '.' typeArguments? identifier arguments?
    ;

explicitGenericInvocationSuffix
    : SUPER superSuffix
    | identifier arguments
    ;

arguments
    : '(' (expressionList? | list_wildcard) ')'
    ;

// Syntax of wildcards
simple_wildcard: WILDCARD_SPACE;
var_wildcard: WILDCARD identifier;
list_wildcard: WILDCARD '*';
contains_wildcard: WILDCARD '<' (simple_wildcard | var_wildcard | contains_wildcard | expression) '>';
simple_compound_wildcard: WILDCARD wildcard_number? block;
multiple_compound_wildcard: WILDCARD '*' block;
wildcard_number: '{' NUMBER (',' | ',' NUMBER)? '}';

// Composite wildcards
stmt_wildcard: (simple_wildcard | var_wildcard | contains_wildcard);
expr_wildcard:  var_wildcard | contains_wildcard | simple_wildcard;
compound_wildcard: simple_compound_wildcard | multiple_compound_wildcard;