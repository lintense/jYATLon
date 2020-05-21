package jyatlon.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jyatlon.core.Path.ValuePath;

/**
 * @author linte
 * SRP Data structure that holds all the compiles template infos.
 * This class should be a data structure, logic should not be placed here!
 * This object is build by: BlockBuilder
 * This object is processed by: BlockProcessor
 */
public abstract class Block {
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
		static final int CONTROL_END = extractControlId("end");
		static final int CONTROL_BEGIN = extractControlId("begin");
		static int extractControlId(String controlName) {
			return AVAILABLE_CONTROLS.indexOf(controlName)/CONTROLS_WORD_LENGTH;
		}
		
//		final int firstBlockIndex;
		final String aliasName;
		final Block parent;
		final List<Block> blocks = new ArrayList<Block>();
//		int lastBlockIndex;
		final ControlOperator begin;
		ControlOperator end;
//		List<ControlBlock> subControls;
		
		public ControlBlock(PathBlock parent) {
			super();
//			this.firstBlockIndex = 0;
//			this.lastBlockIndex = 0;
			this.parent = parent;
			this.aliasName = null;
			this.begin = null;
			this.end = null;
//			this.blocks = null;
//			this.subControls = null;
		}
		public ControlBlock(ControlBlock parent, ControlOperator beginControl){
			super();
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
			this.end = ops.get(CONTROL_END);
//			this.subControls = subControls;
			
			return this;
		}
		void addBlock(Block b) {
			blocks.add(b);
		}
		List<ValueBlock> getValues(){
			return blocks.stream().filter(b->b.isValue()).map(b->(ValueBlock)b).collect(Collectors.toList());
		}		
		boolean isSectionBlock() {
			return begin == null;
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
		
		public ControlOperator(boolean isEndOfBlock, String aliasName, int operation) {
			super();
			this.isEndOfBlock = isEndOfBlock;
			this.aliasName = aliasName;
			this.operation = operation;
		}
		@Override
		boolean isControlOperator() {
			return true;
		}
	}
	public static class PathBlock extends Block {
		final String pathname;
		final CallBlock path;
		List<Block> blocks;
		ControlBlock controlBlock;
		
		public PathBlock(String pathname, CallBlock path, List<Block> blocks) {
			super();
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

		public TextBlock(String text) {
			this.text = text;
		}
		boolean isText() {
			return true;
		}
		
	}
	// Do not put logic in this class
	// When something can be pre-computed, put the result here
	public static class ValueBlock extends Block {
		final String argName;
		final String aliasName;
		final CallBlock call;
		final List<OperationBlock> ops = new ArrayList<OperationBlock>();
		
		public ValueBlock(String argName, String aliasName, CallBlock call) {
			super();
			this.argName = argName;
			this.aliasName = aliasName;
			this.call = call;
		}
		boolean isValue() {
			return true;
		}
		void addOperation(OperationBlock op) {
			ops.add(op);
		}
		Path getPath() {
			Path p = new ValuePath(argName, aliasName, null);
			for (OperationBlock ob : ops)
				p = p.add(ob.methodName, ob.aliasName, null);
			return p;
		}


	}
	public static class OperationBlock extends Block {
		final String methodName;
		final String aliasName;
		final List<ValueBlock> args = new ArrayList<ValueBlock>();
		
		public OperationBlock(String methodName, String aliasName) {
			super();
			this.methodName = methodName;
			this.aliasName = aliasName;
		}
		void addArgument(ValueBlock arg) {
			args.add(arg);
		}
		
	}
	public static class CallBlock {

		public final Path path;
		public final String name;
		public final boolean isRelative;
		private PathBlock toCall;
		
		public CallBlock(Path path, boolean isRelative) {
			super();
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
			// TODO add alias to rep
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
	
}
