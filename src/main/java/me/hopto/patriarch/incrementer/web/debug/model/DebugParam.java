package me.hopto.patriarch.incrementer.web.debug.model;

import java.io.Serializable;

public interface DebugParam<T> extends Serializable {
	String getTitle();

	T getValue();

	String getLessTitle();

	void less();

	String getMoreTitle();

	void more();
}
