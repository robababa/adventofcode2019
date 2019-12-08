package day08;

public class Pixel {
    private int vertical = 0;
    private int horizontal = 0;
    private int layer = 0;
    private int value = 0;

    public Pixel(int layer, int horizontal, int vertical, int value) {
        this.layer = layer;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.value = value;
    }

    public boolean equals(Pixel pixel) {
        return this.vertical == pixel.vertical &&
                this.horizontal == pixel.horizontal &&
                this.layer == pixel.layer &&
                this.value == pixel.value;
    }

    public int hashCode() {
        return 10000 * vertical + 100 * horizontal + layer;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
