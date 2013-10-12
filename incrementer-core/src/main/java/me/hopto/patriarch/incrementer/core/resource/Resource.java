package me.hopto.patriarch.incrementer.core.resource;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Defines a resource. Abstractions are package protected for simplicity.
 */
public abstract class Resource implements Serializable {
	private static final long	serialVersionUID	= -6448923455673479126L;

	/** Current quantity of resource. */
	private double						quantity;
	/** Current increment by this the quantity of resource. */
	private double						increment;
	/** This resource's type. */
	ResourceType							type;

	/** Default constructor (init). */
	public Resource() {
		this.quantity = 0;
		this.increment = 0.0d;
		this.type = null;
	}

	/**
	 * Init this resource stack (load).
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
	 * 
	 * @param ratio apply a ratio on the increment.
	 */
	public void increment(double ratio) {
		quantity += (increment * ratio);
	}

	/**
	 * Decrement a resource's quantity by an cost value.
	 * 
	 * @param cost the cost to decrement the quantity by.
	 */
	public void buy(double cost) {
		quantity -= cost;
	}

	/** @return the current quantity. */
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

	/** Basic override for debugging purposes. */
	@Override
	public String toString() {
		BigDecimal aQuantity = BigDecimal.valueOf(quantity);
		BigDecimal anIncrement = BigDecimal.valueOf(increment);
		return new StringBuffer(30).append("[").append(this.getClass().getSimpleName()).append("] [Quantity: ").append(aQuantity.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()).append("] [Increment: ").append(anIncrement.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()).append("]").toString();
	}

	/**
	 * Return the type of resource we are using
	 * 
	 * @return the type of resource we are using
	 */
	public ResourceType getType() {
		return type;
	}

	/** Increment only one unit. */
	public void manualIncrement() {
		quantity++;
	}
}
