package me.hopto.patriarch.incrementer.web.components.resource;

import me.hopto.patriarch.incrementer.core.Built;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class ResourceLabel extends Label {
	private static final long	serialVersionUID	= -4817416546871911593L;

	public ResourceLabel(String id, Built built, ResourceType resourceType) {
		super(id, new ResourceModel(built, resourceType));
	}

	private static class ResourceModel extends AbstractReadOnlyModel<String> {
		private static final long	serialVersionUID	= -5278580199867202676L;
		private Built							built;
		private ResourceType			resourceType;

		public ResourceModel(Built built, ResourceType resourceType) {
			this.built = built;
			this.resourceType = resourceType;
		}

		@Override
		public String getObject() {
			return built.getFormattedResourceQuantity(resourceType);
		}
	}
}
