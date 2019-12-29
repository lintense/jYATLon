package jyatlon.dev;

// TODO - Use YATL for generating its own Struct.java class...

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @author linte
 * SRP: This class will generate the Struct class by using Reflection on YATLParser.
 * Struct.java must be generated in order to keep in sync with the template grammar.
 * This will insure that the processing of the syntax is always up to date.
 */
public class StructGen {

    public static void main(String[] parms) {
    	
    	String parserClassName = "jyatlon.generated.YATLParser";
    	StringBuilder sb = new StringBuilder();
   
    	try {
			Class<?> c = Class.forName(parserClassName);
			Class<Parser> cl = Parser.class.isAssignableFrom(c) ? (Class<Parser>)c : null;
			
			if (cl == null)
				System.out.println(parserClassName + " is not a subclass of " + Parser.class.getName());
			else
				extractStruct(sb, cl);

		} catch (ClassNotFoundException e) {
			System.out.println(parserClassName + " not found");
		}
		
    	System.out.println(sb.toString());
    }
	private static void extractStruct(StringBuilder w, Class<Parser> parserType) {
		
		w.append("package jyatlon.core;\n\n");

		w.append("/**\n");
		w.append(" *	-----------------------------------------------------------------------------\n"); 
		w.append(" *	----          	  GENERATED on " + new Date() + "              ----\n");
		w.append(" *	-----------------------------------------------------------------------------\n");
		w.append(" *	DO NOT EDIT THIS FILE - THIS IS A GENERATED CLASS - YOUR CHANGES WILL BE LOST\n");
		w.append(" *	-----------------------------------------------------------------------------\n");
		w.append(" *	You must comply with the following instructions in order to do a modification\n");
		w.append(" *	in this file. Not doing so will see all your changes erased upon regeneration\n");
		w.append(" *\n");
		w.append(" *	What file(s) to edit:\n");
		w.append(" *		- jyatlon/src/main/antlr4/generated/YATL.g4\n");
		w.append(" *	\n");
		w.append(" *	How to generate this file (In Eclipse):\n");
		w.append(" *		- Run As > Java Application: @link StructGen\n");
		w.append(" *		- Copy console content into file: jyatlon/src/main/java/jyatlon/core/Struct.java\n");
		w.append(" *\n");
		w.append(" *	Generator class:\n"); 
		w.append(" *		- @link StructGen\n"); 
		w.append(" */\n\n");
		
		
		w.append("import java.util.List;\n\n");
		w.append("public class Struct {\n");
		w.append("	protected final int from;\n");
		w.append("	protected final int to;\n\n");
		
		
		w.append("	public Struct(int from, int to) {\n");
		w.append("		super();\n");
		w.append("		this.from = from;\n");
		w.append("		this.to = to;\n");
		w.append("	}\n");
		
		// For each subclass
		Class<?>[] parserClasses = parserType.getClasses();
		Arrays.stream(parserClasses).filter(x -> hasGrammarMethod(x)).forEach(c -> {
			
			w.append("	public static class " + getStructName(c) + " extends Struct {\n");
			
			// Fields
			Arrays.stream(c.getDeclaredMethods()).filter(m2 -> !m2.getReturnType().getName().equals(c.getName())).filter(m1 -> isGrammarMethod(m1)).forEach(m -> {
				w.append("		final " + toFieldString(m) + ";\n");
			});
			
			// Constructor
			// Generate only one constructor per Struct subclasses.
			// This is because there is no way (that I am aware of) to collect which args are optional(?,*) vs mandatory(+)
			// StructBuilder will need to find the constructor but this would be easy since there is only one!
			w.append("		public " + getStructName(c) + "(int from, int to, ");
			CommaObj com = new CommaObj();
			Arrays.stream(c.getDeclaredMethods()).filter(m2 -> !m2.getReturnType().getName().equals(c.getName())).filter(m1 -> isGrammarMethod(m1)).forEach(m -> {
				w.append(com.getComma() + toFieldString(m));
			});
			w.append("){\n");
			w.append("			super(from, to);\n");
			
			Arrays.stream(c.getDeclaredMethods()).filter(m2 -> !m2.getReturnType().getName().equals(c.getName())).filter(m1 -> isGrammarMethod(m1)).forEach(m -> {
				w.append("			this." + lowerFirst(getStructName(m.getReturnType())) + " = " + lowerFirst(getStructName(m.getReturnType())) + ";\n");
			});
			w.append("		}\n");

			w.append("	}\n");
		});
		w.append("}\n");
	}
	private static String toFieldString(Method m) {
		String structType = isTerminalClass(m.getReturnType()) ? "String" : getStructName(m.getReturnType());
		String structName = m.getName();
		return (isListMethod(m) ? "List<" + structType + ">" : structType) + " " + structName;
	}
	private static boolean isTerminalClass(Class<?> parserClass) {
		return !hasGrammarMethod(parserClass);
	}
	private static String lowerFirst(String arg) {
		return Character.toLowerCase(arg.charAt(0)) + arg.substring(1);
	}
	private static boolean hasGrammarMethod(Class<?> parserClass) {
		return Arrays.stream(parserClass.getDeclaredMethods()).anyMatch(m -> isGrammarMethod(m));
	}
	private static boolean isGrammarMethod(Method m) {
		return ParserRuleContext.class.isAssignableFrom(m.getReturnType());
	}
	private static boolean isListMethod(Method m) {
		return m.getParameterCount() == 1;
	}
	private static String getStructName(Class<?> c) {
		String className = c.getSimpleName();
		int p2 = className.lastIndexOf("Context");
		return p2 < 0 ? className : className.substring(0, p2);
	}
	private static class CommaObj {
		private int first = 0;
		String getComma() {
			return first++ == 0 ? "" : ", ";
		}
	}
}