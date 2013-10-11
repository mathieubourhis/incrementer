package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.calculator.Formula;
import me.hopto.patriarch.incrementer.data.calculator.FormulaWrapper;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

/**
 * 
 * @see {@link Building}
 */
public class WoodGatherer extends Building {
	private static final long	serialVersionUID	= -5833093484743001743L;

	/** Default constructor for serialization. */
	public WoodGatherer() {
		this(0);
	}

	/**
	 * Init this building's level.
	 * 
	 * @param startingLevel starting level of building
	 * @see {@link Building#Building}
	 */
	public WoodGatherer(int startingLevel) {
		super(startingLevel);
		buildingType = BuildingType.WoodGatherer;
	}

	@Override
	public void addFoodFormula() {
		formulas.put(ResourceType.Food, new FormulaWrapper(ResourceType.Food, 0.0d, 20.0d, new Formula() {
			private static final long	serialVersionUID	= 4415565703841658704L;

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

	@Override
	public void addWoodFormula() {
		formulas.put(ResourceType.Wood, new FormulaWrapper(ResourceType.Wood, 0.1d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= -5456817389876614405L;

			@Override
			public double getGlobalIncrementForLevel(double baseIncrement, int level) {
				return lameHackForRounding(baseIncrement * level);
			}
		}));
	}

	@Override
	public void addMetalFormula() {
		formulas.put(ResourceType.Metal, new FormulaWrapper(ResourceType.Metal, 0.0d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= -8433013699166727148L;
		}));

	}

	@Override
	public void addToolFormula() {
		formulas.put(ResourceType.Tool, new FormulaWrapper(ResourceType.Tool, 0.0d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= 254373899066709290L;
		}));

	}
}
