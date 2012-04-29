package com.djdduty.wgc01.logic;

import com.djdduty.wgc01.core.GameObject;

public class PerPixCol {
	public static Vector2 intersectPoint = new Vector2();
	
	public static boolean intersect(GameObject object1, GameObject object2, boolean pixelPerfect) {
		if(object1.getRect().intersects(object2.getRect())) {
			if(pixelPerfect) {
				if(object1.getWidth() != object1.getTexture().getImageWidth() || object1.getHeight() != object1.getTexture().getImageHeight())
					throw new IllegalArgumentException("Dimensions are bonked object 1");
				if(object2.getWidth() != object2.getTexture().getImageWidth() || object2.getHeight() != object2.getTexture().getImageHeight())
					throw new IllegalArgumentException("Dimensions are bonked object 2");
				try {
					Rectangle leftMost = object1.getRect().getX() < object2.getRect().getX() ? object1.getRect() : object2.getRect();
					Rectangle rightMost = object2.getRect().getX() < object1.getRect().getX() ? object1.getRect() : object2.getRect();
					
					Rectangle topMost = object1.getRect().getY() < object2.getRect().getY() ? object1.getRect() : object2.getRect();
					Rectangle bottomMost = object2.getRect().getY() < object1.getRect().getY() ? object1.getRect() : object2.getRect();
					
					int left = (int) (rightMost.getX() - leftMost.getX());
					int top = (int) (bottomMost.getY() - topMost.getY());
					int width = Integer.valueOf((int) Math.round(leftMost.getWidth())) - left;
					int height = Integer.valueOf((int) Math.round(topMost.getHeight())) - top;
					byte[] leftData = leftMost.getOwner().getTexture().getTextureData();
					byte[] rightData = rightMost.getOwner().getTexture().getTextureData();		
					int leftTop = leftMost == topMost ? top : 0;
					int rightTop = rightMost == topMost ? top : 0;		
					for(int y = 0; y < height; y++) {
						for(int x = 0; x < width; x++) {
							int leftIdx = 3 + 4 * (x + left + (y + leftTop) * Integer.valueOf((int) Math.round(leftMost.getWidth())));
							int rightIdx = 3 + 4 * (x + (y + rightTop) * Integer.valueOf((int) Math.round(rightMost.getWidth())));	
							if(!((((int)leftData[leftIdx]) & 0xff) < 100 || (((int)rightData[rightIdx]) & 0xff) < 100)) {
								intersectPoint.x(leftMost.getX() + x + left);
								intersectPoint.y(leftMost.getY() + y + (leftMost == topMost ? top : 0));
								System.out.println("collision n' shit happens");
								System.out.println(intersectPoint.x() + ":" +  intersectPoint.y());
								return true;
							}
						}
					}
					return false;
				}finally {}
			}
			return true;
		}
		
		return false;
	}
}