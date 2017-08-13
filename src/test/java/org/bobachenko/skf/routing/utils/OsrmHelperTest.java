package org.bobachenko.skf.routing.utils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.bobachenko.skf.routing.model.Point;
import org.bobachenko.skf.routing.repo.MemoryRepository;
import org.bobachenko.skf.routing.repo.Repository;
import org.bobachenko.skf.routing.utils.OsrmHelper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class OsrmHelperTest {

	private static final String OSRM_URL = "http://kpl.soft.kz/osrm";

	@Test
	public void makeRequest() throws IOException {
		OsrmHelper osrm = new OsrmHelper(OSRM_URL);
		Optional<OsrmHelper.Route> result = osrm.getRoute(51.185478, 71.494938, 51.173482, 71.450494);
		Assert.assertTrue(result.isPresent());
	}

	@Test
	@Ignore
	/**
	 * For generate path list.
	 */
	public void generateMatrixCode() {
		OsrmHelper osrm = new OsrmHelper(OSRM_URL);
		Repository repo = new MemoryRepository();

		String template = "pathList.add(new Path(%s, %s, \"%s\", %s, \"%s\", %s, \"%s\"));";

		List<Point> pointList = repo.getPoints();

		int id = 0;

		for (int i = 0; i < pointList.size(); i++) {
			for (int j = 0; j < pointList.size(); j++) {
				if (i == j)
					continue;

				Point from = pointList.get(i);
				Point to = pointList.get(j);

				Optional<OsrmHelper.Route> result = osrm.getRoute(from.getLat(), from.getLon(), to.getLat(),
						to.getLon());

				System.out.println(String.format(template, ++id, from.getId(), from.getName(), to.getId(), to.getName(),
						result.get().getDistance(), result.get().getGeometryJson()));
			}
		}
	}
}
