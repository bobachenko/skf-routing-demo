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
	private long calculationTime;
	
	public Route() {
	}
	
	public Route(List<Path> paths, double distance) {
		this.paths = paths;
		this.distance = distance;
	}	
	
	public Route(List<Path> paths, double distance, long calculationTime) {
		this.paths = paths;
		this.distance = distance;
		this.calculationTime = calculationTime;
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
	public long getCalculationTime() {
		return calculationTime;
	}

	public void setCalculationTime(long calculationTime) {
		this.calculationTime = calculationTime;
	}	
	
}
