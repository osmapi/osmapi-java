package pl.execon.osmapi.dto.osm;

import org.simpleframework.xml.Attribute;



public class OSMNodeReference {
	
	@Attribute(required=false)
	private long ref;

	public long getRef() {
		return ref;
	}

	public void setRef(long ref) {
		this.ref = ref;
	}
	
	
}
