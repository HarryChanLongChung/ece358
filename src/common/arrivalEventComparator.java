package common;

import java.util.Comparator;

public class arrivalEventComparator implements Comparator<arrivalEvent> {
    @Override
    public int compare(arrivalEvent x, arrivalEvent y) {
        if (x.getTimeStamp() < y.getTimeStamp()) {
            return -1;
        }
        if (x.getTimeStamp() > y.getTimeStamp()) {
            return 1;
        }
        return 0;
    }
}