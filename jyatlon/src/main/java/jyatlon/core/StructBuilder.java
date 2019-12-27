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
 * 
 * Constraint #1 - Every subclasses of class T has only ONE constructor!
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
				// Create new object
				Class<T> currentClass = getSubclassForName(currentRule.extractStructName());
				if (currentClass != null) {
					currentRule = currentRule.toStruct(currentClass);
//					List<Object> constructorParms = currentRule.getContructorParms(currentClass, children);
//					Constructor<T> currentConstructor = (Constructor<T>)currentClass.getConstructors()[0];
//					Object test = currentConstructor.newInstance(constructorParms.toArray());
//					currentRule = new Struct((jyatlon.core.Struct)test, currentRule.getArgName());
				} //else
					//currentRule = new Struct(currentRule.getValue());
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
//		abstract String getType();
		boolean isSameStruct(Base b) {
			return false;
		}
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
//		@Override
//		String getType() {
//			return extractStructName();
//		}
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
//		static String getRuleType(String pseudoType) {
//			return pseudoType.endsWith("Arg") || pseudoType.endsWith("Name") || pseudoType.endsWith("Op") ? "String" : pseudoType;
//		}
//		@Override
//		String getType() {
//			return getRuleType(extractStructName());
//		}
		
		boolean isSameStruct(Rule r) {
			return extractStructName().equals(r.extractStructName());
//					r != null && r.ctx != null && ctx != null && r.ctx.getClass() == ctx.getClass();
//			return b instanceof Rule && ((Rule)b);
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
			throw new IllegalStateException();
		}
		<T> Struct<T> toStruct(Class<T> currentClass) throws Exception {
//			Class<T> currentClass = builder.getSubclassForName(extractStructName());
			Object[] constructorParms = getContructorParms(currentClass, children);
			assert currentClass.getConstructors().length == 1; // Constraint #1
			Constructor<T> currentConstructor = (Constructor<T>)currentClass.getConstructors()[0]; 
			T test = currentConstructor.newInstance(constructorParms);
			return new Struct<T>(test);
		}
		
		/**
		 * @param currentRule
		 * @param currentClass
		 * @param children
		 * @return The arguments to pass to the constructor in order to generate a structure for this Rule.
		 */
		 Object[] getContructorParms(Class currentClass, List<Rule> children){
			// Get the only constructor first
			List<String> constructorsFields = Arrays.stream(currentClass.getDeclaredFields())
					.map(f -> f.getName())
					.collect(Collectors.toList());
//			List<Field> constructorsFields = new ArrayList(Arrays.asList(currentClass.getDeclaredFields()));

			Object[] constructorParms = new Object[constructorsFields.size() + ABSTRACT_STRUCT_PARM_SIZE];
			
			// Prepare the constructor parameters list
//			List<Object> constructorParms = new ArrayList<Object>();
//			constructorParms.add(ctx.start.getStartIndex());
//			constructorParms.add(ctx.stop.getStopIndex());
			constructorParms[0] = ctx.start.getStartIndex();
			constructorParms[1] = ctx.stop.getStopIndex();
			
			
			
			// Use a Map to gather values
			Set<String> collArgs = getCollectionParms();
			Map<String,ArrayList<Object>> tempMap = new HashMap<String,ArrayList<Object>>();
			children.forEach(a -> {
				String argName = a.getArgName();
//				try {
//					// If an argument is not present then fill it with null
//					while (!tempMap.containsKey(argName) && !argName.equals(constructorsFields.remove(0).getName()))
//						constructorParms.add(null);
//				} catch (Exception e) {
//					System.out.println(currentClass.getDeclaredFields());
					
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
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
//			while (!constructorsFields.isEmpty()) {
//				constructorsFields.remove(0);
//				constructorParms.add(null);
//			}
			return constructorParms;
		}
	}
	private static class Node extends Base {
		private final TerminalNode node;
		public Node(TerminalNode node) {
			super();
			this.node = node;
		}
//		@Override
//		String getType() {
//			return "String";
//		}
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