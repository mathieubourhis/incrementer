package me.hopto.patriarch.incrementer.core.resource;

/**
 * Tool Resource.
 * 
 * @see {@link Resource}
 */
public class Tool extends Resource {
	private static final long	serialVersionUID	= 2688702428894968224L;

	/** Default constructor (init). */
	public Tool() {
		super();
	}

	/**
	 * Init this resource stack (load).
	 * 
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 */
	public Tool(double startingQuantity, double startingIncrement) {
		super(startingQuantity, startingIncrement);
		super.type = ResourceType.Tool;
	}
}
