package pl.execon.osmapi;

import java.lang.reflect.Type;

import pl.execon.osmapi.dto.Feature;
import pl.execon.osmapi.dto.FeatureCollection;
import pl.execon.osmapi.dto.FeatureImage;
import pl.execon.osmapi.dto.Geometry;
import pl.execon.osmapi.dto.PlacesAPIQuery;
import pl.execon.osmapi.endpoint.GenericEndpoint;
import pl.execon.osmapi.util.Preferences;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Provides reverse geocoding facilities
 * @author grulka
 *
 */
public class PlacesAPI {
	private GenericEndpoint endpoint;
	
	public PlacesAPI(){
		endpoint = new GenericEndpoint();
	}
	
	/**
	 * Returns reverse geocoded representation of place provided in query
	 * Followin types of results may be provided : 
	 * exact - this is the most exact match according to the algorithm
	 * within - objects that encapsulate the place
	 * near - objects that are close to the place
	 * 
	 * When in <em>box</em> mode then no within results will be provided.
	 * @param query
	 * @return list of geojson features matching given place provided in query
	 */
	public FeatureCollection queryPlace(PlacesAPIQuery query){
		String url = "http://"+Preferences.ENDPOINT_PLACES_API_BASE_URL;
						
		if(query.getBoundingBox()!=null){
			url += "/box?";
			url += "l="+query.getBoundingBox()[0].getLatitude()+","+query.getBoundingBox()[0].getLongitude();
			url += "&";
			url += "u="+query.getBoundingBox()[1].getLatitude()+","+query.getBoundingBox()[1].getLongitude();
		}else{
			url += "?";
			url += "query="+query.getLocation().getLatitude()+","+query.getLocation().getLongitude();
			url += query.getNear()!=null? "&near="+query.getNear().intValue():"";
		}
		
		String response = endpoint.requestURL(url);
		Gson gson = new GsonBuilder().registerTypeAdapter(Geometry.class, new GeometryDeserializer()).create();
				
					
		FeatureCollection features = gson.fromJson(response, FeatureCollection.class);
		
		return features;
	}
	
	/**
	 * Returns image representation of the feature. Please bear in mind that this method may take
	 * some time to generate image for feature.
	 * @param feature
	 * @return
	 */
	public FeatureImage imageForFeature(Feature feature){
		FeatureImage featureImage = null;
		
		if(feature.getProperties().getDetailsLink()==null||feature.getProperties().getDetailsLink().isEmpty()){
			return featureImage;
		}
		
		String url = "http://"+Preferences.ENDPOINT_WEBSNAP_API_BASE_URL;
		url += "?u="+feature.getProperties().getDetailsLink();
		url += "&w="+Preferences.WEBSNAP_DEFAULT_WIDTH;
		url += "&h="+Preferences.WEBSNAP_DEFAULT_HEIGHT;
		
		byte[] image = endpoint.downloadURL(url);
		if(image!=null){
			featureImage = new FeatureImage();
			
			featureImage.setWidth(Preferences.WEBSNAP_DEFAULT_WIDTH);
			featureImage.setHeight(Preferences.WEBSNAP_DEFAULT_HEIGHT);
			featureImage.setContentType(Preferences.WEBSNAP_DEFAULT_CONTENT_TYPE);
			featureImage.setImageBytes(image);
		}
		
		return featureImage;			
	}

	public GenericEndpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(GenericEndpoint endpoint) {
		this.endpoint = endpoint;
	}

	
	private class GeometryDeserializer implements JsonDeserializer<Geometry> {
		  public Geometry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
		      throws JsonParseException {
			  Geometry geometry = new Geometry();
			  
			  JsonObject geometryObject = json.getAsJsonObject();
			  JsonElement type = geometryObject.get("type");
			  geometry.setType(type.getAsString());
			  
			  JsonElement coordinatesJson = geometryObject.get("coordinates");
			  
			  if(type.getAsString().equalsIgnoreCase("Point")){
				  JsonArray array = coordinatesJson.getAsJsonArray();
				  double longitude = array.get(0).getAsDouble();
				  double latitude = array.get(1).getAsDouble();
				  double[][] coordinates = new double[1][2];
				  coordinates[0][0] = latitude;
				  coordinates[0][1] = longitude;
				  geometry.setCoordinates(coordinates);
			  }else{
				  JsonArray array = coordinatesJson.getAsJsonArray();
				  int coordinatesCount = array.size();
				  
				  double[][] coordinates = new double[coordinatesCount][2];
				  
				  for(int i=0;i<coordinatesCount;i++){
					JsonElement element = array.get(i);
					 double longitude = element.getAsJsonArray().get(0).getAsDouble();
					 double latitude = element.getAsJsonArray().get(1).getAsDouble();
					 
					 coordinates[i][0] = latitude;
					 coordinates[i][1] = longitude;
				  }
				  geometry.setCoordinates(coordinates);				  
			  }
			  return geometry;
		  }
		}
	
}

