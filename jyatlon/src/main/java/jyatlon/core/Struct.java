package jyatlon.core;

import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Struct {
	// This looks promising, but the constructor calls won't be bound...so expect some runtime bugs
	// TODO - Have parameters in alpha order
	// Is there a way to know when a parameter can be a collection?
	// YES! Their names all end by "Exp" (But some of them are not: callExp)
	// To know which parm? Look inside YATLParser for a method having (int) as its parameter
	
	// To validate - No alias in binaryExp
	// ...
	
	
	public static class Template extends Struct {
		private final List<Section> section;
		public Template(List<Section> section){
			this.section = section;
		}
	}

	public static class Section extends Struct {
		private final PathExp pathExp;
		private final List<Line> line;
		public Section(PathExp pathExp, List<Line> line){
			this.pathExp = pathExp;
			this.line = line;
		}
	}
	
	public static class Line extends Struct {
		private final List<LineExp> lineExp;
		public Line(){
			this.lineExp = null;
		}
		public Line(List<LineExp> lineExp){
			this.lineExp = lineExp;
		}
	}
	
	public static class LineExp extends Struct {
		private final RawText rawText;
		private final Value value;
		private final EscapedChar escapedChar;
		private final EscapedBraket escapedBraket;
		public LineExp(RawText rawText) {
			this.rawText = rawText;
			this.value = null;
			this.escapedChar = null;
			this.escapedBraket = null;
		}
		public LineExp(Value value) {
			this.rawText = null;
			this.value = value;
			this.escapedChar = null;
			this.escapedBraket = null;
		}
		public LineExp(EscapedChar escapedChar) {
			this.rawText = null;
			this.value = null;
			this.escapedChar = escapedChar;
			this.escapedBraket = null;
		}
		public LineExp(EscapedBraket escapedBraket){
			this.rawText = null;
			this.value = null;
			this.escapedChar = null;
			this.escapedBraket = escapedBraket;
		}
	}
	
	public static class EscapedChar extends Struct {
		public EscapedChar() {	
		}
	}
	
	public static class EscapedBraket extends Struct {
		public EscapedBraket() {
		}
	}

//	public static class LineExp extends Struct {
//		List<RawText> rawText;
//		private final List<Value> value;
//		public LineExp() {
//			this.rawText = null;
//			this.value = null;
//		}
//		public LineExp(List<Value> value) {
//			this.rawText = null;
//			this.value = value;
//		}
//		public LineExp(List<RawText> rawText, List<Value> value){
//			this.rawText = rawText;
//			this.value = value;
//		}
//	}
	public static class RawText extends Struct {
		public RawText() {
			this.hashCode();
		}
	}
	
	public static class Operation extends Struct {
		private final String methodName;
		private final ArgExp argExp;
		private final String aliasName;
		public Operation(String methodName){
			this.methodName = methodName;
			this.argExp = null;
			this.aliasName = null;
		};
		public Operation(String methodName, ArgExp argExp){
			this.methodName = methodName;
			this.argExp = argExp;
			this.aliasName = null;
		};
		public Operation(String methodName, ArgExp argExp, String aliasName){
			this.methodName = methodName;
			this.argExp = argExp;
			this.aliasName = aliasName;
		};
//		public List<?> getValues(Object o, Object root) throws Exception { // 
//		Object x = oper.getValues(o, root);
//		if (x == null && alias != null)
//			return Collections.EMPTY_LIST;
//		else if (x instanceof Collection<?> && alias != null)
//			return new ArrayList<Object>((Collection<?>)x);
//		else if (alias == null && (x == null || x instanceof Collection<?>))
//			throw new IllegalStateException("Value that can be empty or a collection must have an ALIAS and be inserted in a BLOCK");
//		return Arrays.asList(new Object[] {x});
//	}
//		public Object getValues(Object o, Object root) throws Exception { // methodName (need to extract args...
//			if (methodName.equals(IToken.ROOT_PATH))
//				if (o == null)
//					return root;
//				else
//					throw new IllegalStateException("ROOT symbol is not a method name!");
//			if (constant != null) {
//				return constant;
//			} else if (o == null)
//				return null;
//			try {
//				Method m = o.getClass().getDeclaredMethod(methodName);
//				return m.invoke(o);
//			} catch (NoSuchMethodException e) {
//				String m2 = "get" + Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
//				Method m = o.getClass().getDeclaredMethod(m2);
//				return m.invoke(o);
//			}
//		}
	};

	// FIXME - operation should be a Collection
	public static class ValueExp extends Struct {
		private final String valueArg;
		private final String aliasName;
		private final List<Operation> operation;
		public ValueExp(String valueArg){
			this.valueArg = valueArg;
			this.aliasName = null;
			this.operation = null;
		};
		public ValueExp(String valueArg, List<Operation> operation){
			this.valueArg = valueArg;
			this.aliasName = null;
			this.operation = operation;
		};
		public ValueExp(String valueArg, String aliasName, List<Operation> operation) {
			this.valueArg = valueArg;
			this.aliasName = aliasName;
			this.operation = operation;
		}
		
//		private List<String> myAliases(){
//			List<String> result = new ArrayList<String>();
//			for (ValueCall c : calls)
//				result.add(c.alias);
//			return result;
//		}
//		public List<List<?>> getValues(Object root) throws Exception{ // runtime
//			List<List<?>> results = new ArrayList<List<?>>();
//			int index = 0;
//			for (ValueCall c : calls) {
//				if (results.isEmpty())
//					results.add(c.getValues(null, root));
//				else {
//					List<List<?>> newResults = new ArrayList<List<?>>();
//					for (List<?> previousResult : results) {
//						List <?> currentValues = c.getValues(previousResult.get(index), root);
//						for (Object o2 : currentValues) {
//							List<Object> newResult = new ArrayList<Object>(previousResult);
//							newResult.add(o2);
//							newResults.add(newResult);
//						}
//					}
//					results = newResults;
//					index++;
//				}
//			}
//			return results;
//		}

//		@Override
//		public void print(Writer w, Object root) throws Exception {
//			boolean first = true;
//			w.append("[");
//			int index = 0;
//			for (String a : aliases) {
//				w.append(first ? "" : ", ");
//				w.append(a == null ? (index == 0 ? IToken.ROOT_PATH : "_" + index) : a.toUpperCase());
//				first = false;
//			}
//			w.append("]\n");
//			first = true;
//			List<List<?>> allValues = getValues(root);
//			for (List<?> values : allValues) {
//				first = true;
//				w.append("[");
//				for (Object o : values) {
//					w.append(first ? "" : ", ");
//					w.append(o.toString());
//					first = false;
//				}
//				w.append("]\n");
//			}
//		}
	};

	// FIXME - valueExp should be a Collection
	public static class ArgExp extends Struct {
		private final List<ValueExp> valueExp;
		public ArgExp(List<ValueExp> valueExp){
			this.valueExp = valueExp;
		};
	};

	// FIXME - binaryOp should be a Collection
	// FIXME - valueExp should be a Collection
	private static class BinaryExp extends Struct {
		private final List<ValueExp> valueExp;
		private final List<String> binaryOp;
		public BinaryExp(List<ValueExp> valueExp, List<String> binaryOp){
			this.valueExp = valueExp;
			this.binaryOp = binaryOp;
		};
	};

	// FIXME - logicalOp should be a Collection
	// FIXME - binaryExp should be a Collection
	private static class LogicalExp extends Struct {
		private final List<BinaryExp> binaryExp;
		private final List<String> logicalOp;
		public LogicalExp(List<BinaryExp> binaryExp, List<String> logicalOp){
			this.binaryExp = binaryExp;
			this.logicalOp = logicalOp;
		};
	};

	private static class IfExp extends Struct {
		private final LogicalExp logicalExp;
		public IfExp(LogicalExp logicalExp){
			this.logicalExp = logicalExp;
		};
	};

	// FIXME - pathName should be a Collection
	private static class PathExp extends Struct {
		private final String anyPathOp;
		private final List<String> pathName;
		public PathExp(List<String> pathName) {
			this.anyPathOp = null;
			this.pathName = pathName;
		}
		public PathExp(String anyPathOp, List<String> pathName){
			this.anyPathOp = anyPathOp;
			this.pathName = pathName;
		};
	};

	private static class CallExp extends Struct {
		private final PathExp pathExp;
		public CallExp(PathExp pathExp){
			this.pathExp = pathExp;
		};
	};

	private static class Value extends Struct {
		private final IfExp ifExp;
		private final CallExp callExp;
		private final ValueExp valueExp;
		public Value(IfExp ifExp, CallExp callExp, ValueExp valueExp){
			this.ifExp = ifExp;
			this.callExp = callExp;
			this.valueExp = valueExp;
		};
	};
}
