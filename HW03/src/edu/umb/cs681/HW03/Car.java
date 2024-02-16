package edu.umb.cs681.HW03;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Car {

    private String make, model;
    private int mileage;
    private int year;
    private float price;

    public Car(String make, String model, int mileage, int year, float price) {
        super();
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public int getMileage() {
        return this.mileage;
    }

    public int getYear() {
        return this.year;
    }

    public float getPrice() {
        return this.price;
    }

    public static List<Double> normalize(int mileage, int year, float price,int minMileage, int maxMileage, int minYear, int maxYear, float minPrice, float maxPrice) {
        double normalizedMileage = ((double) (mileage - minMileage)) / ( maxMileage - minMileage);
        double normalizedYear = ((double) (year - minYear)) / (maxYear - minYear);
        double normalizedPrice = ((double) (price - minPrice)) / (maxPrice - minPrice);

        List<Double> normalizedValues = new ArrayList<>();
        normalizedValues.add(normalizedMileage);
        normalizedValues.add(normalizedYear);
        normalizedValues.add(normalizedPrice);

        return normalizedValues;
    }
}
