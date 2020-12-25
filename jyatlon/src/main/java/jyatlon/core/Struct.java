package jyatlon.core;

/**
 *	-----------------------------------------------------------------------------
 *	----          	  GENERATED on Thu Dec 24 22:59:01 EST 2020              ----
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
	public static class PathArg extends Struct {
		final String aliasName;
		final String pathName;
		public PathArg(int from, int to, String aliasName, String pathName){
			super(from, to);
			this.aliasName = aliasName;
			this.pathName = pathName;
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
	public static class ArgExp extends Struct {
		final List<ValueExp> valueExp;
		public ArgExp(int from, int to, List<ValueExp> valueExp){
			super(from, to);
			this.valueExp = valueExp;
		}
	}
	public static class Operation extends Struct {
		final String aliasName;
		final ArgExp argExp;
		final String methodName;
		public Operation(int from, int to, String aliasName, ArgExp argExp, String methodName){
			super(from, to);
			this.aliasName = aliasName;
			this.argExp = argExp;
			this.methodName = methodName;
		}
	}
	public static class ValueExp extends Struct {
		final String aliasName;
		final String indexOp;
		final List<Operation> operation;
		final String unaryOp;
		final String valueArg;
		public ValueExp(int from, int to, String aliasName, String indexOp, List<Operation> operation, String unaryOp, String valueArg){
			super(from, to);
			this.aliasName = aliasName;
			this.indexOp = indexOp;
			this.operation = operation;
			this.unaryOp = unaryOp;
			this.valueArg = valueArg;
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
	public static class LogicalExp extends Struct {
		final List<BinaryExp> binaryExp;
		final List<LogicalExp> logicalExp;
		final List<String> logicalOp;
		public LogicalExp(int from, int to, List<BinaryExp> binaryExp, List<LogicalExp> logicalExp, List<String> logicalOp){
			super(from, to);
			this.binaryExp = binaryExp;
			this.logicalExp = logicalExp;
			this.logicalOp = logicalOp;
		}
	}
	public static class CallExp extends Struct {
		final ArgExp argExp;
		final List<PathArg> pathArg;
		final PathExp pathExp;
		public CallExp(int from, int to, ArgExp argExp, List<PathArg> pathArg, PathExp pathExp){
			super(from, to);
			this.argExp = argExp;
			this.pathArg = pathArg;
			this.pathExp = pathExp;
		}
	}
	public static class IfExp extends Struct {
		final LogicalExp logicalExp;
		public IfExp(int from, int to, LogicalExp logicalExp){
			super(from, to);
			this.logicalExp = logicalExp;
		}
	}
	public static class Value extends Struct {
		final CallExp callExp;
		final IfExp ifExp;
		final ValueExp valueExp;
		public Value(int from, int to, CallExp callExp, IfExp ifExp, ValueExp valueExp){
			super(from, to);
			this.callExp = callExp;
			this.ifExp = ifExp;
			this.valueExp = valueExp;
		}
	}
	public static class TupleValue extends Struct {
		final String aliasName;
		final List<Operation> operation;
		final String valueArg;
		public TupleValue(int from, int to, String aliasName, List<Operation> operation, String valueArg){
			super(from, to);
			this.aliasName = aliasName;
			this.operation = operation;
			this.valueArg = valueArg;
		}
	}
	public static class TupleExp extends Struct {
		final List<TupleValue> tupleValue;
		final ValueExp valueExp;
		public TupleExp(int from, int to, List<TupleValue> tupleValue, ValueExp valueExp){
			super(from, to);
			this.tupleValue = tupleValue;
			this.valueExp = valueExp;
		}
	}
	public static class ControlExp extends Struct {
		final String aliasName;
		final String controlOp;
		final TupleExp tupleExp;
		public ControlExp(int from, int to, String aliasName, String controlOp, TupleExp tupleExp){
			super(from, to);
			this.aliasName = aliasName;
			this.controlOp = controlOp;
			this.tupleExp = tupleExp;
		}
	}
	public static class LineExp extends Struct {
		final String commentOp;
		final ControlExp controlExp;
		final String escapedChar;
		final String rawText;
		final Value value;
		public LineExp(int from, int to, String commentOp, ControlExp controlExp, String escapedChar, String rawText, Value value){
			super(from, to);
			this.commentOp = commentOp;
			this.controlExp = controlExp;
			this.escapedChar = escapedChar;
			this.rawText = rawText;
			this.value = value;
		}
	}
	public static class Line extends Struct {
		final List<LineExp> lineExp;
		public Line(int from, int to, List<LineExp> lineExp){
			super(from, to);
			this.lineExp = lineExp;
		}
	}
	public static class Section extends Struct {
		final AliasExp aliasExp;
		final List<String> commentOp;
		final List<Line> line;
		final PathExp pathExp;
		final List<String> rawText;
		public Section(int from, int to, AliasExp aliasExp, List<String> commentOp, List<Line> line, PathExp pathExp, List<String> rawText){
			super(from, to);
			this.aliasExp = aliasExp;
			this.commentOp = commentOp;
			this.line = line;
			this.pathExp = pathExp;
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
}

