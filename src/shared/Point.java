package shared;

public class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point otherPoint = (Point) o;
        return otherPoint.x == x && otherPoint.y == y;
    }

    @Override
    public int hashCode() {
        return x * 10000 + y;
    }

    public int distance() { return Math.abs(x) + Math.abs(y); }
}
