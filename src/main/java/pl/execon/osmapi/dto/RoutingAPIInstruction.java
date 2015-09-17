package pl.execon.osmapi.dto;

public class RoutingAPIInstruction {
	private String instructionCode;
	private String description;
	private long distance;
	private String objName;
	
	public String getInstructionCode() {
		return instructionCode;
	}
	public void setInstructionCode(String instructionCode) {
		this.instructionCode = instructionCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	
}
