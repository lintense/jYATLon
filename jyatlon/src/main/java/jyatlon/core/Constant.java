package jyatlon.core;

import jyatlon.generated.YATLLexer;

public interface Constant {

	static final String QUOTES = "\"''\"";
	
	static final String ROOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.ROOT));
	static final String NOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.NOT));
	static final String MINUS = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.MINUS));
	static final String INDEX_OF = "indexOf";
	static final String SIZE_OF = "sizeOf";
	
	static final String EQUAL = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.EQUAL));
	static final String PIPE = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.PIPE));
	static final String AMP = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.AMP));
	static final String LOWER = "<";
	static final String GREATER = ">";
	
	static final String COMPARE_EQUAL = EQUAL + EQUAL;
	static final String COMPARE_GREATER_THAN = GREATER;
	static final String COMPARE_LOWER_THAN = LOWER;
	static final String COMPARE_NOT_EQUAL1 = NOT + EQUAL;
	static final String COMPARE_NOT_EQUAL2 = LOWER + GREATER;
	static final String COMPARE_GREATER_OR_EQUAL = GREATER + EQUAL;
	static final String COMPARE_LOWER_OR_EQUAL = LOWER + EQUAL;
	
	static final String COMPARE_OR = PIPE + PIPE;
	static final String COMPARE_AND = AMP + AMP;
	
	static final String MAP_KEY_FOR_CLASS = "CLASS";
}
