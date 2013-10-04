package me.hopto.patriarch.incrementer.game;

import me.hopto.patriarch.incrementer.data.Built;

public class GameThread extends Thread {

	private Built built;

	public GameThread() {
		built = new Built();
	}
	
	public void levelUpPub() {
		built.levelUpPub();
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();

		while (System.currentTimeMillis() < (start + (1000 * 5))) {

			built.incrementAll();

			System.out.println(built);

			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
			}
		}
	}
}
