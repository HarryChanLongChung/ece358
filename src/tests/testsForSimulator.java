package tests;

import lab01.simulator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class testsForSimulator {

    public static void main(String[] args) {
        int buffersize = Integer.parseInt(args[0]);
        int C = Integer.parseInt(args[1]);
        int L = Integer.parseInt(args[2]);
        int runtime = Integer.parseInt(args[3]);
        double minP = Double.parseDouble(args[4]);
        double maxP = Double.parseDouble(args[5]);
        double step = Double.parseDouble(args[6]);

        double[] avgNumberOfPackage = new double[(int)((maxP - minP)/step + 2)];
        double[] pIdel = new double[(int)((maxP - minP)/step + 2)];
        double[] pLoss = new double[(int)((maxP - minP)/step + 2)];
        int crt = 0;

        simulator sim = new simulator(0, C, L, 0.00, 0.00, 0);

        for (double p = minP; p <= maxP; p+=step) {
            double arrivalRate = p*C/L;
            System.out.println("p: " + p + ", arrival rate: " + arrivalRate);
            sim.reset(buffersize, C, L, arrivalRate, arrivalRate*15, runtime);
            sim.initialize();
            sim.run(false);

            avgNumberOfPackage[crt] = sim.getAvgPacketInQueue();
            pIdel[crt] = sim.getIdlePortion();
            pLoss[crt] = sim.getLossPortion();
            crt++;
        }
        PrintWriter pw;
        StringBuilder sb = new StringBuilder();
        try {
            pw = new PrintWriter(new File(args[7]));
            sb.append("p, avgNumberOfPackage, pIdel, pLoss,\n");
            crt = 0;
            for (double p = minP; p <= maxP; p+=step) {
                sb.append(p);
                sb.append(',');
                sb.append(avgNumberOfPackage[crt]);
                sb.append(',');
                sb.append(pIdel[crt]);
                sb.append(',');
                sb.append(pLoss[crt]);
                sb.append(',');
                sb.append('\n');
                crt++;
            }
        
            pw.write(sb.toString());
            pw.close();
        } catch (Exception e) {
            System.out.println("ERROR: cannot open target file");
        }
    }
}