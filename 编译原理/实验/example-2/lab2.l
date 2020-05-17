/* 把讨厌的注释去掉 */

%{
#include <stdio.h> 
#define LT					1
#define	LE					2
#define GT					3
#define	GE					4
#define	EQ					5
#define NE					6
#define COLON				8
#define POINT				7
#define LLB					9
#define RLB					10
#define SEMIC				13
#define ASSIG				14

#define IF					15
#define ELSE				16
#define WHILE				17
#define	DO					18


#define ID          19
#define FLOAT       20
#define RELOP       21
#define NEWLINE     22
#define ERRORCHAR   23

#define PLUS 		24
#define MINUS 		25
#define MULT	26
#define DIV		27

#define PROGRAM				28
#define VAR					29
#define	BEG				30
#define END					31

#define THEN				32
#define COMMA				33
#define INT   				34
#define INTEGER  			35
#define REAL				36

int yylval;
typedef union{
  int ival;
  float fval;
  char str[16];
}Type;
Type yylval_2;

%}

delim		[ \t \n]
ws			{delim}+
letter	[A-Za-z]
digit		[0-9]
id			({letter})({letter}|{digit})*
float	{digit}?(\.{digit}+)(E[+-]?{digit}+)?
integer  		{digit}+
%%

<INITIAL>{ws}	          { ;}
<INITIAL>PROGRAM		  { return (PROGRAM);}
<INITIAL>IF				  { return (IF);}
<INITIAL>ELSE			  { return(ELSE);}
<INITIAL>THEN		      { return (THEN);}
<INITIAL>WHILE			  { return (WHILE);}
<INITIAL>DO		          { return (DO);}
<INITIAL>VAR			  { return (VAR); }
<INITIAL>BEGIN		      { return (BEG);}
<INITIAL>INTEGER		  { return (INTEGER);}
<INITIAL>REAL		      { return (REAL);}
<INITIAL>END		      { return (END);}

<INITIAL>{integer}			{ yylval_2.ival=atoi(yytext);return (INT);}
<INITIAL>{float}				{ yylval_2.fval=atof(yytext);return (FLOAT);}
<INITIAL>{id}	          { strcpy(yylval_2.str,yytext);return (ID);}

<INITIAL>"<"	          { yylval=LT;return (RELOP);}
<INITIAL>"<="	          { yylval=LE;return (RELOP);}
<INITIAL>"=="	          { yylval=EQ;return (RELOP);}
<INITIAL>"!="	          { yylval=NE;return (RELOP);}
<INITIAL>">"	          { yylval=GT;return (RELOP);}
<INITIAL>">="	          { yylval=GE;return (RELOP);}
<INITIAL>":="			  {	yylval=ASSIG;return (RELOP);}
<INITIAL>"("			  {	yylval=LLB;return (RELOP);}
<INITIAL>")"			  {	yylval=RLB;return (RELOP);}

<INITIAL>"+"			  {	yylval=PLUS;return (PLUS);}
<INITIAL>"-"			  {	yylval=MINUS;return (MINUS);}
<INITIAL>"*"			  {	yylval=MULT;return (MULT);}
<INITIAL>"/"			  {	yylval=DIV;return (DIV);}

<INITIAL>":"			  {	yylval=COLON;return (RELOP);}
<INITIAL>";"			  {	yylval=SEMIC;return (RELOP);}
<INITIAL>"."			  {	yylval=POINT;return (RELOP);}
<INITIAL>","			  { yylval=COMMA;return (RELOP); }
<INITIAL>.				  { return ERRORCHAR;}

 
%%

int yywrap (){
  return 1;
}

void writeout(int c){
  switch(c){
    case ERRORCHAR: fprintf(yyout, "(ERRORCHAR, \"%s\") ", yytext);break;
    case RELOP: fprintf(yyout, "(RELOP, \"%s\") ", yytext);break;  	  

    case PLUS: fprintf(yyout, "(PLUS, \"%s\") ", yytext);break;  	  
    case MINUS: fprintf(yyout, "(MINUS, \"%s\") ", yytext);break;  	  
    case MULT: fprintf(yyout, "(MULT, \"%s\") ", yytext);break;  	  
    case DIV: fprintf(yyout, "(DIV, \"%s\") ", yytext);break;  	
      
    case WHILE: fprintf(yyout, "(WHILE, \"%s\") ", yytext);break;
    case DO: fprintf(yyout, "(DO, \"%s\") ", yytext);break;
    case IF: fprintf(yyout, "(IF, \"%s\") ", yytext);break;
    case ELSE: fprintf(yyout, "(ELSE, \"%s\") ", yytext);break;
    case THEN: fprintf(yyout, "(THEN, \"%s\") ", yytext);break;
    case NEWLINE: fprintf(yyout, "\n");break;
    case PROGRAM: fprintf(yyout, "(PROGRAM, \"%s\") ", yytext);break;
    case VAR: fprintf(yyout, "(VAR, \"%s\") ", yytext);break;
    case END: fprintf(yyout, "(END, \"%s\") ", yytext);break;
    case INTEGER: fprintf(yyout, "(INTEGER, \"%s\") ", yytext);break;
    case REAL: fprintf(yyout, "(REAL, \"%s\") ", yytext);break;
    
    case INT: fprintf(yyout, "(INT, \"%s\" , value: %d)" , yytext,yylval_2.ival);break;
    case FLOAT: fprintf(yyout, "(FLOAT, \"%s\" ,value: %f)", yytext,yylval_2.fval);break;
    case ID: fprintf(yyout, "(ID, \"%s\" ,value: %s)", yytext,yylval_2.str);break;
    
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
