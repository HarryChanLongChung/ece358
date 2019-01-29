JFLAGS = -g
JC = javac
JAR = jar

default: classes

classes:
	$(JC) -d ./build src/lab01/*
	$(JAR) cvf ./build/lab01.jar ./build/lab01

clean:
	$(RM) *.class
	$(RM) -rf build/*
	$(RM) src/tests/*.class

# java -cp "src/:./build/lab01.jar:build/" tests.testsForSimulator