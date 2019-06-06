package com.srccodes.example;

import acm.program.*;
import acm.graphics.*;

public class Six extends GraphicsProgram {
	public void run() {
		// draw a row
		for (int bricksInRow = BRICKS_IN_BASE; bricksInRow > 0; bricksInRow--) {
			int brickX = (getWidth() - bricksInRow * BRICK_WIDTH ) / 2; 
			int brickY = getHeight() - (BRICKS_IN_BASE - bricksInRow + 1) * BRICK_HEIGHT; 
			
			// draw each brick in the row
			for (int i = 0; i < bricksInRow; i++) {
				add(new GRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT));
				brickX = brickX + BRICK_WIDTH;
			}
		}
	}
	
	private final int BRICK_WIDTH = 30;
	private final int BRICK_HEIGHT = 12;
	private final int BRICKS_IN_BASE = 100;
}
