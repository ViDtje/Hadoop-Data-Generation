grammar ConstraintGrammar;

constraints
    : constraint+ EOF
    ;

constraint
    : CARDINALITY predicate CARDINALITY EQ VALUE (NEWLINE)*
    ;

predicate
    : equality
    | inequality
    | predicate AND predicate 
    | predicate OR predicate 
    ;

equality
    : ATTRIBUTE EQ VALUE
    ;

inequality
    : valueLowerThanAttribute
    | valueGreaterThanAttribute 
    | interval
    ;

valueLowerThanAttribute
    : VALUE LT ATTRIBUTE
    ;

valueGreaterThanAttribute
    : VALUE GT ATTRIBUTE
    ;

interval
    : VALUE LT ATTRIBUTE LT VALUE
    ;

VALUE
    : ('0' .. '9')+
    ;

ATTRIBUTE
    : ('A' .. 'Z')(VALUE)*
    ;

AND
    : SPACE* '&&' SPACE *
    ;

OR
    : SPACE* '||' SPACE*
    ;

GT 
    : SPACE* '>' SPACE*
    ;
GE 
    : SPACE* '>=' SPACE*
    ;
LT 
    : SPACE* '<' SPACE*
    ;
LE 
    : SPACE* '<=' SPACE*
    ;
EQ 
    : SPACE* '=' SPACE*
    ;

LPAREN 
    : SPACE* '(' SPACE*
    ;
RPAREN 
    : SPACE* ')' SPACE*
    ;

CARDINALITY
    : SPACE* '|' SPACE*
    ;

NEWLINE
    : SPACE* '\n' SPACE*
    ;

SPACE
    : ' '
    ;