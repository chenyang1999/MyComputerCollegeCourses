# 实验2：用Lex设计词法分析器1

# 实验3：用Lex设计词法分析器2

陈扬_17150011001

缪倩玲_17070001148

---

目录:

[toc]

---

##实验目的

（1）学会使用Lex构造简单的词法分析器。

（2）能主动思考词法分析器和语法分析器后续的链接问题

## 学习资料：

1. 实验内容和要求。见BB平台“实验”模块。
2. 实验参考资料。主要是Lex源程序”exam1.l”;“exam2.l”及parser0目录中testcases目录下的“test1.p”测试例。 见BB平台“实验”模块。
3. Lex使用指南。见BB平台“实验”模块。

## 实验要求

### ex02

使用 lex 为下述文法语言写一个词法分析器。

使用lex为下述文法语言写一个词法分析器。
语言文法：

```sql
<程序>->PROGRAM <标识符> ; <分程序>
<分程序> -><变量说明> BEGIN <语句表> END.
<变量说明> -> VAR <变量说明表>;
<变量说明表>-> <变量表>: <类型> | <变量表>: <类型>; <变量说明表>
<类型>-> INTEGER | REAL
<变量表>-> <变量> | <变量>, <变量表>
<语句表>-> <语句> | <语句>; <语句表>
<语句>-> <赋值语句> | <条件语句> | <WHILE语句> | <复合语句>
<赋值语句>-> <变量> := <算术表达式>
<条件语句>-> IF <关系表达式> THEN <语句> ELSE <语句>
<WHILE语句>-> WHILE <关系表达式> DO <语句>
<复合语句>->  BEGIN <语句表> END
<算术表达式>->  <项> | <算术表达式> + <项> | <算术表达式> - <项>
<项>->  <因式> | <项> * <因式> | <项> / <因式>
<因式>-> <变量> | <常数> | (<算术表达式>)
<关系表达式>-> <算术表达式> <关系符> <算术表达式>
<变量>-> <标识符>
<标识符>-> <标识符><字母> | <标识符><数字> | <字母>
<常数>-> <整数> | <浮点数>
<整数>-> <数字> | <数字> <整数>
<浮点数>-> .<整数> | <整数>.<整数>
<关系符>-> < | <= | = | > | >=| <>
<字母>-> A | B | …| X | Y | Z | a | b | …| x | y | z 
<数字>-> 0|1|2|…|9          
```

要求：输入为用该语言所写的源程序文件；输出为记号序列，每个记号显示为二元组(记号名，记号属性值)的形式。输出可以在屏幕上，也可以输出到文件中。不要求建立符号表。

在cygwin下用flex和gcc工具将实验调试通过，并能通过例子parser0中testcases目录下的test1.p测试例的测试。

### ex03

实验目的：学会用lex设计一个词法分析器，并考虑其与后续语法分析器的链接问题。

实验内容：修改上次实验2的词法分析器，使其满足下列要求。

实验要求：

1. 要求每次调用词法分析函数yylex时，只返回一个记号(token)；

2. 为记号选择适当的属性值，并且每次词法分析函数返回记号前，都将记号的属性值存入全局变量yylval中。（yylval可以自己定义为全局变量）；

3. 记号属性值的选择：标识符的属性为标识符的名字字符串（例如，标识符name1的属性为字符串”name1”），整数的属性为整数值，浮点数的属性为浮点数值。其他记号属性值可自己选择。关键字可以省略属性。

4. 注意：由于属性值需要存入yylval中，并且记号属性值的类型比较多（可能为字符串、整数、浮点数等），因此yylval必须能同时存放各种类型的值（提示：将yylval设置为union类型）。

5. 在cygwin下用flex和gcc

## 实验内容2

### 添加关键字记号名的定义

```c
<程序> PROGRAM <标识符> ; <分程序>
<分程序> <变量说明> BEGIN <语句表> END.
<变量说明>  VAR <变量说明表>;
<变量说明表>-> <变量表>: <类型> | <变量表>: <类型>; <变量说明表>
<类型>-> INTEGER | REAL
<变量表>-> <变量> | <变量>, <变量表>
```

