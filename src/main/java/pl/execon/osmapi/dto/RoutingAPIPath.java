package pl.execon.osmapi.dto;

public class RoutingAPIPath {
	private Point[] latLonsOnPath;
	private RoutingAPIInstruction[] instructions;
	private boolean isPathFound;
	private long totalDistance;
	private long totalTime;
	
	public Point[] getLatLonsOnPath() {
		return latLonsOnPath;
	}
	public void setLatLonsOnPath(Point[] latLonsOnPath) {
		this.latLonsOnPath = latLonsOnPath;
	}
	public RoutingAPIInstruction[] getInstructions() {
		return instructions;
	}
	public void setInstructions(RoutingAPIInstruction[] instructions) {
		this.instructions = instructions;
	}
	public boolean isPathFound() {
		return isPathFound;
	}
	public void setPathFound(boolean isPathFound) {
		this.isPathFound = isPathFound;
	}
	public long getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(long totalDistance) {
		this.totalDistance = totalDistance;
	}
	public long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
	
	
	
}
