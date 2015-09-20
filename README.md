# About
OSMAPI Java is a java library to interact with OpenStreetMap based data. It includes fast address searching, reverse geocoding, routing and OSM data manipulation.

The API may be used in java applications as well as with Android apps.

# Usage
To find matching addresses starting with "wars" use:
```java
SearchAPIQuery query = QueryFactory.createSearchQuery("wars");
SearchAPIPlace[] results = searchAPI.findAddress(query);
```

To find a route between two places use:
```java
RoutingAPIQuery query = QueryFactory.createRoutingQuery(52.8709827, 20.6171934, 52.5354061, 19.715046);
RoutingAPIPath path = routingAPI.findPath(query, Locale.getDefault());
```

To find a place matching given location use:
```java
PlacesAPIQuery query = QueryFactory.createPlacesQuery(53.22054,21.87315);
FeatureCollection results = placesAPI.queryPlace(query);
```

To get all places in given bounding box use:
```java
PlacesAPIQuery query = QueryFactory.createPlacesQuery(52.22733,21.00427, 52.22815,21.00539);
FeatureCollection results = placesAPI.queryPlace(query);
```

To get an image for a place use:
```java
PlacesAPIQuery query = QueryFactory.createPlacesQuery(53.22054,21.87315);
FeatureCollection results = placesAPI.queryPlace(query);
FeatureImage image = placesAPI.imageForFeature(results.getFeatures()[0]);
```


# Documentation
See the Wiki's [API documentation](https://github.com/osmapi/osmapi-java/wiki/Java-API)

#Java installation
Please download [java-osmapi-1.0.0-RELEASE.jar](https://github.com/osmapi/osmapi-java/releases/download/v1.0.1/java-osmapi-1.0.0-RELEASE.jar) into your project libs folder and add following pom dependencies:

```ruby
		<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpclient</artifactId>
			<version>${httpclient.version}</version>
		</dependency>
		
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>${gson.version}</version>
		</dependency>
		
		<dependency>
			<groupId>org.simpleframework</groupId>
			<artifactId>simple-xml</artifactId>
			<version>${simplexml.version}</version>
		</dependency>
	
```

# Android installation
For use with Android based apps please download [java-osmapi-1.0.0-RELEASE.jar] (https://github.com/osmapi/osmapi-java/releases/download/v1.0.1/java-osmapi-1.0.0-RELEASE.jar) into your project libs folder and add following Gradle dependencies:

```ruby
	compile 'com.google.code.gson:gson:2.3.1'
    compile group: 'org.apache.httpcomponents' , name: 'httpclient-android' , version: '4.3.5.1'
    compile('org.simpleframework:simple-xml:2.7.+'){
        exclude module: 'stax'
        exclude module: 'stax-api'
        exclude module: 'xpp3'
    }
```
# Maven
Coming soon - we are working on putting osmapi-java into Maven Central repository.

