package org.bobachenko.skf.routing.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bobachenko.skf.routing.model.Path;
import org.bobachenko.skf.routing.model.Point;
import org.bobachenko.skf.routing.model.RouteProcessor;
import org.bobachenko.skf.routing.repo.Repository;

import com.google.inject.Inject;

import spark.Request;
import spark.Response;

/**
 * Controller implementation.
 * 
 * @author Maxim Bobachenko
 */
public class ControllerImpl implements Controller {
	
	@Inject
	private Repository repo;
	
	@Inject
	private RouteProcessor processor;

	@Override
	public List<Point> getPoints(Request req, Response res) {		
        return repo.getPoints();
	}
	
	@Override
	public List<Path> getRoute(Request req, Response res) {
		String[] idListStr = req.params(":ids").split(",");	
		List<Long> ids = Arrays.stream(idListStr).map(s -> Long.valueOf(s)).collect(Collectors.toList());
        return processor.makeRoute(ids);
	}
}