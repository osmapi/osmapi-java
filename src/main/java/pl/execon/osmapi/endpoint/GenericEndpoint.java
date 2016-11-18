package pl.execon.osmapi.endpoint;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import pl.execon.osmapi.util.Preferences;


public class GenericEndpoint {
	
	private final static String HTTP = "http://";
	private final static String HTTPS = "https://";
	
	/**
	 * Adds base URL and handles protocol selection.
	 * @param servicePath relative path to service i.e. /search?query=asdasdasd
	 * @param useHTTPS
	 * @return
	 */
	public static String generateURL(String servicePath, Boolean useHTTPS){
		String resultURL = HTTP;
		if(useHTTPS){
			resultURL = HTTPS;
		}
		resultURL+=Preferences.ENDPOINT_BASE_URL;
		resultURL+=servicePath;
		
		return resultURL;
	}
	
	/**
	 * Sends GET request to url and returns string representation of response body
	 * @param url to send GET request
	 * @return resposne or null when something went wrong
	 */
	public String requestURL(String url){
		HttpClient client = new DefaultHttpClient();
		
		return requestURLInternal(url, client);			
	}
	
	/**
	 * Sends GET request to url and returns string representation of response body with HTTP Basic Auth
	 * @param url to send GET request
	 * @return resposne or null when something went wrong
	 */
	public String requestURLWithBasicAuth(String url, String login, String password){
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(login, password));		
		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
		
		return requestURLInternal(url, client);
					
	}
	
	private String requestURLInternal(String url, HttpClient client){
		HttpGet request = new HttpGet(url);
		request.addHeader("User-Agent", Preferences.NAME+"_"+Preferences.VERSION);

		String result = null;
		
		try {
			HttpResponse response = client.execute(request);
			if(response.getEntity()!=null)
				result = EntityUtils.toString(response.getEntity(), Preferences.ENCODING);			
		} catch (IOException e) {
			System.err.println("Error requesting URL: "+url+" due to:"+e.getMessage());
		}		
		return result;
	}
	
	/**
	 * Sends PUT request to url and returns string representation of response body
	 * @param url to send GET request
	 * @return resposne or null when something went wrong
	 */
	public String requestURLWithPUT(String url, String body){
		HttpClient client = new DefaultHttpClient();
		
		return requestURLWithPUTInternal(url, body, client);			
	}
	
	public String requestURLWithPUTWithBasicAuth(String url, String body, String login, String password){
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(login, password));		
		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
		
		return requestURLWithPUTInternal(url, body, client);
					
	}
	
	public String requestURLWithPUTWithBasicAuth(String url, String login, String password){
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(login, password));		
		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
		
		return requestURLWithPUTInternal(url, null, client);
					
	}
	
	private String requestURLWithPUTInternal(String url,String body, HttpClient client){
		
		
		String result = null;		
		try {
			HttpPut request = new HttpPut(url);
			request.addHeader("User-Agent", Preferences.NAME+"_"+Preferences.VERSION);
			if(body!=null){
				request.setEntity(new StringEntity(body));
			}
			HttpResponse response = client.execute(request);
			if(response.getEntity()!=null&&response.getStatusLine().getStatusCode()==200)
				result = EntityUtils.toString(response.getEntity(), Preferences.ENCODING);			
		} catch (IOException e) {
			System.err.println("Error requesting URL: "+url+" due to:"+e.getMessage());
		}		
		return result;
	}
	
	/**
	 * Download binary content from given url
	 * @param url
	 * @return content returned from URL or null when something went wrong
	 */
	public byte[] downloadURL(String url){
		HttpClient client = new DefaultHttpClient();
		
		HttpGet request = new HttpGet(url);
		request.addHeader("User-Agent", Preferences.NAME+"_"+Preferences.VERSION);
		
		byte[] result = null;
		
		try {
			HttpResponse response = client.execute(request);
			if(response.getEntity()!=null)
				result = EntityUtils.toByteArray(response.getEntity());			
		} catch (IOException e) {
			System.err.println("Error downloading URL: "+url+" due to:"+e.getMessage());
		}
		
		return result;
	}
}
