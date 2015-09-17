package pl.execon.osmapi.dto.osm;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OSMNode {
	
	@JacksonXmlProperty(isAttribute=true)
	private long id;
	@JacksonXmlProperty(isAttribute=true)
	private long changeset;
	@JacksonXmlProperty(isAttribute=true)
	private String timestamp;
	@JacksonXmlProperty(isAttribute=true)
	private long version;
	@JacksonXmlProperty(isAttribute=true)
	private boolean visible;
	@JacksonXmlProperty(isAttribute=true)
	private String user;
	@JacksonXmlProperty(isAttribute=true)
	private double lon;
	@JacksonXmlProperty(isAttribute=true)
	private double lat;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getChangeset() {
		return changeset;
	}
	public void setChangeset(long changeset) {
		this.changeset = changeset;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	
}
