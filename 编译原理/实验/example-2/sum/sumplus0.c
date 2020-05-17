#include <ctype.h>
#include <stdio.h>

#define PLUS   		1
#define LPAREN		2
#define RPAREN		3
#define NUM				4
#define ENTER			0

int yylval;
int lookahead;

int yylex();
int match(int token);
int expr();


/* 词法分析器: yylex()
 * yylex()从键盘读入字符，并返回记号名（词法分析成功）或者-1（词法分析失败）
 */
int yylex(){
  int c;
  do{
    c=getchar();
  } while(c==' ' || c=='\t');
  switch(c){
  case '+': return PLUS;
  case '(': return LPAREN;
  case ')': return RPAREN;
  case '\n': return ENTER;
  default: 
    if ((c=='.')||(isdigit(c))){
      ungetc(c,stdin);
      scanf("%d", &yylval);
      return NUM;
    } else {
      printf("\nLEX:ERROR! c=%c\n", c);
  	  return -1;}
  }
}

/* 函数march()：如果lookahead与token匹配，则返回真（1），否则返回假（0）。
 */
int match(int token){
	if (lookahead == token){
		lookahead = yylex();
		return 1;
	} else return 0;
}



/* 函数exp()：对表达式进行递归下降的预测分析。表达式可以包含0和正整数，左右小括号和加号，并以回车结尾。
 * 如果分析成功，则该函数返回真（1），否则返回假（0）  
 * 该函数需要改写。
 */
int expr(){
	//TODO
	return 0;
}

int main(){
	lookahead = yylex();
	if (expr()){
		printf("Parsing Success!\n");
	} else {
		printf("Parsing Fail!\n");
	}
	
}