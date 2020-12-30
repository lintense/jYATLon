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

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import jyatlon.core.Block.PathBlock;
import jyatlon.core.BlockBuilder.BuildingError;
import jyatlon.core.BlockProcessor.ProcessingError;
import jyatlon.core.Struct.Template;
import jyatlon.generated.YATLLexer;
import jyatlon.generated.YATLParser;

/**
 * @author lintense
 *
 */
public class YATL {
	
//	private static final String LINE_SEP = System.lineSeparator();
	private final String content;
//	private final Template template;
	private final Block.PathBlock mainBlock;
	private final Map<String,PathBlock> pathBlocks;
	
	public static YATL fromString(String templateContent) throws IOException {
		return new YATL(templateContent);
	}
	public static YATL getTemplate(String templateFilename) throws IOException {
		return new YATL(Utils.pathToString(Utils.getPath(templateFilename)));
	}
	public static YATL getTemplate(URL templateUrl) throws IOException, URISyntaxException {
		return new YATL(Utils.pathToString(Utils.getPath(templateUrl)));
	}
	public static YATL getTemplate(File templateFile) throws IOException {
		return new YATL(Utils.pathToString(Utils.getPath(templateFile)));
	}
	private YATL(String templateCcontent) {
        try {
			this.content = getActualContent(templateCcontent);
			Template t = parseTemplate(content);
	    	pathBlocks = BlockBuilder.parseTemplate(content, t);
	    	mainBlock = pathBlocks.get(Constant.ROOT);
		} catch (BuildingError e) {
			Point p = getErrorPosition(e.pos);
			throw new IllegalArgumentException("line " + p.y + ":" + p.x + " " + e.getMessage(), e);
		}
	}
	public void merge(Object root, Writer w) {
		try {
			BlockProcessor.merge(mainBlock, w, root);
		} catch (ProcessingError e) {
			Point p = getErrorPosition(e.pos);
			throw new IllegalArgumentException("line " + p.y + ":" + p.x + " " + e.getMessage(), e);
		}
	}
    static Template parseTemplate(String templateContent) { // Used for testing
    	String actualTemplate = getActualContent(templateContent); // Always at least 2 lines
    	UnbufferedCharStream input = new UnbufferedCharStream(new ByteArrayInputStream(actualTemplate.getBytes()));
    	YATLLexer lexer = new YATLLexer(input);
        lexer.setTokenFactory(new CommonTokenFactory(true));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        
//        // Write all extracted Tokens
//        int i = tokens.LA(1);
//        while (i >= 0) {
//        	System.out.print(YATLLexer.ruleNames[i-1] + (i == YATLLexer.NEWLINE ? "\n" : " "));
//        	tokens.consume();
//        	i = tokens.LA(1);
//        }
//        tokens.seek(0);
        
        
        YATLParser parser = new YATLParser(tokens);
        ParseTree tree = parser.template(); // begin parsing at rule 'r'
//        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        
        // Errors could be collected from parser.getErrorListeners()
        // FIXME the line number returned is +1 because of the HIDDEN_HEADER
        if (parser.getNumberOfSyntaxErrors() > 0) // Crash early and crash often for more reliable software...
        	throw new IllegalArgumentException("Template cannot be compiled due to errors. Processing stopped!");
        
		StructBuilder<Struct> myListener = new StructBuilder<Struct>(Struct.class);
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(myListener, tree);
		Struct struct = myListener.getStruct(); // java.util.EmptyStackException
		return (Template)struct;

    }

    /**
     * @param pos
     * Note: Slow processing, use only for error handling!
     * @return
     */
    private static String getActualContent(String templateContent) {
    	return templateContent.startsWith(Constant.HIDDEN_HEADER) 
    			? templateContent 
    			: Constant.HIDDEN_HEADER + Constant.LINE_SEP + templateContent + Constant.LINE_SEP; // Always at least 2 lines
    }
    private Point getErrorPosition(int pos) {
    	String[] lines = content.split(Constant.LINE_SEP);
    	int x = 0, y = 0;
    	while (x <= pos)
			x+=lines[y++].length()+Constant.LINE_SEP.length();
//    	int adj = lines[--y].charAt(0) == '\r' ? -1 : 0;
    	return new Point(lines[y-1].length()-x+pos+Constant.HIDDEN_HEADER.length()-1, y-1);
    }
}
/*
Have a nice mechanism for error message handling ERROR_P1_P2
Use a writer when compiling instead of loggers.
Detect ~ cannot be used inside COMMANDS and VALUES (except {begin '...'})
Detect any { before the enclosing }
When parsing {, could check for valid COMMAND names to avoid using ~
Have a trace to follow the order of calling to debug the command calls
Controls & commands in error are printed as is for convenience so it is easy to find the error in the script.
Regex to validate alias names. [A-Za-z_0-9]*
*/
