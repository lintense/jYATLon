package jyatlon.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import jyatlon.core.Block.ControlBlock;
import jyatlon.core.Block.ControlOperator;
import jyatlon.core.Block.OperationBlock;
import jyatlon.core.Block.PathBlock;
import jyatlon.core.Block.TextBlock;
import jyatlon.core.Block.ValueBlock;
import jyatlon.core.Path.CallPath;
import jyatlon.core.Struct.ControlExp;
import jyatlon.core.Struct.Line;
import jyatlon.core.Struct.Operation;
import jyatlon.core.Struct.PathExp;
import jyatlon.core.Struct.Section;
import jyatlon.core.Struct.Template;
import jyatlon.core.Struct.ValueExp;
import jyatlon.generated.YATLLexer;

/**
 * @author linte
 * SRP - A stateless "Struct to Block" data structure converter.
 * Struct is used as a facade when the Template Language is updated
 * Block is the actual structure that is to be processed.
 */
public class BlockBuilder {
	
	public static final String ROOT = Utils.unquote(YATLLexer.VOCABULARY.getLiteralName(YATLLexer.ROOT));
	
	private final String fullText;
	public BlockBuilder(String fullText) {
		super();
		this.fullText = fullText;
	}
	private String getStructText(Struct s) {
		return fullText.substring(s.from, s.to);
	}

	/**
	 * @param t
	 * @return
	 * PathBlock contains a list of <Text|Value>
	 */
	PathBlock parseTemplate(Template t) {
		
		if (t.section == null)
			return null;
		
		Map<String,PathBlock> pathBlocks = new HashMap<String,PathBlock>();
		t.section.forEach(section -> {
			PathBlock b = parseSection(section);
			pathBlocks.put(b.pathname, b);
		});
		return pathBlocks.get(ROOT);
	}
		
		
	PathBlock parseSection(Section section){
		
//		Map<Section,List<Line>> sectionLines = new HashMap();
		if (section.line == null)
			return null;

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
		
		// Process path
		CallPath cp = parseCallPath(section.pathExp);
		
		// Process lines
		List<Block> blocks = parseLines(lines);
		PathBlock pb =  new PathBlock(cp == null ? ROOT : cp.getPathName(), cp, blocks);
		
		// Compute value blocks
		ControlBlock cb = extractControlBlock(new ControlBlock(pb, ROOT), blocks.iterator());
//		cb.setValues(extractControlValues(cb));
		pb.init(cb);
		
		return pb;
	}
	CallPath parseCallPath(PathExp path) {
		// First Section Path is null for now
		// Useless to create a Path here since we dont know the actual root Object type
		if (path == null)
			return null;
		
		return null;
	}
	List<Block> parseLines(List<Line> lines){
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
						String esc = getStructText(lineExp);
						buffer.append(esc.charAt(esc.length()-1)); // Remove escape code
					}
				} else if (lineExp.rawText != null) {
					if (!insideComment.get()) {
						buffer.append(getStructText(lineExp));
					}
				} else if (lineExp.value != null) {
					if (!insideComment.get()) {
						if (buffer.length() > 0) {
							result.add(new TextBlock(buffer.toString()));
							buffer.setLength(0);
						}
						if (lineExp.value.valueExp != null)
							result.add(parseValue(lineExp.value.valueExp));
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

	ControlBlock extractControlBlock(ControlBlock currentBlock, Iterator<Block> i) {

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
	ValueBlock parseValue(ValueExp value) {
		// For now, Value is expected to be a valueExp...
		ValueBlock result = new ValueBlock(value.valueArg, value.aliasName);
		if (value.operation != null && !value.operation.isEmpty())
			value.operation.forEach(o->result.addOperation(parseOperation(o)));
		return result;
	}
	OperationBlock parseOperation(Operation op) {
		OperationBlock result = new OperationBlock(op.methodName, op.aliasName);
		if (op.argExp != null && op.argExp.valueExp != null && !op.argExp.valueExp.isEmpty())
			op.argExp.valueExp.forEach(a->result.addArgument(parseValue(a)));
		return result;
	}
	ControlOperator parseControlExp(ControlExp ce) {
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
