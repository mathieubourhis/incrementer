package me.hopto.patriarch.incrementer.data;

import static com.google.common.base.Objects.toStringHelper;

import java.io.Serializable;
import java.math.BigDecimal;
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
import me.hopto.patriarch.incrementer.data.calculator.FormulaWrapper;
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

	List<Resource> resources;
	List<Building> buildings;

	public Built() {
		resources = new ArrayList<Resource>();
		resources.add(new Food(0.0d, 0.0d));
		resources.add(new Metal(0.0d, 0.0d));
		resources.add(new Wood(0.0d, 0.0d));
		resources.add(new Tool(0.0d, 0.0d));

		buildings = new ArrayList<Building>();
		buildings.add(new BerryPicker(0));
		buildings.add(new FisherMan(0));
		buildings.add(new WoodGatherer(0));
		buildings.add(new LumberJack(0));
		buildings.add(new MetalDigger(0));
		buildings.add(new Miner(0));
		buildings.add(new Inventor(0));
		buildings.add(new BlackSmith(0));

		incrementCalculator = new IncrementCalculator();
	}

	public void incrementAll(double ratio) {
		for (Resource resource : resources) {
			resource.increment(ratio);
			if (logger.isDebugEnabled())
				logger.debug(resource);
		}
	}

	public void incrementOne(ResourceType resourceType) {
		findResourceByType(resourceType).manualIncrement();
	}

	/**
	 * Returns a formatted quantity of resources
	 * 
	 * @return the formatted quantity of resources
	 */
	public String getFormattedResourceQuantity(final ResourceType resourceType) {
		double quantity = lameHackForRounding(findResourceByType(resourceType)
				.getQuantity());
		return String.valueOf(quantity);
		// format("%.2f", quantity);
	}

	private double lameHackForRounding(double globalCost) {
		BigDecimal a = BigDecimal.valueOf(globalCost);
		BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return roundOff.doubleValue();
	}

	/**
	 * Returns a formatted quantity of resources
	 * 
	 * @return the formatted quantity of resources
	 */
	public String getResourceCostForBuilding(final ResourceType resourceType,
			final BuildingType buildingType) {
		Building building = findBuildingByType(buildingType);
		FormulaWrapper formula = building.findFormulaForResource(resourceType);
		return String.valueOf(formula.getNextCostForLevel(building.getLevel()));
		// format("%.2f",findBuildingByType.getCostForResource(resourceType));
	}

	/**
	 * Returns a formatted quantity of resources
	 * 
	 * @return the formatted quantity of resources
	 */
	public String getLevelForBuilding(final BuildingType buildingType) {
		Building findBuildingByType = findBuildingByType(buildingType);
		return String.valueOf(findBuildingByType.getLevel());
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
		if (incrementCalculator.canBuy(building, resources)) {
			incrementCalculator.buy(building, resources);
			building.levelUp();
			incrementCalculator.incrementResources(building, resources);

			if (logger.isInfoEnabled()) {
				logger.info(new StringBuffer(30).append("Leveling ")
						.append(building.getType().name()).append(" to ")
						.append(building.getLevel() + 1).toString());
			}
		} else {
			if (logger.isInfoEnabled()) {
				logger.info(new StringBuffer(30).append("Cannot level ")
						.append(building.getType().name()).append(" to ")
						.append(building.getLevel() + 1).toString());
			}
		}
	}

	/**
	 * Basic override for debugging purposes.
	 */
	@Override
	public String toString() {
		return toStringHelper(this).addValue(this.resources)
				.addValue(this.buildings).toString();
	}

	Resource findResourceByType(final ResourceType resourceType) {
		Predicate<Resource> finderByType = new Predicate<Resource>() {
			@Override
			public boolean apply(Resource input) {
				return resourceType == input.getType();
			}
		};
		return Iterables.filter(resources, finderByType).iterator().next();
	}

	Building findBuildingByType(final BuildingType buildingType) {
		Predicate<Building> finderByType = new Predicate<Building>() {
			@Override
			public boolean apply(Building input) {
				return buildingType == input.getType();
			}
		};
		return Iterables.filter(buildings, finderByType).iterator().next();
	}

}
