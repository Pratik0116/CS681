package edu.umb.cs681.HW03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Distance {
	public static double get(List<Double> p1, List<Double> p2) {
		return Distance.get(p1, p2, new Euclidean());
	}
	
	public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
		return metric.distance(p1, p2);
	}
	
	public static List<List<Double>> matrix(List<List<Double>> points) {
			return Distance.matrix(points, new Euclidean());
	}

	public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {
		return points.stream()
				.map(current -> points.stream()
						.map(peer -> Distance.get(current, peer, metric))
						.collect(Collectors.toList()))
				.collect(Collectors.toList());
	}
	
	private static List<List<Double>> initDistanceMatrix(int numOfPoints){
		List<List<Double>> distanceMatrix = new ArrayList<>(numOfPoints);
		for(int i=0; i < numOfPoints; i++) {
			Double[] vector = new Double[numOfPoints];
			Arrays.fill(vector, 0.0);
			distanceMatrix.add( Arrays.asList(vector) );
		}
		return distanceMatrix;
	}

}
