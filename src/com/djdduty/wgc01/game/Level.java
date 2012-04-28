package com.djdduty.wgc01.game;

import java.util.ArrayList;

public class Level {
	private ArrayList<Tile> Tiles = new ArrayList<Tile>();
	
	public Level() {
		
	}
	
	public void loadLevel() {
		
	}
	
	public void saveLevel() {
		
	}
	
	public Tile getTile(int index) {
		return Tiles.get(index);
	}
	
	public void addTile(Tile tile) {
		for(Tile t : Tiles) {
			if (tile.getX() == t.getX() && tile.getY() == t.getY()) {
				Tiles.remove(t);
				Tiles.add(tile);
			}else {
				Tiles.add(tile);
			}
		}
		
	}
	
	public void draw() {
		for(Tile t : Tiles) {
			t.draw();
		}
	}
	
	public int getListSize() {
		return Tiles.size();
	}
}
