// Generated from jyatlon\generated\YATL.g4 by ANTLR 4.7.2
package jyatlon.generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link YATLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface YATLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link YATLParser#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(YATLParser.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(YATLParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(YATLParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#lineExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineExp(YATLParser.LineExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#escapedChar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEscapedChar(YATLParser.EscapedCharContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#controlExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControlExp(YATLParser.ControlExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#tupleExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTupleExp(YATLParser.TupleExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#tupleValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTupleValue(YATLParser.TupleValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#controlOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControlOp(YATLParser.ControlOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#commentOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommentOp(YATLParser.CommentOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#rawText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRawText(YATLParser.RawTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(YATLParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#ifExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExp(YATLParser.IfExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#callExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExp(YATLParser.CallExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#logicalExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExp(YATLParser.LogicalExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#logicalOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOp(YATLParser.LogicalOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#binaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExp(YATLParser.BinaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(YATLParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#binaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOp(YATLParser.BinaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#valueExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueExp(YATLParser.ValueExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#indexOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexOp(YATLParser.IndexOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#valueArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueArg(YATLParser.ValueArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(YATLParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#argExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgExp(YATLParser.ArgExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#pathExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathExp(YATLParser.PathExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#anyPathOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnyPathOp(YATLParser.AnyPathOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#pathArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathArg(YATLParser.PathArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#pathName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathName(YATLParser.PathNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#methodName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodName(YATLParser.MethodNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#aliasExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasExp(YATLParser.AliasExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YATLParser#aliasName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasName(YATLParser.AliasNameContext ctx);
}