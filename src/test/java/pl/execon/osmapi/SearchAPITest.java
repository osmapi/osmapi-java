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
import pl.execon.osmapi.dto.FeatureCollection;
import pl.execon.osmapi.dto.SearchAPIPlace;
import pl.execon.osmapi.dto.SearchAPIPlaceType;
import pl.execon.osmapi.dto.SearchAPIQuery;
import pl.execon.osmapi.endpoint.GenericEndpoint;
import pl.execon.osmapi.util.Preferences;

@RunWith(MockitoJUnitRunner.class)
public class SearchAPITest {
	@InjectMocks
	SearchAPI searchAPIHTTPS = new SearchAPI(true);
	@InjectMocks
	SearchAPI searchAPIHTTP = new SearchAPI(false);
	
	SearchAPI searchAPIOnline = new SearchAPI(true);
	
	
	@Mock
	GenericEndpoint endpoint;
	
	
	@Before
	public void setUp() throws IOException, URISyntaxException{
		String ziel_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/SearchAPI_Response_Ziel").toURI())));
		String ziel_Dzien_53_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/SearchAPI_Response_Ziel_Dzien_53").toURI())));
		String ziel_utf8_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/SearchAPI_Response_utf8").toURI())));
		
		;
		
		when(endpoint.requestURL(GenericEndpoint.generateURL(Preferences.ENDPOINT_SEARCH_API_BASE_URL, true)+"?query=ziel")).thenReturn(ziel_Response);
		when(endpoint.requestURL(GenericEndpoint.generateURL(Preferences.ENDPOINT_SEARCH_API_BASE_URL, false)+"?query=ziel")).thenReturn(ziel_Response);
		when(endpoint.requestURL(GenericEndpoint.generateURL(Preferences.ENDPOINT_SEARCH_API_BASE_URL, true)+"?query=dziennikarska+53")).thenReturn(ziel_Dzien_53_Response);
		when(endpoint.requestURL(GenericEndpoint.generateURL(Preferences.ENDPOINT_SEARCH_API_BASE_URL, true)+"?query=z%C4%85bki+wolno%C5%9Bci")).thenReturn(ziel_utf8_Response);
		
		
	}
	
	@Test
	public void httpsSearch(){		
		
		SearchAPIQuery query = QueryFactory.createSearchQuery("ziel");
		FeatureCollection searchResult = searchAPIHTTPS.findAddress(query);
		assertNotNull(searchResult);				
	}
	
	@Test
	public void httpSearch(){		
		
		SearchAPIQuery query = QueryFactory.createSearchQuery("ziel");
		FeatureCollection searchResult = searchAPIHTTP.findAddress(query);
		assertNotNull(searchResult);				
	}
	

	
	@Test
	public void httpsEndpoint(){		
		GenericEndpoint endpoint = new GenericEndpoint();
		String httpsURL = endpoint.generateURL("/test", true);
		assertEquals("https://"+Preferences.ENDPOINT_BASE_URL+"/test", httpsURL);				
	}
	
	@Test
	public void httpEndpoint(){		
		GenericEndpoint endpoint = new GenericEndpoint();
		String httpURL = endpoint.generateURL("/test", false);
		assertEquals("http://"+Preferences.ENDPOINT_BASE_URL+"/test", httpURL);				
	}
	
	
	
	@Test
	public void simpleQuery(){		
		SearchAPIQuery query = QueryFactory.createSearchQuery("ziel");
		
		FeatureCollection results = searchAPIHTTPS.findAddress(query);
		assertEquals("Result size mismatch:",14, results.getFeatures().length);
		assertEquals("Type mismatch: ",SearchAPIPlaceType.C,SearchAPIPlaceType.C.valueOf(results.getFeatures()[0].getProperties().getType()));
		assertEquals("Type mismatch: ",SearchAPIPlaceType.S,SearchAPIPlaceType.S.valueOf(results.getFeatures()[12].getProperties().getType()));
				
	}
	
//	@Test
//	public void simpleQueryOnline(){		
//		SearchAPIQuery query = QueryFactory.createSearchQuery("ziel");
//		FeatureCollection results = searchAPIOnline.findAddress(query);
//		assertEquals("Result size mismatch:",14, results.getFeatures().length);
//		assertEquals("Type mismatch: ",SearchAPIPlaceType.C,results.getFeatures()[0].getType());
//		assertEquals("Type mismatch: ",SearchAPIPlaceType.S,results.getFeatures()[12].getType());
//				
//	}
	
	@Test
	public void preciseQuery(){
		SearchAPIQuery query = QueryFactory.createSearchQuery("dziennikarska 53");
		FeatureCollection results = searchAPIHTTPS.findAddress(query);
		assertEquals("Result size mismatch:",6, results.getFeatures().length);
	}
	
//	@Test
//	public void preciseQueryOnline(){
//		SearchAPIQuery query = QueryFactory.createSearchQuery("zielonka dziennikarska 53");
//		FeatureCollection results = searchAPIOnline.findAddress(query);
//		assertEquals("Result size mismatch:",6, results.getFeatures().length);
//	}
	
	@Test
	public void utf8Query(){
		SearchAPIQuery query = QueryFactory.createSearchQuery("ząbki wolności");
		FeatureCollection results = searchAPIHTTPS.findAddress(query);
		assertEquals("Result size mismatch:",14, results.getFeatures().length);
		
	}
	
//	@Test
//	public void utf8QueryOnline(){
//		SearchAPIQuery query = QueryFactory.createSearchQuery("ząbki wolności");
//		FeatureCollection results = searchAPIOnline.findAddress(query);
//		assertEquals("Result size mismatch:",2, results.getFeatures().length);
//		
//	}
}
