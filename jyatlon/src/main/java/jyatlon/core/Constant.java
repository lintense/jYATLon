package jyatlon.core;

import jyatlon.generated.YATLLexer;

public interface Constant {

	public static final String HIDDEN_HEADER = "=$=\n";
	public static final String QUOTES = "\"''\"";
	
	public static final String ROOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.ROOT));
	public static final String NOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.NOT));
	public static final String MINUS = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.MINUS));
	public static final String INDEX_OF = "indexOf";
	public static final String SIZE_OF = "sizeOf";
	
	public static final String EQUAL = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.EQUAL));
	public static final String PIPE = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.PIPE));
	public static final String AMP = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.AMP));
	public static final String LOWER = "<";
	public static final String GREATER = ">";
	
	public static final String COMPARE_EQUAL = EQUAL + EQUAL;
	public static final String COMPARE_GREATER_THAN = GREATER;
	public static final String COMPARE_LOWER_THAN = LOWER;
	public static final String COMPARE_NOT_EQUAL1 = NOT + EQUAL;
	public static final String COMPARE_NOT_EQUAL2 = LOWER + GREATER;
	public static final String COMPARE_GREATER_OR_EQUAL = GREATER + EQUAL;
	public static final String COMPARE_LOWER_OR_EQUAL = LOWER + EQUAL;
	
	public static final String COMPARE_OR = PIPE + PIPE;
	public static final String COMPARE_AND = AMP + AMP;
}
