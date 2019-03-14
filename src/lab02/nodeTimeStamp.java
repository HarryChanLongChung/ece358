package lab02;

public class nodeTimeStamp {
    public double timestamp;
    public int nodePos;

    public nodeTimeStamp(double ts, int node) {
        timestamp = ts;
        nodePos = node;
    }

    public double getTimeStamp() {return timestamp;}
    public void updateTimeStamp(double time) {
        timestamp = time;
    }

    public int getNodePos() {return nodePos;}

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            nodeTimeStamp nts = (nodeTimeStamp) obj;
            return (nts.getTimeStamp() == timestamp && nts.nodePos == nodePos);
        }
        return false;
    }
}