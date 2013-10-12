package me.hopto.patriarch.incrementer.core.building;

import me.hopto.patriarch.incrementer.core.calculator.Formula;
import me.hopto.patriarch.incrementer.core.calculator.FormulaWrapper;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;

/**
 * Finds metal in rivers
 * 
 * @see {@link Building}
 */
public class MetalDigger extends Building {
	private static final long	serialVersionUID	= -7600326882064946876L;

	/** Default constructor for serialization. */
	public MetalDigger() {
		this(0);
	}

	/**
	 * Init this building's level.
	 * 
	 * @param startingLevel starting level of building
	 * @see {@link Building#Building}
	 */
	public MetalDigger(int startingLevel) {
		super(startingLevel);
		buildingType = BuildingType.MetalDigger;
	}

	@Override
	public void addFoodFormula() {
		formulas.put(ResourceType.Food, new FormulaWrapper(ResourceType.Food, 0.0d, 25.0d, new Formula() {
			private static final long	serialVersionUID	= -7431220885156775280L;

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
		formulas.put(ResourceType.Wood, new FormulaWrapper(ResourceType.Wood, 0.0d, 25.0d, new Formula() {
			private static final long	serialVersionUID	= 6177630148588622037L;

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
		formulas.put(ResourceType.Metal, new FormulaWrapper(ResourceType.Metal, 0.1d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= -1383467645202175881L;

			@Override
			public double getGlobalIncrementForLevel(double baseIncrement, int level) {
				return lameHackForRounding(baseIncrement * level);
			}
		}));
	}

	@Override
	public void addToolFormula() {
		formulas.put(ResourceType.Tool, new FormulaWrapper(ResourceType.Tool, 0.0d, 0.0d, new Formula() {
			private static final long	serialVersionUID	= 8833566662588676604L;
		}));

	}
}
