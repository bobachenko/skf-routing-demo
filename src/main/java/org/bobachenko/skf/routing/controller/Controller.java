package org.bobachenko.skf.routing.controller;

import java.util.List;
import org.bobachenko.skf.routing.model.Point;
import org.bobachenko.skf.routing.model.Route;

import spark.Request;
import spark.Response;

/**
 * Abstract controller
 * @author Maxim Bobachenko
 */
public interface Controller {
	List<Point> getPoints(Request req, Response res);
	Route getRoute(Request req, Response res);
}
