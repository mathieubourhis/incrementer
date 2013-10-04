package me.hopto.patriarch.incrementer.data;

import me.hopto.patriarch.incrementer.data.building.Pub;
import me.hopto.patriarch.incrementer.data.calculator.BottleCapIncrementCalculator;
import me.hopto.patriarch.incrementer.data.resource.BottleCap;

public class Built {

	BottleCapIncrementCalculator bottleCapIncrementCalculator;
	BottleCap caps;
	Pub pub;
	
	public Built() {
		caps = new BottleCap(0.0d,0.0d);
		pub = new Pub(0, 0.5d);
		bottleCapIncrementCalculator = new BottleCapIncrementCalculator();
	}
	
	public void incrementAll() {
		caps.increment();
	}
	
	public void levelUpPub(){
		pub.levelUp();
		caps.updateIncrementValue(bottleCapIncrementCalculator.getIncrement(pub));
	}
	
	/**
	 * Basic override for debugging purposes.
	 */
	@Override
	public String toString() {
		return new StringBuffer(30).append("[").append(this.getClass().getSimpleName())
				.append("]\n\t").append(caps).toString();
	}
}
