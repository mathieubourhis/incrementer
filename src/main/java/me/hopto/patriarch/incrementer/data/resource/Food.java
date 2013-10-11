package me.hopto.patriarch.incrementer.data.resource;

/**
 * Food Resource.
 * 
 * @see {@link Resource}
 */
public class Food extends Resource {
	private static final long	serialVersionUID	= -4716902641231987512L;

	/** Default constructor for serialization. */
	public Food() {
		super();
	}

	/**
	 * Init this resource stack.
	 * 
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 * @see {@link Resource#Resource}
	 */
	public Food(double startingQuantity, double startingIncrement) {
		super(startingQuantity, startingIncrement);
		super.type = ResourceType.Food;
	}

}
