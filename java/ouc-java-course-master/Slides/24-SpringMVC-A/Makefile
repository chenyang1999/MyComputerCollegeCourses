FILE=springmvc01
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
	rm -f ${FILE}.aux ${FILE}.log ${FILE}.nav ${FILE}.out ${FILE}.pdf ${FILE}.snm ${FILE}.toc
