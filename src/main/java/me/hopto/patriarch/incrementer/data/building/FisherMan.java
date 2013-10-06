package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.resource.ResourceType;

/**
 * @see {@link Building}
 */
public class FisherMan extends Building {
	private static final long serialVersionUID = -3203852755166742102L;

	/** Default constructor for serialization. */
	public FisherMan() {
		super();
		buildingType = BuildingType.FisherMan;
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
	public FisherMan(int startingLevel, final double baseIncrement,
			final double baseCost) {
		super(startingLevel, baseIncrement, baseCost);
		buildingType = BuildingType.FisherMan;
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
			return level * baseIncrement;
		case Wood:
			return 0.0d;
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
			return baseCost * level;
		case Metal:
			return 0.0d;
		case Wood:
			return 0.0d;
		case Tool:
			return baseCost * level;
		default:
			break;
		}
		return 0.0d;
	}
}
