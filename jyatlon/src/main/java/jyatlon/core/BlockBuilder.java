package jyatlon.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jyatlon.core.Block.ControlBlock;
import jyatlon.core.Block.PathBlock;
import jyatlon.core.Struct.ControlExp;
import jyatlon.core.Struct.Line;
import jyatlon.core.Struct.LineExp;
import jyatlon.core.Struct.Template;

/**
 * @author linte
 * SRP - A stateless Struct to Block data structure converter.
 * Struct is used as a facade when the Template Language is updated
 * Block is the actual structure that is to be processed.
 */
public class BlockBuilder {

	public static PathBlock extractBlock(Template t) {
		
		if (t.section == null)
			return null;
		
		t.section.forEach(section -> {
		
			if (section.line != null) {
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
				section.setProperty("Lines", lines);
				
				// Set list of non comment expressions
				List<Struct> exps = section.line.stream().flatMap(l -> l.getStructList("Expressions").stream()).collect(Collectors.toList());
				section.setProperty("Expressions", exps);
				
				// Init control blocks
		//		private static final String ROOT = YATLParser.VOCABULARY.getLiteralName(YATLParser.ROOT).replace("'", "");
				Map<String,ControlExp[]> controls = new HashMap();
				
				exps.forEach(e -> {
					ControlExp ce = ((LineExp)e).controlExp;
					if (ce != null) {
						ControlExp[] array = controls.getOrDefault(ce.aliasName, new ControlExp[5]);
						int i = ControlBlock.extractControlId(ce.controlOp);
						// TODO Validate order
						// TODO Validate not already filled
						array[i] = ce;
						controls.put(ce.aliasName, array);
					}
				});
				
				// Set the map of all ControlBlocks
				Map<String,ControlBlock> blockMap = new HashMap();
				controls.forEach((k,v) -> blockMap.put(k, new ControlBlock(k,v)));

			}
			
		}); // Section
		
	}
}
