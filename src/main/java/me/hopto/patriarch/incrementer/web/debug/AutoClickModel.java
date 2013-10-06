package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class AutoClickModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -4558606815496303188L;
	private DebugConf debugConf;

	public AutoClickModel(DebugConf debugConf) {
		this.debugConf = debugConf;
	}

	@Override
	public String getObject() {
		return debugConf.autoClickStatus ? "On" : "Off";
	}
}