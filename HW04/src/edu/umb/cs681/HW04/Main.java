package edu.umb.cs681.HW04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    private static void addColors(Image image) {
        Random random = new Random();
        List<List<Color>> pixels = new ArrayList<>();
        for (int i = 0; i < image.getHeight(); i++) {
            List<Color> row = new ArrayList<>();
            for (int j = 0; j < image.getWidth(); j++) {
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                row.add(new Color(red, green, blue));
            }
            pixels.add(row);
        }
        image.setPixels(pixels);
    }

    private static void printImagePixels(Image img) {
        List<List<Color>> pixels = img.getPixels();
        for (List<Color> row : pixels) {
            for (Color pixel : row) {
                System.out.print("(" + pixel.getRedScale() + ", " +
                        pixel.getGreenScale() + ", " + pixel.getBlueScale() + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Image image = new Image(5,3);
        addColors(image);

        System.out.println("Original Image: ");
        printImagePixels(image);

        Image newImg = new Image(image.getWidth(), image.getHeight(),
                image.getPixels().stream()
                        .map(row -> row.stream()
                                .map(pixel -> {
                                    int r = pixel.getRedScale();
                                    int g = pixel.getGreenScale();
                                    int b = pixel.getBlueScale();
                                    int avg = (r + g + b) / 3;
                                    return new Color(avg, avg, avg);
                                })
                                .collect(Collectors.toList())
                        )
                        .collect(Collectors.toList())
        );

        System.out.println("\nNew Image after Grayscale Filter:");
        printImagePixels(newImg);
    }
}