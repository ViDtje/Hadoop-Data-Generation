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
    : interval
    | valueLowerThanAttribute
    | valueGreaterThanAttribute
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
    : '&&'
    | ' &&'
    | '&& '
    | ' && '
    ;

OR
    : '||'
    | ' ||'
    | '|| '
    | ' || '
    ;

GT 
    : '>' 
    | ' >'
    | '> '
    | ' > '
    ;
GE 
    : '>=' 
    | ' >=' 
    | '>= ' 
    | ' >= ' 
    ;
LT 
    : '<' 
    | ' <'
    | '< '
    | ' < '
    ;
LE 
    : '<='
    | ' <='
    | '<= '
    | ' <= '
    ;
EQ 
    : '=' 
    |  ' ='
    | '= '
    | ' = '
    ;

LPAREN 
    : '(' 
    | ' (' 
    | '( ' 
    | ' ( ' 
    ;
RPAREN 
    : ')' 
    | ' )' 
    | ') ' 
    | ' ) ' 
    ;

CARDINALITY
    : '|'
    | ' |'
    | '| '
    | ' | '
    ;

NEWLINE
    : '\n'
    | ' \n'
    | '\n '
    | ' \n '
    ;