package lab02;

import java.util.PriorityQueue;
import java.util.List;

import common.*;

public class network {
    private static int D = 10;
    private static int S_PROP = 200000000;
    private static double T_PROP = 5e-7;

    private boolean isPresistent = false;
    private node[] nodes;
    private PriorityQueue<observerEvent> eventQueue;
    private PriorityQueue<Double> arrivalEvents;

    private int crtTransmittedPkg = 0;
    private int crtSuccessfulTransmittedPkg = 0;
    private int rt = 0;//time simulation 


    public network(int N, double arrivalRate, int R, int packetLength, boolean persistent, int rt) {
        isPresistent = persistent;
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
            arrivalEvents = n.getLastestEvent();
        }

        // Detect collision
        List<node> collideNodes = collisionDetection();

        if (collideNodes.isEmpty()) {
            // no collision
            double waitTime = 0.0;
            for (node n : nodes) {
                if (n.getLastestEvent().timestamp > waitTime) {
                    if (isPresistent) {
                        n.randomWait(waitTime);
                    } else {
                        arrivalEvents = n.notifyWaitTime(waitTime);
                    }
                }
            }

        } else {
            for (node n : collideNodes) {
                n.expoBackOff();
            }
        }
    }

    public void collisionDetection() {

    }
    
    public double efficiency(int crtTransmittedPkg, int crtSuccessfulTransmittedPkg) {
    	return crtSuccessfulTransmittedPkg/crtTransmittedPkg;

    }

    public double throughput(int crtSuccessfulTransmittedPkg,int avgPacketSize) {
    	return (crtSuccessfulTransmittedPkg*avgPacketSize)/rt; //double check line 20 if u agree w it cuz it was the only way to get rt here wout getting an error
    }
    
}