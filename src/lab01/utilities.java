package lab01;

import java.util.Random;

public class utilities {
    public static double getNextExpoRandomVariable(double rate) {
        Random rand = new Random();
        double u = rand.nextDouble();
        return (Math.log(1.00 - u) / (-1*rate));
    }

    public List<observerEvent> generateObserverEvents(int maxTime, double rate) {
        List<observerEvent> events = new ArrayList<observerEvent>();
        // TODO implement the generateion procedure
        // counter for time to record the total amount of time that all generations is taking 
        // which will be used as the ts parameter of the Oberserver Events constructor

        // dont overpass maxTime 
        // events.add(e)

        return events;
    }

    public List<arrivalEvent> generateArrivalEvents(int maxTime, double rate) {
        List<arrivalEvent> events = new ArrayList<arrivalEvent>();
        // TODO implement the generateion procedure
        return events;
    }
}