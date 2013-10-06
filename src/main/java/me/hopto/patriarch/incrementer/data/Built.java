package me.hopto.patriarch.incrementer.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.hopto.patriarch.incrementer.data.building.BerryPicker;
import me.hopto.patriarch.incrementer.data.building.BlackSmith;
import me.hopto.patriarch.incrementer.data.building.Building;
import me.hopto.patriarch.incrementer.data.building.BuildingType;
import me.hopto.patriarch.incrementer.data.building.FisherMan;
import me.hopto.patriarch.incrementer.data.building.Inventor;
import me.hopto.patriarch.incrementer.data.building.LumberJack;
import me.hopto.patriarch.incrementer.data.building.MetalDigger;
import me.hopto.patriarch.incrementer.data.building.Miner;
import me.hopto.patriarch.incrementer.data.building.WoodGatherer;
import me.hopto.patriarch.incrementer.data.calculator.IncrementCalculator;
import me.hopto.patriarch.incrementer.data.resource.Food;
import me.hopto.patriarch.incrementer.data.resource.Metal;
import me.hopto.patriarch.incrementer.data.resource.Resource;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;
import me.hopto.patriarch.incrementer.data.resource.Tool;
import me.hopto.patriarch.incrementer.data.resource.Wood;

import org.apache.log4j.Logger;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class Built implements Serializable {
	private static final long serialVersionUID = 8766412728828956639L;
	private static Logger logger = Logger.getLogger(Built.class);
	IncrementCalculator incrementCalculator;

	// TODO i might delete all the sub references and keep working with the
	// guava predicates.
	List<Resource> resources;
	Wood wood;
	Metal metal;
	Food food;
	Tool tool;

	// TODO i might delete all the sub references and keep working with the
	// guava predicates.
	List<Building> buildings;
	BerryPicker berryPicker;
	FisherMan fisherMan;
	WoodGatherer woodGatherer;
	LumberJack lumberJack;
	MetalDigger metalDigger;
	Miner miner;
	Inventor inventor;
	BlackSmith blackSmith;

	public Built() {
		resources = new ArrayList<Resource>();
		resources.add(food = new Food(0.0d, 0.0d));
		resources.add(metal = new Metal(0.0d, 0.0d));
		resources.add(wood = new Wood(0.0d, 0.0d));
		resources.add(tool = new Tool(0.0d, 0.0d));

		buildings = new ArrayList<Building>();
		buildings.add(berryPicker = new BerryPicker(0, 0.2d, 0.0d));
		buildings.add(fisherMan = new FisherMan(0, 0.2d, 0.0d));
		buildings.add(woodGatherer = new WoodGatherer(0, 0.2d, 0.0d));
		buildings.add(lumberJack = new LumberJack(0, 0.2d, 0.0d));
		buildings.add(metalDigger = new MetalDigger(0, 0.2d, 0.0d));
		buildings.add(miner = new Miner(0, 0.5d, 3.0d));
		buildings.add(inventor = new Inventor(0, 0.2d, 0.0d));
		buildings.add(blackSmith = new BlackSmith(0, 0.2d, 0.0d));

		incrementCalculator = new IncrementCalculator();
	}

	public void incrementAll(double ratio) {
		for (Resource resource : resources) {
			resource.increment(ratio);
			if (logger.isDebugEnabled())
				logger.debug(resource);
		}
	}

	/**
	 * Returns a formatted quantity of resources
	 * 
	 * @return the formatted quantity of resources
	 */
	public String getFormattedResourceQuantity(final ResourceType resourceType) {
		return String.format("%.2f", findResourceByType(resourceType)
				.getQuantity());
	}

	/**
	 * Returns a formatted quantity of resources
	 * 
	 * @return the formatted quantity of resources
	 */
	public String getFormattedResourceCostForBuilding(
			final ResourceType resourceType, final BuildingType buildingType) {
		Building findBuildingByType = findBuildingByType(buildingType);
		return String.format("%.2f",
				findBuildingByType.getCostForResource(resourceType));
	}

	/**
	 * Approach by predicates Just be sure not to level up an inexisting
	 * building. is that even possible ? :p
	 * 
	 * @param buildingType
	 *            the building to level up
	 */
	public void levelUp(final BuildingType buildingType) {
		levelUp(findBuildingByType(buildingType));
	}

	/**
	 * Approach by known building.
	 * 
	 * Just be sure not to level up an inexisting building. is that even
	 * possible ? :p
	 * 
	 * @param buildingType
	 *            the building to level up
	 */
	private void levelUp(Building building) {
		if (building.canBuy(resources)) {
			incrementCalculator.buy(building, resources);
			building.levelUp();
			incrementCalculator.incrementResources(buildings, resources);
		}
	}

	/**
	 * Basic override for debugging purposes.
	 */
	@Override
	public String toString() {
		return new StringBuffer(30).append("[")
				.append(this.getClass().getSimpleName()).append("]\n\t")
				.append(wood).append("\n\t").append(metal).toString();
	}

	private Resource findResourceByType(final ResourceType resourceType) {
		Predicate<Resource> finderByType = new Predicate<Resource>() {
			@Override
			public boolean apply(Resource input) {
				return resourceType == input.getType();
			}
		};
		return Iterables.filter(resources, finderByType).iterator().next();
	}

	private Building findBuildingByType(final BuildingType buildingType) {
		Predicate<Building> finderByType = new Predicate<Building>() {
			@Override
			public boolean apply(Building input) {
				return buildingType == input.getType();
			}
		};
		return Iterables.filter(buildings, finderByType).iterator().next();
	}

}
