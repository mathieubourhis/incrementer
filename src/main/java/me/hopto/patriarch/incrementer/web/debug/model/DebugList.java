package me.hopto.patriarch.incrementer.web.debug.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

@SuppressWarnings("rawtypes")
public class DebugList extends ArrayList<IModel<DebugParam>> implements Serializable {

	private static final long serialVersionUID = 8736402874408053532L;
	private IModel<DebugParam> foodParam;
	private IModel<DebugParam> woodParam;
	private IModel<DebugParam> metalParam;
	private IModel<DebugParam> toolParam;
	private IModel<DebugParam> autoClickParam;
	private IModel<DebugParam> autoBuyParam;
//	private List<DebugParam> params;

	public DebugList () {
		
//		params = new ArrayList<DebugParam>();
		foodParam = new Model<DebugParam>(new QuantityDebugParam("Food",0));
		woodParam = new Model<DebugParam>(new QuantityDebugParam("Wood",0));
		metalParam = new Model<DebugParam>(new QuantityDebugParam("Metal",0));
		toolParam = new Model<DebugParam>(new QuantityDebugParam("Tool",0));
		autoClickParam = new Model<DebugParam>(new ModeDebugParam("AutoClick",false));
		autoBuyParam = new Model<DebugParam>(new ModeDebugParam("AutoBuy",false));
		
		add(metalParam);
		add(toolParam);
		add(autoClickParam);
		add(autoBuyParam);
		add(foodParam);
		add(woodParam);
	}
}
