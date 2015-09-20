package pl.execon.osmapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.execon.osmapi.dto.Feature;
import pl.execon.osmapi.dto.FeatureCollection;
import pl.execon.osmapi.dto.FeatureImage;
import pl.execon.osmapi.dto.PlacesAPIQuery;
import pl.execon.osmapi.endpoint.GenericEndpoint;
import pl.execon.osmapi.util.Preferences;


@RunWith(MockitoJUnitRunner.class)
public class PlacesAPITest {
	@InjectMocks
	PlacesAPI placesAPI = new PlacesAPI();
	
	PlacesAPI placesAPIOnline = new PlacesAPI();
	
	@Mock
	GenericEndpoint endpoint;
	
	
	@Before
	public void setUp() throws IOException, URISyntaxException{
		String place1_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/PlacesAPI_Response_1").toURI())));
		String place2_Response = new String(Files.readAllBytes(Paths.get(getClass().getResource("/PlacesAPI_Response_2").toURI())));
		
		byte[] place3_Response = Files.readAllBytes(Paths.get(getClass().getResource("/PlacesAPI_Response_3.png").toURI()));
		
		when(endpoint.requestURL("http://"+Preferences.ENDPOINT_PLACES_API_BASE_URL+"?query=53.22054,21.87315")).thenReturn(place1_Response);
		when(endpoint.requestURL("http://"+Preferences.ENDPOINT_PLACES_API_BASE_URL+"/box?l=52.22733,21.00427&u=52.22815,21.00539")).thenReturn(place2_Response);
		when(endpoint.downloadURL("http://"+Preferences.ENDPOINT_WEBSNAP_API_BASE_URL+"?u=P&w=300&h=225")).thenReturn(place3_Response);
		
	}
	
	@Test
	public void simplePlacesQuery(){		
		
		PlacesAPIQuery query = QueryFactory.createPlacesQuery(53.22054,21.87315);
		FeatureCollection results = placesAPI.queryPlace(query);
		assertEquals("Single geometry latitude mismatch", 53.220543, results.getFeatures()[0].getGeometry().getCoordinates()[0][0],0.00001);
		assertEquals("Single geometry latitude mismatch", 21.873286, results.getFeatures()[0].getGeometry().getCoordinates()[0][1],0.00001);
		assertEquals("Coordinates array size mismatch", 5,results.getFeatures()[3].getGeometry().getCoordinates().length);
	}	
	
	@Test
	public void simplePlacesQueryOnline(){		
		
		PlacesAPIQuery query = QueryFactory.createPlacesQuery(53.22054,21.87315);
		FeatureCollection results = placesAPIOnline.queryPlace(query);
		assertEquals("Single geometry latitude mismatch", 53.220543, results.getFeatures()[0].getGeometry().getCoordinates()[0][0],0.00001);
		assertEquals("Single geometry latitude mismatch", 21.873286, results.getFeatures()[0].getGeometry().getCoordinates()[0][1],0.00001);
		assertEquals("Coordinates array size mismatch", 5,results.getFeatures()[3].getGeometry().getCoordinates().length);
	}
	
	
	@Test
	public void boxPlacesQuery(){				
		PlacesAPIQuery query = QueryFactory.createPlacesQuery(52.22733,21.00427, 52.22815,21.00539);
		FeatureCollection results = placesAPI.queryPlace(query);
		assertTrue("Features list is empty:", results.getFeatures().length>1);
		assertTrue("Type mismatch:", results.getFeatures()[0].getProperties().getMatchType().equalsIgnoreCase("exact"));
		assertTrue("Type mismatch:", results.getFeatures()[results.getFeatures().length-1].getProperties().getMatchType().equalsIgnoreCase("exact"));		
	}	
	
	@Test
	public void boxPlacesQueryOnline(){
		PlacesAPIQuery query = QueryFactory.createPlacesQuery(52.22733,21.00427, 52.22815,21.00539);
		FeatureCollection results = placesAPIOnline.queryPlace(query);
		assertTrue("Features list is empty:", results.getFeatures().length>1);
		assertTrue("Type mismatch:", results.getFeatures()[0].getProperties().getMatchType().equalsIgnoreCase("exact"));
		assertTrue("Type mismatch:", results.getFeatures()[results.getFeatures().length-1].getProperties().getMatchType().equalsIgnoreCase("exact"));		
	}
	
	@Test
	public void featureImageQuery(){
		PlacesAPIQuery query = QueryFactory.createPlacesQuery(53.22054,21.87315);
		FeatureCollection results = placesAPI.queryPlace(query);
		FeatureImage image = placesAPI.imageForFeature(results.getFeatures()[0]);
		assertNotNull(image);
		assertEquals("Width mismatch", 300,image.getWidth());
		assertEquals("Height mismatch", 225,image.getHeight());
		assertEquals("Content type mismatch", "image/png",image.getContentType());
		assertEquals("Size mismatch", 18227,image.getImageBytes().length);		
	}
	
	@Test
	public void featureImageQueryEmpty(){
		PlacesAPIQuery query = QueryFactory.createPlacesQuery(53.22054,21.87315);
		FeatureCollection results = placesAPI.queryPlace(query);
		Feature feature = results.getFeatures()[0];
		feature.getProperties().setDetailsLink(null);
		FeatureImage image = placesAPI.imageForFeature(feature);
		assertNull(image);
				
	}
	
	@Test
	public void featureImageQueryOnline(){
		PlacesAPIQuery query = QueryFactory.createPlacesQuery(53.22054,21.87315);
		FeatureCollection results = placesAPI.queryPlace(query);
		
		Feature feature = results.getFeatures()[0];
		feature.getProperties().setDetailsLink("http://www.natemat.pl");
		FeatureImage image = placesAPIOnline.imageForFeature(feature);
		assertNotNull(image);
		assertEquals("Width mismatch", 300,image.getWidth());
		assertEquals("Height mismatch", 225,image.getHeight());
		assertEquals("Content type mismatch", "image/png",image.getContentType());
		assertTrue("Size mismatch", image.getImageBytes().length>10000);		
	}
}
