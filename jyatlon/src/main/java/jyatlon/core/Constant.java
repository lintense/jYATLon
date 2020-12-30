package jyatlon.core;

/*
 * BSD 3-Clause Clear License
 * 
 * Copyright (c) 2019 Sylvain Nadeau
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted (subject to the limitations in the disclaimer 
 * below) provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in the 
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of [Owner Organization] nor the names of its contributors 
 *    may be used to endorse or promote products derived from this software 
 *    without specific prior written permission.
 * 
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY 
 * THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT 
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

import jyatlon.generated.YATLLexer;

/**
 * @author lintense
 * Yeah, this looks rather ugly BUT if the YATL grammar file is updated then it will flashes here!
 *
 */
public interface Constant {

	static final String QUOTES = "\"''\"";
	
	static final String LCURL = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.LCURL));
	static final String RCURL = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.RCURL));
	static final String LVALUE = LCURL + LCURL;
	static final String RVALUE = RCURL + RCURL;
	
	static final String ROOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.ROOT));
	static final String NOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.NOT));
	static final String MINUS = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.MINUS));
	static final String INDEX_OF = "indexOf";
	static final String SIZE_OF = "sizeOf";
	
	static final String EQUAL = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.EQUAL));
	static final String PIPE = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.PIPE));
	static final String AMP = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.AMP));
	static final String COLON = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.COLON));
	static final String DOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.DOT));
	static final String LINE_SEP = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.NEWLINE));
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
	
	static final String ANYPATH = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.ANYPATH));
	static final String PATHSEP = "/";
	
	static final String JAVA_ACCESSOR_PREFIX = "get";
	static final String JAVA_BOOLEAN_ACCESSOR_PREFIX = "is";
	
	static final String HIDDEN_HEADER = Constant.EQUAL + Constant.ROOT + Constant.EQUAL;
}
