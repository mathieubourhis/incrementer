package me.hopto.patriarch.incrementer.core.building;

import me.hopto.patriarch.incrementer.core.formula.Formula;
import me.hopto.patriarch.incrementer.core.formula.FormulaWrapper;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;

/**
 * Will mass-produce tools.
 * 
 * @see {@link Building}
 */
public class BlackSmith extends Building {
	private static final long	serialVersionUID	= 2733711938756765142L;

	/** Default constructor (init). */
	public BlackSmith() {
		this(0);
	}

	/**
	 * Init this building's level (load).
	 * 
	 * @param startingLevel starting level of building
	 */
	public BlackSmith(int startingLevel) {
		super(startingLevel);
		buildingType = BuildingType.BlackSmith;
	}

	/** Adds formulas for food resource and income calculations. */
	@Override
	public void addFoodFormula() {
		formulas.put(ResourceType.Food, new FormulaWrapper(0.0d, 100.0d, new Formula() {
			private static final long	serialVersionUID	= -8570184579067688949L;

			/**
			 * Basic formula based on a baseCost and a level to calculate the next building cost.
			 * 
			 * @param baseCost the base cost of the building
			 * @param level the desired level to calculate the cost of.
			 * @return the calculated next cost.
			 */
			@Override
			public double getNextCostForLevel(double baseCost, int level) {
				return lameHackForRounding(lameHackForRounding(baseCost * (Math.pow(1.15d, level))));
			}

			/**
			 * Basic formula based on a baseCost and a level to calculate the global building cost.
			 * 
			 * @param baseCost the base cost of the building
			 * @param level the current building level to calculate the global cost of.
			 * @return the calculated global cost.
			 */
			@Override
			public double getGlobalCostForLevel(double baseCost, int level) {
				return lameHackForRounding((baseCost * (Math.pow(1.15D, level) - 1.0d)) / 0.15d);
			}
		}));
	}

	/** Adds formulas for wood resource and income calculations. */
	@Override
	public void addWoodFormula() {
		formulas.put(ResourceType.Wood, new FormulaWrapper(0.0d, 60.0d, new Formula() {
			private static final long	serialVersionUID	= 3811501606828731300L;

			/**
			 * Basic formula based on a baseCost and a level to calculate the next building cost.
			 * 
			 * @param baseCost the base cost of the building
			 * @param level the desired level to calculate the cost of.
			 * @return the calculated next cost.
			 */
			@Override
			public double getNextCostForLevel(double baseCost, int level) {
				return lameHackForRounding(lameHackForRounding(baseCost * (Math.pow(1.15d, level))));
			}

			/**
			 * Basic formula based on a baseCost and a level to calculate the global building cost.
			 * 
			 * @param baseCost the base cost of the building
			 * @param level the current building level to calculate the global cost of.
			 * @return the calculated global cost.
			 */
			@Override
			public double getGlobalCostForLevel(double baseCost, int level) {
				return lameHackForRounding((baseCost * (Math.pow(1.15D, level) - 1.0d)) / 0.15d);
			}
		}));
	}

	/** Adds formulas for Metal resource and income calculations. */
	@Override
	public void addMetalFormula() {
		formulas.put(ResourceType.Metal, new FormulaWrapper(0.0d, 100.0d, new Formula() {
			private static final long	serialVersionUID	= -6714763910461930868L;

			/**
			 * Basic formula based on a baseCost and a level to calculate the next building cost.
			 * 
			 * @param baseCost the base cost of the building
			 * @param level the desired level to calculate the cost of.
			 * @return the calculated next cost.
			 */
			@Override
			public double getNextCostForLevel(double baseCost, int level) {
				return lameHackForRounding(lameHackForRounding(baseCost * (Math.pow(1.15d, level))));
			}

			/**
			 * Basic formula based on a baseCost and a level to calculate the global building cost.
			 * 
			 * @param baseCost the base cost of the building
			 * @param level the current building level to calculate the global cost of.
			 * @return the calculated global cost.
			 */
			@Override
			public double getGlobalCostForLevel(double baseCost, int level) {
				return lameHackForRounding((baseCost * (Math.pow(1.15D, level) - 1.0d)) / 0.15d);
			}
		}));
	}

	/** Adds formulas for Tool resource and income calculations. */
	@Override
	public void addToolFormula() {
		formulas.put(ResourceType.Tool, new FormulaWrapper(0.5d, 35.0d, new Formula() {
			private static final long	serialVersionUID	= 1895945245459361747L;

			/**
			 * Basic formula based on a baseCost and a level to calculate the next building cost.
			 * 
			 * @param baseCost the base cost of the building
			 * @param level the desired level to calculate the cost of.
			 * @return the calculated next cost.
			 */
			@Override
			public double getNextCostForLevel(double baseCost, int level) {
				return lameHackForRounding(lameHackForRounding(baseCost * (Math.pow(1.15d, level))));
			}

			/**
			 * Basic formula based on a baseCost and a level to calculate the global building cost.
			 * 
			 * @param baseCost the base cost of the building
			 * @param level the current building level to calculate the global cost of.
			 * @return the calculated global cost.
			 */
			@Override
			public double getGlobalCostForLevel(double baseCost, int level) {
				return lameHackForRounding((baseCost * (Math.pow(1.15D, level) - 1.0d)) / 0.15d);
			}

			/**
			 * Basic formula based on a baseIncrement and a level to calculate the global building Increment.
			 * 
			 * @param baseIncrement the base cost of the building
			 * @param level the current building level to calculate the global cost of.
			 * @return the calculated global cost.
			 */
			@Override
			public double getGlobalIncrementForLevel(double baseIncrement, int level) {
				return lameHackForRounding(baseIncrement * level);
			}
		}));
	}
}
