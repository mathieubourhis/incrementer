package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class MetalAutoClickModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -2770025853563062920L;
	private DebugConf debugConf;

	public MetalAutoClickModel(DebugConf debugConf) {
		this.debugConf = debugConf;
	}

	@Override
	public String getObject() {
		return String.valueOf(debugConf.metalAutoClick);
	}
}