package me.hopto.patriarch.incrementer.core.building;

import me.hopto.patriarch.incrementer.core.resource.ResourceType;

/**
 * Building type enum.
 */
public enum BuildingType {
	BerryPicker(ResourceType.Food), FisherMan(ResourceType.Food), WoodGatherer(ResourceType.Wood), LumberJack(ResourceType.Wood), MetalDigger(ResourceType.Metal), Miner(ResourceType.Metal), Inventor(ResourceType.Tool), BlackSmith(ResourceType.Tool);

	/** The kind of resource this building produces. */
	private ResourceType	produces;

	/**
	 * Specifies what kind of resource a building produces.
	 * 
	 * @param produces the kind of resource a building produces.
	 */
	private BuildingType(ResourceType produces) {
		this.produces = produces;
	}

	/**
	 * @return the kind of resource a building produces.
	 */
	public ResourceType getResourceProduced() {
		return produces;
	}
}
