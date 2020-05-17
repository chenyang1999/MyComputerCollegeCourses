%{
	#include <stdio.h>
	int yylex();
  #define YYSTYPE double /* 将Yacc栈定义为double类型 */
  int yyerror(char *);   /* 不声明该函数将出现warning */
%}

%token NUM LPAREN RPAREN ENTER PLUS
%left  MINUS
%left  TIMES DIVIDE
%right UMINUS

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
			| MINUS expr  {$$ = -$2;}
			| NUM							{$$ = $1;}
			;


%%

int main(){
	yyparse();
	return 0;
}
