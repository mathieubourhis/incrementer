package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class WoodAutoClickModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -4781088821726816087L;
	private DebugConf debugConf;

	public WoodAutoClickModel(DebugConf debugConf) {
		this.debugConf = debugConf;
	}

	@Override
	public String getObject() {
		return String.valueOf(debugConf.woodAutoClick);
	}
}