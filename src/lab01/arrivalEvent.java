package lab01;

public class arrivalEvent implements simulatedEvent{
    public eventType et = eventType.ARRIVAL;
    public double timestamp;

    public arrivalEvent(double ts) {
        timestamp = ts;
    }
}