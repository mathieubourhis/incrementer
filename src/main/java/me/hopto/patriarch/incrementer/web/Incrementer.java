package me.hopto.patriarch.incrementer.web;

import java.util.ArrayList;
import java.util.List;

import me.hopto.patriarch.incrementer.data.Built;
import me.hopto.patriarch.incrementer.data.building.BuildingType;
import me.hopto.patriarch.incrementer.data.resource.ResourceType;
import me.hopto.patriarch.incrementer.web.components.resource.ResourceLabel;
import me.hopto.patriarch.incrementer.web.components.resource.ResourceModel;
import me.hopto.patriarch.incrementer.web.debug.AutoBuyModel;
import me.hopto.patriarch.incrementer.web.debug.AutoClickModel;
import me.hopto.patriarch.incrementer.web.debug.DebugConf;
import me.hopto.patriarch.incrementer.web.debug.DebugEnabledModel;
import me.hopto.patriarch.incrementer.web.debug.DebugPanel;
import me.hopto.patriarch.incrementer.web.debug.FoodAutoClickModel;
import me.hopto.patriarch.incrementer.web.debug.MetalAutoClickModel;
import me.hopto.patriarch.incrementer.web.debug.ToolAutoClickModel;
import me.hopto.patriarch.incrementer.web.debug.WoodAutoClickModel;
import me.hopto.patriarch.incrementer.web.level.LevelModel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.time.Duration;

public class Incrementer extends WebPage {
	private static final long serialVersionUID = -5659419987245005726L;

	private Built built;
	long hasSlept;
	DebugConf debugConf;

	private List<Component> componentsToRefresh;
	private List<Component> componentsToRefreshInDebugMode;

	private WebMarkupContainer debugContainer;

	public Incrementer(final PageParameters parameters) {
		super(parameters);
		built = new Built();
		debugConf = new DebugConf(true, true);
		componentsToRefresh = new ArrayList<Component>();
		componentsToRefreshInDebugMode = new ArrayList<Component>();

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
				for (Component component : componentsToRefresh) {
					target.add(component);
				}
			}
		});

		if (getApplication().getDebugSettings().isDevelopmentUtilitiesEnabled()) {
			add(new DebugBar("dev"));
			add(new DebugPanel("debugPanel", new Model<DebugConf>(debugConf)));
		} else {
			add(new EmptyPanel("dev").setVisible(false));
			add(new EmptyPanel("debugPanel").setVisible(false));
		}
		
