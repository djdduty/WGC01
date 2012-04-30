package com.djdduty.wgc01.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.djdduty.wgc01.core.Level;
import com.djdduty.wgc01.core.State;
import com.djdduty.wgc01.core.StateManager;
import com.djdduty.wgc01.core.TextureManager;
import com.djdduty.wgc01.graphics.Tile;

public class EditorState implements State {
	private StateManager manager;
	private int selectedIndex, size = 32, xoff=0, yoff=0;
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
		TextureManager.get().add("wood", "res/tiles/log.png");
		textures.add("wood");
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
		level.draw(xoff, yoff);
		drawPreview();
	}
	
	public void drawPreview() {
		preview.setX((positionX-=positionX%size) - xoff-xoff%size);
		preview.setY((positionY-=positionY%size) - yoff-yoff%size);
		preview.setTexture(textures.get(selectedIndex));
		preview.update();
		preview.draw(xoff, yoff);
	}
	
	public void input() {
		while(Mouse.next()) {
			if(mouseEnabled) {
				if(Mouse.getEventButton() == 0) {
						level.addTile(new Tile(preview.getTexName(), preview.getX(), preview.getY()));
						manager.getGame().writeMessage("Made " + preview.getTexName() + " at " + preview.getX() + " " + preview.getY());
						mouseEnabled = false;
				}
			}else {
				mouseEnabled = true;
			}
		}
		if(Mouse.isButtonDown(1)) {
			level.removeUnder(positionX-xoff, positionY-yoff);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			level.saveLevel("src/res/levels/level2.xml");
			manager.getGame().writeMessage("Saved level!");
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_L)) {
			level.clearTiles();
			try {
				level.loadLevel(new FileInputStream("src/res/levels/level.xml"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

		if(Keyboard.isKeyDown(Keyboard.KEY_6)) {
			selectedIndex = 5;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			xoff -= 16;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			xoff += 16;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			yoff += 16;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			yoff -= 16;
		}
	}
}
