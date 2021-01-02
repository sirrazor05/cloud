package com.noboko.assignment.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.noboko.assignment.model.EntryPoint;
import com.noboko.assignment.utils.GeoUtils;

public class SimplePricePredictionService implements PricePredictionService {

	private final PointsLoadedService pointsLoaderService;

	public SimplePricePredictionService(final PointsLoadedService pointsLoaderService) {
		this.pointsLoaderService = pointsLoaderService;
	}

	@Override
	public double predict(double lat, double lon) {

		// find 4 closest points
		// compute AVG on these prices points

		final List<EntryPoint> closest4points = find4ClosestPoints(lat, lon);
		final EntryPoint p1 = closest4points.get(0);
		final EntryPoint p2 = closest4points.get(1);
		final EntryPoint p3 = closest4points.get(2);
		final EntryPoint p4 = closest4points.get(3);
		double price = (p1.getPrice() + p2.getPrice() + p3.getPrice() + p4.getPrice()) / 4;
		return price;
	}

	private List<EntryPoint> find4ClosestPoints(double lat, double lon) {
		final Map<EntryPoint, Double> distancesMap = new HashMap<>();
		pointsLoaderService.getPointsList()
				.forEach(point -> distancesMap.put(point, GeoUtils.distance(lat, lon, point.getLat(), point.getLng())));
		// LinkedHashMap to preserve order
		final Map<EntryPoint, Double> sorted = distancesMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
		return sorted.keySet().stream().limit(4).collect(Collectors.toList());
	}

}
