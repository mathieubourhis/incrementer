package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class AutoClickModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -4558606815496303188L;
	private IModel<DebugConf> debugConf;

	public AutoClickModel(IModel<DebugConf> debugConf) {
		this.debugConf = debugConf;
	}

	@Override
	public String getObject() {
		return debugConf.getObject().autoClickStatus ? "On" : "Off";
	}
}