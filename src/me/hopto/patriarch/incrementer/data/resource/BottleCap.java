package me.hopto.patriarch.incrementer.data.resource;

/**
 * A fallout bottle caps :p.
 */
public class BottleCap {

	/** Current quantity of resource. */
	private double quantity;
	/** Current increment by this the quantity of resource. */
	private double increment;

	/**
	 * Init this resource stack.
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 */
	public BottleCap(double startingQuantity, double startingIncrement) {
		this.quantity = startingQuantity;
		this.increment = startingIncrement;
	}
	
	public void increment() {
		quantity += increment;
	}
	
	public double getQuantity() {
		return quantity;
	}
}
