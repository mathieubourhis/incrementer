package me.hopto.patriarch.incrementer.core.calculator;

import java.io.Serializable;
import java.util.List;
import me.hopto.patriarch.incrementer.core.building.Building;
import me.hopto.patriarch.incrementer.core.resource.Resource;

// TODO this should be a spring stateless service, gotta add maven or dl libs.
public class IncrementCalculator implements Serializable {
	private static final long	serialVersionUID	= 3567474099928109902L;

	public IncrementCalculator() {

	}

	public void incrementResources(Building building, List<Resource> resources) {
		for (Resource resource : resources) {
			FormulaWrapper formula = building.findFormulaForResource(resource.getType());
			resource.updateIncrementValue(formula.getNextIncrement());
		}
	}

	public void buy(Building building, List<Resource> resources) {
		for (Resource resource : resources) {
			FormulaWrapper formula = building.findFormulaForResource(resource.getType());
			resource.buy(formula.getNextCostForLevel(building.getLevel()));
		}
	}

	/**
	 * checks wether an item can be bought
	 * 
	 * @return the current level
	 */
	public boolean canBuy(Building building, List<Resource> resources) {
		boolean canBuy = true;
		for (Resource resource : resources) {
			FormulaWrapper formula = building.findFormulaForResource(resource.getType());
			canBuy &= formula.getNextCostForLevel(building.getLevel()) <= resource.getQuantity();
			if (!canBuy) break;
		}

		return canBuy;
	}
}
