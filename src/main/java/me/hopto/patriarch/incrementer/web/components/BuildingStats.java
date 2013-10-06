package me.hopto.patriarch.incrementer.web.components;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.data.building.BuildingType;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

public class BuildingStats extends WebMarkupContainer {
	private static final long serialVersionUID = 1L;

	// private Image lumberJackBuilding;
	//
	// private Image minerBuilding;

	/**
	 * Constructor
	 */
	public BuildingStats(String id, final Built built) {
		// super(id, new BuildingModel(built));
		super(id, new Model<Built>(built));

		for (BuildingType buildingType : BuildingType.values()) {
			addBuilding(built, buildingType);
		}

		// lumberJackBuilding = new Image("LumberJack",
		// getBuildingImage("LumberJack"));
		// minerBuilding = new Image("Miner", getBuildingImage("Miner"));
		// add(lumberJackBuilding);
		// add(minerBuilding);
		// lumberJackBuilding.setOutputMarkupId(true);
		// minerBuilding.setOutputMarkupId(true);
		// lumberJackBuilding.add(new AjaxEventBehavior("onclick") {
		// private static final long serialVersionUID = 7136318411468165625L;
		//
		// protected void onEvent(AjaxRequestTarget target) {
		// built.levelUp(BuildingType.LumberJack);
		// }
		// });

		// minerBuilding.add(new AjaxEventBehavior("onclick") {
		// private static final long serialVersionUID = -3803612229594342066L;
		//
		// protected void onEvent(AjaxRequestTarget target) {
		// built.levelUp(BuildingType.Miner);
		// }
		// });
		// final int idx = index;
		// item.add(AttributeModifier.replace("class",
		// new AbstractReadOnlyModel<String>() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public String getObject() {
		// return (idx % 2 == 1) ? "even" : "odd";
		// }
		// }));

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
		for (ResourceType resourceType : ResourceType.values()) {
			Label label = new Label(buildingType.name() + resourceType.name()
					+ "Cost", new ResourceModel(built, resourceType,
					buildingType));
			label.setOutputMarkupId(true);
			add(label);
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
			return built.getFormattedResourceCostForBuilding(resourceType,
					buildingType);
		}
	}
	//
	// private static class BuildingModel extends AbstractReadOnlyModel<String>
	// {
	// private static final long serialVersionUID = -5278580199867202676L;
	// // private Built built;
	// // private ResourceType resourceType;
	//
	// public BuildingModel(Built built) {
	// this.built = built;
	// }
	//
	// @Override
	// public String getObject() {
	// return null;
	// }
	// }
}