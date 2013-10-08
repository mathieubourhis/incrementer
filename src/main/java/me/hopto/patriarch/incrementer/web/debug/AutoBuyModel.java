package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class AutoBuyModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -2757209162337804931L;
	private IModel<DebugConf> debugConf;

	public AutoBuyModel(IModel<DebugConf> debugConf) {
		this.debugConf = debugConf;
	}

	@Override
	public String getObject() {
		return debugConf.getObject().autoBuyStatus ? "On" : "Off";
	}
}