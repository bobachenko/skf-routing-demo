package org.bobachenko.skf.routing.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bobachenko.skf.routing.repo.Repository;

import com.google.inject.Inject;

/**
 * Class for routing.
 * 
 * @author Maxim Bobachenko
 */
public class RouteProcessor {	
	
	private Repository repo;	
	
	@Inject
	public RouteProcessor(Repository repo) {
		this.repo = repo;
	}

	/**
	 * Make route.
	 * @param pointIdList List of point ids.
	 * @return Shortest route for all points.
	 */
	public List<Path> makeRoute(List<Long> pointIdList) {
	
		if(pointIdList==null || pointIdList.isEmpty())
			throw new IllegalArgumentException("Argument don't must be empty.");
		
		List<Path> result = new LinkedList<>();		
		
		long currentPoint = repo.getStore().getId();
		pointIdList.remove(currentPoint); // remove store from the list, if exists

		while(pointIdList.size()>0) {
			Path p = getShortestPath(currentPoint, pointIdList);
			result.add(p);
			currentPoint = p.getTo();
			pointIdList.remove(currentPoint);
		}
		
		return result;
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
