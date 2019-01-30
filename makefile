JFLAGS = -g
JC = javac
JAR = jar

default: classes

classes:
	$(JC) -d ./build src/lab01/*
	$(JAR) cvf ./build/lab01.jar ./build/lab01/*
	$(JC) -cp "src/:./build/lab01.jar:build/" src/tests/testsForSimulator.java

clean:
	$(RM) *.class
	$(RM) -rf build/*
	$(RM) src/tests/*.class
	$(RM) *.csv

# java -cp "src/:./build/lab01.jar:build/" tests.testsForSimulator