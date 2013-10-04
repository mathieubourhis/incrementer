package me.hopto.patriarch.incrementer.data.building;

public abstract class Building {

	/** Current level of building. */
	int level;
	
	/** Base increment of building. */
	final double baseIncrement;
	
	/**	
	 * Init this building's level. 
	 * @param startingLevel starting level of building
	 * @param baseIncrement starting increment given by building
	 */
	public Building(int startingLevel, final double baseIncrement) {
		this.level = startingLevel;
		this.baseIncrement = baseIncrement;
	}
	
	/** Increase this building's level. */
	public void levelUp() {
		this.level++;
	}
	
	/**
	 * Returns the current level
	 * @return the current level
	 */
	public int getLevel() {
		return level;
	}
	
	/** 
	 * Get the current increment value based on the building's level.
	 * @return the current increment value.
	 */
	public abstract double getIncrement();
}
