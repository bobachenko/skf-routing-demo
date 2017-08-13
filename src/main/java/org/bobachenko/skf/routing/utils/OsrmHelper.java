package org.bobachenko.skf.routing.utils;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility class to make OSRM-Server routing requests
 * 
 * @author Maxim Bobachenko
 */
public class OsrmHelper {
	
	private String serverUrl;
	private final String REQUEST_TEMPLATE = "%s/route/v1/driving/%s,%s;%s,%s?geometries=geojson";
	private static final String OK_STATUS = "Ok";
	
	public static class Route {
		private double distance;
		private List<Point.Double> points = new LinkedList<>();
		private String geometryJson;
		
		public double getDistance() {
			return distance;
		}
		public void setDistance(double distance) {
			this.distance = distance;
		}
		public List<Point.Double> getPoints() {
			return points;
		}
		public void setPoints(List<Point.Double> points) {
			this.points = points;
		}
		public String getGeometryJson() {
			return geometryJson;
		}
		public void setGeometryJson(String geometryJson) {
			this.geometryJson = geometryJson;
		}		
	}
	
	public OsrmHelper(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public Optional<Route> getRoute(double latFrom, double lonFrom, double latTo, double lonTo) {
		String jsonStr = null;
		try {
			
			jsonStr = sendRequest(latFrom, lonFrom, latTo, lonTo);
			return parseJson(jsonStr);
			
		} catch (Exception e) {
			throw new RuntimeException("Cannot get route!", e);
		}
	}

	private String sendRequest(double latFrom, double lonFrom, double latTo, double lonTo) throws IOException {
		StringBuilder resultStr = new StringBuilder();

		URL url = new URL(String.format(REQUEST_TEMPLATE, serverUrl, lonFrom, latFrom, lonTo, latTo));

		URLConnection connection = url.openConnection();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String line;
			while ((line = in.readLine()) != null)
				resultStr.append(line);
		}

		return resultStr.toString();
	}
	
	private Optional<Route> parseJson(String jsonStr) throws JSONException {
		Route result = null;
		JSONObject json = new JSONObject(jsonStr);

		if (!json.getString("code").equals(OK_STATUS))
			throw new RuntimeException("Wrong response code. " + json.getString("code"));
		
		result = new Route();		
		
		JSONArray routes = json.getJSONArray("routes");
		
		if(routes.length()==0)
			Optional.empty();
		
		result.setDistance(routes.getJSONObject(0).getDouble("distance"));

		JSONArray coords = routes.getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates");
		result.setGeometryJson(routes.getJSONObject(0).getJSONObject("geometry").getString("coordinates"));
		
		for(int i=0; i<coords.length();i++) {
			Point.Double coord = new Point.Double(coords.getJSONArray(i).getDouble(0), coords.getJSONArray(i).getDouble(1));
			result.getPoints().add(coord);
		}		

		return Optional.of(result);		
	}
}
