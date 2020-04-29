/* 这是注释的形式，与C中的/*...* /注释相同。 */
/* 注释如果出现在翻译规则部分要有前导空格，否则lex编译出错。*/
/* 第一部分是定义、声明部分。这部分内容可以为空。*/

%{

  /* 写在 %{...%}这对特殊括号内的内容会被直接拷贝到C文件中.
    *
    * 这部分通常进行一些头文件声明，变量（全局，外部）、常量
    * 的定义，用C语法。
    *
    * %{和%}两个符号都必须位于行首 
    */

/* 下面定义了需要识别的记号名，如果和yacc联合使用，这些记号名都应该在yacc中定义 */
#include <stdio.h> 
#define LT					1
#define	LE					2
#define GT					3
#define	GE					4
#define	EQ					5
#define NE					6

#define WHILE				18
#define	DO					19
#define ID          20
#define NUMBER      21
#define RELOP       22

#define NEWLINE     23
#define ERRORCHAR   24

#define LEFTBRACKET 25	/*bracket:()*/
#define RIGHTBRACKET 26
#define SEMICOLON 27		/*分号*/
#define ASSIGN 28			/*赋值*/
#define ARITHMETIC 29		/*算术*/
#define NOTE 30			/*注释*/
#define IF 31
#define ELSE 32
#define LEFTBRACES 33		/*braces 为大括号:{}*/
#define RIGHTBRACES 34

int yylval;
  /* yylval 是yacc中定义的变量，用来保存记号的属性值，默认是int类型。 
    * 在用lex实现的词法分析器中可以使用这个变量将记号的属性传递给用
    * yacc实现的语法分析器。
    *
    * 注意：该变量只有在联合使用lex和yacc编写词法和语法分析器时才可在lex
    *       中使用，此时该变量不需要定义即可使用。
    *       单独使用lex时，编译器找不到这个变量。这里定义该变量为了“欺骗”编译器。
    */
 
int installID();
int installNum(); 
  /* 声明翻译规则中使用到的辅助函数。
    */

%}

  /* 这里进行正规定义和状态定义。
    * 下面就是正规定义，注意，正规定义和状态定义都要顶着行首写。
    */
 
delim		[ \t\n]
/* \用来表示转义，例如\t表示制表符，\n表示换行符。*/
ws			{delim}+
letter	[A-Za-z]
digit		[0-9]
id			{letter}({letter}|{digit})*
  /* 注意：上面正规定义中出现的小括号表示分组，而不是被匹配的字符。
    *       而大括号括起的部分表示正规定义名。
    */
number	{digit}+(\.{digit}+)?(E[+-]?{digit}+)?



/* %%作为lex文件三个部分的分割符，必须位于行首 */
/* 下面这个%%不能省略 */
%%

  /* 第二部分是翻译规则部分。 */
  /* 写在这一部分的注释要有前导空格，否则lex编译出错。*/
  /* 翻译规则的形式是：正规式  {动作}
   * 其中，正规式要顶行首写，动作要以C语法写（动作会被拷贝到yylex()函数中，），\
   * 正规式和动作之间要用空白分割。
   */
  
{ws}	          {;/* 此时词法分析器没有动作，也不返回，而是继续分析。 */}
  /* 正规式部分用大括号扩住的表示正规定义名，例如{ws}。
   * 没有扩住的直接表示正规式本身。
   * 一些元字符没办法表示它本身，此时可以用转义字符或
   * 用双引号括起来，例如"<"
   */
while	          { return (WHILE);}
do		          { return (DO);}
{id}	          { yylval = installID (); return (ID);}
{number}	      { yylval = installNum (); return (NUMBER);}
"<"	            { yylval = LT; return (RELOP);}
"<="	          { yylval = LE; return (RELOP);}
"="	            { yylval = EQ; return (RELOP);}
"<>"	          { yylval = NE; return (RELOP);}
">"	            { yylval = GT; return (RELOP);}
">="	          { yylval = GE; return (RELOP);}

.								{ yylval = ERRORCHAR; return ERRORCHAR;}
 /*.匹配除换行之外的任何字符，一般可作为最后一条翻译规则。*/
 
%%
  /* 第三部分是辅助函数部分，这部分内容以及前面的%%都可以省略 */
  /* 辅助函数可以定义“动作”中使用的一些函数。这些函数
    * 使用C语言编写，并会直接被拷贝到lex.yy.c中。
    */

int installID () {
  /* 把词法单元装入符号表并返回指针。*/
	return ID;
}


int installNum () {
	/* 类似上面的过程，但词法单元不是标识符而是数 */
  return NUMBER;
}


  /* yywrap这个辅助函数是词法分析器遇到输入文件结尾时会调用的，用来决定下一步怎么做：
    * 若yywrap返回0，则继续扫描；返回1，则词法分析器返回报告文件已结束的0。
    * lex库中的标准yywrap程序就是返回1，你也可以定义自己的yywrap。
    */
int yywrap (){
  return 1;
}

/* 以一定格式向词法分析器的输出文件yyout写出记号二元组 */
void writeout(int c){
  switch(c){
  	case ERRORCHAR: fprintf(yyout, "(ERRORCHAR, \"%s\") ", yytext);break;
  	case RELOP: fprintf(yyout, "(RELOP, \"%s\") ", yytext);break;  	  
    case WHILE: fprintf(yyout, "(WHILE, \"%s\") ", yytext);break;
    case DO: fprintf(yyout, "(DO, \"%s\") ", yytext);break;
    case NUMBER: fprintf(yyout, "(NUM, \"%s\") ", yytext);break;
    case ID: fprintf(yyout, "(ID, \"%s\") ", yytext);break;
    case NEWLINE: fprintf(yyout, "\n");break;
    default:break;
  }
  return;
}

  /* 辅助函数里可以使用yytext和yyleng这些外部定义的变量。
    * yytext指向输入缓冲区当前词法单元(lexeme)的第一个字符，
    * yyleng给出该词法单元的长度		*/

  /* 如果你的词法分析器并不是作为语法分析器的子程序，
    * 而是有自己的输入输出，你可以在这里定义你的词法
    * 分析器的main函数，main函数里需要调用yylex()
    */

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
	/* yyin和yyout是lex中定义的输入输出文件指针，它们指明了
	 * lex生成的词法分析器从哪里获得输入和输出到哪里。
	 * 默认：键盘输入，屏幕输出。 
	 */
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
