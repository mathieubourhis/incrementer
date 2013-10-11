package me.hopto.patriarch.incrementer.data.calculator;

import java.io.Serializable;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

public final class FormulaWrapper implements Serializable {
	private static final long	serialVersionUID	= 811466630726523721L;

	/** Which resource is concerned. */
	final ResourceType				resourceType;

	/** Base increment of building. */
	final double							baseIncrement;

	/** Base cost of building. */
	final double							baseCost;

	final Formula							formula;

	public FormulaWrapper(final ResourceType resourceType, final double baseIncrement, final double baseCost, final Formula formula) {
		this.resourceType = resourceType;
		this.baseIncrement = baseIncrement;
		this.baseCost = baseCost;
		this.formula = formula;
	}

	public double getNextCostForLevel(int level) {
		return formula.getNextCostForLevel(baseCost, level);
	}

	public double getGlobalCostForLevel(int level) {
		return formula.getGlobalCostForLevel(baseCost, level);
	}

	public double getNextIncrement() {
		return baseIncrement;
	}

	public double getGlobalIncrementForLevel(int level) {
		return formula.getGlobalIncrementForLevel(baseIncrement, level);
	}

	// TODO not sure if usefull..
	public ResourceType getResourceType() {
		return resourceType;
	}

	public ResourceType getFormula() {
		return resourceType;
	}
}
