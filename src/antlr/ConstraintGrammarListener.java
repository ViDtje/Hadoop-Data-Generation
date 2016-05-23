package antlr;

// Generated from ConstraintGrammar.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ConstraintGrammarParser}.
 */
public interface ConstraintGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ConstraintGrammarParser#constraints}.
	 * @param ctx the parse tree
	 */
	void enterConstraints(ConstraintGrammarParser.ConstraintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConstraintGrammarParser#constraints}.
	 * @param ctx the parse tree
	 */
	void exitConstraints(ConstraintGrammarParser.ConstraintsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConstraintGrammarParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(ConstraintGrammarParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConstraintGrammarParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(ConstraintGrammarParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConstraintGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(ConstraintGrammarParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConstraintGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(ConstraintGrammarParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConstraintGrammarParser#equality}.
	 * @param ctx the parse tree
	 */
	void enterEquality(ConstraintGrammarParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConstraintGrammarParser#equality}.
	 * @param ctx the parse tree
	 */
	void exitEquality(ConstraintGrammarParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConstraintGrammarParser#inequality}.
	 * @param ctx the parse tree
	 */
	void enterInequality(ConstraintGrammarParser.InequalityContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConstraintGrammarParser#inequality}.
	 * @param ctx the parse tree
	 */
	void exitInequality(ConstraintGrammarParser.InequalityContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConstraintGrammarParser#valueLowerThanAttribute}.
	 * @param ctx the parse tree
	 */
	void enterValueLowerThanAttribute(ConstraintGrammarParser.ValueLowerThanAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConstraintGrammarParser#valueLowerThanAttribute}.
	 * @param ctx the parse tree
	 */
	void exitValueLowerThanAttribute(ConstraintGrammarParser.ValueLowerThanAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConstraintGrammarParser#valueGreaterThanAttribute}.
	 * @param ctx the parse tree
	 */
	void enterValueGreaterThanAttribute(ConstraintGrammarParser.ValueGreaterThanAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConstraintGrammarParser#valueGreaterThanAttribute}.
	 * @param ctx the parse tree
	 */
	void exitValueGreaterThanAttribute(ConstraintGrammarParser.ValueGreaterThanAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConstraintGrammarParser#interval}.
	 * @param ctx the parse tree
	 */
	void enterInterval(ConstraintGrammarParser.IntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConstraintGrammarParser#interval}.
	 * @param ctx the parse tree
	 */
	void exitInterval(ConstraintGrammarParser.IntervalContext ctx);
}