/* 把讨厌的注释去掉 */

%{

#include <stdio.h> 
#define LT					1
#define	LE					2
#define PLUS					3
#define	MINUS					4
#define	TIMES				5
#define DIV					6

#define WHILE				18
#define	DO					19
#define ID          20
#define NUMBER      21
#define RELOP       22

#define NEWLINE     23
#define ERRORCHAR   24

#define LPAREN 25	/*bracket:()*/
#define RPAREN 26
#define SEMICOLON 27		/*分号*/
#define ASSIGN 28			/*赋值*/
#define ARITHMETIC 29		/*算术*/
#define NOTE 30			/*注释*/
#define IF 31
#define ELSE 32
#define LBRACE 33		/*braces 为大括号:{}*/
#define RBRACE 34

#define STRING 35
#define STRERROR 36

%}

 
delim		[ \t \n]
ws			{delim}+
letter	[A-Za-z_]
digit		[0-9]
id			{letter}({letter}|{digit})*
number	{digit}+(\.{digit}+)?(E[+-]?{digit}+)?
str      ({allow}|[^\"\'\n\\])*\"
allow    ((\\\\)|(\\\")|(\\\')|(\\{letter})|(\\{digit}))*


/* 状态（或条件）定义可以定义在这里 
 * INITIAL是一个默认的状态，不需要定义
 */

%s COMMENT
%s COMMENT2
%s QUOTE

%%


<INITIAL>"/*"						{ BEGIN COMMENT;}
<COMMENT>"*/"						{ BEGIN INITIAL;}
<COMMENT>.|\n						{ ;}

<INITIAL>"//"						{BEGIN COMMENT2;}
<COMMENT2>\n				  {BEGIN INITIAL;}
<COMMENT2>.				  {;}
 /* ECHO是一个宏，相当于 fprintf(yyout, "%s", yytext)*/

<INITIAL>{ws}	          {;}
<INITIAL>while					{return (WHILE);}
<INITIAL>if					{return (IF);}
<INITIAL>else					{return (ELSE);}
<INITIAL>do		          {return (DO);}
<INITIAL>{id}	          {return (ID);}
<INITIAL>{number}	      {return (NUMBER);}
<INITIAL>"<"	          {return (RELOP);}
<INITIAL>"<="	          {return (RELOP);}
<INITIAL>"=="	          {return (RELOP);}
<INITIAL>"!="	          {return (RELOP);}
<INITIAL>">"	          {return (RELOP);}
<INITIAL>">="	          {return (RELOP);}
<INITIAL>"="	          {return (ASSIGN);}
<INITIAL>"{"	          {return (LBRACE);}
<INITIAL>"}"	          {return (RBRACE);}
<INITIAL>"("	          {return (LPAREN);}
<INITIAL>")"	          {return (RPAREN);}
<INITIAL>";"	          {return (SEMICOLON);}
<INITIAL>"+"	          {return (PLUS);}
<INITIAL>"-"	          {return (MINUS);}
<INITIAL>"*"	          {return (TIMES);}
<INITIAL>"/"	          {return (DIV);}

<INITIAL>.							{ return ERRORCHAR;}


 
%%

int yywrap (){
  return 1;
}

void writeout(int c){
  switch(c){
    case ERRORCHAR: fprintf(yyout, "(ERRORCHAR, \"%s\") ", yytext);break;
    case RELOP: fprintf(yyout, "(RELOP, \"%s\") ", yytext);break;     
    case WHILE: fprintf(yyout, "(WHILE, \"%s\") ", yytext);break;
    case DO: fprintf(yyout, "(DO, \"%s\") ", yytext);break;
    case NUMBER: fprintf(yyout, "(NUM, \"%s\") ", yytext);break;
    case ID: fprintf(yyout, "(ID, \"%s\") ", yytext);break;
    case LBRACE: fprintf(yyout, "(LBRACE, \"%s\") ", yytext);break;
    case RBRACE: fprintf(yyout, "(RBRACE, \"%s\") ", yytext);break;
    case LPAREN: fprintf(yyout, "(LPAREN, \"%s\") ", yytext);break;
    case RPAREN: fprintf(yyout, "(RPAREN, \"%s\") ", yytext);break;
    case SEMICOLON: fprintf(yyout, "(SEMICOLON, \"%s\") ", yytext);break;
    case ASSIGN: fprintf(yyout, "(ASSIGN, \"%s\") ", yytext);break;
    case PLUS: fprintf(yyout, "(PLUS, \"%s\") ", yytext);break;
    case MINUS: fprintf(yyout, "(MINUS, \"%s\") ", yytext);break;
    case TIMES: fprintf(yyout, "(TIMES, \"%s\") ", yytext);break;
    case DIV: fprintf(yyout, "(DIV, \"%s\") ", yytext);break;
    case NOTE: fprintf(yyout, "(NOTE, \"%s\") ", yytext);break;
    case IF: fprintf(yyout, "(IF, \"%s\") ", yytext);break;
    case ELSE: fprintf(yyout, "(ELSE, \"%s\") ", yytext);break;
    case NEWLINE: fprintf(yyout, "\n");break;
    case STRING: fprintf(yyout, "(String, \"%s\") ", yytext);break;
    default:break;
  }
  return;
}


int main (int argc, char ** argv){
	int c,j=0;
	if (argc>=2){
	  if ((yyin = fopen(argv[1], "r")) == NULL){
	    printf("Can't open file %s\n", argv[1]);
	    return 1;
	  }
	  if (argc>=3){
	    yyout=fopen(argv[2], "w");
	  }
	}

	while (c = yylex()){
		writeout(c);
		j++;
		if (j%5 == 0) writeout(NEWLINE);
	}
	if(argc>=2){
	  fclose(yyin);
	  if (argc>=3) fclose(yyout);
	}
	return 0;
}
