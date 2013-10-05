package me.hopto.patriarch.incrementer.data.resource;

/** 
 * Wood Resource. 
 * @see {@link Resource} 
 */
public class Wood extends Resource {

	/**
	 * Init this resource stack.
	 * @param startingQuantity starting quantity of resource.
	 * @param incrementBy starting increment by this the quantity of resource.
	 * @see {@link Resource#Resource}
	 */
	public Wood(double startingQuantity, double startingIncrement) {
		super(startingQuantity, startingIncrement);
		super.type = ResourceType.Wood;
	}

}
