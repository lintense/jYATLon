package jyatlon.core;

import java.util.ArrayList;
import java.util.List;

import jyatlon.core.Path.CallPath;
import jyatlon.core.Struct.ValueExp;

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
	public static class ControlBlock extends Block {
		final String aliasName;
		final List<Block> blocks = new ArrayList();
		
		public ControlBlock(String aliasName) {
			super();
			this.aliasName = aliasName;
		}
		
	}
	public static class ControlOperator extends Block {
		final boolean isEndOfBlock;
		final String aliasName;
		final int operation;
		final List<Block> blocks = new ArrayList();
		
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
		final CallPath path;
		List<Block> blocks;
		
		public PathBlock(String pathname, CallPath path, List<Block> blocks) {
			super();
			this.pathname = pathname;
			this.path = path;
			this.blocks = blocks;
		}

	}
	public static class TextBlock extends Block {
		final String text;

		public TextBlock(String text) {
			this.text = text;
		}
		
	}
	// Do not put logic in this class
	// When something can be pre-computed, put the result here
	public static class ValueBlock extends Block {
		final ValueExp valueExp;
		
		public ValueBlock(ValueExp valueExp) {
			super();
			this.valueExp = valueExp;
		}


	}
	
	
}
