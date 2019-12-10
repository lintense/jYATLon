package jyatlon.core;

import jyatlon.core.Struct.ControlExp;

/**
 * @author linte
 * SRP Data structure that holds all the compiles template infos.
 */
public abstract class Block {

	public static class ControlBlock {
		
		static private final String AVAILABLE_CONTROLS = "begin    |before   |between  |after    |end      |empty    |prepare  |call     ";
		public static int extractControlId(String controlName) {
			return AVAILABLE_CONTROLS.indexOf(controlName)/10;
		}
		public ControlBlock(String aliasName, ControlExp[] array) {
			super();
			// TODO Auto-generated constructor stub
		}
	}
	public static class PathBlock {
		
	}
	public static class TextBlock {
		
	}
	public static class ValueBlock {
		
	}
	
	
}
