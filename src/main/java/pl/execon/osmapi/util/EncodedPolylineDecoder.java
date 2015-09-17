package pl.execon.osmapi.util;

import java.util.ArrayList;
import java.util.List;

import pl.execon.osmapi.dto.Point;

public class EncodedPolylineDecoder {
	public static List<Point> decodePoly(String encoded, int decimalPlaces) {
		double precision = Math.pow(10d, -decimalPlaces);
		List<Point> poly = new ArrayList<Point>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;
			Point p = new Point(lat*precision, lng*precision);			
			poly.add(p);
		}

		return poly;
	}
}
