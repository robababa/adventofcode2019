package day05;

import shared.IntCodeComputer;

import java.io.File;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) {

        IntCodeComputer part1Computer = new IntCodeComputer(1);


        File file = new File(args[0]);
        try {
            Scanner scanner = new Scanner(file).useDelimiter(",|$");
            while (scanner.hasNext()) {
                part1Computer.addElement(Integer.parseInt(scanner.next()));
            }
            // read the very last one
            part1Computer.addElement(Integer.parseInt(scanner.next()));
        } catch(Exception ignored) {
        }

        System.out.println("last argument is " + args[1]);
        IntCodeComputer part2Computer = new IntCodeComputer(part1Computer, Integer.parseInt(args[1]));

//        try {
//            System.out.println("Part 1 answer:");
//            computeAnswer(part1Computer);
//        } catch(Exception ignored) {
//        }

        try {
            System.out.println("Part 2 answer:");
            computeAnswer(part2Computer);
        } catch(Exception ignored) {
        }
    }

    private static void computeAnswer(IntCodeComputer computer) throws Exception {
        while (!computer.finished()) {
            computer.runCommand();
        }
    }
}
