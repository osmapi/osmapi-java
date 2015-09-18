package pl.execon.osmapi.dto.osm;

import org.simpleframework.xml.Attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OSMNode {
	
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private long id;
	
	@JsonIgnore
	@Attribute(required=false)
	private long uid;
	
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private long changeset;
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private String timestamp;
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private long version;
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private boolean visible;
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private String user;
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private double lon;
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private double lat;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
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
