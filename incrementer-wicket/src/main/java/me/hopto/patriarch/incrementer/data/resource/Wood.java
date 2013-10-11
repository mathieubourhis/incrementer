package me.hopto.patriarch.incrementer.data.resource;

/**
 * Wood Resource.
 * 
 * @see {@link Resource}
 */
public class Wood extends Resource {
	private static final long	serialVersionUID	= 5837388679197667282L;

	/** Default constructor for serialization. */
	public Wood() {
		super();
	}

	/**
	 * Init this resource stack.
	 * 
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 * @see {@link Resource#Resource}
	 */
	public Wood(double startingQuantity, double startingIncrement) {
		super(startingQuantity, startingIncrement);
		super.type = ResourceType.Wood;
	}

}
