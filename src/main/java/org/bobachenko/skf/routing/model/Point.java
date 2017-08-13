package org.bobachenko.skf.routing.model;

/**
 * Entity describes store's or customer's location.
 *  
 * @author Maxim Bobachenko
 */
public class Point extends Entity {

	private String name;
	private Double lon;
	private Double lat;
	private boolean store;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public boolean isStore() {
		return store;
	}
	public void setStore(boolean store) {
		this.store = store;
	}	
	
	public Point(long id, String name, Double lat, Double lon, boolean store) {
		super(id);
		this.name = name;
		this.lon = lon;
		this.lat = lat;
		this.store = store;
	}
	
	
}
