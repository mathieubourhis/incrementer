package me.hopto.patriarch.incrementer.data.building;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import me.hopto.patriarch.incrementer.data.calculator.FormulaWrapper;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

public abstract class Building implements Serializable {
	private static final long					serialVersionUID	= 1931346693501761137L;

	/** Current level of building. */
	int																level;

	/** the kind of building this is. */
	BuildingType											buildingType;

	/** All formulas for this building. */
	Map<ResourceType, FormulaWrapper>	formulas;

	/** Default constructor for serialization. */
	// TODO check if default public empty constructor is really needed for
	// serialization.
	public Building() {
		this(0);
	}

	/**
	 * Init this building's level.
	 * 
	 * @param startingLevel starting level of building
	 */
	public Building(int startingLevel) {
		this.level = startingLevel;
		formulas = new HashMap<ResourceType, FormulaWrapper>();
		addFoodFormula();
		addWoodFormula();
		addMetalFormula();
		addToolFormula();
	}

	/** Increase this building's level. */
	public void levelUp() {
		this.level++;
	}

	/**
	 * Returns the current level
	 * 
	 * @return the current level
	 */
	public int getLevel() {
		return level;
	}

	public abstract void addFoodFormula();

	public abstract void addWoodFormula();

	public abstract void addMetalFormula();

	public abstract void addToolFormula();

	/**
	 * the kind of building this is.
	 * 
	 * @return the kind of building this is.
	 */
	public BuildingType getType() {
		return buildingType;
	}

	public FormulaWrapper findFormulaForResource(ResourceType resourceType) {
		return formulas.get(resourceType);
	}
}
