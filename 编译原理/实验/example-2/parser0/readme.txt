本例修改自Appel的Tiger编译器前端。

本例给出某文法的一个简单的语法分析器。
输入：用该文法所表示的语言写的源程序文件。
输出：屏幕显示语法分析是否成功。


该语言的文法为：
program--> PROGRAM ID; vardec BEGIN stmts END. 
vardec-->   VAR declist 
declist-->  ID:INTEGER;
stmts--> ID := exp
exp--> INT

该语法分析器的测试例（输入文件）为：testcases/test0.p

该语法分析器的词法分析器由lex编写，本例并未给出lex规则源程序，只给出了flex编译后的c语言程序lex.yy.c。本词法分析器实际上可以识别编译原理课程设计实验1所给语言的所有记号，另外还支持以(*开始，以*)结束的注释（不支持注释的嵌套）。

parsetest.c为该语法分析器主控程序，包含main函数。

util.c含有一般工具函数，util.h是其头文件。

errormsg.c含有错误管理函数，errormsg.h是其头文件。

parser.y是语法分析器的文法说明文件。该文法说明文件中的终结符声明包含了编译原理课程设计实验1所给语言的所有记号（终结符）。

testcases文件夹下的文件为语法分析器的测试例，test0.p为当前语法分析器可通过的测试例，test1.p为编译原理课程设计实验1中所示文法的一个测试例。你写的其他测试例也应该放在该文件夹下。

makefile文件表示了本例的编译方法：
在cygwin下，输入make，将会得到一些中间object文件以及可执行文件parser0.exe；
输入 make clean，会将编译及运行中生成的文件删除（只留下原有文件）；
编译完成后，输入make test00，将会以testcases/test0.p为输入源程序运行生成的语法分析器parser0.exe
编译完成后，输入make test01，将会测试参数错误时parser0.exe的行为。