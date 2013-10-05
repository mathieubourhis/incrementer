package me.hopto.patriarch.incrementer.web.components;

import me.hopto.patriarch.incrementer.data.Built;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class ResourceLabel extends Label {
	private static final long serialVersionUID = -4817416546871911593L;

	public ResourceLabel(String id, Built built) {
		super(id, new ResourceModel(built));
	}

	private static class ResourceModel extends AbstractReadOnlyModel<String> {
		private static final long serialVersionUID = -5278580199867202676L;
		private Built built;

		public ResourceModel(Built built) {
			this.built = built;
		}

		@Override
		public String getObject() {
			return built.toString();
		}
	}
}
