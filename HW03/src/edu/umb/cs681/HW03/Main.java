package edu.umb.cs681.HW03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static List<List<Double>> generateLargeDataset(int numPoints, int numDimensions) {
        List<List<Double>> dataset = new ArrayList<>();

        for (int i = 0; i < numPoints; i++) {
            List<Double> point = generatePoint(numDimensions);
            dataset.add(point);
        }

        return dataset;
    }

    private static List<Double> generatePoint(int numDimensions) {
        return IntStream.range(0, numDimensions)
                .mapToDouble(i -> Math.random()) // Generate random values for each dimension
                .boxed()
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {

        List<List<Double>> points = generateLargeDataset(1000, 100);

        List<List<Double>> distanceMatrix = Distance.matrix(points);

        int numRows = distanceMatrix.size();
        int numCols = (numRows > 0) ? distanceMatrix.get(0).size() : 0;
        System.out.println("Distance matrix size: " + numRows + "x" + numCols);

        if (numRows > 0 && numCols > 0) {
            System.out.println("Example distances:");
            System.out.println("Distance between point 0 and point 1: " + distanceMatrix.get(0).get(1));
            System.out.println("Distance between point 1 and point 2: " + distanceMatrix.get(1).get(2));
            System.out.println("Distance between point 0 and point 2: " + distanceMatrix.get(0).get(2));
        }

        double minDistance = distanceMatrix.stream()
                .flatMapToDouble(row -> row.stream().mapToDouble(Double::doubleValue))
                .min().orElse(Double.NaN);
        double maxDistance = distanceMatrix.stream()
                .flatMapToDouble(row -> row.stream().mapToDouble(Double::doubleValue))
                .max().orElse(Double.NaN);
        System.out.println("Minimum distance: " + minDistance);
        System.out.println("Maximum distance: " + maxDistance);

        double totalDistance = distanceMatrix.stream()
                .flatMapToDouble(row -> row.stream().mapToDouble(Double::doubleValue))
                .sum();
        double averageDistance = totalDistance / (numRows * numCols);
        System.out.println("Average distance: " + averageDistance);

    }
}