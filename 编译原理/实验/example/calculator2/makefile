cal2.exe: cal.tab.o lex.yy.o  
	gcc -o cal2 cal.tab.o lex.yy.o -ly

lex.yy.o: lex.yy.c cal.tab.h
	gcc -c lex.yy.c

cal.tab.o: cal.tab.c
	gcc -c cal.tab.c

lex.yy.c: cal.l
	flex cal.l

cal.tab.c: cal.y
	bison -dv cal.y

cal.tab.h: cal.y
	echo "cal.tab.h was created at the same time as cal.tab.c."

clean:
	rm -f cal2.exe lex.yy.o cal.tab.o lex.yy.c cal.tab.c cal.tab.h cal2.exe.stackdump cal.output
