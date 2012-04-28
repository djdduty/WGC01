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
		for(int i = 0; i < Tiles.size(); i++) {
			if (tile.getX() == Tiles.get(i).getX() && tile.getY() == Tiles.get(i).getY()) {
				Tiles.remove(i);
				Tiles.add(tile);
			}else {
				Tiles.add(tile);
			}
		}
		
	}
	
	public void draw() {
		for(int i = 0; i < Tiles.size(); i++) {
			Tiles.get(i).draw();
		}
	}
}
