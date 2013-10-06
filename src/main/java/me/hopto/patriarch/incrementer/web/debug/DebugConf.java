package me.hopto.patriarch.incrementer.web.debug;

import java.io.Serializable;

public class DebugConf implements Serializable {
	private static final long serialVersionUID = -6410721397290452179L;
	public int foodAutoClick;
	public int woodAutoClick;
	public int metalAutoClick;
	public int toolAutoClick;
	public boolean autoClickStatus;
	public boolean autoBuyStatus;
	public boolean debugEnabled;
}
