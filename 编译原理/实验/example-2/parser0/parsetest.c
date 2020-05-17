/* 本文件是语法分析器的主控程序 */
#include <stdio.h>
#include <stdlib.h>
#include "util.h"
#include "errormsg.h"


extern int yyparse(void);//yacc生成的语法分析驱动程序


/* 语法分析函数，如果分析成功，返回0；分析失败，返回1。
 * 参数：输入文件名fname，输出文件名output，
 * 返回值：整数0或1 
 */
int parse(string fname) {  
	int yy ;
	EM_reset(fname, "");
	//EM_yydebug();/*将本行注释去掉则运行时进入错误跟踪状态。*/
	yy = yyparse();
  if (yy == 0) {/* parsing worked */
 	  printf("\nParse Successful!\n");
    return 0;
  }
  else return 1;
}


int main(int argc, char **argv) {
	int i;
	/* 若缺少参数，则显示使用方法。*/
 	if (argc!=2) {fprintf(stderr,"usage: parser0.exe inputfile \n"); exit(1);}
 	/* 只有输入文件的情况 */
 	else {
 		printf("\nParsing begin: %s\n", argv[1]);
 		parse(argv[1]);
 		printf("\nParsing end: %s\n", argv[1]);
 	} 
 	return 0;
}

