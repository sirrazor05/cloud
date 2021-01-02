package com.noboko.assignment.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.noboko.assignment.services.CsvPointsLoaderService;
import com.noboko.assignment.services.PointsLoadedService;
import com.noboko.assignment.services.PricePredictionService;
import com.noboko.assignment.services.SimplePricePredictionService;

@Configuration
public class AppConfig {

	@Bean
	public PointsLoadedService pointsLoaderService(
			final @Value("classpath:homegate_rental_data_50.csv") Resource resource) {
		return new CsvPointsLoaderService(resource);
	}

	@Bean
	public PricePredictionService pricePredictionService(final PointsLoadedService pointsLoaderService) {
		return new SimplePricePredictionService(pointsLoaderService);
	}
}
