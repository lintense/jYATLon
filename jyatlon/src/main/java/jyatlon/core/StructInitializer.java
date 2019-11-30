package jyatlon.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import jyatlon.core.Struct.ControlExp;
import jyatlon.core.Struct.Line;
import jyatlon.core.Struct.LineExp;
import jyatlon.core.Struct.Section;
import jyatlon.core.Struct.Template;
import jyatlon.generated.YATLParser;

/**
 * @author linte
 * SRP: This class will be called upon each Struct object creation.
 * It is responsible for initializing and validating each Struct type.
 */
public class StructInitializer {
	
	private final StringBuilder out;

	public StructInitializer() {
		super();
		this.out = new StringBuilder();
	}
	public StructInitializer(StringBuilder out) {
		super();
		this.out = out;
	}
	void init(Struct s) {
		// Do nothing
//		System.out.println(s.getClass().getSimpleName() + ".init(Struct s)");
	}
	void init(Template s) {
//		System.out.println(s.getClass().getSimpleName() + ".init(Template s)");
	}
	void init(Section s) {
		
		// Extract lines
		List<Line> lines = s.line.stream().collect(Collectors.toList());
		// Remove first and last when empty
		int first = 0;
		int last = lines.size();
		if (last > 0 && lines.get(first).lineExp == null)
			first++;
		if (last > first && lines.get(last-1).lineExp == null)
			last--;
		lines = lines.subList(first, last);
		s.setProperty("Lines", lines);
		
		// Set list of non comment expressions
		List<Struct> exps = s.line.stream().flatMap(l -> l.getStructList("Expressions").stream()).collect(Collectors.toList());
		s.setProperty("Expressions", exps);
		
		// Init control blocks
//		private static final String ROOT = YATLParser.VOCABULARY.getLiteralName(YATLParser.ROOT).replace("'", "");
		Map<String,ControlExp[]> controls = new HashMap();
		
		exps.forEach(e -> {
			ControlExp ce = ((LineExp)e).controlExp;
			if (ce != null) {
				ControlExp[] array = controls.getOrDefault(ce.aliasName, new ControlExp[5]);
				int i = "begin    |before   |between  |after    |end".indexOf(ce.controlOp)/10;
				// TODO Validate order
				// TODO Validate not already filled
				array[i] = ce;
				controls.put(ce.aliasName, array);
			}
		});
		
		Map<String,ControlBlock> blockMap = new HashMap();
		controls.forEach((k,v) -> blockMap.put(k, new ControlBlock(k,v)));
		s.setProperty("BlockMap", blockMap);
		
//		getExpStream().forEach(lineExp -> {if (lineExp.controlExp != null) System.out.println(lineExp.controlExp.controlOp);});
		
		
//		public Stream<LineExp> getExpStream(){
//			return line.stream().flatMap(Line::getExpStream);
//		}
//		public Stream<Value> getValueStream(){
//			return getExpStream().filter(exp -> exp.value != null).map(exp -> exp.value);
//		}
//		public Stream<ControlExp> getControlStream(){
//			return getExpStream().filter(exp -> exp.controlExp != null).map(exp -> exp.controlExp);
//		}
//		public void call(Path rootPath) {
//			assert pathExp.isCompatible(rootPath);
//			
//			// Compute all values
//			List<Path> result = new ArrayList();
//			List<Value> values = getValueStream().collect(Collectors.toList());
//			values.forEach(v -> result.addAll(v.getValues(rootPath))); // FIXME this is probably wrong
//			result.forEach(r -> r.toString());
//		}
		
		
	}
	
	void init(Line s) {
		// Set list of non comment expressions
		AtomicBoolean ic = new AtomicBoolean(false);
		List<LineExp> exps = s.lineExp == null 
				? Collections.emptyList()
				: s.lineExp.stream()
					.filter(exp -> ic.getAndSet(exp.comment ? !ic.get() : ic.get()) || exp.comment)
					.collect(Collectors.toList());
		s.setProperty("Expressions", exps); // Flip value on exp.comment  
	}
	
}
