// Generated from /home/julien/Documents/phd/Pyttern/pyttern/antlr/python/Python3Parser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Python3Parser extends Python3ParserBase {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INDENT=1, DEDENT=2, STRING=3, NUMBER=4, INTEGER=5, AND=6, AS=7, ASSERT=8, 
		ASYNC=9, AWAIT=10, BREAK=11, CASE=12, CLASS=13, CONTINUE=14, DEF=15, DEL=16, 
		ELIF=17, ELSE=18, EXCEPT=19, FALSE=20, FINALLY=21, FOR=22, FROM=23, GLOBAL=24, 
		IF=25, IMPORT=26, IN=27, IS=28, LAMBDA=29, MATCH=30, NONE=31, NONLOCAL=32, 
		NOT=33, OR=34, PASS=35, RAISE=36, RETURN=37, TRUE=38, TRY=39, UNDERSCORE=40, 
		WHILE=41, WITH=42, YIELD=43, STRICT=44, DEFINE=45, NEWLINE=46, NAME=47, 
		STRING_LITERAL=48, BYTES_LITERAL=49, DECIMAL_INTEGER=50, OCT_INTEGER=51, 
		HEX_INTEGER=52, BIN_INTEGER=53, FLOAT_NUMBER=54, IMAG_NUMBER=55, DOT=56, 
		ELLIPSIS=57, STAR=58, OPEN_PAREN=59, CLOSE_PAREN=60, COMMA=61, COLON=62, 
		SEMI_COLON=63, POWER=64, ASSIGN=65, OPEN_BRACK=66, CLOSE_BRACK=67, OR_OP=68, 
		XOR=69, AND_OP=70, LEFT_SHIFT=71, RIGHT_SHIFT=72, ADD=73, MINUS=74, DIV=75, 
		MOD=76, IDIV=77, NOT_OP=78, OPEN_BRACE=79, CLOSE_BRACE=80, LESS_THAN=81, 
		GREATER_THAN=82, EQUALS=83, GT_EQ=84, LT_EQ=85, NOT_EQ_1=86, NOT_EQ_2=87, 
		AT=88, ARROW=89, ADD_ASSIGN=90, SUB_ASSIGN=91, MULT_ASSIGN=92, AT_ASSIGN=93, 
		DIV_ASSIGN=94, MOD_ASSIGN=95, AND_ASSIGN=96, OR_ASSIGN=97, XOR_ASSIGN=98, 
		LEFT_SHIFT_ASSIGN=99, RIGHT_SHIFT_ASSIGN=100, POWER_ASSIGN=101, IDIV_ASSIGN=102, 
		WILDCARD=103, BALISE=104, SUB_PATTERN=105, SKIP_=106, UNKNOWN_CHAR=107;
	public static final int
		RULE_single_input = 0, RULE_file_input = 1, RULE_eval_input = 2, RULE_macro_input = 3, 
		RULE_decorator = 4, RULE_decorators = 5, RULE_decorated = 6, RULE_async_funcdef = 7, 
		RULE_funcdef = 8, RULE_parameters = 9, RULE_tfpdef = 10, RULE_varargslist = 11, 
		RULE_vfpdef = 12, RULE_stmt = 13, RULE_simple_stmts = 14, RULE_simple_stmt = 15, 
		RULE_expr_stmt = 16, RULE_annassign = 17, RULE_testlist_star_expr = 18, 
		RULE_augassign = 19, RULE_del_stmt = 20, RULE_pass_stmt = 21, RULE_flow_stmt = 22, 
		RULE_break_stmt = 23, RULE_continue_stmt = 24, RULE_return_stmt = 25, 
		RULE_yield_stmt = 26, RULE_raise_stmt = 27, RULE_import_stmt = 28, RULE_import_name = 29, 
		RULE_import_from = 30, RULE_import_as_name = 31, RULE_dotted_as_name = 32, 
		RULE_import_as_names = 33, RULE_dotted_as_names = 34, RULE_dotted_name = 35, 
		RULE_global_stmt = 36, RULE_nonlocal_stmt = 37, RULE_assert_stmt = 38, 
		RULE_compound_stmt = 39, RULE_async_stmt = 40, RULE_if_stmt = 41, RULE_while_stmt = 42, 
		RULE_for_stmt = 43, RULE_try_stmt = 44, RULE_with_stmt = 45, RULE_with_item = 46, 
		RULE_except_clause = 47, RULE_block = 48, RULE_match_stmt = 49, RULE_subject_expr = 50, 
		RULE_star_named_expressions = 51, RULE_star_named_expression = 52, RULE_case_block = 53, 
		RULE_guard = 54, RULE_patterns = 55, RULE_pattern = 56, RULE_as_pattern = 57, 
		RULE_or_pattern = 58, RULE_closed_pattern = 59, RULE_literal_pattern = 60, 
		RULE_literal_expr = 61, RULE_complex_number = 62, RULE_signed_number = 63, 
		RULE_signed_real_number = 64, RULE_real_number = 65, RULE_imaginary_number = 66, 
		RULE_capture_pattern = 67, RULE_pattern_capture_target = 68, RULE_wildcard_pattern = 69, 
		RULE_value_pattern = 70, RULE_attr = 71, RULE_name_or_attr = 72, RULE_group_pattern = 73, 
		RULE_sequence_pattern = 74, RULE_open_sequence_pattern = 75, RULE_maybe_sequence_pattern = 76, 
		RULE_maybe_star_pattern = 77, RULE_star_pattern = 78, RULE_mapping_pattern = 79, 
		RULE_items_pattern = 80, RULE_key_value_pattern = 81, RULE_double_star_pattern = 82, 
		RULE_class_pattern = 83, RULE_positional_patterns = 84, RULE_keyword_patterns = 85, 
		RULE_keyword_pattern = 86, RULE_test = 87, RULE_test_nocond = 88, RULE_lambdef = 89, 
		RULE_lambdef_nocond = 90, RULE_or_test = 91, RULE_and_test = 92, RULE_not_test = 93, 
		RULE_comparison = 94, RULE_comp_op = 95, RULE_star_expr = 96, RULE_expr = 97, 
		RULE_atom_expr = 98, RULE_atom = 99, RULE_name = 100, RULE_testlist_comp = 101, 
		RULE_trailer = 102, RULE_subscriptlist = 103, RULE_subscript_ = 104, RULE_sliceop = 105, 
		RULE_exprlist = 106, RULE_testlist = 107, RULE_dictorsetmaker = 108, RULE_classdef = 109, 
		RULE_arglist = 110, RULE_argument = 111, RULE_comp_iter = 112, RULE_comp_for = 113, 
		RULE_comp_if = 114, RULE_encoding_decl = 115, RULE_yield_expr = 116, RULE_yield_arg = 117, 
		RULE_strings = 118, RULE_wildcard_number = 119, RULE_stmt_wildcard = 120, 
		RULE_expr_wildcard = 121, RULE_atom_wildcard = 122, RULE_simple_wildcard = 123, 
		RULE_number_wildcard = 124, RULE_double_wildcard = 125, RULE_var_wildcard = 126, 
		RULE_contains_wildcard = 127, RULE_compound_wildcard = 128, RULE_simple_compound_wildcard = 129, 
		RULE_multiple_compound_wildcard = 130, RULE_list_wildcard = 131, RULE_macro_call = 132, 
		RULE_macro_stmts = 133, RULE_macro_def = 134, RULE_macro = 135, RULE_macro_args = 136, 
		RULE_macro_arg = 137, RULE_simple_macro = 138, RULE_compound_macro = 139, 
		RULE_transformation = 140;
	private static String[] makeRuleNames() {
		return new String[] {
			"single_input", "file_input", "eval_input", "macro_input", "decorator", 
			"decorators", "decorated", "async_funcdef", "funcdef", "parameters", 
			"tfpdef", "varargslist", "vfpdef", "stmt", "simple_stmts", "simple_stmt", 
			"expr_stmt", "annassign", "testlist_star_expr", "augassign", "del_stmt", 
			"pass_stmt", "flow_stmt", "break_stmt", "continue_stmt", "return_stmt", 
			"yield_stmt", "raise_stmt", "import_stmt", "import_name", "import_from", 
			"import_as_name", "dotted_as_name", "import_as_names", "dotted_as_names", 
			"dotted_name", "global_stmt", "nonlocal_stmt", "assert_stmt", "compound_stmt", 
			"async_stmt", "if_stmt", "while_stmt", "for_stmt", "try_stmt", "with_stmt", 
			"with_item", "except_clause", "block", "match_stmt", "subject_expr", 
			"star_named_expressions", "star_named_expression", "case_block", "guard", 
			"patterns", "pattern", "as_pattern", "or_pattern", "closed_pattern", 
			"literal_pattern", "literal_expr", "complex_number", "signed_number", 
			"signed_real_number", "real_number", "imaginary_number", "capture_pattern", 
			"pattern_capture_target", "wildcard_pattern", "value_pattern", "attr", 
			"name_or_attr", "group_pattern", "sequence_pattern", "open_sequence_pattern", 
			"maybe_sequence_pattern", "maybe_star_pattern", "star_pattern", "mapping_pattern", 
			"items_pattern", "key_value_pattern", "double_star_pattern", "class_pattern", 
			"positional_patterns", "keyword_patterns", "keyword_pattern", "test", 
			"test_nocond", "lambdef", "lambdef_nocond", "or_test", "and_test", "not_test", 
			"comparison", "comp_op", "star_expr", "expr", "atom_expr", "atom", "name", 
			"testlist_comp", "trailer", "subscriptlist", "subscript_", "sliceop", 
			"exprlist", "testlist", "dictorsetmaker", "classdef", "arglist", "argument", 
			"comp_iter", "comp_for", "comp_if", "encoding_decl", "yield_expr", "yield_arg", 
			"strings", "wildcard_number", "stmt_wildcard", "expr_wildcard", "atom_wildcard", 
			"simple_wildcard", "number_wildcard", "double_wildcard", "var_wildcard", 
			"contains_wildcard", "compound_wildcard", "simple_compound_wildcard", 
			"multiple_compound_wildcard", "list_wildcard", "macro_call", "macro_stmts", 
			"macro_def", "macro", "macro_args", "macro_arg", "simple_macro", "compound_macro", 
			"transformation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'and'", "'as'", "'assert'", "'async'", 
			"'await'", "'break'", "'case'", "'class'", "'continue'", "'def'", "'del'", 
			"'elif'", "'else'", "'except'", "'False'", "'finally'", "'for'", "'from'", 
			"'global'", "'if'", "'import'", "'in'", "'is'", "'lambda'", "'match'", 
			"'None'", "'nonlocal'", "'not'", "'or'", "'pass'", "'raise'", "'return'", 
			"'True'", "'try'", "'_'", "'while'", "'with'", "'yield'", "'strict'", 
			"'define'", null, null, null, null, null, null, null, null, null, null, 
			"'.'", "'...'", "'*'", "'('", "')'", "','", "':'", "';'", "'**'", "'='", 
			"'['", "']'", "'|'", "'^'", "'&'", "'<<'", "'>>'", "'+'", "'-'", "'/'", 
			"'%'", "'//'", "'~'", "'{'", "'}'", "'<'", "'>'", "'=='", "'>='", "'<='", 
			"'<>'", "'!='", "'@'", "'->'", "'+='", "'-='", "'*='", "'@='", "'/='", 
			"'%='", "'&='", "'|='", "'^='", "'<<='", "'>>='", "'**='", "'//='", "'?'", 
			"'$#'", "'$'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INDENT", "DEDENT", "STRING", "NUMBER", "INTEGER", "AND", "AS", 
			"ASSERT", "ASYNC", "AWAIT", "BREAK", "CASE", "CLASS", "CONTINUE", "DEF", 
			"DEL", "ELIF", "ELSE", "EXCEPT", "FALSE", "FINALLY", "FOR", "FROM", "GLOBAL", 
			"IF", "IMPORT", "IN", "IS", "LAMBDA", "MATCH", "NONE", "NONLOCAL", "NOT", 
			"OR", "PASS", "RAISE", "RETURN", "TRUE", "TRY", "UNDERSCORE", "WHILE", 
			"WITH", "YIELD", "STRICT", "DEFINE", "NEWLINE", "NAME", "STRING_LITERAL", 
			"BYTES_LITERAL", "DECIMAL_INTEGER", "OCT_INTEGER", "HEX_INTEGER", "BIN_INTEGER", 
			"FLOAT_NUMBER", "IMAG_NUMBER", "DOT", "ELLIPSIS", "STAR", "OPEN_PAREN", 
			"CLOSE_PAREN", "COMMA", "COLON", "SEMI_COLON", "POWER", "ASSIGN", "OPEN_BRACK", 
			"CLOSE_BRACK", "OR_OP", "XOR", "AND_OP", "LEFT_SHIFT", "RIGHT_SHIFT", 
			"ADD", "MINUS", "DIV", "MOD", "IDIV", "NOT_OP", "OPEN_BRACE", "CLOSE_BRACE", 
			"LESS_THAN", "GREATER_THAN", "EQUALS", "GT_EQ", "LT_EQ", "NOT_EQ_1", 
			"NOT_EQ_2", "AT", "ARROW", "ADD_ASSIGN", "SUB_ASSIGN", "MULT_ASSIGN", 
			"AT_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", 
			"LEFT_SHIFT_ASSIGN", "RIGHT_SHIFT_ASSIGN", "POWER_ASSIGN", "IDIV_ASSIGN", 
			"WILDCARD", "BALISE", "SUB_PATTERN", "SKIP_", "UNKNOWN_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Python3Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Python3Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Single_inputContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public Simple_stmtsContext simple_stmts() {
			return getRuleContext(Simple_stmtsContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public Single_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSingle_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSingle_input(this);
		}
	}

	public final Single_inputContext single_input() throws RecognitionException {
		Single_inputContext _localctx = new Single_inputContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_single_input);
		try {
			setState(287);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				match(NEWLINE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				simple_stmts();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(284);
				compound_stmt();
				setState(285);
				match(NEWLINE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class File_inputContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(Python3Parser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(Python3Parser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Python3Parser.NEWLINE, i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public File_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterFile_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitFile_input(this);
		}
	}

	public final File_inputContext file_input() throws RecognitionException {
		File_inputContext _localctx = new File_inputContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_file_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1009034997364027160L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137443160449L) != 0)) {
				{
				setState(291);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NEWLINE:
					{
					setState(289);
					match(NEWLINE);
					}
					break;
				case STRING:
				case NUMBER:
				case ASSERT:
				case ASYNC:
				case AWAIT:
				case BREAK:
				case CLASS:
				case CONTINUE:
				case DEF:
				case DEL:
				case FALSE:
				case FOR:
				case FROM:
				case GLOBAL:
				case IF:
				case IMPORT:
				case LAMBDA:
				case MATCH:
				case NONE:
				case NONLOCAL:
				case NOT:
				case PASS:
				case RAISE:
				case RETURN:
				case TRUE:
				case TRY:
				case UNDERSCORE:
				case WHILE:
				case WITH:
				case YIELD:
				case NAME:
				case ELLIPSIS:
				case STAR:
				case OPEN_PAREN:
				case OPEN_BRACK:
				case ADD:
				case MINUS:
				case NOT_OP:
				case OPEN_BRACE:
				case AT:
				case WILDCARD:
					{
					setState(290);
					stmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(296);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Eval_inputContext extends ParserRuleContext {
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Python3Parser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(Python3Parser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Python3Parser.NEWLINE, i);
		}
		public Eval_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eval_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterEval_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitEval_input(this);
		}
	}

	public final Eval_inputContext eval_input() throws RecognitionException {
		Eval_inputContext _localctx = new Eval_inputContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_eval_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			testlist();
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(299);
				match(NEWLINE);
				}
				}
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(305);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Macro_inputContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(Python3Parser.EOF, 0); }
		public List<Macro_stmtsContext> macro_stmts() {
			return getRuleContexts(Macro_stmtsContext.class);
		}
		public Macro_stmtsContext macro_stmts(int i) {
			return getRuleContext(Macro_stmtsContext.class,i);
		}
		public Macro_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMacro_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMacro_input(this);
		}
	}

	public final Macro_inputContext macro_input() throws RecognitionException {
		Macro_inputContext _localctx = new Macro_inputContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_macro_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(307);
				macro_stmts();
				}
				}
				setState(310); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SUB_PATTERN );
			setState(312);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecoratorContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(Python3Parser.AT, 0); }
		public Dotted_nameContext dotted_name() {
			return getRuleContext(Dotted_nameContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public DecoratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decorator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDecorator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDecorator(this);
		}
	}

	public final DecoratorContext decorator() throws RecognitionException {
		DecoratorContext _localctx = new DecoratorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_decorator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			match(AT);
			setState(315);
			dotted_name();
			setState(321);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_PAREN) {
				{
				setState(316);
				match(OPEN_PAREN);
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 549755864581L) != 0)) {
					{
					setState(317);
					arglist();
					}
				}

				setState(320);
				match(CLOSE_PAREN);
				}
			}

			setState(323);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecoratorsContext extends ParserRuleContext {
		public List<DecoratorContext> decorator() {
			return getRuleContexts(DecoratorContext.class);
		}
		public DecoratorContext decorator(int i) {
			return getRuleContext(DecoratorContext.class,i);
		}
		public DecoratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decorators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDecorators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDecorators(this);
		}
	}

	public final DecoratorsContext decorators() throws RecognitionException {
		DecoratorsContext _localctx = new DecoratorsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_decorators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(325);
				decorator();
				}
				}
				setState(328); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==AT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecoratedContext extends ParserRuleContext {
		public DecoratorsContext decorators() {
			return getRuleContext(DecoratorsContext.class,0);
		}
		public ClassdefContext classdef() {
			return getRuleContext(ClassdefContext.class,0);
		}
		public FuncdefContext funcdef() {
			return getRuleContext(FuncdefContext.class,0);
		}
		public Async_funcdefContext async_funcdef() {
			return getRuleContext(Async_funcdefContext.class,0);
		}
		public DecoratedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decorated; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDecorated(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDecorated(this);
		}
	}

	public final DecoratedContext decorated() throws RecognitionException {
		DecoratedContext _localctx = new DecoratedContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_decorated);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			decorators();
			setState(334);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLASS:
				{
				setState(331);
				classdef();
				}
				break;
			case DEF:
				{
				setState(332);
				funcdef();
				}
				break;
			case ASYNC:
				{
				setState(333);
				async_funcdef();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Async_funcdefContext extends ParserRuleContext {
		public TerminalNode ASYNC() { return getToken(Python3Parser.ASYNC, 0); }
		public FuncdefContext funcdef() {
			return getRuleContext(FuncdefContext.class,0);
		}
		public Async_funcdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_async_funcdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAsync_funcdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAsync_funcdef(this);
		}
	}

	public final Async_funcdefContext async_funcdef() throws RecognitionException {
		Async_funcdefContext _localctx = new Async_funcdefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_async_funcdef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(ASYNC);
			setState(337);
			funcdef();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncdefContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(Python3Parser.DEF, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Simple_wildcardContext simple_wildcard() {
			return getRuleContext(Simple_wildcardContext.class,0);
		}
		public Var_wildcardContext var_wildcard() {
			return getRuleContext(Var_wildcardContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(Python3Parser.ARROW, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public FuncdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterFuncdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitFuncdef(this);
		}
	}

	public final FuncdefContext funcdef() throws RecognitionException {
		FuncdefContext _localctx = new FuncdefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_funcdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(DEF);
			setState(343);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(340);
				name();
				}
				break;
			case 2:
				{
				setState(341);
				simple_wildcard();
				}
				break;
			case 3:
				{
				setState(342);
				var_wildcard();
				}
				break;
			}
			setState(345);
			parameters();
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(346);
				match(ARROW);
				setState(347);
				test();
				}
			}

			setState(350);
			match(COLON);
			setState(351);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametersContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public List<TfpdefContext> tfpdef() {
			return getRuleContexts(TfpdefContext.class);
		}
		public TfpdefContext tfpdef(int i) {
			return getRuleContext(TfpdefContext.class,i);
		}
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public TerminalNode POWER() { return getToken(Python3Parser.POWER, 0); }
		public List<TerminalNode> ASSIGN() { return getTokens(Python3Parser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(Python3Parser.ASSIGN, i);
		}
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitParameters(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameters);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			match(OPEN_PAREN);
			setState(435);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCH:
			case UNDERSCORE:
			case NAME:
			case WILDCARD:
				{
				setState(354);
				tfpdef();
				setState(357);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(355);
					match(ASSIGN);
					setState(356);
					test();
					}
				}

				setState(367);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(359);
						match(COMMA);
						setState(360);
						tfpdef();
						setState(363);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(361);
							match(ASSIGN);
							setState(362);
							test();
							}
						}

						}
						} 
					}
					setState(369);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(370);
					match(COMMA);
					setState(401);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case STAR:
						{
						setState(371);
						match(STAR);
						setState(373);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141838073724928L) != 0) || _la==WILDCARD) {
							{
							setState(372);
							tfpdef();
							}
						}

						setState(383);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(375);
								match(COMMA);
								setState(376);
								tfpdef();
								setState(379);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==ASSIGN) {
									{
									setState(377);
									match(ASSIGN);
									setState(378);
									test();
									}
								}

								}
								} 
							}
							setState(385);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
						}
						setState(394);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(386);
							match(COMMA);
							setState(392);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==POWER) {
								{
								setState(387);
								match(POWER);
								setState(388);
								tfpdef();
								setState(390);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==COMMA) {
									{
									setState(389);
									match(COMMA);
									}
								}

								}
							}

							}
						}

						}
						break;
					case POWER:
						{
						setState(396);
						match(POWER);
						setState(397);
						tfpdef();
						setState(399);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(398);
							match(COMMA);
							}
						}

						}
						break;
					case CLOSE_PAREN:
						break;
					default:
						break;
					}
					}
				}

				}
				break;
			case STAR:
				{
				setState(405);
				match(STAR);
				setState(407);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141838073724928L) != 0) || _la==WILDCARD) {
					{
					setState(406);
					tfpdef();
					}
				}

				setState(417);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(409);
						match(COMMA);
						setState(410);
						tfpdef();
						setState(413);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(411);
							match(ASSIGN);
							setState(412);
							test();
							}
						}

						}
						} 
					}
					setState(419);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				setState(428);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(420);
					match(COMMA);
					setState(426);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==POWER) {
						{
						setState(421);
						match(POWER);
						setState(422);
						tfpdef();
						setState(424);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(423);
							match(COMMA);
							}
						}

						}
					}

					}
				}

				}
				break;
			case POWER:
				{
				setState(430);
				match(POWER);
				setState(431);
				tfpdef();
				setState(433);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(432);
					match(COMMA);
					}
				}

				}
				break;
			case CLOSE_PAREN:
				break;
			default:
				break;
			}
			setState(437);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TfpdefContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Expr_wildcardContext expr_wildcard() {
			return getRuleContext(Expr_wildcardContext.class,0);
		}
		public Number_wildcardContext number_wildcard() {
			return getRuleContext(Number_wildcardContext.class,0);
		}
		public List_wildcardContext list_wildcard() {
			return getRuleContext(List_wildcardContext.class,0);
		}
		public TfpdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tfpdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterTfpdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitTfpdef(this);
		}
	}

	public final TfpdefContext tfpdef() throws RecognitionException {
		TfpdefContext _localctx = new TfpdefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tfpdef);
		int _la;
		try {
			setState(447);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(439);
				name();
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(440);
					match(COLON);
					setState(441);
					test();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(444);
				expr_wildcard();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(445);
				number_wildcard();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(446);
				list_wildcard();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarargslistContext extends ParserRuleContext {
		public List<VfpdefContext> vfpdef() {
			return getRuleContexts(VfpdefContext.class);
		}
		public VfpdefContext vfpdef(int i) {
			return getRuleContext(VfpdefContext.class,i);
		}
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public TerminalNode POWER() { return getToken(Python3Parser.POWER, 0); }
		public List<TerminalNode> ASSIGN() { return getTokens(Python3Parser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(Python3Parser.ASSIGN, i);
		}
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public VarargslistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varargslist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterVarargslist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitVarargslist(this);
		}
	}

	public final VarargslistContext varargslist() throws RecognitionException {
		VarargslistContext _localctx = new VarargslistContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_varargslist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCH:
			case UNDERSCORE:
			case NAME:
			case WILDCARD:
				{
				setState(449);
				vfpdef();
				setState(452);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(450);
					match(ASSIGN);
					setState(451);
					test();
					}
				}

				setState(462);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(454);
						match(COMMA);
						setState(455);
						vfpdef();
						setState(458);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(456);
							match(ASSIGN);
							setState(457);
							test();
							}
						}

						}
						} 
					}
					setState(464);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				}
				setState(498);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(465);
					match(COMMA);
					setState(496);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case STAR:
						{
						setState(466);
						match(STAR);
						setState(468);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141838073724928L) != 0) || _la==WILDCARD) {
							{
							setState(467);
							vfpdef();
							}
						}

						setState(478);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(470);
								match(COMMA);
								setState(471);
								vfpdef();
								setState(474);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==ASSIGN) {
									{
									setState(472);
									match(ASSIGN);
									setState(473);
									test();
									}
								}

								}
								} 
							}
							setState(480);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
						}
						setState(489);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(481);
							match(COMMA);
							setState(487);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==POWER) {
								{
								setState(482);
								match(POWER);
								setState(483);
								vfpdef();
								setState(485);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==COMMA) {
									{
									setState(484);
									match(COMMA);
									}
								}

								}
							}

							}
						}

						}
						break;
					case POWER:
						{
						setState(491);
						match(POWER);
						setState(492);
						vfpdef();
						setState(494);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(493);
							match(COMMA);
							}
						}

						}
						break;
					case COLON:
						break;
					default:
						break;
					}
					}
				}

				}
				break;
			case STAR:
				{
				setState(500);
				match(STAR);
				setState(502);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141838073724928L) != 0) || _la==WILDCARD) {
					{
					setState(501);
					vfpdef();
					}
				}

				setState(512);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(504);
						match(COMMA);
						setState(505);
						vfpdef();
						setState(508);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(506);
							match(ASSIGN);
							setState(507);
							test();
							}
						}

						}
						} 
					}
					setState(514);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				}
				setState(523);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(515);
					match(COMMA);
					setState(521);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==POWER) {
						{
						setState(516);
						match(POWER);
						setState(517);
						vfpdef();
						setState(519);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(518);
							match(COMMA);
							}
						}

						}
					}

					}
				}

				}
				break;
			case POWER:
				{
				setState(525);
				match(POWER);
				setState(526);
				vfpdef();
				setState(528);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(527);
					match(COMMA);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VfpdefContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Expr_wildcardContext expr_wildcard() {
			return getRuleContext(Expr_wildcardContext.class,0);
		}
		public Number_wildcardContext number_wildcard() {
			return getRuleContext(Number_wildcardContext.class,0);
		}
		public List_wildcardContext list_wildcard() {
			return getRuleContext(List_wildcardContext.class,0);
		}
		public VfpdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vfpdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterVfpdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitVfpdef(this);
		}
	}

	public final VfpdefContext vfpdef() throws RecognitionException {
		VfpdefContext _localctx = new VfpdefContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_vfpdef);
		try {
			setState(536);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(532);
				name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(533);
				expr_wildcard();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(534);
				number_wildcard();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(535);
				list_wildcard();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public Macro_callContext macro_call() {
			return getRuleContext(Macro_callContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public Contains_wildcardContext contains_wildcard() {
			return getRuleContext(Contains_wildcardContext.class,0);
		}
		public Simple_stmtsContext simple_stmts() {
			return getRuleContext(Simple_stmtsContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_stmt);
		try {
			setState(546);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(538);
				macro_call();
				setState(539);
				match(NEWLINE);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(541);
				contains_wildcard();
				setState(542);
				match(NEWLINE);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(544);
				simple_stmts();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(545);
				compound_stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_stmtsContext extends ParserRuleContext {
		public List<Simple_stmtContext> simple_stmt() {
			return getRuleContexts(Simple_stmtContext.class);
		}
		public Simple_stmtContext simple_stmt(int i) {
			return getRuleContext(Simple_stmtContext.class,i);
		}
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public List<TerminalNode> SEMI_COLON() { return getTokens(Python3Parser.SEMI_COLON); }
		public TerminalNode SEMI_COLON(int i) {
			return getToken(Python3Parser.SEMI_COLON, i);
		}
		public Simple_stmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSimple_stmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSimple_stmts(this);
		}
	}

	public final Simple_stmtsContext simple_stmts() throws RecognitionException {
		Simple_stmtsContext _localctx = new Simple_stmtsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_simple_stmts);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			simple_stmt();
			setState(553);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(549);
					match(SEMI_COLON);
					setState(550);
					simple_stmt();
					}
					} 
				}
				setState(555);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			}
			setState(557);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI_COLON) {
				{
				setState(556);
				match(SEMI_COLON);
				}
			}

			setState(559);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_stmtContext extends ParserRuleContext {
		public Stmt_wildcardContext stmt_wildcard() {
			return getRuleContext(Stmt_wildcardContext.class,0);
		}
		public Expr_stmtContext expr_stmt() {
			return getRuleContext(Expr_stmtContext.class,0);
		}
		public Del_stmtContext del_stmt() {
			return getRuleContext(Del_stmtContext.class,0);
		}
		public Pass_stmtContext pass_stmt() {
			return getRuleContext(Pass_stmtContext.class,0);
		}
		public Flow_stmtContext flow_stmt() {
			return getRuleContext(Flow_stmtContext.class,0);
		}
		public Import_stmtContext import_stmt() {
			return getRuleContext(Import_stmtContext.class,0);
		}
		public Global_stmtContext global_stmt() {
			return getRuleContext(Global_stmtContext.class,0);
		}
		public Nonlocal_stmtContext nonlocal_stmt() {
			return getRuleContext(Nonlocal_stmtContext.class,0);
		}
		public Assert_stmtContext assert_stmt() {
			return getRuleContext(Assert_stmtContext.class,0);
		}
		public Simple_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSimple_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSimple_stmt(this);
		}
	}

	public final Simple_stmtContext simple_stmt() throws RecognitionException {
		Simple_stmtContext _localctx = new Simple_stmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_simple_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(570);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(561);
				stmt_wildcard();
				}
				break;
			case 2:
				{
				setState(562);
				expr_stmt();
				}
				break;
			case 3:
				{
				setState(563);
				del_stmt();
				}
				break;
			case 4:
				{
				setState(564);
				pass_stmt();
				}
				break;
			case 5:
				{
				setState(565);
				flow_stmt();
				}
				break;
			case 6:
				{
				setState(566);
				import_stmt();
				}
				break;
			case 7:
				{
				setState(567);
				global_stmt();
				}
				break;
			case 8:
				{
				setState(568);
				nonlocal_stmt();
				}
				break;
			case 9:
				{
				setState(569);
				assert_stmt();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_stmtContext extends ParserRuleContext {
		public List<Testlist_star_exprContext> testlist_star_expr() {
			return getRuleContexts(Testlist_star_exprContext.class);
		}
		public Testlist_star_exprContext testlist_star_expr(int i) {
			return getRuleContext(Testlist_star_exprContext.class,i);
		}
		public AnnassignContext annassign() {
			return getRuleContext(AnnassignContext.class,0);
		}
		public AugassignContext augassign() {
			return getRuleContext(AugassignContext.class,0);
		}
		public List<Yield_exprContext> yield_expr() {
			return getRuleContexts(Yield_exprContext.class);
		}
		public Yield_exprContext yield_expr(int i) {
			return getRuleContext(Yield_exprContext.class,i);
		}
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(Python3Parser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(Python3Parser.ASSIGN, i);
		}
		public Expr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterExpr_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitExpr_stmt(this);
		}
	}

	public final Expr_stmtContext expr_stmt() throws RecognitionException {
		Expr_stmtContext _localctx = new Expr_stmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expr_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(572);
			testlist_star_expr();
			setState(589);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(573);
				annassign();
				}
				break;
			case ADD_ASSIGN:
			case SUB_ASSIGN:
			case MULT_ASSIGN:
			case AT_ASSIGN:
			case DIV_ASSIGN:
			case MOD_ASSIGN:
			case AND_ASSIGN:
			case OR_ASSIGN:
			case XOR_ASSIGN:
			case LEFT_SHIFT_ASSIGN:
			case RIGHT_SHIFT_ASSIGN:
			case POWER_ASSIGN:
			case IDIV_ASSIGN:
				{
				setState(574);
				augassign();
				setState(577);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case YIELD:
					{
					setState(575);
					yield_expr();
					}
					break;
				case STRING:
				case NUMBER:
				case AWAIT:
				case FALSE:
				case LAMBDA:
				case MATCH:
				case NONE:
				case NOT:
				case TRUE:
				case UNDERSCORE:
				case NAME:
				case ELLIPSIS:
				case OPEN_PAREN:
				case OPEN_BRACK:
				case ADD:
				case MINUS:
				case NOT_OP:
				case OPEN_BRACE:
				case WILDCARD:
					{
					setState(576);
					testlist();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case NEWLINE:
			case SEMI_COLON:
			case ASSIGN:
			case GREATER_THAN:
				{
				setState(586);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ASSIGN) {
					{
					{
					setState(579);
					match(ASSIGN);
					setState(582);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case YIELD:
						{
						setState(580);
						yield_expr();
						}
						break;
					case STRING:
					case NUMBER:
					case AWAIT:
					case FALSE:
					case LAMBDA:
					case MATCH:
					case NONE:
					case NOT:
					case TRUE:
					case UNDERSCORE:
					case NAME:
					case ELLIPSIS:
					case STAR:
					case OPEN_PAREN:
					case OPEN_BRACK:
					case ADD:
					case MINUS:
					case NOT_OP:
					case OPEN_BRACE:
					case WILDCARD:
						{
						setState(581);
						testlist_star_expr();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(588);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnassignContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(Python3Parser.ASSIGN, 0); }
		public AnnassignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annassign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAnnassign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAnnassign(this);
		}
	}

	public final AnnassignContext annassign() throws RecognitionException {
		AnnassignContext _localctx = new AnnassignContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_annassign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(591);
			match(COLON);
			setState(592);
			test();
			setState(595);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(593);
				match(ASSIGN);
				setState(594);
				test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Testlist_star_exprContext extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<Star_exprContext> star_expr() {
			return getRuleContexts(Star_exprContext.class);
		}
		public Star_exprContext star_expr(int i) {
			return getRuleContext(Star_exprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Testlist_star_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testlist_star_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterTestlist_star_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitTestlist_star_expr(this);
		}
	}

	public final Testlist_star_exprContext testlist_star_expr() throws RecognitionException {
		Testlist_star_exprContext _localctx = new Testlist_star_exprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_testlist_star_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(599);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case NUMBER:
			case AWAIT:
			case FALSE:
			case LAMBDA:
			case MATCH:
			case NONE:
			case NOT:
			case TRUE:
			case UNDERSCORE:
			case NAME:
			case ELLIPSIS:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
			case WILDCARD:
				{
				setState(597);
				test();
				}
				break;
			case STAR:
				{
				setState(598);
				star_expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(608);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(601);
					match(COMMA);
					setState(604);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case STRING:
					case NUMBER:
					case AWAIT:
					case FALSE:
					case LAMBDA:
					case MATCH:
					case NONE:
					case NOT:
					case TRUE:
					case UNDERSCORE:
					case NAME:
					case ELLIPSIS:
					case OPEN_PAREN:
					case OPEN_BRACK:
					case ADD:
					case MINUS:
					case NOT_OP:
					case OPEN_BRACE:
					case WILDCARD:
						{
						setState(602);
						test();
						}
						break;
					case STAR:
						{
						setState(603);
						star_expr();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(610);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
			}
			setState(612);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(611);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AugassignContext extends ParserRuleContext {
		public TerminalNode ADD_ASSIGN() { return getToken(Python3Parser.ADD_ASSIGN, 0); }
		public TerminalNode SUB_ASSIGN() { return getToken(Python3Parser.SUB_ASSIGN, 0); }
		public TerminalNode MULT_ASSIGN() { return getToken(Python3Parser.MULT_ASSIGN, 0); }
		public TerminalNode AT_ASSIGN() { return getToken(Python3Parser.AT_ASSIGN, 0); }
		public TerminalNode DIV_ASSIGN() { return getToken(Python3Parser.DIV_ASSIGN, 0); }
		public TerminalNode MOD_ASSIGN() { return getToken(Python3Parser.MOD_ASSIGN, 0); }
		public TerminalNode AND_ASSIGN() { return getToken(Python3Parser.AND_ASSIGN, 0); }
		public TerminalNode OR_ASSIGN() { return getToken(Python3Parser.OR_ASSIGN, 0); }
		public TerminalNode XOR_ASSIGN() { return getToken(Python3Parser.XOR_ASSIGN, 0); }
		public TerminalNode LEFT_SHIFT_ASSIGN() { return getToken(Python3Parser.LEFT_SHIFT_ASSIGN, 0); }
		public TerminalNode RIGHT_SHIFT_ASSIGN() { return getToken(Python3Parser.RIGHT_SHIFT_ASSIGN, 0); }
		public TerminalNode POWER_ASSIGN() { return getToken(Python3Parser.POWER_ASSIGN, 0); }
		public TerminalNode IDIV_ASSIGN() { return getToken(Python3Parser.IDIV_ASSIGN, 0); }
		public AugassignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_augassign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAugassign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAugassign(this);
		}
	}

	public final AugassignContext augassign() throws RecognitionException {
		AugassignContext _localctx = new AugassignContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_augassign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(614);
			_la = _input.LA(1);
			if ( !(((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & 8191L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Del_stmtContext extends ParserRuleContext {
		public TerminalNode DEL() { return getToken(Python3Parser.DEL, 0); }
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public Del_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_del_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDel_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDel_stmt(this);
		}
	}

	public final Del_stmtContext del_stmt() throws RecognitionException {
		Del_stmtContext _localctx = new Del_stmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_del_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616);
			match(DEL);
			setState(617);
			exprlist();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Pass_stmtContext extends ParserRuleContext {
		public TerminalNode PASS() { return getToken(Python3Parser.PASS, 0); }
		public Pass_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pass_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterPass_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitPass_stmt(this);
		}
	}

	public final Pass_stmtContext pass_stmt() throws RecognitionException {
		Pass_stmtContext _localctx = new Pass_stmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_pass_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(619);
			match(PASS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Flow_stmtContext extends ParserRuleContext {
		public Break_stmtContext break_stmt() {
			return getRuleContext(Break_stmtContext.class,0);
		}
		public Continue_stmtContext continue_stmt() {
			return getRuleContext(Continue_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Raise_stmtContext raise_stmt() {
			return getRuleContext(Raise_stmtContext.class,0);
		}
		public Yield_stmtContext yield_stmt() {
			return getRuleContext(Yield_stmtContext.class,0);
		}
		public Flow_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flow_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterFlow_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitFlow_stmt(this);
		}
	}

	public final Flow_stmtContext flow_stmt() throws RecognitionException {
		Flow_stmtContext _localctx = new Flow_stmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_flow_stmt);
		try {
			setState(626);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BREAK:
				enterOuterAlt(_localctx, 1);
				{
				setState(621);
				break_stmt();
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(622);
				continue_stmt();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 3);
				{
				setState(623);
				return_stmt();
				}
				break;
			case RAISE:
				enterOuterAlt(_localctx, 4);
				{
				setState(624);
				raise_stmt();
				}
				break;
			case YIELD:
				enterOuterAlt(_localctx, 5);
				{
				setState(625);
				yield_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Break_stmtContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(Python3Parser.BREAK, 0); }
		public Break_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_break_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterBreak_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitBreak_stmt(this);
		}
	}

	public final Break_stmtContext break_stmt() throws RecognitionException {
		Break_stmtContext _localctx = new Break_stmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_break_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(628);
			match(BREAK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Continue_stmtContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(Python3Parser.CONTINUE, 0); }
		public Continue_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continue_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterContinue_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitContinue_stmt(this);
		}
	}

	public final Continue_stmtContext continue_stmt() throws RecognitionException {
		Continue_stmtContext _localctx = new Continue_stmtContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_continue_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(630);
			match(CONTINUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(Python3Parser.RETURN, 0); }
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitReturn_stmt(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_return_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(632);
			match(RETURN);
			setState(634);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(633);
				testlist();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Yield_stmtContext extends ParserRuleContext {
		public Yield_exprContext yield_expr() {
			return getRuleContext(Yield_exprContext.class,0);
		}
		public Yield_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yield_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterYield_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitYield_stmt(this);
		}
	}

	public final Yield_stmtContext yield_stmt() throws RecognitionException {
		Yield_stmtContext _localctx = new Yield_stmtContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_yield_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(636);
			yield_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Raise_stmtContext extends ParserRuleContext {
		public TerminalNode RAISE() { return getToken(Python3Parser.RAISE, 0); }
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public TerminalNode FROM() { return getToken(Python3Parser.FROM, 0); }
		public Raise_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_raise_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterRaise_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitRaise_stmt(this);
		}
	}

	public final Raise_stmtContext raise_stmt() throws RecognitionException {
		Raise_stmtContext _localctx = new Raise_stmtContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_raise_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(638);
			match(RAISE);
			setState(644);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(639);
				test();
				setState(642);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FROM) {
					{
					setState(640);
					match(FROM);
					setState(641);
					test();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_stmtContext extends ParserRuleContext {
		public Import_nameContext import_name() {
			return getRuleContext(Import_nameContext.class,0);
		}
		public Import_fromContext import_from() {
			return getRuleContext(Import_fromContext.class,0);
		}
		public Import_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterImport_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitImport_stmt(this);
		}
	}

	public final Import_stmtContext import_stmt() throws RecognitionException {
		Import_stmtContext _localctx = new Import_stmtContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_import_stmt);
		try {
			setState(648);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IMPORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(646);
				import_name();
				}
				break;
			case FROM:
				enterOuterAlt(_localctx, 2);
				{
				setState(647);
				import_from();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_nameContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(Python3Parser.IMPORT, 0); }
		public Dotted_as_namesContext dotted_as_names() {
			return getRuleContext(Dotted_as_namesContext.class,0);
		}
		public Import_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterImport_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitImport_name(this);
		}
	}

	public final Import_nameContext import_name() throws RecognitionException {
		Import_nameContext _localctx = new Import_nameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_import_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(650);
			match(IMPORT);
			setState(651);
			dotted_as_names();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_fromContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(Python3Parser.FROM, 0); }
		public TerminalNode IMPORT() { return getToken(Python3Parser.IMPORT, 0); }
		public Dotted_nameContext dotted_name() {
			return getRuleContext(Dotted_nameContext.class,0);
		}
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public Import_as_namesContext import_as_names() {
			return getRuleContext(Import_as_namesContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public List<TerminalNode> DOT() { return getTokens(Python3Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Python3Parser.DOT, i);
		}
		public List<TerminalNode> ELLIPSIS() { return getTokens(Python3Parser.ELLIPSIS); }
		public TerminalNode ELLIPSIS(int i) {
			return getToken(Python3Parser.ELLIPSIS, i);
		}
		public Import_fromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_from; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterImport_from(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitImport_from(this);
		}
	}

	public final Import_fromContext import_from() throws RecognitionException {
		Import_fromContext _localctx = new Import_fromContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_import_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(653);
			match(FROM);
			setState(666);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				{
				setState(657);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==ELLIPSIS) {
					{
					{
					setState(654);
					_la = _input.LA(1);
					if ( !(_la==DOT || _la==ELLIPSIS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(659);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(660);
				dotted_name();
				}
				break;
			case 2:
				{
				setState(662); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(661);
					_la = _input.LA(1);
					if ( !(_la==DOT || _la==ELLIPSIS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(664); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DOT || _la==ELLIPSIS );
				}
				break;
			}
			setState(668);
			match(IMPORT);
			setState(675);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				{
				setState(669);
				match(STAR);
				}
				break;
			case OPEN_PAREN:
				{
				setState(670);
				match(OPEN_PAREN);
				setState(671);
				import_as_names();
				setState(672);
				match(CLOSE_PAREN);
				}
				break;
			case MATCH:
			case UNDERSCORE:
			case NAME:
				{
				setState(674);
				import_as_names();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_as_nameContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TerminalNode AS() { return getToken(Python3Parser.AS, 0); }
		public Import_as_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_as_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterImport_as_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitImport_as_name(this);
		}
	}

	public final Import_as_nameContext import_as_name() throws RecognitionException {
		Import_as_nameContext _localctx = new Import_as_nameContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_import_as_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(677);
			name();
			setState(680);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(678);
				match(AS);
				setState(679);
				name();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Dotted_as_nameContext extends ParserRuleContext {
		public Dotted_nameContext dotted_name() {
			return getRuleContext(Dotted_nameContext.class,0);
		}
		public TerminalNode AS() { return getToken(Python3Parser.AS, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Dotted_as_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotted_as_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDotted_as_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDotted_as_name(this);
		}
	}

	public final Dotted_as_nameContext dotted_as_name() throws RecognitionException {
		Dotted_as_nameContext _localctx = new Dotted_as_nameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_dotted_as_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(682);
			dotted_name();
			setState(685);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(683);
				match(AS);
				setState(684);
				name();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_as_namesContext extends ParserRuleContext {
		public List<Import_as_nameContext> import_as_name() {
			return getRuleContexts(Import_as_nameContext.class);
		}
		public Import_as_nameContext import_as_name(int i) {
			return getRuleContext(Import_as_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Import_as_namesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_as_names; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterImport_as_names(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitImport_as_names(this);
		}
	}

	public final Import_as_namesContext import_as_names() throws RecognitionException {
		Import_as_namesContext _localctx = new Import_as_namesContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_import_as_names);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(687);
			import_as_name();
			setState(692);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(688);
					match(COMMA);
					setState(689);
					import_as_name();
					}
					} 
				}
				setState(694);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
			}
			setState(696);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(695);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Dotted_as_namesContext extends ParserRuleContext {
		public List<Dotted_as_nameContext> dotted_as_name() {
			return getRuleContexts(Dotted_as_nameContext.class);
		}
		public Dotted_as_nameContext dotted_as_name(int i) {
			return getRuleContext(Dotted_as_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Dotted_as_namesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotted_as_names; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDotted_as_names(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDotted_as_names(this);
		}
	}

	public final Dotted_as_namesContext dotted_as_names() throws RecognitionException {
		Dotted_as_namesContext _localctx = new Dotted_as_namesContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_dotted_as_names);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(698);
			dotted_as_name();
			setState(703);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(699);
				match(COMMA);
				setState(700);
				dotted_as_name();
				}
				}
				setState(705);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Dotted_nameContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(Python3Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Python3Parser.DOT, i);
		}
		public Dotted_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotted_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDotted_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDotted_name(this);
		}
	}

	public final Dotted_nameContext dotted_name() throws RecognitionException {
		Dotted_nameContext _localctx = new Dotted_nameContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_dotted_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(706);
			name();
			setState(711);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(707);
				match(DOT);
				setState(708);
				name();
				}
				}
				setState(713);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Global_stmtContext extends ParserRuleContext {
		public TerminalNode GLOBAL() { return getToken(Python3Parser.GLOBAL, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Global_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterGlobal_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitGlobal_stmt(this);
		}
	}

	public final Global_stmtContext global_stmt() throws RecognitionException {
		Global_stmtContext _localctx = new Global_stmtContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_global_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(714);
			match(GLOBAL);
			setState(715);
			name();
			setState(720);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(716);
				match(COMMA);
				setState(717);
				name();
				}
				}
				setState(722);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Nonlocal_stmtContext extends ParserRuleContext {
		public TerminalNode NONLOCAL() { return getToken(Python3Parser.NONLOCAL, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Nonlocal_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonlocal_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterNonlocal_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitNonlocal_stmt(this);
		}
	}

	public final Nonlocal_stmtContext nonlocal_stmt() throws RecognitionException {
		Nonlocal_stmtContext _localctx = new Nonlocal_stmtContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_nonlocal_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(723);
			match(NONLOCAL);
			setState(724);
			name();
			setState(729);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(725);
				match(COMMA);
				setState(726);
				name();
				}
				}
				setState(731);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Assert_stmtContext extends ParserRuleContext {
		public TerminalNode ASSERT() { return getToken(Python3Parser.ASSERT, 0); }
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(Python3Parser.COMMA, 0); }
		public Assert_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assert_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAssert_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAssert_stmt(this);
		}
	}

	public final Assert_stmtContext assert_stmt() throws RecognitionException {
		Assert_stmtContext _localctx = new Assert_stmtContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_assert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(732);
			match(ASSERT);
			setState(733);
			test();
			setState(736);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(734);
				match(COMMA);
				setState(735);
				test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_stmtContext extends ParserRuleContext {
		public Compound_wildcardContext compound_wildcard() {
			return getRuleContext(Compound_wildcardContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public Try_stmtContext try_stmt() {
			return getRuleContext(Try_stmtContext.class,0);
		}
		public With_stmtContext with_stmt() {
			return getRuleContext(With_stmtContext.class,0);
		}
		public FuncdefContext funcdef() {
			return getRuleContext(FuncdefContext.class,0);
		}
		public ClassdefContext classdef() {
			return getRuleContext(ClassdefContext.class,0);
		}
		public DecoratedContext decorated() {
			return getRuleContext(DecoratedContext.class,0);
		}
		public Async_stmtContext async_stmt() {
			return getRuleContext(Async_stmtContext.class,0);
		}
		public Match_stmtContext match_stmt() {
			return getRuleContext(Match_stmtContext.class,0);
		}
		public Compound_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterCompound_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitCompound_stmt(this);
		}
	}

	public final Compound_stmtContext compound_stmt() throws RecognitionException {
		Compound_stmtContext _localctx = new Compound_stmtContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_compound_stmt);
		try {
			setState(749);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				enterOuterAlt(_localctx, 1);
				{
				setState(738);
				compound_wildcard();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(739);
				if_stmt();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(740);
				while_stmt();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(741);
				for_stmt();
				}
				break;
			case TRY:
				enterOuterAlt(_localctx, 5);
				{
				setState(742);
				try_stmt();
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 6);
				{
				setState(743);
				with_stmt();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 7);
				{
				setState(744);
				funcdef();
				}
				break;
			case CLASS:
				enterOuterAlt(_localctx, 8);
				{
				setState(745);
				classdef();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 9);
				{
				setState(746);
				decorated();
				}
				break;
			case ASYNC:
				enterOuterAlt(_localctx, 10);
				{
				setState(747);
				async_stmt();
				}
				break;
			case MATCH:
				enterOuterAlt(_localctx, 11);
				{
				setState(748);
				match_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Async_stmtContext extends ParserRuleContext {
		public TerminalNode ASYNC() { return getToken(Python3Parser.ASYNC, 0); }
		public FuncdefContext funcdef() {
			return getRuleContext(FuncdefContext.class,0);
		}
		public With_stmtContext with_stmt() {
			return getRuleContext(With_stmtContext.class,0);
		}
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public Async_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_async_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAsync_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAsync_stmt(this);
		}
	}

	public final Async_stmtContext async_stmt() throws RecognitionException {
		Async_stmtContext _localctx = new Async_stmtContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_async_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(751);
			match(ASYNC);
			setState(755);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEF:
				{
				setState(752);
				funcdef();
				}
				break;
			case WITH:
				{
				setState(753);
				with_stmt();
				}
				break;
			case FOR:
				{
				setState(754);
				for_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(Python3Parser.IF, 0); }
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(Python3Parser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(Python3Parser.COLON, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> ELIF() { return getTokens(Python3Parser.ELIF); }
		public TerminalNode ELIF(int i) {
			return getToken(Python3Parser.ELIF, i);
		}
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitIf_stmt(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_if_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(757);
			match(IF);
			setState(758);
			test();
			setState(759);
			match(COLON);
			setState(760);
			block();
			setState(768);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(761);
				match(ELIF);
				setState(762);
				test();
				setState(763);
				match(COLON);
				setState(764);
				block();
				}
				}
				setState(770);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(774);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(771);
				match(ELSE);
				setState(772);
				match(COLON);
				setState(773);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class While_stmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(Python3Parser.WHILE, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public List<TerminalNode> COLON() { return getTokens(Python3Parser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(Python3Parser.COLON, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterWhile_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitWhile_stmt(this);
		}
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_while_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(776);
			match(WHILE);
			setState(777);
			test();
			setState(778);
			match(COLON);
			setState(779);
			block();
			setState(783);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(780);
				match(ELSE);
				setState(781);
				match(COLON);
				setState(782);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class For_stmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(Python3Parser.FOR, 0); }
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public TerminalNode IN() { return getToken(Python3Parser.IN, 0); }
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public List<TerminalNode> COLON() { return getTokens(Python3Parser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(Python3Parser.COLON, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public For_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterFor_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitFor_stmt(this);
		}
	}

	public final For_stmtContext for_stmt() throws RecognitionException {
		For_stmtContext _localctx = new For_stmtContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_for_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(785);
			match(FOR);
			setState(786);
			exprlist();
			setState(787);
			match(IN);
			setState(788);
			testlist();
			setState(789);
			match(COLON);
			setState(790);
			block();
			setState(794);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(791);
				match(ELSE);
				setState(792);
				match(COLON);
				setState(793);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Try_stmtContext extends ParserRuleContext {
		public TerminalNode TRY() { return getToken(Python3Parser.TRY, 0); }
		public List<TerminalNode> COLON() { return getTokens(Python3Parser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(Python3Parser.COLON, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode FINALLY() { return getToken(Python3Parser.FINALLY, 0); }
		public List<Except_clauseContext> except_clause() {
			return getRuleContexts(Except_clauseContext.class);
		}
		public Except_clauseContext except_clause(int i) {
			return getRuleContext(Except_clauseContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public Try_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_try_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterTry_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitTry_stmt(this);
		}
	}

	public final Try_stmtContext try_stmt() throws RecognitionException {
		Try_stmtContext _localctx = new Try_stmtContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_try_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(796);
			match(TRY);
			setState(797);
			match(COLON);
			setState(798);
			block();
			setState(820);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXCEPT:
				{
				setState(803); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(799);
					except_clause();
					setState(800);
					match(COLON);
					setState(801);
					block();
					}
					}
					setState(805); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==EXCEPT );
				setState(810);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(807);
					match(ELSE);
					setState(808);
					match(COLON);
					setState(809);
					block();
					}
				}

				setState(815);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(812);
					match(FINALLY);
					setState(813);
					match(COLON);
					setState(814);
					block();
					}
				}

				}
				break;
			case FINALLY:
				{
				setState(817);
				match(FINALLY);
				setState(818);
				match(COLON);
				setState(819);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class With_stmtContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(Python3Parser.WITH, 0); }
		public List<With_itemContext> with_item() {
			return getRuleContexts(With_itemContext.class);
		}
		public With_itemContext with_item(int i) {
			return getRuleContext(With_itemContext.class,i);
		}
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public With_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterWith_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitWith_stmt(this);
		}
	}

	public final With_stmtContext with_stmt() throws RecognitionException {
		With_stmtContext _localctx = new With_stmtContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_with_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(822);
			match(WITH);
			setState(823);
			with_item();
			setState(828);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(824);
				match(COMMA);
				setState(825);
				with_item();
				}
				}
				setState(830);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(831);
			match(COLON);
			setState(832);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class With_itemContext extends ParserRuleContext {
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TerminalNode AS() { return getToken(Python3Parser.AS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public With_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterWith_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitWith_item(this);
		}
	}

	public final With_itemContext with_item() throws RecognitionException {
		With_itemContext _localctx = new With_itemContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_with_item);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(834);
			test();
			setState(837);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(835);
				match(AS);
				setState(836);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Except_clauseContext extends ParserRuleContext {
		public TerminalNode EXCEPT() { return getToken(Python3Parser.EXCEPT, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TerminalNode AS() { return getToken(Python3Parser.AS, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Except_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_except_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterExcept_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitExcept_clause(this);
		}
	}

	public final Except_clauseContext except_clause() throws RecognitionException {
		Except_clauseContext _localctx = new Except_clauseContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_except_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(839);
			match(EXCEPT);
			setState(845);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(840);
				test();
				setState(843);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(841);
					match(AS);
					setState(842);
					name();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public Simple_stmtsContext simple_stmts() {
			return getRuleContext(Simple_stmtsContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(Python3Parser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(Python3Parser.DEDENT, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_block);
		int _la;
		try {
			setState(857);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case NUMBER:
			case ASSERT:
			case AWAIT:
			case BREAK:
			case CONTINUE:
			case DEL:
			case FALSE:
			case FROM:
			case GLOBAL:
			case IMPORT:
			case LAMBDA:
			case MATCH:
			case NONE:
			case NONLOCAL:
			case NOT:
			case PASS:
			case RAISE:
			case RETURN:
			case TRUE:
			case UNDERSCORE:
			case YIELD:
			case NAME:
			case ELLIPSIS:
			case STAR:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
			case WILDCARD:
				enterOuterAlt(_localctx, 1);
				{
				setState(847);
				simple_stmts();
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(848);
				match(NEWLINE);
				setState(849);
				match(INDENT);
				setState(851); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(850);
					stmt();
					}
					}
					setState(853); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1008964628619849496L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137443160449L) != 0) );
				setState(855);
				match(DEDENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Match_stmtContext extends ParserRuleContext {
		public TerminalNode MATCH() { return getToken(Python3Parser.MATCH, 0); }
		public Subject_exprContext subject_expr() {
			return getRuleContext(Subject_exprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(Python3Parser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(Python3Parser.DEDENT, 0); }
		public List<Case_blockContext> case_block() {
			return getRuleContexts(Case_blockContext.class);
		}
		public Case_blockContext case_block(int i) {
			return getRuleContext(Case_blockContext.class,i);
		}
		public Match_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_match_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMatch_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMatch_stmt(this);
		}
	}

	public final Match_stmtContext match_stmt() throws RecognitionException {
		Match_stmtContext _localctx = new Match_stmtContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_match_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(859);
			match(MATCH);
			setState(860);
			subject_expr();
			setState(861);
			match(COLON);
			setState(862);
			match(NEWLINE);
			setState(863);
			match(INDENT);
			setState(865); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(864);
				case_block();
				}
				}
				setState(867); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASE );
			setState(869);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Subject_exprContext extends ParserRuleContext {
		public Star_named_expressionContext star_named_expression() {
			return getRuleContext(Star_named_expressionContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Python3Parser.COMMA, 0); }
		public Star_named_expressionsContext star_named_expressions() {
			return getRuleContext(Star_named_expressionsContext.class,0);
		}
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Subject_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subject_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSubject_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSubject_expr(this);
		}
	}

	public final Subject_exprContext subject_expr() throws RecognitionException {
		Subject_exprContext _localctx = new Subject_exprContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_subject_expr);
		int _la;
		try {
			setState(877);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(871);
				star_named_expression();
				setState(872);
				match(COMMA);
				setState(874);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(873);
					star_named_expressions();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(876);
				test();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Star_named_expressionsContext extends ParserRuleContext {
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public List<Star_named_expressionContext> star_named_expression() {
			return getRuleContexts(Star_named_expressionContext.class);
		}
		public Star_named_expressionContext star_named_expression(int i) {
			return getRuleContext(Star_named_expressionContext.class,i);
		}
		public Star_named_expressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_star_named_expressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterStar_named_expressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitStar_named_expressions(this);
		}
	}

	public final Star_named_expressionsContext star_named_expressions() throws RecognitionException {
		Star_named_expressionsContext _localctx = new Star_named_expressionsContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_star_named_expressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(879);
			match(COMMA);
			setState(881); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(880);
				star_named_expression();
				}
				}
				setState(883); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0) );
			setState(886);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(885);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Star_named_expressionContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Star_named_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_star_named_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterStar_named_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitStar_named_expression(this);
		}
	}

	public final Star_named_expressionContext star_named_expression() throws RecognitionException {
		Star_named_expressionContext _localctx = new Star_named_expressionContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_star_named_expression);
		try {
			setState(891);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(888);
				match(STAR);
				setState(889);
				expr(0);
				}
				break;
			case STRING:
			case NUMBER:
			case AWAIT:
			case FALSE:
			case LAMBDA:
			case MATCH:
			case NONE:
			case NOT:
			case TRUE:
			case UNDERSCORE:
			case NAME:
			case ELLIPSIS:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
			case WILDCARD:
				enterOuterAlt(_localctx, 2);
				{
				setState(890);
				test();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Case_blockContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(Python3Parser.CASE, 0); }
		public PatternsContext patterns() {
			return getRuleContext(PatternsContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public GuardContext guard() {
			return getRuleContext(GuardContext.class,0);
		}
		public Case_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterCase_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitCase_block(this);
		}
	}

	public final Case_blockContext case_block() throws RecognitionException {
		Case_blockContext _localctx = new Case_blockContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_case_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(893);
			match(CASE);
			setState(894);
			patterns();
			setState(896);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(895);
				guard();
				}
			}

			setState(898);
			match(COLON);
			setState(899);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GuardContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(Python3Parser.IF, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public GuardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_guard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterGuard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitGuard(this);
		}
	}

	public final GuardContext guard() throws RecognitionException {
		GuardContext _localctx = new GuardContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_guard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(901);
			match(IF);
			setState(902);
			test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternsContext extends ParserRuleContext {
		public Open_sequence_patternContext open_sequence_pattern() {
			return getRuleContext(Open_sequence_patternContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public PatternsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patterns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterPatterns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitPatterns(this);
		}
	}

	public final PatternsContext patterns() throws RecognitionException {
		PatternsContext _localctx = new PatternsContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_patterns);
		try {
			setState(906);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(904);
				open_sequence_pattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(905);
				pattern();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternContext extends ParserRuleContext {
		public As_patternContext as_pattern() {
			return getRuleContext(As_patternContext.class,0);
		}
		public Or_patternContext or_pattern() {
			return getRuleContext(Or_patternContext.class,0);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitPattern(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_pattern);
		try {
			setState(910);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(908);
				as_pattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(909);
				or_pattern();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class As_patternContext extends ParserRuleContext {
		public Or_patternContext or_pattern() {
			return getRuleContext(Or_patternContext.class,0);
		}
		public TerminalNode AS() { return getToken(Python3Parser.AS, 0); }
		public Pattern_capture_targetContext pattern_capture_target() {
			return getRuleContext(Pattern_capture_targetContext.class,0);
		}
		public As_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_as_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAs_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAs_pattern(this);
		}
	}

	public final As_patternContext as_pattern() throws RecognitionException {
		As_patternContext _localctx = new As_patternContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_as_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(912);
			or_pattern();
			setState(913);
			match(AS);
			setState(914);
			pattern_capture_target();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Or_patternContext extends ParserRuleContext {
		public List<Closed_patternContext> closed_pattern() {
			return getRuleContexts(Closed_patternContext.class);
		}
		public Closed_patternContext closed_pattern(int i) {
			return getRuleContext(Closed_patternContext.class,i);
		}
		public List<TerminalNode> OR_OP() { return getTokens(Python3Parser.OR_OP); }
		public TerminalNode OR_OP(int i) {
			return getToken(Python3Parser.OR_OP, i);
		}
		public Or_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterOr_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitOr_pattern(this);
		}
	}

	public final Or_patternContext or_pattern() throws RecognitionException {
		Or_patternContext _localctx = new Or_patternContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_or_pattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(916);
			closed_pattern();
			setState(921);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_OP) {
				{
				{
				setState(917);
				match(OR_OP);
				setState(918);
				closed_pattern();
				}
				}
				setState(923);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Closed_patternContext extends ParserRuleContext {
		public Wildcard_patternContext wildcard_pattern() {
			return getRuleContext(Wildcard_patternContext.class,0);
		}
		public Literal_patternContext literal_pattern() {
			return getRuleContext(Literal_patternContext.class,0);
		}
		public Capture_patternContext capture_pattern() {
			return getRuleContext(Capture_patternContext.class,0);
		}
		public Value_patternContext value_pattern() {
			return getRuleContext(Value_patternContext.class,0);
		}
		public Group_patternContext group_pattern() {
			return getRuleContext(Group_patternContext.class,0);
		}
		public Sequence_patternContext sequence_pattern() {
			return getRuleContext(Sequence_patternContext.class,0);
		}
		public Mapping_patternContext mapping_pattern() {
			return getRuleContext(Mapping_patternContext.class,0);
		}
		public Class_patternContext class_pattern() {
			return getRuleContext(Class_patternContext.class,0);
		}
		public Closed_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_closed_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterClosed_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitClosed_pattern(this);
		}
	}

	public final Closed_patternContext closed_pattern() throws RecognitionException {
		Closed_patternContext _localctx = new Closed_patternContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_closed_pattern);
		try {
			setState(932);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(924);
				wildcard_pattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(925);
				literal_pattern();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(926);
				capture_pattern();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(927);
				value_pattern();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(928);
				group_pattern();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(929);
				sequence_pattern();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(930);
				mapping_pattern();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(931);
				class_pattern();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Literal_patternContext extends ParserRuleContext {
		public Signed_numberContext signed_number() {
			return getRuleContext(Signed_numberContext.class,0);
		}
		public Complex_numberContext complex_number() {
			return getRuleContext(Complex_numberContext.class,0);
		}
		public StringsContext strings() {
			return getRuleContext(StringsContext.class,0);
		}
		public TerminalNode NONE() { return getToken(Python3Parser.NONE, 0); }
		public TerminalNode TRUE() { return getToken(Python3Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Python3Parser.FALSE, 0); }
		public Literal_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterLiteral_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitLiteral_pattern(this);
		}
	}

	public final Literal_patternContext literal_pattern() throws RecognitionException {
		Literal_patternContext _localctx = new Literal_patternContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_literal_pattern);
		try {
			setState(942);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(934);
				signed_number();
				setState(935);
				if (!( self.CannotBePlusMinus() )) throw new FailedPredicateException(this, " self.CannotBePlusMinus() ");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(937);
				complex_number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(938);
				strings();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(939);
				match(NONE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(940);
				match(TRUE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(941);
				match(FALSE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Literal_exprContext extends ParserRuleContext {
		public Signed_numberContext signed_number() {
			return getRuleContext(Signed_numberContext.class,0);
		}
		public Complex_numberContext complex_number() {
			return getRuleContext(Complex_numberContext.class,0);
		}
		public StringsContext strings() {
			return getRuleContext(StringsContext.class,0);
		}
		public TerminalNode NONE() { return getToken(Python3Parser.NONE, 0); }
		public TerminalNode TRUE() { return getToken(Python3Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Python3Parser.FALSE, 0); }
		public Literal_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterLiteral_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitLiteral_expr(this);
		}
	}

	public final Literal_exprContext literal_expr() throws RecognitionException {
		Literal_exprContext _localctx = new Literal_exprContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_literal_expr);
		try {
			setState(952);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(944);
				signed_number();
				setState(945);
				if (!( self.CannotBePlusMinus() )) throw new FailedPredicateException(this, " self.CannotBePlusMinus() ");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(947);
				complex_number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(948);
				strings();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(949);
				match(NONE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(950);
				match(TRUE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(951);
				match(FALSE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Complex_numberContext extends ParserRuleContext {
		public Signed_real_numberContext signed_real_number() {
			return getRuleContext(Signed_real_numberContext.class,0);
		}
		public TerminalNode ADD() { return getToken(Python3Parser.ADD, 0); }
		public Imaginary_numberContext imaginary_number() {
			return getRuleContext(Imaginary_numberContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(Python3Parser.MINUS, 0); }
		public Complex_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complex_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterComplex_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitComplex_number(this);
		}
	}

	public final Complex_numberContext complex_number() throws RecognitionException {
		Complex_numberContext _localctx = new Complex_numberContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_complex_number);
		try {
			setState(962);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(954);
				signed_real_number();
				setState(955);
				match(ADD);
				setState(956);
				imaginary_number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(958);
				signed_real_number();
				setState(959);
				match(MINUS);
				setState(960);
				imaginary_number();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Signed_numberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(Python3Parser.NUMBER, 0); }
		public TerminalNode MINUS() { return getToken(Python3Parser.MINUS, 0); }
		public Signed_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSigned_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSigned_number(this);
		}
	}

	public final Signed_numberContext signed_number() throws RecognitionException {
		Signed_numberContext _localctx = new Signed_numberContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_signed_number);
		try {
			setState(967);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(964);
				match(NUMBER);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(965);
				match(MINUS);
				setState(966);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Signed_real_numberContext extends ParserRuleContext {
		public Real_numberContext real_number() {
			return getRuleContext(Real_numberContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(Python3Parser.MINUS, 0); }
		public Signed_real_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_real_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSigned_real_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSigned_real_number(this);
		}
	}

	public final Signed_real_numberContext signed_real_number() throws RecognitionException {
		Signed_real_numberContext _localctx = new Signed_real_numberContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_signed_real_number);
		try {
			setState(972);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(969);
				real_number();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(970);
				match(MINUS);
				setState(971);
				real_number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Real_numberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(Python3Parser.NUMBER, 0); }
		public Real_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_real_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterReal_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitReal_number(this);
		}
	}

	public final Real_numberContext real_number() throws RecognitionException {
		Real_numberContext _localctx = new Real_numberContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_real_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(974);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Imaginary_numberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(Python3Parser.NUMBER, 0); }
		public Imaginary_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imaginary_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterImaginary_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitImaginary_number(this);
		}
	}

	public final Imaginary_numberContext imaginary_number() throws RecognitionException {
		Imaginary_numberContext _localctx = new Imaginary_numberContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_imaginary_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(976);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Capture_patternContext extends ParserRuleContext {
		public Pattern_capture_targetContext pattern_capture_target() {
			return getRuleContext(Pattern_capture_targetContext.class,0);
		}
		public Capture_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_capture_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterCapture_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitCapture_pattern(this);
		}
	}

	public final Capture_patternContext capture_pattern() throws RecognitionException {
		Capture_patternContext _localctx = new Capture_patternContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_capture_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(978);
			pattern_capture_target();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Pattern_capture_targetContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Pattern_capture_targetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern_capture_target; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterPattern_capture_target(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitPattern_capture_target(this);
		}
	}

	public final Pattern_capture_targetContext pattern_capture_target() throws RecognitionException {
		Pattern_capture_targetContext _localctx = new Pattern_capture_targetContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_pattern_capture_target);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(980);
			name();
			setState(981);
			if (!( self.CannotBeDotLpEq() )) throw new FailedPredicateException(this, " self.CannotBeDotLpEq() ");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Wildcard_patternContext extends ParserRuleContext {
		public TerminalNode UNDERSCORE() { return getToken(Python3Parser.UNDERSCORE, 0); }
		public Wildcard_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterWildcard_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitWildcard_pattern(this);
		}
	}

	public final Wildcard_patternContext wildcard_pattern() throws RecognitionException {
		Wildcard_patternContext _localctx = new Wildcard_patternContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_wildcard_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(983);
			match(UNDERSCORE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Value_patternContext extends ParserRuleContext {
		public AttrContext attr() {
			return getRuleContext(AttrContext.class,0);
		}
		public Value_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterValue_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitValue_pattern(this);
		}
	}

	public final Value_patternContext value_pattern() throws RecognitionException {
		Value_patternContext _localctx = new Value_patternContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_value_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(985);
			attr();
			setState(986);
			if (!( self.CannotBeDotLpEq() )) throw new FailedPredicateException(this, " self.CannotBeDotLpEq() ");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttrContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(Python3Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Python3Parser.DOT, i);
		}
		public AttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAttr(this);
		}
	}

	public final AttrContext attr() throws RecognitionException {
		AttrContext _localctx = new AttrContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_attr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(988);
			name();
			setState(991); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(989);
					match(DOT);
					setState(990);
					name();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(993); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Name_or_attrContext extends ParserRuleContext {
		public AttrContext attr() {
			return getRuleContext(AttrContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Name_or_attrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name_or_attr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterName_or_attr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitName_or_attr(this);
		}
	}

	public final Name_or_attrContext name_or_attr() throws RecognitionException {
		Name_or_attrContext _localctx = new Name_or_attrContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_name_or_attr);
		try {
			setState(997);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(995);
				attr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(996);
				name();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Group_patternContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public Group_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterGroup_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitGroup_pattern(this);
		}
	}

	public final Group_patternContext group_pattern() throws RecognitionException {
		Group_patternContext _localctx = new Group_patternContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_group_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(999);
			match(OPEN_PAREN);
			setState(1000);
			pattern();
			setState(1001);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Sequence_patternContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACK() { return getToken(Python3Parser.OPEN_BRACK, 0); }
		public TerminalNode CLOSE_BRACK() { return getToken(Python3Parser.CLOSE_BRACK, 0); }
		public Maybe_sequence_patternContext maybe_sequence_pattern() {
			return getRuleContext(Maybe_sequence_patternContext.class,0);
		}
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public Open_sequence_patternContext open_sequence_pattern() {
			return getRuleContext(Open_sequence_patternContext.class,0);
		}
		public Sequence_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sequence_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSequence_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSequence_pattern(this);
		}
	}

	public final Sequence_patternContext sequence_pattern() throws RecognitionException {
		Sequence_patternContext _localctx = new Sequence_patternContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_sequence_pattern);
		int _la;
		try {
			setState(1013);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(1003);
				match(OPEN_BRACK);
				setState(1005);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 864833243555299352L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 8449L) != 0)) {
					{
					setState(1004);
					maybe_sequence_pattern();
					}
				}

				setState(1007);
				match(CLOSE_BRACK);
				}
				break;
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(1008);
				match(OPEN_PAREN);
				setState(1010);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 864833243555299352L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 8449L) != 0)) {
					{
					setState(1009);
					open_sequence_pattern();
					}
				}

				setState(1012);
				match(CLOSE_PAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Open_sequence_patternContext extends ParserRuleContext {
		public Maybe_star_patternContext maybe_star_pattern() {
			return getRuleContext(Maybe_star_patternContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Python3Parser.COMMA, 0); }
		public Maybe_sequence_patternContext maybe_sequence_pattern() {
			return getRuleContext(Maybe_sequence_patternContext.class,0);
		}
		public Open_sequence_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_open_sequence_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterOpen_sequence_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitOpen_sequence_pattern(this);
		}
	}

	public final Open_sequence_patternContext open_sequence_pattern() throws RecognitionException {
		Open_sequence_patternContext _localctx = new Open_sequence_patternContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_open_sequence_pattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1015);
			maybe_star_pattern();
			setState(1016);
			match(COMMA);
			setState(1018);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 864833243555299352L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 8449L) != 0)) {
				{
				setState(1017);
				maybe_sequence_pattern();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Maybe_sequence_patternContext extends ParserRuleContext {
		public List<Maybe_star_patternContext> maybe_star_pattern() {
			return getRuleContexts(Maybe_star_patternContext.class);
		}
		public Maybe_star_patternContext maybe_star_pattern(int i) {
			return getRuleContext(Maybe_star_patternContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Maybe_sequence_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maybe_sequence_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMaybe_sequence_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMaybe_sequence_pattern(this);
		}
	}

	public final Maybe_sequence_patternContext maybe_sequence_pattern() throws RecognitionException {
		Maybe_sequence_patternContext _localctx = new Maybe_sequence_patternContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_maybe_sequence_pattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1020);
			maybe_star_pattern();
			setState(1025);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,123,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1021);
					match(COMMA);
					setState(1022);
					maybe_star_pattern();
					}
					} 
				}
				setState(1027);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,123,_ctx);
			}
			setState(1029);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1028);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Maybe_star_patternContext extends ParserRuleContext {
		public Star_patternContext star_pattern() {
			return getRuleContext(Star_patternContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public Maybe_star_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maybe_star_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMaybe_star_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMaybe_star_pattern(this);
		}
	}

	public final Maybe_star_patternContext maybe_star_pattern() throws RecognitionException {
		Maybe_star_patternContext _localctx = new Maybe_star_patternContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_maybe_star_pattern);
		try {
			setState(1033);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(1031);
				star_pattern();
				}
				break;
			case STRING:
			case NUMBER:
			case FALSE:
			case MATCH:
			case NONE:
			case TRUE:
			case UNDERSCORE:
			case NAME:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case MINUS:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1032);
				pattern();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Star_patternContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public Pattern_capture_targetContext pattern_capture_target() {
			return getRuleContext(Pattern_capture_targetContext.class,0);
		}
		public Wildcard_patternContext wildcard_pattern() {
			return getRuleContext(Wildcard_patternContext.class,0);
		}
		public Star_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_star_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterStar_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitStar_pattern(this);
		}
	}

	public final Star_patternContext star_pattern() throws RecognitionException {
		Star_patternContext _localctx = new Star_patternContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_star_pattern);
		try {
			setState(1039);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,126,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1035);
				match(STAR);
				setState(1036);
				pattern_capture_target();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1037);
				match(STAR);
				setState(1038);
				wildcard_pattern();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Mapping_patternContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(Python3Parser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(Python3Parser.CLOSE_BRACE, 0); }
		public Double_star_patternContext double_star_pattern() {
			return getRuleContext(Double_star_patternContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Items_patternContext items_pattern() {
			return getRuleContext(Items_patternContext.class,0);
		}
		public Mapping_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapping_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMapping_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMapping_pattern(this);
		}
	}

	public final Mapping_patternContext mapping_pattern() throws RecognitionException {
		Mapping_patternContext _localctx = new Mapping_patternContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_mapping_pattern);
		int _la;
		try {
			setState(1066);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,130,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1041);
				match(OPEN_BRACE);
				setState(1042);
				match(CLOSE_BRACE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1043);
				match(OPEN_BRACE);
				setState(1044);
				double_star_pattern();
				setState(1046);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1045);
					match(COMMA);
					}
				}

				setState(1048);
				match(CLOSE_BRACE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1050);
				match(OPEN_BRACE);
				setState(1051);
				items_pattern();
				setState(1052);
				match(COMMA);
				setState(1053);
				double_star_pattern();
				setState(1055);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1054);
					match(COMMA);
					}
				}

				setState(1057);
				match(CLOSE_BRACE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1059);
				match(OPEN_BRACE);
				setState(1060);
				items_pattern();
				setState(1062);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1061);
					match(COMMA);
					}
				}

				setState(1064);
				match(CLOSE_BRACE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Items_patternContext extends ParserRuleContext {
		public List<Key_value_patternContext> key_value_pattern() {
			return getRuleContexts(Key_value_patternContext.class);
		}
		public Key_value_patternContext key_value_pattern(int i) {
			return getRuleContext(Key_value_patternContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Items_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_items_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterItems_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitItems_pattern(this);
		}
	}

	public final Items_patternContext items_pattern() throws RecognitionException {
		Items_patternContext _localctx = new Items_patternContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_items_pattern);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1068);
			key_value_pattern();
			setState(1073);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,131,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1069);
					match(COMMA);
					setState(1070);
					key_value_pattern();
					}
					} 
				}
				setState(1075);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,131,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Key_value_patternContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public Literal_exprContext literal_expr() {
			return getRuleContext(Literal_exprContext.class,0);
		}
		public AttrContext attr() {
			return getRuleContext(AttrContext.class,0);
		}
		public Key_value_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key_value_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterKey_value_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitKey_value_pattern(this);
		}
	}

	public final Key_value_patternContext key_value_pattern() throws RecognitionException {
		Key_value_patternContext _localctx = new Key_value_patternContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_key_value_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1078);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case NUMBER:
			case FALSE:
			case NONE:
			case TRUE:
			case MINUS:
				{
				setState(1076);
				literal_expr();
				}
				break;
			case MATCH:
			case UNDERSCORE:
			case NAME:
				{
				setState(1077);
				attr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1080);
			match(COLON);
			setState(1081);
			pattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Double_star_patternContext extends ParserRuleContext {
		public TerminalNode POWER() { return getToken(Python3Parser.POWER, 0); }
		public Pattern_capture_targetContext pattern_capture_target() {
			return getRuleContext(Pattern_capture_targetContext.class,0);
		}
		public Double_star_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_star_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDouble_star_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDouble_star_pattern(this);
		}
	}

	public final Double_star_patternContext double_star_pattern() throws RecognitionException {
		Double_star_patternContext _localctx = new Double_star_patternContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_double_star_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1083);
			match(POWER);
			setState(1084);
			pattern_capture_target();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Class_patternContext extends ParserRuleContext {
		public Name_or_attrContext name_or_attr() {
			return getRuleContext(Name_or_attrContext.class,0);
		}
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public Positional_patternsContext positional_patterns() {
			return getRuleContext(Positional_patternsContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Keyword_patternsContext keyword_patterns() {
			return getRuleContext(Keyword_patternsContext.class,0);
		}
		public Class_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterClass_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitClass_pattern(this);
		}
	}

	public final Class_patternContext class_pattern() throws RecognitionException {
		Class_patternContext _localctx = new Class_patternContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_class_pattern);
		int _la;
		try {
			setState(1116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,136,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1086);
				name_or_attr();
				setState(1087);
				match(OPEN_PAREN);
				setState(1088);
				match(CLOSE_PAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1090);
				name_or_attr();
				setState(1091);
				match(OPEN_PAREN);
				setState(1092);
				positional_patterns();
				setState(1094);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1093);
					match(COMMA);
					}
				}

				setState(1096);
				match(CLOSE_PAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1098);
				name_or_attr();
				setState(1099);
				match(OPEN_PAREN);
				setState(1100);
				keyword_patterns();
				setState(1102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1101);
					match(COMMA);
					}
				}

				setState(1104);
				match(CLOSE_PAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1106);
				name_or_attr();
				setState(1107);
				match(OPEN_PAREN);
				setState(1108);
				positional_patterns();
				setState(1109);
				match(COMMA);
				setState(1110);
				keyword_patterns();
				setState(1112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1111);
					match(COMMA);
					}
				}

				setState(1114);
				match(CLOSE_PAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Positional_patternsContext extends ParserRuleContext {
		public List<PatternContext> pattern() {
			return getRuleContexts(PatternContext.class);
		}
		public PatternContext pattern(int i) {
			return getRuleContext(PatternContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Positional_patternsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positional_patterns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterPositional_patterns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitPositional_patterns(this);
		}
	}

	public final Positional_patternsContext positional_patterns() throws RecognitionException {
		Positional_patternsContext _localctx = new Positional_patternsContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_positional_patterns);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1118);
			pattern();
			setState(1123);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,137,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1119);
					match(COMMA);
					setState(1120);
					pattern();
					}
					} 
				}
				setState(1125);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,137,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Keyword_patternsContext extends ParserRuleContext {
		public List<Keyword_patternContext> keyword_pattern() {
			return getRuleContexts(Keyword_patternContext.class);
		}
		public Keyword_patternContext keyword_pattern(int i) {
			return getRuleContext(Keyword_patternContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Keyword_patternsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword_patterns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterKeyword_patterns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitKeyword_patterns(this);
		}
	}

	public final Keyword_patternsContext keyword_patterns() throws RecognitionException {
		Keyword_patternsContext _localctx = new Keyword_patternsContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_keyword_patterns);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1126);
			keyword_pattern();
			setState(1131);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,138,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1127);
					match(COMMA);
					setState(1128);
					keyword_pattern();
					}
					} 
				}
				setState(1133);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,138,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Keyword_patternContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(Python3Parser.ASSIGN, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public Keyword_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterKeyword_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitKeyword_pattern(this);
		}
	}

	public final Keyword_patternContext keyword_pattern() throws RecognitionException {
		Keyword_patternContext _localctx = new Keyword_patternContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_keyword_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1134);
			name();
			setState(1135);
			match(ASSIGN);
			setState(1136);
			pattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TestContext extends ParserRuleContext {
		public List<Or_testContext> or_test() {
			return getRuleContexts(Or_testContext.class);
		}
		public Or_testContext or_test(int i) {
			return getRuleContext(Or_testContext.class,i);
		}
		public TerminalNode IF() { return getToken(Python3Parser.IF, 0); }
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public LambdefContext lambdef() {
			return getRuleContext(LambdefContext.class,0);
		}
		public TestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitTest(this);
		}
	}

	public final TestContext test() throws RecognitionException {
		TestContext _localctx = new TestContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_test);
		int _la;
		try {
			setState(1147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case NUMBER:
			case AWAIT:
			case FALSE:
			case MATCH:
			case NONE:
			case NOT:
			case TRUE:
			case UNDERSCORE:
			case NAME:
			case ELLIPSIS:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
			case WILDCARD:
				enterOuterAlt(_localctx, 1);
				{
				setState(1138);
				or_test();
				setState(1144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(1139);
					match(IF);
					setState(1140);
					or_test();
					setState(1141);
					match(ELSE);
					setState(1142);
					test();
					}
				}

				}
				break;
			case LAMBDA:
				enterOuterAlt(_localctx, 2);
				{
				setState(1146);
				lambdef();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Test_nocondContext extends ParserRuleContext {
		public Or_testContext or_test() {
			return getRuleContext(Or_testContext.class,0);
		}
		public Lambdef_nocondContext lambdef_nocond() {
			return getRuleContext(Lambdef_nocondContext.class,0);
		}
		public Test_nocondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test_nocond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterTest_nocond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitTest_nocond(this);
		}
	}

	public final Test_nocondContext test_nocond() throws RecognitionException {
		Test_nocondContext _localctx = new Test_nocondContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_test_nocond);
		try {
			setState(1151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case NUMBER:
			case AWAIT:
			case FALSE:
			case MATCH:
			case NONE:
			case NOT:
			case TRUE:
			case UNDERSCORE:
			case NAME:
			case ELLIPSIS:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
			case WILDCARD:
				enterOuterAlt(_localctx, 1);
				{
				setState(1149);
				or_test();
				}
				break;
			case LAMBDA:
				enterOuterAlt(_localctx, 2);
				{
				setState(1150);
				lambdef_nocond();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdefContext extends ParserRuleContext {
		public TerminalNode LAMBDA() { return getToken(Python3Parser.LAMBDA, 0); }
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public VarargslistContext varargslist() {
			return getRuleContext(VarargslistContext.class,0);
		}
		public LambdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterLambdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitLambdef(this);
		}
	}

	public final LambdefContext lambdef() throws RecognitionException {
		LambdefContext _localctx = new LambdefContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_lambdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1153);
			match(LAMBDA);
			setState(1155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 288372214225436672L) != 0) || _la==POWER || _la==WILDCARD) {
				{
				setState(1154);
				varargslist();
				}
			}

			setState(1157);
			match(COLON);
			setState(1158);
			test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Lambdef_nocondContext extends ParserRuleContext {
		public TerminalNode LAMBDA() { return getToken(Python3Parser.LAMBDA, 0); }
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public Test_nocondContext test_nocond() {
			return getRuleContext(Test_nocondContext.class,0);
		}
		public VarargslistContext varargslist() {
			return getRuleContext(VarargslistContext.class,0);
		}
		public Lambdef_nocondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdef_nocond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterLambdef_nocond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitLambdef_nocond(this);
		}
	}

	public final Lambdef_nocondContext lambdef_nocond() throws RecognitionException {
		Lambdef_nocondContext _localctx = new Lambdef_nocondContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_lambdef_nocond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1160);
			match(LAMBDA);
			setState(1162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 288372214225436672L) != 0) || _la==POWER || _la==WILDCARD) {
				{
				setState(1161);
				varargslist();
				}
			}

			setState(1164);
			match(COLON);
			setState(1165);
			test_nocond();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Or_testContext extends ParserRuleContext {
		public List<And_testContext> and_test() {
			return getRuleContexts(And_testContext.class);
		}
		public And_testContext and_test(int i) {
			return getRuleContext(And_testContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(Python3Parser.OR); }
		public TerminalNode OR(int i) {
			return getToken(Python3Parser.OR, i);
		}
		public Or_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterOr_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitOr_test(this);
		}
	}

	public final Or_testContext or_test() throws RecognitionException {
		Or_testContext _localctx = new Or_testContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_or_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1167);
			and_test();
			setState(1172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(1168);
				match(OR);
				setState(1169);
				and_test();
				}
				}
				setState(1174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class And_testContext extends ParserRuleContext {
		public List<Not_testContext> not_test() {
			return getRuleContexts(Not_testContext.class);
		}
		public Not_testContext not_test(int i) {
			return getRuleContext(Not_testContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(Python3Parser.AND); }
		public TerminalNode AND(int i) {
			return getToken(Python3Parser.AND, i);
		}
		public And_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAnd_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAnd_test(this);
		}
	}

	public final And_testContext and_test() throws RecognitionException {
		And_testContext _localctx = new And_testContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_and_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1175);
			not_test();
			setState(1180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(1176);
				match(AND);
				setState(1177);
				not_test();
				}
				}
				setState(1182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Not_testContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(Python3Parser.NOT, 0); }
		public Not_testContext not_test() {
			return getRuleContext(Not_testContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public Not_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterNot_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitNot_test(this);
		}
	}

	public final Not_testContext not_test() throws RecognitionException {
		Not_testContext _localctx = new Not_testContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_not_test);
		try {
			setState(1186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1183);
				match(NOT);
				setState(1184);
				not_test();
				}
				break;
			case STRING:
			case NUMBER:
			case AWAIT:
			case FALSE:
			case MATCH:
			case NONE:
			case TRUE:
			case UNDERSCORE:
			case NAME:
			case ELLIPSIS:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
			case WILDCARD:
				enterOuterAlt(_localctx, 2);
				{
				setState(1185);
				comparison();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<Comp_opContext> comp_op() {
			return getRuleContexts(Comp_opContext.class);
		}
		public Comp_opContext comp_op(int i) {
			return getRuleContext(Comp_opContext.class,i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_comparison);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1188);
			expr(0);
			setState(1194);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,147,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1189);
					comp_op();
					setState(1190);
					expr(0);
					}
					} 
				}
				setState(1196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,147,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Comp_opContext extends ParserRuleContext {
		public TerminalNode LESS_THAN() { return getToken(Python3Parser.LESS_THAN, 0); }
		public TerminalNode GREATER_THAN() { return getToken(Python3Parser.GREATER_THAN, 0); }
		public TerminalNode EQUALS() { return getToken(Python3Parser.EQUALS, 0); }
		public TerminalNode GT_EQ() { return getToken(Python3Parser.GT_EQ, 0); }
		public TerminalNode LT_EQ() { return getToken(Python3Parser.LT_EQ, 0); }
		public TerminalNode NOT_EQ_1() { return getToken(Python3Parser.NOT_EQ_1, 0); }
		public TerminalNode NOT_EQ_2() { return getToken(Python3Parser.NOT_EQ_2, 0); }
		public TerminalNode IN() { return getToken(Python3Parser.IN, 0); }
		public TerminalNode NOT() { return getToken(Python3Parser.NOT, 0); }
		public TerminalNode IS() { return getToken(Python3Parser.IS, 0); }
		public Comp_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterComp_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitComp_op(this);
		}
	}

	public final Comp_opContext comp_op() throws RecognitionException {
		Comp_opContext _localctx = new Comp_opContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_comp_op);
		try {
			setState(1210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,148,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1197);
				match(LESS_THAN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1198);
				match(GREATER_THAN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1199);
				match(EQUALS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1200);
				match(GT_EQ);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1201);
				match(LT_EQ);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1202);
				match(NOT_EQ_1);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1203);
				match(NOT_EQ_2);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1204);
				match(IN);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1205);
				match(NOT);
				setState(1206);
				match(IN);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1207);
				match(IS);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1208);
				match(IS);
				setState(1209);
				match(NOT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Star_exprContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Star_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_star_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterStar_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitStar_expr(this);
		}
	}

	public final Star_exprContext star_expr() throws RecognitionException {
		Star_exprContext _localctx = new Star_exprContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_star_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1212);
			match(STAR);
			setState(1213);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public Expr_wildcardContext expr_wildcard() {
			return getRuleContext(Expr_wildcardContext.class,0);
		}
		public Number_wildcardContext number_wildcard() {
			return getRuleContext(Number_wildcardContext.class,0);
		}
		public Atom_exprContext atom_expr() {
			return getRuleContext(Atom_exprContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(Python3Parser.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(Python3Parser.ADD, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(Python3Parser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(Python3Parser.MINUS, i);
		}
		public List<TerminalNode> NOT_OP() { return getTokens(Python3Parser.NOT_OP); }
		public TerminalNode NOT_OP(int i) {
			return getToken(Python3Parser.NOT_OP, i);
		}
		public TerminalNode POWER() { return getToken(Python3Parser.POWER, 0); }
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public TerminalNode AT() { return getToken(Python3Parser.AT, 0); }
		public TerminalNode DIV() { return getToken(Python3Parser.DIV, 0); }
		public TerminalNode MOD() { return getToken(Python3Parser.MOD, 0); }
		public TerminalNode IDIV() { return getToken(Python3Parser.IDIV, 0); }
		public TerminalNode LEFT_SHIFT() { return getToken(Python3Parser.LEFT_SHIFT, 0); }
		public TerminalNode RIGHT_SHIFT() { return getToken(Python3Parser.RIGHT_SHIFT, 0); }
		public TerminalNode AND_OP() { return getToken(Python3Parser.AND_OP, 0); }
		public TerminalNode XOR() { return getToken(Python3Parser.XOR, 0); }
		public TerminalNode OR_OP() { return getToken(Python3Parser.OR_OP, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 194;
		enterRecursionRule(_localctx, 194, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,150,_ctx) ) {
			case 1:
				{
				setState(1216);
				expr_wildcard();
				}
				break;
			case 2:
				{
				setState(1217);
				number_wildcard();
				}
				break;
			case 3:
				{
				setState(1218);
				atom_expr();
				}
				break;
			case 4:
				{
				setState(1220); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1219);
						_la = _input.LA(1);
						if ( !(((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 35L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1222); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,149,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(1224);
				expr(7);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1250);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,152,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1248);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,151,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1227);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1228);
						match(POWER);
						setState(1229);
						expr(9);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1230);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1231);
						_la = _input.LA(1);
						if ( !(((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & 1074659329L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1232);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1233);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1234);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1235);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1236);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1237);
						_la = _input.LA(1);
						if ( !(_la==LEFT_SHIFT || _la==RIGHT_SHIFT) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1238);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1239);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1240);
						match(AND_OP);
						setState(1241);
						expr(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1242);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1243);
						match(XOR);
						setState(1244);
						expr(3);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1245);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1246);
						match(OR_OP);
						setState(1247);
						expr(2);
						}
						break;
					}
					} 
				}
				setState(1252);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,152,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom_exprContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode AWAIT() { return getToken(Python3Parser.AWAIT, 0); }
		public List<TrailerContext> trailer() {
			return getRuleContexts(TrailerContext.class);
		}
		public TrailerContext trailer(int i) {
			return getRuleContext(TrailerContext.class,i);
		}
		public Atom_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAtom_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAtom_expr(this);
		}
	}

	public final Atom_exprContext atom_expr() throws RecognitionException {
		Atom_exprContext _localctx = new Atom_exprContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_atom_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AWAIT) {
				{
				setState(1253);
				match(AWAIT);
				}
			}

			setState(1256);
			atom();
			setState(1260);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,154,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1257);
					trailer();
					}
					} 
				}
				setState(1262);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,154,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public Yield_exprContext yield_expr() {
			return getRuleContext(Yield_exprContext.class,0);
		}
		public Testlist_compContext testlist_comp() {
			return getRuleContext(Testlist_compContext.class,0);
		}
		public TerminalNode OPEN_BRACK() { return getToken(Python3Parser.OPEN_BRACK, 0); }
		public TerminalNode CLOSE_BRACK() { return getToken(Python3Parser.CLOSE_BRACK, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(Python3Parser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(Python3Parser.CLOSE_BRACE, 0); }
		public DictorsetmakerContext dictorsetmaker() {
			return getRuleContext(DictorsetmakerContext.class,0);
		}
		public Atom_wildcardContext atom_wildcard() {
			return getRuleContext(Atom_wildcardContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(Python3Parser.NUMBER, 0); }
		public List<TerminalNode> STRING() { return getTokens(Python3Parser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(Python3Parser.STRING, i);
		}
		public TerminalNode ELLIPSIS() { return getToken(Python3Parser.ELLIPSIS, 0); }
		public TerminalNode NONE() { return getToken(Python3Parser.NONE, 0); }
		public TerminalNode TRUE() { return getToken(Python3Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Python3Parser.FALSE, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_atom);
		int _la;
		try {
			int _alt;
			setState(1291);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1263);
				match(OPEN_PAREN);
				setState(1266);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case YIELD:
					{
					setState(1264);
					yield_expr();
					}
					break;
				case STRING:
				case NUMBER:
				case AWAIT:
				case FALSE:
				case LAMBDA:
				case MATCH:
				case NONE:
				case NOT:
				case TRUE:
				case UNDERSCORE:
				case NAME:
				case ELLIPSIS:
				case STAR:
				case OPEN_PAREN:
				case OPEN_BRACK:
				case ADD:
				case MINUS:
				case NOT_OP:
				case OPEN_BRACE:
				case WILDCARD:
					{
					setState(1265);
					testlist_comp();
					}
					break;
				case CLOSE_PAREN:
					break;
				default:
					break;
				}
				setState(1268);
				match(CLOSE_PAREN);
				}
				break;
			case OPEN_BRACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(1269);
				match(OPEN_BRACK);
				setState(1271);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
					{
					setState(1270);
					testlist_comp();
					}
				}

				setState(1273);
				match(CLOSE_BRACK);
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(1274);
				match(OPEN_BRACE);
				setState(1276);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 549755864581L) != 0)) {
					{
					setState(1275);
					dictorsetmaker();
					}
				}

				setState(1278);
				match(CLOSE_BRACE);
				}
				break;
			case WILDCARD:
				enterOuterAlt(_localctx, 4);
				{
				setState(1279);
				atom_wildcard();
				}
				break;
			case MATCH:
			case UNDERSCORE:
			case NAME:
				enterOuterAlt(_localctx, 5);
				{
				setState(1280);
				name();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 6);
				{
				setState(1281);
				match(NUMBER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 7);
				{
				setState(1283); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1282);
						match(STRING);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1285); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,158,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case ELLIPSIS:
				enterOuterAlt(_localctx, 8);
				{
				setState(1287);
				match(ELLIPSIS);
				}
				break;
			case NONE:
				enterOuterAlt(_localctx, 9);
				{
				setState(1288);
				match(NONE);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 10);
				{
				setState(1289);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 11);
				{
				setState(1290);
				match(FALSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public TerminalNode UNDERSCORE() { return getToken(Python3Parser.UNDERSCORE, 0); }
		public TerminalNode MATCH() { return getToken(Python3Parser.MATCH, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1293);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 141838073724928L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Testlist_compContext extends ParserRuleContext {
		public List<List_wildcardContext> list_wildcard() {
			return getRuleContexts(List_wildcardContext.class);
		}
		public List_wildcardContext list_wildcard(int i) {
			return getRuleContext(List_wildcardContext.class,i);
		}
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<Star_exprContext> star_expr() {
			return getRuleContexts(Star_exprContext.class);
		}
		public Star_exprContext star_expr(int i) {
			return getRuleContext(Star_exprContext.class,i);
		}
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Testlist_compContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testlist_comp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterTestlist_comp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitTestlist_comp(this);
		}
	}

	public final Testlist_compContext testlist_comp() throws RecognitionException {
		Testlist_compContext _localctx = new Testlist_compContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_testlist_comp);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1298);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,160,_ctx) ) {
			case 1:
				{
				setState(1295);
				list_wildcard();
				}
				break;
			case 2:
				{
				setState(1296);
				test();
				}
				break;
			case 3:
				{
				setState(1297);
				star_expr();
				}
				break;
			}
			setState(1315);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASYNC:
			case FOR:
				{
				setState(1300);
				comp_for();
				}
				break;
			case CLOSE_PAREN:
			case COMMA:
			case CLOSE_BRACK:
				{
				setState(1309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,162,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1301);
						match(COMMA);
						setState(1305);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,161,_ctx) ) {
						case 1:
							{
							setState(1302);
							list_wildcard();
							}
							break;
						case 2:
							{
							setState(1303);
							test();
							}
							break;
						case 3:
							{
							setState(1304);
							star_expr();
							}
							break;
						}
						}
						} 
					}
					setState(1311);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,162,_ctx);
				}
				setState(1313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1312);
					match(COMMA);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TrailerContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public TerminalNode OPEN_BRACK() { return getToken(Python3Parser.OPEN_BRACK, 0); }
		public SubscriptlistContext subscriptlist() {
			return getRuleContext(SubscriptlistContext.class,0);
		}
		public TerminalNode CLOSE_BRACK() { return getToken(Python3Parser.CLOSE_BRACK, 0); }
		public TerminalNode DOT() { return getToken(Python3Parser.DOT, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Atom_wildcardContext atom_wildcard() {
			return getRuleContext(Atom_wildcardContext.class,0);
		}
		public TrailerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trailer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterTrailer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitTrailer(this);
		}
	}

	public final TrailerContext trailer() throws RecognitionException {
		TrailerContext _localctx = new TrailerContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_trailer);
		int _la;
		try {
			setState(1331);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1317);
				match(OPEN_PAREN);
				setState(1319);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 549755864581L) != 0)) {
					{
					setState(1318);
					arglist();
					}
				}

				setState(1321);
				match(CLOSE_PAREN);
				}
				break;
			case OPEN_BRACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(1322);
				match(OPEN_BRACK);
				setState(1323);
				subscriptlist();
				setState(1324);
				match(CLOSE_BRACK);
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(1326);
				match(DOT);
				setState(1329);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MATCH:
				case UNDERSCORE:
				case NAME:
					{
					setState(1327);
					name();
					}
					break;
				case WILDCARD:
					{
					setState(1328);
					atom_wildcard();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubscriptlistContext extends ParserRuleContext {
		public List<Subscript_Context> subscript_() {
			return getRuleContexts(Subscript_Context.class);
		}
		public Subscript_Context subscript_(int i) {
			return getRuleContext(Subscript_Context.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public SubscriptlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subscriptlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSubscriptlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSubscriptlist(this);
		}
	}

	public final SubscriptlistContext subscriptlist() throws RecognitionException {
		SubscriptlistContext _localctx = new SubscriptlistContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_subscriptlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1333);
			subscript_();
			setState(1338);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,168,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1334);
					match(COMMA);
					setState(1335);
					subscript_();
					}
					} 
				}
				setState(1340);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,168,_ctx);
			}
			setState(1342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1341);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Subscript_Context extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public SliceopContext sliceop() {
			return getRuleContext(SliceopContext.class,0);
		}
		public Subscript_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subscript_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSubscript_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSubscript_(this);
		}
	}

	public final Subscript_Context subscript_() throws RecognitionException {
		Subscript_Context _localctx = new Subscript_Context(_ctx, getState());
		enterRule(_localctx, 208, RULE_subscript_);
		int _la;
		try {
			setState(1355);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,173,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1344);
				test();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
					{
					setState(1345);
					test();
					}
				}

				setState(1348);
				match(COLON);
				setState(1350);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
					{
					setState(1349);
					test();
					}
				}

				setState(1353);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(1352);
					sliceop();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SliceopContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public SliceopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sliceop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSliceop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSliceop(this);
		}
	}

	public final SliceopContext sliceop() throws RecognitionException {
		SliceopContext _localctx = new SliceopContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_sliceop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1357);
			match(COLON);
			setState(1359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(1358);
				test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprlistContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<Star_exprContext> star_expr() {
			return getRuleContexts(Star_exprContext.class);
		}
		public Star_exprContext star_expr(int i) {
			return getRuleContext(Star_exprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public ExprlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterExprlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitExprlist(this);
		}
	}

	public final ExprlistContext exprlist() throws RecognitionException {
		ExprlistContext _localctx = new ExprlistContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_exprlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1363);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case NUMBER:
			case AWAIT:
			case FALSE:
			case MATCH:
			case NONE:
			case TRUE:
			case UNDERSCORE:
			case NAME:
			case ELLIPSIS:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
			case WILDCARD:
				{
				setState(1361);
				expr(0);
				}
				break;
			case STAR:
				{
				setState(1362);
				star_expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1372);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,177,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1365);
					match(COMMA);
					setState(1368);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case STRING:
					case NUMBER:
					case AWAIT:
					case FALSE:
					case MATCH:
					case NONE:
					case TRUE:
					case UNDERSCORE:
					case NAME:
					case ELLIPSIS:
					case OPEN_PAREN:
					case OPEN_BRACK:
					case ADD:
					case MINUS:
					case NOT_OP:
					case OPEN_BRACE:
					case WILDCARD:
						{
						setState(1366);
						expr(0);
						}
						break;
					case STAR:
						{
						setState(1367);
						star_expr();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(1374);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,177,_ctx);
			}
			setState(1376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1375);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TestlistContext extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public TestlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterTestlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitTestlist(this);
		}
	}

	public final TestlistContext testlist() throws RecognitionException {
		TestlistContext _localctx = new TestlistContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_testlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1378);
			test();
			setState(1383);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,179,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1379);
					match(COMMA);
					setState(1380);
					test();
					}
					} 
				}
				setState(1385);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,179,_ctx);
			}
			setState(1387);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1386);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DictorsetmakerContext extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(Python3Parser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(Python3Parser.COLON, i);
		}
		public List<TerminalNode> POWER() { return getTokens(Python3Parser.POWER); }
		public TerminalNode POWER(int i) {
			return getToken(Python3Parser.POWER, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public List<Star_exprContext> star_expr() {
			return getRuleContexts(Star_exprContext.class);
		}
		public Star_exprContext star_expr(int i) {
			return getRuleContext(Star_exprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public DictorsetmakerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dictorsetmaker; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDictorsetmaker(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDictorsetmaker(this);
		}
	}

	public final DictorsetmakerContext dictorsetmaker() throws RecognitionException {
		DictorsetmakerContext _localctx = new DictorsetmakerContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_dictorsetmaker);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1437);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,191,_ctx) ) {
			case 1:
				{
				{
				setState(1395);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STRING:
				case NUMBER:
				case AWAIT:
				case FALSE:
				case LAMBDA:
				case MATCH:
				case NONE:
				case NOT:
				case TRUE:
				case UNDERSCORE:
				case NAME:
				case ELLIPSIS:
				case OPEN_PAREN:
				case OPEN_BRACK:
				case ADD:
				case MINUS:
				case NOT_OP:
				case OPEN_BRACE:
				case WILDCARD:
					{
					setState(1389);
					test();
					setState(1390);
					match(COLON);
					setState(1391);
					test();
					}
					break;
				case POWER:
					{
					setState(1393);
					match(POWER);
					setState(1394);
					expr(0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1415);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ASYNC:
				case FOR:
					{
					setState(1397);
					comp_for();
					}
					break;
				case COMMA:
				case CLOSE_BRACE:
					{
					setState(1409);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,183,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(1398);
							match(COMMA);
							setState(1405);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case STRING:
							case NUMBER:
							case AWAIT:
							case FALSE:
							case LAMBDA:
							case MATCH:
							case NONE:
							case NOT:
							case TRUE:
							case UNDERSCORE:
							case NAME:
							case ELLIPSIS:
							case OPEN_PAREN:
							case OPEN_BRACK:
							case ADD:
							case MINUS:
							case NOT_OP:
							case OPEN_BRACE:
							case WILDCARD:
								{
								setState(1399);
								test();
								setState(1400);
								match(COLON);
								setState(1401);
								test();
								}
								break;
							case POWER:
								{
								setState(1403);
								match(POWER);
								setState(1404);
								expr(0);
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							} 
						}
						setState(1411);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,183,_ctx);
					}
					setState(1413);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(1412);
						match(COMMA);
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				break;
			case 2:
				{
				{
				setState(1419);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STRING:
				case NUMBER:
				case AWAIT:
				case FALSE:
				case LAMBDA:
				case MATCH:
				case NONE:
				case NOT:
				case TRUE:
				case UNDERSCORE:
				case NAME:
				case ELLIPSIS:
				case OPEN_PAREN:
				case OPEN_BRACK:
				case ADD:
				case MINUS:
				case NOT_OP:
				case OPEN_BRACE:
				case WILDCARD:
					{
					setState(1417);
					test();
					}
					break;
				case STAR:
					{
					setState(1418);
					star_expr();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1435);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ASYNC:
				case FOR:
					{
					setState(1421);
					comp_for();
					}
					break;
				case COMMA:
				case CLOSE_BRACE:
					{
					setState(1429);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,188,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(1422);
							match(COMMA);
							setState(1425);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case STRING:
							case NUMBER:
							case AWAIT:
							case FALSE:
							case LAMBDA:
							case MATCH:
							case NONE:
							case NOT:
							case TRUE:
							case UNDERSCORE:
							case NAME:
							case ELLIPSIS:
							case OPEN_PAREN:
							case OPEN_BRACK:
							case ADD:
							case MINUS:
							case NOT_OP:
							case OPEN_BRACE:
							case WILDCARD:
								{
								setState(1423);
								test();
								}
								break;
							case STAR:
								{
								setState(1424);
								star_expr();
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							} 
						}
						setState(1431);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,188,_ctx);
					}
					setState(1433);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(1432);
						match(COMMA);
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassdefContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(Python3Parser.CLASS, 0); }
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Simple_wildcardContext simple_wildcard() {
			return getRuleContext(Simple_wildcardContext.class,0);
		}
		public Var_wildcardContext var_wildcard() {
			return getRuleContext(Var_wildcardContext.class,0);
		}
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public ClassdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterClassdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitClassdef(this);
		}
	}

	public final ClassdefContext classdef() throws RecognitionException {
		ClassdefContext _localctx = new ClassdefContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_classdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1439);
			match(CLASS);
			setState(1443);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,192,_ctx) ) {
			case 1:
				{
				setState(1440);
				name();
				}
				break;
			case 2:
				{
				setState(1441);
				simple_wildcard();
				}
				break;
			case 3:
				{
				setState(1442);
				var_wildcard();
				}
				break;
			}
			setState(1450);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_PAREN) {
				{
				setState(1445);
				match(OPEN_PAREN);
				setState(1447);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 549755864581L) != 0)) {
					{
					setState(1446);
					arglist();
					}
				}

				setState(1449);
				match(CLOSE_PAREN);
				}
			}

			setState(1452);
			match(COLON);
			setState(1453);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArglistContext extends ParserRuleContext {
		public List<List_wildcardContext> list_wildcard() {
			return getRuleContexts(List_wildcardContext.class);
		}
		public List_wildcardContext list_wildcard(int i) {
			return getRuleContext(List_wildcardContext.class,i);
		}
		public List<Number_wildcardContext> number_wildcard() {
			return getRuleContexts(Number_wildcardContext.class);
		}
		public Number_wildcardContext number_wildcard(int i) {
			return getRuleContext(Number_wildcardContext.class,i);
		}
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterArglist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitArglist(this);
		}
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_arglist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1458);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,195,_ctx) ) {
			case 1:
				{
				setState(1455);
				list_wildcard();
				}
				break;
			case 2:
				{
				setState(1456);
				number_wildcard();
				}
				break;
			case 3:
				{
				setState(1457);
				argument();
				}
				break;
			}
			setState(1468);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,197,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1460);
					match(COMMA);
					setState(1464);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,196,_ctx) ) {
					case 1:
						{
						setState(1461);
						list_wildcard();
						}
						break;
					case 2:
						{
						setState(1462);
						number_wildcard();
						}
						break;
					case 3:
						{
						setState(1463);
						argument();
						}
						break;
					}
					}
					} 
				}
				setState(1470);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,197,_ctx);
			}
			setState(1472);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1471);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public Var_wildcardContext var_wildcard() {
			return getRuleContext(Var_wildcardContext.class,0);
		}
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(Python3Parser.ASSIGN, 0); }
		public TerminalNode POWER() { return getToken(Python3Parser.POWER, 0); }
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitArgument(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1487);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,200,_ctx) ) {
			case 1:
				{
				setState(1474);
				var_wildcard();
				}
				break;
			case 2:
				{
				setState(1475);
				test();
				setState(1477);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASYNC || _la==FOR) {
					{
					setState(1476);
					comp_for();
					}
				}

				}
				break;
			case 3:
				{
				setState(1479);
				test();
				setState(1480);
				match(ASSIGN);
				setState(1481);
				test();
				}
				break;
			case 4:
				{
				setState(1483);
				match(POWER);
				setState(1484);
				test();
				}
				break;
			case 5:
				{
				setState(1485);
				match(STAR);
				setState(1486);
				test();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Comp_iterContext extends ParserRuleContext {
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public Comp_ifContext comp_if() {
			return getRuleContext(Comp_ifContext.class,0);
		}
		public Comp_iterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_iter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterComp_iter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitComp_iter(this);
		}
	}

	public final Comp_iterContext comp_iter() throws RecognitionException {
		Comp_iterContext _localctx = new Comp_iterContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_comp_iter);
		try {
			setState(1491);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASYNC:
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(1489);
				comp_for();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(1490);
				comp_if();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Comp_forContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(Python3Parser.FOR, 0); }
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public TerminalNode IN() { return getToken(Python3Parser.IN, 0); }
		public Or_testContext or_test() {
			return getRuleContext(Or_testContext.class,0);
		}
		public TerminalNode ASYNC() { return getToken(Python3Parser.ASYNC, 0); }
		public Comp_iterContext comp_iter() {
			return getRuleContext(Comp_iterContext.class,0);
		}
		public Comp_forContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_for; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterComp_for(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitComp_for(this);
		}
	}

	public final Comp_forContext comp_for() throws RecognitionException {
		Comp_forContext _localctx = new Comp_forContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_comp_for);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1494);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASYNC) {
				{
				setState(1493);
				match(ASYNC);
				}
			}

			setState(1496);
			match(FOR);
			setState(1497);
			exprlist();
			setState(1498);
			match(IN);
			setState(1499);
			or_test();
			setState(1501);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 37749248L) != 0)) {
				{
				setState(1500);
				comp_iter();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Comp_ifContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(Python3Parser.IF, 0); }
		public Test_nocondContext test_nocond() {
			return getRuleContext(Test_nocondContext.class,0);
		}
		public Comp_iterContext comp_iter() {
			return getRuleContext(Comp_iterContext.class,0);
		}
		public Comp_ifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_if; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterComp_if(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitComp_if(this);
		}
	}

	public final Comp_ifContext comp_if() throws RecognitionException {
		Comp_ifContext _localctx = new Comp_ifContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_comp_if);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1503);
			match(IF);
			setState(1504);
			test_nocond();
			setState(1506);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 37749248L) != 0)) {
				{
				setState(1505);
				comp_iter();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Encoding_declContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Encoding_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_encoding_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterEncoding_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitEncoding_decl(this);
		}
	}

	public final Encoding_declContext encoding_decl() throws RecognitionException {
		Encoding_declContext _localctx = new Encoding_declContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_encoding_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1508);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Yield_exprContext extends ParserRuleContext {
		public TerminalNode YIELD() { return getToken(Python3Parser.YIELD, 0); }
		public Yield_argContext yield_arg() {
			return getRuleContext(Yield_argContext.class,0);
		}
		public Yield_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yield_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterYield_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitYield_expr(this);
		}
	}

	public final Yield_exprContext yield_expr() throws RecognitionException {
		Yield_exprContext _localctx = new Yield_exprContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_yield_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1510);
			match(YIELD);
			setState(1512);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064614638616L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(1511);
				yield_arg();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Yield_argContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(Python3Parser.FROM, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public Yield_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yield_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterYield_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitYield_arg(this);
		}
	}

	public final Yield_argContext yield_arg() throws RecognitionException {
		Yield_argContext _localctx = new Yield_argContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_yield_arg);
		try {
			setState(1517);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FROM:
				enterOuterAlt(_localctx, 1);
				{
				setState(1514);
				match(FROM);
				setState(1515);
				test();
				}
				break;
			case STRING:
			case NUMBER:
			case AWAIT:
			case FALSE:
			case LAMBDA:
			case MATCH:
			case NONE:
			case NOT:
			case TRUE:
			case UNDERSCORE:
			case NAME:
			case ELLIPSIS:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
			case WILDCARD:
				enterOuterAlt(_localctx, 2);
				{
				setState(1516);
				testlist();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringsContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(Python3Parser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(Python3Parser.STRING, i);
		}
		public StringsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strings; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterStrings(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitStrings(this);
		}
	}

	public final StringsContext strings() throws RecognitionException {
		StringsContext _localctx = new StringsContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_strings);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1520); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1519);
				match(STRING);
				}
				}
				setState(1522); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Wildcard_numberContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(Python3Parser.OPEN_BRACE, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(Python3Parser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(Python3Parser.NUMBER, i);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(Python3Parser.CLOSE_BRACE, 0); }
		public TerminalNode COMMA() { return getToken(Python3Parser.COMMA, 0); }
		public Wildcard_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterWildcard_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitWildcard_number(this);
		}
	}

	public final Wildcard_numberContext wildcard_number() throws RecognitionException {
		Wildcard_numberContext _localctx = new Wildcard_numberContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_wildcard_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1524);
			match(OPEN_BRACE);
			setState(1525);
			match(NUMBER);
			setState(1529);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,208,_ctx) ) {
			case 1:
				{
				setState(1526);
				match(COMMA);
				}
				break;
			case 2:
				{
				setState(1527);
				match(COMMA);
				setState(1528);
				match(NUMBER);
				}
				break;
			}
			setState(1531);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Stmt_wildcardContext extends ParserRuleContext {
		public Double_wildcardContext double_wildcard() {
			return getRuleContext(Double_wildcardContext.class,0);
		}
		public Contains_wildcardContext contains_wildcard() {
			return getRuleContext(Contains_wildcardContext.class,0);
		}
		public Simple_wildcardContext simple_wildcard() {
			return getRuleContext(Simple_wildcardContext.class,0);
		}
		public Number_wildcardContext number_wildcard() {
			return getRuleContext(Number_wildcardContext.class,0);
		}
		public Var_wildcardContext var_wildcard() {
			return getRuleContext(Var_wildcardContext.class,0);
		}
		public Stmt_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterStmt_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitStmt_wildcard(this);
		}
	}

	public final Stmt_wildcardContext stmt_wildcard() throws RecognitionException {
		Stmt_wildcardContext _localctx = new Stmt_wildcardContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_stmt_wildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1538);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,209,_ctx) ) {
			case 1:
				{
				setState(1533);
				double_wildcard();
				}
				break;
			case 2:
				{
				setState(1534);
				contains_wildcard();
				}
				break;
			case 3:
				{
				setState(1535);
				simple_wildcard();
				}
				break;
			case 4:
				{
				setState(1536);
				number_wildcard();
				}
				break;
			case 5:
				{
				setState(1537);
				var_wildcard();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_wildcardContext extends ParserRuleContext {
		public Macro_callContext macro_call() {
			return getRuleContext(Macro_callContext.class,0);
		}
		public Var_wildcardContext var_wildcard() {
			return getRuleContext(Var_wildcardContext.class,0);
		}
		public Contains_wildcardContext contains_wildcard() {
			return getRuleContext(Contains_wildcardContext.class,0);
		}
		public Simple_wildcardContext simple_wildcard() {
			return getRuleContext(Simple_wildcardContext.class,0);
		}
		public Expr_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterExpr_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitExpr_wildcard(this);
		}
	}

	public final Expr_wildcardContext expr_wildcard() throws RecognitionException {
		Expr_wildcardContext _localctx = new Expr_wildcardContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_expr_wildcard);
		try {
			setState(1544);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,210,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1540);
				macro_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1541);
				var_wildcard();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1542);
				contains_wildcard();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1543);
				simple_wildcard();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom_wildcardContext extends ParserRuleContext {
		public Simple_wildcardContext simple_wildcard() {
			return getRuleContext(Simple_wildcardContext.class,0);
		}
		public Var_wildcardContext var_wildcard() {
			return getRuleContext(Var_wildcardContext.class,0);
		}
		public Atom_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterAtom_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitAtom_wildcard(this);
		}
	}

	public final Atom_wildcardContext atom_wildcard() throws RecognitionException {
		Atom_wildcardContext _localctx = new Atom_wildcardContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_atom_wildcard);
		try {
			setState(1548);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,211,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1546);
				simple_wildcard();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1547);
				var_wildcard();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(Python3Parser.WILDCARD, 0); }
		public Simple_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSimple_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSimple_wildcard(this);
		}
	}

	public final Simple_wildcardContext simple_wildcard() throws RecognitionException {
		Simple_wildcardContext _localctx = new Simple_wildcardContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_simple_wildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1550);
			match(WILDCARD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Number_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(Python3Parser.WILDCARD, 0); }
		public Wildcard_numberContext wildcard_number() {
			return getRuleContext(Wildcard_numberContext.class,0);
		}
		public Number_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterNumber_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitNumber_wildcard(this);
		}
	}

	public final Number_wildcardContext number_wildcard() throws RecognitionException {
		Number_wildcardContext _localctx = new Number_wildcardContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_number_wildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1552);
			match(WILDCARD);
			setState(1553);
			wildcard_number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Double_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(Python3Parser.WILDCARD, 0); }
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public Double_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterDouble_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitDouble_wildcard(this);
		}
	}

	public final Double_wildcardContext double_wildcard() throws RecognitionException {
		Double_wildcardContext _localctx = new Double_wildcardContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_double_wildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1555);
			match(WILDCARD);
			setState(1556);
			match(STAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(Python3Parser.WILDCARD, 0); }
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public Var_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterVar_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitVar_wildcard(this);
		}
	}

	public final Var_wildcardContext var_wildcard() throws RecognitionException {
		Var_wildcardContext _localctx = new Var_wildcardContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_var_wildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1558);
			match(WILDCARD);
			setState(1559);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Contains_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(Python3Parser.WILDCARD, 0); }
		public TerminalNode LESS_THAN() { return getToken(Python3Parser.LESS_THAN, 0); }
		public TerminalNode GREATER_THAN() { return getToken(Python3Parser.GREATER_THAN, 0); }
		public Expr_wildcardContext expr_wildcard() {
			return getRuleContext(Expr_wildcardContext.class,0);
		}
		public Expr_stmtContext expr_stmt() {
			return getRuleContext(Expr_stmtContext.class,0);
		}
		public Contains_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contains_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterContains_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitContains_wildcard(this);
		}
	}

	public final Contains_wildcardContext contains_wildcard() throws RecognitionException {
		Contains_wildcardContext _localctx = new Contains_wildcardContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_contains_wildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1561);
			match(WILDCARD);
			setState(1562);
			match(LESS_THAN);
			setState(1565);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,212,_ctx) ) {
			case 1:
				{
				setState(1563);
				expr_wildcard();
				}
				break;
			case 2:
				{
				setState(1564);
				expr_stmt();
				}
				break;
			}
			setState(1567);
			match(GREATER_THAN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_wildcardContext extends ParserRuleContext {
		public Simple_compound_wildcardContext simple_compound_wildcard() {
			return getRuleContext(Simple_compound_wildcardContext.class,0);
		}
		public Multiple_compound_wildcardContext multiple_compound_wildcard() {
			return getRuleContext(Multiple_compound_wildcardContext.class,0);
		}
		public Compound_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterCompound_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitCompound_wildcard(this);
		}
	}

	public final Compound_wildcardContext compound_wildcard() throws RecognitionException {
		Compound_wildcardContext _localctx = new Compound_wildcardContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_compound_wildcard);
		try {
			setState(1571);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,213,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1569);
				simple_compound_wildcard();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1570);
				multiple_compound_wildcard();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_compound_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(Python3Parser.WILDCARD, 0); }
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Wildcard_numberContext wildcard_number() {
			return getRuleContext(Wildcard_numberContext.class,0);
		}
		public Simple_compound_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_compound_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSimple_compound_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSimple_compound_wildcard(this);
		}
	}

	public final Simple_compound_wildcardContext simple_compound_wildcard() throws RecognitionException {
		Simple_compound_wildcardContext _localctx = new Simple_compound_wildcardContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_simple_compound_wildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1573);
			match(WILDCARD);
			setState(1574);
			match(COLON);
			setState(1576);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,214,_ctx) ) {
			case 1:
				{
				setState(1575);
				wildcard_number();
				}
				break;
			}
			setState(1578);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Multiple_compound_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(Python3Parser.WILDCARD, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public Multiple_compound_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_compound_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMultiple_compound_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMultiple_compound_wildcard(this);
		}
	}

	public final Multiple_compound_wildcardContext multiple_compound_wildcard() throws RecognitionException {
		Multiple_compound_wildcardContext _localctx = new Multiple_compound_wildcardContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_multiple_compound_wildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1580);
			match(WILDCARD);
			setState(1585);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(1581);
				match(COLON);
				setState(1582);
				match(STAR);
				}
				break;
			case STAR:
				{
				setState(1583);
				match(STAR);
				setState(1584);
				match(COLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1587);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class List_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(Python3Parser.WILDCARD, 0); }
		public TerminalNode STAR() { return getToken(Python3Parser.STAR, 0); }
		public List_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterList_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitList_wildcard(this);
		}
	}

	public final List_wildcardContext list_wildcard() throws RecognitionException {
		List_wildcardContext _localctx = new List_wildcardContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_list_wildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1589);
			match(WILDCARD);
			setState(1590);
			match(STAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Macro_callContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(Python3Parser.WILDCARD, 0); }
		public TerminalNode SUB_PATTERN() { return getToken(Python3Parser.SUB_PATTERN, 0); }
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public Macro_argsContext macro_args() {
			return getRuleContext(Macro_argsContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Macro_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMacro_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMacro_call(this);
		}
	}

	public final Macro_callContext macro_call() throws RecognitionException {
		Macro_callContext _localctx = new Macro_callContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_macro_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1592);
			match(WILDCARD);
			setState(1593);
			match(SUB_PATTERN);
			setState(1594);
			match(NAME);
			setState(1595);
			match(OPEN_PAREN);
			setState(1597);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718055479443480L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438961665L) != 0)) {
				{
				setState(1596);
				macro_args();
				}
			}

			setState(1599);
			match(CLOSE_PAREN);
			setState(1602);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,217,_ctx) ) {
			case 1:
				{
				setState(1600);
				match(COLON);
				setState(1601);
				block();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Macro_stmtsContext extends ParserRuleContext {
		public Macro_defContext macro_def() {
			return getRuleContext(Macro_defContext.class,0);
		}
		public List<TransformationContext> transformation() {
			return getRuleContexts(TransformationContext.class);
		}
		public TransformationContext transformation(int i) {
			return getRuleContext(TransformationContext.class,i);
		}
		public Macro_stmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMacro_stmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMacro_stmts(this);
		}
	}

	public final Macro_stmtsContext macro_stmts() throws RecognitionException {
		Macro_stmtsContext _localctx = new Macro_stmtsContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_macro_stmts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1604);
			macro_def();
			setState(1606); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1605);
				transformation();
				}
				}
				setState(1608); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BALISE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Macro_defContext extends ParserRuleContext {
		public MacroContext macro() {
			return getRuleContext(MacroContext.class,0);
		}
		public Macro_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMacro_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMacro_def(this);
		}
	}

	public final Macro_defContext macro_def() throws RecognitionException {
		Macro_defContext _localctx = new Macro_defContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_macro_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1610);
			macro();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MacroContext extends ParserRuleContext {
		public Compound_macroContext compound_macro() {
			return getRuleContext(Compound_macroContext.class,0);
		}
		public Simple_macroContext simple_macro() {
			return getRuleContext(Simple_macroContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public MacroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMacro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMacro(this);
		}
	}

	public final MacroContext macro() throws RecognitionException {
		MacroContext _localctx = new MacroContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_macro);
		try {
			setState(1616);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,219,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1612);
				compound_macro();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(1613);
				simple_macro();
				setState(1614);
				match(NEWLINE);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Macro_argsContext extends ParserRuleContext {
		public List<Macro_argContext> macro_arg() {
			return getRuleContexts(Macro_argContext.class);
		}
		public Macro_argContext macro_arg(int i) {
			return getRuleContext(Macro_argContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Python3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Python3Parser.COMMA, i);
		}
		public Macro_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMacro_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMacro_args(this);
		}
	}

	public final Macro_argsContext macro_args() throws RecognitionException {
		Macro_argsContext _localctx = new Macro_argsContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_macro_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1618);
			macro_arg();
			setState(1623);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1619);
				match(COMMA);
				setState(1620);
				macro_arg();
				}
				}
				setState(1625);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Macro_argContext extends ParserRuleContext {
		public Atom_wildcardContext atom_wildcard() {
			return getRuleContext(Atom_wildcardContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(Python3Parser.ASSIGN, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Macro_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterMacro_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitMacro_arg(this);
		}
	}

	public final Macro_argContext macro_arg() throws RecognitionException {
		Macro_argContext _localctx = new Macro_argContext(_ctx, getState());
		enterRule(_localctx, 274, RULE_macro_arg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1628);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,221,_ctx) ) {
			case 1:
				{
				setState(1626);
				atom_wildcard();
				}
				break;
			case 2:
				{
				setState(1627);
				atom();
				}
				break;
			}
			setState(1632);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(1630);
				match(ASSIGN);
				setState(1631);
				test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_macroContext extends ParserRuleContext {
		public TerminalNode SUB_PATTERN() { return getToken(Python3Parser.SUB_PATTERN, 0); }
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public TerminalNode OPEN_PAREN() { return getToken(Python3Parser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(Python3Parser.CLOSE_PAREN, 0); }
		public TerminalNode AND_OP() { return getToken(Python3Parser.AND_OP, 0); }
		public TerminalNode OR_OP() { return getToken(Python3Parser.OR_OP, 0); }
		public Macro_argsContext macro_args() {
			return getRuleContext(Macro_argsContext.class,0);
		}
		public Simple_macroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_macro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterSimple_macro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitSimple_macro(this);
		}
	}

	public final Simple_macroContext simple_macro() throws RecognitionException {
		Simple_macroContext _localctx = new Simple_macroContext(_ctx, getState());
		enterRule(_localctx, 276, RULE_simple_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1634);
			match(SUB_PATTERN);
			setState(1635);
			_la = _input.LA(1);
			if ( !(_la==OR_OP || _la==AND_OP) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1636);
			match(NAME);
			setState(1637);
			match(OPEN_PAREN);
			setState(1639);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718055479443480L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438961665L) != 0)) {
				{
				setState(1638);
				macro_args();
				}
			}

			setState(1641);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_macroContext extends ParserRuleContext {
		public Simple_macroContext simple_macro() {
			return getRuleContext(Simple_macroContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Python3Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Compound_macroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_macro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterCompound_macro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitCompound_macro(this);
		}
	}

	public final Compound_macroContext compound_macro() throws RecognitionException {
		Compound_macroContext _localctx = new Compound_macroContext(_ctx, getState());
		enterRule(_localctx, 278, RULE_compound_macro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1643);
			simple_macro();
			setState(1644);
			match(COLON);
			setState(1645);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TransformationContext extends ParserRuleContext {
		public TerminalNode BALISE() { return getToken(Python3Parser.BALISE, 0); }
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public TransformationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transformation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).enterTransformation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Python3ParserListener ) ((Python3ParserListener)listener).exitTransformation(this);
		}
	}

	public final TransformationContext transformation() throws RecognitionException {
		TransformationContext _localctx = new TransformationContext(_ctx, getState());
		enterRule(_localctx, 280, RULE_transformation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1647);
			match(BALISE);
			setState(1648);
			match(NAME);
			setState(1649);
			match(NEWLINE);
			setState(1650);
			stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 60:
			return literal_pattern_sempred((Literal_patternContext)_localctx, predIndex);
		case 61:
			return literal_expr_sempred((Literal_exprContext)_localctx, predIndex);
		case 68:
			return pattern_capture_target_sempred((Pattern_capture_targetContext)_localctx, predIndex);
		case 70:
			return value_pattern_sempred((Value_patternContext)_localctx, predIndex);
		case 97:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean literal_pattern_sempred(Literal_patternContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return  self.CannotBePlusMinus() ;
		}
		return true;
	}
	private boolean literal_expr_sempred(Literal_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return  self.CannotBePlusMinus() ;
		}
		return true;
	}
	private boolean pattern_capture_target_sempred(Pattern_capture_targetContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return  self.CannotBeDotLpEq() ;
		}
		return true;
	}
	private boolean value_pattern_sempred(Value_patternContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return  self.CannotBeDotLpEq() ;
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001k\u0675\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007E\u0002"+
		"F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007J\u0002"+
		"K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007O\u0002"+
		"P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007T\u0002"+
		"U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007X\u0002Y\u0007Y\u0002"+
		"Z\u0007Z\u0002[\u0007[\u0002\\\u0007\\\u0002]\u0007]\u0002^\u0007^\u0002"+
		"_\u0007_\u0002`\u0007`\u0002a\u0007a\u0002b\u0007b\u0002c\u0007c\u0002"+
		"d\u0007d\u0002e\u0007e\u0002f\u0007f\u0002g\u0007g\u0002h\u0007h\u0002"+
		"i\u0007i\u0002j\u0007j\u0002k\u0007k\u0002l\u0007l\u0002m\u0007m\u0002"+
		"n\u0007n\u0002o\u0007o\u0002p\u0007p\u0002q\u0007q\u0002r\u0007r\u0002"+
		"s\u0007s\u0002t\u0007t\u0002u\u0007u\u0002v\u0007v\u0002w\u0007w\u0002"+
		"x\u0007x\u0002y\u0007y\u0002z\u0007z\u0002{\u0007{\u0002|\u0007|\u0002"+
		"}\u0007}\u0002~\u0007~\u0002\u007f\u0007\u007f\u0002\u0080\u0007\u0080"+
		"\u0002\u0081\u0007\u0081\u0002\u0082\u0007\u0082\u0002\u0083\u0007\u0083"+
		"\u0002\u0084\u0007\u0084\u0002\u0085\u0007\u0085\u0002\u0086\u0007\u0086"+
		"\u0002\u0087\u0007\u0087\u0002\u0088\u0007\u0088\u0002\u0089\u0007\u0089"+
		"\u0002\u008a\u0007\u008a\u0002\u008b\u0007\u008b\u0002\u008c\u0007\u008c"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"\u0120\b\u0000\u0001\u0001\u0001\u0001\u0005\u0001\u0124\b\u0001\n\u0001"+
		"\f\u0001\u0127\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0005\u0002\u012d\b\u0002\n\u0002\f\u0002\u0130\t\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0004\u0003\u0135\b\u0003\u000b\u0003\f\u0003\u0136"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004\u013f\b\u0004\u0001\u0004\u0003\u0004\u0142\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0004\u0005\u0147\b\u0005\u000b\u0005\f"+
		"\u0005\u0148\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u014f\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\b\u0158\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u015d\b\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0166"+
		"\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u016c\b\t\u0005\t\u016e\b"+
		"\t\n\t\f\t\u0171\t\t\u0001\t\u0001\t\u0001\t\u0003\t\u0176\b\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0003\t\u017c\b\t\u0005\t\u017e\b\t\n\t\f\t\u0181"+
		"\t\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0187\b\t\u0003\t\u0189\b"+
		"\t\u0003\t\u018b\b\t\u0001\t\u0001\t\u0001\t\u0003\t\u0190\b\t\u0003\t"+
		"\u0192\b\t\u0003\t\u0194\b\t\u0001\t\u0001\t\u0003\t\u0198\b\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0003\t\u019e\b\t\u0005\t\u01a0\b\t\n\t\f\t\u01a3"+
		"\t\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u01a9\b\t\u0003\t\u01ab\b"+
		"\t\u0003\t\u01ad\b\t\u0001\t\u0001\t\u0001\t\u0003\t\u01b2\b\t\u0003\t"+
		"\u01b4\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0003\n\u01bb\b\n\u0001"+
		"\n\u0001\n\u0001\n\u0003\n\u01c0\b\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u01c5\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u01cb\b\u000b\u0005\u000b\u01cd\b\u000b\n\u000b\f\u000b\u01d0"+
		"\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01d5\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01db\b\u000b"+
		"\u0005\u000b\u01dd\b\u000b\n\u000b\f\u000b\u01e0\t\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01e6\b\u000b\u0003\u000b\u01e8"+
		"\b\u000b\u0003\u000b\u01ea\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u01ef\b\u000b\u0003\u000b\u01f1\b\u000b\u0003\u000b\u01f3"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01f7\b\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01fd\b\u000b\u0005\u000b"+
		"\u01ff\b\u000b\n\u000b\f\u000b\u0202\t\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u0208\b\u000b\u0003\u000b\u020a\b\u000b"+
		"\u0003\u000b\u020c\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u0211\b\u000b\u0003\u000b\u0213\b\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u0219\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u0223\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005"+
		"\u000e\u0228\b\u000e\n\u000e\f\u000e\u022b\t\u000e\u0001\u000e\u0003\u000e"+
		"\u022e\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u023b\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0003\u0010\u0242\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u0247\b\u0010\u0005\u0010\u0249\b\u0010\n\u0010\f\u0010\u024c"+
		"\t\u0010\u0003\u0010\u024e\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u0254\b\u0011\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u0258\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u025d\b"+
		"\u0012\u0005\u0012\u025f\b\u0012\n\u0012\f\u0012\u0262\t\u0012\u0001\u0012"+
		"\u0003\u0012\u0265\b\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u0273\b\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0003\u0019\u027b\b\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0003\u001b\u0283\b\u001b\u0003\u001b\u0285\b\u001b\u0001\u001c\u0001"+
		"\u001c\u0003\u001c\u0289\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001e\u0001\u001e\u0005\u001e\u0290\b\u001e\n\u001e\f\u001e\u0293\t\u001e"+
		"\u0001\u001e\u0001\u001e\u0004\u001e\u0297\b\u001e\u000b\u001e\f\u001e"+
		"\u0298\u0003\u001e\u029b\b\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u02a4\b\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u02a9\b\u001f\u0001 \u0001"+
		" \u0001 \u0003 \u02ae\b \u0001!\u0001!\u0001!\u0005!\u02b3\b!\n!\f!\u02b6"+
		"\t!\u0001!\u0003!\u02b9\b!\u0001\"\u0001\"\u0001\"\u0005\"\u02be\b\"\n"+
		"\"\f\"\u02c1\t\"\u0001#\u0001#\u0001#\u0005#\u02c6\b#\n#\f#\u02c9\t#\u0001"+
		"$\u0001$\u0001$\u0001$\u0005$\u02cf\b$\n$\f$\u02d2\t$\u0001%\u0001%\u0001"+
		"%\u0001%\u0005%\u02d8\b%\n%\f%\u02db\t%\u0001&\u0001&\u0001&\u0001&\u0003"+
		"&\u02e1\b&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0003\'\u02ee\b\'\u0001(\u0001(\u0001(\u0001"+
		"(\u0003(\u02f4\b(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0005)\u02ff\b)\n)\f)\u0302\t)\u0001)\u0001)\u0001)\u0003)\u0307"+
		"\b)\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0003*\u0310\b*\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u031b"+
		"\b+\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0004,\u0324\b,\u000b"+
		",\f,\u0325\u0001,\u0001,\u0001,\u0003,\u032b\b,\u0001,\u0001,\u0001,\u0003"+
		",\u0330\b,\u0001,\u0001,\u0001,\u0003,\u0335\b,\u0001-\u0001-\u0001-\u0001"+
		"-\u0005-\u033b\b-\n-\f-\u033e\t-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001"+
		".\u0003.\u0346\b.\u0001/\u0001/\u0001/\u0001/\u0003/\u034c\b/\u0003/\u034e"+
		"\b/\u00010\u00010\u00010\u00010\u00040\u0354\b0\u000b0\f0\u0355\u0001"+
		"0\u00010\u00030\u035a\b0\u00011\u00011\u00011\u00011\u00011\u00011\u0004"+
		"1\u0362\b1\u000b1\f1\u0363\u00011\u00011\u00012\u00012\u00012\u00032\u036b"+
		"\b2\u00012\u00032\u036e\b2\u00013\u00013\u00043\u0372\b3\u000b3\f3\u0373"+
		"\u00013\u00033\u0377\b3\u00014\u00014\u00014\u00034\u037c\b4\u00015\u0001"+
		"5\u00015\u00035\u0381\b5\u00015\u00015\u00015\u00016\u00016\u00016\u0001"+
		"7\u00017\u00037\u038b\b7\u00018\u00018\u00038\u038f\b8\u00019\u00019\u0001"+
		"9\u00019\u0001:\u0001:\u0001:\u0005:\u0398\b:\n:\f:\u039b\t:\u0001;\u0001"+
		";\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0003;\u03a5\b;\u0001<\u0001"+
		"<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0003<\u03af\b<\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0003=\u03b9\b=\u0001>\u0001"+
		">\u0001>\u0001>\u0001>\u0001>\u0001>\u0001>\u0003>\u03c3\b>\u0001?\u0001"+
		"?\u0001?\u0003?\u03c8\b?\u0001@\u0001@\u0001@\u0003@\u03cd\b@\u0001A\u0001"+
		"A\u0001B\u0001B\u0001C\u0001C\u0001D\u0001D\u0001D\u0001E\u0001E\u0001"+
		"F\u0001F\u0001F\u0001G\u0001G\u0001G\u0004G\u03e0\bG\u000bG\fG\u03e1\u0001"+
		"H\u0001H\u0003H\u03e6\bH\u0001I\u0001I\u0001I\u0001I\u0001J\u0001J\u0003"+
		"J\u03ee\bJ\u0001J\u0001J\u0001J\u0003J\u03f3\bJ\u0001J\u0003J\u03f6\b"+
		"J\u0001K\u0001K\u0001K\u0003K\u03fb\bK\u0001L\u0001L\u0001L\u0005L\u0400"+
		"\bL\nL\fL\u0403\tL\u0001L\u0003L\u0406\bL\u0001M\u0001M\u0003M\u040a\b"+
		"M\u0001N\u0001N\u0001N\u0001N\u0003N\u0410\bN\u0001O\u0001O\u0001O\u0001"+
		"O\u0001O\u0003O\u0417\bO\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0001"+
		"O\u0003O\u0420\bO\u0001O\u0001O\u0001O\u0001O\u0001O\u0003O\u0427\bO\u0001"+
		"O\u0001O\u0003O\u042b\bO\u0001P\u0001P\u0001P\u0005P\u0430\bP\nP\fP\u0433"+
		"\tP\u0001Q\u0001Q\u0003Q\u0437\bQ\u0001Q\u0001Q\u0001Q\u0001R\u0001R\u0001"+
		"R\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0003S\u0447"+
		"\bS\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0003S\u044f\bS\u0001S\u0001"+
		"S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0003S\u0459\bS\u0001S\u0001"+
		"S\u0003S\u045d\bS\u0001T\u0001T\u0001T\u0005T\u0462\bT\nT\fT\u0465\tT"+
		"\u0001U\u0001U\u0001U\u0005U\u046a\bU\nU\fU\u046d\tU\u0001V\u0001V\u0001"+
		"V\u0001V\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0003W\u0479\bW\u0001"+
		"W\u0003W\u047c\bW\u0001X\u0001X\u0003X\u0480\bX\u0001Y\u0001Y\u0003Y\u0484"+
		"\bY\u0001Y\u0001Y\u0001Y\u0001Z\u0001Z\u0003Z\u048b\bZ\u0001Z\u0001Z\u0001"+
		"Z\u0001[\u0001[\u0001[\u0005[\u0493\b[\n[\f[\u0496\t[\u0001\\\u0001\\"+
		"\u0001\\\u0005\\\u049b\b\\\n\\\f\\\u049e\t\\\u0001]\u0001]\u0001]\u0003"+
		"]\u04a3\b]\u0001^\u0001^\u0001^\u0001^\u0005^\u04a9\b^\n^\f^\u04ac\t^"+
		"\u0001_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001"+
		"_\u0001_\u0001_\u0001_\u0003_\u04bb\b_\u0001`\u0001`\u0001`\u0001a\u0001"+
		"a\u0001a\u0001a\u0001a\u0004a\u04c5\ba\u000ba\fa\u04c6\u0001a\u0003a\u04ca"+
		"\ba\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001"+
		"a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001"+
		"a\u0001a\u0005a\u04e1\ba\na\fa\u04e4\ta\u0001b\u0003b\u04e7\bb\u0001b"+
		"\u0001b\u0005b\u04eb\bb\nb\fb\u04ee\tb\u0001c\u0001c\u0001c\u0003c\u04f3"+
		"\bc\u0001c\u0001c\u0001c\u0003c\u04f8\bc\u0001c\u0001c\u0001c\u0003c\u04fd"+
		"\bc\u0001c\u0001c\u0001c\u0001c\u0001c\u0004c\u0504\bc\u000bc\fc\u0505"+
		"\u0001c\u0001c\u0001c\u0001c\u0003c\u050c\bc\u0001d\u0001d\u0001e\u0001"+
		"e\u0001e\u0003e\u0513\be\u0001e\u0001e\u0001e\u0001e\u0001e\u0003e\u051a"+
		"\be\u0005e\u051c\be\ne\fe\u051f\te\u0001e\u0003e\u0522\be\u0003e\u0524"+
		"\be\u0001f\u0001f\u0003f\u0528\bf\u0001f\u0001f\u0001f\u0001f\u0001f\u0001"+
		"f\u0001f\u0001f\u0003f\u0532\bf\u0003f\u0534\bf\u0001g\u0001g\u0001g\u0005"+
		"g\u0539\bg\ng\fg\u053c\tg\u0001g\u0003g\u053f\bg\u0001h\u0001h\u0003h"+
		"\u0543\bh\u0001h\u0001h\u0003h\u0547\bh\u0001h\u0003h\u054a\bh\u0003h"+
		"\u054c\bh\u0001i\u0001i\u0003i\u0550\bi\u0001j\u0001j\u0003j\u0554\bj"+
		"\u0001j\u0001j\u0001j\u0003j\u0559\bj\u0005j\u055b\bj\nj\fj\u055e\tj\u0001"+
		"j\u0003j\u0561\bj\u0001k\u0001k\u0001k\u0005k\u0566\bk\nk\fk\u0569\tk"+
		"\u0001k\u0003k\u056c\bk\u0001l\u0001l\u0001l\u0001l\u0001l\u0001l\u0003"+
		"l\u0574\bl\u0001l\u0001l\u0001l\u0001l\u0001l\u0001l\u0001l\u0001l\u0003"+
		"l\u057e\bl\u0005l\u0580\bl\nl\fl\u0583\tl\u0001l\u0003l\u0586\bl\u0003"+
		"l\u0588\bl\u0001l\u0001l\u0003l\u058c\bl\u0001l\u0001l\u0001l\u0001l\u0003"+
		"l\u0592\bl\u0005l\u0594\bl\nl\fl\u0597\tl\u0001l\u0003l\u059a\bl\u0003"+
		"l\u059c\bl\u0003l\u059e\bl\u0001m\u0001m\u0001m\u0001m\u0003m\u05a4\b"+
		"m\u0001m\u0001m\u0003m\u05a8\bm\u0001m\u0003m\u05ab\bm\u0001m\u0001m\u0001"+
		"m\u0001n\u0001n\u0001n\u0003n\u05b3\bn\u0001n\u0001n\u0001n\u0001n\u0003"+
		"n\u05b9\bn\u0005n\u05bb\bn\nn\fn\u05be\tn\u0001n\u0003n\u05c1\bn\u0001"+
		"o\u0001o\u0001o\u0003o\u05c6\bo\u0001o\u0001o\u0001o\u0001o\u0001o\u0001"+
		"o\u0001o\u0001o\u0003o\u05d0\bo\u0001p\u0001p\u0003p\u05d4\bp\u0001q\u0003"+
		"q\u05d7\bq\u0001q\u0001q\u0001q\u0001q\u0001q\u0003q\u05de\bq\u0001r\u0001"+
		"r\u0001r\u0003r\u05e3\br\u0001s\u0001s\u0001t\u0001t\u0003t\u05e9\bt\u0001"+
		"u\u0001u\u0001u\u0003u\u05ee\bu\u0001v\u0004v\u05f1\bv\u000bv\fv\u05f2"+
		"\u0001w\u0001w\u0001w\u0001w\u0001w\u0003w\u05fa\bw\u0001w\u0001w\u0001"+
		"x\u0001x\u0001x\u0001x\u0001x\u0003x\u0603\bx\u0001y\u0001y\u0001y\u0001"+
		"y\u0003y\u0609\by\u0001z\u0001z\u0003z\u060d\bz\u0001{\u0001{\u0001|\u0001"+
		"|\u0001|\u0001}\u0001}\u0001}\u0001~\u0001~\u0001~\u0001\u007f\u0001\u007f"+
		"\u0001\u007f\u0001\u007f\u0003\u007f\u061e\b\u007f\u0001\u007f\u0001\u007f"+
		"\u0001\u0080\u0001\u0080\u0003\u0080\u0624\b\u0080\u0001\u0081\u0001\u0081"+
		"\u0001\u0081\u0003\u0081\u0629\b\u0081\u0001\u0081\u0001\u0081\u0001\u0082"+
		"\u0001\u0082\u0001\u0082\u0001\u0082\u0001\u0082\u0003\u0082\u0632\b\u0082"+
		"\u0001\u0082\u0001\u0082\u0001\u0083\u0001\u0083\u0001\u0083\u0001\u0084"+
		"\u0001\u0084\u0001\u0084\u0001\u0084\u0001\u0084\u0003\u0084\u063e\b\u0084"+
		"\u0001\u0084\u0001\u0084\u0001\u0084\u0003\u0084\u0643\b\u0084\u0001\u0085"+
		"\u0001\u0085\u0004\u0085\u0647\b\u0085\u000b\u0085\f\u0085\u0648\u0001"+
		"\u0086\u0001\u0086\u0001\u0087\u0001\u0087\u0001\u0087\u0001\u0087\u0003"+
		"\u0087\u0651\b\u0087\u0001\u0088\u0001\u0088\u0001\u0088\u0005\u0088\u0656"+
		"\b\u0088\n\u0088\f\u0088\u0659\t\u0088\u0001\u0089\u0001\u0089\u0003\u0089"+
		"\u065d\b\u0089\u0001\u0089\u0001\u0089\u0003\u0089\u0661\b\u0089\u0001"+
		"\u008a\u0001\u008a\u0001\u008a\u0001\u008a\u0001\u008a\u0003\u008a\u0668"+
		"\b\u008a\u0001\u008a\u0001\u008a\u0001\u008b\u0001\u008b\u0001\u008b\u0001"+
		"\u008b\u0001\u008c\u0001\u008c\u0001\u008c\u0001\u008c\u0001\u008c\u0001"+
		"\u008c\u0000\u0001\u00c2\u008d\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR"+
		"TVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e"+
		"\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6"+
		"\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be"+
		"\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6"+
		"\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec\u00ee"+
		"\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102\u0104\u0106"+
		"\u0108\u010a\u010c\u010e\u0110\u0112\u0114\u0116\u0118\u0000\b\u0001\u0000"+
		"Zf\u0001\u000089\u0002\u0000IJNN\u0003\u0000::KMXX\u0001\u0000IJ\u0001"+
		"\u0000GH\u0003\u0000\u001e\u001e((//\u0002\u0000DDFF\u0726\u0000\u011f"+
		"\u0001\u0000\u0000\u0000\u0002\u0125\u0001\u0000\u0000\u0000\u0004\u012a"+
		"\u0001\u0000\u0000\u0000\u0006\u0134\u0001\u0000\u0000\u0000\b\u013a\u0001"+
		"\u0000\u0000\u0000\n\u0146\u0001\u0000\u0000\u0000\f\u014a\u0001\u0000"+
		"\u0000\u0000\u000e\u0150\u0001\u0000\u0000\u0000\u0010\u0153\u0001\u0000"+
		"\u0000\u0000\u0012\u0161\u0001\u0000\u0000\u0000\u0014\u01bf\u0001\u0000"+
		"\u0000\u0000\u0016\u0212\u0001\u0000\u0000\u0000\u0018\u0218\u0001\u0000"+
		"\u0000\u0000\u001a\u0222\u0001\u0000\u0000\u0000\u001c\u0224\u0001\u0000"+
		"\u0000\u0000\u001e\u023a\u0001\u0000\u0000\u0000 \u023c\u0001\u0000\u0000"+
		"\u0000\"\u024f\u0001\u0000\u0000\u0000$\u0257\u0001\u0000\u0000\u0000"+
		"&\u0266\u0001\u0000\u0000\u0000(\u0268\u0001\u0000\u0000\u0000*\u026b"+
		"\u0001\u0000\u0000\u0000,\u0272\u0001\u0000\u0000\u0000.\u0274\u0001\u0000"+
		"\u0000\u00000\u0276\u0001\u0000\u0000\u00002\u0278\u0001\u0000\u0000\u0000"+
		"4\u027c\u0001\u0000\u0000\u00006\u027e\u0001\u0000\u0000\u00008\u0288"+
		"\u0001\u0000\u0000\u0000:\u028a\u0001\u0000\u0000\u0000<\u028d\u0001\u0000"+
		"\u0000\u0000>\u02a5\u0001\u0000\u0000\u0000@\u02aa\u0001\u0000\u0000\u0000"+
		"B\u02af\u0001\u0000\u0000\u0000D\u02ba\u0001\u0000\u0000\u0000F\u02c2"+
		"\u0001\u0000\u0000\u0000H\u02ca\u0001\u0000\u0000\u0000J\u02d3\u0001\u0000"+
		"\u0000\u0000L\u02dc\u0001\u0000\u0000\u0000N\u02ed\u0001\u0000\u0000\u0000"+
		"P\u02ef\u0001\u0000\u0000\u0000R\u02f5\u0001\u0000\u0000\u0000T\u0308"+
		"\u0001\u0000\u0000\u0000V\u0311\u0001\u0000\u0000\u0000X\u031c\u0001\u0000"+
		"\u0000\u0000Z\u0336\u0001\u0000\u0000\u0000\\\u0342\u0001\u0000\u0000"+
		"\u0000^\u0347\u0001\u0000\u0000\u0000`\u0359\u0001\u0000\u0000\u0000b"+
		"\u035b\u0001\u0000\u0000\u0000d\u036d\u0001\u0000\u0000\u0000f\u036f\u0001"+
		"\u0000\u0000\u0000h\u037b\u0001\u0000\u0000\u0000j\u037d\u0001\u0000\u0000"+
		"\u0000l\u0385\u0001\u0000\u0000\u0000n\u038a\u0001\u0000\u0000\u0000p"+
		"\u038e\u0001\u0000\u0000\u0000r\u0390\u0001\u0000\u0000\u0000t\u0394\u0001"+
		"\u0000\u0000\u0000v\u03a4\u0001\u0000\u0000\u0000x\u03ae\u0001\u0000\u0000"+
		"\u0000z\u03b8\u0001\u0000\u0000\u0000|\u03c2\u0001\u0000\u0000\u0000~"+
		"\u03c7\u0001\u0000\u0000\u0000\u0080\u03cc\u0001\u0000\u0000\u0000\u0082"+
		"\u03ce\u0001\u0000\u0000\u0000\u0084\u03d0\u0001\u0000\u0000\u0000\u0086"+
		"\u03d2\u0001\u0000\u0000\u0000\u0088\u03d4\u0001\u0000\u0000\u0000\u008a"+
		"\u03d7\u0001\u0000\u0000\u0000\u008c\u03d9\u0001\u0000\u0000\u0000\u008e"+
		"\u03dc\u0001\u0000\u0000\u0000\u0090\u03e5\u0001\u0000\u0000\u0000\u0092"+
		"\u03e7\u0001\u0000\u0000\u0000\u0094\u03f5\u0001\u0000\u0000\u0000\u0096"+
		"\u03f7\u0001\u0000\u0000\u0000\u0098\u03fc\u0001\u0000\u0000\u0000\u009a"+
		"\u0409\u0001\u0000\u0000\u0000\u009c\u040f\u0001\u0000\u0000\u0000\u009e"+
		"\u042a\u0001\u0000\u0000\u0000\u00a0\u042c\u0001\u0000\u0000\u0000\u00a2"+
		"\u0436\u0001\u0000\u0000\u0000\u00a4\u043b\u0001\u0000\u0000\u0000\u00a6"+
		"\u045c\u0001\u0000\u0000\u0000\u00a8\u045e\u0001\u0000\u0000\u0000\u00aa"+
		"\u0466\u0001\u0000\u0000\u0000\u00ac\u046e\u0001\u0000\u0000\u0000\u00ae"+
		"\u047b\u0001\u0000\u0000\u0000\u00b0\u047f\u0001\u0000\u0000\u0000\u00b2"+
		"\u0481\u0001\u0000\u0000\u0000\u00b4\u0488\u0001\u0000\u0000\u0000\u00b6"+
		"\u048f\u0001\u0000\u0000\u0000\u00b8\u0497\u0001\u0000\u0000\u0000\u00ba"+
		"\u04a2\u0001\u0000\u0000\u0000\u00bc\u04a4\u0001\u0000\u0000\u0000\u00be"+
		"\u04ba\u0001\u0000\u0000\u0000\u00c0\u04bc\u0001\u0000\u0000\u0000\u00c2"+
		"\u04c9\u0001\u0000\u0000\u0000\u00c4\u04e6\u0001\u0000\u0000\u0000\u00c6"+
		"\u050b\u0001\u0000\u0000\u0000\u00c8\u050d\u0001\u0000\u0000\u0000\u00ca"+
		"\u0512\u0001\u0000\u0000\u0000\u00cc\u0533\u0001\u0000\u0000\u0000\u00ce"+
		"\u0535\u0001\u0000\u0000\u0000\u00d0\u054b\u0001\u0000\u0000\u0000\u00d2"+
		"\u054d\u0001\u0000\u0000\u0000\u00d4\u0553\u0001\u0000\u0000\u0000\u00d6"+
		"\u0562\u0001\u0000\u0000\u0000\u00d8\u059d\u0001\u0000\u0000\u0000\u00da"+
		"\u059f\u0001\u0000\u0000\u0000\u00dc\u05b2\u0001\u0000\u0000\u0000\u00de"+
		"\u05cf\u0001\u0000\u0000\u0000\u00e0\u05d3\u0001\u0000\u0000\u0000\u00e2"+
		"\u05d6\u0001\u0000\u0000\u0000\u00e4\u05df\u0001\u0000\u0000\u0000\u00e6"+
		"\u05e4\u0001\u0000\u0000\u0000\u00e8\u05e6\u0001\u0000\u0000\u0000\u00ea"+
		"\u05ed\u0001\u0000\u0000\u0000\u00ec\u05f0\u0001\u0000\u0000\u0000\u00ee"+
		"\u05f4\u0001\u0000\u0000\u0000\u00f0\u0602\u0001\u0000\u0000\u0000\u00f2"+
		"\u0608\u0001\u0000\u0000\u0000\u00f4\u060c\u0001\u0000\u0000\u0000\u00f6"+
		"\u060e\u0001\u0000\u0000\u0000\u00f8\u0610\u0001\u0000\u0000\u0000\u00fa"+
		"\u0613\u0001\u0000\u0000\u0000\u00fc\u0616\u0001\u0000\u0000\u0000\u00fe"+
		"\u0619\u0001\u0000\u0000\u0000\u0100\u0623\u0001\u0000\u0000\u0000\u0102"+
		"\u0625\u0001\u0000\u0000\u0000\u0104\u062c\u0001\u0000\u0000\u0000\u0106"+
		"\u0635\u0001\u0000\u0000\u0000\u0108\u0638\u0001\u0000\u0000\u0000\u010a"+
		"\u0644\u0001\u0000\u0000\u0000\u010c\u064a\u0001\u0000\u0000\u0000\u010e"+
		"\u0650\u0001\u0000\u0000\u0000\u0110\u0652\u0001\u0000\u0000\u0000\u0112"+
		"\u065c\u0001\u0000\u0000\u0000\u0114\u0662\u0001\u0000\u0000\u0000\u0116"+
		"\u066b\u0001\u0000\u0000\u0000\u0118\u066f\u0001\u0000\u0000\u0000\u011a"+
		"\u0120\u0005.\u0000\u0000\u011b\u0120\u0003\u001c\u000e\u0000\u011c\u011d"+
		"\u0003N\'\u0000\u011d\u011e\u0005.\u0000\u0000\u011e\u0120\u0001\u0000"+
		"\u0000\u0000\u011f\u011a\u0001\u0000\u0000\u0000\u011f\u011b\u0001\u0000"+
		"\u0000\u0000\u011f\u011c\u0001\u0000\u0000\u0000\u0120\u0001\u0001\u0000"+
		"\u0000\u0000\u0121\u0124\u0005.\u0000\u0000\u0122\u0124\u0003\u001a\r"+
		"\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0122\u0001\u0000\u0000"+
		"\u0000\u0124\u0127\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000\u0000"+
		"\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0128\u0001\u0000\u0000"+
		"\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0128\u0129\u0005\u0000\u0000"+
		"\u0001\u0129\u0003\u0001\u0000\u0000\u0000\u012a\u012e\u0003\u00d6k\u0000"+
		"\u012b\u012d\u0005.\u0000\u0000\u012c\u012b\u0001\u0000\u0000\u0000\u012d"+
		"\u0130\u0001\u0000\u0000\u0000\u012e\u012c\u0001\u0000\u0000\u0000\u012e"+
		"\u012f\u0001\u0000\u0000\u0000\u012f\u0131\u0001\u0000\u0000\u0000\u0130"+
		"\u012e\u0001\u0000\u0000\u0000\u0131\u0132\u0005\u0000\u0000\u0001\u0132"+
		"\u0005\u0001\u0000\u0000\u0000\u0133\u0135\u0003\u010a\u0085\u0000\u0134"+
		"\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136"+
		"\u0134\u0001\u0000\u0000\u0000\u0136\u0137\u0001\u0000\u0000\u0000\u0137"+
		"\u0138\u0001\u0000\u0000\u0000\u0138\u0139\u0005\u0000\u0000\u0001\u0139"+
		"\u0007\u0001\u0000\u0000\u0000\u013a\u013b\u0005X\u0000\u0000\u013b\u0141"+
		"\u0003F#\u0000\u013c\u013e\u0005;\u0000\u0000\u013d\u013f\u0003\u00dc"+
		"n\u0000\u013e\u013d\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000"+
		"\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u0140\u0142\u0005<\u0000\u0000"+
		"\u0141\u013c\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000"+
		"\u0142\u0143\u0001\u0000\u0000\u0000\u0143\u0144\u0005.\u0000\u0000\u0144"+
		"\t\u0001\u0000\u0000\u0000\u0145\u0147\u0003\b\u0004\u0000\u0146\u0145"+
		"\u0001\u0000\u0000\u0000\u0147\u0148\u0001\u0000\u0000\u0000\u0148\u0146"+
		"\u0001\u0000\u0000\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149\u000b"+
		"\u0001\u0000\u0000\u0000\u014a\u014e\u0003\n\u0005\u0000\u014b\u014f\u0003"+
		"\u00dam\u0000\u014c\u014f\u0003\u0010\b\u0000\u014d\u014f\u0003\u000e"+
		"\u0007\u0000\u014e\u014b\u0001\u0000\u0000\u0000\u014e\u014c\u0001\u0000"+
		"\u0000\u0000\u014e\u014d\u0001\u0000\u0000\u0000\u014f\r\u0001\u0000\u0000"+
		"\u0000\u0150\u0151\u0005\t\u0000\u0000\u0151\u0152\u0003\u0010\b\u0000"+
		"\u0152\u000f\u0001\u0000\u0000\u0000\u0153\u0157\u0005\u000f\u0000\u0000"+
		"\u0154\u0158\u0003\u00c8d\u0000\u0155\u0158\u0003\u00f6{\u0000\u0156\u0158"+
		"\u0003\u00fc~\u0000\u0157\u0154\u0001\u0000\u0000\u0000\u0157\u0155\u0001"+
		"\u0000\u0000\u0000\u0157\u0156\u0001\u0000\u0000\u0000\u0158\u0159\u0001"+
		"\u0000\u0000\u0000\u0159\u015c\u0003\u0012\t\u0000\u015a\u015b\u0005Y"+
		"\u0000\u0000\u015b\u015d\u0003\u00aeW\u0000\u015c\u015a\u0001\u0000\u0000"+
		"\u0000\u015c\u015d\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000"+
		"\u0000\u015e\u015f\u0005>\u0000\u0000\u015f\u0160\u0003`0\u0000\u0160"+
		"\u0011\u0001\u0000\u0000\u0000\u0161\u01b3\u0005;\u0000\u0000\u0162\u0165"+
		"\u0003\u0014\n\u0000\u0163\u0164\u0005A\u0000\u0000\u0164\u0166\u0003"+
		"\u00aeW\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000"+
		"\u0000\u0000\u0166\u016f\u0001\u0000\u0000\u0000\u0167\u0168\u0005=\u0000"+
		"\u0000\u0168\u016b\u0003\u0014\n\u0000\u0169\u016a\u0005A\u0000\u0000"+
		"\u016a\u016c\u0003\u00aeW\u0000\u016b\u0169\u0001\u0000\u0000\u0000\u016b"+
		"\u016c\u0001\u0000\u0000\u0000\u016c\u016e\u0001\u0000\u0000\u0000\u016d"+
		"\u0167\u0001\u0000\u0000\u0000\u016e\u0171\u0001\u0000\u0000\u0000\u016f"+
		"\u016d\u0001\u0000\u0000\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170"+
		"\u0193\u0001\u0000\u0000\u0000\u0171\u016f\u0001\u0000\u0000\u0000\u0172"+
		"\u0191\u0005=\u0000\u0000\u0173\u0175\u0005:\u0000\u0000\u0174\u0176\u0003"+
		"\u0014\n\u0000\u0175\u0174\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000"+
		"\u0000\u0000\u0176\u017f\u0001\u0000\u0000\u0000\u0177\u0178\u0005=\u0000"+
		"\u0000\u0178\u017b\u0003\u0014\n\u0000\u0179\u017a\u0005A\u0000\u0000"+
		"\u017a\u017c\u0003\u00aeW\u0000\u017b\u0179\u0001\u0000\u0000\u0000\u017b"+
		"\u017c\u0001\u0000\u0000\u0000\u017c\u017e\u0001\u0000\u0000\u0000\u017d"+
		"\u0177\u0001\u0000\u0000\u0000\u017e\u0181\u0001\u0000\u0000\u0000\u017f"+
		"\u017d\u0001\u0000\u0000\u0000\u017f\u0180\u0001\u0000\u0000\u0000\u0180"+
		"\u018a\u0001\u0000\u0000\u0000\u0181\u017f\u0001\u0000\u0000\u0000\u0182"+
		"\u0188\u0005=\u0000\u0000\u0183\u0184\u0005@\u0000\u0000\u0184\u0186\u0003"+
		"\u0014\n\u0000\u0185\u0187\u0005=\u0000\u0000\u0186\u0185\u0001\u0000"+
		"\u0000\u0000\u0186\u0187\u0001\u0000\u0000\u0000\u0187\u0189\u0001\u0000"+
		"\u0000\u0000\u0188\u0183\u0001\u0000\u0000\u0000\u0188\u0189\u0001\u0000"+
		"\u0000\u0000\u0189\u018b\u0001\u0000\u0000\u0000\u018a\u0182\u0001\u0000"+
		"\u0000\u0000\u018a\u018b\u0001\u0000\u0000\u0000\u018b\u0192\u0001\u0000"+
		"\u0000\u0000\u018c\u018d\u0005@\u0000\u0000\u018d\u018f\u0003\u0014\n"+
		"\u0000\u018e\u0190\u0005=\u0000\u0000\u018f\u018e\u0001\u0000\u0000\u0000"+
		"\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u0192\u0001\u0000\u0000\u0000"+
		"\u0191\u0173\u0001\u0000\u0000\u0000\u0191\u018c\u0001\u0000\u0000\u0000"+
		"\u0191\u0192\u0001\u0000\u0000\u0000\u0192\u0194\u0001\u0000\u0000\u0000"+
		"\u0193\u0172\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000\u0000\u0000"+
		"\u0194\u01b4\u0001\u0000\u0000\u0000\u0195\u0197\u0005:\u0000\u0000\u0196"+
		"\u0198\u0003\u0014\n\u0000\u0197\u0196\u0001\u0000\u0000\u0000\u0197\u0198"+
		"\u0001\u0000\u0000\u0000\u0198\u01a1\u0001\u0000\u0000\u0000\u0199\u019a"+
		"\u0005=\u0000\u0000\u019a\u019d\u0003\u0014\n\u0000\u019b\u019c\u0005"+
		"A\u0000\u0000\u019c\u019e\u0003\u00aeW\u0000\u019d\u019b\u0001\u0000\u0000"+
		"\u0000\u019d\u019e\u0001\u0000\u0000\u0000\u019e\u01a0\u0001\u0000\u0000"+
		"\u0000\u019f\u0199\u0001\u0000\u0000\u0000\u01a0\u01a3\u0001\u0000\u0000"+
		"\u0000\u01a1\u019f\u0001\u0000\u0000\u0000\u01a1\u01a2\u0001\u0000\u0000"+
		"\u0000\u01a2\u01ac\u0001\u0000\u0000\u0000\u01a3\u01a1\u0001\u0000\u0000"+
		"\u0000\u01a4\u01aa\u0005=\u0000\u0000\u01a5\u01a6\u0005@\u0000\u0000\u01a6"+
		"\u01a8\u0003\u0014\n\u0000\u01a7\u01a9\u0005=\u0000\u0000\u01a8\u01a7"+
		"\u0001\u0000\u0000\u0000\u01a8\u01a9\u0001\u0000\u0000\u0000\u01a9\u01ab"+
		"\u0001\u0000\u0000\u0000\u01aa\u01a5\u0001\u0000\u0000\u0000\u01aa\u01ab"+
		"\u0001\u0000\u0000\u0000\u01ab\u01ad\u0001\u0000\u0000\u0000\u01ac\u01a4"+
		"\u0001\u0000\u0000\u0000\u01ac\u01ad\u0001\u0000\u0000\u0000\u01ad\u01b4"+
		"\u0001\u0000\u0000\u0000\u01ae\u01af\u0005@\u0000\u0000\u01af\u01b1\u0003"+
		"\u0014\n\u0000\u01b0\u01b2\u0005=\u0000\u0000\u01b1\u01b0\u0001\u0000"+
		"\u0000\u0000\u01b1\u01b2\u0001\u0000\u0000\u0000\u01b2\u01b4\u0001\u0000"+
		"\u0000\u0000\u01b3\u0162\u0001\u0000\u0000\u0000\u01b3\u0195\u0001\u0000"+
		"\u0000\u0000\u01b3\u01ae\u0001\u0000\u0000\u0000\u01b3\u01b4\u0001\u0000"+
		"\u0000\u0000\u01b4\u01b5\u0001\u0000\u0000\u0000\u01b5\u01b6\u0005<\u0000"+
		"\u0000\u01b6\u0013\u0001\u0000\u0000\u0000\u01b7\u01ba\u0003\u00c8d\u0000"+
		"\u01b8\u01b9\u0005>\u0000\u0000\u01b9\u01bb\u0003\u00aeW\u0000\u01ba\u01b8"+
		"\u0001\u0000\u0000\u0000\u01ba\u01bb\u0001\u0000\u0000\u0000\u01bb\u01c0"+
		"\u0001\u0000\u0000\u0000\u01bc\u01c0\u0003\u00f2y\u0000\u01bd\u01c0\u0003"+
		"\u00f8|\u0000\u01be\u01c0\u0003\u0106\u0083\u0000\u01bf\u01b7\u0001\u0000"+
		"\u0000\u0000\u01bf\u01bc\u0001\u0000\u0000\u0000\u01bf\u01bd\u0001\u0000"+
		"\u0000\u0000\u01bf\u01be\u0001\u0000\u0000\u0000\u01c0\u0015\u0001\u0000"+
		"\u0000\u0000\u01c1\u01c4\u0003\u0018\f\u0000\u01c2\u01c3\u0005A\u0000"+
		"\u0000\u01c3\u01c5\u0003\u00aeW\u0000\u01c4\u01c2\u0001\u0000\u0000\u0000"+
		"\u01c4\u01c5\u0001\u0000\u0000\u0000\u01c5\u01ce\u0001\u0000\u0000\u0000"+
		"\u01c6\u01c7\u0005=\u0000\u0000\u01c7\u01ca\u0003\u0018\f\u0000\u01c8"+
		"\u01c9\u0005A\u0000\u0000\u01c9\u01cb\u0003\u00aeW\u0000\u01ca\u01c8\u0001"+
		"\u0000\u0000\u0000\u01ca\u01cb\u0001\u0000\u0000\u0000\u01cb\u01cd\u0001"+
		"\u0000\u0000\u0000\u01cc\u01c6\u0001\u0000\u0000\u0000\u01cd\u01d0\u0001"+
		"\u0000\u0000\u0000\u01ce\u01cc\u0001\u0000\u0000\u0000\u01ce\u01cf\u0001"+
		"\u0000\u0000\u0000\u01cf\u01f2\u0001\u0000\u0000\u0000\u01d0\u01ce\u0001"+
		"\u0000\u0000\u0000\u01d1\u01f0\u0005=\u0000\u0000\u01d2\u01d4\u0005:\u0000"+
		"\u0000\u01d3\u01d5\u0003\u0018\f\u0000\u01d4\u01d3\u0001\u0000\u0000\u0000"+
		"\u01d4\u01d5\u0001\u0000\u0000\u0000\u01d5\u01de\u0001\u0000\u0000\u0000"+
		"\u01d6\u01d7\u0005=\u0000\u0000\u01d7\u01da\u0003\u0018\f\u0000\u01d8"+
		"\u01d9\u0005A\u0000\u0000\u01d9\u01db\u0003\u00aeW\u0000\u01da\u01d8\u0001"+
		"\u0000\u0000\u0000\u01da\u01db\u0001\u0000\u0000\u0000\u01db\u01dd\u0001"+
		"\u0000\u0000\u0000\u01dc\u01d6\u0001\u0000\u0000\u0000\u01dd\u01e0\u0001"+
		"\u0000\u0000\u0000\u01de\u01dc\u0001\u0000\u0000\u0000\u01de\u01df\u0001"+
		"\u0000\u0000\u0000\u01df\u01e9\u0001\u0000\u0000\u0000\u01e0\u01de\u0001"+
		"\u0000\u0000\u0000\u01e1\u01e7\u0005=\u0000\u0000\u01e2\u01e3\u0005@\u0000"+
		"\u0000\u01e3\u01e5\u0003\u0018\f\u0000\u01e4\u01e6\u0005=\u0000\u0000"+
		"\u01e5\u01e4\u0001\u0000\u0000\u0000\u01e5\u01e6\u0001\u0000\u0000\u0000"+
		"\u01e6\u01e8\u0001\u0000\u0000\u0000\u01e7\u01e2\u0001\u0000\u0000\u0000"+
		"\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8\u01ea\u0001\u0000\u0000\u0000"+
		"\u01e9\u01e1\u0001\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000\u0000"+
		"\u01ea\u01f1\u0001\u0000\u0000\u0000\u01eb\u01ec\u0005@\u0000\u0000\u01ec"+
		"\u01ee\u0003\u0018\f\u0000\u01ed\u01ef\u0005=\u0000\u0000\u01ee\u01ed"+
		"\u0001\u0000\u0000\u0000\u01ee\u01ef\u0001\u0000\u0000\u0000\u01ef\u01f1"+
		"\u0001\u0000\u0000\u0000\u01f0\u01d2\u0001\u0000\u0000\u0000\u01f0\u01eb"+
		"\u0001\u0000\u0000\u0000\u01f0\u01f1\u0001\u0000\u0000\u0000\u01f1\u01f3"+
		"\u0001\u0000\u0000\u0000\u01f2\u01d1\u0001\u0000\u0000\u0000\u01f2\u01f3"+
		"\u0001\u0000\u0000\u0000\u01f3\u0213\u0001\u0000\u0000\u0000\u01f4\u01f6"+
		"\u0005:\u0000\u0000\u01f5\u01f7\u0003\u0018\f\u0000\u01f6\u01f5\u0001"+
		"\u0000\u0000\u0000\u01f6\u01f7\u0001\u0000\u0000\u0000\u01f7\u0200\u0001"+
		"\u0000\u0000\u0000\u01f8\u01f9\u0005=\u0000\u0000\u01f9\u01fc\u0003\u0018"+
		"\f\u0000\u01fa\u01fb\u0005A\u0000\u0000\u01fb\u01fd\u0003\u00aeW\u0000"+
		"\u01fc\u01fa\u0001\u0000\u0000\u0000\u01fc\u01fd\u0001\u0000\u0000\u0000"+
		"\u01fd\u01ff\u0001\u0000\u0000\u0000\u01fe\u01f8\u0001\u0000\u0000\u0000"+
		"\u01ff\u0202\u0001\u0000\u0000\u0000\u0200\u01fe\u0001\u0000\u0000\u0000"+
		"\u0200\u0201\u0001\u0000\u0000\u0000\u0201\u020b\u0001\u0000\u0000\u0000"+
		"\u0202\u0200\u0001\u0000\u0000\u0000\u0203\u0209\u0005=\u0000\u0000\u0204"+
		"\u0205\u0005@\u0000\u0000\u0205\u0207\u0003\u0018\f\u0000\u0206\u0208"+
		"\u0005=\u0000\u0000\u0207\u0206\u0001\u0000\u0000\u0000\u0207\u0208\u0001"+
		"\u0000\u0000\u0000\u0208\u020a\u0001\u0000\u0000\u0000\u0209\u0204\u0001"+
		"\u0000\u0000\u0000\u0209\u020a\u0001\u0000\u0000\u0000\u020a\u020c\u0001"+
		"\u0000\u0000\u0000\u020b\u0203\u0001\u0000\u0000\u0000\u020b\u020c\u0001"+
		"\u0000\u0000\u0000\u020c\u0213\u0001\u0000\u0000\u0000\u020d\u020e\u0005"+
		"@\u0000\u0000\u020e\u0210\u0003\u0018\f\u0000\u020f\u0211\u0005=\u0000"+
		"\u0000\u0210\u020f\u0001\u0000\u0000\u0000\u0210\u0211\u0001\u0000\u0000"+
		"\u0000\u0211\u0213\u0001\u0000\u0000\u0000\u0212\u01c1\u0001\u0000\u0000"+
		"\u0000\u0212\u01f4\u0001\u0000\u0000\u0000\u0212\u020d\u0001\u0000\u0000"+
		"\u0000\u0213\u0017\u0001\u0000\u0000\u0000\u0214\u0219\u0003\u00c8d\u0000"+
		"\u0215\u0219\u0003\u00f2y\u0000\u0216\u0219\u0003\u00f8|\u0000\u0217\u0219"+
		"\u0003\u0106\u0083\u0000\u0218\u0214\u0001\u0000\u0000\u0000\u0218\u0215"+
		"\u0001\u0000\u0000\u0000\u0218\u0216\u0001\u0000\u0000\u0000\u0218\u0217"+
		"\u0001\u0000\u0000\u0000\u0219\u0019\u0001\u0000\u0000\u0000\u021a\u021b"+
		"\u0003\u0108\u0084\u0000\u021b\u021c\u0005.\u0000\u0000\u021c\u0223\u0001"+
		"\u0000\u0000\u0000\u021d\u021e\u0003\u00fe\u007f\u0000\u021e\u021f\u0005"+
		".\u0000\u0000\u021f\u0223\u0001\u0000\u0000\u0000\u0220\u0223\u0003\u001c"+
		"\u000e\u0000\u0221\u0223\u0003N\'\u0000\u0222\u021a\u0001\u0000\u0000"+
		"\u0000\u0222\u021d\u0001\u0000\u0000\u0000\u0222\u0220\u0001\u0000\u0000"+
		"\u0000\u0222\u0221\u0001\u0000\u0000\u0000\u0223\u001b\u0001\u0000\u0000"+
		"\u0000\u0224\u0229\u0003\u001e\u000f\u0000\u0225\u0226\u0005?\u0000\u0000"+
		"\u0226\u0228\u0003\u001e\u000f\u0000\u0227\u0225\u0001\u0000\u0000\u0000"+
		"\u0228\u022b\u0001\u0000\u0000\u0000\u0229\u0227\u0001\u0000\u0000\u0000"+
		"\u0229\u022a\u0001\u0000\u0000\u0000\u022a\u022d\u0001\u0000\u0000\u0000"+
		"\u022b\u0229\u0001\u0000\u0000\u0000\u022c\u022e\u0005?\u0000\u0000\u022d"+
		"\u022c\u0001\u0000\u0000\u0000\u022d\u022e\u0001\u0000\u0000\u0000\u022e"+
		"\u022f\u0001\u0000\u0000\u0000\u022f\u0230\u0005.\u0000\u0000\u0230\u001d"+
		"\u0001\u0000\u0000\u0000\u0231\u023b\u0003\u00f0x\u0000\u0232\u023b\u0003"+
		" \u0010\u0000\u0233\u023b\u0003(\u0014\u0000\u0234\u023b\u0003*\u0015"+
		"\u0000\u0235\u023b\u0003,\u0016\u0000\u0236\u023b\u00038\u001c\u0000\u0237"+
		"\u023b\u0003H$\u0000\u0238\u023b\u0003J%\u0000\u0239\u023b\u0003L&\u0000"+
		"\u023a\u0231\u0001\u0000\u0000\u0000\u023a\u0232\u0001\u0000\u0000\u0000"+
		"\u023a\u0233\u0001\u0000\u0000\u0000\u023a\u0234\u0001\u0000\u0000\u0000"+
		"\u023a\u0235\u0001\u0000\u0000\u0000\u023a\u0236\u0001\u0000\u0000\u0000"+
		"\u023a\u0237\u0001\u0000\u0000\u0000\u023a\u0238\u0001\u0000\u0000\u0000"+
		"\u023a\u0239\u0001\u0000\u0000\u0000\u023b\u001f\u0001\u0000\u0000\u0000"+
		"\u023c\u024d\u0003$\u0012\u0000\u023d\u024e\u0003\"\u0011\u0000\u023e"+
		"\u0241\u0003&\u0013\u0000\u023f\u0242\u0003\u00e8t\u0000\u0240\u0242\u0003"+
		"\u00d6k\u0000\u0241\u023f\u0001\u0000\u0000\u0000\u0241\u0240\u0001\u0000"+
		"\u0000\u0000\u0242\u024e\u0001\u0000\u0000\u0000\u0243\u0246\u0005A\u0000"+
		"\u0000\u0244\u0247\u0003\u00e8t\u0000\u0245\u0247\u0003$\u0012\u0000\u0246"+
		"\u0244\u0001\u0000\u0000\u0000\u0246\u0245\u0001\u0000\u0000\u0000\u0247"+
		"\u0249\u0001\u0000\u0000\u0000\u0248\u0243\u0001\u0000\u0000\u0000\u0249"+
		"\u024c\u0001\u0000\u0000\u0000\u024a\u0248\u0001\u0000\u0000\u0000\u024a"+
		"\u024b\u0001\u0000\u0000\u0000\u024b\u024e\u0001\u0000\u0000\u0000\u024c"+
		"\u024a\u0001\u0000\u0000\u0000\u024d\u023d\u0001\u0000\u0000\u0000\u024d"+
		"\u023e\u0001\u0000\u0000\u0000\u024d\u024a\u0001\u0000\u0000\u0000\u024e"+
		"!\u0001\u0000\u0000\u0000\u024f\u0250\u0005>\u0000\u0000\u0250\u0253\u0003"+
		"\u00aeW\u0000\u0251\u0252\u0005A\u0000\u0000\u0252\u0254\u0003\u00aeW"+
		"\u0000\u0253\u0251\u0001\u0000\u0000\u0000\u0253\u0254\u0001\u0000\u0000"+
		"\u0000\u0254#\u0001\u0000\u0000\u0000\u0255\u0258\u0003\u00aeW\u0000\u0256"+
		"\u0258\u0003\u00c0`\u0000\u0257\u0255\u0001\u0000\u0000\u0000\u0257\u0256"+
		"\u0001\u0000\u0000\u0000\u0258\u0260\u0001\u0000\u0000\u0000\u0259\u025c"+
		"\u0005=\u0000\u0000\u025a\u025d\u0003\u00aeW\u0000\u025b\u025d\u0003\u00c0"+
		"`\u0000\u025c\u025a\u0001\u0000\u0000\u0000\u025c\u025b\u0001\u0000\u0000"+
		"\u0000\u025d\u025f\u0001\u0000\u0000\u0000\u025e\u0259\u0001\u0000\u0000"+
		"\u0000\u025f\u0262\u0001\u0000\u0000\u0000\u0260\u025e\u0001\u0000\u0000"+
		"\u0000\u0260\u0261\u0001\u0000\u0000\u0000\u0261\u0264\u0001\u0000\u0000"+
		"\u0000\u0262\u0260\u0001\u0000\u0000\u0000\u0263\u0265\u0005=\u0000\u0000"+
		"\u0264\u0263\u0001\u0000\u0000\u0000\u0264\u0265\u0001\u0000\u0000\u0000"+
		"\u0265%\u0001\u0000\u0000\u0000\u0266\u0267\u0007\u0000\u0000\u0000\u0267"+
		"\'\u0001\u0000\u0000\u0000\u0268\u0269\u0005\u0010\u0000\u0000\u0269\u026a"+
		"\u0003\u00d4j\u0000\u026a)\u0001\u0000\u0000\u0000\u026b\u026c\u0005#"+
		"\u0000\u0000\u026c+\u0001\u0000\u0000\u0000\u026d\u0273\u0003.\u0017\u0000"+
		"\u026e\u0273\u00030\u0018\u0000\u026f\u0273\u00032\u0019\u0000\u0270\u0273"+
		"\u00036\u001b\u0000\u0271\u0273\u00034\u001a\u0000\u0272\u026d\u0001\u0000"+
		"\u0000\u0000\u0272\u026e\u0001\u0000\u0000\u0000\u0272\u026f\u0001\u0000"+
		"\u0000\u0000\u0272\u0270\u0001\u0000\u0000\u0000\u0272\u0271\u0001\u0000"+
		"\u0000\u0000\u0273-\u0001\u0000\u0000\u0000\u0274\u0275\u0005\u000b\u0000"+
		"\u0000\u0275/\u0001\u0000\u0000\u0000\u0276\u0277\u0005\u000e\u0000\u0000"+
		"\u02771\u0001\u0000\u0000\u0000\u0278\u027a\u0005%\u0000\u0000\u0279\u027b"+
		"\u0003\u00d6k\u0000\u027a\u0279\u0001\u0000\u0000\u0000\u027a\u027b\u0001"+
		"\u0000\u0000\u0000\u027b3\u0001\u0000\u0000\u0000\u027c\u027d\u0003\u00e8"+
		"t\u0000\u027d5\u0001\u0000\u0000\u0000\u027e\u0284\u0005$\u0000\u0000"+
		"\u027f\u0282\u0003\u00aeW\u0000\u0280\u0281\u0005\u0017\u0000\u0000\u0281"+
		"\u0283\u0003\u00aeW\u0000\u0282\u0280\u0001\u0000\u0000\u0000\u0282\u0283"+
		"\u0001\u0000\u0000\u0000\u0283\u0285\u0001\u0000\u0000\u0000\u0284\u027f"+
		"\u0001\u0000\u0000\u0000\u0284\u0285\u0001\u0000\u0000\u0000\u02857\u0001"+
		"\u0000\u0000\u0000\u0286\u0289\u0003:\u001d\u0000\u0287\u0289\u0003<\u001e"+
		"\u0000\u0288\u0286\u0001\u0000\u0000\u0000\u0288\u0287\u0001\u0000\u0000"+
		"\u0000\u02899\u0001\u0000\u0000\u0000\u028a\u028b\u0005\u001a\u0000\u0000"+
		"\u028b\u028c\u0003D\"\u0000\u028c;\u0001\u0000\u0000\u0000\u028d\u029a"+
		"\u0005\u0017\u0000\u0000\u028e\u0290\u0007\u0001\u0000\u0000\u028f\u028e"+
		"\u0001\u0000\u0000\u0000\u0290\u0293\u0001\u0000\u0000\u0000\u0291\u028f"+
		"\u0001\u0000\u0000\u0000\u0291\u0292\u0001\u0000\u0000\u0000\u0292\u0294"+
		"\u0001\u0000\u0000\u0000\u0293\u0291\u0001\u0000\u0000\u0000\u0294\u029b"+
		"\u0003F#\u0000\u0295\u0297\u0007\u0001\u0000\u0000\u0296\u0295\u0001\u0000"+
		"\u0000\u0000\u0297\u0298\u0001\u0000\u0000\u0000\u0298\u0296\u0001\u0000"+
		"\u0000\u0000\u0298\u0299\u0001\u0000\u0000\u0000\u0299\u029b\u0001\u0000"+
		"\u0000\u0000\u029a\u0291\u0001\u0000\u0000\u0000\u029a\u0296\u0001\u0000"+
		"\u0000\u0000\u029b\u029c\u0001\u0000\u0000\u0000\u029c\u02a3\u0005\u001a"+
		"\u0000\u0000\u029d\u02a4\u0005:\u0000\u0000\u029e\u029f\u0005;\u0000\u0000"+
		"\u029f\u02a0\u0003B!\u0000\u02a0\u02a1\u0005<\u0000\u0000\u02a1\u02a4"+
		"\u0001\u0000\u0000\u0000\u02a2\u02a4\u0003B!\u0000\u02a3\u029d\u0001\u0000"+
		"\u0000\u0000\u02a3\u029e\u0001\u0000\u0000\u0000\u02a3\u02a2\u0001\u0000"+
		"\u0000\u0000\u02a4=\u0001\u0000\u0000\u0000\u02a5\u02a8\u0003\u00c8d\u0000"+
		"\u02a6\u02a7\u0005\u0007\u0000\u0000\u02a7\u02a9\u0003\u00c8d\u0000\u02a8"+
		"\u02a6\u0001\u0000\u0000\u0000\u02a8\u02a9\u0001\u0000\u0000\u0000\u02a9"+
		"?\u0001\u0000\u0000\u0000\u02aa\u02ad\u0003F#\u0000\u02ab\u02ac\u0005"+
		"\u0007\u0000\u0000\u02ac\u02ae\u0003\u00c8d\u0000\u02ad\u02ab\u0001\u0000"+
		"\u0000\u0000\u02ad\u02ae\u0001\u0000\u0000\u0000\u02aeA\u0001\u0000\u0000"+
		"\u0000\u02af\u02b4\u0003>\u001f\u0000\u02b0\u02b1\u0005=\u0000\u0000\u02b1"+
		"\u02b3\u0003>\u001f\u0000\u02b2\u02b0\u0001\u0000\u0000\u0000\u02b3\u02b6"+
		"\u0001\u0000\u0000\u0000\u02b4\u02b2\u0001\u0000\u0000\u0000\u02b4\u02b5"+
		"\u0001\u0000\u0000\u0000\u02b5\u02b8\u0001\u0000\u0000\u0000\u02b6\u02b4"+
		"\u0001\u0000\u0000\u0000\u02b7\u02b9\u0005=\u0000\u0000\u02b8\u02b7\u0001"+
		"\u0000\u0000\u0000\u02b8\u02b9\u0001\u0000\u0000\u0000\u02b9C\u0001\u0000"+
		"\u0000\u0000\u02ba\u02bf\u0003@ \u0000\u02bb\u02bc\u0005=\u0000\u0000"+
		"\u02bc\u02be\u0003@ \u0000\u02bd\u02bb\u0001\u0000\u0000\u0000\u02be\u02c1"+
		"\u0001\u0000\u0000\u0000\u02bf\u02bd\u0001\u0000\u0000\u0000\u02bf\u02c0"+
		"\u0001\u0000\u0000\u0000\u02c0E\u0001\u0000\u0000\u0000\u02c1\u02bf\u0001"+
		"\u0000\u0000\u0000\u02c2\u02c7\u0003\u00c8d\u0000\u02c3\u02c4\u00058\u0000"+
		"\u0000\u02c4\u02c6\u0003\u00c8d\u0000\u02c5\u02c3\u0001\u0000\u0000\u0000"+
		"\u02c6\u02c9\u0001\u0000\u0000\u0000\u02c7\u02c5\u0001\u0000\u0000\u0000"+
		"\u02c7\u02c8\u0001\u0000\u0000\u0000\u02c8G\u0001\u0000\u0000\u0000\u02c9"+
		"\u02c7\u0001\u0000\u0000\u0000\u02ca\u02cb\u0005\u0018\u0000\u0000\u02cb"+
		"\u02d0\u0003\u00c8d\u0000\u02cc\u02cd\u0005=\u0000\u0000\u02cd\u02cf\u0003"+
		"\u00c8d\u0000\u02ce\u02cc\u0001\u0000\u0000\u0000\u02cf\u02d2\u0001\u0000"+
		"\u0000\u0000\u02d0\u02ce\u0001\u0000\u0000\u0000\u02d0\u02d1\u0001\u0000"+
		"\u0000\u0000\u02d1I\u0001\u0000\u0000\u0000\u02d2\u02d0\u0001\u0000\u0000"+
		"\u0000\u02d3\u02d4\u0005 \u0000\u0000\u02d4\u02d9\u0003\u00c8d\u0000\u02d5"+
		"\u02d6\u0005=\u0000\u0000\u02d6\u02d8\u0003\u00c8d\u0000\u02d7\u02d5\u0001"+
		"\u0000\u0000\u0000\u02d8\u02db\u0001\u0000\u0000\u0000\u02d9\u02d7\u0001"+
		"\u0000\u0000\u0000\u02d9\u02da\u0001\u0000\u0000\u0000\u02daK\u0001\u0000"+
		"\u0000\u0000\u02db\u02d9\u0001\u0000\u0000\u0000\u02dc\u02dd\u0005\b\u0000"+
		"\u0000\u02dd\u02e0\u0003\u00aeW\u0000\u02de\u02df\u0005=\u0000\u0000\u02df"+
		"\u02e1\u0003\u00aeW\u0000\u02e0\u02de\u0001\u0000\u0000\u0000\u02e0\u02e1"+
		"\u0001\u0000\u0000\u0000\u02e1M\u0001\u0000\u0000\u0000\u02e2\u02ee\u0003"+
		"\u0100\u0080\u0000\u02e3\u02ee\u0003R)\u0000\u02e4\u02ee\u0003T*\u0000"+
		"\u02e5\u02ee\u0003V+\u0000\u02e6\u02ee\u0003X,\u0000\u02e7\u02ee\u0003"+
		"Z-\u0000\u02e8\u02ee\u0003\u0010\b\u0000\u02e9\u02ee\u0003\u00dam\u0000"+
		"\u02ea\u02ee\u0003\f\u0006\u0000\u02eb\u02ee\u0003P(\u0000\u02ec\u02ee"+
		"\u0003b1\u0000\u02ed\u02e2\u0001\u0000\u0000\u0000\u02ed\u02e3\u0001\u0000"+
		"\u0000\u0000\u02ed\u02e4\u0001\u0000\u0000\u0000\u02ed\u02e5\u0001\u0000"+
		"\u0000\u0000\u02ed\u02e6\u0001\u0000\u0000\u0000\u02ed\u02e7\u0001\u0000"+
		"\u0000\u0000\u02ed\u02e8\u0001\u0000\u0000\u0000\u02ed\u02e9\u0001\u0000"+
		"\u0000\u0000\u02ed\u02ea\u0001\u0000\u0000\u0000\u02ed\u02eb\u0001\u0000"+
		"\u0000\u0000\u02ed\u02ec\u0001\u0000\u0000\u0000\u02eeO\u0001\u0000\u0000"+
		"\u0000\u02ef\u02f3\u0005\t\u0000\u0000\u02f0\u02f4\u0003\u0010\b\u0000"+
		"\u02f1\u02f4\u0003Z-\u0000\u02f2\u02f4\u0003V+\u0000\u02f3\u02f0\u0001"+
		"\u0000\u0000\u0000\u02f3\u02f1\u0001\u0000\u0000\u0000\u02f3\u02f2\u0001"+
		"\u0000\u0000\u0000\u02f4Q\u0001\u0000\u0000\u0000\u02f5\u02f6\u0005\u0019"+
		"\u0000\u0000\u02f6\u02f7\u0003\u00aeW\u0000\u02f7\u02f8\u0005>\u0000\u0000"+
		"\u02f8\u0300\u0003`0\u0000\u02f9\u02fa\u0005\u0011\u0000\u0000\u02fa\u02fb"+
		"\u0003\u00aeW\u0000\u02fb\u02fc\u0005>\u0000\u0000\u02fc\u02fd\u0003`"+
		"0\u0000\u02fd\u02ff\u0001\u0000\u0000\u0000\u02fe\u02f9\u0001\u0000\u0000"+
		"\u0000\u02ff\u0302\u0001\u0000\u0000\u0000\u0300\u02fe\u0001\u0000\u0000"+
		"\u0000\u0300\u0301\u0001\u0000\u0000\u0000\u0301\u0306\u0001\u0000\u0000"+
		"\u0000\u0302\u0300\u0001\u0000\u0000\u0000\u0303\u0304\u0005\u0012\u0000"+
		"\u0000\u0304\u0305\u0005>\u0000\u0000\u0305\u0307\u0003`0\u0000\u0306"+
		"\u0303\u0001\u0000\u0000\u0000\u0306\u0307\u0001\u0000\u0000\u0000\u0307"+
		"S\u0001\u0000\u0000\u0000\u0308\u0309\u0005)\u0000\u0000\u0309\u030a\u0003"+
		"\u00aeW\u0000\u030a\u030b\u0005>\u0000\u0000\u030b\u030f\u0003`0\u0000"+
		"\u030c\u030d\u0005\u0012\u0000\u0000\u030d\u030e\u0005>\u0000\u0000\u030e"+
		"\u0310\u0003`0\u0000\u030f\u030c\u0001\u0000\u0000\u0000\u030f\u0310\u0001"+
		"\u0000\u0000\u0000\u0310U\u0001\u0000\u0000\u0000\u0311\u0312\u0005\u0016"+
		"\u0000\u0000\u0312\u0313\u0003\u00d4j\u0000\u0313\u0314\u0005\u001b\u0000"+
		"\u0000\u0314\u0315\u0003\u00d6k\u0000\u0315\u0316\u0005>\u0000\u0000\u0316"+
		"\u031a\u0003`0\u0000\u0317\u0318\u0005\u0012\u0000\u0000\u0318\u0319\u0005"+
		">\u0000\u0000\u0319\u031b\u0003`0\u0000\u031a\u0317\u0001\u0000\u0000"+
		"\u0000\u031a\u031b\u0001\u0000\u0000\u0000\u031bW\u0001\u0000\u0000\u0000"+
		"\u031c\u031d\u0005\'\u0000\u0000\u031d\u031e\u0005>\u0000\u0000\u031e"+
		"\u0334\u0003`0\u0000\u031f\u0320\u0003^/\u0000\u0320\u0321\u0005>\u0000"+
		"\u0000\u0321\u0322\u0003`0\u0000\u0322\u0324\u0001\u0000\u0000\u0000\u0323"+
		"\u031f\u0001\u0000\u0000\u0000\u0324\u0325\u0001\u0000\u0000\u0000\u0325"+
		"\u0323\u0001\u0000\u0000\u0000\u0325\u0326\u0001\u0000\u0000\u0000\u0326"+
		"\u032a\u0001\u0000\u0000\u0000\u0327\u0328\u0005\u0012\u0000\u0000\u0328"+
		"\u0329\u0005>\u0000\u0000\u0329\u032b\u0003`0\u0000\u032a\u0327\u0001"+
		"\u0000\u0000\u0000\u032a\u032b\u0001\u0000\u0000\u0000\u032b\u032f\u0001"+
		"\u0000\u0000\u0000\u032c\u032d\u0005\u0015\u0000\u0000\u032d\u032e\u0005"+
		">\u0000\u0000\u032e\u0330\u0003`0\u0000\u032f\u032c\u0001\u0000\u0000"+
		"\u0000\u032f\u0330\u0001\u0000\u0000\u0000\u0330\u0335\u0001\u0000\u0000"+
		"\u0000\u0331\u0332\u0005\u0015\u0000\u0000\u0332\u0333\u0005>\u0000\u0000"+
		"\u0333\u0335\u0003`0\u0000\u0334\u0323\u0001\u0000\u0000\u0000\u0334\u0331"+
		"\u0001\u0000\u0000\u0000\u0335Y\u0001\u0000\u0000\u0000\u0336\u0337\u0005"+
		"*\u0000\u0000\u0337\u033c\u0003\\.\u0000\u0338\u0339\u0005=\u0000\u0000"+
		"\u0339\u033b\u0003\\.\u0000\u033a\u0338\u0001\u0000\u0000\u0000\u033b"+
		"\u033e\u0001\u0000\u0000\u0000\u033c\u033a\u0001\u0000\u0000\u0000\u033c"+
		"\u033d\u0001\u0000\u0000\u0000\u033d\u033f\u0001\u0000\u0000\u0000\u033e"+
		"\u033c\u0001\u0000\u0000\u0000\u033f\u0340\u0005>\u0000\u0000\u0340\u0341"+
		"\u0003`0\u0000\u0341[\u0001\u0000\u0000\u0000\u0342\u0345\u0003\u00ae"+
		"W\u0000\u0343\u0344\u0005\u0007\u0000\u0000\u0344\u0346\u0003\u00c2a\u0000"+
		"\u0345\u0343\u0001\u0000\u0000\u0000\u0345\u0346\u0001\u0000\u0000\u0000"+
		"\u0346]\u0001\u0000\u0000\u0000\u0347\u034d\u0005\u0013\u0000\u0000\u0348"+
		"\u034b\u0003\u00aeW\u0000\u0349\u034a\u0005\u0007\u0000\u0000\u034a\u034c"+
		"\u0003\u00c8d\u0000\u034b\u0349\u0001\u0000\u0000\u0000\u034b\u034c\u0001"+
		"\u0000\u0000\u0000\u034c\u034e\u0001\u0000\u0000\u0000\u034d\u0348\u0001"+
		"\u0000\u0000\u0000\u034d\u034e\u0001\u0000\u0000\u0000\u034e_\u0001\u0000"+
		"\u0000\u0000\u034f\u035a\u0003\u001c\u000e\u0000\u0350\u0351\u0005.\u0000"+
		"\u0000\u0351\u0353\u0005\u0001\u0000\u0000\u0352\u0354\u0003\u001a\r\u0000"+
		"\u0353\u0352\u0001\u0000\u0000\u0000\u0354\u0355\u0001\u0000\u0000\u0000"+
		"\u0355\u0353\u0001\u0000\u0000\u0000\u0355\u0356\u0001\u0000\u0000\u0000"+
		"\u0356\u0357\u0001\u0000\u0000\u0000\u0357\u0358\u0005\u0002\u0000\u0000"+
		"\u0358\u035a\u0001\u0000\u0000\u0000\u0359\u034f\u0001\u0000\u0000\u0000"+
		"\u0359\u0350\u0001\u0000\u0000\u0000\u035aa\u0001\u0000\u0000\u0000\u035b"+
		"\u035c\u0005\u001e\u0000\u0000\u035c\u035d\u0003d2\u0000\u035d\u035e\u0005"+
		">\u0000\u0000\u035e\u035f\u0005.\u0000\u0000\u035f\u0361\u0005\u0001\u0000"+
		"\u0000\u0360\u0362\u0003j5\u0000\u0361\u0360\u0001\u0000\u0000\u0000\u0362"+
		"\u0363\u0001\u0000\u0000\u0000\u0363\u0361\u0001\u0000\u0000\u0000\u0363"+
		"\u0364\u0001\u0000\u0000\u0000\u0364\u0365\u0001\u0000\u0000\u0000\u0365"+
		"\u0366\u0005\u0002\u0000\u0000\u0366c\u0001\u0000\u0000\u0000\u0367\u0368"+
		"\u0003h4\u0000\u0368\u036a\u0005=\u0000\u0000\u0369\u036b\u0003f3\u0000"+
		"\u036a\u0369\u0001\u0000\u0000\u0000\u036a\u036b\u0001\u0000\u0000\u0000"+
		"\u036b\u036e\u0001\u0000\u0000\u0000\u036c\u036e\u0003\u00aeW\u0000\u036d"+
		"\u0367\u0001\u0000\u0000\u0000\u036d\u036c\u0001\u0000\u0000\u0000\u036e"+
		"e\u0001\u0000\u0000\u0000\u036f\u0371\u0005=\u0000\u0000\u0370\u0372\u0003"+
		"h4\u0000\u0371\u0370\u0001\u0000\u0000\u0000\u0372\u0373\u0001\u0000\u0000"+
		"\u0000\u0373\u0371\u0001\u0000\u0000\u0000\u0373\u0374\u0001\u0000\u0000"+
		"\u0000\u0374\u0376\u0001\u0000\u0000\u0000\u0375\u0377\u0005=\u0000\u0000"+
		"\u0376\u0375\u0001\u0000\u0000\u0000\u0376\u0377\u0001\u0000\u0000\u0000"+
		"\u0377g\u0001\u0000\u0000\u0000\u0378\u0379\u0005:\u0000\u0000\u0379\u037c"+
		"\u0003\u00c2a\u0000\u037a\u037c\u0003\u00aeW\u0000\u037b\u0378\u0001\u0000"+
		"\u0000\u0000\u037b\u037a\u0001\u0000\u0000\u0000\u037ci\u0001\u0000\u0000"+
		"\u0000\u037d\u037e\u0005\f\u0000\u0000\u037e\u0380\u0003n7\u0000\u037f"+
		"\u0381\u0003l6\u0000\u0380\u037f\u0001\u0000\u0000\u0000\u0380\u0381\u0001"+
		"\u0000\u0000\u0000\u0381\u0382\u0001\u0000\u0000\u0000\u0382\u0383\u0005"+
		">\u0000\u0000\u0383\u0384\u0003`0\u0000\u0384k\u0001\u0000\u0000\u0000"+
		"\u0385\u0386\u0005\u0019\u0000\u0000\u0386\u0387\u0003\u00aeW\u0000\u0387"+
		"m\u0001\u0000\u0000\u0000\u0388\u038b\u0003\u0096K\u0000\u0389\u038b\u0003"+
		"p8\u0000\u038a\u0388\u0001\u0000\u0000\u0000\u038a\u0389\u0001\u0000\u0000"+
		"\u0000\u038bo\u0001\u0000\u0000\u0000\u038c\u038f\u0003r9\u0000\u038d"+
		"\u038f\u0003t:\u0000\u038e\u038c\u0001\u0000\u0000\u0000\u038e\u038d\u0001"+
		"\u0000\u0000\u0000\u038fq\u0001\u0000\u0000\u0000\u0390\u0391\u0003t:"+
		"\u0000\u0391\u0392\u0005\u0007\u0000\u0000\u0392\u0393\u0003\u0088D\u0000"+
		"\u0393s\u0001\u0000\u0000\u0000\u0394\u0399\u0003v;\u0000\u0395\u0396"+
		"\u0005D\u0000\u0000\u0396\u0398\u0003v;\u0000\u0397\u0395\u0001\u0000"+
		"\u0000\u0000\u0398\u039b\u0001\u0000\u0000\u0000\u0399\u0397\u0001\u0000"+
		"\u0000\u0000\u0399\u039a\u0001\u0000\u0000\u0000\u039au\u0001\u0000\u0000"+
		"\u0000\u039b\u0399\u0001\u0000\u0000\u0000\u039c\u03a5\u0003\u008aE\u0000"+
		"\u039d\u03a5\u0003x<\u0000\u039e\u03a5\u0003\u0086C\u0000\u039f\u03a5"+
		"\u0003\u008cF\u0000\u03a0\u03a5\u0003\u0092I\u0000\u03a1\u03a5\u0003\u0094"+
		"J\u0000\u03a2\u03a5\u0003\u009eO\u0000\u03a3\u03a5\u0003\u00a6S\u0000"+
		"\u03a4\u039c\u0001\u0000\u0000\u0000\u03a4\u039d\u0001\u0000\u0000\u0000"+
		"\u03a4\u039e\u0001\u0000\u0000\u0000\u03a4\u039f\u0001\u0000\u0000\u0000"+
		"\u03a4\u03a0\u0001\u0000\u0000\u0000\u03a4\u03a1\u0001\u0000\u0000\u0000"+
		"\u03a4\u03a2\u0001\u0000\u0000\u0000\u03a4\u03a3\u0001\u0000\u0000\u0000"+
		"\u03a5w\u0001\u0000\u0000\u0000\u03a6\u03a7\u0003~?\u0000\u03a7\u03a8"+
		"\u0004<\u0000\u0000\u03a8\u03af\u0001\u0000\u0000\u0000\u03a9\u03af\u0003"+
		"|>\u0000\u03aa\u03af\u0003\u00ecv\u0000\u03ab\u03af\u0005\u001f\u0000"+
		"\u0000\u03ac\u03af\u0005&\u0000\u0000\u03ad\u03af\u0005\u0014\u0000\u0000"+
		"\u03ae\u03a6\u0001\u0000\u0000\u0000\u03ae\u03a9\u0001\u0000\u0000\u0000"+
		"\u03ae\u03aa\u0001\u0000\u0000\u0000\u03ae\u03ab\u0001\u0000\u0000\u0000"+
		"\u03ae\u03ac\u0001\u0000\u0000\u0000\u03ae\u03ad\u0001\u0000\u0000\u0000"+
		"\u03afy\u0001\u0000\u0000\u0000\u03b0\u03b1\u0003~?\u0000\u03b1\u03b2"+
		"\u0004=\u0001\u0000\u03b2\u03b9\u0001\u0000\u0000\u0000\u03b3\u03b9\u0003"+
		"|>\u0000\u03b4\u03b9\u0003\u00ecv\u0000\u03b5\u03b9\u0005\u001f\u0000"+
		"\u0000\u03b6\u03b9\u0005&\u0000\u0000\u03b7\u03b9\u0005\u0014\u0000\u0000"+
		"\u03b8\u03b0\u0001\u0000\u0000\u0000\u03b8\u03b3\u0001\u0000\u0000\u0000"+
		"\u03b8\u03b4\u0001\u0000\u0000\u0000\u03b8\u03b5\u0001\u0000\u0000\u0000"+
		"\u03b8\u03b6\u0001\u0000\u0000\u0000\u03b8\u03b7\u0001\u0000\u0000\u0000"+
		"\u03b9{\u0001\u0000\u0000\u0000\u03ba\u03bb\u0003\u0080@\u0000\u03bb\u03bc"+
		"\u0005I\u0000\u0000\u03bc\u03bd\u0003\u0084B\u0000\u03bd\u03c3\u0001\u0000"+
		"\u0000\u0000\u03be\u03bf\u0003\u0080@\u0000\u03bf\u03c0\u0005J\u0000\u0000"+
		"\u03c0\u03c1\u0003\u0084B\u0000\u03c1\u03c3\u0001\u0000\u0000\u0000\u03c2"+
		"\u03ba\u0001\u0000\u0000\u0000\u03c2\u03be\u0001\u0000\u0000\u0000\u03c3"+
		"}\u0001\u0000\u0000\u0000\u03c4\u03c8\u0005\u0004\u0000\u0000\u03c5\u03c6"+
		"\u0005J\u0000\u0000\u03c6\u03c8\u0005\u0004\u0000\u0000\u03c7\u03c4\u0001"+
		"\u0000\u0000\u0000\u03c7\u03c5\u0001\u0000\u0000\u0000\u03c8\u007f\u0001"+
		"\u0000\u0000\u0000\u03c9\u03cd\u0003\u0082A\u0000\u03ca\u03cb\u0005J\u0000"+
		"\u0000\u03cb\u03cd\u0003\u0082A\u0000\u03cc\u03c9\u0001\u0000\u0000\u0000"+
		"\u03cc\u03ca\u0001\u0000\u0000\u0000\u03cd\u0081\u0001\u0000\u0000\u0000"+
		"\u03ce\u03cf\u0005\u0004\u0000\u0000\u03cf\u0083\u0001\u0000\u0000\u0000"+
		"\u03d0\u03d1\u0005\u0004\u0000\u0000\u03d1\u0085\u0001\u0000\u0000\u0000"+
		"\u03d2\u03d3\u0003\u0088D\u0000\u03d3\u0087\u0001\u0000\u0000\u0000\u03d4"+
		"\u03d5\u0003\u00c8d\u0000\u03d5\u03d6\u0004D\u0002\u0000\u03d6\u0089\u0001"+
		"\u0000\u0000\u0000\u03d7\u03d8\u0005(\u0000\u0000\u03d8\u008b\u0001\u0000"+
		"\u0000\u0000\u03d9\u03da\u0003\u008eG\u0000\u03da\u03db\u0004F\u0003\u0000"+
		"\u03db\u008d\u0001\u0000\u0000\u0000\u03dc\u03df\u0003\u00c8d\u0000\u03dd"+
		"\u03de\u00058\u0000\u0000\u03de\u03e0\u0003\u00c8d\u0000\u03df\u03dd\u0001"+
		"\u0000\u0000\u0000\u03e0\u03e1\u0001\u0000\u0000\u0000\u03e1\u03df\u0001"+
		"\u0000\u0000\u0000\u03e1\u03e2\u0001\u0000\u0000\u0000\u03e2\u008f\u0001"+
		"\u0000\u0000\u0000\u03e3\u03e6\u0003\u008eG\u0000\u03e4\u03e6\u0003\u00c8"+
		"d\u0000\u03e5\u03e3\u0001\u0000\u0000\u0000\u03e5\u03e4\u0001\u0000\u0000"+
		"\u0000\u03e6\u0091\u0001\u0000\u0000\u0000\u03e7\u03e8\u0005;\u0000\u0000"+
		"\u03e8\u03e9\u0003p8\u0000\u03e9\u03ea\u0005<\u0000\u0000\u03ea\u0093"+
		"\u0001\u0000\u0000\u0000\u03eb\u03ed\u0005B\u0000\u0000\u03ec\u03ee\u0003"+
		"\u0098L\u0000\u03ed\u03ec\u0001\u0000\u0000\u0000\u03ed\u03ee\u0001\u0000"+
		"\u0000\u0000\u03ee\u03ef\u0001\u0000\u0000\u0000\u03ef\u03f6\u0005C\u0000"+
		"\u0000\u03f0\u03f2\u0005;\u0000\u0000\u03f1\u03f3\u0003\u0096K\u0000\u03f2"+
		"\u03f1\u0001\u0000\u0000\u0000\u03f2\u03f3\u0001\u0000\u0000\u0000\u03f3"+
		"\u03f4\u0001\u0000\u0000\u0000\u03f4\u03f6\u0005<\u0000\u0000\u03f5\u03eb"+
		"\u0001\u0000\u0000\u0000\u03f5\u03f0\u0001\u0000\u0000\u0000\u03f6\u0095"+
		"\u0001\u0000\u0000\u0000\u03f7\u03f8\u0003\u009aM\u0000\u03f8\u03fa\u0005"+
		"=\u0000\u0000\u03f9\u03fb\u0003\u0098L\u0000\u03fa\u03f9\u0001\u0000\u0000"+
		"\u0000\u03fa\u03fb\u0001\u0000\u0000\u0000\u03fb\u0097\u0001\u0000\u0000"+
		"\u0000\u03fc\u0401\u0003\u009aM\u0000\u03fd\u03fe\u0005=\u0000\u0000\u03fe"+
		"\u0400\u0003\u009aM\u0000\u03ff\u03fd\u0001\u0000\u0000\u0000\u0400\u0403"+
		"\u0001\u0000\u0000\u0000\u0401\u03ff\u0001\u0000\u0000\u0000\u0401\u0402"+
		"\u0001\u0000\u0000\u0000\u0402\u0405\u0001\u0000\u0000\u0000\u0403\u0401"+
		"\u0001\u0000\u0000\u0000\u0404\u0406\u0005=\u0000\u0000\u0405\u0404\u0001"+
		"\u0000\u0000\u0000\u0405\u0406\u0001\u0000\u0000\u0000\u0406\u0099\u0001"+
		"\u0000\u0000\u0000\u0407\u040a\u0003\u009cN\u0000\u0408\u040a\u0003p8"+
		"\u0000\u0409\u0407\u0001\u0000\u0000\u0000\u0409\u0408\u0001\u0000\u0000"+
		"\u0000\u040a\u009b\u0001\u0000\u0000\u0000\u040b\u040c\u0005:\u0000\u0000"+
		"\u040c\u0410\u0003\u0088D\u0000\u040d\u040e\u0005:\u0000\u0000\u040e\u0410"+
		"\u0003\u008aE\u0000\u040f\u040b\u0001\u0000\u0000\u0000\u040f\u040d\u0001"+
		"\u0000\u0000\u0000\u0410\u009d\u0001\u0000\u0000\u0000\u0411\u0412\u0005"+
		"O\u0000\u0000\u0412\u042b\u0005P\u0000\u0000\u0413\u0414\u0005O\u0000"+
		"\u0000\u0414\u0416\u0003\u00a4R\u0000\u0415\u0417\u0005=\u0000\u0000\u0416"+
		"\u0415\u0001\u0000\u0000\u0000\u0416\u0417\u0001\u0000\u0000\u0000\u0417"+
		"\u0418\u0001\u0000\u0000\u0000\u0418\u0419\u0005P\u0000\u0000\u0419\u042b"+
		"\u0001\u0000\u0000\u0000\u041a\u041b\u0005O\u0000\u0000\u041b\u041c\u0003"+
		"\u00a0P\u0000\u041c\u041d\u0005=\u0000\u0000\u041d\u041f\u0003\u00a4R"+
		"\u0000\u041e\u0420\u0005=\u0000\u0000\u041f\u041e\u0001\u0000\u0000\u0000"+
		"\u041f\u0420\u0001\u0000\u0000\u0000\u0420\u0421\u0001\u0000\u0000\u0000"+
		"\u0421\u0422\u0005P\u0000\u0000\u0422\u042b\u0001\u0000\u0000\u0000\u0423"+
		"\u0424\u0005O\u0000\u0000\u0424\u0426\u0003\u00a0P\u0000\u0425\u0427\u0005"+
		"=\u0000\u0000\u0426\u0425\u0001\u0000\u0000\u0000\u0426\u0427\u0001\u0000"+
		"\u0000\u0000\u0427\u0428\u0001\u0000\u0000\u0000\u0428\u0429\u0005P\u0000"+
		"\u0000\u0429\u042b\u0001\u0000\u0000\u0000\u042a\u0411\u0001\u0000\u0000"+
		"\u0000\u042a\u0413\u0001\u0000\u0000\u0000\u042a\u041a\u0001\u0000\u0000"+
		"\u0000\u042a\u0423\u0001\u0000\u0000\u0000\u042b\u009f\u0001\u0000\u0000"+
		"\u0000\u042c\u0431\u0003\u00a2Q\u0000\u042d\u042e\u0005=\u0000\u0000\u042e"+
		"\u0430\u0003\u00a2Q\u0000\u042f\u042d\u0001\u0000\u0000\u0000\u0430\u0433"+
		"\u0001\u0000\u0000\u0000\u0431\u042f\u0001\u0000\u0000\u0000\u0431\u0432"+
		"\u0001\u0000\u0000\u0000\u0432\u00a1\u0001\u0000\u0000\u0000\u0433\u0431"+
		"\u0001\u0000\u0000\u0000\u0434\u0437\u0003z=\u0000\u0435\u0437\u0003\u008e"+
		"G\u0000\u0436\u0434\u0001\u0000\u0000\u0000\u0436\u0435\u0001\u0000\u0000"+
		"\u0000\u0437\u0438\u0001\u0000\u0000\u0000\u0438\u0439\u0005>\u0000\u0000"+
		"\u0439\u043a\u0003p8\u0000\u043a\u00a3\u0001\u0000\u0000\u0000\u043b\u043c"+
		"\u0005@\u0000\u0000\u043c\u043d\u0003\u0088D\u0000\u043d\u00a5\u0001\u0000"+
		"\u0000\u0000\u043e\u043f\u0003\u0090H\u0000\u043f\u0440\u0005;\u0000\u0000"+
		"\u0440\u0441\u0005<\u0000\u0000\u0441\u045d\u0001\u0000\u0000\u0000\u0442"+
		"\u0443\u0003\u0090H\u0000\u0443\u0444\u0005;\u0000\u0000\u0444\u0446\u0003"+
		"\u00a8T\u0000\u0445\u0447\u0005=\u0000\u0000\u0446\u0445\u0001\u0000\u0000"+
		"\u0000\u0446\u0447\u0001\u0000\u0000\u0000\u0447\u0448\u0001\u0000\u0000"+
		"\u0000\u0448\u0449\u0005<\u0000\u0000\u0449\u045d\u0001\u0000\u0000\u0000"+
		"\u044a\u044b\u0003\u0090H\u0000\u044b\u044c\u0005;\u0000\u0000\u044c\u044e"+
		"\u0003\u00aaU\u0000\u044d\u044f\u0005=\u0000\u0000\u044e\u044d\u0001\u0000"+
		"\u0000\u0000\u044e\u044f\u0001\u0000\u0000\u0000\u044f\u0450\u0001\u0000"+
		"\u0000\u0000\u0450\u0451\u0005<\u0000\u0000\u0451\u045d\u0001\u0000\u0000"+
		"\u0000\u0452\u0453\u0003\u0090H\u0000\u0453\u0454\u0005;\u0000\u0000\u0454"+
		"\u0455\u0003\u00a8T\u0000\u0455\u0456\u0005=\u0000\u0000\u0456\u0458\u0003"+
		"\u00aaU\u0000\u0457\u0459\u0005=\u0000\u0000\u0458\u0457\u0001\u0000\u0000"+
		"\u0000\u0458\u0459\u0001\u0000\u0000\u0000\u0459\u045a\u0001\u0000\u0000"+
		"\u0000\u045a\u045b\u0005<\u0000\u0000\u045b\u045d\u0001\u0000\u0000\u0000"+
		"\u045c\u043e\u0001\u0000\u0000\u0000\u045c\u0442\u0001\u0000\u0000\u0000"+
		"\u045c\u044a\u0001\u0000\u0000\u0000\u045c\u0452\u0001\u0000\u0000\u0000"+
		"\u045d\u00a7\u0001\u0000\u0000\u0000\u045e\u0463\u0003p8\u0000\u045f\u0460"+
		"\u0005=\u0000\u0000\u0460\u0462\u0003p8\u0000\u0461\u045f\u0001\u0000"+
		"\u0000\u0000\u0462\u0465\u0001\u0000\u0000\u0000\u0463\u0461\u0001\u0000"+
		"\u0000\u0000\u0463\u0464\u0001\u0000\u0000\u0000\u0464\u00a9\u0001\u0000"+
		"\u0000\u0000\u0465\u0463\u0001\u0000\u0000\u0000\u0466\u046b\u0003\u00ac"+
		"V\u0000\u0467\u0468\u0005=\u0000\u0000\u0468\u046a\u0003\u00acV\u0000"+
		"\u0469\u0467\u0001\u0000\u0000\u0000\u046a\u046d\u0001\u0000\u0000\u0000"+
		"\u046b\u0469\u0001\u0000\u0000\u0000\u046b\u046c\u0001\u0000\u0000\u0000"+
		"\u046c\u00ab\u0001\u0000\u0000\u0000\u046d\u046b\u0001\u0000\u0000\u0000"+
		"\u046e\u046f\u0003\u00c8d\u0000\u046f\u0470\u0005A\u0000\u0000\u0470\u0471"+
		"\u0003p8\u0000\u0471\u00ad\u0001\u0000\u0000\u0000\u0472\u0478\u0003\u00b6"+
		"[\u0000\u0473\u0474\u0005\u0019\u0000\u0000\u0474\u0475\u0003\u00b6[\u0000"+
		"\u0475\u0476\u0005\u0012\u0000\u0000\u0476\u0477\u0003\u00aeW\u0000\u0477"+
		"\u0479\u0001\u0000\u0000\u0000\u0478\u0473\u0001\u0000\u0000\u0000\u0478"+
		"\u0479\u0001\u0000\u0000\u0000\u0479\u047c\u0001\u0000\u0000\u0000\u047a"+
		"\u047c\u0003\u00b2Y\u0000\u047b\u0472\u0001\u0000\u0000\u0000\u047b\u047a"+
		"\u0001\u0000\u0000\u0000\u047c\u00af\u0001\u0000\u0000\u0000\u047d\u0480"+
		"\u0003\u00b6[\u0000\u047e\u0480\u0003\u00b4Z\u0000\u047f\u047d\u0001\u0000"+
		"\u0000\u0000\u047f\u047e\u0001\u0000\u0000\u0000\u0480\u00b1\u0001\u0000"+
		"\u0000\u0000\u0481\u0483\u0005\u001d\u0000\u0000\u0482\u0484\u0003\u0016"+
		"\u000b\u0000\u0483\u0482\u0001\u0000\u0000\u0000\u0483\u0484\u0001\u0000"+
		"\u0000\u0000\u0484\u0485\u0001\u0000\u0000\u0000\u0485\u0486\u0005>\u0000"+
		"\u0000\u0486\u0487\u0003\u00aeW\u0000\u0487\u00b3\u0001\u0000\u0000\u0000"+
		"\u0488\u048a\u0005\u001d\u0000\u0000\u0489\u048b\u0003\u0016\u000b\u0000"+
		"\u048a\u0489\u0001\u0000\u0000\u0000\u048a\u048b\u0001\u0000\u0000\u0000"+
		"\u048b\u048c\u0001\u0000\u0000\u0000\u048c\u048d\u0005>\u0000\u0000\u048d"+
		"\u048e\u0003\u00b0X\u0000\u048e\u00b5\u0001\u0000\u0000\u0000\u048f\u0494"+
		"\u0003\u00b8\\\u0000\u0490\u0491\u0005\"\u0000\u0000\u0491\u0493\u0003"+
		"\u00b8\\\u0000\u0492\u0490\u0001\u0000\u0000\u0000\u0493\u0496\u0001\u0000"+
		"\u0000\u0000\u0494\u0492\u0001\u0000\u0000\u0000\u0494\u0495\u0001\u0000"+
		"\u0000\u0000\u0495\u00b7\u0001\u0000\u0000\u0000\u0496\u0494\u0001\u0000"+
		"\u0000\u0000\u0497\u049c\u0003\u00ba]\u0000\u0498\u0499\u0005\u0006\u0000"+
		"\u0000\u0499\u049b\u0003\u00ba]\u0000\u049a\u0498\u0001\u0000\u0000\u0000"+
		"\u049b\u049e\u0001\u0000\u0000\u0000\u049c\u049a\u0001\u0000\u0000\u0000"+
		"\u049c\u049d\u0001\u0000\u0000\u0000\u049d\u00b9\u0001\u0000\u0000\u0000"+
		"\u049e\u049c\u0001\u0000\u0000\u0000\u049f\u04a0\u0005!\u0000\u0000\u04a0"+
		"\u04a3\u0003\u00ba]\u0000\u04a1\u04a3\u0003\u00bc^\u0000\u04a2\u049f\u0001"+
		"\u0000\u0000\u0000\u04a2\u04a1\u0001\u0000\u0000\u0000\u04a3\u00bb\u0001"+
		"\u0000\u0000\u0000\u04a4\u04aa\u0003\u00c2a\u0000\u04a5\u04a6\u0003\u00be"+
		"_\u0000\u04a6\u04a7\u0003\u00c2a\u0000\u04a7\u04a9\u0001\u0000\u0000\u0000"+
		"\u04a8\u04a5\u0001\u0000\u0000\u0000\u04a9\u04ac\u0001\u0000\u0000\u0000"+
		"\u04aa\u04a8\u0001\u0000\u0000\u0000\u04aa\u04ab\u0001\u0000\u0000\u0000"+
		"\u04ab\u00bd\u0001\u0000\u0000\u0000\u04ac\u04aa\u0001\u0000\u0000\u0000"+
		"\u04ad\u04bb\u0005Q\u0000\u0000\u04ae\u04bb\u0005R\u0000\u0000\u04af\u04bb"+
		"\u0005S\u0000\u0000\u04b0\u04bb\u0005T\u0000\u0000\u04b1\u04bb\u0005U"+
		"\u0000\u0000\u04b2\u04bb\u0005V\u0000\u0000\u04b3\u04bb\u0005W\u0000\u0000"+
		"\u04b4\u04bb\u0005\u001b\u0000\u0000\u04b5\u04b6\u0005!\u0000\u0000\u04b6"+
		"\u04bb\u0005\u001b\u0000\u0000\u04b7\u04bb\u0005\u001c\u0000\u0000\u04b8"+
		"\u04b9\u0005\u001c\u0000\u0000\u04b9\u04bb\u0005!\u0000\u0000\u04ba\u04ad"+
		"\u0001\u0000\u0000\u0000\u04ba\u04ae\u0001\u0000\u0000\u0000\u04ba\u04af"+
		"\u0001\u0000\u0000\u0000\u04ba\u04b0\u0001\u0000\u0000\u0000\u04ba\u04b1"+
		"\u0001\u0000\u0000\u0000\u04ba\u04b2\u0001\u0000\u0000\u0000\u04ba\u04b3"+
		"\u0001\u0000\u0000\u0000\u04ba\u04b4\u0001\u0000\u0000\u0000\u04ba\u04b5"+
		"\u0001\u0000\u0000\u0000\u04ba\u04b7\u0001\u0000\u0000\u0000\u04ba\u04b8"+
		"\u0001\u0000\u0000\u0000\u04bb\u00bf\u0001\u0000\u0000\u0000\u04bc\u04bd"+
		"\u0005:\u0000\u0000\u04bd\u04be\u0003\u00c2a\u0000\u04be\u00c1\u0001\u0000"+
		"\u0000\u0000\u04bf\u04c0\u0006a\uffff\uffff\u0000\u04c0\u04ca\u0003\u00f2"+
		"y\u0000\u04c1\u04ca\u0003\u00f8|\u0000\u04c2\u04ca\u0003\u00c4b\u0000"+
		"\u04c3\u04c5\u0007\u0002\u0000\u0000\u04c4\u04c3\u0001\u0000\u0000\u0000"+
		"\u04c5\u04c6\u0001\u0000\u0000\u0000\u04c6\u04c4\u0001\u0000\u0000\u0000"+
		"\u04c6\u04c7\u0001\u0000\u0000\u0000\u04c7\u04c8\u0001\u0000\u0000\u0000"+
		"\u04c8\u04ca\u0003\u00c2a\u0007\u04c9\u04bf\u0001\u0000\u0000\u0000\u04c9"+
		"\u04c1\u0001\u0000\u0000\u0000\u04c9\u04c2\u0001\u0000\u0000\u0000\u04c9"+
		"\u04c4\u0001\u0000\u0000\u0000\u04ca\u04e2\u0001\u0000\u0000\u0000\u04cb"+
		"\u04cc\n\b\u0000\u0000\u04cc\u04cd\u0005@\u0000\u0000\u04cd\u04e1\u0003"+
		"\u00c2a\t\u04ce\u04cf\n\u0006\u0000\u0000\u04cf\u04d0\u0007\u0003\u0000"+
		"\u0000\u04d0\u04e1\u0003\u00c2a\u0007\u04d1\u04d2\n\u0005\u0000\u0000"+
		"\u04d2\u04d3\u0007\u0004\u0000\u0000\u04d3\u04e1\u0003\u00c2a\u0006\u04d4"+
		"\u04d5\n\u0004\u0000\u0000\u04d5\u04d6\u0007\u0005\u0000\u0000\u04d6\u04e1"+
		"\u0003\u00c2a\u0005\u04d7\u04d8\n\u0003\u0000\u0000\u04d8\u04d9\u0005"+
		"F\u0000\u0000\u04d9\u04e1\u0003\u00c2a\u0004\u04da\u04db\n\u0002\u0000"+
		"\u0000\u04db\u04dc\u0005E\u0000\u0000\u04dc\u04e1\u0003\u00c2a\u0003\u04dd"+
		"\u04de\n\u0001\u0000\u0000\u04de\u04df\u0005D\u0000\u0000\u04df\u04e1"+
		"\u0003\u00c2a\u0002\u04e0\u04cb\u0001\u0000\u0000\u0000\u04e0\u04ce\u0001"+
		"\u0000\u0000\u0000\u04e0\u04d1\u0001\u0000\u0000\u0000\u04e0\u04d4\u0001"+
		"\u0000\u0000\u0000\u04e0\u04d7\u0001\u0000\u0000\u0000\u04e0\u04da\u0001"+
		"\u0000\u0000\u0000\u04e0\u04dd\u0001\u0000\u0000\u0000\u04e1\u04e4\u0001"+
		"\u0000\u0000\u0000\u04e2\u04e0\u0001\u0000\u0000\u0000\u04e2\u04e3\u0001"+
		"\u0000\u0000\u0000\u04e3\u00c3\u0001\u0000\u0000\u0000\u04e4\u04e2\u0001"+
		"\u0000\u0000\u0000\u04e5\u04e7\u0005\n\u0000\u0000\u04e6\u04e5\u0001\u0000"+
		"\u0000\u0000\u04e6\u04e7\u0001\u0000\u0000\u0000\u04e7\u04e8\u0001\u0000"+
		"\u0000\u0000\u04e8\u04ec\u0003\u00c6c\u0000\u04e9\u04eb\u0003\u00ccf\u0000"+
		"\u04ea\u04e9\u0001\u0000\u0000\u0000\u04eb\u04ee\u0001\u0000\u0000\u0000"+
		"\u04ec\u04ea\u0001\u0000\u0000\u0000\u04ec\u04ed\u0001\u0000\u0000\u0000"+
		"\u04ed\u00c5\u0001\u0000\u0000\u0000\u04ee\u04ec\u0001\u0000\u0000\u0000"+
		"\u04ef\u04f2\u0005;\u0000\u0000\u04f0\u04f3\u0003\u00e8t\u0000\u04f1\u04f3"+
		"\u0003\u00cae\u0000\u04f2\u04f0\u0001\u0000\u0000\u0000\u04f2\u04f1\u0001"+
		"\u0000\u0000\u0000\u04f2\u04f3\u0001\u0000\u0000\u0000\u04f3\u04f4\u0001"+
		"\u0000\u0000\u0000\u04f4\u050c\u0005<\u0000\u0000\u04f5\u04f7\u0005B\u0000"+
		"\u0000\u04f6\u04f8\u0003\u00cae\u0000\u04f7\u04f6\u0001\u0000\u0000\u0000"+
		"\u04f7\u04f8\u0001\u0000\u0000\u0000\u04f8\u04f9\u0001\u0000\u0000\u0000"+
		"\u04f9\u050c\u0005C\u0000\u0000\u04fa\u04fc\u0005O\u0000\u0000\u04fb\u04fd"+
		"\u0003\u00d8l\u0000\u04fc\u04fb\u0001\u0000\u0000\u0000\u04fc\u04fd\u0001"+
		"\u0000\u0000\u0000\u04fd\u04fe\u0001\u0000\u0000\u0000\u04fe\u050c\u0005"+
		"P\u0000\u0000\u04ff\u050c\u0003\u00f4z\u0000\u0500\u050c\u0003\u00c8d"+
		"\u0000\u0501\u050c\u0005\u0004\u0000\u0000\u0502\u0504\u0005\u0003\u0000"+
		"\u0000\u0503\u0502\u0001\u0000\u0000\u0000\u0504\u0505\u0001\u0000\u0000"+
		"\u0000\u0505\u0503\u0001\u0000\u0000\u0000\u0505\u0506\u0001\u0000\u0000"+
		"\u0000\u0506\u050c\u0001\u0000\u0000\u0000\u0507\u050c\u00059\u0000\u0000"+
		"\u0508\u050c\u0005\u001f\u0000\u0000\u0509\u050c\u0005&\u0000\u0000\u050a"+
		"\u050c\u0005\u0014\u0000\u0000\u050b\u04ef\u0001\u0000\u0000\u0000\u050b"+
		"\u04f5\u0001\u0000\u0000\u0000\u050b\u04fa\u0001\u0000\u0000\u0000\u050b"+
		"\u04ff\u0001\u0000\u0000\u0000\u050b\u0500\u0001\u0000\u0000\u0000\u050b"+
		"\u0501\u0001\u0000\u0000\u0000\u050b\u0503\u0001\u0000\u0000\u0000\u050b"+
		"\u0507\u0001\u0000\u0000\u0000\u050b\u0508\u0001\u0000\u0000\u0000\u050b"+
		"\u0509\u0001\u0000\u0000\u0000\u050b\u050a\u0001\u0000\u0000\u0000\u050c"+
		"\u00c7\u0001\u0000\u0000\u0000\u050d\u050e\u0007\u0006\u0000\u0000\u050e"+
		"\u00c9\u0001\u0000\u0000\u0000\u050f\u0513\u0003\u0106\u0083\u0000\u0510"+
		"\u0513\u0003\u00aeW\u0000\u0511\u0513\u0003\u00c0`\u0000\u0512\u050f\u0001"+
		"\u0000\u0000\u0000\u0512\u0510\u0001\u0000\u0000\u0000\u0512\u0511\u0001"+
		"\u0000\u0000\u0000\u0513\u0523\u0001\u0000\u0000\u0000\u0514\u0524\u0003"+
		"\u00e2q\u0000\u0515\u0519\u0005=\u0000\u0000\u0516\u051a\u0003\u0106\u0083"+
		"\u0000\u0517\u051a\u0003\u00aeW\u0000\u0518\u051a\u0003\u00c0`\u0000\u0519"+
		"\u0516\u0001\u0000\u0000\u0000\u0519\u0517\u0001\u0000\u0000\u0000\u0519"+
		"\u0518\u0001\u0000\u0000\u0000\u051a\u051c\u0001\u0000\u0000\u0000\u051b"+
		"\u0515\u0001\u0000\u0000\u0000\u051c\u051f\u0001\u0000\u0000\u0000\u051d"+
		"\u051b\u0001\u0000\u0000\u0000\u051d\u051e\u0001\u0000\u0000\u0000\u051e"+
		"\u0521\u0001\u0000\u0000\u0000\u051f\u051d\u0001\u0000\u0000\u0000\u0520"+
		"\u0522\u0005=\u0000\u0000\u0521\u0520\u0001\u0000\u0000\u0000\u0521\u0522"+
		"\u0001\u0000\u0000\u0000\u0522\u0524\u0001\u0000\u0000\u0000\u0523\u0514"+
		"\u0001\u0000\u0000\u0000\u0523\u051d\u0001\u0000\u0000\u0000\u0524\u00cb"+
		"\u0001\u0000\u0000\u0000\u0525\u0527\u0005;\u0000\u0000\u0526\u0528\u0003"+
		"\u00dcn\u0000\u0527\u0526\u0001\u0000\u0000\u0000\u0527\u0528\u0001\u0000"+
		"\u0000\u0000\u0528\u0529\u0001\u0000\u0000\u0000\u0529\u0534\u0005<\u0000"+
		"\u0000\u052a\u052b\u0005B\u0000\u0000\u052b\u052c\u0003\u00ceg\u0000\u052c"+
		"\u052d\u0005C\u0000\u0000\u052d\u0534\u0001\u0000\u0000\u0000\u052e\u0531"+
		"\u00058\u0000\u0000\u052f\u0532\u0003\u00c8d\u0000\u0530\u0532\u0003\u00f4"+
		"z\u0000\u0531\u052f\u0001\u0000\u0000\u0000\u0531\u0530\u0001\u0000\u0000"+
		"\u0000\u0532\u0534\u0001\u0000\u0000\u0000\u0533\u0525\u0001\u0000\u0000"+
		"\u0000\u0533\u052a\u0001\u0000\u0000\u0000\u0533\u052e\u0001\u0000\u0000"+
		"\u0000\u0534\u00cd\u0001\u0000\u0000\u0000\u0535\u053a\u0003\u00d0h\u0000"+
		"\u0536\u0537\u0005=\u0000\u0000\u0537\u0539\u0003\u00d0h\u0000\u0538\u0536"+
		"\u0001\u0000\u0000\u0000\u0539\u053c\u0001\u0000\u0000\u0000\u053a\u0538"+
		"\u0001\u0000\u0000\u0000\u053a\u053b\u0001\u0000\u0000\u0000\u053b\u053e"+
		"\u0001\u0000\u0000\u0000\u053c\u053a\u0001\u0000\u0000\u0000\u053d\u053f"+
		"\u0005=\u0000\u0000\u053e\u053d\u0001\u0000\u0000\u0000\u053e\u053f\u0001"+
		"\u0000\u0000\u0000\u053f\u00cf\u0001\u0000\u0000\u0000\u0540\u054c\u0003"+
		"\u00aeW\u0000\u0541\u0543\u0003\u00aeW\u0000\u0542\u0541\u0001\u0000\u0000"+
		"\u0000\u0542\u0543\u0001\u0000\u0000\u0000\u0543\u0544\u0001\u0000\u0000"+
		"\u0000\u0544\u0546\u0005>\u0000\u0000\u0545\u0547\u0003\u00aeW\u0000\u0546"+
		"\u0545\u0001\u0000\u0000\u0000\u0546\u0547\u0001\u0000\u0000\u0000\u0547"+
		"\u0549\u0001\u0000\u0000\u0000\u0548\u054a\u0003\u00d2i\u0000\u0549\u0548"+
		"\u0001\u0000\u0000\u0000\u0549\u054a\u0001\u0000\u0000\u0000\u054a\u054c"+
		"\u0001\u0000\u0000\u0000\u054b\u0540\u0001\u0000\u0000\u0000\u054b\u0542"+
		"\u0001\u0000\u0000\u0000\u054c\u00d1\u0001\u0000\u0000\u0000\u054d\u054f"+
		"\u0005>\u0000\u0000\u054e\u0550\u0003\u00aeW\u0000\u054f\u054e\u0001\u0000"+
		"\u0000\u0000\u054f\u0550\u0001\u0000\u0000\u0000\u0550\u00d3\u0001\u0000"+
		"\u0000\u0000\u0551\u0554\u0003\u00c2a\u0000\u0552\u0554\u0003\u00c0`\u0000"+
		"\u0553\u0551\u0001\u0000\u0000\u0000\u0553\u0552\u0001\u0000\u0000\u0000"+
		"\u0554\u055c\u0001\u0000\u0000\u0000\u0555\u0558\u0005=\u0000\u0000\u0556"+
		"\u0559\u0003\u00c2a\u0000\u0557\u0559\u0003\u00c0`\u0000\u0558\u0556\u0001"+
		"\u0000\u0000\u0000\u0558\u0557\u0001\u0000\u0000\u0000\u0559\u055b\u0001"+
		"\u0000\u0000\u0000\u055a\u0555\u0001\u0000\u0000\u0000\u055b\u055e\u0001"+
		"\u0000\u0000\u0000\u055c\u055a\u0001\u0000\u0000\u0000\u055c\u055d\u0001"+
		"\u0000\u0000\u0000\u055d\u0560\u0001\u0000\u0000\u0000\u055e\u055c\u0001"+
		"\u0000\u0000\u0000\u055f\u0561\u0005=\u0000\u0000\u0560\u055f\u0001\u0000"+
		"\u0000\u0000\u0560\u0561\u0001\u0000\u0000\u0000\u0561\u00d5\u0001\u0000"+
		"\u0000\u0000\u0562\u0567\u0003\u00aeW\u0000\u0563\u0564\u0005=\u0000\u0000"+
		"\u0564\u0566\u0003\u00aeW\u0000\u0565\u0563\u0001\u0000\u0000\u0000\u0566"+
		"\u0569\u0001\u0000\u0000\u0000\u0567\u0565\u0001\u0000\u0000\u0000\u0567"+
		"\u0568\u0001\u0000\u0000\u0000\u0568\u056b\u0001\u0000\u0000\u0000\u0569"+
		"\u0567\u0001\u0000\u0000\u0000\u056a\u056c\u0005=\u0000\u0000\u056b\u056a"+
		"\u0001\u0000\u0000\u0000\u056b\u056c\u0001\u0000\u0000\u0000\u056c\u00d7"+
		"\u0001\u0000\u0000\u0000\u056d\u056e\u0003\u00aeW\u0000\u056e\u056f\u0005"+
		">\u0000\u0000\u056f\u0570\u0003\u00aeW\u0000\u0570\u0574\u0001\u0000\u0000"+
		"\u0000\u0571\u0572\u0005@\u0000\u0000\u0572\u0574\u0003\u00c2a\u0000\u0573"+
		"\u056d\u0001\u0000\u0000\u0000\u0573\u0571\u0001\u0000\u0000\u0000\u0574"+
		"\u0587\u0001\u0000\u0000\u0000\u0575\u0588\u0003\u00e2q\u0000\u0576\u057d"+
		"\u0005=\u0000\u0000\u0577\u0578\u0003\u00aeW\u0000\u0578\u0579\u0005>"+
		"\u0000\u0000\u0579\u057a\u0003\u00aeW\u0000\u057a\u057e\u0001\u0000\u0000"+
		"\u0000\u057b\u057c\u0005@\u0000\u0000\u057c\u057e\u0003\u00c2a\u0000\u057d"+
		"\u0577\u0001\u0000\u0000\u0000\u057d\u057b\u0001\u0000\u0000\u0000\u057e"+
		"\u0580\u0001\u0000\u0000\u0000\u057f\u0576\u0001\u0000\u0000\u0000\u0580"+
		"\u0583\u0001\u0000\u0000\u0000\u0581\u057f\u0001\u0000\u0000\u0000\u0581"+
		"\u0582\u0001\u0000\u0000\u0000\u0582\u0585\u0001\u0000\u0000\u0000\u0583"+
		"\u0581\u0001\u0000\u0000\u0000\u0584\u0586\u0005=\u0000\u0000\u0585\u0584"+
		"\u0001\u0000\u0000\u0000\u0585\u0586\u0001\u0000\u0000\u0000\u0586\u0588"+
		"\u0001\u0000\u0000\u0000\u0587\u0575\u0001\u0000\u0000\u0000\u0587\u0581"+
		"\u0001\u0000\u0000\u0000\u0588\u059e\u0001\u0000\u0000\u0000\u0589\u058c"+
		"\u0003\u00aeW\u0000\u058a\u058c\u0003\u00c0`\u0000\u058b\u0589\u0001\u0000"+
		"\u0000\u0000\u058b\u058a\u0001\u0000\u0000\u0000\u058c\u059b\u0001\u0000"+
		"\u0000\u0000\u058d\u059c\u0003\u00e2q\u0000\u058e\u0591\u0005=\u0000\u0000"+
		"\u058f\u0592\u0003\u00aeW\u0000\u0590\u0592\u0003\u00c0`\u0000\u0591\u058f"+
		"\u0001\u0000\u0000\u0000\u0591\u0590\u0001\u0000\u0000\u0000\u0592\u0594"+
		"\u0001\u0000\u0000\u0000\u0593\u058e\u0001\u0000\u0000\u0000\u0594\u0597"+
		"\u0001\u0000\u0000\u0000\u0595\u0593\u0001\u0000\u0000\u0000\u0595\u0596"+
		"\u0001\u0000\u0000\u0000\u0596\u0599\u0001\u0000\u0000\u0000\u0597\u0595"+
		"\u0001\u0000\u0000\u0000\u0598\u059a\u0005=\u0000\u0000\u0599\u0598\u0001"+
		"\u0000\u0000\u0000\u0599\u059a\u0001\u0000\u0000\u0000\u059a\u059c\u0001"+
		"\u0000\u0000\u0000\u059b\u058d\u0001\u0000\u0000\u0000\u059b\u0595\u0001"+
		"\u0000\u0000\u0000\u059c\u059e\u0001\u0000\u0000\u0000\u059d\u0573\u0001"+
		"\u0000\u0000\u0000\u059d\u058b\u0001\u0000\u0000\u0000\u059e\u00d9\u0001"+
		"\u0000\u0000\u0000\u059f\u05a3\u0005\r\u0000\u0000\u05a0\u05a4\u0003\u00c8"+
		"d\u0000\u05a1\u05a4\u0003\u00f6{\u0000\u05a2\u05a4\u0003\u00fc~\u0000"+
		"\u05a3\u05a0\u0001\u0000\u0000\u0000\u05a3\u05a1\u0001\u0000\u0000\u0000"+
		"\u05a3\u05a2\u0001\u0000\u0000\u0000\u05a4\u05aa\u0001\u0000\u0000\u0000"+
		"\u05a5\u05a7\u0005;\u0000\u0000\u05a6\u05a8\u0003\u00dcn\u0000\u05a7\u05a6"+
		"\u0001\u0000\u0000\u0000\u05a7\u05a8\u0001\u0000\u0000\u0000\u05a8\u05a9"+
		"\u0001\u0000\u0000\u0000\u05a9\u05ab\u0005<\u0000\u0000\u05aa\u05a5\u0001"+
		"\u0000\u0000\u0000\u05aa\u05ab\u0001\u0000\u0000\u0000\u05ab\u05ac\u0001"+
		"\u0000\u0000\u0000\u05ac\u05ad\u0005>\u0000\u0000\u05ad\u05ae\u0003`0"+
		"\u0000\u05ae\u00db\u0001\u0000\u0000\u0000\u05af\u05b3\u0003\u0106\u0083"+
		"\u0000\u05b0\u05b3\u0003\u00f8|\u0000\u05b1\u05b3\u0003\u00deo\u0000\u05b2"+
		"\u05af\u0001\u0000\u0000\u0000\u05b2\u05b0\u0001\u0000\u0000\u0000\u05b2"+
		"\u05b1\u0001\u0000\u0000\u0000\u05b3\u05bc\u0001\u0000\u0000\u0000\u05b4"+
		"\u05b8\u0005=\u0000\u0000\u05b5\u05b9\u0003\u0106\u0083\u0000\u05b6\u05b9"+
		"\u0003\u00f8|\u0000\u05b7\u05b9\u0003\u00deo\u0000\u05b8\u05b5\u0001\u0000"+
		"\u0000\u0000\u05b8\u05b6\u0001\u0000\u0000\u0000\u05b8\u05b7\u0001\u0000"+
		"\u0000\u0000\u05b9\u05bb\u0001\u0000\u0000\u0000\u05ba\u05b4\u0001\u0000"+
		"\u0000\u0000\u05bb\u05be\u0001\u0000\u0000\u0000\u05bc\u05ba\u0001\u0000"+
		"\u0000\u0000\u05bc\u05bd\u0001\u0000\u0000\u0000\u05bd\u05c0\u0001\u0000"+
		"\u0000\u0000\u05be\u05bc\u0001\u0000\u0000\u0000\u05bf\u05c1\u0005=\u0000"+
		"\u0000\u05c0\u05bf\u0001\u0000\u0000\u0000\u05c0\u05c1\u0001\u0000\u0000"+
		"\u0000\u05c1\u00dd\u0001\u0000\u0000\u0000\u05c2\u05d0\u0003\u00fc~\u0000"+
		"\u05c3\u05c5\u0003\u00aeW\u0000\u05c4\u05c6\u0003\u00e2q\u0000\u05c5\u05c4"+
		"\u0001\u0000\u0000\u0000\u05c5\u05c6\u0001\u0000\u0000\u0000\u05c6\u05d0"+
		"\u0001\u0000\u0000\u0000\u05c7\u05c8\u0003\u00aeW\u0000\u05c8\u05c9\u0005"+
		"A\u0000\u0000\u05c9\u05ca\u0003\u00aeW\u0000\u05ca\u05d0\u0001\u0000\u0000"+
		"\u0000\u05cb\u05cc\u0005@\u0000\u0000\u05cc\u05d0\u0003\u00aeW\u0000\u05cd"+
		"\u05ce\u0005:\u0000\u0000\u05ce\u05d0\u0003\u00aeW\u0000\u05cf\u05c2\u0001"+
		"\u0000\u0000\u0000\u05cf\u05c3\u0001\u0000\u0000\u0000\u05cf\u05c7\u0001"+
		"\u0000\u0000\u0000\u05cf\u05cb\u0001\u0000\u0000\u0000\u05cf\u05cd\u0001"+
		"\u0000\u0000\u0000\u05d0\u00df\u0001\u0000\u0000\u0000\u05d1\u05d4\u0003"+
		"\u00e2q\u0000\u05d2\u05d4\u0003\u00e4r\u0000\u05d3\u05d1\u0001\u0000\u0000"+
		"\u0000\u05d3\u05d2\u0001\u0000\u0000\u0000\u05d4\u00e1\u0001\u0000\u0000"+
		"\u0000\u05d5\u05d7\u0005\t\u0000\u0000\u05d6\u05d5\u0001\u0000\u0000\u0000"+
		"\u05d6\u05d7\u0001\u0000\u0000\u0000\u05d7\u05d8\u0001\u0000\u0000\u0000"+
		"\u05d8\u05d9\u0005\u0016\u0000\u0000\u05d9\u05da\u0003\u00d4j\u0000\u05da"+
		"\u05db\u0005\u001b\u0000\u0000\u05db\u05dd\u0003\u00b6[\u0000\u05dc\u05de"+
		"\u0003\u00e0p\u0000\u05dd\u05dc\u0001\u0000\u0000\u0000\u05dd\u05de\u0001"+
		"\u0000\u0000\u0000\u05de\u00e3\u0001\u0000\u0000\u0000\u05df\u05e0\u0005"+
		"\u0019\u0000\u0000\u05e0\u05e2\u0003\u00b0X\u0000\u05e1\u05e3\u0003\u00e0"+
		"p\u0000\u05e2\u05e1\u0001\u0000\u0000\u0000\u05e2\u05e3\u0001\u0000\u0000"+
		"\u0000\u05e3\u00e5\u0001\u0000\u0000\u0000\u05e4\u05e5\u0003\u00c8d\u0000"+
		"\u05e5\u00e7\u0001\u0000\u0000\u0000\u05e6\u05e8\u0005+\u0000\u0000\u05e7"+
		"\u05e9\u0003\u00eau\u0000\u05e8\u05e7\u0001\u0000\u0000\u0000\u05e8\u05e9"+
		"\u0001\u0000\u0000\u0000\u05e9\u00e9\u0001\u0000\u0000\u0000\u05ea\u05eb"+
		"\u0005\u0017\u0000\u0000\u05eb\u05ee\u0003\u00aeW\u0000\u05ec\u05ee\u0003"+
		"\u00d6k\u0000\u05ed\u05ea\u0001\u0000\u0000\u0000\u05ed\u05ec\u0001\u0000"+
		"\u0000\u0000\u05ee\u00eb\u0001\u0000\u0000\u0000\u05ef\u05f1\u0005\u0003"+
		"\u0000\u0000\u05f0\u05ef\u0001\u0000\u0000\u0000\u05f1\u05f2\u0001\u0000"+
		"\u0000\u0000\u05f2\u05f0\u0001\u0000\u0000\u0000\u05f2\u05f3\u0001\u0000"+
		"\u0000\u0000\u05f3\u00ed\u0001\u0000\u0000\u0000\u05f4\u05f5\u0005O\u0000"+
		"\u0000\u05f5\u05f9\u0005\u0004\u0000\u0000\u05f6\u05fa\u0005=\u0000\u0000"+
		"\u05f7\u05f8\u0005=\u0000\u0000\u05f8\u05fa\u0005\u0004\u0000\u0000\u05f9"+
		"\u05f6\u0001\u0000\u0000\u0000\u05f9\u05f7\u0001\u0000\u0000\u0000\u05f9"+
		"\u05fa\u0001\u0000\u0000\u0000\u05fa\u05fb\u0001\u0000\u0000\u0000\u05fb"+
		"\u05fc\u0005P\u0000\u0000\u05fc\u00ef\u0001\u0000\u0000\u0000\u05fd\u0603"+
		"\u0003\u00fa}\u0000\u05fe\u0603\u0003\u00fe\u007f\u0000\u05ff\u0603\u0003"+
		"\u00f6{\u0000\u0600\u0603\u0003\u00f8|\u0000\u0601\u0603\u0003\u00fc~"+
		"\u0000\u0602\u05fd\u0001\u0000\u0000\u0000\u0602\u05fe\u0001\u0000\u0000"+
		"\u0000\u0602\u05ff\u0001\u0000\u0000\u0000\u0602\u0600\u0001\u0000\u0000"+
		"\u0000\u0602\u0601\u0001\u0000\u0000\u0000\u0603\u00f1\u0001\u0000\u0000"+
		"\u0000\u0604\u0609\u0003\u0108\u0084\u0000\u0605\u0609\u0003\u00fc~\u0000"+
		"\u0606\u0609\u0003\u00fe\u007f\u0000\u0607\u0609\u0003\u00f6{\u0000\u0608"+
		"\u0604\u0001\u0000\u0000\u0000\u0608\u0605\u0001\u0000\u0000\u0000\u0608"+
		"\u0606\u0001\u0000\u0000\u0000\u0608\u0607\u0001\u0000\u0000\u0000\u0609"+
		"\u00f3\u0001\u0000\u0000\u0000\u060a\u060d\u0003\u00f6{\u0000\u060b\u060d"+
		"\u0003\u00fc~\u0000\u060c\u060a\u0001\u0000\u0000\u0000\u060c\u060b\u0001"+
		"\u0000\u0000\u0000\u060d\u00f5\u0001\u0000\u0000\u0000\u060e\u060f\u0005"+
		"g\u0000\u0000\u060f\u00f7\u0001\u0000\u0000\u0000\u0610\u0611\u0005g\u0000"+
		"\u0000\u0611\u0612\u0003\u00eew\u0000\u0612\u00f9\u0001\u0000\u0000\u0000"+
		"\u0613\u0614\u0005g\u0000\u0000\u0614\u0615\u0005:\u0000\u0000\u0615\u00fb"+
		"\u0001\u0000\u0000\u0000\u0616\u0617\u0005g\u0000\u0000\u0617\u0618\u0005"+
		"/\u0000\u0000\u0618\u00fd\u0001\u0000\u0000\u0000\u0619\u061a\u0005g\u0000"+
		"\u0000\u061a\u061d\u0005Q\u0000\u0000\u061b\u061e\u0003\u00f2y\u0000\u061c"+
		"\u061e\u0003 \u0010\u0000\u061d\u061b\u0001\u0000\u0000\u0000\u061d\u061c"+
		"\u0001\u0000\u0000\u0000\u061e\u061f\u0001\u0000\u0000\u0000\u061f\u0620"+
		"\u0005R\u0000\u0000\u0620\u00ff\u0001\u0000\u0000\u0000\u0621\u0624\u0003"+
		"\u0102\u0081\u0000\u0622\u0624\u0003\u0104\u0082\u0000\u0623\u0621\u0001"+
		"\u0000\u0000\u0000\u0623\u0622\u0001\u0000\u0000\u0000\u0624\u0101\u0001"+
		"\u0000\u0000\u0000\u0625\u0626\u0005g\u0000\u0000\u0626\u0628\u0005>\u0000"+
		"\u0000\u0627\u0629\u0003\u00eew\u0000\u0628\u0627\u0001\u0000\u0000\u0000"+
		"\u0628\u0629\u0001\u0000\u0000\u0000\u0629\u062a\u0001\u0000\u0000\u0000"+
		"\u062a\u062b\u0003`0\u0000\u062b\u0103\u0001\u0000\u0000\u0000\u062c\u0631"+
		"\u0005g\u0000\u0000\u062d\u062e\u0005>\u0000\u0000\u062e\u0632\u0005:"+
		"\u0000\u0000\u062f\u0630\u0005:\u0000\u0000\u0630\u0632\u0005>\u0000\u0000"+
		"\u0631\u062d\u0001\u0000\u0000\u0000\u0631\u062f\u0001\u0000\u0000\u0000"+
		"\u0632\u0633\u0001\u0000\u0000\u0000\u0633\u0634\u0003`0\u0000\u0634\u0105"+
		"\u0001\u0000\u0000\u0000\u0635\u0636\u0005g\u0000\u0000\u0636\u0637\u0005"+
		":\u0000\u0000\u0637\u0107\u0001\u0000\u0000\u0000\u0638\u0639\u0005g\u0000"+
		"\u0000\u0639\u063a\u0005i\u0000\u0000\u063a\u063b\u0005/\u0000\u0000\u063b"+
		"\u063d\u0005;\u0000\u0000\u063c\u063e\u0003\u0110\u0088\u0000\u063d\u063c"+
		"\u0001\u0000\u0000\u0000\u063d\u063e\u0001\u0000\u0000\u0000\u063e\u063f"+
		"\u0001\u0000\u0000\u0000\u063f\u0642\u0005<\u0000\u0000\u0640\u0641\u0005"+
		">\u0000\u0000\u0641\u0643\u0003`0\u0000\u0642\u0640\u0001\u0000\u0000"+
		"\u0000\u0642\u0643\u0001\u0000\u0000\u0000\u0643\u0109\u0001\u0000\u0000"+
		"\u0000\u0644\u0646\u0003\u010c\u0086\u0000\u0645\u0647\u0003\u0118\u008c"+
		"\u0000\u0646\u0645\u0001\u0000\u0000\u0000\u0647\u0648\u0001\u0000\u0000"+
		"\u0000\u0648\u0646\u0001\u0000\u0000\u0000\u0648\u0649\u0001\u0000\u0000"+
		"\u0000\u0649\u010b\u0001\u0000\u0000\u0000\u064a\u064b\u0003\u010e\u0087"+
		"\u0000\u064b\u010d\u0001\u0000\u0000\u0000\u064c\u0651\u0003\u0116\u008b"+
		"\u0000\u064d\u064e\u0003\u0114\u008a\u0000\u064e\u064f\u0005.\u0000\u0000"+
		"\u064f\u0651\u0001\u0000\u0000\u0000\u0650\u064c\u0001\u0000\u0000\u0000"+
		"\u0650\u064d\u0001\u0000\u0000\u0000\u0651\u010f\u0001\u0000\u0000\u0000"+
		"\u0652\u0657\u0003\u0112\u0089\u0000\u0653\u0654\u0005=\u0000\u0000\u0654"+
		"\u0656\u0003\u0112\u0089\u0000\u0655\u0653\u0001\u0000\u0000\u0000\u0656"+
		"\u0659\u0001\u0000\u0000\u0000\u0657\u0655\u0001\u0000\u0000\u0000\u0657"+
		"\u0658\u0001\u0000\u0000\u0000\u0658\u0111\u0001\u0000\u0000\u0000\u0659"+
		"\u0657\u0001\u0000\u0000\u0000\u065a\u065d\u0003\u00f4z\u0000\u065b\u065d"+
		"\u0003\u00c6c\u0000\u065c\u065a\u0001\u0000\u0000\u0000\u065c\u065b\u0001"+
		"\u0000\u0000\u0000\u065d\u0660\u0001\u0000\u0000\u0000\u065e\u065f\u0005"+
		"A\u0000\u0000\u065f\u0661\u0003\u00aeW\u0000\u0660\u065e\u0001\u0000\u0000"+
		"\u0000\u0660\u0661\u0001\u0000\u0000\u0000\u0661\u0113\u0001\u0000\u0000"+
		"\u0000\u0662\u0663\u0005i\u0000\u0000\u0663\u0664\u0007\u0007\u0000\u0000"+
		"\u0664\u0665\u0005/\u0000\u0000\u0665\u0667\u0005;\u0000\u0000\u0666\u0668"+
		"\u0003\u0110\u0088\u0000\u0667\u0666\u0001\u0000\u0000\u0000\u0667\u0668"+
		"\u0001\u0000\u0000\u0000\u0668\u0669\u0001\u0000\u0000\u0000\u0669\u066a"+
		"\u0005<\u0000\u0000\u066a\u0115\u0001\u0000\u0000\u0000\u066b\u066c\u0003"+
		"\u0114\u008a\u0000\u066c\u066d\u0005>\u0000\u0000\u066d\u066e\u0003`0"+
		"\u0000\u066e\u0117\u0001\u0000\u0000\u0000\u066f\u0670\u0005h\u0000\u0000"+
		"\u0670\u0671\u0005/\u0000\u0000\u0671\u0672\u0005.\u0000\u0000\u0672\u0673"+
		"\u0003\u001a\r\u0000\u0673\u0119\u0001\u0000\u0000\u0000\u00e0\u011f\u0123"+
		"\u0125\u012e\u0136\u013e\u0141\u0148\u014e\u0157\u015c\u0165\u016b\u016f"+
		"\u0175\u017b\u017f\u0186\u0188\u018a\u018f\u0191\u0193\u0197\u019d\u01a1"+
		"\u01a8\u01aa\u01ac\u01b1\u01b3\u01ba\u01bf\u01c4\u01ca\u01ce\u01d4\u01da"+
		"\u01de\u01e5\u01e7\u01e9\u01ee\u01f0\u01f2\u01f6\u01fc\u0200\u0207\u0209"+
		"\u020b\u0210\u0212\u0218\u0222\u0229\u022d\u023a\u0241\u0246\u024a\u024d"+
		"\u0253\u0257\u025c\u0260\u0264\u0272\u027a\u0282\u0284\u0288\u0291\u0298"+
		"\u029a\u02a3\u02a8\u02ad\u02b4\u02b8\u02bf\u02c7\u02d0\u02d9\u02e0\u02ed"+
		"\u02f3\u0300\u0306\u030f\u031a\u0325\u032a\u032f\u0334\u033c\u0345\u034b"+
		"\u034d\u0355\u0359\u0363\u036a\u036d\u0373\u0376\u037b\u0380\u038a\u038e"+
		"\u0399\u03a4\u03ae\u03b8\u03c2\u03c7\u03cc\u03e1\u03e5\u03ed\u03f2\u03f5"+
		"\u03fa\u0401\u0405\u0409\u040f\u0416\u041f\u0426\u042a\u0431\u0436\u0446"+
		"\u044e\u0458\u045c\u0463\u046b\u0478\u047b\u047f\u0483\u048a\u0494\u049c"+
		"\u04a2\u04aa\u04ba\u04c6\u04c9\u04e0\u04e2\u04e6\u04ec\u04f2\u04f7\u04fc"+
		"\u0505\u050b\u0512\u0519\u051d\u0521\u0523\u0527\u0531\u0533\u053a\u053e"+
		"\u0542\u0546\u0549\u054b\u054f\u0553\u0558\u055c\u0560\u0567\u056b\u0573"+
		"\u057d\u0581\u0585\u0587\u058b\u0591\u0595\u0599\u059b\u059d\u05a3\u05a7"+
		"\u05aa\u05b2\u05b8\u05bc\u05c0\u05c5\u05cf\u05d3\u05d6\u05dd\u05e2\u05e8"+
		"\u05ed\u05f2\u05f9\u0602\u0608\u060c\u061d\u0623\u0628\u0631\u063d\u0642"+
		"\u0648\u0650\u0657\u065c\u0660\u0667";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}