package com.djdduty.wgc01.game;

import java.util.ArrayList;

import com.djdduty.wgc01.graphics.Tile;

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
		Tiles.add(tile);	
	}
	
	public void remove(Tile t) {
		Tiles.remove(t);
	}
	
	public void draw() {
		for(Tile t : Tiles) {
			t.draw();
		}
	}
	
	public int getListSize() {
		return Tiles.size();
	}
	
	public Tile tileHere(int x, int y) {
		for(int i = 0; i < Tiles.size(); i++) {
			if(Tiles.get(i).getX() == x && Tiles.get(i).getY() == y) {
				return Tiles.get(i);
			}else {
				return null;
			}
		}
		return null;
	}
}
