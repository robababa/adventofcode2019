package day08;

public class Pixel implements Comparable {
    private int vertical;
    private int horizontal;
    private int layer;
    private int value;

    public Pixel(int layer, int horizontal, int vertical, int value) {
        this.layer = layer;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.value = value;
    }

    @Override
    public String toString() {
        return "layer = " + layer + ", horizontal = " + horizontal + ", vertical = " + vertical + ", value = " + value;
    }

    public int position() {
        return 100 * vertical + horizontal;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Pixel)) {
            return 0;
        }
        Pixel p = (Pixel) o;

        if (layer < p.layer) {
            return -1;
        } else if (layer > p.layer) {
            return 1;
        } else if (vertical < p.vertical) {
            return -1;
        } else if (vertical > p.vertical) {
            return 1;
        } else if (horizontal < p.horizontal) {
            return -1;
        } else if (horizontal > p.horizontal) {
            return 1;
        } else if (value < p.value) {
            return -1;
        } else if (value > p.value) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getVertical() {
        return vertical;
    }

    public int getLayer() {
        return layer;
    }

    public int getValue() {
        return value;
    }
}
