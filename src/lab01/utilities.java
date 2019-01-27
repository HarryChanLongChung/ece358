package lab01;

import java.util.Random;

public class utilities {
    public static double getNextExpoRandomVariable(double rate) {
        Random rand = new Random();
        double u = rand.nextDouble();
        return (Math.log(1.00 - u) / (-1*rate));
    }
}