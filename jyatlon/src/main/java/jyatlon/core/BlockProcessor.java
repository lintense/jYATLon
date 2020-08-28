package jyatlon.core;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jyatlon.core.Block.BinaryTestBlock;
import jyatlon.core.Block.ControlBlock;
import jyatlon.core.Block.ControlOperator;
import jyatlon.core.Block.LogicalTestBlock;
import jyatlon.core.Block.OperationBlock;
import jyatlon.core.Block.PathBlock;
import jyatlon.core.Block.TextBlock;
import jyatlon.core.Block.ValueBlock;

/**
 * @author linte
 * SRP: A state less processor that generate a text file.

 * Add into doc:
 * ATTENTION : The ROOT ctx IS NOT available in absolute path...
 * Parameters...
 * 
 * TODO : Do we still need the statuses???
 * TODO : Processing of 'null'
 * TODO : Map key access and 'class' key
 * TODO : Collection
 * TODO : Revise doc for missing stuff
 * TODO : Write a Dev Guide?
 * TODO : Define Matcher interface and add it as an optional parm
 * TODO : {call} should be a control, not a value...
 */
public class BlockProcessor {

	private static final Object[] EMPTY_PARMS = new Object[] {};
//	private static final Class<?>[] EMPTY_PARM_CLASSES = new Class[] {};
	public static void merge(PathBlock pb, Writer w, Object r) {

		try {
			System.out.println("Starting merge process...");
			long t1 = System.currentTimeMillis();
			Matcher matcher = new Matcher();
			writeBlock(pb, w, new Combination(matcher), ValuePath.getRoot(r));
			long t2 = System.currentTimeMillis();
			System.out.println("Completed in " + (t2-t1) + " milliseconds.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void writeBlock(PathBlock pb, Writer w, Combination combination, ValuePath ctx) throws IOException {

		// Record of all created value from here!
//		Map<String,Status> statuses = new HashMap<>(); // FIXME do we need this?
		
		// This process must be here in order to throw errors
		List<ValuePath> pathParms = new ArrayList<>();
		pb.args.forEach(a->{
			Object o = combination.getObjectForName(a);
			if (o == null)
				throw new IllegalStateException("Should not happen: Parameter '" + a + "' not defined for section " + pb.pathname);
			pathParms.add(new ValuePath(o.getClass().getSimpleName(), a, o));
		});
		
		if (pb.getControlBlock() != null)
			writeBlock(pb.getControlBlock(), w, combination.callPath(ctx, pathParms));
	}
	private static void writeBlock(ControlBlock cb, Writer w, Combination combination) throws IOException {

		// Questions: - For each ALIAS: Is it a Collection? Is is empty?
//		List<String> outputs = writeBlock(cb.begin, combination, statuses);
		
		List<Combination> oldCombinations = computeCombinations(combination, new LinkedList<>(cb.begin.getExclusiveValues()));
		
		StringWriter sw = new StringWriter();
		// Always write blocks in order
		boolean first = true;
		for (Block b : cb.begin.blocks) {
			if (b.isText())
				writeBlock((TextBlock)b, sw);
			else if (b.isValue() && cb.aliasName.equals(((ValueBlock)b).valuePath.getAliasName())) {

				for (Combination oc : oldCombinations) {
					if (!first && cb.between != null)
						writeBlock(cb.between, oc, sw);
					else
						first = false;
					if (cb.before != null)
						writeBlock(cb.before, combination, sw);
					writeBlock((ValueBlock)b, sw, oc);
					if (cb.after != null)
						writeBlock(cb.after, combination, sw);
				}

			} else if (b.isValue()) {
				for (Combination oc : oldCombinations)
					writeBlock((ValueBlock)b, sw, oc);
			} else if (b.isControl())
				for (Combination oc : oldCombinations)
					writeBlock((ControlBlock)b, sw, oc);
			else
				throw new IllegalStateException("To be implemented"); // FIXME To be implemented
		}
		if (first && cb.empty != null) {// No alias block found, the whole begin block must be ignored entirely
			
			writeBlock(cb.empty, combination, w);
			
		} else
			w.append(sw.toString());
		
//		result.add(sw.toString());
//		sw.getBuffer().setLength(0);
		
		
		

		

//		if (!combination.isAliasDefined(cb.aliasName))
//			flushStrings(outputs, w); // Alias was never used, so ignore this control block.
//		else {
//			if (combination.isAliasNotEmpty(cb.aliasName)) { // This is a collection, so dress it up a bit.
//				if (cb.before != null)
//					flushStrings(writeBlock(cb.before, combination, statuses), w);
//				boolean first = true;
//				for (String out : outputs) {
//					if (!first && cb.between != null)
//						flushStrings(writeBlock(cb.between, combination, statuses), w);
//					else
//						first = false;
//					w.append(out);
//				}
//				if (cb.after != null)
//					flushStrings(writeBlock(cb.after, combination, statuses), w);	
//			} else if (combination.isAliasEmpty(cb.aliasName)) { // There was an attempt to write the alias so consider it as empty.
//				if (cb.empty != null)
//					flushStrings(writeBlock(cb.empty, combination, statuses), w);
//			} else { // not a Collection
//				flushStrings(outputs, w); // It is a defined object, keep output intact.
//			}
//		}
	}
//	private static void flushStrings(List<String> strings, Writer w) throws IOException {
//		for (String s : strings)
//			w.append(s);
//	}
	private static boolean isDefined(ValueBlock vb, Combination combination) {
		boolean argOk = vb.ops == null || vb.ops.stream().map(op -> op.getValues()).flatMap(List::stream).allMatch(ivb -> combination.getMatchingPath(ivb.valuePath) != null);
		boolean vbOk = combination.isAliasDefined(vb.argName)
				|| Utils.isString(vb.argName, BlockBuilder.QUOTES)
				|| combination.isClassDefined(vb.argName);
		return argOk && vbOk;
	}
	private static List<Combination> computeCombinations(Combination combination, LinkedList<ValueBlock> toProcess){
		List<Combination> oldCombinations = new ArrayList<>();
		List<Combination> newCombinations;
		oldCombinations.add(combination);
		
		// Compute all the possible values.
		int control = 0; // Ensure this process will end some day!
		while (!toProcess.isEmpty() && control < toProcess.size()) {
			ValueBlock vb = toProcess.removeFirst();
			
			// If ValueBlock starts by an alias that has not been processed yet then we must delay its processing to the end
			if (!oldCombinations.isEmpty() && isDefined(vb, oldCombinations.get(0))) {
				control = 0;
				newCombinations = new ArrayList<>();
				for (Combination oc : oldCombinations) {
					List<ValuePath> paths = computeValues(vb, oc);
					for (ValuePath pathCtx : paths) {
						Combination nc = oc.addPath(pathCtx);
						if (nc != null) // Add valid only
							newCombinations.add(nc);
					}
				}
				oldCombinations = newCombinations;
			} else {
				control++;
				toProcess.addLast(vb);
			}
		}
		// Some value cannot be processed. This should not happen.
		// Add the required validations inside BlockBuilding class to avoid an error here!
		if (toProcess.size() > 0)
			throw new IllegalStateException("Cannot process undefined value: " + toProcess.get(0).argName); // FIXME AVoid this at any cost. This should be validated in the BlockBuilder phase.

		return oldCombinations;
	}
//	private static List<String> writeBlock(ControlOperator co, Combination combination, Map<String,Status> statuses) throws IOException {
//
//		List<Combination> oldCombinations = computeCombinations(combination, new LinkedList<>(co.getExclusiveValues()), statuses);
//		
//		// Iterate all combinations
//		StringWriter sw = new StringWriter();
//		List<String> result = new ArrayList<>();
//		if (oldCombinations.isEmpty()) { // Give a chance to inner controls to run once
//			for (Block b : co.blocks) {
//				if (b.isText())
//					writeBlock((TextBlock)b, sw);
//				else if (b.isValue())
//					writeBlock((ValueBlock)b, sw, combination);
//				else if (b.isControl())
//					writeBlock((ControlBlock)b, sw, combination, statuses);
//				else
//					throw new IllegalStateException("To be implemented"); // FIXME To be implemented
//			}
//			result.add(sw.toString());
//			sw.getBuffer().setLength(0);
//		} else { // Iterate combinations
//			for (Block b : co.blocks) {
//				if (b.isText())
//					writeBlock((TextBlock)b, sw);
//				else if (b.isValue())
//					for (Combination oc : oldCombinations)
//						writeBlock((ValueBlock)b, sw, oc);
//				else if (b.isControl())
//					for (Combination oc : oldCombinations)
//						writeBlock((ControlBlock)b, sw, oc, statuses);
//				else
//					throw new IllegalStateException("To be implemented"); // FIXME To be implemented
//			}
//			result.add(sw.toString());
//			sw.getBuffer().setLength(0);
//		}
//		return result;
//	}
	private static void writeBlock(ControlOperator co, Combination combination, Writer w) throws IOException {
		for (Block b : co.blocks) {
			if (b.isText())
				writeBlock((TextBlock)b, w);
			else if (b.isValue()) {
				writeBlock((ValueBlock)b, w, combination);
			} else if (b.isControl())
				writeBlock((ControlBlock)b, w, combination);
			else
				throw new IllegalStateException("To be implemented"); // FIXME To be implemented
		}
	}
	private static void writeBlock(ValueBlock vb, Writer w, Combination combination) throws IOException {
		
		boolean test = computeTest(vb.test, combination);
		if (test) {
			ValuePath foundPath = combination.getMatchingPath(vb.valuePath);
			if (foundPath != null) {
				Object o = foundPath.getObject();
				if (vb.call == null)
					w.append(o.toString());
				else { // TODO called path must match actual object class or interface?
					PathBlock pb = vb.call.getBlockToCall();
//					writeBlock(vb.call.getBlockToCall(), w, new Combination(combination.matcher, combination.pathCtx.add(pb.path.path.getClassName(), pb.path.path.getAliasName(), o)));@
					writeBlock(vb.call.getBlockToCall(), w, combination, new ValuePath(pb.path.path.getClassName(), pb.path.path.getAliasName(), o));
				}
			} else if (foundPath == null)
				throw new IllegalStateException("Path not found for current value!"); // FIXME should this ever happen?
		}
	}

	// OR | AND
	private static boolean computeTest(LogicalTestBlock test, Combination combination) { // FIXME this method is too complex... 3 x 2 x 2
		if (test == null)
			return true;
		String op = test.op != null ? test.op : "&&";
		if (test.bexp != null) {
			if ("||".equals(op)) {
				for (BinaryTestBlock tb : test.bexp)
					if (computeTest(tb, combination))
						return true;
				return false;
			} else if ("&&".equals(op)) {
				for (BinaryTestBlock tb : test.bexp)
					if (!computeTest(tb, combination))
						return false;
				return true;
			}
		} else if (test.lexp != null) {
			if ("||".equals(op)) {
				for (LogicalTestBlock tb : test.lexp)
					if (computeTest(tb, combination))
						return true;
				return false;
			} else if ("&&".equals(op)) {
				for (LogicalTestBlock tb : test.lexp)
					if (!computeTest(tb, combination))
						return false;
				return true;
			}
		}
		throw new IllegalStateException("invalid operator " + op + " for boolean.");
	}
	private static boolean computeBooleanValue(ValueBlock vb, Combination combination) {
		ValuePath vp = combination.getMatchingPath(vb.valuePath);
		Object o = vp.getObject();
		boolean b = o != null && Boolean.valueOf(o.toString());
		return BlockBuilder.NOT.equals(vb.unaryOp) ? !b : b;
	}
	private static double computeDoubleValue(ValueBlock vb, Combination combination) {
		ValuePath vp = combination.getMatchingPath(vb.valuePath);
		Object o = vp.getObject();
		return o != null ? Double.parseDouble(o.toString()) : 0d;
	}
	// NOT EQUAL | EQUAL EQUAL | '>' | '>' EQUAL | '<' | '<' EQUAL | '<>'
	private static boolean computeTest(BinaryTestBlock test, Combination combination) {
		
		// Single value, must be boolean
		ValueBlock vb1 = test.values.get(0); // FIXME to be extended
		if (test.op == null)
			return computeBooleanValue(vb1, combination);
		
		// One value starts with a not, so both considered boolean
		ValueBlock vb2 = test.values.get(1);
		if (BlockBuilder.NOT.equals(vb1.unaryOp) || BlockBuilder.NOT.equals(vb2.unaryOp)) {
			boolean b1 = computeBooleanValue(vb1, combination);
			boolean b2 = computeBooleanValue(vb2, combination);
			if ("==".equals(test.op))
				return b1 == b2;
			else if ("!=".equals(test.op) || "<>".equals(test.op))
				return b1 != b2;
			else
				throw new IllegalStateException("invalid operator " + test.op + " for boolean.");
			
		}
		
		ValuePath vp1 = combination.getMatchingPath(vb1.valuePath);
		Object o1 = vp1.getObject();
		ValuePath vp2 = combination.getMatchingPath(vb2.valuePath);
		Object o2 = vp2.getObject();
		if ("==".equals(test.op))
			return combination.matcher.isSameObject(o1, o2);
		else if ("!=".equals(test.op) || "<>".equals(test.op))
			return !combination.matcher.isSameObject(o1, o2);

		// Number comparison
		double d1 = computeDoubleValue(vb1, combination);
		double d2 = computeDoubleValue(vb2, combination);
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
	private static void writeBlock(TextBlock tb, Writer w) throws IOException {
		w.append(tb.text);
	}

	private static List<ValuePath> computeValues(ValueBlock vb, Combination combination) {
		// Init value object
//		if (vb.unaryOp != null)
//			throw new IllegalStateException("unaryOp not yet implemented"); // FIXME To be implemented
		
		Object obj;
		if (Utils.isString(vb.argName, BlockBuilder.QUOTES)) // value is a String
			obj = Utils.unquote(vb.argName);
		else if (BlockBuilder.MINUS.equals(vb.unaryOp) || Utils.isNumber(vb.argName)) // value is a Number
			obj = BlockBuilder.MINUS.equals(vb.unaryOp) ? -Double.parseDouble(vb.argName) : Double.parseDouble(vb.argName);
		else
			obj = combination.getObjectForName(vb.argName); // Should be already defined
		
		// FIXME When the value argName IS the current alias then we
		// must find a way to access ALL the already defined alias values
		// This is because we need to evaluate the further operations...
		
		// Maybe it is not a problem since empty list and null object are both considered empty and as such are treated the same way.
		if (obj == null) {
//			if (!statuses.containsKey(vb.aliasName))
//				statuses.put(vb.aliasName, new Status(vb.aliasName)); // At least we have tried...
			return Collections.emptyList();
		}
		
		// Init processing
		final List<ValuePath> toProcess = new ArrayList<ValuePath>();
		if (obj instanceof Collection)
			((Collection<?>) obj).forEach(o->toProcess.add(new ValuePath(vb.argName, vb.aliasName, o)));
		else if (obj != null)
			toProcess.add(new ValuePath(vb.argName, vb.aliasName, obj));
		
		List<ValuePath> result = toProcess;
//		if (vb.aliasName != null)
//			statuses.put(vb.aliasName, new Status(vb.aliasName, obj instanceof Collection, result.isEmpty()));
		
		// Process operators
//		for (OperationBlock op : vb.ops) {
//			result = result.stream().map(p->extractObject(op,p)).filter(p->p != null).collect(Collectors.toList());
//			if (op.aliasName != null)
//				statuses.put(op.aliasName, new Status(op.aliasName, result instanceof Collection, result.isEmpty()));
//		}
		for (OperationBlock op : vb.ops) {
			result = result.stream().map(p->extractPaths(op, p, combination)).flatMap(List::stream).collect(Collectors.toList());
		}
		
		// Compute sub values inside test block
		if (vb.test != null) 
			result.addAll(computeValues(vb.test, combination));
		
		return result;
	}
	private static List<ValuePath> computeValues(LogicalTestBlock lbt, Combination combination) {
		List<ValuePath> result = new ArrayList<>();
		if (lbt.bexp != null)
			for (BinaryTestBlock bt : lbt.bexp)
				result.addAll(computeValues(bt, combination));
		else if (lbt.lexp != null)
			for (LogicalTestBlock lt :lbt.lexp)
				result.addAll(computeValues(lt, combination));
		return result;
	}
	private static List<ValuePath> computeValues(BinaryTestBlock bt, Combination combination) {
		List<ValuePath> result = new ArrayList<>();
		for (ValueBlock subvb : bt.values)
			result.addAll(computeValues(subvb, combination));
		return result;
	}
	private static List<ValuePath> extractPaths(OperationBlock ob, ValuePath p, Combination combination){
		Object o = p.getObject();
		Class<?> c = o.getClass();
		
		Object[] parms = ob.args != null && !ob.args.isEmpty()
				? ob.args.stream().map(vb -> combination.getMatchingPath(vb.valuePath).getObject()).collect(Collectors.toList()).toArray()
				: EMPTY_PARMS;

		Object x = extractObject(o, c, ob.methodName, parms);
		
		List<ValuePath> result = new ArrayList<>();
		if (x != null) {
			if (x instanceof Collection) {
				Collection<?> cx = (Collection<?>)x;
				for (Object xe : cx)
					result.add(p.add(ob.methodName, ob.aliasName, xe));
//				if (ob.aliasName != null)
//					statuses.put(ob.aliasName, new Status(ob.aliasName, true, cx.isEmpty()));
			} else {
				result.add(p.add(ob.methodName, ob.aliasName, x));
//				if (ob.aliasName != null)
//					statuses.put(ob.aliasName, new Status(ob.aliasName, false, false));
			} // TODO Map?
		}
		return result;
	}
	private static Method getMatchingMethod(Class<?> c, String methodName, Object[] parms) {
		Method method = Arrays.stream(c.getDeclaredMethods()).filter(m -> m.getName().equals(methodName) && m.getParameterCount() == parms.length).findFirst().orElse(null);
		if (method == null) {
			String getMethodName = "get" + Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
			method = Arrays.stream(c.getDeclaredMethods()).filter(m -> m.getName().equals(getMethodName) && m.getParameterCount() == parms.length).findFirst().orElse(null);
		}
		return method;
	}
	private static Object extractObject(Object o, Class<?> c, String methodName, Object[] parms) {
		Object x = null;
		try {
			Method m = getMatchingMethod(c, methodName, parms);
			x = m != null ? m.invoke(o, parms) : c.getField(methodName).get(o);
		} catch (Exception e) { // FIXME should log the error with all details but on a single line
			throw new IllegalStateException("Error calling method '" + methodName + "' for class "+ c.getName() + " with parms " + Arrays.toString(parms), e);
		}

		return x;
	}
//	public static class Status {
//		public final String aliasName;
//		public final boolean isDefined;
//		public final boolean isCollection;
//		public final boolean isEmpty;
////		public final Object value;
//		
//		public Status(String aliasName) { // This ALIAS exists but has not yet been defined
//			this.aliasName = aliasName;
//			this.isDefined = false;
//			this.isCollection = false;
//			this.isEmpty = false;
////			this.value = null;
//		}
//		public Status(String aliasName, boolean isCollection, boolean isEmpty) {
//			super();
//			this.aliasName = aliasName;
//			this.isDefined = true;
//			this.isCollection = isCollection;
//			this.isEmpty = isEmpty;
////			this.value = value;
//		}
//		
//		
//	}
	private static class Combination {
		
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
			return new Combination(matcher, pathCtx != null ? pathCtx.add(vp) : vp, pathParms);
		}
		private Combination(Matcher matcher, ValuePath pathCtx, List<ValuePath> pathParms) {
			this.pathCtx = pathCtx;
			this.matcher = matcher;
			
			if (paths.isEmpty()) {
				paths.add(pathCtx);
				if (pathCtx.isRoot())
					this.combinedClasses.put(pathCtx.classes[0], pathCtx.objects[0]);
			}
			initPath(pathCtx);
			paths.addAll(pathParms);
			pathParms.forEach(p->initPath(p));
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
			result.paths.add(vp);
			return result.initPath(vp) ? result : null;
		}
		private boolean initPath(ValuePath vp) {
//			if (paths.isEmpty()) {
//				paths.add(pathCtx);
//				if (BlockBuilder.ROOT.equals(pathCtx.classes[0]))
//					this.combinedClasses.put(pathCtx.classes[0], pathCtx.objects[0]);
//			}
			for (int i = 0; i < vp.aliases.length; i++) {
				if (vp.aliases[i] != null) {
					Object previous = combinedAliases.put(vp.aliases[i], vp.objects[i]);
					if (previous != null && !matcher.isSameObject(previous, vp.objects[i]))
						return false;
				}
			}
			for (int i = 0; i < vp.classes.length; i++)
				if (vp.objects[i].getClass().getName().endsWith(vp.classes[i])) // Must be the actual class name (for now)
					combinedClasses.put(vp.classes[i], vp.objects[i]); // Keep only the most recent object
			return true;
		}
		ValuePath getMatchingPath(ValuePath current) { // path=found, null=not found
			// Find which path element is matching the current value
			// TODO seem a bit restrictive. i.e.Null alias should match
			for (ValuePath path : paths)
				if (Arrays.equals(current.classes, path.classes) && Arrays.equals(current.aliases, path.aliases))
					return path;
			return null;
		}
		Object getObjectForName(String name){
			Object result = combinedAliases.get(name);
			return result == null ? combinedClasses.get(name) : result;
//			return paths.stream().map(vp -> vp.getObjectForName(name, null)).filter(p -> p != null).collect(Collectors.toList());
		}
		boolean isAliasDefined(String aliasName) {
			return combinedAliases.containsKey(aliasName);
		}
		boolean isClassDefined(String className) {
			return combinedClasses.containsKey(className);
		}
//		boolean isAliasEmpty(String name) {
//			Object o = combinedAliases.getOrDefault(name, null);
//			return o != null && Collection.class.isAssignableFrom(o.getClass()) && ((Collection<?>)o).isEmpty();
//		}
//		boolean isAliasNotEmpty(String name) {
//			Object o = combinedAliases.getOrDefault(name, null);
//			return o != null && Collection.class.isAssignableFrom(o.getClass()) && !((Collection<?>)o).isEmpty();
//		}
	}
}
