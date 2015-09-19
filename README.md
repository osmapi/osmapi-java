# About
OSMAPI Java is a java library to interact with OpenStreetMap based data. It includes fast address searching, reverse geocoding, routing and OSM data manipulation.

The API may be used in java applications as well as with Android apps.

# Documentation
See the Wiki's [API documentation](https://github.com/alkeicam/osmapi-java/wiki/Java-API)

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


