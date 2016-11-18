package pl.execon.osmapi.dto;

public class Properties {
	private String detailsLink;
	private String name;
	private String matchType;
	private String type;
	private Long externalId;
	
	public String getDetailsLink() {
		return detailsLink;
	}
	public void setDetailsLink(String detailsLink) {
		this.detailsLink = detailsLink;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getExternalId() {
		return externalId;
	}
	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}
	
	
	
}
