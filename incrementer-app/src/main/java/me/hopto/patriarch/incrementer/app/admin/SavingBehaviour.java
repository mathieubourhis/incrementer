package me.hopto.patriarch.incrementer.app.admin;

/**
 * Allows multiple strategies and behaviours for game saving.
 */
public interface SavingBehaviour {
	Save load();

	String save(Save o);
}
