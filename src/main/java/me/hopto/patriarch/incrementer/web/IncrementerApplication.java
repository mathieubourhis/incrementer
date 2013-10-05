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
    public Class<? extends Page>  getHomePage() {
        return Incrementer.class;
    }
    
	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
	}
}