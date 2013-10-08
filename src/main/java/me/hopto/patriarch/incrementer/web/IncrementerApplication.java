package me.hopto.patriarch.incrementer.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class IncrementerApplication extends WebApplication {
	public IncrementerApplication() {

	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() {
		return Incrementer.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		// Ok, so i have to much information within the ajax debug window, it
		// slows down A LOT the page.

		// So i can either remove it (even in deployment mode)
		// Seriously I really dont care about it.
		getDebugSettings().setAjaxDebugModeEnabled(false);
		// getDebugSettings().setDevelopmentUtilitiesEnabled(false);

		// Or I can up level of info to WARN/ERROR/FATAL in my log4j.
		// Its really the amount of info that bugs me. I refresh a lot the page.

		// Or I can find a way to clear it in my threads. But that's a bit too
		// much (JS : Wicket.Ajax.DebugWindow.clearLog();)
	}
}