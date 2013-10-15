package me.hopto.patriarch.incrementer.core.building;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import me.hopto.patriarch.incrementer.core.formula.FormulaWrapper;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;
import com.google.common.base.Objects;

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

	//TODO Consider formulas in equals / toString / hash ? 
	/**
	 * @param obj Object to be compared to me for equality.
	 * @return {@code true} if provided object is considered equal to me or {@code false} if provided object is not considered equal to me.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) return false;

		final Building other = (Building) obj;
		return equal(this.level, other.level) && equal(this.buildingType, other.buildingType);
	}

	/** @return basic HashCode. */
	@Override
	public int hashCode() {
		return Objects.hashCode(this.level, this.buildingType);
	}

	/** @return basic toString. */
	@Override
	public String toString() {
		return toStringHelper(this).addValue(this.buildingType).addValue(this.level).toString();
	}
}
