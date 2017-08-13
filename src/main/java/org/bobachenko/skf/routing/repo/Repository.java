package org.bobachenko.skf.routing.repo;

import java.util.List;
import java.util.Optional;

import org.bobachenko.skf.routing.model.Path;
import org.bobachenko.skf.routing.model.Point;

/**
 * Abstract repository
 * @author Maxim Bobachenko
 */
public interface Repository {
	String getHello();
	List<Point> getPoints();	
	Optional<Path> getPath(long from, long to);	
	Point getStore();
}