```c
#define PROGRAM				28
#define VAR					29
#define	BEG				30
#define END					31
#define INTEGER  			35
#define REAL				36
```

```
<语句表>-> <语句> | <语句>; <语句表>
<语句>-> <赋值语句> | <条件语句> | <WHILE语句> | <复合语句>
<赋值语句>-> <变量> := <算术表达式>
<条件语句>-> IF <关系表达式> THEN <语句> ELSE <语句>
<WHILE语句>-> WHILE <关系表达式> DO <语句>
<复合语句>->  BEGIN <语句表> END
<算术表达式>->  <项> | <算术表达式> + <项> | <算术表达式> - <项>
<项>->  <因式> | <项> * <因式> | <项> / <因式>
<因式>-> <变量> | <常数> | (<算术表达式>)
<关系表达式>-> <算术表达式> <关系符> <算术表达式>
<变量>-> <标识符>
```

```c
#define IF					15
#define ELSE				16
#define WHILE				17
#define	DO					18
#define THEN				32
```

### 添加正规式定义

```
<标识符>-> <标识符><字母> | <标识符><数字> | <字母>
<常数>-> <整数> | <浮点数>
<整数>-> <数字> | <数字> <整数>
<浮点数>-> .<整数> | <整数>.<整数>
<关系符>-> < | <= | = | > | >=| <>
<字母>-> A | B | …| X | Y | Z | a | b | …| x | y | z 
<数字>-> 0|1|2|…|9  
```

```c
delim		[ \t \n]
ws			{delim}+
letter	[A-Za-z]
digit		[0-9]
id			({letter})({letter}|{digit})*
float	{digit}?(\.{digit}+)(E[+-]?{digit}+)?
integer  		{digit}+
```



### 翻译规则

```c
<INITIAL>{ws}	          { ;}
<INITIAL>PROGRAM		  { return (PROGRAM);}
<INITIAL>IF				  { return (IF);}
<INITIAL>ELSE			  { return(ELSE);}
<INITIAL>THEN		      { return (THEN);}
<INITIAL>WHILE			  { return (WHILE);}
<INITIAL>DO		          { return (DO);}
<INITIAL>VAR			  { return (VAR); }
<INITIAL>BEGIN		      { return (BEG);}
<INITIAL>INTEGER		  { return (INTEGER);}
<INITIAL>REAL		      { return (REAL);}
<INITIAL>END		      { return (END);}
<INITIAL>{id}	          { return (ID);}
<INITIAL>{integer}	      { return (INT);}
<INITIAL>{float}	      { return (FLOAT);}
<INITIAL>"<"	          { yylval=LT;return (RELOP);}
<INITIAL>"<="	          { yylval=LE;return (RELOP);}
<INITIAL>"=="	          { yylval=EQ;return (RELOP);}
<INITIAL>"!="	          { yylval=NE;return (RELOP);}
<INITIAL>">"	          { yylval=GT;return (RELOP);}
<INITIAL>">="	          { yylval=GE;return (RELOP);}
<INITIAL>":="			  {	yylval=ASSIG;return (RELOP);}
<INITIAL>"("			  {	yylval=LLB;return (RELOP);}
<INITIAL>")"			  {	yylval=RLB;return (RELOP);}

<INITIAL>"+"			  {	yylval=PLUS;return (PLUS);}
<INITIAL>"-"			  {	yylval=MINUS;return (MINUS);}
<INITIAL>"*"			  {	yylval=MULT;return (MULT);}
<INITIAL>"/"			  {	yylval=DIV;return (DIV);}

<INITIAL>":"			  {	yylval=COLON;return (RELOP);}
<INITIAL>";"			  {	yylval=SEMIC;return (RELOP);}
<INITIAL>"."			  {	yylval=POINT;return (RELOP);}
<INITIAL>","			  { yylval=COMMA;return (RELOP); }
<INITIAL>.				  { return ERRORCHAR;}
```

