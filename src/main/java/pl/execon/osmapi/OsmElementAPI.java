package pl.execon.osmapi;

import java.util.LinkedList;

import pl.execon.osmapi.dto.osm.OSMChangeset;
import pl.execon.osmapi.dto.osm.OSMCredentials;
import pl.execon.osmapi.dto.osm.OSMElement;
import pl.execon.osmapi.dto.osm.OSMNode;
import pl.execon.osmapi.dto.osm.OSMRelation;
import pl.execon.osmapi.dto.osm.OSMTag;
import pl.execon.osmapi.dto.osm.OSMWay;
import pl.execon.osmapi.endpoint.GenericEndpoint;
import pl.execon.osmapi.util.EncodeDecoderUtils;
import pl.execon.osmapi.util.Settings;


public class OsmElementAPI {

	GenericEndpoint endpoint;
	
	public OsmElementAPI(){
		endpoint = new GenericEndpoint();
	}
	/**
	 * Creates a new node in OSM returning the new node with populated id.
	 * @param node node data to be stored
	 * @param comment change message
	 * @param osmCredentials user credentials
	 * @return null on error, node with populated id on success
	 */
	public OSMNode createNode(OSMNode node,String comment, OSMCredentials osmCredentials){					
		OSMNode result = null;
		
		OSMChangeset osmChangeset = createChangeset(comment, osmCredentials);
		
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/node/create";		
		OSMElement osmElement = new OSMElement();
		osmElement.setNode(node);
		osmElement = createElement(osmElement, url, osmChangeset, osmCredentials);
		if(osmElement!=null)
			result = osmElement.getNode();
		closeChangeset(osmChangeset, osmCredentials);
		
		return result;
	};
	/**
	 * Retrieves a node with given id from OSM.
	 * @param id node id to be retrieved
	 * @return null on error, the node on success
	 */
	public OSMNode getNode(long id){
		OSMNode result = null;
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/node/"+id;		
		OSMElement element =  getElement(id, url);
		if(element!=null)
			result = element.getNode();
		
		return result;
	};
	
	/**
	 * Updates node data in OSM. BEWARE ! Please note that this will overwrite previous version of node so
	 * use with caution.
	 * @param node node with modified properties
	 * @param comment change message
	 * @param osmCredentials user credentials
	 * @return null on error, updated node on success
	 */
	public OSMNode updateNode(OSMNode node,String comment, OSMCredentials osmCredentials){
		OSMChangeset osmChangeset = createChangeset(comment, osmCredentials);
		
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/node/"+node.getId();		
		OSMElement osmElement = new OSMElement();
		osmElement.setNode(node);
		osmElement = updateElement(osmElement, url, osmChangeset, osmCredentials);
		
		closeChangeset(osmChangeset, osmCredentials);
		
		return osmElement.getNode();
	}
	
	/**
	 * Creates a new way in OSM returning the new way with populated id.
	 * @param way way data to be stored
	 * @param comment change message
	 * @param osmCredentials user credentials
	 * @return null on error, way with populated id on success
	 */
	public OSMWay createWay(OSMWay way,String comment, OSMCredentials osmCredentials){					
		OSMWay result = null;
		
		OSMChangeset osmChangeset = createChangeset(comment, osmCredentials);
		
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/way/create";		
		OSMElement osmElement = new OSMElement();
		osmElement.setWay(way);
		osmElement = createElement(osmElement, url, osmChangeset, osmCredentials);
		if(osmElement!=null)
			result = osmElement.getWay();
		
		closeChangeset(osmChangeset, osmCredentials);
		
		return result;
	};
	
	/**
	 * 
	 * Retrieves a way with given id from OSM.
	 * @param id way id to be retrieved
	 * @return null on error, the way on success
	 */
	public OSMWay getWay(long id){
		OSMWay result = null;
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/way/"+id;		
		OSMElement element = getElement(id, url);
		if(element != null)
			result = element.getWay();
		
		return result;
	};
	
	/**
	 * Updates way data in OSM. BEWARE ! Please note that this will overwrite previous version of way so
	 * use with caution.
	 * @param way way with modified properties
	 * @param comment change message
	 * @param osmCredentials user credentials
	 * @return null on error, updated way on success
	 */
	public OSMWay updateWay(OSMWay way,String comment, OSMCredentials osmCredentials){
		
		OSMChangeset osmChangeset = createChangeset("", osmCredentials);
		
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/way/"+way.getId();		
		OSMElement osmElement = new OSMElement();
		osmElement.setWay(way);
		osmElement = updateElement(osmElement, url, osmChangeset, osmCredentials);
		
		closeChangeset(osmChangeset, osmCredentials);
		
		return osmElement.getWay();
	};
	
