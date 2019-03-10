package lab02;

import common.*;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class node {
    // TIME_P = 512 * (1 / R)
    private static double TIME_P = 5.12e-4;
    private static double TIME_BIT = 1e-6;
    
    private PriorityQueue<arrivalEvent> eventQueue;
    private int collisionCrt;

    int r = 0;
    double timeDelay = 0.0;

    public node(double arrivalRate, int rt, int packetSize) {
        List<arrivalEvent> list 
            = utilities.generateArrivalEvents(rt, arrivalRate, packetSize);
        eventQueue 
            = utilities.getNewArrivalEventQueue(list.size());
        eventQueue.addAll(list);
        collisionCrt = 0;
    }

    public void handleCollision() {
        collisionCrt++;
    	if (collisionCrt > 10) {
    	    eventQueue.remove();
            collisionCrt = 0;
    	} else {
            expoBackOff();
    	}
    }

    public void expoBackOff() {
        Random rand = new Random();
        double backoffTime = rand.nextDouble() * (Math.pow(2, collisionCrt) - 1) * TIME_P;
        double minWaitTime = getLastestEventTimeStamp() + backoffTime;
        System.out.println("min: "+minWaitTime);
        ArrayList<arrivalEvent> updatedEvents = new ArrayList<arrivalEvent>();

        double nextTimeStamp = eventQueue.peek().timestamp;
        while (nextTimeStamp < minWaitTime && !eventQueue.isEmpty()) {
            System.out.println("next_st: "+nextTimeStamp);
            arrivalEvent e = eventQueue.remove();
            e.updateTimeStamp(minWaitTime);
            updatedEvents.add(e);
            minWaitTime += TIME_BIT;
            nextTimeStamp = eventQueue.peek().timestamp;
        }

        eventQueue.addAll(updatedEvents);
    }

    public double getLastestEventTimeStamp() {
        return eventQueue.isEmpty() ? -1 : eventQueue.peek().timestamp;
    }

    public void randomWait(double waitTime) {

    }

    public void notifyWaitTime(double waitTime) {

    }

    public void print() {
        for (arrivalEvent e : eventQueue) {
            System.out.println(e.timestamp + "" + e.packetSize);
        }
        System.out.println(eventQueue.size());
    }

    public void verify() {
        double lastTS = -1.0;
        double newTS;
        while (!eventQueue.isEmpty()) {
            newTS = eventQueue.remove().timestamp;
            if(newTS < lastTS){
                System.out.println("STH is wrong");
            }
            lastTS = newTS;
        }
    }
}