package day08;

import java.util.*;
import java.util.stream.Collectors;

public class Image {
    List<Pixel> pixels = new ArrayList<>();

    public Image(String input, int length, int height) {
        int inputSize = input.length();
        int position = 0;
        for (int layer = 0; layer < inputSize / length / height; layer++) {
            for (int verticalPixel = 0; verticalPixel < height; verticalPixel++ ) {
                for (int horizontalPixel = 0; horizontalPixel < length; horizontalPixel++) {
                    int value = Character.getNumericValue(input.charAt(position));
                    this.pixels.add(new Pixel(layer, horizontalPixel, verticalPixel, value));
                    position++;
                }
            }
        }
    }

    public Image(Collection<Optional<Pixel>> values) {
        for (Optional<Pixel> value : values) {
            pixels.add(value.get());
        }
        pixels.sort(Comparator.comparing(Pixel::position));
    }

    int layerWithLeastZeros() {
        Map<Integer, Long> zeroCounts = pixels.stream()
                .filter(pixel -> pixel.getValue() == 0)
                .collect(Collectors.groupingBy(Pixel::getLayer, Collectors.counting()));

        return zeroCounts.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
    }

    long onesAndTwosProductInLayer(int layer) {
        Map<Integer, Long> nonzeroCounts = pixels.stream()
                .filter(pixel -> pixel.getLayer() == layer)
                .collect(Collectors.groupingBy(Pixel::getValue, Collectors.counting()));

        return nonzeroCounts.get(1) * nonzeroCounts.get(2);
    }

    public Image resultingImage() {
        return new Image(pixels.stream()
                .filter(pixel -> pixel.getValue() != 2)
                .sorted()
                .collect(Collectors.groupingBy(Pixel::position, Collectors.minBy(Comparator.comparing(Pixel::getLayer))))
                .values());
    }

    public void printImage() {
        System.out.println("Size of pixels array: " + pixels.size());
        int currentVertical = pixels.get(0).getVertical();
        for (Pixel pixel : pixels) {
            if (pixel.getVertical() != currentVertical) {
                System.out.println();
            }
            System.out.print(pixel.getValue());
            currentVertical = pixel.getVertical();
        }
        System.out.println();
    }
}
