package jyatlon.core;

// TODO All final List should be set to immutable
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jyatlon.generated.YATLParser;

public abstract class Struct {
	private static final String ALIAS_PREFIX = "_/";
	private static final String ROOT = YATLParser.VOCABULARY.getLiteralName(YATLParser.ROOT).replace("'", "");
	
	protected final Map<String,Object> properties = new HashMap<String,Object>();
	protected final int from;
	protected final int to;
	
	public Struct(int from, int to) {
		super();
		this.from = from;
		this.to = to;
	}
	public void init(StructInitializer initializer) {
		// To be overridden by subclasses when necessary
	}
	public Object getProperty(String key) {
		return properties.get(key);
	}
	public List<Struct> getStructList(String key) {
		return (List<Struct>)properties.get(key);
	}
	public Map<String,Struct> getStructMap(String key) {
		return (Map<String,Struct>)properties.get(key);
	}
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
	
	protected String getDefaultAlias(String alias) {
		return alias != null ? alias : ALIAS_PREFIX + from;
	}
	// This looks promising, but the constructor calls won't be bound...so expect some runtime bugs
	// To validate - No alias in binaryExp
	// ...
	
	
	public static class Template extends Struct {
		final List<Section> section;
		public Template(int from, int to, List<Section> section){
			super(from, to);
			this.section = section;
		}
		public void test(Object root) {
			Path rootPath = new Path(root.getClass().getSimpleName(), ROOT, root);
			String obj = rootPath.toString();
			section.forEach(s -> s.call(rootPath.add("String", getDefaultAlias(null), obj))); // TODO - String:Alias1, ...
		}
		public void init(StructInitializer initializer) { // calling init must be done by subclass for Dynamic Method Dispatch to work
			initializer.init(this);
		}
	}

	public static class Section extends Struct {
		final PathExp pathExp;
		final List<Line> line;
		public Section(int from, int to) {
			super(from, to);
			this.pathExp = null;
			this.line = null;
		}
		public Section(int from, int to, PathExp pathExp) {
			super(from, to);
			this.pathExp = pathExp;
			this.line = null;
		}
		public Section(int from, int to, List<Line> line){
			super(from, to);
			this.pathExp = null;
			this.line = line;	
		}
		public Section(int from, int to, PathExp pathExp, List<Line> line){
			super(from, to);
			this.pathExp = pathExp;
			this.line = line;	
		}
		public void init(StructInitializer initializer) { // calling init must be done by subclass for Dynamic Method Dispatch to work
			initializer.init(this);
		}
		public Stream<LineExp> getExpStream(){
			return line.stream().flatMap(Line::getExpStream);
		}
		public Stream<Value> getValueStream(){
			return getExpStream().filter(exp -> exp.value != null).map(exp -> exp.value);
		}
		public Stream<ControlExp> getControlStream(){
			return getExpStream().filter(exp -> exp.controlExp != null).map(exp -> exp.controlExp);
		}
		public void call(Path rootPath) {
			assert pathExp.isCompatible(rootPath);
			
			// Compute all values
			List<Path> result = new ArrayList();
			List<Value> values = getValueStream().collect(Collectors.toList());
			values.forEach(v -> result.addAll(v.getValues(rootPath))); // FIXME this is probably wrong
			result.forEach(r -> r.toString());
		}
//		public List<List<?>> getValues(Path path) throws Exception{ // runtime
//			List<List<?>> results = new ArrayList<List<?>>();
////			int index = 0;
//			for (ValueCall c : calls) {
////				if (results.isEmpty())
////					results.add(c.getValues(null, root));
////				else {
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
////				}
//			}
//			return results;
//		}
	}
	
	public static class Line extends Struct {
		final List<LineExp> lineExp;
		public Line(int from, int to){
			super(from, to);
			this.lineExp = null;
		}
		public Line(int from, int to, List<LineExp> lineExp){
			super(from, to);
			this.lineExp = lineExp;
		}
		public Stream<LineExp> getExpStream(){
			AtomicBoolean ic = new AtomicBoolean(false);
			return lineExp == null 
				? Stream.empty() 
				: lineExp.stream()
					.filter(exp -> ic.getAndSet(exp.comment ? !ic.get() : ic.get()) || exp.comment); // Flip value on exp.comment  
		}
		public void init(StructInitializer initializer) { // calling init must be done by subclass for Dynamic Method Dispatch to work
			initializer.init(this);
		}
	}
	
