package me.hopto.patriarch.incrementer.data.calculator;

import me.hopto.patriarch.incrementer.data.building.Pub;


// TODO this should be a spring stateless service, gotta add maven or dl libs.
public class BottleCapIncrementCalculator {
	
	public BottleCapIncrementCalculator() {
		
	}
	
	public double getIncrement(Pub pub) {
		return pub.getIncrement();
	}
}
