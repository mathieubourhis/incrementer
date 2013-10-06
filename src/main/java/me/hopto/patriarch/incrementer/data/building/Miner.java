package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.calculator.Formula;
import me.hopto.patriarch.incrementer.data.calculator.FormulaWrapper;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

/**
 * 
 * @see {@link Building}
 */
public class Miner extends Building {
	private static final long serialVersionUID = -7600326882064946876L;

	/** Default constructor for serialization. */
	public Miner() {
		this(0);
	}

	/**
	 * Init this building's level.
	 * 
	 * @param startingLevel
	 *            starting level of building
	 * @see {@link Building#Building}
	 */
	public Miner(int startingLevel) {
		super(startingLevel);
		buildingType = BuildingType.Miner;
	}

	@Override
	public void addFoodFormula() {
		formulas.put(ResourceType.Food, new FormulaWrapper(ResourceType.Food,
				0.0d, 40.0d, new Formula() {
					private static final long serialVersionUID = -3538898412238508801L;

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
				0.0d, 35.0d, new Formula() {
					private static final long serialVersionUID = -3934513612823397736L;

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
	public void addMetalFormula() {
		formulas.put(ResourceType.Metal, new FormulaWrapper(ResourceType.Metal,
				0.5d, 0.0d, new Formula() {
					private static final long serialVersionUID = -7441010107416769221L;

					@Override
					public double getGlobalIncrementForLevel(
							double baseIncrement, int level) {
						return lameHackForRounding(baseIncrement * level);
					}
				}));

	}

	@Override
	public void addToolFormula() {
		formulas.put(ResourceType.Tool, new FormulaWrapper(ResourceType.Tool,
				0.0d, 15.0d, new Formula() {
					private static final long serialVersionUID = -7084638883390137575L;

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
