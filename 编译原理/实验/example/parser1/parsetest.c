/* 本文件是语法分析器的主控程序 */
#include <stdio.h>
#include <stdlib.h>
#include "ast.h"
#include "errormsg.h"
#include "prast.h"


extern int yyparse(void);//yacc生成的语法分析驱动程序
extern a_prog program;//program的分析树存放于此
extern FILE * yyout;

/* 语法分析函数，如果分析成功，返回语法分析树；分析失败，返回NULL。
 * 参数：输入文件名fname，输出文件名output，
 * 返回值：语法分析树 
 */
a_prog parse(string fname, string output) {  
	int yy ;
	EM_reset(fname, output);
	//EM_yydebug();/*将本行注释去掉则运行时进入错误跟踪状态。*/
	yy = yyparse();
  if (yy == 0) {/* parsing worked */
 	  printf("\nParse Successful!\n");
    return program;
  }
  else return NULL;
}


int main(int argc, char **argv) {
	int i;
	a_prog prog; //prog存放program的分析树。
	/* 若缺少参数，则显示使用方法。*/
 	if ((argc!=2)&&(argc!=3)) {fprintf(stderr,"usage: a.exe inputfile [outputfile]\n"); exit(1);}
 	/* 若只有输入文件，则输出显示在屏幕上 */
 	else if (argc == 2){
 		printf("\nParsing begin: %s\n", argv[1]);
 		prog = parse(argv[1], "");
 		pr_prog(yyout, prog); //pr_prog是将program分析树打印出来的函数。
 		printf("\nParsing end: %s\n", argv[1]);
 	} else { /* 输入、输出文件齐全的情况 */
 	  printf("\nParsing begin: %s\n", argv[1]);
 		prog = parse(argv[1], argv[2]);
 		pr_prog(yyout, prog);
 		printf("Parsing end: %s\n", argv[1]);
	}
 	
 	return 0;
}