	public static class LineExp extends Struct {
		final RawText rawText;
		final Value value;
		final boolean comment;
		final EscapedChar escapedChar;
		final EscapedBraket escapedBraket;
		final ControlExp controlExp;
		public LineExp(int from, int to, RawText rawText) {
			super(from, to);
			this.rawText = rawText;
			this.value = null;
			this.comment = false;
			this.escapedChar = null;
			this.escapedBraket = null;
			this.controlExp = null;
		}
		public LineExp(int from, int to, Value value) {
			super(from, to);
			this.rawText = null;
			this.value = value;
			this.comment = false;
			this.escapedChar = null;
			this.escapedBraket = null;
			this.controlExp = null;
		}
		public LineExp(int from, int to, EscapedChar escapedChar) {
			super(from, to);
			this.rawText = null;
			this.value = null;
			this.comment = false;
			this.escapedChar = escapedChar;
			this.escapedBraket = null;
			this.controlExp = null;
		}
		public LineExp(int from, int to, EscapedBraket escapedBraket){
			super(from, to);
			this.rawText = null;
			this.value = null;
			this.comment = false;
			this.escapedChar = null;
			this.escapedBraket = escapedBraket;
			this.controlExp = null;
		}
		public LineExp(int from, int to, String commentOp) {
			super(from, to);
			this.rawText = null;
			this.value = null;
			this.comment = true;
			this.escapedChar = null;
			this.escapedBraket = null;
			this.controlExp = null;
		}
		public LineExp(int from, int to, ControlExp controlExp) {
			super(from, to);
			this.rawText = null;
			this.value = null;
			this.comment = false;
			this.escapedChar = null;
			this.escapedBraket = null;
			this.controlExp = controlExp;
		}
	}
	
	public static class ControlExp extends Struct {
		final String controlOp;
		final String aliasName;
		public ControlExp(int from, int to, String controlOp, String aliasName) {
			super(from, to);
			this.controlOp = controlOp;
			this.aliasName = aliasName;
		}
	}
	
	
	
	public static class EscapedChar extends Struct {
		public EscapedChar(int from, int to) {
			super(from, to);
		}
	}
	
	public static class EscapedBraket extends Struct {
		public EscapedBraket(int from, int to) {
			super(from, to);
		}
	}

	public static class RawText extends Struct {
		public RawText(int from, int to) {
			super(from, to);
			this.hashCode();
		}
	}
	
	public static class Operation extends Struct {
		final String methodName;
		final ArgExp argExp;
		final String aliasName;
		public Operation(int from, int to, String methodName){
			super(from, to);
			this.methodName = methodName;
			this.argExp = null;
			this.aliasName = null;
		}
		public Operation(int from, int to, String methodName, ArgExp argExp){
			super(from, to);
			this.methodName = methodName;
			this.argExp = argExp;
			this.aliasName = null;
		}
		public Operation(int from, int to, String methodName, ArgExp argExp, String aliasName){
			super(from, to);
			this.methodName = methodName;
			this.argExp = argExp;
			this.aliasName = aliasName;
		}
		public List<Path> appendValuePath(List<Path> inputPath) {
			List<Path> result = new ArrayList();
			for (Iterator<Path> i = inputPath.iterator(); i.hasNext();) {
				Path p = i.next();
				Object o = p.getObject();
				if (o instanceof Collection<?>) {
					Collection<?> c = (Collection<?>)o;
					c.forEach(oc -> result.add(callOperation(p, oc)));
				} else {
					result.add(callOperation(p, o));
				}
			}
			return result;
		}
		private Path callOperation(Path inputPath, Object o) { // TODO - Implement MAP
			Object response = null;
			try {
				try {
					Method m = o.getClass().getDeclaredMethod(methodName);
					response = m.invoke(o);
				} catch (NoSuchMethodException e) {
						String m2 = "get" + Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
						Method m = o.getClass().getDeclaredMethod(m2);
						response = m.invoke(o);
				}
			} catch (Exception e1) {
				// FIXME - Auto-generated catch block
				e1.printStackTrace();
			}
			return new Path(response.getClass().getSimpleName(), getDefaultAlias(aliasName), response);
		}

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
	}

