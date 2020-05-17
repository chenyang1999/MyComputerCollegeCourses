%{
#include "cal.tab.h"

int yywrap(void){
  return 1;
}
%}

ws			[ \t]
digit	 	[0-9]
inum		{digit}+
fnum 		{digit}*\.{digit}+

%%

{inum} 	{sscanf(yytext, "%lf", &yylval); return NUM;}
{fnum}	{sscanf(yytext, "%lf", &yylval); return NUM;}
"+"			{return PLUS;}
"*"			{return TIMES;}
"-"			{return MINUS;}
"/"			{return DIVIDE;}
"("			{return LPAREN;}
")"			{return RPAREN;}
{ws}		{;}
"\n"		{return ENTER;}
.				{printf("\nLEX:ERROR! c=%s\n", yytext);}
