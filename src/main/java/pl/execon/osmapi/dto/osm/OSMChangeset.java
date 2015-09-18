package pl.execon.osmapi.dto.osm;

import java.util.List;

import org.simpleframework.xml.ElementList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


public class OSMChangeset {
	@JsonIgnore
	private Long id;
	
	@JacksonXmlProperty(localName="tag")
	@JacksonXmlElementWrapper(useWrapping=false)
	@ElementList(inline=true, entry="nd", required=false)
	List<OSMTag> properties;

	public List<OSMTag> getProperties() {
		return properties;
	}

	public void setProperties(List<OSMTag> properties) {
		this.properties = properties;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
}
