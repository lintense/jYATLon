package jyatlon.core;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jyatlon.core.Block.ControlBlock;
import jyatlon.core.Block.OperationBlock;
import jyatlon.core.Block.PathBlock;
import jyatlon.core.Block.TextBlock;
import jyatlon.core.Block.ValueBlock;
import jyatlon.core.Path.ValuePath;

/**
 * @author linte
 * SRP: A state less processor that generate a text file.
 *
 */
public class BlockProcessor {

	public static void merge(PathBlock pb, Writer w, Object r) {

		try {
			writeBlock(pb, w, r);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void writeBlock(PathBlock pb, Writer w, Object r) throws IOException {
		
		if (pb.controlBlock != null)
			writeBlock(pb.controlBlock, w, r);
	}
	private static void writeBlock(ControlBlock cb, Writer w, Object r) throws IOException {
		
		
		// For this control block, iterate through all the values and their ops
		// for each op, collect all the result and their associated alias path
		
		List<List<Path>> matrix = new ArrayList<List<Path>>(); // One element per ValueBlock
		for (ValueBlock vb : cb.getValues()) {
			// For now only use the root object
			matrix.add(computeValues(vb, r));
		}
		
//		paths.forEach(p->System.out.println(Arrays.toString(p.aliases)));
		
		
		
		// The whole control block must be repeated in case there are multiple value path...
		// Here we must find which combination of values must be shown simultaneously
		// All possible combinations must be shown
		// When the value are independent (not sharing aliases) then its a product,
		
		// Each matrix entry is (a priori) multiplied with each other
		// 1,2 x 3,4 = 13, 14, 23, 24
		List<List<Path>> m = new ArrayList<List<Path>>();
		m.add(new ArrayList<Path>());
		for (List<Path> l : matrix) {
			List<List<Path>>  newm = new ArrayList<List<Path>>();
			for (Path p : l) {
				for (List<Path> mm : m) {
					List<Path> mmm = new ArrayList<Path>(mm);
					mmm.add(p);
					newm.add(mmm);
				}
			}
			m = newm;
		}
		
		// Iterate all combinations
		for (List<Path> mm : m) {
			
			// When they share aliases, they must be compatible
			mm.forEach(p->System.out.println(Arrays.toString(p.aliases)));

			// In case of collisions, check compatibility
			// This means that each alias must have the same value (must be the same object)
			Map<String,Object> aliasObjects = new HashMap<String,Object>();
			boolean incompatible = mm.stream().anyMatch(p->!p.canAddAliasObjectToMap(aliasObjects));
		
			if (!incompatible) {
				for (Block b : cb.blocks) {
					
					if (b.isText())
						writeBlock((TextBlock)b, w, r);
					else if (b.isValue())
						writeBlock((ValueBlock)b, w, mm);
					else if (b.isControl())
						writeBlock((ControlBlock)b, w, r);
					else
						throw new IllegalStateException("To be implemented"); // FIXME
				}
			}
		}
	}
	private static void writeBlock(ValueBlock vb, Writer w, List<Path> paths) throws IOException {
		
		// Current value path
		Path current = vb.getPath();
		
		// Find which path element is matching the current value
		boolean found = false;
		Object o = null;
		FIND_PATH: for (Path path : paths) {
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
				writeBlock(vb.call.getBlockToCall(), w, o);
		} else if (!found)
			throw new IllegalStateException("Path not found for current value!"); // FIXME
	}
	private static void writeBlock(TextBlock tb, Writer w, Object r) throws IOException {
		w.append(tb.text);
	}
//	private static void writeBlock(OperationBlock ob, Writer w, Object r) {
//		
//	}
	
	public static List<Path> computeValues(ValueBlock vb, Object obj) {
		// Init result list
		final List<Path> toProcess = new ArrayList<Path>();
		
		if (!vb.argName.equals(BlockBuilder.ROOT))
			throw new IllegalStateException("To be implemented"); // FIXME
		
		// Init processing
		if (obj instanceof Collection)
			((Collection) obj).forEach(o->toProcess.add(new ValuePath(vb.argName, vb.aliasName, o)));
		else if (obj != null)
			toProcess.add(new ValuePath(vb.argName, vb.aliasName, obj));
		
		// Process operators
		List<Path> result = toProcess;
		for (OperationBlock op : vb.ops)
			result = result.stream().map(p->extractObject(op,p)).filter(p->p != null).collect(Collectors.toList());

		return result;
	}
	public static Path extractObject(OperationBlock ob, Path p) {
		Object x = null;
		Object o = p.getObject();
		Class c = o.getClass();

		try {
			try {
				Method m = c.getDeclaredMethod(ob.methodName);
				x = m.invoke(o);
			} catch (NoSuchMethodException e) {
				String m2 = "get" + Character.toUpperCase(ob.methodName.charAt(0)) + ob.methodName.substring(1);
				Method m = c.getDeclaredMethod(m2);
				x = m.invoke(o);
			}
		} catch (NoSuchMethodException e) { // FIXME should log the error with details but on a single line
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

}
