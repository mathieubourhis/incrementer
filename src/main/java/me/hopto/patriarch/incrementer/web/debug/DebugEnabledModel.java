package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class DebugEnabledModel extends AbstractReadOnlyModel<Boolean> {
	private static final long serialVersionUID = 6393978634983422618L;
	private DebugConf debugConf;

	public DebugEnabledModel(DebugConf debugConf) {
		this.debugConf = debugConf;
	}

	public boolean isVisible() {
		return debugConf.debugEnabled;
	}

	@Override
	public Boolean getObject() {
		return debugConf.debugEnabled;
	}
}