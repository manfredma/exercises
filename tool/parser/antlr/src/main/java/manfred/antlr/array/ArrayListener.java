package manfred.antlr.array;// Generated from /Users/maxingfang/IdeaProjects/exercises/asm/antlr/src/main/resources/Array.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArrayParser}.
 */
public interface ArrayListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArrayParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(ArrayParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArrayParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(ArrayParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArrayParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ArrayParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArrayParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ArrayParser.ValueContext ctx);
}