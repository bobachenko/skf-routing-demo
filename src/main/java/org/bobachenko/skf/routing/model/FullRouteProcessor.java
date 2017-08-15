package org.bobachenko.skf.routing.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.bobachenko.skf.routing.repo.Repository;
import com.google.common.collect.Collections2;

/**
 * Class for routing.
 * 
 * @author Maxim Bobachenko
 */
public class FullRouteProcessor extends RouteProcessor {	
	
	public FullRouteProcessor(Repository repo) {
		super(repo);
	}

	/**
	 * Make route with full search algorithm.
	 * @param pointIdList List of point ids.
	 * @return Shortest route for all points.
	 */
	@Override
	public Route makeRoute(List<Long> pointIdList) {
		
		long startTime = System.currentTimeMillis();
	
		if(pointIdList==null)
			throw new IllegalArgumentException("Argument shouldn't be null.");
		
		long storeId = repo.getStore().getId();
		pointIdList.remove(storeId); // remove store from the list, if exists
		
		if(pointIdList.isEmpty())
			throw new IllegalArgumentException("Argument shouldn't be empty.");
		
		List<Route> routeList = new LinkedList<>();
		
	    Collection<List<Long>> variantIds =
	            Collections2.permutations(pointIdList);

	    for(List<Long> variant : variantIds) {
	    	routeList.add(buildRouteVariant(storeId, variant));
	    }
		
	    Route r = routeList.stream().min((r1, r2) -> Double.compare(r1.getDistance(), r2.getDistance())).get();
	    
	    r.setCalculationTime(System.currentTimeMillis() - startTime);
	    
		return r;
	}

	private Route buildRouteVariant(long storeId, List<Long> variantIdList) {
				
		double distance = 0;
		Route r = new Route();
		
		Path fromStoreToFirstPoint = repo.getPath(storeId, variantIdList.get(0)).get();  
		r.getPaths().add(fromStoreToFirstPoint);
		distance+=fromStoreToFirstPoint.getDistance();
		
		for(int i=0; i<variantIdList.size()-1; i++) {
			Path p = repo.getPath(variantIdList.get(i), variantIdList.get(i+1)).get();  
			r.getPaths().add(p);
			distance+=p.getDistance();
		}
		
		r.setDistance(distance);		
		return r;
	}


}
