package shared;

public class Point implements Comparable {
    private int x;
    private int y;

    public Point(int x, int y) {
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

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Point)) {
            return 0;
        }
        Point otherPoint = (Point) o;
        if (x < otherPoint.x) {
            return -1;
        } else if (x == otherPoint.x && y < otherPoint.y) {
            return -1;
        } else if (x > otherPoint.x) {
            return 1;
        } else if (x == otherPoint.x && y > otherPoint.y) {
            return 1;
        } else {
            return 0;
        }
    }

    private int gcd(int a, int b) {
        int larger = Math.max(Math.abs(a), Math.abs(b));
        int smaller = Math.min(Math.abs(a), Math.abs(b));
        int remainder = larger % smaller;
        if (remainder == 0) {
            return smaller;
        } else {
            return gcd(smaller, remainder);
        }

    }

    public Point slopeStep(Point otherPoint) {
        if (this.equals(otherPoint)) {
            return new Point(0, 0);
        }
        int run = otherPoint.x - this.x;
        int rise = otherPoint.y - this.y;
        if (run == 0) {
            return new Point(0, rise > 0 ? 1 : -1);
        } else if (rise == 0) {
            return new Point(run > 0 ? 1 : -1, 0);
        }

        int gcdValue = gcd(run, rise);

        return new Point(run / gcdValue, rise / gcdValue);
    }
}
