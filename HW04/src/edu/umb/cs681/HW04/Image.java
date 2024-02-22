package edu.umb.cs681.HW04;

import java.util.List;

public class Image {

    private int width, height;
    private List<List<Color>> pixels;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Image(int width, int height, List<List<Color>> pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<List<Color>> getPixels() {
        return pixels;
    }

    public void setPixels(List<List<Color>> pixels) {
        this.pixels = pixels;
    }
}
