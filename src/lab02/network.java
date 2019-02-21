package lab02;

import java.util.PriorityQueue;
import java.util.List;

import common.*;

public class network {
    private static int D = 10;
    private static int S_PROP = 200000000;
    private static double T_PROP = 5e-7;

    private node[] nodes;
    private PriorityQueue<observerEvent> eventQueue;

    public network(int N, double arrivalRate, int R, int packetLength, boolean persistent, int rt) {
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
}