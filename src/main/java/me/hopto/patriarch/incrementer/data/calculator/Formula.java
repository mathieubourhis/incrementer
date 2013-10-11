package me.hopto.patriarch.incrementer.data.calculator;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class Formula implements Serializable {
	private static final long	serialVersionUID	= 6335699924699243360L;

	/**
	 * To be overridden. Basic formula based on a baseCost and a level to calculate the next building cost.
	 * 
	 * @param baseCost the base cost of the building
	 * @param level the desired level to calculate the cost of.
	 * @return the calculated next cost.
	 */
	public double getNextCostForLevel(double baseCost, int level) {
		return 0.0d;
	}

	/**
	 * To be overridden. Basic formula based on a baseCost and a level to calculate the global building cost.
	 * 
	 * @param baseCost the base cost of the building
	 * @param level the current building level to calculate the global cost of.
	 * @return the calculated global cost.
	 */
	public double getGlobalCostForLevel(double baseCost, int level) {
		return 0.0d;
	}

	/**
	 * To be overridden. Basic formula based on a baseIncrement and a level to calculate the global building Increment.
	 * 
	 * @param baseIncrement the base cost of the building
	 * @param level the current building level to calculate the global cost of.
	 * @return the calculated global cost.
	 */
	public double getGlobalIncrementForLevel(double baseIncrement, int level) {
		return 0.0d;
	}

	protected double lameHackForRounding(double globalCost) {
		BigDecimal a = BigDecimal.valueOf(globalCost);
		BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return roundOff.doubleValue();
	}
}
