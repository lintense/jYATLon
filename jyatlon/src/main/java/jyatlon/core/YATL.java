package jyatlon.core;

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
import jyatlon.core.Struct.Template;
import jyatlon.generated.YATLLexer;
import jyatlon.generated.YATLParser;

/**
 * @author lintense
 *
 */
public class YATL {
	
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
		} catch (BlockBuildingError e) {
			Point p = getErrorPosition(e.pos);
			throw new IllegalArgumentException("line " + p.y + ":" + p.x + " " + e.getMessage(), e);
		}
	}
	public void merge(Object root, Writer w) {
		BlockProcessor.merge(mainBlock, w, root);
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
    			: Constant.HIDDEN_HEADER + templateContent + Constant.LINE_SEP; // Always at least 2 lines
    }
    private Point getErrorPosition(int pos) {
    	String[] lines = content.split(Constant.LINE_SEP);
    	int eol = content.indexOf(lines[1])-lines[0].length(); // Always at least 2 lines
    	int x = 0, y = 0;
    	LOOP: for (String s : lines)
    		if (x < pos) {
    			y++;
    			x+=s.length()+eol;
    		} else 
    			break LOOP;
    	return new Point(x-pos+1, y);
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
