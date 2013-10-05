package me.hopto.patriarch.incrementer.game;


public class GameMain {
	
	public static void main(String[] args) {
		GameThread thread = new GameThread();
	    thread.start();
	    long hasSlept = 0;
	    while( thread.isAlive() ) {
	      try {
	        Thread.sleep(200);
	        hasSlept+=200;
	        if (hasSlept%1000 == 400) {
		        thread.levelUpMiner();
	        } else if (hasSlept%600 == 400) {
	        	thread.levelUpLumberJack();
	        }
	      }
	      catch (InterruptedException ex) {}
	    }
	}
}
