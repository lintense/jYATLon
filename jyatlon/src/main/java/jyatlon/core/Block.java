package jyatlon.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author linte
 * SRP Data structure that holds all the compiles template infos.
 * This class should be a data structure, logic should not be placed here!
 * This object is build by: BlockBuilder
 * This object is processed by: BlockProcessor
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

	public static class ControlBlock extends Block {
		
		static final String AVAILABLE_CONTROLS = "        |{begin   |{before  |{between |{after   |{empty   |{end     |prepare  |call     ";
//		private static final int CONTROL_MAX = AVAILABLE_CONTROLS.length()/10;
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
//		List<ControlBlock> subControls;
		
		public ControlBlock(PathBlock parent, int from) {
			super(from);
			this.parent = parent;
			this.aliasName = null;
			this.begin = new ControlOperator(false, null, ControlBlock.CONTROL_BEGIN, from);
			this.end = null;
		}
		public ControlBlock(ControlBlock parent, ControlOperator beginControl, int from){
			super(from);
//			this.firstBlockIndex = firstIndex;
			this.parent= parent;
			this.aliasName = beginControl.aliasName;
			this.begin = beginControl;
			
//			this.blocks = blocks;
		}
		/**
		 * @param lastIndex
		 * @param subControls
		 * @param ops
		 * ControlBlock mmust be initialized since they must be created up front in order to provide its parent upon creation.
		 */
		public ControlBlock init(Map<Integer, ControlOperator> ops){
//			this.lastBlockIndex = lastIndex;
			this.before = ops.get(CONTROL_BEFORE);
			this.between = ops.get(CONTROL_BETWEEN);
			this.after = ops.get(CONTROL_AFTER);
			this.empty = ops.get(CONTROL_EMPTY);
			this.end = ops.get(CONTROL_END);
			return this;
		}
//		void addBlock(Block b) {
//			blocks.add(b);
//		}
	
		boolean isSectionBlock() {
			return aliasName == null;
		}
		boolean isControl() {
			return true;
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
		List<ValueBlock> getValues(){
			return blocks.stream().filter(b->b.isValue()).map(b->(ValueBlock)b).collect(Collectors.toList());
		}	
	}
	public static class PathBlock extends Block {
		final String pathname;
		final CallBlock path;
		List<Block> blocks;
		ControlBlock controlBlock;
		
		public PathBlock(String pathname, CallBlock path, List<Block> blocks, int from) {
			super(from);
			this.pathname = pathname;
			this.path = path;
			this.blocks = blocks;
		}
		public void init(ControlBlock cb) {
			this.controlBlock = cb;
		}
		List<ValueBlock> getValues(){
			return blocks.stream().filter(b->b.isValue()).map(b->(ValueBlock)b).collect(Collectors.toList());
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
		
	}
	// Do not put logic in this class
	// When something can be pre-computed, put the result here
	public static class ValueBlock extends Block {
		final String unaryOp;
		final String argName;
		final String aliasName;
		final CallBlock call;
		final LogicalTestBlock test;
		final List<OperationBlock> ops = new ArrayList<>();
		final ValuePath valuePath;
		
		public ValueBlock(String unaryOp, String argName, String aliasName, CallBlock call, LogicalTestBlock test, ValuePath valuePath, int from) {
			super(from);
			this.unaryOp = unaryOp;
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
	}
	public static class CallBlock extends Block {

		public final ValuePath path;
		public final String name;
		public final boolean isRelative;
		private PathBlock toCall;
		
		public CallBlock(ValuePath path, boolean isRelative, int from) {
			super(from);
			this.path = path;
			this.isRelative = isRelative;
			this.name = computeName();
		}
		private String computeName() {
			String result = "";
			String finalAlias = path.getAlias(); // Only final alias is part of the name
			for (int i = 0; i < path.classes.length; i++)
				result += "/" + path.classes[i];
			return (isRelative ? ".../" : "") + result.substring(1) + (finalAlias != null ? ":" + finalAlias : "");
		}
		public void setBlockToCall(PathBlock toCall) {
			this.toCall = toCall;
		}
		public PathBlock getBlockToCall() {
			return toCall;
		}
		public boolean isValidForValue() {
			// Only the last alias is allowed in a value block call block
			return path.aliases.length > 0 && IntStream.range(0, path.aliases.length - 1).allMatch(a->path.aliases[a] == null);
		}
	}
	public static class LogicalTestBlock extends Block { // FIXME To be expanded later
		public final List<BinaryTestBlock> exp;
		public final String op;
		public LogicalTestBlock(int from, List<BinaryTestBlock> exp, String op) {
			super(from);
			this.exp = exp;
			this.op = op;
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
	}
}
