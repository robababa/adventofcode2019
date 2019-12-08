package day08;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Image {
    List<Pixel> pixels = new ArrayList<>();

    public Image(String input, int length, int height) {
        int inputSize = input.length();
        int position = 0;
        for (int layer = 0; layer < inputSize / length / height; layer++) {
            for (int horizontalPixel = 0; horizontalPixel < length; horizontalPixel++) {
                for (int verticalPixel = 0; verticalPixel < height; verticalPixel++ ) {
                    int value = Character.getNumericValue(input.charAt(position));
                    this.pixels.add(new Pixel(layer, horizontalPixel, verticalPixel, value));
                    position++;
                }
            }
        }
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
}
