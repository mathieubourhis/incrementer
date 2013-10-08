package me.hopto.patriarch.incrementer.web.debug;

import java.util.List;

import me.hopto.patriarch.incrementer.data.resource.ResourceType;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.devutils.debugbar.IDebugBarContributor;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


public class DebugPanel extends Panel {

	public DebugPanel(String id, IModel<DebugConf> debugConf) {
		super(id, debugConf);
//		setMarkupId("incrementerDebugBar");
		setMarkupId(id);
		setOutputMarkupId(true);
		
		add(AttributeModifier.replace("class", "incrementerDebugBar"));
//		new AbstractReadOnlyModel<String>()
//		{
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public String getObject()
//			{
//				return "incrementerDebugBar" + (DebugPanel.this.hasErrorMessage() ? "Error" : "");
//			}
//		}));
		add(contentSection());
	}
	


	/**
	 * -- resources
	 * ----- resourcerow
	 * --------- centeredTextCell
	 * --------- centeredTextCell
	 * --------- centeredTextCell
	 * ----- /resourcerow
	 * ----- resourcerow
	 * --------- resource
	 * ------------- leftButton
	 * ------------- centeredTextCell
	 * ------------- rightButton
	 * --------- /resource
	 * --------- resource
	 * ------------- leftButton
	 * ------------- centeredTextCell
	 * ------------- rightButton
	 * --------- /resource
	 * ----- /resourcerow
	 * -- /resources
	 * @return
	 */
	private Component contentSection()
	{
		WebMarkupContainer reourcesTable = getTable("resourcesTable");
		WebMarkupContainer titleRow = getRow("resourcerowTitle");
		
		reourcesTable.add(titleRow);
		
		for (ResourceType resourceType : ResourceType.values()) {
			// No need for models for static titles, i think.
			titleRow.add(getTitle(resourceType.name()));
		}
		titleRow.add(getTitle("AutoClick"));
		titleRow.add(getTitle("AutoBuy"));
//		
//		
//		reourcesTable.add(AttributeModifier.append("style", "display:none").setSeparator(";"));
//
//		List<IDebugBarContributor> contributors;// = getContributors();
//
//		reourcesTable.add(new ListView<IDebugBarContributor>("contributors", contributors)
//		{
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			protected void populateItem(final ListItem<IDebugBarContributor> item)
//			{
//				IDebugBarContributor contrib = item.getModelObject();
//				Component comp = contrib.createComponent("contrib", DebugBar.this);
//				if (comp == null)
//				{
//					// some contributors only add information to the debug bar
//					// and don't actually create a contributed component
//					item.setVisibilityAllowed(false);
//				}
//				else
//				{
//					item.add(comp);
//				}
//			}
//		});
//
//		reourcesTable.add(new Image("removeImg", new PackageResourceReference(DebugBar.class, "remove.png")));

		return reourcesTable;
	}



	private Label getTitle(String id) {
		Label aTitle = new Label("debugTitle"+id+"Label",id);
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
	public void renderHead(final IHeaderResponse response)
	{
		response.render(CssHeaderItem.forReference(new PackageResourceReference(DebugPanel.class,
			"DebugPanel.css")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(
				DebugPanel.class, "DebugPanel.js")));
	}

}
