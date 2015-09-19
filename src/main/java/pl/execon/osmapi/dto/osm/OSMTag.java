package pl.execon.osmapi.dto.osm;

import org.simpleframework.xml.Attribute;




public class OSMTag {
	
	@Attribute
	private String k;
	
	@Attribute
	private String v;
	
	public OSMTag(){		
	}
	
	public OSMTag(String k, String v){
		this.k = k;
		this.v = v;
	}
	
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	
	
}
