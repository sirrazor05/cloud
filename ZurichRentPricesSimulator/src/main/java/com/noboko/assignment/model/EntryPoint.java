package com.noboko.assignment.model;

public class EntryPoint {
	private final double lat;
	private final double lng;
	private final double price;

	public EntryPoint(final double lat, final double lng, final double price) {
		this.lat = lat;
		this.lng = lng;
		this.price = price;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "[" + lat + ", " + lng + ", " + price + " ]";
	}

}
