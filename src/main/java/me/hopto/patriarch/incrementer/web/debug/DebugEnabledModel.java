package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class DebugEnabledModel extends AbstractReadOnlyModel<Boolean> {
	private static final long serialVersionUID = 6393978634983422618L;
	private IModel<DebugConf> debugConf;

	public DebugEnabledModel(IModel<DebugConf> debugConf) {
		this.debugConf = debugConf;
	}

	public boolean isVisible() {
		return debugConf.getObject().debugEnabled;
	}

	@Override
	public Boolean getObject() {
		return debugConf.getObject().debugEnabled;
	}
}