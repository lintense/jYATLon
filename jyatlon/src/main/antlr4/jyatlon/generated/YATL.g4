// THIS FILE MUST BE UTF-8
// Define a grammar called Hello
// https://www.antlr.org/tools.html
// Instructions:
// All names ending by "Arg", "Name", "Op" should be terminals and will be send to constructor as a String
// All names ending by "Exp" are considered to have a terminal Collection arg. (NOT needed anymore)
// Always wrap Terminals so we can drop them
// Using 'literal' in expression will provide better error messages (at least is seem so...)

grammar YATL;

template
	: section+
	;
	
section
	: SPACE* '===' SPACE* pathExp SPACE* '===' SPACE* NEWLINE line*
	;
	
line
	: lineExp* NEWLINE
	;
	
lineExp
	: commentOp | escapedChar | value | controlExp | rawText | escapedBraket
	;

escapedChar
	: ESCAPE .
	;
	
escapedBraket
	: ( LEFTCB | '[' | POUND )
	;

controlExp
	: LEFTCB controlOp SPACE+ aliasName SPACE* RIGHTCB
	;

controlOp
	: BEGIN | BEFORE | BETWEEN | AFTER | END
	;

commentOp
	: POUND POUND POUND
	;
	
rawText
	: (~(NEWLINE | ESCAPE | LEFTCB | '[' | POUND))+
	;

value
	: LEFTCB '[' SPACE* (ifExp SPACE+)? (callExp SPACE+)? valueExp SPACE* ']' RIGHTCB
	| '[' SPACE* (ifExp SPACE+)? (callExp SPACE+)? valueExp SPACE* ']'
	;
	
ifExp
	: IF SPACE+ logicalExp
	;

callExp
	: CALL SPACE+ pathExp
	;

logicalExp
	: binaryExp (SPACE* logicalOp SPACE* binaryExp)*
	| '(' SPACE* logicalExp SPACE* ')'
	;

logicalOp
	: '||' | '&&'
	;

binaryExp
	: unaryOp? SPACE* valueExp (SPACE* binaryOp SPACE* valueExp)*
	| '(' SPACE* binaryExp SPACE* ')'
	;

unaryOp
	: '!' | '-'
	;

binaryOp
	: '!=' | '==' | '>' | '>=' | '<' | '<=' | '<>'
	;

valueExp
	: valueArg (SPACE* ':' SPACE* aliasName)? SPACE* operation*
	| '(' SPACE* valueExp SPACE* ')'
	;
	
valueArg
	: ROOT | NAME | INTEGER | NUMBER | STRING
	;
	
operation 
	: '.' SPACE? methodName (SPACE? '(' SPACE? argExp? SPACE? ')')? (SPACE? ':' SPACE? aliasName)?
	;

argExp
	: valueExp (SPACE? ',' SPACE? valueExp)*
	;

pathExp
	: anyPathOp? pathName (( '/' | '\\' ) pathName)*
	;

anyPathOp
	: '...' ( '\\' | '/' )
	;

pathName
	: NAME
	;

methodName
	: NAME
	;
	
aliasName
	: NAME
	;
	
//////////////////////////////////////////////////////////////////////////////

//WS	
//options {
//  paraphrase = "white space";
//}
//	:	(' '
//	|	'\t'
//	|	'\r')
//		-> skip
//	;



ROOT			: '$';
BEGIN			: 'begin';
BEFORE			: 'before';
BETWEEN			: 'between';
AFTER			: 'after';
END				: 'end';
CALL			: 'call';
IF				: 'if';

POUND			: '#' ;
LEFTCB			: '{' ;
RIGHTCB			: '}' ;
ESCAPE			: '~' ;

NEWLINE			: '\n';

WS	: [\r]+    -> skip ;

SPACE
	: ( ' ' | '\t' )
	;

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
    