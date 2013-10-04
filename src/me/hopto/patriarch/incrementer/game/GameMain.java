package me.hopto.patriarch.incrementer.game;


public class GameMain {
	
	public static void main(String[] args) {
		GameThread thread = new GameThread();
	    thread.start();
	    
	    while( thread.isAlive() ) {
	      try {
	        Thread.sleep(800);
	      }
	      catch (InterruptedException ex) {}
	    }
	}
}
