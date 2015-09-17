package pl.execon.osmapi.dto;

public class RoutingAPIRawResponse {
	private RoutingAPIHintData hint_data;
	private String[] route_name;
	private long[] via_indices;
	boolean found_alternative;
	private RoutingAPIRouteSummary route_summary;
	private double[][] via_points;
	private Object[][] route_instructions;
	private String route_geometry;
	private String status_message;
	private int status;
	public RoutingAPIHintData getHint_data() {
		return hint_data;
	}
	public void setHint_data(RoutingAPIHintData hint_data) {
		this.hint_data = hint_data;
	}
	public String[] getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String[] route_name) {
		this.route_name = route_name;
	}
	public long[] getVia_indices() {
		return via_indices;
	}
	public void setVia_indices(long[] via_indices) {
		this.via_indices = via_indices;
	}
	public boolean isFound_alternative() {
		return found_alternative;
	}
	public void setFound_alternative(boolean found_alternative) {
		this.found_alternative = found_alternative;
	}
	public RoutingAPIRouteSummary getRoute_summary() {
		return route_summary;
	}
	public void setRoute_summary(RoutingAPIRouteSummary route_summary) {
		this.route_summary = route_summary;
	}
	
	public double[][] getVia_points() {
		return via_points;
	}
	public void setVia_points(double[][] via_points) {
		this.via_points = via_points;
	}
	public Object[][] getRoute_instructions() {
		return route_instructions;
	}
	public void setRoute_instructions(Object[][] route_instructions) {
		this.route_instructions = route_instructions;
	}
	public String getRoute_geometry() {
		return route_geometry;
	}
	public void setRoute_geometry(String route_geometry) {
		this.route_geometry = route_geometry;
	}
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
