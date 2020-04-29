parser1.exe: parsetest.o parser.tab.o lex.yy.o errormsg.o util.o ast.o prast.o
	gcc -o parser1 parsetest.o parser.tab.o lex.yy.o errormsg.o util.o ast.o prast.o

parsetest.o: parsetest.c errormsg.h ast.h util.h prast.h
	gcc -c parsetest.c

parser.tab.o: parser.tab.c ast.h util.h errormsg.h
	gcc -c parser.tab.c

parser.tab.c: parser.y
	bison -dv parser.y

parser.tab.h: parser.tab.c
	echo "parser.tab.h was created at the same time as parser.tab.c"

errormsg.o: errormsg.c errormsg.h util.h
	gcc -c errormsg.c

ast.o: ast.c ast.h util.h
	gcc -c ast.c

lex.yy.o: lex.yy.c parser.tab.h errormsg.h ast.h util.h
	gcc -c lex.yy.c

#lex.yy.c: parser.l
#	flex parser.l


util.o: util.c util.h
	gcc -c util.c

prast.o: prast.c prast.h ast.h
	gcc -c prast.c


#测试例test0.p（只指定输入文件，屏幕输出）
test00:
	./parser1.exe testcases/test0.p

#测试例test0.p （指定了输入和输出文件）
test01:
	./parser1.exe testcases/test0.p test0.out



clean: 
	rm -f parser1.exe util.o parsetest.o lex.yy.o errormsg.o parser.tab.c parser.tab.h parser.tab.o ast.o prast.o *.stackdump parser.output *.out *.bak

