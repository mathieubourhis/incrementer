package me.hopto.patriarch.incrementer.app.admin;

import java.util.Map;

public abstract class Mapper<T> {

	public abstract T map(Class<T> classToMap, Map<String, String> toMap);
}
