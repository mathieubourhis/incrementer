package me.hopto.patriarch.incrementer.data.resource;

/**
 * Metal Resource.
 * 
 * @see {@link Resource}
 */
public class Metal extends Resource {
	private static final long	serialVersionUID	= 818423470852393251L;

	/** Default constructor for serialization. */
	public Metal() {
		super();
	}

	/**
	 * Init this resource stack.
	 * 
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 * @see {@link Resource#Resource}
	 */
	public Metal(double startingQuantity, double startingIncrement) {
		super(startingQuantity, startingIncrement);
		super.type = ResourceType.Metal;
	}

}
