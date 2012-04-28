package com.djdduty.wgc01.game;

import static org.lwjgl.opengl.GL11.*;

import java.awt.geom.Rectangle2D;
import org.newdawn.slick.opengl.Texture;
import com.djdduty.wgc01.core.TextureManager;

public class Tile {
	private int x, y, size = 16;
	private String textureName;
	private Texture texture;
	private Rectangle2D.Float bounds;
	
	public Tile(String imageName, int x, int y) {
		this.x = x;
		this.y = y;
		bounds.setRect(x, y, size, size);
		setTexture(textureName);
	}
	
	private void setTexture(String textureName) {
		texture = TextureManager.get().get(textureName);
		if(texture == null) throw new NullPointerException("Dumbass that texture is not found");
		else this.textureName = textureName;
	}

	public void draw() {
	bounds.setRect(x, y, size, size);
	glEnable(GL_TEXTURE_2D);
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(x,y);
			glTexCoord2f(1,0);
			glVertex2f(x+size,y);
			glTexCoord2f(1,1);
			glVertex2f(x+size,y+size);
			glTexCoord2f(0,1);
			glVertex2f(x,y+size);
		glEnd();
	glDisable(GL_TEXTURE_2D);
		
	}
	
	public String getTexName() {
		return textureName;
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
}
