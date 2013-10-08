package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class FoodAutoClickModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -517305296965684717L;
	private IModel<DebugConf> debugConf;

	public FoodAutoClickModel(IModel<DebugConf> debugConf) {
		this.debugConf = debugConf;
	}

	public boolean isVisible() {
		return debugConf.getObject().debugEnabled;
	}

	@Override
	public String getObject() {
		return String.valueOf(debugConf.getObject().foodAutoClick);
	}
}