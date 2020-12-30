package jyatlon.core;

/*
 * BSD 3-Clause Clear License
 * 
 * Copyright (c) 2019 Sylvain Nadeau
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted (subject to the limitations in the disclaimer 
 * below) provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in the 
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of [Owner Organization] nor the names of its contributors 
 *    may be used to endorse or promote products derived from this software 
 *    without specific prior written permission.
 * 
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY 
 * THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT 
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

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

import jyatlon.core.Block.*; // Input structure

/**
 * @author lintense
 * SRP: A state less (static) processor that generate a text file.
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
		System.out.print("Starting merge process...");
		long t1 = System.currentTimeMillis();
		try {
			Matcher matcher = new Matcher();
			writeBlock(pb, w, new Combination(matcher), ValuePath.getRoot(r));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			long t2 = System.currentTimeMillis();
			System.out.println("completed in " + (t2-t1) + " ms.");
		}
	}
	private static void writeBlock(PathBlock pb, Writer w, Combination combination, ValuePath ctx) throws IOException {
		if (pb.getControlBlock() != null) {
			// This process must be in this class in order to throw errors
			List<ValuePath> pathParms = new ArrayList<>();
			pb.args.forEach(a->{
				Object o = combination.getObjectForName(a);
				if (o == null)
					throw new IllegalStateException("Should not happen: Parameter '" + a + "' not defined for section " + pb.pathname);
				pathParms.add(new ValuePath(o.getClass().getSimpleName(), a, o));
			});
			writeBlock(pb.getControlBlock(), w, combination.callPath(ctx, pathParms), new HashMap<String,Integer>(), new HashMap<String,Integer>());
		}
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

		// Compute all the possible values.
		int control = 0; // Ensure this process will end some day!
		Set<String> aliases = getDefinedAliases(cb);
		LOOP: while (!toProcess.isEmpty() && control < toProcess.size()) {
			ValueBlock vb = toProcess.removeFirst();
			// If ValueBlock starts by an alias that has not been processed yet then we must delay its processing to the end
			if (!oldCombinations.isEmpty() && oldCombinations.get(0).isDefined(vb)) {
				control = 0;
				newCombinations = new ArrayList<>();
				for (Combination oc : oldCombinations) {
					if (oc.hasMatchingPath(vb.valuePath))
						continue LOOP;
					
					List<ValuePath> paths = oc.computeValues(vb);
					if (paths.size() > 1 && !aliases.contains(vb.getFinalAliasName()))
						throw new ProcessingError("Value that returns a collection must be declared in a block: " + vb.valuePath.toString(), vb.from);
					for (ValuePath pathCtx : paths) {
						Combination nc = oc.addPath(pathCtx); // BUG do not add when already there!
						if (nc != null) { // Add valid only
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
		if (!oldCombinations.isEmpty() && toProcess.size() > 0)
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
			currentValues = cb.begin.getValues().stream().filter(v -> v.valuePath.containsAliasName(cb.aliasName)).collect(Collectors.toList());
		else
			currentValues = cb.begin.getValues();
		if (cb.between != null)
			currentValues.addAll(cb.between.getValues());
		
		List<Combination> oldCombinations = compute(combination, currentValues, cb);
		// TODO Here we know the sizeOf and indexOf for cb.aliasName
		// Always write blocks in order
		int index = 0;
		sizeMap.put(cb.aliasName, oldCombinations.size());
		if (!oldCombinations.isEmpty()) {
			for (Combination oc : oldCombinations) {
				if (index > 0 && cb.between != null)
					writeBlock(cb.between, oc, w, indexMap, sizeMap);
				if (cb.before != null)
					writeBlock(cb.before, oc, w, indexMap, sizeMap);
				if (cb.aliasName != null)
					indexMap.put(cb.aliasName, ++index);
				writeBlock(cb.begin, oc, w, indexMap, sizeMap);
				indexMap.remove(cb.aliasName);
				if (cb.after != null)
					writeBlock(cb.after, oc, w, indexMap, sizeMap);
			}
		} else if (cb.empty != null)
			writeEmptyBlock(cb, w, combination, indexMap, sizeMap);
		sizeMap.remove(cb.aliasName);
	}
	private static void writeEmptyBlock(ControlBlock cb, Writer w, Combination combination, Map<String,Integer> indexMap, Map<String,Integer> sizeMap) throws IOException {
		List<ValueBlock> currentValues = null;
		if (cb.aliasName == null)
			currentValues = cb.empty.blocks.stream().filter(b -> b.isValue()).map(v -> v.getValues()).flatMap(List::stream).collect(Collectors.toList());
		else if (cb.empty.hasControl())
			currentValues = cb.empty.getValues().stream().filter(v -> v.valuePath.containsAliasName(cb.aliasName)).collect(Collectors.toList());
		else
			currentValues = cb.empty.getValues();
		List<Combination> oldCombinations = compute(combination, currentValues, cb);
		// This may look strange to have a collection here but lets say that it is probably inside another control block
		for (Combination oc : oldCombinations)
			writeBlock(cb.empty, oc, w, indexMap, sizeMap);
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
				throw new IllegalStateException("To be implemented");
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
						PathBlock pb = vb.call.getBlockToCall(combination, foundPath);
						if (pb == null)
							throw new ProcessingError("No path found to match object: " + Utils.getClassName(o) + ". Possible suffixes are: " + Arrays.toString(vb.call.getPossibleCalls()), vb.from);
						writeBlock(pb, w, combination, new ValuePath(pb.path.getClassName(), pb.path.getAliasName(), o));
					}
				}
			} else if (foundPath == null)
				throw new IllegalStateException("Path not found for current value!"); // FIXME should this ever happen?
		}
	}
	private static void writeBlock(TextBlock tb, Writer w) throws IOException {
		w.append(tb.text);
	}
	/**
	 * @author linte
	 * Use only for error that cannot be validated at the building phase.
	 * These errors are cause by the fact the actual structure to be processed is unknown at building time.
	 */
	public static class ProcessingError extends Error {

		private static final long serialVersionUID = 1L;
		public final int pos;

		public ProcessingError(String message, int pos) {
			super(message);
			this.pos = pos;
		}
	}
}
