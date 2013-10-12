package me.hopto.patriarch.incrementer.core.resource;

/**
 * Tool Resource.
 * 
 * @see {@link Resource}
 */
public class Tool extends Resource {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2688702428894968224L;

	/** Default constructor for serialization. */
	public Tool() {
		super();
	}

	/**
	 * Init this resource stack.
	 * 
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 * @see {@link Resource#Resource}
	 */
	public Tool(double startingQuantity, double startingIncrement) {
		super(startingQuantity, startingIncrement);
		super.type = ResourceType.Tool;
	}

}
