package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.resource.ResourceType;

/**
 * Finds metal in rivers
 * 
 * @see {@link Building}
 */
public class MetalDigger extends Building {
	private static final long serialVersionUID = -7600326882064946876L;

	/** Default constructor for serialization. */
	public MetalDigger() {
		super();
		buildingType = BuildingType.MetalDigger;
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
	public MetalDigger(int startingLevel, final double baseIncrement,
			final double baseCost) {
		super(startingLevel, baseIncrement, baseCost);
		buildingType = BuildingType.MetalDigger;
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
			break;
		case Wood:
			return baseCost * level;
		case Tool:
			break;
		default:
			break;
		}
		return 0.0d;
	}
}
