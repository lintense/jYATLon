// Generated from jyatlon\generated\YATL.g4 by ANTLR 4.7.2
package jyatlon.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class YATLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, ANYPATH=6, BEGIN=7, BEFORE=8, 
		BETWEEN=9, AFTER=10, END=11, CALL=12, IF=13, CONTROL=14, ROOT=15, LCURL=16, 
		LBRACK=17, LVALUE=18, RCURL=19, RBRACK=20, RVALUE=21, PATHSEP=22, ESCAPE=23, 
		DOT=24, COMMA=25, COLON=26, NEWLINE=27, NOT=28, MINUS=29, WS=30, SPACE=31, 
		EQUAL=32, SECTIONSEP=33, POUND=34, COMMENTSEP=35, PIPE=36, OR=37, AMP=38, 
		AND=39, NAME=40, INTEGER=41, NUMBER=42, STRING=43;
	public static final int
		RULE_template = 0, RULE_section = 1, RULE_line = 2, RULE_lineExp = 3, 
		RULE_escapedChar = 4, RULE_controlExp = 5, RULE_controlOp = 6, RULE_commentOp = 7, 
		RULE_rawText = 8, RULE_value = 9, RULE_ifExp = 10, RULE_callExp = 11, 
		RULE_logicalExp = 12, RULE_logicalOp = 13, RULE_binaryExp = 14, RULE_unaryOp = 15, 
		RULE_binaryOp = 16, RULE_valueExp = 17, RULE_valueArg = 18, RULE_operation = 19, 
		RULE_argExp = 20, RULE_pathExp = 21, RULE_anyPathOp = 22, RULE_pathName = 23, 
		RULE_methodName = 24, RULE_aliasName = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"template", "section", "line", "lineExp", "escapedChar", "controlExp", 
			"controlOp", "commentOp", "rawText", "value", "ifExp", "callExp", "logicalExp", 
			"logicalOp", "binaryExp", "unaryOp", "binaryOp", "valueExp", "valueArg", 
			"operation", "argExp", "pathExp", "anyPathOp", "pathName", "methodName", 
			"aliasName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'>'", "'<'", "'<>'", "'...'", "'begin'", "'before'", 
			"'between'", "'after'", "'end'", "'call'", "'if'", null, "'$'", "'{'", 
			"'['", null, "'}'", "']'", null, null, "'~'", "'.'", "','", "':'", "'\n'", 
			"'!'", "'-'", null, null, "'='", null, "'#'", null, "'|'", null, "'&'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "ANYPATH", "BEGIN", "BEFORE", "BETWEEN", 
			"AFTER", "END", "CALL", "IF", "CONTROL", "ROOT", "LCURL", "LBRACK", "LVALUE", 
			"RCURL", "RBRACK", "RVALUE", "PATHSEP", "ESCAPE", "DOT", "COMMA", "COLON", 
			"NEWLINE", "NOT", "MINUS", "WS", "SPACE", "EQUAL", "SECTIONSEP", "POUND", 
			"COMMENTSEP", "PIPE", "OR", "AMP", "AND", "NAME", "INTEGER", "NUMBER", 
			"STRING"
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
	public String getGrammarFileName() { return "YATL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public YATLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TemplateContext extends ParserRuleContext {
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitTemplate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52);
				section();
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPACE) | (1L << EQUAL) | (1L << SECTIONSEP))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SectionContext extends ParserRuleContext {
		public List<TerminalNode> SECTIONSEP() { return getTokens(YATLParser.SECTIONSEP); }
		public TerminalNode SECTIONSEP(int i) {
			return getToken(YATLParser.SECTIONSEP, i);
		}
		public PathExpContext pathExp() {
			return getRuleContext(PathExpContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(YATLParser.NEWLINE, 0); }
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(YATLParser.EQUAL); }
		public TerminalNode EQUAL(int i) {
			return getToken(YATLParser.EQUAL, i);
		}
		public TerminalNode ROOT() { return getToken(YATLParser.ROOT, 0); }
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_section);
		int _la;
		try {
			int _alt;
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SPACE:
			case SECTIONSEP:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(57);
					match(SPACE);
					}
					}
					setState(62);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(63);
				match(SECTIONSEP);
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(64);
					match(SPACE);
					}
					}
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(70);
				pathExp();
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(71);
					match(SPACE);
					}
					}
					setState(76);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(77);
				match(SECTIONSEP);
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(78);
					match(SPACE);
					}
					}
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(84);
				match(NEWLINE);
				setState(88);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(85);
						line();
						}
						} 
					}
					setState(90);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				}
				break;
			case EQUAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				match(EQUAL);
				setState(92);
				match(ROOT);
				setState(93);
				match(EQUAL);
				setState(94);
				match(NEWLINE);
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(95);
						line();
						}
						} 
					}
					setState(100);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class LineContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(YATLParser.NEWLINE, 0); }
		public List<LineExpContext> lineExp() {
			return getRuleContexts(LineExpContext.class);
		}
		public LineExpContext lineExp(int i) {
			return getRuleContext(LineExpContext.class,i);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << ANYPATH) | (1L << BEGIN) | (1L << BEFORE) | (1L << BETWEEN) | (1L << AFTER) | (1L << END) | (1L << CALL) | (1L << IF) | (1L << CONTROL) | (1L << ROOT) | (1L << LCURL) | (1L << LBRACK) | (1L << LVALUE) | (1L << RCURL) | (1L << RBRACK) | (1L << RVALUE) | (1L << PATHSEP) | (1L << ESCAPE) | (1L << DOT) | (1L << COMMA) | (1L << COLON) | (1L << NOT) | (1L << MINUS) | (1L << WS) | (1L << SPACE) | (1L << EQUAL) | (1L << POUND) | (1L << COMMENTSEP) | (1L << PIPE) | (1L << OR) | (1L << AMP) | (1L << AND) | (1L << NAME) | (1L << INTEGER) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				{
				setState(103);
				lineExp();
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109);
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

	public static class LineExpContext extends ParserRuleContext {
		public CommentOpContext commentOp() {
			return getRuleContext(CommentOpContext.class,0);
		}
		public EscapedCharContext escapedChar() {
			return getRuleContext(EscapedCharContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ControlExpContext controlExp() {
			return getRuleContext(ControlExpContext.class,0);
		}
		public RawTextContext rawText() {
			return getRuleContext(RawTextContext.class,0);
		}
		public LineExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lineExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterLineExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitLineExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitLineExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineExpContext lineExp() throws RecognitionException {
		LineExpContext _localctx = new LineExpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lineExp);
		try {
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMENTSEP:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				commentOp();
				}
				break;
			case ESCAPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				escapedChar();
				}
				break;
			case LVALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
				value();
				}
				break;
			case CONTROL:
				enterOuterAlt(_localctx, 4);
				{
				setState(114);
				controlExp();
				}
				break;
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case ANYPATH:
			case BEGIN:
			case BEFORE:
			case BETWEEN:
			case AFTER:
			case END:
			case CALL:
			case IF:
			case ROOT:
			case LCURL:
			case LBRACK:
			case RCURL:
			case RBRACK:
			case RVALUE:
			case PATHSEP:
			case DOT:
			case COMMA:
			case COLON:
			case NOT:
			case MINUS:
			case WS:
			case SPACE:
			case EQUAL:
			case POUND:
			case PIPE:
			case OR:
			case AMP:
			case AND:
			case NAME:
			case INTEGER:
			case NUMBER:
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				rawText();
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

	public static class EscapedCharContext extends ParserRuleContext {
		public TerminalNode ESCAPE() { return getToken(YATLParser.ESCAPE, 0); }
		public EscapedCharContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escapedChar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterEscapedChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitEscapedChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitEscapedChar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EscapedCharContext escapedChar() throws RecognitionException {
		EscapedCharContext _localctx = new EscapedCharContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_escapedChar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(ESCAPE);
			setState(119);
			matchWildcard();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ControlExpContext extends ParserRuleContext {
		public ControlOpContext controlOp() {
			return getRuleContext(ControlOpContext.class,0);
		}
		public AliasNameContext aliasName() {
			return getRuleContext(AliasNameContext.class,0);
		}
		public TerminalNode RCURL() { return getToken(YATLParser.RCURL, 0); }
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public ControlExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controlExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterControlExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitControlExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitControlExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ControlExpContext controlExp() throws RecognitionException {
		ControlExpContext _localctx = new ControlExpContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_controlExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			controlOp();
			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(122);
				match(SPACE);
				}
				}
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SPACE );
			setState(127);
			aliasName();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(128);
				match(SPACE);
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134);
			match(RCURL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ControlOpContext extends ParserRuleContext {
		public TerminalNode CONTROL() { return getToken(YATLParser.CONTROL, 0); }
		public ControlOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controlOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterControlOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitControlOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitControlOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ControlOpContext controlOp() throws RecognitionException {
		ControlOpContext _localctx = new ControlOpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_controlOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(CONTROL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentOpContext extends ParserRuleContext {
		public TerminalNode COMMENTSEP() { return getToken(YATLParser.COMMENTSEP, 0); }
		public CommentOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commentOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterCommentOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitCommentOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitCommentOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentOpContext commentOp() throws RecognitionException {
		CommentOpContext _localctx = new CommentOpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_commentOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(COMMENTSEP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RawTextContext extends ParserRuleContext {
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public List<TerminalNode> SECTIONSEP() { return getTokens(YATLParser.SECTIONSEP); }
		public TerminalNode SECTIONSEP(int i) {
			return getToken(YATLParser.SECTIONSEP, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(YATLParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(YATLParser.NEWLINE, i);
		}
		public List<TerminalNode> ESCAPE() { return getTokens(YATLParser.ESCAPE); }
		public TerminalNode ESCAPE(int i) {
			return getToken(YATLParser.ESCAPE, i);
		}
		public List<TerminalNode> CONTROL() { return getTokens(YATLParser.CONTROL); }
		public TerminalNode CONTROL(int i) {
			return getToken(YATLParser.CONTROL, i);
		}
		public List<TerminalNode> COMMENTSEP() { return getTokens(YATLParser.COMMENTSEP); }
		public TerminalNode COMMENTSEP(int i) {
			return getToken(YATLParser.COMMENTSEP, i);
		}
		public List<TerminalNode> LVALUE() { return getTokens(YATLParser.LVALUE); }
		public TerminalNode LVALUE(int i) {
			return getToken(YATLParser.LVALUE, i);
		}
		public RawTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rawText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterRawText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitRawText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitRawText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RawTextContext rawText() throws RecognitionException {
		RawTextContext _localctx = new RawTextContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rawText);
		int _la;
		try {
			int _alt;
			setState(150);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SPACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(141); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(140);
						match(SPACE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(143); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case ANYPATH:
			case BEGIN:
			case BEFORE:
			case BETWEEN:
			case AFTER:
			case END:
			case CALL:
			case IF:
			case ROOT:
			case LCURL:
			case LBRACK:
			case RCURL:
			case RBRACK:
			case RVALUE:
			case PATHSEP:
			case DOT:
			case COMMA:
			case COLON:
			case NOT:
			case MINUS:
			case WS:
			case EQUAL:
			case POUND:
			case PIPE:
			case OR:
			case AMP:
			case AND:
			case NAME:
			case INTEGER:
			case NUMBER:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(146); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(145);
						_la = _input.LA(1);
						if ( _la <= 0 || ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONTROL) | (1L << LVALUE) | (1L << ESCAPE) | (1L << NEWLINE) | (1L << SPACE) | (1L << SECTIONSEP) | (1L << COMMENTSEP))) != 0)) ) {
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
					setState(148); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode LVALUE() { return getToken(YATLParser.LVALUE, 0); }
		public ValueExpContext valueExp() {
			return getRuleContext(ValueExpContext.class,0);
		}
		public TerminalNode RVALUE() { return getToken(YATLParser.RVALUE, 0); }
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public IfExpContext ifExp() {
			return getRuleContext(IfExpContext.class,0);
		}
		public CallExpContext callExp() {
			return getRuleContext(CallExpContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(LVALUE);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(153);
				match(SPACE);
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(159);
				ifExp();
				setState(161); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(160);
					match(SPACE);
					}
					}
					setState(163); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SPACE );
				}
			}

			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CALL) {
				{
				setState(167);
				callExp();
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(168);
					match(SPACE);
					}
					}
					setState(171); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SPACE );
				}
			}

			setState(175);
			valueExp();
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(176);
				match(SPACE);
				}
				}
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(182);
			match(RVALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfExpContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(YATLParser.IF, 0); }
		public LogicalExpContext logicalExp() {
			return getRuleContext(LogicalExpContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public IfExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterIfExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitIfExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitIfExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfExpContext ifExp() throws RecognitionException {
		IfExpContext _localctx = new IfExpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifExp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(IF);
			setState(186); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(185);
					match(SPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(188); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(190);
			logicalExp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallExpContext extends ParserRuleContext {
		public TerminalNode CALL() { return getToken(YATLParser.CALL, 0); }
		public PathExpContext pathExp() {
			return getRuleContext(PathExpContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public CallExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterCallExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitCallExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitCallExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallExpContext callExp() throws RecognitionException {
		CallExpContext _localctx = new CallExpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_callExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(CALL);
			setState(194); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(193);
				match(SPACE);
				}
				}
				setState(196); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SPACE );
			setState(198);
			pathExp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalExpContext extends ParserRuleContext {
		public List<BinaryExpContext> binaryExp() {
			return getRuleContexts(BinaryExpContext.class);
		}
		public BinaryExpContext binaryExp(int i) {
			return getRuleContext(BinaryExpContext.class,i);
		}
		public List<LogicalOpContext> logicalOp() {
			return getRuleContexts(LogicalOpContext.class);
		}
		public LogicalOpContext logicalOp(int i) {
			return getRuleContext(LogicalOpContext.class,i);
		}
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public LogicalExpContext logicalExp() {
			return getRuleContext(LogicalExpContext.class,0);
		}
		public LogicalExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterLogicalExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitLogicalExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitLogicalExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalExpContext logicalExp() throws RecognitionException {
		LogicalExpContext _localctx = new LogicalExpContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_logicalExp);
		int _la;
		try {
			int _alt;
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				binaryExp();
				setState(218);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(204);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(201);
							match(SPACE);
							}
							}
							setState(206);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(207);
						logicalOp();
						setState(211);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(208);
								match(SPACE);
								}
								} 
							}
							setState(213);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
						}
						setState(214);
						binaryExp();
						}
						} 
					}
					setState(220);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				match(T__0);
				setState(225);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(222);
						match(SPACE);
						}
						} 
					}
					setState(227);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				setState(228);
				logicalExp();
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(229);
					match(SPACE);
					}
					}
					setState(234);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(235);
				match(T__1);
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

	public static class LogicalOpContext extends ParserRuleContext {
		public TerminalNode OR() { return getToken(YATLParser.OR, 0); }
		public TerminalNode AND() { return getToken(YATLParser.AND, 0); }
		public LogicalOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterLogicalOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitLogicalOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitLogicalOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOpContext logicalOp() throws RecognitionException {
		LogicalOpContext _localctx = new LogicalOpContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_logicalOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			_la = _input.LA(1);
			if ( !(_la==OR || _la==AND) ) {
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

	public static class BinaryExpContext extends ParserRuleContext {
		public List<ValueExpContext> valueExp() {
			return getRuleContexts(ValueExpContext.class);
		}
		public ValueExpContext valueExp(int i) {
			return getRuleContext(ValueExpContext.class,i);
		}
		public UnaryOpContext unaryOp() {
			return getRuleContext(UnaryOpContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public List<BinaryOpContext> binaryOp() {
			return getRuleContexts(BinaryOpContext.class);
		}
		public BinaryOpContext binaryOp(int i) {
			return getRuleContext(BinaryOpContext.class,i);
		}
		public BinaryExpContext binaryExp() {
			return getRuleContext(BinaryExpContext.class,0);
		}
		public BinaryExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterBinaryExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitBinaryExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitBinaryExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryExpContext binaryExp() throws RecognitionException {
		BinaryExpContext _localctx = new BinaryExpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_binaryExp);
		int _la;
		try {
			int _alt;
			setState(287);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT || _la==MINUS) {
					{
					setState(241);
					unaryOp();
					}
				}

				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(244);
					match(SPACE);
					}
					}
					setState(249);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(250);
				valueExp();
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(254);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(251);
							match(SPACE);
							}
							}
							setState(256);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(257);
						binaryOp();
						setState(261);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(258);
							match(SPACE);
							}
							}
							setState(263);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(264);
						valueExp();
						}
						} 
					}
					setState(270);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				match(T__0);
				setState(275);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(272);
						match(SPACE);
						}
						} 
					}
					setState(277);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				}
				setState(278);
				binaryExp();
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(279);
					match(SPACE);
					}
					}
					setState(284);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(285);
				match(T__1);
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

	public static class UnaryOpContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(YATLParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(YATLParser.MINUS, 0); }
		public UnaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterUnaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitUnaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitUnaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOpContext unaryOp() throws RecognitionException {
		UnaryOpContext _localctx = new UnaryOpContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_unaryOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_la = _input.LA(1);
			if ( !(_la==NOT || _la==MINUS) ) {
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

	public static class BinaryOpContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(YATLParser.NOT, 0); }
		public List<TerminalNode> EQUAL() { return getTokens(YATLParser.EQUAL); }
		public TerminalNode EQUAL(int i) {
			return getToken(YATLParser.EQUAL, i);
		}
		public BinaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterBinaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitBinaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitBinaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOpContext binaryOp() throws RecognitionException {
		BinaryOpContext _localctx = new BinaryOpContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_binaryOp);
		try {
			setState(302);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				match(NOT);
				setState(292);
				match(EQUAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				match(EQUAL);
				setState(294);
				match(EQUAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(295);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(296);
				match(T__2);
				setState(297);
				match(EQUAL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(298);
				match(T__3);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(299);
				match(T__3);
				setState(300);
				match(EQUAL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(301);
				match(T__4);
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

	public static class ValueExpContext extends ParserRuleContext {
		public ValueArgContext valueArg() {
			return getRuleContext(ValueArgContext.class,0);
		}
		public TerminalNode COLON() { return getToken(YATLParser.COLON, 0); }
		public AliasNameContext aliasName() {
			return getRuleContext(AliasNameContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public ValueExpContext valueExp() {
			return getRuleContext(ValueExpContext.class,0);
		}
		public ValueExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterValueExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitValueExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitValueExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueExpContext valueExp() throws RecognitionException {
		ValueExpContext _localctx = new ValueExpContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_valueExp);
		int _la;
		try {
			int _alt;
			setState(349);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ROOT:
			case NAME:
			case INTEGER:
			case NUMBER:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				valueArg();
				setState(319);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(308);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SPACE) {
						{
						{
						setState(305);
						match(SPACE);
						}
						}
						setState(310);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(311);
					match(COLON);
					setState(315);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SPACE) {
						{
						{
						setState(312);
						match(SPACE);
						}
						}
						setState(317);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(318);
					aliasName();
					}
					break;
				}
				setState(324);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(321);
						match(SPACE);
						}
						} 
					}
					setState(326);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				}
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(327);
					operation();
					}
					}
					setState(332);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(333);
				match(T__0);
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(334);
					match(SPACE);
					}
					}
					setState(339);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(340);
				valueExp();
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(341);
					match(SPACE);
					}
					}
					setState(346);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(347);
				match(T__1);
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

	public static class ValueArgContext extends ParserRuleContext {
		public TerminalNode ROOT() { return getToken(YATLParser.ROOT, 0); }
		public TerminalNode NAME() { return getToken(YATLParser.NAME, 0); }
		public TerminalNode INTEGER() { return getToken(YATLParser.INTEGER, 0); }
		public TerminalNode NUMBER() { return getToken(YATLParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(YATLParser.STRING, 0); }
		public ValueArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterValueArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitValueArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitValueArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueArgContext valueArg() throws RecognitionException {
		ValueArgContext _localctx = new ValueArgContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_valueArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ROOT) | (1L << NAME) | (1L << INTEGER) | (1L << NUMBER) | (1L << STRING))) != 0)) ) {
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

	public static class OperationContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(YATLParser.DOT, 0); }
		public MethodNameContext methodName() {
			return getRuleContext(MethodNameContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public TerminalNode COLON() { return getToken(YATLParser.COLON, 0); }
		public AliasNameContext aliasName() {
			return getRuleContext(AliasNameContext.class,0);
		}
		public ArgExpContext argExp() {
			return getRuleContext(ArgExpContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			match(DOT);
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SPACE) {
				{
				setState(354);
				match(SPACE);
				}
			}

			setState(357);
			methodName();
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(358);
					match(SPACE);
					}
				}

				setState(361);
				match(T__0);
				setState(363);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(362);
					match(SPACE);
					}
					break;
				}
				setState(366);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ROOT) | (1L << NAME) | (1L << INTEGER) | (1L << NUMBER) | (1L << STRING))) != 0)) {
					{
					setState(365);
					argExp();
					}
				}

				setState(369);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(368);
					match(SPACE);
					}
				}

				setState(371);
				match(T__1);
				}
				break;
			}
			setState(382);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(375);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(374);
					match(SPACE);
					}
				}

				setState(377);
				match(COLON);
				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(378);
					match(SPACE);
					}
				}

				setState(381);
				aliasName();
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

	public static class ArgExpContext extends ParserRuleContext {
		public List<ValueExpContext> valueExp() {
			return getRuleContexts(ValueExpContext.class);
		}
		public ValueExpContext valueExp(int i) {
			return getRuleContext(ValueExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(YATLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(YATLParser.COMMA, i);
		}
		public List<TerminalNode> SPACE() { return getTokens(YATLParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(YATLParser.SPACE, i);
		}
		public ArgExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterArgExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitArgExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitArgExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgExpContext argExp() throws RecognitionException {
		ArgExpContext _localctx = new ArgExpContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_argExp);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			valueExp();
			setState(395);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(386);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SPACE) {
						{
						setState(385);
						match(SPACE);
						}
					}

					setState(388);
					match(COMMA);
					setState(390);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SPACE) {
						{
						setState(389);
						match(SPACE);
						}
					}

					setState(392);
					valueExp();
					}
					} 
				}
				setState(397);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
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

	public static class PathExpContext extends ParserRuleContext {
		public List<PathNameContext> pathName() {
			return getRuleContexts(PathNameContext.class);
		}
		public PathNameContext pathName(int i) {
			return getRuleContext(PathNameContext.class,i);
		}
		public AnyPathOpContext anyPathOp() {
			return getRuleContext(AnyPathOpContext.class,0);
		}
		public List<TerminalNode> PATHSEP() { return getTokens(YATLParser.PATHSEP); }
		public TerminalNode PATHSEP(int i) {
			return getToken(YATLParser.PATHSEP, i);
		}
		public PathExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterPathExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitPathExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitPathExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathExpContext pathExp() throws RecognitionException {
		PathExpContext _localctx = new PathExpContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_pathExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ANYPATH) {
				{
				setState(398);
				anyPathOp();
				}
			}

			setState(401);
			pathName();
			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PATHSEP) {
				{
				{
				setState(402);
				match(PATHSEP);
				setState(403);
				pathName();
				}
				}
				setState(408);
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

	public static class AnyPathOpContext extends ParserRuleContext {
		public TerminalNode ANYPATH() { return getToken(YATLParser.ANYPATH, 0); }
		public TerminalNode PATHSEP() { return getToken(YATLParser.PATHSEP, 0); }
		public AnyPathOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anyPathOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterAnyPathOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitAnyPathOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitAnyPathOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnyPathOpContext anyPathOp() throws RecognitionException {
		AnyPathOpContext _localctx = new AnyPathOpContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_anyPathOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			match(ANYPATH);
			setState(410);
			match(PATHSEP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathNameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(YATLParser.NAME, 0); }
		public PathNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterPathName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitPathName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitPathName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathNameContext pathName() throws RecognitionException {
		PathNameContext _localctx = new PathNameContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_pathName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
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

	public static class MethodNameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(YATLParser.NAME, 0); }
		public MethodNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterMethodName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitMethodName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitMethodName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodNameContext methodName() throws RecognitionException {
		MethodNameContext _localctx = new MethodNameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_methodName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
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

	public static class AliasNameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(YATLParser.NAME, 0); }
		public AliasNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterAliasName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitAliasName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitAliasName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasNameContext aliasName() throws RecognitionException {
		AliasNameContext _localctx = new AliasNameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_aliasName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u01a5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\6\28\n\2\r\2\16\29\3\3\7\3=\n\3\f\3\16\3@\13"+
		"\3\3\3\3\3\7\3D\n\3\f\3\16\3G\13\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\3\3"+
		"\3\3\7\3R\n\3\f\3\16\3U\13\3\3\3\3\3\7\3Y\n\3\f\3\16\3\\\13\3\3\3\3\3"+
		"\3\3\3\3\3\3\7\3c\n\3\f\3\16\3f\13\3\5\3h\n\3\3\4\7\4k\n\4\f\4\16\4n\13"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\5\5w\n\5\3\6\3\6\3\6\3\7\3\7\6\7~\n\7\r"+
		"\7\16\7\177\3\7\3\7\7\7\u0084\n\7\f\7\16\7\u0087\13\7\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\n\6\n\u0090\n\n\r\n\16\n\u0091\3\n\6\n\u0095\n\n\r\n\16\n\u0096"+
		"\5\n\u0099\n\n\3\13\3\13\7\13\u009d\n\13\f\13\16\13\u00a0\13\13\3\13\3"+
		"\13\6\13\u00a4\n\13\r\13\16\13\u00a5\5\13\u00a8\n\13\3\13\3\13\6\13\u00ac"+
		"\n\13\r\13\16\13\u00ad\5\13\u00b0\n\13\3\13\3\13\7\13\u00b4\n\13\f\13"+
		"\16\13\u00b7\13\13\3\13\3\13\3\f\3\f\6\f\u00bd\n\f\r\f\16\f\u00be\3\f"+
		"\3\f\3\r\3\r\6\r\u00c5\n\r\r\r\16\r\u00c6\3\r\3\r\3\16\3\16\7\16\u00cd"+
		"\n\16\f\16\16\16\u00d0\13\16\3\16\3\16\7\16\u00d4\n\16\f\16\16\16\u00d7"+
		"\13\16\3\16\3\16\7\16\u00db\n\16\f\16\16\16\u00de\13\16\3\16\3\16\7\16"+
		"\u00e2\n\16\f\16\16\16\u00e5\13\16\3\16\3\16\7\16\u00e9\n\16\f\16\16\16"+
		"\u00ec\13\16\3\16\3\16\5\16\u00f0\n\16\3\17\3\17\3\20\5\20\u00f5\n\20"+
		"\3\20\7\20\u00f8\n\20\f\20\16\20\u00fb\13\20\3\20\3\20\7\20\u00ff\n\20"+
		"\f\20\16\20\u0102\13\20\3\20\3\20\7\20\u0106\n\20\f\20\16\20\u0109\13"+
		"\20\3\20\3\20\7\20\u010d\n\20\f\20\16\20\u0110\13\20\3\20\3\20\7\20\u0114"+
		"\n\20\f\20\16\20\u0117\13\20\3\20\3\20\7\20\u011b\n\20\f\20\16\20\u011e"+
		"\13\20\3\20\3\20\5\20\u0122\n\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\5\22\u0131\n\22\3\23\3\23\7\23\u0135\n\23"+
		"\f\23\16\23\u0138\13\23\3\23\3\23\7\23\u013c\n\23\f\23\16\23\u013f\13"+
		"\23\3\23\5\23\u0142\n\23\3\23\7\23\u0145\n\23\f\23\16\23\u0148\13\23\3"+
		"\23\7\23\u014b\n\23\f\23\16\23\u014e\13\23\3\23\3\23\7\23\u0152\n\23\f"+
		"\23\16\23\u0155\13\23\3\23\3\23\7\23\u0159\n\23\f\23\16\23\u015c\13\23"+
		"\3\23\3\23\5\23\u0160\n\23\3\24\3\24\3\25\3\25\5\25\u0166\n\25\3\25\3"+
		"\25\5\25\u016a\n\25\3\25\3\25\5\25\u016e\n\25\3\25\5\25\u0171\n\25\3\25"+
		"\5\25\u0174\n\25\3\25\5\25\u0177\n\25\3\25\5\25\u017a\n\25\3\25\3\25\5"+
		"\25\u017e\n\25\3\25\5\25\u0181\n\25\3\26\3\26\5\26\u0185\n\26\3\26\3\26"+
		"\5\26\u0189\n\26\3\26\7\26\u018c\n\26\f\26\16\26\u018f\13\26\3\27\5\27"+
		"\u0192\n\27\3\27\3\27\3\27\7\27\u0197\n\27\f\27\16\27\u019a\13\27\3\30"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\2\2\34\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\2\6\t\2\20\20\24\24\31\31\35\35"+
		"!!##%%\4\2\'\'))\3\2\36\37\4\2\21\21*-\2\u01ce\2\67\3\2\2\2\4g\3\2\2\2"+
		"\6l\3\2\2\2\bv\3\2\2\2\nx\3\2\2\2\f{\3\2\2\2\16\u008a\3\2\2\2\20\u008c"+
		"\3\2\2\2\22\u0098\3\2\2\2\24\u009a\3\2\2\2\26\u00ba\3\2\2\2\30\u00c2\3"+
		"\2\2\2\32\u00ef\3\2\2\2\34\u00f1\3\2\2\2\36\u0121\3\2\2\2 \u0123\3\2\2"+
		"\2\"\u0130\3\2\2\2$\u015f\3\2\2\2&\u0161\3\2\2\2(\u0163\3\2\2\2*\u0182"+
		"\3\2\2\2,\u0191\3\2\2\2.\u019b\3\2\2\2\60\u019e\3\2\2\2\62\u01a0\3\2\2"+
		"\2\64\u01a2\3\2\2\2\668\5\4\3\2\67\66\3\2\2\289\3\2\2\29\67\3\2\2\29:"+
		"\3\2\2\2:\3\3\2\2\2;=\7!\2\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?"+
		"A\3\2\2\2@>\3\2\2\2AE\7#\2\2BD\7!\2\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF"+
		"\3\2\2\2FH\3\2\2\2GE\3\2\2\2HL\5,\27\2IK\7!\2\2JI\3\2\2\2KN\3\2\2\2LJ"+
		"\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OS\7#\2\2PR\7!\2\2QP\3\2\2\2RU\3"+
		"\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2US\3\2\2\2VZ\7\35\2\2WY\5\6\4\2XW"+
		"\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[h\3\2\2\2\\Z\3\2\2\2]^\7\"\2\2"+
		"^_\7\21\2\2_`\7\"\2\2`d\7\35\2\2ac\5\6\4\2ba\3\2\2\2cf\3\2\2\2db\3\2\2"+
		"\2de\3\2\2\2eh\3\2\2\2fd\3\2\2\2g>\3\2\2\2g]\3\2\2\2h\5\3\2\2\2ik\5\b"+
		"\5\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\7\35"+
		"\2\2p\7\3\2\2\2qw\5\20\t\2rw\5\n\6\2sw\5\24\13\2tw\5\f\7\2uw\5\22\n\2"+
		"vq\3\2\2\2vr\3\2\2\2vs\3\2\2\2vt\3\2\2\2vu\3\2\2\2w\t\3\2\2\2xy\7\31\2"+
		"\2yz\13\2\2\2z\13\3\2\2\2{}\5\16\b\2|~\7!\2\2}|\3\2\2\2~\177\3\2\2\2\177"+
		"}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0085\5\64\33\2"+
		"\u0082\u0084\7!\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2\2\2\u0088"+
		"\u0089\7\25\2\2\u0089\r\3\2\2\2\u008a\u008b\7\20\2\2\u008b\17\3\2\2\2"+
		"\u008c\u008d\7%\2\2\u008d\21\3\2\2\2\u008e\u0090\7!\2\2\u008f\u008e\3"+
		"\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0099\3\2\2\2\u0093\u0095\n\2\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2"+
		"\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0099\3\2\2\2\u0098"+
		"\u008f\3\2\2\2\u0098\u0094\3\2\2\2\u0099\23\3\2\2\2\u009a\u009e\7\24\2"+
		"\2\u009b\u009d\7!\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c"+
		"\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a7\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1"+
		"\u00a3\5\26\f\2\u00a2\u00a4\7!\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7"+
		"\u00a1\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00af\3\2\2\2\u00a9\u00ab\5\30"+
		"\r\2\u00aa\u00ac\7!\2\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af\u00a9\3\2"+
		"\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b5\5$\23\2\u00b2"+
		"\u00b4\7!\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2"+
		"\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8"+
		"\u00b9\7\27\2\2\u00b9\25\3\2\2\2\u00ba\u00bc\7\17\2\2\u00bb\u00bd\7!\2"+
		"\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf"+
		"\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\5\32\16\2\u00c1\27\3\2\2\2\u00c2"+
		"\u00c4\7\16\2\2\u00c3\u00c5\7!\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2"+
		"\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"\u00c9\5,\27\2\u00c9\31\3\2\2\2\u00ca\u00dc\5\36\20\2\u00cb\u00cd\7!\2"+
		"\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d5\5\34\17\2"+
		"\u00d2\u00d4\7!\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3"+
		"\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8"+
		"\u00d9\5\36\20\2\u00d9\u00db\3\2\2\2\u00da\u00ce\3\2\2\2\u00db\u00de\3"+
		"\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00f0\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00df\u00e3\7\3\2\2\u00e0\u00e2\7!\2\2\u00e1\u00e0\3\2"+
		"\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4"+
		"\u00e6\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00ea\5\32\16\2\u00e7\u00e9\7"+
		"!\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea"+
		"\u00eb\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\7\4"+
		"\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00ca\3\2\2\2\u00ef\u00df\3\2\2\2\u00f0"+
		"\33\3\2\2\2\u00f1\u00f2\t\3\2\2\u00f2\35\3\2\2\2\u00f3\u00f5\5 \21\2\u00f4"+
		"\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f9\3\2\2\2\u00f6\u00f8\7!"+
		"\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u010e\5$"+
		"\23\2\u00fd\u00ff\7!\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100"+
		"\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u0100\3\2"+
		"\2\2\u0103\u0107\5\"\22\2\u0104\u0106\7!\2\2\u0105\u0104\3\2\2\2\u0106"+
		"\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010a\3\2"+
		"\2\2\u0109\u0107\3\2\2\2\u010a\u010b\5$\23\2\u010b\u010d\3\2\2\2\u010c"+
		"\u0100\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010f\u0122\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0115\7\3\2\2\u0112"+
		"\u0114\7!\2\2\u0113\u0112\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2"+
		"\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117\u0115\3\2\2\2\u0118"+
		"\u011c\5\36\20\2\u0119\u011b\7!\2\2\u011a\u0119\3\2\2\2\u011b\u011e\3"+
		"\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011f\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011f\u0120\7\4\2\2\u0120\u0122\3\2\2\2\u0121\u00f4\3\2"+
		"\2\2\u0121\u0111\3\2\2\2\u0122\37\3\2\2\2\u0123\u0124\t\4\2\2\u0124!\3"+
		"\2\2\2\u0125\u0126\7\36\2\2\u0126\u0131\7\"\2\2\u0127\u0128\7\"\2\2\u0128"+
		"\u0131\7\"\2\2\u0129\u0131\7\5\2\2\u012a\u012b\7\5\2\2\u012b\u0131\7\""+
		"\2\2\u012c\u0131\7\6\2\2\u012d\u012e\7\6\2\2\u012e\u0131\7\"\2\2\u012f"+
		"\u0131\7\7\2\2\u0130\u0125\3\2\2\2\u0130\u0127\3\2\2\2\u0130\u0129\3\2"+
		"\2\2\u0130\u012a\3\2\2\2\u0130\u012c\3\2\2\2\u0130\u012d\3\2\2\2\u0130"+
		"\u012f\3\2\2\2\u0131#\3\2\2\2\u0132\u0141\5&\24\2\u0133\u0135\7!\2\2\u0134"+
		"\u0133\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2"+
		"\2\2\u0137\u0139\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u013d\7\34\2\2\u013a"+
		"\u013c\7!\2\2\u013b\u013a\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2"+
		"\2\2\u013d\u013e\3\2\2\2\u013e\u0140\3\2\2\2\u013f\u013d\3\2\2\2\u0140"+
		"\u0142\5\64\33\2\u0141\u0136\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0146\3"+
		"\2\2\2\u0143\u0145\7!\2\2\u0144\u0143\3\2\2\2\u0145\u0148\3\2\2\2\u0146"+
		"\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u014c\3\2\2\2\u0148\u0146\3\2"+
		"\2\2\u0149\u014b\5(\25\2\u014a\u0149\3\2\2\2\u014b\u014e\3\2\2\2\u014c"+
		"\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u0160\3\2\2\2\u014e\u014c\3\2"+
		"\2\2\u014f\u0153\7\3\2\2\u0150\u0152\7!\2\2\u0151\u0150\3\2\2\2\u0152"+
		"\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0156\3\2"+
		"\2\2\u0155\u0153\3\2\2\2\u0156\u015a\5$\23\2\u0157\u0159\7!\2\2\u0158"+
		"\u0157\3\2\2\2\u0159\u015c\3\2\2\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2"+
		"\2\2\u015b\u015d\3\2\2\2\u015c\u015a\3\2\2\2\u015d\u015e\7\4\2\2\u015e"+
		"\u0160\3\2\2\2\u015f\u0132\3\2\2\2\u015f\u014f\3\2\2\2\u0160%\3\2\2\2"+
		"\u0161\u0162\t\5\2\2\u0162\'\3\2\2\2\u0163\u0165\7\32\2\2\u0164\u0166"+
		"\7!\2\2\u0165\u0164\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0167\3\2\2\2\u0167"+
		"\u0176\5\62\32\2\u0168\u016a\7!\2\2\u0169\u0168\3\2\2\2\u0169\u016a\3"+
		"\2\2\2\u016a\u016b\3\2\2\2\u016b\u016d\7\3\2\2\u016c\u016e\7!\2\2\u016d"+
		"\u016c\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u0170\3\2\2\2\u016f\u0171\5*"+
		"\26\2\u0170\u016f\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0173\3\2\2\2\u0172"+
		"\u0174\7!\2\2\u0173\u0172\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0175\3\2"+
		"\2\2\u0175\u0177\7\4\2\2\u0176\u0169\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\u0180\3\2\2\2\u0178\u017a\7!\2\2\u0179\u0178\3\2\2\2\u0179\u017a\3\2"+
		"\2\2\u017a\u017b\3\2\2\2\u017b\u017d\7\34\2\2\u017c\u017e\7!\2\2\u017d"+
		"\u017c\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0181\5\64"+
		"\33\2\u0180\u0179\3\2\2\2\u0180\u0181\3\2\2\2\u0181)\3\2\2\2\u0182\u018d"+
		"\5$\23\2\u0183\u0185\7!\2\2\u0184\u0183\3\2\2\2\u0184\u0185\3\2\2\2\u0185"+
		"\u0186\3\2\2\2\u0186\u0188\7\33\2\2\u0187\u0189\7!\2\2\u0188\u0187\3\2"+
		"\2\2\u0188\u0189\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018c\5$\23\2\u018b"+
		"\u0184\3\2\2\2\u018c\u018f\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2"+
		"\2\2\u018e+\3\2\2\2\u018f\u018d\3\2\2\2\u0190\u0192\5.\30\2\u0191\u0190"+
		"\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0198\5\60\31\2"+
		"\u0194\u0195\7\30\2\2\u0195\u0197\5\60\31\2\u0196\u0194\3\2\2\2\u0197"+
		"\u019a\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199-\3\2\2\2"+
		"\u019a\u0198\3\2\2\2\u019b\u019c\7\b\2\2\u019c\u019d\7\30\2\2\u019d/\3"+
		"\2\2\2\u019e\u019f\7*\2\2\u019f\61\3\2\2\2\u01a0\u01a1\7*\2\2\u01a1\63"+
		"\3\2\2\2\u01a2\u01a3\7*\2\2\u01a3\65\3\2\2\2>9>ELSZdglv\177\u0085\u0091"+
		"\u0096\u0098\u009e\u00a5\u00a7\u00ad\u00af\u00b5\u00be\u00c6\u00ce\u00d5"+
		"\u00dc\u00e3\u00ea\u00ef\u00f4\u00f9\u0100\u0107\u010e\u0115\u011c\u0121"+
		"\u0130\u0136\u013d\u0141\u0146\u014c\u0153\u015a\u015f\u0165\u0169\u016d"+
		"\u0170\u0173\u0176\u0179\u017d\u0180\u0184\u0188\u018d\u0191\u0198";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}