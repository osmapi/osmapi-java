package pl.execon.osmapi.dto.osm;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OSMWay {
	@JacksonXmlProperty(isAttribute=true)
	private long id;
	@JacksonXmlProperty(isAttribute=true)
	private long changeset;
	@JacksonXmlProperty(isAttribute=true)
	private boolean visible;
	@JacksonXmlProperty(isAttribute=true)
	private String user;
	@JacksonXmlProperty(isAttribute=true)
	private String timestamp;
	@JacksonXmlProperty(isAttribute=true)
	private long version;
	
	@JacksonXmlProperty(localName="tag")
	@JacksonXmlElementWrapper(useWrapping=false)
	List<OSMTag> tags;
	
	@JacksonXmlProperty(localName="nd")
	@JacksonXmlElementWrapper(useWrapping=false)
	List<OSMNodeReference> nodeReferences;

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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public List<OSMTag> getTags() {
		return tags;
	}

	public void setTags(List<OSMTag> tags) {
		this.tags = tags;
	}

	public List<OSMNodeReference> getNodeReferences() {
		return nodeReferences;
	}

	public void setNodeReferences(List<OSMNodeReference> nodeReferences) {
		this.nodeReferences = nodeReferences;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	
}
