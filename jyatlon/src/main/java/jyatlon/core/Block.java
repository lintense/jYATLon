package jyatlon.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author lintense
 * SRP Data structure that holds all the compiled template infos.
 * This class is a data structure, logic should not be placed here!
 * This class can be seen as a facade for the actual processing class.
 * This means that when modifying the language grammar, only the changes that
 * make it through here will have an impact on the actual processing.
 * 
 * @see BlockBuilder This object is build by BlockBuilder.
 * @see BlockProcessor This object is processed by BlockProcessor.
 */
public abstract class Block {
	public final int from;
	public Block(int from) {
		super();
		this.from = from;
	}
	boolean isControlOperator() {
		return false;
	}
	boolean isValue() {
		return false;
	}
	boolean isText() {
		return false;
	}
	boolean isControl() {
		return false;
	}
	public abstract List<ValueBlock> getValues();

	public static class ControlBlock extends Block {
		
		// Do not change this initialization String
		static final String AVAILABLE_CONTROLS = "        |{begin   |{before  |{between |{after   |{empty   |{end     |init     |call     ";
		static final int CONTROLS_WORD_LENGTH = AVAILABLE_CONTROLS.indexOf('|') + 1;
		
		static final int CONTROL_BEGIN = extractControlId("begin");
		static final int CONTROL_BEFORE = extractControlId("before");
		static final int CONTROL_BETWEEN = extractControlId("between");
		static final int CONTROL_AFTER = extractControlId("after");
		static final int CONTROL_EMPTY = extractControlId("empty");
		static final int CONTROL_END = extractControlId("end");
		
		static int extractControlId(String controlName) {
			return AVAILABLE_CONTROLS.indexOf(controlName)/CONTROLS_WORD_LENGTH;
		}
		
		final String aliasName;
		final Block parent;
		final ControlOperator begin;
		ControlOperator before;
		ControlOperator between;
		ControlOperator after;
		ControlOperator empty;
		ControlOperator end;
		
		public ControlBlock(PathBlock parent, int from) {
			super(from);
			this.parent = parent;
			this.aliasName = null;
			this.begin = new ControlOperator(false, null, ControlBlock.CONTROL_BEGIN, from);
			this.end = null;
		}
		public ControlBlock(ControlBlock parent, ControlOperator beginControl, int from){
			super(from);
			this.parent= parent;
			this.aliasName = beginControl.aliasName;
			this.begin = beginControl;
		}
		/**
		 * @param lastIndex
		 * @param subControls
		 * @param ops
		 * ControlBlock must be initialized since they must be created up front in order to provide its parent upon creation.
		 */
		public ControlBlock init(Map<Integer, ControlOperator> ops){
			this.before = ops.get(CONTROL_BEFORE);
			this.between = ops.get(CONTROL_BETWEEN);
			this.after = ops.get(CONTROL_AFTER);
			this.empty = ops.get(CONTROL_EMPTY);
			this.end = ops.get(CONTROL_END);
			return this;
		}
		boolean isSectionBlock() {
			return aliasName == null;
		}
		boolean isControl() {
			return true;
		}
		public List<ValueBlock> getValues(){
			return begin.getValues();
		}
	}
	public static class ControlOperator extends Block {
		final boolean isEndOfBlock;
		final String aliasName;
		final int operation;
		final List<Block> blocks = new ArrayList<Block>();
		
		public ControlOperator(boolean isEndOfBlock, String aliasName, int operation, int from) {
			super(from);
			this.isEndOfBlock = isEndOfBlock;
			this.aliasName = aliasName;
			this.operation = operation;
		}
		@Override
		boolean isControlOperator() {
			return true;
		}
		public void addBlock(Block b) {
			blocks.add(b);
		}
		public List<ValueBlock> getValues(){
			return blocks.stream().map(b->b.getValues()).flatMap(List::stream).collect(Collectors.toList());
		}
		public boolean hasControl() {
			return blocks.stream().anyMatch(b->b.isControl());
		}
	}
	public static class PathBlock extends Block {
		final String pathname;
		final ValuePath path;
		final boolean isRelative;
		List<Block> blocks;
		final List<String> args;
		private ControlBlock controlBlock;
		
		public PathBlock(String pathname, ValuePath path, boolean isRelative, List<Block> blocks, List<String> args, int from) {
			super(from);
			this.pathname = pathname;
			this.path = path;
			this.isRelative = isRelative;
			this.args = args;
			this.blocks = blocks;
		}
		public void init(ControlBlock cb) {
			this.controlBlock = cb;
		}
		public List<ValueBlock> getValues(){
			return blocks.stream().map(b->b.getValues()).flatMap(List::stream).collect(Collectors.toList());
		}
		public ControlBlock getControlBlock() {
			return controlBlock;
		}
	}
	public static class TextBlock extends Block {
		final String text;

