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
		T__9=10, ANYPATH=11, BEGIN=12, BEFORE=13, BETWEEN=14, AFTER=15, EMPTY=16, 
		END=17, CALL=18, IF=19, CONTROL=20, ROOT=21, LCURL=22, LBRACK=23, LVALUE=24, 
		RCURL=25, RBRACK=26, RVALUE=27, PATHSEP=28, ESCAPE=29, DOT=30, COMMA=31, 
		COLON=32, NEWLINE=33, NOT=34, MINUS=35, WS=36, SPACE=37, EQUAL=38, SECTIONSEP=39, 
		POUND=40, COMMENTSEP=41, PIPE=42, OR=43, AMP=44, AND=45, PERCENT=46, NAME=47, 
		INTEGER=48, NUMBER=49, STRING=50;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "ANYPATH", "BEGIN", "BEFORE", "BETWEEN", "AFTER", "EMPTY", "END", 
			"CALL", "IF", "CONTROL", "ROOT", "LCURL", "LBRACK", "LVALUE", "RCURL", 
			"RBRACK", "RVALUE", "PATHSEP", "ESCAPE", "DOT", "COMMA", "COLON", "NEWLINE", 
			"NOT", "MINUS", "WS", "SPACE", "EQUAL", "SECTIONSEP", "POUND", "COMMENTSEP", 
			"PIPE", "OR", "AMP", "AND", "PERCENT", "NAME", "INTEGER", "NUMBER", "STRING"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'^'", "'?'", "';'", "'>'", "'<'", "'<>'", "'indexOf'", 
			"'sizeOf'", "'...'", "'begin'", "'before'", "'between'", "'after'", "'empty'", 
			"'end'", "'call'", "'if'", null, "'$'", "'{'", "'['", null, "'}'", "']'", 
			null, null, "'~'", "'.'", "','", "':'", "'\n'", "'!'", "'-'", null, null, 
			"'='", null, "'#'", null, "'|'", null, "'&'", null, "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "ANYPATH", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\64\u0142\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00c0\n\25"+
		"\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$"+
		"\3$\3%\6%\u00e3\n%\r%\16%\u00e4\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3(\3)\3)"+
		"\3*\3*\3*\3*\3+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/\3/\3\60\5\60\u0104\n\60"+
		"\3\60\7\60\u0107\n\60\f\60\16\60\u010a\13\60\3\61\6\61\u010d\n\61\r\61"+
		"\16\61\u010e\3\62\6\62\u0112\n\62\r\62\16\62\u0113\3\62\3\62\6\62\u0118"+
		"\n\62\r\62\16\62\u0119\5\62\u011c\n\62\3\62\3\62\5\62\u0120\n\62\3\62"+
		"\6\62\u0123\n\62\r\62\16\62\u0124\5\62\u0127\n\62\3\63\3\63\3\63\5\63"+
		"\u012c\n\63\3\63\7\63\u012f\n\63\f\63\16\63\u0132\13\63\3\63\3\63\3\63"+
		"\3\63\5\63\u0138\n\63\3\63\7\63\u013b\n\63\f\63\16\63\u013e\13\63\3\63"+
		"\5\63\u0141\n\63\2\2\64\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63"+
		"\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62"+
		"c\63e\64\3\2\13\4\2\61\61^^\3\2\17\17\4\2\13\13\"\"\5\2C\\aac|\6\2\62"+
		";C\\aac|\4\2GGgg\4\2--//\4\2))^^\4\2$$^^\2\u0156\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\3g\3\2\2\2\5i\3\2\2\2\7k\3\2\2\2\tm\3\2\2\2\13o\3\2\2\2\rq\3\2"+
		"\2\2\17s\3\2\2\2\21u\3\2\2\2\23x\3\2\2\2\25\u0080\3\2\2\2\27\u0087\3\2"+
		"\2\2\31\u008b\3\2\2\2\33\u0091\3\2\2\2\35\u0098\3\2\2\2\37\u00a0\3\2\2"+
		"\2!\u00a6\3\2\2\2#\u00ac\3\2\2\2%\u00b0\3\2\2\2\'\u00b5\3\2\2\2)\u00b8"+
		"\3\2\2\2+\u00c1\3\2\2\2-\u00c3\3\2\2\2/\u00c5\3\2\2\2\61\u00c7\3\2\2\2"+
		"\63\u00ca\3\2\2\2\65\u00cc\3\2\2\2\67\u00ce\3\2\2\29\u00d1\3\2\2\2;\u00d3"+
		"\3\2\2\2=\u00d5\3\2\2\2?\u00d7\3\2\2\2A\u00d9\3\2\2\2C\u00db\3\2\2\2E"+
		"\u00dd\3\2\2\2G\u00df\3\2\2\2I\u00e2\3\2\2\2K\u00e8\3\2\2\2M\u00ea\3\2"+
		"\2\2O\u00ec\3\2\2\2Q\u00f0\3\2\2\2S\u00f2\3\2\2\2U\u00f6\3\2\2\2W\u00f8"+
		"\3\2\2\2Y\u00fb\3\2\2\2[\u00fd\3\2\2\2]\u0100\3\2\2\2_\u0103\3\2\2\2a"+
		"\u010c\3\2\2\2c\u0111\3\2\2\2e\u0140\3\2\2\2gh\7*\2\2h\4\3\2\2\2ij\7+"+
		"\2\2j\6\3\2\2\2kl\7`\2\2l\b\3\2\2\2mn\7A\2\2n\n\3\2\2\2op\7=\2\2p\f\3"+
		"\2\2\2qr\7@\2\2r\16\3\2\2\2st\7>\2\2t\20\3\2\2\2uv\7>\2\2vw\7@\2\2w\22"+
		"\3\2\2\2xy\7k\2\2yz\7p\2\2z{\7f\2\2{|\7g\2\2|}\7z\2\2}~\7Q\2\2~\177\7"+
		"h\2\2\177\24\3\2\2\2\u0080\u0081\7u\2\2\u0081\u0082\7k\2\2\u0082\u0083"+
		"\7|\2\2\u0083\u0084\7g\2\2\u0084\u0085\7Q\2\2\u0085\u0086\7h\2\2\u0086"+
		"\26\3\2\2\2\u0087\u0088\7\60\2\2\u0088\u0089\7\60\2\2\u0089\u008a\7\60"+
		"\2\2\u008a\30\3\2\2\2\u008b\u008c\7d\2\2\u008c\u008d\7g\2\2\u008d\u008e"+
		"\7i\2\2\u008e\u008f\7k\2\2\u008f\u0090\7p\2\2\u0090\32\3\2\2\2\u0091\u0092"+
		"\7d\2\2\u0092\u0093\7g\2\2\u0093\u0094\7h\2\2\u0094\u0095\7q\2\2\u0095"+
		"\u0096\7t\2\2\u0096\u0097\7g\2\2\u0097\34\3\2\2\2\u0098\u0099\7d\2\2\u0099"+
		"\u009a\7g\2\2\u009a\u009b\7v\2\2\u009b\u009c\7y\2\2\u009c\u009d\7g\2\2"+
		"\u009d\u009e\7g\2\2\u009e\u009f\7p\2\2\u009f\36\3\2\2\2\u00a0\u00a1\7"+
		"c\2\2\u00a1\u00a2\7h\2\2\u00a2\u00a3\7v\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5"+
		"\7t\2\2\u00a5 \3\2\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7o\2\2\u00a8\u00a9"+
		"\7r\2\2\u00a9\u00aa\7v\2\2\u00aa\u00ab\7{\2\2\u00ab\"\3\2\2\2\u00ac\u00ad"+
		"\7g\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af\7f\2\2\u00af$\3\2\2\2\u00b0\u00b1"+
		"\7e\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7n\2\2\u00b3\u00b4\7n\2\2\u00b4"+
		"&\3\2\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7h\2\2\u00b7(\3\2\2\2\u00b8\u00bf"+
		"\5-\27\2\u00b9\u00c0\5\31\r\2\u00ba\u00c0\5\33\16\2\u00bb\u00c0\5\35\17"+
		"\2\u00bc\u00c0\5\37\20\2\u00bd\u00c0\5!\21\2\u00be\u00c0\5#\22\2\u00bf"+
		"\u00b9\3\2\2\2\u00bf\u00ba\3\2\2\2\u00bf\u00bb\3\2\2\2\u00bf\u00bc\3\2"+
		"\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0*\3\2\2\2\u00c1\u00c2"+
		"\7&\2\2\u00c2,\3\2\2\2\u00c3\u00c4\7}\2\2\u00c4.\3\2\2\2\u00c5\u00c6\7"+
		"]\2\2\u00c6\60\3\2\2\2\u00c7\u00c8\5-\27\2\u00c8\u00c9\5-\27\2\u00c9\62"+
		"\3\2\2\2\u00ca\u00cb\7\177\2\2\u00cb\64\3\2\2\2\u00cc\u00cd\7_\2\2\u00cd"+
		"\66\3\2\2\2\u00ce\u00cf\5\63\32\2\u00cf\u00d0\5\63\32\2\u00d08\3\2\2\2"+
		"\u00d1\u00d2\t\2\2\2\u00d2:\3\2\2\2\u00d3\u00d4\7\u0080\2\2\u00d4<\3\2"+
		"\2\2\u00d5\u00d6\7\60\2\2\u00d6>\3\2\2\2\u00d7\u00d8\7.\2\2\u00d8@\3\2"+
		"\2\2\u00d9\u00da\7<\2\2\u00daB\3\2\2\2\u00db\u00dc\7\f\2\2\u00dcD\3\2"+
		"\2\2\u00dd\u00de\7#\2\2\u00deF\3\2\2\2\u00df\u00e0\7/\2\2\u00e0H\3\2\2"+
		"\2\u00e1\u00e3\t\3\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2"+
		"\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\b%\2\2\u00e7"+
		"J\3\2\2\2\u00e8\u00e9\t\4\2\2\u00e9L\3\2\2\2\u00ea\u00eb\7?\2\2\u00eb"+
		"N\3\2\2\2\u00ec\u00ed\5M\'\2\u00ed\u00ee\5M\'\2\u00ee\u00ef\5M\'\2\u00ef"+
		"P\3\2\2\2\u00f0\u00f1\7%\2\2\u00f1R\3\2\2\2\u00f2\u00f3\5]/\2\u00f3\u00f4"+
		"\5]/\2\u00f4\u00f5\5]/\2\u00f5T\3\2\2\2\u00f6\u00f7\7~\2\2\u00f7V\3\2"+
		"\2\2\u00f8\u00f9\5U+\2\u00f9\u00fa\5U+\2\u00faX\3\2\2\2\u00fb\u00fc\7"+
		"(\2\2\u00fcZ\3\2\2\2\u00fd\u00fe\5Y-\2\u00fe\u00ff\5Y-\2\u00ff\\\3\2\2"+
		"\2\u0100\u0101\7\'\2\2\u0101^\3\2\2\2\u0102\u0104\t\5\2\2\u0103\u0102"+
		"\3\2\2\2\u0104\u0108\3\2\2\2\u0105\u0107\t\6\2\2\u0106\u0105\3\2\2\2\u0107"+
		"\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109`\3\2\2\2"+
		"\u010a\u0108\3\2\2\2\u010b\u010d\4\62;\2\u010c\u010b\3\2\2\2\u010d\u010e"+
		"\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010fb\3\2\2\2\u0110"+
		"\u0112\4\62;\2\u0111\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0111\3\2"+
		"\2\2\u0113\u0114\3\2\2\2\u0114\u011b\3\2\2\2\u0115\u0117\7\60\2\2\u0116"+
		"\u0118\4\62;\2\u0117\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0117\3\2"+
		"\2\2\u0119\u011a\3\2\2\2\u011a\u011c\3\2\2\2\u011b\u0115\3\2\2\2\u011b"+
		"\u011c\3\2\2\2\u011c\u0126\3\2\2\2\u011d\u011f\t\7\2\2\u011e\u0120\t\b"+
		"\2\2\u011f\u011e\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0122\3\2\2\2\u0121"+
		"\u0123\4\62;\2\u0122\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0122\3\2"+
		"\2\2\u0124\u0125\3\2\2\2\u0125\u0127\3\2\2\2\u0126\u011d\3\2\2\2\u0126"+
		"\u0127\3\2\2\2\u0127d\3\2\2\2\u0128\u0130\7)\2\2\u0129\u012b\7^\2\2\u012a"+
		"\u012c\7)\2\2\u012b\u012a\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012f\3\2"+
		"\2\2\u012d\u012f\n\t\2\2\u012e\u0129\3\2\2\2\u012e\u012d\3\2\2\2\u012f"+
		"\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0133\3\2"+
		"\2\2\u0132\u0130\3\2\2\2\u0133\u0141\7)\2\2\u0134\u013c\7$\2\2\u0135\u0137"+
		"\7^\2\2\u0136\u0138\7$\2\2\u0137\u0136\3\2\2\2\u0137\u0138\3\2\2\2\u0138"+
		"\u013b\3\2\2\2\u0139\u013b\n\n\2\2\u013a\u0135\3\2\2\2\u013a\u0139\3\2"+
		"\2\2\u013b\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d"+
		"\u013f\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0141\7$\2\2\u0140\u0128\3\2"+
		"\2\2\u0140\u0134\3\2\2\2\u0141f\3\2\2\2\26\2\u00bf\u00e4\u0103\u0106\u0108"+
		"\u010e\u0113\u0119\u011b\u011f\u0124\u0126\u012b\u012e\u0130\u0137\u013a"+
		"\u013c\u0140\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}