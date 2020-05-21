package jyatlon.core;

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
		this.content = "=$=\n" + templateCcontent + "\n";
		Template t = parseTemplate();
//    	BlockBuilder bb = new BlockBuilder(content);
    	pathBlocks = BlockBuilder.parseTemplate(content, t);
    	mainBlock = pathBlocks.get(BlockBuilder.ROOT);
	}
	public void merge(Object root, Writer w) {
		BlockProcessor.merge(mainBlock, w, root);
	}
	

    private Template parseTemplate() {
    	UnbufferedCharStream input = new UnbufferedCharStream(new ByteArrayInputStream(content.getBytes()));
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
        
        StructBuilder<Struct> myListener = new StructBuilder<Struct>(Struct.class);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(myListener, tree);
        Struct struct = myListener.getStruct(); // java.util.EmptyStackException
        return (Template)struct;
    }
}
