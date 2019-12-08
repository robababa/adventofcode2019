package day08;

import java.io.File;
import java.util.Scanner;

public class Day08 {
    public static void main(String[] args) {
        String input = "";
        File file = new File(args[0]);
        try {
            Scanner scanner = new Scanner(file);
            input = scanner.next();

        } catch (Exception ignored) {
        }
//        System.out.println("input is: " + input);
        Image image = new Image(input, 25, 6);

        int layerWithLeastZeros = image.layerWithLeastZeros();

        System.out.println("Part 1 answer: layer with least zeros, " +
                layerWithLeastZeros
                + ", product is " +
                image.onesAndTwosProductInLayer(layerWithLeastZeros));
    }
}
