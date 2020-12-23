package jyatlon.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jyatlon.core.Block.BinaryTestBlock;
import jyatlon.core.Block.LogicalTestBlock;
import jyatlon.core.Block.OperationBlock;
import jyatlon.core.Block.ValueBlock;

/**
 * @author snadeau
 * A statefull data container and processor used to manage a list of ValuePaths.
 *
 */
public class Combination {
	
	private static final Object[] EMPTY_PARMS = new Object[] {};
	
	final Matcher matcher;
	final ValuePath pathCtx;
	final Map<String,Object> combinedAliases = new HashMap<>();
	final List<ValuePath> paths = new ArrayList<>();
	final Map<String,Object> combinedClasses = new HashMap<>();
	
	Combination(Matcher matcher){ // Initial empty combination
		this.pathCtx = null;
		this.matcher = matcher;
	}
	Combination callPath(ValuePath vp, List<ValuePath> pathParms) {
		return new Combination(matcher, pathCtx != null ? pathCtx.add(vp) : vp, pathParms, combinedClasses.get(Constant.ROOT));
	}
	private Combination(Matcher matcher, ValuePath pathCtx, List<ValuePath> pathParms, Object o) {
		this.pathCtx = pathCtx;
		this.matcher = matcher;
		initPath(pathCtx);
		pathParms.forEach(p->initPath(p));
		if (o != null && !isClassDefined(Constant.ROOT)) // Extract root from ctx
			initPath(ValuePath.getRoot(o));			
	}
	private Combination(Combination parent){
		this.pathCtx = parent.pathCtx;
		this.matcher = parent.matcher;
		this.paths.addAll(parent.paths);
		this.combinedAliases.putAll(parent.combinedAliases);
		this.combinedClasses.putAll(parent.combinedClasses);
	}
	Combination addPath(ValuePath vp) {
		Combination result = new Combination(this);
		return result.initPath(vp) ? result : null;
	}
	private boolean initPath(ValuePath vp) {
		boolean result = true;
		paths.add(vp);
		for (int i = 0; i < vp.aliases.length; i++) {
			if (vp.aliases[i] != null) {
				Object previous = combinedAliases.put(vp.aliases[i], vp.objects[i]);
				if (previous != null && !matcher.isSameObject(previous, vp.objects[i]))
					result = false;
			}
		}
		for (int i = 0; i < vp.classes.length; i++)
			if ((Map.class.isAssignableFrom(vp.objects[i].getClass()) && ((Map)vp.objects[i]).containsKey(Constant.MAP_KEY_FOR_CLASS) 
					? ((Map)vp.objects[i]).get(Constant.MAP_KEY_FOR_CLASS).toString() 
					: vp.objects[i].getClass().getName())
						.endsWith(vp.classes[i])) // Must be the actual class name (for now)
				combinedClasses.put(vp.classes[i], vp.objects[i]); // Keep only the most recent object
		if (vp.isRoot())
			combinedClasses.put(vp.classes[0], vp.objects[0]);

		return result;
	}
	// FIXME - Do we need this method? 
	// FIXME - Needs to be synchronized with getMatchingPath()
	// Reason: This is quicker than getMatchingPath() when testing for isDefined();
	// This is because computeValues() is a heavy process...
	private boolean hasMatchingPath(ValuePath current) { 
		// Find which path element is matching the current value
		// TODO seem a bit restrictive. i.e.Null alias should match
		for (ValuePath path : paths)
			if (Arrays.equals(current.classes, path.classes) && Arrays.equals(current.aliases, path.aliases))
				return true;
		
		return getObjectForName(current.aliases[0] != null ? current.aliases[0] : current.classes[0]) != null;
	}
	public ValuePath getMatchingPath(ValuePath current) { // path=found, null=not found
		// Find which path element is matching the current value
		// TODO seem a bit restrictive. i.e.Null alias should match
		for (ValuePath path : paths)
			if (Arrays.equals(current.classes, path.classes) && Arrays.equals(current.aliases, path.aliases))
				return path;
		
		if (current.aliases.length == 1) {
			// path extracted from the context must keep their context
			ValuePath ctx = pathCtx;
			while (ctx != null) {
				if (current.aliases[0] != null && current.aliases[0].equals(ctx.getAliasName()))
					return ctx;
				else if (current.classes[0] != null && current.classes[0].equals(ctx.getClassName()))
					return ctx;
				ctx = ctx.parent;
			}
			Object o = current.aliases[0] != null 
					? getObjectForName(current.aliases[0])
					: getObjectForName(current.classes[0]);
			if (o != null)
				return new ValuePath(current.getClassName(), current.getAliasName(), o);
		}

		return null;
	}
	public Object getObjectForName(String name){
		Object result = combinedAliases.get(name);
		return result == null ? combinedClasses.get(name) : result;
//		return paths.stream().map(vp -> vp.getObjectForName(name, null)).filter(p -> p != null).collect(Collectors.toList());
	}
	private boolean isAliasDefined(String aliasName) {
		return combinedAliases.containsKey(aliasName);
	}
	private boolean isClassDefined(String className) {
		return combinedClasses.containsKey(className);
	}
//	boolean isAliasEmpty(String name) {
//		Object o = combinedAliases.getOrDefault(name, null);
//		return o != null && Collection.class.isAssignableFrom(o.getClass()) && ((Collection<?>)o).isEmpty();
//	}
//	boolean isAliasNotEmpty(String name) {
//		Object o = combinedAliases.getOrDefault(name, null);
//		return o != null && Collection.class.isAssignableFrom(o.getClass()) && !((Collection<?>)o).isEmpty();
//	}