	/**
	 * Creates a new relation in OSM returning the new relation with populated id.
	 * @param relation relation data to be stored
	 * @param comment change message
	 * @param osmCredentials user credentials
	 * @return null on error, updated relation on success
	 */
	public OSMRelation createRelation(OSMRelation relation,String comment, OSMCredentials osmCredentials){					
		OSMRelation result = null;
		
		OSMChangeset osmChangeset = createChangeset(comment, osmCredentials);
		
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/relation/create";		
		OSMElement osmElement = new OSMElement();
		osmElement.setRelation(relation);
		osmElement = createElement(osmElement, url, osmChangeset, osmCredentials);
		if(osmElement!=null)
			result = osmElement.getRelation();
		
		closeChangeset(osmChangeset, osmCredentials);
		
		return result;
	};
	
	/**
	 * 
	 * Retrieves a relation with given id from OSM.
	 * @param id relation id to be retrieved
	 * @return null on error, the relation on success
	 */
	public OSMRelation getRelation(long id){
		OSMRelation result = null;
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/relation/"+id;		
		OSMElement element = getElement(id, url);
		if(element != null)
			result = element.getRelation();
		
		return result;
	};
	
	/**
	 * Updates relation data in OSM. BEWARE ! Please note that this will overwrite previous version of relation so
	 * use with caution.
	 * @param relation relation with modified properties
	 * @param comment change message
	 * @param osmCredentials user credentials
	 * @return null on error, updated relation on success
	 */
	public OSMRelation updateRelation(OSMRelation relation,String comment, OSMCredentials osmCredentials){
		
		OSMChangeset osmChangeset = createChangeset("", osmCredentials);
		
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/relation/"+relation.getId();		
		OSMElement osmElement = new OSMElement();
		osmElement.setRelation(relation);
		osmElement = updateElement(osmElement, url, osmChangeset, osmCredentials);
		
		closeChangeset(osmChangeset, osmCredentials);
		
		return osmElement.getRelation();
	}
		
	
	/**
	 * Tries to close changeset. This method may fail as changeset may be closed by some other process
	 * @param osmChangeset changeset to be closed
	 * @param osmCredentials user credentials
	 */
	protected void closeChangeset(OSMChangeset osmChangeset, OSMCredentials osmCredentials) {
		if(osmChangeset==null||osmCredentials==null)
			return;
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/changeset/"+osmChangeset.getId()+"/close";
		
		endpoint.requestURLWithPUTWithBasicAuth(url, osmCredentials.getLogin(), osmCredentials.getPassword());
	}
	
	/**
	 * Creates given element (node, way, ) using active changeset
	 * @param osmElement element to be created in OSM (without id)
	 * @param url api url
	 * @param osmChangeset active changeset
	 * @param osmCredentials user credentials
	 * @return null on error, element with updated id on success
	 */
	protected OSMElement createElement(OSMElement osmElement,String url, OSMChangeset osmChangeset, OSMCredentials osmCredentials){
		if(osmCredentials==null||osmChangeset==null)
			return null;
		
		Long createdId = null;
		// need to add changeset into node information
		if(osmElement.getNode()!=null)
			osmElement.getNode().setChangeset(osmChangeset.getId());
		if(osmElement.getWay()!=null)
			osmElement.getWay().setChangeset(osmChangeset.getId());
		if(osmElement.getRelation()!=null)
			osmElement.getRelation().setChangeset(osmChangeset.getId());
		
				
//		XmlMapper xmlMapper = new XmlMapper();
//		xmlMapper.setSerializationInclusion(Include.NON_NULL);
//		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
		
		String requestBody = null;
//		try {
//			//xmlMapper.writeValue(System.out, osmElement);
//			requestBody = xmlMapper.writeValueAsString(osmElement);
//		} catch (IOException e) {
//			System.err.println("Error while serializing element: "+e.getMessage());
//		}
		
		
		requestBody = EncodeDecoderUtils.toXML(osmElement);
		

		
		String textResponse = null;
		if(requestBody!=null){
			textResponse = endpoint.requestURLWithPUTWithBasicAuth(url,requestBody, osmCredentials.getLogin(), osmCredentials.getPassword());
		}
		
		if(textResponse!=null){
			createdId = Long.valueOf(textResponse);
			if(osmElement.getNode()!=null){
				osmElement.getNode().setId(createdId);
				osmElement.setNode(getNode(createdId));
			}
			if(osmElement.getWay()!=null){
				osmElement.getWay().setId(createdId);
				osmElement.setWay(getWay(createdId));
			}
			if(osmElement.getRelation()!=null){
				osmElement.getRelation().setId(createdId);
				osmElement.setRelation(getRelation(createdId));
			}
			
			
			
			return osmElement;
		}
		return null;
	}
	
