package com.djdduty.wgc01.logic;

import com.djdduty.wgc01.core.GameObject;

public class Rectangle {
	private double x, y, width, height;
	private GameObject owner;
	
	public Rectangle() {
		this(0,0,0,0);
	}
	
	public Rectangle(double x, double y, double width, double height) {
		setBounds(x,y,width,height);
	}
	
	public GameObject getOwner() {
		return owner;
	}
	
	public void setOwner(GameObject owner) {
		this.owner = owner;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public void setBounds(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersects(Rectangle other) {
		return !(x + width <= other.x ||
				 x >= other.x + other.width ||
				 y + height <= other.y ||
				 y >= other.y + other.height);
	}
}
