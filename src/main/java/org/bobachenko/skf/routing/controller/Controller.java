package org.bobachenko.skf.routing.controller;

import java.util.List;

import org.bobachenko.skf.routing.model.Path;
import org.bobachenko.skf.routing.model.Point;

import spark.Request;
import spark.Response;

/**
 * Abstract controller
 * @author Maxim Bobachenko
 */
public interface Controller {
	List<Point> getPoints(Request req, Response res);
	List<Path> getRoute(Request req, Response res);
}
