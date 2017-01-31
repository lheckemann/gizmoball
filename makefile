define spec

# Specification

endef
export spec

define usecases

# Use cases

endef
export usecases

define design
\\newpage
![Class diagram](docs/design.png)
endef
export design

define screenshots
![Build mode](docs/screenshots/BuildModeScreenshot.PNG)
![Build mode - add selection](docs/screenshots/BuildModeAddSelectionScreenshot.PNG)
![Run mode](docs/screenshots/RunModeScreenshot.PNG)
![Run mode - stop](docs/screenshots/RunStopScreenshot.PNG)
endef
export screenshots

docs: docs/report.pdf

docs/report.pdf: docs/report.md
	pandoc -f markdown -t latex docs/report.md -o docs/report.pdf

docs/report.md: docs/specs.md docs/usecases.md docs/design.png
	echo "$$spec" >> docs/report.md
	cat docs/specs.md >> docs/report.md
	echo "$$usecases" >> docs/report.md
	cat docs/usecases.md >> docs/report.md
	echo "$$design" >> docs/report.md
	echo "$$screenshots" >> docs/report.md

docs/design.png: docs/design.dia
	dia -t png -s 2000x -e docs/design.png docs/design.dia
	convert docs/design.png -rotate 90 docs/design.png
