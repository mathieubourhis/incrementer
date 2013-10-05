package me.hopto.patriarch.incrementer.data.calculator;

import java.util.List;

import me.hopto.patriarch.incrementer.data.building.Building;


// TODO this should be a spring stateless service, gotta add maven or dl libs.
public class IncrementCalculator {
	
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
