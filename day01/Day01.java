package day01;

import java.io.File;
import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) {
        System.out.println("For 1969, the answer is " + fuel(1969));

        int answer = 0;

        File file = new File("./input.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                answer += fuel(Integer.parseInt(scanner.nextLine()));
            }
        System.out.println("Answer for part 1 is " + answer);

        } catch(Exception e) {
            System.err.println("Error - could not create scanner");
        }
    }

    static int fuel(int mass) {
        return mass / 3 - 2;
    }
}
