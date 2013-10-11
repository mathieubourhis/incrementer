package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.calculator.Formula;
import me.hopto.patriarch.incrementer.data.calculator.FormulaWrapper;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

/**
 * @see {@link Building}
 */
public class FisherMan extends Building {
	private static final long	serialVersionUID	= -3203852755166742102L;

	/** Default constructor for serialization. */
	public FisherMan() {
		this(0);
	}

	/**
	 * Init this building's level.
	 * 
	 * @param startingLevel starting level of building
	 * @see {@link Building#Building}
	 */
	public FisherMan(int startingLevel) {
		super(startingLevel);
		buildingType = BuildingType.FisherMan;
	}

	@Override
	public void addFoodFormula() {
		formulas.put(ResourceType.Food, new FormulaWrapper(ResourceType.Food, 2.0d, 25.0d, new Formula() {
			private static final long	serialVersionUID	= 4282001913370688493L;

			@Override
			public double getNextCostForLevel(double baseCost, int level) {
				return lameHackForRounding(lameHackForRounding(baseCost * (Math.pow(1.15d, level))));
			}

			@Override
			public double getGlobalCostForLevel(double baseCost, int level) {
				return lameHackForRounding((baseCost * (Math.pow(1.15D, level) - 1.0d)) / 0.15d);
			}

			@Override
			public double getGlobalIncrementForLevel(double baseIncrement, int level) {
				return lameHackForRounding(baseIncrement * level);
			}
		}));

	}

	@Override
	public void addWoodFormula() {
		formulas.put(ResourceType.Wood, new FormulaWrapper(ResourceType.Wood, 0.0d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= -849589385610671048L;
		}));

	}

	@Override
	public void addMetalFormula() {
		formulas.put(ResourceType.Metal, new FormulaWrapper(ResourceType.Metal, 0.0d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= 4931260316030218640L;
		}));

	}

	@Override
	public void addToolFormula() {
		formulas.put(ResourceType.Tool, new FormulaWrapper(ResourceType.Tool, 0.0d, 15.0d, new Formula() {
			private static final long	serialVersionUID	= -4118434917738292949L;

			@Override
			public double getNextCostForLevel(double baseCost, int level) {
				return lameHackForRounding(lameHackForRounding(baseCost * (Math.pow(1.15d, level))));
			}

			@Override
			public double getGlobalCostForLevel(double baseCost, int level) {
				return lameHackForRounding((baseCost * (Math.pow(1.15D, level) - 1.0d)) / 0.15d);
			}
		}));

	}
}
