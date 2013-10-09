package me.hopto.patriarch.incrementer.web.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import me.hopto.patriarch.incrementer.data.resource.ResourceType;
import me.hopto.patriarch.incrementer.web.debug.model.DebugList;
import me.hopto.patriarch.incrementer.web.debug.model.DebugParam;
import me.hopto.patriarch.incrementer.web.debug.model.ModeDebugParam;
import me.hopto.patriarch.incrementer.web.debug.model.QuantityDebugParam;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.time.Duration;

@SuppressWarnings("rawtypes")
public class DebugPanel extends Panel {
	private static final long serialVersionUID = -2062435641421139462L;

	// private AjaxEventBehavior clickMinusButton;
	// private AjaxEventBehavior clickPlusButton;

	private final IModel<DebugConf> debugConf;
	private ResourceReference minusImage;
	private ResourceReference plusImage;
	private List<Component> componentsToRefreshInDebugMode;
	private List<IModel<DebugParam>> params;

	private final RefreshingView<DebugParam> testRefreshingepeatableAction;

	public DebugPanel(String id, final IModel<DebugConf> debugConf) {
		super(id, debugConf);
		// setMarkupId("incrementerDebugBar");
		setMarkupId(id);
		setOutputMarkupId(true);
		this.debugConf = debugConf;
		componentsToRefreshInDebugMode = new ArrayList<Component>();

		add(AttributeModifier.replace("class", "incrementerDebugBar"));

		minusImage = getBuildingImage("-");
		plusImage = getBuildingImage("+");

		// new AbstractReadOnlyModel<String>()
		// {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public String getObject()
		// {
		// return "incrementerDebugBar" + (DebugPanel.this.hasErrorMessage() ?
		// "Error" : "");
		// }
		// }));
		add(contentSection());
		
        params = new DebugList();
		final WebMarkupContainer aTag = new WebMarkupContainer("aTag" );
		aTag.setOutputMarkupId(true);
		testRefreshingepeatableAction = testRefreshingepeatableAction();
		aTag.add(testRefreshingepeatableAction);
		aTag.add(testRepeatable(params));
		add(aTag);
		
		
		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(200.0d)) {
			private static final long serialVersionUID = 1100349890208440665L;

			/**
			 * @see org.apache.wicket.ajax.AbstractAjaxTimerBehavior#onTimer(org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void onTimer(AjaxRequestTarget target) {
//				componentsToRefreshInDebugMode.clear();
//				getModelObject().add(new Object());
//	            removeAll();
				if (this.getComponent().isVisible()) {
					for (Component component : componentsToRefreshInDebugMode) {
						target.add(component);
					}
				}
				
//				Iterator<Component> debugPanel = testRefreshingepeatableAction.iterator();
//				while (debugPanel.hasNext()) {
//					Item component = (Item) debugPanel.next();
//					Iterator<Component> ressourceRow = component.iterator();
//					while (ressourceRow.hasNext()) {
//						Component tag = ressourceRow.next();
//						if (tag instanceof Label) {
//							target.add(tag);
//						}
//					}
//				}
				
				
//				for (Component component : testRefreshingepeatableAction)
//				{
//					if (component.getMarkupId().startsWith("action")) {
//						target.add(component);
//					}
//				}

			}
		});


	}

	

	private RefreshingView<DebugParam> testRefreshingepeatableAction() {        
		RefreshingView<DebugParam> repeatingssAction = new RefreshingView<DebugParam>("repeatingAction") {
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<IModel<DebugParam>> getItemModels() {
				return params.iterator();
			}

			@Override
			protected void populateItem(Item<DebugParam> item) {


				final DebugParam modelObject = item.getModelObject();
				
				// FIXME, is there a way to do it via model ?
//				Image less = new Image("less", new Model<DebugParam>(modelObject));
//				less.setImageResourceReference(getBuildingImage(modelObject.getLessTitle());
				Image less = new Image("less", getBuildingImage(modelObject.getLessTitle()));
//				less.setOutputMarkupId(true);
				less.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = 7136318411468165625L;

					protected void onEvent(AjaxRequestTarget target) {
						modelObject.less();
					}
				});
				item.add(less);

				Image more = new Image("more", getBuildingImage(modelObject.getMoreTitle()));
//				more.setOutputMarkupId(true);
				more.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = 7136318411468165625L;

					protected void onEvent(AjaxRequestTarget target) {
						modelObject.more();
					}
				});
				item.add(more);

				// FIXME Model ? LoadableDetachableModel ? No Model ? Other Model ? Findout
				Label value = new Label("action", new Model<String>(modelObject.toString()));
//				item.add(new Label("action", item.getModel()));
//				item.add(new Label("action", new LoadableDetachableModel<String>(item.getModelObject().toString()) {
//					private static final long serialVersionUID = 1L;
//					@Override
//					protected String load() {
//						return this.getObject();
//					}
//				}));
				value.setMarkupId("action"+modelObject.getTitle());
				value.setOutputMarkupId(true);
				item.add(value);
				componentsToRefreshInDebugMode.add(value);
				
			}
		};
		return repeatingssAction;
	}
	
	
	private Component testRepeatableAction(List<IModel<DebugParam>> params) {
		ListView<IModel<DebugParam>> repeatingAction = new ListView<IModel<DebugParam>>("repeatingAction", params) {
			private static final long serialVersionUID = -5704982492677831512L;

			@Override
			protected void populateItem(ListItem<IModel<DebugParam>> item) {

				final DebugParam modelObject = item.getModelObject().getObject();
				
				// FIXME, is there a way to do it via model ?
//				Image less = new Image("less", new Model<DebugParam>(modelObject));
//				less.setImageResourceReference(getBuildingImage(modelObject.getLessTitle());
				Image less = new Image("less", getBuildingImage(modelObject.getLessTitle()));
//				less.setOutputMarkupId(true);
				less.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = 7136318411468165625L;

					protected void onEvent(AjaxRequestTarget target) {
						modelObject.less();
					}
				});
				item.add(less);

				Image more = new Image("more", getBuildingImage(modelObject.getMoreTitle()));
//				more.setOutputMarkupId(true);
				more.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = 7136318411468165625L;

					protected void onEvent(AjaxRequestTarget target) {
						modelObject.more();
					}
				});
				item.add(more);

				// FIXME Model ? LoadableDetachableModel ? No Model ? Other Model ? Findout
				Label value = new Label("action", new Model<String>(modelObject.toString()));
//				item.add(new Label("action", item.getModel()));
//				item.add(new Label("action", new LoadableDetachableModel<String>(item.getModelObject().toString()) {
//					private static final long serialVersionUID = 1L;
//					@Override
//					protected String load() {
//						return this.getObject();
//					}
//				}));

				value.setOutputMarkupId(true);
//				item.add(value);
				componentsToRefreshInDebugMode.add(value);
			}
		};
		return repeatingAction;
	}

	private Component testRepeatable(List<IModel<DebugParam>> params) {
		RepeatingView repeating = new RepeatingView("repeating");
		for (IModel<DebugParam> title : params) {
			AbstractItem item = new AbstractItem(repeating.newChildId());
			item.add(new Label("title", title.getObject().getTitle()));
			repeating.add(item);
		}
		return repeating;
	}

	/**
	 * -- resources</br> ----- resourcerow --------- centeredTextCell ---------
	 * centeredTextCell --------- centeredTextCell ----- /resourcerow -----
	 * resourcerow --------- resource ------------- leftButton -------------
	 * centeredTextCell ------------- rightButton --------- /resource ---------
	 * resource ------------- leftButton ------------- centeredTextCell
	 * ------------- rightButton --------- /resource ----- /resourcerow --
	 * /resources
	 * 
	 * @return
	 */
	private Component contentSection() {
		WebMarkupContainer reourcesTable = getTable("resourcesTable");
		WebMarkupContainer titleRow = getRow("resourcerowTitle");

		reourcesTable.add(titleRow);

		for (ResourceType resourceType : ResourceType.values()) {
			// No need for models for static titles, i think.
			titleRow.add(getTitle(resourceType.name()));
		}
		titleRow.add(getTitle("AutoClick"));
		titleRow.add(getTitle("AutoBuy"));

		WebMarkupContainer actionRow = getRow("resourcerowAction");
		reourcesTable.add(actionRow);

		for (ResourceType resourceType : ResourceType.values()) {
			String name = resourceType.name();
			actionRow.add(getMinusImage(name));
			actionRow.add(getPlusImage(name));
		}

		Label foodAutoClick = new Label("FoodAutoClick",
				new FoodAutoClickModel(debugConf));
		foodAutoClick.setOutputMarkupId(true);
		actionRow.add(foodAutoClick);
		componentsToRefreshInDebugMode.add(foodAutoClick);

		Label woodAutoClick = new Label("WoodAutoClick",
				new WoodAutoClickModel(debugConf));
		woodAutoClick.setOutputMarkupId(true);
		actionRow.add(woodAutoClick);
		componentsToRefreshInDebugMode.add(woodAutoClick);

		Label metalAutoClick = new Label("MetalAutoClick",
				new MetalAutoClickModel(debugConf));
		metalAutoClick.setOutputMarkupId(true);
		actionRow.add(metalAutoClick);
		componentsToRefreshInDebugMode.add(metalAutoClick);

		Label toolAutoClick = new Label("ToolAutoClick",
				new ToolAutoClickModel(debugConf));
		toolAutoClick.setOutputMarkupId(true);
		actionRow.add(toolAutoClick);
		componentsToRefreshInDebugMode.add(toolAutoClick);

		Image autoClickOn = new Image("AutoClickOn", getBuildingImage("On"));
		autoClickOn.setOutputMarkupId(true);
		autoClickOn.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				debugConf.getObject().autoClickStatus = true;
			}
		});
		actionRow.add(autoClickOn);

		Image autoClickOff = new Image("AutoClickOff", getBuildingImage("Off"));
		autoClickOff.setOutputMarkupId(true);
		autoClickOff.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				debugConf.getObject().autoClickStatus = false;
			}
		});
		actionRow.add(autoClickOff);

		Image autoBuyOn = new Image("AutoBuyOn", getBuildingImage("On"));
		autoBuyOn.setOutputMarkupId(true);
		autoBuyOn.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				debugConf.getObject().autoBuyStatus = true;
			}
		});
		actionRow.add(autoBuyOn);

		Image autoBuyOff = new Image("AutoBuyOff", getBuildingImage("Off"));
		autoBuyOff.setOutputMarkupId(true);
		autoBuyOff.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				debugConf.getObject().autoBuyStatus = false;
			}
		});
		actionRow.add(autoBuyOff);

		Label autoClickStatus = new Label("AutoClickStatus",
				new AutoClickModel(debugConf));
		autoClickStatus.setOutputMarkupId(true);
		actionRow.add(autoClickStatus);
		componentsToRefreshInDebugMode.add(autoClickStatus);

		Label autoBuyStatus = new Label("AutoBuyStatus", new AutoBuyModel(
				debugConf));
		autoBuyStatus.setOutputMarkupId(true);
		actionRow.add(autoBuyStatus);
		componentsToRefreshInDebugMode.add(autoBuyStatus);

		//
		//
		// reourcesTable.add(AttributeModifier.append("style",
		// "display:none").setSeparator(";"));
		//
		// List<IDebugBarContributor> contributors;// = getContributors();
		//
		// reourcesTable.add(new ListView<IDebugBarContributor>("contributors",
		// contributors)
		// {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// protected void populateItem(final ListItem<IDebugBarContributor>
		// item)
		// {
		// IDebugBarContributor contrib = item.getModelObject();
		// Component comp = contrib.createComponent("contrib", DebugBar.this);
		// if (comp == null)
		// {
		// // some contributors only add information to the debug bar
		// // and don't actually create a contributed component
		// item.setVisibilityAllowed(false);
		// }
		// else
		// {
		// item.add(comp);
		// }
		// }
		// });
		//
		// reourcesTable.add(new Image("removeImg", new
		// PackageResourceReference(DebugBar.class, "remove.png")));

		return reourcesTable;
	}

	private Image getPlusImage(String name) {
		Image imagePlus = new Image(name + "Plus", plusImage);
		imagePlus.setOutputMarkupId(true);
		// imagePlus.add(getNewMinusOnClickBehaviour(debugConf));
		imagePlus.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			protected void onEvent(AjaxRequestTarget target) {
				String id = this.getComponent().getId();
				if ("FoodPlus".equals(id)) {
					debugConf.getObject().foodAutoClick++;
				} else if ("WoodPlus".equals(id)) {
					debugConf.getObject().woodAutoClick++;
				} else if ("MetalPlus".equals(id)) {
					debugConf.getObject().metalAutoClick++;
				} else if ("ToolPlus".equals(id)) {
					debugConf.getObject().toolAutoClick++;
				}
			}
		});
		return imagePlus;
	}

	private Image getMinusImage(String name) {
		Image imageMinus = new Image(name + "Minus", minusImage);
		imageMinus.setOutputMarkupId(true);
		imageMinus.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 7136318411468165625L;

			// FIXME BEUURK
			protected void onEvent(AjaxRequestTarget target) {
				String id = this.getComponent().getId();
				if ("FoodMinus".equals(id)
						&& debugConf.getObject().foodAutoClick > 0) {
					debugConf.getObject().foodAutoClick--;
				} else if ("WoodMinus".equals(id)
						&& debugConf.getObject().woodAutoClick > 0) {
					debugConf.getObject().woodAutoClick--;
				} else if ("MetalMinus".equals(id)
						&& debugConf.getObject().metalAutoClick > 0) {
					debugConf.getObject().metalAutoClick--;
				} else if ("ToolMinus".equals(id)
						&& debugConf.getObject().toolAutoClick > 0) {
					debugConf.getObject().toolAutoClick--;
				}
			}
		});
		return imageMinus;
	}

	private Label getTitle(String id) {
		Label aTitle = new Label("debugTitle" + id + "Label", id);
		aTitle.add(AttributeModifier.replace("class", "centeredTextCell"));
		return aTitle;
	}

	private WebMarkupContainer getTable(String id) {
		WebMarkupContainer reourcesTable = new WebMarkupContainer(id);
		reourcesTable.add(AttributeModifier.replace("class", "resources"));
		return reourcesTable;
	}

	private WebMarkupContainer getRow(String id) {
		WebMarkupContainer tableTitle = new WebMarkupContainer(id);
		tableTitle.add(AttributeModifier.replace("class", "resourcerow"));
		return tableTitle;
	}

	@Override
	public void renderHead(final IHeaderResponse response) {
		response.render(CssHeaderItem
				.forReference(new PackageResourceReference(DebugPanel.class,
						"DebugPanel.css")));
		response.render(JavaScriptHeaderItem
				.forReference(new JavaScriptResourceReference(DebugPanel.class,
						"DebugPanel.js")));
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
