package lab01;

public class departureEvent implements simulatedEvent{
    public eventType et = eventType.DEPARTURE;
    public double timestamp;

    public departureEvent(double ts) {
        timestamp = ts;
    }

    public eventType getEventType() {return et;}
    public double getTimeStamp() {return timestamp;}
}