# About
OSMAPI Java is a java library to interact with OpenStreetMap based data. It includes fast address searching, reverse geocoding, routing and OSM data manipulation.

The API may be used in java applications as well as with Android apps.

# Documentation
See the Wiki's [API documentation](https://github.com/alkeicam/osmapi-java/wiki/Java-API)

# Android dependencies
For use with Android based apps please use following dependencies:

```ruby
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:22.1.1'
        
    compile files('libs/java-osmapi-1.0.0-RELEASE.jar')
    compile 'com.fasterxml.jackson.core:jackson-databind:2.5.3'
    compile 'org.codehaus.woodstox:woodstox-core-asl:4.1.4'
}
```


