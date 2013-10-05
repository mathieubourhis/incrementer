package me.hopto.patriarch.incrementer.web;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.web.components.ResourceLabel;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

public class Incrementer extends WebPage {
	private static final long serialVersionUID = -5659419987245005726L;

	private Built built;
	long hasSlept;

	public Incrementer(final PageParameters parameters) {

		super(parameters);
		built = new Built();
		hasSlept = 0;
		final ResourceLabel label = new ResourceLabel("message", built);
		add(label);
		label.setOutputMarkupId(true);

		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(500d)) {
			private static final long serialVersionUID = 1100349890208440665L;

			/**
			 * @see org.apache.wicket.ajax.AbstractAjaxTimerBehavior#onTimer(org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void onTimer(AjaxRequestTarget target) {
				built.incrementAll();
			}
		});

		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(200d)) {
			private static final long serialVersionUID = -1487136610861370272L;

			/**
			 * @see org.apache.wicket.ajax.AbstractAjaxTimerBehavior#onTimer(org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void onTimer(AjaxRequestTarget target) {
				hasSlept += 200;
				if (hasSlept % 1000 == 400) {
					built.levelUpMiner();
				} else if (hasSlept % 600 == 400) {
					built.levelUpLumberJack();
				}
				target.add(label);
			}
		});
	}
}