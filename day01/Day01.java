package day01;

import java.io.File;
import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) {
        System.out.println("For 1969, the answer in part 1 is " + fuel(1969));
        System.out.println("For 1969, the answer in part 2 is " + fuelWithFuelForFuel(1969));

        int answerPart1 = 0;
        int answerPart2 = 0;

        File file = new File("./input.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                int mass = Integer.parseInt(scanner.nextLine());
                answerPart1 += fuel(mass);
                answerPart2 += fuelWithFuelForFuel(mass);
            }
        System.out.println("Answer for part 1 is " + answerPart1);
        System.out.println("Answer for part 2 is " + answerPart2);

        } catch(Exception e) {
            System.err.println("Error - could not create scanner");
        }
    }

    private static int fuel(int mass) {
        return mass / 3 - 2;
    }

    private static int fuelWithFuelForFuel(int mass) {
        int initialFuel = fuel(mass);
        if (initialFuel <= 0) {
            return 0;
        } else {
            return initialFuel + fuelWithFuelForFuel(initialFuel);
        }
    }
}
