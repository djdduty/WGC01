package com.djdduty.wgc01.core;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureManager extends ResourceManager<Texture> {
	private static TextureManager instance;
	
	public static TextureManager get() {
		if(instance == null)
			instance = new TextureManager();
		
		return instance;
	}
	
	private TextureManager() {}
	
	public Texture extract(String path) {
		path = path.replace('\\', '/');
				
		if(path.startsWith("/"))
			path = path.substring(1);
		
		try{
			return TextureLoader.getTexture(path.substring(path.lastIndexOf('.')+1).toUpperCase(),TextureManager.class.getClassLoader().getResourceAsStream(path));
		}
		catch(Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
}
