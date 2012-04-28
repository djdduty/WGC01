package com.djdduty.wgc01.game;

import com.djdduty.wgc01.core.State;
import com.djdduty.wgc01.core.StateManager;
import com.djdduty.wgc01.core.TextureManager;

public class MainState implements State {
	private StateManager manager;
	private Level level;
	public void start(StateManager manager) {
		this.manager = manager;
		manager.getGame().writeMessage("Switched to Main State");
		TextureManager.get().add("test", "res/tiles/test.png");
		level = new Level();
		level.addTile(new Tile("test", 32, 32));
	}

	public void update(long time) {
		
	}

	public void draw() {
		level.draw();
	}

}
