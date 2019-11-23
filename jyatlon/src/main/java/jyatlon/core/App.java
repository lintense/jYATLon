package jyatlon.core;

// What is the simplest way to convert the Parse Tree into a Struct?
// https://jakubdziworski.github.io/java/2016/04/01/antlr_visitor_vs_listener.html

//https://tomassetti.me/antlr-mega-tutorial/ 

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import jyatlon.generated.YATLLexer;
import jyatlon.generated.YATLListener;
import jyatlon.generated.YATLParser;

public class App {

    public static void main( String[] args )
    {
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
" === path/Exp ===\n\n\n" +
" a b### {}[]~c [if ((($.name) == $.val.val2(1,2.3).val2) || $.name() == $.val || $.name == $._val(1)) call .../String ($:ROOT.name(('test')):ALIAS)]x{begin X}yz\n").getBytes()));
        
		YATLLexer lexer = new YATLLexer(input);
        lexer.setTokenFactory(new CommonTokenFactory(true));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        YATLParser parser = new YATLParser(tokens);
        ParseTree tree = parser.template(); // begin parsing at rule 'r'
//        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        
        StructGenerator<Struct> myListener = new StructGenerator<Struct>(Struct.class); //MyListener(); //
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(myListener, tree);
        Struct struct = myListener.getStruct(); // java.util.EmptyStackException
        
        
	}
    void merge(){
    	
    	// Always add ===$=== at the beginning of the file
    	// Always add '\n' \ at the end
    	
    }
}
