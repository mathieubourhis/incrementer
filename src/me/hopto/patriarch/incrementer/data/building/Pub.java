package me.hopto.patriarch.incrementer.data.building;


/** You can find bottle caps in pubs */
public class Pub {

	/** Current level of building. */
	private int level;
	
	/** Base increment of building. */
	private final double baseIncrement;
	
	/**	
	 * Init this building's level. 
	 * @param startingLevel starting level of building
	 * @param baseIncrement starting increment given by building
	 */
	public Pub(int startingLevel, final double baseIncrement) {
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
	public double getIncrement() {
		return baseIncrement * level;
	}
}
