package com.djdduty.wgc01.logic;

public class Vector2 {
	private double x, y;
	
	public Vector2() {
		this(0,0);
	}
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double x() {
		return x;
	}
	
	public void x(double x) {
		this.x = x;
	}
	
	public double y() {
		return y;
	}
	
	public void y(double y) {
		this.y = y;
	}
	
	public double magnitude() {
		return Math.sqrt(x*x + y*y);
	}
	
	public void normalize() {
		double m = magnitude();
		x = x/m;
		y = y/m;
	}
	
	public double dot(Vector2 v) {
		return x * v.x + y * v.y;
	}
	
	public double angle() {
		return Math.atan2(y, x);
	}
	
	public double angle(Vector2 v) {
		return Math.acos(dot(v) / (magnitude() * v.magnitude()));
	}
}
