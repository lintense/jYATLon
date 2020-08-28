package jyatlon.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
 * 
 * This is a very simple quite powerful parser that will read the ANTLR generated tree and create a nice Java Struct from it.
 * The nicest thing is that the Struct class will be generated for you using the StructGen.main method.
 * This will insure that your Struct class to be always in perfect sync with the Grammar.
 * The binding between the Struct and the rest of the application is insured by Java.
 * 
 * Instructions for the Struct (aka <T>) class hierarchy:
 * - The structure to generate is passed as a parameter.
 * - All the subclasses of the Struct class MUST be inner classes so they can be found easily using getDeclaredClasses()
 * - All the constructors MUST be public.
 * 
 * Instructions for the Grammar (*.g4) file:
 * - All names ending by "Arg", "Name", "Op" should be terminals and will be send to the Struct constructors as Strings
 * - All names ending by "Exp" are considered to have a terminal constructor Collection parameter argument.
 * - Always wrap Terminals ("Arg", "Name", "Op") so we can drop them.
 * - Using literals (such as ',') instead of ALIASES (such as COMMA) gives better error parsing error messages (1).
 * (1) Not my fault, this is how ANTLR works!
 * 
 * TODO - Could be improved to return all the possible constructor which would avoid runtime errors
 * TODO - Need better/proper logging
 * TODO - from & to values do not look good
 * 
 * Constraint #1 - Every subclasses of class T must have only ONE constructor!
 * 
 * 
 */
public class StructBuilder<T> extends YATLBaseListener {
	
	private final Class<T> strucType;
	private final Stack<Base> args = new Stack<Base>();
	private static final int ABSTRACT_STRUCT_PARM_SIZE = 2; 
	public StructBuilder(Class<T> strucType) {
		super();
		this.strucType = strucType;
	}
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
		Rule currentRule = new Rule(ctx, children);
		boolean parentesis = children.size() == 1 && currentRule.isSameStruct(children.get(0));
		if (parentesis) // Type that contains itself is surrounded by parenthesis so we remove it
			currentRule = children.get(0);
		else if (!currentRule.isTerminal()) { // Do not create a Struct for terminal types
			try {
				Class<T> currentClass = getSubclassForName(currentRule.extractStructName());
				if (currentClass != null)
					currentRule = currentRule.toStruct(currentClass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		args.push(currentRule);
	}
	@Override 
	public void visitTerminal(TerminalNode node) { 
		// Not needed, all useful is wrapped so terminals can be dropped
		// Still we must push something here to match the child count!
		args.push(new Node(node)); 
	}

	/**
	 * @author linte
	 * SRP - A small helper hierarchy to hold the already parsed nodes.
	 * @param <T>
	 */
	private static abstract class Base {
		boolean isTerminal() {
			return false;
		}
		abstract Object getValue();
	}
	private static class Struct<T> extends Rule {
		private final T struct;
		public Struct(T struct) {
			super(null, null);
			this.struct = struct;
			if (struct == null)
				throw new IllegalArgumentException();
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
			this.ctx = ctx;
			this.children = children;
		}
		Set<String> getCollectionParms() {
			return Arrays.stream(ctx.getClass().getDeclaredMethods())
				.filter(m -> m.getParameterTypes().length == 1)
				.filter(m -> ParserRuleContext.class.isAssignableFrom(m.getReturnType()))
				.map(m -> m.getName())
				.collect(Collectors.toSet());
		}
		boolean isSameStruct(Rule r) {
			return extractStructName().equals(r.extractStructName());
		}
		String getArgName() {
			return Utils.toLowerFirst(extractStructName());
		}
		String extractStructName() {
			String result = ctx.getClass().getSimpleName();
			int p = result.lastIndexOf("Context");
			return result.substring(0, p > 0 ? p : result.length());
		}
		@Override
		Object getValue() {
			if (children.isEmpty())
				return ctx.getText();
			throw new IllegalStateException("BUG: " + this.getClass().getName() + " " + this.getArgName() + " text: " + ctx.getText() + " args: " + children.size() + " arg1: " + children.get(0));
		}
		<T> Struct<T> toStruct(Class<T> currentClass) throws Exception {
			assert currentClass.getConstructors().length == 1; // Constraint #1
			Constructor<T> currentConstructor = (Constructor<T>)currentClass.getConstructors()[0];
			Object[] constructorParms = getContructorParms(currentConstructor);
			T test = null;
			try {
				test = currentConstructor.newInstance(constructorParms);
			} catch (Exception e) {
				// TODO Auto-generated catch block)
				showDebugInfo(currentConstructor, constructorParms);
				e.printStackTrace();
			} 
			return new Struct<T>(test);
		}
		private <T> void showDebugInfo(Constructor<T> c, Object[] constructorParms) {
			System.out.println("Constructor Parms: " + c.getName() + Arrays.toString(c.getParameterTypes()));
			String fields = "";
			for (Field f : Arrays.asList(c.getDeclaringClass().getDeclaredFields()))
				fields += "," + f.getName();
			System.out.println("Declared Fields: " + fields.substring(1));
			System.out.println("Parameters values: " + Arrays.toString(constructorParms));
		}

		/**
		 * @param currentConstructor
		 * @return The arguments to pass to the constructor in order to generate a structure for this Rule.
		 */
		<T> Object[] getContructorParms(Constructor<T> currentConstructor){
			Class<T> currentClass = currentConstructor.getDeclaringClass();
			// Get the only constructor first
			List<String> constructorsFields = Arrays.stream(currentClass.getDeclaredFields())
					.map(f -> f.getName())
					.collect(Collectors.toList());

			// Prepare the constructor parameters list
			Object[] constructorParms = new Object[currentConstructor.getParameterCount()];
			constructorParms[0] = ctx.start.getStartIndex();
			constructorParms[1] = ctx.stop.getStopIndex() + 1;

			// Use a Map to gather values
			Set<String> collArgs = getCollectionParms();
			Map<String,ArrayList<Object>> tempMap = new HashMap<String,ArrayList<Object>>();
			children.forEach(a -> {
				String argName = a.getArgName();
				if (collArgs.contains(argName)) {
					if (tempMap.containsKey(argName))
						tempMap.get(argName).add(a.getValue());
					else {
						ArrayList<Object> coll = new ArrayList<Object>();
						constructorParms[constructorsFields.indexOf(argName) + ABSTRACT_STRUCT_PARM_SIZE] = coll;
						coll.add(a.getValue());
						tempMap.put(argName, coll);
					}
				} else
					constructorParms[constructorsFields.indexOf(argName) + ABSTRACT_STRUCT_PARM_SIZE] = a.getValue();
			});
			return constructorParms;
		}
	}
	private static class Node extends Base {
		private final TerminalNode node;
		public Node(TerminalNode node) {
			super();
			this.node = node;
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
}