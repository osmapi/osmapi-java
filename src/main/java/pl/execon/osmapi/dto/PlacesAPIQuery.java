package pl.execon.osmapi.dto;

public class PlacesAPIQuery {
	private Point location;
	private Point[] boundingBox;
	Integer near;
	
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public Point[] getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(Point[] boundingBox) {
		this.boundingBox = boundingBox;
	}
	public Integer getNear() {
		return near;
	}
	public void setNear(Integer near) {
		this.near = near;
	}
	
	
	
}
