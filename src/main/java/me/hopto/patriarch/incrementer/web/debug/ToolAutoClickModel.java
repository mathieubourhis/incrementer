package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class ToolAutoClickModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = 7390992113488039089L;
	private IModel<DebugConf> debugConf;

	public ToolAutoClickModel(IModel<DebugConf> debugConf) {
		this.debugConf = debugConf;
	}

	@Override
	public String getObject() {
		return String.valueOf(debugConf.getObject().toolAutoClick);
	}
}