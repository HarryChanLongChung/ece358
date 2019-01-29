package tests;

import lab01.simulator;

public class testsForSimulator {

    public static void main(String[] args) {
        int buffersize = Integer.parseInt(args[0]);
        int C = Integer.parseInt(args[1]);
        int L = Integer.parseInt(args[2]);
        int runtime = Integer.parseInt(args[3]);
        double minP = Double.parseDouble(args[4]);
        double maxP = Double.parseDouble(args[5]);
        double step = Double.parseDouble(args[6]);

        simulator sim = new simulator(0, C, L, 0.00, 0.00, 0);

        for (double p = minP; p <= maxP; p+=step) {
            double arrivalRate = p*C/L;
            System.out.println("p: " + p + ", arrival rate: " + arrivalRate);
            sim.reset(buffersize, C, L, arrivalRate, arrivalRate*15, runtime);
            sim.initialize();
            sim.run();
            System.out.println();
        }

        // System.out.println("Double time frame");

        // for (double p = 0.25; p <= 0.95; p+=0.1) {
        //     double arrivalRate = p*C/L;
        //     System.out.println("p: " + p + ", arrival rate: " + arrivalRate);
        //     sim.reset(10, C, L, arrivalRate, arrivalRate*10, 2000);
        //     sim.initialize();
        //     sim.run();
        //     System.out.println();
        // }

    }
}