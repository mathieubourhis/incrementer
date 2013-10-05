package me.hopto.patriarch.incrementer.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.hopto.patriarch.incrementer.data.building.Building;
import me.hopto.patriarch.incrementer.data.building.LumberJack;
import me.hopto.patriarch.incrementer.data.building.Miner;
import me.hopto.patriarch.incrementer.data.calculator.IncrementCalculator;
import me.hopto.patriarch.incrementer.data.resource.Metal;
import me.hopto.patriarch.incrementer.data.resource.Resource;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;
import me.hopto.patriarch.incrementer.data.resource.Wood;

import org.apache.log4j.Logger;

public class Built implements Serializable {
	private static final long serialVersionUID = 8766412728828956639L;
	private static Logger logger = Logger.getLogger(Built.class);
	IncrementCalculator IncrementCalculator;
	Resource wood;
	Resource metal;
	Miner miner;
	LumberJack lumberJack;
	List<Building> woodIncrementingBuildings;
	List<Building> metalIncrementingBuildings;

	public Built() {
		wood = new Wood(0.0d, 0.0d);
		metal = new Metal(0.0d, 0.0d);
		lumberJack = new LumberJack(0, 0.2d, 0.0d);
		miner = new Miner(0, 0.5d, 3.0d);
		woodIncrementingBuildings = new ArrayList<Building>();
		woodIncrementingBuildings.add(lumberJack);
		metalIncrementingBuildings = new ArrayList<Building>();
		metalIncrementingBuildings.add(miner);
		IncrementCalculator = new IncrementCalculator();
	}

	public void incrementAll(double ratio) {
		wood.increment(ratio);
		metal.increment(ratio);
		if (logger.isDebugEnabled()) {
			logger.debug(wood);
			logger.debug(metal);
		}

	}

	public void levelUpMiner() {
		if (miner.canBuy(metal.getQuantity())) {
			metal.buy(miner.getCost());
			miner.levelUp();
			metal.updateIncrementValue(IncrementCalculator
					.getIncrement(metalIncrementingBuildings));
		}
	}

	public void levelUpLumberJack() {
		if (lumberJack.canBuy(wood.getQuantity())) {
			wood.buy(lumberJack.getCost());
			lumberJack.levelUp();
			wood.updateIncrementValue(IncrementCalculator
					.getIncrement(woodIncrementingBuildings));
		}
	}

	/**
	 * Basic override for debugging purposes.
	 */
	@Override
	public String toString() {
		return new StringBuffer(30).append("[")
				.append(this.getClass().getSimpleName()).append("]\n\t")
				.append(wood).append("\n\t").append(metal).toString();
	}

	/**
	 * Returns a formatted quantity of resources
	 * 
	 * @return the formatted quantity of resources
	 */
	public String getFormattedResourceQuantity(ResourceType resourceType) {
		switch (resourceType) {
		case Metal:
			return String.format("%.2f", metal.getQuantity());
		case Wood:
			return String.format("%.2f", wood.getQuantity());
		default:
			return "";
		}
	}
}
