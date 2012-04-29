package com.djdduty.wgc01.logic;

import com.djdduty.wgc01.core.GameObject;

public class PerPixCol {
	public static Vector2 intersectPoint = new Vector2();
	private static long lastTime;
	
	public static boolean intersect(GameObject entity1, GameObject Other, boolean pixelPerfect) {
		if(lastTime == 0)
			lastTime = System.nanoTime();
		if(entity1.getRect().intersects(Other.getRect())) {
			if(pixelPerfect) {
				if(entity1.getWidth() != entity1.getTexture().getImageWidth() || entity1.getHeight() != entity1.getTexture().getImageHeight())
					throw new IllegalArgumentException("Dimensions are bonked object 1");
				if(Other.getWidth() != Other.getTexture().getImageWidth() || Other.getHeight() != Other.getTexture().getImageHeight())
					throw new IllegalArgumentException("Dimensions are bonked object 2");
				try {
					Rectangle leftMost = entity1.getRect().getX() < Other.getRect().getX() ? entity1.getRect() : Other.getRect();
					Rectangle rightMost = Other.getRect().getX() < entity1.getRect().getX() ? entity1.getRect() : Other.getRect();
					
					Rectangle topMost = entity1.getRect().getY() < Other.getRect().getY() ? entity1.getRect() : Other.getRect();
					Rectangle bottomMost = Other.getRect().getY() < entity1.getRect().getY() ? entity1.getRect() : Other.getRect();
					
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