//		addDebugPanel();
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
				if (debugConf.autoClickStatus) {
					for (int i = 0; i < debugConf.foodAutoClick; i++)
						built.incrementOne(ResourceType.Food);
					for (int i = 0; i < debugConf.woodAutoClick; i++)
						built.incrementOne(ResourceType.Wood);
					for (int i = 0; i < debugConf.metalAutoClick; i++)
						built.incrementOne(ResourceType.Metal);
					for (int i = 0; i < debugConf.toolAutoClick; i++)
						built.incrementOne(ResourceType.Tool);
				}
				if (debugConf.autoBuyStatus) {
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
			}

		});
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

	// FIXME yes i actually throwed up writing all this..
	private void addDebugPanel() {
		debugContainer = new WebMarkupContainer("myDebugPanel",
				new DebugEnabledModel(debugConf));
		for (ResourceType resourceType : ResourceType.values()) {
			Image imageMinus = new Image(resourceType.name() + "Minus",
					getBuildingImage("-"));
			imageMinus.setOutputMarkupId(true);
			imageMinus.add(new AjaxEventBehavior("onclick") {
				private static final long serialVersionUID = 7136318411468165625L;

				// FIXME BEUURK
				protected void onEvent(AjaxRequestTarget target) {
					String id = this.getComponent().getId();
					if ("FoodMinus".equals(id) && debugConf.foodAutoClick > 0) {
						debugConf.foodAutoClick--;
					} else if ("WoodMinus".equals(id)
							&& debugConf.woodAutoClick > 0) {
						debugConf.woodAutoClick--;
					} else if ("MetalMinus".equals(id)
							&& debugConf.metalAutoClick > 0) {
						debugConf.metalAutoClick--;
					} else if ("ToolMinus".equals(id)
							&& debugConf.toolAutoClick > 0) {
						debugConf.toolAutoClick--;
					}
				}
			});
			debugContainer.add(imageMinus);

			Image imagePlus = new Image(resourceType.name() + "Plus",
					getBuildingImage("+"));
			imagePlus.setOutputMarkupId(true);
			imagePlus.add(new AjaxEventBehavior("onclick") {
				private static final long serialVersionUID = 7136318411468165625L;

				protected void onEvent(AjaxRequestTarget target) {
					String id = this.getComponent().getId();
					if ("FoodPlus".equals(id)) {
						debugConf.foodAutoClick++;
					} else if ("WoodPlus".equals(id)) {
						debugConf.woodAutoClick++;
					} else if ("MetalPlus".equals(id)) {
						debugConf.metalAutoClick++;
					} else if ("ToolPlus".equals(id)) {
						debugConf.toolAutoClick++;
					}
				}
			});
			debugContainer.add(imagePlus);
			// TODO Add a model here
			// Label autoClickQuantity = new Label(resourceType.name()
			// + "AutoClick");
			// autoClickQuantity.setOutputMarkupId(true);
			// debugContainer.add(autoClickQuantity);
			// componentsToRefreshInDebugMode.add(autoClickQuantity);
		}

		// TODO Add a model here
		Label foodAutoClick = new Label("FoodAutoClick",
				new FoodAutoClickModel(debugConf));
		foodAutoClick.setOutputMarkupId(true);
		debugContainer.add(foodAutoClick);
		componentsToRefreshInDebugMode.add(foodAutoClick);

		Label woodAutoClick = new Label("WoodAutoClick",
				new WoodAutoClickModel(debugConf));
		woodAutoClick.setOutputMarkupId(true);
		debugContainer.add(woodAutoClick);
		componentsToRefreshInDebugMode.add(woodAutoClick);

		Label metalAutoClick = new Label("MetalAutoClick",
				new MetalAutoClickModel(debugConf));
		metalAutoClick.setOutputMarkupId(true);
		debugContainer.add(metalAutoClick);
		componentsToRefreshInDebugMode.add(metalAutoClick);

		Label toolAutoClick = new Label("ToolAutoClick",
				new ToolAutoClickModel(debugConf));
		toolAutoClick.setOutputMarkupId(true);
		debugContainer.add(toolAutoClick);
		componentsToRefreshInDebugMode.add(toolAutoClick);

		Image autoClickOn = new Image("AutoClickOn", getBuildingImage("On"));
		autoClickOn.setOutputMarkupId(true);
		autoClickOn.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				debugConf.autoClickStatus = true;
			}
		});
		debugContainer.add(autoClickOn);

		Image autoClickOff = new Image("AutoClickOff", getBuildingImage("Off"));
		autoClickOff.setOutputMarkupId(true);
		autoClickOff.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				debugConf.autoClickStatus = false;
			}
		});
		debugContainer.add(autoClickOff);

		Image autoBuyOn = new Image("AutoBuyOn", getBuildingImage("On"));
		autoBuyOn.setOutputMarkupId(true);
		autoBuyOn.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				debugConf.autoBuyStatus = true;
			}
		});
		debugContainer.add(autoBuyOn);

		Image autoBuyOff = new Image("AutoBuyOff", getBuildingImage("Off"));
		autoBuyOff.setOutputMarkupId(true);
		autoBuyOff.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				debugConf.autoBuyStatus = false;
			}
		});
		debugContainer.add(autoBuyOff);

		Label autoClickStatus = new Label("AutoClickStatus",
				new AutoClickModel(debugConf));
		autoClickStatus.setOutputMarkupId(true);
		debugContainer.add(autoClickStatus);
		componentsToRefreshInDebugMode.add(autoClickStatus);

		Label autoBuyStatus = new Label("AutoBuyStatus", new AutoBuyModel(
				debugConf));
		autoBuyStatus.setOutputMarkupId(true);
		debugContainer.add(autoBuyStatus);
		componentsToRefreshInDebugMode.add(autoBuyStatus);

		debugContainer.add(new AbstractAjaxTimerBehavior(Duration
				.milliseconds(200.0d)) {
			private static final long serialVersionUID = 1100349890208440665L;

			/**
			 * @see org.apache.wicket.ajax.AbstractAjaxTimerBehavior#onTimer(org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void onTimer(AjaxRequestTarget target) {
				if (debugContainer.isVisible()) {
					for (Component component : componentsToRefreshInDebugMode) {
						target.add(component);
					}
				}
			}
		});
		add(debugContainer);
	}
}