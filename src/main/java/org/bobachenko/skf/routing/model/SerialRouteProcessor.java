package org.bobachenko.skf.routing.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.bobachenko.skf.routing.repo.Repository;


/**
 * Class for routing.
 * 
 * @author Maxim Bobachenko
 */
public class SerialRouteProcessor extends RouteProcessor {	
	
	public SerialRouteProcessor(Repository repo) {
		super(repo);
	}

	/**
	 * Route calculation with serial search algorithm.
	 * @param pointIdList List of point ids.
	 * @return Shortest route for all points.
	 */
	@Override
	public Route makeRoute(List<Long> pointIdList) {
		
		long startTime = System.currentTimeMillis();
	
		if(pointIdList==null || pointIdList.isEmpty())
			throw new IllegalArgumentException("Argument shouldn't be empty.");
		
		List<Path> paths = new LinkedList<>();		
		
		long currentPoint = repo.getStore().getId();
		pointIdList.remove(currentPoint); // remove store from the list, if exists
		
		double distance = 0;

		while(pointIdList.size()>0) {
			Path p = getShortestPath(currentPoint, pointIdList);
			paths.add(p);
			distance+=p.getDistance();			
			currentPoint = p.getTo();
			pointIdList.remove(currentPoint);
		}		
		
		return new Route(paths, distance, System.currentTimeMillis() - startTime);
	}
	
	
	private Path getShortestPath(long fromId, List<Long> toIdList) {
		Path shortest = null;
		
		for(Long toId : toIdList) {
			Optional<Path> p = repo.getPath(fromId, toId);
			if(p.isPresent() && (shortest==null || shortest.getDistance()>p.get().getDistance()))
				shortest = p.get();
		}
		
		return shortest;
	}

}
