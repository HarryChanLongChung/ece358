package lab01;

public class arrivalEvent implements simulatedEvent{
    public eventType et = eventType.ARRIVAL;
    public double timestamp;
    public int packetSize;

    public arrivalEvent(double ts, int ps) {
        timestamp = ts;
        packetSize = ps;
    }

    public eventType getEventType() {return et;}
    public double getTimeStamp() {return timestamp;}
}