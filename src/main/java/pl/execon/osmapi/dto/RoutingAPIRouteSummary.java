package pl.execon.osmapi.dto;

public class RoutingAPIRouteSummary {
	private String end_point;
	private String start_point;
	private long total_time;
	private long total_distance;
	public String getEnd_point() {
		return end_point;
	}
	public void setEnd_point(String end_point) {
		this.end_point = end_point;
	}
	public String getStart_point() {
		return start_point;
	}
	public void setStart_point(String start_point) {
		this.start_point = start_point;
	}
	public long getTotal_time() {
		return total_time;
	}
	public void setTotal_time(long total_time) {
		this.total_time = total_time;
	}
	public long getTotal_distance() {
		return total_distance;
	}
	public void setTotal_distance(long total_distance) {
		this.total_distance = total_distance;
	}
	
	
}
