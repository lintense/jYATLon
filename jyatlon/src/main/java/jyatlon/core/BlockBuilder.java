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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jyatlon.core.Struct.*; // Input structure
import jyatlon.core.Block.*; // Output structure

/**
 * @author lintense
 * 
 * SRP - A stateless (all static) "Struct to Block" data structure converter.
 * Struct is used as a facade when the Template Language is updated.
 * Block is the actual structure that is to be processed by the template.
 * 
 * TODO all methods should be static to ensure thread safety (ok)
 * TODO : Missing some validations
 */
public class BlockBuilder {
	
	private static String getStructText(String fullText, Struct s) {
		return fullText.substring(s.from, s.to);
	}

	/**
	 * @param t
	 * @return
	 * PathBlock contains a list of <Text|Value>
	 */
	public static Map<String,PathBlock> parseTemplate(String fullText, Template t) {
		
		System.out.print("Starting parse process...");
		long t1 = System.currentTimeMillis();
		
		try {
	//		if (t.section == null)
	//			return null;
			
			Map<String,PathBlock> pathBlocks = new HashMap<String,PathBlock>();
			t.section.forEach(section -> {
				PathBlock b = parseSection(fullText, section);
				if (pathBlocks.put(b.pathname, b) != null)
					throw new BuildingError("Duplicated path block name: " + b.pathname, b.from);
			});
			
			// Gather all alias path blocks aliases
			Map<PathBlock,Set<String>> aliasForPathBlock = new HashMap<>();
			pathBlocks.values().stream().forEach(pb->pb.getValues().forEach(vb -> {
				Set<String> x = aliasForPathBlock.getOrDefault(pb, new HashSet<>());
				aliasForPathBlock.put(pb, x);
				x.addAll(vb.getAliases());
			}));
			
			// Validate the pivot of each value expression
			// A value expression always starts with any of: the root context, a path or an alias.
			// Reason: To help the user, no implicit value.
			pathBlocks.values().stream().forEach(pb->pb.getValues().stream().forEach(vb->{
				if (!isValidValueBlock(vb, pb, aliasForPathBlock))
					throw new BuildingError("unknown reference for value " + vb.argName + ". A value must begin with the root context, a path or an alias.", vb.from);
	
				// Validate sub values inside test block
				if (vb.test != null)
					validateTestBlock(vb.test, pb, aliasForPathBlock);
	//				for (BinaryTestBlock bt : vb.test.exp)
	//					for (ValueBlock subvb : bt.values)
	//						if (!isReferenceValidValueBlock(subvb, pb, aliasForPathBlock))
	//							throw new BlockBuildingError("unknown reference for value " + subvb.argName + ". A value must begin with the root context, a path or an alias.", subvb.from);
			}));
			
			// TODO - Can this be in a separate method
			// Compute the destination of each value call blocks
			pathBlocks.values().stream().forEach(pb->pb.getValues().stream().filter(vb->vb.call != null).forEach(vb->{
				
				
					
					// TODO - Validate that each path is compatible with parent path block
					// i.e. === .../X/Y === {{call .../W/Z}} should be valid (I think!)
					
	
					// If the call is absolute we check only for an exact match
					// First check if there exist an unambiguous matching absolute destination
	//				List<PathBlock> toCall;
	//				if (!vb.call.isRelative) {
	//					toCall = getAbsolutePathBlockToCall(pathBlocks, vb.call);
	////					if (toCall == null)
	////						throw new BlockBuildingError("cannot find absolute path " + vb.call.name + ". Maybe you'd want to call .../" + vb.call.name + " instead?", vb.call.from);
	//				} else {
	//					List<ValuePath> vps = new ArrayList<>();
	//					vb.call.paths.forEach(path->{ // FIXME useless iteration!!!
	//						vps.add(pb.path.add(path));
	//					});
						
	//						List<ValueBlock> args = null; // Why not simply : CallBlock newCB = pb.path ?
						
						// Add parent block path to call block if call is relative
	//					CallBlock newCB = pb.path != null ? new CallBlock(Arrays.asList(new ValuePath[] {pb.path.add(path)}), vb.call.isRelative, null, vb.from) : vb.call;
				CallBlock newCB = vb.call.isRelative && pb.path != null 
						? new CallBlock(vb.call.paths.stream().map(p->pb.path.add(p)).collect(Collectors.toList()), vb.call.isRelative, null, vb.from) 
						: vb.call;
	//					if (!newCB.isRelative)
							
	
				// If new call block is absolute (as by parent) then check again for an exact match
				List<PathBlock> toCall = vb.call.isRelative
					? getRelativePathBlockToCall(pathBlocks, newCB)
					: getAbsolutePathBlockToCall(pathBlocks, newCB);
						
	//				}
	//				if (toCall == null)
	//					throw new BlockBuildingError("cannot find any path for " + vb.call.name, vb.from);
	
				
				for (PathBlock tc : toCall) {
					if (vb.call.isRelative != tc.isRelative)
						throw new BuildingError("Incompatible path!!!", tc.from);
					
					int start = tc.pathname.startsWith(Constant.ANYPATH) ? Constant.ANYPATH.length()+1 : 0;
					int end = (end = tc.pathname.lastIndexOf(Constant.COLON)) > start ? end : tc.pathname.length();
					String classname = tc.pathname.substring(start, end);
					
					vb.call.addBlockToCall(classname, tc); // FIXME
				}
				// TODO Add detection for possible infinite loop (warn user) if any
			}));
			return pathBlocks;
		} finally {
			long t2 = System.currentTimeMillis();
			System.out.println("completed in " + (t2-t1) + " ms.");
		}
		
		// TODO Validate that alias names in paths are unique in the path (also w.r.t class names)
		
		
	}
	private static void validateTestBlock(LogicalTestBlock lt, PathBlock pb, Map<PathBlock,Set<String>> aliasForPathBlock) {
		if (lt == null) {} // do nothing
		else if (lt.bexp != null) {
			for (BinaryTestBlock tb : lt.bexp)
				validateTestBlock(tb, pb, aliasForPathBlock);
		} else if (lt.lexp != null) {
			for (LogicalTestBlock tb : lt.lexp)
				validateTestBlock(tb, pb, aliasForPathBlock);
		} else
			throw new IllegalStateException("To be implemented when needed");
	}
	private static void validateTestBlock(BinaryTestBlock bt, PathBlock pb, Map<PathBlock,Set<String>> aliasForPathBlock) {
		for (ValueBlock subvb : bt.values)
			if (!isValidValueBlock(subvb, pb, aliasForPathBlock))
				throw new BuildingError("unknown reference for value " + subvb.argName + ". A value must begin with the root context, a path or an alias.", subvb.from);
	}
	private static boolean isValidValueBlock(ValueBlock vb, PathBlock pb, Map<PathBlock, Set<String>> aliasForPathBlock) {
		return Constant.ROOT.equals(vb.argName)
		|| (pb.args.contains(vb.argName))
		|| (pb.path != null && pb.path.containsClassName(vb.argName))
		|| (pb.path != null && pb.path.containsAliasName(vb.argName)) //@
		|| Utils.isString(vb.argName, Constant.QUOTES)
		|| Utils.isNumber(vb.argName)
		|| aliasForPathBlock.get(pb).contains(vb.argName);
	}
	/**
	TODO Validate all the destinations for compatibility
	- Absolute always has priority over relative
	- All aliases are local except when used inside the call block.
	- Only one alias at the complete end is allowed inside call path.
	
	Caller can be relative or absolute.
	- If an absolute path cannot be matched then relative path ARE NOT tried (not the opposite)
	- So absolute are always matched to absolute and vice versa. 
	(the only exception is when a call path is relative but its parent is absolute. In which
	case the parent path is prepended and the path become absolute...).
	- If the call is absolute then we check for an exactly matching absolute destination
	- If a call contains an alias then it MUST be present in the destination (not tested)
	- Multi aliases in the caller and destination are not supported.
	
	- If the call is relative, then we add the parent class chain (not the parent alias)
	.../Z = W/X/Y/Z making it an absolute chain.
	
	Destination can also be relative or absolute
	- Absolute destination can only be selected by and absolute call.
	
	- The single longest compatible destination path is selected (matching alias taken into account!).
	- Use an alias to discriminate between such destinations.
	- Aliases in destinations will be used has a preferred choice but ARE NOT mandatory.
	
	(Note that if a destination with matching alias is found then it will be selected)
	- Only the last alias of destination is taken into account when computing the absolute path name.
	

	*/
	private static List<PathBlock> getAbsolutePathBlockToCall(Map<String,PathBlock> pathBlocks, CallBlock call) {
		
		List<PathBlock> results = new ArrayList<>();
		call.paths.forEach(path -> {
		
			String name = getPathName(path, call.isRelative);
		
			PathBlock result = pathBlocks.get(name);
			
			// Maybe there is an alias in destination
			// Get all destination starting by current call name
			if (result == null) {
				List<PathBlock> comp =  pathBlocks.values().stream().filter(pb2->pb2.path != null && !pb2.isRelative && pb2.pathname.startsWith(name)).collect(Collectors.toList());
				if (comp.size() == 1)
					result = comp.get(0);
				else if (comp.isEmpty())
					throw new BuildingError("No path found for call " + name, call.from);
				else if (!comp.isEmpty())
					throw new BuildingError("Multiple paths found for call " + name, call.from);
			}
			results.add(result);
		});
		return results;
	}
	private static String getPathName(CallBlock cp) {
		return getPathName(cp.paths.get(0), cp.isRelative);
	}
	private static String getPathName(ValuePath path, boolean isRelative) {
		String result = "";
		String finalAlias = path.getAliasName(); // Only final alias is part of the name
		for (int i = 0; i < path.classes.length; i++)
			result += Constant.PATHSEP + path.classes[i];
		return (isRelative ? Constant.ANYPATH + Constant.PATHSEP : "") + result.substring(1) + (finalAlias != null ? ":" + finalAlias : "");
	}
	private static List<PathBlock> getRelativePathBlockToCall(Map<String,PathBlock> pathBlocks, CallBlock newCB) {
		
		List<PathBlock> results = new ArrayList<>();
		
		newCB.paths.forEach(path->{
			// If relative then gather all compatible destinations
			// start by the full call path
			final String currentCallAlias = path.getAliasName();
			List<PathBlock> result =  new ArrayList<PathBlock>();
			List<PathBlock> resultWithAlias =  new ArrayList<PathBlock>();
	
			String[] caller = path.classes;
			for (PathBlock pb2 : pathBlocks.values()) {
				if (pb2.path != null) { // Skip root path block since we dont know the class of the root
					// destination must be relative
					// Some path of the caller can be ignored but ALL the paths of destination MUST be present
					// This implies the destination path MUST be smaller or equal
					String[] dest = pb2.path.classes;
					if (pb2.isRelative && dest.length <= caller.length
							&& IntStream.range(0, dest.length).allMatch(i->dest[i].equals(caller[caller.length - dest.length + i]))) {
						// dest = .../X/Y/Z, caller = .../W/X/Y/Z
						result.add(pb2);
						if (currentCallAlias != null && currentCallAlias.equals(pb2.path.getAliasName()))
							resultWithAlias.add(pb2);
					}
				}
			}
			// If aliasFound then only keep those
			// Some path were having the given correct alias so they have priority since we have full control on the caller alias
			if (!resultWithAlias.isEmpty())
				result = resultWithAlias;
	
			// Keep the single longest compatible destination path
			PathBlock bad = null;
			PathBlock good = null;
			int length = 0;
			for (PathBlock pb2 : result) {
				if (pb2.path.classes.length > length) {
					good = pb2;
					bad = null;
					length = good.path.classes.length;
				} else if (pb2.path.classes.length == length)
					bad = pb2; // There should be only one!
			}
			
			if (good != null && bad == null)
				results.add(good);
			else if (good != null && bad != null)
				throw new BuildingError("ambiguous path for call " + getPathName(path, newCB.isRelative), newCB.from);
			else if (good == null)
				throw new BuildingError("cannot find any path for " + getPathName(path, newCB.isRelative), newCB.from);
		});
		return results;
	}
	private static PathBlock parseSection(String fullText, Section section){
		
		// Extract path parms
		List<String> sectionParms = section.aliasExp == null || section.aliasExp.aliasName == null
			? Collections.emptyList()
			: section.aliasExp.aliasName;
		
		// Process path
		CallBlock cp = parsePathExp(section.pathExp, null, Collections.emptyList());
		if (section.line == null)
			return cp == null
				? new PathBlock(Constant.ROOT, null, false, Collections.emptyList(), sectionParms, section.from)
				: new PathBlock(getPathName(cp), cp.paths.get(0), cp.isRelative, Collections.emptyList(), sectionParms, section.from);

		// Extract lines
		List<Line> lines = section.line.stream().collect(Collectors.toList());
		
		// Remove first and last when empty
		int first = 0;
		int last = lines.size();
		if (last > 0 && lines.get(first).lineExp == null)
			first++;
		if (last > first && lines.get(last-1).lineExp == null)
			last--;
		lines = lines.subList(first, last);
				
		// Process lines
		List<Block> blocks = parseLines(fullText, lines);
		PathBlock pb = cp == null
				? new PathBlock(Constant.ROOT, null, false, blocks, sectionParms, section.from)
				: new PathBlock(getPathName(cp), cp.paths.get(0), cp.isRelative, blocks, sectionParms, section.from);
		
		// Compute value blocks
		ControlBlock cb = extractControlBlock(new ControlBlock(pb, pb.from), blocks.iterator());
		pb.init(cb);
		
		return pb;
	}
	private static CallBlock parseCallExp(CallExp call) {
		
		if (call != null) {
			List<ValueBlock> result = new ArrayList<>();
			if (call.argExp != null && call.argExp.valueExp != null) // && !call.argExp.valueExp.isEmpty())
				call.argExp.valueExp.forEach(a->result.add(parseValueExp(a, null, null, a.from)));
			return parsePathExp(call.pathExp, call.pathArg, result);
		}
		return null;
	}
	private static CallBlock parsePathExp(PathExp path, List<PathArg> pathArgs, List<ValueBlock> args) {
		// First Section Path is null for now
		// Useless to create a Path here since we dont know the actual root Object type
		if (path == null)
			return null;
		
		return new CallBlock(parsePathArg(path.pathArg, pathArgs, path.anyPathOp != null), path.anyPathOp != null, args, path.from);
	}
	private static List<ValuePath> parsePathArg(List<PathArg> mainPathArgs, List<PathArg> extraPathArgs, boolean isRelative) {
		List<ValuePath> result = new ArrayList<>();
		ValuePath p = isRelative ? null : ValuePath.getRoot(null);
		for (PathArg pathArg : mainPathArgs)
			p = p == null 
				? (new ValuePath(pathArg.pathName, pathArg.aliasName, null)) 
				: p.add(pathArg.pathName, pathArg.aliasName, null);
		// Insert primary path
		result.add(p);
		// Add extra paths to primary parent
		final ValuePath pp = p;
		if (extraPathArgs != null)
			extraPathArgs.forEach(x -> result.add(pp.parent == null
				? new ValuePath(x.pathName, x.aliasName, null)
				: pp.parent.add(x.pathName, x.aliasName, null)));
		return result;
	}
	private static List<Block> parseLines(String fullText, List<Line> lines){
		Stack<Block> result = new Stack<Block> ();
		StringBuilder buffer = new StringBuilder();

		lines.forEach(line -> {
			AtomicBoolean insideComment = new AtomicBoolean(false);
			if (line.lineExp != null) {
				line.lineExp.forEach(lineExp -> {
					
					if (lineExp.commentOp != null) {
						insideComment.set(!insideComment.get());
					} else if (lineExp.controlExp != null) {
						if (!insideComment.get()) {
							if (buffer.length() > 0) {
								result.add(new TextBlock(buffer.toString(), lineExp.from));
								buffer.setLength(0);
							}
							result.add(parseControlExp(lineExp.controlExp));
						}
					} else if (lineExp.escapedChar != null) {
						if (!insideComment.get()) {
							String esc = getStructText(fullText, lineExp);
							buffer.append(esc.substring(1)); // Remove escape code
						}
					} else if (lineExp.rawText != null) {
						if (!insideComment.get()) {
							buffer.append(getStructText(fullText, lineExp));
						}
					} else if (lineExp.value != null) {
						if (!insideComment.get()) {
							if (buffer.length() > 0) {
								result.add(new TextBlock(buffer.toString(), lineExp.from));
								buffer.setLength(0);
							}
							if (lineExp.value.valueExp != null)
								result.add(parseValue(lineExp.value));
							else
								throw new BuildingError("To be implemented when needed", lineExp.from); // FIXME To be implemented
						}
					} else {
						throw new BuildingError("To be implemented when needed", lineExp.from);
					}
				});
				
				// Something left in buffer, add it in text block
				if (buffer.length() > 0) { 
					int textPos = line.lineExp.get(line.lineExp.size()-1).to-buffer.length();
					result.add(new TextBlock(buffer.toString(), textPos));
					buffer.setLength(0);
				}
				// Add new line if needed: 
				if (!result.isEmpty() && !result.peek().isControlOperator() && !insideComment.get())
					result.add(new TextBlock(System.lineSeparator(), line.from));
				
			} else // Empty line
				result.add(new TextBlock(System.lineSeparator(), line.from));
			
		}); // end line processing

		return result;
	}
	private static ControlBlock extractControlBlock(ControlBlock currentBlock, Iterator<Block> i) {
		ControlOperator currentControl = currentBlock.begin;
		Map<Integer,ControlOperator> activeControl = new HashMap<Integer,ControlOperator>();
		activeControl.put(ControlBlock.CONTROL_BEGIN, currentBlock.begin);
		while (i.hasNext()) {
			Block b = i.next();
			if (b.isControlOperator()) {
				ControlOperator co = (ControlOperator)b;
				boolean sameAlias = co.aliasName.equals(currentBlock.aliasName);
				if (!sameAlias && co.operation == ControlBlock.CONTROL_BEGIN) {
					currentControl.addBlock(extractControlBlock(new ControlBlock(currentBlock, co, currentBlock.from), i)); // Extract sub block
				} else if (co.operation == ControlBlock.CONTROL_BEGIN)
					throw new BuildingError("duplicated control {begin:" + co.aliasName + "}", co.from) ;
				else if (sameAlias && co.operation == ControlBlock.CONTROL_END) {
					activeControl.put(co.operation, currentControl = co);
					return validateControlBlock(currentBlock.init(activeControl));
				} else if (sameAlias)
					activeControl.put(co.operation, currentControl = co);
				else
					throw new BuildingError("missing control {begin " + co.aliasName + "}", co.from) ;
			} else 
				currentControl.addBlock(b);
		}
		if (currentBlock.isSectionBlock())
			return currentBlock;
		throw new BuildingError("missing control {begin " + currentBlock.aliasName + "}", currentBlock.from);
	}
	private static ControlBlock validateControlBlock(ControlBlock cb) {
		// My alias MUST be defined at least once
		// Also the defining block should/could be flagged to be processed first!
		
		// Validate that all the control operators (except begin) do not reference the control alias.
		// FIXME error message must be clearer
		if (cb.before != null && cb.before.getValues().stream().anyMatch(v->v.getAliases().stream().anyMatch(a->a.equals(cb.aliasName))))
			throw new BuildingError("{before " + cb.aliasName + "} control block cannot reference its own alias: " + cb.aliasName, cb.before.from);
		if (cb.between != null && cb.between.getValues().stream().anyMatch(v->v.getAliases().stream().anyMatch(a->a.equals(cb.aliasName))))
			throw new BuildingError("{between " + cb.aliasName + "} control block cannot reference its own alias: " + cb.aliasName, cb.between.from);
		if (cb.after != null && cb.after.getValues().stream().anyMatch(v->v.getAliases().stream().anyMatch(a->a.equals(cb.aliasName))))
			throw new BuildingError("{after " + cb.aliasName + "} control block cannot reference its own alias: " + cb.aliasName, cb.after.from);
		if (cb.empty != null && cb.empty.getValues().stream().anyMatch(v->v.getAliases().stream().anyMatch(a->a.equals(cb.aliasName))))
			throw new BuildingError("{empty " + cb.aliasName + "} control block cannot reference its own alias: " + cb.aliasName, cb.empty.from);

		// TODO Validate all aliases used everywhere are defined in block parents... 
	
		return cb;
	}
	private static ValueBlock parseValue(Value value) {
		ValueBlock result = parseValueExp(value.valueExp, parseCallExp(value.callExp), parseIfExp(value.ifExp), value.from);
		if (result.call != null && !isValidForValue(result.call)) // TODO Why is that so?
			throw new BuildingError("alias only allowed at the end of a call in " + getPathName(result.call), result.from);
		return result;
	}
	private static boolean isValidForValue(CallBlock cb) {
		// Only the last alias is allowed in a value block call block
		return cb.paths.stream().allMatch(path -> path.aliases.length > 0 && IntStream.range(0, path.aliases.length - 1).allMatch(a->path.aliases[a] == null));
	}
	private static LogicalTestBlock parseIfExp(IfExp exp) {
		return exp != null ? parseLogicalTestBlock(exp.logicalExp) : null;
	}
	private static LogicalTestBlock parseLogicalTestBlock(LogicalExp exp) {
		String op = null;
		if (exp.logicalOp != null) {
			// Validation: Only one operator allowed.
			// TODO Allow inner logical test block inside parenthesis
			if (exp.logicalOp.stream().distinct().limit(2).count() != 1)
				throw new BuildingError("mixing logical operators is not allowed " + exp.logicalOp, exp.from);
			else
				op = exp.logicalOp.get(0);
		}
		if (exp.logicalExp != null) {
			List<LogicalTestBlock> ops = exp.logicalExp.stream().map(x -> parseLogicalTestBlock(x)).collect(Collectors.toList());
			return new LogicalTestBlock(exp.from, ops, null, op);
		} else if (exp.binaryExp != null) {
			List<BinaryTestBlock> ops = exp.binaryExp.stream().map(x -> parseBinaryTestBlock(x)).collect(Collectors.toList());
			return new LogicalTestBlock(exp.from, null, ops, op);
		} else
			throw new IllegalStateException("Case not allowed by grammar");
	}
	private static BinaryTestBlock parseBinaryTestBlock(BinaryExp exp) {
		List<ValueBlock> values = exp.valueExp.stream().map(x -> parseValueExp(x, null, null, x.from)).collect(Collectors.toList());
		return new BinaryTestBlock(exp.from, exp.binaryOp, values);
	}
	private static ValueBlock parseValueExp(ValueExp valueExp, CallBlock cb, LogicalTestBlock tb, int from) {
		// Initialize value path
		ValuePath vp = new ValuePath(valueExp.valueArg, valueExp.aliasName, null);
		if (valueExp.operation != null && !valueExp.operation.isEmpty())
			for (Operation o : valueExp.operation)
				vp = vp.add(o.methodName, o.aliasName, null);
		ValueBlock result = new ValueBlock(valueExp.unaryOp, valueExp.indexOp, valueExp.valueArg, valueExp.aliasName, cb, tb, vp, from);
		if (valueExp.operation != null && !valueExp.operation.isEmpty())
			valueExp.operation.forEach(o->result.addOperation(parseOperation(o)));
		return result;
	}
	private static OperationBlock parseOperation(Operation op) {
		OperationBlock result = new OperationBlock(op.methodName, op.aliasName, op.from);
		if (op.argExp != null && op.argExp.valueExp != null && !op.argExp.valueExp.isEmpty())
			op.argExp.valueExp.forEach(a->result.addArgument(parseValueExp(a, null, null, a.from)));
		return result;
	}
	private static ControlOperator parseControlExp(ControlExp ce) {
		// TODO Validate order
		// TODO Validate not already filled
		int operationId = ControlBlock.extractControlId(ce.controlOp);
		boolean isEndOfBlock = operationId == ControlBlock.CONTROL_END;
		return new ControlOperator(isEndOfBlock, ce.aliasName, operationId, ce.from);
	}
	public static class BuildingError extends Error {

		private static final long serialVersionUID = 1L;
		public final int pos;

		public BuildingError(String message, int pos) {
			super(message);
			this.pos = pos;
		}
	}
}
