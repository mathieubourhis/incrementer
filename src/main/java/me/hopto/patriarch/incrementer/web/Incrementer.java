package me.hopto.patriarch.incrementer.web;

import java.util.ArrayList;
import java.util.List;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.data.building.BuildingType;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;
import me.hopto.patriarch.incrementer.web.components.ResourceLabel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.time.Duration;

public class Incrementer extends WebPage {
	private static final long serialVersionUID = -5659419987245005726L;

	private Built built;
	long hasSlept;
	boolean autoManualClick = false;

	private List<Component> componentsToRefresh;

	public Incrementer(final PageParameters parameters) {

		super(parameters);
		if (getApplication().getDebugSettings().isDevelopmentUtilitiesEnabled()) {
			add(new DebugBar("dev"));
		} else {
			add(new EmptyPanel("dev").setVisible(false));
		}

		built = new Built();
		componentsToRefresh = new ArrayList<Component>();

		for (ResourceType resourceType : ResourceType.values()) {
			addResource(built, resourceType);
		}

		for (BuildingType buildingType : BuildingType.values()) {
			addBuilding(built, buildingType);
		}

		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(10d)) {
			private static final long serialVersionUID = 1100349890208440665L;

			/**
			 * @see org.apache.wicket.ajax.AbstractAjaxTimerBehavior#onTimer(org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void onTimer(AjaxRequestTarget target) {
				built.incrementAll(0.01d);
				refreshComponents(target);
			}
		});

		automatic();
	}

	void automatic() {
		hasSlept = 0;

		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(200d)) {
			private static final long serialVersionUID = -1487136610861370272L;

			/**
			 * @see org.apache.wicket.ajax.AbstractAjaxTimerBehavior#onTimer(org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void onTimer(AjaxRequestTarget target) {
				hasSlept += 200;
				if (autoManualClick) {
					built.incrementOne(ResourceType.Food);
					built.incrementOne(ResourceType.Food);
					built.incrementOne(ResourceType.Food);
					built.incrementOne(ResourceType.Wood);
					built.incrementOne(ResourceType.Metal);
					built.incrementOne(ResourceType.Tool);
				}
				if (hasSlept % 600 == 400) {
					built.levelUp(BuildingType.BlackSmith);
				}

				if (hasSlept % 500 == 400) {
					built.levelUp(BuildingType.Inventor);
				}

				if (hasSlept % 600 == 400) {
					built.levelUp(BuildingType.Miner);
				}

				if (hasSlept % 500 == 400) {
					built.levelUp(BuildingType.MetalDigger);
				}

				if (hasSlept % 500 == 300) {
					built.levelUp(BuildingType.LumberJack);
				}
				if (hasSlept % 500 == 100) {
					built.levelUp(BuildingType.WoodGatherer);
				}

				if (hasSlept % 300 == 200) {
					built.levelUp(BuildingType.BerryPicker);
				}
				if (hasSlept % 300 == 100) {
					built.levelUp(BuildingType.FisherMan);
				}

			}

		});
	}

	private void refreshComponents(AjaxRequestTarget target) {
		for (Component component : componentsToRefresh) {
			target.add(component);
		}
	}

	private void addResource(final Built built, final ResourceType resourceType) {
		Image image = new Image(resourceType.name(),
				getBuildingImage(resourceType.name()));
		image.setOutputMarkupId(true);
		image.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				built.incrementOne(resourceType);
			}
		});
		add(image);

		ResourceLabel resourceLabel = new ResourceLabel(resourceType.name()
				+ "Quantity", built, resourceType);
		resourceLabel.setOutputMarkupId(true);
		componentsToRefresh.add(resourceLabel);
		add(resourceLabel);
	}

	private void addBuilding(final Built built, final BuildingType buildingType) {
		Image image = new Image(buildingType.name(),
				getBuildingImage(buildingType.name()));
		image.setOutputMarkupId(true);
		image.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				built.levelUp(buildingType);
			}
		});
		add(image);
		Label level = new Label(buildingType.name() + "Level", new LevelModel(
				built, buildingType));
		level.setOutputMarkupId(true);
		add(level);
		componentsToRefresh.add(level);
		for (ResourceType resourceType : ResourceType.values()) {
			Label label = new Label(buildingType.name() + resourceType.name()
					+ "Cost", new ResourceModel(built, resourceType,
					buildingType));
			label.setOutputMarkupId(true);
			add(label);
			componentsToRefresh.add(label);
		}
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

	// TODO Put Models in other files.
	// TODO store ref of items in constructor instead of searching for them
	// every 10 millisecs
	private static class ResourceModel extends AbstractReadOnlyModel<String> {
		private static final long serialVersionUID = -5278580199867202676L;
		private Built built;
		private ResourceType resourceType;
		private BuildingType buildingType;

		public ResourceModel(Built built, ResourceType resourceType,
				BuildingType buildingType) {
			this.built = built;
			this.resourceType = resourceType;
			this.buildingType = buildingType;
		}

		@Override
		public String getObject() {
			return built.getResourceCostForBuilding(resourceType, buildingType);
		}
	}

	// TODO Put Models in other files.
	// TODO store ref of items in constructor instead of searching for them
	// every 10 millisecs
	private static class LevelModel extends AbstractReadOnlyModel<String> {

		private static final long serialVersionUID = 8749860115221101495L;
		private Built built;
		private BuildingType buildingType;

		public LevelModel(Built built, BuildingType buildingType) {
			this.built = built;
			this.buildingType = buildingType;
		}

		@Override
		public String getObject() {
			return built.getLevelForBuilding(buildingType);
		}
	}
}