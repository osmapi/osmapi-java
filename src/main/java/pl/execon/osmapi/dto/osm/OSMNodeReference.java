package pl.execon.osmapi.dto.osm;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OSMNodeReference {
	@JacksonXmlProperty(isAttribute=true, namespace="")
	private long ref;

	public long getRef() {
		return ref;
	}

	public void setRef(long ref) {
		this.ref = ref;
	}
	
	
}
