package pl.execon.osmapi.dto;

import java.util.Arrays;

public class Geometry {
	private String type;
	private double[][] coordinates;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double[][] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(double[][] coordinates) {
		this.coordinates = coordinates;
	}
	@Override
	public String toString() {
		return "Geometry ["
				+ (type != null ? "type=" + type + ", " : "")
				+ (coordinates != null ? "coordinates="
						+ Arrays.toString(coordinates) : "") + "]";
	}
	
	
}
