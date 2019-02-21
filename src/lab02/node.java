package lab02;

import common.*;

import java.util.PriorityQueue;
import java.util.List;

public class node {
    public PriorityQueue<arrivalEvent> eventQueue;
    private int collisionCrt;
    double timeDelay = 0.0;
    private static double TIME_P = 5.12e-4;
    int r = 0;

    public node(double arrivalRate, int rt, int packetSize) {
        List<arrivalEvent> list 
            = utilities.generateArrivalEvents(rt, arrivalRate, packetSize);
        eventQueue.addAll(list);
        collisionCrt = 0;
    }

    public void expoBackOff() {
    	collisionCrt++;
    	if (collisionCrt>=10) {
    		//collisionCrt = 0; I dunno if i have to do 
    	} else {
    		r=  (int) (Math.random()* (Math.pow(2,collisionCrt)-1));
    		timeDelay = (double) r * TIME_P;
    	}
    	
    	
    }

    
}