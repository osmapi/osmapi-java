package pl.execon.osmapi.dto;

public class SearchAPIPlace {
	private String address;
	private String lon;
	private String lat;
	private SearchAPIPlaceType type;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public SearchAPIPlaceType getType() {
		return type;
	}
	public void setType(SearchAPIPlaceType type) {
		this.type = type;
	}
	
	
}
