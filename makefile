jars:
	mvn package

deliverables: docs/preliminary-design/report.pdf docs/preliminary-release/release.zip
	:

docs/preliminary-design/report.pdf:
	cd docs/preliminary-design && make

docs/preliminary-release/release.zip:
	cd docs/preliminary-release && make
