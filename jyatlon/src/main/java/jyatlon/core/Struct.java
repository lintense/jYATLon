package jyatlon.core;

/**
 *	-----------------------------------------------------------------------------
 *	----          	  GENERATED on Thu Sep 03 21:28:23 EDT 2020              ----
 *	-----------------------------------------------------------------------------
 *	DO NOT EDIT THIS FILE - THIS IS A GENERATED CLASS - YOUR CHANGES WILL BE LOST
 *	-----------------------------------------------------------------------------
 *	You must comply with the following instructions in order to do a modification
 *	in this file. Not doing so will see all your changes erased upon regeneration
 *
 *	What file(s) to edit:
 *		- jyatlon/src/main/antlr4/generated/YATL.g4
 *	
 *	How to generate this file (In Eclipse):
 *		- Run As > Java Application: @link StructGen
 *		- Copy console content into file: jyatlon/src/main/java/jyatlon/core/Struct.java
 *
 *	Generator class:
 *		- @link StructGen
 */

import java.util.List;

public class Struct {
	protected final int from;
	protected final int to;

	public Struct(int from, int to) {
		super();
		this.from = from;
		this.to = to;
	}
	public static class AliasExp extends Struct {
		final List<String> aliasName;
		public AliasExp(int from, int to, List<String> aliasName){
			super(from, to);
			this.aliasName = aliasName;
		}
	}
	public static class ArgExp extends Struct {
		final List<ValueExp> valueExp;
		public ArgExp(int from, int to, List<ValueExp> valueExp){
			super(from, to);
			this.valueExp = valueExp;
		}
	}
	public static class BinaryExp extends Struct {
		final String binaryOp;
		final List<ValueExp> valueExp;
		public BinaryExp(int from, int to, String binaryOp, List<ValueExp> valueExp){
			super(from, to);
			this.binaryOp = binaryOp;
			this.valueExp = valueExp;
		}
	}
	public static class CallExp extends Struct {
		final PathExp pathExp;
		final ArgExp argExp;
		public CallExp(int from, int to, PathExp pathExp, ArgExp argExp){
			super(from, to);
			this.pathExp = pathExp;
			this.argExp = argExp;
		}
	}
	public static class ControlExp extends Struct {
		final String controlOp;
		final String aliasName;
		public ControlExp(int from, int to, String controlOp, String aliasName){
			super(from, to);
			this.controlOp = controlOp;
			this.aliasName = aliasName;
		}
	}
	public static class IfExp extends Struct {
		final LogicalExp logicalExp;
		public IfExp(int from, int to, LogicalExp logicalExp){
			super(from, to);
			this.logicalExp = logicalExp;
		}
	}
	public static class Line extends Struct {
		final List<LineExp> lineExp;
		public Line(int from, int to, List<LineExp> lineExp){
			super(from, to);
			this.lineExp = lineExp;
		}
	}
	public static class LineExp extends Struct {
		final Value value;
		final String commentOp;
		final ControlExp controlExp;
		final String rawText;
		final String escapedChar;
		public LineExp(int from, int to, Value value, String commentOp, ControlExp controlExp, String rawText, String escapedChar){
			super(from, to);
			this.value = value;
			this.commentOp = commentOp;
			this.controlExp = controlExp;
			this.rawText = rawText;
			this.escapedChar = escapedChar;
		}
	}
	public static class LogicalExp extends Struct {
		final List<LogicalExp> logicalExp;
		final List<BinaryExp> binaryExp;
		final List<String> logicalOp;
		public LogicalExp(int from, int to, List<LogicalExp> logicalExp, List<BinaryExp> binaryExp, List<String> logicalOp){
			super(from, to);
			this.logicalExp = logicalExp;
			this.binaryExp = binaryExp;
			this.logicalOp = logicalOp;
		}
	}
	public static class Operation extends Struct {
		final String methodName;
		final ArgExp argExp;
		final String aliasName;
		public Operation(int from, int to, String methodName, ArgExp argExp, String aliasName){
			super(from, to);
			this.methodName = methodName;
			this.argExp = argExp;
			this.aliasName = aliasName;
		}
	}
	public static class PathArg extends Struct {
		final String pathName;
		final String aliasName;
		public PathArg(int from, int to, String pathName, String aliasName){
			super(from, to);
			this.pathName = pathName;
			this.aliasName = aliasName;
		}
	}
	public static class PathExp extends Struct {
		final String anyPathOp;
		final List<PathArg> pathArg;
		public PathExp(int from, int to, String anyPathOp, List<PathArg> pathArg){
			super(from, to);
			this.anyPathOp = anyPathOp;
			this.pathArg = pathArg;
		}
	}
	public static class Section extends Struct {
		final AliasExp aliasExp;
		final List<String> commentOp;
		final PathExp pathExp;
		final List<Line> line;
		final List<String> rawText;
		public Section(int from, int to, AliasExp aliasExp, List<String> commentOp, PathExp pathExp, List<Line> line, List<String> rawText){
			super(from, to);
			this.aliasExp = aliasExp;
			this.commentOp = commentOp;
			this.pathExp = pathExp;
			this.line = line;
			this.rawText = rawText;
		}
	}
	public static class Template extends Struct {
		final List<Section> section;
		public Template(int from, int to, List<Section> section){
			super(from, to);
			this.section = section;
		}
	}
	public static class Value extends Struct {
		final CallExp callExp;
		final ValueExp valueExp;
		final IfExp ifExp;
		public Value(int from, int to, CallExp callExp, ValueExp valueExp, IfExp ifExp){
			super(from, to);
			this.callExp = callExp;
			this.valueExp = valueExp;
			this.ifExp = ifExp;
		}
	}
	public static class ValueExp extends Struct {
		final String valueArg;
		final String indexOp;
		final List<Operation> operation;
		final String unaryOp;
		final String aliasName;
		public ValueExp(int from, int to, String valueArg, String indexOp, List<Operation> operation, String unaryOp, String aliasName){
			super(from, to);
			this.valueArg = valueArg;
			this.indexOp = indexOp;
			this.operation = operation;
			this.unaryOp = unaryOp;
			this.aliasName = aliasName;
		}
	}
}

