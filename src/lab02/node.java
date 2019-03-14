package lab02;

import common.*;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class node {
    // TIME_P = 512 * (1 / R)
    private static double TIME_P = 5.12e-4;
    private static double TIME_BIT = 1e-6;
    
    private PriorityQueue<arrivalEvent> eventQueue;
    private int collisionCrt;
    private int waitBackOffCrt;

    int r = 0;
    double timeDelay = 0.0;

    public node(double arrivalRate, int rt, int packetSize) {
        List<arrivalEvent> list 
            = utilities.generateArrivalEventsFixedSize(rt, arrivalRate, packetSize);
        eventQueue 
            = utilities.getNewArrivalEventQueue(list.size());
        eventQueue.addAll(list);
        collisionCrt = 0;
        waitBackOffCrt = 0;
    }

    public void handleCollision() {
        collisionCrt++;
        waitBackOffCrt = 0;
    	if (collisionCrt >= 10 && !eventQueue.isEmpty()) {
    	    eventQueue.remove();
            collisionCrt = 0;
    	} else {
            expoBackOff(collisionCrt);
    	}
    }

    public void expoBackOff(int counter) {
        Random rand = new Random();
        double backoffTime = rand.nextDouble() * (Math.pow(2, counter) - 1) * TIME_P;
        double minWaitTime = getLastestEventTimeStamp() + backoffTime;

        reorganizeEventQueue(minWaitTime);
    }

    public double getLastestEventTimeStamp() {
        return eventQueue.isEmpty() ? -1 : eventQueue.peek().timestamp;
    }

    public void randomWait(double waitTime) {
        waitBackOffCrt++;
        if (waitBackOffCrt >= 10 && !eventQueue.isEmpty()) {
            eventQueue.remove();
            waitBackOffCrt = 0;
        } else {
            expoBackOff(waitBackOffCrt);
            if(getLastestEventTimeStamp() < waitTime) {
                randomWait(waitTime);
            }
        }
    }

    public void notifyWaitTime(double waitTime, boolean isPresistent) {
        if (eventQueue.isEmpty()) return;
        if (isPresistent) {
            reorganizeEventQueue(waitTime);
        } else {
            randomWait(waitTime);
        }
    }


    public void successfulTranmission() {
        if (eventQueue.isEmpty()) return;
    	eventQueue.remove();
        collisionCrt = 0;
        waitBackOffCrt = 0;
    }

    private void reorganizeEventQueue(double minWaitTime) {
        ArrayList<arrivalEvent> updatedEvents = new ArrayList<arrivalEvent>();

        for (int i = 0; i < eventQueue.size(); i++) {
            double nextTimeStamp = eventQueue.peek().timestamp;
            if (nextTimeStamp < minWaitTime) {
                arrivalEvent e = eventQueue.remove();
                e.updateTimeStamp(minWaitTime);
                updatedEvents.add(e);
                minWaitTime += TIME_BIT;
            }
        }

        eventQueue.addAll(updatedEvents);
    }

    public void print() {
        for (arrivalEvent e : eventQueue) {
            System.out.println(e.timestamp + "" + e.packetSize);
        }
        System.out.println(eventQueue.size());
    }

    public int getQueueSize() {
        return eventQueue.size();
    }
}