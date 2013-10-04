package me.hopto.patriarch.incrementer.data;

import me.hopto.patriarch.incrementer.data.resource.BottleCap;

public class Built {

	BottleCap caps;
	
	public Built() {
		caps = new BottleCap(0.0d,0.5d);
	}
	
	public void incrementAll() {
		caps.increment();
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
