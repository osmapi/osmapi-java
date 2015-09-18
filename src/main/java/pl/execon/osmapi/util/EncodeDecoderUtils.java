package pl.execon.osmapi.util;

import java.io.StringWriter;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import pl.execon.osmapi.dto.osm.OSMElement;

public class EncodeDecoderUtils {
	/**
	 * Converts OSMElement object into its xml representation in accordance with
	 * OSM api specification
	 * @param osmElement element to be converted into string xml
	 * @return null on error, string representation of xml on success
	 */
	public static String toXML(OSMElement osmElement){
		String stringRepresentation = null;
		try {
			Serializer xmlMapper = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));
			StringWriter stringWriter = new StringWriter();
			xmlMapper.write(osmElement, stringWriter);
			stringRepresentation = stringWriter.toString();
		} catch (Exception e) {
			System.err.println("Error while serializing element: "+e.getMessage());
		}
		return stringRepresentation;
	}
	
	/**
	 * Converts string xml into OSMElement object instance
	 * @param xml string xml to be transformed into element object
	 * @return null on error, element instance on success
	 */
	public static OSMElement fromXML(String xml){
		OSMElement osmElement = null;
		try {
			Serializer xmlMapper = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));
			osmElement = xmlMapper.read(OSMElement.class, xml);
			
		} catch (Exception e) {
			System.err.println("Error while serializing element: "+e.getMessage());
		}
		return osmElement;
	}
}
