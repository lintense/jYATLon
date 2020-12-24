package jyatlon.core;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import jyatlon.core.Block.ControlBlock;
import jyatlon.core.Block.ControlOperator;
import jyatlon.core.Block.PathBlock;
import jyatlon.core.Block.TextBlock;
import jyatlon.core.Block.ValueBlock;

/**
 * @author linte
 * SRP: A state less processor that generate a text file.
 * All exceptions should be generated from this class not its delegates.
 * 
 * 
 * Add into doc:
 * Set Notepad to Smalltalk/SQL for nice coloring
 * Set Notepad to Pascal for all controls in green
 * Parameters...
 * TODO : indexOf, sizeOf : cannot be used inside operations...
 * TODO : vb.test valueBlocks are only computed when vb is computed so we cannot define alias inside test?
 * TODO : Combination alias and class map should be the same (but check initPath for duplicated class names) i.e Relation/Relation
 * TODO : {begin (X,Y):Z}(x1,y1),(x1,y2),(x1,y3),(x2,y1),(x2,y2),(x2,y3)
 * {begin X}{begin Y}x1,y1,y2,y3,x2,y1,y2,y3
 * TODO : {call .../A(X)|B(Y)|C(Z) vb} all relative or all absolute (ok)
 * TODO : === Relation(CHAIN) with sizeOf(CHAIN)==1 ===
 * TODO : Map key access and 'class' key
 * TODO : Revise doc for missing stuff (parms, )
 * - {before} and {after} have the same effect as putting stuff at the beginning and end of {begin}
 * TODO : Write a Dev Guide? (How to update YATL.g4 Maven>Update, update Struct (StructGen) and Block, run the tests)
 * - No throw in service classes only here
 * - Ideally no throw here either. Put validation into BlockBuilder instead. to avoid run-time errors.
 * TODO : Define Matcher interface and add it as an optional parm
 * TODO : {call} should be a control, not a value?
 * Known limitation: index operation (sizeOf, indexOf) are not allowed inside function calls. This is because the index/size is not known when the values are computed.
 */
public class BlockProcessor {

	
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

