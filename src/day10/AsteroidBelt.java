package day10;

import shared.Point;

import java.util.*;

public class AsteroidBelt {
    private List<Point> asteroids = new ArrayList<>();

    private Map<Point, Set<Point>> visibilities = new HashMap<>();

    AsteroidBelt(List<String> spaceMap) {
        for (int j = 0; j < spaceMap.size(); j++) {
            processRow(j, spaceMap.get(j));
        }
        // now add an empty map for each asteroid's visibilities
        asteroids.forEach(asteroid -> visibilities.put(asteroid, new HashSet<>()));
        buildVisibilities();
    }

    private void processRow(int j, String row) {
        for (int i = 0; i < row.length(); i++) {
            if (row.substring(i, i + 1).equals("#")) {
                asteroids.add(new Point(i, j));
            }
        }
    }

    private void buildVisibilities() {
        for (int a1 = 0; a1 < asteroids.size(); a1++) {
            Set<Point> slopeSteps = new HashSet<>();
            for (int a2 = a1 + 1; a2 < asteroids.size(); a2++) {
                Point asteroid1 = asteroids.get(a1);
                Point asteroid2 = asteroids.get(a2);
                Point thisSlopeStep = asteroid1.slopeStep(asteroid2);
                if (!slopeSteps.contains(thisSlopeStep)) {
                    // visibility is bi-directional, but we only check it once, from a1 to a2, not a2 to a1
                    visibilities.get(asteroid1).add(asteroid2);
                    visibilities.get(asteroid2).add(asteroid1);
                    slopeSteps.add(thisSlopeStep);
                }
            }
        }
    }

    public int greatestVisibilities() {
        return visibilities.values()
                .stream()
                .map(Set::size)
                .map(Integer::intValue)
                .sorted(Collections.reverseOrder())
                .findFirst()
                .get();
    }

    public int size() {
        return asteroids.size();
    }
}
