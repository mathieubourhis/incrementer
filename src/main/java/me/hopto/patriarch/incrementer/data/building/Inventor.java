package me.hopto.patriarch.incrementer.data.building;

import me.hopto.patriarch.incrementer.data.calculator.Formula;
import me.hopto.patriarch.incrementer.data.calculator.FormulaWrapper;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

/**
 * 
 * @see {@link Building}
 */
public class Inventor extends Building {
	private static final long	serialVersionUID	= 2733711938756765142L;

	/** Default constructor for serialization. */
	public Inventor() {
		this(0);
	}

	/**
	 * Init this building's level.
	 * 
	 * @param startingLevel starting level of building
	 * @param baseIncrement starting increment given by building
	 * @see {@link Building#Building}
	 */
	public Inventor(int startingLevel) {
		super(startingLevel);
		buildingType = BuildingType.Inventor;
	}

	@Override
	public void addFoodFormula() {
		formulas.put(ResourceType.Food, new FormulaWrapper(ResourceType.Food, 0.0d, 50.0d, new Formula() {
			private static final long	serialVersionUID	= 954550748473206722L;

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
		formulas.put(ResourceType.Wood, new FormulaWrapper(ResourceType.Wood, 0.0d, 40.0d, new Formula() {
			private static final long	serialVersionUID	= 2725507202521691750L;

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
	public void addMetalFormula() {
		formulas.put(ResourceType.Metal, new FormulaWrapper(ResourceType.Metal, 0.0d, 60.0d, new Formula() {
			private static final long	serialVersionUID	= -8646879104938688035L;

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
	public void addToolFormula() {
		formulas.put(ResourceType.Tool, new FormulaWrapper(ResourceType.Tool, 0.1d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= 7988956390583673173L;

			@Override
			public double getGlobalIncrementForLevel(double baseIncrement, int level) {
				return lameHackForRounding(baseIncrement * level);
			}
		}));
	}
}
