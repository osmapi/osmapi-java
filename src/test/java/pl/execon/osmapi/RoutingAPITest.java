package pl.execon.osmapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.execon.osmapi.dto.RoutingAPIPath;
import pl.execon.osmapi.dto.RoutingAPIQuery;
import pl.execon.osmapi.dto.RoutingAPIRawResponse;
import pl.execon.osmapi.endpoint.GenericEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class RoutingAPITest {
	@InjectMocks
	RoutingAPI routingAPI = new RoutingAPI();
	
	RoutingAPI routingAPIOnline = new RoutingAPI();
	
	
	@Mock
	GenericEndpoint endpoint;
	
	
	@Before
	public void setUp() throws IOException, URISyntaxException{
		String route1_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/RouteAPI_Response_1").toURI())));
		String route2_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/RouteAPI_Response_2").toURI())));
		
		when(endpoint.requestURL("http://osmapi.execon.pl/routing/viaroute?loc=52.8709827,20.6171934&loc=52.5354061,19.715046")).thenReturn(route1_Response);
		when(endpoint.requestURL("http://osmapi.execon.pl/routing/viaroute?loc=52.8709827,20.6171934&loc=52.5354061,19.715046&instructions=true")).thenReturn(route2_Response);
		//
	}
	
	@Test
	public void simpleRouting(){		
		RoutingAPIQuery query = QueryFactory.createRoutingQuery(52.8709827, 20.6171934, 52.5354061, 19.715046);
		RoutingAPIPath path = routingAPI.findPath(query, Locale.getDefault());
		assertEquals("Total distance mismatch:",82466, path.getTotalDistance());
		assertEquals("Total time mismatch:",4731, path.getTotalTime());
		//assertEquals("Path length mismatch:",9, path.getLatLonsOnPath().length);
		assertEquals("Instruction length mismatch:",0, path.getInstructions().length);
	}
	
	@Test
	public void simpleRoutingOnline(){		
		RoutingAPIQuery query = QueryFactory.createRoutingQuery(52.8709827, 20.6171934, 52.5354061, 19.715046);
		RoutingAPIPath path = routingAPIOnline.findPath(query, Locale.getDefault());
		assertEquals("Total distance mismatch:",82466, path.getTotalDistance());
		assertEquals("Total time mismatch:",4731, path.getTotalTime());
		//assertEquals("Path length mismatch:",9, path.getLatLonsOnPath().length);
		assertEquals("Instruction length mismatch:",0, path.getInstructions().length);
	}
	
	@Test
	public void routingWithInstructions(){		
		RoutingAPIQuery query = QueryFactory.createRoutingQueryWithInstructions(52.8709827, 20.6171934, 52.5354061, 19.715046);
		RoutingAPIPath path = routingAPI.findPath(query, Locale.getDefault());
		assertEquals("Total distance mismatch:",82466, path.getTotalDistance());
		assertEquals("Total time mismatch:",4731, path.getTotalTime());
		//assertEquals("Path length mismatch:",9, path.getLatLonsOnPath().length);
		assertEquals("Instruction length mismatch:",13, path.getInstructions().length);				
	}
	
	@Test
	public void routingWithInstructionsOnline(){		
		RoutingAPIQuery query = QueryFactory.createRoutingQueryWithInstructions(52.8709827, 20.6171934, 52.5354061, 19.715046);
		RoutingAPIPath path = routingAPIOnline.findPath(query, Locale.getDefault());
		assertEquals("Total distance mismatch:",82466, path.getTotalDistance());
		assertEquals("Total time mismatch:",4731, path.getTotalTime());
		//assertEquals("Path length mismatch:",9, path.getLatLonsOnPath().length);
		assertEquals("Instruction length mismatch:",13, path.getInstructions().length);				
	}
	
	@Test
	public void rawRoutingWithInstructions(){		
		RoutingAPIQuery query = QueryFactory.createRoutingQueryWithInstructions(52.8709827, 20.6171934, 52.5354061, 19.715046);
		RoutingAPIRawResponse rawResponse = routingAPI.findPathRaw(query);
		
		assertEquals("Checksum mismatch: ",2264440226L,rawResponse.getHint_data().getChecksum());
		assertEquals("Hint location mismatch: ","9-k3A3YHCQUCdgkAJgAAABAAAAAvAQAAkAEAAHQSbgEAAAAAZL8mA_iJOgEIABEA",rawResponse.getHint_data().getLocations()[0]);
		assertEquals("Total distance mismatch: ",82466,rawResponse.getRoute_summary().getTotal_distance());
		assertEquals("Total time mismatch: ",4731,rawResponse.getRoute_summary().getTotal_time());
//		assertEquals("Start point mismatch: ","Augustiańska",rawResponse.getRoute_summary().getStart_point());
//		assertEquals("End point mismatch: ","Południowa",rawResponse.getRoute_summary().getEnd_point());
		assertNotNull("Route geometry null:",rawResponse.getRoute_geometry());
		assertEquals("Status mismatch: ",0,rawResponse.getStatus());
		assertEquals("Instructions contents mismatch: ",9,rawResponse.getRoute_instructions()[0].length);
		assertEquals("Instructions mismatch: ",13,rawResponse.getRoute_instructions().length);
				
	}
	
	@Test
	public void rawRoutingWithInstructionsOnline(){		
		RoutingAPIQuery query = QueryFactory.createRoutingQueryWithInstructions(52.8709827, 20.6171934, 52.5354061, 19.715046);
		RoutingAPIRawResponse rawResponse = routingAPIOnline.findPathRaw(query);
		
		assertEquals("Checksum mismatch: ",2264440226L,rawResponse.getHint_data().getChecksum());
		assertEquals("Hint location mismatch: ","9-k3A3YHCQUCdgkAJgAAABAAAAAvAQAAkAEAAHQSbgEAAAAAZL8mA_iJOgEIABEA",rawResponse.getHint_data().getLocations()[0]);
		assertEquals("Total distance mismatch: ",82466,rawResponse.getRoute_summary().getTotal_distance());
		assertEquals("Total time mismatch: ",4731,rawResponse.getRoute_summary().getTotal_time());
		assertEquals("Start point mismatch: ","Augustiańska",rawResponse.getRoute_summary().getStart_point());
		assertEquals("End point mismatch: ","Południowa",rawResponse.getRoute_summary().getEnd_point());
		assertNotNull("Route geometry null:",rawResponse.getRoute_geometry());
		assertEquals("Status mismatch: ",0,rawResponse.getStatus());
		assertEquals("Instructions contents mismatch: ",9,rawResponse.getRoute_instructions()[0].length);
		assertEquals("Instructions mismatch: ",13,rawResponse.getRoute_instructions().length);
				
	}
	
	
	
	
}
