package pl.execon.osmapi.dto;

public class RoutingAPIHintData {
	private String[] locations;
	private long checksum;
	public String[] getLocations() {
		return locations;
	}
	public void setLocations(String[] locations) {
		this.locations = locations;
	}
	public long getChecksum() {
		return checksum;
	}
	public void setChecksum(long checksum) {
		this.checksum = checksum;
	}
	
	
}
