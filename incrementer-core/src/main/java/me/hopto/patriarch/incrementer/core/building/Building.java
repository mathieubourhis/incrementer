package me.hopto.patriarch.incrementer.core.building;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import me.hopto.patriarch.incrementer.core.formula.FormulaWrapper;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;

/**
 * Defines a building. Abstractions are package protected for simplicity.
 */
public abstract class Building implements Serializable {
	private static final long					serialVersionUID	= 1931346693501761137L;

	/** Current level of building. */
	int																level;

	/** the kind of building this is. */
	BuildingType											buildingType;

	/** All formulas for this building. */
	Map<ResourceType, FormulaWrapper>	formulas;

	/** Default constructor (init). */
	public Building() {
		this(0);
	}

	/**
	 * Init this building's level (load).
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

	/**
	 * Adds formulas for food resource and income calculations.
	 * 
	 * @see {@link Building#addFoodFormula}
	 */
	abstract void addFoodFormula();

	/**
	 * Adds formulas for wood resource and income calculations.
	 * 
	 * @see {@link Building#addWoodFormula}
	 */
	abstract void addWoodFormula();

	/**
	 * Adds formulas for Metal resource and income calculations.
	 * 
	 * @see {@link Building#addMetalFormula}
	 */
	abstract void addMetalFormula();

	/**
	 * Adds formulas for Tool resource and income calculations.
	 * 
	 * @see {@link Building#addToolFormula}
	 */
	abstract void addToolFormula();

	/**
	 * the kind of building this is.
	 * 
	 * @return the kind of building this is.
	 */
	public BuildingType getType() {
		return buildingType;
	}

	/**
	 * Finds formulas for a resource.
	 * 
	 * @param resourceType the resource to find the formulas of
	 * @return the found formulas
	 */
	public FormulaWrapper findFormulaForResource(ResourceType resourceType) {
		return formulas.get(resourceType);
	}
}
