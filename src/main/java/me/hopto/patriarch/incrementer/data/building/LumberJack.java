package me.hopto.patriarch.incrementer.data.building;

/**
 * You find bottle caps in trash bins
 * @see {@link Building} 
 */
public class LumberJack extends Building  {
	/**	
	 * Init this building's level. 
	 * @param startingLevel starting level of building
	 * @param baseIncrement starting increment given by building
	 * @see {@link Building#Building}
	 */
	public LumberJack(int startingLevel, final double baseIncrement, final double baseCost) {
		super(startingLevel, baseIncrement, baseCost);
	}
	
	/** 
	 * Get the current increment value based on the building's level.
	 * @return the current increment value.
	 * @see {@link Building#getIncrement()}
	 */
	@Override
	public double getIncrement() {
		return baseIncrement * level;
	}
	

	/** 
	 * Get the current cost value based on the building's level.
	 * @return the current cost value.
	 * @see {@link Building#getCost()}
	 */
	@Override
	public double getCost() {
		return baseCost * level;
	}
}
