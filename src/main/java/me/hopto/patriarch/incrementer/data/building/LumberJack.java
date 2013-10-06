package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.calculator.Formula;
import me.hopto.patriarch.incrementer.data.calculator.FormulaWrapper;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

/**
 * 
 * @see {@link Building}
 */
public class LumberJack extends Building {
	private static final long serialVersionUID = 4822841833261117010L;

	/** Default constructor for serialization. */
	public LumberJack() {
		this(0);
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
	public LumberJack(int startingLevel) {
		super(startingLevel);
		buildingType = BuildingType.LumberJack;
	}

	@Override
	public void addFoodFormula() {
		formulas.put(ResourceType.Food, new FormulaWrapper(ResourceType.Food,
				0.0d, 30.0d, new Formula() {
					private static final long serialVersionUID = -5274982444152224351L;

					@Override
					public double getNextCostForLevel(double baseCost, int level) {
						return lameHackForRounding(lameHackForRounding(baseCost
								* (Math.pow(1.15d, level))));
					}

					@Override
					public double getGlobalCostForLevel(double baseCost,
							int level) {
						return lameHackForRounding((baseCost * (Math.pow(1.15D,
								level) - 1.0d)) / 0.15d);
					}
				}));

	}

	@Override
	public void addWoodFormula() {
		formulas.put(ResourceType.Wood, new FormulaWrapper(ResourceType.Wood,
				0.5d, 0.0d, new Formula() {
					private static final long serialVersionUID = 8536395896085451211L;

					@Override
					public double getGlobalIncrementForLevel(
							double baseIncrement, int level) {
						return lameHackForRounding(baseIncrement * level);
					}
				}));

	}

	@Override
	public void addMetalFormula() {
		formulas.put(ResourceType.Metal, new FormulaWrapper(ResourceType.Metal,
				0.0d, 0.0d, new Formula() {
					private static final long serialVersionUID = 879319387047441583L;
				}));

	}

	@Override
	public void addToolFormula() {
		formulas.put(ResourceType.Tool, new FormulaWrapper(ResourceType.Tool,
				0.0d, 15.0d, new Formula() {
					private static final long serialVersionUID = 257949669330623275L;

					@Override
					public double getNextCostForLevel(double baseCost, int level) {
						return lameHackForRounding(lameHackForRounding(baseCost
								* (Math.pow(1.15d, level))));
					}

					@Override
					public double getGlobalCostForLevel(double baseCost,
							int level) {
						return lameHackForRounding((baseCost * (Math.pow(1.15D,
								level) - 1.0d)) / 0.15d);
					}
				}));

	}
}
