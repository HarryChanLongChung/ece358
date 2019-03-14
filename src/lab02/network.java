package lab02;

import java.util.PriorityQueue;

import java.util.List;
import java.util.ArrayList;

public class network {
    private static double PROP_DELAY_PER_NODE = 5e-8;
    private static double TRANS_DELAY = 1.5e-3;

    private boolean isPresistent = false;
    private node[] nodes;
    private PriorityQueue<nodeTimeStamp> arrivalEvents;

    private int crtTransmittedPkg = 0;
    private int crtSuccessfulTransmittedPkg = 0;
    private int avgPacketLength;
    private double lastEventTs;


    public network(int N, double arrivalRate, int packetLength, boolean persistent, int rt) {
        isPresistent = persistent;
        avgPacketLength = packetLength;

        int totalPkg = 0;
        // setup all node
        nodes = new node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new node(arrivalRate, rt, packetLength);
            totalPkg += nodes[i].getQueueSize();
        }
        System.out.print(" totalpkg = " + totalPkg);
        arrivalEvents = new PriorityQueue<nodeTimeStamp>(N, new nodeTimeStampComparator());
    }

    public void run(){
        lastEventTs = 0.00;
        // Pick the lastest event
        for (int i = 0; i < nodes.length; i++) {
            double ts = nodes[i].getLastestEventTimeStamp();
            if (ts != -1) {
                arrivalEvents.add(new nodeTimeStamp(ts, i));
            }
        }

        while (!arrivalEvents.isEmpty()) {
            // Detect collision
            List<nodeTimeStamp> collideNts = collisionDetection();

            if (collideNts.size() == 1) {
                // no collision
                crtSuccessfulTransmittedPkg++;
                crtTransmittedPkg++;
                nodeTimeStamp successNodeTs = arrivalEvents.remove();

                for (int i = 0; i < nodes.length; i++) {
                    double delay = TRANS_DELAY + PROP_DELAY_PER_NODE * (double) Math.abs(successNodeTs.nodePos - i);
                    if (successNodeTs.nodePos == i) {
                        // same node
                        nodes[i].successfulTranmission();
                        double nextTs = nodes[i].getLastestEventTimeStamp();
                        if (nextTs > successNodeTs.getTimeStamp() + delay) {
                            arrivalEvents.add(new nodeTimeStamp(nextTs, i));
                        } else {
                            nodes[i].notifyWaitTime(successNodeTs.getTimeStamp() + delay, isPresistent);
                        }
                    } else {
                        // other nodes
                        double nextTs = nodes[i].getLastestEventTimeStamp();
                        if (nextTs <= successNodeTs.getTimeStamp() + delay && nextTs >= 0) {
                            arrivalEvents.remove(new nodeTimeStamp(nextTs, i));
                            nodes[i].notifyWaitTime(successNodeTs.getTimeStamp() + delay, isPresistent);
                            arrivalEvents.add(new nodeTimeStamp(nodes[i].getLastestEventTimeStamp(), i));
                        }
                    }
                }
            } else {
                // collision happen
                for (nodeTimeStamp nts : collideNts) {
                    int nodePos = nts.getNodePos();
                    arrivalEvents.remove(new nodeTimeStamp(nts.getTimeStamp(), nodePos));
                    nodes[nodePos].handleCollision();
                    double newTs = nodes[nodePos].getLastestEventTimeStamp();
                    if(newTs > 0) {
                        arrivalEvents.add(new nodeTimeStamp(newTs, nodePos));
                    }
                    crtTransmittedPkg ++;
                }
            }
        }
    }

    public List<nodeTimeStamp> collisionDetection() {
        nodeTimeStamp lastestEvent = arrivalEvents.peek();
        lastEventTs = lastestEvent.getTimeStamp();
        List<nodeTimeStamp> collideNts = new ArrayList<nodeTimeStamp>();
        for (nodeTimeStamp e : arrivalEvents) {
            double delay = PROP_DELAY_PER_NODE * (double) Math.abs(lastestEvent.nodePos - e.nodePos);
            if (e.getTimeStamp() <= (lastestEvent.getTimeStamp() + delay)){
                collideNts.add(e);
            }
        }
        return collideNts;
    }
    
    public double genereateEfficiency() {
    	return (double) crtSuccessfulTransmittedPkg / crtTransmittedPkg;
    }

    public double calculateThroughput() {
    	return  ((double)crtSuccessfulTransmittedPkg * avgPacketLength) / lastEventTs;
    }

    public int getSuccessfulTransmitted() {
    	return crtSuccessfulTransmittedPkg;
    }

    public int getTotalTransmitted() {
    	return crtTransmittedPkg;
    }

    public double getLastTimeStamp() {
    	return lastEventTs;
    }
}