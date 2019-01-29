package lab01;

import java.util.Comparator;

public class simulatedEventComparator implements Comparator<simulatedEvent> {
    @Override
    public int compare(simulatedEvent x, simulatedEvent y) {
        if (x.getTimeStamp() < y.getTimeStamp()) {
            return -1;
        }
        if (x.getTimeStamp() > y.getTimeStamp()) {
            return 1;
        }
        return 0;
    }
}