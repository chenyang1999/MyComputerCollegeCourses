本例写了一个实数算术表达式的计算器。可以计算实数的加减乘除，可识别负数和括号。无论输入为
整数还是实数，通通识别为实数。

输入输出：键盘输入一行算术表达式，以换行结束，屏幕输出计算结果。该过程可重复进行，
直到出现词法或语法错误而退出，或者按ctrl+c结束程序。

词法分析程序用lex编写。
语法分析规则中使用的文法为二义的算术表达式文法。


注意：
int yywrap(void)函数中void不能少，否则出warning。
makefile中 gcc *.tab.o lex.yy.o -ly ，-ly必须在最后，否则可能出现重复定义main函数等错误。
makefile中， cal2.exe: cal.tab.o lex.yy.o ，lex.yy.o必须在cal.tab.o后面，否则会先编译lex.yy.o发现找不到cal.tab.h


yacc程序中不要随便引用yytext和yyleng，容易出问题。