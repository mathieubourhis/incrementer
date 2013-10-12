package me.hopto.patriarch.incrementer.core.resource;

/**
 * Food Resource.
 * 
 * @see {@link Resource}
 */
public class Food extends Resource {
	private static final long	serialVersionUID	= -4716902641231987512L;

	/** Default constructor (init). */
	public Food() {
		super();
	}

	/**
	 * Init this resource stack (load).
	 * 
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 */
	public Food(double startingQuantity, double startingIncrement) {
		super(startingQuantity, startingIncrement);
		super.type = ResourceType.Food;
	}
}
