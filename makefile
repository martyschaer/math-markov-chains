slides:
	pandoc -t beamer slides.md -o slides.pdf --template templates/beamer.tex --pdf-engine=xelatex --slide-level 2
	mupdf slides.pdf
handout:
	pandoc handout.md -o handout.pdf --template templates/handout.tex
	mupdf handout.pdf
all: slides handout 

clean:
	rm -f slides.pdf
	rm -f handout.pdf
