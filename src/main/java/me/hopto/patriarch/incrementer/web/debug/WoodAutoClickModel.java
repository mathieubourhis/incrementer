package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class WoodAutoClickModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -4781088821726816087L;
	private IModel<DebugConf> debugConf;

	public WoodAutoClickModel(IModel<DebugConf> debugConf) {
		this.debugConf = debugConf;
	}

	@Override
	public String getObject() {
		return String.valueOf(debugConf.getObject().woodAutoClick);
	}
}