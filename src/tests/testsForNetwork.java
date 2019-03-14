package tests;

import lab02.*;

public class testsForNetwork {
    public static void main(String[] args) {
        int numberOfNodes = Integer.parseInt(args[0]);
        double arrivalRate = Double.parseDouble(args[1]);
        boolean presistent = Boolean.parseBoolean(args[2]);
        int simulationTime = Integer.parseInt(args[3]);

        network simulation = new network(numberOfNodes, arrivalRate, 1500, presistent, simulationTime);
        // network simulation = new network(20, 7, 1500, true, 1000);
        simulation.run();
        System.out.println("Efficiency: " + simulation.genereateEfficiency());
        System.out.println("Throughput: " + simulation.calculateThroughput());
    }
}