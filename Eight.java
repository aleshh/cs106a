package com.srccodes.example;

import acm.program.*;
import acm.graphics.*;

public class Eight extends GraphicsProgram {
	public void run() {
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;
		int tileOffset = TILE_GAP / 2;
		
		drawTile(centerX - TILE_WIDTH - tileOffset, centerY - TILE_HEIGHT - tileOffset);
		drawTile(centerX + tileOffset,              centerY - TILE_HEIGHT - tileOffset);
		drawTile(centerX - TILE_WIDTH - tileOffset, centerY + tileOffset);
		drawTile(centerX + tileOffset,              centerY + tileOffset);
	}
	
	private void drawTile(int originX, int originY) {
		
		GRect box = new GRect(TILE_WIDTH, TILE_HEIGHT);
		GLabel title = new GLabel("CS106A");
		title.setFont("SansSerif-24");

		int titleHeight = (int) title.getAscent();
		int titleWidth = (int) title.getWidth();
		
		int titleOriginX = originX + (TILE_WIDTH - titleWidth) / 2;
		int titleOriginY = originY + (TILE_HEIGHT + titleHeight) /2;
		
		add(box, originX, originY);
		add(title, titleOriginX, titleOriginY);
	}
	
	private final int TILE_HEIGHT = 100;
	private final int TILE_WIDTH = 250;
	private final int TILE_GAP = 50;
	
}
