package lab02;

import java.util.PriorityQueue;
import java.util.List;
import java.util.Collections;

import common.*;

public class network {
    private static int D = 10;
    private static int S_PROP = 200000000;
    private static double T_PROP = 5e-7;

    private boolean isPresistent = false;
    private node[] nodes;
    private PriorityQueue<observerEvent> eventQueue;
    private PriorityQueue<Double> arrivalEventsTimeStamp;

    private int crtTransmittedPkg = 0;
    private int crtSuccessfulTransmittedPkg = 0;
    private int simulationTime;//time simulation 
    private int avgPacketLength;


    public network(int N, double arrivalRate, int R, int packetLength, boolean persistent, int rt) {
        isPresistent = persistent;
        simulationTime = rt;
        avgPacketLength = packetLength;
        // setup observer event
        List<observerEvent> observerList 
            = utilities.generateObserverEvents(rt, arrivalRate*15);
        eventQueue 
            = utilities.getNewObserverEventQueue(observerList.size());
        eventQueue.addAll(observerList);

        // setup all node
        nodes = new node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new node(arrivalRate, rt, packetLength);
        }
    }

    public void run(){
        // Pick the lastest event
        for (node n : nodes) {
            arrivalEventsTimeStamp.add(n.getLastestEventTimeStamp());
        }

        // Detect collision
        List<node> collideNodes = collisionDetection();

        if (collideNodes.isEmpty()) {
            // no collision
        	crtSuccessfulTransmittedPkg ++;
            double waitTime = 0.0;
            for (node n : nodes) {
                if (n.getLastestEventTimeStamp() > waitTime) {
                    if (isPresistent) {
                        n.randomWait(waitTime);
                    } else {
                        n.notifyWaitTime(waitTime);
                    }
                }
            }

        } else {
            for (node n : collideNodes) {
                n.expoBackOff();
            }
        }
    }

    public List<node> collisionDetection() {
        List<node> updatedEvents = Collections.emptyList();
        return updatedEvents;
    }
    
    public double genereateEfficiency() {
    	return crtSuccessfulTransmittedPkg / crtTransmittedPkg;

    }

    public double calculateThroughput() {
    	return (crtSuccessfulTransmittedPkg * avgPacketLength) / simulationTime;
    }
    
}