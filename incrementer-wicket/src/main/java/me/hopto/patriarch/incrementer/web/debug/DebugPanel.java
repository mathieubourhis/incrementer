package me.hopto.patriarch.incrementer.web.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.hopto.patriarch.incrementer.app.data.Built;
import me.hopto.patriarch.incrementer.core.building.BuildingType;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;
import me.hopto.patriarch.incrementer.web.debug.model.DebugList;
import me.hopto.patriarch.incrementer.web.debug.model.DebugParam;
import me.hopto.patriarch.incrementer.web.debug.model.DebugParamModel;
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
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.time.Duration;

/**
 * my quest for a generic debug bar is just a giant pain in the ass after all.. Can't re-render only part of my repeater without going to astronomic length and rewriting the whole ListView class. Is it worth it ? i'll just take the best of both worlds. Static html / css, dynamic Java. And come back to this task after having done some significant improvements on the game."
 */
@SuppressWarnings("rawtypes")
public class DebugPanel extends Panel {
	private static final long											serialVersionUID	= -2062435641421139462L;
	private final List<Component>									componentsToRefreshInDebugMode;
	private final IModel<DebugList>								params;

	private final Map<String, ResourceReference>	resources;
	private long																	hasSlept;

	public DebugPanel(String id, final IModel<Built> built) {
		super(id);
		this.params = new Model<DebugList>(new DebugList());
		resources = new HashMap<String, ResourceReference>();
		componentsToRefreshInDebugMode = new ArrayList<Component>();
		hasSlept = 0;
		add(AttributeModifier.replace("class", "incrementerDebugBar"));

		build(params.getObject());

		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(200.0d)) {
			private static final long	serialVersionUID	= 1100349890208440665L;

			@Override
			protected void onTimer(AjaxRequestTarget target) {
				if (this.getComponent().isVisible()) {
					for (Component component : componentsToRefreshInDebugMode) {
						target.add(component);
					}
				}
			}
		});

		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(200d)) {
			private static final long	serialVersionUID	= -1487136610861370272L;

			@Override
			protected void onTimer(AjaxRequestTarget target) {
				hasSlept += 200;
				if (params.getObject().getAutoClickParam().getValue()) {
					for (int i = 0; i < params.getObject().getFoodParam().getValue(); i++)
						built.getObject().incrementOne(ResourceType.Food);
					for (int i = 0; i < params.getObject().getWoodParam().getValue(); i++)
						built.getObject().incrementOne(ResourceType.Wood);
					for (int i = 0; i < params.getObject().getMetalParam().getValue(); i++)
						built.getObject().incrementOne(ResourceType.Metal);
					for (int i = 0; i < params.getObject().getToolParam().getValue(); i++)
						built.getObject().incrementOne(ResourceType.Tool);
				}

				if (params.getObject().getAutoBuyParam().getValue()) {
					if (hasSlept % 600 == 400) built.getObject().levelUp(BuildingType.BlackSmith);
					if (hasSlept % 500 == 400) built.getObject().levelUp(BuildingType.Inventor);
					if (hasSlept % 600 == 400) built.getObject().levelUp(BuildingType.Miner);
					if (hasSlept % 500 == 400) built.getObject().levelUp(BuildingType.MetalDigger);
					if (hasSlept % 500 == 300) built.getObject().levelUp(BuildingType.LumberJack);
					if (hasSlept % 500 == 100) built.getObject().levelUp(BuildingType.WoodGatherer);
					if (hasSlept % 300 == 200) built.getObject().levelUp(BuildingType.BerryPicker);
					if (hasSlept % 300 == 100) built.getObject().levelUp(BuildingType.FisherMan);
				}
			}
		});
	}

	private void build(DebugList params) {
		WebMarkupContainer actionRow = new WebMarkupContainer("resourcerowAction");
		actionRow.add(AttributeModifier.replace("class", "resourcerow"));

		WebMarkupContainer reourcesTable = new WebMarkupContainer("resourcesTable");
		reourcesTable.add(AttributeModifier.replace("class", "resources"));
		reourcesTable.add(actionRow);

		for (final DebugParam param : params) {
			String lessTitle = param.getLessTitle();
			ResourceReference lessImage = resources.get(lessTitle);
			if (lessImage == null) {
				lessImage = getBuildingImage(lessTitle);
				resources.put(lessTitle, lessImage);
			}
			String moreTitle = param.getMoreTitle();
			ResourceReference moreImage = resources.get(moreTitle);
			if (moreImage == null) {
				moreImage = getBuildingImage(moreTitle);
				resources.put(moreTitle, moreImage);
			}

			String title = param.getTitle();

			Image less = new Image("less" + title, lessImage);
			less.add(new AjaxEventBehavior("onclick") {
				private static final long	serialVersionUID	= 7136318411468165625L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {
					param.less();
				}
			});
			actionRow.add(less);

			Image more = new Image("more" + title, moreImage);
			more.add(new AjaxEventBehavior("onclick") {
				private static final long	serialVersionUID	= 7136318411468165625L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {
					param.more();
				}
			});
			actionRow.add(more);

			Label value = new Label("action" + title, new DebugParamModel(param));
			value.setOutputMarkupId(true);
			actionRow.add(value);
			componentsToRefreshInDebugMode.add(value);
		}
		add(reourcesTable);
	}

	@Override
	public void renderHead(final IHeaderResponse response) {
		response.render(CssHeaderItem.forReference(new PackageResourceReference(DebugPanel.class, "DebugPanel.css")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(DebugPanel.class, "DebugPanel.js")));
	}

	final ResourceReference getBuildingImage(final String label) {
		return new ResourceReference(label) {
			private static final long	serialVersionUID	= -548600298151897112L;

			@Override
			public IResource getResource() {
				return new DefaultButtonImageResource(label);
			}
		};
	}
}
