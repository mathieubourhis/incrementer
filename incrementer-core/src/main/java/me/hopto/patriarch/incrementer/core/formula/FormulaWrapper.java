package me.hopto.patriarch.incrementer.core.formula;

import java.io.Serializable;

/**
 * Wrapps a formula and base costs and increments.<BR>
 * TODO I should remove this class. But it might be usefull if I wan't to apply modifiers on increments. To Track.
 */
public final class FormulaWrapper implements Serializable {
	private static final long	serialVersionUID	= 811466630726523721L;

	/** Base increment of building. */
	final double							baseIncrement;

	/** Base cost of building. */
	final double							baseCost;

	/** The formula to wrap. */
	final Formula							formula;

	/**
	 * Wrapps a formula.
	 * 
	 * @param baseIncrement the base increment.
	 * @param baseCost the base cost.
	 * @param formula the formula.
	 */
	public FormulaWrapper(final double baseIncrement, final double baseCost, final Formula formula) {
		this.baseIncrement = baseIncrement;
		this.baseCost = baseCost;
		this.formula = formula;
	}

	/**
	 * Wrapped. Basic formula based on a baseCost and a level to calculate the next building cost.
	 * 
	 * @param baseCost the base cost of the building
	 * @param level the desired level to calculate the cost of.
	 * @return the calculated next cost.
	 * @see {@link Formula#getNextCostForLevel} for the actual method.
	 */
	public double getNextCostForLevel(int level) {
		return formula.getNextCostForLevel(baseCost, level);
	}

	/**
	 * Wrapped. Basic formula based on a baseCost and a level to calculate the global building cost.
	 * 
	 * @param baseCost the base cost of the building
	 * @param level the current building level to calculate the global cost of.
	 * @return the calculated global cost.
	 * @see {@link Formula#getGlobalCostForLevel} for the actual method.
	 */
	public double getGlobalCostForLevel(int level) {
		return formula.getGlobalCostForLevel(baseCost, level);
	}

	/** @return The next increment. */
	public double getNextIncrement() {
		// TODO Make increment less linear
		return baseIncrement;
	}

	/**
	 * Wrapped. Basic formula based on a baseIncrement and a level to calculate the global building Increment.
	 * 
	 * @param baseIncrement the base cost of the building
	 * @param level the current building level to calculate the global cost of.
	 * @return the calculated global cost.
	 */
	public double getGlobalIncrementForLevel(int level) {
		return formula.getGlobalIncrementForLevel(baseIncrement, level);
	}
}
