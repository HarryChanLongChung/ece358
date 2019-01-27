package lab01;

public class observerEvent implements simulatedEvent{
    public eventType et = eventType.OBSERVER;
    public double timestamp;

    public observerEvent(double ts) {
        timestamp = ts;
    }
}