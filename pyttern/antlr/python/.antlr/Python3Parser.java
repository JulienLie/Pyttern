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
		WILDCARD=103, BALISE=104, SUB_PATTERN=105, NOT_WILDCARD=106, SKIP_=107, 
		UNKNOWN_CHAR=108;
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
		RULE_macro_stmts = 133, RULE_macro = 134, RULE_macro_args = 135, RULE_macro_arg = 136, 
		RULE_simple_macro = 137, RULE_compound_macro = 138, RULE_transformation = 139;
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
			"macro", "macro_args", "macro_arg", "simple_macro", "compound_macro", 
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
			"'$#'", "'$'", "'!'"
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
			"WILDCARD", "BALISE", "SUB_PATTERN", "NOT_WILDCARD", "SKIP_", "UNKNOWN_CHAR"
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
			setState(285);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(280);
				match(NEWLINE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				simple_stmts();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(282);
				compound_stmt();
				setState(283);
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
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1009034997364027160L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137443160449L) != 0)) {
				{
				setState(289);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NEWLINE:
					{
					setState(287);
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
					setState(288);
					stmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(294);
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
			setState(296);
			testlist();
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(297);
				match(NEWLINE);
				}
				}
				setState(302);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(303);
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
		public List<TerminalNode> NEWLINE() { return getTokens(Python3Parser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Python3Parser.NEWLINE, i);
		}
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
			setState(307); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(307);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NEWLINE:
					{
					setState(305);
					match(NEWLINE);
					}
					break;
				case SUB_PATTERN:
					{
					setState(306);
					macro_stmts();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(309); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE || _la==SUB_PATTERN );
			setState(311);
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
			setState(313);
			match(AT);
			setState(314);
			dotted_name();
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_PAREN) {
				{
				setState(315);
				match(OPEN_PAREN);
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 549755864581L) != 0)) {
					{
					setState(316);
					arglist();
					}
				}

				setState(319);
				match(CLOSE_PAREN);
				}
			}

			setState(322);
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
			setState(325); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(324);
				decorator();
				}
				}
				setState(327); 
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
			setState(329);
			decorators();
			setState(333);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLASS:
				{
				setState(330);
				classdef();
				}
				break;
			case DEF:
				{
				setState(331);
				funcdef();
				}
				break;
			case ASYNC:
				{
				setState(332);
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
			setState(335);
			match(ASYNC);
			setState(336);
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
			setState(338);
			match(DEF);
			setState(342);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(339);
				name();
				}
				break;
			case 2:
				{
				setState(340);
				simple_wildcard();
				}
				break;
			case 3:
				{
				setState(341);
				var_wildcard();
				}
				break;
			}
			setState(344);
			parameters();
			setState(347);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(345);
				match(ARROW);
				setState(346);
				test();
				}
			}

			setState(349);
			match(COLON);
			setState(350);
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
			setState(352);
			match(OPEN_PAREN);
			setState(434);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCH:
			case UNDERSCORE:
			case NAME:
			case WILDCARD:
				{
				setState(353);
				tfpdef();
				setState(356);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(354);
					match(ASSIGN);
					setState(355);
					test();
					}
				}

				setState(366);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(358);
						match(COMMA);
						setState(359);
						tfpdef();
						setState(362);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(360);
							match(ASSIGN);
							setState(361);
							test();
							}
						}

						}
						} 
					}
					setState(368);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				setState(402);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(369);
					match(COMMA);
					setState(400);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case STAR:
						{
						setState(370);
						match(STAR);
						setState(372);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141838073724928L) != 0) || _la==WILDCARD) {
							{
							setState(371);
							tfpdef();
							}
						}

						setState(382);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(374);
								match(COMMA);
								setState(375);
								tfpdef();
								setState(378);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==ASSIGN) {
									{
									setState(376);
									match(ASSIGN);
									setState(377);
									test();
									}
								}

								}
								} 
							}
							setState(384);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
						}
						setState(393);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(385);
							match(COMMA);
							setState(391);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==POWER) {
								{
								setState(386);
								match(POWER);
								setState(387);
								tfpdef();
								setState(389);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==COMMA) {
									{
									setState(388);
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
						setState(395);
						match(POWER);
						setState(396);
						tfpdef();
						setState(398);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(397);
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
				setState(404);
				match(STAR);
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141838073724928L) != 0) || _la==WILDCARD) {
					{
					setState(405);
					tfpdef();
					}
				}

				setState(416);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(408);
						match(COMMA);
						setState(409);
						tfpdef();
						setState(412);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(410);
							match(ASSIGN);
							setState(411);
							test();
							}
						}

						}
						} 
					}
					setState(418);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				setState(427);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(419);
					match(COMMA);
					setState(425);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==POWER) {
						{
						setState(420);
						match(POWER);
						setState(421);
						tfpdef();
						setState(423);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(422);
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
				setState(429);
				match(POWER);
				setState(430);
				tfpdef();
				setState(432);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(431);
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
			setState(436);
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
			setState(446);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(438);
				name();
				setState(441);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(439);
					match(COLON);
					setState(440);
					test();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(443);
				expr_wildcard();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(444);
				number_wildcard();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(445);
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
			setState(529);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCH:
			case UNDERSCORE:
			case NAME:
			case WILDCARD:
				{
				setState(448);
				vfpdef();
				setState(451);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(449);
					match(ASSIGN);
					setState(450);
					test();
					}
				}

				setState(461);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(453);
						match(COMMA);
						setState(454);
						vfpdef();
						setState(457);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(455);
							match(ASSIGN);
							setState(456);
							test();
							}
						}

						}
						} 
					}
					setState(463);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				}
				setState(497);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(464);
					match(COMMA);
					setState(495);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case STAR:
						{
						setState(465);
						match(STAR);
						setState(467);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141838073724928L) != 0) || _la==WILDCARD) {
							{
							setState(466);
							vfpdef();
							}
						}

						setState(477);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(469);
								match(COMMA);
								setState(470);
								vfpdef();
								setState(473);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==ASSIGN) {
									{
									setState(471);
									match(ASSIGN);
									setState(472);
									test();
									}
								}

								}
								} 
							}
							setState(479);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
						}
						setState(488);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(480);
							match(COMMA);
							setState(486);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==POWER) {
								{
								setState(481);
								match(POWER);
								setState(482);
								vfpdef();
								setState(484);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==COMMA) {
									{
									setState(483);
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
						setState(490);
						match(POWER);
						setState(491);
						vfpdef();
						setState(493);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(492);
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
				setState(499);
				match(STAR);
				setState(501);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141838073724928L) != 0) || _la==WILDCARD) {
					{
					setState(500);
					vfpdef();
					}
				}

				setState(511);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(503);
						match(COMMA);
						setState(504);
						vfpdef();
						setState(507);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(505);
							match(ASSIGN);
							setState(506);
							test();
							}
						}

						}
						} 
					}
					setState(513);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				}
				setState(522);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(514);
					match(COMMA);
					setState(520);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==POWER) {
						{
						setState(515);
						match(POWER);
						setState(516);
						vfpdef();
						setState(518);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(517);
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
				setState(524);
				match(POWER);
				setState(525);
				vfpdef();
				setState(527);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(526);
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
			setState(535);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(531);
				name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(532);
				expr_wildcard();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(533);
				number_wildcard();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(534);
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
			setState(545);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(537);
				macro_call();
				setState(538);
				match(NEWLINE);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(540);
				contains_wildcard();
				setState(541);
				match(NEWLINE);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(543);
				simple_stmts();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(544);
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
			setState(547);
			simple_stmt();
			setState(552);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(548);
					match(SEMI_COLON);
					setState(549);
					simple_stmt();
					}
					} 
				}
				setState(554);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			}
			setState(556);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI_COLON) {
				{
				setState(555);
				match(SEMI_COLON);
				}
			}

			setState(558);
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
			setState(569);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				setState(560);
				stmt_wildcard();
				}
				break;
			case 2:
				{
				setState(561);
				expr_stmt();
				}
				break;
			case 3:
				{
				setState(562);
				del_stmt();
				}
				break;
			case 4:
				{
				setState(563);
				pass_stmt();
				}
				break;
			case 5:
				{
				setState(564);
				flow_stmt();
				}
				break;
			case 6:
				{
				setState(565);
				import_stmt();
				}
				break;
			case 7:
				{
				setState(566);
				global_stmt();
				}
				break;
			case 8:
				{
				setState(567);
				nonlocal_stmt();
				}
				break;
			case 9:
				{
				setState(568);
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
			setState(571);
			testlist_star_expr();
			setState(588);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(572);
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
				setState(573);
				augassign();
				setState(576);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case YIELD:
					{
					setState(574);
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
					setState(575);
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
				setState(585);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ASSIGN) {
					{
					{
					setState(578);
					match(ASSIGN);
					setState(581);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case YIELD:
						{
						setState(579);
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
						setState(580);
						testlist_star_expr();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(587);
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
			setState(590);
			match(COLON);
			setState(591);
			test();
			setState(594);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(592);
				match(ASSIGN);
				setState(593);
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
			setState(598);
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
				setState(596);
				test();
				}
				break;
			case STAR:
				{
				setState(597);
				star_expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(607);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(600);
					match(COMMA);
					setState(603);
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
						setState(601);
						test();
						}
						break;
					case STAR:
						{
						setState(602);
						star_expr();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(609);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			}
			setState(611);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(610);
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
			setState(613);
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
			setState(615);
			match(DEL);
			setState(616);
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
			setState(618);
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
			setState(625);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BREAK:
				enterOuterAlt(_localctx, 1);
				{
				setState(620);
				break_stmt();
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(621);
				continue_stmt();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 3);
				{
				setState(622);
				return_stmt();
				}
				break;
			case RAISE:
				enterOuterAlt(_localctx, 4);
				{
				setState(623);
				raise_stmt();
				}
				break;
			case YIELD:
				enterOuterAlt(_localctx, 5);
				{
				setState(624);
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
			setState(627);
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
			setState(629);
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
			setState(631);
			match(RETURN);
			setState(633);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(632);
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
			setState(635);
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
			setState(637);
			match(RAISE);
			setState(643);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(638);
				test();
				setState(641);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FROM) {
					{
					setState(639);
					match(FROM);
					setState(640);
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
			setState(647);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IMPORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(645);
				import_name();
				}
				break;
			case FROM:
				enterOuterAlt(_localctx, 2);
				{
				setState(646);
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
			setState(649);
			match(IMPORT);
			setState(650);
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
			setState(652);
			match(FROM);
			setState(665);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				{
				setState(656);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==ELLIPSIS) {
					{
					{
					setState(653);
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
					setState(658);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(659);
				dotted_name();
				}
				break;
			case 2:
				{
				setState(661); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(660);
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
					setState(663); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DOT || _la==ELLIPSIS );
				}
				break;
			}
			setState(667);
			match(IMPORT);
			setState(674);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				{
				setState(668);
				match(STAR);
				}
				break;
			case OPEN_PAREN:
				{
				setState(669);
				match(OPEN_PAREN);
				setState(670);
				import_as_names();
				setState(671);
				match(CLOSE_PAREN);
				}
				break;
			case MATCH:
			case UNDERSCORE:
			case NAME:
				{
				setState(673);
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
			setState(676);
			name();
			setState(679);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(677);
				match(AS);
				setState(678);
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
			setState(681);
			dotted_name();
			setState(684);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(682);
				match(AS);
				setState(683);
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
			setState(686);
			import_as_name();
			setState(691);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(687);
					match(COMMA);
					setState(688);
					import_as_name();
					}
					} 
				}
				setState(693);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			}
			setState(695);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(694);
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
			setState(697);
			dotted_as_name();
			setState(702);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(698);
				match(COMMA);
				setState(699);
				dotted_as_name();
				}
				}
				setState(704);
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
			setState(705);
			name();
			setState(710);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(706);
				match(DOT);
				setState(707);
				name();
				}
				}
				setState(712);
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
			setState(713);
			match(GLOBAL);
			setState(714);
			name();
			setState(719);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(715);
				match(COMMA);
				setState(716);
				name();
				}
				}
				setState(721);
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
			setState(722);
			match(NONLOCAL);
			setState(723);
			name();
			setState(728);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(724);
				match(COMMA);
				setState(725);
				name();
				}
				}
				setState(730);
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
			setState(731);
			match(ASSERT);
			setState(732);
			test();
			setState(735);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(733);
				match(COMMA);
				setState(734);
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
			setState(748);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				enterOuterAlt(_localctx, 1);
				{
				setState(737);
				compound_wildcard();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(738);
				if_stmt();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(739);
				while_stmt();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(740);
				for_stmt();
				}
				break;
			case TRY:
				enterOuterAlt(_localctx, 5);
				{
				setState(741);
				try_stmt();
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 6);
				{
				setState(742);
				with_stmt();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 7);
				{
				setState(743);
				funcdef();
				}
				break;
			case CLASS:
				enterOuterAlt(_localctx, 8);
				{
				setState(744);
				classdef();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 9);
				{
				setState(745);
				decorated();
				}
				break;
			case ASYNC:
				enterOuterAlt(_localctx, 10);
				{
				setState(746);
				async_stmt();
				}
				break;
			case MATCH:
				enterOuterAlt(_localctx, 11);
				{
				setState(747);
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
			setState(750);
			match(ASYNC);
			setState(754);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEF:
				{
				setState(751);
				funcdef();
				}
				break;
			case WITH:
				{
				setState(752);
				with_stmt();
				}
				break;
			case FOR:
				{
				setState(753);
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
			setState(756);
			match(IF);
			setState(757);
			test();
			setState(758);
			match(COLON);
			setState(759);
			block();
			setState(767);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(760);
				match(ELIF);
				setState(761);
				test();
				setState(762);
				match(COLON);
				setState(763);
				block();
				}
				}
				setState(769);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(773);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(770);
				match(ELSE);
				setState(771);
				match(COLON);
				setState(772);
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
			setState(775);
			match(WHILE);
			setState(776);
			test();
			setState(777);
			match(COLON);
			setState(778);
			block();
			setState(782);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(779);
				match(ELSE);
				setState(780);
				match(COLON);
				setState(781);
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
			setState(784);
			match(FOR);
			setState(785);
			exprlist();
			setState(786);
			match(IN);
			setState(787);
			testlist();
			setState(788);
			match(COLON);
			setState(789);
			block();
			setState(793);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(790);
				match(ELSE);
				setState(791);
				match(COLON);
				setState(792);
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
			setState(795);
			match(TRY);
			setState(796);
			match(COLON);
			setState(797);
			block();
			setState(819);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXCEPT:
				{
				setState(802); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(798);
					except_clause();
					setState(799);
					match(COLON);
					setState(800);
					block();
					}
					}
					setState(804); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==EXCEPT );
				setState(809);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(806);
					match(ELSE);
					setState(807);
					match(COLON);
					setState(808);
					block();
					}
				}

				setState(814);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(811);
					match(FINALLY);
					setState(812);
					match(COLON);
					setState(813);
					block();
					}
				}

				}
				break;
			case FINALLY:
				{
				setState(816);
				match(FINALLY);
				setState(817);
				match(COLON);
				setState(818);
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
			setState(821);
			match(WITH);
			setState(822);
			with_item();
			setState(827);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(823);
				match(COMMA);
				setState(824);
				with_item();
				}
				}
				setState(829);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(830);
			match(COLON);
			setState(831);
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
			setState(833);
			test();
			setState(836);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(834);
				match(AS);
				setState(835);
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
			setState(838);
			match(EXCEPT);
			setState(844);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(839);
				test();
				setState(842);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(840);
					match(AS);
					setState(841);
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
			setState(856);
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
				setState(846);
				simple_stmts();
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(847);
				match(NEWLINE);
				setState(848);
				match(INDENT);
				setState(850); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(849);
					stmt();
					}
					}
					setState(852); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1008964628619849496L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137443160449L) != 0) );
				setState(854);
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
			setState(858);
			match(MATCH);
			setState(859);
			subject_expr();
			setState(860);
			match(COLON);
			setState(861);
			match(NEWLINE);
			setState(862);
			match(INDENT);
			setState(864); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(863);
				case_block();
				}
				}
				setState(866); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASE );
			setState(868);
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
			setState(876);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(870);
				star_named_expression();
				setState(871);
				match(COMMA);
				setState(873);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(872);
					star_named_expressions();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(875);
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
			setState(878);
			match(COMMA);
			setState(880); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(879);
				star_named_expression();
				}
				}
				setState(882); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0) );
			setState(885);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(884);
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
			setState(890);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(887);
				match(STAR);
				setState(888);
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
				setState(889);
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
			setState(892);
			match(CASE);
			setState(893);
			patterns();
			setState(895);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(894);
				guard();
				}
			}

			setState(897);
			match(COLON);
			setState(898);
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
			setState(900);
			match(IF);
			setState(901);
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
			setState(905);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(903);
				open_sequence_pattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(904);
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
			setState(909);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(907);
				as_pattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(908);
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
			setState(911);
			or_pattern();
			setState(912);
			match(AS);
			setState(913);
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
			setState(915);
			closed_pattern();
			setState(920);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_OP) {
				{
				{
				setState(916);
				match(OR_OP);
				setState(917);
				closed_pattern();
				}
				}
				setState(922);
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
			setState(931);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(923);
				wildcard_pattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(924);
				literal_pattern();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(925);
				capture_pattern();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(926);
				value_pattern();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(927);
				group_pattern();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(928);
				sequence_pattern();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(929);
				mapping_pattern();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(930);
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
			setState(941);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(933);
				signed_number();
				setState(934);
				if (!( self.CannotBePlusMinus() )) throw new FailedPredicateException(this, " self.CannotBePlusMinus() ");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(936);
				complex_number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(937);
				strings();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(938);
				match(NONE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(939);
				match(TRUE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(940);
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
			setState(951);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(943);
				signed_number();
				setState(944);
				if (!( self.CannotBePlusMinus() )) throw new FailedPredicateException(this, " self.CannotBePlusMinus() ");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(946);
				complex_number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(947);
				strings();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(948);
				match(NONE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(949);
				match(TRUE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(950);
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
			setState(961);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(953);
				signed_real_number();
				setState(954);
				match(ADD);
				setState(955);
				imaginary_number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(957);
				signed_real_number();
				setState(958);
				match(MINUS);
				setState(959);
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
			setState(966);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(963);
				match(NUMBER);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(964);
				match(MINUS);
				setState(965);
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
			setState(971);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(968);
				real_number();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(969);
				match(MINUS);
				setState(970);
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
			setState(973);
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
			setState(975);
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
			setState(977);
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
			setState(979);
			name();
			setState(980);
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
			setState(982);
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
			setState(984);
			attr();
			setState(985);
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
			setState(987);
			name();
			setState(990); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(988);
					match(DOT);
					setState(989);
					name();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(992); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,118,_ctx);
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
			setState(996);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(994);
				attr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(995);
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
			setState(998);
			match(OPEN_PAREN);
			setState(999);
			pattern();
			setState(1000);
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
			setState(1012);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(1002);
				match(OPEN_BRACK);
				setState(1004);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 864833243555299352L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 8449L) != 0)) {
					{
					setState(1003);
					maybe_sequence_pattern();
					}
				}

				setState(1006);
				match(CLOSE_BRACK);
				}
				break;
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(1007);
				match(OPEN_PAREN);
				setState(1009);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 864833243555299352L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 8449L) != 0)) {
					{
					setState(1008);
					open_sequence_pattern();
					}
				}

				setState(1011);
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
			setState(1014);
			maybe_star_pattern();
			setState(1015);
			match(COMMA);
			setState(1017);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 864833243555299352L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 8449L) != 0)) {
				{
				setState(1016);
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
			setState(1019);
			maybe_star_pattern();
			setState(1024);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,124,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1020);
					match(COMMA);
					setState(1021);
					maybe_star_pattern();
					}
					} 
				}
				setState(1026);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,124,_ctx);
			}
			setState(1028);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1027);
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
			setState(1032);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(1030);
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
				setState(1031);
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
			setState(1038);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1034);
				match(STAR);
				setState(1035);
				pattern_capture_target();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1036);
				match(STAR);
				setState(1037);
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
			setState(1065);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,131,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1040);
				match(OPEN_BRACE);
				setState(1041);
				match(CLOSE_BRACE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1042);
				match(OPEN_BRACE);
				setState(1043);
				double_star_pattern();
				setState(1045);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1044);
					match(COMMA);
					}
				}

				setState(1047);
				match(CLOSE_BRACE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1049);
				match(OPEN_BRACE);
				setState(1050);
				items_pattern();
				setState(1051);
				match(COMMA);
				setState(1052);
				double_star_pattern();
				setState(1054);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1053);
					match(COMMA);
					}
				}

				setState(1056);
				match(CLOSE_BRACE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1058);
				match(OPEN_BRACE);
				setState(1059);
				items_pattern();
				setState(1061);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1060);
					match(COMMA);
					}
				}

				setState(1063);
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
			setState(1067);
			key_value_pattern();
			setState(1072);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,132,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1068);
					match(COMMA);
					setState(1069);
					key_value_pattern();
					}
					} 
				}
				setState(1074);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,132,_ctx);
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
			setState(1077);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case NUMBER:
			case FALSE:
			case NONE:
			case TRUE:
			case MINUS:
				{
				setState(1075);
				literal_expr();
				}
				break;
			case MATCH:
			case UNDERSCORE:
			case NAME:
				{
				setState(1076);
				attr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1079);
			match(COLON);
			setState(1080);
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
			setState(1082);
			match(POWER);
			setState(1083);
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
			setState(1115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,137,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1085);
				name_or_attr();
				setState(1086);
				match(OPEN_PAREN);
				setState(1087);
				match(CLOSE_PAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1089);
				name_or_attr();
				setState(1090);
				match(OPEN_PAREN);
				setState(1091);
				positional_patterns();
				setState(1093);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1092);
					match(COMMA);
					}
				}

				setState(1095);
				match(CLOSE_PAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1097);
				name_or_attr();
				setState(1098);
				match(OPEN_PAREN);
				setState(1099);
				keyword_patterns();
				setState(1101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1100);
					match(COMMA);
					}
				}

				setState(1103);
				match(CLOSE_PAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1105);
				name_or_attr();
				setState(1106);
				match(OPEN_PAREN);
				setState(1107);
				positional_patterns();
				setState(1108);
				match(COMMA);
				setState(1109);
				keyword_patterns();
				setState(1111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1110);
					match(COMMA);
					}
				}

				setState(1113);
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
			setState(1117);
			pattern();
			setState(1122);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,138,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1118);
					match(COMMA);
					setState(1119);
					pattern();
					}
					} 
				}
				setState(1124);
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
			setState(1125);
			keyword_pattern();
			setState(1130);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,139,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1126);
					match(COMMA);
					setState(1127);
					keyword_pattern();
					}
					} 
				}
				setState(1132);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,139,_ctx);
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
			setState(1133);
			name();
			setState(1134);
			match(ASSIGN);
			setState(1135);
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
			setState(1146);
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
				setState(1137);
				or_test();
				setState(1143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(1138);
					match(IF);
					setState(1139);
					or_test();
					setState(1140);
					match(ELSE);
					setState(1141);
					test();
					}
				}

				}
				break;
			case LAMBDA:
				enterOuterAlt(_localctx, 2);
				{
				setState(1145);
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
			setState(1150);
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
				setState(1148);
				or_test();
				}
				break;
			case LAMBDA:
				enterOuterAlt(_localctx, 2);
				{
				setState(1149);
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
			setState(1152);
			match(LAMBDA);
			setState(1154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 288372214225436672L) != 0) || _la==POWER || _la==WILDCARD) {
				{
				setState(1153);
				varargslist();
				}
			}

			setState(1156);
			match(COLON);
			setState(1157);
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
			setState(1159);
			match(LAMBDA);
			setState(1161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 288372214225436672L) != 0) || _la==POWER || _la==WILDCARD) {
				{
				setState(1160);
				varargslist();
				}
			}

			setState(1163);
			match(COLON);
			setState(1164);
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
			setState(1166);
			and_test();
			setState(1171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(1167);
				match(OR);
				setState(1168);
				and_test();
				}
				}
				setState(1173);
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
			setState(1174);
			not_test();
			setState(1179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(1175);
				match(AND);
				setState(1176);
				not_test();
				}
				}
				setState(1181);
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
			setState(1185);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1182);
				match(NOT);
				setState(1183);
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
				setState(1184);
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
			setState(1187);
			expr(0);
			setState(1193);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,148,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1188);
					comp_op();
					setState(1189);
					expr(0);
					}
					} 
				}
				setState(1195);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,148,_ctx);
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
			setState(1209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,149,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1196);
				match(LESS_THAN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1197);
				match(GREATER_THAN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1198);
				match(EQUALS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1199);
				match(GT_EQ);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1200);
				match(LT_EQ);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1201);
				match(NOT_EQ_1);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1202);
				match(NOT_EQ_2);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1203);
				match(IN);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1204);
				match(NOT);
				setState(1205);
				match(IN);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1206);
				match(IS);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1207);
				match(IS);
				setState(1208);
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
			setState(1211);
			match(STAR);
			setState(1212);
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
			setState(1224);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,151,_ctx) ) {
			case 1:
				{
				setState(1215);
				expr_wildcard();
				}
				break;
			case 2:
				{
				setState(1216);
				number_wildcard();
				}
				break;
			case 3:
				{
				setState(1217);
				atom_expr();
				}
				break;
			case 4:
				{
				setState(1219); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1218);
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
					setState(1221); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,150,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(1223);
				expr(7);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1249);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,153,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1247);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,152,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1226);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1227);
						match(POWER);
						setState(1228);
						expr(9);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1229);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1230);
						_la = _input.LA(1);
						if ( !(((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & 1074659329L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1231);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1232);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1233);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1234);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1235);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1236);
						_la = _input.LA(1);
						if ( !(_la==LEFT_SHIFT || _la==RIGHT_SHIFT) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1237);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1238);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1239);
						match(AND_OP);
						setState(1240);
						expr(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1241);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1242);
						match(XOR);
						setState(1243);
						expr(3);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1244);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1245);
						match(OR_OP);
						setState(1246);
						expr(2);
						}
						break;
					}
					} 
				}
				setState(1251);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,153,_ctx);
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
			setState(1253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AWAIT) {
				{
				setState(1252);
				match(AWAIT);
				}
			}

			setState(1255);
			atom();
			setState(1259);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,155,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1256);
					trailer();
					}
					} 
				}
				setState(1261);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,155,_ctx);
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
			setState(1290);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1262);
				match(OPEN_PAREN);
				setState(1265);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case YIELD:
					{
					setState(1263);
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
					setState(1264);
					testlist_comp();
					}
					break;
				case CLOSE_PAREN:
					break;
				default:
					break;
				}
				setState(1267);
				match(CLOSE_PAREN);
				}
				break;
			case OPEN_BRACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(1268);
				match(OPEN_BRACK);
				setState(1270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
					{
					setState(1269);
					testlist_comp();
					}
				}

				setState(1272);
				match(CLOSE_BRACK);
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(1273);
				match(OPEN_BRACE);
				setState(1275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 549755864581L) != 0)) {
					{
					setState(1274);
					dictorsetmaker();
					}
				}

				setState(1277);
				match(CLOSE_BRACE);
				}
				break;
			case WILDCARD:
				enterOuterAlt(_localctx, 4);
				{
				setState(1278);
				atom_wildcard();
				}
				break;
			case MATCH:
			case UNDERSCORE:
			case NAME:
				enterOuterAlt(_localctx, 5);
				{
				setState(1279);
				name();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 6);
				{
				setState(1280);
				match(NUMBER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 7);
				{
				setState(1282); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1281);
						match(STRING);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1284); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,159,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case ELLIPSIS:
				enterOuterAlt(_localctx, 8);
				{
				setState(1286);
				match(ELLIPSIS);
				}
				break;
			case NONE:
				enterOuterAlt(_localctx, 9);
				{
				setState(1287);
				match(NONE);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 10);
				{
				setState(1288);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 11);
				{
				setState(1289);
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
			setState(1292);
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
			setState(1297);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,161,_ctx) ) {
			case 1:
				{
				setState(1294);
				list_wildcard();
				}
				break;
			case 2:
				{
				setState(1295);
				test();
				}
				break;
			case 3:
				{
				setState(1296);
				star_expr();
				}
				break;
			}
			setState(1314);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASYNC:
			case FOR:
				{
				setState(1299);
				comp_for();
				}
				break;
			case CLOSE_PAREN:
			case COMMA:
			case CLOSE_BRACK:
				{
				setState(1308);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,163,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1300);
						match(COMMA);
						setState(1304);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,162,_ctx) ) {
						case 1:
							{
							setState(1301);
							list_wildcard();
							}
							break;
						case 2:
							{
							setState(1302);
							test();
							}
							break;
						case 3:
							{
							setState(1303);
							star_expr();
							}
							break;
						}
						}
						} 
					}
					setState(1310);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,163,_ctx);
				}
				setState(1312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1311);
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
		public Atom_wildcardContext atom_wildcard() {
			return getRuleContext(Atom_wildcardContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
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
			setState(1330);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1316);
				match(OPEN_PAREN);
				setState(1318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 549755864581L) != 0)) {
					{
					setState(1317);
					arglist();
					}
				}

				setState(1320);
				match(CLOSE_PAREN);
				}
				break;
			case OPEN_BRACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(1321);
				match(OPEN_BRACK);
				setState(1322);
				subscriptlist();
				setState(1323);
				match(CLOSE_BRACK);
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(1325);
				match(DOT);
				setState(1328);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WILDCARD:
					{
					setState(1326);
					atom_wildcard();
					}
					break;
				case MATCH:
				case UNDERSCORE:
				case NAME:
					{
					setState(1327);
					name();
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
			setState(1332);
			subscript_();
			setState(1337);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,169,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1333);
					match(COMMA);
					setState(1334);
					subscript_();
					}
					} 
				}
				setState(1339);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,169,_ctx);
			}
			setState(1341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1340);
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
			setState(1354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,174,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1343);
				test();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1345);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
					{
					setState(1344);
					test();
					}
				}

				setState(1347);
				match(COLON);
				setState(1349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
					{
					setState(1348);
					test();
					}
				}

				setState(1352);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(1351);
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
			setState(1356);
			match(COLON);
			setState(1358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064606250008L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(1357);
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
			setState(1362);
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
				setState(1360);
				expr(0);
				}
				break;
			case STAR:
				{
				setState(1361);
				star_expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1371);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,178,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1364);
					match(COMMA);
					setState(1367);
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
						setState(1365);
						expr(0);
						}
						break;
					case STAR:
						{
						setState(1366);
						star_expr();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(1373);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,178,_ctx);
			}
			setState(1375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1374);
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
		public List<List_wildcardContext> list_wildcard() {
			return getRuleContexts(List_wildcardContext.class);
		}
		public List_wildcardContext list_wildcard(int i) {
			return getRuleContext(List_wildcardContext.class,i);
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
			setState(1379);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,180,_ctx) ) {
			case 1:
				{
				setState(1377);
				test();
				}
				break;
			case 2:
				{
				setState(1378);
				list_wildcard();
				}
				break;
			}
			setState(1388);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,182,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1381);
					match(COMMA);
					setState(1384);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,181,_ctx) ) {
					case 1:
						{
						setState(1382);
						test();
						}
						break;
					case 2:
						{
						setState(1383);
						list_wildcard();
						}
						break;
					}
					}
					} 
				}
				setState(1390);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,182,_ctx);
			}
			setState(1392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1391);
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
			setState(1442);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,194,_ctx) ) {
			case 1:
				{
				{
				setState(1400);
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
					setState(1394);
					test();
					setState(1395);
					match(COLON);
					setState(1396);
					test();
					}
					break;
				case POWER:
					{
					setState(1398);
					match(POWER);
					setState(1399);
					expr(0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1420);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ASYNC:
				case FOR:
					{
					setState(1402);
					comp_for();
					}
					break;
				case COMMA:
				case CLOSE_BRACE:
					{
					setState(1414);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,186,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(1403);
							match(COMMA);
							setState(1410);
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
								setState(1404);
								test();
								setState(1405);
								match(COLON);
								setState(1406);
								test();
								}
								break;
							case POWER:
								{
								setState(1408);
								match(POWER);
								setState(1409);
								expr(0);
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							} 
						}
						setState(1416);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,186,_ctx);
					}
					setState(1418);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(1417);
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
				setState(1424);
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
					setState(1422);
					test();
					}
					break;
				case STAR:
					{
					setState(1423);
					star_expr();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1440);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ASYNC:
				case FOR:
					{
					setState(1426);
					comp_for();
					}
					break;
				case COMMA:
				case CLOSE_BRACE:
					{
					setState(1434);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,191,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(1427);
							match(COMMA);
							setState(1430);
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
								setState(1428);
								test();
								}
								break;
							case STAR:
								{
								setState(1429);
								star_expr();
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							} 
						}
						setState(1436);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,191,_ctx);
					}
					setState(1438);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(1437);
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
			setState(1444);
			match(CLASS);
			setState(1448);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,195,_ctx) ) {
			case 1:
				{
				setState(1445);
				name();
				}
				break;
			case 2:
				{
				setState(1446);
				simple_wildcard();
				}
				break;
			case 3:
				{
				setState(1447);
				var_wildcard();
				}
				break;
			}
			setState(1455);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_PAREN) {
				{
				setState(1450);
				match(OPEN_PAREN);
				setState(1452);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008948440757961752L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 549755864581L) != 0)) {
					{
					setState(1451);
					arglist();
					}
				}

				setState(1454);
				match(CLOSE_PAREN);
				}
			}

			setState(1457);
			match(COLON);
			setState(1458);
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
			setState(1463);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,198,_ctx) ) {
			case 1:
				{
				setState(1460);
				list_wildcard();
				}
				break;
			case 2:
				{
				setState(1461);
				number_wildcard();
				}
				break;
			case 3:
				{
				setState(1462);
				argument();
				}
				break;
			}
			setState(1473);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,200,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1465);
					match(COMMA);
					setState(1469);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,199,_ctx) ) {
					case 1:
						{
						setState(1466);
						list_wildcard();
						}
						break;
					case 2:
						{
						setState(1467);
						number_wildcard();
						}
						break;
					case 3:
						{
						setState(1468);
						argument();
						}
						break;
					}
					}
					} 
				}
				setState(1475);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,200,_ctx);
			}
			setState(1477);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1476);
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
			setState(1492);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,203,_ctx) ) {
			case 1:
				{
				setState(1479);
				var_wildcard();
				}
				break;
			case 2:
				{
				setState(1480);
				test();
				setState(1482);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASYNC || _la==FOR) {
					{
					setState(1481);
					comp_for();
					}
				}

				}
				break;
			case 3:
				{
				setState(1484);
				test();
				setState(1485);
				match(ASSIGN);
				setState(1486);
				test();
				}
				break;
			case 4:
				{
				setState(1488);
				match(POWER);
				setState(1489);
				test();
				}
				break;
			case 5:
				{
				setState(1490);
				match(STAR);
				setState(1491);
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
			setState(1496);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASYNC:
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(1494);
				comp_for();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(1495);
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
			setState(1499);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASYNC) {
				{
				setState(1498);
				match(ASYNC);
				}
			}

			setState(1501);
			match(FOR);
			setState(1502);
			exprlist();
			setState(1503);
			match(IN);
			setState(1504);
			or_test();
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
			setState(1508);
			match(IF);
			setState(1509);
			test_nocond();
			setState(1511);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 37749248L) != 0)) {
				{
				setState(1510);
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
			setState(1513);
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
			setState(1515);
			match(YIELD);
			setState(1517);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718064614638616L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438966145L) != 0)) {
				{
				setState(1516);
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
			setState(1522);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FROM:
				enterOuterAlt(_localctx, 1);
				{
				setState(1519);
				match(FROM);
				setState(1520);
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
				setState(1521);
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
			setState(1525); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1524);
				match(STRING);
				}
				}
				setState(1527); 
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
			setState(1529);
			match(OPEN_BRACE);
			setState(1530);
			match(NUMBER);
			setState(1534);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,211,_ctx) ) {
			case 1:
				{
				setState(1531);
				match(COMMA);
				}
				break;
			case 2:
				{
				setState(1532);
				match(COMMA);
				setState(1533);
				match(NUMBER);
				}
				break;
			}
			setState(1536);
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
			setState(1543);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,212,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1538);
				double_wildcard();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1539);
				contains_wildcard();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1540);
				simple_wildcard();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1541);
				number_wildcard();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1542);
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
			setState(1549);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,213,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1545);
				macro_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1546);
				var_wildcard();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1547);
				contains_wildcard();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1548);
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
			setState(1553);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,214,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1551);
				simple_wildcard();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1552);
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
			setState(1555);
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
			setState(1557);
			match(WILDCARD);
			setState(1558);
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
			setState(1560);
			match(WILDCARD);
			setState(1561);
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
			setState(1563);
			match(WILDCARD);
			setState(1564);
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
			setState(1566);
			match(WILDCARD);
			setState(1567);
			match(LESS_THAN);
			setState(1570);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,215,_ctx) ) {
			case 1:
				{
				setState(1568);
				expr_wildcard();
				}
				break;
			case 2:
				{
				setState(1569);
				expr_stmt();
				}
				break;
			}
			setState(1572);
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
			setState(1576);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,216,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1574);
				simple_compound_wildcard();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1575);
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
			setState(1578);
			match(WILDCARD);
			setState(1579);
			match(COLON);
			setState(1581);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,217,_ctx) ) {
			case 1:
				{
				setState(1580);
				wildcard_number();
				}
				break;
			}
			setState(1583);
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
			setState(1585);
			match(WILDCARD);
			setState(1590);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(1586);
				match(COLON);
				setState(1587);
				match(STAR);
				}
				break;
			case STAR:
				{
				setState(1588);
				match(STAR);
				setState(1589);
				match(COLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1592);
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
			setState(1594);
			match(WILDCARD);
			setState(1595);
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
			setState(1597);
			match(WILDCARD);
			setState(1598);
			match(SUB_PATTERN);
			setState(1599);
			match(NAME);
			setState(1600);
			match(OPEN_PAREN);
			setState(1602);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718055479443480L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438961665L) != 0)) {
				{
				setState(1601);
				macro_args();
				}
			}

			setState(1604);
			match(CLOSE_PAREN);
			setState(1607);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,220,_ctx) ) {
			case 1:
				{
				setState(1605);
				match(COLON);
				setState(1606);
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
		public MacroContext macro() {
			return getRuleContext(MacroContext.class,0);
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
			setState(1609);
			macro();
			setState(1611); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1610);
				transformation();
				}
				}
				setState(1613); 
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
		enterRule(_localctx, 268, RULE_macro);
		try {
			setState(1619);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,222,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1615);
				compound_macro();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(1616);
				simple_macro();
				setState(1617);
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
		enterRule(_localctx, 270, RULE_macro_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1621);
			macro_arg();
			setState(1626);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1622);
				match(COMMA);
				setState(1623);
				macro_arg();
				}
				}
				setState(1628);
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
		enterRule(_localctx, 272, RULE_macro_arg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1631);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,224,_ctx) ) {
			case 1:
				{
				setState(1629);
				atom_wildcard();
				}
				break;
			case 2:
				{
				setState(1630);
				atom();
				}
				break;
			}
			setState(1635);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(1633);
				match(ASSIGN);
				setState(1634);
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
		public TerminalNode NOT_WILDCARD() { return getToken(Python3Parser.NOT_WILDCARD, 0); }
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
		enterRule(_localctx, 274, RULE_simple_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1637);
			match(SUB_PATTERN);
			setState(1638);
			_la = _input.LA(1);
			if ( !(((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 274877906949L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1639);
			match(NAME);
			setState(1640);
			match(OPEN_PAREN);
			setState(1642);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 720718055479443480L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 137438961665L) != 0)) {
				{
				setState(1641);
				macro_args();
				}
			}

			setState(1644);
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
		enterRule(_localctx, 276, RULE_compound_macro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1646);
			simple_macro();
			setState(1647);
			match(COLON);
			setState(1648);
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
		enterRule(_localctx, 278, RULE_transformation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1650);
			match(BALISE);
			setState(1651);
			match(NAME);
			setState(1652);
			match(NEWLINE);
			setState(1653);
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
		"\u0004\u0001l\u0678\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"\u0002\u008a\u0007\u008a\u0002\u008b\u0007\u008b\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u011e\b\u0000\u0001\u0001"+
		"\u0001\u0001\u0005\u0001\u0122\b\u0001\n\u0001\f\u0001\u0125\t\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0005\u0002\u012b\b\u0002\n"+
		"\u0002\f\u0002\u012e\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0004\u0003\u0134\b\u0003\u000b\u0003\f\u0003\u0135\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"\u013e\b\u0004\u0001\u0004\u0003\u0004\u0141\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0004\u0005\u0146\b\u0005\u000b\u0005\f\u0005\u0147"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u014e\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u0157\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u015c\b\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0165\b\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u016b\b\t\u0005\t\u016d\b\t\n\t\f\t\u0170\t"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u0175\b\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u017b\b\t\u0005\t\u017d\b\t\n\t\f\t\u0180\t\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u0186\b\t\u0003\t\u0188\b\t\u0003\t\u018a\b"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u018f\b\t\u0003\t\u0191\b\t\u0003\t"+
		"\u0193\b\t\u0001\t\u0001\t\u0003\t\u0197\b\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u019d\b\t\u0005\t\u019f\b\t\n\t\f\t\u01a2\t\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u01a8\b\t\u0003\t\u01aa\b\t\u0003\t\u01ac\b"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u01b1\b\t\u0003\t\u01b3\b\t\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0001\n\u0003\n\u01ba\b\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u01bf\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01c4"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01ca"+
		"\b\u000b\u0005\u000b\u01cc\b\u000b\n\u000b\f\u000b\u01cf\t\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01d4\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01da\b\u000b\u0005\u000b\u01dc"+
		"\b\u000b\n\u000b\f\u000b\u01df\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u01e5\b\u000b\u0003\u000b\u01e7\b\u000b\u0003"+
		"\u000b\u01e9\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01ee"+
		"\b\u000b\u0003\u000b\u01f0\b\u000b\u0003\u000b\u01f2\b\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u01f6\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u01fc\b\u000b\u0005\u000b\u01fe\b\u000b\n\u000b"+
		"\f\u000b\u0201\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u0207\b\u000b\u0003\u000b\u0209\b\u000b\u0003\u000b\u020b"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0210\b\u000b"+
		"\u0003\u000b\u0212\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0218"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u0222\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0227\b\u000e"+
		"\n\u000e\f\u000e\u022a\t\u000e\u0001\u000e\u0003\u000e\u022d\b\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u023a"+
		"\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u0241\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0246"+
		"\b\u0010\u0005\u0010\u0248\b\u0010\n\u0010\f\u0010\u024b\t\u0010\u0003"+
		"\u0010\u024d\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u0253\b\u0011\u0001\u0012\u0001\u0012\u0003\u0012\u0257\b\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u025c\b\u0012\u0005\u0012"+
		"\u025e\b\u0012\n\u0012\f\u0012\u0261\t\u0012\u0001\u0012\u0003\u0012\u0264"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0003\u0016\u0272\b\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0003\u0019\u027a\b\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0282"+
		"\b\u001b\u0003\u001b\u0284\b\u001b\u0001\u001c\u0001\u001c\u0003\u001c"+
		"\u0288\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e"+
		"\u0005\u001e\u028f\b\u001e\n\u001e\f\u001e\u0292\t\u001e\u0001\u001e\u0001"+
		"\u001e\u0004\u001e\u0296\b\u001e\u000b\u001e\f\u001e\u0297\u0003\u001e"+
		"\u029a\b\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0003\u001e\u02a3\b\u001e\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0003\u001f\u02a8\b\u001f\u0001 \u0001 \u0001 \u0003 \u02ad"+
		"\b \u0001!\u0001!\u0001!\u0005!\u02b2\b!\n!\f!\u02b5\t!\u0001!\u0003!"+
		"\u02b8\b!\u0001\"\u0001\"\u0001\"\u0005\"\u02bd\b\"\n\"\f\"\u02c0\t\""+
		"\u0001#\u0001#\u0001#\u0005#\u02c5\b#\n#\f#\u02c8\t#\u0001$\u0001$\u0001"+
		"$\u0001$\u0005$\u02ce\b$\n$\f$\u02d1\t$\u0001%\u0001%\u0001%\u0001%\u0005"+
		"%\u02d7\b%\n%\f%\u02da\t%\u0001&\u0001&\u0001&\u0001&\u0003&\u02e0\b&"+
		"\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0003\'\u02ed\b\'\u0001(\u0001(\u0001(\u0001(\u0003"+
		"(\u02f3\b(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0005)\u02fe\b)\n)\f)\u0301\t)\u0001)\u0001)\u0001)\u0003)\u0306\b)"+
		"\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0003*\u030f\b*\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u031a"+
		"\b+\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0004,\u0323\b,\u000b"+
		",\f,\u0324\u0001,\u0001,\u0001,\u0003,\u032a\b,\u0001,\u0001,\u0001,\u0003"+
		",\u032f\b,\u0001,\u0001,\u0001,\u0003,\u0334\b,\u0001-\u0001-\u0001-\u0001"+
		"-\u0005-\u033a\b-\n-\f-\u033d\t-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001"+
		".\u0003.\u0345\b.\u0001/\u0001/\u0001/\u0001/\u0003/\u034b\b/\u0003/\u034d"+
		"\b/\u00010\u00010\u00010\u00010\u00040\u0353\b0\u000b0\f0\u0354\u0001"+
		"0\u00010\u00030\u0359\b0\u00011\u00011\u00011\u00011\u00011\u00011\u0004"+
		"1\u0361\b1\u000b1\f1\u0362\u00011\u00011\u00012\u00012\u00012\u00032\u036a"+
		"\b2\u00012\u00032\u036d\b2\u00013\u00013\u00043\u0371\b3\u000b3\f3\u0372"+
		"\u00013\u00033\u0376\b3\u00014\u00014\u00014\u00034\u037b\b4\u00015\u0001"+
		"5\u00015\u00035\u0380\b5\u00015\u00015\u00015\u00016\u00016\u00016\u0001"+
		"7\u00017\u00037\u038a\b7\u00018\u00018\u00038\u038e\b8\u00019\u00019\u0001"+
		"9\u00019\u0001:\u0001:\u0001:\u0005:\u0397\b:\n:\f:\u039a\t:\u0001;\u0001"+
		";\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0003;\u03a4\b;\u0001<\u0001"+
		"<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0003<\u03ae\b<\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0003=\u03b8\b=\u0001>\u0001"+
		">\u0001>\u0001>\u0001>\u0001>\u0001>\u0001>\u0003>\u03c2\b>\u0001?\u0001"+
		"?\u0001?\u0003?\u03c7\b?\u0001@\u0001@\u0001@\u0003@\u03cc\b@\u0001A\u0001"+
		"A\u0001B\u0001B\u0001C\u0001C\u0001D\u0001D\u0001D\u0001E\u0001E\u0001"+
		"F\u0001F\u0001F\u0001G\u0001G\u0001G\u0004G\u03df\bG\u000bG\fG\u03e0\u0001"+
		"H\u0001H\u0003H\u03e5\bH\u0001I\u0001I\u0001I\u0001I\u0001J\u0001J\u0003"+
		"J\u03ed\bJ\u0001J\u0001J\u0001J\u0003J\u03f2\bJ\u0001J\u0003J\u03f5\b"+
		"J\u0001K\u0001K\u0001K\u0003K\u03fa\bK\u0001L\u0001L\u0001L\u0005L\u03ff"+
		"\bL\nL\fL\u0402\tL\u0001L\u0003L\u0405\bL\u0001M\u0001M\u0003M\u0409\b"+
		"M\u0001N\u0001N\u0001N\u0001N\u0003N\u040f\bN\u0001O\u0001O\u0001O\u0001"+
		"O\u0001O\u0003O\u0416\bO\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0001"+
		"O\u0003O\u041f\bO\u0001O\u0001O\u0001O\u0001O\u0001O\u0003O\u0426\bO\u0001"+
		"O\u0001O\u0003O\u042a\bO\u0001P\u0001P\u0001P\u0005P\u042f\bP\nP\fP\u0432"+
		"\tP\u0001Q\u0001Q\u0003Q\u0436\bQ\u0001Q\u0001Q\u0001Q\u0001R\u0001R\u0001"+
		"R\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0003S\u0446"+
		"\bS\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0003S\u044e\bS\u0001S\u0001"+
		"S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0003S\u0458\bS\u0001S\u0001"+
		"S\u0003S\u045c\bS\u0001T\u0001T\u0001T\u0005T\u0461\bT\nT\fT\u0464\tT"+
		"\u0001U\u0001U\u0001U\u0005U\u0469\bU\nU\fU\u046c\tU\u0001V\u0001V\u0001"+
		"V\u0001V\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0003W\u0478\bW\u0001"+
		"W\u0003W\u047b\bW\u0001X\u0001X\u0003X\u047f\bX\u0001Y\u0001Y\u0003Y\u0483"+
		"\bY\u0001Y\u0001Y\u0001Y\u0001Z\u0001Z\u0003Z\u048a\bZ\u0001Z\u0001Z\u0001"+
		"Z\u0001[\u0001[\u0001[\u0005[\u0492\b[\n[\f[\u0495\t[\u0001\\\u0001\\"+
		"\u0001\\\u0005\\\u049a\b\\\n\\\f\\\u049d\t\\\u0001]\u0001]\u0001]\u0003"+
		"]\u04a2\b]\u0001^\u0001^\u0001^\u0001^\u0005^\u04a8\b^\n^\f^\u04ab\t^"+
		"\u0001_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001"+
		"_\u0001_\u0001_\u0001_\u0003_\u04ba\b_\u0001`\u0001`\u0001`\u0001a\u0001"+
		"a\u0001a\u0001a\u0001a\u0004a\u04c4\ba\u000ba\fa\u04c5\u0001a\u0003a\u04c9"+
		"\ba\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001"+
		"a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001a\u0001"+
		"a\u0001a\u0005a\u04e0\ba\na\fa\u04e3\ta\u0001b\u0003b\u04e6\bb\u0001b"+
		"\u0001b\u0005b\u04ea\bb\nb\fb\u04ed\tb\u0001c\u0001c\u0001c\u0003c\u04f2"+
		"\bc\u0001c\u0001c\u0001c\u0003c\u04f7\bc\u0001c\u0001c\u0001c\u0003c\u04fc"+
		"\bc\u0001c\u0001c\u0001c\u0001c\u0001c\u0004c\u0503\bc\u000bc\fc\u0504"+
		"\u0001c\u0001c\u0001c\u0001c\u0003c\u050b\bc\u0001d\u0001d\u0001e\u0001"+
		"e\u0001e\u0003e\u0512\be\u0001e\u0001e\u0001e\u0001e\u0001e\u0003e\u0519"+
		"\be\u0005e\u051b\be\ne\fe\u051e\te\u0001e\u0003e\u0521\be\u0003e\u0523"+
		"\be\u0001f\u0001f\u0003f\u0527\bf\u0001f\u0001f\u0001f\u0001f\u0001f\u0001"+
		"f\u0001f\u0001f\u0003f\u0531\bf\u0003f\u0533\bf\u0001g\u0001g\u0001g\u0005"+
		"g\u0538\bg\ng\fg\u053b\tg\u0001g\u0003g\u053e\bg\u0001h\u0001h\u0003h"+
		"\u0542\bh\u0001h\u0001h\u0003h\u0546\bh\u0001h\u0003h\u0549\bh\u0003h"+
		"\u054b\bh\u0001i\u0001i\u0003i\u054f\bi\u0001j\u0001j\u0003j\u0553\bj"+
		"\u0001j\u0001j\u0001j\u0003j\u0558\bj\u0005j\u055a\bj\nj\fj\u055d\tj\u0001"+
		"j\u0003j\u0560\bj\u0001k\u0001k\u0003k\u0564\bk\u0001k\u0001k\u0001k\u0003"+
		"k\u0569\bk\u0005k\u056b\bk\nk\fk\u056e\tk\u0001k\u0003k\u0571\bk\u0001"+
		"l\u0001l\u0001l\u0001l\u0001l\u0001l\u0003l\u0579\bl\u0001l\u0001l\u0001"+
		"l\u0001l\u0001l\u0001l\u0001l\u0001l\u0003l\u0583\bl\u0005l\u0585\bl\n"+
		"l\fl\u0588\tl\u0001l\u0003l\u058b\bl\u0003l\u058d\bl\u0001l\u0001l\u0003"+
		"l\u0591\bl\u0001l\u0001l\u0001l\u0001l\u0003l\u0597\bl\u0005l\u0599\b"+
		"l\nl\fl\u059c\tl\u0001l\u0003l\u059f\bl\u0003l\u05a1\bl\u0003l\u05a3\b"+
		"l\u0001m\u0001m\u0001m\u0001m\u0003m\u05a9\bm\u0001m\u0001m\u0003m\u05ad"+
		"\bm\u0001m\u0003m\u05b0\bm\u0001m\u0001m\u0001m\u0001n\u0001n\u0001n\u0003"+
		"n\u05b8\bn\u0001n\u0001n\u0001n\u0001n\u0003n\u05be\bn\u0005n\u05c0\b"+
		"n\nn\fn\u05c3\tn\u0001n\u0003n\u05c6\bn\u0001o\u0001o\u0001o\u0003o\u05cb"+
		"\bo\u0001o\u0001o\u0001o\u0001o\u0001o\u0001o\u0001o\u0001o\u0003o\u05d5"+
		"\bo\u0001p\u0001p\u0003p\u05d9\bp\u0001q\u0003q\u05dc\bq\u0001q\u0001"+
		"q\u0001q\u0001q\u0001q\u0003q\u05e3\bq\u0001r\u0001r\u0001r\u0003r\u05e8"+
		"\br\u0001s\u0001s\u0001t\u0001t\u0003t\u05ee\bt\u0001u\u0001u\u0001u\u0003"+
		"u\u05f3\bu\u0001v\u0004v\u05f6\bv\u000bv\fv\u05f7\u0001w\u0001w\u0001"+
		"w\u0001w\u0001w\u0003w\u05ff\bw\u0001w\u0001w\u0001x\u0001x\u0001x\u0001"+
		"x\u0001x\u0003x\u0608\bx\u0001y\u0001y\u0001y\u0001y\u0003y\u060e\by\u0001"+
		"z\u0001z\u0003z\u0612\bz\u0001{\u0001{\u0001|\u0001|\u0001|\u0001}\u0001"+
		"}\u0001}\u0001~\u0001~\u0001~\u0001\u007f\u0001\u007f\u0001\u007f\u0001"+
		"\u007f\u0003\u007f\u0623\b\u007f\u0001\u007f\u0001\u007f\u0001\u0080\u0001"+
		"\u0080\u0003\u0080\u0629\b\u0080\u0001\u0081\u0001\u0081\u0001\u0081\u0003"+
		"\u0081\u062e\b\u0081\u0001\u0081\u0001\u0081\u0001\u0082\u0001\u0082\u0001"+
		"\u0082\u0001\u0082\u0001\u0082\u0003\u0082\u0637\b\u0082\u0001\u0082\u0001"+
		"\u0082\u0001\u0083\u0001\u0083\u0001\u0083\u0001\u0084\u0001\u0084\u0001"+
		"\u0084\u0001\u0084\u0001\u0084\u0003\u0084\u0643\b\u0084\u0001\u0084\u0001"+
		"\u0084\u0001\u0084\u0003\u0084\u0648\b\u0084\u0001\u0085\u0001\u0085\u0004"+
		"\u0085\u064c\b\u0085\u000b\u0085\f\u0085\u064d\u0001\u0086\u0001\u0086"+
		"\u0001\u0086\u0001\u0086\u0003\u0086\u0654\b\u0086\u0001\u0087\u0001\u0087"+
		"\u0001\u0087\u0005\u0087\u0659\b\u0087\n\u0087\f\u0087\u065c\t\u0087\u0001"+
		"\u0088\u0001\u0088\u0003\u0088\u0660\b\u0088\u0001\u0088\u0001\u0088\u0003"+
		"\u0088\u0664\b\u0088\u0001\u0089\u0001\u0089\u0001\u0089\u0001\u0089\u0001"+
		"\u0089\u0003\u0089\u066b\b\u0089\u0001\u0089\u0001\u0089\u0001\u008a\u0001"+
		"\u008a\u0001\u008a\u0001\u008a\u0001\u008b\u0001\u008b\u0001\u008b\u0001"+
		"\u008b\u0001\u008b\u0001\u008b\u0000\u0001\u00c2\u008c\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086"+
		"\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e"+
		"\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6"+
		"\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce"+
		"\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6"+
		"\u00e8\u00ea\u00ec\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe"+
		"\u0100\u0102\u0104\u0106\u0108\u010a\u010c\u010e\u0110\u0112\u0114\u0116"+
		"\u0000\b\u0001\u0000Zf\u0001\u000089\u0002\u0000IJNN\u0003\u0000::KMX"+
		"X\u0001\u0000IJ\u0001\u0000GH\u0003\u0000\u001e\u001e((//\u0003\u0000"+
		"DDFFjj\u072d\u0000\u011d\u0001\u0000\u0000\u0000\u0002\u0123\u0001\u0000"+
		"\u0000\u0000\u0004\u0128\u0001\u0000\u0000\u0000\u0006\u0133\u0001\u0000"+
		"\u0000\u0000\b\u0139\u0001\u0000\u0000\u0000\n\u0145\u0001\u0000\u0000"+
		"\u0000\f\u0149\u0001\u0000\u0000\u0000\u000e\u014f\u0001\u0000\u0000\u0000"+
		"\u0010\u0152\u0001\u0000\u0000\u0000\u0012\u0160\u0001\u0000\u0000\u0000"+
		"\u0014\u01be\u0001\u0000\u0000\u0000\u0016\u0211\u0001\u0000\u0000\u0000"+
		"\u0018\u0217\u0001\u0000\u0000\u0000\u001a\u0221\u0001\u0000\u0000\u0000"+
		"\u001c\u0223\u0001\u0000\u0000\u0000\u001e\u0239\u0001\u0000\u0000\u0000"+
		" \u023b\u0001\u0000\u0000\u0000\"\u024e\u0001\u0000\u0000\u0000$\u0256"+
		"\u0001\u0000\u0000\u0000&\u0265\u0001\u0000\u0000\u0000(\u0267\u0001\u0000"+
		"\u0000\u0000*\u026a\u0001\u0000\u0000\u0000,\u0271\u0001\u0000\u0000\u0000"+
		".\u0273\u0001\u0000\u0000\u00000\u0275\u0001\u0000\u0000\u00002\u0277"+
		"\u0001\u0000\u0000\u00004\u027b\u0001\u0000\u0000\u00006\u027d\u0001\u0000"+
		"\u0000\u00008\u0287\u0001\u0000\u0000\u0000:\u0289\u0001\u0000\u0000\u0000"+
		"<\u028c\u0001\u0000\u0000\u0000>\u02a4\u0001\u0000\u0000\u0000@\u02a9"+
		"\u0001\u0000\u0000\u0000B\u02ae\u0001\u0000\u0000\u0000D\u02b9\u0001\u0000"+
		"\u0000\u0000F\u02c1\u0001\u0000\u0000\u0000H\u02c9\u0001\u0000\u0000\u0000"+
		"J\u02d2\u0001\u0000\u0000\u0000L\u02db\u0001\u0000\u0000\u0000N\u02ec"+
		"\u0001\u0000\u0000\u0000P\u02ee\u0001\u0000\u0000\u0000R\u02f4\u0001\u0000"+
		"\u0000\u0000T\u0307\u0001\u0000\u0000\u0000V\u0310\u0001\u0000\u0000\u0000"+
		"X\u031b\u0001\u0000\u0000\u0000Z\u0335\u0001\u0000\u0000\u0000\\\u0341"+
		"\u0001\u0000\u0000\u0000^\u0346\u0001\u0000\u0000\u0000`\u0358\u0001\u0000"+
		"\u0000\u0000b\u035a\u0001\u0000\u0000\u0000d\u036c\u0001\u0000\u0000\u0000"+
		"f\u036e\u0001\u0000\u0000\u0000h\u037a\u0001\u0000\u0000\u0000j\u037c"+
		"\u0001\u0000\u0000\u0000l\u0384\u0001\u0000\u0000\u0000n\u0389\u0001\u0000"+
		"\u0000\u0000p\u038d\u0001\u0000\u0000\u0000r\u038f\u0001\u0000\u0000\u0000"+
		"t\u0393\u0001\u0000\u0000\u0000v\u03a3\u0001\u0000\u0000\u0000x\u03ad"+
		"\u0001\u0000\u0000\u0000z\u03b7\u0001\u0000\u0000\u0000|\u03c1\u0001\u0000"+
		"\u0000\u0000~\u03c6\u0001\u0000\u0000\u0000\u0080\u03cb\u0001\u0000\u0000"+
		"\u0000\u0082\u03cd\u0001\u0000\u0000\u0000\u0084\u03cf\u0001\u0000\u0000"+
		"\u0000\u0086\u03d1\u0001\u0000\u0000\u0000\u0088\u03d3\u0001\u0000\u0000"+
		"\u0000\u008a\u03d6\u0001\u0000\u0000\u0000\u008c\u03d8\u0001\u0000\u0000"+
		"\u0000\u008e\u03db\u0001\u0000\u0000\u0000\u0090\u03e4\u0001\u0000\u0000"+
		"\u0000\u0092\u03e6\u0001\u0000\u0000\u0000\u0094\u03f4\u0001\u0000\u0000"+
		"\u0000\u0096\u03f6\u0001\u0000\u0000\u0000\u0098\u03fb\u0001\u0000\u0000"+
		"\u0000\u009a\u0408\u0001\u0000\u0000\u0000\u009c\u040e\u0001\u0000\u0000"+
		"\u0000\u009e\u0429\u0001\u0000\u0000\u0000\u00a0\u042b\u0001\u0000\u0000"+
		"\u0000\u00a2\u0435\u0001\u0000\u0000\u0000\u00a4\u043a\u0001\u0000\u0000"+
		"\u0000\u00a6\u045b\u0001\u0000\u0000\u0000\u00a8\u045d\u0001\u0000\u0000"+
		"\u0000\u00aa\u0465\u0001\u0000\u0000\u0000\u00ac\u046d\u0001\u0000\u0000"+
		"\u0000\u00ae\u047a\u0001\u0000\u0000\u0000\u00b0\u047e\u0001\u0000\u0000"+
		"\u0000\u00b2\u0480\u0001\u0000\u0000\u0000\u00b4\u0487\u0001\u0000\u0000"+
		"\u0000\u00b6\u048e\u0001\u0000\u0000\u0000\u00b8\u0496\u0001\u0000\u0000"+
		"\u0000\u00ba\u04a1\u0001\u0000\u0000\u0000\u00bc\u04a3\u0001\u0000\u0000"+
		"\u0000\u00be\u04b9\u0001\u0000\u0000\u0000\u00c0\u04bb\u0001\u0000\u0000"+
		"\u0000\u00c2\u04c8\u0001\u0000\u0000\u0000\u00c4\u04e5\u0001\u0000\u0000"+
		"\u0000\u00c6\u050a\u0001\u0000\u0000\u0000\u00c8\u050c\u0001\u0000\u0000"+
		"\u0000\u00ca\u0511\u0001\u0000\u0000\u0000\u00cc\u0532\u0001\u0000\u0000"+
		"\u0000\u00ce\u0534\u0001\u0000\u0000\u0000\u00d0\u054a\u0001\u0000\u0000"+
		"\u0000\u00d2\u054c\u0001\u0000\u0000\u0000\u00d4\u0552\u0001\u0000\u0000"+
		"\u0000\u00d6\u0563\u0001\u0000\u0000\u0000\u00d8\u05a2\u0001\u0000\u0000"+
		"\u0000\u00da\u05a4\u0001\u0000\u0000\u0000\u00dc\u05b7\u0001\u0000\u0000"+
		"\u0000\u00de\u05d4\u0001\u0000\u0000\u0000\u00e0\u05d8\u0001\u0000\u0000"+
		"\u0000\u00e2\u05db\u0001\u0000\u0000\u0000\u00e4\u05e4\u0001\u0000\u0000"+
		"\u0000\u00e6\u05e9\u0001\u0000\u0000\u0000\u00e8\u05eb\u0001\u0000\u0000"+
		"\u0000\u00ea\u05f2\u0001\u0000\u0000\u0000\u00ec\u05f5\u0001\u0000\u0000"+
		"\u0000\u00ee\u05f9\u0001\u0000\u0000\u0000\u00f0\u0607\u0001\u0000\u0000"+
		"\u0000\u00f2\u060d\u0001\u0000\u0000\u0000\u00f4\u0611\u0001\u0000\u0000"+
		"\u0000\u00f6\u0613\u0001\u0000\u0000\u0000\u00f8\u0615\u0001\u0000\u0000"+
		"\u0000\u00fa\u0618\u0001\u0000\u0000\u0000\u00fc\u061b\u0001\u0000\u0000"+
		"\u0000\u00fe\u061e\u0001\u0000\u0000\u0000\u0100\u0628\u0001\u0000\u0000"+
		"\u0000\u0102\u062a\u0001\u0000\u0000\u0000\u0104\u0631\u0001\u0000\u0000"+
		"\u0000\u0106\u063a\u0001\u0000\u0000\u0000\u0108\u063d\u0001\u0000\u0000"+
		"\u0000\u010a\u0649\u0001\u0000\u0000\u0000\u010c\u0653\u0001\u0000\u0000"+
		"\u0000\u010e\u0655\u0001\u0000\u0000\u0000\u0110\u065f\u0001\u0000\u0000"+
		"\u0000\u0112\u0665\u0001\u0000\u0000\u0000\u0114\u066e\u0001\u0000\u0000"+
		"\u0000\u0116\u0672\u0001\u0000\u0000\u0000\u0118\u011e\u0005.\u0000\u0000"+
		"\u0119\u011e\u0003\u001c\u000e\u0000\u011a\u011b\u0003N\'\u0000\u011b"+
		"\u011c\u0005.\u0000\u0000\u011c\u011e\u0001\u0000\u0000\u0000\u011d\u0118"+
		"\u0001\u0000\u0000\u0000\u011d\u0119\u0001\u0000\u0000\u0000\u011d\u011a"+
		"\u0001\u0000\u0000\u0000\u011e\u0001\u0001\u0000\u0000\u0000\u011f\u0122"+
		"\u0005.\u0000\u0000\u0120\u0122\u0003\u001a\r\u0000\u0121\u011f\u0001"+
		"\u0000\u0000\u0000\u0121\u0120\u0001\u0000\u0000\u0000\u0122\u0125\u0001"+
		"\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0124\u0001"+
		"\u0000\u0000\u0000\u0124\u0126\u0001\u0000\u0000\u0000\u0125\u0123\u0001"+
		"\u0000\u0000\u0000\u0126\u0127\u0005\u0000\u0000\u0001\u0127\u0003\u0001"+
		"\u0000\u0000\u0000\u0128\u012c\u0003\u00d6k\u0000\u0129\u012b\u0005.\u0000"+
		"\u0000\u012a\u0129\u0001\u0000\u0000\u0000\u012b\u012e\u0001\u0000\u0000"+
		"\u0000\u012c\u012a\u0001\u0000\u0000\u0000\u012c\u012d\u0001\u0000\u0000"+
		"\u0000\u012d\u012f\u0001\u0000\u0000\u0000\u012e\u012c\u0001\u0000\u0000"+
		"\u0000\u012f\u0130\u0005\u0000\u0000\u0001\u0130\u0005\u0001\u0000\u0000"+
		"\u0000\u0131\u0134\u0005.\u0000\u0000\u0132\u0134\u0003\u010a\u0085\u0000"+
		"\u0133\u0131\u0001\u0000\u0000\u0000\u0133\u0132\u0001\u0000\u0000\u0000"+
		"\u0134\u0135\u0001\u0000\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000"+
		"\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0137\u0001\u0000\u0000\u0000"+
		"\u0137\u0138\u0005\u0000\u0000\u0001\u0138\u0007\u0001\u0000\u0000\u0000"+
		"\u0139\u013a\u0005X\u0000\u0000\u013a\u0140\u0003F#\u0000\u013b\u013d"+
		"\u0005;\u0000\u0000\u013c\u013e\u0003\u00dcn\u0000\u013d\u013c\u0001\u0000"+
		"\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000"+
		"\u0000\u0000\u013f\u0141\u0005<\u0000\u0000\u0140\u013b\u0001\u0000\u0000"+
		"\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000"+
		"\u0000\u0142\u0143\u0005.\u0000\u0000\u0143\t\u0001\u0000\u0000\u0000"+
		"\u0144\u0146\u0003\b\u0004\u0000\u0145\u0144\u0001\u0000\u0000\u0000\u0146"+
		"\u0147\u0001\u0000\u0000\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0147"+
		"\u0148\u0001\u0000\u0000\u0000\u0148\u000b\u0001\u0000\u0000\u0000\u0149"+
		"\u014d\u0003\n\u0005\u0000\u014a\u014e\u0003\u00dam\u0000\u014b\u014e"+
		"\u0003\u0010\b\u0000\u014c\u014e\u0003\u000e\u0007\u0000\u014d\u014a\u0001"+
		"\u0000\u0000\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014d\u014c\u0001"+
		"\u0000\u0000\u0000\u014e\r\u0001\u0000\u0000\u0000\u014f\u0150\u0005\t"+
		"\u0000\u0000\u0150\u0151\u0003\u0010\b\u0000\u0151\u000f\u0001\u0000\u0000"+
		"\u0000\u0152\u0156\u0005\u000f\u0000\u0000\u0153\u0157\u0003\u00c8d\u0000"+
		"\u0154\u0157\u0003\u00f6{\u0000\u0155\u0157\u0003\u00fc~\u0000\u0156\u0153"+
		"\u0001\u0000\u0000\u0000\u0156\u0154\u0001\u0000\u0000\u0000\u0156\u0155"+
		"\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000\u0000\u0000\u0158\u015b"+
		"\u0003\u0012\t\u0000\u0159\u015a\u0005Y\u0000\u0000\u015a\u015c\u0003"+
		"\u00aeW\u0000\u015b\u0159\u0001\u0000\u0000\u0000\u015b\u015c\u0001\u0000"+
		"\u0000\u0000\u015c\u015d\u0001\u0000\u0000\u0000\u015d\u015e\u0005>\u0000"+
		"\u0000\u015e\u015f\u0003`0\u0000\u015f\u0011\u0001\u0000\u0000\u0000\u0160"+
		"\u01b2\u0005;\u0000\u0000\u0161\u0164\u0003\u0014\n\u0000\u0162\u0163"+
		"\u0005A\u0000\u0000\u0163\u0165\u0003\u00aeW\u0000\u0164\u0162\u0001\u0000"+
		"\u0000\u0000\u0164\u0165\u0001\u0000\u0000\u0000\u0165\u016e\u0001\u0000"+
		"\u0000\u0000\u0166\u0167\u0005=\u0000\u0000\u0167\u016a\u0003\u0014\n"+
		"\u0000\u0168\u0169\u0005A\u0000\u0000\u0169\u016b\u0003\u00aeW\u0000\u016a"+
		"\u0168\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b"+
		"\u016d\u0001\u0000\u0000\u0000\u016c\u0166\u0001\u0000\u0000\u0000\u016d"+
		"\u0170\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000\u0000\u0000\u016e"+
		"\u016f\u0001\u0000\u0000\u0000\u016f\u0192\u0001\u0000\u0000\u0000\u0170"+
		"\u016e\u0001\u0000\u0000\u0000\u0171\u0190\u0005=\u0000\u0000\u0172\u0174"+
		"\u0005:\u0000\u0000\u0173\u0175\u0003\u0014\n\u0000\u0174\u0173\u0001"+
		"\u0000\u0000\u0000\u0174\u0175\u0001\u0000\u0000\u0000\u0175\u017e\u0001"+
		"\u0000\u0000\u0000\u0176\u0177\u0005=\u0000\u0000\u0177\u017a\u0003\u0014"+
		"\n\u0000\u0178\u0179\u0005A\u0000\u0000\u0179\u017b\u0003\u00aeW\u0000"+
		"\u017a\u0178\u0001\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000"+
		"\u017b\u017d\u0001\u0000\u0000\u0000\u017c\u0176\u0001\u0000\u0000\u0000"+
		"\u017d\u0180\u0001\u0000\u0000\u0000\u017e\u017c\u0001\u0000\u0000\u0000"+
		"\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0189\u0001\u0000\u0000\u0000"+
		"\u0180\u017e\u0001\u0000\u0000\u0000\u0181\u0187\u0005=\u0000\u0000\u0182"+
		"\u0183\u0005@\u0000\u0000\u0183\u0185\u0003\u0014\n\u0000\u0184\u0186"+
		"\u0005=\u0000\u0000\u0185\u0184\u0001\u0000\u0000\u0000\u0185\u0186\u0001"+
		"\u0000\u0000\u0000\u0186\u0188\u0001\u0000\u0000\u0000\u0187\u0182\u0001"+
		"\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000\u0000\u0188\u018a\u0001"+
		"\u0000\u0000\u0000\u0189\u0181\u0001\u0000\u0000\u0000\u0189\u018a\u0001"+
		"\u0000\u0000\u0000\u018a\u0191\u0001\u0000\u0000\u0000\u018b\u018c\u0005"+
		"@\u0000\u0000\u018c\u018e\u0003\u0014\n\u0000\u018d\u018f\u0005=\u0000"+
		"\u0000\u018e\u018d\u0001\u0000\u0000\u0000\u018e\u018f\u0001\u0000\u0000"+
		"\u0000\u018f\u0191\u0001\u0000\u0000\u0000\u0190\u0172\u0001\u0000\u0000"+
		"\u0000\u0190\u018b\u0001\u0000\u0000\u0000\u0190\u0191\u0001\u0000\u0000"+
		"\u0000\u0191\u0193\u0001\u0000\u0000\u0000\u0192\u0171\u0001\u0000\u0000"+
		"\u0000\u0192\u0193\u0001\u0000\u0000\u0000\u0193\u01b3\u0001\u0000\u0000"+
		"\u0000\u0194\u0196\u0005:\u0000\u0000\u0195\u0197\u0003\u0014\n\u0000"+
		"\u0196\u0195\u0001\u0000\u0000\u0000\u0196\u0197\u0001\u0000\u0000\u0000"+
		"\u0197\u01a0\u0001\u0000\u0000\u0000\u0198\u0199\u0005=\u0000\u0000\u0199"+
		"\u019c\u0003\u0014\n\u0000\u019a\u019b\u0005A\u0000\u0000\u019b\u019d"+
		"\u0003\u00aeW\u0000\u019c\u019a\u0001\u0000\u0000\u0000\u019c\u019d\u0001"+
		"\u0000\u0000\u0000\u019d\u019f\u0001\u0000\u0000\u0000\u019e\u0198\u0001"+
		"\u0000\u0000\u0000\u019f\u01a2\u0001\u0000\u0000\u0000\u01a0\u019e\u0001"+
		"\u0000\u0000\u0000\u01a0\u01a1\u0001\u0000\u0000\u0000\u01a1\u01ab\u0001"+
		"\u0000\u0000\u0000\u01a2\u01a0\u0001\u0000\u0000\u0000\u01a3\u01a9\u0005"+
		"=\u0000\u0000\u01a4\u01a5\u0005@\u0000\u0000\u01a5\u01a7\u0003\u0014\n"+
		"\u0000\u01a6\u01a8\u0005=\u0000\u0000\u01a7\u01a6\u0001\u0000\u0000\u0000"+
		"\u01a7\u01a8\u0001\u0000\u0000\u0000\u01a8\u01aa\u0001\u0000\u0000\u0000"+
		"\u01a9\u01a4\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000\u0000\u0000"+
		"\u01aa\u01ac\u0001\u0000\u0000\u0000\u01ab\u01a3\u0001\u0000\u0000\u0000"+
		"\u01ab\u01ac\u0001\u0000\u0000\u0000\u01ac\u01b3\u0001\u0000\u0000\u0000"+
		"\u01ad\u01ae\u0005@\u0000\u0000\u01ae\u01b0\u0003\u0014\n\u0000\u01af"+
		"\u01b1\u0005=\u0000\u0000\u01b0\u01af\u0001\u0000\u0000\u0000\u01b0\u01b1"+
		"\u0001\u0000\u0000\u0000\u01b1\u01b3\u0001\u0000\u0000\u0000\u01b2\u0161"+
		"\u0001\u0000\u0000\u0000\u01b2\u0194\u0001\u0000\u0000\u0000\u01b2\u01ad"+
		"\u0001\u0000\u0000\u0000\u01b2\u01b3\u0001\u0000\u0000\u0000\u01b3\u01b4"+
		"\u0001\u0000\u0000\u0000\u01b4\u01b5\u0005<\u0000\u0000\u01b5\u0013\u0001"+
		"\u0000\u0000\u0000\u01b6\u01b9\u0003\u00c8d\u0000\u01b7\u01b8\u0005>\u0000"+
		"\u0000\u01b8\u01ba\u0003\u00aeW\u0000\u01b9\u01b7\u0001\u0000\u0000\u0000"+
		"\u01b9\u01ba\u0001\u0000\u0000\u0000\u01ba\u01bf\u0001\u0000\u0000\u0000"+
		"\u01bb\u01bf\u0003\u00f2y\u0000\u01bc\u01bf\u0003\u00f8|\u0000\u01bd\u01bf"+
		"\u0003\u0106\u0083\u0000\u01be\u01b6\u0001\u0000\u0000\u0000\u01be\u01bb"+
		"\u0001\u0000\u0000\u0000\u01be\u01bc\u0001\u0000\u0000\u0000\u01be\u01bd"+
		"\u0001\u0000\u0000\u0000\u01bf\u0015\u0001\u0000\u0000\u0000\u01c0\u01c3"+
		"\u0003\u0018\f\u0000\u01c1\u01c2\u0005A\u0000\u0000\u01c2\u01c4\u0003"+
		"\u00aeW\u0000\u01c3\u01c1\u0001\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000"+
		"\u0000\u0000\u01c4\u01cd\u0001\u0000\u0000\u0000\u01c5\u01c6\u0005=\u0000"+
		"\u0000\u01c6\u01c9\u0003\u0018\f\u0000\u01c7\u01c8\u0005A\u0000\u0000"+
		"\u01c8\u01ca\u0003\u00aeW\u0000\u01c9\u01c7\u0001\u0000\u0000\u0000\u01c9"+
		"\u01ca\u0001\u0000\u0000\u0000\u01ca\u01cc\u0001\u0000\u0000\u0000\u01cb"+
		"\u01c5\u0001\u0000\u0000\u0000\u01cc\u01cf\u0001\u0000\u0000\u0000\u01cd"+
		"\u01cb\u0001\u0000\u0000\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce"+
		"\u01f1\u0001\u0000\u0000\u0000\u01cf\u01cd\u0001\u0000\u0000\u0000\u01d0"+
		"\u01ef\u0005=\u0000\u0000\u01d1\u01d3\u0005:\u0000\u0000\u01d2\u01d4\u0003"+
		"\u0018\f\u0000\u01d3\u01d2\u0001\u0000\u0000\u0000\u01d3\u01d4\u0001\u0000"+
		"\u0000\u0000\u01d4\u01dd\u0001\u0000\u0000\u0000\u01d5\u01d6\u0005=\u0000"+
		"\u0000\u01d6\u01d9\u0003\u0018\f\u0000\u01d7\u01d8\u0005A\u0000\u0000"+
		"\u01d8\u01da\u0003\u00aeW\u0000\u01d9\u01d7\u0001\u0000\u0000\u0000\u01d9"+
		"\u01da\u0001\u0000\u0000\u0000\u01da\u01dc\u0001\u0000\u0000\u0000\u01db"+
		"\u01d5\u0001\u0000\u0000\u0000\u01dc\u01df\u0001\u0000\u0000\u0000\u01dd"+
		"\u01db\u0001\u0000\u0000\u0000\u01dd\u01de\u0001\u0000\u0000\u0000\u01de"+
		"\u01e8\u0001\u0000\u0000\u0000\u01df\u01dd\u0001\u0000\u0000\u0000\u01e0"+
		"\u01e6\u0005=\u0000\u0000\u01e1\u01e2\u0005@\u0000\u0000\u01e2\u01e4\u0003"+
		"\u0018\f\u0000\u01e3\u01e5\u0005=\u0000\u0000\u01e4\u01e3\u0001\u0000"+
		"\u0000\u0000\u01e4\u01e5\u0001\u0000\u0000\u0000\u01e5\u01e7\u0001\u0000"+
		"\u0000\u0000\u01e6\u01e1\u0001\u0000\u0000\u0000\u01e6\u01e7\u0001\u0000"+
		"\u0000\u0000\u01e7\u01e9\u0001\u0000\u0000\u0000\u01e8\u01e0\u0001\u0000"+
		"\u0000\u0000\u01e8\u01e9\u0001\u0000\u0000\u0000\u01e9\u01f0\u0001\u0000"+
		"\u0000\u0000\u01ea\u01eb\u0005@\u0000\u0000\u01eb\u01ed\u0003\u0018\f"+
		"\u0000\u01ec\u01ee\u0005=\u0000\u0000\u01ed\u01ec\u0001\u0000\u0000\u0000"+
		"\u01ed\u01ee\u0001\u0000\u0000\u0000\u01ee\u01f0\u0001\u0000\u0000\u0000"+
		"\u01ef\u01d1\u0001\u0000\u0000\u0000\u01ef\u01ea\u0001\u0000\u0000\u0000"+
		"\u01ef\u01f0\u0001\u0000\u0000\u0000\u01f0\u01f2\u0001\u0000\u0000\u0000"+
		"\u01f1\u01d0\u0001\u0000\u0000\u0000\u01f1\u01f2\u0001\u0000\u0000\u0000"+
		"\u01f2\u0212\u0001\u0000\u0000\u0000\u01f3\u01f5\u0005:\u0000\u0000\u01f4"+
		"\u01f6\u0003\u0018\f\u0000\u01f5\u01f4\u0001\u0000\u0000\u0000\u01f5\u01f6"+
		"\u0001\u0000\u0000\u0000\u01f6\u01ff\u0001\u0000\u0000\u0000\u01f7\u01f8"+
		"\u0005=\u0000\u0000\u01f8\u01fb\u0003\u0018\f\u0000\u01f9\u01fa\u0005"+
		"A\u0000\u0000\u01fa\u01fc\u0003\u00aeW\u0000\u01fb\u01f9\u0001\u0000\u0000"+
		"\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000\u01fc\u01fe\u0001\u0000\u0000"+
		"\u0000\u01fd\u01f7\u0001\u0000\u0000\u0000\u01fe\u0201\u0001\u0000\u0000"+
		"\u0000\u01ff\u01fd\u0001\u0000\u0000\u0000\u01ff\u0200\u0001\u0000\u0000"+
		"\u0000\u0200\u020a\u0001\u0000\u0000\u0000\u0201\u01ff\u0001\u0000\u0000"+
		"\u0000\u0202\u0208\u0005=\u0000\u0000\u0203\u0204\u0005@\u0000\u0000\u0204"+
		"\u0206\u0003\u0018\f\u0000\u0205\u0207\u0005=\u0000\u0000\u0206\u0205"+
		"\u0001\u0000\u0000\u0000\u0206\u0207\u0001\u0000\u0000\u0000\u0207\u0209"+
		"\u0001\u0000\u0000\u0000\u0208\u0203\u0001\u0000\u0000\u0000\u0208\u0209"+
		"\u0001\u0000\u0000\u0000\u0209\u020b\u0001\u0000\u0000\u0000\u020a\u0202"+
		"\u0001\u0000\u0000\u0000\u020a\u020b\u0001\u0000\u0000\u0000\u020b\u0212"+
		"\u0001\u0000\u0000\u0000\u020c\u020d\u0005@\u0000\u0000\u020d\u020f\u0003"+
		"\u0018\f\u0000\u020e\u0210\u0005=\u0000\u0000\u020f\u020e\u0001\u0000"+
		"\u0000\u0000\u020f\u0210\u0001\u0000\u0000\u0000\u0210\u0212\u0001\u0000"+
		"\u0000\u0000\u0211\u01c0\u0001\u0000\u0000\u0000\u0211\u01f3\u0001\u0000"+
		"\u0000\u0000\u0211\u020c\u0001\u0000\u0000\u0000\u0212\u0017\u0001\u0000"+
		"\u0000\u0000\u0213\u0218\u0003\u00c8d\u0000\u0214\u0218\u0003\u00f2y\u0000"+
		"\u0215\u0218\u0003\u00f8|\u0000\u0216\u0218\u0003\u0106\u0083\u0000\u0217"+
		"\u0213\u0001\u0000\u0000\u0000\u0217\u0214\u0001\u0000\u0000\u0000\u0217"+
		"\u0215\u0001\u0000\u0000\u0000\u0217\u0216\u0001\u0000\u0000\u0000\u0218"+
		"\u0019\u0001\u0000\u0000\u0000\u0219\u021a\u0003\u0108\u0084\u0000\u021a"+
		"\u021b\u0005.\u0000\u0000\u021b\u0222\u0001\u0000\u0000\u0000\u021c\u021d"+
		"\u0003\u00fe\u007f\u0000\u021d\u021e\u0005.\u0000\u0000\u021e\u0222\u0001"+
		"\u0000\u0000\u0000\u021f\u0222\u0003\u001c\u000e\u0000\u0220\u0222\u0003"+
		"N\'\u0000\u0221\u0219\u0001\u0000\u0000\u0000\u0221\u021c\u0001\u0000"+
		"\u0000\u0000\u0221\u021f\u0001\u0000\u0000\u0000\u0221\u0220\u0001\u0000"+
		"\u0000\u0000\u0222\u001b\u0001\u0000\u0000\u0000\u0223\u0228\u0003\u001e"+
		"\u000f\u0000\u0224\u0225\u0005?\u0000\u0000\u0225\u0227\u0003\u001e\u000f"+
		"\u0000\u0226\u0224\u0001\u0000\u0000\u0000\u0227\u022a\u0001\u0000\u0000"+
		"\u0000\u0228\u0226\u0001\u0000\u0000\u0000\u0228\u0229\u0001\u0000\u0000"+
		"\u0000\u0229\u022c\u0001\u0000\u0000\u0000\u022a\u0228\u0001\u0000\u0000"+
		"\u0000\u022b\u022d\u0005?\u0000\u0000\u022c\u022b\u0001\u0000\u0000\u0000"+
		"\u022c\u022d\u0001\u0000\u0000\u0000\u022d\u022e\u0001\u0000\u0000\u0000"+
		"\u022e\u022f\u0005.\u0000\u0000\u022f\u001d\u0001\u0000\u0000\u0000\u0230"+
		"\u023a\u0003\u00f0x\u0000\u0231\u023a\u0003 \u0010\u0000\u0232\u023a\u0003"+
		"(\u0014\u0000\u0233\u023a\u0003*\u0015\u0000\u0234\u023a\u0003,\u0016"+
		"\u0000\u0235\u023a\u00038\u001c\u0000\u0236\u023a\u0003H$\u0000\u0237"+
		"\u023a\u0003J%\u0000\u0238\u023a\u0003L&\u0000\u0239\u0230\u0001\u0000"+
		"\u0000\u0000\u0239\u0231\u0001\u0000\u0000\u0000\u0239\u0232\u0001\u0000"+
		"\u0000\u0000\u0239\u0233\u0001\u0000\u0000\u0000\u0239\u0234\u0001\u0000"+
		"\u0000\u0000\u0239\u0235\u0001\u0000\u0000\u0000\u0239\u0236\u0001\u0000"+
		"\u0000\u0000\u0239\u0237\u0001\u0000\u0000\u0000\u0239\u0238\u0001\u0000"+
		"\u0000\u0000\u023a\u001f\u0001\u0000\u0000\u0000\u023b\u024c\u0003$\u0012"+
		"\u0000\u023c\u024d\u0003\"\u0011\u0000\u023d\u0240\u0003&\u0013\u0000"+
		"\u023e\u0241\u0003\u00e8t\u0000\u023f\u0241\u0003\u00d6k\u0000\u0240\u023e"+
		"\u0001\u0000\u0000\u0000\u0240\u023f\u0001\u0000\u0000\u0000\u0241\u024d"+
		"\u0001\u0000\u0000\u0000\u0242\u0245\u0005A\u0000\u0000\u0243\u0246\u0003"+
		"\u00e8t\u0000\u0244\u0246\u0003$\u0012\u0000\u0245\u0243\u0001\u0000\u0000"+
		"\u0000\u0245\u0244\u0001\u0000\u0000\u0000\u0246\u0248\u0001\u0000\u0000"+
		"\u0000\u0247\u0242\u0001\u0000\u0000\u0000\u0248\u024b\u0001\u0000\u0000"+
		"\u0000\u0249\u0247\u0001\u0000\u0000\u0000\u0249\u024a\u0001\u0000\u0000"+
		"\u0000\u024a\u024d\u0001\u0000\u0000\u0000\u024b\u0249\u0001\u0000\u0000"+
		"\u0000\u024c\u023c\u0001\u0000\u0000\u0000\u024c\u023d\u0001\u0000\u0000"+
		"\u0000\u024c\u0249\u0001\u0000\u0000\u0000\u024d!\u0001\u0000\u0000\u0000"+
		"\u024e\u024f\u0005>\u0000\u0000\u024f\u0252\u0003\u00aeW\u0000\u0250\u0251"+
		"\u0005A\u0000\u0000\u0251\u0253\u0003\u00aeW\u0000\u0252\u0250\u0001\u0000"+
		"\u0000\u0000\u0252\u0253\u0001\u0000\u0000\u0000\u0253#\u0001\u0000\u0000"+
		"\u0000\u0254\u0257\u0003\u00aeW\u0000\u0255\u0257\u0003\u00c0`\u0000\u0256"+
		"\u0254\u0001\u0000\u0000\u0000\u0256\u0255\u0001\u0000\u0000\u0000\u0257"+
		"\u025f\u0001\u0000\u0000\u0000\u0258\u025b\u0005=\u0000\u0000\u0259\u025c"+
		"\u0003\u00aeW\u0000\u025a\u025c\u0003\u00c0`\u0000\u025b\u0259\u0001\u0000"+
		"\u0000\u0000\u025b\u025a\u0001\u0000\u0000\u0000\u025c\u025e\u0001\u0000"+
		"\u0000\u0000\u025d\u0258\u0001\u0000\u0000\u0000\u025e\u0261\u0001\u0000"+
		"\u0000\u0000\u025f\u025d\u0001\u0000\u0000\u0000\u025f\u0260\u0001\u0000"+
		"\u0000\u0000\u0260\u0263\u0001\u0000\u0000\u0000\u0261\u025f\u0001\u0000"+
		"\u0000\u0000\u0262\u0264\u0005=\u0000\u0000\u0263\u0262\u0001\u0000\u0000"+
		"\u0000\u0263\u0264\u0001\u0000\u0000\u0000\u0264%\u0001\u0000\u0000\u0000"+
		"\u0265\u0266\u0007\u0000\u0000\u0000\u0266\'\u0001\u0000\u0000\u0000\u0267"+
		"\u0268\u0005\u0010\u0000\u0000\u0268\u0269\u0003\u00d4j\u0000\u0269)\u0001"+
		"\u0000\u0000\u0000\u026a\u026b\u0005#\u0000\u0000\u026b+\u0001\u0000\u0000"+
		"\u0000\u026c\u0272\u0003.\u0017\u0000\u026d\u0272\u00030\u0018\u0000\u026e"+
		"\u0272\u00032\u0019\u0000\u026f\u0272\u00036\u001b\u0000\u0270\u0272\u0003"+
		"4\u001a\u0000\u0271\u026c\u0001\u0000\u0000\u0000\u0271\u026d\u0001\u0000"+
		"\u0000\u0000\u0271\u026e\u0001\u0000\u0000\u0000\u0271\u026f\u0001\u0000"+
		"\u0000\u0000\u0271\u0270\u0001\u0000\u0000\u0000\u0272-\u0001\u0000\u0000"+
		"\u0000\u0273\u0274\u0005\u000b\u0000\u0000\u0274/\u0001\u0000\u0000\u0000"+
		"\u0275\u0276\u0005\u000e\u0000\u0000\u02761\u0001\u0000\u0000\u0000\u0277"+
		"\u0279\u0005%\u0000\u0000\u0278\u027a\u0003\u00d6k\u0000\u0279\u0278\u0001"+
		"\u0000\u0000\u0000\u0279\u027a\u0001\u0000\u0000\u0000\u027a3\u0001\u0000"+
		"\u0000\u0000\u027b\u027c\u0003\u00e8t\u0000\u027c5\u0001\u0000\u0000\u0000"+
		"\u027d\u0283\u0005$\u0000\u0000\u027e\u0281\u0003\u00aeW\u0000\u027f\u0280"+
		"\u0005\u0017\u0000\u0000\u0280\u0282\u0003\u00aeW\u0000\u0281\u027f\u0001"+
		"\u0000\u0000\u0000\u0281\u0282\u0001\u0000\u0000\u0000\u0282\u0284\u0001"+
		"\u0000\u0000\u0000\u0283\u027e\u0001\u0000\u0000\u0000\u0283\u0284\u0001"+
		"\u0000\u0000\u0000\u02847\u0001\u0000\u0000\u0000\u0285\u0288\u0003:\u001d"+
		"\u0000\u0286\u0288\u0003<\u001e\u0000\u0287\u0285\u0001\u0000\u0000\u0000"+
		"\u0287\u0286\u0001\u0000\u0000\u0000\u02889\u0001\u0000\u0000\u0000\u0289"+
		"\u028a\u0005\u001a\u0000\u0000\u028a\u028b\u0003D\"\u0000\u028b;\u0001"+
		"\u0000\u0000\u0000\u028c\u0299\u0005\u0017\u0000\u0000\u028d\u028f\u0007"+
		"\u0001\u0000\u0000\u028e\u028d\u0001\u0000\u0000\u0000\u028f\u0292\u0001"+
		"\u0000\u0000\u0000\u0290\u028e\u0001\u0000\u0000\u0000\u0290\u0291\u0001"+
		"\u0000\u0000\u0000\u0291\u0293\u0001\u0000\u0000\u0000\u0292\u0290\u0001"+
		"\u0000\u0000\u0000\u0293\u029a\u0003F#\u0000\u0294\u0296\u0007\u0001\u0000"+
		"\u0000\u0295\u0294\u0001\u0000\u0000\u0000\u0296\u0297\u0001\u0000\u0000"+
		"\u0000\u0297\u0295\u0001\u0000\u0000\u0000\u0297\u0298\u0001\u0000\u0000"+
		"\u0000\u0298\u029a\u0001\u0000\u0000\u0000\u0299\u0290\u0001\u0000\u0000"+
		"\u0000\u0299\u0295\u0001\u0000\u0000\u0000\u029a\u029b\u0001\u0000\u0000"+
		"\u0000\u029b\u02a2\u0005\u001a\u0000\u0000\u029c\u02a3\u0005:\u0000\u0000"+
		"\u029d\u029e\u0005;\u0000\u0000\u029e\u029f\u0003B!\u0000\u029f\u02a0"+
		"\u0005<\u0000\u0000\u02a0\u02a3\u0001\u0000\u0000\u0000\u02a1\u02a3\u0003"+
		"B!\u0000\u02a2\u029c\u0001\u0000\u0000\u0000\u02a2\u029d\u0001\u0000\u0000"+
		"\u0000\u02a2\u02a1\u0001\u0000\u0000\u0000\u02a3=\u0001\u0000\u0000\u0000"+
		"\u02a4\u02a7\u0003\u00c8d\u0000\u02a5\u02a6\u0005\u0007\u0000\u0000\u02a6"+
		"\u02a8\u0003\u00c8d\u0000\u02a7\u02a5\u0001\u0000\u0000\u0000\u02a7\u02a8"+
		"\u0001\u0000\u0000\u0000\u02a8?\u0001\u0000\u0000\u0000\u02a9\u02ac\u0003"+
		"F#\u0000\u02aa\u02ab\u0005\u0007\u0000\u0000\u02ab\u02ad\u0003\u00c8d"+
		"\u0000\u02ac\u02aa\u0001\u0000\u0000\u0000\u02ac\u02ad\u0001\u0000\u0000"+
		"\u0000\u02adA\u0001\u0000\u0000\u0000\u02ae\u02b3\u0003>\u001f\u0000\u02af"+
		"\u02b0\u0005=\u0000\u0000\u02b0\u02b2\u0003>\u001f\u0000\u02b1\u02af\u0001"+
		"\u0000\u0000\u0000\u02b2\u02b5\u0001\u0000\u0000\u0000\u02b3\u02b1\u0001"+
		"\u0000\u0000\u0000\u02b3\u02b4\u0001\u0000\u0000\u0000\u02b4\u02b7\u0001"+
		"\u0000\u0000\u0000\u02b5\u02b3\u0001\u0000\u0000\u0000\u02b6\u02b8\u0005"+
		"=\u0000\u0000\u02b7\u02b6\u0001\u0000\u0000\u0000\u02b7\u02b8\u0001\u0000"+
		"\u0000\u0000\u02b8C\u0001\u0000\u0000\u0000\u02b9\u02be\u0003@ \u0000"+
		"\u02ba\u02bb\u0005=\u0000\u0000\u02bb\u02bd\u0003@ \u0000\u02bc\u02ba"+
		"\u0001\u0000\u0000\u0000\u02bd\u02c0\u0001\u0000\u0000\u0000\u02be\u02bc"+
		"\u0001\u0000\u0000\u0000\u02be\u02bf\u0001\u0000\u0000\u0000\u02bfE\u0001"+
		"\u0000\u0000\u0000\u02c0\u02be\u0001\u0000\u0000\u0000\u02c1\u02c6\u0003"+
		"\u00c8d\u0000\u02c2\u02c3\u00058\u0000\u0000\u02c3\u02c5\u0003\u00c8d"+
		"\u0000\u02c4\u02c2\u0001\u0000\u0000\u0000\u02c5\u02c8\u0001\u0000\u0000"+
		"\u0000\u02c6\u02c4\u0001\u0000\u0000\u0000\u02c6\u02c7\u0001\u0000\u0000"+
		"\u0000\u02c7G\u0001\u0000\u0000\u0000\u02c8\u02c6\u0001\u0000\u0000\u0000"+
		"\u02c9\u02ca\u0005\u0018\u0000\u0000\u02ca\u02cf\u0003\u00c8d\u0000\u02cb"+
		"\u02cc\u0005=\u0000\u0000\u02cc\u02ce\u0003\u00c8d\u0000\u02cd\u02cb\u0001"+
		"\u0000\u0000\u0000\u02ce\u02d1\u0001\u0000\u0000\u0000\u02cf\u02cd\u0001"+
		"\u0000\u0000\u0000\u02cf\u02d0\u0001\u0000\u0000\u0000\u02d0I\u0001\u0000"+
		"\u0000\u0000\u02d1\u02cf\u0001\u0000\u0000\u0000\u02d2\u02d3\u0005 \u0000"+
		"\u0000\u02d3\u02d8\u0003\u00c8d\u0000\u02d4\u02d5\u0005=\u0000\u0000\u02d5"+
		"\u02d7\u0003\u00c8d\u0000\u02d6\u02d4\u0001\u0000\u0000\u0000\u02d7\u02da"+
		"\u0001\u0000\u0000\u0000\u02d8\u02d6\u0001\u0000\u0000\u0000\u02d8\u02d9"+
		"\u0001\u0000\u0000\u0000\u02d9K\u0001\u0000\u0000\u0000\u02da\u02d8\u0001"+
		"\u0000\u0000\u0000\u02db\u02dc\u0005\b\u0000\u0000\u02dc\u02df\u0003\u00ae"+
		"W\u0000\u02dd\u02de\u0005=\u0000\u0000\u02de\u02e0\u0003\u00aeW\u0000"+
		"\u02df\u02dd\u0001\u0000\u0000\u0000\u02df\u02e0\u0001\u0000\u0000\u0000"+
		"\u02e0M\u0001\u0000\u0000\u0000\u02e1\u02ed\u0003\u0100\u0080\u0000\u02e2"+
		"\u02ed\u0003R)\u0000\u02e3\u02ed\u0003T*\u0000\u02e4\u02ed\u0003V+\u0000"+
		"\u02e5\u02ed\u0003X,\u0000\u02e6\u02ed\u0003Z-\u0000\u02e7\u02ed\u0003"+
		"\u0010\b\u0000\u02e8\u02ed\u0003\u00dam\u0000\u02e9\u02ed\u0003\f\u0006"+
		"\u0000\u02ea\u02ed\u0003P(\u0000\u02eb\u02ed\u0003b1\u0000\u02ec\u02e1"+
		"\u0001\u0000\u0000\u0000\u02ec\u02e2\u0001\u0000\u0000\u0000\u02ec\u02e3"+
		"\u0001\u0000\u0000\u0000\u02ec\u02e4\u0001\u0000\u0000\u0000\u02ec\u02e5"+
		"\u0001\u0000\u0000\u0000\u02ec\u02e6\u0001\u0000\u0000\u0000\u02ec\u02e7"+
		"\u0001\u0000\u0000\u0000\u02ec\u02e8\u0001\u0000\u0000\u0000\u02ec\u02e9"+
		"\u0001\u0000\u0000\u0000\u02ec\u02ea\u0001\u0000\u0000\u0000\u02ec\u02eb"+
		"\u0001\u0000\u0000\u0000\u02edO\u0001\u0000\u0000\u0000\u02ee\u02f2\u0005"+
		"\t\u0000\u0000\u02ef\u02f3\u0003\u0010\b\u0000\u02f0\u02f3\u0003Z-\u0000"+
		"\u02f1\u02f3\u0003V+\u0000\u02f2\u02ef\u0001\u0000\u0000\u0000\u02f2\u02f0"+
		"\u0001\u0000\u0000\u0000\u02f2\u02f1\u0001\u0000\u0000\u0000\u02f3Q\u0001"+
		"\u0000\u0000\u0000\u02f4\u02f5\u0005\u0019\u0000\u0000\u02f5\u02f6\u0003"+
		"\u00aeW\u0000\u02f6\u02f7\u0005>\u0000\u0000\u02f7\u02ff\u0003`0\u0000"+
		"\u02f8\u02f9\u0005\u0011\u0000\u0000\u02f9\u02fa\u0003\u00aeW\u0000\u02fa"+
		"\u02fb\u0005>\u0000\u0000\u02fb\u02fc\u0003`0\u0000\u02fc\u02fe\u0001"+
		"\u0000\u0000\u0000\u02fd\u02f8\u0001\u0000\u0000\u0000\u02fe\u0301\u0001"+
		"\u0000\u0000\u0000\u02ff\u02fd\u0001\u0000\u0000\u0000\u02ff\u0300\u0001"+
		"\u0000\u0000\u0000\u0300\u0305\u0001\u0000\u0000\u0000\u0301\u02ff\u0001"+
		"\u0000\u0000\u0000\u0302\u0303\u0005\u0012\u0000\u0000\u0303\u0304\u0005"+
		">\u0000\u0000\u0304\u0306\u0003`0\u0000\u0305\u0302\u0001\u0000\u0000"+
		"\u0000\u0305\u0306\u0001\u0000\u0000\u0000\u0306S\u0001\u0000\u0000\u0000"+
		"\u0307\u0308\u0005)\u0000\u0000\u0308\u0309\u0003\u00aeW\u0000\u0309\u030a"+
		"\u0005>\u0000\u0000\u030a\u030e\u0003`0\u0000\u030b\u030c\u0005\u0012"+
		"\u0000\u0000\u030c\u030d\u0005>\u0000\u0000\u030d\u030f\u0003`0\u0000"+
		"\u030e\u030b\u0001\u0000\u0000\u0000\u030e\u030f\u0001\u0000\u0000\u0000"+
		"\u030fU\u0001\u0000\u0000\u0000\u0310\u0311\u0005\u0016\u0000\u0000\u0311"+
		"\u0312\u0003\u00d4j\u0000\u0312\u0313\u0005\u001b\u0000\u0000\u0313\u0314"+
		"\u0003\u00d6k\u0000\u0314\u0315\u0005>\u0000\u0000\u0315\u0319\u0003`"+
		"0\u0000\u0316\u0317\u0005\u0012\u0000\u0000\u0317\u0318\u0005>\u0000\u0000"+
		"\u0318\u031a\u0003`0\u0000\u0319\u0316\u0001\u0000\u0000\u0000\u0319\u031a"+
		"\u0001\u0000\u0000\u0000\u031aW\u0001\u0000\u0000\u0000\u031b\u031c\u0005"+
		"\'\u0000\u0000\u031c\u031d\u0005>\u0000\u0000\u031d\u0333\u0003`0\u0000"+
		"\u031e\u031f\u0003^/\u0000\u031f\u0320\u0005>\u0000\u0000\u0320\u0321"+
		"\u0003`0\u0000\u0321\u0323\u0001\u0000\u0000\u0000\u0322\u031e\u0001\u0000"+
		"\u0000\u0000\u0323\u0324\u0001\u0000\u0000\u0000\u0324\u0322\u0001\u0000"+
		"\u0000\u0000\u0324\u0325\u0001\u0000\u0000\u0000\u0325\u0329\u0001\u0000"+
		"\u0000\u0000\u0326\u0327\u0005\u0012\u0000\u0000\u0327\u0328\u0005>\u0000"+
		"\u0000\u0328\u032a\u0003`0\u0000\u0329\u0326\u0001\u0000\u0000\u0000\u0329"+
		"\u032a\u0001\u0000\u0000\u0000\u032a\u032e\u0001\u0000\u0000\u0000\u032b"+
		"\u032c\u0005\u0015\u0000\u0000\u032c\u032d\u0005>\u0000\u0000\u032d\u032f"+
		"\u0003`0\u0000\u032e\u032b\u0001\u0000\u0000\u0000\u032e\u032f\u0001\u0000"+
		"\u0000\u0000\u032f\u0334\u0001\u0000\u0000\u0000\u0330\u0331\u0005\u0015"+
		"\u0000\u0000\u0331\u0332\u0005>\u0000\u0000\u0332\u0334\u0003`0\u0000"+
		"\u0333\u0322\u0001\u0000\u0000\u0000\u0333\u0330\u0001\u0000\u0000\u0000"+
		"\u0334Y\u0001\u0000\u0000\u0000\u0335\u0336\u0005*\u0000\u0000\u0336\u033b"+
		"\u0003\\.\u0000\u0337\u0338\u0005=\u0000\u0000\u0338\u033a\u0003\\.\u0000"+
		"\u0339\u0337\u0001\u0000\u0000\u0000\u033a\u033d\u0001\u0000\u0000\u0000"+
		"\u033b\u0339\u0001\u0000\u0000\u0000\u033b\u033c\u0001\u0000\u0000\u0000"+
		"\u033c\u033e\u0001\u0000\u0000\u0000\u033d\u033b\u0001\u0000\u0000\u0000"+
		"\u033e\u033f\u0005>\u0000\u0000\u033f\u0340\u0003`0\u0000\u0340[\u0001"+
		"\u0000\u0000\u0000\u0341\u0344\u0003\u00aeW\u0000\u0342\u0343\u0005\u0007"+
		"\u0000\u0000\u0343\u0345\u0003\u00c2a\u0000\u0344\u0342\u0001\u0000\u0000"+
		"\u0000\u0344\u0345\u0001\u0000\u0000\u0000\u0345]\u0001\u0000\u0000\u0000"+
		"\u0346\u034c\u0005\u0013\u0000\u0000\u0347\u034a\u0003\u00aeW\u0000\u0348"+
		"\u0349\u0005\u0007\u0000\u0000\u0349\u034b\u0003\u00c8d\u0000\u034a\u0348"+
		"\u0001\u0000\u0000\u0000\u034a\u034b\u0001\u0000\u0000\u0000\u034b\u034d"+
		"\u0001\u0000\u0000\u0000\u034c\u0347\u0001\u0000\u0000\u0000\u034c\u034d"+
		"\u0001\u0000\u0000\u0000\u034d_\u0001\u0000\u0000\u0000\u034e\u0359\u0003"+
		"\u001c\u000e\u0000\u034f\u0350\u0005.\u0000\u0000\u0350\u0352\u0005\u0001"+
		"\u0000\u0000\u0351\u0353\u0003\u001a\r\u0000\u0352\u0351\u0001\u0000\u0000"+
		"\u0000\u0353\u0354\u0001\u0000\u0000\u0000\u0354\u0352\u0001\u0000\u0000"+
		"\u0000\u0354\u0355\u0001\u0000\u0000\u0000\u0355\u0356\u0001\u0000\u0000"+
		"\u0000\u0356\u0357\u0005\u0002\u0000\u0000\u0357\u0359\u0001\u0000\u0000"+
		"\u0000\u0358\u034e\u0001\u0000\u0000\u0000\u0358\u034f\u0001\u0000\u0000"+
		"\u0000\u0359a\u0001\u0000\u0000\u0000\u035a\u035b\u0005\u001e\u0000\u0000"+
		"\u035b\u035c\u0003d2\u0000\u035c\u035d\u0005>\u0000\u0000\u035d\u035e"+
		"\u0005.\u0000\u0000\u035e\u0360\u0005\u0001\u0000\u0000\u035f\u0361\u0003"+
		"j5\u0000\u0360\u035f\u0001\u0000\u0000\u0000\u0361\u0362\u0001\u0000\u0000"+
		"\u0000\u0362\u0360\u0001\u0000\u0000\u0000\u0362\u0363\u0001\u0000\u0000"+
		"\u0000\u0363\u0364\u0001\u0000\u0000\u0000\u0364\u0365\u0005\u0002\u0000"+
		"\u0000\u0365c\u0001\u0000\u0000\u0000\u0366\u0367\u0003h4\u0000\u0367"+
		"\u0369\u0005=\u0000\u0000\u0368\u036a\u0003f3\u0000\u0369\u0368\u0001"+
		"\u0000\u0000\u0000\u0369\u036a\u0001\u0000\u0000\u0000\u036a\u036d\u0001"+
		"\u0000\u0000\u0000\u036b\u036d\u0003\u00aeW\u0000\u036c\u0366\u0001\u0000"+
		"\u0000\u0000\u036c\u036b\u0001\u0000\u0000\u0000\u036de\u0001\u0000\u0000"+
		"\u0000\u036e\u0370\u0005=\u0000\u0000\u036f\u0371\u0003h4\u0000\u0370"+
		"\u036f\u0001\u0000\u0000\u0000\u0371\u0372\u0001\u0000\u0000\u0000\u0372"+
		"\u0370\u0001\u0000\u0000\u0000\u0372\u0373\u0001\u0000\u0000\u0000\u0373"+
		"\u0375\u0001\u0000\u0000\u0000\u0374\u0376\u0005=\u0000\u0000\u0375\u0374"+
		"\u0001\u0000\u0000\u0000\u0375\u0376\u0001\u0000\u0000\u0000\u0376g\u0001"+
		"\u0000\u0000\u0000\u0377\u0378\u0005:\u0000\u0000\u0378\u037b\u0003\u00c2"+
		"a\u0000\u0379\u037b\u0003\u00aeW\u0000\u037a\u0377\u0001\u0000\u0000\u0000"+
		"\u037a\u0379\u0001\u0000\u0000\u0000\u037bi\u0001\u0000\u0000\u0000\u037c"+
		"\u037d\u0005\f\u0000\u0000\u037d\u037f\u0003n7\u0000\u037e\u0380\u0003"+
		"l6\u0000\u037f\u037e\u0001\u0000\u0000\u0000\u037f\u0380\u0001\u0000\u0000"+
		"\u0000\u0380\u0381\u0001\u0000\u0000\u0000\u0381\u0382\u0005>\u0000\u0000"+
		"\u0382\u0383\u0003`0\u0000\u0383k\u0001\u0000\u0000\u0000\u0384\u0385"+
		"\u0005\u0019\u0000\u0000\u0385\u0386\u0003\u00aeW\u0000\u0386m\u0001\u0000"+
		"\u0000\u0000\u0387\u038a\u0003\u0096K\u0000\u0388\u038a\u0003p8\u0000"+
		"\u0389\u0387\u0001\u0000\u0000\u0000\u0389\u0388\u0001\u0000\u0000\u0000"+
		"\u038ao\u0001\u0000\u0000\u0000\u038b\u038e\u0003r9\u0000\u038c\u038e"+
		"\u0003t:\u0000\u038d\u038b\u0001\u0000\u0000\u0000\u038d\u038c\u0001\u0000"+
		"\u0000\u0000\u038eq\u0001\u0000\u0000\u0000\u038f\u0390\u0003t:\u0000"+
		"\u0390\u0391\u0005\u0007\u0000\u0000\u0391\u0392\u0003\u0088D\u0000\u0392"+
		"s\u0001\u0000\u0000\u0000\u0393\u0398\u0003v;\u0000\u0394\u0395\u0005"+
		"D\u0000\u0000\u0395\u0397\u0003v;\u0000\u0396\u0394\u0001\u0000\u0000"+
		"\u0000\u0397\u039a\u0001\u0000\u0000\u0000\u0398\u0396\u0001\u0000\u0000"+
		"\u0000\u0398\u0399\u0001\u0000\u0000\u0000\u0399u\u0001\u0000\u0000\u0000"+
		"\u039a\u0398\u0001\u0000\u0000\u0000\u039b\u03a4\u0003\u008aE\u0000\u039c"+
		"\u03a4\u0003x<\u0000\u039d\u03a4\u0003\u0086C\u0000\u039e\u03a4\u0003"+
		"\u008cF\u0000\u039f\u03a4\u0003\u0092I\u0000\u03a0\u03a4\u0003\u0094J"+
		"\u0000\u03a1\u03a4\u0003\u009eO\u0000\u03a2\u03a4\u0003\u00a6S\u0000\u03a3"+
		"\u039b\u0001\u0000\u0000\u0000\u03a3\u039c\u0001\u0000\u0000\u0000\u03a3"+
		"\u039d\u0001\u0000\u0000\u0000\u03a3\u039e\u0001\u0000\u0000\u0000\u03a3"+
		"\u039f\u0001\u0000\u0000\u0000\u03a3\u03a0\u0001\u0000\u0000\u0000\u03a3"+
		"\u03a1\u0001\u0000\u0000\u0000\u03a3\u03a2\u0001\u0000\u0000\u0000\u03a4"+
		"w\u0001\u0000\u0000\u0000\u03a5\u03a6\u0003~?\u0000\u03a6\u03a7\u0004"+
		"<\u0000\u0000\u03a7\u03ae\u0001\u0000\u0000\u0000\u03a8\u03ae\u0003|>"+
		"\u0000\u03a9\u03ae\u0003\u00ecv\u0000\u03aa\u03ae\u0005\u001f\u0000\u0000"+
		"\u03ab\u03ae\u0005&\u0000\u0000\u03ac\u03ae\u0005\u0014\u0000\u0000\u03ad"+
		"\u03a5\u0001\u0000\u0000\u0000\u03ad\u03a8\u0001\u0000\u0000\u0000\u03ad"+
		"\u03a9\u0001\u0000\u0000\u0000\u03ad\u03aa\u0001\u0000\u0000\u0000\u03ad"+
		"\u03ab\u0001\u0000\u0000\u0000\u03ad\u03ac\u0001\u0000\u0000\u0000\u03ae"+
		"y\u0001\u0000\u0000\u0000\u03af\u03b0\u0003~?\u0000\u03b0\u03b1\u0004"+
		"=\u0001\u0000\u03b1\u03b8\u0001\u0000\u0000\u0000\u03b2\u03b8\u0003|>"+
		"\u0000\u03b3\u03b8\u0003\u00ecv\u0000\u03b4\u03b8\u0005\u001f\u0000\u0000"+
		"\u03b5\u03b8\u0005&\u0000\u0000\u03b6\u03b8\u0005\u0014\u0000\u0000\u03b7"+
		"\u03af\u0001\u0000\u0000\u0000\u03b7\u03b2\u0001\u0000\u0000\u0000\u03b7"+
		"\u03b3\u0001\u0000\u0000\u0000\u03b7\u03b4\u0001\u0000\u0000\u0000\u03b7"+
		"\u03b5\u0001\u0000\u0000\u0000\u03b7\u03b6\u0001\u0000\u0000\u0000\u03b8"+
		"{\u0001\u0000\u0000\u0000\u03b9\u03ba\u0003\u0080@\u0000\u03ba\u03bb\u0005"+
		"I\u0000\u0000\u03bb\u03bc\u0003\u0084B\u0000\u03bc\u03c2\u0001\u0000\u0000"+
		"\u0000\u03bd\u03be\u0003\u0080@\u0000\u03be\u03bf\u0005J\u0000\u0000\u03bf"+
		"\u03c0\u0003\u0084B\u0000\u03c0\u03c2\u0001\u0000\u0000\u0000\u03c1\u03b9"+
		"\u0001\u0000\u0000\u0000\u03c1\u03bd\u0001\u0000\u0000\u0000\u03c2}\u0001"+
		"\u0000\u0000\u0000\u03c3\u03c7\u0005\u0004\u0000\u0000\u03c4\u03c5\u0005"+
		"J\u0000\u0000\u03c5\u03c7\u0005\u0004\u0000\u0000\u03c6\u03c3\u0001\u0000"+
		"\u0000\u0000\u03c6\u03c4\u0001\u0000\u0000\u0000\u03c7\u007f\u0001\u0000"+
		"\u0000\u0000\u03c8\u03cc\u0003\u0082A\u0000\u03c9\u03ca\u0005J\u0000\u0000"+
		"\u03ca\u03cc\u0003\u0082A\u0000\u03cb\u03c8\u0001\u0000\u0000\u0000\u03cb"+
		"\u03c9\u0001\u0000\u0000\u0000\u03cc\u0081\u0001\u0000\u0000\u0000\u03cd"+
		"\u03ce\u0005\u0004\u0000\u0000\u03ce\u0083\u0001\u0000\u0000\u0000\u03cf"+
		"\u03d0\u0005\u0004\u0000\u0000\u03d0\u0085\u0001\u0000\u0000\u0000\u03d1"+
		"\u03d2\u0003\u0088D\u0000\u03d2\u0087\u0001\u0000\u0000\u0000\u03d3\u03d4"+
		"\u0003\u00c8d\u0000\u03d4\u03d5\u0004D\u0002\u0000\u03d5\u0089\u0001\u0000"+
		"\u0000\u0000\u03d6\u03d7\u0005(\u0000\u0000\u03d7\u008b\u0001\u0000\u0000"+
		"\u0000\u03d8\u03d9\u0003\u008eG\u0000\u03d9\u03da\u0004F\u0003\u0000\u03da"+
		"\u008d\u0001\u0000\u0000\u0000\u03db\u03de\u0003\u00c8d\u0000\u03dc\u03dd"+
		"\u00058\u0000\u0000\u03dd\u03df\u0003\u00c8d\u0000\u03de\u03dc\u0001\u0000"+
		"\u0000\u0000\u03df\u03e0\u0001\u0000\u0000\u0000\u03e0\u03de\u0001\u0000"+
		"\u0000\u0000\u03e0\u03e1\u0001\u0000\u0000\u0000\u03e1\u008f\u0001\u0000"+
		"\u0000\u0000\u03e2\u03e5\u0003\u008eG\u0000\u03e3\u03e5\u0003\u00c8d\u0000"+
		"\u03e4\u03e2\u0001\u0000\u0000\u0000\u03e4\u03e3\u0001\u0000\u0000\u0000"+
		"\u03e5\u0091\u0001\u0000\u0000\u0000\u03e6\u03e7\u0005;\u0000\u0000\u03e7"+
		"\u03e8\u0003p8\u0000\u03e8\u03e9\u0005<\u0000\u0000\u03e9\u0093\u0001"+
		"\u0000\u0000\u0000\u03ea\u03ec\u0005B\u0000\u0000\u03eb\u03ed\u0003\u0098"+
		"L\u0000\u03ec\u03eb\u0001\u0000\u0000\u0000\u03ec\u03ed\u0001\u0000\u0000"+
		"\u0000\u03ed\u03ee\u0001\u0000\u0000\u0000\u03ee\u03f5\u0005C\u0000\u0000"+
		"\u03ef\u03f1\u0005;\u0000\u0000\u03f0\u03f2\u0003\u0096K\u0000\u03f1\u03f0"+
		"\u0001\u0000\u0000\u0000\u03f1\u03f2\u0001\u0000\u0000\u0000\u03f2\u03f3"+
		"\u0001\u0000\u0000\u0000\u03f3\u03f5\u0005<\u0000\u0000\u03f4\u03ea\u0001"+
		"\u0000\u0000\u0000\u03f4\u03ef\u0001\u0000\u0000\u0000\u03f5\u0095\u0001"+
		"\u0000\u0000\u0000\u03f6\u03f7\u0003\u009aM\u0000\u03f7\u03f9\u0005=\u0000"+
		"\u0000\u03f8\u03fa\u0003\u0098L\u0000\u03f9\u03f8\u0001\u0000\u0000\u0000"+
		"\u03f9\u03fa\u0001\u0000\u0000\u0000\u03fa\u0097\u0001\u0000\u0000\u0000"+
		"\u03fb\u0400\u0003\u009aM\u0000\u03fc\u03fd\u0005=\u0000\u0000\u03fd\u03ff"+
		"\u0003\u009aM\u0000\u03fe\u03fc\u0001\u0000\u0000\u0000\u03ff\u0402\u0001"+
		"\u0000\u0000\u0000\u0400\u03fe\u0001\u0000\u0000\u0000\u0400\u0401\u0001"+
		"\u0000\u0000\u0000\u0401\u0404\u0001\u0000\u0000\u0000\u0402\u0400\u0001"+
		"\u0000\u0000\u0000\u0403\u0405\u0005=\u0000\u0000\u0404\u0403\u0001\u0000"+
		"\u0000\u0000\u0404\u0405\u0001\u0000\u0000\u0000\u0405\u0099\u0001\u0000"+
		"\u0000\u0000\u0406\u0409\u0003\u009cN\u0000\u0407\u0409\u0003p8\u0000"+
		"\u0408\u0406\u0001\u0000\u0000\u0000\u0408\u0407\u0001\u0000\u0000\u0000"+
		"\u0409\u009b\u0001\u0000\u0000\u0000\u040a\u040b\u0005:\u0000\u0000\u040b"+
		"\u040f\u0003\u0088D\u0000\u040c\u040d\u0005:\u0000\u0000\u040d\u040f\u0003"+
		"\u008aE\u0000\u040e\u040a\u0001\u0000\u0000\u0000\u040e\u040c\u0001\u0000"+
		"\u0000\u0000\u040f\u009d\u0001\u0000\u0000\u0000\u0410\u0411\u0005O\u0000"+
		"\u0000\u0411\u042a\u0005P\u0000\u0000\u0412\u0413\u0005O\u0000\u0000\u0413"+
		"\u0415\u0003\u00a4R\u0000\u0414\u0416\u0005=\u0000\u0000\u0415\u0414\u0001"+
		"\u0000\u0000\u0000\u0415\u0416\u0001\u0000\u0000\u0000\u0416\u0417\u0001"+
		"\u0000\u0000\u0000\u0417\u0418\u0005P\u0000\u0000\u0418\u042a\u0001\u0000"+
		"\u0000\u0000\u0419\u041a\u0005O\u0000\u0000\u041a\u041b\u0003\u00a0P\u0000"+
		"\u041b\u041c\u0005=\u0000\u0000\u041c\u041e\u0003\u00a4R\u0000\u041d\u041f"+
		"\u0005=\u0000\u0000\u041e\u041d\u0001\u0000\u0000\u0000\u041e\u041f\u0001"+
		"\u0000\u0000\u0000\u041f\u0420\u0001\u0000\u0000\u0000\u0420\u0421\u0005"+
		"P\u0000\u0000\u0421\u042a\u0001\u0000\u0000\u0000\u0422\u0423\u0005O\u0000"+
		"\u0000\u0423\u0425\u0003\u00a0P\u0000\u0424\u0426\u0005=\u0000\u0000\u0425"+
		"\u0424\u0001\u0000\u0000\u0000\u0425\u0426\u0001\u0000\u0000\u0000\u0426"+
		"\u0427\u0001\u0000\u0000\u0000\u0427\u0428\u0005P\u0000\u0000\u0428\u042a"+
		"\u0001\u0000\u0000\u0000\u0429\u0410\u0001\u0000\u0000\u0000\u0429\u0412"+
		"\u0001\u0000\u0000\u0000\u0429\u0419\u0001\u0000\u0000\u0000\u0429\u0422"+
		"\u0001\u0000\u0000\u0000\u042a\u009f\u0001\u0000\u0000\u0000\u042b\u0430"+
		"\u0003\u00a2Q\u0000\u042c\u042d\u0005=\u0000\u0000\u042d\u042f\u0003\u00a2"+
		"Q\u0000\u042e\u042c\u0001\u0000\u0000\u0000\u042f\u0432\u0001\u0000\u0000"+
		"\u0000\u0430\u042e\u0001\u0000\u0000\u0000\u0430\u0431\u0001\u0000\u0000"+
		"\u0000\u0431\u00a1\u0001\u0000\u0000\u0000\u0432\u0430\u0001\u0000\u0000"+
		"\u0000\u0433\u0436\u0003z=\u0000\u0434\u0436\u0003\u008eG\u0000\u0435"+
		"\u0433\u0001\u0000\u0000\u0000\u0435\u0434\u0001\u0000\u0000\u0000\u0436"+
		"\u0437\u0001\u0000\u0000\u0000\u0437\u0438\u0005>\u0000\u0000\u0438\u0439"+
		"\u0003p8\u0000\u0439\u00a3\u0001\u0000\u0000\u0000\u043a\u043b\u0005@"+
		"\u0000\u0000\u043b\u043c\u0003\u0088D\u0000\u043c\u00a5\u0001\u0000\u0000"+
		"\u0000\u043d\u043e\u0003\u0090H\u0000\u043e\u043f\u0005;\u0000\u0000\u043f"+
		"\u0440\u0005<\u0000\u0000\u0440\u045c\u0001\u0000\u0000\u0000\u0441\u0442"+
		"\u0003\u0090H\u0000\u0442\u0443\u0005;\u0000\u0000\u0443\u0445\u0003\u00a8"+
		"T\u0000\u0444\u0446\u0005=\u0000\u0000\u0445\u0444\u0001\u0000\u0000\u0000"+
		"\u0445\u0446\u0001\u0000\u0000\u0000\u0446\u0447\u0001\u0000\u0000\u0000"+
		"\u0447\u0448\u0005<\u0000\u0000\u0448\u045c\u0001\u0000\u0000\u0000\u0449"+
		"\u044a\u0003\u0090H\u0000\u044a\u044b\u0005;\u0000\u0000\u044b\u044d\u0003"+
		"\u00aaU\u0000\u044c\u044e\u0005=\u0000\u0000\u044d\u044c\u0001\u0000\u0000"+
		"\u0000\u044d\u044e\u0001\u0000\u0000\u0000\u044e\u044f\u0001\u0000\u0000"+
		"\u0000\u044f\u0450\u0005<\u0000\u0000\u0450\u045c\u0001\u0000\u0000\u0000"+
		"\u0451\u0452\u0003\u0090H\u0000\u0452\u0453\u0005;\u0000\u0000\u0453\u0454"+
		"\u0003\u00a8T\u0000\u0454\u0455\u0005=\u0000\u0000\u0455\u0457\u0003\u00aa"+
		"U\u0000\u0456\u0458\u0005=\u0000\u0000\u0457\u0456\u0001\u0000\u0000\u0000"+
		"\u0457\u0458\u0001\u0000\u0000\u0000\u0458\u0459\u0001\u0000\u0000\u0000"+
		"\u0459\u045a\u0005<\u0000\u0000\u045a\u045c\u0001\u0000\u0000\u0000\u045b"+
		"\u043d\u0001\u0000\u0000\u0000\u045b\u0441\u0001\u0000\u0000\u0000\u045b"+
		"\u0449\u0001\u0000\u0000\u0000\u045b\u0451\u0001\u0000\u0000\u0000\u045c"+
		"\u00a7\u0001\u0000\u0000\u0000\u045d\u0462\u0003p8\u0000\u045e\u045f\u0005"+
		"=\u0000\u0000\u045f\u0461\u0003p8\u0000\u0460\u045e\u0001\u0000\u0000"+
		"\u0000\u0461\u0464\u0001\u0000\u0000\u0000\u0462\u0460\u0001\u0000\u0000"+
		"\u0000\u0462\u0463\u0001\u0000\u0000\u0000\u0463\u00a9\u0001\u0000\u0000"+
		"\u0000\u0464\u0462\u0001\u0000\u0000\u0000\u0465\u046a\u0003\u00acV\u0000"+
		"\u0466\u0467\u0005=\u0000\u0000\u0467\u0469\u0003\u00acV\u0000\u0468\u0466"+
		"\u0001\u0000\u0000\u0000\u0469\u046c\u0001\u0000\u0000\u0000\u046a\u0468"+
		"\u0001\u0000\u0000\u0000\u046a\u046b\u0001\u0000\u0000\u0000\u046b\u00ab"+
		"\u0001\u0000\u0000\u0000\u046c\u046a\u0001\u0000\u0000\u0000\u046d\u046e"+
		"\u0003\u00c8d\u0000\u046e\u046f\u0005A\u0000\u0000\u046f\u0470\u0003p"+
		"8\u0000\u0470\u00ad\u0001\u0000\u0000\u0000\u0471\u0477\u0003\u00b6[\u0000"+
		"\u0472\u0473\u0005\u0019\u0000\u0000\u0473\u0474\u0003\u00b6[\u0000\u0474"+
		"\u0475\u0005\u0012\u0000\u0000\u0475\u0476\u0003\u00aeW\u0000\u0476\u0478"+
		"\u0001\u0000\u0000\u0000\u0477\u0472\u0001\u0000\u0000\u0000\u0477\u0478"+
		"\u0001\u0000\u0000\u0000\u0478\u047b\u0001\u0000\u0000\u0000\u0479\u047b"+
		"\u0003\u00b2Y\u0000\u047a\u0471\u0001\u0000\u0000\u0000\u047a\u0479\u0001"+
		"\u0000\u0000\u0000\u047b\u00af\u0001\u0000\u0000\u0000\u047c\u047f\u0003"+
		"\u00b6[\u0000\u047d\u047f\u0003\u00b4Z\u0000\u047e\u047c\u0001\u0000\u0000"+
		"\u0000\u047e\u047d\u0001\u0000\u0000\u0000\u047f\u00b1\u0001\u0000\u0000"+
		"\u0000\u0480\u0482\u0005\u001d\u0000\u0000\u0481\u0483\u0003\u0016\u000b"+
		"\u0000\u0482\u0481\u0001\u0000\u0000\u0000\u0482\u0483\u0001\u0000\u0000"+
		"\u0000\u0483\u0484\u0001\u0000\u0000\u0000\u0484\u0485\u0005>\u0000\u0000"+
		"\u0485\u0486\u0003\u00aeW\u0000\u0486\u00b3\u0001\u0000\u0000\u0000\u0487"+
		"\u0489\u0005\u001d\u0000\u0000\u0488\u048a\u0003\u0016\u000b\u0000\u0489"+
		"\u0488\u0001\u0000\u0000\u0000\u0489\u048a\u0001\u0000\u0000\u0000\u048a"+
		"\u048b\u0001\u0000\u0000\u0000\u048b\u048c\u0005>\u0000\u0000\u048c\u048d"+
		"\u0003\u00b0X\u0000\u048d\u00b5\u0001\u0000\u0000\u0000\u048e\u0493\u0003"+
		"\u00b8\\\u0000\u048f\u0490\u0005\"\u0000\u0000\u0490\u0492\u0003\u00b8"+
		"\\\u0000\u0491\u048f\u0001\u0000\u0000\u0000\u0492\u0495\u0001\u0000\u0000"+
		"\u0000\u0493\u0491\u0001\u0000\u0000\u0000\u0493\u0494\u0001\u0000\u0000"+
		"\u0000\u0494\u00b7\u0001\u0000\u0000\u0000\u0495\u0493\u0001\u0000\u0000"+
		"\u0000\u0496\u049b\u0003\u00ba]\u0000\u0497\u0498\u0005\u0006\u0000\u0000"+
		"\u0498\u049a\u0003\u00ba]\u0000\u0499\u0497\u0001\u0000\u0000\u0000\u049a"+
		"\u049d\u0001\u0000\u0000\u0000\u049b\u0499\u0001\u0000\u0000\u0000\u049b"+
		"\u049c\u0001\u0000\u0000\u0000\u049c\u00b9\u0001\u0000\u0000\u0000\u049d"+
		"\u049b\u0001\u0000\u0000\u0000\u049e\u049f\u0005!\u0000\u0000\u049f\u04a2"+
		"\u0003\u00ba]\u0000\u04a0\u04a2\u0003\u00bc^\u0000\u04a1\u049e\u0001\u0000"+
		"\u0000\u0000\u04a1\u04a0\u0001\u0000\u0000\u0000\u04a2\u00bb\u0001\u0000"+
		"\u0000\u0000\u04a3\u04a9\u0003\u00c2a\u0000\u04a4\u04a5\u0003\u00be_\u0000"+
		"\u04a5\u04a6\u0003\u00c2a\u0000\u04a6\u04a8\u0001\u0000\u0000\u0000\u04a7"+
		"\u04a4\u0001\u0000\u0000\u0000\u04a8\u04ab\u0001\u0000\u0000\u0000\u04a9"+
		"\u04a7\u0001\u0000\u0000\u0000\u04a9\u04aa\u0001\u0000\u0000\u0000\u04aa"+
		"\u00bd\u0001\u0000\u0000\u0000\u04ab\u04a9\u0001\u0000\u0000\u0000\u04ac"+
		"\u04ba\u0005Q\u0000\u0000\u04ad\u04ba\u0005R\u0000\u0000\u04ae\u04ba\u0005"+
		"S\u0000\u0000\u04af\u04ba\u0005T\u0000\u0000\u04b0\u04ba\u0005U\u0000"+
		"\u0000\u04b1\u04ba\u0005V\u0000\u0000\u04b2\u04ba\u0005W\u0000\u0000\u04b3"+
		"\u04ba\u0005\u001b\u0000\u0000\u04b4\u04b5\u0005!\u0000\u0000\u04b5\u04ba"+
		"\u0005\u001b\u0000\u0000\u04b6\u04ba\u0005\u001c\u0000\u0000\u04b7\u04b8"+
		"\u0005\u001c\u0000\u0000\u04b8\u04ba\u0005!\u0000\u0000\u04b9\u04ac\u0001"+
		"\u0000\u0000\u0000\u04b9\u04ad\u0001\u0000\u0000\u0000\u04b9\u04ae\u0001"+
		"\u0000\u0000\u0000\u04b9\u04af\u0001\u0000\u0000\u0000\u04b9\u04b0\u0001"+
		"\u0000\u0000\u0000\u04b9\u04b1\u0001\u0000\u0000\u0000\u04b9\u04b2\u0001"+
		"\u0000\u0000\u0000\u04b9\u04b3\u0001\u0000\u0000\u0000\u04b9\u04b4\u0001"+
		"\u0000\u0000\u0000\u04b9\u04b6\u0001\u0000\u0000\u0000\u04b9\u04b7\u0001"+
		"\u0000\u0000\u0000\u04ba\u00bf\u0001\u0000\u0000\u0000\u04bb\u04bc\u0005"+
		":\u0000\u0000\u04bc\u04bd\u0003\u00c2a\u0000\u04bd\u00c1\u0001\u0000\u0000"+
		"\u0000\u04be\u04bf\u0006a\uffff\uffff\u0000\u04bf\u04c9\u0003\u00f2y\u0000"+
		"\u04c0\u04c9\u0003\u00f8|\u0000\u04c1\u04c9\u0003\u00c4b\u0000\u04c2\u04c4"+
		"\u0007\u0002\u0000\u0000\u04c3\u04c2\u0001\u0000\u0000\u0000\u04c4\u04c5"+
		"\u0001\u0000\u0000\u0000\u04c5\u04c3\u0001\u0000\u0000\u0000\u04c5\u04c6"+
		"\u0001\u0000\u0000\u0000\u04c6\u04c7\u0001\u0000\u0000\u0000\u04c7\u04c9"+
		"\u0003\u00c2a\u0007\u04c8\u04be\u0001\u0000\u0000\u0000\u04c8\u04c0\u0001"+
		"\u0000\u0000\u0000\u04c8\u04c1\u0001\u0000\u0000\u0000\u04c8\u04c3\u0001"+
		"\u0000\u0000\u0000\u04c9\u04e1\u0001\u0000\u0000\u0000\u04ca\u04cb\n\b"+
		"\u0000\u0000\u04cb\u04cc\u0005@\u0000\u0000\u04cc\u04e0\u0003\u00c2a\t"+
		"\u04cd\u04ce\n\u0006\u0000\u0000\u04ce\u04cf\u0007\u0003\u0000\u0000\u04cf"+
		"\u04e0\u0003\u00c2a\u0007\u04d0\u04d1\n\u0005\u0000\u0000\u04d1\u04d2"+
		"\u0007\u0004\u0000\u0000\u04d2\u04e0\u0003\u00c2a\u0006\u04d3\u04d4\n"+
		"\u0004\u0000\u0000\u04d4\u04d5\u0007\u0005\u0000\u0000\u04d5\u04e0\u0003"+
		"\u00c2a\u0005\u04d6\u04d7\n\u0003\u0000\u0000\u04d7\u04d8\u0005F\u0000"+
		"\u0000\u04d8\u04e0\u0003\u00c2a\u0004\u04d9\u04da\n\u0002\u0000\u0000"+
		"\u04da\u04db\u0005E\u0000\u0000\u04db\u04e0\u0003\u00c2a\u0003\u04dc\u04dd"+
		"\n\u0001\u0000\u0000\u04dd\u04de\u0005D\u0000\u0000\u04de\u04e0\u0003"+
		"\u00c2a\u0002\u04df\u04ca\u0001\u0000\u0000\u0000\u04df\u04cd\u0001\u0000"+
		"\u0000\u0000\u04df\u04d0\u0001\u0000\u0000\u0000\u04df\u04d3\u0001\u0000"+
		"\u0000\u0000\u04df\u04d6\u0001\u0000\u0000\u0000\u04df\u04d9\u0001\u0000"+
		"\u0000\u0000\u04df\u04dc\u0001\u0000\u0000\u0000\u04e0\u04e3\u0001\u0000"+
		"\u0000\u0000\u04e1\u04df\u0001\u0000\u0000\u0000\u04e1\u04e2\u0001\u0000"+
		"\u0000\u0000\u04e2\u00c3\u0001\u0000\u0000\u0000\u04e3\u04e1\u0001\u0000"+
		"\u0000\u0000\u04e4\u04e6\u0005\n\u0000\u0000\u04e5\u04e4\u0001\u0000\u0000"+
		"\u0000\u04e5\u04e6\u0001\u0000\u0000\u0000\u04e6\u04e7\u0001\u0000\u0000"+
		"\u0000\u04e7\u04eb\u0003\u00c6c\u0000\u04e8\u04ea\u0003\u00ccf\u0000\u04e9"+
		"\u04e8\u0001\u0000\u0000\u0000\u04ea\u04ed\u0001\u0000\u0000\u0000\u04eb"+
		"\u04e9\u0001\u0000\u0000\u0000\u04eb\u04ec\u0001\u0000\u0000\u0000\u04ec"+
		"\u00c5\u0001\u0000\u0000\u0000\u04ed\u04eb\u0001\u0000\u0000\u0000\u04ee"+
		"\u04f1\u0005;\u0000\u0000\u04ef\u04f2\u0003\u00e8t\u0000\u04f0\u04f2\u0003"+
		"\u00cae\u0000\u04f1\u04ef\u0001\u0000\u0000\u0000\u04f1\u04f0\u0001\u0000"+
		"\u0000\u0000\u04f1\u04f2\u0001\u0000\u0000\u0000\u04f2\u04f3\u0001\u0000"+
		"\u0000\u0000\u04f3\u050b\u0005<\u0000\u0000\u04f4\u04f6\u0005B\u0000\u0000"+
		"\u04f5\u04f7\u0003\u00cae\u0000\u04f6\u04f5\u0001\u0000\u0000\u0000\u04f6"+
		"\u04f7\u0001\u0000\u0000\u0000\u04f7\u04f8\u0001\u0000\u0000\u0000\u04f8"+
		"\u050b\u0005C\u0000\u0000\u04f9\u04fb\u0005O\u0000\u0000\u04fa\u04fc\u0003"+
		"\u00d8l\u0000\u04fb\u04fa\u0001\u0000\u0000\u0000\u04fb\u04fc\u0001\u0000"+
		"\u0000\u0000\u04fc\u04fd\u0001\u0000\u0000\u0000\u04fd\u050b\u0005P\u0000"+
		"\u0000\u04fe\u050b\u0003\u00f4z\u0000\u04ff\u050b\u0003\u00c8d\u0000\u0500"+
		"\u050b\u0005\u0004\u0000\u0000\u0501\u0503\u0005\u0003\u0000\u0000\u0502"+
		"\u0501\u0001\u0000\u0000\u0000\u0503\u0504\u0001\u0000\u0000\u0000\u0504"+
		"\u0502\u0001\u0000\u0000\u0000\u0504\u0505\u0001\u0000\u0000\u0000\u0505"+
		"\u050b\u0001\u0000\u0000\u0000\u0506\u050b\u00059\u0000\u0000\u0507\u050b"+
		"\u0005\u001f\u0000\u0000\u0508\u050b\u0005&\u0000\u0000\u0509\u050b\u0005"+
		"\u0014\u0000\u0000\u050a\u04ee\u0001\u0000\u0000\u0000\u050a\u04f4\u0001"+
		"\u0000\u0000\u0000\u050a\u04f9\u0001\u0000\u0000\u0000\u050a\u04fe\u0001"+
		"\u0000\u0000\u0000\u050a\u04ff\u0001\u0000\u0000\u0000\u050a\u0500\u0001"+
		"\u0000\u0000\u0000\u050a\u0502\u0001\u0000\u0000\u0000\u050a\u0506\u0001"+
		"\u0000\u0000\u0000\u050a\u0507\u0001\u0000\u0000\u0000\u050a\u0508\u0001"+
		"\u0000\u0000\u0000\u050a\u0509\u0001\u0000\u0000\u0000\u050b\u00c7\u0001"+
		"\u0000\u0000\u0000\u050c\u050d\u0007\u0006\u0000\u0000\u050d\u00c9\u0001"+
		"\u0000\u0000\u0000\u050e\u0512\u0003\u0106\u0083\u0000\u050f\u0512\u0003"+
		"\u00aeW\u0000\u0510\u0512\u0003\u00c0`\u0000\u0511\u050e\u0001\u0000\u0000"+
		"\u0000\u0511\u050f\u0001\u0000\u0000\u0000\u0511\u0510\u0001\u0000\u0000"+
		"\u0000\u0512\u0522\u0001\u0000\u0000\u0000\u0513\u0523\u0003\u00e2q\u0000"+
		"\u0514\u0518\u0005=\u0000\u0000\u0515\u0519\u0003\u0106\u0083\u0000\u0516"+
		"\u0519\u0003\u00aeW\u0000\u0517\u0519\u0003\u00c0`\u0000\u0518\u0515\u0001"+
		"\u0000\u0000\u0000\u0518\u0516\u0001\u0000\u0000\u0000\u0518\u0517\u0001"+
		"\u0000\u0000\u0000\u0519\u051b\u0001\u0000\u0000\u0000\u051a\u0514\u0001"+
		"\u0000\u0000\u0000\u051b\u051e\u0001\u0000\u0000\u0000\u051c\u051a\u0001"+
		"\u0000\u0000\u0000\u051c\u051d\u0001\u0000\u0000\u0000\u051d\u0520\u0001"+
		"\u0000\u0000\u0000\u051e\u051c\u0001\u0000\u0000\u0000\u051f\u0521\u0005"+
		"=\u0000\u0000\u0520\u051f\u0001\u0000\u0000\u0000\u0520\u0521\u0001\u0000"+
		"\u0000\u0000\u0521\u0523\u0001\u0000\u0000\u0000\u0522\u0513\u0001\u0000"+
		"\u0000\u0000\u0522\u051c\u0001\u0000\u0000\u0000\u0523\u00cb\u0001\u0000"+
		"\u0000\u0000\u0524\u0526\u0005;\u0000\u0000\u0525\u0527\u0003\u00dcn\u0000"+
		"\u0526\u0525\u0001\u0000\u0000\u0000\u0526\u0527\u0001\u0000\u0000\u0000"+
		"\u0527\u0528\u0001\u0000\u0000\u0000\u0528\u0533\u0005<\u0000\u0000\u0529"+
		"\u052a\u0005B\u0000\u0000\u052a\u052b\u0003\u00ceg\u0000\u052b\u052c\u0005"+
		"C\u0000\u0000\u052c\u0533\u0001\u0000\u0000\u0000\u052d\u0530\u00058\u0000"+
		"\u0000\u052e\u0531\u0003\u00f4z\u0000\u052f\u0531\u0003\u00c8d\u0000\u0530"+
		"\u052e\u0001\u0000\u0000\u0000\u0530\u052f\u0001\u0000\u0000\u0000\u0531"+
		"\u0533\u0001\u0000\u0000\u0000\u0532\u0524\u0001\u0000\u0000\u0000\u0532"+
		"\u0529\u0001\u0000\u0000\u0000\u0532\u052d\u0001\u0000\u0000\u0000\u0533"+
		"\u00cd\u0001\u0000\u0000\u0000\u0534\u0539\u0003\u00d0h\u0000\u0535\u0536"+
		"\u0005=\u0000\u0000\u0536\u0538\u0003\u00d0h\u0000\u0537\u0535\u0001\u0000"+
		"\u0000\u0000\u0538\u053b\u0001\u0000\u0000\u0000\u0539\u0537\u0001\u0000"+
		"\u0000\u0000\u0539\u053a\u0001\u0000\u0000\u0000\u053a\u053d\u0001\u0000"+
		"\u0000\u0000\u053b\u0539\u0001\u0000\u0000\u0000\u053c\u053e\u0005=\u0000"+
		"\u0000\u053d\u053c\u0001\u0000\u0000\u0000\u053d\u053e\u0001\u0000\u0000"+
		"\u0000\u053e\u00cf\u0001\u0000\u0000\u0000\u053f\u054b\u0003\u00aeW\u0000"+
		"\u0540\u0542\u0003\u00aeW\u0000\u0541\u0540\u0001\u0000\u0000\u0000\u0541"+
		"\u0542\u0001\u0000\u0000\u0000\u0542\u0543\u0001\u0000\u0000\u0000\u0543"+
		"\u0545\u0005>\u0000\u0000\u0544\u0546\u0003\u00aeW\u0000\u0545\u0544\u0001"+
		"\u0000\u0000\u0000\u0545\u0546\u0001\u0000\u0000\u0000\u0546\u0548\u0001"+
		"\u0000\u0000\u0000\u0547\u0549\u0003\u00d2i\u0000\u0548\u0547\u0001\u0000"+
		"\u0000\u0000\u0548\u0549\u0001\u0000\u0000\u0000\u0549\u054b\u0001\u0000"+
		"\u0000\u0000\u054a\u053f\u0001\u0000\u0000\u0000\u054a\u0541\u0001\u0000"+
		"\u0000\u0000\u054b\u00d1\u0001\u0000\u0000\u0000\u054c\u054e\u0005>\u0000"+
		"\u0000\u054d\u054f\u0003\u00aeW\u0000\u054e\u054d\u0001\u0000\u0000\u0000"+
		"\u054e\u054f\u0001\u0000\u0000\u0000\u054f\u00d3\u0001\u0000\u0000\u0000"+
		"\u0550\u0553\u0003\u00c2a\u0000\u0551\u0553\u0003\u00c0`\u0000\u0552\u0550"+
		"\u0001\u0000\u0000\u0000\u0552\u0551\u0001\u0000\u0000\u0000\u0553\u055b"+
		"\u0001\u0000\u0000\u0000\u0554\u0557\u0005=\u0000\u0000\u0555\u0558\u0003"+
		"\u00c2a\u0000\u0556\u0558\u0003\u00c0`\u0000\u0557\u0555\u0001\u0000\u0000"+
		"\u0000\u0557\u0556\u0001\u0000\u0000\u0000\u0558\u055a\u0001\u0000\u0000"+
		"\u0000\u0559\u0554\u0001\u0000\u0000\u0000\u055a\u055d\u0001\u0000\u0000"+
		"\u0000\u055b\u0559\u0001\u0000\u0000\u0000\u055b\u055c\u0001\u0000\u0000"+
		"\u0000\u055c\u055f\u0001\u0000\u0000\u0000\u055d\u055b\u0001\u0000\u0000"+
		"\u0000\u055e\u0560\u0005=\u0000\u0000\u055f\u055e\u0001\u0000\u0000\u0000"+
		"\u055f\u0560\u0001\u0000\u0000\u0000\u0560\u00d5\u0001\u0000\u0000\u0000"+
		"\u0561\u0564\u0003\u00aeW\u0000\u0562\u0564\u0003\u0106\u0083\u0000\u0563"+
		"\u0561\u0001\u0000\u0000\u0000\u0563\u0562\u0001\u0000\u0000\u0000\u0564"+
		"\u056c\u0001\u0000\u0000\u0000\u0565\u0568\u0005=\u0000\u0000\u0566\u0569"+
		"\u0003\u00aeW\u0000\u0567\u0569\u0003\u0106\u0083\u0000\u0568\u0566\u0001"+
		"\u0000\u0000\u0000\u0568\u0567\u0001\u0000\u0000\u0000\u0569\u056b\u0001"+
		"\u0000\u0000\u0000\u056a\u0565\u0001\u0000\u0000\u0000\u056b\u056e\u0001"+
		"\u0000\u0000\u0000\u056c\u056a\u0001\u0000\u0000\u0000\u056c\u056d\u0001"+
		"\u0000\u0000\u0000\u056d\u0570\u0001\u0000\u0000\u0000\u056e\u056c\u0001"+
		"\u0000\u0000\u0000\u056f\u0571\u0005=\u0000\u0000\u0570\u056f\u0001\u0000"+
		"\u0000\u0000\u0570\u0571\u0001\u0000\u0000\u0000\u0571\u00d7\u0001\u0000"+
		"\u0000\u0000\u0572\u0573\u0003\u00aeW\u0000\u0573\u0574\u0005>\u0000\u0000"+
		"\u0574\u0575\u0003\u00aeW\u0000\u0575\u0579\u0001\u0000\u0000\u0000\u0576"+
		"\u0577\u0005@\u0000\u0000\u0577\u0579\u0003\u00c2a\u0000\u0578\u0572\u0001"+
		"\u0000\u0000\u0000\u0578\u0576\u0001\u0000\u0000\u0000\u0579\u058c\u0001"+
		"\u0000\u0000\u0000\u057a\u058d\u0003\u00e2q\u0000\u057b\u0582\u0005=\u0000"+
		"\u0000\u057c\u057d\u0003\u00aeW\u0000\u057d\u057e\u0005>\u0000\u0000\u057e"+
		"\u057f\u0003\u00aeW\u0000\u057f\u0583\u0001\u0000\u0000\u0000\u0580\u0581"+
		"\u0005@\u0000\u0000\u0581\u0583\u0003\u00c2a\u0000\u0582\u057c\u0001\u0000"+
		"\u0000\u0000\u0582\u0580\u0001\u0000\u0000\u0000\u0583\u0585\u0001\u0000"+
		"\u0000\u0000\u0584\u057b\u0001\u0000\u0000\u0000\u0585\u0588\u0001\u0000"+
		"\u0000\u0000\u0586\u0584\u0001\u0000\u0000\u0000\u0586\u0587\u0001\u0000"+
		"\u0000\u0000\u0587\u058a\u0001\u0000\u0000\u0000\u0588\u0586\u0001\u0000"+
		"\u0000\u0000\u0589\u058b\u0005=\u0000\u0000\u058a\u0589\u0001\u0000\u0000"+
		"\u0000\u058a\u058b\u0001\u0000\u0000\u0000\u058b\u058d\u0001\u0000\u0000"+
		"\u0000\u058c\u057a\u0001\u0000\u0000\u0000\u058c\u0586\u0001\u0000\u0000"+
		"\u0000\u058d\u05a3\u0001\u0000\u0000\u0000\u058e\u0591\u0003\u00aeW\u0000"+
		"\u058f\u0591\u0003\u00c0`\u0000\u0590\u058e\u0001\u0000\u0000\u0000\u0590"+
		"\u058f\u0001\u0000\u0000\u0000\u0591\u05a0\u0001\u0000\u0000\u0000\u0592"+
		"\u05a1\u0003\u00e2q\u0000\u0593\u0596\u0005=\u0000\u0000\u0594\u0597\u0003"+
		"\u00aeW\u0000\u0595\u0597\u0003\u00c0`\u0000\u0596\u0594\u0001\u0000\u0000"+
		"\u0000\u0596\u0595\u0001\u0000\u0000\u0000\u0597\u0599\u0001\u0000\u0000"+
		"\u0000\u0598\u0593\u0001\u0000\u0000\u0000\u0599\u059c\u0001\u0000\u0000"+
		"\u0000\u059a\u0598\u0001\u0000\u0000\u0000\u059a\u059b\u0001\u0000\u0000"+
		"\u0000\u059b\u059e\u0001\u0000\u0000\u0000\u059c\u059a\u0001\u0000\u0000"+
		"\u0000\u059d\u059f\u0005=\u0000\u0000\u059e\u059d\u0001\u0000\u0000\u0000"+
		"\u059e\u059f\u0001\u0000\u0000\u0000\u059f\u05a1\u0001\u0000\u0000\u0000"+
		"\u05a0\u0592\u0001\u0000\u0000\u0000\u05a0\u059a\u0001\u0000\u0000\u0000"+
		"\u05a1\u05a3\u0001\u0000\u0000\u0000\u05a2\u0578\u0001\u0000\u0000\u0000"+
		"\u05a2\u0590\u0001\u0000\u0000\u0000\u05a3\u00d9\u0001\u0000\u0000\u0000"+
		"\u05a4\u05a8\u0005\r\u0000\u0000\u05a5\u05a9\u0003\u00c8d\u0000\u05a6"+
		"\u05a9\u0003\u00f6{\u0000\u05a7\u05a9\u0003\u00fc~\u0000\u05a8\u05a5\u0001"+
		"\u0000\u0000\u0000\u05a8\u05a6\u0001\u0000\u0000\u0000\u05a8\u05a7\u0001"+
		"\u0000\u0000\u0000\u05a9\u05af\u0001\u0000\u0000\u0000\u05aa\u05ac\u0005"+
		";\u0000\u0000\u05ab\u05ad\u0003\u00dcn\u0000\u05ac\u05ab\u0001\u0000\u0000"+
		"\u0000\u05ac\u05ad\u0001\u0000\u0000\u0000\u05ad\u05ae\u0001\u0000\u0000"+
		"\u0000\u05ae\u05b0\u0005<\u0000\u0000\u05af\u05aa\u0001\u0000\u0000\u0000"+
		"\u05af\u05b0\u0001\u0000\u0000\u0000\u05b0\u05b1\u0001\u0000\u0000\u0000"+
		"\u05b1\u05b2\u0005>\u0000\u0000\u05b2\u05b3\u0003`0\u0000\u05b3\u00db"+
		"\u0001\u0000\u0000\u0000\u05b4\u05b8\u0003\u0106\u0083\u0000\u05b5\u05b8"+
		"\u0003\u00f8|\u0000\u05b6\u05b8\u0003\u00deo\u0000\u05b7\u05b4\u0001\u0000"+
		"\u0000\u0000\u05b7\u05b5\u0001\u0000\u0000\u0000\u05b7\u05b6\u0001\u0000"+
		"\u0000\u0000\u05b8\u05c1\u0001\u0000\u0000\u0000\u05b9\u05bd\u0005=\u0000"+
		"\u0000\u05ba\u05be\u0003\u0106\u0083\u0000\u05bb\u05be\u0003\u00f8|\u0000"+
		"\u05bc\u05be\u0003\u00deo\u0000\u05bd\u05ba\u0001\u0000\u0000\u0000\u05bd"+
		"\u05bb\u0001\u0000\u0000\u0000\u05bd\u05bc\u0001\u0000\u0000\u0000\u05be"+
		"\u05c0\u0001\u0000\u0000\u0000\u05bf\u05b9\u0001\u0000\u0000\u0000\u05c0"+
		"\u05c3\u0001\u0000\u0000\u0000\u05c1\u05bf\u0001\u0000\u0000\u0000\u05c1"+
		"\u05c2\u0001\u0000\u0000\u0000\u05c2\u05c5\u0001\u0000\u0000\u0000\u05c3"+
		"\u05c1\u0001\u0000\u0000\u0000\u05c4\u05c6\u0005=\u0000\u0000\u05c5\u05c4"+
		"\u0001\u0000\u0000\u0000\u05c5\u05c6\u0001\u0000\u0000\u0000\u05c6\u00dd"+
		"\u0001\u0000\u0000\u0000\u05c7\u05d5\u0003\u00fc~\u0000\u05c8\u05ca\u0003"+
		"\u00aeW\u0000\u05c9\u05cb\u0003\u00e2q\u0000\u05ca\u05c9\u0001\u0000\u0000"+
		"\u0000\u05ca\u05cb\u0001\u0000\u0000\u0000\u05cb\u05d5\u0001\u0000\u0000"+
		"\u0000\u05cc\u05cd\u0003\u00aeW\u0000\u05cd\u05ce\u0005A\u0000\u0000\u05ce"+
		"\u05cf\u0003\u00aeW\u0000\u05cf\u05d5\u0001\u0000\u0000\u0000\u05d0\u05d1"+
		"\u0005@\u0000\u0000\u05d1\u05d5\u0003\u00aeW\u0000\u05d2\u05d3\u0005:"+
		"\u0000\u0000\u05d3\u05d5\u0003\u00aeW\u0000\u05d4\u05c7\u0001\u0000\u0000"+
		"\u0000\u05d4\u05c8\u0001\u0000\u0000\u0000\u05d4\u05cc\u0001\u0000\u0000"+
		"\u0000\u05d4\u05d0\u0001\u0000\u0000\u0000\u05d4\u05d2\u0001\u0000\u0000"+
		"\u0000\u05d5\u00df\u0001\u0000\u0000\u0000\u05d6\u05d9\u0003\u00e2q\u0000"+
		"\u05d7\u05d9\u0003\u00e4r\u0000\u05d8\u05d6\u0001\u0000\u0000\u0000\u05d8"+
		"\u05d7\u0001\u0000\u0000\u0000\u05d9\u00e1\u0001\u0000\u0000\u0000\u05da"+
		"\u05dc\u0005\t\u0000\u0000\u05db\u05da\u0001\u0000\u0000\u0000\u05db\u05dc"+
		"\u0001\u0000\u0000\u0000\u05dc\u05dd\u0001\u0000\u0000\u0000\u05dd\u05de"+
		"\u0005\u0016\u0000\u0000\u05de\u05df\u0003\u00d4j\u0000\u05df\u05e0\u0005"+
		"\u001b\u0000\u0000\u05e0\u05e2\u0003\u00b6[\u0000\u05e1\u05e3\u0003\u00e0"+
		"p\u0000\u05e2\u05e1\u0001\u0000\u0000\u0000\u05e2\u05e3\u0001\u0000\u0000"+
		"\u0000\u05e3\u00e3\u0001\u0000\u0000\u0000\u05e4\u05e5\u0005\u0019\u0000"+
		"\u0000\u05e5\u05e7\u0003\u00b0X\u0000\u05e6\u05e8\u0003\u00e0p\u0000\u05e7"+
		"\u05e6\u0001\u0000\u0000\u0000\u05e7\u05e8\u0001\u0000\u0000\u0000\u05e8"+
		"\u00e5\u0001\u0000\u0000\u0000\u05e9\u05ea\u0003\u00c8d\u0000\u05ea\u00e7"+
		"\u0001\u0000\u0000\u0000\u05eb\u05ed\u0005+\u0000\u0000\u05ec\u05ee\u0003"+
		"\u00eau\u0000\u05ed\u05ec\u0001\u0000\u0000\u0000\u05ed\u05ee\u0001\u0000"+
		"\u0000\u0000\u05ee\u00e9\u0001\u0000\u0000\u0000\u05ef\u05f0\u0005\u0017"+
		"\u0000\u0000\u05f0\u05f3\u0003\u00aeW\u0000\u05f1\u05f3\u0003\u00d6k\u0000"+
		"\u05f2\u05ef\u0001\u0000\u0000\u0000\u05f2\u05f1\u0001\u0000\u0000\u0000"+
		"\u05f3\u00eb\u0001\u0000\u0000\u0000\u05f4\u05f6\u0005\u0003\u0000\u0000"+
		"\u05f5\u05f4\u0001\u0000\u0000\u0000\u05f6\u05f7\u0001\u0000\u0000\u0000"+
		"\u05f7\u05f5\u0001\u0000\u0000\u0000\u05f7\u05f8\u0001\u0000\u0000\u0000"+
		"\u05f8\u00ed\u0001\u0000\u0000\u0000\u05f9\u05fa\u0005O\u0000\u0000\u05fa"+
		"\u05fe\u0005\u0004\u0000\u0000\u05fb\u05ff\u0005=\u0000\u0000\u05fc\u05fd"+
		"\u0005=\u0000\u0000\u05fd\u05ff\u0005\u0004\u0000\u0000\u05fe\u05fb\u0001"+
		"\u0000\u0000\u0000\u05fe\u05fc\u0001\u0000\u0000\u0000\u05fe\u05ff\u0001"+
		"\u0000\u0000\u0000\u05ff\u0600\u0001\u0000\u0000\u0000\u0600\u0601\u0005"+
		"P\u0000\u0000\u0601\u00ef\u0001\u0000\u0000\u0000\u0602\u0608\u0003\u00fa"+
		"}\u0000\u0603\u0608\u0003\u00fe\u007f\u0000\u0604\u0608\u0003\u00f6{\u0000"+
		"\u0605\u0608\u0003\u00f8|\u0000\u0606\u0608\u0003\u00fc~\u0000\u0607\u0602"+
		"\u0001\u0000\u0000\u0000\u0607\u0603\u0001\u0000\u0000\u0000\u0607\u0604"+
		"\u0001\u0000\u0000\u0000\u0607\u0605\u0001\u0000\u0000\u0000\u0607\u0606"+
		"\u0001\u0000\u0000\u0000\u0608\u00f1\u0001\u0000\u0000\u0000\u0609\u060e"+
		"\u0003\u0108\u0084\u0000\u060a\u060e\u0003\u00fc~\u0000\u060b\u060e\u0003"+
		"\u00fe\u007f\u0000\u060c\u060e\u0003\u00f6{\u0000\u060d\u0609\u0001\u0000"+
		"\u0000\u0000\u060d\u060a\u0001\u0000\u0000\u0000\u060d\u060b\u0001\u0000"+
		"\u0000\u0000\u060d\u060c\u0001\u0000\u0000\u0000\u060e\u00f3\u0001\u0000"+
		"\u0000\u0000\u060f\u0612\u0003\u00f6{\u0000\u0610\u0612\u0003\u00fc~\u0000"+
		"\u0611\u060f\u0001\u0000\u0000\u0000\u0611\u0610\u0001\u0000\u0000\u0000"+
		"\u0612\u00f5\u0001\u0000\u0000\u0000\u0613\u0614\u0005g\u0000\u0000\u0614"+
		"\u00f7\u0001\u0000\u0000\u0000\u0615\u0616\u0005g\u0000\u0000\u0616\u0617"+
		"\u0003\u00eew\u0000\u0617\u00f9\u0001\u0000\u0000\u0000\u0618\u0619\u0005"+
		"g\u0000\u0000\u0619\u061a\u0005:\u0000\u0000\u061a\u00fb\u0001\u0000\u0000"+
		"\u0000\u061b\u061c\u0005g\u0000\u0000\u061c\u061d\u0005/\u0000\u0000\u061d"+
		"\u00fd\u0001\u0000\u0000\u0000\u061e\u061f\u0005g\u0000\u0000\u061f\u0622"+
		"\u0005Q\u0000\u0000\u0620\u0623\u0003\u00f2y\u0000\u0621\u0623\u0003 "+
		"\u0010\u0000\u0622\u0620\u0001\u0000\u0000\u0000\u0622\u0621\u0001\u0000"+
		"\u0000\u0000\u0623\u0624\u0001\u0000\u0000\u0000\u0624\u0625\u0005R\u0000"+
		"\u0000\u0625\u00ff\u0001\u0000\u0000\u0000\u0626\u0629\u0003\u0102\u0081"+
		"\u0000\u0627\u0629\u0003\u0104\u0082\u0000\u0628\u0626\u0001\u0000\u0000"+
		"\u0000\u0628\u0627\u0001\u0000\u0000\u0000\u0629\u0101\u0001\u0000\u0000"+
		"\u0000\u062a\u062b\u0005g\u0000\u0000\u062b\u062d\u0005>\u0000\u0000\u062c"+
		"\u062e\u0003\u00eew\u0000\u062d\u062c\u0001\u0000\u0000\u0000\u062d\u062e"+
		"\u0001\u0000\u0000\u0000\u062e\u062f\u0001\u0000\u0000\u0000\u062f\u0630"+
		"\u0003`0\u0000\u0630\u0103\u0001\u0000\u0000\u0000\u0631\u0636\u0005g"+
		"\u0000\u0000\u0632\u0633\u0005>\u0000\u0000\u0633\u0637\u0005:\u0000\u0000"+
		"\u0634\u0635\u0005:\u0000\u0000\u0635\u0637\u0005>\u0000\u0000\u0636\u0632"+
		"\u0001\u0000\u0000\u0000\u0636\u0634\u0001\u0000\u0000\u0000\u0637\u0638"+
		"\u0001\u0000\u0000\u0000\u0638\u0639\u0003`0\u0000\u0639\u0105\u0001\u0000"+
		"\u0000\u0000\u063a\u063b\u0005g\u0000\u0000\u063b\u063c\u0005:\u0000\u0000"+
		"\u063c\u0107\u0001\u0000\u0000\u0000\u063d\u063e\u0005g\u0000\u0000\u063e"+
		"\u063f\u0005i\u0000\u0000\u063f\u0640\u0005/\u0000\u0000\u0640\u0642\u0005"+
		";\u0000\u0000\u0641\u0643\u0003\u010e\u0087\u0000\u0642\u0641\u0001\u0000"+
		"\u0000\u0000\u0642\u0643\u0001\u0000\u0000\u0000\u0643\u0644\u0001\u0000"+
		"\u0000\u0000\u0644\u0647\u0005<\u0000\u0000\u0645\u0646\u0005>\u0000\u0000"+
		"\u0646\u0648\u0003`0\u0000\u0647\u0645\u0001\u0000\u0000\u0000\u0647\u0648"+
		"\u0001\u0000\u0000\u0000\u0648\u0109\u0001\u0000\u0000\u0000\u0649\u064b"+
		"\u0003\u010c\u0086\u0000\u064a\u064c\u0003\u0116\u008b\u0000\u064b\u064a"+
		"\u0001\u0000\u0000\u0000\u064c\u064d\u0001\u0000\u0000\u0000\u064d\u064b"+
		"\u0001\u0000\u0000\u0000\u064d\u064e\u0001\u0000\u0000\u0000\u064e\u010b"+
		"\u0001\u0000\u0000\u0000\u064f\u0654\u0003\u0114\u008a\u0000\u0650\u0651"+
		"\u0003\u0112\u0089\u0000\u0651\u0652\u0005.\u0000\u0000\u0652\u0654\u0001"+
		"\u0000\u0000\u0000\u0653\u064f\u0001\u0000\u0000\u0000\u0653\u0650\u0001"+
		"\u0000\u0000\u0000\u0654\u010d\u0001\u0000\u0000\u0000\u0655\u065a\u0003"+
		"\u0110\u0088\u0000\u0656\u0657\u0005=\u0000\u0000\u0657\u0659\u0003\u0110"+
		"\u0088\u0000\u0658\u0656\u0001\u0000\u0000\u0000\u0659\u065c\u0001\u0000"+
		"\u0000\u0000\u065a\u0658\u0001\u0000\u0000\u0000\u065a\u065b\u0001\u0000"+
		"\u0000\u0000\u065b\u010f\u0001\u0000\u0000\u0000\u065c\u065a\u0001\u0000"+
		"\u0000\u0000\u065d\u0660\u0003\u00f4z\u0000\u065e\u0660\u0003\u00c6c\u0000"+
		"\u065f\u065d\u0001\u0000\u0000\u0000\u065f\u065e\u0001\u0000\u0000\u0000"+
		"\u0660\u0663\u0001\u0000\u0000\u0000\u0661\u0662\u0005A\u0000\u0000\u0662"+
		"\u0664\u0003\u00aeW\u0000\u0663\u0661\u0001\u0000\u0000\u0000\u0663\u0664"+
		"\u0001\u0000\u0000\u0000\u0664\u0111\u0001\u0000\u0000\u0000\u0665\u0666"+
		"\u0005i\u0000\u0000\u0666\u0667\u0007\u0007\u0000\u0000\u0667\u0668\u0005"+
		"/\u0000\u0000\u0668\u066a\u0005;\u0000\u0000\u0669\u066b\u0003\u010e\u0087"+
		"\u0000\u066a\u0669\u0001\u0000\u0000\u0000\u066a\u066b\u0001\u0000\u0000"+
		"\u0000\u066b\u066c\u0001\u0000\u0000\u0000\u066c\u066d\u0005<\u0000\u0000"+
		"\u066d\u0113\u0001\u0000\u0000\u0000\u066e\u066f\u0003\u0112\u0089\u0000"+
		"\u066f\u0670\u0005>\u0000\u0000\u0670\u0671\u0003`0\u0000\u0671\u0115"+
		"\u0001\u0000\u0000\u0000\u0672\u0673\u0005h\u0000\u0000\u0673\u0674\u0005"+
		"/\u0000\u0000\u0674\u0675\u0005.\u0000\u0000\u0675\u0676\u0003\u001a\r"+
		"\u0000\u0676\u0117\u0001\u0000\u0000\u0000\u00e3\u011d\u0121\u0123\u012c"+
		"\u0133\u0135\u013d\u0140\u0147\u014d\u0156\u015b\u0164\u016a\u016e\u0174"+
		"\u017a\u017e\u0185\u0187\u0189\u018e\u0190\u0192\u0196\u019c\u01a0\u01a7"+
		"\u01a9\u01ab\u01b0\u01b2\u01b9\u01be\u01c3\u01c9\u01cd\u01d3\u01d9\u01dd"+
		"\u01e4\u01e6\u01e8\u01ed\u01ef\u01f1\u01f5\u01fb\u01ff\u0206\u0208\u020a"+
		"\u020f\u0211\u0217\u0221\u0228\u022c\u0239\u0240\u0245\u0249\u024c\u0252"+
		"\u0256\u025b\u025f\u0263\u0271\u0279\u0281\u0283\u0287\u0290\u0297\u0299"+
		"\u02a2\u02a7\u02ac\u02b3\u02b7\u02be\u02c6\u02cf\u02d8\u02df\u02ec\u02f2"+
		"\u02ff\u0305\u030e\u0319\u0324\u0329\u032e\u0333\u033b\u0344\u034a\u034c"+
		"\u0354\u0358\u0362\u0369\u036c\u0372\u0375\u037a\u037f\u0389\u038d\u0398"+
		"\u03a3\u03ad\u03b7\u03c1\u03c6\u03cb\u03e0\u03e4\u03ec\u03f1\u03f4\u03f9"+
		"\u0400\u0404\u0408\u040e\u0415\u041e\u0425\u0429\u0430\u0435\u0445\u044d"+
		"\u0457\u045b\u0462\u046a\u0477\u047a\u047e\u0482\u0489\u0493\u049b\u04a1"+
		"\u04a9\u04b9\u04c5\u04c8\u04df\u04e1\u04e5\u04eb\u04f1\u04f6\u04fb\u0504"+
		"\u050a\u0511\u0518\u051c\u0520\u0522\u0526\u0530\u0532\u0539\u053d\u0541"+
		"\u0545\u0548\u054a\u054e\u0552\u0557\u055b\u055f\u0563\u0568\u056c\u0570"+
		"\u0578\u0582\u0586\u058a\u058c\u0590\u0596\u059a\u059e\u05a0\u05a2\u05a8"+
		"\u05ac\u05af\u05b7\u05bd\u05c1\u05c5\u05ca\u05d4\u05d8\u05db\u05e2\u05e7"+
		"\u05ed\u05f2\u05f7\u05fe\u0607\u060d\u0611\u0622\u0628\u062d\u0636\u0642"+
		"\u0647\u064d\u0653\u065a\u065f\u0663\u066a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}