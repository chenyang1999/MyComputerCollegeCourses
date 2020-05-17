%{
	#include <ctype.h>
	#include <stdio.h>
	int yylex();
	int yyerror(char* s);  /* yyerror 声明，使用库中的实现，为避免出现warning */
  #define YYSTYPE double /* 将Yacc栈定义为double类型 */
  #define YYDEBUG 1      /* 允许debug模式 */
%}

%token NUM LPAREN RPAREN ENTER PLUS MINUS TIMES DIVIDE

%%

  /* 这样写prog可以让分析器每次读入一行进行分析，下一行重新分析expr */

prog : prog expln
		 | expln
		 ;

expln 	: expr ENTER {printf("\nThe value of the expression is %lf.\n", $1);}
			 	;
		 	
expr  : expr PLUS term	{$$ = $1 + $3;}
			| expr MINUS term {$$ = $1 - $3;}
			| term	
			;
 
term	:	term TIMES factor  {$$ = $1 * $3;}
			| term DIVIDE factor {$$ = $1 / $3;}
			| factor
			;
			
factor  : LPAREN expr RPAREN {$$ = $2;}
				| MINUS factor 	{$$ = - $2;}
				| NUM						{$$ = $1;}
				;
 
%%

/* 用c写的识别算术表达式的词法分析器。
 * 忽略空格和制表符
 * 能够识别+，-，*，/，(，)，换行符
 * 还能够识别浮点数（整数也识别为浮点数）
 */

int yylex(){
  int c;
  do{
    c=getchar();
  } while(c==' ' || c=='\t');
  switch(c){
  case '+': return PLUS;
  case '-': return MINUS;
  case '*': return TIMES;
  case '/': return DIVIDE;
  case '(': return LPAREN;
  case ')': return RPAREN;
  case '\n': return ENTER;
  default: 
    if ((c=='.')||(isdigit(c))){
      ungetc(c,stdin);
      scanf("%lf", &yylval);
      return NUM;
    } else {
      printf("\nLEX:ERROR! c=%c\n", c);
  	  return -1;}
  }
}

int main(){
  // yydebug = 1;
	yyparse();
	return 0;
}
