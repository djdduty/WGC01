package com.djdduty.wgc01.core;

public interface State {
	public void start(StateManager manager);
	public void update(long time);
	public void draw();
}
