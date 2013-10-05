package me.hopto.patriarch.incrementer.game;

import me.hopto.patriarch.incrementer.data.Built;

public class GameThread extends Thread {

	private Built built;

	public GameThread() {
		built = new Built();
	}

	public void levelUpMiner() {
		built.levelUpMiner();
	}

	public void levelUpLumberJack() {
		built.levelUpLumberJack();
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
