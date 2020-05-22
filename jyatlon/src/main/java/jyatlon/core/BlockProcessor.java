package jyatlon.core;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
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

import jyatlon.core.Block.ControlBlock;
import jyatlon.core.Block.ControlOperator;
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
			writeBlock(pb, w, r, matcher);
			long t2 = System.currentTimeMillis();
			System.out.println("Completed in " + (t2-t1) + " milliseconds.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void writeBlock(PathBlock pb, Writer w, Object r, Matcher matcher) throws IOException {
		
		if (pb.controlBlock != null)
			writeBlock(pb.controlBlock, w, r, matcher);
	}
	private static void writeBlock(ControlBlock cb, Writer w, Object r, Matcher matcher) throws IOException {
		
		// For this control block, iterate through all the values and their ops
		// for each op, collect all the result and their associated alias path
		
		// Questions: - For each ALIAS: Is it a Collection? Is is empty?
		Map<String,Status> statuses = new HashMap<String,Status>();
		List<String> outputs = writeBlock(cb.begin, r, matcher, statuses);
		
		// My alias should have been defined here!
		// CASE 1 - Something has been written but the alias was not present // This control block should be ignored... keep the output stuff
		// CASE 2 - Alias was present but never defined // Consider it as empty!
		Status s = statuses.getOrDefault(cb.aliasName, null);
		if (s == null)
			flushStrings(outputs, w); // Alias was never used, so ignore this control block.
		else {
			if (!s.isDefined || (s.isDefined && s.isEmpty)) // There was an attempt to write the alias so consider it as empty.
				if (cb.empty != null)
					flushStrings(writeBlock(cb.empty, r, matcher, statuses), w);
			else if (s.isDefined && s.isCollection) { // This is a collection, so dress it up a bit.
				if (cb.before != null)
					flushStrings(writeBlock(cb.before, r, matcher, statuses), w);
				boolean first = true;
				for (String out : outputs) {
					if (!first && cb.between != null)
						flushStrings(writeBlock(cb.between, r, matcher, statuses), w);
					else
						first = false;
					w.append(out);
				}
				if (cb.end != null)
					flushStrings(writeBlock(cb.end, r, matcher, statuses), w);	
			} else {
				flushStrings(outputs, w); // It is a defined object, keep output intact.
			}
		}
	}
	private static void flushStrings(List<String> strings, Writer w) throws IOException {
		for (String s : strings)
			w.append(s);
	}
	private static boolean isDefined(ValueBlock vb, Set<String> alreadyDefinedAliases) {
		return BlockBuilder.ROOT.equals(vb.argName) || alreadyDefinedAliases.contains(vb.argName);
	}
	private static List<String> writeBlock(ControlOperator co, Object r, Matcher matcher, Map<String,Status> statuses) throws IOException {
		
//		paths.forEach(p->System.out.println(Arrays.toString(p.aliases)));

		// Iterate begin control
		int control = 0; // Ensure this process will end some day!
		List<List<ValuePath>> matrix = new ArrayList<List<ValuePath>>(); // One element per ValueBlock
		LinkedList<ValueBlock> toProcess = new LinkedList(co.getValues());
		while (!toProcess.isEmpty() && control < toProcess.size()) { // FIXME Try to avoid this kind of processing here!!!
			ValueBlock vb = toProcess.removeFirst();
			
			// FIXME It is logical we want to process all the defining value first.
			// This is because we do not want to skip these values inside the computeValues() process
			// Speaking of which, should we have a collection of all found aliases so we can process them all at that place?
			
			// If ValueBlock starts by an alias that has not been processed yet then we must delay its processing to the end
			
			if (isDefined(vb, statuses.keySet())) {
				control = 0;
				matrix.add(computeValues(vb, r, statuses));
			} else {
				control++;
				toProcess.addLast(vb);
			}
			
			// For now only use the root object
			
		}
		// 
		if (control > 0) // This is bad... Maybe we could keep it but at least ensure it will terminate && without crash!!!
			throw new IllegalStateException("Cannot process the value"); // FIXME AVoid this at any cost. This should be validated in the BlockBuilder phase.
		
		
		
		// The whole control block must be repeated in case there are multiple value path...
		// Here we must find which combination of values must be shown simultaneously
		// All possible combinations must be shown
		// When the value are independent (not sharing aliases) then its a product,
		
		// Each matrix entry is (a priori) multiplied with each other
		// 1,2 x 3,4 = 13, 14, 23, 24
		List<List<ValuePath>> m = new ArrayList<List<ValuePath>>();
		m.add(new ArrayList<ValuePath>());
		for (List<ValuePath> l : matrix) {
			List<List<ValuePath>>  newm = new ArrayList<List<ValuePath>>();
			for (ValuePath p : l) {
				for (List<ValuePath> mm : m) {
					List<ValuePath> mmm = new ArrayList<ValuePath>(mm);
					mmm.add(p);
					newm.add(mmm);
				}
			}
			m = newm;
		}
		
		// Iterate all combinations
		StringWriter sw = new StringWriter();
		List<String> result = new ArrayList();
		for (List<ValuePath> mm : m) {
			
			// When they share aliases, they must be compatible
			mm.forEach(p->System.out.println(Arrays.toString(p.aliases)));

			// In case of collisions, check compatibility
			// This means that each alias must have the same value (must be the same object)
			Map<String,Object> aliasObjects = new HashMap<String,Object>();
			boolean incompatible = mm.stream().anyMatch(p->!canAddAliasObjectToMap(aliasObjects, p, matcher));
		
			if (!incompatible) {
				
				for (Block b : co.blocks) {
					
					if (b.isText())
						writeBlock((TextBlock)b, sw, r, matcher);
					else if (b.isValue())
						writeBlock((ValueBlock)b, sw, mm, matcher);
					else if (b.isControl())
						writeBlock((ControlBlock)b, sw, r, matcher);
					else
						throw new IllegalStateException("To be implemented"); // FIXME To be implemented
				}
				result.add(sw.toString());
				sw.getBuffer().setLength(0);
			}
		}
		return result;
	}
	private static boolean canAddAliasObjectToMap(Map<String, Object> aliasObjects, ValuePath p, Matcher matcher) {
		for (int i = 0; i < p.aliases.length; i++) {
			if (p.aliases[i] != null) {
				Object newObj = p.objects[i];
				Object previousObj = aliasObjects.put(p.aliases[i], newObj);
				if (previousObj != null && !matcher.isSameObject(newObj, previousObj))
					return false;
			}
		}
		return true;
	}
	private static void writeBlock(ValueBlock vb, Writer w, List<ValuePath> paths, Matcher matcher) throws IOException {
		// Current value path
		ValuePath current = vb.getPath();
		
		// Find which path element is matching the current value
		boolean found = false;
		Object o = null;
		FIND_PATH: for (ValuePath path : paths) {
			if (Arrays.equals(current.classes, path.classes) && Arrays.equals(current.aliases, path.aliases)) { // FIXME use stream
				found = true;
				o = path.getObject();
				break FIND_PATH;
			}
		}
		if (found && o != null) {
			if (vb.call == null)
				w.append(o.toString());
			else // TODO called path must match actual object class or interface
				writeBlock(vb.call.getBlockToCall(), w, o, matcher);
		} else if (!found)
			throw new IllegalStateException("Path not found for current value!"); // FIXME should this ever happen?
	}
	private static void writeBlock(TextBlock tb, Writer w, Object r, Matcher matcher) throws IOException {
		w.append(tb.text);
	}
	public static List<ValuePath> computeValues(ValueBlock vb, Object root, Map<String,Status> statuses) {
		// Init value object
		Object obj;
		if (!vb.argName.equals(BlockBuilder.ROOT))
			throw new IllegalStateException("To be implemented"); // FIXME To be implemented
		else
			obj = root;
		
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
		
		// Process operators
		List<ValuePath> result = toProcess;
		for (OperationBlock op : vb.ops)
			result = result.stream().map(p->extractObject(op,p)).filter(p->p != null).collect(Collectors.toList());

		statuses.put(vb.aliasName, new Status(vb.aliasName, obj instanceof Collection, result.isEmpty()));
		
		return result;
	}
	public static ValuePath extractObject(OperationBlock ob, ValuePath p) {
		Object x = null;
		Object o = p.getObject();
		Class<?> c = o.getClass();

		try {
			try {
				Method m = c.getDeclaredMethod(ob.methodName);
				Collection.class.isAssignableFrom(m.getGenericReturnType().getClass());
				x = m.invoke(o);
			} catch (NoSuchMethodException e) {
				String m2 = "get" + Character.toUpperCase(ob.methodName.charAt(0)) + ob.methodName.substring(1);
				Method m = c.getDeclaredMethod(m2);
				x = m.invoke(o);
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
