package day02;

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) {

        OpCodeProcessor opCodeProcessor = new OpCodeProcessor();

        File file = new File("day02/input.txt");
        try {
            Scanner scanner = new Scanner(file).useDelimiter(",|$");
            while (scanner.hasNext()) {
                opCodeProcessor.addElement(Integer.parseInt(scanner.next()));
            }
            // read the very last one
            opCodeProcessor.addElement(Integer.parseInt(scanner.next()));
        } catch(Exception e) {
            System.err.println("Error - could not read input");
        }

        opCodeProcessor.replaceAtPositionWithValue(1, 12);
        opCodeProcessor.replaceAtPositionWithValue(2, 2);

        while (!opCodeProcessor.finished()) {
            opCodeProcessor.runCommand();
        }

        System.out.println("Part 1 answer is: " + opCodeProcessor.firstElement());
    }
}
