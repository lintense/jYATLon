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
	: '===' pathExp '===' '\n' line*
	;
	
line
	: lineExp* '\n'
	;
	
lineExp
	: escapedChar | commentOp | value | controlExp | rawText | escapedBraket
	;

escapedChar
	: '~' .
	;
	
escapedBraket
	: ( '{' | '[' | '#' )
	;

controlExp
	: controlOp aliasName '}'
	;

controlOp
	: BEGIN | BEFORE | BETWEEN | AFTER | END
	;

commentOp
	: '###'
	;
	
rawText
	: (~('\n' | '~' | '{' | '[' | '#'))+
	;

value
	: '{[' ifExp? callExp? valueExp ']}'
	| '[' ifExp? callExp? valueExp ']'
	;
	
ifExp
	: 'if' logicalExp
	;

callExp
	: 'call' pathExp
	;

logicalExp
	: binaryExp (logicalOp binaryExp)*
	| '(' logicalExp ')'
	;

logicalOp
	: '||' | '&&'
	;

binaryExp
	: unaryOp? valueExp (binaryOp valueExp)*
	| '(' binaryExp ')'
	;

unaryOp
	: '!' | '-'
	;

binaryOp
	: '!=' | '==' | '>' | '>=' | '<' | '<=' | '<>'
	;

valueExp
	: valueArg (':' aliasName)? operation*
	| '(' valueExp ')'
	;
	
valueArg
	: ROOT | NAME | INTEGER | NUMBER | STRING
	;
	
operation 
	: '.' methodName ('(' argExp? ')')? (':' aliasName)?
	;

argExp
	: valueExp (',' valueExp)*
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
BEGIN			: '{begin';
BEFORE			: '{before';
BETWEEN			: '{between';
AFTER			: '{after';
END				: '{end';
CALL			: '{call';
IF				: '{if';


NEWLINE			: '\n';


SPACE
	: ( ' ' | '\t' | '\r' )
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
    