package com.djdduty.wgc01.core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Game {
	private StateManager sManager;
	//while reffering to ra4king's time shit I noticed this, seems important, I wrote it by hand instead of copying it, like using documentation so it does count! :P
	static {
		if(System.getProperty("os.name").startsWith("Win")) {
			new Thread() {
				{
					setDaemon(true);
					start();
				}
				
				public void run() {
					while(true) {
						try {
							Thread.sleep(Long.MAX_VALUE);
						}
						catch(Exception exc) {}
					}
				}
			};
		}
	}
	public Game(State firstState, int width, int height) throws LWJGLException {
		sManager = new StateManager(this, firstState);
		Display.setDisplayMode(new DisplayMode(width,height));
	}
	
	public void setTitle(String title) {
		Display.setTitle(title);
	}
	
	public void init() throws LWJGLException {
		Display.create();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 0.1, -1);
		glMatrixMode(GL_MODELVIEW);
		//enable shit!
		glEnable(GL_BLEND);
		glEnable(GL_ALPHA_TEST);
		//we want things to blend correctly
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		sManager.start();
		long lTime = System.nanoTime();
		int frames = 0;
		long lSecond = System.nanoTime();
		//main loop \o/
		while(!Display.isCloseRequested()) {
			long modern = System.nanoTime();
			glClear(GL_COLOR_BUFFER_BIT);//gotta clear that color bit shit
			if(Keyboard.isKeyDown(Keyboard.KEY_F1) || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				break;
			}
			long dTime = System.nanoTime() - lTime;
			//had to refer to some of ra4king's shit for this timer crap, too drunk
			lTime += 1e9/60.0;
			sManager.update(dTime);
			sManager.draw();
			Display.update();
			frames++;
			if(System.nanoTime()-lSecond >= 1e9) {
				writeInt(frames);
				frames = 0;
				lSecond += 1e9;
			}
			//Ra4King, I love you for this shit ^_^
			long sTime = (int)Math.round(1e9/60.0 - (System.nanoTime() - modern));
			if(sTime > 0) {
				long time = System.nanoTime();
				long timePassed;
				while((timePassed = System.nanoTime() - time) < sTime) {
					if(timePassed < sTime * 0.8)
						try{
							Thread.sleep(1);
						}
						catch(Exception exc) {}
					else
						Thread.yield();
				}
			}
		}
	}
	
	public StateManager getManager() {
		return sManager;
	}
	
	public void writeMessage(String string) {
		System.out.println(string);
		//TODO add to log
	}
	
	public void writeInt(int i) {//no idea how to merge these into one method/function like in c++
		System.out.println(i);
	}
}
