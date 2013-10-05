package me.hopto.patriarch.incrementer.data;

import java.util.ArrayList;
import java.util.List;

import me.hopto.patriarch.incrementer.data.building.Building;
import me.hopto.patriarch.incrementer.data.building.LumberJack;
import me.hopto.patriarch.incrementer.data.building.Miner;
import me.hopto.patriarch.incrementer.data.calculator.IncrementCalculator;
import me.hopto.patriarch.incrementer.data.resource.Metal;
import me.hopto.patriarch.incrementer.data.resource.Resource;
import me.hopto.patriarch.incrementer.data.resource.Wood;

public class Built {

	IncrementCalculator IncrementCalculator;
	Resource wood;
	Resource metal;
	Miner miner;
	LumberJack lumberJack;
	List<Building> woodIncrementingBuildings;
	List<Building> metalIncrementingBuildings;
	
	public Built() {
		wood = new Wood(0.0d,0.0d);
		metal = new Metal(0.0d,0.0d);
		lumberJack = new LumberJack(0, 0.1d);
		miner = new Miner(0, 0.5d);
		woodIncrementingBuildings = new ArrayList<Building>();
		woodIncrementingBuildings.add(lumberJack);
		metalIncrementingBuildings = new ArrayList<Building>();
		metalIncrementingBuildings.add(miner);
		IncrementCalculator = new IncrementCalculator();
	}
	
	public void incrementAll() {
		wood.increment();
		metal.increment();
		System.out.println("[DEBUG] " +this);
	}
	
	public void levelUpMiner(){
		miner.levelUp();
		System.out.println("[DEBUG] Leveling miner to " + miner.getLevel());
		metal.updateIncrementValue(IncrementCalculator.getIncrement(metalIncrementingBuildings));
	}
	
	public void levelUpLumberJack(){
		lumberJack.levelUp();
		System.out.println("[DEBUG] Leveling lumber jack to " + lumberJack.getLevel());
		wood.updateIncrementValue(IncrementCalculator.getIncrement(woodIncrementingBuildings));
	}
	
	/**
	 * Basic override for debugging purposes.
	 */
	@Override
	public String toString() {
		return new StringBuffer(30).append("[").append(this.getClass().getSimpleName())
				.append("]\n\t").append(wood).append("\n\t").append(metal).toString();
	}
}
