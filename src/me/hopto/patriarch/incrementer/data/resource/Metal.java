package me.hopto.patriarch.incrementer.data.resource;


/** 
 * Metal Resource. 
 * @see {@link Resource} 
 */
public class Metal extends Resource {

	/**
	 * Init this resource stack.
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 * @see {@link Resource#Resource}
	 */
	public Metal(double startingQuantity, double startingIncrement) {
		super(startingQuantity, startingIncrement);
		super.type = ResourceType.Metal;
	}

}
