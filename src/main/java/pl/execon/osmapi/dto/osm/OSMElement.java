package pl.execon.osmapi.dto.osm;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Wrapper object for OSM data manipulation. Contains one of: node, way, relation.
 * Examples:
 * <br>
 * A. Node<br>
 * <pre>
 * &lt;osm&gt;
 * &lt;node changeset=&quot;12&quot; lat=&quot;...&quot; lon=&quot;...&quot;&gt;
 *  &lt;tag k=&quot;note&quot; v=&quot;Just a node&quot;/&gt;
 *  ...
 * &lt;/node&gt;
 * &lt;/osm&gt;
 * </pre>
 * <br>
 * B. Way</br>
 * <pre>
 * &lt;osm&gt;
 * &lt;way changeset=&quot;12&quot;&gt;
 *  &lt;tag k=&quot;note&quot; v=&quot;Just a way&quot;/&gt;
 *  ...
 *  &lt;nd ref=&quot;123&quot;/&gt;
 *  &lt;nd ref=&quot;4345&quot;/&gt;
 *  ...
 *	&lt;/way&gt;
 * &lt;/osm&gt;
 * </pre>
 * <br>
 * C. Relation<br>
 * <pre>
 * &lt;osm&gt;
 * &lt;relation changeset=&quot;12&quot;&gt;
 *   &lt;tag k=&quot;note&quot; v=&quot;Just a relation&quot;/&gt;
 *   ...
 *   &lt;member type=&quot;node&quot; role=&quot;stop&quot; ref=&quot;123&quot;/&gt;
 *  &lt;member type=&quot;way&quot; ref=&quot;234&quot;/&gt;
 * &lt;/relation&gt;
 * &lt;/osm&gt; 
 * </pre>
 * @author grulka
 *
 */
@JacksonXmlRootElement(localName="osm")
@Root(name="osm",strict=false)
public class OSMElement {
	@Element(required=false)
	private OSMNode node;
	@Element(required=false)
	private OSMChangeset changeset;
	@Element(required=false)
	private OSMWay way;
	@Element(required=false)
	private OSMRelation relation;

	public OSMNode getNode() {
		return node;
	}

	public void setNode(OSMNode node) {
		this.node = node;
	}

	public OSMChangeset getChangeset() {
		return changeset;
	}

	public void setChangeset(OSMChangeset changeset) {
		this.changeset = changeset;
	}

	public OSMWay getWay() {
		return way;
	}

	public void setWay(OSMWay way) {
		this.way = way;
	}

	public OSMRelation getRelation() {
		return relation;
	}

	public void setRelation(OSMRelation relation) {
		this.relation = relation;
	}
	
	
	
	
	
	
	
}
