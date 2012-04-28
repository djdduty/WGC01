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
	
	public void addTile(int x, int y, String texture) {
		for(int i = 0; i < Tiles.size(); i++) {
			if (x == Tiles.get(i).getX() && y == Tiles.get(i).getY()) {
				Tiles.remove(i);
			}
		}
		Tiles.add(new Tile(texture, x, y));
	}
	
	public void draw() {
		for(int i = 0; i < Tiles.size(); i++) {
			Tiles.get(i).draw();
		}
	}
}
