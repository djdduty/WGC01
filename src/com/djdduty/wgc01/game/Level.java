package com.djdduty.wgc01.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.djdduty.wgc01.graphics.Tile;

public class Level {
	public ArrayList<Tile> Tiles = new ArrayList<Tile>();
	private ArrayList<Tile> TilesRem = new ArrayList<Tile>();
	private int size = 32;
	public Level() {
		
	}
	
	public void loadLevel(String level) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(level);
	 
		  try {
	 
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("tile");
	 
			for (int i = 0; i < list.size(); i++) {
			   Element node = (Element) list.get(i);
			   Tiles.add(new Tile(node.getAttributeValue("texture"), Integer.valueOf(node.getAttributeValue("x")), Integer.valueOf(node.getAttributeValue("y"))));
			}
	 
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
	}
	
	public void saveLevel() {
		Document doc = new Document();
		Element root = new Element("tiles");
		for(Tile t : Tiles) {
			Element tile = new Element("tile");
				tile.setAttribute("texture", t.getTexName());
				tile.setAttribute("x", String.valueOf(t.getX()));
				tile.setAttribute("y", String.valueOf(t.getY()));
			root.addContent(tile);
		}
		doc.setRootElement(root);
		XMLOutputter outputter = new XMLOutputter();
		
		try {
			outputter.output(doc, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		for(Tile t: Tiles) {
			t.update();
		}
		
		for(Tile t : TilesRem) {
			Tiles.remove(t);
		}
		
		TilesRem.clear();
	}
	
	public Tile getTile(int index) {
		return Tiles.get(index);
	}
	
	public void addTile(Tile t) {	
		Tiles.add(t);
	}
	
	public void remove(Tile t) {
		TilesRem.add(t);
	}
	
	public void draw(int xoff,int  yoff) {
		for(Tile t : Tiles) {
			t.draw(xoff, yoff);
		}
	}
	
	public int getListSize() {
		return Tiles.size();
	}
	
	public void removeUnder(int x, int y) {
		for(Tile t : Tiles) {
			if(x > t.getX() && x < t.getX() + size && y > t.getY() && y < t.getY() + size) {
				remove(t);
				System.out.println("Removed a tile under " + x + " " + y);
			}
		}
	}
	
	public void clearTiles() {
		Tiles.clear();
	}
}