	public static class ValueExp extends Struct {
		final String valueArg;
		final String aliasName;
		final List<Operation> operation;

		public ValueExp(int from, int to, String valueArg){
			super(from, to);
			this.valueArg = valueArg;
			this.aliasName = null;
			this.operation = null;
		};
		public ValueExp(int from, int to, String valueArg, List<Operation> operation){
			super(from, to);
			this.valueArg = valueArg;
			this.aliasName = null;
			this.operation = operation;
		};
		public ValueExp(int from, int to, String valueArg, String aliasName, List<Operation> operation) {
			super(from, to);
			this.valueArg = valueArg;
			this.aliasName = aliasName;
			this.operation = operation;
		}
		public List<Path> getValues(Path rootPath) {
			Object obj = rootPath.getValueForName(valueArg);
			List<Path> result = new ArrayList();
			result.add(new Path(null, aliasName != null ? aliasName : getDefaultAlias(aliasName), obj));
			for (Iterator<Operation> i = operation.iterator(); i.hasNext();)
				result = i.next().appendValuePath(result);
			return result;
		}
		
//		private List<String> myAliases(){
//			List<String> result = new ArrayList<String>();
//			for (ValueCall c : calls)
//				result.add(c.alias);
//			return result;
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

	public static class ArgExp extends Struct {
		final List<ValueExp> valueExp;
		public ArgExp(int from, int to, List<ValueExp> valueExp){
			super(from, to);
			this.valueExp = valueExp;
		};
	}

	private static class BinaryExp extends Struct {
		final List<ValueExp> valueExp;
		final List<String> binaryOp;
		public BinaryExp(int from, int to, List<ValueExp> valueExp, List<String> binaryOp){
			super(from, to);
			this.valueExp = valueExp;
			this.binaryOp = binaryOp;
		};
	}

	private static class LogicalExp extends Struct {
		final List<BinaryExp> binaryExp;
		final List<String> logicalOp;
		public LogicalExp(int from, int to, List<BinaryExp> binaryExp, List<String> logicalOp){
			super(from, to);
			this.binaryExp = binaryExp;
			this.logicalOp = logicalOp;
		};
	}
	private static class IfExp extends Struct {
		final LogicalExp logicalExp;
		public IfExp(int from, int to, LogicalExp logicalExp){
			super(from, to);
			this.logicalExp = logicalExp;
		};
	}

	private static class PathExp extends Struct {
		final boolean isAbsolute;
		final List<String> pathName;
		public PathExp(int from, int to, List<String> pathName) {
			super(from, to);
			this.isAbsolute = true;
			this.pathName = pathName;
		}
		public PathExp(int from, int to, String anyPathOp, List<String> pathName){
			super(from, to);
			this.isAbsolute = false;
			this.pathName = pathName;
		};
		public boolean isCompatible(Path path) {
			return path.compatibility(pathName, isAbsolute) > 0;
		}
	}

	private static class CallExp extends Struct {
		final PathExp pathExp;
		public CallExp(int from, int to, PathExp pathExp){
			super(from, to);
			this.pathExp = pathExp;
		};
	}

	private static class Value extends Struct {
		final IfExp ifExp;
		final CallExp callExp;
		final ValueExp valueExp;
		public Value(int from, int to, IfExp ifExp, CallExp callExp, ValueExp valueExp){
			super(from, to);
			this.ifExp = ifExp;
			this.callExp = callExp;
			this.valueExp = valueExp;
		};
		public List<Path> getValues(Path rootPath) {
			return valueExp.getValues(rootPath);
		}
	}
}

