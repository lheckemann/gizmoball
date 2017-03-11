jars:
	mvn package

deliverables: docs/preliminary-design/report.pdf docs/preliminary-release/release.zip docs/final-release/js8.zip
	:

docs/preliminary-design/report.pdf:
	cd docs/preliminary-design && make

docs/preliminary-release/release.zip:
	cd docs/preliminary-release && make

docs/final-release/js8.zip:
	cd docs/final-release && make
