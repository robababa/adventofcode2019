package day06;

import java.io.File;
import java.util.Scanner;

public class Day06 {
    public static void main(String[] args) {
        Constellation constellation = new Constellation();
        readInput(args[0], constellation);
        constellation.computeOrbits();
        System.out.println("Part 1 total orbits: " + constellation.totalDirectAndIndirectOrbits());
    }

    private static void readInput(String filename, Constellation constellation) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                constellation.addOrbit(scanner.next());
            }
            constellation.addOrbit(scanner.next());
        } catch(Exception ignored) {
        }
    }
}