		if (pb.getControlBlock() != null) {
			// This process must be here in order to throw errors
			List<ValuePath> pathParms = new ArrayList<>();
			pb.args.forEach(a->{
				Object o = combination.getObjectForName(a);
				if (o == null)
					throw new IllegalStateException("Should not happen: Parameter '" + a + "' not defined for section " + pb.pathname);
				pathParms.add(new ValuePath(o.getClass().getSimpleName(), a, o));
			});
			
			writeBlock(pb.getControlBlock(), w, combination.callPath(ctx, pathParms), new HashMap<String,Integer>(), new HashMap<String,Integer>());
			
			//@ Should only compute the related alias at each stage, so nothing here!
//			List<Combination> oldCombinations = combination.compute(pb.getValues());
//			for (Combination oc : oldCombinations)
//				writeBlock(pb.getControlBlock(), w, oc.callPath(ctx, pathParms));

//			writeBlock(pb.getControlBlock(), pb.getControlBlock().begin.getExclusiveValues()));
		}
			;
	}
	/**
	 * @param combination
	 * @param vbList
	 * @param aliasName
	 * @return A list of new combination
	 * This method is located inside the processor since it throws exceptions. 
	 */
	private static List<Combination> compute(Combination combination, List<ValueBlock> vbList, ControlBlock cb){
		LinkedList<ValueBlock> toProcess = new LinkedList<>(vbList);
		List<Combination> oldCombinations = new ArrayList<>();
		List<Combination> newCombinations;
		oldCombinations.add(combination);
		
		Set<String> aliases = getDefinedAliases(cb);
		
		// Compute all the possible values.
		int control = 0; // Ensure this process will end some day!
		while (!toProcess.isEmpty() && control < toProcess.size()) {
			ValueBlock vb = toProcess.removeFirst();
			
			// If ValueBlock starts by an alias that has not been processed yet then we must delay its processing to the end
			if (!oldCombinations.isEmpty() && oldCombinations.get(0).isDefined(vb)) {
				control = 0;
				newCombinations = new ArrayList<>();
				for (Combination oc : oldCombinations) {
					List<ValuePath> paths = oc.computeValues(vb);
					if (paths.size() > 1 && !aliases.contains(vb.getFinalAliasName()))
						throw new IllegalStateException("Value that return a collection must be inside a block: " + vb.valuePath.toString());
//					else if (paths.size() > 1 && !aliases.contains(vb.getFinalAliasName()))
//						throw new IllegalStateException("Value that return a collection must be inside a block: " + vb.valuePath.toString());
//					// Relation.parms:PARM.index is ok since the split comes from PARM
					// 
//					int i = 1;
					for (ValuePath pathCtx : paths) {
						Combination nc = oc.addPath(pathCtx);
						if (nc != null) {// Add valid only
							newCombinations.add(nc);
							
							// Add test values inside new combination
							if (vb.test != null)
								nc.paths.addAll(nc.computeValues(vb.test));	
						}
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
	private static Set<String> getDefinedAliases(ControlBlock cb) {
		ControlBlock currentBlock = cb;
		Set<String> result = new HashSet<>();
		while (!currentBlock.isSectionBlock()) {
			result.add(currentBlock.aliasName);
			currentBlock = (ControlBlock)currentBlock.parent;
		}
		return result;
	}
	private static void writeBlock(ControlBlock cb, Writer w, Combination combination, Map<String,Integer> indexMap, Map<String,Integer> sizeMap) throws IOException {

		List<ValueBlock> currentValues = null;
		if (cb.aliasName == null)
			currentValues = cb.begin.blocks.stream().filter(b -> b.isValue()).map(v -> v.getValues()).flatMap(List::stream).collect(Collectors.toList());
		else if (cb.begin.hasControl())
			currentValues = cb.getValues().stream().filter(v -> v.valuePath.containsAliasName(cb.aliasName)).collect(Collectors.toList());
		else
			currentValues = cb.getValues();

		List<Combination> oldCombinations = compute(combination, currentValues, cb);
		// TODO Here we know the size and index for cb.aliasName
		// Always write blocks in order
		int index = 0;
		sizeMap.put(cb.aliasName, oldCombinations.size());
		
		if (!oldCombinations.isEmpty()) { // && oldCombinations.get(0).isAliasDefined(cb.aliasName)) {
//			boolean first = true;
			for (Combination oc : oldCombinations) {
				if (index > 0 && cb.between != null)
					writeBlock(cb.between, combination, w, indexMap, sizeMap);

				if (cb.before != null)
					writeBlock(cb.before, combination, w, indexMap, sizeMap);
				
				indexMap.put(cb.aliasName, ++index);
				writeBlock(cb.begin, oc, w, indexMap, sizeMap);
				indexMap.remove(cb.aliasName);
				
				if (cb.after != null)
					writeBlock(cb.after, combination, w, indexMap, sizeMap);
			}
		} else if (cb.empty != null)
			writeBlock(cb.empty, combination, w, indexMap, sizeMap);

		sizeMap.remove(cb.aliasName);
		
	}

	private static void writeBlock(ControlOperator co, Combination combination, Writer w, Map<String,Integer> indexMap, Map<String,Integer> sizeMap) throws IOException {
		for (Block b : co.blocks) {
			if (b.isText())
				writeBlock((TextBlock)b, w);
			else if (b.isValue())
				writeBlock((ValueBlock)b, w, combination, indexMap, sizeMap);
			else if (b.isControl())
				writeBlock((ControlBlock)b, w, combination, indexMap, sizeMap);
			else
				throw new IllegalStateException("To be implemented"); // FIXME To be implemented
		}
	}
	private static void writeBlock(ValueBlock vb, Writer w, Combination combination, Map<String,Integer> indexMap, Map<String,Integer> sizeMap) throws IOException {
		
		boolean test = combination.computeTest(vb.test, indexMap, sizeMap);
		if (test) {
			ValuePath foundPath = combination.getMatchingPath(vb.valuePath);
			if (foundPath != null) {
				if (Constant.INDEX_OF.equals(vb.indexOp))
					w.append(Integer.toString(indexMap.get(vb.getFinalAliasName())));
				else if (Constant.SIZE_OF.equals(vb.indexOp))
					w.append(Integer.toString(sizeMap.get(vb.getFinalAliasName())));
				else {
					Object o = foundPath.getObject();
					if (vb.call == null)
						w.append(o.toString());
					else { // TODO called path must match actual object class or interface?
						PathBlock pb = vb.call.getBlockToCall(foundPath);
						if (pb == null)
							throw new IllegalStateException("No path found to match object: " + Utils.getClassName(o) + ". Possible suffixes are: " + Arrays.toString(vb.call.getPossibleCalls()));
						writeBlock(pb, w, combination, new ValuePath(pb.path.getClassName(), pb.path.getAliasName(), o));
					}
				}
			} else if (foundPath == null) {
				throw new IllegalStateException("Path not found for current value!"); // FIXME should this ever happen?
//				List<ValuePath> values = combination.computeValues(vb);
//				if (values.size() == 1)
//					writeBlock(vb, w, combination.addPath(values.get(0)));
//				else if (values.size() > 1)
//					throw new IllegalStateException("Values that are collection must be inside a block: " + vb.argName);
			}
		}
	}

	private static void writeBlock(TextBlock tb, Writer w) throws IOException {
		w.append(tb.text);
	}

}
