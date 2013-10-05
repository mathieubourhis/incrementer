package me.hopto.patriarch.incrementer.web;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;
import me.hopto.patriarch.incrementer.web.components.BuildingStats;
import me.hopto.patriarch.incrementer.web.components.ResourceLabel;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

public class Incrementer extends WebPage {
	private static final long serialVersionUID = -5659419987245005726L;

	private Built built;
	long hasSlept;

	private ResourceLabel woodLabel;

	private ResourceLabel metalLabel;

	public Incrementer(final PageParameters parameters) {

		super(parameters);
		if (getApplication().getDebugSettings().isDevelopmentUtilitiesEnabled()) {
			add(new DebugBar("dev"));
		} else {
			add(new EmptyPanel("dev").setVisible(false));
		}

		built = new Built();
		hasSlept = 0;

		woodLabel = new ResourceLabel("woodQuantity", built, ResourceType.Wood);
		metalLabel = new ResourceLabel("metalQuantity", built,
				ResourceType.Metal);
		add(woodLabel);
		add(metalLabel);
		metalLabel.setOutputMarkupId(true);
		woodLabel.setOutputMarkupId(true);

		add(new BuildingStats("buildings", built));

		manual();
	}

	private void manual() {
		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(10d)) {
			private static final long serialVersionUID = 1100349890208440665L;

			/**
			 * @see org.apache.wicket.ajax.AbstractAjaxTimerBehavior#onTimer(org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void onTimer(AjaxRequestTarget target) {
				built.incrementAll(0.01d);
				target.add(metalLabel);
				target.add(woodLabel);
			}
		});
	}

	void automatic() {
		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(500d)) {
			private static final long serialVersionUID = 1100349890208440665L;

			/**
			 * @see org.apache.wicket.ajax.AbstractAjaxTimerBehavior#onTimer(org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void onTimer(AjaxRequestTarget target) {
				built.incrementAll(0.5d);
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
				target.add(metalLabel);
				target.add(woodLabel);
			}
		});
	}

}