package me.hopto.patriarch.incrementer.data.resource;

import java.io.Serializable;

/**
 * A resource
 */
public abstract class Resource implements Serializable {
	private static final long	serialVersionUID	= -6448923455673479126L;

	/** Current quantity of resource. */
	double										quantity;
	/** Current increment by this the quantity of resource. */
	double										increment;
	/** This resource's type. */
	ResourceType							type;

	/** Default constructor for serialization. */
	public Resource() {
		this.quantity = 0;
		this.increment = 0.0d;
		this.type = null;
	}

	/**
	 * Init this resource stack.
	 * 
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 */
	public Resource(double startingQuantity, double startingIncrement) {
		this.quantity = startingQuantity;
		this.increment = startingIncrement;
	}

	/**
	 * Increment a resource's quantity by an increment value.
	 */
	public void increment(double ratio) {
		quantity += (increment * ratio);
	}

	/**
	 * decrement a resource's quantity by an cost value.
	 */
	public void buy(double cost) {
		quantity -= cost;
	}

	/**
	 * Get the current quantity.
	 * 
	 * @return the current quantity.
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * Updates the increment's value.
	 * 
	 * @param updatedIncrement the new increment;
	 */
	public void updateIncrementValue(double updatedIncrement) {
		this.increment += updatedIncrement;
	}

	/**
	 * Basic override for debugging purposes.
	 * 
	 * @TODO Beware String.format is really heavy for almost nothing.
	 * @link http://stackoverflow.com/questions/513600/should-i-use-javas-string- format-if-performance-is-important
	 */
	@Override
	public String toString() {

		return new StringBuffer(30).append("[").append(this.getClass().getSimpleName()).append("] [Quantity: ").append(String.format("%.2f", quantity)).append("] [Increment: ").append(String.format("%.2f", increment)).append("]").toString();
	}

	/**
	 * Return the type of resource we are using
	 * 
	 * @return the type of resource we are using
	 */
	public ResourceType getType() {
		return type;
	}

	public void manualIncrement() {
		quantity++;
	}
}
