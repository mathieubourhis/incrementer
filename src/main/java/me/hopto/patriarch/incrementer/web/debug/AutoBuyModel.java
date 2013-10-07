package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class AutoBuyModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -2757209162337804931L;
	private DebugConf debugConf;

	public AutoBuyModel(DebugConf debugConf) {
		this.debugConf = debugConf;
	}

	@Override
	public String getObject() {
		return debugConf.autoBuyStatus ? "On" : "Off";
	}
}