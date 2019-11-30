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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, ANYPATH=15, BEGIN=16, 
		BEFORE=17, BETWEEN=18, AFTER=19, END=20, CALL=21, IF=22, ROOT=23, POUND=24, 
		LEFTCB=25, RIGHTCB=26, ESCAPE=27, EQUAL=28, COMMA=29, COLON=30, NEWLINE=31, 
		WS=32, SPACE=33, NAME=34, INTEGER=35, NUMBER=36, STRING=37;
	public static final int
		RULE_template = 0, RULE_section = 1, RULE_line = 2, RULE_lineExp = 3, 
		RULE_escapedChar = 4, RULE_escapedBraket = 5, RULE_controlExp = 6, RULE_controlOp = 7, 
		RULE_commentOp = 8, RULE_rawText = 9, RULE_value = 10, RULE_ifExp = 11, 
		RULE_callExp = 12, RULE_logicalExp = 13, RULE_logicalOp = 14, RULE_binaryExp = 15, 
		RULE_unaryOp = 16, RULE_binaryOp = 17, RULE_valueExp = 18, RULE_valueArg = 19, 
		RULE_operation = 20, RULE_argExp = 21, RULE_pathExp = 22, RULE_anyPathOp = 23, 
		RULE_pathName = 24, RULE_methodName = 25, RULE_aliasName = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"template", "section", "line", "lineExp", "escapedChar", "escapedBraket", 
			"controlExp", "controlOp", "commentOp", "rawText", "value", "ifExp", 
			"callExp", "logicalExp", "logicalOp", "binaryExp", "unaryOp", "binaryOp", 
			"valueExp", "valueArg", "operation", "argExp", "pathExp", "anyPathOp", 
			"pathName", "methodName", "aliasName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'('", "')'", "'||'", "'&&'", "'!'", "'-'", "'>'", 
			"'<'", "'<>'", "'.'", "'/'", "'\\'", "'...'", "'begin'", "'before'", 
			"'between'", "'after'", "'end'", "'call'", "'if'", "'$'", "'#'", "'{'", 
			"'}'", "'~'", "'='", "','", "':'", "'\n'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "ANYPATH", "BEGIN", "BEFORE", "BETWEEN", "AFTER", "END", 
			"CALL", "IF", "ROOT", "POUND", "LEFTCB", "RIGHTCB", "ESCAPE", "EQUAL", 
			"COMMA", "COLON", "NEWLINE", "WS", "SPACE", "NAME", "INTEGER", "NUMBER", 
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
			} while ( _la==EQUAL || _la==SPACE );
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> EQUAL() { return getTokens(YATLParser.EQUAL); }
		public TerminalNode EQUAL(int i) {
			return getToken(YATLParser.EQUAL, i);
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
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
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
				match(EQUAL);
				setState(66);
				match(EQUAL);
				setState(67);
				match(EQUAL);
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(68);
					match(SPACE);
					}
					}
					setState(73);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(74);
				pathExp();
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(75);
					match(SPACE);
					}
					}
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(81);
				match(EQUAL);
				setState(82);
				match(EQUAL);
				setState(83);
				match(EQUAL);
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(84);
					match(SPACE);
					}
					}
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(90);
				match(NEWLINE);
				setState(94);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(91);
						line();
						}
						} 
					}
					setState(96);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				match(EQUAL);
				setState(98);
				match(ROOT);
				setState(99);
				match(EQUAL);
				setState(100);
				match(NEWLINE);
				setState(104);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(101);
						line();
						}
						} 
					}
					setState(106);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << ANYPATH) | (1L << BEGIN) | (1L << BEFORE) | (1L << BETWEEN) | (1L << AFTER) | (1L << END) | (1L << CALL) | (1L << IF) | (1L << ROOT) | (1L << POUND) | (1L << LEFTCB) | (1L << RIGHTCB) | (1L << ESCAPE) | (1L << COMMA) | (1L << COLON) | (1L << WS) | (1L << SPACE) | (1L << NAME) | (1L << INTEGER) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				{
				setState(109);
				lineExp();
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
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
		public EscapedBraketContext escapedBraket() {
			return getRuleContext(EscapedBraketContext.class,0);
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
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				commentOp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				escapedChar();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				value();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				controlExp();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(121);
				rawText();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(122);
				escapedBraket();
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
			setState(125);
			match(ESCAPE);
			setState(126);
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

	public static class EscapedBraketContext extends ParserRuleContext {
		public TerminalNode LEFTCB() { return getToken(YATLParser.LEFTCB, 0); }
		public TerminalNode POUND() { return getToken(YATLParser.POUND, 0); }
		public EscapedBraketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escapedBraket; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).enterEscapedBraket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YATLListener ) ((YATLListener)listener).exitEscapedBraket(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YATLVisitor ) return ((YATLVisitor<? extends T>)visitor).visitEscapedBraket(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EscapedBraketContext escapedBraket() throws RecognitionException {
		EscapedBraketContext _localctx = new EscapedBraketContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_escapedBraket);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << POUND) | (1L << LEFTCB))) != 0)) ) {
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

	public static class ControlExpContext extends ParserRuleContext {
		public TerminalNode LEFTCB() { return getToken(YATLParser.LEFTCB, 0); }
		public ControlOpContext controlOp() {
			return getRuleContext(ControlOpContext.class,0);
		}
		public AliasNameContext aliasName() {
			return getRuleContext(AliasNameContext.class,0);
		}
		public TerminalNode RIGHTCB() { return getToken(YATLParser.RIGHTCB, 0); }
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
		enterRule(_localctx, 12, RULE_controlExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(LEFTCB);
			setState(131);
			controlOp();
			setState(133); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(132);
				match(SPACE);
				}
				}
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SPACE );
			setState(137);
			aliasName();
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(138);
				match(SPACE);
				}
				}
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(144);
			match(RIGHTCB);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode BEGIN() { return getToken(YATLParser.BEGIN, 0); }
		public TerminalNode BEFORE() { return getToken(YATLParser.BEFORE, 0); }
		public TerminalNode BETWEEN() { return getToken(YATLParser.BETWEEN, 0); }
		public TerminalNode AFTER() { return getToken(YATLParser.AFTER, 0); }
		public TerminalNode END() { return getToken(YATLParser.END, 0); }
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
		enterRule(_localctx, 14, RULE_controlOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BEGIN) | (1L << BEFORE) | (1L << BETWEEN) | (1L << AFTER) | (1L << END))) != 0)) ) {
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

	public static class CommentOpContext extends ParserRuleContext {
		public List<TerminalNode> POUND() { return getTokens(YATLParser.POUND); }
		public TerminalNode POUND(int i) {
			return getToken(YATLParser.POUND, i);
		}
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
		enterRule(_localctx, 16, RULE_commentOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(POUND);
			setState(149);
			match(POUND);
			setState(150);
			match(POUND);
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> EQUAL() { return getTokens(YATLParser.EQUAL); }
		public TerminalNode EQUAL(int i) {
			return getToken(YATLParser.EQUAL, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(YATLParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(YATLParser.NEWLINE, i);
		}
		public List<TerminalNode> ESCAPE() { return getTokens(YATLParser.ESCAPE); }
		public TerminalNode ESCAPE(int i) {
			return getToken(YATLParser.ESCAPE, i);
		}
		public List<TerminalNode> LEFTCB() { return getTokens(YATLParser.LEFTCB); }
		public TerminalNode LEFTCB(int i) {
			return getToken(YATLParser.LEFTCB, i);
		}
		public List<TerminalNode> POUND() { return getTokens(YATLParser.POUND); }
		public TerminalNode POUND(int i) {
			return getToken(YATLParser.POUND, i);
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
		enterRule(_localctx, 18, RULE_rawText);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(153); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(152);
					_la = _input.LA(1);
					if ( _la <= 0 || ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << POUND) | (1L << LEFTCB) | (1L << ESCAPE) | (1L << EQUAL) | (1L << NEWLINE))) != 0)) ) {
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
				setState(155); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode LEFTCB() { return getToken(YATLParser.LEFTCB, 0); }
		public ValueExpContext valueExp() {
			return getRuleContext(ValueExpContext.class,0);
		}
		public TerminalNode RIGHTCB() { return getToken(YATLParser.RIGHTCB, 0); }
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
		enterRule(_localctx, 20, RULE_value);
		int _la;
		try {
			setState(223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEFTCB:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				match(LEFTCB);
				setState(158);
				match(T__0);
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(159);
					match(SPACE);
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(165);
					ifExp();
					setState(167); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(166);
						match(SPACE);
						}
						}
						setState(169); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SPACE );
					}
				}

				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CALL) {
					{
					setState(173);
					callExp();
					setState(175); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(174);
						match(SPACE);
						}
						}
						setState(177); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SPACE );
					}
				}

				setState(181);
				valueExp();
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(182);
					match(SPACE);
					}
					}
					setState(187);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(188);
				match(T__1);
				setState(189);
				match(RIGHTCB);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				match(T__0);
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(192);
					match(SPACE);
					}
					}
					setState(197);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(198);
					ifExp();
					setState(200); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(199);
						match(SPACE);
						}
						}
						setState(202); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SPACE );
					}
				}

				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CALL) {
					{
					setState(206);
					callExp();
					setState(208); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(207);
						match(SPACE);
						}
						}
						setState(210); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SPACE );
					}
				}

				setState(214);
				valueExp();
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(215);
					match(SPACE);
					}
					}
					setState(220);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(221);
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
		enterRule(_localctx, 22, RULE_ifExp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(IF);
			setState(227); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(226);
					match(SPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(229); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(231);
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
		enterRule(_localctx, 24, RULE_callExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(CALL);
			setState(235); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(234);
				match(SPACE);
				}
				}
				setState(237); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SPACE );
			setState(239);
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
		enterRule(_localctx, 26, RULE_logicalExp);
		int _la;
		try {
			int _alt;
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				binaryExp();
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(245);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(242);
							match(SPACE);
							}
							}
							setState(247);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(248);
						logicalOp();
						setState(252);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(249);
								match(SPACE);
								}
								} 
							}
							setState(254);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
						}
						setState(255);
						binaryExp();
						}
						} 
					}
					setState(261);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				match(T__2);
				setState(266);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(263);
						match(SPACE);
						}
						} 
					}
					setState(268);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				}
				setState(269);
				logicalExp();
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(270);
					match(SPACE);
					}
					}
					setState(275);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(276);
				match(T__3);
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
		enterRule(_localctx, 28, RULE_logicalOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__5) ) {
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
		enterRule(_localctx, 30, RULE_binaryExp);
		int _la;
		try {
			int _alt;
			setState(328);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__6 || _la==T__7) {
					{
					setState(282);
					unaryOp();
					}
				}

				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(285);
					match(SPACE);
					}
					}
					setState(290);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(291);
				valueExp();
				setState(309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(295);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(292);
							match(SPACE);
							}
							}
							setState(297);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(298);
						binaryOp();
						setState(302);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(299);
							match(SPACE);
							}
							}
							setState(304);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(305);
						valueExp();
						}
						} 
					}
					setState(311);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
				match(T__2);
				setState(316);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(313);
						match(SPACE);
						}
						} 
					}
					setState(318);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				}
				setState(319);
				binaryExp();
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(320);
					match(SPACE);
					}
					}
					setState(325);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(326);
				match(T__3);
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
		enterRule(_localctx, 32, RULE_unaryOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			_la = _input.LA(1);
			if ( !(_la==T__6 || _la==T__7) ) {
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
		enterRule(_localctx, 34, RULE_binaryOp);
		try {
			setState(343);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(332);
				match(T__6);
				setState(333);
				match(EQUAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				match(EQUAL);
				setState(335);
				match(EQUAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(336);
				match(T__8);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(337);
				match(T__8);
				setState(338);
				match(EQUAL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(339);
				match(T__9);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(340);
				match(T__9);
				setState(341);
				match(EQUAL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(342);
				match(T__10);
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
		enterRule(_localctx, 36, RULE_valueExp);
		int _la;
		try {
			int _alt;
			setState(390);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ROOT:
			case NAME:
			case INTEGER:
			case NUMBER:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(345);
				valueArg();
				setState(360);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(349);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SPACE) {
						{
						{
						setState(346);
						match(SPACE);
						}
						}
						setState(351);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(352);
					match(COLON);
					setState(356);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SPACE) {
						{
						{
						setState(353);
						match(SPACE);
						}
						}
						setState(358);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(359);
					aliasName();
					}
					break;
				}
				setState(365);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(362);
						match(SPACE);
						}
						} 
					}
					setState(367);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				}
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(368);
					operation();
					}
					}
					setState(373);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(374);
				match(T__2);
				setState(378);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(375);
					match(SPACE);
					}
					}
					setState(380);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(381);
				valueExp();
				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(382);
					match(SPACE);
					}
					}
					setState(387);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(388);
				match(T__3);
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
		enterRule(_localctx, 38, RULE_valueArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
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
		enterRule(_localctx, 40, RULE_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			match(T__11);
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SPACE) {
				{
				setState(395);
				match(SPACE);
				}
			}

			setState(398);
			methodName();
			setState(413);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(400);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(399);
					match(SPACE);
					}
				}

				setState(402);
				match(T__2);
				setState(404);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					{
					setState(403);
					match(SPACE);
					}
					break;
				}
				setState(407);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << ROOT) | (1L << NAME) | (1L << INTEGER) | (1L << NUMBER) | (1L << STRING))) != 0)) {
					{
					setState(406);
					argExp();
					}
				}

				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(409);
					match(SPACE);
					}
				}

				setState(412);
				match(T__3);
				}
				break;
			}
			setState(423);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(416);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(415);
					match(SPACE);
					}
				}

				setState(418);
				match(COLON);
				setState(420);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(419);
					match(SPACE);
					}
				}

				setState(422);
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
		enterRule(_localctx, 42, RULE_argExp);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			valueExp();
			setState(436);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(427);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SPACE) {
						{
						setState(426);
						match(SPACE);
						}
					}

					setState(429);
					match(COMMA);
					setState(431);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SPACE) {
						{
						setState(430);
						match(SPACE);
						}
					}

					setState(433);
					valueExp();
					}
					} 
				}
				setState(438);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
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
		enterRule(_localctx, 44, RULE_pathExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ANYPATH) {
				{
				setState(439);
				anyPathOp();
				}
			}

			setState(442);
			pathName();
			setState(447);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12 || _la==T__13) {
				{
				{
				setState(443);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(444);
				pathName();
				}
				}
				setState(449);
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
		enterRule(_localctx, 46, RULE_anyPathOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450);
			match(ANYPATH);
			setState(451);
			_la = _input.LA(1);
			if ( !(_la==T__12 || _la==T__13) ) {
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
			setState(453);
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
			setState(455);
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
			setState(457);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\'\u01ce\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\6\2:\n\2\r\2\16\2;\3\3\7\3?\n\3\f\3"+
		"\16\3B\13\3\3\3\3\3\3\3\3\3\7\3H\n\3\f\3\16\3K\13\3\3\3\3\3\7\3O\n\3\f"+
		"\3\16\3R\13\3\3\3\3\3\3\3\3\3\7\3X\n\3\f\3\16\3[\13\3\3\3\3\3\7\3_\n\3"+
		"\f\3\16\3b\13\3\3\3\3\3\3\3\3\3\3\3\7\3i\n\3\f\3\16\3l\13\3\5\3n\n\3\3"+
		"\4\7\4q\n\4\f\4\16\4t\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5~\n\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\6\b\u0088\n\b\r\b\16\b\u0089\3\b\3\b\7"+
		"\b\u008e\n\b\f\b\16\b\u0091\13\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13"+
		"\6\13\u009c\n\13\r\13\16\13\u009d\3\f\3\f\3\f\7\f\u00a3\n\f\f\f\16\f\u00a6"+
		"\13\f\3\f\3\f\6\f\u00aa\n\f\r\f\16\f\u00ab\5\f\u00ae\n\f\3\f\3\f\6\f\u00b2"+
		"\n\f\r\f\16\f\u00b3\5\f\u00b6\n\f\3\f\3\f\7\f\u00ba\n\f\f\f\16\f\u00bd"+
		"\13\f\3\f\3\f\3\f\3\f\3\f\7\f\u00c4\n\f\f\f\16\f\u00c7\13\f\3\f\3\f\6"+
		"\f\u00cb\n\f\r\f\16\f\u00cc\5\f\u00cf\n\f\3\f\3\f\6\f\u00d3\n\f\r\f\16"+
		"\f\u00d4\5\f\u00d7\n\f\3\f\3\f\7\f\u00db\n\f\f\f\16\f\u00de\13\f\3\f\3"+
		"\f\5\f\u00e2\n\f\3\r\3\r\6\r\u00e6\n\r\r\r\16\r\u00e7\3\r\3\r\3\16\3\16"+
		"\6\16\u00ee\n\16\r\16\16\16\u00ef\3\16\3\16\3\17\3\17\7\17\u00f6\n\17"+
		"\f\17\16\17\u00f9\13\17\3\17\3\17\7\17\u00fd\n\17\f\17\16\17\u0100\13"+
		"\17\3\17\3\17\7\17\u0104\n\17\f\17\16\17\u0107\13\17\3\17\3\17\7\17\u010b"+
		"\n\17\f\17\16\17\u010e\13\17\3\17\3\17\7\17\u0112\n\17\f\17\16\17\u0115"+
		"\13\17\3\17\3\17\5\17\u0119\n\17\3\20\3\20\3\21\5\21\u011e\n\21\3\21\7"+
		"\21\u0121\n\21\f\21\16\21\u0124\13\21\3\21\3\21\7\21\u0128\n\21\f\21\16"+
		"\21\u012b\13\21\3\21\3\21\7\21\u012f\n\21\f\21\16\21\u0132\13\21\3\21"+
		"\3\21\7\21\u0136\n\21\f\21\16\21\u0139\13\21\3\21\3\21\7\21\u013d\n\21"+
		"\f\21\16\21\u0140\13\21\3\21\3\21\7\21\u0144\n\21\f\21\16\21\u0147\13"+
		"\21\3\21\3\21\5\21\u014b\n\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\5\23\u015a\n\23\3\24\3\24\7\24\u015e\n\24\f"+
		"\24\16\24\u0161\13\24\3\24\3\24\7\24\u0165\n\24\f\24\16\24\u0168\13\24"+
		"\3\24\5\24\u016b\n\24\3\24\7\24\u016e\n\24\f\24\16\24\u0171\13\24\3\24"+
		"\7\24\u0174\n\24\f\24\16\24\u0177\13\24\3\24\3\24\7\24\u017b\n\24\f\24"+
		"\16\24\u017e\13\24\3\24\3\24\7\24\u0182\n\24\f\24\16\24\u0185\13\24\3"+
		"\24\3\24\5\24\u0189\n\24\3\25\3\25\3\26\3\26\5\26\u018f\n\26\3\26\3\26"+
		"\5\26\u0193\n\26\3\26\3\26\5\26\u0197\n\26\3\26\5\26\u019a\n\26\3\26\5"+
		"\26\u019d\n\26\3\26\5\26\u01a0\n\26\3\26\5\26\u01a3\n\26\3\26\3\26\5\26"+
		"\u01a7\n\26\3\26\5\26\u01aa\n\26\3\27\3\27\5\27\u01ae\n\27\3\27\3\27\5"+
		"\27\u01b2\n\27\3\27\7\27\u01b5\n\27\f\27\16\27\u01b8\13\27\3\30\5\30\u01bb"+
		"\n\30\3\30\3\30\3\30\7\30\u01c0\n\30\f\30\16\30\u01c3\13\30\3\31\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\2\2\35\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\t\4\2\3\3\32\33\3\2\22\26\6\2\3"+
		"\3\32\33\35\36!!\3\2\7\b\3\2\t\n\4\2\31\31$\'\3\2\17\20\2\u01fc\29\3\2"+
		"\2\2\4m\3\2\2\2\6r\3\2\2\2\b}\3\2\2\2\n\177\3\2\2\2\f\u0082\3\2\2\2\16"+
		"\u0084\3\2\2\2\20\u0094\3\2\2\2\22\u0096\3\2\2\2\24\u009b\3\2\2\2\26\u00e1"+
		"\3\2\2\2\30\u00e3\3\2\2\2\32\u00eb\3\2\2\2\34\u0118\3\2\2\2\36\u011a\3"+
		"\2\2\2 \u014a\3\2\2\2\"\u014c\3\2\2\2$\u0159\3\2\2\2&\u0188\3\2\2\2(\u018a"+
		"\3\2\2\2*\u018c\3\2\2\2,\u01ab\3\2\2\2.\u01ba\3\2\2\2\60\u01c4\3\2\2\2"+
		"\62\u01c7\3\2\2\2\64\u01c9\3\2\2\2\66\u01cb\3\2\2\28:\5\4\3\298\3\2\2"+
		"\2:;\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\3\3\2\2\2=?\7#\2\2>=\3\2\2\2?B\3\2\2"+
		"\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2CD\7\36\2\2DE\7\36\2\2EI\7\36"+
		"\2\2FH\7#\2\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2"+
		"\2\2LP\5.\30\2MO\7#\2\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2"+
		"\2\2RP\3\2\2\2ST\7\36\2\2TU\7\36\2\2UY\7\36\2\2VX\7#\2\2WV\3\2\2\2X[\3"+
		"\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\`\7!\2\2]_\5\6\4\2^]"+
		"\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2an\3\2\2\2b`\3\2\2\2cd\7\36\2\2"+
		"de\7\31\2\2ef\7\36\2\2fj\7!\2\2gi\5\6\4\2hg\3\2\2\2il\3\2\2\2jh\3\2\2"+
		"\2jk\3\2\2\2kn\3\2\2\2lj\3\2\2\2m@\3\2\2\2mc\3\2\2\2n\5\3\2\2\2oq\5\b"+
		"\5\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\7!"+
		"\2\2v\7\3\2\2\2w~\5\22\n\2x~\5\n\6\2y~\5\26\f\2z~\5\16\b\2{~\5\24\13\2"+
		"|~\5\f\7\2}w\3\2\2\2}x\3\2\2\2}y\3\2\2\2}z\3\2\2\2}{\3\2\2\2}|\3\2\2\2"+
		"~\t\3\2\2\2\177\u0080\7\35\2\2\u0080\u0081\13\2\2\2\u0081\13\3\2\2\2\u0082"+
		"\u0083\t\2\2\2\u0083\r\3\2\2\2\u0084\u0085\7\33\2\2\u0085\u0087\5\20\t"+
		"\2\u0086\u0088\7#\2\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008f\5\66\34\2"+
		"\u008c\u008e\7#\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008f\3\2\2\2\u0092"+
		"\u0093\7\34\2\2\u0093\17\3\2\2\2\u0094\u0095\t\3\2\2\u0095\21\3\2\2\2"+
		"\u0096\u0097\7\32\2\2\u0097\u0098\7\32\2\2\u0098\u0099\7\32\2\2\u0099"+
		"\23\3\2\2\2\u009a\u009c\n\4\2\2\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2"+
		"\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\25\3\2\2\2\u009f\u00a0"+
		"\7\33\2\2\u00a0\u00a4\7\3\2\2\u00a1\u00a3\7#\2\2\u00a2\u00a1\3\2\2\2\u00a3"+
		"\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00ad\3\2"+
		"\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a9\5\30\r\2\u00a8\u00aa\7#\2\2\u00a9"+
		"\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2"+
		"\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00a7\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00b5\3\2\2\2\u00af\u00b1\5\32\16\2\u00b0\u00b2\7#\2\2\u00b1\u00b0\3"+
		"\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b6\3\2\2\2\u00b5\u00af\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\u00bb\5&\24\2\u00b8\u00ba\7#\2\2\u00b9\u00b8\3\2\2\2\u00ba"+
		"\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2"+
		"\2\2\u00bd\u00bb\3\2\2\2\u00be\u00bf\7\4\2\2\u00bf\u00c0\7\34\2\2\u00c0"+
		"\u00e2\3\2\2\2\u00c1\u00c5\7\3\2\2\u00c2\u00c4\7#\2\2\u00c3\u00c2\3\2"+
		"\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00ce\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00ca\5\30\r\2\u00c9\u00cb\7"+
		"#\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00c8\3\2\2\2\u00ce\u00cf\3\2"+
		"\2\2\u00cf\u00d6\3\2\2\2\u00d0\u00d2\5\32\16\2\u00d1\u00d3\7#\2\2\u00d2"+
		"\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2"+
		"\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d0\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00dc\5&\24\2\u00d9\u00db\7#\2\2\u00da\u00d9\3\2"+
		"\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\7\4\2\2\u00e0\u00e2\3\2"+
		"\2\2\u00e1\u009f\3\2\2\2\u00e1\u00c1\3\2\2\2\u00e2\27\3\2\2\2\u00e3\u00e5"+
		"\7\30\2\2\u00e4\u00e6\7#\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\5\34"+
		"\17\2\u00ea\31\3\2\2\2\u00eb\u00ed\7\27\2\2\u00ec\u00ee\7#\2\2\u00ed\u00ec"+
		"\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1\u00f2\5.\30\2\u00f2\33\3\2\2\2\u00f3\u0105\5 \21"+
		"\2\u00f4\u00f6\7#\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5"+
		"\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa"+
		"\u00fe\5\36\20\2\u00fb\u00fd\7#\2\2\u00fc\u00fb\3\2\2\2\u00fd\u0100\3"+
		"\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\3\2\2\2\u0100"+
		"\u00fe\3\2\2\2\u0101\u0102\5 \21\2\u0102\u0104\3\2\2\2\u0103\u00f7\3\2"+
		"\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"\u0119\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u010c\7\5\2\2\u0109\u010b\7#"+
		"\2\2\u010a\u0109\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\u010f\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0113\5\34"+
		"\17\2\u0110\u0112\7#\2\2\u0111\u0110\3\2\2\2\u0112\u0115\3\2\2\2\u0113"+
		"\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2"+
		"\2\2\u0116\u0117\7\6\2\2\u0117\u0119\3\2\2\2\u0118\u00f3\3\2\2\2\u0118"+
		"\u0108\3\2\2\2\u0119\35\3\2\2\2\u011a\u011b\t\5\2\2\u011b\37\3\2\2\2\u011c"+
		"\u011e\5\"\22\2\u011d\u011c\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0122\3"+
		"\2\2\2\u011f\u0121\7#\2\2\u0120\u011f\3\2\2\2\u0121\u0124\3\2\2\2\u0122"+
		"\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0125\3\2\2\2\u0124\u0122\3\2"+
		"\2\2\u0125\u0137\5&\24\2\u0126\u0128\7#\2\2\u0127\u0126\3\2\2\2\u0128"+
		"\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012c\3\2"+
		"\2\2\u012b\u0129\3\2\2\2\u012c\u0130\5$\23\2\u012d\u012f\7#\2\2\u012e"+
		"\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2"+
		"\2\2\u0131\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0134\5&\24\2\u0134"+
		"\u0136\3\2\2\2\u0135\u0129\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0135\3\2"+
		"\2\2\u0137\u0138\3\2\2\2\u0138\u014b\3\2\2\2\u0139\u0137\3\2\2\2\u013a"+
		"\u013e\7\5\2\2\u013b\u013d\7#\2\2\u013c\u013b\3\2\2\2\u013d\u0140\3\2"+
		"\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0141\3\2\2\2\u0140"+
		"\u013e\3\2\2\2\u0141\u0145\5 \21\2\u0142\u0144\7#\2\2\u0143\u0142\3\2"+
		"\2\2\u0144\u0147\3\2\2\2\u0145\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u0146"+
		"\u0148\3\2\2\2\u0147\u0145\3\2\2\2\u0148\u0149\7\6\2\2\u0149\u014b\3\2"+
		"\2\2\u014a\u011d\3\2\2\2\u014a\u013a\3\2\2\2\u014b!\3\2\2\2\u014c\u014d"+
		"\t\6\2\2\u014d#\3\2\2\2\u014e\u014f\7\t\2\2\u014f\u015a\7\36\2\2\u0150"+
		"\u0151\7\36\2\2\u0151\u015a\7\36\2\2\u0152\u015a\7\13\2\2\u0153\u0154"+
		"\7\13\2\2\u0154\u015a\7\36\2\2\u0155\u015a\7\f\2\2\u0156\u0157\7\f\2\2"+
		"\u0157\u015a\7\36\2\2\u0158\u015a\7\r\2\2\u0159\u014e\3\2\2\2\u0159\u0150"+
		"\3\2\2\2\u0159\u0152\3\2\2\2\u0159\u0153\3\2\2\2\u0159\u0155\3\2\2\2\u0159"+
		"\u0156\3\2\2\2\u0159\u0158\3\2\2\2\u015a%\3\2\2\2\u015b\u016a\5(\25\2"+
		"\u015c\u015e\7#\2\2\u015d\u015c\3\2\2\2\u015e\u0161\3\2\2\2\u015f\u015d"+
		"\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0162\3\2\2\2\u0161\u015f\3\2\2\2\u0162"+
		"\u0166\7 \2\2\u0163\u0165\7#\2\2\u0164\u0163\3\2\2\2\u0165\u0168\3\2\2"+
		"\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0169\3\2\2\2\u0168\u0166"+
		"\3\2\2\2\u0169\u016b\5\66\34\2\u016a\u015f\3\2\2\2\u016a\u016b\3\2\2\2"+
		"\u016b\u016f\3\2\2\2\u016c\u016e\7#\2\2\u016d\u016c\3\2\2\2\u016e\u0171"+
		"\3\2\2\2\u016f\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0175\3\2\2\2\u0171"+
		"\u016f\3\2\2\2\u0172\u0174\5*\26\2\u0173\u0172\3\2\2\2\u0174\u0177\3\2"+
		"\2\2\u0175\u0173\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0189\3\2\2\2\u0177"+
		"\u0175\3\2\2\2\u0178\u017c\7\5\2\2\u0179\u017b\7#\2\2\u017a\u0179\3\2"+
		"\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d"+
		"\u017f\3\2\2\2\u017e\u017c\3\2\2\2\u017f\u0183\5&\24\2\u0180\u0182\7#"+
		"\2\2\u0181\u0180\3\2\2\2\u0182\u0185\3\2\2\2\u0183\u0181\3\2\2\2\u0183"+
		"\u0184\3\2\2\2\u0184\u0186\3\2\2\2\u0185\u0183\3\2\2\2\u0186\u0187\7\6"+
		"\2\2\u0187\u0189\3\2\2\2\u0188\u015b\3\2\2\2\u0188\u0178\3\2\2\2\u0189"+
		"\'\3\2\2\2\u018a\u018b\t\7\2\2\u018b)\3\2\2\2\u018c\u018e\7\16\2\2\u018d"+
		"\u018f\7#\2\2\u018e\u018d\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0190\3\2"+
		"\2\2\u0190\u019f\5\64\33\2\u0191\u0193\7#\2\2\u0192\u0191\3\2\2\2\u0192"+
		"\u0193\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196\7\5\2\2\u0195\u0197\7#"+
		"\2\2\u0196\u0195\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0199\3\2\2\2\u0198"+
		"\u019a\5,\27\2\u0199\u0198\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019c\3\2"+
		"\2\2\u019b\u019d\7#\2\2\u019c\u019b\3\2\2\2\u019c\u019d\3\2\2\2\u019d"+
		"\u019e\3\2\2\2\u019e\u01a0\7\6\2\2\u019f\u0192\3\2\2\2\u019f\u01a0\3\2"+
		"\2\2\u01a0\u01a9\3\2\2\2\u01a1\u01a3\7#\2\2\u01a2\u01a1\3\2\2\2\u01a2"+
		"\u01a3\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4\u01a6\7 \2\2\u01a5\u01a7\7#\2"+
		"\2\u01a6\u01a5\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01aa"+
		"\5\66\34\2\u01a9\u01a2\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa+\3\2\2\2\u01ab"+
		"\u01b6\5&\24\2\u01ac\u01ae\7#\2\2\u01ad\u01ac\3\2\2\2\u01ad\u01ae\3\2"+
		"\2\2\u01ae\u01af\3\2\2\2\u01af\u01b1\7\37\2\2\u01b0\u01b2\7#\2\2\u01b1"+
		"\u01b0\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b5\5&"+
		"\24\2\u01b4\u01ad\3\2\2\2\u01b5\u01b8\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b6"+
		"\u01b7\3\2\2\2\u01b7-\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b9\u01bb\5\60\31"+
		"\2\u01ba\u01b9\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc\u01c1"+
		"\5\62\32\2\u01bd\u01be\t\b\2\2\u01be\u01c0\5\62\32\2\u01bf\u01bd\3\2\2"+
		"\2\u01c0\u01c3\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2/"+
		"\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c4\u01c5\7\21\2\2\u01c5\u01c6\t\b\2\2"+
		"\u01c6\61\3\2\2\2\u01c7\u01c8\7$\2\2\u01c8\63\3\2\2\2\u01c9\u01ca\7$\2"+
		"\2\u01ca\65\3\2\2\2\u01cb\u01cc\7$\2\2\u01cc\67\3\2\2\2C;@IPY`jmr}\u0089"+
		"\u008f\u009d\u00a4\u00ab\u00ad\u00b3\u00b5\u00bb\u00c5\u00cc\u00ce\u00d4"+
		"\u00d6\u00dc\u00e1\u00e7\u00ef\u00f7\u00fe\u0105\u010c\u0113\u0118\u011d"+
		"\u0122\u0129\u0130\u0137\u013e\u0145\u014a\u0159\u015f\u0166\u016a\u016f"+
		"\u0175\u017c\u0183\u0188\u018e\u0192\u0196\u0199\u019c\u019f\u01a2\u01a6"+
		"\u01a9\u01ad\u01b1\u01b6\u01ba\u01c1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}