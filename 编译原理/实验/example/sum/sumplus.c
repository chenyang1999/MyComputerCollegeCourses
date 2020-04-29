/* 递归下降的预测分析器
 * 该程序可以连续读入加法表达式并进行语法分析，显示语法分析是否成功。
 * 加法表达式可由+，(,),数字组成，以回车结尾。
 * 该程序应可以连续对多个加法表达式进行语法分析。
 * 空格和table将被忽略，多余的回车也将被忽略。
 * 该程序的输入可以为标准输入（键盘），也可以为任意文本文件，输出可以为标准输出（屏幕），也可以为任意文本文件。
 * 如遇文件结尾，则分析结束。
 */
#include <ctype.h>
#include <stdio.h>

#define PLUS   		1
#define LPAREN		2
#define RPAREN		3
#define NUM				4
#define ENTER			5

#define LEXERROR  -10
//#define EOF -1

FILE *yyin; 
FILE *yyout;

int yylval;
int lookahead;

int yylex();
int match(int token);
int expr();
void print(int token);
void printError(int token);

/* 词法分析器，每调用一次返回一个token，可能的token有：+， （， ），\n，number，另外还有表示文件结尾的EOF
 * 分析出错时返回LEXERROR。
 */
int yylex(){
  int c;
  do{
    c=getc(yyin);
  } while(c==' ' || c=='\t');
  switch(c){
  case '+': return PLUS;
  case '(': return LPAREN;
  case ')': return RPAREN;
  case '\n': return ENTER;
  case EOF: return EOF;
  default: 
    if ((c=='.')||(isdigit(c))){
    	ungetc(c,yyin);
      fscanf(yyin,"%d", &yylval);
      return NUM;
    } else {
      fprintf(yyout, "\nLEX:ERROR! c=%c\n", c);
  	  return LEXERROR;}
  }
}

/* 打印当前 token 到输出文件中，如果当前token不是合法的，则打印 ERROR TOKEN
 */
void print(int token){
	switch(token){
		case LPAREN:
			fprintf(yyout, "LPAREN ");
			break;
		case RPAREN:
			fprintf(yyout, "RPAREN ");
			break; 
		case PLUS:
			fprintf(yyout, "PLUS ");
			break;
		case NUM:
			fprintf(yyout, "NUM(%d) ", yylval);
			break;
		case ENTER:
			fprintf(yyout, "ENTER\n");
			break;
		case EOF:
			fprintf(yyout, "EOF");
			break;
		default:
			fprintf(yyout, "ERROR TOKEN");
	}
}

/* 语法分析出错时，打印出错的token到输出文件中。
 */
void printError(int token){
	fprintf(yyout, "PARSE_ERROR:(");
	print(token);
	fprintf(yyout, ")");
}

/* 函数march()：如果lookahead与token匹配，则返回真（1），否则返回假（0）。可附带打印已匹配的token或者未匹配的token。
 */
int match(int token){
	if (lookahead == token){
		print(token);
		lookahead = yylex();
		return 1;
	} else{ 
		printError(lookahead);
		return 0;
	}
}

/* 函数exp()：对表达式进行递归下降的预测分析。表达式可以包含数字（0和正整数），左右小括号和加号。
 * 如果分析成功，则该函数返回真（1），否则返回假（0）  
 * 该函数需要改写。
 */
int expr(){
	//TODO
	return 0;
}

/* 对一行表达式进行递归下降的预测分析。
 * 一行表达式由一个表达式跟上一个回车组成。 
 * 返回真（1）表示分析成功，返回假（0）表示分析失败。
 */
int exprln(){
	return(expr() && match(ENTER));
}

int main(int argc, char ** argv){
  //可以指定输入输出文件，默认为键盘输入屏幕输出。
	if (argc>=2){
	  if ((yyin = fopen(argv[1], "r")) == NULL){
	    printf("Can't open file %s\n", argv[1]);
	    return 1;
	  }
	  if (argc>=3){
	    yyout=fopen(argv[2], "w");
	  } else {
	  	yyout = stdout;
	  }
	} else {
		yyin = stdin;
		yyout =stdout;
	}
	//lookahead指向当前输入缓冲器的第一个token
	lookahead = yylex();
	while(lookahead != EOF){
		while(lookahead == ENTER){
			lookahead = yylex();
		}
		if (lookahead == EOF){
			break;
		}
		//分析成功则显示分析成功，并继续分析下一个表达式
		//分析失败则显示分析失败，跳过当前的token，继续分析下一个表达式
		if (exprln()){
			fprintf(yyout, "Parsing Success!\n");
		} 	else {
			fprintf(yyout, "Parsing Fail!\n");
			lookahead = yylex();
		}
	}
	
	if(argc>=2){
	  fclose(yyin);
	  if (argc>=3) fclose(yyout);
	}
	return 0;
}