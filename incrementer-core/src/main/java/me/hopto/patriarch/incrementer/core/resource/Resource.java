package me.hopto.patriarch.incrementer.core.resource;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import com.google.common.base.Objects;

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
	 * For initialization purpose only. Init the quantity of this Resource.
	 * 
	 * @param quantity the quantity of this Resource.
	 */
	public void setInitialQuantity(double quantity) {
		this.quantity = quantity;
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

	/**
	 * @param obj Object to be compared to me for equality.
	 * @return {@code true} if provided object is considered equal to me or {@code false} if provided object is not considered equal to me.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) return false;

		BigDecimal thisQuantity = BigDecimal.valueOf(quantity);
		BigDecimal thisIncrement = BigDecimal.valueOf(increment);

		final Resource other = (Resource) obj;
		BigDecimal otherQuantity = BigDecimal.valueOf(other.quantity);
		BigDecimal otherIncrement = BigDecimal.valueOf(other.increment);

		return equal(thisQuantity.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(), otherQuantity.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()) && equal(thisIncrement.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString(), otherIncrement.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()) && equal(this.type, other.type);
	}

	/** @return basic HashCode. */
	@Override
	public int hashCode() {
		BigDecimal thisQuantity = BigDecimal.valueOf(quantity);
		BigDecimal thisIncrement = BigDecimal.valueOf(increment);
		return Objects.hashCode(thisQuantity.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue(), thisIncrement.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
	}

	/** @return basic toString. */
	@Override
	public String toString() {
		BigDecimal thisQuantity = BigDecimal.valueOf(quantity);
		BigDecimal thisIncrement = BigDecimal.valueOf(increment);
		return toStringHelper(this).addValue(this.type).addValue(thisQuantity.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()).addValue(thisIncrement.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()).toString();
	}
}
