import java.util.Random;
import lab01.utilities;

public class testExpRandomVariable {
    public static void main(String[] args) {
        double[] m = new double[1000];
        for (int i = 0; i < 1000; i++) {
            m[i] = utilities.getNextExpoRandomVariable(75);
        }

        // The mean
        double mean = 0.0;
        for (double variable : m) {
            mean += variable;
        } 
        mean /= 1000;

        // The variance
        double variance = 0.0;
        for (double variable : m) {
            variance += (variable - mean) * (variable - mean);
        }
        variance /= 1000;

        System.out.print(mean);
        System.out.println();
        System.out.print(variance);
        System.out.println();

    }
}