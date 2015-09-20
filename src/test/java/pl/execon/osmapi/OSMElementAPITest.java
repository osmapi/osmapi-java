package pl.execon.osmapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.execon.osmapi.dto.osm.OSMChangeset;
import pl.execon.osmapi.dto.osm.OSMCredentials;
import pl.execon.osmapi.dto.osm.OSMNode;
import pl.execon.osmapi.dto.osm.OSMNodeReference;
import pl.execon.osmapi.dto.osm.OSMRelation;
import pl.execon.osmapi.dto.osm.OSMRelationMember;
import pl.execon.osmapi.dto.osm.OSMTag;
import pl.execon.osmapi.dto.osm.OSMWay;
import pl.execon.osmapi.endpoint.GenericEndpoint;
import pl.execon.osmapi.util.Preferences;


@RunWith(MockitoJUnitRunner.class)
public class OSMElementAPITest {
	@InjectMocks
	OsmElementAPI osmElementAPI = new OsmElementAPI();
	
	OsmElementAPI osmElementAPIOnline = new OsmElementAPI();
	
	@Mock
	GenericEndpoint endpoint;
	
	OSMCredentials osmCredentials;
	
	@Before
	public void setUp() throws IOException, URISyntaxException{
		String response_1 = new String(Files.readAllBytes(Paths.get(getClass().getResource("/OsmElementAPI_Response_1").toURI())));		
		
		when(endpoint.requestURL(Preferences.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL+"/node/803223")).thenReturn(response_1);
		
		when(endpoint.requestURLWithBasicAuth(Matchers.eq("https://"+Preferences.ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL+"/node/803223"),Matchers.anyString(), Matchers.anyString())).thenReturn(response_1);
				
		osmCredentials = new OSMCredentials(Preferences.DEVELOPMENT_OSM_CREDENTIALS_LOGIN, Preferences.DEVELOPMENT_OSM_CREDENTIALS_PASS);
		
	}
	
	
		
	@Test
	public void createChangeset(){					
		OSMChangeset changeset = osmElementAPIOnline.createChangeset("some changes",osmCredentials);
		assertNotNull(changeset);
		assertNotNull(changeset.getId());	
	}
	
	@Test
	public void createNodeOnline(){	
		OSMNode node = new OSMNode();
		node.setLon(23.50);
		node.setLat(53.21);
		node.setVisible(true);
		OSMNode result = osmElementAPIOnline.createNode(node, "some comment", osmCredentials);
		assertNotNull(result);		
		assertNotNull(result.getId());		
		assertNotNull(result.getVersion());
	}
	
	@Test
	public void getNodeOnline(){				
		OSMNode result = osmElementAPIOnline.getNode(803223);
		assertNotNull(result);		
		assertNotNull(result.getId());
	}
	
	@Test
	public void updateNodeOnline(){				
		OSMNode result = osmElementAPIOnline.getNode(803223);
		long previousVersion = result.getVersion();		
					
		OSMNode updatedNode = osmElementAPIOnline.updateNode(result,"some changes", osmCredentials);
		assertNotNull(updatedNode);
		assertTrue(updatedNode.getVersion()>previousVersion);
	}
	
	@Test
	public void createWayOnline(){
		OSMWay way = new OSMWay();
		OSMNodeReference ref1 = new OSMNodeReference();
		ref1.setRef(803223);
		OSMNodeReference ref2 = new OSMNodeReference();
		ref2.setRef(803224);
		List<OSMNodeReference> nodesList = new LinkedList<OSMNodeReference>();
		nodesList.add(ref1);
		nodesList.add(ref2);
		way.setNodeReferences(nodesList);
		
		OSMTag tag = new OSMTag("amenity","fastfood");
		List<OSMTag> tags = new LinkedList<OSMTag>();
		tags.add(tag);
		way.setTags(tags);
		
		way = osmElementAPIOnline.createWay(way, "some changes", osmCredentials);
		
		assertNotNull(way);
		assertNotNull(way.getId());
	}
	
	@Test
	public void getWayOnline(){				
		OSMWay result = osmElementAPIOnline.getWay(4298099995L);
		assertNotNull(result);
		assertNotNull(result.getId());
		
		assertTrue(result.getNodeReferences().size()>0);
		assertTrue(result.getTags().size()>0);
	}
	
	@Test
	public void updateWayOnline(){				
		OSMWay result = osmElementAPIOnline.getWay(12344);
		assertNotNull(result);
		assertNotNull(result.getId());
		long previousVersion = result.getVersion();
		
		OSMWay updatedWay = osmElementAPIOnline.updateWay(result, "some changes", osmCredentials);
		
		assertNotNull(updatedWay);
		assertTrue(updatedWay.getVersion()>previousVersion);
	}
	
	
	@Test
	public void createRelationOnline(){
		OSMRelation relation = new OSMRelation();
		
		OSMRelationMember member1 = new OSMRelationMember();
		member1.setType(OSMRelationMember.TYPE_NODE);
		member1.setRole("from");
		member1.setRef(803223);
		
		
		List<OSMRelationMember> membersList = new LinkedList<OSMRelationMember>();
		membersList.add(member1);		
		relation.setMembers(membersList);
		
		OSMTag tag = new OSMTag("note","Just a relation");
		List<OSMTag> tags = new LinkedList<OSMTag>();
		tags.add(tag);
		relation.setTags(tags);
		
		relation = osmElementAPIOnline.createRelation(relation, "some changes", osmCredentials);
		
		assertNotNull(relation);
		assertNotNull(relation.getId());
	}
	
	@Test
	public void getRelationOnline(){				
		OSMRelation result = osmElementAPIOnline.getRelation(4297939855L);
		assertNotNull(result);
		assertNotNull(result.getId());
		
		assertTrue(result.getMembers().size()>0);
		assertTrue(result.getTags().size()>0);
	}
	
	@Test
	public void updateRelationOnline(){				
		OSMRelation result = osmElementAPIOnline.getRelation(4297939855L);
		assertNotNull(result);
		assertNotNull(result.getId());
		long previousVersion = result.getVersion();
		
		OSMRelation updatedRelation = osmElementAPIOnline.updateRelation(result, "some changes", osmCredentials);
		
		assertNotNull(updatedRelation);
		assertTrue(updatedRelation.getVersion()>previousVersion);
	}
	
}
