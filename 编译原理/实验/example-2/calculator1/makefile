cal1: cal.tab.c
	gcc -o cal1 cal.tab.c

cal.tab.c: cal.y
	bison -v cal.y

clean:
	rm -f cal1.exe cal.tab.c cal.output
