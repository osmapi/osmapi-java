package pl.execon.osmapi.dto.osm;

import org.simpleframework.xml.Attribute;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OSMRelationMember {
	public final static String TYPE_NODE = "node", TYPE_WAY = "way", TYPE_RELATION = "relation";
	
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private String type;
	
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private String role;
	
	@JacksonXmlProperty(isAttribute=true)
	@Attribute(required=false)
	private long ref;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getRef() {
		return ref;
	}

	public void setRef(long ref) {
		this.ref = ref;
	}
	
	
}
