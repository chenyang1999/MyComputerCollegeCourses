/*
 * errormsg.c - functions used in all phases of the compiler to give
 *              error messages about the program.
 *
 */

/* 本文件定义各种错误管理操作 */

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include "util.h"
#include "errormsg.h"

extern void resetLexState();
extern void yyrestart(FILE * newfile);

bool anyErrors= FALSE;

static string fileName = "";

static int lineNum = 1;//行号，用于报错

int EM_tokPos=0;

extern FILE *yyin;
extern FILE *yyout;

extern int yydebug;//bison中用于查错的标记，yydebug非0时，输出错误信息。



typedef struct intList {int i; struct intList *rest;} *IntList;

static IntList intList(int i, IntList rest) 
{IntList l= checked_malloc(sizeof *l);
 l->i=i; l->rest=rest;
 return l;
}

static IntList linePos=NULL; //行内位置，用于报错。

/* 换行时，处理行号和行内位置的函数。 */
void EM_newline(void)
{lineNum++;
 linePos = intList(EM_tokPos, linePos);
}

/* 报错函数，message是错误信息格式串，pos是错误位置。 */
void EM_error(int pos, char *message,...)
{va_list ap;//可变参数列表指针
 IntList lines = linePos; 
 int num=lineNum;
 

  anyErrors=TRUE;
  while (lines && lines->i >= pos) 
       {lines=lines->rest; num--;}

  if (fileName) fprintf(stderr,"%s:",fileName);
  if (lines) fprintf(stderr,"%d.%d: ", num, pos-lines->i);
  va_start(ap,message);//ap指向message这个参数的地址
  //格式打印到文件stderr中，格式串为message，所需的参数由ap指针指向
  vfprintf(stderr, message, ap);
  va_end(ap);//ap指针归零
  fprintf(stderr,"\n");

}


/* 重置关于错误管理的一些设置。 */
void EM_reset(string fname, string output)
{
 anyErrors=FALSE; fileName=fname; lineNum=1;
 linePos=intList(0,NULL);
 yyin = fopen(fname,"r");
 if (output){
 	yyout = fopen(output, "w");
 }
 yyrestart(yyin);//用于重置缓冲区的内容
 /* 在flex中，函数 void yyrestart(FILE * newfile) 会将yyin的指针指向newfile的开始处，并且，
  * 原lex输入缓冲区中的内容全部抹掉。这可以用来重启yylex()。（因为当文件读取一半时，重新
  * 调用yylex仍会接着原文件的位置分析，即使重新制定yyin，缓冲区内的内容也还是原来的内容）
  * 该函数不会重置lex的状态为INITIAL。
  */
 resetLexState(); //重置lex状态为INTIAL。
 if (!yyin) {EM_error(0,"cannot open"); exit(1);}
}

/* 此函数用于设定语法分析查错 */
void EM_yydebug(){
	yydebug = 1;
}

