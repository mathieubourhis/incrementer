package me.hopto.patriarch.incrementer.data.calculator;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class Formula implements Serializable {
	private static final long serialVersionUID = 6335699924699243360L;

	public double getNextCostForLevel(double baseCost, int level) {
		return 0.0d;
	}

	public double getGlobalCostForLevel(double baseCost, int level) {
		return 0.0d;
	}

	public double getGlobalIncrementForLevel(double baseIncrement, int level) {
		return 0.0d;
	}

	protected double lameHackForRounding(double globalCost) {
		BigDecimal a = BigDecimal.valueOf(globalCost);
		BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return roundOff.doubleValue();
	}
}
