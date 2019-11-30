package jyatlon.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import jyatlon.generated.YATLBaseListener;

/**
 * @author linte
 * SRP: This class is responsible to create a new structure from the AST resulting from parsing.
 * The package of the structure to generate should be pass as a parameter.
 * The structure to create MUST comply to certain rules.
 * All the subclasses of your struct <T> class MUST be inner classes so they can be found using getDeclaredClasses()
 * The constructors MUST be public
 * The main hacks are in the grammar file:
 * Instructions:
 * All names ending by "Arg", "Name", "Op" should be terminals and will be send to constructor as a String
 * All names ending by "Exp" are considered to have a terminal Collection arg.
 * Always wrap Terminals so we can drop them
 * Use literals instead of ALIASES gives better error messages on parsing errors
 * TODO - Could be improved to return all the possible constructor which would avoid runtime errors
 */
public class StructBuilder<T> extends YATLBaseListener {
	public StructBuilder(Class<T> strucType, StructInitializer initializer) {
		super();
		this.strucType = strucType;
		this.initializer = initializer;
	}
	private static final char INNER_CLASS_SEPARATOR = '$';
	private final Class<T> strucType;
	private final Stack<Base> args = new Stack<Base>();
	private final StructInitializer initializer;

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
						current.init(initializer);
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
		void init(StructInitializer initializer){
			throw new IllegalStateException("To be implemented by subclass");
		}
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
		void init(StructInitializer initializer){
			struct.init(initializer);
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
//		String getKey() {
//			String count = "00000" + children.size();
//			return extractStructName() + count.substring(count.length() - 4);
//		}
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
	}
}