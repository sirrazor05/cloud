package com.noboko.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.noboko.services.CsvPointsLoaderService;
import com.noboko.services.PointsLoadedService;
import com.noboko.services.PricePredictionService;
import com.noboko.services.SimplePricePredictionService;

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
