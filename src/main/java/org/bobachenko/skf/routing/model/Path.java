package org.bobachenko.skf.routing.model;

/**
 * Represents path between two points. Denormalized data structure. 
 * This one should be optimized in real application.
 * 
 * @author Maxim Bobachenko
 */
public class Path extends Entity {
	
	public Path(long id, long from, String fromName, long to, String toName, double distance, String path) {
		super(id);
		this.from = from;
		this.to = to;
		this.distance = distance;
		this.path = path;
		this.fromName = fromName;
		this.toName = toName;
	}

	private long from;
	private long to;
	private String fromName;
	private String toName;
	private double distance;
	private String path;
	
	public long getFrom() {
		return from;
	}
	public void setFrom(long from) {
		this.from = from;
	}
	public long getTo() {
		return to;
	}
	public void setTo(long to) {
		this.to = to;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}		
}
