package tests;

import lab01.*;

public class testsForEvent {
    public static void main(String[] args) {
        observerEvent e1 = new observerEvent(0.1111);
        arrivalEvent e2 = new arrivalEvent(0.123);
        departureEvent e3 = new departureEvent(0.23232);

        assert(e1.timestamp == 0.1111);
        assert(e1.et == eventType.OBSERVER);

        assert(e2.timestamp == 0.123);
        assert(e2.et == eventType.ARRIVAL);

        assert(e3.timestamp == 0.23232);
        assert(e3.et == eventType.DEPARTURE);

        System.out.print("Passing all tests casess");
    }
}