package pl.execon.osmapi.dto.osm;

import java.util.List;

import org.simpleframework.xml.ElementList;



public class OSMChangeset {
	
	private Long id;
		
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
