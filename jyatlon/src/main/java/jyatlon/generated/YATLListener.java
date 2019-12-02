// Generated from jyatlon\generated\YATL.g4 by ANTLR 4.7.2
package jyatlon.generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YATLParser}.
 */
public interface YATLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YATLParser#template}.
	 * @param ctx the parse tree
	 */
	void enterTemplate(YATLParser.TemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#template}.
	 * @param ctx the parse tree
	 */
	void exitTemplate(YATLParser.TemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(YATLParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(YATLParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(YATLParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(YATLParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#lineExp}.
	 * @param ctx the parse tree
	 */
	void enterLineExp(YATLParser.LineExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#lineExp}.
	 * @param ctx the parse tree
	 */
	void exitLineExp(YATLParser.LineExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#escapedChar}.
	 * @param ctx the parse tree
	 */
	void enterEscapedChar(YATLParser.EscapedCharContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#escapedChar}.
	 * @param ctx the parse tree
	 */
	void exitEscapedChar(YATLParser.EscapedCharContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#controlExp}.
	 * @param ctx the parse tree
	 */
	void enterControlExp(YATLParser.ControlExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#controlExp}.
	 * @param ctx the parse tree
	 */
	void exitControlExp(YATLParser.ControlExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#controlOp}.
	 * @param ctx the parse tree
	 */
	void enterControlOp(YATLParser.ControlOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#controlOp}.
	 * @param ctx the parse tree
	 */
	void exitControlOp(YATLParser.ControlOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#commentOp}.
	 * @param ctx the parse tree
	 */
	void enterCommentOp(YATLParser.CommentOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#commentOp}.
	 * @param ctx the parse tree
	 */
	void exitCommentOp(YATLParser.CommentOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#rawText}.
	 * @param ctx the parse tree
	 */
	void enterRawText(YATLParser.RawTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#rawText}.
	 * @param ctx the parse tree
	 */
	void exitRawText(YATLParser.RawTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(YATLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(YATLParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#ifExp}.
	 * @param ctx the parse tree
	 */
	void enterIfExp(YATLParser.IfExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#ifExp}.
	 * @param ctx the parse tree
	 */
	void exitIfExp(YATLParser.IfExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#callExp}.
	 * @param ctx the parse tree
	 */
	void enterCallExp(YATLParser.CallExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#callExp}.
	 * @param ctx the parse tree
	 */
	void exitCallExp(YATLParser.CallExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#logicalExp}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExp(YATLParser.LogicalExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#logicalExp}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExp(YATLParser.LogicalExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#logicalOp}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOp(YATLParser.LogicalOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#logicalOp}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOp(YATLParser.LogicalOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#binaryExp}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExp(YATLParser.BinaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#binaryExp}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExp(YATLParser.BinaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(YATLParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(YATLParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#binaryOp}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOp(YATLParser.BinaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#binaryOp}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOp(YATLParser.BinaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#valueExp}.
	 * @param ctx the parse tree
	 */
	void enterValueExp(YATLParser.ValueExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#valueExp}.
	 * @param ctx the parse tree
	 */
	void exitValueExp(YATLParser.ValueExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#valueArg}.
	 * @param ctx the parse tree
	 */
	void enterValueArg(YATLParser.ValueArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#valueArg}.
	 * @param ctx the parse tree
	 */
	void exitValueArg(YATLParser.ValueArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(YATLParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(YATLParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#argExp}.
	 * @param ctx the parse tree
	 */
	void enterArgExp(YATLParser.ArgExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#argExp}.
	 * @param ctx the parse tree
	 */
	void exitArgExp(YATLParser.ArgExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#pathExp}.
	 * @param ctx the parse tree
	 */
	void enterPathExp(YATLParser.PathExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#pathExp}.
	 * @param ctx the parse tree
	 */
	void exitPathExp(YATLParser.PathExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#anyPathOp}.
	 * @param ctx the parse tree
	 */
	void enterAnyPathOp(YATLParser.AnyPathOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#anyPathOp}.
	 * @param ctx the parse tree
	 */
	void exitAnyPathOp(YATLParser.AnyPathOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#pathName}.
	 * @param ctx the parse tree
	 */
	void enterPathName(YATLParser.PathNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#pathName}.
	 * @param ctx the parse tree
	 */
	void exitPathName(YATLParser.PathNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#methodName}.
	 * @param ctx the parse tree
	 */
	void enterMethodName(YATLParser.MethodNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#methodName}.
	 * @param ctx the parse tree
	 */
	void exitMethodName(YATLParser.MethodNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link YATLParser#aliasName}.
	 * @param ctx the parse tree
	 */
	void enterAliasName(YATLParser.AliasNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link YATLParser#aliasName}.
	 * @param ctx the parse tree
	 */
	void exitAliasName(YATLParser.AliasNameContext ctx);
}