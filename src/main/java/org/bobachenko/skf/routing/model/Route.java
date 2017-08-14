package org.bobachenko.skf.routing.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Route. Result after calculation.
 * @author Maxim Bobachenko
 */
public class Route {
	private List<Path> paths = new LinkedList<>();
	private double distance;
	
	public Route() {
	}
	
	public Route(List<Path> paths, double distance) {
		this.paths = paths;
		this.distance = distance;
	}
	
	public List<Path> getPaths() {
		return paths;
	}
	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}	
}
