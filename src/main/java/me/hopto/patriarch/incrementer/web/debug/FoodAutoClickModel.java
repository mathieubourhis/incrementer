package me.hopto.patriarch.incrementer.web.debug;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class FoodAutoClickModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -517305296965684717L;
	private DebugConf debugConf;

	public FoodAutoClickModel(DebugConf debugConf) {
		this.debugConf = debugConf;
	}

	public boolean isVisible() {
		return debugConf.debugEnabled;
	}

	@Override
	public String getObject() {
		return String.valueOf(debugConf.foodAutoClick);
	}
}