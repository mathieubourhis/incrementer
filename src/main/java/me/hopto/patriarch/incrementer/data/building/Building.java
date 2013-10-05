package me.hopto.patriarch.incrementer.data.building;

import java.io.Serializable;

public abstract class Building implements Serializable {
	private static final long serialVersionUID = 1931346693501761137L;

	/** Current level of building. */
	int level;

	/** Base increment of building. */
	final double baseIncrement;

	/** Base cost of building. */
	final double baseCost;

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
		this.level = startingLevel;
		this.baseIncrement = baseIncrement;
		this.baseCost = baseCost;
	}

	/** Increase this building's level. */
	public void levelUp() {
		this.level++;
		System.out.println(new StringBuffer(30).append("[DEBUG] Leveling ")
				.append(this.getClass().getSimpleName()).append(" to ")
				.append(level).toString());
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
	public boolean canBuy(double resourceQuantity) {
		boolean canBuy = getCost() <= resourceQuantity;
		if (!canBuy)
			System.err.println(new StringBuffer(30)
					.append("[DEBUG] Cannot level ")
					.append(this.getClass().getSimpleName()).append(" to ")
					.append(level + 1).toString());
		return canBuy;
	}

	/**
	 * Get the current increment value based on the building's level.
	 * 
	 * @return the current increment value.
	 */
	public abstract double getIncrement();

	/**
	 * Get the current cost value based on the building's level.
	 * 
	 * @return the current cost value.
	 */
	public abstract double getCost();
}
