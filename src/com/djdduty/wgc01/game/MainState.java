package com.djdduty.wgc01.game;

import org.lwjgl.input.Keyboard;

import com.djdduty.wgc01.core.State;
import com.djdduty.wgc01.core.StateManager;
import com.djdduty.wgc01.core.TextureManager;
import com.djdduty.wgc01.graphics.Tile;
import com.djdduty.wgc01.logic.Entity;

public class MainState implements State {
	private StateManager manager;
	private Level level;
	private Entity player;
	public void start(StateManager manager) {
		this.manager = manager;
		this.manager.getGame().writeMessage("Switched to Main State");
		level = new Level();
		TextureManager.get().add("test", "res/tiles/test.png");
		level.loadLevel("src/res/levels/level.xml");
		player = new Entity("test", 100, 100, level, 2.0f, 0.25f);
		
	}

	public void update(long time) {
		if(Keyboard.isKeyDown(Keyboard.KEY_2))
			manager.changeState(new EditorState());
		player.update(time);
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.gx = 3;
		}else {
			if(!Keyboard.isKeyDown(Keyboard.KEY_A)) {
				player.gx = 0;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.gx = -3;
		}else {
			if(!Keyboard.isKeyDown(Keyboard.KEY_D)) {
				player.gx = 0;
			}
		}
	}

	public void draw() {
		level.draw(0,0);
		player.draw(0, 0);
	}

}
