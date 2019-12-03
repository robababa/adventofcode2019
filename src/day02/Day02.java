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

        OpCodeProcessor newProcessor = new OpCodeProcessor(opCodeProcessor);

        newProcessor.replaceAtPositionWithValue(1, 12);
        newProcessor.replaceAtPositionWithValue(2, 2);

        System.out.println("Part 1 answer is: " + computeAnswer(newProcessor));
        findMatchingProcessor(opCodeProcessor);
    }

    private static int computeAnswer(OpCodeProcessor processor) {
        while (!processor.finished()) {
            processor.runCommand();
        }
        return processor.firstElement();
    }

    private static void findMatchingProcessor(OpCodeProcessor originalProcessor) {
        int desiredAnswer = 19690720;
        boolean foundAnswer = false;
        while (!foundAnswer) {
            for (int noun = 0; noun <= 99; noun++) {
                for (int verb = 0; verb <= 99; verb++) {
                    OpCodeProcessor newProcessor = new OpCodeProcessor(originalProcessor);
                    // OK, let's compute our answer.  Does it match the target?
                    // don't forget to replace with the noun and verb!
                    newProcessor.replaceAtPositionWithValue(1, noun);
                    newProcessor.replaceAtPositionWithValue(2, verb);
                    int answer = computeAnswer(newProcessor);
                    if (answer == desiredAnswer) {
                        // we found it!
                        foundAnswer = true;
                        System.out.println("Noun = " + noun + " Verb = " + verb + " Answer = " + answer + " Just right!");
                        System.out.println("Part 2 answer = 100 * Noun + Verb = " + (100 * noun + verb));
                    }
                }
            }
        }
    }
}
