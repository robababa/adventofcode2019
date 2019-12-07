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

    public void printOrbits() {
        orbits.forEach((k, v) -> System.out.println(k + " orbits " + v.getBigObject() + " with depth " + v.getDepth() ));
    }

    public Set<String> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<String> planets) {
        this.planets = planets;
    }

    public Map<String, Orbit> getOrbits() {
        return orbits;
    }

    public void setOrbits(Map<String, Orbit> orbits) {
        this.orbits = orbits;
    }
}
