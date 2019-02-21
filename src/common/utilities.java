package common;

import java.util.Random;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class utilities {
    public static double getNextExpoRandomVariable(double rate) {
        Random rand = new Random();
        double u = rand.nextDouble();
        return (Math.log(1.00 - u) / (-1*rate));
    }

    public static List<observerEvent> generateObserverEvents(int maxTime, double rate) {
        List<observerEvent> events = new ArrayList<observerEvent>();
        double ctr = 0;
        
        while (ctr <= maxTime) {
            ctr += getNextExpoRandomVariable(rate);
        	events.add(new observerEvent(ctr));
        }
        return events;
    }

    public static List<arrivalEvent> generateArrivalEvents(int maxTime, double rate, int avgPacketSize) {
        List<arrivalEvent> events = new ArrayList<arrivalEvent>();
        double ctr = 0;
        
        while (ctr <= maxTime) {
            ctr += getNextExpoRandomVariable(rate);
        	events.add(new arrivalEvent(ctr, (int)getNextExpoRandomVariable(1.00/avgPacketSize)));
        }
        return events;
    }

    public static PriorityQueue<simulatedEvent> getNewEventQueue(int cap) {
        return new PriorityQueue<simulatedEvent>(cap, new simulatedEventComparator());
    }

    public static PriorityQueue<arrivalEvent> getNewArrivalEventQueue(int cap) {
        return new PriorityQueue<arrivalEvent>(cap, new simulatedEventComparator());
    }
}