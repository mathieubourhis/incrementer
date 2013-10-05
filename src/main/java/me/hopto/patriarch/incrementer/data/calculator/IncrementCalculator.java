package me.hopto.patriarch.incrementer.data.calculator;

import java.io.Serializable;
import java.util.List;

import me.hopto.patriarch.incrementer.data.building.Building;

// TODO this should be a spring stateless service, gotta add maven or dl libs.
public class IncrementCalculator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3567474099928109902L;

	public IncrementCalculator() {

	}

	public double getIncrement(List<Building> bottleCapIncrementinBuildings) {
		double increment = 0.0d;
		for (Building building : bottleCapIncrementinBuildings) {
			increment += building.getIncrement();
		}
		return increment;
	}
}
