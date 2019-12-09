package day05;

import intCodeComputer.IntCodeComputer;

import java.io.File;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) {

        IntCodeComputer intCodeComputer = new IntCodeComputer(1);

        File file = new File(args[0]);
        try {
            Scanner scanner = new Scanner(file).useDelimiter(",|$");
            while (scanner.hasNext()) {
                intCodeComputer.addElement(Integer.parseInt(scanner.next()));
            }
            // read the very last one
            intCodeComputer.addElement(Integer.parseInt(scanner.next()));
        } catch(Exception ignored) {
        }

        try {
            System.out.println("Part 1 answer:");
            computeAnswer(intCodeComputer);
        } catch(Exception ignored) {
        }
    }

    private static void computeAnswer(IntCodeComputer computer) throws Exception {
        while (!computer.finished()) {
            computer.runCommand();
        }
    }
}
