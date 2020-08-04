package jyatlon.core;

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

import jyatlon.core.Block.BinaryTestBlock;
import jyatlon.core.Block.CallBlock;
import jyatlon.core.Block.ControlBlock;
import jyatlon.core.Block.ControlOperator;
import jyatlon.core.Block.LogicalTestBlock;
import jyatlon.core.Block.OperationBlock;
import jyatlon.core.Block.PathBlock;
import jyatlon.core.Block.TextBlock;
import jyatlon.core.Block.ValueBlock;
import jyatlon.core.Struct.BinaryExp;
import jyatlon.core.Struct.CallExp;
import jyatlon.core.Struct.ControlExp;
import jyatlon.core.Struct.IfExp;
import jyatlon.core.Struct.Line;
import jyatlon.core.Struct.Operation;
import jyatlon.core.Struct.PathArg;
import jyatlon.core.Struct.PathExp;
import jyatlon.core.Struct.Section;
import jyatlon.core.Struct.Template;
import jyatlon.core.Struct.Value;
import jyatlon.core.Struct.ValueExp;
import jyatlon.generated.YATLLexer;

/**
 * @author linte
 * SRP - A stateless "Struct to Block" data structure converter.
 * Struct is used as a facade when the Template Language is updated.
 * Block is the actual structure that is to be processed by the template.
 * 
 * TODO all methods should be static to ensure thread safety (ok)
 * 
 */
public class BlockBuilder {
	
