package day05;

import java.io.File;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) {

        OpCodeProcessor opCodeProcessor = new OpCodeProcessor(1);

        File file = new File(args[0]);
        try {
            Scanner scanner = new Scanner(file).useDelimiter(",|$");
            while (scanner.hasNext()) {
                opCodeProcessor.addElement(Integer.parseInt(scanner.next()));
            }
            // read the very last one
            opCodeProcessor.addElement(Integer.parseInt(scanner.next()));
        } catch(Exception ignored) {
        }

        try {
            System.out.println("Part 1 answer:");
            computeAnswer(opCodeProcessor);
        } catch(Exception ignored) {
        }
    }

    private static void computeAnswer(OpCodeProcessor processor) throws Exception {
        while (!processor.finished()) {
            processor.runCommand();
        }
    }
}
