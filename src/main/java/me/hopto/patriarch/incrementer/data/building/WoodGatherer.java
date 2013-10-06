package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.resource.ResourceType;

/**
 * 
 * @see {@link Building}
 */
public class WoodGatherer extends Building {
	private static final long serialVersionUID = -5833093484743001743L;

	/** Default constructor for serialization. */
	public WoodGatherer() {
		super();
		buildingType = BuildingType.WoodGatherer;
	}

	/**
	 * Init this building's level.
	 * 
	 * @param startingLevel
	 *            starting level of building
	 * @param baseIncrement
	 *            starting increment given by building
	 * @see {@link Building#Building}
	 */
	public WoodGatherer(int startingLevel, final double baseIncrement,
			final double baseCost) {
		super(startingLevel, baseIncrement, baseCost);
		buildingType = BuildingType.WoodGatherer;
	}

	/**
	 * Get the current increment value based on the building's level.
	 * 
	 * @return the current increment value.
	 * @see {@link Building#getIncrementForResource(ResourceType)}
	 */
	@Override
	public double getIncrementForResource(ResourceType resourceType) {
		switch (resourceType) {
		case Food:
			return 0.0d;
		case Metal:
			return 0.0d;
		case Wood:
			return baseIncrement * level;
		case Tool:
			return 0.0d;
		default:
			break;
		}
		return 0.0d;
	}

	/**
	 * Get the current cost value based on the building's level.
	 * 
	 * @return the current cost value.
	 * @see {@link Building#getCostForResource(ResourceType)}
	 */
	@Override
	public double getCostForResource(ResourceType resourceType) {
		switch (resourceType) {
		case Food:
			return baseCost + (0.5d * level);
		case Metal:
			return 0.0d;
		case Wood:
			return 0.0d;
		case Tool:
			return 0.0d;
		default:
			break;
		}
		return 0.0d;
	}
}
