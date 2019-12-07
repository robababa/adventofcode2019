package day06;

public class Orbit {
    private String bigObject;
    private int depth = 1;
    private boolean finished = false;

    public Orbit(String center) {
        this.bigObject = center;
    }

    public String getBigObject() {
        return bigObject;
    }

    public void setBigObject(String bigObject) {
        this.bigObject = bigObject;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
