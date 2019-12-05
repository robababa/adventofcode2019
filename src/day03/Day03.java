package day03;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day03 {
    public static void main(String[] args) {
        String line1 = "";
        String line2 = "";
        File file = new File(args[0]);
        try {
            Scanner scanner = new Scanner(file);
            line1 = scanner.nextLine();
            line2 = scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Error - could not create scanner");
        }

        Trail player1Trail = new Trail();
        Trail player2Trail = new Trail();

        Arrays.stream(line1.split(",")).map(Motion::new).forEach(motion -> motion.move(player1Trail));
        Arrays.stream(line2.split(",")).map(Motion::new).forEach(motion -> motion.move(player2Trail));

        System.out.println("Trail 1: " + player1Trail);
        System.out.println("Trail 2: " + player2Trail);

        Set<Point> player1Points = new HashSet<>(player1Trail.getPoints());
        Set<Point> player2Points = new HashSet<>(player2Trail.getPoints());

        Set<Point> commonPoints = player1Points
                .stream()
                .filter(p1 -> player2Points.stream().anyMatch(p1::equals))
                .collect(Collectors.toSet());

        System.out.println("Part 1 answer: " +
            commonPoints.stream().map(Point::distance).filter(d -> d > 0).sorted().findFirst().get()
        );
    }
}
