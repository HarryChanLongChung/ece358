package tests;

import lab01.simulator;

public class testsForSimulator {

    public static void main(String[] args) {
        int C = 1000000;
        int L = 2000;

        simulator sim = new simulator(10, C, L, 75.00, 7000.00, 1000);

        for (double p = 0.25; p <= 0.95; p+=0.1) {
            double arrivalRate = p*C/L;
            System.out.println("p: " + p + ", arrival rate: " + arrivalRate);
            sim.reset(10, C, L, arrivalRate, arrivalRate*10, 1000);
            sim.initialize();
            sim.run();
            System.out.println();
        }

        System.out.println("Double time frame");

        for (double p = 0.25; p <= 0.95; p+=0.1) {
            double arrivalRate = p*C/L;
            System.out.println("p: " + p + ", arrival rate: " + arrivalRate);
            sim.reset(10, C, L, arrivalRate, arrivalRate*10, 2000);
            sim.initialize();
            sim.run();
            System.out.println();
        }

    }
}