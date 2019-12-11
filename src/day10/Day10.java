package day10;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) {
        List<String> inputLines = new ArrayList<>();
        File input = new File(args[0]);
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                inputLines.add(scanner.nextLine());
            }
            scanner.nextLine();
        } catch(Exception ignored) {
        }

        AsteroidBelt asteroidBelt = new AsteroidBelt(inputLines);

        System.out.println(String.format("There are %d asteroids", asteroidBelt.size()));
        System.out.println(String.format(
                "The greatest number of visibilities is %d", asteroidBelt.greatestVisibilities()
        ));

    }
}
