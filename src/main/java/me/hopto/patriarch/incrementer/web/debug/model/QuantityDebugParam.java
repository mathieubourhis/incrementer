package me.hopto.patriarch.incrementer.web.debug.model;

public class QuantityDebugParam implements DebugParam<Integer> {
	private static final long serialVersionUID = -613586970847900362L;

	String title;
	Integer value;

	public QuantityDebugParam(String title, Integer value) {
		this.title = title;
		this.value = value;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Integer getValue() {
		return value;

	}

	@Override
	public void less() {
		if (value > 0) {
			value--;
		}

	}

	@Override
	public void more() {
		value++;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public String getLessTitle() {
		return "-";
	}

	@Override
	public String getMoreTitle() {
		return "+";
	}
}
