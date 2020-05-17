本例修改自Appel的Tiger编译器前端。

本例给出某文法的一个简单的语法分析器。它是在parser0的基础上添加语义动作而得到的。
输入：用该文法所表示的语言写的源程序文件。
输出：分析树的数据结构描述，可以显示在屏幕上，也可以写入指定的文件中。


该语言的文法为：（与parser0文法相同）
program--> PROGRAM ID; vardec BEGIN stmts END. 
vardec-->   VAR declist 
declist-->  ID:INTEGER;
stmts--> ID := exp
exp--> INT

该语法分析器的测试例（输入文件）为：testcases/test0.p

该语法分析器的词法分析器由lex编写，本例并未给出lex规则源程序，只给出了flex编译后
的c语言程序lex.yy.c。本词法分析器实际上可以识别“编译原理课程设计”实验1所给语言的所有记号，
另外还支持以(*开始，以*)结束的注释（不支持注释的嵌套）。

parsetest.c为该语法分析器主控程序，包含main函数。

util.c含有一般工具函数，util.h是其头文件。

errormsg.c含有错误管理函数，errormsg.h是其头文件。

parser.y是语法分析器的文法说明文件。该文法说明文件中的终结符声明包含了“编译原理课程设计”
实验1所给语言的所有记号（终结符）。在该文法说明文件中，我们为产生式添加了语义动
作，语义动作为产生语法分析树。

ast.h文件给出了语法分析器所应生成的语法分析树（抽象语法树）的各部分的数据结构；而
ast.c则为这些数据结构提供了构造函数（注意：ast.c提供的构造函数并不完整，需要你进行
完善）

prast.c文件含有语法分析树各个数据结构的打印函数。prast.h是其头文件。

testcases文件夹下的文件为语法分析器的测试例，test0.p为当前语法分析器可通过的测试例，
test1.p为“编译原理课程设计”实验1所示文法的一个测试例。你写的其他测试例也应该放在
该文件夹下。

makefile文件表示了本例的编译方法：
在cygwin下，输入make，将会得到一些中间object文件以及可执行文件parser1.exe；
输入 make clean，会将编译及运行中生成的文件删除（只留下原有文件）；
编译完成后，输入make test00，将会以testcases/test0.p为输入源程序运行生成的语法分析
器parser1.exe，输出的分析树将显示在屏幕上。
编译完成后，输入make test01，将会以testcases/test0.p为输入源程序运行生成的语法分析
器parser1.exe，输出的分析树将写入指定文件test0.out中。