	/**
	 * Retrieves element from OSM with given id. Type of element is driven by proper url construction 
	 * @param id the id of the element to be retrieved
	 * @param url api call url (way, node, )
	 * @return null on error, the element when found
	 */
	protected OSMElement getElement(long id, String url){
			
		String xmlResponse = endpoint.requestURL(url);
		
		OSMElement result = null;
		
//		XmlMapper xmlMapper = new XmlMapper();
//		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
//		
//		
//		try {
//			if(xmlResponse!=null && !xmlResponse.isEmpty()){
//				result =  xmlMapper.readValue(xmlResponse, OSMElement.class);
//			}
//		} catch (IOException e) {
//			System.err.println("Error getting way: "+id+" due to:"+e.getMessage());
//		}

		result = EncodeDecoderUtils.fromXML(xmlResponse);
		return result;
	}
	

	/**
	 * Updates element data in osm (FULL update), returning the updated element or null on error.
	 * @param osmElement element to be updated
	 * @param url update mode url (node, way, )
	 * @param osmChangeset active changset
	 * @param osmCredentials user credentials
	 * @return null on error, updated element on success
	 */
	protected OSMElement updateElement(OSMElement osmElement,String url, OSMChangeset osmChangeset, OSMCredentials osmCredentials){
		
		
		if(osmCredentials==null||osmChangeset==null)
			return null;
		
		Long versionNumber = null;
		// need to add changeset into node information
		if(osmElement.getNode()!=null)
			osmElement.getNode().setChangeset(osmChangeset.getId());
		if(osmElement.getWay()!=null)
			osmElement.getWay().setChangeset(osmChangeset.getId());
		if(osmElement.getRelation()!=null)
			osmElement.getRelation().setChangeset(osmChangeset.getId());
		
				
//		XmlMapper xmlMapper = new XmlMapper();
//		xmlMapper.setSerializationInclusion(Include.NON_NULL);
//		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
//		
//		String requestBody = null;
//		try {
//			//xmlMapper.writeValue(System.out, osmElement);
//			requestBody = xmlMapper.writeValueAsString(osmElement);
//		} catch (IOException e) {
//			System.err.println("Error while serializing element: "+e.getMessage());
//		}
		
		String requestBody = null;
		requestBody = EncodeDecoderUtils.toXML(osmElement);

		
		String textResponse = null;
		if(requestBody!=null){
			textResponse = endpoint.requestURLWithPUTWithBasicAuth(url,requestBody, osmCredentials.getLogin(), osmCredentials.getPassword());
		}
		
		if(textResponse!=null){
			versionNumber = Long.valueOf(textResponse);
			if(osmElement.getNode()!=null){
				osmElement.setNode(getNode(osmElement.getNode().getId()));
			}
				//osmElement.getNode().setVersion(versionNumber);
			if(osmElement.getWay()!=null){
				osmElement.setWay(getWay(osmElement.getWay().getId()));
			}
				//osmElement.getWay().setVersion(versionNumber);
			if(osmElement.getRelation()!=null){
				osmElement.setRelation(getRelation(osmElement.getRelation().getId()));
			}
				//osmElement.getRelation().setVersion(versionNumber);
		}
		return osmElement;
	};

	/**
	 * Opens new, active changeset in OSM database
	 * @param comment change message
	 * @param osmCredentials user credentials
	 * @return null on error, changeset upon success
	 */
	protected OSMChangeset createChangeset(String comment, OSMCredentials osmCredentials){
		if(osmCredentials==null)
			return null;
		
		Long changesetId = null;
		
		OSMElement osmElement = new OSMElement();
		
		OSMChangeset changeset = new OSMChangeset();
		OSMTag createdBy = new OSMTag("created_by", "Java-APIOSM");
		OSMTag commentTag = null;
		
		if(comment==null||comment.isEmpty()){
			commentTag = new OSMTag("comment", Settings.ENDPOINT_OSM_API_V06_DEFAULT_COMMENT);
		}else{
			commentTag = new OSMTag("comment", Settings.ENDPOINT_OSM_API_V06_DEFAULT_COMMENT);
		}
		
		LinkedList<OSMTag> properties = new LinkedList<OSMTag>();
		properties.add(commentTag);
		properties.add(createdBy);
		
		changeset.setProperties(properties);
		osmElement.setChangeset(changeset);
		
//		XmlMapper xmlMapper = new XmlMapper();
//		xmlMapper.setSerializationInclusion(Include.NON_NULL);
//		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
//		
//		String requestBody = null;
//		try {
//			//xmlMapper.writeValue(System.out, osmElement);
//			requestBody = xmlMapper.writeValueAsString(osmElement);
//		} catch (IOException e) {
//			System.err.println("Error while serializing changeset: "+e.getMessage());
//		}
		
		String requestBody = null;
		requestBody = EncodeDecoderUtils.toXML(osmElement);
		
		String url = Settings.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL;
		url += "/changeset/create";
		
		String response = null;
		
		if(requestBody != null){
			response = endpoint.requestURLWithPUTWithBasicAuth(url, requestBody, osmCredentials.getLogin(), osmCredentials.getPassword());
		}
		
		if(response!=null){
			changesetId = Long.valueOf(response);
			changeset.setId(changesetId);
		}else{
			changeset = null;
		}
		return changeset;
		
	}
	
}
