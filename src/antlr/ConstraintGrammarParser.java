package antlr;

// Generated from ConstraintGrammar.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ConstraintGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VALUE=1, ATTRIBUTE=2, AND=3, OR=4, GT=5, GE=6, LT=7, LE=8, EQ=9, LPAREN=10, 
		RPAREN=11, CARDINALITY=12, NEWLINE=13;
	public static final int
		RULE_constraints = 0, RULE_constraint = 1, RULE_predicate = 2, RULE_equality = 3, 
		RULE_inequality = 4, RULE_valueLowerThanAttribute = 5, RULE_valueGreaterThanAttribute = 6, 
		RULE_interval = 7;
	public static final String[] ruleNames = {
		"constraints", "constraint", "predicate", "equality", "inequality", "valueLowerThanAttribute", 
		"valueGreaterThanAttribute", "interval"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VALUE", "ATTRIBUTE", "AND", "OR", "GT", "GE", "LT", "LE", "EQ", 
		"LPAREN", "RPAREN", "CARDINALITY", "NEWLINE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ConstraintGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ConstraintGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ConstraintsContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ConstraintGrammarParser.EOF, 0); }
		public List<ConstraintContext> constraint() {
			return getRuleContexts(ConstraintContext.class);
		}
		public ConstraintContext constraint(int i) {
			return getRuleContext(ConstraintContext.class,i);
		}
		public ConstraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).enterConstraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).exitConstraints(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConstraintGrammarVisitor ) return ((ConstraintGrammarVisitor<? extends T>)visitor).visitConstraints(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintsContext constraints() throws RecognitionException {
		ConstraintsContext _localctx = new ConstraintsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_constraints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				constraint();
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CARDINALITY );
			setState(21);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public List<TerminalNode> CARDINALITY() { return getTokens(ConstraintGrammarParser.CARDINALITY); }
		public TerminalNode CARDINALITY(int i) {
			return getToken(ConstraintGrammarParser.CARDINALITY, i);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode EQ() { return getToken(ConstraintGrammarParser.EQ, 0); }
		public TerminalNode VALUE() { return getToken(ConstraintGrammarParser.VALUE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ConstraintGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ConstraintGrammarParser.NEWLINE, i);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).exitConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConstraintGrammarVisitor ) return ((ConstraintGrammarVisitor<? extends T>)visitor).visitConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(CARDINALITY);
			setState(24);
			predicate(0);
			setState(25);
			match(CARDINALITY);
			setState(26);
			match(EQ);
			setState(27);
			match(VALUE);
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(28);
				match(NEWLINE);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public EqualityContext equality() {
			return getRuleContext(EqualityContext.class,0);
		}
		public InequalityContext inequality() {
			return getRuleContext(InequalityContext.class,0);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public TerminalNode AND() { return getToken(ConstraintGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(ConstraintGrammarParser.OR, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConstraintGrammarVisitor ) return ((ConstraintGrammarVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		return predicate(0);
	}

	private PredicateContext predicate(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PredicateContext _localctx = new PredicateContext(_ctx, _parentState);
		PredicateContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_predicate, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			switch (_input.LA(1)) {
			case ATTRIBUTE:
				{
				setState(35);
				equality();
				}
				break;
			case VALUE:
				{
				setState(36);
				inequality();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(47);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(45);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new PredicateContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_predicate);
						setState(39);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(40);
						match(AND);
						setState(41);
						predicate(3);
						}
						break;
					case 2:
						{
						_localctx = new PredicateContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_predicate);
						setState(42);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(43);
						match(OR);
						setState(44);
						predicate(2);
						}
						break;
					}
					} 
				}
				setState(49);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EqualityContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTE() { return getToken(ConstraintGrammarParser.ATTRIBUTE, 0); }
		public TerminalNode EQ() { return getToken(ConstraintGrammarParser.EQ, 0); }
		public TerminalNode VALUE() { return getToken(ConstraintGrammarParser.VALUE, 0); }
		public EqualityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConstraintGrammarVisitor ) return ((ConstraintGrammarVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityContext equality() throws RecognitionException {
		EqualityContext _localctx = new EqualityContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_equality);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(ATTRIBUTE);
			setState(51);
			match(EQ);
			setState(52);
			match(VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InequalityContext extends ParserRuleContext {
		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class,0);
		}
		public ValueLowerThanAttributeContext valueLowerThanAttribute() {
			return getRuleContext(ValueLowerThanAttributeContext.class,0);
		}
		public ValueGreaterThanAttributeContext valueGreaterThanAttribute() {
			return getRuleContext(ValueGreaterThanAttributeContext.class,0);
		}
		public InequalityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inequality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).enterInequality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).exitInequality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConstraintGrammarVisitor ) return ((ConstraintGrammarVisitor<? extends T>)visitor).visitInequality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InequalityContext inequality() throws RecognitionException {
		InequalityContext _localctx = new InequalityContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_inequality);
		try {
			setState(57);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				interval();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				valueLowerThanAttribute();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				valueGreaterThanAttribute();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueLowerThanAttributeContext extends ParserRuleContext {
		public TerminalNode VALUE() { return getToken(ConstraintGrammarParser.VALUE, 0); }
		public TerminalNode LT() { return getToken(ConstraintGrammarParser.LT, 0); }
		public TerminalNode ATTRIBUTE() { return getToken(ConstraintGrammarParser.ATTRIBUTE, 0); }
		public ValueLowerThanAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueLowerThanAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).enterValueLowerThanAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).exitValueLowerThanAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConstraintGrammarVisitor ) return ((ConstraintGrammarVisitor<? extends T>)visitor).visitValueLowerThanAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueLowerThanAttributeContext valueLowerThanAttribute() throws RecognitionException {
		ValueLowerThanAttributeContext _localctx = new ValueLowerThanAttributeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_valueLowerThanAttribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(VALUE);
			setState(60);
			match(LT);
			setState(61);
			match(ATTRIBUTE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueGreaterThanAttributeContext extends ParserRuleContext {
		public TerminalNode VALUE() { return getToken(ConstraintGrammarParser.VALUE, 0); }
		public TerminalNode GT() { return getToken(ConstraintGrammarParser.GT, 0); }
		public TerminalNode ATTRIBUTE() { return getToken(ConstraintGrammarParser.ATTRIBUTE, 0); }
		public ValueGreaterThanAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueGreaterThanAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).enterValueGreaterThanAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).exitValueGreaterThanAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConstraintGrammarVisitor ) return ((ConstraintGrammarVisitor<? extends T>)visitor).visitValueGreaterThanAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueGreaterThanAttributeContext valueGreaterThanAttribute() throws RecognitionException {
		ValueGreaterThanAttributeContext _localctx = new ValueGreaterThanAttributeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_valueGreaterThanAttribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(VALUE);
			setState(64);
			match(GT);
			setState(65);
			match(ATTRIBUTE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntervalContext extends ParserRuleContext {
		public List<TerminalNode> VALUE() { return getTokens(ConstraintGrammarParser.VALUE); }
		public TerminalNode VALUE(int i) {
			return getToken(ConstraintGrammarParser.VALUE, i);
		}
		public List<TerminalNode> LT() { return getTokens(ConstraintGrammarParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(ConstraintGrammarParser.LT, i);
		}
		public TerminalNode ATTRIBUTE() { return getToken(ConstraintGrammarParser.ATTRIBUTE, 0); }
		public IntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).enterInterval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConstraintGrammarListener ) ((ConstraintGrammarListener)listener).exitInterval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConstraintGrammarVisitor ) return ((ConstraintGrammarVisitor<? extends T>)visitor).visitInterval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntervalContext interval() throws RecognitionException {
		IntervalContext _localctx = new IntervalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_interval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(VALUE);
			setState(68);
			match(LT);
			setState(69);
			match(ATTRIBUTE);
			setState(70);
			match(LT);
			setState(71);
			match(VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return predicate_sempred((PredicateContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean predicate_sempred(PredicateContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17L\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\6\2\24\n\2\r\2"+
		"\16\2\25\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3\4\3"+
		"\4\3\4\5\4(\n\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\60\n\4\f\4\16\4\63\13\4\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6<\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\2\3\6\n\2\4\6\b\n\f\16\20\2\2J\2\23\3\2\2\2"+
		"\4\31\3\2\2\2\6\'\3\2\2\2\b\64\3\2\2\2\n;\3\2\2\2\f=\3\2\2\2\16A\3\2\2"+
		"\2\20E\3\2\2\2\22\24\5\4\3\2\23\22\3\2\2\2\24\25\3\2\2\2\25\23\3\2\2\2"+
		"\25\26\3\2\2\2\26\27\3\2\2\2\27\30\7\2\2\3\30\3\3\2\2\2\31\32\7\16\2\2"+
		"\32\33\5\6\4\2\33\34\7\16\2\2\34\35\7\13\2\2\35!\7\3\2\2\36 \7\17\2\2"+
		"\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\5\3\2\2\2#!\3\2\2\2"+
		"$%\b\4\1\2%(\5\b\5\2&(\5\n\6\2\'$\3\2\2\2\'&\3\2\2\2(\61\3\2\2\2)*\f\4"+
		"\2\2*+\7\5\2\2+\60\5\6\4\5,-\f\3\2\2-.\7\6\2\2.\60\5\6\4\4/)\3\2\2\2/"+
		",\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\7\3\2\2\2\63\61\3"+
		"\2\2\2\64\65\7\4\2\2\65\66\7\13\2\2\66\67\7\3\2\2\67\t\3\2\2\28<\5\20"+
		"\t\29<\5\f\7\2:<\5\16\b\2;8\3\2\2\2;9\3\2\2\2;:\3\2\2\2<\13\3\2\2\2=>"+
		"\7\3\2\2>?\7\t\2\2?@\7\4\2\2@\r\3\2\2\2AB\7\3\2\2BC\7\7\2\2CD\7\4\2\2"+
		"D\17\3\2\2\2EF\7\3\2\2FG\7\t\2\2GH\7\4\2\2HI\7\t\2\2IJ\7\3\2\2J\21\3\2"+
		"\2\2\b\25!\'/\61;";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}