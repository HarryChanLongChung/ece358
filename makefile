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

run: 
	# java -cp "src/:./build/lab01.jar:build/" tests.testsForSimulator {bufferSize C L duration min_p max_p step_p output_csv_name} 
	java -cp "src/:./build/lab01.jar:build/" tests.testsForSimulator 0 1000000 2000 1000 0.25 0.95 0.1 test.csv