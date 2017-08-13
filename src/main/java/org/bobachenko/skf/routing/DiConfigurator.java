package org.bobachenko.skf.routing;

import org.bobachenko.skf.routing.controller.Controller;
import org.bobachenko.skf.routing.controller.ControllerImpl;
import org.bobachenko.skf.routing.repo.MemoryRepository;
import org.bobachenko.skf.routing.repo.Repository;

import com.google.inject.AbstractModule;

/**
 * Dependency injection configuration module
 * @author Maxim Bobachenko
 */
public class DiConfigurator extends AbstractModule {

	@Override
	protected void configure() {
		bind(Controller.class).to(ControllerImpl.class);
		bind(Repository.class).to(MemoryRepository.class);
	}

}
