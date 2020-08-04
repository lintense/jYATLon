package jyatlon.core;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
 *
 */
public class BlockProcessor {

	public static void merge(PathBlock pb, Writer w, Object r) {

		try {
			System.out.println("Starting merge process...");
			long t1 = System.currentTimeMillis();
			Matcher matcher = new Matcher();
			writeBlock(pb, w, matcher, new ValuePath(BlockBuilder.ROOT, null, r));
			long t2 = System.currentTimeMillis();
			System.out.println("Completed in " + (t2-t1) + " milliseconds.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void writeBlock(PathBlock pb, Writer w, Matcher matcher, ValuePath vp) throws IOException {

		// Record of all created value from here!
		Map<String,Status> statuses = new HashMap<>();
		if (pb.controlBlock != null)
			writeBlock(pb.controlBlock, w, matcher, vp, statuses);
	}
	private static void writeBlock(ControlBlock cb, Writer w, Matcher matcher, ValuePath vp, Map<String,Status> statuses) throws IOException {
		
//		Object r = vp.getObject();
		// For this control block, iterate through all the values and their ops
		// for each op, collect all the result and their associated alias path
		
		// Questions: - For each ALIAS: Is it a Collection? Is is empty?
		List<String> outputs = writeBlock(cb.begin, matcher, vp, statuses);
		
		// My alias should have been defined here!
		// CASE 1 - Something has been written but the alias was not present // This control block should be ignored... keep the output stuff
		// CASE 2 - Alias was present but never defined // Consider it as empty!
		Status s = statuses.getOrDefault(cb.aliasName, null);
		if (s == null)
			flushStrings(outputs, w); // Alias was never used, so ignore this control block.
		else {
			if (!s.isDefined || (s.isDefined && s.isEmpty)) { // There was an attempt to write the alias so consider it as empty.
				if (cb.empty != null)
					flushStrings(writeBlock(cb.empty, matcher, vp, statuses), w);
			} else if (s.isDefined && s.isCollection) { // This is a collection, so dress it up a bit.
				if (cb.before != null)
					flushStrings(writeBlock(cb.before, matcher, vp, statuses), w);
				boolean first = true;
				for (String out : outputs) {
					if (!first && cb.between != null)
						flushStrings(writeBlock(cb.between, matcher, vp, statuses), w);
					else
						first = false;
					w.append(out);
				}
				if (cb.end != null)
					flushStrings(writeBlock(cb.end, matcher, vp, statuses), w);	
			} else {
				flushStrings(outputs, w); // It is a defined object, keep output intact.
			}
		}
	}
	private static void flushStrings(List<String> strings, Writer w) throws IOException {
		for (String s : strings)
			w.append(s);
	}
	
//	if (!(BlockBuilder.ROOT.equals(vb.argName)
//	|| (pb.path != null && pb.path.path.hasClass(vb.argName))
//	|| Utils.isString(vb.argName)
//	|| aliasForPathBlock.get(pb).contains(vb.argName))) // TODO should use Sets instead of a Lists...

	private static boolean isDefined(ValueBlock vb, ValuePath vp, Set<String> alreadyDefinedAliases) {
		return BlockBuilder.ROOT.equals(vb.argName) 
				|| alreadyDefinedAliases.contains(vb.argName)
				|| Utils.isString(vb.argName)
				|| vp.hasClassName(vb.argName)
				|| vp.hasAlias(vb.aliasName);
	}

	private static List<String> writeBlock(ControlOperator co, Matcher matcher, ValuePath vp, Map<String,Status> statuses) throws IOException {
		
//		Object r = vp.getObject();
//		paths.forEach(p->System.out.println(Arrays.toString(p.aliases)));

		// Iterate begin control
		int control = 0; // Ensure this process will end some day!
		List<List<ValuePath>> allValueCtx = new ArrayList<List<ValuePath>>(); // One element per ValueBlock
		LinkedList<ValueBlock> toProcess = new LinkedList<>(co.getValues());
		while (!toProcess.isEmpty() && control < toProcess.size()) { // FIXME Try to avoid this kind of processing here!!!
			ValueBlock vb = toProcess.removeFirst();
			
			// FIXME It is logical we want to process all the defining value first.
			// This is because we do not want to skip these values inside the computeValues() process
			// Speaking of which, should we have a collection of all found aliases so we can process them all at that place?
			
			// If ValueBlock starts by an alias that has not been processed yet then we must delay its processing to the end
			
			if (isDefined(vb, vp, statuses.keySet())) {
				control = 0;
				allValueCtx.add(computeValues(vb, vp, statuses));
			} else {
				control++;
				toProcess.addLast(vb);
			}
			
			// For now only use the root object
			
		}
		// 
		if (toProcess.size() > 0) // This is bad... Maybe we could keep it but at least ensure it will terminate && without crash!!!
			throw new IllegalStateException("Cannot process undefined value: " + toProcess.get(0).argName); // FIXME AVoid this at any cost. This should be validated in the BlockBuilder phase.
		
		
		
		// The whole control block must be repeated in case there are multiple value path...
		// Here we must find which combination of values must be shown simultaneously
		// All possible combinations must be shown
		// When the value are independent (not sharing aliases) then its a product,
		
		// Each matrix entry is (a priori) multiplied with each other
		// 1,2 x 3,4 = 13, 14, 23, 24
		
		// Wrong: should be 1,2 x 3,4 = 1,2,3,4
		// When intersecting: 1,2 x 2,3 = 1,2,3
		
		// Combine a lists
		// When do we need to dedup the lists? In case of collections!
		// When to scrap a list? When the aliases are not matching!
		
		List<List<ValuePath>> allCombinations = new ArrayList<>();
		
		// For now, its seems to exist only one combination...
		Map<String,Object> combinedAliases = new HashMap<>();
		List<ValuePath> combinedCtx = new ArrayList<>(); 
		LOOP: for (List<ValuePath> valueCtx : allValueCtx) {
			
			// Validate new valueCtx combination
			for (ValuePath pathCtx : valueCtx) {
				for (int i = 0; i < pathCtx.aliases.length; i++) {
					if (pathCtx.aliases[i] != null) {
						Object previous = combinedAliases.put(pathCtx.aliases[i], pathCtx.objects[i]);
						if (previous != null && !matcher.isSameObject(previous, pathCtx.objects[i])) {
							// not a valid combination
							combinedAliases.clear();
							combinedCtx.clear();
							break LOOP;
						}
					
					}
				}
			}
			combinedCtx.addAll(valueCtx);
		}

		if (!combinedCtx.isEmpty())
			allCombinations.add(combinedCtx);
		
		
		
		
//		// Previous version - Bad!
//		List<List<ValuePath>> m = new ArrayList<List<ValuePath>>();
//		m.add(new ArrayList<ValuePath>());
//		for (List<ValuePath> l : allValueCtx) {
//			List<List<ValuePath>>  newm = new ArrayList<List<ValuePath>>();
//			for (ValuePath p : l) {
//				for (List<ValuePath> mm : m) {
//					List<ValuePath> mmm = new ArrayList<ValuePath>(mm);
//					mmm.add(p);
//					newm.add(mmm);
//				}
//			}
//			m = newm;
//		}
		
		// Iterate all combinations
		StringWriter sw = new StringWriter();
		List<String> result = new ArrayList<>();
		
		if (allCombinations.isEmpty()) { // Give a chance to inner controls to run once
			for (Block b : co.blocks) {
				
				if (b.isText())
					writeBlock((TextBlock)b, sw);
				else if (b.isValue())
					writeBlock((ValueBlock)b, sw, Collections.emptyList(), matcher, vp);
				else if (b.isControl())
					writeBlock((ControlBlock)b, sw, matcher, vp, statuses);
				else
					throw new IllegalStateException("To be implemented"); // FIXME To be implemented
			}
			result.add(sw.toString());
			sw.getBuffer().setLength(0);
		} else { // Iterate combinations
			for (List<ValuePath> mm : allCombinations) {
			
			// When they share aliases, they must be compatible
//			mm.forEach(p->System.out.println(Arrays.toString(p.aliases)));

			// In case of collisions, check compatibility
			// This means that each alias must have the same value (must be the same object)
//			Map<String,Object> aliasObjects = new HashMap<String,Object>();
//			boolean incompatible = mm.stream().anyMatch(p->!canAddAliasObjectToMap(aliasObjects, p, matcher));
//		
//			if (!incompatible) {
				
				for (Block b : co.blocks) {
					
					if (b.isText())
						writeBlock((TextBlock)b, sw);
					else if (b.isValue())
						writeBlock((ValueBlock)b, sw, mm, matcher, vp);
					else if (b.isControl())
						writeBlock((ControlBlock)b, sw, matcher, vp, statuses);
					else
						throw new IllegalStateException("To be implemented"); // FIXME To be implemented
				}
				result.add(sw.toString());
				sw.getBuffer().setLength(0);
			}
		}
		return result;
	}
//	private static boolean canAddAliasObjectToMap(Map<String, Object> aliasObjects, ValuePath p, Matcher matcher) {
//		for (int i = 0; i < p.aliases.length; i++) {
//			if (p.aliases[i] != null) {
//				Object newObj = p.objects[i];
//				Object previousObj = aliasObjects.put(p.aliases[i], newObj);
//				if (previousObj != null && !matcher.isSameObject(newObj, previousObj))
//					return false;
//			}
//		}
//		return true;
//	}
	private static void writeBlock(ValueBlock vb, Writer w, List<ValuePath> paths, Matcher matcher, ValuePath vp) throws IOException {
		// Current value path
//		ValuePath current = vb.getPath();
		
		// Find which path element is matching the current value
//		boolean found = false;
//		Object o = null;
//		FIND_PATH: for (ValuePath path : paths) {
//			if (Arrays.equals(current.classes, path.classes) && Arrays.equals(current.aliases, path.aliases)) { // FIXME use stream
//				found = true;
//				o = path.getObject();
//				break FIND_PATH;
//			}
//		}
		
		boolean test = computeTest(vb.test, paths, matcher);
		if (test) {
			ValuePath foundPath = getMatchingPath(vb.valuePath, paths);
			if (foundPath != null) {
				Object o = foundPath.getObject();
				if (vb.call == null)
					w.append(o.toString());
				else {// TODO called path must match actual object class or interface
					PathBlock pb = vb.call.getBlockToCall();
					writeBlock(vb.call.getBlockToCall(), w, matcher, vp.add(pb.path.path.getClassName(), pb.path.path.getAlias(), o));
				}
			} else if (foundPath == null)
				throw new IllegalStateException("Path not found for current value!"); // FIXME should this ever happen?
		}
	}
	private static ValuePath getMatchingPath(ValuePath current, List<ValuePath> paths) { // path=found, null=not found
		// Find which path element is matching the current value
		// TODO seem a bit restrictive. i.e.Null alias should match
		for (ValuePath path : paths)
			if (Arrays.equals(current.classes, path.classes) && Arrays.equals(current.aliases, path.aliases)) // FIXME use stream
				return path;
		return null;
	}
	// OR | AND
	private static boolean computeTest(LogicalTestBlock test, List<ValuePath> paths, Matcher matcher) {
		if (test == null)
			return true;
		else if ("||".equals(test.op)) {
			for (BinaryTestBlock tb : test.exp)
				if (computeTest(tb, paths, matcher))
					return true;
			return false;
		} else { // "&&" is assumed here
			for (BinaryTestBlock tb : test.exp)
				if (!computeTest(tb, paths, matcher))
					return false;
			return true;
		}
	}
	// NOT EQUAL | EQUAL EQUAL | '>' | '>' EQUAL | '<' | '<' EQUAL | '<>'
	private static boolean computeTest(BinaryTestBlock test, List<ValuePath> paths, Matcher matcher) {
		ValueBlock vb1 = test.values.get(0); // FIXME to be extended
		ValuePath vp1 = getMatchingPath(vb1.valuePath, paths);
//		if (vp1 == null)
//			throw new IllegalStateException("Left value not found"); // Should not happen
		ValueBlock vb2 = test.values.get(1);
		ValuePath vp2 = getMatchingPath(vb2.valuePath, paths);
//		if (vp2 == null)
//			throw new IllegalStateException("Right value not found"); // Should not happen
		
		if ("==".equals(test.op))
			return matcher.isSameObject(vp1.getObject(), vp2.getObject());
		else if ("!=".equals(test.op) || "<>".equals(test.op))
			return !matcher.isSameObject(vp1.getObject(), vp2.getObject());
		throw new IllegalStateException("Binary operator " + test.op + " is not yet implemented");
	}
	private static void writeBlock(TextBlock tb, Writer w) throws IOException {
		w.append(tb.text);
	}
	private static List<ValuePath> computeValues(ValueBlock vb, ValuePath vp, Map<String,Status> statuses) {
		// Init value object
		if (vb.unaryOp != null)
			throw new IllegalStateException("unaryOp not yet implemented"); // FIXME To be implemented
		
		Object obj;
		if (Utils.isString(vb.argName))
			obj = vb.argName;
		else
			obj = vp.getObject(vb.argName);
		
		// FIXME When the value argName IS the current alias then we
		// must find a way to access ALL the already defined alias values
		// This is because we need to evaluate the further operations...
		
		
		// Maybe it is not a problem since empty list and null object are both considered empty and as such are treated the same way.
		if (obj == null) {
			if (!statuses.containsKey(vb.aliasName))
				statuses.put(vb.aliasName, new Status(vb.aliasName)); // At least we have tried...
			return Collections.emptyList();
		}
		
		// Init processing
		final List<ValuePath> toProcess = new ArrayList<ValuePath>();
		if (obj instanceof Collection)
			((Collection<?>) obj).forEach(o->toProcess.add(new ValuePath(vb.argName, vb.aliasName, o)));
		else if (obj != null)
			toProcess.add(new ValuePath(vb.argName, vb.aliasName, obj));
		
		List<ValuePath> result = toProcess;
		if (vb.aliasName != null)
			statuses.put(vb.aliasName, new Status(vb.aliasName, obj instanceof Collection, result.isEmpty()));
		
		// Process operators
		for (OperationBlock op : vb.ops) {
			result = result.stream().map(p->extractObject(op,p)).filter(p->p != null).collect(Collectors.toList());
			if (op.aliasName != null)
				statuses.put(op.aliasName, new Status(op.aliasName, result instanceof Collection, result.isEmpty()));
		}

		// Compute sub values inside test block
		if (vb.test != null)
			for (BinaryTestBlock bt : vb.test.exp)
				for (ValueBlock subvb : bt.values)
					result.addAll(computeValues(subvb, vp, statuses));
		
		return result;
	}
	private static ValuePath extractObject(OperationBlock ob, ValuePath p) {
		Object x = null;
		Object o = p.getObject();
		Class<?> c = o.getClass();

		try {
			try {
				Method m = c.getDeclaredMethod(ob.methodName);
				Collection.class.isAssignableFrom(m.getGenericReturnType().getClass());
				x = m.invoke(o);
			} catch (NoSuchMethodException e) {
				try {
					Field f = c.getField(ob.methodName);
					x = f.get(o);
				} catch (NoSuchFieldException e1) {
					String m2 = "get" + Character.toUpperCase(ob.methodName.charAt(0)) + ob.methodName.substring(1);
					Method m = c.getDeclaredMethod(m2);
					x = m.invoke(o);
				} 
			}
		} catch (NoSuchMethodException e) { // FIXME should log the error with all details but on a single line
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return x != null ? p.add(ob.methodName, ob.aliasName, x) : null;
	}
	public static class Status {
		public final String aliasName;
		public final boolean isDefined;
		public final boolean isCollection;
		public final boolean isEmpty;
//		public final Object value;
		
		public Status(String aliasName) { // This ALIAS exists but has not yet been defined
			this.aliasName = aliasName;
			this.isDefined = false;
			this.isCollection = false;
			this.isEmpty = false;
//			this.value = null;
		}
		public Status(String aliasName, boolean isCollection, boolean isEmpty) {
			super();
			this.aliasName = aliasName;
			this.isDefined = true;
			this.isCollection = isCollection;
			this.isEmpty = isEmpty;
//			this.value = value;
		}
		
		
	}
}
