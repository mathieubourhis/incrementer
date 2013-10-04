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
	
	/**
	 * Increment a resource's quantity by an increment value.
	 */
	public void increment() {
		quantity += increment;
	}
	
	/**
	 * Get the current quantity.
	 * @return the current quantity.
	 */
	public double getQuantity() {
		return quantity;
	}
	
	/** 
	 * Updates the increment's value.
	 * @param updatedIncrement the new increment;
	 */
	public void updateIncrementValue(double updatedIncrement) {
		this.increment = updatedIncrement;
	}
	
	/**
	 * Basic override for debugging purposes.
	 */
	@Override
	public String toString() {
		return new StringBuffer(30).append("[").append(this.getClass().getSimpleName())
				.append("] [Quantity: ").append(quantity)
				.append("] [Increment: ").append(increment)
				.append("]").toString();
	}
}
