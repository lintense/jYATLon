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
		BETWEEN=9, AFTER=10, EMPTY=11, END=12, CALL=13, IF=14, CONTROL=15, ROOT=16, 
		LCURL=17, LBRACK=18, LVALUE=19, RCURL=20, RBRACK=21, RVALUE=22, PATHSEP=23, 
		ESCAPE=24, DOT=25, COMMA=26, COLON=27, NEWLINE=28, NOT=29, MINUS=30, WS=31, 
		SPACE=32, EQUAL=33, SECTIONSEP=34, POUND=35, COMMENTSEP=36, PIPE=37, OR=38, 
		AMP=39, AND=40, NAME=41, INTEGER=42, NUMBER=43, STRING=44;
	public static final int
		RULE_template = 0, RULE_section = 1, RULE_line = 2, RULE_lineExp = 3, 
		RULE_escapedChar = 4, RULE_controlExp = 5, RULE_controlOp = 6, RULE_commentOp = 7, 
		RULE_rawText = 8, RULE_value = 9, RULE_ifExp = 10, RULE_callExp = 11, 
		RULE_logicalExp = 12, RULE_logicalOp = 13, RULE_binaryExp = 14, RULE_unaryOp = 15, 
		RULE_binaryOp = 16, RULE_valueExp = 17, RULE_valueArg = 18, RULE_operation = 19, 
		RULE_argExp = 20, RULE_pathExp = 21, RULE_anyPathOp = 22, RULE_pathArg = 23, 
		RULE_pathName = 24, RULE_methodName = 25, RULE_aliasName = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"template", "section", "line", "lineExp", "escapedChar", "controlExp", 
			"controlOp", "commentOp", "rawText", "value", "ifExp", "callExp", "logicalExp", 
			"logicalOp", "binaryExp", "unaryOp", "binaryOp", "valueExp", "valueArg", 
			"operation", "argExp", "pathExp", "anyPathOp", "pathArg", "pathName", 
			"methodName", "aliasName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'>'", "'<'", "'<>'", "'...'", "'begin'", "'before'", 
			"'between'", "'after'", "'empty'", "'end'", "'call'", "'if'", null, "'$'", 
			"'{'", "'['", null, "'}'", "']'", null, null, "'~'", "'.'", "','", "':'", 
			"'\n'", "'!'", "'-'", null, null, "'='", null, "'#'", null, "'|'", null, 
			"'&'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "ANYPATH", "BEGIN", "BEFORE", "BETWEEN", 
			"AFTER", "EMPTY", "END", "CALL", "IF", "CONTROL", "ROOT", "LCURL", "LBRACK", 
			"LVALUE", "RCURL", "RBRACK", "RVALUE", "PATHSEP", "ESCAPE", "DOT", "COMMA", 
			"COLON", "NEWLINE", "NOT", "MINUS", "WS", "SPACE", "EQUAL", "SECTIONSEP", 
			"POUND", "COMMENTSEP", "PIPE", "OR", "AMP", "AND", "NAME", "INTEGER", 
			"NUMBER", "STRING"
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
			setState(55); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(54);
				section();
				}
				}
				setState(57); 
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
			setState(103);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SPACE:
			case SECTIONSEP:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(59);
					match(SPACE);
					}
					}
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(65);
				match(SECTIONSEP);
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(66);
					match(SPACE);
					}
					}
					setState(71);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(72);
				pathExp();
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(73);
					match(SPACE);
					}
					}
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(79);
				match(SECTIONSEP);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(80);
					match(SPACE);
					}
					}
					setState(85);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(86);
				match(NEWLINE);
				setState(90);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(87);
						line();
						}
						} 
					}
					setState(92);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				}
				break;
			case EQUAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				match(EQUAL);
				setState(94);
				match(ROOT);
				setState(95);
				match(EQUAL);
				setState(96);
				match(NEWLINE);
				setState(100);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(97);
						line();
						}
						} 
					}
					setState(102);
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
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << ANYPATH) | (1L << BEGIN) | (1L << BEFORE) | (1L << BETWEEN) | (1L << AFTER) | (1L << EMPTY) | (1L << END) | (1L << CALL) | (1L << IF) | (1L << CONTROL) | (1L << ROOT) | (1L << LCURL) | (1L << LBRACK) | (1L << LVALUE) | (1L << RCURL) | (1L << RBRACK) | (1L << RVALUE) | (1L << PATHSEP) | (1L << ESCAPE) | (1L << DOT) | (1L << COMMA) | (1L << COLON) | (1L << NOT) | (1L << MINUS) | (1L << WS) | (1L << SPACE) | (1L << EQUAL) | (1L << POUND) | (1L << COMMENTSEP) | (1L << PIPE) | (1L << OR) | (1L << AMP) | (1L << AND) | (1L << NAME) | (1L << INTEGER) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				{
				setState(105);
				lineExp();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
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
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMENTSEP:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				commentOp();
				}
				break;
			case ESCAPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				escapedChar();
				}
				break;
			case LVALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				value();
				}
				break;
			case CONTROL:
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
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
			case EMPTY:
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
				setState(117);
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
			setState(120);
			match(ESCAPE);
			setState(121);
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
			setState(123);
			controlOp();
			setState(125); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124);
				match(SPACE);
				}
				}
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SPACE );
			setState(129);
			aliasName();
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(130);
				match(SPACE);
				}
				}
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(136);
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
			setState(138);
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
			setState(140);
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
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SPACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(143); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(142);
						match(SPACE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(145); 
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
			case EMPTY:
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
				setState(148); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(147);
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
					setState(150); 
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
			setState(154);
			match(LVALUE);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(155);
				match(SPACE);
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(161);
				ifExp();
				setState(163); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(162);
					match(SPACE);
					}
					}
					setState(165); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SPACE );
				}
			}

			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CALL) {
				{
				setState(169);
				callExp();
				setState(171); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(170);
					match(SPACE);
					}
					}
					setState(173); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SPACE );
				}
			}

			setState(177);
			valueExp();
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(178);
				match(SPACE);
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
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
			setState(186);
			match(IF);
			setState(188); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(187);
					match(SPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(190); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(192);
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
			setState(194);
			match(CALL);
			setState(196); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(195);
				match(SPACE);
				}
				}
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SPACE );
			setState(200);
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
			setState(239);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				binaryExp();
				setState(220);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(206);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(203);
							match(SPACE);
							}
							}
							setState(208);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(209);
						logicalOp();
						setState(213);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(210);
								match(SPACE);
								}
								} 
							}
							setState(215);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
						}
						setState(216);
						binaryExp();
						}
						} 
					}
					setState(222);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(223);
				match(T__0);
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(224);
						match(SPACE);
						}
						} 
					}
					setState(229);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				setState(230);
				logicalExp();
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(231);
					match(SPACE);
					}
					}
					setState(236);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(237);
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
			setState(241);
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
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT || _la==MINUS) {
					{
					setState(243);
					unaryOp();
					}
				}

				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(246);
					match(SPACE);
					}
					}
					setState(251);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(252);
				valueExp();
				setState(270);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(256);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(253);
							match(SPACE);
							}
							}
							setState(258);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(259);
						binaryOp();
						setState(263);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(260);
							match(SPACE);
							}
							}
							setState(265);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(266);
						valueExp();
						}
						} 
					}
					setState(272);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				match(T__0);
				setState(277);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(274);
						match(SPACE);
						}
						} 
					}
					setState(279);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				}
				setState(280);
				binaryExp();
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(281);
					match(SPACE);
					}
					}
					setState(286);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(287);
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
			setState(291);
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
			setState(304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				match(NOT);
				setState(294);
				match(EQUAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(295);
				match(EQUAL);
				setState(296);
				match(EQUAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(297);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(298);
				match(T__2);
				setState(299);
				match(EQUAL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(300);
				match(T__3);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(301);
				match(T__3);
				setState(302);
				match(EQUAL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(303);
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
			setState(351);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ROOT:
			case NAME:
			case INTEGER:
			case NUMBER:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				valueArg();
				setState(321);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(310);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SPACE) {
						{
						{
						setState(307);
						match(SPACE);
						}
						}
						setState(312);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(313);
					match(COLON);
					setState(317);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SPACE) {
						{
						{
						setState(314);
						match(SPACE);
						}
						}
						setState(319);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(320);
					aliasName();
					}
					break;
				}
				setState(326);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(323);
						match(SPACE);
						}
						} 
					}
					setState(328);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				}
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(329);
					operation();
					}
					}
					setState(334);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(335);
				match(T__0);
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(336);
					match(SPACE);
					}
					}
					setState(341);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(342);
				valueExp();
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(343);
					match(SPACE);
					}
					}
					setState(348);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(349);
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
			setState(353);
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
			setState(355);
			match(DOT);
			setState(357);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SPACE) {
				{
				setState(356);
				match(SPACE);
				}
			}

			setState(359);
			methodName();
			setState(374);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(360);
					match(SPACE);
					}
				}

				setState(363);
				match(T__0);
				setState(365);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(364);
					match(SPACE);
					}
					break;
				}
				setState(368);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ROOT) | (1L << NAME) | (1L << INTEGER) | (1L << NUMBER) | (1L << STRING))) != 0)) {
					{
					setState(367);
					argExp();
					}
				}

				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(370);
					match(SPACE);
					}
				}

				setState(373);
				match(T__1);
				}
				break;
			}
			setState(384);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(377);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(376);
					match(SPACE);
					}
				}

				setState(379);
				match(COLON);
				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(380);
					match(SPACE);
					}
				}

				setState(383);
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
			setState(386);
			valueExp();
			setState(397);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(388);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SPACE) {
						{
						setState(387);
						match(SPACE);
						}
					}

					setState(390);
					match(COMMA);
					setState(392);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SPACE) {
						{
						setState(391);
						match(SPACE);
						}
					}

					setState(394);
					valueExp();
					}
					} 
				}
				setState(399);
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
		public List<PathArgContext> pathArg() {
			return getRuleContexts(PathArgContext.class);
		}
		public PathArgContext pathArg(int i) {
			return getRuleContext(PathArgContext.class,i);
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
			setState(401);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ANYPATH) {
				{
				setState(400);
				anyPathOp();
				}
			}

			setState(403);
			pathArg();
			setState(408);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PATHSEP) {
				{
				{
				setState(404);
				match(PATHSEP);
				setState(405);
				pathArg();
				}
				}
				setState(410);
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
			setState(411);
			match(ANYPATH);
			setState(412);
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

	public static class PathArgContext extends ParserRuleContext {
		public PathNameContext pathName() {
			return getRuleContext(PathNameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(YATLParser.COLON, 0); }
		public AliasNameContext aliasName() {
			return getRuleContext(AliasNameContext.class,0);
		}
		public PathArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterPathArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitPathArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitPathArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathArgContext pathArg() throws RecognitionException {
		PathArgContext _localctx = new PathArgContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_pathArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			pathName();
			setState(417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(415);
				match(COLON);
				setState(416);
				aliasName();
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
		enterRule(_localctx, 48, RULE_pathName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
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
		enterRule(_localctx, 50, RULE_methodName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
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
		enterRule(_localctx, 52, RULE_aliasName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3.\u01ac\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\6\2:\n\2\r\2\16\2;\3\3\7\3?\n\3\f\3"+
		"\16\3B\13\3\3\3\3\3\7\3F\n\3\f\3\16\3I\13\3\3\3\3\3\7\3M\n\3\f\3\16\3"+
		"P\13\3\3\3\3\3\7\3T\n\3\f\3\16\3W\13\3\3\3\3\3\7\3[\n\3\f\3\16\3^\13\3"+
		"\3\3\3\3\3\3\3\3\3\3\7\3e\n\3\f\3\16\3h\13\3\5\3j\n\3\3\4\7\4m\n\4\f\4"+
		"\16\4p\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\5\5y\n\5\3\6\3\6\3\6\3\7\3\7\6"+
		"\7\u0080\n\7\r\7\16\7\u0081\3\7\3\7\7\7\u0086\n\7\f\7\16\7\u0089\13\7"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\n\6\n\u0092\n\n\r\n\16\n\u0093\3\n\6\n\u0097"+
		"\n\n\r\n\16\n\u0098\5\n\u009b\n\n\3\13\3\13\7\13\u009f\n\13\f\13\16\13"+
		"\u00a2\13\13\3\13\3\13\6\13\u00a6\n\13\r\13\16\13\u00a7\5\13\u00aa\n\13"+
		"\3\13\3\13\6\13\u00ae\n\13\r\13\16\13\u00af\5\13\u00b2\n\13\3\13\3\13"+
		"\7\13\u00b6\n\13\f\13\16\13\u00b9\13\13\3\13\3\13\3\f\3\f\6\f\u00bf\n"+
		"\f\r\f\16\f\u00c0\3\f\3\f\3\r\3\r\6\r\u00c7\n\r\r\r\16\r\u00c8\3\r\3\r"+
		"\3\16\3\16\7\16\u00cf\n\16\f\16\16\16\u00d2\13\16\3\16\3\16\7\16\u00d6"+
		"\n\16\f\16\16\16\u00d9\13\16\3\16\3\16\7\16\u00dd\n\16\f\16\16\16\u00e0"+
		"\13\16\3\16\3\16\7\16\u00e4\n\16\f\16\16\16\u00e7\13\16\3\16\3\16\7\16"+
		"\u00eb\n\16\f\16\16\16\u00ee\13\16\3\16\3\16\5\16\u00f2\n\16\3\17\3\17"+
		"\3\20\5\20\u00f7\n\20\3\20\7\20\u00fa\n\20\f\20\16\20\u00fd\13\20\3\20"+
		"\3\20\7\20\u0101\n\20\f\20\16\20\u0104\13\20\3\20\3\20\7\20\u0108\n\20"+
		"\f\20\16\20\u010b\13\20\3\20\3\20\7\20\u010f\n\20\f\20\16\20\u0112\13"+
		"\20\3\20\3\20\7\20\u0116\n\20\f\20\16\20\u0119\13\20\3\20\3\20\7\20\u011d"+
		"\n\20\f\20\16\20\u0120\13\20\3\20\3\20\5\20\u0124\n\20\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0133\n\22\3\23"+
		"\3\23\7\23\u0137\n\23\f\23\16\23\u013a\13\23\3\23\3\23\7\23\u013e\n\23"+
		"\f\23\16\23\u0141\13\23\3\23\5\23\u0144\n\23\3\23\7\23\u0147\n\23\f\23"+
		"\16\23\u014a\13\23\3\23\7\23\u014d\n\23\f\23\16\23\u0150\13\23\3\23\3"+
		"\23\7\23\u0154\n\23\f\23\16\23\u0157\13\23\3\23\3\23\7\23\u015b\n\23\f"+
		"\23\16\23\u015e\13\23\3\23\3\23\5\23\u0162\n\23\3\24\3\24\3\25\3\25\5"+
		"\25\u0168\n\25\3\25\3\25\5\25\u016c\n\25\3\25\3\25\5\25\u0170\n\25\3\25"+
		"\5\25\u0173\n\25\3\25\5\25\u0176\n\25\3\25\5\25\u0179\n\25\3\25\5\25\u017c"+
		"\n\25\3\25\3\25\5\25\u0180\n\25\3\25\5\25\u0183\n\25\3\26\3\26\5\26\u0187"+
		"\n\26\3\26\3\26\5\26\u018b\n\26\3\26\7\26\u018e\n\26\f\26\16\26\u0191"+
		"\13\26\3\27\5\27\u0194\n\27\3\27\3\27\3\27\7\27\u0199\n\27\f\27\16\27"+
		"\u019c\13\27\3\30\3\30\3\30\3\31\3\31\3\31\5\31\u01a4\n\31\3\32\3\32\3"+
		"\33\3\33\3\34\3\34\3\34\2\2\35\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\66\2\6\t\2\21\21\25\25\32\32\36\36\"\"$$&&\4\2((**"+
		"\3\2\37 \4\2\22\22+.\2\u01d5\29\3\2\2\2\4i\3\2\2\2\6n\3\2\2\2\bx\3\2\2"+
		"\2\nz\3\2\2\2\f}\3\2\2\2\16\u008c\3\2\2\2\20\u008e\3\2\2\2\22\u009a\3"+
		"\2\2\2\24\u009c\3\2\2\2\26\u00bc\3\2\2\2\30\u00c4\3\2\2\2\32\u00f1\3\2"+
		"\2\2\34\u00f3\3\2\2\2\36\u0123\3\2\2\2 \u0125\3\2\2\2\"\u0132\3\2\2\2"+
		"$\u0161\3\2\2\2&\u0163\3\2\2\2(\u0165\3\2\2\2*\u0184\3\2\2\2,\u0193\3"+
		"\2\2\2.\u019d\3\2\2\2\60\u01a0\3\2\2\2\62\u01a5\3\2\2\2\64\u01a7\3\2\2"+
		"\2\66\u01a9\3\2\2\28:\5\4\3\298\3\2\2\2:;\3\2\2\2;9\3\2\2\2;<\3\2\2\2"+
		"<\3\3\2\2\2=?\7\"\2\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2"+
		"\2B@\3\2\2\2CG\7$\2\2DF\7\"\2\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2"+
		"\2HJ\3\2\2\2IG\3\2\2\2JN\5,\27\2KM\7\"\2\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2"+
		"\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QU\7$\2\2RT\7\"\2\2SR\3\2\2\2TW\3\2\2"+
		"\2US\3\2\2\2UV\3\2\2\2VX\3\2\2\2WU\3\2\2\2X\\\7\36\2\2Y[\5\6\4\2ZY\3\2"+
		"\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]j\3\2\2\2^\\\3\2\2\2_`\7#\2\2`a\7"+
		"\22\2\2ab\7#\2\2bf\7\36\2\2ce\5\6\4\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg"+
		"\3\2\2\2gj\3\2\2\2hf\3\2\2\2i@\3\2\2\2i_\3\2\2\2j\5\3\2\2\2km\5\b\5\2"+
		"lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2\2qr\7\36\2"+
		"\2r\7\3\2\2\2sy\5\20\t\2ty\5\n\6\2uy\5\24\13\2vy\5\f\7\2wy\5\22\n\2xs"+
		"\3\2\2\2xt\3\2\2\2xu\3\2\2\2xv\3\2\2\2xw\3\2\2\2y\t\3\2\2\2z{\7\32\2\2"+
		"{|\13\2\2\2|\13\3\2\2\2}\177\5\16\b\2~\u0080\7\"\2\2\177~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2"+
		"\2\u0083\u0087\5\66\34\2\u0084\u0086\7\"\2\2\u0085\u0084\3\2\2\2\u0086"+
		"\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2"+
		"\2\2\u0089\u0087\3\2\2\2\u008a\u008b\7\26\2\2\u008b\r\3\2\2\2\u008c\u008d"+
		"\7\21\2\2\u008d\17\3\2\2\2\u008e\u008f\7&\2\2\u008f\21\3\2\2\2\u0090\u0092"+
		"\7\"\2\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u009b\3\2\2\2\u0095\u0097\n\2\2\2\u0096\u0095\3\2"+
		"\2\2\u0097\u0098\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009b\3\2\2\2\u009a\u0091\3\2\2\2\u009a\u0096\3\2\2\2\u009b\23\3\2\2"+
		"\2\u009c\u00a0\7\25\2\2\u009d\u009f\7\"\2\2\u009e\u009d\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a9\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a5\5\26\f\2\u00a4\u00a6\7\"\2\2\u00a5"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a3\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00b1\3\2\2\2\u00ab\u00ad\5\30\r\2\u00ac\u00ae\7\"\2\2\u00ad\u00ac\3"+
		"\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\u00b2\3\2\2\2\u00b1\u00ab\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2"+
		"\2\2\u00b3\u00b7\5$\23\2\u00b4\u00b6\7\"\2\2\u00b5\u00b4\3\2\2\2\u00b6"+
		"\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2"+
		"\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\7\30\2\2\u00bb\25\3\2\2\2\u00bc\u00be"+
		"\7\20\2\2\u00bd\u00bf\7\"\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2"+
		"\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3"+
		"\5\32\16\2\u00c3\27\3\2\2\2\u00c4\u00c6\7\17\2\2\u00c5\u00c7\7\"\2\2\u00c6"+
		"\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\5,\27\2\u00cb\31\3\2\2\2\u00cc\u00de"+
		"\5\36\20\2\u00cd\u00cf\7\"\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d2\3\2\2\2"+
		"\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\3\2\2\2\u00d2\u00d0"+
		"\3\2\2\2\u00d3\u00d7\5\34\17\2\u00d4\u00d6\7\"\2\2\u00d5\u00d4\3\2\2\2"+
		"\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da"+
		"\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db\5\36\20\2\u00db\u00dd\3\2\2\2"+
		"\u00dc\u00d0\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df"+
		"\3\2\2\2\u00df\u00f2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e5\7\3\2\2\u00e2"+
		"\u00e4\7\"\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2"+
		"\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8"+
		"\u00ec\5\32\16\2\u00e9\u00eb\7\"\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ee\3"+
		"\2\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ef\u00f0\7\4\2\2\u00f0\u00f2\3\2\2\2\u00f1\u00cc\3\2"+
		"\2\2\u00f1\u00e1\3\2\2\2\u00f2\33\3\2\2\2\u00f3\u00f4\t\3\2\2\u00f4\35"+
		"\3\2\2\2\u00f5\u00f7\5 \21\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\u00fb\3\2\2\2\u00f8\u00fa\7\"\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fd\3\2"+
		"\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fe\u0110\5$\23\2\u00ff\u0101\7\"\2\2\u0100\u00ff\3\2"+
		"\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"\u0105\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0109\5\"\22\2\u0106\u0108\7"+
		"\"\2\2\u0107\u0106\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d\5$"+
		"\23\2\u010d\u010f\3\2\2\2\u010e\u0102\3\2\2\2\u010f\u0112\3\2\2\2\u0110"+
		"\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0124\3\2\2\2\u0112\u0110\3\2"+
		"\2\2\u0113\u0117\7\3\2\2\u0114\u0116\7\"\2\2\u0115\u0114\3\2\2\2\u0116"+
		"\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a\3\2"+
		"\2\2\u0119\u0117\3\2\2\2\u011a\u011e\5\36\20\2\u011b\u011d\7\"\2\2\u011c"+
		"\u011b\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2"+
		"\2\2\u011f\u0121\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0122\7\4\2\2\u0122"+
		"\u0124\3\2\2\2\u0123\u00f6\3\2\2\2\u0123\u0113\3\2\2\2\u0124\37\3\2\2"+
		"\2\u0125\u0126\t\4\2\2\u0126!\3\2\2\2\u0127\u0128\7\37\2\2\u0128\u0133"+
		"\7#\2\2\u0129\u012a\7#\2\2\u012a\u0133\7#\2\2\u012b\u0133\7\5\2\2\u012c"+
		"\u012d\7\5\2\2\u012d\u0133\7#\2\2\u012e\u0133\7\6\2\2\u012f\u0130\7\6"+
		"\2\2\u0130\u0133\7#\2\2\u0131\u0133\7\7\2\2\u0132\u0127\3\2\2\2\u0132"+
		"\u0129\3\2\2\2\u0132\u012b\3\2\2\2\u0132\u012c\3\2\2\2\u0132\u012e\3\2"+
		"\2\2\u0132\u012f\3\2\2\2\u0132\u0131\3\2\2\2\u0133#\3\2\2\2\u0134\u0143"+
		"\5&\24\2\u0135\u0137\7\"\2\2\u0136\u0135\3\2\2\2\u0137\u013a\3\2\2\2\u0138"+
		"\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013b\3\2\2\2\u013a\u0138\3\2"+
		"\2\2\u013b\u013f\7\35\2\2\u013c\u013e\7\"\2\2\u013d\u013c\3\2\2\2\u013e"+
		"\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0142\3\2"+
		"\2\2\u0141\u013f\3\2\2\2\u0142\u0144\5\66\34\2\u0143\u0138\3\2\2\2\u0143"+
		"\u0144\3\2\2\2\u0144\u0148\3\2\2\2\u0145\u0147\7\"\2\2\u0146\u0145\3\2"+
		"\2\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149"+
		"\u014e\3\2\2\2\u014a\u0148\3\2\2\2\u014b\u014d\5(\25\2\u014c\u014b\3\2"+
		"\2\2\u014d\u0150\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f"+
		"\u0162\3\2\2\2\u0150\u014e\3\2\2\2\u0151\u0155\7\3\2\2\u0152\u0154\7\""+
		"\2\2\u0153\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155"+
		"\u0156\3\2\2\2\u0156\u0158\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u015c\5$"+
		"\23\2\u0159\u015b\7\"\2\2\u015a\u0159\3\2\2\2\u015b\u015e\3\2\2\2\u015c"+
		"\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015f\3\2\2\2\u015e\u015c\3\2"+
		"\2\2\u015f\u0160\7\4\2\2\u0160\u0162\3\2\2\2\u0161\u0134\3\2\2\2\u0161"+
		"\u0151\3\2\2\2\u0162%\3\2\2\2\u0163\u0164\t\5\2\2\u0164\'\3\2\2\2\u0165"+
		"\u0167\7\33\2\2\u0166\u0168\7\"\2\2\u0167\u0166\3\2\2\2\u0167\u0168\3"+
		"\2\2\2\u0168\u0169\3\2\2\2\u0169\u0178\5\64\33\2\u016a\u016c\7\"\2\2\u016b"+
		"\u016a\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016f\7\3"+
		"\2\2\u016e\u0170\7\"\2\2\u016f\u016e\3\2\2\2\u016f\u0170\3\2\2\2\u0170"+
		"\u0172\3\2\2\2\u0171\u0173\5*\26\2\u0172\u0171\3\2\2\2\u0172\u0173\3\2"+
		"\2\2\u0173\u0175\3\2\2\2\u0174\u0176\7\"\2\2\u0175\u0174\3\2\2\2\u0175"+
		"\u0176\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0179\7\4\2\2\u0178\u016b\3\2"+
		"\2\2\u0178\u0179\3\2\2\2\u0179\u0182\3\2\2\2\u017a\u017c\7\"\2\2\u017b"+
		"\u017a\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017f\7\35"+
		"\2\2\u017e\u0180\7\"\2\2\u017f\u017e\3\2\2\2\u017f\u0180\3\2\2\2\u0180"+
		"\u0181\3\2\2\2\u0181\u0183\5\66\34\2\u0182\u017b\3\2\2\2\u0182\u0183\3"+
		"\2\2\2\u0183)\3\2\2\2\u0184\u018f\5$\23\2\u0185\u0187\7\"\2\2\u0186\u0185"+
		"\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u018a\7\34\2\2"+
		"\u0189\u018b\7\"\2\2\u018a\u0189\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c"+
		"\3\2\2\2\u018c\u018e\5$\23\2\u018d\u0186\3\2\2\2\u018e\u0191\3\2\2\2\u018f"+
		"\u018d\3\2\2\2\u018f\u0190\3\2\2\2\u0190+\3\2\2\2\u0191\u018f\3\2\2\2"+
		"\u0192\u0194\5.\30\2\u0193\u0192\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0195"+
		"\3\2\2\2\u0195\u019a\5\60\31\2\u0196\u0197\7\31\2\2\u0197\u0199\5\60\31"+
		"\2\u0198\u0196\3\2\2\2\u0199\u019c\3\2\2\2\u019a\u0198\3\2\2\2\u019a\u019b"+
		"\3\2\2\2\u019b-\3\2\2\2\u019c\u019a\3\2\2\2\u019d\u019e\7\b\2\2\u019e"+
		"\u019f\7\31\2\2\u019f/\3\2\2\2\u01a0\u01a3\5\62\32\2\u01a1\u01a2\7\35"+
		"\2\2\u01a2\u01a4\5\66\34\2\u01a3\u01a1\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4"+
		"\61\3\2\2\2\u01a5\u01a6\7+\2\2\u01a6\63\3\2\2\2\u01a7\u01a8\7+\2\2\u01a8"+
		"\65\3\2\2\2\u01a9\u01aa\7+\2\2\u01aa\67\3\2\2\2?;@GNU\\finx\u0081\u0087"+
		"\u0093\u0098\u009a\u00a0\u00a7\u00a9\u00af\u00b1\u00b7\u00c0\u00c8\u00d0"+
		"\u00d7\u00de\u00e5\u00ec\u00f1\u00f6\u00fb\u0102\u0109\u0110\u0117\u011e"+
		"\u0123\u0132\u0138\u013f\u0143\u0148\u014e\u0155\u015c\u0161\u0167\u016b"+
		"\u016f\u0172\u0175\u0178\u017b\u017f\u0182\u0186\u018a\u018f\u0193\u019a"+
		"\u01a3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}