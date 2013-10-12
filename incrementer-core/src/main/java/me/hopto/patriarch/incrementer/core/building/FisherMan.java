package me.hopto.patriarch.incrementer.core.building;

import me.hopto.patriarch.incrementer.core.formula.Formula;
import me.hopto.patriarch.incrementer.core.formula.FormulaWrapper;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;

/** A fisherman will produce better amounts of food. */
public class FisherMan extends Building {
	private static final long	serialVersionUID	= -3203852755166742102L;

	/** Default constructor (init). */
	public FisherMan() {
		this(0);
	}

	/**
	 * Init this building's level (load).
	 * 
	 * @param startingLevel starting level of building
	 */
	public FisherMan(int startingLevel) {
		super(startingLevel);
		buildingType = BuildingType.FisherMan;
	}

	/** Adds formulas for food resource and income calculations. */
	@Override
	public void addFoodFormula() {
		formulas.put(ResourceType.Food, new FormulaWrapper(2.0d, 25.0d, new Formula() {
			private static final long	serialVersionUID	= 4282001913370688493L;

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

	/** Adds formulas for wood resource and income calculations. */
	@Override
	public void addWoodFormula() {
		formulas.put(ResourceType.Wood, new FormulaWrapper(0.0d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= -849589385610671048L;
		}));
	}

	/** Adds formulas for Metal resource and income calculations. */
	@Override
	public void addMetalFormula() {
		formulas.put(ResourceType.Metal, new FormulaWrapper(0.0d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= 4931260316030218640L;
		}));
	}

	/** Adds formulas for Tool resource and income calculations. */
	@Override
	public void addToolFormula() {
		formulas.put(ResourceType.Tool, new FormulaWrapper(0.0d, 15.0d, new Formula() {
			private static final long	serialVersionUID	= -4118434917738292949L;

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
}
