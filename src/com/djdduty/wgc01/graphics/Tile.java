package com.djdduty.wgc01.graphics;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import com.djdduty.wgc01.core.GameObject;
import com.djdduty.wgc01.core.TextureManager;
import com.djdduty.wgc01.logic.Rectangle;

public class Tile extends GameObject {
	private int x, y, size = 32, xoff=0, yoff=0;
	private Texture texture;
	private int width, height;
	public Tile(String imageName, int x, int y) {
		this.x = x;
		this.y = y;
		width = size;
		height = size;
		setTexture(imageName);
	}
	
	public void setTexture(String name) {
		texture = TextureManager.get().get(name);
	}
	
	public void update() {
		
	}
	
	public void draw(int xOffset, int yOffset) {
	glEnable(GL_TEXTURE_2D);
		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0,0);
		glVertex2f(x+xOffset,y+yOffset);
		glTexCoord2f(1,0);
		glVertex2f(x+size+xOffset,y+yOffset);
		glTexCoord2f(1,1);
		glVertex2f(x+size+xOffset,y+size+yOffset);
		glTexCoord2f(0,1);
		glVertex2f(x+xOffset,y+size+yOffset);
		glEnd();
	glDisable(GL_TEXTURE_2D);
		xoff = xOffset;
		yoff = yOffset;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
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
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public Rectangle getRect() {
		Rectangle rect = new Rectangle();
		rect.setBounds(x, y, size, size);
		rect.setOwner(this);
		return rect;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getTexName() {
		return TextureManager.get().getName(texture);
	}
}
