package org.bobachenko.skf.routing.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bobachenko.skf.routing.model.Path;
import org.bobachenko.skf.routing.model.RouteProcessor;
import org.bobachenko.skf.routing.repo.MemoryRepository;
import org.junit.Assert;
import org.junit.Test;

public class RouteProcessorTest {

	@Test
	public void makeRoute(){
		RouteProcessor proc = new RouteProcessor(new MemoryRepository());
		List<Long> ids = Stream.of(1L, 3L, 5L).collect(Collectors.toList());		
		List<Path> route = proc.makeRoute(ids);
		
		Assert.assertTrue(route.size()==2 && route.get(0).getTo()==5 && route.get(1).getTo()==3);		
	}	
}
