package jyatlon.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import jyatlon.core.Block.ControlBlock;
import jyatlon.core.Block.ControlOperator;
import jyatlon.core.Block.PathBlock;
import jyatlon.core.Block.TextBlock;
import jyatlon.core.Block.ValueBlock;
import jyatlon.core.Path.CallPath;
import jyatlon.core.Struct.ControlExp;
import jyatlon.core.Struct.Line;
import jyatlon.core.Struct.LineExp;
import jyatlon.core.Struct.Section;
import jyatlon.core.Struct.Template;
import jyatlon.core.Struct.Value;
import jyatlon.generated.YATLLexer;

/**
 * @author linte
 * SRP - A stateless Struct to Block data structure converter.
 * Struct is used as a facade when the Template Language is updated
 * Block is the actual structure that is to be processed.
 */
public class BlockBuilder {
	
	
	private final String fullText;
	public BlockBuilder(String fullText) {
		super();
		this.fullText = fullText;
	}
	private String getStructText(Struct s) {
		return fullText.substring(s.from, s.to);
	}

	
	private static final String ROOT = YATLLexer.VOCABULARY.getLiteralName(YATLLexer.ROOT);
	/**
	 * @param t
	 * @return
	 * PathBlock contains a list of <Text|Value>
	 */
	PathBlock parseTemplate(Template t) {
		
		if (t.section == null)
			return null;
		
		Map<String,PathBlock> pathBlocks = new HashMap();
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
		
		
		return new PathBlock(cp.getPathName(), cp, blocks);
	}
	List<Block> parseLines(List<Line> lines){
		Stack<Block> result = new Stack();
		StringBuffer buffer = new StringBuffer();
//		Map<String,ControlBlock[]> controls = new HashMap();

		lines.forEach(line -> {
			
			AtomicBoolean insideComment = new AtomicBoolean(false);
			line.lineExp.forEach(lineExp -> {
				
				switch (getLineExpCase(lineExp)) {
				
				case LINE_EXP_IS_COMMENT :
					insideComment.set(!insideComment.get());
					break;
				case LINE_EXP_IS_CONTROL :
					if (!insideComment.get()) {
						if (buffer.length() > 0) {
							result.add(new TextBlock(buffer.toString()));
							buffer.setLength(0);
						}
						result.add(parseControlExp(lineExp.controlExp));
					}
				case LINE_EXP_IS_ESCAPE :
					if (!insideComment.get()) {
						String esc = getStructText(lineExp);
						buffer.append(esc.charAt(esc.length()-1)); // Remove escape code
					}
				case LINE_EXP_IS_RAW_TEXT :
					if (!insideComment.get()) {
						buffer.append(getStructText(lineExp));
					}
				case LINE_EXP_IS_VALUE :
					if (!insideComment.get()) {
						if (buffer.length() > 0) {
							result.add(new TextBlock(buffer.toString()));
							buffer.setLength(0);
						}
						result.add(parseValue(lineExp.value));
					}
				default:
					throw new IllegalStateException("Not yet implemented line exp type");
				}
			});

			// Add new line if needed
			if (!insideComment.get()) {
				if (result.peek().isControlOperator() && buffer.toString().trim().isEmpty())
					buffer.setLength(0);
			}

		}); // line
		if (buffer.length() > 0)
			result.add(new TextBlock(buffer.toString()));

		
		// Process resulting list to insert control operators into their parent control block
		List<Block> temp = new ArrayList();
		result.forEach(block -> {
			
			if (block.isControlOperator()) {
				ControlOperator co = (ControlOperator)block;
				ControlBlock cb = new ControlBlock(co.aliasName);
				
				
				
				
			}
				
			
			
		});
		
		
		
		
		return result;
	}
	ValueBlock parseValue(Value value) {

		return new ValueBlock(value.valueExp); // For now, keep the struct
	}
	private static final int LINE_EXP_IS_UNKNOWN = 0; // Should never happen!
	private static final int LINE_EXP_IS_COMMENT = 1;
	private static final int LINE_EXP_IS_CONTROL = 2;
	private static final int LINE_EXP_IS_ESCAPE = 3;
	private static final int LINE_EXP_IS_RAW_TEXT = 4;
	private static final int LINE_EXP_IS_VALUE = 5;
	private static int getLineExpCase(LineExp exp) {
		return exp.commentOp != null ? LINE_EXP_IS_COMMENT
			: exp.controlExp != null ? LINE_EXP_IS_CONTROL
			: exp.escapedChar != null ? LINE_EXP_IS_ESCAPE
			: exp.rawText != null ? LINE_EXP_IS_RAW_TEXT
			: exp.value != null ? LINE_EXP_IS_VALUE
			: LINE_EXP_IS_UNKNOWN;	
	}
	
	
	private static final String AVAILABLE_CONTROLS = "        |begin    |before   |between  |after    |end      |empty    |prepare  |call     ";
//	private static final int CONTROL_MAX = AVAILABLE_CONTROLS.length()/10;
	private static final int CONTROL_END = extractControlId("end");
	private static int extractControlId(String controlName) {
		return AVAILABLE_CONTROLS.indexOf(controlName)/10;
	}
	ControlOperator parseControlExp(ControlExp ce) {
//		ControlExp ce = lineExp.controlExp;
//		ControlBlock[] array = controls.getOrDefault(ce.aliasName, new ControlBlock[MAX_CONTROL]);
//		int i = extractControlId(ce.controlOp);
		// TODO Validate order
		// TODO Validate not already filled
//		array[i] = ce;
//		controls.put(ce.aliasName, array);
		int operationId = extractControlId(ce.controlOp);
		boolean isEndOfBlock = operationId == CONTROL_END;
		return new ControlOperator(isEndOfBlock, ce.aliasName, operationId);
	}
}
