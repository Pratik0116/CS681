package edu.umb.cs681.HW05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static void processCSVFile(String filePath) {
        List<List<String>> matrix = readCSVFile(filePath);
        if (matrix != null) {
            LocalDate date = LocalDate.of(2021, 1, 1);
            double dailyMeanTemperature = calculateDailyMeanTemperature(matrix, date);
            System.out.println("Daily Mean Temperature for " + date + ": " + dailyMeanTemperature);
            double dailyMedianTemperature = calculateDailyMedianTemperature(matrix, date);
            System.out.println("Daily Median Temperature for " + date + ": " + dailyMedianTemperature);
            double standardDeviation = calculateDailyStandardDeviationTemperature(matrix, date);
            System.out.println("Standard Deviation for " + date + ": " + standardDeviation);

            Map<String, Double> maxTempsByMonth = maxTemperatureByMonth(matrix);
            System.out.println("Maximum Temperatures by Month:");
            maxTempsByMonth.forEach((month, maxTemp) ->
                    System.out.println(month + ": " + (maxTemp.isNaN() ? "No data" : maxTemp + "°C")));

            Map<String, Double> minTempsByMonth = minTemperatureByMonth(matrix);
            System.out.println("\nMinimum Temperatures by Month:");
            minTempsByMonth.forEach((month, minTemp) ->
                    System.out.println(month + ": " + (minTemp.isNaN() ? "No data" : minTemp + "°C")));

            Map<String, Double> maxTempAllMonths = maxTemperatureAllMonths(matrix);
            System.out.println("\nMaximum Temperature for all 3 months combined: " +
                    (maxTempAllMonths.get("MaxTempAllMonths").isNaN() ?
                            "No data" : maxTempAllMonths.get("MaxTempAllMonths") + "°C"));

            Map<String, Double> minTempAllMonths = minTemperatureAllMonths(matrix);
            System.out.println("Minimum Temperature for all 3 months combined: " +
                    (minTempAllMonths.get("MinTempAllMonths").isNaN() ?
                            "No data" : minTempAllMonths.get("MinTempAllMonths") + "°C"));

        } else {
            System.out.println("Failed to read data from CSV file.");
        }
    }

    private static List<List<String>> readCSVFile(String filePath) {
        Path path = Paths.get(filePath);
        List<List<String>> data = null;

        try (Stream<String> lines = Files.lines(path)) {
            data = lines.skip(1) // Skip the first line (header)
                    .map(line -> Arrays.stream(line.split(","))
                            .map(value -> value.replaceAll("\"", "").trim())
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return data;
    }

    private static double calculateDailyMeanTemperature(List<List<String>> data, LocalDate date) {
        double sum = 0.0;
        int count = 0;
        for (List<String> row : data) {
            LocalDate rowDate = LocalDate.of(Integer.parseInt(row.get(2)), // YEAR
                    Integer.parseInt(row.get(3)), // MONTH
                    Integer.parseInt(row.get(4))); // DATE
            if (rowDate.equals(date)) {
                double temperature = Double.parseDouble(row.get(5)); // Temperature at 2 Meters
                sum += temperature;
                count++;
            }
        }
        return count > 0 ? sum / count : 0.0;
    }

    private static double calculateDailyMedianTemperature(List<List<String>> data, LocalDate date) {
        List<Double> temperatures = data.stream()
                .filter(row -> {
                    LocalDate rowDate = LocalDate.of(Integer.parseInt(row.get(2)), // YEAR
                            Integer.parseInt(row.get(3)), // MONTH
                            Integer.parseInt(row.get(4))); // DATE
                    return rowDate.equals(date);
                })
                .mapToDouble(row -> Double.parseDouble(row.get(5))) // Temperature at 2 Meters
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        int size = temperatures.size();
        if (size == 0) {
            return 0.0; // No data for the given date
        }
        if (size % 2 == 0) {
            return (temperatures.get(size / 2 - 1) + temperatures.get(size / 2)) / 2.0;
        } else {
            return temperatures.get(size / 2);
        }
    }

    private static double calculateDailyStandardDeviationTemperature(List<List<String>> data, LocalDate date) {
        double mean = calculateDailyMeanTemperature(data, date);
        List<Double> deviations = data.stream()
                .filter(row -> {
                    LocalDate rowDate = LocalDate.of(Integer.parseInt(row.get(2)), // YEAR
                            Integer.parseInt(row.get(3)), // MONTH
                            Integer.parseInt(row.get(4))); // DATE
                    return rowDate.equals(date);
                })
                .mapToDouble(row -> {
                    double temperature = Double.parseDouble(row.get(5)); // Temperature at 2 Meters
                    return Math.pow(temperature - mean, 2);
                })
                .boxed()
                .collect(Collectors.toList());

        int count = deviations.size();
        if (count == 0) {
            return 0.0; // No data for the given date
        }
        double sum = deviations.stream().reduce(0.0, Double::sum);
        return Math.sqrt(sum / count);
    }

    private static Map<String, Double> maxTemperatureByMonth(List<List<String>> data) {
        Map<String, Double> maxTempsByMonth = new HashMap<>();

        Map<String, Double> maxTempByMonth = data.stream()
                .collect(Collectors.groupingBy(row -> row.get(3), // MONTH
                        Collectors.mapping(row -> Double.parseDouble(row.get(5)), // Temperature at 2 Meters
                                Collectors.collectingAndThen(Collectors.toList(),
                                        values -> values.stream()
                                                .mapToDouble(Double::doubleValue)
                                                .max()
                                                .orElse(Double.NaN)))));
        maxTempsByMonth.putAll(maxTempByMonth);

        return maxTempsByMonth;
    }

    private static Map<String, Double> minTemperatureByMonth(List<List<String>> data) {
        Map<String, Double> minTempsByMonth = new HashMap<>();

        Map<String, Double> minTempByMonth = data.stream()
                .collect(Collectors.groupingBy(row -> row.get(3), // MONTH
                        Collectors.mapping(row -> Double.parseDouble(row.get(5)), // Temperature at 2 Meters
                                Collectors.collectingAndThen(Collectors.toList(),
                                        values -> values.stream()
                                                .mapToDouble(Double::doubleValue)
                                                .min()
                                                .orElse(Double.NaN)))));
        minTempsByMonth.putAll(minTempByMonth);

        return minTempsByMonth;
    }

    private static Map<String, Double> maxTemperatureAllMonths(List<List<String>> data) {
        Map<String, Double> allMonthsData = new HashMap<>();

        List<Double> allTemperatures = data.stream()
                .mapToDouble(row -> Double.parseDouble(row.get(5))) // Temperature at 2 Meters
                .boxed()
                .collect(Collectors.toList());
        double maxTempAllMonths = allTemperatures.stream()
                .max(Double::compareTo)
                .orElse(Double.NaN);
        allMonthsData.put("MaxTempAllMonths", maxTempAllMonths);

        return allMonthsData;
    }

    private static Map<String, Double> minTemperatureAllMonths(List<List<String>> data) {
        Map<String, Double> allMonthsData = new HashMap<>();

        List<Double> allTemperatures = data.stream()
                .mapToDouble(row -> Double.parseDouble(row.get(5))) // Temperature at 2 Meters
                .boxed()
                .collect(Collectors.toList());
        double minTempAllMonths = allTemperatures.stream()
                .min(Double::compareTo)
                .orElse(Double.NaN);
        allMonthsData.put("MinTempAllMonths", minTempAllMonths);

        return allMonthsData;
    }


    public static void main(String[] args) {
        processCSVFile("/Users/pratikdhameliya/Documents/4th Sem/CS681/Homeworks/HW05/src/edu/umb/cs681/HW05/Data.csv");
    }

}
