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

        Image image = new Image(input, Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        int layerWithLeastZeros = image.layerWithLeastZeros();

        System.out.println("Part 1 answer: layer with least zeros, " +
                layerWithLeastZeros
                + ", product is " +
                image.onesAndTwosProductInLayer(layerWithLeastZeros));

        Image finalImage = image.resultingImage();
        System.out.println("Part 2 answer:");
        finalImage.printImage();
    }
}
