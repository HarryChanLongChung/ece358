package lab01;

import java.util.PriorityQueue;
import java.util.List;

public class simulator {
    private PriorityQueue<simulatedEvent> eventQueue;

    // user input parameter
    private double arriveRate;
    private double observeRate;
    private int averagePackageLength;
    private double tranmissionRate;
    private int bufferSize; // 0 for infinite buffer size
    private int runtime;

    private int numberOfPackageInBuffer;
    private int totalBufferSize;
    private Boolean debug = false;
    private double lastPacketDepartureTime;

    public int ctrArrival;
    public int ctrDeparture;
    public int ctrObservation;
    public int ctrIdle;
    public int ctrDropped;
    public int ctrPacketGenerated;

    public simulator(int bs, int tr, int apl, double ar, double or, int rt) {
        reset(bs, tr, apl, ar, or, rt);
    }

    public void initialize() {
        List<observerEvent> observerList 
            = utilities.generateObserverEvents(runtime, observeRate);
        List<arrivalEvent> arrivalList 
            = utilities.generateArrivalEvents(runtime, arriveRate, averagePackageLength);
        eventQueue 
            = utilities.getNewEventQueue(observerList.size() + arrivalList.size());
        eventQueue.addAll(observerList);
        eventQueue.addAll(arrivalList);
    }

    public void run() {
        while (!eventQueue.isEmpty()) {
            simulatedEvent e = eventQueue.remove();
            switch (e.getEventType()) {
                case OBSERVER:
                    handleObserverEvent((observerEvent)e);
                    if (debug) System.out.print("Observe" + " @ ");
                    break;
                case ARRIVAL:
                    handleArrivalEvent((arrivalEvent)e);
                    int packageSize = ((arrivalEvent)e).packetSize;
                    if (debug) System.out.print("Arrival with " + packageSize + " @ ");
                    break;
                case DEPARTURE:
                    handleDepartureEvent((departureEvent)e);
                    if (debug) System.out.print("Depart" + " @ ");
                    break;
                default:
                    break;
            }
            if (debug) System.out.print(e.getTimeStamp() + "(" + numberOfPackageInBuffer + ")");
            if (debug) System.out.println();
        }
        report();
    }

    private void handleObserverEvent(observerEvent e) {
        ctrObservation++;
        if (numberOfPackageInBuffer == 0) ctrIdle++;
        totalBufferSize+=numberOfPackageInBuffer;
    }

    private void handleArrivalEvent(arrivalEvent e) {
        ctrArrival++;
        if (bufferSize == 0) {
            //no buffer limit
            addDepartureEvent(e);
            numberOfPackageInBuffer++;
        } else {
            if (numberOfPackageInBuffer >= bufferSize) {
                ctrDropped++;
                if (debug) System.out.print("DROPPED: ");
            } else {
                addDepartureEvent(e);
                numberOfPackageInBuffer++;
            }
        }
    }

    private void handleDepartureEvent(departureEvent e) {
        ctrDeparture++;
        numberOfPackageInBuffer--;
    }

    private void addDepartureEvent(arrivalEvent e) {
        double deltaTime = (double)e.packetSize / (double)tranmissionRate;
        lastPacketDepartureTime += deltaTime;

        if (numberOfPackageInBuffer == 0) {
            lastPacketDepartureTime = e.getTimeStamp() + deltaTime;
        }

        eventQueue.add(new departureEvent(lastPacketDepartureTime));
    }

    private void report() {
        // System.out.println();
        // System.out.println("Arrival counter: " + ctrArrival);
        // System.out.println("Departure counter: " + ctrDeparture);
        // System.out.println("Observation counter: " + ctrObservation);
        // System.out.println("Idle counter: " + ctrIdle);
        // System.out.println("Packet dropped counter: " + ctrDropped);
        // System.out.println("Packet generated counter: " + ctrPacketGenerated);
        // System.out.println("TotalBufferSize: " + totalBufferSize);
        System.out.println("Average number of packets: " + (double)totalBufferSize/ctrObservation);
        System.out.println("Portion of idle time: " + (double)ctrIdle/ctrObservation);

    }

    private void reset() {
        numberOfPackageInBuffer = 0;
        totalBufferSize = 0;

        lastPacketDepartureTime = 0.00;
        
        ctrArrival = 0;
        ctrDeparture = 0;
        ctrObservation = 0;
        ctrIdle = 0;
        ctrDropped = 0;
        ctrPacketGenerated = 0;
    }

    public void reset(int bs, int tr, int apl, double ar, double or, int rt) {
        bufferSize = bs;
        tranmissionRate = (double)tr;
        averagePackageLength = apl;
        runtime = rt;
        arriveRate = ar;
        observeRate = or;
        
        reset();
    }

    public void display() {
        while (!eventQueue.isEmpty()) {
            simulatedEvent e = eventQueue.remove();
            switch (e.getEventType()) {
                case ARRIVAL:
                    int packageSize = ((arrivalEvent)e).packetSize;
                    System.out.print("Arrival with " + packageSize + " @ ");
                    break;
                case DEPARTURE:
                    System.out.print("Depart" + " @ ");
                    break;
                case OBSERVER:
                    System.out.print("Observe" + " @ ");
                    break;
                default:
                    break;
            }
            System.out.print(e.getTimeStamp());
            System.out.println();
        }
    }



}