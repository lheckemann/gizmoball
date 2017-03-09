
release.zip: testing.pdf design.pdf diagram.pdf gantt.png jars
	zip release.zip testing.pdf design.pdf diagram.pdf gantt.png gizmoball-?.?.?-*.jar

testing.pdf: validation.md junit.md
	printf "%% Testing strategy\n%% Group JS8\n" > testing.md
	cat validation.md junit.md >> testing.md
	pandoc -f markdown -t latex testing.md -o testing.pdf

design.pdf: design.md
	pandoc -f markdown -t latex design.md -o design.pdf

diagram.pdf: diagram.dia
	dia -t svg -e diagram.svg diagram.dia
	inkscape diagram.svg --export-pdf=diagram.pdf

jars:
	cd ../../ && make jars
	cp ../../target/gizmoball-?.?.?-*.jar .
