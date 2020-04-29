%{
	#include <ctype.h>
	#include <stdio.h>
	int yylex();
	int yyerror(char * s);
  #define YYSTYPE double /* 将Yacc栈定义为double类型 */
%}

%token NUM LPAREN RPAREN ENTER
%left PLUS MINUS
%left TIMES DIVIDE
%right UMINUS

%debug

%%

 /* 这样写prog可以让分析器每次读入一行进行分析，下一行重新分析expr */
prog : prog exprp
		 | exprp
		 ;

exprp 	: expr ENTER {printf("\nThe value of the expression is %lf.\n", $1);}
			 	;
expr  : expr PLUS expr	{$$ = $1 + $3;}
			| expr MINUS expr {$$ = $1 - $3;}
			| expr TIMES expr {$$ = $1 * $3;}
			| expr DIVIDE expr {$$ = $1 / $3;}
			| LPAREN expr RPAREN {$$ = $2;}
			| MINUS expr %prec UMINUS {$$ = -$2;}
			/* %prec UMINUS 表示该产生式的优先级和结合性与终结符UMINUS的相同。
			 * 注意：UMINUS 其实从未从词法分析器中获得，只是用来表示优先级和结合性的。
			 */
			| NUM							{$$ = $1;}
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
  
int yyerror(char* s){
	printf(s);
	return 0;
}  

int main(){
  //yydebug = 1;
	yyparse();
	return 0;
}
