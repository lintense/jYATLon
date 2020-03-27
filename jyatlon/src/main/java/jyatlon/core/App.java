package jyatlon.core;

// What is the simplest way to convert the Parse Tree into a Struct?
// https://jakubdziworski.github.io/java/2016/04/01/antlr_visitor_vs_listener.html

//https://tomassetti.me/antlr-mega-tutorial/ 

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import jyatlon.core.Struct.Template;
import jyatlon.generated.YATLLexer;
import jyatlon.generated.YATLParser;

public class App { // Rename to YATL

    public static void main( String[] args )
    {
    	
    	//generateStruct();
    	
    	/*
        ANTLRInputStream inputStream = new ANTLRInputStream(
            "hello you");
        HelloLexer markupLexer = new HelloLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(markupLexer);
        HelloParser markupParser = new HelloParser(commonTokenStream);
        HelloParser.RContext fileContext = markupParser.r();                
        HelloVisitor visitor = new HelloVisitor();                
        visitor.visit(fileContext);        
        */
        /*
//--------------------- 
		CharStream cs = new ANTLRInputStream(oclExp);
		OCLSyntaxLexer lexer = new OCLSyntaxLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		OCLSyntaxParser parser = new OCLSyntaxParser(tokens);

		parser.setBuildParseTree(true);
		ParseTree tree = parser.expression();
		
		// Extract definition from the parsed file
		SyntaxProcessor listener = new SyntaxProcessor();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(listener, tree);
		
		System.out.println("*** Dump three ***");
		listener.dumpTree();
		
		TokenProcessor extractor = new TokenProcessor(reader);
//		ExtractStructure extractor = new ExtractStructure(reader, new ParsingContext("Component"));
//		OCLExpressionStructure exp = extractor.extractStructure(listener.getToken(), new ParsingContext(reader, "Component", 0));
		
		// The pivot is the component on which the given method will apply
		ParsingContext.Pivot pivot = new ParsingContext.Pivot("Component"); // null = unknown type for self, break the translator
		List<ParsingContext> contextList = extractor.getContextList(listener, pivot);
        		*/
//---------------------
		UnbufferedCharStream input = new UnbufferedCharStream(new ByteArrayInputStream((
" === String ===\n\n\n" +
" a b### {}[begin]~c [if ((($.name) == $.val.val2(1,2.3).val2) || $.name() == $.val || $.name == $._val(1)) call .../String ($:ROOT.name(('test')):ALIAS)]x{begin X}yz\n").getBytes()));
        
		YATLLexer lexer = new YATLLexer(input);
        lexer.setTokenFactory(new CommonTokenFactory(true));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        YATLParser parser = new YATLParser(tokens);
        ParseTree tree = parser.template(); // begin parsing at rule 'r'
//        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        
//        StructInitializer initializer = new StructInitializer();
        StructBuilder<Struct> myListener = new StructBuilder<Struct>(Struct.class); //, initializer); //MyListener(); //
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(myListener, tree);
        Struct struct = myListener.getStruct(); // java.util.EmptyStackException
        
        Template t = (Template)struct;
//        t.test(t);
	}
//    public static Template getTemplate(String template) {
//    	
//    	return getBlock(getStruct(template));
//    }
    static String process(String templateText, Object root) {
    	Template t = getTemplate(templateText);
    	BlockBuilder bb = new BlockBuilder(templateText);
    	Block.PathBlock pb = bb.parseTemplate(t);
    	StringWriter sw = new StringWriter();
    	BlockProcessor.merge(pb, sw, root);
    	return sw.toString();
    }
    
    
    static Block.PathBlock getBlock(String templateText, Template t) {
    	BlockBuilder bb = new BlockBuilder(templateText);
    	return bb.parseTemplate(t);
    }
    	
    static Template getTemplate(String template) {
    	String actualTemplate = "=$=\n" + template + "\n";
    	UnbufferedCharStream input = new UnbufferedCharStream(new ByteArrayInputStream(actualTemplate.getBytes()));
    	YATLLexer lexer = new YATLLexer(input);
        lexer.setTokenFactory(new CommonTokenFactory(true));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        
        // Write all extracted Tokens
        int i = tokens.LA(1);
        while (i >= 0) {
        	System.out.print(YATLLexer.ruleNames[i-1] + (i == YATLLexer.NEWLINE ? "\n" : " "));
        	tokens.consume();
        	i = tokens.LA(1);
        }
        tokens.seek(0);
        
        
        YATLParser parser = new YATLParser(tokens);
        ParseTree tree = parser.template(); // begin parsing at rule 'r'
//        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        
//        StructInitializer initializer = new StructInitializer();
        StructBuilder<Struct> myListener = new StructBuilder<Struct>(Struct.class); //, initializer); //MyListener(); //
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(myListener, tree);
        Struct struct = myListener.getStruct(); // java.util.EmptyStackException
        return (Template)struct;
    }
    void merge(){
    	
    	// Always add ===$=== at the beginning of the file
    	// Always add '\n' \ at the end
    	
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
