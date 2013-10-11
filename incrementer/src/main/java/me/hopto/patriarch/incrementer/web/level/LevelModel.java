package me.hopto.patriarch.incrementer.web.level;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.data.building.BuildingType;
import org.apache.wicket.model.AbstractReadOnlyModel;

// TODO store ref of items in constructor instead of searching for them
// every 10 millisecs
public class LevelModel extends AbstractReadOnlyModel<String> {

	private static final long	serialVersionUID	= 8749860115221101495L;
	private Built							built;
	private BuildingType			buildingType;

	public LevelModel(Built built, BuildingType buildingType) {
		this.built = built;
		this.buildingType = buildingType;
	}

	@Override
	public String getObject() {
		return built.getLevelForBuilding(buildingType);
	}
}
