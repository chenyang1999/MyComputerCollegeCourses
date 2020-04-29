%{
#include <stdio.h>
#include "util.h" 
#include "errormsg.h"


int yylex(void); /* function prototype */

/* 该函数显示错误信息s，显示时包含了错误发生的位置。*/
void yyerror(char *s)
{
 EM_error(EM_tokPos, "%s", s);
}


%}


 /* 定义属性值栈的类型，后续实验中如果需要存储不同类型的属性值，则需要修改此处 */
%union {
	int ival;
	double fval;
	string sval;
}

 /* 定义各个终结符，以及他们的属性值的类型，后续实验中如果需要存储不同类型的属性值，则需要修改此处 */
%token <sval> ID /* id */
%token <ival> INT  /*整型数*/
%token <fval> FLOAT /*浮点数*/
%token INTEGER REAL  /*两种类型名：整型、实型*/
%token 
  COMMA COLON SEMICOLON LPAREN RPAREN PERIOD /* 符号 , : ; ( ) . */
  PROGRAM BEGINN END VAR IF WHILE DO   /* 关键字：PROGRAM BEGIN END VAR IF WHILE DO */
  /* 想想为什么不声明为BEGIN？ */
  THEN ELSE /* 关键字：THEN ELSE */
  ASSIGN EQ NEQ LT LE GT GE /* 符号 :=	 =  <>  <  <=  >  >= */
  PLUS MINUS TIMES DIVIDE /* 符号 + = * / */
%start program /* 开始符号为program */



%debug /* 允许跟踪错误 */

%%
 /* 文法：program--> PROGRAM ID; vardec BEGIN stmts END. 
  *       vardec-->   VAR declist 
  *       declist-->  ID:INTEGER;
  *       stmts--> ID := exp
  *       exp--> INT
  *
  * 后续实验可能需要修改这个文法
  */

program	:	PROGRAM ID SEMICOLON vardec BEGINN stmts END PERIOD	
				;

vardec 	: VAR declist 
				;
declist : ID COLON INTEGER SEMICOLON
				;

stmts : ID ASSIGN exp	                
			;
			
exp : INT	
		;
 
