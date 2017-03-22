
js8.zip: validation.pdf design.pdf source.zip gizmoball-0.0.1.jar
	zip js8.zip validation.pdf design.pdf source.zip gizmoball-0.0.1.jar

validation.pdf: validation.md
	pandoc -f markdown -t latex validation.md -o validation.pdf

design.pdf: design.md
	pandoc -f markdown -t latex design.md -o design.pdf

source.zip:
	git archive --format zip > source.zip

gizmoball-0.0.1.jar:
	cd ../../ && make jars
	cp ../../target/gizmoball-0.0.1.jar .
