##LAB01

To run this simulation, run the following command in order:

```
$ make clean
$ make 
$ make run
```

The command - "make run" allows will run the simultaion with default command
To run the simulation is a specific command, using the following command:

1. Make sure you are at the root directory
2. Run with the following syntax
```
java -cp "src/:./build/lab01.jar:build/" tests.testsForSimulator {bufferSize C L duration min_p max_p step_p output_csv_name} 
```
3. See the output result in the .csv file

! Please include the .csv file extension when providing the output_csv_name.s

##LAB02

To run this simulation, run the following command in order:

```
$ make clean
$ make cp_lab02
$ make run_lab02
```

The command - "make run_lab02" allows will run the simultaion with default command
To run the simulation is a specific command, using the following command:

1. Make sure you are at the root directory
2. Run with the following syntax
```
java -cp "src/:./build/lab02.jar:build/" tests.testsForNetwork {isPresistent output_csv_name} 
```
3. See the output result in the .csv file

! Please include the .csv file extension when providing the output_csv_name.s