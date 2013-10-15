package me.hopto.patriarch.incrementer.app.admin;

import java.util.Map;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;
import org.apache.log4j.Logger;

// FIXME I need to add a spring / gin like... why not guice ? 
// I wan't to handle it with rules
public class ResourceMapper {
	private static Logger	logger	= Logger.getLogger(ResourceMapper.class);

	public double map(Map<String, String> toMap) {
		String savedQuantity = toMap != null ? toMap.get(ResourceType.Food.name()) : null;
		double mappedQuantity = 0.0d;
		if (savedQuantity != null) {
			mappedQuantity = Double.parseDouble(savedQuantity);
		}
		return mappedQuantity;
	}
}
