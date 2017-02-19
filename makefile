define images

![Class diagram](docs/preliminary/design.png)

![Build mode](docs/preliminary/screenshots/BuildModeScreenshot.PNG)

![Build mode - add selection](docs/preliminary/screenshots/BuildModeAddSelectionScreenshot.PNG)

![Run mode](docs/preliminary/screenshots/RunModeScreenshot.PNG)

![Run mode - stop](docs/preliminary/screenshots/RunStopScreenshot.PNG)

endef
export images

docs: docs/preliminary/report.pdf

docs/preliminary/report.pdf: docs/preliminary/report.md
	pandoc -f markdown -t latex docs/preliminary/report.md -o docs/preliminary/report.pdf

docs/preliminary/report.md: docs/preliminary/specs.md docs/preliminary/usecases.md docs/preliminary/physics.md docs/preliminary/design.png docs/preliminary/triggering.md docs/preliminary/design.md docs/preliminary/projectplan.md
	printf "%% Preliminary design\n%% Group JS8\n" > docs/preliminary/report.md
	cat docs/preliminary/specs.md docs/preliminary/usecases.md docs/preliminary/physics.md docs/preliminary/triggering.md docs/preliminary/design.md docs/preliminary/projectplan.md >> docs/preliminary/report.md
	printf "%s" "$$images" >> docs/preliminary/report.md

docs/preliminary/design.png: docs/preliminary/design.dia
	dia -t png -s 2000x -e docs/preliminary/design.png docs/preliminary/design.dia
	convert docs/preliminary/design.png -rotate 90 docs/preliminary/design.png
