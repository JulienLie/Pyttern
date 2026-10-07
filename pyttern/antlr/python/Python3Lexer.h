#include "Python3LexerBase.h"

// Generated from Python3Lexer.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"




class  Python3Lexer : public Python3LexerBase {
public:
  enum {
    INDENT = 1, DEDENT = 2, STRING = 3, NUMBER = 4, INTEGER = 5, AND = 6, 
    AS = 7, ASSERT = 8, ASYNC = 9, AWAIT = 10, BREAK = 11, CASE = 12, CLASS = 13, 
    CONTINUE = 14, DEF = 15, DEL = 16, ELIF = 17, ELSE = 18, EXCEPT = 19, 
    FALSE = 20, FINALLY = 21, FOR = 22, FROM = 23, GLOBAL = 24, IF = 25, 
    IMPORT = 26, IN = 27, IS = 28, LAMBDA = 29, MATCH = 30, NONE = 31, NONLOCAL = 32, 
    NOT = 33, OR = 34, PASS = 35, RAISE = 36, RETURN = 37, TRUE = 38, TRY = 39, 
    UNDERSCORE = 40, WHILE = 41, WITH = 42, YIELD = 43, STRICT = 44, DEFINE = 45, 
    NEWLINE = 46, NAME = 47, STRING_LITERAL = 48, BYTES_LITERAL = 49, DECIMAL_INTEGER = 50, 
    OCT_INTEGER = 51, HEX_INTEGER = 52, BIN_INTEGER = 53, FLOAT_NUMBER = 54, 
    IMAG_NUMBER = 55, DOT = 56, ELLIPSIS = 57, STAR = 58, OPEN_PAREN = 59, 
    CLOSE_PAREN = 60, COMMA = 61, COLON = 62, SEMI_COLON = 63, POWER = 64, 
    ASSIGN = 65, OPEN_BRACK = 66, CLOSE_BRACK = 67, OR_OP = 68, XOR = 69, 
    AND_OP = 70, LEFT_SHIFT = 71, RIGHT_SHIFT = 72, ADD = 73, MINUS = 74, 
    DIV = 75, MOD = 76, IDIV = 77, NOT_OP = 78, OPEN_BRACE = 79, CLOSE_BRACE = 80, 
    LESS_THAN = 81, GREATER_THAN = 82, EQUALS = 83, GT_EQ = 84, LT_EQ = 85, 
    NOT_EQ_1 = 86, NOT_EQ_2 = 87, AT = 88, ARROW = 89, ADD_ASSIGN = 90, 
    SUB_ASSIGN = 91, MULT_ASSIGN = 92, AT_ASSIGN = 93, DIV_ASSIGN = 94, 
    MOD_ASSIGN = 95, AND_ASSIGN = 96, OR_ASSIGN = 97, XOR_ASSIGN = 98, LEFT_SHIFT_ASSIGN = 99, 
    RIGHT_SHIFT_ASSIGN = 100, POWER_ASSIGN = 101, IDIV_ASSIGN = 102, WILDCARD = 103, 
    BALISE = 104, SUB_PATTERN = 105, NOT_WILDCARD = 106, SKIP_ = 107, UNKNOWN_CHAR = 108
  };

  explicit Python3Lexer(antlr4::CharStream *input);

  ~Python3Lexer() override;


  std::string getGrammarFileName() const override;

  const std::vector<std::string>& getRuleNames() const override;

  const std::vector<std::string>& getChannelNames() const override;

  const std::vector<std::string>& getModeNames() const override;

  const antlr4::dfa::Vocabulary& getVocabulary() const override;

  antlr4::atn::SerializedATNView getSerializedATN() const override;

  const antlr4::atn::ATN& getATN() const override;

  void action(antlr4::RuleContext *context, size_t ruleIndex, size_t actionIndex) override;

  bool sempred(antlr4::RuleContext *_localctx, size_t ruleIndex, size_t predicateIndex) override;

  // By default the static state used to implement the lexer is lazily initialized during the first
  // call to the constructor. You can call this function if you wish to initialize the static state
  // ahead of time.
  static void initialize();

private:

  // Individual action functions triggered by action() above.
  void NEWLINEAction(antlr4::RuleContext *context, size_t actionIndex);
  void OPEN_PARENAction(antlr4::RuleContext *context, size_t actionIndex);
  void CLOSE_PARENAction(antlr4::RuleContext *context, size_t actionIndex);
  void OPEN_BRACKAction(antlr4::RuleContext *context, size_t actionIndex);
  void CLOSE_BRACKAction(antlr4::RuleContext *context, size_t actionIndex);
  void OPEN_BRACEAction(antlr4::RuleContext *context, size_t actionIndex);
  void CLOSE_BRACEAction(antlr4::RuleContext *context, size_t actionIndex);

  // Individual semantic predicate functions triggered by sempred() above.
  bool NEWLINESempred(antlr4::RuleContext *_localctx, size_t predicateIndex);

};

