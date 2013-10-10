package me.hopto.patriarch.incrementer.web.debug.model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class DebugList extends ArrayList<DebugParam> implements Serializable {
	private static final long serialVersionUID = 8736402874408053532L;
	
	private QuantityDebugParam foodParam;
	private QuantityDebugParam woodParam;
	private QuantityDebugParam metalParam;
	private QuantityDebugParam toolParam;
	private ModeDebugParam autoClickParam;
	private ModeDebugParam autoBuyParam;

	public DebugList () {
		foodParam = new QuantityDebugParam("Food",0);
		woodParam = new QuantityDebugParam("Wood",0);
		metalParam = new QuantityDebugParam("Metal",0);
		toolParam = new QuantityDebugParam("Tool",0);
		autoClickParam = new ModeDebugParam("AutoClick",false);
		autoBuyParam = new ModeDebugParam("AutoBuy",false);
		
		add(metalParam);
		add(toolParam);
		add(autoClickParam);
		add(autoBuyParam);
		add(foodParam);
		add(woodParam);
	}

	public QuantityDebugParam getFoodParam() {
		return foodParam;
	}

	public QuantityDebugParam getWoodParam() {
		return woodParam;
	}

	public QuantityDebugParam getMetalParam() {
		return metalParam;
	}

	public QuantityDebugParam getToolParam() {
		return toolParam;
	}

	public ModeDebugParam getAutoClickParam() {
		return autoClickParam;
	}

	public ModeDebugParam getAutoBuyParam() {
		return autoBuyParam;
	}
}
