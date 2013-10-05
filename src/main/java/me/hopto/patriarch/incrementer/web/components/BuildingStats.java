package me.hopto.patriarch.incrementer.web.components;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

public class BuildingStats extends WebMarkupContainer {
	private static final long serialVersionUID = 1L;

	private Image lumberJackBuilding;

	private Image minerBuilding;

	/**
	 * Constructor
	 */
	public BuildingStats(String id, final Built built) {
		super(id, new BuildingModel(built));

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

	final ResourceReference getBuildingImage(final String label) {
		return new ResourceReference(label) {
			private static final long serialVersionUID = -548600298151897112L;

			@Override
			public IResource getResource() {
				return new DefaultButtonImageResource(label);
			}
		};
	}

	private static class BuildingModel extends AbstractReadOnlyModel<String> {
		private static final long serialVersionUID = -5278580199867202676L;
		private Built built;
		private ResourceType resourceType;

		public BuildingModel(Built built) {
			this.built = built;
		}

		@Override
		public String getObject() {
			return null;
		}
	}
}