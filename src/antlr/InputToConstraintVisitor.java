package antlr;

import java.util.ArrayList;

import predicate.ConjunctionPredicate;
import predicate.Constraint;
import predicate.DisjunctionPredicate;
import predicate.EqualityPredicate;
import predicate.IntervalPredicate;
import predicate.Predicate;
import predicate.valueGreaterThanAttributePredicate;
import predicate.valueLowerThanAttributePredicate;

public class InputToConstraintVisitor extends ConstraintGrammarBaseVisitor<Predicate> {
	private ArrayList<Constraint> constraints = new ArrayList<>();
	
	public  ArrayList<Constraint> getConstraints() {
		return constraints;
	}
	
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Predicate visitConstraint(ConstraintGrammarParser.ConstraintContext ctx) {
		Constraint c = new Constraint();
		c.setCardinality(Integer.parseInt(ctx.VALUE().getText()));
		c.setPredicate(visit(ctx.predicate()));
		
		constraints.add(c);
		System.out.println("constraint: " + ctx.getText());
		return super.visitChildren(ctx);
//		return null;
	}
	
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Predicate visitPredicate(ConstraintGrammarParser.PredicateContext ctx) {
		// Either AND or OR
		if (ctx.getChildCount() > 1) {
			if (ctx.getChild(1).equals(ctx.AND())) {
				ConjunctionPredicate p = new ConjunctionPredicate();
				p.setPredicate1(visit(ctx.predicate(0)));
				p.setPredicate2(visit(ctx.predicate(1)));
				return p;
			} else if (ctx.getChild(1).equals(ctx.OR())) {
				DisjunctionPredicate p = new DisjunctionPredicate();
				p.setPredicate1(visit(ctx.predicate(0)));
				p.setPredicate2(visit(ctx.predicate(1)));
				return p;
			}
		}
		
		return visitChildren(ctx);
	}
	
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#equality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Predicate visitEquality(ConstraintGrammarParser.EqualityContext ctx) {
		EqualityPredicate p = new EqualityPredicate();
		p.setAttribute(ctx.ATTRIBUTE().getText());
		p.setValue(Integer.parseInt(ctx.VALUE().getText()));
		return p;
	}
	
	
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#valueLowerThanAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Predicate visitValueLowerThanAttribute(ConstraintGrammarParser.ValueLowerThanAttributeContext ctx) {
		valueLowerThanAttributePredicate p = new valueLowerThanAttributePredicate();
		p.setAttribute(ctx.ATTRIBUTE().getText());
		p.setValue(Integer.parseInt(ctx.VALUE().getText()));
		return p;
	}
	
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#valueGreaterThanAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Predicate visitValueGreaterThanAttribute(ConstraintGrammarParser.ValueGreaterThanAttributeContext ctx) {
		valueGreaterThanAttributePredicate p = new valueGreaterThanAttributePredicate();
		p.setAttribute(ctx.ATTRIBUTE().getText());
		p.setValue(Integer.parseInt(ctx.VALUE().getText()));
		return p;
	}
	
	/**
	 * Visit a parse tree produced by {@link ConstraintGrammarParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Predicate visitInterval(ConstraintGrammarParser.IntervalContext ctx) {
		IntervalPredicate p = new IntervalPredicate();
		p.setAttribute(ctx.ATTRIBUTE().getText());
		p.setValue1(Integer.parseInt(ctx.VALUE(0).getText()));
		p.setValue2(Integer.parseInt(ctx.VALUE(1).getText()));
		return p;
	}
}
