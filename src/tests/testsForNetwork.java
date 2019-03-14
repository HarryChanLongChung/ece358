package tests;

import lab02.*;
import java.io.File;
import java.io.PrintWriter;

public class testsForNetwork {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        boolean isPresistent = Boolean.parseBoolean(args[0]);

        int nodeNumbers[] = {20, 40, 60, 80, 100};
        double arrivalRates[] = {7.0, 10.0, 20.0};

        sb.append("arrivalRate, nodeNumber, efficiency, throughput,\n");
        for (double ar : arrivalRates) {
            sb.append(ar);
            for (int node : nodeNumbers) {
                System.out.print("Arrival Rate: " + ar + " Node Number: " + node);
                
                network simulation = new network(node, ar, 1500, isPresistent, 100);
                simulation.run();

                System.out.println("  ... DONE!! " + simulation.getSuccessfulTransmitted() + ", " + simulation.getTotalTransmitted() + ", " + simulation.getLastTimeStamp());

                sb.append(',');
                sb.append(node);
                sb.append(',');
                sb.append(simulation.genereateEfficiency());
                sb.append(',');
                sb.append(simulation.calculateThroughput());
                sb.append(",\n");
            }
        }

        try {
            PrintWriter pw = new PrintWriter(new File(args[1]));
            pw.write(sb.toString());
            pw.close();
        } catch (Exception e) {
            System.out.println("ERROR: cannot open target file");
        }
    }
}