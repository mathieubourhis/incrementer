package me.hopto.patriarch.incrementer.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Incrementer extends WebPage {
	private static final long serialVersionUID = -5659419987245005726L;

	public Incrementer(final PageParameters parameters) {
		super(parameters);
        add(new Label("message", "Hello World!"));
    }
}