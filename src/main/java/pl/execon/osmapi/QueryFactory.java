package pl.execon.osmapi;

import pl.execon.osmapi.dto.PlacesAPIQuery;
import pl.execon.osmapi.dto.Point;
import pl.execon.osmapi.dto.RoutingAPIQuery;
import pl.execon.osmapi.dto.SearchAPIQuery;

/**
 * Quick generator of most popular osmapi queries 
 * @author grulka
 *
 */
public class QueryFactory {
	
	/**
	 * Query for locations matching search string
	 * @param searchString
	 * @return
	 */
	public static SearchAPIQuery createSearchQuery(String searchString){
		SearchAPIQuery query = new SearchAPIQuery();
		query.setSearchString(searchString);
		return query;
	}
	
	/**
	 * Query for locations matching search string, results are checked only within <i>range</i> of the location provided
	 * @param 
	 * @return
	 */
	public static SearchAPIQuery createSearchQuery(String searchString, double longitude, double latitude, int range){
		SearchAPIQuery query = new SearchAPIQuery();
		query.setSearchString(searchString);
		query.setLat(latitude);
		query.setLon(longitude);
		query.setRange(range);
		return query;
	}
	
	/**
	 * Query for navigation path (without manouver instructions) 
	 * @param latitudeFrom departure
	 * @param longitudeFrom departure
	 * @param latitudeTo arrival
	 * @param longitudeTo arrival
	 * @return
	 */
	public static RoutingAPIQuery createRoutingQuery(double latitudeFrom, double longitudeFrom, double  latitudeTo, double longitudeTo){
		RoutingAPIQuery query = new RoutingAPIQuery();
		Point from = new Point(latitudeFrom, longitudeFrom);
		Point to = new Point(latitudeTo, longitudeTo);
		Point[] points = new Point[2];
		points[0] = from;
		points[1] = to;
		query.setViaPoints(points);
		query.setWithInstructions(false);
		
		return query;
	}
	
	/**
	 * Query for navigation path with manouver instrucions included
	 * @param latitudeFrom departure
	 * @param longitudeFrom departure
	 * @param latitudeTo arrival
	 * @param longitudeTo arrival
	 * @return
	 */
	public static RoutingAPIQuery createRoutingQueryWithInstructions(double latitudeFrom, double longitudeFrom, double  latitudeTo, double longitudeTo){
		RoutingAPIQuery query = new RoutingAPIQuery();
		Point from = new Point(latitudeFrom, longitudeFrom);
		Point to = new Point(latitudeTo, longitudeTo);
		Point[] points = new Point[2];
		points[0] = from;
		points[1] = to;
		query.setViaPoints(points);
		query.setWithInstructions(true);
		
		return query;
	}
	
	/**
	 * Query for reverse geocoding of a place
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static PlacesAPIQuery createPlacesQuery(double latitude, double longitude){
		PlacesAPIQuery query = new PlacesAPIQuery();
		Point point = new Point(latitude,longitude);		
		query.setLocation(point);
		return query;
	}
	/**
	 * Query for reverse geocoding of a place with limited number of results
	 * @param latitude
	 * @param longitude
	 * @param near
	 * @return
	 */
	public static PlacesAPIQuery createPlacesQuery(double latitude, double longitude, int near){
		PlacesAPIQuery query = new PlacesAPIQuery();
		Point point = new Point(latitude,longitude);		
		query.setLocation(point);
		query.setNear(near);
		return query;
	}
	
	/**
	 * Query for selecting places in given bounding box
	 * @param boxLowerLatitude
	 * @param boxLowerLongitude
	 * @param boxUpperLatitude
	 * @param boxUpperLongitude
	 * @return
	 */
	public static PlacesAPIQuery createPlacesQuery(double boxLowerLatitude, double boxLowerLongitude, double boxUpperLatitude, double boxUpperLongitude){
		PlacesAPIQuery query = new PlacesAPIQuery();
		Point lower = new Point(boxLowerLatitude, boxLowerLongitude);
		Point upper = new Point(boxUpperLatitude, boxUpperLongitude);
		
		Point[] bbox = new Point[]{lower, upper};		
		query.setBoundingBox(bbox);
		
		return query;
	}
	
}