		public TextBlock(String text, int from) {
			super(from);
			this.text = text;
		}
		boolean isText() {
			return true;
		}
		public List<ValueBlock> getValues() {
			return Collections.emptyList();
		}
	}
	// Do not put logic in this class
	// When something can be pre-computed, put the result here
	public static class ValueBlock extends Block {
		final String unaryOp;
		final String indexOp;
		final String argName;
		final String aliasName;
		final CallBlock call;
		final LogicalTestBlock test;
		final List<OperationBlock> ops = new ArrayList<>();
		final ValuePath valuePath;
		
		public ValueBlock(String unaryOp, String indexOp, String argName, String aliasName, CallBlock call, LogicalTestBlock test, ValuePath valuePath, int from) {
			super(from);
			this.unaryOp = unaryOp;
			this.indexOp = indexOp;
			this.argName = argName;
			this.aliasName = aliasName;
			this.call = call;
			this.test = test;
			this.valuePath = valuePath;
		}
		boolean isValue() {
			return true;
		}
		void addOperation(OperationBlock op) {
			ops.add(op);
		}
		/**
		 * @return A list of pure aliases for that value.
		 * Aliases that are used as arguments do not count.
		 * Aliases are returned in a Set for convenience.
		 */
		public Set<String> getAliases(){
			Set<String> result = new HashSet<String>();
			result.add(aliasName);
			for (OperationBlock op : ops)
				result.add(op.aliasName);
			result.remove(null);
			return result;
		}
		public List<ValueBlock> getValues(){
			List<ValueBlock> result = new ArrayList<>();
			ops.forEach(op -> result.addAll(op.getValues())); // will be needed before the value itself
			result.addAll(call != null ? call.getValues() : Collections.emptyList());
			result.addAll(test != null ? test.getValues() : Collections.emptyList());
			result.add(this);
			return result;
		}
		public String getFinalAliasName() {
			return !ops.isEmpty()
					? ops.get(ops.size()-1).aliasName
					: (aliasName != null
							? aliasName
							: argName);
		}
	}
	public static class OperationBlock extends Block {
		final String methodName;
		final String aliasName;
		final List<ValueBlock> args = new ArrayList<ValueBlock>();
		
		public OperationBlock(String methodName, String aliasName, int from) {
			super(from);
			this.methodName = methodName;
			this.aliasName = aliasName;
		}
		void addArgument(ValueBlock arg) {
			args.add(arg);
		}
		public List<ValueBlock> getValues(){
			return args;
		}
	}
	public static class CallBlock extends Block {
		final List<ValuePath> paths;
		final boolean isRelative;
		final List<ValueBlock> args; // empty when into a PathBlock.path
		private Map<ValuePath,PathBlock> toCall = new HashMap<>(); // FIXME
		
		public CallBlock(List<ValuePath> paths, boolean isRelative, List<ValueBlock> args, int from) {
			super(from);
			this.paths = paths;
			this.args = args;
			this.isRelative = isRelative;
		}
		public void addBlockToCall(String classname, PathBlock call) {
			toCall.put(call.path, call);
		}
		public PathBlock getBlockToCall(Combination combination, ValuePath vp) { // TODO !isRelative
			// TODO validate there are no conflicting ClassName in value block call paths...
			assert isRelative || toCall.keySet().iterator().next().length() == combination.pathCtx.length() + 1;
			
			// For now, just check the last object class
			String className = Utils.getClassName(vp.getObject());
			for (Iterator<Entry<ValuePath, PathBlock>> i = toCall.entrySet().iterator(); i.hasNext();) {
				Map.Entry<ValuePath,Block.PathBlock> e = i.next();
				if (className.endsWith(e.getKey().getClassName()))
					return e.getValue();
			}
			return null;
		}
		public String[] getPossibleCalls() {
			String[] result = new String[toCall.keySet().size()];
			return toCall.keySet().stream().map(p->p.getClassName()).collect(Collectors.toList()).toArray(result);
		}
		public List<ValueBlock> getValues() {
			return args.stream().map(b->b.getValues()).flatMap(List::stream).collect(Collectors.toList());
		}
	}
	public static class LogicalTestBlock extends Block {
		public final List<BinaryTestBlock> bexp;
		public final List<LogicalTestBlock> lexp;
		public final String op;
		public LogicalTestBlock(int from, List<LogicalTestBlock> lexp, List<BinaryTestBlock> bexp, String op) {
			super(from);
			this.bexp = bexp;
			this.lexp = lexp;
			this.op = op;
		}
		public List<ValueBlock> getValues(){
			List<ValueBlock> result = new ArrayList<>();
			result.addAll(bexp != null ? bexp.stream().map(b->b.getValues()).flatMap(List::stream).collect(Collectors.toList()) : Collections.emptyList());
			result.addAll(lexp != null ? lexp.stream().map(b->b.getValues()).flatMap(List::stream).collect(Collectors.toList()) : Collections.emptyList());
			return result;
		}
	}
	public static class BinaryTestBlock extends Block {
		public final String op;
		public final List<ValueBlock> values;
		public BinaryTestBlock(int from, String op, List<ValueBlock> values) {
			super(from);
			this.op = op;
			this.values = values;
		}
		public List<ValueBlock> getValues(){
			return values;
		}
	}
}
