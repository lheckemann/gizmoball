define images

![Class diagram](design.png)

![Build mode](screenshots/BuildModeScreenshot.PNG)

![Build mode - add selection](screenshots/BuildModeAddSelectionScreenshot.PNG)

![Run mode](screenshots/RunModeScreenshot.PNG)

![Run mode - stop](screenshots/RunStopScreenshot.PNG)

endef
export images

report.pdf: report.md
	pandoc -f markdown -t latex report.md -o report.pdf

report.md: specs.md usecases.md physics.md design.png triggering.md design.md projectplan.md
	printf "%% Preliminary design\n%% Group JS8\n" > report.md
	cat specs.md usecases.md physics.md triggering.md design.md projectplan.md >> report.md
	printf "%s" "$$images" >> report.md

design.png: design.dia
	dia -t png -s 2000x -e design.png design.dia
	convert design.png -rotate 90 design.png