	public List<ValuePath> computeValues(ValueBlock vb) {
		
		// Init value object
		Object obj;
		if (Utils.isString(vb.argName, Constant.QUOTES)) // value is a String
			obj = Utils.unquote(vb.argName);
		else if (Utils.isNumber(vb.argName)) // value is a Number
			if (vb.argName.indexOf('.') > 0)
				obj = Double.valueOf(vb.argName);
			else
				obj = Integer.valueOf(vb.argName);
		else
			obj = getObjectForName(vb.argName); // Should be already defined

		// Maybe it is not a problem since empty list and null object are both considered empty and as such are treated the same way.
		if (obj == null)
			return Collections.emptyList();
		
		// Init processing
		final List<ValuePath> toProcess = new ArrayList<ValuePath>();
		if (obj instanceof Collection && vb.aliasName == null && combinedAliases.containsKey(vb.argName)) // Previously computed
			toProcess.add(new ValuePath(vb.argName, vb.aliasName, obj));
		else if (obj instanceof Collection)
			((Collection<?>) obj).forEach(o->toProcess.add(new ValuePath(vb.argName, vb.aliasName, o)));
		else if (obj != null)
			toProcess.add(new ValuePath(vb.argName, vb.aliasName, obj));
		
		List<ValuePath> result = toProcess;
		for (OperationBlock op : vb.ops)
			result = result.stream().map(p->extractPaths(op, p)).flatMap(List::stream).collect(Collectors.toList());
		
		return result;
	}
	private List<ValuePath> extractPaths(OperationBlock ob, ValuePath p){
		Object o = p.getObject();
		Class<?> c = o.getClass();
		
		Object[] parms = ob.args != null && !ob.args.isEmpty()
				? ob.args.stream().map(vb -> basicGetObject(vb)).collect(Collectors.toList()).toArray()
				: EMPTY_PARMS;

		Object x = extractObject(o, c, ob.methodName, parms);
		
		List<ValuePath> result = new ArrayList<>();
		if (x != null) {
			if (x instanceof Collection) {
				Collection<?> cx = (Collection<?>)x;
				for (Object xe : cx)
					result.add(p.add(ob.methodName, ob.aliasName, xe));
			} else {
				result.add(p.add(ob.methodName, ob.aliasName, x));
			} // TODO Map?
		}
		return result;
	}
	public List<ValuePath> computeValues(LogicalTestBlock lbt) {
		List<ValuePath> result = new ArrayList<>();
		if (lbt.bexp != null)
			for (BinaryTestBlock bt : lbt.bexp)
				result.addAll(computeValues(bt));
		else if (lbt.lexp != null)
			for (LogicalTestBlock lt :lbt.lexp)
				result.addAll(computeValues(lt));
		return result;
	}
	private List<ValuePath> computeValues(BinaryTestBlock bt) {
		List<ValuePath> result = new ArrayList<>();
		for (ValueBlock subvb : bt.values)
			result.addAll(computeValues(subvb));
		return result;
	}
	public boolean isDefined(ValueBlock vb) {
		boolean argOk = vb.ops == null || vb.ops.stream().map(op -> op.getValues()).flatMap(List::stream).allMatch(ivb -> hasMatchingPath(ivb.valuePath));
		boolean vbOk = isAliasDefined(vb.argName)
				|| Utils.isString(vb.argName, Constant.QUOTES)
				|| Utils.isNumber(vb.argName)
				|| isClassDefined(vb.argName);
		return argOk && vbOk;
	}
	private static Object extractObject(Object o, Class<?> c, String methodName, Object[] parms) {
		Object x = null;
		try {
			if (parms.length == 0 && Map.class.isAssignableFrom(c) && ((Map)o).containsKey(methodName))
				return ((Map)o).get(methodName);
			
			Method m = getMatchingMethod(c, methodName, parms);
			x = m != null ? m.invoke(o, parms) : c.getField(methodName).get(o);
		} catch (Exception e) { // FIXME should log the error with all details but on a single line
			throw new IllegalStateException("Error calling method '" + methodName + "' for class "+ c.getName() + " with parms " + Arrays.toString(parms), e);
		}

		return x;
	}
	private static Method getMatchingMethod(Class<?> c, String methodName, Object[] parms) {
		Method method = Arrays.stream(c.getMethods()).filter(m -> m.getName().equals(methodName) && m.getParameterCount() == parms.length).findFirst().orElse(null);
		if (method == null) {
			String getMethodName = "get" + Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
			method = Arrays.stream(c.getMethods()).filter(m -> m.getName().equals(getMethodName) && m.getParameterCount() == parms.length).findFirst().orElse(null);
		}
		if (method == null) {
			String getMethodName = "is" + Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
			method = Arrays.stream(c.getMethods()).filter(m -> m.getName().equals(getMethodName) && m.getParameterCount() == parms.length).findFirst().orElse(null);
		}
		return method;
	}
	// OR | AND
	public boolean computeTest(LogicalTestBlock test, Map<String,Integer> indexMap, Map<String,Integer> sizeMap) { // FIXME this method is too complex... 3 x 2 x 2
		if (test == null)
			return true;
		String op = test.op != null ? test.op : Constant.COMPARE_AND;
		if (test.bexp != null) {
			if (Constant.COMPARE_OR.equals(op)) {
				for (BinaryTestBlock tb : test.bexp)
					if (computeTest(tb, indexMap, sizeMap))
						return true;
				return false;
			} else if (Constant.COMPARE_AND.equals(op)) {
				for (BinaryTestBlock tb : test.bexp)
					if (!computeTest(tb, indexMap, sizeMap))
						return false;
				return true;
			}
		} else if (test.lexp != null) {
			if (Constant.COMPARE_OR.equals(op)) {
				for (LogicalTestBlock tb : test.lexp)
					if (computeTest(tb, indexMap, sizeMap))
						return true;
				return false;
			} else if (Constant.COMPARE_AND.equals(op)) {
				for (LogicalTestBlock tb : test.lexp)
					if (!computeTest(tb, indexMap, sizeMap))
						return false;
				return true;
			}
		}
		throw new IllegalStateException("invalid operator " + op + " for boolean.");
	}
	private boolean computeBooleanValue(ValueBlock vb) {
		ValuePath vp = getMatchingPath(vb.valuePath);
		Object o = vp.getObject();
		boolean b = o != null && Boolean.valueOf(o.toString());
		return Constant.NOT.equals(vb.unaryOp) ? !b : b;
	}
//	private double computeDoubleValue(ValueBlock vb) {
//		ValuePath vp = getMatchingPath(vb.valuePath);
//		Object o = vp.getObject();
//		return o != null ? Double.parseDouble(o.toString()) : 0d;
//	}
	// NOT EQUAL | EQUAL EQUAL | '>' | '>' EQUAL | '<' | '<' EQUAL | '<>'
	private boolean computeTest(BinaryTestBlock test, Map<String,Integer> indexMap, Map<String,Integer> sizeMap) {
		
		// Single value, must be boolean
		ValueBlock vb1 = test.values.get(0); // FIXME to be extended
		if (test.op == null)
			return computeBooleanValue(vb1);
		
		// One value starts with a not, so both considered boolean
		ValueBlock vb2 = test.values.get(1);
		if (Constant.NOT.equals(vb1.unaryOp) || Constant.NOT.equals(vb2.unaryOp)) {
			boolean b1 = computeBooleanValue(vb1);
			boolean b2 = computeBooleanValue(vb2);
			if (Constant.COMPARE_EQUAL.equals(test.op))
				return b1 == b2;
			else if (Constant.COMPARE_NOT_EQUAL1.equals(test.op) || Constant.COMPARE_NOT_EQUAL2.equals(test.op))
				return b1 != b2;
			else
				throw new IllegalStateException("invalid operator " + test.op + " for boolean.");
		}
		
		Object o1 = basicGetObject(vb1, indexMap, sizeMap);
		Object o2 = basicGetObject(vb2, indexMap, sizeMap);
		
//		ValuePath vp1 = getMatchingPath(vb1.valuePath);
//		Object o1 = vp1.getObject();
//		
//		
//		ValuePath vp2 = getMatchingPath(vb2.valuePath);
//		Object o2 = vp2.getObject();
		if (Constant.COMPARE_EQUAL.equals(test.op))
			return matcher.isSameObject(o1, o2);
		else if (Constant.COMPARE_NOT_EQUAL1.equals(test.op) || Constant.COMPARE_NOT_EQUAL2.equals(test.op))
			return !matcher.isSameObject(o1, o2);

		// Number comparison
//		double d1 = computeDoubleValue(vb1);
//		double d2 = computeDoubleValue(vb2);
		double d1 = ((Double)o1).doubleValue();
		double d2 = ((Double)o2).doubleValue();
		if (">".equals(test.op))
			return d1 > d2;
		else if (">=".equals(test.op))
			return d1 >= d2;
		else if ("<".equals(test.op))
			return d1 < d2;
		else if ("<=".equals(test.op))
			return d1 <= d2;

		// Unknown comparison
		throw new IllegalStateException("unknown binary operation " + test.op);

	}
	private Object basicGetObject(ValueBlock vb) {
		if (vb.indexOp != null)
			throw new IllegalStateException("Index operator not allowed here (as inside function calls)");
		ValuePath vp = getMatchingPath(vb.valuePath);
		Object o = vp != null ? vp.getObject() : null;
		if (vb.unaryOp == null)
			return o;
		if (Constant.NOT.equals(vb.unaryOp))
			return o == null || !Boolean.valueOf(o.toString());
		if (Constant.MINUS.equals(vb.unaryOp) && o == null)
			return 0d;
		int i = Integer.parseInt(o.toString());
		double d = Double.parseDouble(o.toString());
		o = (double)i == d 
			? Integer.valueOf(Constant.MINUS.equals(vb.unaryOp) ? -i : i) 
			: Double.valueOf(Constant.MINUS.equals(vb.unaryOp) ? -d : d);
		throw new IllegalStateException("Invalid operator"); // Should never happen unless grammar is updated!
	}
	private Object basicGetObject(ValueBlock vb, Map<String,Integer> indexMap, Map<String,Integer> sizeMap) {
		if (Constant.INDEX_OF.equals(vb.indexOp) && indexMap.containsKey(vb.getFinalAliasName()))
			return Integer.toString(indexMap.get(vb.getFinalAliasName()));
		else if (Constant.SIZE_OF.equals(vb.indexOp) && sizeMap.containsKey(vb.getFinalAliasName()))
			return Integer.toString(sizeMap.get(vb.getFinalAliasName()));
		else if (vb.indexOp == null)
			return basicGetObject(vb);
		throw new IllegalStateException("No valid index value for: " + vb.getFinalAliasName());
	}
}
