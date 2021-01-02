package com.noboko.assignment.controllers;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noboko.assignment.services.PricePredictionService;

@Controller
public class IndexController {

	@Resource
	private PricePredictionService predictionService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/json/predict")
	public void jsonPredict(@RequestParam Double lat, @RequestParam Double lon, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		double price = predictionService.predict(lat, lon);
		response.setContentType("text/plain");
		response.getWriter().write(String.valueOf(price));
	}

}
