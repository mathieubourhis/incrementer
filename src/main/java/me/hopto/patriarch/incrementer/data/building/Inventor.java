package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.resource.ResourceType;

/**
 * 
 * @see {@link Building}
 */
public class Inventor extends Building {
	private static final long serialVersionUID = 2733711938756765142L;

	/** Default constructor for serialization. */
	public Inventor() {
		super();
		buildingType = BuildingType.Inventor;
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
	public Inventor(int startingLevel, final double baseIncrement,
			final double baseCost) {
		super(startingLevel, baseIncrement, baseCost);
		buildingType = BuildingType.Inventor;
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
			return 0.0d;
		case Tool:
			return level * baseIncrement;
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
			return baseCost * level;
		case Wood:
			return baseCost * level;
		case Tool:
			return 0.0d;
		default:
			break;
		}
		return 0.0d;
	}
}
