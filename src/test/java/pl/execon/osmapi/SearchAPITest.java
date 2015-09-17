package pl.execon.osmapi;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.execon.osmapi.QueryFactory;
import pl.execon.osmapi.SearchAPI;
import pl.execon.osmapi.dto.SearchAPIPlace;
import pl.execon.osmapi.dto.SearchAPIPlaceType;
import pl.execon.osmapi.dto.SearchAPIQuery;
import pl.execon.osmapi.endpoint.GenericEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class SearchAPITest {
	@InjectMocks
	SearchAPI searchAPI = new SearchAPI();
	
	SearchAPI searchAPIOnline = new SearchAPI();
	
	
	@Mock
	GenericEndpoint endpoint;
	
	
	@Before
	public void setUp() throws IOException, URISyntaxException{
		String ziel_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/SearchAPI_Response_Ziel").toURI())));
		String ziel_Dzien_53_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/SearchAPI_Response_Ziel_Dzien_53").toURI())));
		String ziel_utf8_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/SearchAPI_Response_utf8").toURI())));
		
		when(endpoint.requestURL("http://osmapi.execon.pl/search/address?query=ziel")).thenReturn(ziel_Response);
		when(endpoint.requestURL("http://osmapi.execon.pl/search/address?query=zielonka+dziennikarska+53")).thenReturn(ziel_Dzien_53_Response);
		when(endpoint.requestURL("http://osmapi.execon.pl/search/address?query=z%C4%85bki+wolno%C5%9Bci")).thenReturn(ziel_utf8_Response);
	}
	
	@Test
	public void simpleQuery(){		
		SearchAPIQuery query = QueryFactory.createSearchQuery("ziel");
		SearchAPIPlace[] results = searchAPI.findAddress(query);
		assertEquals("Result size mismatch:",57, results.length);
		assertEquals("Type mismatch: ",SearchAPIPlaceType.C,results[0].getType());
		assertEquals("Type mismatch: ",SearchAPIPlaceType.P,results[56].getType());
				
	}
	
	@Test
	public void simpleQueryOnline(){		
		SearchAPIQuery query = QueryFactory.createSearchQuery("ziel");
		SearchAPIPlace[] results = searchAPIOnline.findAddress(query);
		assertEquals("Result size mismatch:",57, results.length);
		assertEquals("Type mismatch: ",SearchAPIPlaceType.C,results[0].getType());
		assertEquals("Type mismatch: ",SearchAPIPlaceType.P,results[56].getType());
				
	}
	
	@Test
	public void preciseQuery(){
		SearchAPIQuery query = QueryFactory.createSearchQuery("zielonka dziennikarska 53");
		SearchAPIPlace[] results = searchAPI.findAddress(query);
		assertEquals("Result size mismatch:",6, results.length);
	}
	
	@Test
	public void preciseQueryOnline(){
		SearchAPIQuery query = QueryFactory.createSearchQuery("zielonka dziennikarska 53");
		SearchAPIPlace[] results = searchAPIOnline.findAddress(query);
		assertEquals("Result size mismatch:",6, results.length);
	}
	
	@Test
	public void utf8Query(){
		SearchAPIQuery query = QueryFactory.createSearchQuery("ząbki wolności");
		SearchAPIPlace[] results = searchAPI.findAddress(query);
		assertEquals("Result size mismatch:",2, results.length);
		
	}
	
	@Test
	public void utf8QueryOnline(){
		SearchAPIQuery query = QueryFactory.createSearchQuery("ząbki wolności");
		SearchAPIPlace[] results = searchAPIOnline.findAddress(query);
		assertEquals("Result size mismatch:",2, results.length);
		
	}
}
