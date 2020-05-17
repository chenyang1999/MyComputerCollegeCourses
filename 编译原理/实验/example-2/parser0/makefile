parser0.exe: parsetest.o parser.tab.o lex.yy.o errormsg.o util.o 
	gcc -o parser0 parsetest.o parser.tab.o lex.yy.o errormsg.o util.o 

parsetest.o: parsetest.c errormsg.h util.h
	gcc -c parsetest.c

parser.tab.o: parser.tab.c util.h errormsg.h
	gcc -c parser.tab.c

parser.tab.c: parser.y
	bison -dv parser.y

parser.tab.h: parser.tab.c
	echo "parser.tab.h was created at the same time as parser.tab.c"

errormsg.o: errormsg.c errormsg.h util.h
	gcc -c errormsg.c


lex.yy.o: lex.yy.c parser.tab.h errormsg.h util.h
	gcc -c lex.yy.c

#lex.yy.c: parser.l
#	flex parser.l


util.o: util.c util.h
	gcc -c util.c


#²âÊÔÀıtest0.p£¨ÕıÈ·ÊäÈë£©
test00:
	./parser0.exe testcases/test0.p

#²âÊÔÀıtest0.p £¨´íÎóÊäÈë£©
test01:
	./parser0.exe testcases/test0.p test0.out


clean: 
	rm -f parser0.exe util.o parsetest.o lex.yy.o errormsg.o parser.tab.c parser.tab.h parser.tab.o *.stackdump parser.output *.out *.bak

