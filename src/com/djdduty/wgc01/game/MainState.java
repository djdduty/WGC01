package com.djdduty.wgc01.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.djdduty.wgc01.core.Level;
import com.djdduty.wgc01.core.State;
import com.djdduty.wgc01.core.StateManager;
import com.djdduty.wgc01.core.TextureManager;
import com.djdduty.wgc01.logic.Entity;

public class MainState implements State {
	private StateManager manager;
	private Level level;
	private Entity player;
	private ArrayList<String> playerFrames = new ArrayList<String>();
	private int xoff=0, yoff=0;
	
	public void start(StateManager manager) {
		this.manager = manager;
		this.manager.getGame().writeMessage("Switched to Main State");
		level = new Level();
		//textures
		TextureManager.get().add("grass", "res/tiles/grass.png");
		TextureManager.get().add("dirt", "res/tiles/dirt.png");
		TextureManager.get().add("stone", "res/tiles/stone.png");
		TextureManager.get().add("black", "res/tiles/black.png");
		TextureManager.get().add("air", "res/tiles/air.png");
		TextureManager.get().add("wood", "res/tiles/log.png");
		//Done with textures
		//player frames
		TextureManager.get().add("player1", "res/player/player1.png");
		playerFrames.add("player1");
		//done with player frames
		try {
			level.loadLevel(new FileInputStream("src/res/levels/level.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player = new Entity(playerFrames.get(0), 100, 150, level, 2.0f, 0.25f);
		
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
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && player.gy == 0) {
			player.gy = -6.1f;
		}
		if(player.getX() > 400) xoff = ((int) (player.getX() - 400) *-1);
		if(player.getY() > 300) yoff = ((int) player.getY() -300) *-1;	
		if(player.getY() < 200) yoff = ((int) player.getY() -200) *-1;
	}

	public void draw() {
		level.draw(xoff,yoff);
		player.draw(xoff, yoff);
	}

}
