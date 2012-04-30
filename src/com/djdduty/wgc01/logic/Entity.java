package com.djdduty.wgc01.logic;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import com.djdduty.wgc01.core.GameObject;
import com.djdduty.wgc01.core.Level;
import com.djdduty.wgc01.core.TextureManager;
import com.djdduty.wgc01.graphics.Tile;

public class Entity extends GameObject {
	public float gx = 0f, gy = 0f, friction, gravity, wt = 0;
	private Level level;
	private int x, y, width, height, oldx, oldy, xoff = 0, yoff = 0;
	private Texture texture;
	public Boolean gravityOn = false;
	
	public Entity(String texName, int startX, int startY, Level level, float friction, float gravity) {
		this.level = level;
		setTexture(texName);
		x = startX;
		y = startY;
		this.friction = friction;
		this.gravity = gravity;
	}
	
	public Rectangle getRect() {
		Rectangle rect = new Rectangle();
		rect.setBounds(x, y, width, height);
		rect.setOwner(this);
		return rect;
	}
	
	public void update(long time) {	
		checkGravity();
		oldx = x;
		oldy = y;
		x += gx;
		y += gy;
		if(x != oldx) wt += 0.25;
		checkCollision(level);
		gx = gx/friction;
	}
	
	private void checkCollision(Level level) {
		for(Tile t : level.Tiles) {
			collide(t, t.getX(), t.getY(), t.getSize(), t.getSize());
		}
	}

	private void collide(Object o, int ox, int oy, int ow, int oh) {
		int w = getWidth();
		int h = getHeight();
		Rectangle prT = new Rectangle();
		prT.setBounds((x)+xoff, y+yoff, width, 2);
		Rectangle prB = new Rectangle();
		prB.setBounds(x+xoff, y+yoff+height-2, width, 2);
		Rectangle prL = new Rectangle();
		prL.setBounds(x+xoff, y+yoff+ 2, 2, height-2);
		Rectangle prR = new Rectangle();
		prR.setBounds(x+xoff+width-2, y+yoff+ 2, 2, height-2);
		Rectangle oR = new Rectangle();
		oR.setBounds(ox+xoff, oy+yoff, ow, oh);
		
		if(prL.intersects(oR) || prR.intersects(oR)) {
			x = oldx;
			gx = 0;
			gravityOn = true;
		}
		
		if(prT.intersects(oR) || prB.intersects(oR)) {
			y = oldy;
			gy = 0;
			gravityOn = false;
		}else {
			gravityOn = true;
		}
		/*for(Tile t : level.Tiles)
		if(PerPixCol.intersect(this, t, true)) {
			x = oldx;
			y = oldy;
		}*/
	}

	private void checkGravity() {
		if(gravityOn) {
			gy += gravity;
			gravityOn = false;
		}
	}

	public void draw(int xOffset, int yOffset) {
	glEnable(GL_TEXTURE_2D);
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(x+xOffset,y+yOffset);
			glTexCoord2f(1,0);
			glVertex2f(x+getWidth()+xOffset,y+yOffset);
			glTexCoord2f(1,1);
			glVertex2f(x+getWidth()+xOffset,y+getHeight()+yOffset);
			glTexCoord2f(0,1);
			glVertex2f(x+xOffset,y+getHeight()+yOffset);
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
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setTexture(String name) {
		texture = TextureManager.get().get(name);
		width = getWidth();
		height = getHeight();
	}
	
	public int getWidth() {
		return texture.getTextureWidth();
	}
	
	public int getHeight() {
		return texture.getImageHeight();
	}
	
	public Texture getTexture() {
		return texture;
	}
}
