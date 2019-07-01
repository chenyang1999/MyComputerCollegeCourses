LATEX = latex
BIBTEX = bibtex
L2H = latex2html
PDFLATEX = pdflatex
DVIPS = dvips #-t letter
DVIPDFM = dvipdf
XELATEX = xelatex

RERUN = "(There were undefined references|Rerun to get (cross-references|the bars) right)"
RERUNBIB = "No file.*\.bbl|Citation.*undefined"

FILENAME = lecturenotes-for-Java-Application-and-Development
GOALS = $(FILENAME).pdf
DVIFILES = $(FILENAME).dvi

COPY = if test -r $*.toc; then cp $*.toc $*.toc.bak; fi
RM = rm -f

all: $(GOALS)#  main


%.pdf:%.tex
	$(XELATEX) $<

all:
	echo -ne "####### First Processing #######"
	$(XELATEX) $(FILENAME).tex
	echo -ne "####### Second Processing #######"
	$(XELATEX) $(FILENAME).tex
	#echo -ne "####### CJK bookmark Processing #######"
	#gbk2uni $(FILENAME).out
	#echo -ne "####### Last Processing #######"
	#$(PDFLATEX) $(FILENAME).tex

pdf:
	$(PDFLATEX) $(FILENAME).tex

bookmark:
	gbk2uni $(FILENAME).out

clean:
	-rm -f *.aux *.log *.bbl *.blg *.brf *.cb *.ind *.idx *.ilg  \
	*.inx *.ps *.dvi *.pdf *.toc *.out *.lof *.lot

