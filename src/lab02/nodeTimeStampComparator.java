package lab02;

import java.util.Comparator;

public class nodeTimeStampComparator implements Comparator<nodeTimeStamp> {
    @Override
    public int compare(nodeTimeStamp o1, nodeTimeStamp o2) {
        if (o1.getTimeStamp() < o2.getTimeStamp()) {
            return -1;
        }
        if (o1.getTimeStamp() > o2.getTimeStamp()) {
            return 1;
        }
        return 0;
    }
}