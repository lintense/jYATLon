package jyatlon.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jyatlon.core.Block.CallBlock;
import jyatlon.core.Block.ControlBlock;
import jyatlon.core.Block.ControlOperator;
import jyatlon.core.Block.OperationBlock;
import jyatlon.core.Block.PathBlock;
import jyatlon.core.Block.TextBlock;
import jyatlon.core.Block.ValueBlock;
import jyatlon.core.Struct.CallExp;
import jyatlon.core.Struct.ControlExp;
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
 * Struct is used as a facade when the Template Language is updated
 * Block is the actual structure that is to be processed.
 * TODO all methods should be static to ensure thread safety
 */
public class BlockBuilder {
	
	public static final String ROOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.ROOT));
	
//	private final String fullText;
//	public BlockBuilder(String fullText) {
//		super();
//		this.fullText = fullText;
//	}
	private static String getStructText(String fullText, Struct s) {
		return fullText.substring(s.from, s.to);
	}

	/**
	 * @param t
	 * @return
	 * PathBlock contains a list of <Text|Value>
	 */
	public static Map<String,PathBlock> parseTemplate(String fullText, Template t) {
		
		if (t.section == null)
			return null;
		
		Map<String,PathBlock> pathBlocks = new HashMap<String,PathBlock>();
		t.section.forEach(section -> {
			PathBlock b = parseSection(fullText, section);
			if (pathBlocks.put(b.pathname, b) != null)
				throw new IllegalStateException("Duplicated path name: " + b.pathname);
		});

		
		// Compute the destination of each value call blocks
		pathBlocks.values().stream().forEach(pb->pb.getValues().stream().filter(vb->vb.call != null).forEach(vb->{
			
			// If the call is absolute we check only for an exact match
			// First check if there exist an unambiguous matching absolute destination
			PathBlock toCall = null;
			if (!vb.call.isRelative) {
				toCall = getAbsolutePathBlockToCall(pathBlocks, vb.call);
			} else {
				// Add parent block path to call block if call is relative
				CallBlock newCB = pb.path != null ? new CallBlock(pb.path.path.add(vb.call.path), pb.path.isRelative) : vb.call;
				
				// If new call block is absolute (as by parent) then check again for an exact match
				toCall = newCB.isRelative
						? getRelativePathBlockToCall(pathBlocks, newCB)
						: getAbsolutePathBlockToCall(pathBlocks, newCB);
			}
			
			if (toCall == null)
				throw new IllegalStateException("Cannot find destination for " + vb.call.path.getPathName());
			// TODO Add detection for possible infinite loop (warn user)
			
			vb.call.setBlockToCall(toCall);
		}));
		
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
				throw new IllegalStateException("Many destination found for call " + call.name);
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
			throw new IllegalStateException("Ambiguous destination for call " + newCB.path.classes);
	
		return null;
	}
	private static PathBlock parseSection(String fullText, Section section){
		
		// Process path
		CallBlock cp = parsePathExp(section.pathExp);
		
//		Map<Section,List<Line>> sectionLines = new HashMap();
		if (section.line == null)
			return new PathBlock(cp == null ? ROOT : cp.name, cp, Collections.emptyList());

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
		PathBlock pb =  new PathBlock(cp == null ? ROOT : cp.name, cp, blocks);
		
		// Compute value blocks
		ControlBlock cb = extractControlBlock(new ControlBlock(pb), blocks.iterator());
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

		return new CallBlock(parsePathArg(path.pathArg), path.anyPathOp != null);
	}
	private static Path parsePathArg(List<PathArg> pathArgs) {
		Path p = null;
		for (PathArg pathArg : pathArgs) {
			p = p == null 
					? (new Path(pathArg.pathName, pathArg.aliasName, null)) 
					: p.add(pathArg.pathName, pathArg.aliasName, null);
		}
		return p;
	}
	private static List<Block> parseLines(String fullText, List<Line> lines){
		Stack<Block> result = new Stack<Block> ();
		StringBuffer buffer = new StringBuffer();
//		Map<String,ControlBlock[]> controls = new HashMap();

		lines.forEach(line -> {
			
			AtomicBoolean insideComment = new AtomicBoolean(false);
			line.lineExp.forEach(lineExp -> {
				
				if (lineExp.commentOp != null) {
					insideComment.set(!insideComment.get());
				} else if (lineExp.controlExp != null) {
					if (!insideComment.get()) {
						if (buffer.length() > 0) {
							result.add(new TextBlock(buffer.toString()));
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
							result.add(new TextBlock(buffer.toString()));
							buffer.setLength(0);
						}
						if (lineExp.value.valueExp != null)
							result.add(parseValue(lineExp.value));
						else
							throw new IllegalStateException("To be implemented"); // FIXME
					}
				} else {
					throw new IllegalStateException("Not yet implemented line exp type");
				}
			});

			// Add new line if needed
			if (!result.isEmpty() && !insideComment.get()) {
				if (result.peek().isControlOperator() && buffer.toString().trim().isEmpty())
					buffer.setLength(0);
			}

		}); // line
		if (buffer.length() > 0)
			result.add(new TextBlock(buffer.toString()));
		return result;
	}
	private static ControlBlock extractControlBlock(ControlBlock currentBlock, Iterator<Block> i) {

		Map<Integer,ControlOperator> activeControl = new HashMap<Integer,ControlOperator>();
		while (i.hasNext()) {
			Block b = i.next();
			if (b.isControlOperator()) {
				ControlOperator co = (ControlOperator)b;
				boolean sameAlias = co.aliasName.equals(currentBlock.aliasName);
				if (!sameAlias && co.operation == ControlBlock.CONTROL_BEGIN) {
					currentBlock.addBlock(extractControlBlock(new ControlBlock(currentBlock, co), i)); // Extract sub block
				} else if (co.operation == ControlBlock.CONTROL_BEGIN)
					throw new IllegalStateException("Duplicated {begin:" + co.aliasName + "}") ;
				else if (sameAlias && co.operation == ControlBlock.CONTROL_END) {
					activeControl.put(co.operation, co);
					return currentBlock.init(activeControl);
				} else if (sameAlias)
					activeControl.put(co.operation, co);
				else
					throw new IllegalStateException("Missing {begin:" + co.aliasName + "}") ;
			} else if (b.isValue() && activeControl.isEmpty())
				currentBlock.addBlock(b);
			else if (b.isValue())
				throw new IllegalStateException("Value not into {begin:" + currentBlock.aliasName + "}") ;
			else if (b.isText())
				currentBlock.addBlock(b);
			else
				throw new IllegalStateException("Cannot insert " + b.getClass().getSimpleName() + " into ControlBlock");
		}
		if (currentBlock.isSectionBlock())
			return currentBlock;
		throw new IllegalStateException("Missing {begin:" + currentBlock.aliasName + "}");
	}
	private static ValueBlock parseValue(Value value) {
		ValueBlock result = new ValueBlock(value.valueExp.valueArg, value.valueExp.aliasName, parseCallExp(value.callExp));
		if (value.valueExp.operation != null && !value.valueExp.operation.isEmpty())
			value.valueExp.operation.forEach(o->result.addOperation(parseOperation(o)));
		
		// TODO validate that
		if (result.call != null && !result.call.isValidForValue())
			throw new IllegalStateException("Alias only allowed at the end of a call in " + result.call.name);
		return result;
	}
	private static ValueBlock parseValueExp(ValueExp value) {
		ValueBlock result = new ValueBlock(value.valueArg, value.aliasName, null);
		if (value.operation != null && !value.operation.isEmpty())
			value.operation.forEach(o->result.addOperation(parseOperation(o)));
		return result;
	}
	private static OperationBlock parseOperation(Operation op) {
		OperationBlock result = new OperationBlock(op.methodName, op.aliasName);
		if (op.argExp != null && op.argExp.valueExp != null && !op.argExp.valueExp.isEmpty())
			op.argExp.valueExp.forEach(a->result.addArgument(parseValueExp(a)));
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
		return new ControlOperator(isEndOfBlock, ce.aliasName, operationId);
	}
	/* CRAP
	ControlBlock extractControlBlocks(PathBlock currentSection, List<Block> sectionBlocks) {
		
		// Process resulting list to insert control operators into their parent control block	
		Stack<List<ControlBlock>> subControls = new Stack<List<ControlBlock>>();
		subControls.push(new ArrayList<ControlBlock>());
		
		// Push new Control for this section
		Stack<ControlBlock> currentControlBlock = new Stack<ControlBlock> ();
		currentControlBlock.push(new ControlBlock(currentSection));

		// All the control blocks that are currently being parsed
		int index = 0;
		Map<String,Map<Integer,ControlOperator>> activeControl = new HashMap<String,Map<Integer,ControlOperator>>();
		for (Block block : sectionBlocks) {
			if (block.isControlOperator()) {
				ControlOperator co = (ControlOperator)block;
				if (co.operation == ControlBlock.CONTROL_BEGIN) { // Add to active map
					currentControlBlock.push(new ControlBlock(currentControlBlock.peek(), index, sectionBlocks, co.aliasName));
					subControls.peek().add(currentControlBlock.peek());
					subControls.push(new ArrayList<ControlBlock>());
					Map<Integer,ControlOperator> inner = new HashMap<Integer,ControlOperator>();
					inner.put(co.operation, co);
					activeControl.put(co.aliasName, inner);
				} else if (co.operation == ControlBlock.CONTROL_END) { // Remove from active map
					Map<Integer,ControlOperator> inner = activeControl.remove(co.aliasName);
					inner.put(co.operation, co);
					ControlBlock cb = currentControlBlock.pop();
					if (!cb.aliasName.equals(co.aliasName))
						throw new IllegalStateException("Invalid imbrication. Expecting {end:" + cb.aliasName + "}");
					cb.init(index, subControls.pop(), inner);
				} else {
					if (!activeControl.containsKey(co.aliasName))
						throw new IllegalStateException("Missing {begin:" + co.aliasName + "}") ;
					activeControl.get(co.aliasName).put(co.operation,co);
				}
			}
			index++;
		};
		ControlBlock cb = currentControlBlock.pop();
		if (currentControlBlock.size() > 0)
			throw new IllegalStateException("Missing {end:" + cb.aliasName + "}") ;
		
		
		cb.init(index, subControls.pop(), Collections.emptyMap());
		return cb;
	}
	*/
}
