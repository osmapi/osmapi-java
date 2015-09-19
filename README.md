# About
OSMAPI Java is a java library to interact with OpenStreetMap based data. It includes fast address searching, reverse geocoding, routing and OSM data manipulation.

The API may be used in java applications as well as with Android apps.

# Documentation
See the Wiki's [API documentation](https://github.com/alkeicam/osmapi-java/wiki/Java-API)

# Android dependencies
For use with Android based apps please use following dependencies:

```ruby
compile 'com.google.code.gson:gson:2.3.1'
    compile group: 'org.apache.httpcomponents' , name: 'httpclient-android' , version: '4.3.5.1'
    compile('org.simpleframework:simple-xml:2.7.+'){
        exclude module: 'stax'
        exclude module: 'stax-api'
        exclude module: 'xpp3'
    }
```