### 输出函数

```c
void writeout(int c){
  switch(c){
    case ERRORCHAR: fprintf(yyout, "(ERRORCHAR, \"%s\") ", yytext);break;
    case RELOP: fprintf(yyout, "(RELOP, \"%s\") ", yytext);break;  	  

    case PLUS: fprintf(yyout, "(PLUS, \"%s\") ", yytext);break;  	  
    case MINUS: fprintf(yyout, "(MINUS, \"%s\") ", yytext);break;  	  
    case MULT: fprintf(yyout, "(MULT, \"%s\") ", yytext);break;  	  
    case DIV: fprintf(yyout, "(DIV, \"%s\") ", yytext);break;  	
      
    case WHILE: fprintf(yyout, "(WHILE, \"%s\") ", yytext);break;
    case DO: fprintf(yyout, "(DO, \"%s\") ", yytext);break;
    case IF: fprintf(yyout, "(IF, \"%s\") ", yytext);break;
    case ELSE: fprintf(yyout, "(ELSE, \"%s\") ", yytext);break;
    case THEN: fprintf(yyout, "(THEN, \"%s\") ", yytext);break;
    case FLOAT: fprintf(yyout, "(FLOAT, \"%s\") ", yytext);break;
    case ID: fprintf(yyout, "(ID, \"%s\") ", yytext);break;
    case NEWLINE: fprintf(yyout, "\n");break;
    case PROGRAM: fprintf(yyout, "(PROGRAM, \"%s\") ", yytext);break;
    case VAR: fprintf(yyout, "(VAR, \"%s\") ", yytext);break;
    case INT: fprintf(yyout, "(INT, \"%s\") ", yytext);break;
    case END: fprintf(yyout, "(END, \"%s\") ", yytext);break;
    case INTEGER: fprintf(yyout, "(INTEGER, \"%s\") ", yytext);break;
    case REAL: fprintf(yyout, "(REAL, \"%s\") ", yytext);break;
    default:break;
  }
  return;
}
```

### 代码测试

编译源代码并例子parser0中testcases目录下的test1.p测试例

test1.p:

```pascal
PROGRAM test;
VAR i, j, k: INTEGER;
    f0: REAL;
BEGIN
  i := 1;
  j := 1;
  k := 0;
  f0 := 3.2;
  WHILE k<=100 DO
    BEGIN
      IF j <20 THEN
        BEGIN
          j := i;
          k := k+1;
          f0 := f0*0.2
        END
      ELSE 
        BEGIN
          j := k;
          k := k-2;
          f0 := f0/.2
        END
    END
END.
```

```shell
flex lab2.l
gcc lex.yy.c -ll -o a2
./a2 parser0/testcases/test1.p
```

![image-20200511150259327](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-11-070300.png)

实验分析:我们可以从实验结果得出:

- 关键词PROGRAM、VAR、INTEGER、REAL、BEGIN、END、DO、WHILE、ELSE、IF、ELSE 能够被正确的识别
- 能分析运算符号`:=,>,=<,等等`为 RELOP
- 能够正确识别`+-*/`四则运算符号
- 能够正确识别`NUM,INT,FLOAT`常数,整型和浮点型

结论:实验2正确.

## 实验内容 3

### 问题分析

将yylval的值存储为指定的类型的值，分析可以知道：

​	关键字： 使用原来的 yylval 即可

​	浮点数：值得类型是FlOAT

​	整形： 值类型INT

​	ID： 值类型是字符串，利用字符数组来存储，义了一个cha str[32]的字符串。

### 代码实现

额外定义一个联合体来存储各种类型的值

```c
typedef union{
  int ival;
  float fval;
  char str[32];
}Type;
Type yylval_2;
```

使用自带的函数 atoi()动态进行各种类型转换

正规式子识别INT和 FLOAT的时候使用 atoi(),识别 ID 的时候使用 strcpy()：

