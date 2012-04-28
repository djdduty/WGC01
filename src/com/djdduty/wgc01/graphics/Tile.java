package com.djdduty.wgc01.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.awt.geom.Rectangle2D;
import org.newdawn.slick.opengl.Texture;
import com.djdduty.wgc01.core.TextureManager;

public class Tile {
	private int x, y, size = 16;
	private Texture texture;
	private Rectangle2D.Float bounds;
	
	public Tile(String imageName, int x, int y) {
		this.x = x;
		this.y = y;
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
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isCollision(Rectangle2D.Float rect) {
		if(bounds.intersects(rect)) return true;
		else return false;
	}
	
	public Rectangle2D.Float getRect() {
		return bounds;
	}
	
	public String getTexName() {
		return TextureManager.get().getName(texture);
	}
}
