package org.bobachenko.skf.routing.model;

/**
 * Base class for all entities
 * @author Maxim Bobachenko
 */
public abstract class Entity {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Entity(long id) {
		this.id = id;
	}
	
	public Entity() {
	}

	@Override
	public String toString() {
		return String.format("%s Id=%s", this.getClass().getSimpleName(), id);
	}
	
	
}
