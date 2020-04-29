cal0.exe: cal.tab.c
	gcc -o cal0 cal.tab.c -ly

cal.tab.c: cal.y
	bison -v cal.y

clean:
	rm -f cal0.exe cal.tab.c cal.output
