package me.hopto.patriarch.incrementer.web.debug.model;

import org.apache.wicket.model.AbstractReadOnlyModel;

@SuppressWarnings("rawtypes")
public class DebugParamModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = -2757209162337804931L;
	private DebugParam debugParam;

	public DebugParamModel(DebugParam param) {
		this.debugParam = param;
	}

	@Override
	public String getObject() {
		return debugParam.toString();
	}
}