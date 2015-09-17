package pl.execon.osmapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import pl.execon.osmapi.dto.SearchAPIPlace;
import pl.execon.osmapi.dto.SearchAPIQuery;
import pl.execon.osmapi.endpoint.GenericEndpoint;
import pl.execon.osmapi.util.Settings;

public class SearchAPI {
	
	private GenericEndpoint endpoint;	
	public SearchAPI(){
		endpoint = new GenericEndpoint();
	}
	
	/**
	 * Finds addresses matching given query 
	 * @param query
	 * @return
	 */
	public SearchAPIPlace[] findAddress(SearchAPIQuery query){
				
		String queryPart = "";
		
		if(query.getSearchString()!=null){
			try {
				queryPart += "query=";
				queryPart += URLEncoder.encode(query.getSearchString(),Settings.ENCODING);
			} catch (UnsupportedEncodingException e) {	
				System.err.println("Error finding address due to:"+e.getMessage());				
			}
		}
		
		String url = "http://"+Settings.ENDPOINT_SEARCH_API_BASE_URL+"?"+queryPart;
		
		String response = endpoint.requestURL(url);
		SearchAPIPlace[] result = new Gson().fromJson(response, SearchAPIPlace[].class);
		
		return result;
	}

	public GenericEndpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(GenericEndpoint endpoint) {
		this.endpoint = endpoint;
	}
	
	
}
