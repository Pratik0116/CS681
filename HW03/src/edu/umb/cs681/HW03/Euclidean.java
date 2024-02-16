package edu.umb.cs681.HW03;

import java.util.List;
import java.util.stream.IntStream;

public class Euclidean implements DistanceMetric{
	public double distance(List<Double> p1, List<Double> p2) {

		if (p1.size() != p2.size()) {
			throw new IllegalArgumentException("Dimensions of the input points do not match.");
		}
		double sumOfSquared =
				IntStream
						.range(0, p1.size())
						.mapToDouble(i -> p1.get(i) - p2.get(i))
						.map(n -> n * n)
						.sum();

		return Math.sqrt(sumOfSquared);		
	}
}
