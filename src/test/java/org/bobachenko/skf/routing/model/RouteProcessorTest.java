package org.bobachenko.skf.routing.model;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bobachenko.skf.routing.model.RouteProcessor;
import org.bobachenko.skf.routing.repo.MemoryRepository;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Collections2;

public class RouteProcessorTest {
	
	@Test
	public void permutations () {

	    Collection<List<Point>> perm =
	            Collections2.permutations(new MemoryRepository().getPoints().subList(0, 3));

	    perm.stream().forEach(System.out::println);

	    assertEquals(6, perm.size());
	}

	@Test
	public void createRouteBySerial() {
		RouteProcessor proc = RouteProcessor.getInstance(new MemoryRepository(), false);
		List<Long> ids = Stream.of(1L, 3L, 5L).collect(Collectors.toList());		
		Route route = proc.makeRoute(ids);
		
		Assert.assertTrue(route.getPaths().size()==2 && route.getPaths().get(0).getTo()==5 && route.getPaths().get(1).getTo()==3);		
	}	
	
	@Test
	public void createRouteByFull() {
		RouteProcessor proc = RouteProcessor.getInstance(new MemoryRepository(), true);
		List<Long> ids = Stream.of(1L, 3L, 5L).collect(Collectors.toList());		
		Route route = proc.makeRoute(ids);
		
		Assert.assertTrue(route.getPaths().size()==2 && route.getPaths().get(0).getTo()==3 && route.getPaths().get(1).getTo()==5);		
	}	
}