```c
<INITIAL>{integer}			{ yylval_2.ival=atoi(yytext);return (INT);}
<INITIAL>{float}				{ yylval_2.fval=atof(yytext);return (FLOAT);}
<INITIAL>{id}	          { strcpy(yylval_2.str,yytext);return (ID);}
```

识别其值的时候存储到 ival,使用宏定义即可

修改writeout() 函数打印:

```c
case INT: fprintf(yyout, "(INT, \"%s\" ,value: %d)", yytext,yylval_2.ival);break;
case FLOAT: fprintf(yyout, "(FLOAT, \"%s\" ,value: %f)", yytext,yylval_2.fval);break;
case ID: fprintf(yyout, "(ID, \"%s\" ,value: %f)", yytext,yylval_2.str);break;
```

### 代码测试

![image-20200511184504965](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-11-104505.png)

我们可以看到,现在 ID,INT,FLOAT 中有人具体的值,类型也识别正确

实验 3 成功

---

## 实验总结

陈扬:通过实验一的学习,我已经很熟练的掌握运用了 flex 和 gcc 以及 lex 语法,在实验 2 3 的学习中更深入理解了词法生成器属性这一章节的内容

谬倩玲:我在做实验的过程中遇到问题参考了很多网上的资料,帮助陈扬同学收集解决代码问题的办法,在编写实验报告的过程中也更深入理解了词法生成器.

合作分工:陈扬负责了代码编写和实际试验的测试,谬倩玲同学负责试验结果的描述和排版汇总,这个实验两个人都付出了相等的精力.

### 附录

实验 3完整 代码

