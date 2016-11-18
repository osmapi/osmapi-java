package pl.execon.osmapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import pl.execon.osmapi.dto.FeatureCollection;
import pl.execon.osmapi.dto.Geometry;
import pl.execon.osmapi.dto.SearchAPIPlace;
import pl.execon.osmapi.dto.SearchAPIQuery;
import pl.execon.osmapi.endpoint.GenericEndpoint;
import pl.execon.osmapi.util.GeometryDeserializer;
import pl.execon.osmapi.util.Preferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SearchAPI {
	private Boolean useHTTPS;
	
	private GenericEndpoint endpoint;	
	
	public SearchAPI(Boolean useHTTPS){
		endpoint = new GenericEndpoint();
		this.useHTTPS = useHTTPS;
	}
	
	/**
	 * Finds addresses matching given query 
	 * @param query
	 * @return result or null 
	 */
	public FeatureCollection findAddress(SearchAPIQuery query){
		FeatureCollection result = null;
		
		String servicePath = Preferences.ENDPOINT_SEARCH_API_BASE_URL;
		
		String queryPart = "";
		
		if(query.getSearchString()!=null){
			try {
				queryPart += "query=";
				queryPart += URLEncoder.encode(query.getSearchString(),Preferences.ENCODING);
			} catch (UnsupportedEncodingException e) {	
				System.err.println("Error finding address due to:"+e.getMessage());				
			}
		}
		servicePath+="?";
		servicePath+=queryPart;
		
		String url = GenericEndpoint.generateURL(servicePath, useHTTPS);
		
		String response = endpoint.requestURL(url);
		
		if(response!=null){
			Gson gson = new GsonBuilder().registerTypeAdapter(Geometry.class, new GeometryDeserializer()).create();
			result = gson.fromJson(response, FeatureCollection.class);
		}
		
		
		return result;
	}

	public GenericEndpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(GenericEndpoint endpoint) {
		this.endpoint = endpoint;
	}
	
	
}
