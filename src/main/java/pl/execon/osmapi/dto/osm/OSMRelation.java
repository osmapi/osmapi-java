package pl.execon.osmapi.dto.osm;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;


public class OSMRelation {
	
	@Attribute(required=false)
	private long id;
	
	
	@Attribute(required=false)
	private long uid;
	
	
	@Attribute(required=false)
	private long changeset;
	
	@Attribute(required=false)
	private String timestamp;
	
	@Attribute(required=false)
	private long version;
	
	@Attribute(required=false)
	private boolean visible;
	
	@Attribute(required=false)
	private String user;
	
	@ElementList(inline=true, entry="tag")
	List<OSMTag> tags;
	
	@ElementList(inline=true, entry="member")
	List<OSMRelationMember> members;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getChangeset() {
		return changeset;
	}

	public void setChangeset(long changeset) {
		this.changeset = changeset;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<OSMTag> getTags() {
		return tags;
	}

	public void setTags(List<OSMTag> tags) {
		this.tags = tags;
	}

	public List<OSMRelationMember> getMembers() {
		return members;
	}

	public void setMembers(List<OSMRelationMember> members) {
		this.members = members;
	}
	
	
}
