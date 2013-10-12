package me.hopto.patriarch.incrementer.core.resource;

/**
 * Wood Resource.
 * 
 * @see {@link Resource}
 */
public class Wood extends Resource {
	private static final long	serialVersionUID	= 5837388679197667282L;

	/** Default constructor (init). */
	public Wood() {
		super();
	}

	/**
	 * Init this resource stack (load).
	 * 
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 */
	public Wood(double startingQuantity, double startingIncrement) {
		super(startingQuantity, startingIncrement);
		super.type = ResourceType.Wood;
	}
}
