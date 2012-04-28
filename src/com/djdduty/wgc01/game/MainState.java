package com.djdduty.wgc01.game;

import org.lwjgl.input.Keyboard;

import com.djdduty.wgc01.core.State;
import com.djdduty.wgc01.core.StateManager;
import com.djdduty.wgc01.core.TextureManager;
import com.djdduty.wgc01.graphics.Tile;

public class MainState implements State {
	private StateManager manager;
	public void start(StateManager manager) {
		this.manager = manager;
		this.manager.getGame().writeMessage("Switched to Main State");
	}

	public void update(long time) {
		if(Keyboard.isKeyDown(Keyboard.KEY_2))
			manager.changeState(new EditorState());
	}

	public void draw() {
		
	}

}
