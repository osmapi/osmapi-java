package pl.execon.osmapi.util;

import java.lang.reflect.Type;

import pl.execon.osmapi.dto.Geometry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class GeometryDeserializer implements JsonDeserializer<Geometry> {
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
