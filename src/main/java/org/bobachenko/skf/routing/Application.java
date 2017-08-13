package org.bobachenko.skf.routing;

import static spark.Spark.*;

import org.bobachenko.skf.routing.controller.Controller;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Application class
 * @author Maxim Bobachenko
 */
public class Application {	
	
	private static Injector di = Guice.createInjector(new DiConfigurator());
	private Gson gson = new Gson();
	
	@Inject
	private Controller controller;
	
	public Application() {
		initServer();
		makeResources();
	}

	private void initServer() {		
		port(8888);
		staticFileLocation("/static-content");		
	}
	
	private void makeResources() {
		// set resources
		get("/api/points", (req, res) -> controller.getPoints(req, res), gson::toJson);
		get("/api/route/:ids", (req, res) -> controller.getRoute(req, res), gson::toJson);
		
		// set filter for each request to api
		after("/api/*", (request, response) -> {
			response.type("application/json");
		});
	}
	
	public static void main(String[] args) {		
		di.getInstance(Application.class);
	}
}