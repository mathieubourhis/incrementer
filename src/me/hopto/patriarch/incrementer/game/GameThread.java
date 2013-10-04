package me.hopto.patriarch.incrementer.game;

import me.hopto.patriarch.incrementer.data.Built;

public class GameThread extends Thread {

	private Built built;

	public GameThread() {
		built = new Built();
	}
	
	public void levelUpPub() {
		System.out.println("[Infos] Leveling Pub");
		built.levelUpPub();
	}
	

	public void levelUpTrashBin() {
		System.out.println("[Infos] Leveling TrashBin");
		built.levelUpTrashBin();
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();

		while (System.currentTimeMillis() < (start + (1000 * 10))) {

			built.incrementAll();

			System.out.println(built);

			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
			}
		}
	}
}
