package org.bobachenko.skf.routing.model;

import java.util.List;
import org.bobachenko.skf.routing.repo.Repository;


/**
 * Class for routing.
 * 
 * @author Maxim Bobachenko
 */
public abstract class RouteProcessor {	
	
	protected Repository repo;	
	
	public RouteProcessor(Repository repo) {
		this.repo = repo;
	}

	/**
	 * Make route.
	 * @param pointIdList List of point ids.
	 * @return Shortest route for all points.
	 */
	abstract public Route makeRoute(List<Long> pointIdList);


	/**
	 * Factory method to create instance of particular algorithm.
	 * @param repo repository
	 * @param useFull Use full search of ways.
	 * @return
	 */
	public static RouteProcessor getInstance(Repository repo, boolean useFull) {
		if(useFull)
			return new FullRouteProcessor(repo);
		else
			return new SerialRouteProcessor(repo);
	}
}