	public static final String HIDDEN_HEADER = "=$=\n";
	public static final String ROOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.ROOT));
	
	private static String getStructText(String fullText, Struct s) {
		return fullText.substring(s.from, s.to);
	}

	/**
	 * @param t
	 * @return
	 * PathBlock contains a list of <Text|Value>
	 */
	public static Map<String,PathBlock> parseTemplate(String fullText, Template t) {
		
		System.out.println("Starting parse process...");
		long t1 = System.currentTimeMillis();
		
		if (t.section == null)
			return null;
		
		Map<String,PathBlock> pathBlocks = new HashMap<String,PathBlock>();
		t.section.forEach(section -> {
			PathBlock b = parseSection(fullText, section);
			if (pathBlocks.put(b.pathname, b) != null)
				throw new BlockBuildingError("duplicated path name " + b.pathname, b.from);
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
			if (!(BlockBuilder.ROOT.equals(vb.argName)
			|| (pb.path != null && pb.path.path.hasClassName(vb.argName))
			|| Utils.isString(vb.argName)
			|| aliasForPathBlock.get(pb).contains(vb.argName))) // TODO should use Sets instead of a Lists...
				throw new BlockBuildingError("unknown reference for value " + vb.argName + ". A value must begin with the root context, a path or an alias.", vb.from);
		}));
		
		// Compute the destination of each value call blocks
		pathBlocks.values().stream().forEach(pb->pb.getValues().stream().filter(vb->vb.call != null).forEach(vb->{
			
			// If the call is absolute we check only for an exact match
			// First check if there exist an unambiguous matching absolute destination
			PathBlock toCall = null;
			if (!vb.call.isRelative) {
				toCall = getAbsolutePathBlockToCall(pathBlocks, vb.call);
				if (toCall == null)
					throw new BlockBuildingError("cannot find absolute path " + vb.call.name + ". Maybe you'd want to call .../" + vb.call.name + " instead?", vb.call.from);
			} else {
				// Add parent block path to call block if call is relative
				CallBlock newCB = pb.path != null ? new CallBlock(pb.path.path.add(vb.call.path), pb.path.isRelative, vb.from) : vb.call;
				
				// If new call block is absolute (as by parent) then check again for an exact match
				toCall = newCB.isRelative
						? getRelativePathBlockToCall(pathBlocks, newCB)
						: getAbsolutePathBlockToCall(pathBlocks, newCB);

			}
			if (toCall == null)
				throw new BlockBuildingError("cannot find any path for " + vb.call.name, vb.from);

			// TODO Add detection for possible infinite loop (warn user)
			
			vb.call.setBlockToCall(toCall);
		}));
		
		// TODO Validate that alias names in paths are unique in the path (also w.r.t class names)
		
		long t2 = System.currentTimeMillis();
		System.out.println("Completed in " + (t2-t1) + " milliseconds.");
		
		return pathBlocks;
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
	private static PathBlock getAbsolutePathBlockToCall(Map<String,PathBlock> pathBlocks, CallBlock call) {
		PathBlock result = pathBlocks.get(call.name);
		
		// Maybe there is an alias in destination
		// Get all destination starting by current call name
		if (result == null) {
			List<PathBlock> comp =  pathBlocks.values().stream().filter(pb2->pb2.path != null && !pb2.path.isRelative && pb2.path.name.startsWith(call.name)).collect(Collectors.toList());
			if (comp.size() == 1)
				result = comp.get(0);
			else if (!comp.isEmpty())
				throw new BlockBuildingError("multiple paths found for call " + call.name, call.from);
		}
		return result;
	}
	private static PathBlock getRelativePathBlockToCall(Map<String,PathBlock> pathBlocks, CallBlock newCB) {;
		// If relative then gather all compatible destinations
		// start by the full call path
		final String currentCallAlias = newCB.path.getAlias();
		List<PathBlock> result =  new ArrayList<PathBlock>();
		List<PathBlock> resultWithAlias =  new ArrayList<PathBlock>();

		String[] caller = newCB.path.classes;
		for (PathBlock pb2 : pathBlocks.values()) {
			if (pb2.path != null) { // Skip root path block since we dont know the class of the root
				// destination must be relative
				// Some path of the caller can be ignored but ALL the paths of destination MUST be present
				// This implies the destination path MUST be smaller or equal
				String[] dest = pb2.path.path.classes;
				if (pb2.path.isRelative && dest.length <= caller.length
						&& IntStream.range(0, dest.length).allMatch(i->dest[i].equals(caller[caller.length - dest.length + i]))) {
					// dest = .../X/Y/Z, caller = .../W/X/Y/Z
					result.add(pb2);
					if (currentCallAlias != null && currentCallAlias.equals(pb2.path.path.getAlias()))
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
			if (pb2.path.path.classes.length > length) {
				good = pb2;
				bad = null;
				length = good.path.path.classes.length;
			} else if (pb2.path.path.classes.length == length)
				bad = pb2; // There should be only one!
		}
		
		if (good != null && bad == null)
			return good;
		else if (good != null && bad != null)
			throw new BlockBuildingError("ambiguous path for call " + newCB.path.classes, newCB.from);
	
		return null;
	}
	private static PathBlock parseSection(String fullText, Section section){
		
		// Process path
		CallBlock cp = parsePathExp(section.pathExp);
		
//		Map<Section,List<Line>> sectionLines = new HashMap();
		if (section.line == null)
			return new PathBlock(cp == null ? ROOT : cp.name, cp, Collections.emptyList(), section.from);

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
//		sectionLines.put(section, lines);
				
		// Process lines
		List<Block> blocks = parseLines(fullText, lines);
		PathBlock pb =  new PathBlock(cp == null ? ROOT : cp.name, cp, blocks, section.from);
		
		// Compute value blocks
		ControlBlock cb = extractControlBlock(new ControlBlock(pb, pb.from), blocks.iterator());
//		cb.setValues(extractControlValues(cb));
		pb.init(cb);
		
		return pb;
	}
	private static CallBlock parseCallExp(CallExp call) {
		return call != null ? parsePathExp(call.pathExp) : null;
	}
	private static CallBlock parsePathExp(PathExp path) {
		// First Section Path is null for now
		// Useless to create a Path here since we dont know the actual root Object type
		if (path == null)
			return null;

		return new CallBlock(parsePathArg(path.pathArg), path.anyPathOp != null, path.from);
	}
	private static ValuePath parsePathArg(List<PathArg> pathArgs) {
		ValuePath p = null;
		for (PathArg pathArg : pathArgs) {
			p = p == null 
					? (new ValuePath(pathArg.pathName, pathArg.aliasName, null)) 
					: p.add(pathArg.pathName, pathArg.aliasName, null);
		}
		return p;
	}
	private static List<Block> parseLines(String fullText, List<Line> lines){
		Stack<Block> result = new Stack<Block> ();
		StringBuffer buffer = new StringBuffer();

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
							buffer.append(esc.charAt(esc.length()-1)); // Remove escape code
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
								throw new BlockBuildingError("to be implemented", lineExp.from); // FIXME To be implemented
						}
					} else {
						throw new BlockBuildingError("not yet implemented line exp type", lineExp.from);
					}
				});
				
				// Something left in buffer, add it in text block
				if (buffer.length() > 0) { 
//					int textPos = lines.get(lines.size()-1).to-buffer.length();
					int textPos = line.lineExp.get(line.lineExp.size()-1).to-buffer.length();
					result.add(new TextBlock(buffer.toString(), textPos));
					buffer.setLength(0);
				}
				// Add new line if needed: 
				if (!result.peek().isControlOperator() && !insideComment.get())
					result.add(new TextBlock(System.lineSeparator(), line.from));
				
//				if (!result.isEmpty() && !insideComment.get()) {
//					if (result.peek().isControlOperator() && buffer.toString().trim().isEmpty())
//						buffer.setLength(0);
//				}

			} else // Empty line
				result.add(new TextBlock(System.lineSeparator(), line.from));
