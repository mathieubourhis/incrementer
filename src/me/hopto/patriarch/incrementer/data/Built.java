package me.hopto.patriarch.incrementer.data;

import java.util.ArrayList;
import java.util.List;

import me.hopto.patriarch.incrementer.data.building.Building;
import me.hopto.patriarch.incrementer.data.building.Pub;
import me.hopto.patriarch.incrementer.data.building.TrashBin;
import me.hopto.patriarch.incrementer.data.calculator.BottleCapIncrementCalculator;
import me.hopto.patriarch.incrementer.data.resource.BottleCap;

public class Built {

	BottleCapIncrementCalculator bottleCapIncrementCalculator;
	BottleCap caps;
	Pub pub;
	TrashBin trashBin;
	List<Building> bottleCapIncrementingBuildings;
	
	public Built() {
		caps = new BottleCap(0.0d,0.0d);
		trashBin = new TrashBin(0, 0.1d);
		pub = new Pub(0, 0.5d);
		bottleCapIncrementingBuildings = new ArrayList<Building>();
		bottleCapIncrementingBuildings.add(trashBin);
		bottleCapIncrementingBuildings.add(pub);
		bottleCapIncrementCalculator = new BottleCapIncrementCalculator();
	}
	
	public void incrementAll() {
		caps.increment();
	}
	
	public void levelUpPub(){
		pub.levelUp();
		caps.updateIncrementValue(bottleCapIncrementCalculator.getIncrement(bottleCapIncrementingBuildings));
	}
	
	public void levelUpTrashBin(){
		trashBin.levelUp();
		caps.updateIncrementValue(bottleCapIncrementCalculator.getIncrement(bottleCapIncrementingBuildings));
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
