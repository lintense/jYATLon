package jyatlon.core;

// What is the simplest way to convert the Parse Tree into a Struct?
// https://jakubdziworski.github.io/java/2016/04/01/antlr_visitor_vs_listener.html

//https://tomassetti.me/antlr-mega-tutorial/ 

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import jyatlon.core.Struct.Template;
import jyatlon.generated.YATLLexer;
import jyatlon.generated.YATLParser;

public class App { // Rename to YATL

    public static void main( String[] args ) throws IOException
    {
//    	String temp = "{begin X}{{$:X}}{{call .../String $.toString}}{end X}\n=== String ===\n1{{call .../String $}}\n=== String/String ===\n2\n=== .../String ===\n3";
//    	String temp = "{{call .../String/String:Y $}}\n=== .../String/String:X ===\n=== .../String:Y ===\n1";
//    	String temp = "{{$.toString()}}\n=== .../String/String:X ===\n=== .../String:Y ===\n1";
    	
//    	String temp = "{begin X}{{X}}{{$:X}}{end X}";
    	
//    	String temp = "{begin X}{{X}}{empty X}{{X}}{end X}";
    	
    	String temp = "{begin ALIAS}Hello {{$:ALIAS}}!{end ALIAS}";
    	StringWriter w = new StringWriter();
    	YATL template = YATL.fromString(temp);
    	template.merge("test", w);
		System.out.println(w.toString());
    }

//	private void obsolete()
//	{
//		String test = "=$=\n{begin X}Hello {begin Y}Hello {{$.toString:X.toString:X}}{{$:Y}}!{end Y}!{end X}\n";
//
//		UnbufferedCharStream input = new UnbufferedCharStream(new ByteArrayInputStream((test.getBytes())));
//		YATLLexer lexer = new YATLLexer(input);
//        lexer.setTokenFactory(new CommonTokenFactory(true));
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        YATLParser parser = new YATLParser(tokens);
//        ParseTree tree = parser.template(); // begin parsing at rule 'r'
////        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
//        
//        StructBuilder<Struct> myListener = new StructBuilder<Struct>(Struct.class);
//        ParseTreeWalker walker = new ParseTreeWalker();
//        walker.walk(myListener, tree);
//        Struct struct = myListener.getStruct();
//        
//        Template t = (Template)struct;
//        
//        Block.PathBlock pb = getBlock(test, t);
//        StringWriter sw = new StringWriter();
//        BlockProcessor.merge(pb, sw, "World");
//        System.out.println(sw.toString());
//        
////        t.test(t);
//	}

//    static String process(String templateText, Object root) {
//    	Template t = getTemplate(templateText);
//    	BlockBuilder bb = new BlockBuilder(templateText);
//    	Block.PathBlock pb = bb.parseTemplate(t);
//    	StringWriter sw = new StringWriter();
//    	BlockProcessor.merge(pb, sw, root);
//    	return sw.toString();
//    }
    
    
//    static Block.PathBlock getBlock(String templateText, Template t) {
//    	BlockBuilder bb = new BlockBuilder(templateText);
//    	return bb.parseTemplate(t);
//    }
    	
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