//				buffer.append(System.lineSeparator());
			
		}); // line

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
					throw new BlockBuildingError("duplicated control {begin:" + co.aliasName + "}", co.from) ;
				else if (sameAlias && co.operation == ControlBlock.CONTROL_END) {
					activeControl.put(co.operation, currentControl = co);
					return validateControlBlock(currentBlock.init(activeControl));
				} else if (sameAlias)
					activeControl.put(co.operation, currentControl = co);
				else
					throw new BlockBuildingError("missing control {begin:" + co.aliasName + "}", co.from) ;
			} else 
				currentControl.addBlock(b);
//			else if (b.isValue())
//				throw new IllegalStateException("Value not into {begin:" + currentBlock.aliasName + "}") ;
//			else if (b.isText())
//				currentBlock.addBlock(b);
//			else
//				throw new IllegalStateException("Cannot insert " + b.getClass().getSimpleName() + " into ControlBlock");
		}
		if (currentBlock.isSectionBlock())
			return currentBlock;
		throw new BlockBuildingError("missing control {begin:" + currentBlock.aliasName + "}", currentBlock.from);
	}
	private static ControlBlock validateControlBlock(ControlBlock cb) {
		// My alias MUST be defined at least once
		// Also the defining block should/could be flagged to be processed first!
		
		
		// Validate that all the control operators (except begin) do not reference the control alias.
		// FIXME error message must be clearer
		if (cb.before != null && cb.before.getValues().stream().anyMatch(v->v.getAliases().stream().anyMatch(a->a.equals(cb.aliasName))))
			throw new BlockBuildingError("invalid reference in {before " + cb.aliasName + "}", cb.before.from);
		if (cb.between != null && cb.between.getValues().stream().anyMatch(v->v.getAliases().stream().anyMatch(a->a.equals(cb.aliasName))))
			throw new BlockBuildingError("invalid reference in {between " + cb.aliasName + "}", cb.between.from);
		if (cb.after != null && cb.after.getValues().stream().anyMatch(v->v.getAliases().stream().anyMatch(a->a.equals(cb.aliasName))))
			throw new BlockBuildingError("invalid reference in {after " + cb.aliasName + "}", cb.after.from);
		if (cb.empty != null && cb.empty.getValues().stream().anyMatch(v->v.getAliases().stream().anyMatch(a->a.equals(cb.aliasName))))
			throw new BlockBuildingError("invalid reference in {empty " + cb.aliasName + "}", cb.empty.from);

		// TODO Validate all aliases used everywhere are defined in block parents... 
	
		return cb;
	}
	private static ValueBlock parseValue(Value value) {
		ValueBlock result = parseValueExp(value.valueExp, parseCallExp(value.callExp), parseLogicalTestBlock(value.ifExp), value.from);
		if (result.call != null && !result.call.isValidForValue())
			throw new BlockBuildingError("alias only allowed at the end of a call in " + result.call.name, result.from);
		return result;
	}
	private static LogicalTestBlock parseLogicalTestBlock(IfExp exp) {
		if (exp == null)
			return null;
		List<BinaryTestBlock> ops = exp.logicalExp.binaryExp.stream().map(x -> parseBinaryTestBlock(x)).collect(Collectors.toList());
		if (exp.logicalExp.logicalOp != null) {
			// TODO Validation: All logical ops must be equals
			if (exp.logicalExp.logicalOp.stream().distinct().limit(2).count() != 1)
				throw new BlockBuildingError("mixing logical operators is not allowed " + exp.logicalExp.logicalOp, exp.from);
			return new LogicalTestBlock(exp.logicalExp.from, ops, exp.logicalExp.logicalOp.get(0));
		} else if (ops.size() == 1)
			return new LogicalTestBlock(exp.logicalExp.from, ops, "&&"); // default to AND
		else
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
		ValueBlock result = new ValueBlock(valueExp.unaryOp, valueExp.valueArg, valueExp.aliasName, cb, tb, vp, from);
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
//		ControlExp ce = lineExp.controlExp;
//		ControlBlock[] array = controls.getOrDefault(ce.aliasName, new ControlBlock[MAX_CONTROL]);
//		int i = extractControlId(ce.controlOp);
		// TODO Validate order
		// TODO Validate not already filled
//		array[i] = ce;
//		controls.put(ce.aliasName, array);
		int operationId = ControlBlock.extractControlId(ce.controlOp);
		boolean isEndOfBlock = operationId == ControlBlock.CONTROL_END;
		return new ControlOperator(isEndOfBlock, ce.aliasName, operationId, ce.from);
	}
}
