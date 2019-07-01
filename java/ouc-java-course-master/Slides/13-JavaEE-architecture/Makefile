FILE=JavaEE-architecture
ViewPDF=evince #acroread OR evince
XeLaTeX=xelatex

pdf: ${FILE}.pdf

${FILE}.pdf: ${FILE}.tex
	${XeLaTeX} $<
	${XeLaTeX} $<
# cp -f $@ ../PDF/

view:
	${ViewPDF} ${FLIE}.pdf

clean:
	rm -f ${FILE}.aux ${FILE}.log ${FILE}.nav ${FILE}.out ${FILE}.vrb ${FILE}.snm ${FILE}.toc
