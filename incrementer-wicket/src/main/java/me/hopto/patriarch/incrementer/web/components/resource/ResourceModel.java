package me.hopto.patriarch.incrementer.web.components.resource;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.data.building.BuildingType;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;
import org.apache.wicket.model.AbstractReadOnlyModel;

// TODO store ref of items in constructor instead of searching for them
// every 10 millisecs
public class ResourceModel extends AbstractReadOnlyModel<String> {
	private static final long	serialVersionUID	= -5278580199867202676L;
	private Built							built;
	private ResourceType			resourceType;
	private BuildingType			buildingType;

	public ResourceModel(Built built, ResourceType resourceType, BuildingType buildingType) {
		this.built = built;
		this.resourceType = resourceType;
		this.buildingType = buildingType;
	}

	@Override
	public String getObject() {
		return built.getResourceCostForBuilding(resourceType, buildingType);
	}
}
