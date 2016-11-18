package pl.execon.osmapi.dto;

import java.util.Arrays;

public class FeatureCollection {
	private String type;
	Feature[] features;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Feature[] getFeatures() {
		return features;
	}
	public void setFeatures(Feature[] features) {
		this.features = features;
	}
	@Override
	public String toString() {
		return "FeatureCollection ["
				+ (type != null ? "type=" + type + ", " : "")
				+ (features != null ? "features=" + Arrays.toString(features)
						: "") + "]";
	}
	
	
	
}
