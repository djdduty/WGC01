package com.djdduty.wgc01.core;

import org.newdawn.slick.opengl.Texture;

import com.djdduty.wgc01.logic.Rectangle;

public class GameObject {
	private Texture texture;
	private int x, y, width, height;
	public GameObject() {
		
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public Rectangle getRect() {
		Rectangle rect = new Rectangle();
		rect.setBounds(x, y, width, height);
		rect.setOwner(this);
		return rect;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
