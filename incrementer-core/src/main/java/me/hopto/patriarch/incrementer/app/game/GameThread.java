package me.hopto.patriarch.incrementer.app.game;

import me.hopto.patriarch.incrementer.app.data.Built;
import me.hopto.patriarch.incrementer.core.building.BuildingType;

public class GameThread extends Thread {

	private Built	built;

	public GameThread() {
		built = new Built();
	}

	public void levelUp(BuildingType type) {
		built.levelUp(type);
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();

		while (System.currentTimeMillis() < (start + (1000 * 5))) {

			built.incrementAll(0.5d);

			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
			}
		}
	}
}
