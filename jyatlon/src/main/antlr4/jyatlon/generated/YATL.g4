// THIS FILE MUST BE UTF-8
// Define a grammar called Hello
// https://www.antlr.org/tools.html
// Instructions:
// All names ending by "Arg", "Name", "Op" should be terminals and will be send to constructor as a String
// All names ending by "Exp" are considered to have a terminal Collection arg. (NOT needed anymore)
// Always wrap Terminals so we can drop them
// Using 'literal' in expression will provide better error messages (at least is seem so...)
// https://stackoverflow.com/questions/22415208/get-rid-of-token-recognition-error

grammar YATL;

template
	: section+
	;
	
section
	: SPACE* SECTIONSEP SPACE* pathExp aliasExp? SPACE* SECTIONSEP SPACE* (commentOp rawText*)* NEWLINE line*
	| EQUAL ROOT EQUAL NEWLINE line*
	;
	
line
	: lineExp* NEWLINE
	;
	
lineExp
	: commentOp | escapedChar | value | controlExp | rawText //| escapedBraket
	;

escapedChar
	: ESCAPE .
	;
	
//escapedBraket
//	: SPACE* ( LEFTCB | '[' | POUND | EQUAL )
//	;

controlExp
	: controlOp SPACE+ aliasName SPACE* RCURL
	;

controlOp
	: CONTROL
	;

commentOp
	: COMMENTSEP
	;
	
rawText
	: SPACE+
	| ~( SPACE | SECTIONSEP | NEWLINE | ESCAPE | CONTROL | COMMENTSEP | LVALUE )+
	| '?' | ';'
	;

value
	: LVALUE SPACE* (ifExp SPACE+)? (callExp SPACE+)? valueExp SPACE* RVALUE
	;
	
ifExp
	: IF SPACE+ logicalExp
	;

callExp
	: CALL SPACE+ pathExp argExp?
	;

logicalExp
	: binaryExp (SPACE* logicalOp SPACE* binaryExp)*
	| '(' SPACE* logicalExp SPACE* (logicalOp SPACE* logicalExp SPACE*)* ')'
	;

logicalOp
	: OR | AND
	;

binaryExp
	: valueExp (SPACE* binaryOp SPACE* valueExp)?
	| '(' SPACE* binaryExp SPACE* ')'
	;

unaryOp
	: NOT | MINUS
	;

binaryOp
	: NOT EQUAL | EQUAL EQUAL | '>' | '>' EQUAL | '<' | '<' EQUAL | '<>'
	;

valueExp
	: (unaryOp SPACE*)? valueArg (SPACE* COLON SPACE* aliasName)? SPACE* operation*
	| '(' SPACE* valueExp SPACE* ')'
	;
	
valueArg
	: ROOT | NAME | INTEGER | NUMBER | STRING
	;
	
operation 
	: DOT SPACE? methodName argExp? (SPACE? COLON SPACE? aliasName)?
	;

argExp
	: SPACE? '(' (SPACE? valueExp (SPACE? COMMA SPACE? valueExp)*)? SPACE? ')'
	;

pathExp
	: anyPathOp? pathArg (PATHSEP pathArg)*
	;

anyPathOp
	: ANYPATH PATHSEP
	;

pathArg
	: pathName (COLON aliasName)?
	;
	
pathName
	: NAME
	;

methodName
	: NAME
	| BEGIN | BEFORE | BETWEEN | AFTER | EMPTY | END 
	;

aliasExp
	: SPACE? '(' SPACE? aliasName (SPACE? COMMA SPACE? aliasName)* ')'
	;
	
aliasName
	: NAME
	;
	
////////////////////////////////// LEXER RULES ////////////////////////////////////////////

//WS	
//options {
//  paraphrase = "white space";
//}
//	:	(' '
//	|	'\t'
//	|	'\r')
//		-> skip
//	;


ANYPATH			: '...' ;

BEGIN			: 'begin';
BEFORE			: 'before';
BETWEEN			: 'between';
AFTER			: 'after';
EMPTY			: 'empty';
END				: 'end';
CALL			: 'call';
IF				: 'if';
CONTROL			:  LCURL ( BEGIN | BEFORE | BETWEEN | AFTER | EMPTY | END ) ;

ROOT			: '$';

LCURL			: '{' ;
LBRACK			: '[' ;
LVALUE			: LCURL LCURL ;
RCURL			: '}' ;
RBRACK			: ']' ;
RVALUE			: RCURL RCURL;
PATHSEP			: ( '\\' | '/' ) ;

ESCAPE			: '~' ;
DOT				: '.' ;

COMMA			: ',' ;
COLON			: ':' ;
NEWLINE			: '\n';

NOT				: '!' ;
MINUS			: '-' ;

WS				: [\r]+    -> skip ;

SPACE			: ( ' ' | '\t' ) ;


EQUAL			: '=' ;
SECTIONSEP		: EQUAL EQUAL EQUAL ;
POUND			: '#' ;
COMMENTSEP		: PERCENT PERCENT PERCENT ;
PIPE			: '|' ;
OR				: PIPE PIPE ;
AMP				: '&' ;
AND				: AMP AMP ;
PERCENT			: '%';

NAME 
    : ( ('a'..'z') | ('A'..'Z') | ('_') ) 
        ( ('a'..'z') | ('0'..'9') | ('A'..'Z') | ('_') )* 
    ;

INTEGER
	: ('0'..'9')+
	;
	
NUMBER 
    : ('0'..'9')+
    ( '.' ('0'..'9')+ )?
    ( ('e' | 'E') ('+' | '-')? ('0'..'9')+ )?
    ;

STRING
	: '\'' ( '\\' '\''? | ~('\\' | '\'') )* '\''
	| '"' ( '\\' '"'? | ~('\\' | '"') )* '"'
	;	
    