// Generated from jyatlon\generated\YATL.g4 by ANTLR 4.7.2
package jyatlon.generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class YATLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		ANYPATH=10, BEGIN=11, BEFORE=12, BETWEEN=13, AFTER=14, EMPTY=15, END=16, 
		CALL=17, IF=18, CONTROL=19, ROOT=20, LCURL=21, LBRACK=22, LVALUE=23, RCURL=24, 
		RBRACK=25, RVALUE=26, PATHSEP=27, ESCAPE=28, DOT=29, COMMA=30, COLON=31, 
		NEWLINE=32, NOT=33, MINUS=34, WS=35, SPACE=36, EQUAL=37, SECTIONSEP=38, 
		POUND=39, COMMENTSEP=40, PIPE=41, OR=42, AMP=43, AND=44, PERCENT=45, NAME=46, 
		INTEGER=47, NUMBER=48, STRING=49;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"ANYPATH", "BEGIN", "BEFORE", "BETWEEN", "AFTER", "EMPTY", "END", "CALL", 
			"IF", "CONTROL", "ROOT", "LCURL", "LBRACK", "LVALUE", "RCURL", "RBRACK", 
			"RVALUE", "PATHSEP", "ESCAPE", "DOT", "COMMA", "COLON", "NEWLINE", "NOT", 
			"MINUS", "WS", "SPACE", "EQUAL", "SECTIONSEP", "POUND", "COMMENTSEP", 
			"PIPE", "OR", "AMP", "AND", "PERCENT", "NAME", "INTEGER", "NUMBER", "STRING"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'?'", "';'", "'('", "')'", "'>'", "'<'", "'<>'", "'indexOf'", 
			"'sizeOf'", "'...'", "'begin'", "'before'", "'between'", "'after'", "'empty'", 
			"'end'", "'call'", "'if'", null, "'$'", "'{'", "'['", null, "'}'", "']'", 
			null, null, "'~'", "'.'", "','", "':'", "'\n'", "'!'", "'-'", null, null, 
			"'='", null, "'#'", null, "'|'", null, "'&'", null, "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "ANYPATH", 
			"BEGIN", "BEFORE", "BETWEEN", "AFTER", "EMPTY", "END", "CALL", "IF", 
			"CONTROL", "ROOT", "LCURL", "LBRACK", "LVALUE", "RCURL", "RBRACK", "RVALUE", 
			"PATHSEP", "ESCAPE", "DOT", "COMMA", "COLON", "NEWLINE", "NOT", "MINUS", 
			"WS", "SPACE", "EQUAL", "SECTIONSEP", "POUND", "COMMENTSEP", "PIPE", 
			"OR", "AMP", "AND", "PERCENT", "NAME", "INTEGER", "NUMBER", "STRING"
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


	public YATLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "YATL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\63\u013e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\5\24\u00bc\n\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\6$\u00df\n$\r$\16$\u00e0"+
		"\3$\3$\3%\3%\3&\3&\3\'\3\'\3\'\3\'\3(\3(\3)\3)\3)\3)\3*\3*\3+\3+\3+\3"+
		",\3,\3-\3-\3-\3.\3.\3/\5/\u0100\n/\3/\7/\u0103\n/\f/\16/\u0106\13/\3\60"+
		"\6\60\u0109\n\60\r\60\16\60\u010a\3\61\6\61\u010e\n\61\r\61\16\61\u010f"+
		"\3\61\3\61\6\61\u0114\n\61\r\61\16\61\u0115\5\61\u0118\n\61\3\61\3\61"+
		"\5\61\u011c\n\61\3\61\6\61\u011f\n\61\r\61\16\61\u0120\5\61\u0123\n\61"+
		"\3\62\3\62\3\62\5\62\u0128\n\62\3\62\7\62\u012b\n\62\f\62\16\62\u012e"+
		"\13\62\3\62\3\62\3\62\3\62\5\62\u0134\n\62\3\62\7\62\u0137\n\62\f\62\16"+
		"\62\u013a\13\62\3\62\5\62\u013d\n\62\2\2\63\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+"+
		"U,W-Y.[/]\60_\61a\62c\63\3\2\13\4\2\61\61^^\3\2\17\17\4\2\13\13\"\"\5"+
		"\2C\\aac|\6\2\62;C\\aac|\4\2GGgg\4\2--//\4\2))^^\4\2$$^^\2\u0152\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2"+
		"\2c\3\2\2\2\3e\3\2\2\2\5g\3\2\2\2\7i\3\2\2\2\tk\3\2\2\2\13m\3\2\2\2\r"+
		"o\3\2\2\2\17q\3\2\2\2\21t\3\2\2\2\23|\3\2\2\2\25\u0083\3\2\2\2\27\u0087"+
		"\3\2\2\2\31\u008d\3\2\2\2\33\u0094\3\2\2\2\35\u009c\3\2\2\2\37\u00a2\3"+
		"\2\2\2!\u00a8\3\2\2\2#\u00ac\3\2\2\2%\u00b1\3\2\2\2\'\u00b4\3\2\2\2)\u00bd"+
		"\3\2\2\2+\u00bf\3\2\2\2-\u00c1\3\2\2\2/\u00c3\3\2\2\2\61\u00c6\3\2\2\2"+
		"\63\u00c8\3\2\2\2\65\u00ca\3\2\2\2\67\u00cd\3\2\2\29\u00cf\3\2\2\2;\u00d1"+
		"\3\2\2\2=\u00d3\3\2\2\2?\u00d5\3\2\2\2A\u00d7\3\2\2\2C\u00d9\3\2\2\2E"+
		"\u00db\3\2\2\2G\u00de\3\2\2\2I\u00e4\3\2\2\2K\u00e6\3\2\2\2M\u00e8\3\2"+
		"\2\2O\u00ec\3\2\2\2Q\u00ee\3\2\2\2S\u00f2\3\2\2\2U\u00f4\3\2\2\2W\u00f7"+
		"\3\2\2\2Y\u00f9\3\2\2\2[\u00fc\3\2\2\2]\u00ff\3\2\2\2_\u0108\3\2\2\2a"+
		"\u010d\3\2\2\2c\u013c\3\2\2\2ef\7A\2\2f\4\3\2\2\2gh\7=\2\2h\6\3\2\2\2"+
		"ij\7*\2\2j\b\3\2\2\2kl\7+\2\2l\n\3\2\2\2mn\7@\2\2n\f\3\2\2\2op\7>\2\2"+
		"p\16\3\2\2\2qr\7>\2\2rs\7@\2\2s\20\3\2\2\2tu\7k\2\2uv\7p\2\2vw\7f\2\2"+
		"wx\7g\2\2xy\7z\2\2yz\7Q\2\2z{\7h\2\2{\22\3\2\2\2|}\7u\2\2}~\7k\2\2~\177"+
		"\7|\2\2\177\u0080\7g\2\2\u0080\u0081\7Q\2\2\u0081\u0082\7h\2\2\u0082\24"+
		"\3\2\2\2\u0083\u0084\7\60\2\2\u0084\u0085\7\60\2\2\u0085\u0086\7\60\2"+
		"\2\u0086\26\3\2\2\2\u0087\u0088\7d\2\2\u0088\u0089\7g\2\2\u0089\u008a"+
		"\7i\2\2\u008a\u008b\7k\2\2\u008b\u008c\7p\2\2\u008c\30\3\2\2\2\u008d\u008e"+
		"\7d\2\2\u008e\u008f\7g\2\2\u008f\u0090\7h\2\2\u0090\u0091\7q\2\2\u0091"+
		"\u0092\7t\2\2\u0092\u0093\7g\2\2\u0093\32\3\2\2\2\u0094\u0095\7d\2\2\u0095"+
		"\u0096\7g\2\2\u0096\u0097\7v\2\2\u0097\u0098\7y\2\2\u0098\u0099\7g\2\2"+
		"\u0099\u009a\7g\2\2\u009a\u009b\7p\2\2\u009b\34\3\2\2\2\u009c\u009d\7"+
		"c\2\2\u009d\u009e\7h\2\2\u009e\u009f\7v\2\2\u009f\u00a0\7g\2\2\u00a0\u00a1"+
		"\7t\2\2\u00a1\36\3\2\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7o\2\2\u00a4\u00a5"+
		"\7r\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7{\2\2\u00a7 \3\2\2\2\u00a8\u00a9"+
		"\7g\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7f\2\2\u00ab\"\3\2\2\2\u00ac\u00ad"+
		"\7e\2\2\u00ad\u00ae\7c\2\2\u00ae\u00af\7n\2\2\u00af\u00b0\7n\2\2\u00b0"+
		"$\3\2\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3\7h\2\2\u00b3&\3\2\2\2\u00b4\u00bb"+
		"\5+\26\2\u00b5\u00bc\5\27\f\2\u00b6\u00bc\5\31\r\2\u00b7\u00bc\5\33\16"+
		"\2\u00b8\u00bc\5\35\17\2\u00b9\u00bc\5\37\20\2\u00ba\u00bc\5!\21\2\u00bb"+
		"\u00b5\3\2\2\2\u00bb\u00b6\3\2\2\2\u00bb\u00b7\3\2\2\2\u00bb\u00b8\3\2"+
		"\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00ba\3\2\2\2\u00bc(\3\2\2\2\u00bd\u00be"+
		"\7&\2\2\u00be*\3\2\2\2\u00bf\u00c0\7}\2\2\u00c0,\3\2\2\2\u00c1\u00c2\7"+
		"]\2\2\u00c2.\3\2\2\2\u00c3\u00c4\5+\26\2\u00c4\u00c5\5+\26\2\u00c5\60"+
		"\3\2\2\2\u00c6\u00c7\7\177\2\2\u00c7\62\3\2\2\2\u00c8\u00c9\7_\2\2\u00c9"+
		"\64\3\2\2\2\u00ca\u00cb\5\61\31\2\u00cb\u00cc\5\61\31\2\u00cc\66\3\2\2"+
		"\2\u00cd\u00ce\t\2\2\2\u00ce8\3\2\2\2\u00cf\u00d0\7\u0080\2\2\u00d0:\3"+
		"\2\2\2\u00d1\u00d2\7\60\2\2\u00d2<\3\2\2\2\u00d3\u00d4\7.\2\2\u00d4>\3"+
		"\2\2\2\u00d5\u00d6\7<\2\2\u00d6@\3\2\2\2\u00d7\u00d8\7\f\2\2\u00d8B\3"+
		"\2\2\2\u00d9\u00da\7#\2\2\u00daD\3\2\2\2\u00db\u00dc\7/\2\2\u00dcF\3\2"+
		"\2\2\u00dd\u00df\t\3\2\2\u00de\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\b$"+
		"\2\2\u00e3H\3\2\2\2\u00e4\u00e5\t\4\2\2\u00e5J\3\2\2\2\u00e6\u00e7\7?"+
		"\2\2\u00e7L\3\2\2\2\u00e8\u00e9\5K&\2\u00e9\u00ea\5K&\2\u00ea\u00eb\5"+
		"K&\2\u00ebN\3\2\2\2\u00ec\u00ed\7%\2\2\u00edP\3\2\2\2\u00ee\u00ef\5[."+
		"\2\u00ef\u00f0\5[.\2\u00f0\u00f1\5[.\2\u00f1R\3\2\2\2\u00f2\u00f3\7~\2"+
		"\2\u00f3T\3\2\2\2\u00f4\u00f5\5S*\2\u00f5\u00f6\5S*\2\u00f6V\3\2\2\2\u00f7"+
		"\u00f8\7(\2\2\u00f8X\3\2\2\2\u00f9\u00fa\5W,\2\u00fa\u00fb\5W,\2\u00fb"+
		"Z\3\2\2\2\u00fc\u00fd\7\'\2\2\u00fd\\\3\2\2\2\u00fe\u0100\t\5\2\2\u00ff"+
		"\u00fe\3\2\2\2\u0100\u0104\3\2\2\2\u0101\u0103\t\6\2\2\u0102\u0101\3\2"+
		"\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
		"^\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0109\4\62;\2\u0108\u0107\3\2\2\2"+
		"\u0109\u010a\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b`\3"+
		"\2\2\2\u010c\u010e\4\62;\2\u010d\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0117\3\2\2\2\u0111\u0113\7\60"+
		"\2\2\u0112\u0114\4\62;\2\u0113\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117\u0111\3\2"+
		"\2\2\u0117\u0118\3\2\2\2\u0118\u0122\3\2\2\2\u0119\u011b\t\7\2\2\u011a"+
		"\u011c\t\b\2\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\3\2"+
		"\2\2\u011d\u011f\4\62;\2\u011e\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120"+
		"\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0119\3\2"+
		"\2\2\u0122\u0123\3\2\2\2\u0123b\3\2\2\2\u0124\u012c\7)\2\2\u0125\u0127"+
		"\7^\2\2\u0126\u0128\7)\2\2\u0127\u0126\3\2\2\2\u0127\u0128\3\2\2\2\u0128"+
		"\u012b\3\2\2\2\u0129\u012b\n\t\2\2\u012a\u0125\3\2\2\2\u012a\u0129\3\2"+
		"\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d"+
		"\u012f\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u013d\7)\2\2\u0130\u0138\7$\2"+
		"\2\u0131\u0133\7^\2\2\u0132\u0134\7$\2\2\u0133\u0132\3\2\2\2\u0133\u0134"+
		"\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0137\n\n\2\2\u0136\u0131\3\2\2\2\u0136"+
		"\u0135\3\2\2\2\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2"+
		"\2\2\u0139\u013b\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013d\7$\2\2\u013c"+
		"\u0124\3\2\2\2\u013c\u0130\3\2\2\2\u013dd\3\2\2\2\26\2\u00bb\u00e0\u00ff"+
		"\u0102\u0104\u010a\u010f\u0115\u0117\u011b\u0120\u0122\u0127\u012a\u012c"+
		"\u0133\u0136\u0138\u013c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}