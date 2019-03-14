JFLAGS = -g
JC = javac
JAR = jar

default: common_classes

common_classes:
	$(JC) -O -d ./build src/common/*
	$(JAR) cvf ./build/common.jar ./build/common/*

lab01_classes:
	$(JC) -cp "src/:./build/common.jar:build/" -d ./build src/lab01/*
	$(JAR) cvf ./build/lab01.jar ./build/lab01/*
	$(JC) -cp "src/:./build/lab01.jar:build/" src/tests/testsForSimulator.java

lab02_classes:
	$(JC) -O -cp "src/:./build/common.jar:build/" -d ./build src/lab02/*
	$(JAR) cvf ./build/lab02.jar ./build/lab02/*
	$(JC) -O -cp "src/:./build/lab02.jar:build/" src/tests/testsForNetwork.java

clean:
	$(RM) *.class
	$(RM) -rf build/*
	$(RM) src/tests/*.class
	$(RM) *.csv

run_lab01: 
	# java -cp "src/:./build/lab01.jar:build/" tests.testsForSimulator {bufferSize C L duration min_p max_p step_p output_csv_name} 
	java -cp "src/:./build/lab01.jar:build/" tests.testsForSimulator 0 1000000 2000 1000 0.25 0.95 0.1 test.csv

cp_lab02:
	make common_classes 
	make lab02_classes

run_lab02: 
	# java -cp "src/:./build/lab02.jar:build/" tests.testsForNetwork {isPresistent output_csv_name} 
	java -cp "src/:./build/lab02.jar:build/" tests.testsForNetwork false test_np.csv