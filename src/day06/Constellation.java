package day06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constellation {
    Set<String> planets = new HashSet<>();
    Map<String, Orbit> orbits = new HashMap<>();

    public void addOrbit(String input) {
        String[] objects = input.split("\\)");
        String bigObject = objects[0];
        String smallObject = objects[1];
        planets.add(smallObject);
        orbits.put(smallObject, new Orbit(bigObject));
    }

    private boolean allFinished() {
        return orbits.values().stream().allMatch(Orbit::getFinished);
    }

    private void extendOrbits() {
        planets.forEach(p -> {
            String bigObject = orbits.get(p).getBigObject();
            if (!orbits.containsKey(bigObject)) {
                orbits.get(p).setFinished(true);
            } else if (orbits.get(bigObject).getFinished()) {
                orbits.get(p).setDepth(orbits.get(bigObject).getDepth() + 1);
                orbits.get(p).setFinished(true);
            }
        });
    }

    public void computeOrbits() {
        while (!allFinished()) {
            extendOrbits();
        }
    }

    public int totalDirectAndIndirectOrbits() {
        return orbits.values().stream().map(Orbit::getDepth).mapToInt(i -> i).sum();
    }

    private Set<String> orbitChain(String planet) {
        Set<String> chain = new HashSet<>();
        int parentPlanetCount = orbits.get(planet).getDepth();
        String currentPlanet = new String(planet);
        for (int i = 0; i < parentPlanetCount; i++) {
            String nextPlanet = orbits.get(currentPlanet).getBigObject();
            chain.add(nextPlanet);
            currentPlanet = nextPlanet;
        }
        return chain;
    }

    public int transferCount(String planetA, String planetB) {
        // the number of transfers equals the sum of their depths, minus twice their common ancestors
        Set<String> commonAncestors = orbitChain(planetA);
        commonAncestors.retainAll(orbitChain(planetB));
        return orbits.get(planetA).getDepth() + orbits.get(planetB).getDepth() - 2 * commonAncestors.size();
    }
}
