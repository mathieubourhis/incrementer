package me.hopto.patriarch.incrementer.core.building;

import me.hopto.patriarch.incrementer.core.calculator.Formula;
import me.hopto.patriarch.incrementer.core.calculator.FormulaWrapper;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;

/**
 * @see {@link Building}
 */
public class BerryPicker extends Building {
	private static final long	serialVersionUID	= 4038925478742169348L;

	/** Default constructor for serialization. */
	public BerryPicker() {
		this(0);
	}

	/**
	 * Init this building's level.
	 * 
	 * @param startingLevel starting level of building
	 * @see {@link Building#Building}
	 */
	public BerryPicker(int startingLevel) {
		super(startingLevel);
		buildingType = BuildingType.BerryPicker;
	}

	@Override
	public void addFoodFormula() {
		formulas.put(ResourceType.Food, new FormulaWrapper(ResourceType.Food, 0.5d, 15.0d, new Formula() {
			private static final long	serialVersionUID	= 8601143026598137879L;

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
			private static final long	serialVersionUID	= -2586630522603070578L;
		}));

	}

	@Override
	public void addMetalFormula() {
		formulas.put(ResourceType.Metal, new FormulaWrapper(ResourceType.Metal, 0.0d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= 8817594731379876050L;
		}));

	}

	@Override
	public void addToolFormula() {
		formulas.put(ResourceType.Tool, new FormulaWrapper(ResourceType.Tool, 0.0d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= 1755096260362046997L;
		}));
	}
}
