package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.resource.ResourceType;

public enum BuildingType {
	BerryPicker(ResourceType.Food), FisherMan(ResourceType.Food), WoodGatherer(ResourceType.Wood), LumberJack(ResourceType.Wood), MetalDigger(ResourceType.Metal), Miner(ResourceType.Metal), Inventor(ResourceType.Tool), BlackSmith(ResourceType.Tool);

	private ResourceType	produces;

	private BuildingType(ResourceType produces) {
		this.produces = produces;
	}

	public ResourceType getResourceProduced() {
		return produces;
	}
}
