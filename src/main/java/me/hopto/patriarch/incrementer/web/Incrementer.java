package me.hopto.patriarch.incrementer.web;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;
import me.hopto.patriarch.incrementer.web.components.ResourceLabel;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.time.Duration;

public class Incrementer extends WebPage {
	private static final long serialVersionUID = -5659419987245005726L;

	private Built built;
	long hasSlept;

	private ResourceLabel woodLabel;

	private ResourceLabel metalLabel;

	private Image lumberJackBuilding;

	private Image minerBuilding;

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

		lumberJackBuilding = new Image("LumberJack",
				getBuildingImage("LumberJack"));
		minerBuilding = new Image("Miner", getBuildingImage("Miner"));
		add(lumberJackBuilding);
		add(minerBuilding);
		lumberJackBuilding.setOutputMarkupId(true);
		minerBuilding.setOutputMarkupId(true);
		lumberJackBuilding.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				built.levelUpLumberJack();
			}
		});

		minerBuilding.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = -3803612229594342066L;

			protected void onEvent(AjaxRequestTarget target) {
				built.levelUpMiner();
			}
		});

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

	final ResourceReference getBuildingImage(final String label) {
		return new ResourceReference(label) {
			private static final long serialVersionUID = -548600298151897112L;

			@Override
			public IResource getResource() {
				return new DefaultButtonImageResource(label);
			}
		};
	}
}