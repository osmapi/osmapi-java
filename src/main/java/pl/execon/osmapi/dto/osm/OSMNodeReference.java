package pl.execon.osmapi.dto.osm;

import org.simpleframework.xml.Attribute;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OSMNodeReference {
	@JacksonXmlProperty(isAttribute=true, namespace="")
	@Attribute(required=false)
	private long ref;

	public long getRef() {
		return ref;
	}

	public void setRef(long ref) {
		this.ref = ref;
	}
	
	
}
