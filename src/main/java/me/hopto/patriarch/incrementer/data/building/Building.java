package me.hopto.patriarch.incrementer.data.building;

import java.io.Serializable;
import java.util.List;

import me.hopto.patriarch.incrementer.data.resource.Resource;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

import org.apache.log4j.Logger;

public abstract class Building implements Serializable {
	private static final long serialVersionUID = 1931346693501761137L;
	private static Logger logger = Logger.getLogger(Building.class);

	/** Current level of building. */
	int level;

	/** Base increment of building. */
	final double baseIncrement;

	/** Base cost of building. */
	final double baseCost;

	/** the kind of building this is. */
	BuildingType buildingType;

	/** Default constructor for serialization. */
	public Building() {
		this.level = 0;
		this.baseIncrement = 0.0d;
		this.baseCost = 0.0d;
	}

	/**
	 * Init this building's level.
	 * 
	 * @param startingLevel
	 *            starting level of building
	 * @param baseIncrement
	 *            starting increment given by building
	 * @param baseCost
	 *            starting increment given by building
	 */
	public Building(int startingLevel, final double baseIncrement,
			final double baseCost) {
		// TODO Will need sooner or later a base cost per resources and not a
		// general one
		this.level = startingLevel;
		this.baseIncrement = baseIncrement;
		this.baseCost = baseCost;
	}

	/** Increase this building's level. */
	public void levelUp() {
		this.level++;
		if (logger.isDebugEnabled()) {
			logger.debug(new StringBuffer(30).append("Leveling ")
					.append(this.getClass().getSimpleName()).append(" to ")
					.append(level).toString());
		}
	}

	/**
	 * Returns the current level
	 * 
	 * @return the current level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * checks wether an item can be bought
	 * 
	 * @return the current level
	 */
	public boolean canBuy(List<Resource> resources) {
		boolean canBuy = true;
		for (Resource resource : resources) {
			canBuy &= getCostForResource(resource.getType()) <= resource
					.getQuantity();
			if (!canBuy) {
				if (logger.isDebugEnabled()) {
					logger.error(new StringBuffer(30).append("Cannot level ")
							.append(this.getClass().getSimpleName())
							.append(" to ").append(level).toString());
				}
				break;
			}
		}

		return canBuy;
	}

	/**
	 * Get the current increment value based on the building's level.
	 * 
	 * @param resourceType
	 *            TODO
	 * 
	 * @return the current increment value.
	 */
	public abstract double getIncrementForResource(ResourceType resourceType);

	/**
	 * Get the current cost value based on the building's level.
	 * 
	 * @param resourceType
	 *            TODO
	 * 
	 * @return the current cost value.
	 */
	public abstract double getCostForResource(ResourceType resourceType);

	/**
	 * the kind of building this is. 
	 * @return the kind of building this is.
	 */
	public BuildingType getType() {
		return buildingType;
	}
}
