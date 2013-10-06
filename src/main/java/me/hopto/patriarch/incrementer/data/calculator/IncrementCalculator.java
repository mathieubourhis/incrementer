package me.hopto.patriarch.incrementer.data.calculator;

import java.io.Serializable;
import java.util.List;

import me.hopto.patriarch.incrementer.data.building.Building;
import me.hopto.patriarch.incrementer.data.resource.Resource;

// TODO this should be a spring stateless service, gotta add maven or dl libs.
public class IncrementCalculator implements Serializable {
	private static final long serialVersionUID = 3567474099928109902L;

	public IncrementCalculator() {

	}

	public void incrementResources(List<Building> buildings,
			List<Resource> resources) {
		for (Resource resource : resources) {
			double increment = 0.0d;
			for (Building building : buildings) {
				increment += building.getIncrementForResource(resource
						.getType());
			}
			resource.updateIncrementValue(increment);
		}
	}

	public void buy(Building building, List<Resource> resources) {
		for (Resource resource : resources) {
			resource.buy(building.getCostForResource(resource.getType()));
		}
	}
}
