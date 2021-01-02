package com.noboko.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.core.io.Resource;

import com.noboko.model.EntryPoint;

public class CsvPointsLoaderService implements PointsLoadedService {

	private final Resource resourceFile;

	// load points in memory
	// threadSafe as only read
	private final List<EntryPoint> pointsList = new ArrayList<>();

	public CsvPointsLoaderService(final Resource resourceFile) {
		this.resourceFile = resourceFile;
	}

	@PostConstruct
	public void init() throws FileNotFoundException, IOException {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceFile.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String[] latLng = values[0].substring(values[0].indexOf("(") + 1, values[0].indexOf(")")).split(" ");
				double lat = Double.valueOf(latLng[0]);
				double lng = Double.valueOf(latLng[1]);
				double price = Double.valueOf(values[1]);
				pointsList.add(new EntryPoint(lat, lng, price));
			}
		}
	}

	@Override
	public List<EntryPoint> getPointsList() {
		return pointsList;
	}

}
