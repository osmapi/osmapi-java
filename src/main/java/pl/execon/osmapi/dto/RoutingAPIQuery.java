package pl.execon.osmapi.dto;

public class RoutingAPIQuery {
	private  Point[] viaPoints;
	private boolean withInstructions;
	public Point[] getViaPoints() {
		return viaPoints;
	}
	public void setViaPoints(Point[] viaPoints) {
		this.viaPoints = viaPoints;
	}
	public boolean isWithInstructions() {
		return withInstructions;
	}
	public void setWithInstructions(boolean withInstructions) {
		this.withInstructions = withInstructions;
	}
	
}
