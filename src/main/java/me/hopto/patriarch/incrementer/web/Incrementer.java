package me.hopto.patriarch.incrementer.web;

import java.util.ArrayList;
import java.util.List;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.data.building.BuildingType;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;
import me.hopto.patriarch.incrementer.web.components.BuildingStats;
import me.hopto.patriarch.incrementer.web.components.ResourceLabel;

import org.apache.wicket.Component;
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

	// private ResourceLabel woodLabel;
	//
	// private ResourceLabel metalLabel;

	private List<Component> components;

	private BuildingStats buildingStats;

	public Incrementer(final PageParameters parameters) {

		super(parameters);
		if (getApplication().getDebugSettings().isDevelopmentUtilitiesEnabled()) {
			add(new DebugBar("dev"));
		} else {
			add(new EmptyPanel("dev").setVisible(false));
		}

		built = new Built();
		hasSlept = 0;
		//
		// woodLabel = new ResourceLabel("woodQuantity", built,
		// ResourceType.Wood);
		// metalLabel = new ResourceLabel("metalQuantity", built,
		// ResourceType.Metal);
		// add(woodLabel);
		// add(metalLabel);
		// metalLabel.setOutputMarkupId(true);
		// woodLabel.setOutputMarkupId(true);

		components = new ArrayList<Component>();
		for (ResourceType resourceType : ResourceType.values()) {
			ResourceLabel resourceLabel = new ResourceLabel(resourceType.name()
					+ "Quantity", built, resourceType);
			resourceLabel.setOutputMarkupId(true);
			components.add(resourceLabel);
			add(resourceLabel);
		}

		buildingStats = new BuildingStats("buildings", built);
		buildingStats.setOutputMarkupId(true);
		add(buildingStats);

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
				for (Component component : components) {
					target.add(component);
				}
				target.add(buildingStats);
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
					built.levelUp(BuildingType.Miner);
				} else if (hasSlept % 600 == 400) {
					built.levelUp(BuildingType.LumberJack);
				}
				for (Component component : components) {
					target.add(component);
				}
				target.add(buildingStats);
			}
		});
	}

}