```c
/* 把讨厌的注释去掉 */

%{
#include <stdio.h> 
#define LT					1
#define	LE					2
#define GT					3
#define	GE					4
#define	EQ					5
#define NE					6
#define COLON				8
#define POINT				7
#define LLB					9
#define RLB					10
#define SEMIC				13
#define ASSIG				14

#define IF					15
#define ELSE				16
#define WHILE				17
#define	DO					18


#define ID          19
#define FLOAT       20
#define RELOP       21
#define NEWLINE     22
#define ERRORCHAR   23

#define PLUS 		24
#define MINUS 		25
#define MULT	26
#define DIV		27

#define PROGRAM				28
#define VAR					29
#define	BEG				30
#define END					31

#define THEN				32
#define COMMA				33
#define INT   				34
#define INTEGER  			35
#define REAL				36

int yylval;
typedef union{
  int ival;
  float fval;
  char str[16];
}Type;
Type yylval_2;

%}

delim		[ \t \n]
ws			{delim}+
letter	[A-Za-z]
digit		[0-9]
id			({letter})({letter}|{digit})*
float	{digit}?(\.{digit}+)(E[+-]?{digit}+)?
integer  		{digit}+
%%

<INITIAL>{ws}	          { ;}
<INITIAL>PROGRAM		  { return (PROGRAM);}
<INITIAL>IF				  { return (IF);}
<INITIAL>ELSE			  { return(ELSE);}
<INITIAL>THEN		      { return (THEN);}
<INITIAL>WHILE			  { return (WHILE);}
<INITIAL>DO		          { return (DO);}
<INITIAL>VAR			  { return (VAR); }
<INITIAL>BEGIN		      { return (BEG);}
<INITIAL>INTEGER		  { return (INTEGER);}
<INITIAL>REAL		      { return (REAL);}
<INITIAL>END		      { return (END);}

<INITIAL>{integer}			{ yylval_2.ival=atoi(yytext);return (INT);}
<INITIAL>{float}				{ yylval_2.fval=atof(yytext);return (FLOAT);}
<INITIAL>{id}	          { strcpy(yylval_2.str,yytext);return (ID);}

<INITIAL>"<"	          { yylval=LT;return (RELOP);}
<INITIAL>"<="	          { yylval=LE;return (RELOP);}
<INITIAL>"=="	          { yylval=EQ;return (RELOP);}
<INITIAL>"!="	          { yylval=NE;return (RELOP);}
<INITIAL>">"	          { yylval=GT;return (RELOP);}
<INITIAL>">="	          { yylval=GE;return (RELOP);}
<INITIAL>":="			  {	yylval=ASSIG;return (RELOP);}
<INITIAL>"("			  {	yylval=LLB;return (RELOP);}
<INITIAL>")"			  {	yylval=RLB;return (RELOP);}

<INITIAL>"+"			  {	yylval=PLUS;return (PLUS);}
<INITIAL>"-"			  {	yylval=MINUS;return (MINUS);}
<INITIAL>"*"			  {	yylval=MULT;return (MULT);}
<INITIAL>"/"			  {	yylval=DIV;return (DIV);}

<INITIAL>":"			  {	yylval=COLON;return (RELOP);}
<INITIAL>";"			  {	yylval=SEMIC;return (RELOP);}
<INITIAL>"."			  {	yylval=POINT;return (RELOP);}
<INITIAL>","			  { yylval=COMMA;return (RELOP); }
<INITIAL>.				  { return ERRORCHAR;}

 
%%

int yywrap (){
  return 1;
}

void writeout(int c){
  switch(c){
    case ERRORCHAR: fprintf(yyout, "(ERRORCHAR, \"%s\") ", yytext);break;
    case RELOP: fprintf(yyout, "(RELOP, \"%s\") ", yytext);break;  	  

    case PLUS: fprintf(yyout, "(PLUS, \"%s\") ", yytext);break;  	  
    case MINUS: fprintf(yyout, "(MINUS, \"%s\") ", yytext);break;  	  
    case MULT: fprintf(yyout, "(MULT, \"%s\") ", yytext);break;  	  
    case DIV: fprintf(yyout, "(DIV, \"%s\") ", yytext);break;  	
      
    case WHILE: fprintf(yyout, "(WHILE, \"%s\") ", yytext);break;
    case DO: fprintf(yyout, "(DO, \"%s\") ", yytext);break;
    case IF: fprintf(yyout, "(IF, \"%s\") ", yytext);break;
    case ELSE: fprintf(yyout, "(ELSE, \"%s\") ", yytext);break;
    case THEN: fprintf(yyout, "(THEN, \"%s\") ", yytext);break;
    case NEWLINE: fprintf(yyout, "\n");break;
    case PROGRAM: fprintf(yyout, "(PROGRAM, \"%s\") ", yytext);break;
    case VAR: fprintf(yyout, "(VAR, \"%s\") ", yytext);break;
    case END: fprintf(yyout, "(END, \"%s\") ", yytext);break;
    case INTEGER: fprintf(yyout, "(INTEGER, \"%s\") ", yytext);break;
    case REAL: fprintf(yyout, "(REAL, \"%s\") ", yytext);break;
    
    case INT: fprintf(yyout, "(INT, \"%s\") ,value: %d", yytext,yylval_2.ival);break;
    case FLOAT: fprintf(yyout, "(FLOAT, \"%s\" ,value: %f)", yytext,yylval_2.fval);break;
    case ID: fprintf(yyout, "(ID, \"%s\" ,value: %s)", yytext,yylval_2.str);break;
    
    default:break;
  }
  return;
}


int main (int argc, char ** argv){
	int c,j=0;
	if (argc>=2){
	  if ((yyin = fopen(argv[1], "r")) == NULL){
	    printf("Can't open file %s\n", argv[1]);
	    return 1;
	  }
	  if (argc>=3){
	    yyout=fopen(argv[2], "w");
	  }
	}

	while (c = yylex()){
		writeout(c);
		j++;
		if (j%5 == 0) writeout(NEWLINE);
	}
	if(argc>=2){
	  fclose(yyin);
	  if (argc>=3) fclose(yyout);
	}
	return 0;
}

```



## reference

flex 教程:https://pandolia.net/tinyc/ch13_bison.html

gcc教程:https://zhuanlan.zhihu.com/p/27190930

字符串正则表达式:https://blog.csdn.net/xfxyy_sxfancy/article/details/45024573

