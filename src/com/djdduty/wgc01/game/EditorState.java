package com.djdduty.wgc01.game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.djdduty.wgc01.core.State;
import com.djdduty.wgc01.core.StateManager;
import com.djdduty.wgc01.core.TextureManager;
import com.djdduty.wgc01.graphics.Tile;

public class EditorState implements State {
	private StateManager manager;
	private int selectedIndex, size = 16, xoff=0, yoff=0;
	private int positionX, positionY;
	private boolean mouseEnabled = true;
	private Level level;
	private Tile preview, test;
	private ArrayList<String> textures = new ArrayList<String>();
	
	public void start(StateManager manager) {
		this.manager = manager;
		
		//init textures
		TextureManager.get().add("grass", "res/tiles/grass.png");
		textures.add("grass");
		TextureManager.get().add("dirt", "res/tiles/dirt.png");
		textures.add("dirt");
		TextureManager.get().add("stone", "res/tiles/stone.png");
		textures.add("stone");
		TextureManager.get().add("black", "res/tiles/black.png");
		textures.add("black");
		TextureManager.get().add("air", "res/tiles/air.png");
		textures.add("air");
		//done with textures
		//init other shit
		selectedIndex = 0;
		level = new Level();
		preview = new Tile("grass", Math.round(Display.getWidth()/size), Math.round(Display.getHeight()/size));
		//done with it
		manager.getGame().writeMessage("Succesfully switched to Editor state");
	}

	public void update(long time) {
		positionX = Mouse.getX();
		positionY = Display.getHeight() - Mouse.getY() -1;
		input();
		level.update();
	}

	public void draw() {
		drawPreview();
		level.draw(xoff, yoff);
	}
	
	public void drawPreview() {
		preview.setX(positionX-=positionX%size);
		preview.setY(positionY-=positionY%size);
		preview.setTexture(textures.get(selectedIndex));
		preview.draw(0, 0);
	}
	
	public void input() {
		while(Mouse.next()) {
			if(mouseEnabled) {
				if(Mouse.getEventButton() == 0) {
						level.addTile(new Tile(textures.get(selectedIndex), positionX-=positionX%size, positionY-=positionY%size));
						manager.getGame().writeMessage("Made tile at " + (positionX-=positionX%size) + " " + (positionY-=positionY%size));
						mouseEnabled = false;
				}
			}else {
				mouseEnabled = true;
			}
		}
		if(Mouse.isButtonDown(1)) {
			level.removeUnder(positionX, positionY);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			level.saveLevel();
			manager.getGame().writeMessage("Saved level!");
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_1)) {
			selectedIndex = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_2)) {
			selectedIndex = 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_3)) {
			selectedIndex = 2;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_4)) {
			selectedIndex = 3;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_5)) {
			selectedIndex = 4;
		}
	}
}
