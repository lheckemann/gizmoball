
release.zip: testing.pdf design.pdf jars
	zip release.zip testing.pdf design.pdf gizmoball-?.?.?-*.jar

testing.pdf: validation.md junit.md
	printf "%% Testing strategy\n%% Group JS8\n" > testing.md
	cat validation.md junit.md >> testing.md
	pandoc -f markdown -t latex testing.md -o testing.pdf

design.png: design.dia
	dia -t png -s 4000x -e design.png design.dia
	convert design.png -rotate 90 design.png

design.pdf: design.png design.md
	pandoc -f markdown -t latex design.md -o design.pdf

jars:
	cd ../../ && make jars
	cp ../../target/gizmoball-?.?.?-*.jar .
