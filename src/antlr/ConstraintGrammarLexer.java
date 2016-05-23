package antlr;

// Generated from ConstraintGrammar.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ConstraintGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VALUE=1, ATTRIBUTE=2, AND=3, OR=4, GT=5, GE=6, LT=7, LE=8, EQ=9, LPAREN=10, 
		RPAREN=11, CARDINALITY=12, NEWLINE=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"VALUE", "ATTRIBUTE", "AND", "OR", "GT", "GE", "LT", "LE", "EQ", "LPAREN", 
		"RPAREN", "CARDINALITY", "NEWLINE"
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


	public ConstraintGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ConstraintGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17\u00a7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\6\2\37\n\2\r\2\16\2 \3\3\3\3\7"+
		"\3%\n\3\f\3\16\3(\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\5\4\66\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5D\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6N\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\5\7\\\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bf\n\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tt\n\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\5\n~\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13\u0088\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0092\n\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u009c\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\5\16\u00a6\n\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\3\2\2\u00c9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\36\3"+
		"\2\2\2\5\"\3\2\2\2\7\65\3\2\2\2\tC\3\2\2\2\13M\3\2\2\2\r[\3\2\2\2\17e"+
		"\3\2\2\2\21s\3\2\2\2\23}\3\2\2\2\25\u0087\3\2\2\2\27\u0091\3\2\2\2\31"+
		"\u009b\3\2\2\2\33\u00a5\3\2\2\2\35\37\4\62;\2\36\35\3\2\2\2\37 \3\2\2"+
		"\2 \36\3\2\2\2 !\3\2\2\2!\4\3\2\2\2\"&\4C\\\2#%\5\3\2\2$#\3\2\2\2%(\3"+
		"\2\2\2&$\3\2\2\2&\'\3\2\2\2\'\6\3\2\2\2(&\3\2\2\2)*\7(\2\2*\66\7(\2\2"+
		"+,\7\"\2\2,-\7(\2\2-\66\7(\2\2./\7(\2\2/\60\7(\2\2\60\66\7\"\2\2\61\62"+
		"\7\"\2\2\62\63\7(\2\2\63\64\7(\2\2\64\66\7\"\2\2\65)\3\2\2\2\65+\3\2\2"+
		"\2\65.\3\2\2\2\65\61\3\2\2\2\66\b\3\2\2\2\678\7~\2\28D\7~\2\29:\7\"\2"+
		"\2:;\7~\2\2;D\7~\2\2<=\7~\2\2=>\7~\2\2>D\7\"\2\2?@\7\"\2\2@A\7~\2\2AB"+
		"\7~\2\2BD\7\"\2\2C\67\3\2\2\2C9\3\2\2\2C<\3\2\2\2C?\3\2\2\2D\n\3\2\2\2"+
		"EN\7@\2\2FG\7\"\2\2GN\7@\2\2HI\7@\2\2IN\7\"\2\2JK\7\"\2\2KL\7@\2\2LN\7"+
		"\"\2\2ME\3\2\2\2MF\3\2\2\2MH\3\2\2\2MJ\3\2\2\2N\f\3\2\2\2OP\7@\2\2P\\"+
		"\7?\2\2QR\7\"\2\2RS\7@\2\2S\\\7?\2\2TU\7@\2\2UV\7?\2\2V\\\7\"\2\2WX\7"+
		"\"\2\2XY\7@\2\2YZ\7?\2\2Z\\\7\"\2\2[O\3\2\2\2[Q\3\2\2\2[T\3\2\2\2[W\3"+
		"\2\2\2\\\16\3\2\2\2]f\7>\2\2^_\7\"\2\2_f\7>\2\2`a\7>\2\2af\7\"\2\2bc\7"+
		"\"\2\2cd\7>\2\2df\7\"\2\2e]\3\2\2\2e^\3\2\2\2e`\3\2\2\2eb\3\2\2\2f\20"+
		"\3\2\2\2gh\7>\2\2ht\7?\2\2ij\7\"\2\2jk\7>\2\2kt\7?\2\2lm\7>\2\2mn\7?\2"+
		"\2nt\7\"\2\2op\7\"\2\2pq\7>\2\2qr\7?\2\2rt\7\"\2\2sg\3\2\2\2si\3\2\2\2"+
		"sl\3\2\2\2so\3\2\2\2t\22\3\2\2\2u~\7?\2\2vw\7\"\2\2w~\7?\2\2xy\7?\2\2"+
		"y~\7\"\2\2z{\7\"\2\2{|\7?\2\2|~\7\"\2\2}u\3\2\2\2}v\3\2\2\2}x\3\2\2\2"+
		"}z\3\2\2\2~\24\3\2\2\2\177\u0088\7*\2\2\u0080\u0081\7\"\2\2\u0081\u0088"+
		"\7*\2\2\u0082\u0083\7*\2\2\u0083\u0088\7\"\2\2\u0084\u0085\7\"\2\2\u0085"+
		"\u0086\7*\2\2\u0086\u0088\7\"\2\2\u0087\177\3\2\2\2\u0087\u0080\3\2\2"+
		"\2\u0087\u0082\3\2\2\2\u0087\u0084\3\2\2\2\u0088\26\3\2\2\2\u0089\u0092"+
		"\7+\2\2\u008a\u008b\7\"\2\2\u008b\u0092\7+\2\2\u008c\u008d\7+\2\2\u008d"+
		"\u0092\7\"\2\2\u008e\u008f\7\"\2\2\u008f\u0090\7+\2\2\u0090\u0092\7\""+
		"\2\2\u0091\u0089\3\2\2\2\u0091\u008a\3\2\2\2\u0091\u008c\3\2\2\2\u0091"+
		"\u008e\3\2\2\2\u0092\30\3\2\2\2\u0093\u009c\7~\2\2\u0094\u0095\7\"\2\2"+
		"\u0095\u009c\7~\2\2\u0096\u0097\7~\2\2\u0097\u009c\7\"\2\2\u0098\u0099"+
		"\7\"\2\2\u0099\u009a\7~\2\2\u009a\u009c\7\"\2\2\u009b\u0093\3\2\2\2\u009b"+
		"\u0094\3\2\2\2\u009b\u0096\3\2\2\2\u009b\u0098\3\2\2\2\u009c\32\3\2\2"+
		"\2\u009d\u00a6\7\f\2\2\u009e\u009f\7\"\2\2\u009f\u00a6\7\f\2\2\u00a0\u00a1"+
		"\7\f\2\2\u00a1\u00a6\7\"\2\2\u00a2\u00a3\7\"\2\2\u00a3\u00a4\7\f\2\2\u00a4"+
		"\u00a6\7\"\2\2\u00a5\u009d\3\2\2\2\u00a5\u009e\3\2\2\2\u00a5\u00a0\3\2"+
		"\2\2\u00a5\u00a2\3\2\2\2\u00a6\34\3\2\2\2\20\2 &\65CM[es}\u0087\u0091"+
		"\u009b\u00a5\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}