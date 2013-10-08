package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class MetalAutoClickModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -2770025853563062920L;
	private IModel<DebugConf> debugConf;

	public MetalAutoClickModel(IModel<DebugConf> debugConf) {
		this.debugConf = debugConf;
	}

	@Override
	public String getObject() {
		return String.valueOf(debugConf.getObject().metalAutoClick);
	}
}