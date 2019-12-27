package jyatlon.dev;

// TODO - Wait for the Templater to work to self template its Struct Class

import java.lang.reflect.Method;
import java.util.Arrays;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @author linte
 * SRP: This class will generate the Struct class by using Reflection on YATLParser.
 * UNKNOWN: Will it be possible to know in advance all the required constructor.
 * Otherwise, will need to change the StructBuilder to fill in with nulls.
 */
public class StructGen {
	
//	private static final char INNER_CLASS_SEPARATOR = '$';
	
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
	public static void extractStruct(StringBuilder w, Class<Parser> parserType) {
		
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
	
	
//	private static Class<Parser> getParserForName(String subclassName){
//		Class<?> subclass = Arrays.asList(Parser.class.getDeclaredClasses())
//				.stream()
//				.filter(a -> Parser.class.isAssignableFrom(a))
//				.filter(b -> b.getSimpleName().equals(subclassName))
//				.findAny().orElse(null);
//		return subclass != null && Parser.class.isAssignableFrom(subclass)
//				? Parser.class.getClass().cast(subclass) 
//				: null;
//	}
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
//	private static boolean isGrammarMethod(Method m) {
//		Class<?> c = m.getReturnType();
//		return m.getParameterCount() == 0 && (
//				c.getName().equals(String.class.getName())
//				|| ParserRuleContext.class.isAssignableFrom(c)
//				|| Collection.class.isAssignableFrom(c));
//	}
	
	
//	private static boolean isContextListMethod(Method m) {
//		Class<?> c = m.getReturnType();
//		return m.getParameterCount() == 0 && (
//				c.getName().equals(String.class.getName())
//				|| ParserRuleContext.class.isAssignableFrom(c)
//				|| Collection.class.isAssignableFrom(c));
//	}
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
	
	/*

	public T getStruct() {
		return args.peek() != null && strucType.isAssignableFrom(args.peek().getValue().getClass()) 
				? strucType.cast(args.peek().getValue()) 
				: null;
	}
	Class<T> getSubclassForName(String subclassName){
		Class<?> subclass = Arrays.asList(strucType.getDeclaredClasses())
				.stream()
				.filter(a -> strucType.isAssignableFrom(a))
				.filter(b -> b.getSimpleName().equals(subclassName))
				.findAny().orElse(null);
		return subclass != null && strucType.isAssignableFrom(subclass)
				? strucType.getClass().cast(subclass) 
				: null;
	}
	@Override public void exitEveryRule(ParserRuleContext ctx) {
		List<Rule> children = new ArrayList<Rule>();
		for (int i = 0; i < ctx.getChildCount(); i++) {
			Base parm = args.pop();
			if (!parm.isTerminal()) // Drop terminal cause they are wrapped inside a rule
				children.add((Rule)parm);
		}
		Collections.reverse(children); // Keep original ordering
		
		Rule current = new Rule(ctx, children);
		
		boolean parentesis = children.size() == 1 && children.get(0).getType().equals(current.getType());
		if (parentesis) // Type that contains itself is surrounded by parenthesis so we remove it
			current = children.get(0);
		else if (!current.isString()) { // Do not create a Struct for terminal types
			
			// Prepare the constructor parameters list
			StringBuffer buf = new StringBuffer();
			buf.append("int,int");
			StringBuffer fullDesc = new StringBuffer();
			fullDesc.append("int from, int to");
			
			Set<String> collArgs = current.getCollectionParms();
			List<Object> constructorParms = new ArrayList<Object>();
			constructorParms.add(current.ctx.start.getStartIndex());
			constructorParms.add(current.ctx.stop.getStopIndex());
			
			Map<String,ArrayList<Object>> tempMap = new HashMap<String,ArrayList<Object>>();
			children.forEach(a -> {
				String argName = a.getArgName();
				if (collArgs.contains(argName)) {
					if (tempMap.containsKey(argName))
						tempMap.get(argName).add(a.getValue());
					else {
						ArrayList<Object> coll = new ArrayList<Object>();
						constructorParms.add(coll);
						coll.add(a.getValue());
						tempMap.put(argName, coll);
						buf.append(",List");
						fullDesc.append(", List<" + a.getType() + "> " + argName);
					}
				} else {
					buf.append("," + a.getType());
					fullDesc.append(", " + a.getType() + " " + argName);
					constructorParms.add(a.getValue()); // a should have been replaced
				}
					
			});
			String parmTypes = buf.toString(); //constructorParms.stream().map(item -> item.getClass().getSimpleName()).collect(Collectors.joining(","));
			String fullParmTypes = fullDesc.toString();
			Class<T> currentClass = null;
				// Select the correct constructor
				Constructor<T> matchingConstructor = null;
				currentClass = getSubclassForName(current.extractStructName());
				if (currentClass == null) {
//					current.showConstructor();
					throw new IllegalStateException("Missing constructor and class for: " + strucType.getName() + INNER_CLASS_SEPARATOR + current.extractStructName() + "(" + fullParmTypes + ")");
				}
//				currentClass = Class.forName(strucType.getName() + INNER_CLASS_SEPARATOR + current.extractStructName());
				Constructor<T>[] consArr = (Constructor<T>[]) currentClass.getConstructors();
				for (int i = 0; i < consArr.length && matchingConstructor == null; i++) {
					String consParmTypes = Arrays.stream(consArr[i].getParameterTypes()).map(item -> item.getSimpleName()).collect(Collectors.joining(","));
//					System.out.println("Constructor types; " + current.getType() + "(" + consParmTypes + ")");
					if (parmTypes.equals(consParmTypes))
						matchingConstructor = consArr[i];
				}
				if (matchingConstructor != null) {
					try {
						Object test = null;
						if (constructorParms.isEmpty())
							test = matchingConstructor.newInstance();
						else
							test = matchingConstructor.newInstance(constructorParms.toArray());
						current = new Struct((jyatlon.core.Struct)test);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else
					throw new IllegalStateException("Missing constructor: " + strucType.getName() + INNER_CLASS_SEPARATOR + current.extractStructName() + "(" + fullParmTypes + ")");
		}
		args.push(current);
	}

	@Override public void visitTerminal(TerminalNode node) { // Not needed, all useful is wrapped so terminals can be dropped
		args.push(new Node(node)); // Still we must push something here to match the child count!
	}

	private static abstract class Base {
		abstract String getType();
		boolean isTerminal() {
			return false;
		}
		abstract Object getValue();
	}
	private static class Struct extends Rule {
		private final jyatlon.core.Struct struct;
		public Struct(jyatlon.core.Struct struct) {
			super(null, null);
			this.struct = struct;
			if (struct == null)
				throw new IllegalArgumentException();
		}
		@Override
		String getType() {
			return extractStructName();
		}
		@Override
		Object getValue() {
			return struct;
		}
		String extractStructName() {
			return struct.getClass().getSimpleName();
		}
	}
	private static class Rule extends Base {
		private final ParserRuleContext ctx;
		private final List<Rule> children;
		public Rule(ParserRuleContext ctx, List<Rule> children) {
			super();
			this.ctx = ctx;
			this.children = children;
		}
		Set<String> getCollectionParms() {
			return Arrays.stream(ctx.getClass().getDeclaredMethods())
				.filter(m -> m.getParameterTypes().length == 1)
				.filter(m -> m.getParameterTypes()[0].getName().equals("int"))
				.map(m -> m.getName())
				.collect(Collectors.toSet());
		}
		static String getRuleType(String pseudoType) {
			return pseudoType.endsWith("Arg") || pseudoType.endsWith("Name") || pseudoType.endsWith("Op") ? "String" : pseudoType;
		}
		@Override
		String getType() {
			return getRuleType(extractStructName());
		}
		boolean isString() {
			return getRuleType(extractStructName()).equals("String");
		}
		String getKey() {
			String count = "00000" + children.size();
			return extractStructName() + count.substring(count.length() - 4);
		}
		String getArgName() {
			return lowerFirst(extractStructName());
		}
		static String lowerFirst(String arg) {
			return Character.toLowerCase(arg.charAt(0)) + arg.substring(1);
		}
		String extractStructName() {
			String result = ctx.getClass().getSimpleName();
			int p = result.lastIndexOf("Context");
			return result.substring(0, p > 0 ? p : result.length());
		}
		void showConstructor() {
			System.out.println("private static class " + extractStructName() + " extends Struct {");
			children.forEach(base -> {System.out.println("	private final " + base.getType() + " " + base.getArgName() + ";");});
			System.out.print("	public " + extractStructName() + "(");
			CommaObj c = new CommaObj();
			children.forEach(base -> {System.out.print(c.getComma() + base.getType() + " " + base.getArgName());});
			System.out.println("){");
			children.forEach(base -> {System.out.println("		this." + base.getArgName() + " = " + base.getArgName() + ";");});
			System.out.println("	};\n};\n");
		}
		@Override
		Object getValue() {
			if (children.isEmpty())
				return ctx.getText();
			throw new IllegalStateException();
		}
	}
	private static class Node extends Base {
		private final TerminalNode node;
		public Node(TerminalNode node) {
			super();
			this.node = node;
		}
		@Override
		String getType() {
			return "String";
		}
		@Override
		boolean isTerminal() {
			return true;
		}
		@Override
		Object getValue() {
			return node.getText();
		}
	}
	private static class CommaObj {
		private int first = 0;
		String getComma() {
			return first++ == 0 ? "" : ", ";
		}
	}*/
}