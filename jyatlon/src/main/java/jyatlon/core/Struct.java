package jyatlon.core;

import java.util.List;

public class Struct {
	protected final int from;
	protected final int to;

	public Struct(int from, int to) {
		super();
		this.from = from;
		this.to = to;
	}
	public static class ArgExp extends Struct {
		final List<ValueExp> valueExp;
		public ArgExp(int from, int to, List<ValueExp> valueExp){
			super(from, to);
			this.valueExp = valueExp;
		}
	}
	public static class BinaryExp extends Struct {
		final String unaryOp;
		final List<String> binaryOp;
		final List<ValueExp> valueExp;
		public BinaryExp(int from, int to, String unaryOp, List<String> binaryOp, List<ValueExp> valueExp){
			super(from, to);
			this.unaryOp = unaryOp;
			this.binaryOp = binaryOp;
			this.valueExp = valueExp;
		}
	}
	public static class CallExp extends Struct {
		final PathExp pathExp;
		public CallExp(int from, int to, PathExp pathExp){
			super(from, to);
			this.pathExp = pathExp;
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
		final String escapedChar;
		final String rawText;
		final ControlExp controlExp;
		final String commentOp;
		public LineExp(int from, int to, Value value, String escapedChar, String rawText, ControlExp controlExp, String commentOp){
			super(from, to);
			this.value = value;
			this.escapedChar = escapedChar;
			this.rawText = rawText;
			this.controlExp = controlExp;
			this.commentOp = commentOp;
		}
	}
	public static class LogicalExp extends Struct {
		final List<String> logicalOp;
		final List<BinaryExp> binaryExp;
		public LogicalExp(int from, int to, List<String> logicalOp, List<BinaryExp> binaryExp){
			super(from, to);
			this.logicalOp = logicalOp;
			this.binaryExp = binaryExp;
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
	public static class PathExp extends Struct {
		final List<String> pathName;
		final String anyPathOp;
		public PathExp(int from, int to, List<String> pathName, String anyPathOp){
			super(from, to);
			this.pathName = pathName;
			this.anyPathOp = anyPathOp;
		}
	}
	public static class Section extends Struct {
		final List<Line> line;
		final PathExp pathExp;
		public Section(int from, int to, List<Line> line, PathExp pathExp){
			super(from, to);
			this.line = line;
			this.pathExp = pathExp;
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
		final IfExp ifExp;
		final ValueExp valueExp;
		public Value(int from, int to, CallExp callExp, IfExp ifExp, ValueExp valueExp){
			super(from, to);
			this.callExp = callExp;
			this.ifExp = ifExp;
			this.valueExp = valueExp;
		}
	}
	public static class ValueExp extends Struct {
		final String valueArg;
		final List<Operation> operation;
		final String aliasName;
		public ValueExp(int from, int to, String valueArg, List<Operation> operation, String aliasName){
			super(from, to);
			this.valueArg = valueArg;
			this.operation = operation;
			this.aliasName = aliasName;
		}
	}
}


