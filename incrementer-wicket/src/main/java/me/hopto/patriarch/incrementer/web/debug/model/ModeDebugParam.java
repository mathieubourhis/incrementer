package me.hopto.patriarch.incrementer.web.debug.model;

public class ModeDebugParam implements DebugParam<Boolean> {
	private static final long	serialVersionUID	= 2525163762052701879L;

	String										title;
	Boolean										value;

	public ModeDebugParam(String title, Boolean value) {
		this.title = title;
		this.value = value;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Boolean getValue() {
		return value;

	}

	@Override
	public void less() {
		value = Boolean.FALSE;

	}

	@Override
	public void more() {
		value = Boolean.TRUE;
	}

	@Override
	public String toString() {
		return value ? getMoreTitle() : getLessTitle();
	}

	@Override
	public String getLessTitle() {
		return "Off";
	}

	@Override
	public String getMoreTitle() {
		return "On";
	}
}
