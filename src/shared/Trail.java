package shared;

import shared.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Trail {
    private List<Point> points = new ArrayList<>();

    public List<Point> getPoints() {
        return points;
    }

    public Map<Point, Integer> getSteps() {
        HashMap<Point, Integer> returnValue = new HashMap<>();
        int i = 0;
        for (Point p : points) {
            returnValue.putIfAbsent(p, i);
            i += 1;
        }
        return returnValue;
    }

    public Trail() {
        // trails start at the origin
        points.add(new Point(0, 0));
    }

    private Point getLastPoint() {
        return points.get(points.size() -1);
    }

    public void moveLeft() {
        Point lastPoint = getLastPoint();
        points.add(new Point(lastPoint.getX() - 1, lastPoint.getY()));

    }

    public void moveRight() {
        Point lastPoint = getLastPoint();
        points.add(new Point(lastPoint.getX() + 1, lastPoint.getY()));

    }

    public void moveUp() {
        Point lastPoint = getLastPoint();
        points.add(new Point(lastPoint.getX(), lastPoint.getY() + 1));

    }

    public void moveDown() {
        Point lastPoint = getLastPoint();
        points.add(new Point(lastPoint.getX(), lastPoint.getY() - 1));

    }

    public String toString() {
        return points.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
