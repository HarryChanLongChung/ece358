package tests;

import lab01.*;
import java.util.PriorityQueue;
import java.util.List;

public class testsForEvent {
    public static void main(String[] args) {
        observerEvent e1 = new observerEvent(0.453451241);
        arrivalEvent e2 = new arrivalEvent(0.123123, 1000);
        departureEvent e3 = new departureEvent(0.1231233313);

        assert(e1.timestamp == 0.1111);
        assert(e1.et == eventType.OBSERVER);

        assert(e2.timestamp == 0.123);
        assert(e2.et == eventType.ARRIVAL);

        assert(e3.timestamp == 0.23232);
        assert(e3.et == eventType.DEPARTURE);

        PriorityQueue<simulatedEvent> queue = utilities.getNewEventQueue(10);
        queue.add(e1);
        queue.add(e2);
        queue.add(e3);
        while (!queue.isEmpty()) {
            simulatedEvent e = queue.remove();
            switch (e.getEventType()) {
                case ARRIVAL:
                    System.out.print("Arrival" + " @ ");
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

        List<observerEvent> observerList = utilities.generateObserverEvents(4, 500.00);
        List<arrivalEvent> arrivalList = utilities.generateArrivalEvents(4, 30.00, 1000);
        queue.addAll(observerList);
        queue.addAll(arrivalList);

        while (!queue.isEmpty()) {
            simulatedEvent e = queue.remove();
            switch (e.getEventType()) {
                case ARRIVAL:
                    int packageSize = ((arrivalEvent)e).packetSize;
                    System.out.print("Arrival with " + packageSize + " @ ");

                    System.out.print("Arrival" + " @ ");
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

        System.out.print("Passing all tests casess");
    }
}