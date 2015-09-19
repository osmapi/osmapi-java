package pl.execon.osmapi.dto.osm;

import org.simpleframework.xml.Attribute;


public class OSMRelationMember {
	public final static String TYPE_NODE = "node", TYPE_WAY = "way", TYPE_RELATION = "relation";
	
	@Attribute(required=false)
	private String type;
	
	@Attribute(required=false)
	private String role;
	
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
