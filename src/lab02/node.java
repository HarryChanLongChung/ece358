package lab02;

import common.*;

import java.util.PriorityQueue;
import java.util.List;

public class node {
    public PriorityQueue<arrivalEvent> eventQueue;
    private int collisionCrt;

    public node(double arrivalRate, int rt, int packetSize) {
        List<arrivalEvent> list 
            = utilities.generateArrivalEvents(rt, arrivalRate, packetSize);
        eventQueue.addAll(list);
        collisionCrt = 0;
    }

    public expoBackOff() {
    }

    
}