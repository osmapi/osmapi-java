package pl.execon.osmapi.dto;

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
	
	
}
