package edu.umb.cs681.HW03;

import java.util.List;
import java.util.stream.IntStream;

public class Cosine implements DistanceMetric {
    public double distance(List<Double> p1, List<Double> p2) {
        if (p1.size() != p2.size()) {
            throw new IllegalArgumentException("Dimensions of the input points do not match.");
        }

        double dotProduct = IntStream.range(0, p1.size())
                .mapToDouble(i -> p1.get(i) * p2.get(i))
                .sum();

        double magnitudeP1 = p1.stream()
                .mapToDouble(n -> n * n)
                .sum();

        double magnitudeP2 = p2.stream()
                .mapToDouble(n -> n * n)
                .sum();

        if (magnitudeP1 == 0 || magnitudeP2 == 0) {
            throw new ArithmeticException("One or both vectors have zero magnitude.");
        }

        double cosineSimilarity = dotProduct / (Math.sqrt(magnitudeP1) * Math.sqrt(magnitudeP2));
        return 1 - cosineSimilarity;
    }
}