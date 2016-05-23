package antlr;

// Generated from ConstraintGrammar.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ConstraintGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ConstraintGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#constraints}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraints(ConstraintGrammarParser.ConstraintsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraint(ConstraintGrammarParser.ConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(ConstraintGrammarParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#equality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(ConstraintGrammarParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#inequality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInequality(ConstraintGrammarParser.InequalityContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#valueLowerThanAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueLowerThanAttribute(ConstraintGrammarParser.ValueLowerThanAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#valueGreaterThanAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueGreaterThanAttribute(ConstraintGrammarParser.ValueGreaterThanAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval(ConstraintGrammarParser.IntervalContext ctx);
}