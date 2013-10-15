package me.hopto.patriarch.incrementer.app.admin;

import java.util.Map;
import me.hopto.patriarch.incrementer.core.building.Building;

public class BuildingMapper<T extends Building> extends Mapper<T> {

	@Override
	public T map(Class<T> classToMap, Map<String, String> toMap) {
		return null;
	}
}