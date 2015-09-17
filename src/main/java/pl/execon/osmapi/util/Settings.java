package pl.execon.osmapi.util;

public class Settings {
	public final static String VERSION = "v1.0";
	public final static String ENDPOINT_SEARCH_API_BASE_URL = "osmapi.execon.pl/search/address";
	public final static String ENDPOINT_ROUTING_API_BASE_URL = "osmapi.execon.pl/routing/viaroute";
	public final static String ENDPOINT_PLACES_API_BASE_URL = "osmapi.execon.pl:8085/places";
	public final static String ENDPOINT_WEBSNAP_API_BASE_URL = "websnap.execon.pl/c.php";
	
	public final static int WEBSNAP_DEFAULT_WIDTH = 300; // pixels
	public final static int WEBSNAP_DEFAULT_HEIGHT = 225; // pixels
	public final static String WEBSNAP_DEFAULT_CONTENT_TYPE = "image/png";
	
	public final static String NAME = "OSMAPIJavaClient";
	public final static String ENCODING = "UTF-8";
	public static final String ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL = "http://api06.dev.openstreetmap.org/api/0.6"; // DEVELOPMENT
	public static final String DEVELOPMENT_OSM_CREDENTIALS_LOGIN = "maciej.grula@gmail.com"; // DEVELOPMENT
	public static final String DEVELOPMENT_OSM_CREDENTIALS_PASS = "a2d5n6M3"; // DEVELOPMENT
	//public static final String ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL = "https://api.openstreetmap.org/api/0.6"; // PRODUCTION
	public static final String ENDPOINT_OSM_API_V06_DEFAULT_COMMENT = "Created by OSMAPI call"; // DEVELOPMENT
	
}
