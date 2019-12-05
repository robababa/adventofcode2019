package day03;

import java.io.File;
import java.util.*;
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

//        System.out.println("Trail 1: " + player1Trail);
//        System.out.println("Trail 2: " + player2Trail);

        Set<Point> commonPoints = new HashSet<>(player1Trail.getPoints());

        // destructive!
        commonPoints.retainAll(new HashSet<>(player2Trail.getPoints()));

        System.out.println("Part 1 answer: " +
            commonPoints.stream().map(Point::distance).filter(d -> d > 0).sorted().findFirst().get()
        );

        Map<Point, Integer> trail1Steps = player1Trail.getSteps();
        Map<Point, Integer> trail2Steps = player2Trail.getSteps();
        System.out.println("Part 2 answer: " +
            commonPoints
                .stream()
                .map(p -> trail1Steps.get(p) + trail2Steps.get(p))
                .filter(d -> d > 0)
                .sorted()
                .findFirst()
                .get()
        );
    }
}
